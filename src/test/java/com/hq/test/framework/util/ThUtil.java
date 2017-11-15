package com.hq.test.framework.util;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 监控线程，用来做一些脚本本身无法做的事情。比如：WebDriver卡死，就需要这个线程来刷新浏览器，使脚本继续执行，防止卡死
 * @author hanqing
 *
 */
public class ThUtil extends Thread{
	
	Logger l = LogManager.getLogger(this.getClass().getName());
	int timeout;
	
	/**
	 * 构造方法
	 * @param timeout -- 脚本超时时间，单位：秒
	 */
	public ThUtil(int timeout){
		this.timeout = timeout;
	}
	
	//重载run函数
	public void run() {
		try{
			//变量
			Robot robot = new Robot();
			FileUtil f = new FileUtil();
			int logSize = 0; //日志文件行数
			String logFilePathName = String.format("%s\\log\\test.log", System.getProperty("user.dir"));
			//线程等待固定时间，时间到时，进行操作
			while(true){
				try{
					ThUtil.sleep(timeout * 1000);
//					Thread.sleep(timeout * 1000);
					l.info("****************************** [Monitor thread] triggered ({} seconds) ******************************", timeout);
//					System.out.println("Triggered");
					//获取日志文件
					int currentLogSize = f.readFileLines_noLog(logFilePathName).size();
//					System.out.println(String.format("Previous line num: %d", logSize));
//					System.out.println(String.format("Current line num: %d", currentLogSize - 1));
					if(logSize == currentLogSize - 1){
						l.info("****************************** [Monitor thread] refresh browser & press enter key ******************************");
//						System.out.println("Refresh");
						//日志没有增长
						//刷新页面
					    robot.keyPress(KeyEvent.VK_F5);
					    robot.keyPress(KeyEvent.VK_ENTER);
					}else{
						//日志增长
						//do nothing
					}
					logSize = currentLogSize;
				}catch(Exception e){
					l.info("****************************** [Monitor thread] error ******************************");
//					System.out.println("Error");
					l.error("Error!");
					e.printStackTrace();
					continue;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	} 
	
}
