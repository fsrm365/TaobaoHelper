package com.taobao.taobao_8_6_10;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.content.Context;
import android.database.Cursor;
import android.util.Base64;
import de.robv.android.xposed.XposedHelpers;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import py.utils.FileUtil;
import py.utils.Logger;
import py.utils.ReflectUtil;

public class Interface {
	public static String calcXSign(HashMap<String, String> hashMap) {
		try {
			Object objSignImp = Module.get().getSignImp();
			Method signMethod = XposedHelpers.findMethodExact(objSignImp.getClass(), "a", HashMap.class, String.class,
					String.class);
			if (signMethod == null) {
				return null;
			}
			String sign = (String) signMethod.invoke(objSignImp, hashMap, hashMap.get("appKey"), null);
			return sign;
		} catch (Exception e) {
			Logger.log(e);
			return "";
		}
	}


	
	private static String calSign(String strData, HashMap<String, String> headers) {
		HashMap<String, String> haspMap = new HashMap<String, String>();
		String[] locations = URLDecoder.decode(headers.get("x-location")).split(",");
		String lng = locations[0];
		String lat = locations[1];
		
		haspMap.put("deviceId", headers.get("x-devid"));
		haspMap.put("appKey", headers.get("x-appkey"));
		haspMap.put("api", "mtop.taobao.wireless.home.awesome.homepage.recommend");
		haspMap.put("utdid", URLDecoder.decode(headers.get("x-utdid")));
		haspMap.put("x-features", headers.get("x-features"));
		haspMap.put("ttid",  URLDecoder.decode(headers.get("x-ttid")));
		
		haspMap.put("lng", lng);
		haspMap.put("lat", lat);
		haspMap.put("v", "1.0");
		haspMap.put("sid", headers.get("x-sid"));
		haspMap.put("t", headers.get("x-t"));
		haspMap.put("uid", headers.get("x-uid"));
		
		haspMap.put("data", strData);
		Logger.log("" + haspMap);
		String str = Interface.calcXSign(haspMap);
		return str;
	}
}
