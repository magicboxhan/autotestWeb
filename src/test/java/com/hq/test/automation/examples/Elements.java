package com.hq.test.automation.examples;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by hanqing on 2015/1/13.
 */
public class Elements {
    public void test() throws InterruptedException {
        WebDriver d = new FirefoxDriver();
        d.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        d.manage().window().maximize();
        d.get("file:///D:/1.html");
        WebElement e = null;
//		String e = null;


        //=====文本框=====
        e = d.findElement(By.id("id_textField"));
        e = d.findElement(By.className("class_textField"));
        e = d.findElement(By.name("name_textField"));
        e = d.findElement(By.tagName("input"));
        e.clear();
        e.sendKeys("new text");


        //=====按钮=====
        e = d.findElement(By.id("id_button"));
        e.click();
        d.switchTo().alert().accept();


        //=====下拉框=====
        Select s = new Select(d.findElement(By.id("id_select")));
        s.selectByIndex(1);

        s.selectByIndex(0);
        s.selectByValue("Value2");

        s.selectByIndex(0);
        s.selectByVisibleText("Option1");


        //=====链接=====
        e = d.findElement(By.linkText("baidu"));
        e.click();
        d.switchTo().window(d.getWindowHandles().toArray()[d.getWindowHandles().toArray().length - 1].toString());
        d.findElement(By.id("kw1")).sendKeys("test");
        d.findElement(By.id("kw1")).submit();
        d.close();
        d.switchTo().window(d.getWindowHandles().toArray()[0].toString());

        //=====单选框=====
        e = d.findElements(By.id("id_radio")).get(0);
        e.click();


        //=====复选框=====
        for (WebElement checkbox : d.findElements(By.id("id_checkbox"))) {
            checkbox.click();
        }


        //=====表格=====
        WebElement table = d.findElement(By.id("id_table"));
        for (WebElement tr : table.findElements(By.tagName("tr"))) {
            for (WebElement td : tr.findElements(By.tagName("td"))) {
                System.out.println(td.getText());
            }
        }


        //=====退出浏览器=====
        d.quit();
    }
}
