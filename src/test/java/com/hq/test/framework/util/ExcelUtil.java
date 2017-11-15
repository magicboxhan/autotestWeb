package com.hq.test.framework.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * Excel操作类
 * @author hanqing
 *
 */
public class ExcelUtil {

	Logger l = LogManager.getLogger(this.getClass().getName());
	Workbook excel = null;
	
	/**
	 * 构造方法
	 * @param filePath
	 * @param fileName
	 * @param isInZip -- 是否在zip文件中
	 */
	public ExcelUtil(String filePath, String fileName, boolean isInZip){
		l.entry();
		try {
			FileUtil f = new FileUtil();
			if(!isInZip){
				//不在压缩文件中
				String fullFileName = f.getFileNameByPartialFileName(filePath, fileName, "xls");
				excel = new HSSFWorkbook(new FileInputStream(String.format("%s//%s", filePath, fullFileName)));
			}else{
				//在压缩文件中
				String fullFileName = f.getFileNameByPartialFileName(filePath, fileName, "zip");
				ZipFile zf = new ZipFile(String.format("%s//%s", filePath, fullFileName));  
		        InputStream in = new BufferedInputStream(new FileInputStream(String.format("%s//%s", filePath, fullFileName)));  
		        ZipInputStream zin = new ZipInputStream(in);
		        ZipEntry ze;
		        while ((ze = zin.getNextEntry()) != null) {
		            if (!ze.isDirectory()) {
		            	excel = new HSSFWorkbook(zf.getInputStream(ze));
		            	break;
		            }
		        }
		        zf.close();
		        zin.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			l.error("Error!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			l.error("Error!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取Excel文件内容，转换为二维数据
	 * @param filePath -- 文件所在文件夹路径
	 * @param fileName -- 文件名，支持模糊搜索
	 * @return
	 */
	public ArrayList<HashMap<String, String>> readExcelData(){
		l.entry();
        try {
			//返回数据
			ArrayList<HashMap<String, String>> returnData = new ArrayList<HashMap<String, String>>();
			//表头数组
			ArrayList<String> headArray = new ArrayList<String>();
			//所有行
			Sheet sheet = excel.getSheetAt(0);
			int rowNum = sheet.getPhysicalNumberOfRows();
			l.debug("row amount is {}", rowNum);
			//将表头所有列加入表头数组
			Row firstRow = sheet.getRow(0);
			int colNum = firstRow.getPhysicalNumberOfCells();
			l.debug("column amount is {}", colNum);
			for (int i=0;i<colNum;i++){
				l.debug("column text is [{}]", firstRow.getCell(i));
				headArray.add(firstRow.getCell(i).getStringCellValue());
			}
			//表身，每一行
			for (int i=1;i<rowNum;i++){
				HashMap<String,String> rowHash = new HashMap<String,String>();
				Row row = sheet.getRow(i);
				//每一列
				for (int j=0;j<colNum;j++){
					if (row.getCell(j) != null){
						String key = headArray.get(j);
						l.debug("col head is [{}]", key);
						String value = row.getCell(j).getStringCellValue();
						l.debug("col value is [{}]", value);
						rowHash.put(key, value);
					}else{
						String key = headArray.get(j);
						l.debug("col head is [{}]", key);
						l.debug("Cell value is null");
						rowHash.put(key, null);
					}
				}
				returnData.add(rowHash);
			}
			//验证数据
			l.debug("===== verify data =====");
			l.debug("array size is {}", returnData.size());
			for (HashMap<String, String> hash : returnData){
				l.debug("=== new hash ===");
				for (String key : hash.keySet()){
					l.debug("Key is [{}]", key);
					l.debug("Value is [{}]", hash.get(key));
				}
			}
			return returnData;
		} catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
			return null;
		}  
	}
	
	/**
	 * 获取Excel的图片数量
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public int getExcelImageNumber(){
        l.entry();
		try {
//			FileUtil f = new FileUtil();
//			String fullFileName = f.getFileNameByPartialFileName(filePath, fileName, "xls");
//			Workbook excel = new HSSFWorkbook(new FileInputStream(String.format("%s//%s", filePath, fullFileName)));
			return excel.getAllPictures().size();
		} catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
			return 0;
		}  
	}
	
	/**
	 * 返回Excel链接
	 * @param filePath
	 * @param fileName
	 * @return key--链接文字；value--链接地址
	 */
	public HashMap<String,String> getExcelLinks(){
        l.entry();
		try {
			HashMap<String,String> links = new HashMap<String,String>();
//			FileUtil f = new FileUtil();
//			String fullFileName = f.getFileNameByPartialFileName(filePath, fileName, "xls");
//			Workbook excel = new HSSFWorkbook(new FileInputStream(String.format("%s//%s", filePath, fullFileName)));
			Sheet sheet = excel.getSheetAt(0);
			int rowNum = sheet.getPhysicalNumberOfRows();
			l.debug("row amount is {}", rowNum);
			//表身，每一行
			for (int i=0;i<rowNum;i++){
				Row row = sheet.getRow(i);
				//每一列
				for (int j=0;j<row.getPhysicalNumberOfCells();j++){
					Cell cell = row.getCell(j);
					if (cell != null){
						if (cell.getHyperlink() != null){
							String cellText = cell.getStringCellValue();
							l.debug("Cell text: [{}]", cellText);
							String cellLink = cell.getHyperlink().getAddress();
							l.debug("Cell url: [{}]", cellLink);
							links.put(cellText, cellLink);
						}
					}else{
						l.info("Cell is null");
					}
				}
			}
			return links;
		} catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
			return null;
		}  
	}
}
