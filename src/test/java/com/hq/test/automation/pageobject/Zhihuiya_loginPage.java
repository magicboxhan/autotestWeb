package com.hq.test.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 登录页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_loginPage extends Zhihuiya_basePage {

    public Zhihuiya_loginPage(WebDriver driver) {
        l.entry();
        d = driver;
        selfcheckSelector = By.id("LOG-BUTTON");
        l.exit();
    }

    /********** Elements **********/

    /**
     * 输入框 -- 用户名
     *
     * @return
     */
    public WebElement input_uid() {
        l.entry();
        return d.findElement(By.id("ACOUNT"));
    }

    /**
     * 输入框 -- 密码
     *
     * @return
     */
    public WebElement input_pwd() {
        l.entry();
        return d.findElement(By.id("UNAME"));
    }

    /**
     * 链接 -- 登录
     *
     * @return
     */
    public WebElement link_login() {
        l.entry();
        return d.findElement(By.id("LOG-BUTTON"));
    }


    /********** Functions **********/

    /**
     * login
     *
     * @param uid
     * @param pwd
     */
    public void func_login(String uid, String pwd) {
        l.entry();
        if (this.doesExist(By.id("LOG-BUTTON"))) {
            l.debug("Need to login");
            this.input_uid().clear();
            this.input_uid().sendKeys(uid);
            this.input_pwd().sendKeys(pwd);
//			l.debug("===== take screenShot =====");
//			this.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            //解决未知登录错误--用户名输入不全
            l.debug("current uid text: {}", input_uid().getAttribute("value"));
            if (!this.input_uid().getAttribute("value").equals(uid)) {
                this.input_uid().clear();
                this.input_uid().sendKeys(uid);
            }
            this.link_login().click();
        } else if (this.doesExist(By.className("login"))) {
            //特殊IP
            d.findElement(By.className("login")).click();
            this.input_uid().clear();
            this.input_uid().sendKeys(uid);
            this.input_pwd().sendKeys(pwd);
            l.debug("current uid text: {}", input_uid().getAttribute("value"));
            if (!this.input_uid().getAttribute("value").equals(uid)) {
                this.input_uid().clear();
                this.input_uid().sendKeys(uid);
            }
            this.link_login().click();
            d.findElement(By.className("sme-header-tab")).findElements(By.tagName("a")).get(1).click();
        } else {
            l.debug("Do not need to login");
        }

        l.exit();
    }
}
