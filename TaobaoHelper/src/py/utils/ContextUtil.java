package py.utils;

import java.util.Set;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public class ContextUtil {

	public static int getIntMetaData(Context context, String key)  {
		
		ApplicationInfo appInfo;
		try {
			appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);
			Bundle metaData = appInfo.metaData;
			if(metaData!= null)
			{
				return metaData.getInt(key);
			}
		} catch (NameNotFoundException e) {
			return -1;
		}
		return -1;
	}
	
}
