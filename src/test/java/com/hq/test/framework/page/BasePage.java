package com.hq.test.framework.page;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hq.test.framework.util.Tools;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * 
 * @author hanqing
 *
 */
public class BasePage {

	protected Logger l = LogManager.getLogger(this.getClass().getName());
	protected WebDriver d;
	protected Actions act;
	protected By selfcheckSelector;
	protected Tools t = new Tools();
	
	/**
	 * 
	 * @param b
	 * @return
	 */
	protected boolean doesExist(By b) {
		try{
			l.entry();
			d.findElement(b);
			l.debug("Element exists");
			l.exit();
			return true;
		} catch (Exception e) {
			l.debug("Element does not exist");
			l.exit();
			return false;
		}
	}
	
	protected boolean isVisible(By b) {
		try{
			l.entry();
			WebElement e = d.findElement(b);
			l.debug("Element exists");
			if (e.isDisplayed()){
				l.debug("Element is visible");
				return true;
			}else{
				l.debug("Element is invisible");
				return false;
			}
		} catch (Exception e) {
			l.debug("Element does not exist");
			l.exit();
			return false;
		}
	}
	
	protected boolean isEnabled(By b) {
		try{
			l.entry();
			WebElement e = d.findElement(b);
			l.debug("Element exists");
			if (e.isEnabled()){
				l.debug("Element is enabled");
				return true;
			}else{
				l.debug("Element is disabled");
				return false;
			}
		} catch (Exception e) {
			l.debug("Element does not exist");
			l.exit();
			return false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean selfcheck() {
		try{
			l.entry();
			l.debug("Selfcheck selector is {}", selfcheckSelector);
			if (selfcheckSelector == null){
				l.debug("Selfcheck pass (no selfcheck selector)");
				l.exit();
				return true;
			}else if(doesExist(selfcheckSelector)){
				l.debug("Selfcheck pass");
				l.exit();
				return true;
			}else{
				l.error("Selfcheck fail");
				this.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
				l.exit();
				return false;
			}
		} catch (Exception e) {
			l.error("Selfcheck error!!");
			e.printStackTrace();
			this.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
			return false;
		}
	}
	
	/**
	 * 
	 * @param d
	 * @param className
	 * @param methodName
	 */
	public void takeScreenshot(WebDriver d, String className, String methodName){
		l.entry();
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			String timeStr = df.format(new Date());
			File screenShotFile = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
			String filePath = String.format("%s__%s__%s.jpg", className, methodName, timeStr);
			FileUtils.copyFile(screenShotFile, new File(filePath)); 
			l.info("Screenshot file name: {}", filePath);
			l.exit();
		}catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
		}
	}
	
	/**
	 * convert table data into List<HashMap>
	 * @param table
	 * @return
	 */
	public List<HashMap<String, String>> getTableData(WebElement table){
		l.entry();
		//返回数据
		List<HashMap<String, String>> returnData = new ArrayList<HashMap<String, String>>();
		//表头数组
		List<String> headArray = new ArrayList<String>();
		//所有行
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		l.debug("row amount is {}", rows.size());
		//将表头所有列加入表头数组
		List<WebElement> cols_head = rows.get(0).findElements(By.tagName("th"));
		for (WebElement th : cols_head){
			String colText = th.getText();
			l.debug("column text is {}", colText);
			headArray.add(th.getText());
		}
		l.debug("column amount is {}", headArray.size());
		//表身，每一行
		for (int i=1;i<rows.size();i++){
			HashMap<String,String> rowHash = new HashMap<String,String>();
			WebElement tr = rows.get(i);
			List<WebElement> tds = tr.findElements(By.tagName("td"));
			//每一列
			for (int j=0;j<headArray.size();j++){
				String key = headArray.get(j);
				l.debug("col head is {}", key);
				String value = tds.get(j).getText();
				l.debug("col value is {}", value);
				rowHash.put(key, value);
			}
			returnData.add(rowHash);
		}
//		//验证数据
//		l.debug("===== verify data =====");
//		l.debug("array size is {}", returnData.size());
//		for (HashMap<String, String> hash : returnData){
//			l.debug("=== new hash ===");
//			for (String key : hash.keySet()){
//				l.debug("Key is {}", key);
//				l.debug("Value is {}", hash.get(key));
//			}
//		}
		return returnData;
	}
}
