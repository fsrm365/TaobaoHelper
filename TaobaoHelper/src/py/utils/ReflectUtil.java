package py.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Gsang on 2018/6/21.
 */

public class ReflectUtil {
	
	/**
	 * 
	 * @param BeginClsObj
	 * @param strFieldNames  从BeginClsObj中第一个字段，然后字段中的字段，层之间用'/'隔开,要求所有字段都是Object
	 * 非基本数据类型
	 * @return
	 */
	public static Object getMutliLayerField(Object BeginClsObj, String strFieldNames){
		String[] strArys = strFieldNames.split("/");
		Object objTmp = BeginClsObj;
		try {
			for (String fieldName : strArys){
				objTmp = getObjectFiled(objTmp, fieldName);
			}
			return objTmp;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	public static List<Field> getAllFiled(Class<?> clz) {
		List<Field> fieldList = new ArrayList<Field>() ;
		Class tempClass = clz;
		while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
		      fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
		      tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
		}
		return fieldList;
	}

	
	public static Field getField(Class<?> clz, String filedname) throws NoSuchFieldException {  
		List<Field> fieldList = new ArrayList<Field>() ;
		
		Class tempClass = clz;
		while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
			Field[]  fileds = tempClass.getDeclaredFields();
		    if(fileds != null){
		    	for(Field  filed : fileds){
		    		if(filed.getName().equals(filedname)){
		    			return filed;
		    		}
		    	}
		    }
		    tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
		}
		throw new NoSuchFieldException();
	}
	
    public static Object getObjectFiled(Object object, String filedname) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class clz = object.getClass();
        Field filed= ReflectUtil.getField(clz, filedname);
        filed.setAccessible(true);
        return filed.get(object);
    }

}
