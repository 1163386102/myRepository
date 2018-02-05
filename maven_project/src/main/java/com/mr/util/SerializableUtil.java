package com.mr.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** 序列化和反序列化的工具类
 * @author Administrator
 *
 */

public class SerializableUtil {

	/**
	 * @param obj 要序列化的对象
	 * @return 
	 */
	public static byte[] serialize(Object obj){  
		byte[] bytes = null;  
		try {  
		ByteArrayOutputStream baos=new ByteArrayOutputStream();;  
		ObjectOutputStream oos=new ObjectOutputStream(baos);  
		oos.writeObject(obj);  
		bytes=baos.toByteArray();  
		baos.close();  
		oos.close();  
		} catch (IOException e) {  
		e.printStackTrace();  
		}  
		return bytes;  
		}  
	
	/**
	 * 反序列化
	 * @param bytes 传递过来的bytes[]类型的key值
	 * @return
	 */
	public static Object deSerialize(byte[] bytes){  
		Object obj=null;  
		try {  
		ByteArrayInputStream bais=new ByteArrayInputStream(bytes);  
		ObjectInputStream ois=new ObjectInputStream(bais);  
		obj=ois.readObject();  
		} catch (Exception e) {  
		e.printStackTrace();  
		}  
		return obj;  
		}  
}
