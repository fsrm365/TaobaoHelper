package py.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUtil {
	public static String writeFile(String data, String filePath, boolean bAppend) {
		return writeFile(data.getBytes(), filePath, bAppend);
	}
	
	public static String writeFile(byte[] data, String filePath, boolean bAppend) {
		File filePic;
		try {
			filePic = new File(filePath);
			if (!filePic.exists()) {
				filePic.getParentFile().mkdirs();
				filePic.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(filePic, bAppend);
			fos.write(data);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			Logger.log(e);
			return null;
		}
		return filePic.getAbsolutePath();
	}

	public static byte[] readFile(String filePath) {
		try {
			FileInputStream fos = new FileInputStream(filePath);
			byte[] data = new byte[fos.available()];
			int size = fos.read(data);
			Logger.log("size:"+size);
			return data;
		} catch (Exception e) {
			Logger.log(e);
			return null;
		}
		
	}

	/**
	 *
	 * @param actionPath
	 * @return
	 * 2018年6月10日
	 * String
	 */
	
	public static String readString(String filePath) {
		byte[] data = readFile(filePath);
		if(data == null){
			return null;
		}
		return new String(data);
	}

	/**
	 *
	 * @param actionPath
	 * @return
	 * 2018年6月17日
	 * boolean
	 */
	
	public static boolean exists(String filePath) {
		return new File(filePath).exists();
	}

	public static List<String> readLines(String filePath) {
		List<String> lines = new ArrayList<String>();
		try{
			File file = new File(filePath);// Text文件
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				lines.add(s);
			}
		}catch(Exception e){
			Logger.log(e);
		}

		return lines;
	}
	

}
