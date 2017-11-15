package com.hq.test.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 账户设置页
 *
 * @author hanqing
 */
public class Zhihuiya_accountPage extends Zhihuiya_basePage {

    public Zhihuiya_accountPage(WebDriver driver) {
        l.entry();
        d = driver;
        selfcheckSelector = By.id("creataccount");
        l.exit();
    }

    /********** Elements **********/

    /**
     * 输入框 --旧密码
     *
     * @return
     */
    public WebElement input_pwd_old() {
        l.entry();
        return d.findElement(By.id("oldpassword"));
    }

    /**
     * 输入框 --新密码
     *
     * @return
     */
    public WebElement input_pwd_new() {
        l.entry();
        return d.findElement(By.id("password"));
    }

    /**
     * 输入框 --确认新密码
     *
     * @return
     */
    public WebElement input_pwd_confirm() {
        l.entry();
        return d.findElement(By.id("password2"));
    }

    /**
     * 链接 --提交
     *
     * @return
     */
    public WebElement link_submit() {
        l.entry();
        return d.findElement(By.id("creataccount"));
    }


    /********** Functions **********/

    /**
     * 修改密码
     *
     * @param old_pwd
     * @param new_pwd
     */
    public void func_changePassword(String old_pwd, String new_pwd) {
        l.entry();
        this.input_pwd_old().clear();
        this.input_pwd_old().sendKeys(old_pwd);
        this.input_pwd_new().sendKeys(new_pwd);
        this.input_pwd_confirm().sendKeys(new_pwd);
        this.link_submit().click();
        l.exit();
    }
}
