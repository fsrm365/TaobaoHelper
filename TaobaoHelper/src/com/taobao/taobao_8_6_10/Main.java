package com.taobao.taobao_8_6_10;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import py.reverse.action.ActionReceiver;
import py.reverse.entry.PackageMetaInfo;
import py.utils.ContextUtil;
import py.utils.Logger;

public class Main {
    private boolean HAS_REGISTER_LISENER;
    private int apiLevel;
    private Application fristApplication;
    private PackageMetaInfo metaInfo;
    private static Main moduleContext;

    private Main() {
        super();
        this.HAS_REGISTER_LISENER = false;
    }

    private Application getAppContext() {
        return this.fristApplication;
    }

    private ApplicationInfo getAppInfo() {
        return this.metaInfo.getAppInfo();
    }

    private ClassLoader getBaseClassLoader() {
        return this.metaInfo.getClassLoader();
    }

    private String getPackageName() {
        return this.metaInfo.getPackageName();
    }

    private String getProcssName() {
        return this.metaInfo.getProcessName();
    }

    private void hook_Application_attatch() {
        XposedHelpers.findAndHookMethod(Application.class, "attach", new Object[]{Context.class, new XC_MethodHook() {       
        	protected void afterHookedMethod(XC_MethodHook.MethodHookParam params) throws Throwable {
            	Context context = (Context) params.args[0];
                ClassLoader v0 = (context).getClassLoader();
                Module.get().startHook(v0);
                // 主进程执行
				if (getProcssName().equals(getPackageName())) {
					registBroadcast((Application) params.thisObject);
					Server.get().start();
				}
            }
        }});
    }
    
    private void registBroadcast(Application application) {
		if (!HAS_REGISTER_LISENER) {
			fristApplication = application;
			fristApplication.registerReceiver(new ActionReceiver(), new IntentFilter(ActionReceiver.INTENT_ACTION));
			HAS_REGISTER_LISENER = true;
		}
	}
    public static Main self() {
        if(Main.moduleContext == null) {
            Main.moduleContext = new Main();
        }
        return Main.moduleContext;
    }
    
    public void startHook(PackageMetaInfo packageInfo) {
    	Logger.log("WechatMain.startHook......");
        this.metaInfo = packageInfo;
        this.hook_Application_attatch();
    }
}


