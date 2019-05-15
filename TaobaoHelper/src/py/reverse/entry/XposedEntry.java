package py.reverse.entry;

import com.taobao.taobao_8_6_10.Main;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;


public class XposedEntry implements IXposedHookLoadPackage {
	@Override
	public void handleLoadPackage(final LoadPackageParam lpparam) {
		if(!lpparam.isFirstApplication){
			return ;
		}
		PackageMetaInfo pminfo = PackageMetaInfo.fromXposed(lpparam);
	
		if("com.taobao.taobao".equals(lpparam.packageName)){
			Main.self().startHook(pminfo);
		}
	
	}
}