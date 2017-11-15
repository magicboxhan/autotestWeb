package com.hq.test.framework.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RTFUtil {

	Logger l = LogManager.getLogger(this.getClass().getName());
	DefaultStyledDocument dsd;
	RTFEditorKit rtf;
	InputStream in;
	FileUtil f;
	String fileContent;
	ZipFile zf;
	ZipInputStream zin;
	
	public RTFUtil(String filePathName, boolean isInZip){
		l.entry();
		try{
			if(!isInZip){
				//不在压缩文件中
				in = new FileInputStream(filePathName);
			}else{
				//在压缩文件中
				zf = new ZipFile(filePathName);
		        InputStream in2 = new BufferedInputStream(new FileInputStream(filePathName));
		        zin = new ZipInputStream(in2);
		        ZipEntry ze;
		        while ((ze = zin.getNextEntry()) != null) {
		            if (!ze.isDirectory()) {
		            	in = zf.getInputStream(ze);
		            	break;
		            }
		        }
//		        zf.close();
//		        zin.close();
			}
//			in = new FileInputStream(new File(filePathName)); //压缩和非压缩处理方式不同
			
			dsd = new DefaultStyledDocument();
			rtf = new RTFEditorKit();
			f = new FileUtil();
			fileContent = f.readFile(filePathName, isInZip); //压缩和非压缩处理方式不同
			l.exit();
		}catch(Exception e){
			l.error("Error!");
			e.printStackTrace();
			l.exit();
		}
	}
	
	/**
	 * 获取文本信息，但是获取不到超链接
	 * @return
	 */
	public String getText(){
		l.entry();
		try{
			rtf.read(in, dsd, 0);
//			try{
//				zf.close();
//				zin.close();
//			}catch(Exception e){
//				//donothing
//			}
			return dsd.getText(0, dsd.getLength());	
		}catch(Exception e){
			l.error("Error!");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取RTF中的超链接数据列表
	 * @return
	 */
	public ArrayList<String> getlinkTexts(){
		l.entry();
		try{
			//解析RTF源文件，获取pn
			ArrayList<String> pns = new ArrayList<String>();
			String[] pnStrings = fileContent.split("fldrslt");
			for(int i=1;i<pnStrings.length;i++){
				try{
					pns.add(pnStrings[i].split("}")[0].split(" ")[pnStrings[i].split("}")[0].split(" ").length-1].trim());
				}catch(Exception e){
					l.debug("Error when get pn string");
					continue;
				}
			}
			for(String pn : pns){
				l.debug(pn);
			}
			return pns;
		}catch(Exception e){
			l.error("Error!");
			e.printStackTrace();
			return null;
		}
	}
}
