package com.hq.test.framework.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.csvreader.CsvReader;

public class CsvUtil {

	Logger l = LogManager.getLogger(this.getClass().getName());
	ZipFile zf;
	ZipInputStream zin;
	CsvReader csv;
	
	public CsvUtil(String filePathName, String charset, boolean isInZip){
		l.entry();
		try{
			if(charset == null){
				charset = "UTF-8";
			}
			if(!isInZip){
				//不在压缩文件中
				csv = new CsvReader(filePathName, ',', Charset.forName(charset));
			}else{
				//在压缩文件中
				zf = new ZipFile(filePathName);
		        InputStream in2 = new BufferedInputStream(new FileInputStream(filePathName));
		        zin = new ZipInputStream(in2);
		        ZipEntry ze;
		        while ((ze = zin.getNextEntry()) != null) {
		            if (!ze.isDirectory()) {
		            	csv = new CsvReader(zf.getInputStream(ze), ',', Charset.forName(charset));
		            	break;
		            }
		        }
//		        zf.close();
//		        zin.close();
			}
			l.exit();
		}catch(Exception e){
			l.error("Error!");
			e.printStackTrace();
			l.exit();
		}
	}
	
	public ArrayList<HashMap<String, String>> readData(){
		l.entry();
		try {
			//返回数据
			ArrayList<HashMap<String, String>> returnData = new ArrayList<HashMap<String, String>>();
			ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
			while (csv.readRecord()) { // 逐行读入除表头的数据
				csvList.add(csv.getValues());
			}
			csv.close();
			//获取表头
			ArrayList<String> headArray = new ArrayList<String>();
			for(String colText : csvList.get(0)){
//				if(colText.contains("Publication  Number")){
//					colText = colText.substring(1, colText.length());
//				}
				l.debug("column text is {}", colText);
				headArray.add(colText);
			}
			//所有行
			l.debug("row amount is {}", csvList.size()-1);
			//表身，每一行
			for (int i=1;i<csvList.size();i++){
				HashMap<String,String> rowHash = new HashMap<String,String>();
				//每一列
				for (int j=0;j<headArray.size();j++){
					String key = headArray.get(j);
					l.debug("col head is {}", key);
					String value = null;
					try{
						value = csvList.get(i)[j];
					}catch(Exception e){
						//如果读取失败，则赋值为空
						value = "";
					}
					l.debug("col value is {}", value);
					rowHash.put(key, value);
				}
				returnData.add(rowHash);
			}
			//验证数据
//			l.debug("===== verify data =====");
//			l.debug("array size is {}", returnData.size());
//			for (HashMap<String, String> hash : returnData){
//				l.debug("=== new hash ===");
//				for (String key : hash.keySet()){
//					l.debug("Key is {}", key);
//					l.debug("Value is {}", hash.get(key));
//				}
//			}
			return returnData;
		} catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
			return null;
		}
	}
	
}
