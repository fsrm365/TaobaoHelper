package com.taobao.taobao_8_6_10;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import py.utils.Logger;

public class Server {
	
	private static Server instance = new  Server();
	private List<Connection> connections = new ArrayList<Connection>();
	private ListenThread listenThread;
	public static Server get(){
		return instance;
	}

	public void start() {
		// TODO Auto-generated method stub
		if(listenThread == null){
			listenThread = new ListenThread();
			listenThread.start();
		}
	}
	
	private class ListenThread extends Thread{

        private ServerSocket serverSocket;
     

        @Override
        public void interrupt() {
            super.interrupt();
            try{
                if(serverSocket!=null){
                    serverSocket.close();
                    Logger.log("close");
                }
            }catch (Exception e){
                //Log.d("msp", sw.toString());
                Logger.log(e);
            }

        }

        @Override
        public void run() {

            try{
                serverSocket=new ServerSocket();
                serverSocket.setReuseAddress(true);
                serverSocket.bind(new InetSocketAddress(2153));
                //serverSocket.setSoTimeout(10000);
                Logger.log("connecting");
                while(!isInterrupted()){
                    try {
                        Socket socket = serverSocket.accept();
                        Logger.log("connected");

                        Connection connection=new Connection(Server.this, socket);
                        connection.start();
                        connections.add(connection);

                    }catch (Exception e){
                    	Logger.log(e);
                    }
                }
            }catch (Exception e){
            	Logger.log(e);
                return;
            }

           

        }
    }

	public void removeConnection(Connection connection) {
		connections.remove(connection);
		
	}
}
