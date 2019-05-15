package py.reverse.action;

import org.json.JSONObject;

import com.taobao.taobao_8_6_10.Connection;

import android.content.Context;
import py.utils.Logger;

/**
 * Created by Administrator on 2017/1/16.
 */
public abstract class Action implements Runnable {
    protected Context context;
    protected Connection connection;
    protected long taskId;
    protected Object args;

    public Action(Context context, Connection connection, long taskId,Object args){
        this.context=context;
        this.connection=connection;
        this.taskId=taskId;
        this.args = args;
    }

    protected long getTaskId(){
        return taskId;
    }

    protected abstract String getAction();

    protected  String toJson(Object... objects){
        JSONObject jsonObject=new JSONObject();
        try{
            jsonObject.put("id", taskId);
            jsonObject.put("action", getAction());
            jsonObject.put("ret_code", objects[0]);
            jsonObject.put("ret_msg",objects[1]);
        }catch (Exception e){
            Logger.log(e);
        }
        return jsonObject.toString();
    }

    protected void response(String result){
        if(result==null || result.trim().equals("")) 
        	return;
        connection.onResponse(result);
    }

}
