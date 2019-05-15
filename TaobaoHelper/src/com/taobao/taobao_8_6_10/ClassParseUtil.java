package com.taobao.taobao_8_6_10;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public class ClassParseUtil {

    //list转String
    public static String list2String(List<?> list) throws IOException {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(baos);
        oos.writeObject(list);
        String s=new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        oos.close();
        return s;
    }

    //String转list
    public static List<?> string2List(String s) throws IOException, ClassNotFoundException{
        byte[] bytes=Base64.decode(s.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais=new ByteArrayInputStream(bytes);
        ObjectInputStream ois=new ObjectInputStream(bais);
        List<?> list=(List<?>) ois.readObject();
        ois.close();
        return list;
    }

    public static byte[] int2Bytes(int a) {
        return new byte[] {
            (byte) ((a >> 24) & 0xFF),
            (byte) ((a >> 16) & 0xFF),
            (byte) ((a >> 8) & 0xFF),
            (byte) (a & 0xFF)
        };
    }

}
