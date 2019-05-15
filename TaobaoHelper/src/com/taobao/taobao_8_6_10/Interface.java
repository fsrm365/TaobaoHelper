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
			// String sign = (String) XposedHelpers.callMethod(objSignImp, "a",
			// hashMap, str, str2);
			Method signMethod = XposedHelpers.findMethodExact(objSignImp.getClass(), "a", HashMap.class, String.class,
					String.class);
			if (signMethod == null) {
				return null;
			}
			String sign = (String) signMethod.invoke(objSignImp, hashMap, hashMap.get("appKey"), null);
			return sign;
		} catch (Exception e) {
			Logger.log(e);
			return null;
		}
	}

	public static void send() throws IOException {
		new Thread(){
			@Override
			public void run() {
				try {
					test();
				} catch (Exception e) {
					Logger.log(e);
				}
			}
			
		}.start();

	}
	
	private static void test() throws Exception {
		// TODO Auto-generated method stub
		//String data ="%7B%22address%22%3A%22%E5%B9%BF%E4%B8%9C%E7%9C%81+%E6%B7%B1%E5%9C%B3%E5%B8%82+%E5%8D%97%E5%B1%B1%E5%8C%BA+%E5%85%B4%E6%B5%B7%E5%A4%A7%E9%81%93+1030+%E9%9D%A0%E8%BF%91%E6%B7%B1%E5%9C%B3%E5%B8%82%E6%A1%83%E6%B4%B2%E5%9B%AD%E6%9E%97%E7%8E%AF%E4%BF%9D%E7%A7%91%E6%8A%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8+%22%2C%22countryName%22%3A%22%E4%B8%AD%E5%9B%BD%22%2C%22gatewayVersion%22%3A%221.0%22%2C%22encryptUserId%22%3A%22%22%2C%22longitude%22%3A%22113.894553%22%2C%22userId%22%3A%22%22%2C%22utdid%22%3A%22XLNRKmSjZQ0DAKPRVDYM%2FTUU%22%2C%22cityName%22%3A%22%E6%B7%B1%E5%9C%B3%22%2C%22cityCode%22%3A%22440300%22%2C%22edition%22%3A%22%7B%5C%22actualLanguageCode%5C%22%3A%5C%22zh-CN%5C%22%2C%5C%22countryId%5C%22%3A%5C%22CN%5C%22%2C%5C%22countryNumCode%5C%22%3A%5C%22156%5C%22%2C%5C%22currencyCode%5C%22%3A%5C%22CNY%5C%22%7D%22%2C%22nick%22%3A%22%E5%A4%9A%E5%A4%9A%E7%9A%84%E9%9B%A8%E5%A4%A9%22%2C%22areaCode%22%3A%22440305%22%2C%22countryCode%22%3A%22CN%22%2C%22areaName%22%3A%22%E5%8D%97%E5%B1%B1%E5%8C%BA%22%2C%22provinceCode%22%3A%22440000%22%2C%22latitude%22%3A%2222.487897%22%2C%22containerParams%22%3A%22%7B%5C%22recommend_home_main_1002%5C%22%3A%7B%5C%22baseCacheTime%5C%22%3A0%2C%5C%22bizParams%5C%22%3A%7B%5C%22appId%5C%22%3A%5C%228849%5C%22%2C%5C%22expireAction%5C%22%3A%5C%22%5C%22%2C%5C%22tabIndex%5C%22%3A2%7D%2C%5C%22clientReqOffsetTime%5C%22%3A0%2C%5C%22clientReqTime%5C%22%3A1555831775672%2C%5C%22deltaCacheTime%5C%22%3A0%2C%5C%22pageParams%5C%22%3A%7B%5C%22pageNum%5C%22%3A0%7D%2C%5C%22passParams%5C%22%3A%7B%5C%22contentIds%5C%22%3A%5C%227646982724%2C6327685378%2C6094705597%2C7644577487%2C7445105047%2C7595874162%2C5876269522%2C5876045848%2C6076561596%2C7300433638%2C6262763458%2C7332849014%5C%22%2C%5C%22lastVersion%5C%22%3A%5C%22v5%5C%22%7D%2C%5C%22realBaseCacheTime%5C%22%3A0%2C%5C%22requestType%5C%22%3A%5C%22pageEnter%5C%22%7D%7D%22%2C%22provinceName%22%3A%22%E5%B9%BF%E4%B8%9C%E7%9C%81%22%7D";
		String data="%7B%22address%22%3A%22%E5%B9%BF%E4%B8%9C%E7%9C%81+%E6%B7%B1%E5%9C%B3%E5%B8%82+%E5%8D%97%E5%B1%B1%E5%8C%BA+%E5%85%B4%E6%B5%B7%E5%A4%A7%E9%81%93+3%E6%A0%8B+%E9%9D%A0%E8%BF%91%E6%B7%B1%E5%9C%B3%E5%B8%82%E4%BA%91%E5%87%A4%E6%80%9D%E5%84%92%E6%9C%8D%E9%A5%B0%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8+%22%2C%22countryName%22%3A%22%E4%B8%AD%E5%9B%BD%22%2C%22gatewayVersion%22%3A%221.0%22%2C%22encryptUserId%22%3A%22%22%2C%22longitude%22%3A%22113.894726%22%2C%22userId%22%3A%22%22%2C%22utdid%22%3A%22XLNRKmSjZQ0DAKPRVDYM%2FTUU%22%2C%22cityName%22%3A%22%E6%B7%B1%E5%9C%B3%22%2C%22cityCode%22%3A%22440300%22%2C%22edition%22%3A%22%7B%5C%22actualLanguageCode%5C%22%3A%5C%22zh-CN%5C%22%2C%5C%22countryId%5C%22%3A%5C%22CN%5C%22%2C%5C%22countryNumCode%5C%22%3A%5C%22156%5C%22%2C%5C%22currencyCode%5C%22%3A%5C%22CNY%5C%22%7D%22%2C%22nick%22%3A%22%E5%A4%9A%E5%A4%9A%E7%9A%84%E9%9B%A8%E5%A4%A9%22%2C%22areaCode%22%3A%22440305%22%2C%22countryCode%22%3A%22CN%22%2C%22areaName%22%3A%22%E5%8D%97%E5%B1%B1%E5%8C%BA%22%2C%22provinceCode%22%3A%22440000%22%2C%22latitude%22%3A%2222.487632%22%2C%22containerParams%22%3A%22%7B%5C%22recommend_home_main_1002%5C%22%3A%7B%5C%22baseCacheTime%5C%22%3A0%2C%5C%22bizParams%5C%22%3A%7B%5C%22appId%5C%22%3A%5C%228849%5C%22%2C%5C%22expireAction%5C%22%3A%5C%22%5C%22%2C%5C%22tabIndex%5C%22%3A2%7D%2C%5C%22clientReqOffsetTime%5C%22%3A0%2C%5C%22clientReqTime%5C%22%3A0%2C%5C%22deltaCacheTime%5C%22%3A0%2C%5C%22pageParams%5C%22%3A%7B%5C%22isLastPage%5C%22%3A%5C%22n%5C%22%2C%5C%22itemLastCount%5C%22%3A%5C%2250%5C%22%2C%5C%22pageNum%5C%22%3A1%7D%2C%5C%22passParams%5C%22%3A%7B%5C%22contentIds%5C%22%3A%5C%227606785352%2C5604886079%2C6232344687%2C7318875665%2C7472178474%2C7615606015%2C5877314569%2C5876958060%2C5606480976%2C5879051660%2C6293073865%2C6262956739%5C%22%2C%5C%22lastVersion%5C%22%3A%5C%22v5%5C%22%7D%2C%5C%22realBaseCacheTime%5C%22%3A0%2C%5C%22requestType%5C%22%3A%5C%22scrollNextPage%5C%22%7D%7D%22%2C%22provinceName%22%3A%22%E5%B9%BF%E4%B8%9C%E7%9C%81%22%7D";
		String strData = new String(URLDecoder.decode(data));
		HashMap<String, String> headers = new HashMap<String, String>();	

		
	    headers.put("x-appkey", "21646297");
	    headers.put("x-region-channel", "CN");
	    headers.put("x-mini-wua", "HHnB_jeIFxXGRze97tSqtbObrIy9K3uKzXLIELrwTQQL91gm%2FmDAUh%2B%2B1CCCQ%2BAYP51sMYGrg2MkVmDQj0nSZDCtSz%2FKrgmT7HInKPiE%2BUcVmtDNqvd%2B%2B1XXmoaMXVQmfwE0u");
	    headers.put("x-nq", "WIFI");
	    headers.put("x-c-traceid", "XLNRKmSjZQ0DAKPRVDYM%2FTUU1555836941887006514925");
	    headers.put("content-type", "application/x-www-form-urlencoded;charset=UTF-8");
	    headers.put("x-app-conf-v", "19");
	    headers.put("x-features", "27");
	    headers.put("c-launch-info", "0,0,1555836941887,1555836723000,");
	    headers.put("x-page-name", "com.taobao.tao.TBMainActivity");
	    headers.put("x-app-ver", "8.6.10");
	    headers.put("x-t", "1555836941");
	    headers.put("x-pv", "6.1");
	    headers.put("x-location", "113.894726%2C22.487632");
	    headers.put("user-agent", "MTOPSDK%2F3.1.1.7+%28Android%3B6.0%3BHuawei%3BNexus+6P%29");
	    headers.put("f-refer", "mtop");
	    headers.put("x-ttid", "1551686692688%40taobao_android_8.6.10");
	    headers.put("x-nettype", "WIFI");
	    headers.put("cache-control", "no-cache");
	    headers.put("x-utdid", "XLNRKmSjZQ0DAKPRVDYM%2FTUU");
	    headers.put("x-umt", "TqNL1YFLOogaUjVqPrwoSQ9zN4X1PYQL");
	    headers.put("x-devid", "AkL9reMiyzxVGNsaZS4M8b71xjJdIo9FXw31mH2LHtke");
	    headers.put("x-page-url", "http%3A%2F%2Fm.taobao.com%2Findex.htm");
	    String sign = calSign(strData, headers);
	    Logger.log("old-x-sign: ab20380090fffbdd30a522ed107133a296d972220d95a45866\r\n"+ "sign:"+sign);
	    headers.put("x-sign", sign);
		
	    
		String url= "http://guide-acs.m.taobao.com/gw/mtop.taobao.wireless.home.awesome.homepage.recommend/1.0/?data="+strData;
		//OkHttpClient okHttpClient = new OkHttpClient();
		//		final Request request = new Request.Builder().url(url)
		//				.headers(Headers.of(headers))
		//				 .get()
		//				.build();
		//		Call call = okHttpClient.newCall(request);
		//		Logger.log("request:"+request);
		//		Response response = call.execute();
		Document doc = Jsoup.connect(url)
				.headers(headers)
				.ignoreContentType(true)
				.get();
		Logger.log("data:"+doc.body().toString());
	}
	
	public static String calSign(String strData, HashMap<String, String> headers) {
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
