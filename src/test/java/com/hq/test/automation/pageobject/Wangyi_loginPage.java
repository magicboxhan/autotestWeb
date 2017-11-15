package com.hq.test.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 登录页面对象类
 *
 * @author hanqing
 */
public class Wangyi_loginPage extends Zhihuiya_basePage {

    public Wangyi_loginPage(WebDriver driver) {
        l.entry();
        d = driver;
//		selfcheckSelector = By.id("login163");
        selfcheckSelector = null;
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
        return d.findElement(By.id("idInput"));
    }

    /**
     * 输入框 -- 密码
     *
     * @return
     */
    public WebElement input_pwd() {
        l.entry();
        return d.findElement(By.id("pwdInput"));
    }

    /**
     * 链接 -- 登录
     *
     * @return
     */
    public WebElement link_login() {
        l.entry();
        return d.findElement(By.id("loginBtn"));
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
        if (this.doesExist(By.id("loginBtn"))) {
            l.debug("Need to login");
            this.input_uid().clear();
            this.input_uid().sendKeys(uid);
            this.input_pwd().sendKeys(pwd);
            this.link_login().click();
        } else {
            l.debug("Do not need to login");
        }
        l.exit();
    }
}
