package py.reverse.action;

import com.taobao.taobao_8_6_10.Connection;

import android.content.Context;



/**
 * Created by Administrator on 2017/2/8.
 */
public class ErrorAction extends Action {

    public ErrorAction(Context context, Connection connection, long taskId,Object args){
        super(context, connection, taskId, args);
    }

    @Override
    public void run() {
        response(toJson(-1, "Bad request"));
    }

	@Override
	protected String getAction() {
		return "ErrorAction";
	}

}

