package com.hq.test.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.csvreader.CsvReader;

/**
 * 文件读写类
 * @author hanqing
 *
 */
public class FileUtil {

	Logger l = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * 检查文件是否存在
	 * @param fileName
	 * @return
	 */
	public boolean doesFileExist(String fileName){
		try{
			l.entry();
			boolean result = false;
            File file = new File(fileName);
            if(file.exists()&&!file.isDirectory()){
            	l.debug("File [{}] exists", fileName);
            	result = true;
            }else{
            	l.warn("File [{}] does not exist", fileName);
            	result = false;
            }
			return result;
		}catch(Exception e){
			l.error("Error");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除文件
	 * @param fileName
	 */
	public void deleteFile(String fileName){
		try{
			l.entry();
            File file=new File(fileName);
            if(file.exists()&&!file.isDirectory()){
            	boolean deleteResult = file.delete();
            	if (deleteResult){
            		l.debug("delete successfully");
            	}else{
            		l.error("delete failed");
            	}
            }else{
            	l.debug("file {} does not exist", fileName);
            }
			l.exit();
		}catch(Exception e){
			l.error("Error");
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取文件内容--所有行放在一个字符串中返回
	 * @param fileName 文件路径+文件名
	 * @return 文件内容
	 */
	public String readFile(String fileName, boolean isInZip){
		try{
			l.entry();
			FileInputStream fis = null;
			InputStreamReader isr = null;
			BufferedReader br = null;
			String returnString = null;
            String temp = null;
            StringBuffer sb = new StringBuffer();
			if(!isInZip){
				//不在压缩文件中
				fis = new FileInputStream(fileName);
	            isr = new InputStreamReader(fis);
	            br = new BufferedReader(isr);
	            temp = br.readLine();
	            while(temp != null){
	                sb.append(temp + " ");
	                temp = br.readLine();
	            }
	            returnString = sb.toString();
	            br.close();
	            l.debug("File content: {}", returnString);
			}else{
				//在压缩文件中
				ZipFile zf = new ZipFile(fileName);
		        InputStream in = new BufferedInputStream(new FileInputStream(fileName));
		        ZipInputStream zin = new ZipInputStream(in);
		        ZipEntry ze;
		        while ((ze = zin.getNextEntry()) != null) {
		            if (!ze.isDirectory()) {
//		            	fis = (FileInputStream) zf.getInputStream(ze);
		            	br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));  
		                temp = br.readLine();
		                while(temp != null){
		                    sb.append(temp + " ");
		                    temp = br.readLine();
		                }
		                returnString = sb.toString();
		                br.close();
		                l.debug("File content: {}", returnString);
		            	break;
		            }
		        }
		        zf.close();
		        zin.close();
			}
			
//            temp = br.readLine();
//            while(temp != null){
//                sb.append(temp + " ");
//                temp = br.readLine();
//            }
//            returnString = sb.toString();
//            br.close();
//            l.debug("File content: {}", returnString);
			return returnString;
		}catch(Exception e){
			l.error("Error");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param fileName 文件路径+文件名
	 * @return 字符串列表，每个元素代表一行数据
	 */
	public ArrayList<String> readFileLines(String fileName){
		try{
			l.entry();
			ArrayList<String> lines = new ArrayList<String>();
            File file = new File(fileName);
            if(file.exists() && !file.isDirectory()){
            	l.debug("File [{}] exists", fileName);
                BufferedReader br = new BufferedReader(new FileReader(file));
                String temp = null;
                temp = br.readLine();
                while(temp != null){
                	lines.add(temp);
                    temp = br.readLine();
                }
                br.close();
            }else{
            	l.warn("File [{}] does not exist", fileName);
            	lines = null;
            }
            l.debug("File size: {}", lines.size());
			return lines;
		}catch(Exception e){
			l.error("Error");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 该方法不写任何日志，用于检查系统日志是否增长
	 * @param fileName
	 * @return
	 */
	public ArrayList<String> readFileLines_noLog(String fileName){
		try{
			ArrayList<String> lines = new ArrayList<String>();
            File file = new File(fileName);
            if(file.exists() && !file.isDirectory()){
                BufferedReader br = new BufferedReader(new FileReader(file));
                String temp = null;
                temp = br.readLine();
                while(temp != null){
                	lines.add(temp);
                    temp = br.readLine();
                }
                br.close();
            }else{
            	lines = null;
            }
			return lines;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 写文件
	 * @param fileName -- 文件路径+文件名
	 * @param fileContent -- 文件内容
	 */
	public void writeFile(String fileName, String fileContent){
		try{
			l.entry();
            FileOutputStream out=new FileOutputStream(fileName);
            PrintStream p=new PrintStream(out);
            p.println(fileContent);
            p.close();
            l.exit();
		}catch(Exception e){
			l.error("Error");
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过部分文件名，获取完整文件名
	 * @param folder -- 文件夹
	 * @param partialFileName -- 部分文件名
	 * @param extName -- 扩展名
	 * @return
	 */
	public String getFileNameByPartialFileName(String folder, String partialFileName, String extName){
		try{
			l.entry();
	        File file = new File(folder);  
	        l.debug("Folder name: [{}]", folder);
	        l.debug("Partial file name: [{}]", partialFileName);
	        l.debug("Ext name: [{}]", extName);
	        l.info("Trying to get file: {}, {}, {}", folder, partialFileName, extName);
	        if(file.exists()){  
	            File files[] = file.listFiles();
	            //如果文件数量为0，则返回空
	            if (files.length == 0){
	            	l.error("There is no file under folder [{}]", folder);
	            	return null;
	            }
	            ArrayList<File> tempFiles = new ArrayList<File>(); //临时列表，存储所有匹配文件
	            //获取所有匹配文件列表
	            for (File f : files){
	            	l.debug("Current file name: [{}]", f.getName());
	            	if ((f.getName().contains(partialFileName))&&(f.getName().toLowerCase().endsWith(extName.toLowerCase()))){
	            		l.info("File is found, and the name is [{}]. Add to file list", f.getName());
	            		tempFiles.add(f);
	            	}
	            }
	            //如果文件列表只有一个文件，则返回该文件名
	            if(tempFiles.size() == 1){
	            	return tempFiles.get(0).getName();
	            }
	            //在匹配列表中，找到后缀值最大的文件，返回文件名
	            File targetFile = null; //记录当前后缀最大的文件
	            int index = 0; //记录当前的最大后缀
	            for(File f : tempFiles){
	            	//尝试从最后一对括号中解析出数字后缀
	            	try{
	            		String[] ss = f.getName().split("\\)");
	            		String s = ss[ss.length-2];
	            		ss = s.split("\\(");
	            		s = ss[ss.length-1];
	            		int currentIndex = Integer.parseInt(s);
	            		//如果当前的后缀大于之前保存的，则替换最大后缀和最大文件
	            		if(currentIndex > index){
	            			index = currentIndex;
	            			targetFile = f;
	            		}
	            	}catch(Exception e){
	            		//解析不出数字后缀，则表明当前文件非最新下载，跳到下一个
	            		continue;
	            	}
	            }
	            //如果解析不成功，则返回列表中的最后一个文件
	            if(targetFile == null && tempFiles.size() > 0){
	            	targetFile = tempFiles.get(tempFiles.size() - 1);
	            }
	            //如果找到最大后缀文件，则返回文件名
	            if(targetFile != null){
	            	return targetFile.getName();
	            }
	            l.error("File does not exist");
	            return null;
	        }else{
	        	l.error("Folder does not exist");
	        	return null;
	        }
		}catch(Exception e){
			l.error("Error");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取CSV文件数据
	 * @param csvFilePath
	 * @return
	 */
	public ArrayList<HashMap<String, String>> readCsv(String csvFilePath, String charset) {
		l.entry();
		try {
			if(charset == null){
				charset = "UTF-8";
			}
			//返回数据
			ArrayList<HashMap<String, String>> returnData = new ArrayList<HashMap<String, String>>();
			ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
//			String csvFilePath = "c:/test.csv";
			CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName(charset));
//			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
			while (reader.readRecord()) { // 逐行读入除表头的数据
				csvList.add(reader.getValues());
			}
			reader.close();
			//获取表头
			ArrayList<String> headArray = new ArrayList<String>();
			for(String colText : csvList.get(0)){
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
					String value = csvList.get(i)[j];
					l.debug("col value is {}", value);
					rowHash.put(key, value);
				}
				returnData.add(rowHash);
			}
			//验证数据
			l.debug("===== verify data =====");
			l.debug("array size is {}", returnData.size());
			for (HashMap<String, String> hash : returnData){
				l.debug("=== new hash ===");
				for (String key : hash.keySet()){
					l.debug("Key is {}", key);
					l.debug("Value is {}", hash.get(key));
				}
			}
			return returnData;
		} catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
			return null;
		}
	}
}
