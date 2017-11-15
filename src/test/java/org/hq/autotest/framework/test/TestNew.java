package org.hq.autotest.framework.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.hq.test.framework.util.Tools;

public class TestNew {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try{
        	Tools t = new Tools();
        	WebDriver d = new FirefoxDriver();
        	d.get("file:///C:/Users/hanqing/workspace/1.html");
        	t.takeScreenshot(d, "D://pics", "1", "jpg");
        	d.quit();
        }catch(Exception e){
            e.printStackTrace();
        }
	}

}
