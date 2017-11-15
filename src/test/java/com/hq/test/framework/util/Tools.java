package com.hq.test.framework.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * 工具整合
 * @author hanqing
 *
 */
public class Tools {

	Logger l = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * 错误截图
	 * @param d--WebDriver
	 */
	public void takeScreenshot(WebDriver d){
		l.entry();
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			String timeStr = df.format(new Date());
			File screenShotFile = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
			String filePath = String.format("%s.jpg", timeStr);
			FileUtils.copyFile(screenShotFile, new File(filePath)); 
			l.info("============================== Screenshot file name: {}", filePath);
			l.exit();
		}catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 错误截图
	 * @param d -- WebDriver
	 * @param filePath -- 保存图片路径
	 * @param fileName -- 保存图片文件名
	 * @param extName -- 保存图片扩展名
	 */
	public void takeScreenshot(WebDriver d, String filePath, String fileName, String extName){
		l.entry();
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			String timeStr = df.format(new Date());
			File screenShotFile = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
			String fileFullPath = String.format("%s//%s_%s.%s", filePath, fileName, timeStr, extName);
			FileUtils.copyFile(screenShotFile, new File(fileFullPath)); 
			l.info("Screenshot file name: {}", fileFullPath);
			l.exit();
		}catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
		}
	}
}
