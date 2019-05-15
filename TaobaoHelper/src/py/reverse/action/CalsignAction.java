package py.reverse.action;

import java.net.URLDecoder;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.taobao.taobao_8_6_10.Connection;
import com.taobao.taobao_8_6_10.Interface;

import android.content.Context;
import py.utils.Logger;

public class CalsignAction extends Action{
	public CalsignAction(Context context, Connection connection, long taskId, Object args) {
		super(context, connection, taskId, args);
	}

	@Override
	public void run() {
		Logger.log("args:"+args);
		HashMap<String, String> hashMap = new Gson().fromJson((String)args, HashMap.class);
		Logger.log("hashMap:"+hashMap);
		String sign = Interface.calcXSign(hashMap);
		Logger.log("sign:" + sign);
		response(toJson(0, sign));
	}
	
	@Override
	protected String getAction() {
		return "CalsignAction";
	}
}
