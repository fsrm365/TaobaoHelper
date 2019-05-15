package com.taobao.taobao_8_6_10;

import android.content.Context;
import android.net.LocalSocket;
import android.util.Log;
import py.reverse.action.Action;
import py.reverse.action.Dispacher;
import py.utils.Logger;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2017/2/6.
 */
public class Connection {

    public final String id= UUID.randomUUID().toString();
    private Server server;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private ReceiveThread receiveThread;
    private SendThread sendThread;
    private LinkedBlockingQueue queue;
    private ExecutorService threadPool;
    private boolean isRunning;

    public Connection(Server sever, Socket socket){
        this.server=sever;
        this.socket=socket;
        queue=new LinkedBlockingQueue();
        threadPool= Executors.newFixedThreadPool(10);
    }

    public void start(){
        isRunning=true;
        try{
            is=socket.getInputStream();
            receiveThread=new ReceiveThread();
            receiveThread.start();

            os=socket.getOutputStream();
            sendThread=new SendThread();
            sendThread.start();

        }catch (Exception e){
            StringWriter sw=new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Log.d("msp", sw.toString());
        }
    }

    public void stop(){
        if(isRunning){
            try{
                if(receiveThread!=null) receiveThread.interrupt();
                if(sendThread!=null) sendThread.interrupt();
                if(is!=null) is.close();
                if(os!=null) os.close();
                if(socket!=null) socket.close();
                threadPool.shutdownNow();
                server.removeConnection(this);
                System.gc();
            }catch (Exception e){
                StringWriter sw=new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                Log.d("msp", sw.toString());
            }
            isRunning=false;
        }
    }

    public synchronized void onResponse(String result){
        if(queue==null || result==null) 
        	return;
        queue.add(result);
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(o==null) return false;
        if(getClass()!=o.getClass()) return false;
        final Connection connection=(Connection)o;
        if(!connection.id.equals(this.id)) return false;
        return true;
    }

    //接收线程
    private class ReceiveThread extends Thread{

        @Override
        public void run() {
            try{
                while(true) {
                    byte[] buffer = new byte[10240];
                    int n=is.read(buffer, 0, buffer.length);
                    if(n==-1){
                        Log.d("msp", "client is disconnected");
                        Connection.this.stop();
                        return;
                    }
                    String request=new String(buffer, 0, n);
                    Log.d("msp", "server receive:"+request);
                    
                    //根据请求内容，生成对应的model,交给线程池处理
                   Action action = Dispacher.handle(null, Connection.this, request);
                   if (action != null) {
                        //StatusMonitor.setStatus(StatusMonitor.STATUS_BUSY);
                        threadPool.execute(action);
                    }
                }
            }catch (Exception e){
                Logger.log(e);
                Connection.this.stop();
            }
        }
    }

    //写线程
    private class SendThread extends Thread{

        @Override
        public void run() {
            try{
                while (true) {
                    String result = (String) queue.take();
                    //byte[] length=ClassParseUtil.int2Bytes(result.getBytes().length);
                    //os.write(length);
                    os.write(result.getBytes());
                    os.flush();
                }
            }catch (Exception e){
                Log.d("msp", "server send exception:"+e.toString());
                Connection.this.stop();
            }
        }
    }

}
