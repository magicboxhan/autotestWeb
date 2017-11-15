package com.hq.test.framework.testcase;

import java.util.concurrent.TimeUnit;

import com.hq.test.framework.util.ThUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.hq.test.framework.util.Tools;

/**
 * Super class of testcase
 * @author hanqing
 *
 */
public class BaseTestcase {

	protected WebDriver d;
	protected Logger l = LogManager.getLogger(this.getClass().getName());
	protected Tools t = new Tools();
	protected ThUtil th;
	
	/**
	 * 初始化用例集
	 * @param monitorThread_interval -- 监控线程的检查时间，单位：秒
	 */
	@Parameters({
		"monitorThread_interval"
		})
	@BeforeSuite
	public void setup_suite(
		@Optional("300")int monitorThread_interval
		) {
		try{
			l.entry();
			//start monitor thread
			th = new ThUtil(monitorThread_interval);
			th.start();
			l.debug("Monitor thread is started");
			l.exit();
		}catch(Exception e){
			l.error("Error!");
			e.printStackTrace();
			Assert.assertEquals(true, false);
		}
	}
	
	/**
	 * 初始化用例
	 * @param browserType
	 * @param chromeDriverPath
	 * @param ieDriverPath
	 * @param timeout_ImplicitlyWait
	 * @param timeout_PageLoadTimeout
	 * @param timeout_ScriptLoadTimeout
	 * @param firefoxProfile -- 0:selenium默认; 1:用户默认
	 * @param monitorThread_interval -- 监控线程的检查时间，单位：秒
	 */
	@Parameters({
				"browserType", 
				"chromeDriverPath",
				"ieDriverPath", 
				"timeout_ImplicitlyWait", 
				"timeout_PageLoadTimeout",
				"timeout_ScriptLoadTimeout",
				"firefoxProfile"
				})
	@BeforeTest
	public void setup_test(
			@Optional("ff")String browserType, 
			@Optional("")String chromeDriverPath, 
			@Optional("")String ieDriverPath, 
			@Optional("10")int timeout_ImplicitlyWait, 
			@Optional("30")int timeout_PageLoadTimeout,
			@Optional("60")int timeout_ScriptLoadTimeout,
			@Optional("0")int firefoxProfile
			) {
		try{
			l.entry();
			//initialize WebDriver
			String driverType = "";
			if (browserType.toLowerCase().equals("ff")||browserType.toLowerCase().equals("firefox")){
				if (firefoxProfile == 0){
					d = new FirefoxDriver();
				}else if(firefoxProfile == 1){
					ProfilesIni allProfiles = new ProfilesIni();
					FirefoxProfile profile = allProfiles.getProfile("default");
					d = new FirefoxDriver(profile);
				}
				driverType = "Firefox";
			}else if(browserType.toLowerCase().equals("ie")||browserType.toLowerCase().equals("internetexplorer")){
				System.setProperty("webdriver.ie.driver", ieDriverPath);
				d = new InternetExplorerDriver();
				driverType = "IE";
			}else if(browserType.toLowerCase().equals("chrome")){
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				d = new ChromeDriver();
				driverType = "Chrome";
			}else{
				l.trace("Specified driver {} is not valid. Use firefix for default", browserType);
				d = new FirefoxDriver();
				driverType = "Firefox";
			}
			l.debug("{} is launched", driverType);
			//maximize window
			d.manage().window().maximize();
			l.debug("Window is maximized");
			//set timeout
			d.manage().timeouts().implicitlyWait(timeout_ImplicitlyWait, TimeUnit.SECONDS);
			l.debug("ImplicitlyWait is set to {} seconds", timeout_ImplicitlyWait);
			d.manage().timeouts().pageLoadTimeout(timeout_PageLoadTimeout, TimeUnit.SECONDS);
			l.debug("PageLoadTimeout is set to {} seconds", timeout_PageLoadTimeout);
			d.manage().timeouts().setScriptTimeout(timeout_ScriptLoadTimeout, TimeUnit.SECONDS);
			l.debug("ScriptLoadTimeout is set to {} seconds", timeout_ScriptLoadTimeout);
//			//start monitor thread
//			th = new ThUtil(monitorThread_interval);
//			th.start();
//			l.debug("Monitor thread is started");
			l.exit();
		}catch(Exception e){
			l.error("Error!");
			e.printStackTrace();
			Assert.assertEquals(true, false);
		}
	}

	/**
	 * 用例执行完成后要做的事
	 */
	@AfterTest
	public void teardown_test() {
		try{
			l.entry();
			d.quit();
			l.debug("WebDriver is quit");
//			th.interrupt();
			l.exit();
		}catch(Exception e){
			l.error("Error!");
			e.printStackTrace();
			Assert.assertEquals(true, false);
		}
	}
	
	/**
	 * 用例集执行完成后要做的事
	 */
	@AfterSuite
	public void teardown_suite() {
		try{
			l.entry();
//			th.interrupt();
//			l.debug("Monitor thread is interrupted");
			l.exit();
		}catch(Exception e){
			l.error("Error!");
			e.printStackTrace();
			Assert.assertEquals(true, false);
		}
	}
	
	/**
	 * switch to new window
	 */
	public void switchToNewWindow(){
		try{
			l.entry();
			d.switchTo().window((String) d.getWindowHandles().toArray()[d.getWindowHandles().toArray().length-1]);
			l.debug("Switch to new window");
			l.exit();
		}catch(Exception e){
			l.error("Error!");
			e.printStackTrace();
			Assert.assertEquals(true, false);
		}
	}
	
	/**
	 * switch to old window
	 */
	public void switchToOldWindow(){
		try{
			l.entry();
			d.switchTo().window((String) d.getWindowHandles().toArray()[0]);
			l.debug("Switch to old window");
			l.exit();
		}catch(Exception e){
			l.error("Error!");
			e.printStackTrace();
			Assert.assertEquals(true, false);
		}
	}
	
	/**
	 * Inject jquery
	 */
	public void injectJQuery(){
		try{
			l.entry();
			JavascriptExecutor jse = (JavascriptExecutor) d;
			if (!isJQueryLoaded(jse)){
				l.debug("Inject jquery");
				//Inject jquery
				String js = "if(document.getElementsByTagName('head').length==0){document.getElementsByTagName('html')[0].appendChild(document.createElement('head'));}var headID=document.getElementsByTagName('head')[0];var newScript = document.createElement('script');newScript.type = 'text/javascript';newScript.src = 'http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';headID.appendChild(newScript);";
				jse.executeScript(js);
			}
			l.exit();
		}catch(Exception e){
			l.error("Error!");
			e.printStackTrace();
			Assert.assertEquals(true, false);
		}
	}
	
	/**
	 * 页面是否已引入jquery
	 * @param jse
	 * @return
	 */
    public Boolean isJQueryLoaded(JavascriptExecutor jse)
    {
    	l.entry();
        try
        {
        	jse.executeScript("return " + "jQuery()!=null");
        	l.debug("Current page is using jquery");
            l.exit();
        	return true;
        }
        catch (Exception e)
        {
        	l.debug("Current page is not using jquery");
            return false;
        }
        
    }

}
