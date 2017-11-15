package com.hq.test.automation.examples;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by hanqing on 2015/1/13.
 */
public class Examples {
    public void test() throws InterruptedException {
        WebDriver d = new FirefoxDriver();
        d.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        d.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        d.manage().window().maximize();
        //访问地址，登录
        d.get("http://analytics.patsnap.com");
        d.findElement(By.id("ACOUNT")).clear();
        d.findElement(By.id("ACOUNT")).sendKeys("hanqing@zhihuiya.com");
        d.findElement(By.id("UNAME")).clear();
        d.findElement(By.id("UNAME")).sendKeys("123456");
        d.findElement(By.id("UNAME")).submit();
        //搜索
        d.findElement(By.name("TTL")).clear();
        d.findElement(By.name("TTL")).sendKeys("123456");
        d.findElement(By.cssSelector(".btn-26.primary.btn-search")).click();
        //点击搜索结果页PN链接
        d.findElement(By.className("PN")).click();
        Thread.sleep(10000);
        d.quit();
    }
}
