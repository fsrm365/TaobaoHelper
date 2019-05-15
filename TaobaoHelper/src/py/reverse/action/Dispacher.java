package py.reverse.action;

import android.content.Context;
import android.util.Log;
import py.utils.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import com.taobao.taobao_8_6_10.Connection;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Administrator on 2017/1/13.
 */
public class Dispacher {
    public static Action handle(Context context, Connection connection, String request){
        Action action =null;
        long taskId=0;
        String act;
        Object args=null;
        try {
            JSONObject jsonObject=new JSONObject(request);
            taskId=jsonObject.getLong("id");
            act = jsonObject.getString("action");
            
            if(jsonObject.has("args")){
                args=jsonObject.get("args");
                
            }
            if(act.equals("CalsignAction")){
                action = new CalsignAction(context, connection, taskId, args);
            }
        }catch (Exception e){
            action = new ErrorAction(context, connection, -1, e.getMessage());
            Logger.log(e);
        }
        return action;
    }
}
