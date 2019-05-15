package py.reverse.action;

import java.util.HashMap;

import com.taobao.taobao_8_6_10.Interface;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import py.utils.Logger;

public class ActionReceiver extends BroadcastReceiver {

	public static String INTENT_ACTION = "py.send";
	public static String REQUEST = "request";
	
	@Override
	public void onReceive(final Context arg0, Intent arg1) {
		try {
			if (INTENT_ACTION.equals(arg1.getAction())) {
				String request = arg1.getStringExtra(REQUEST);
				Logger.log(request);
				
//				if(request.equals("calXSign")){
//					String data = "eyJhZGRyZXNzIjoi5bm/5Lic55yBIOa3seWcs+W4giDljZflsbHljLog5rex5Y2X5aSn6YGTIDk5Njblj7cg6Z2g6L+R5oub5ZWG6ZO26KGMKOa3seWcs+Wogeebm+Wkp+WOpuaUr+ihjCkgIiwiY291bnRyeU5hbWUiOiLkuK3lm70iLCJnYXRld2F5VmVyc2lvbiI6IjEuMCIsImVuY3J5cHRVc2VySWQiOiJudWxsIiwibG9uZ2l0dWRlIjoiMTEzLjk0MDAyNSIsInVzZXJJZCI6IjE3OTgyMDg2NzUiLCJ1dGRpZCI6IlhMTlJLbVNqWlEwREFLUFJWRFlNL1RVVSIsImNpdHlOYW1lIjoi5rex5ZyzIiwiY2l0eUNvZGUiOiI0NDAzMDAiLCJlZGl0aW9uIjoie1wiYWN0dWFsTGFuZ3VhZ2VDb2RlXCI6XCJ6aC1DTlwiLFwiY291bnRyeUlkXCI6XCJDTlwiLFwiY291bnRyeU51bUNvZGVcIjpcIjE1NlwiLFwiY3VycmVuY3lDb2RlXCI6XCJDTllcIn0iLCJuaWNrIjoi5aSa5aSa55qE6Zuo5aSpIiwiYXJlYUNvZGUiOiI0NDAzMDUiLCJjb3VudHJ5Q29kZSI6IkNOIiwiYXJlYU5hbWUiOiLljZflsbHljLoiLCJwcm92aW5jZUNvZGUiOiI0NDAwMDAiLCJsYXRpdHVkZSI6IjIyLjU0MDEzMSIsImNvbnRhaW5lclBhcmFtcyI6IntcInJlY29tbWVuZF9ob21lX21haW5fMTAwMlwiOntcImJhc2VDYWNoZVRpbWVcIjowLFwiYml6UGFyYW1zXCI6e1wiYXBwSWRcIjpcIjg4NDlcIixcImV4cGlyZUFjdGlvblwiOlwiXCIsXCJ0YWJJbmRleFwiOjN9LFwiY2xpZW50UmVxT2Zmc2V0VGltZVwiOjAsXCJjbGllbnRSZXFUaW1lXCI6MCxcImRlbHRhQ2FjaGVUaW1lXCI6MCxcInBhZ2VQYXJhbXNcIjp7XCJpc0xhc3RQYWdlXCI6XCJuXCIsXCJpdGVtTGFzdENvdW50XCI6XCI1MFwiLFwicGFnZU51bVwiOjF9LFwicGFzc1BhcmFtc1wiOntcImNvbnRlbnRJZHNcIjpcIjc2MTcxNDg2OTAsNzE0MDIzMTEzMSw1OTE3ODAxMTA5LDc2MTg3MDIzMjUsNzE1MzU4NDYzNCw3NjE1NjM2Njk1LDc0MzE3NzcwNzUsNzMwNTcxOTU1Myw1NjA4MDc3NjczLDc0MTk4MzM3ODksNjI2Mjk5Njg5MSw3MzMyNzg5NDk3XCIsXCJpc1Rlbkljb25XaXRoTWluaUFwcFwiOlwiMlwiLFwibGFzdFZlcnNpb25cIjpcInY1XCJ9LFwicmVhbEJhc2VDYWNoZVRpbWVcIjowLFwicmVxdWVzdFR5cGVcIjpcInNjcm9sbE5leHRQYWdlXCJ9fSIsInByb3ZpbmNlTmFtZSI6IuW5v+S4nOecgSJ9";
//					String strData = new String(Base64.decode(data, 0));
//					Logger.log("STRDATA:"+strData);
//					HashMap<String, String > haspMap = new HashMap<String, String>();
//					haspMap.put("deviceId", "AkL9reMiyzxVGNsaZS4M8b71xjJdIo9FXw31mH2LHtke");
//					haspMap.put("appKey","21646297");
//					haspMap.put("api", "mtop.taobao.wireless.home.awesome.homepage.recommend");
//					haspMap.put("utdid","XLNRKmSjZQ0DAKPRVDYM/TUU");
//					haspMap.put("x-features","27");
//					haspMap.put("ttid","1551686692688@taobao_android_8.6.10");
//					haspMap.put("lng","113.940025");
//					haspMap.put("v","1.0");
//					haspMap.put("sid","5d73c4a7a1981577c823073784c393fa");
//					haspMap.put("t","1555481551");
//					haspMap.put("uid","1798208675");
//					haspMap.put("lat","22.540131");
//					haspMap.put("data", strData);
//					
//					Logger.log(""+haspMap);
//					String str = Interface.calcSign(haspMap, "21646297", null);
//					//Logger.log("sign:"+str);
//					return ;
//				}
				
				if(request.equals("send")){
					
					Interface.send();
					return;
				}
			}
		} catch (Exception e) {
			Logger.log(e);
		}
	}
}
