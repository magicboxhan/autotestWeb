package com.hq.test.framework.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xml文件类，读取xml中的二维数组数据
 * @author hanqing
 *
 */
public class XMLUtil {

	Logger l = LogManager.getLogger(this.getClass().getName());
	ZipFile zf;
	ZipInputStream zin;
	SAXReader reader;
	Document doc;
	
	public XMLUtil(String filePathName, boolean isInZip){
		l.entry();
		try{
            reader = new SAXReader();
			if(!isInZip){
				//不在压缩文件中
				doc = reader.read(new FileInputStream(filePathName));
			}else{
				//在压缩文件中
				zf = new ZipFile(filePathName);
		        InputStream in2 = new BufferedInputStream(new FileInputStream(filePathName));
		        zin = new ZipInputStream(in2);
		        ZipEntry ze;
		        while ((ze = zin.getNextEntry()) != null) {
		            if (!ze.isDirectory()) {
		            	doc = reader.read(zf.getInputStream(ze));
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
	
	
	/**
	 * 获取xml中的二维数组--按照智慧芽产品的导出格式
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList<HashMap<String, String>> readData_forExport(){
		l.entry();
        try {
			//返回数据
			ArrayList<HashMap<String, String>> returnData = new ArrayList<HashMap<String, String>>();
			//所有行
			Element root = doc.getRootElement();
		    // 枚举名称为doc的节点(每个节点表示一个专利)
		    for(Iterator i = root.elementIterator("doc"); i.hasNext();){
		       Element patent = (Element) i.next();
		       HashMap<String, String> patentData = new HashMap<String, String>();
		       // 枚举每个str节点(每个节点表示一个属性)
		       for(Iterator j = patent.elementIterator("str"); j.hasNext();){
		    	   Element attr = (Element) j.next();  
		    	   String key = attr.attributeValue("name");
		    	   String value = attr.getText();
		    	   l.debug("Key -- {}", key);
		    	   l.debug("Value -- {}", value);
		    	   patentData.put(key, value);
		       }
		       returnData.add(patentData);
		    }
			//验证数据
//			l.debug("===== verify data =====");
//			l.debug("array size is {}", returnData.size());
//			for (HashMap<String, String> hash : returnData){
//				l.debug("=== new hash ===");
//				for (String key : hash.keySet()){
//					l.debug("Key is [{}]", key);
//					l.debug("Value is [{}]", hash.get(key));
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
