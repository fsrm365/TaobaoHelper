package com.taobao.taobao_8_6_10;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XposedHelpers;
import py.utils.Logger;

public class Module {
	private ClassLoader classLoader;
	private Object objInnerSignImp=null;
	private static Module sInstance;
	static {
		Module.sInstance = new Module();
	}

	public Module() {
		super();
		this.classLoader = null;
	}

	public static Module get() {
		return Module.sInstance;
	}
	public void startHook(ClassLoader classLoader) {
		Logger.log("WechatModule.startHook......");
		this.classLoader = classLoader;
		hook_InnerSignImpl_ctor();
	}

	private void hook_InnerSignImpl_ctor() {
		XposedHelpers.findAndHookConstructor(XposedHelpers.findClass("tb.lmu", classLoader), new XC_MethodHook(){
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
				Logger.log(""+param.thisObject);
				objInnerSignImp = param.thisObject;
			}
		});
	}
	
	public Object getSignImp(){
		return objInnerSignImp;
	}
}
