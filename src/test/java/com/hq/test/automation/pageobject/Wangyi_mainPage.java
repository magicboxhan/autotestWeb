package com.hq.test.automation.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 登录页面对象类
 *
 * @author hanqing
 */
public class Wangyi_mainPage extends Zhihuiya_basePage {

    public Wangyi_mainPage(WebDriver driver) {
        l.entry();
        d = driver;
//		selfcheckSelector = By.id("spnUid");
        selfcheckSelector = null;
        l.exit();
    }

    /********** Elements **********/

    /**
     * span--收信
     *
     * @return
     */
    public WebElement span_receiveEmail() {
        l.entry();
        return d.findElement(By.id("dvNavTop")).findElements(By.tagName("li")).get(0).findElements(By.tagName("span")).get(1);
    }

    /**
     * 复选框--全选
     *
     * @return
     */
    public WebElement b_selectAll() {
        l.entry();
        return d.findElement(By.id("dvContainer")).findElement(By.cssSelector(".js-component-toolbar.nui-toolbar")).findElements(By.className("nui-toolbar-item")).get(0).findElement(By.tagName("b"));
    }

    /**
     * 按钮--删除
     *
     * @return
     */
    public WebElement span_delete() {
        l.entry();
        return d.findElement(By.id("dvContainer")).findElement(By.cssSelector(".js-component-toolbar.nui-toolbar")).findElements(By.className("nui-toolbar-item")).get(1).findElement(By.tagName("span"));
    }

    /**
     * 按钮--刷新
     *
     * @return
     */
    public WebElement span_refresh() {
        l.entry();
        return d.findElement(By.id("dvContainer")).findElement(By.cssSelector(".js-component-toolbar.nui-toolbar")).findElements(By.className("nui-toolbar-item")).get(4).findElement(By.tagName("span"));
    }

    /**
     * div--邮件列表
     *
     * @return
     */
    public WebElement div_emailList() {
        l.entry();
        try {
            return d.findElement(By.cssSelector(".tv0"));
        } catch (Exception e) {
            l.debug("Email list does not exist");
            return null;
        }
    }

    /**
     * divs--邮件列表中的行的集合
     *
     * @return
     */
    public List<WebElement> divs_email() {
        l.entry();
        l.exit();
        return this.div_emailList().findElements(By.cssSelector(".rF0.nui-txt-flag0"));
    }

    /**
     * div--根据索引获取邮件行
     *
     * @param index
     * @return
     */
    public WebElement div_email_byIndex(int index) {
        l.entry();
        l.exit();
        return this.divs_email().get(index);
    }

    /**
     * div--根据邮件标题获取邮件行
     *
     * @param exp_title
     * @return
     */
    public WebElement div_email_byTitle(String exp_title) {
        l.entry();
        l.info("Exp email title: [{}]", exp_title);
        for (WebElement div : this.divs_email()) {
            String act_title = div.findElement(By.className("il0")).getText();
            l.debug("Current act email title: [{}]", act_title);
            if (exp_title.equals(act_title)) {
                l.info("Email is found");
                l.exit();
                return div;
            }
        }
        l.info("Email is not found");
        l.exit();
        return null;
    }

    /**
     * iframe--邮件正文
     *
     * @return
     */
    public WebElement iframe_emailBody() {
        l.entry();
        l.exit();
        return d.findElement(By.className("oD0"));
    }

    /**
     * div--邮件正文内容--iframe中
     *
     * @return
     */
    public WebElement div_emailContent() {
        l.entry();
        l.exit();
        return d.findElement(By.tagName("div"));
    }


    /********** Functions **********/

    /**
     * 点击收件
     */
    public void func_click_receiveEmail() {
        l.entry();
        this.span_receiveEmail().click();
        l.exit();
    }

    /**
     * 删除所有邮件
     */
    public void func_deleteAllEmails() {
        l.entry();
        if (this.b_selectAll().isDisplayed()) {
            //如果没被勾选，则勾选
            if (!this.b_selectAll().getAttribute("class").contains("checked")) {
                this.b_selectAll().click();
                this.span_delete().click();
            }
        } else {
            l.debug("Check box is invisible");
        }
        l.exit();
    }

    /**
     * 验证邮件是否正确接收
     *
     * @param title
     * @param timeout
     * @return
     * @throws InterruptedException
     */
    public boolean func_verify_emailExists(String title, int timeout) throws InterruptedException {
        l.entry();
        l.info("Expected email title: [{}]", title);
        boolean result = true;
        boolean emailFlag = true;
        int i = 0;
//		while (!getEmail(title)){
        while (this.div_email_byTitle(title) == null) {
            if (i < timeout) {
                Thread.sleep(1000);
                //刷新页面
                d.navigate().refresh();
                l.debug("receiving email...");
                i++;
            } else {
                l.debug("timeout");
                emailFlag = false;
                break;
            }
        }
        if (emailFlag) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Email exists");
            result &= true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Email does not exist");
            t.takeScreenshot(d);
            result &= false;
        }
        return result;
    }

    /**
     * 尝试获取邮件
     *
     * @param title
     * @return
     */
    public boolean getEmail(String title) {
        l.entry();
        boolean result = true;
        boolean matchFlag = false;
        if (this.div_emailList() == null) {
            l.debug("Email list does not exist");
            return false;
        }
        for (WebElement row : this.div_emailList().findElements(By.cssSelector(".rF0.kw0.nui-txt-flag0"))) {
            String actTitle = row.findElement(By.className("il0")).findElement(By.className("da0")).getText();
            l.info("Current email title: [{}]", actTitle);
            if (title.equals(actTitle)) {
                l.debug("Email exists");
                result &= true;
                matchFlag = true;
                break;
            }
        }
        if (!matchFlag) {
            l.debug("Email does not exist");
            return false;
        }
        return result;
    }

    /**
     * 根据索引点击邮件
     *
     * @param index
     */
    public void func_click_email_byIndex(int index) {
        l.entry();
        this.div_email_byIndex(index).findElement(By.className("il0")).click();
        l.exit();
    }

    /**
     * 根据标题点击邮件
     *
     * @param title
     */
    public void func_click_email_byTitle(String title) {
        l.entry();
        this.div_email_byTitle(title).findElement(By.className("il0")).click();
        l.exit();
    }

    /**
     * 获取邮件正文内容
     *
     * @return
     */
    public String func_get_emailContent() {
        l.entry();
        try {
            //切换到iframe
            d.switchTo().frame(this.iframe_emailBody());
            String content = this.div_emailContent().getText();
            l.info("Email content: [{}]", content);
            d.switchTo().defaultContent();
            l.exit();
            return content;
        } catch (Exception e) {
            l.error("Can not get email content");
            e.printStackTrace();
            d.switchTo().defaultContent();
            l.exit();
            return "";
        }
    }

    /**
     * 切换到邮件提
     */
    public void func_switchTo_emailBody() {
        l.entry();
        d.switchTo().frame(this.iframe_emailBody());
        l.exit();
    }

    /**
     * 点击邮件中的链接（通过索引）
     *
     * @param index
     */
    public void func_click_emailBodyLink_byIndex(int index) {
        l.entry();
        this.func_switchTo_emailBody();
        d.findElements(By.tagName("a")).get(index).click();
        l.exit();
    }

    /**
     * 验证导出链接是否正确
     *
     * @return
     */
    public boolean func_verify_exportLink() {
        l.entry();
        String content = d.findElement(By.tagName("body")).getText();
        l.info(content);
        if (content.equals("")) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Export link is correct");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Export link is incorrect");
            t.takeScreenshot(d);
            return false;
        }
    }

}
