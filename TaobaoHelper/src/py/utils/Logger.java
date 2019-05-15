package py.utils;
import android.util.Log;

public class Logger {

	public static final String TAG = "py";
	public static void log(String msg) {
	   // CrawlUIUtil.self().addCrawlRecord(msg);	
		Log.i(TAG, msg);
	}
	public static String getCallback(){
		return Log.getStackTraceString(new Throwable());
	}
	public static void log(Throwable e) {
		// CrawlUIUtil.self().addCrawlRecord(Log.getStackTraceString(e));	
		Log.i(TAG, Log.getStackTraceString(e));
	}
	
	public static void log(String reason, Exception e) {	
		// CrawlUIUtil.self().addCrawlRecord(reason + "\r\n" + Log.getStackTraceString(e));
		Log.i(TAG, reason + "\r\n" + Log.getStackTraceString(e));
	}
	
	public static void log(Exception e) {	
		// CrawlUIUtil.self().addCrawlRecord(Log.getStackTraceString(e));
		Log.i(TAG, Log.getStackTraceString(e));
	}
	
	public static void log_callback() {	
		// CrawlUIUtil.self().addCrawlRecord(Log.getStackTraceString(new Throwable()));
		Log.i(TAG, Log.getStackTraceString(new Throwable()));
	}

	public static void log_tag(String tag, String msg) {
		Log.i(tag, msg);
	}
}
