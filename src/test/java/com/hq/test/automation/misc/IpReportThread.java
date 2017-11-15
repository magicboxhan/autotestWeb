package com.hq.test.automation.misc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IpReportThread extends Thread {

    Logger l = LogManager.getLogger(this.getClass().getName());
    String name;
    WebDriver d;
    By by1;
    By by2;
    long startTime;
    int status;

    public IpReportThread(String p_name, WebDriver p_d, By p_by1, By p_by2, long p_startTime) {
        name = p_name;
        d = p_d;
        by1 = p_by1;
        by2 = p_by2;
        startTime = p_startTime;
        status = 0;
    }

    @SuppressWarnings("static-access")
    public void run() {
        l.debug("[{}] Thread start", name);
        boolean isFinished = false; //成功标志
        int i = 0; //循环次数
        while ((!isFinished) && (i < 270)) {
            //先判断是否失败
            try {
                if (d.findElement(by1).findElement(By.className("errorRetry")).isDisplayed()) {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Failed -- [{}]", name);
                    status = -1;
                    return;
                }
            } catch (Exception e) {
                //do nothing
            }
            //等待分析完成
            try {
                d.findElement(by1).findElement(by2);
                isFinished = true;
                l.info("++++++++++++++++++++++++++++++ Passed -- [{}], duration :[{}] seconds", name, (System.currentTimeMillis() - startTime) / 1000);
                status = 1;
                break;
            } catch (Exception e) {
                l.debug("[{}] Waiting for report...", name);
                try {
                    this.sleep(1000);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            i++;
        }
        if (!isFinished) {
            l.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Timeout -- [{}]", name);
            status = -1;
        }
    }

    public int getStatus() {
        return status;
    }
}
