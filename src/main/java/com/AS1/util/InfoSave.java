/**
 * 
 */
package com.AS1.util;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/** 
 * 类描述：将用户传递过来的信息保存
 *@author: 冯冬冬 
 *@date： 日期：2018-7-25 时间：下午8:14:11 
 */
public class InfoSave {
	//数据保存在本地的地址

	
    //保存用户信息和密码
    public static boolean saveParametersInfo( String parameter1,String parameter2,String parameter3,String parameter4,
    		String parameter5,String parameter6,String parameter7,String parameter8){

        try {
        	
        	String savePath = "D:\\data\\";
        	String path=savePath+"params.txt";
        	File file1 = new File(savePath);
            //判断上传文件的保存目录是否存在
            if (!file1.exists() && !file1.isDirectory()) {
                System.out.println(savePath+"目录不存在，需要创建");
                //创建目录
                boolean aa=file1.mkdir();
                if(aa){
                	System.out.println(savePath+"目录创建成功");
                	File file2=new File(path);
                	boolean result=file2.createNewFile();
                	if(result){
                		System.out.print("文件创建成功");
                	}          		
                	else{
                		System.out.print("文件创建失败");
                	}         		
                	
                }else{
                	System.out.println(savePath+"文件创建失败");
                	return false;
                }
            }
            
            String result="预热温度："+parameter1+"\r\n" +"温度控制范围："+ parameter2 + "\r\n" + 
            "波峰1浸锡时间："+parameter3 + "\r\n" + "波峰2浸锡时间："+parameter4 + "\r\n" + 
        "传送温度："+parameter5+"\r\n" +"夹角倾度："+ parameter6+"\r\n" +
            "助焊剂喷雾压力:"+parameter7+"\r\n" + "针阀压力"+parameter8;
           
           BufferedWriter bw=new BufferedWriter(new FileWriter(path));
           bw.write(result);
           bw.flush();
           bw.close();
           return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    
    
	public static boolean saveFileInfo(String inputStream,String fileExtName) throws IOException {

		// 写入新文件
		FileOutputStream out;
		BufferedOutputStream bos = null; 
		try {
			byte[] bytes = Base64.decode(inputStream);
			// 存储路径
			String path = "D:\\data\\";
			out = new FileOutputStream(new File(path+"MyfileParams."+fileExtName));
			bos=new BufferedOutputStream(out);
			bos.write(bytes);
			bos.close();
			out.flush();
			out.close();
			return true;
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
			return false;
		}
	}
    
    
    
}
