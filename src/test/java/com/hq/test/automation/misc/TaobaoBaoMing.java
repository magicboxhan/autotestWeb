package com.hq.test.automation.misc;

import com.hq.test.framework.testcase.BaseTestcase;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class TaobaoBaoMing extends BaseTestcase {

    @Parameters({
            "name", "gender", "email", "phone", "company", "job", "city", "years"
    })
    @Test
    public void Baoming(
            String name,
            int gender,
            String email,
            String phone,
            String company,
            String job,
            String city,
            int years
    ) throws IOException {
        try {
            l.entry();
            // 首页
            d.get("http://www.taobaotest.com/tcon/2014");
            d.findElement(By.id("join")).click();
            // 姓名
            d.findElement(By.name("user[name]")).sendKeys(name);
            // 性别
            if (gender == 0) {
                d.findElements(By.name("user[gender]")).get(0).click();
            } else {
                d.findElements(By.name("user[gender]")).get(1).click();
            }
            // 邮箱
            d.findElement(By.name("user[email]")).sendKeys(email);
            // 手机
            d.findElement(By.name("user[telphone]")).sendKeys(phone);
            // 公司
            d.findElement(By.name("user[company]")).sendKeys(company);
            // 职务
            d.findElement(By.name("user[occupation]")).sendKeys(job);
            // 地点
            d.findElement(By.name("user[location]")).sendKeys(city);
            // 年限
            d.findElements(By.name("user[job]")).get(years).click();
            // 提交
//			Thread.sleep(5000);
            d.findElement(By.className("submit")).click();
//			Thread.sleep(5000);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            Assert.assertEquals(false, true);
        }
    }
}
