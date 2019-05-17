package py.reverse.action;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
			}
		} catch (Exception e) {
			Logger.log(e);
		}
	}
}
