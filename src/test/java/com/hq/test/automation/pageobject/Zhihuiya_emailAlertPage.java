package com.hq.test.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮件提醒页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_emailAlertPage extends Zhihuiya_basePage {

    public Zhihuiya_emailAlertPage(WebDriver driver) {
        l.entry();
        d = driver;
        selfcheckSelector = By.className("emailalert-tools");
        l.exit();
    }

    /********** Elements **********/

    /**
     * 邮件提醒列表
     *
     * @return
     */
    public WebElement div_emailAlert() {
        l.entry();
        return d.findElement(By.className("emailalert-grid"));
    }

    /**
     * div -- 新增邮件提醒按钮列表
     *
     * @return
     */
    public WebElement div_emailAlertTool() {
        l.entry();
        l.exit();
        return d.findElement(By.className("emailalert-tools"));
    }

    /**
     * 文本框 -- 邮件提醒详情 -- 标题
     *
     * @return
     */
    public WebElement input_emailAlertInfo_title() {
        l.entry();
        l.exit();
        return d.findElement(By.name("title"));
    }

    /**
     * 文本框 -- 邮件提醒详情 -- 搜索内容
     *
     * @return
     */
    public WebElement textarea_emailAlertInfo_searchContent() {
        l.entry();
        l.exit();
        return d.findElement(By.name("search_content"));
    }

    /**
     * 复选框 -- 邮件提醒详情 -- 其它邮箱
     *
     * @return
     */
    public WebElement input_emailAlertInfo_sendToOthers() {
        l.entry();
        l.exit();
        return d.findElement(By.name("send_to_others"));
    }

    /**
     * 文本框 -- 邮件提醒详情 -- 收件人
     *
     * @return
     */
    public WebElement textarea_emailAlertInfo_recipients() {
        l.entry();
        l.exit();
        return d.findElement(By.name("recipients"));
    }

    /**
     * 单选框 -- 邮件提醒详情 -- 频率 -- 根据索引
     *
     * @param index
     * @return
     */
    public WebElement input_emailAlertInfo_frequency_byIndex(int index) {
        l.entry();
        l.exit();
        return d.findElements(By.name("freq")).get(index);
    }

    /**
     * 按钮 -- 邮件提醒详情 -- 监控对象类型 -- 根据索引
     *
     * @param index
     * @return
     */
    public WebElement input_emailAlertInfo_sourceType_byIndex(int index) {
        l.entry();
        l.exit();
        return d.findElements(By.name("source_type")).get(index);
    }

    /**
     * label -- 邮件提醒详情 -- 监控对象类型 -- 根据索引
     *
     * @param index
     * @return
     */
    public WebElement label_emailAlertInfo_sourceType_byIndex(int index) {
        l.entry();
        l.exit();
        return d.findElements(By.className("tab-group")).get(0).findElement(By.className("m-btn-group")).findElements(By.tagName("label")).get(index);
    }

    /**
     * 下拉框 -- 邮件提醒详情 -- 每周选项
     *
     * @return
     */
    public Select select_emailAlertInfo_weekly() {
        l.entry();
        l.exit();
        return new Select(d.findElement(By.name("freq_week_day")));
    }

    /**
     * 下拉框 -- 邮件提醒详情 -- 每月选项
     *
     * @return
     */
    public Select select_emailAlertInfo_monthly() {
        l.entry();
        l.exit();
        return new Select(d.findElement(By.name("freq_month_day")));
    }

    /**
     * 下拉框 -- 邮件提醒详情 -- 收藏夹列表
     *
     * @return
     */
    public Select select_emailAlertInfo_userListID() {
        l.entry();
        l.exit();
        return new Select(d.findElement(By.name("userlist_id")));
    }

    /**
     * 按钮 -- 邮件提醒详情 -- 过期 -- 根据索引
     *
     * @param index
     * @return
     */
    public WebElement input_emailAlertInfo_duration_byIndex(int index) {
        l.entry();
        l.exit();
        return d.findElements(By.className("tab-group")).get(1).findElement(By.className("m-btn-group")).findElements(By.tagName("label")).get(index);
//		return d.findElement(By.id("alert_form")).findElements(By.name("expire")).get(index);
    }

    /**
     * 单选框 -- 邮件提醒详情 -- 邮件内容 -- 根据索引
     *
     * @param index
     * @return
     */
    public WebElement input_emailAlertInfo_emailContent_byIndex(int index) {
        l.entry();
        l.exit();
        return d.findElements(By.name("content_type")).get(index);
    }

    /**
     * 按钮 -- 邮件提醒详情 -- 提交
     *
     * @return
     */
    public WebElement button_emailAlertInfo_submit() {
        l.entry();
        l.exit();
        return d.findElement(By.id("alert_form")).findElement(By.className("primary"));
    }

    /**
     * 按钮--新增邮件提醒--专利更新
     *
     * @return
     */
    public WebElement link_new_emailAlert_pu() {
        l.entry();
        l.exit();
        return d.findElement(By.className("emailalert-tools")).findElements(By.tagName("a")).get(0);
    }

    /**
     * 按钮--新增邮件提醒--法律状态
     *
     * @return
     */
    public WebElement link_new_emailAlert_ls() {
        l.entry();
        l.exit();
        return d.findElement(By.className("emailalert-tools")).findElements(By.tagName("a")).get(1);
    }

    /**
     * 链接--页码
     *
     * @param index
     * @return
     */
    public WebElement link_pageNum(int index) {
        l.entry();
        return d.findElement(By.className("page-number")).findElement(By.linkText(String.valueOf(index)));
    }

    /**
     * 根据邮件提醒名称，获取对应的li *
     *
     * @param expName 期望名称
     * @return
     */
    public WebElement li_emailAlertInfo(String expName) {
        l.entry();
        WebElement returnElement = null;
        l.debug("Exp name is {}:", expName);
        for (WebElement li : div_emailAlert().findElements(By.tagName("li"))) {
            String actName = li.findElement(By.tagName("dt")).findElement(By.tagName("b")).getText();
            l.debug("Current act name is: {}", actName);
            if (expName.equals(actName)) {
                returnElement = li;
                break;
            }
        }
        return returnElement;
    }

    /**
     * 链接--当前页码
     *
     * @return
     */
    public WebElement link_current_pageNum() {
        l.entry();
        return d.findElement(By.className("page-number")).findElement(By.className("current"));
    }

    /**
     * 根据名称获取邮件提醒，并根据索引获取连接*
     *
     * @param name  - 邮件提醒名称
     * @param index - 按钮索引（0：Active，1：Inactive，2：Edit，3：Delete）
     * @return
     */
    public WebElement link_emailAlertButtonByName(String name, int index) {
        l.entry();
        return this.li_emailAlertInfo(name).findElements(By.tagName("a")).get(index);
    }


    /********** Functions **********/

    /**
     * 验证邮件提醒是否存在--简单验证
     *
     * @param name -- 期望名称
     * @return 是否存在
     */
    public boolean func_verify_emailAlertExists(String name) {
        l.entry();
        boolean result = false;
        boolean matchFlag = false;
        l.info("Expected email alert name is [{}]", name);
        if (!this.doesExist(By.className("emailalert-grid"))) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Email alert table is not found");
            t.takeScreenshot(d);
            return false;
        }
        WebElement div = this.div_emailAlert();
        for (WebElement li : div.findElements(By.tagName("li"))) {
            String actName = li.findElement(By.className("clearfix")).findElement(By.tagName("b")).getText();
            l.debug("Current actual email alert name is [{}]", actName);
            if (actName.equals(name)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Email alert is found");
                result = true;
                matchFlag = true;
                break;
            }
        }
        if (!matchFlag) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Email alert is not found");
            t.takeScreenshot(d);
            result = false;
        }
        return result;
    }

    /**
     * 验证邮件提醒是否不存在
     *
     * @param name -- 期望名称
     * @return 是否不存在
     */
    public boolean func_verify_emailAlertNotExists(String name) {
        l.entry();
        l.info("Expected email alert name is {}", name);
        //邮件提醒列表存在时，才验证
        if (this.doesExist(By.className("emailalert-grid"))) {
            WebElement div = this.div_emailAlert();
            for (WebElement li : div.findElements(By.tagName("li"))) {
                String actName = li.findElement(By.className("clearfix")).findElement(By.tagName("b")).getText();
                l.debug("Current actual email alert name is [{}]", actName);
                if (actName.equals(name)) {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Email alert is found");
                    t.takeScreenshot(d);
                    return false;
                }
            }
        }
        l.info("++++++++++++++++++++++++++++++ Pass -- Email alert is not found");
        return true;
    }

    /**
     * 删除指定邮件提醒
     *
     * @param name
     */
    public void func_delete_emailAlert(String name) {
        l.entry();
        l.debug("Expected email alert name is {}", name);
        WebElement div = this.div_emailAlert();
        //获取指定邮件提醒所在行
        for (WebElement li : div.findElements(By.tagName("li"))) {
            if (li.findElement(By.className("clearfix")).findElement(By.tagName("b")).getText().contains(name)) {
                //找到数据所在行了，删除
                li.findElement(By.className("btn-remove")).click();
                d.switchTo().alert().accept();
                l.debug("Email alert is deleted");
                break;
            }
        }
    }

    /**
     * 点删除并取消
     *
     * @param name
     */
    public void func_deleteAndCancel_emailAlert(String name) {
        l.entry();
        l.debug("Expected email alert name is {}", name);
        WebElement div = this.div_emailAlert();
        //获取指定邮件提醒所在行
        for (WebElement li : div.findElements(By.tagName("li"))) {
            if (li.findElement(By.className("clearfix")).findElement(By.tagName("b")).getText().contains(name)) {
                //找到数据所在行了，删除
                li.findElement(By.className("btn-remove")).click();
                d.switchTo().alert().dismiss();
                break;
            }
        }
    }

    /**
     * 编辑邮件提醒
     *
     * @param name
     * @param newName
     */
    public void func_edit_emailAlert(String name, String newName) {
        l.entry();
        l.debug("Expected email alert name is {}", name);
        WebElement div = this.div_emailAlert();
        //获取指定邮件提醒所在行
        for (WebElement li : div.findElements(By.tagName("li"))) {
            if (li.findElement(By.className("clearfix")).findElement(By.tagName("b")).getText().contains(name)) {
                //找到数据所在行了，编辑
                li.findElements(By.className("btn-26")).get(2).click();
                this.input_emailAlertInfo_title().clear();
                this.input_emailAlertInfo_title().sendKeys(newName);
                this.button_emailAlertInfo_submit().click();
                break;
            }
        }
    }

    /**
     * 验证页面UI
     *
     * @return
     */
    public boolean func_verify_ui() {
        l.entry();
        l.info("========== Verify ui of email alert page ==========");
        boolean result = true;
        try {
            //logo
            l.info("Verify logo");
            if (this.doesExist(By.className("logo"))) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Logo exists");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Logo does not exist");
                result &= false;
            }
            //database
            l.info("Verify database selector");
            if (this.doesExist(By.id("databaseSelector"))) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Database selector exists");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Database selector does not exist");
                result &= false;
            }
            //search box
            l.info("Verify search box");
            if (this.doesExist(By.id("q"))) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Search box exists");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search box does not exist");
                result &= false;
            }
            //export queue
            l.info("Verify export queue");
            if (this.doesExist(By.id("btn_monitor"))) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Export queue exists");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Export queue does not exist");
                result &= false;
            }
            //account
            l.info("Verify account");
            if (this.doesExist(By.cssSelector(".user-name.tools"))) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Account exists");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Account does not exist");
                result &= false;
            }
            //uservoice icon
            l.info("Verify uservoice icon");
            if (this.doesExist(By.id("uservoice"))) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Uservoice icon exists");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Uservoice icon does not exist");
                result &= false;
            }
            //page title
            l.info("Verify page title");
            if (d.getTitle().contains("Email Alert")) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Page title [{}] is correct", d.getTitle());
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Page title [{}] is incorrect", d.getTitle());
                result &= false;
            }
            //button of add email alert
            l.info("Verify new email alert buttons");
            if (this.div_emailAlertTool().findElements(By.className("btn-26")).size() == 2) {
                l.info("++++++++++++++++++++++++++++++ Pass -- New email alert buttons exist");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- New email alert buttons do not exist");
                result &= false;
            }
            //footer
            l.info("Verify footer links");
            if (this.ul_footerLinks().findElements(By.tagName("a")).size() == 8) {
                l.info("++++++++++++++++++++++++++++++ Pass -- footer links exist");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- footer links do not exist");
                result &= false;
            }
            //language selector
            l.info("Verify language selector");
            if (this.doesExist(By.className("language-select"))) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Language selector exists");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Language selector does not exist");
                result &= false;
            }
            if (!result) {
                t.takeScreenshot(d, System.getProperty("user.dir"), "Fail_EmailAlertPage_VerifyUI", "jpg");
            }
            l.exit();
            return result;
        } catch (Exception e) {
            l.exit();
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error -- func_verify_ui");
            t.takeScreenshot(d, System.getProperty("user.dir"), "Error_EmailAlertPage_VerifyUI", "jpg");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 验证翻译--简体中文
     *
     * @return
     */
    public boolean func_verify_translation_cn() {
        l.entry();
        boolean result = true;
        l.info("========== Verify translation ==========");
        try {
            if (d.getTitle().contains("邮件提醒")) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Translation is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Translation is incorrect");
                result &= false;
            }
            if (!result) {
                t.takeScreenshot(d, System.getProperty("user.dir"), "Fail_EmailAlertPage_VerifyTranslationCN", "jpg");
            }
            l.exit();
            return result;
        } catch (Exception e) {
            l.exit();
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error -- func_verify_translation_cn");
            t.takeScreenshot(d, System.getProperty("user.dir"), "EmailAlertPage_VerifyTranslationCN", "jpg");
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 删除所有邮件提醒
     */
    public void func_delete_all_alerts() {
        l.entry();
        while (this.doesExist(By.className("btn-remove"))) {
            d.findElement(By.className("btn-remove")).click();
            try {
                d.switchTo().alert().accept();
            } catch (Exception e) {
                //Do nothing
            }
        }
        l.exit();
    }

    /**
     * 创建或编辑邮件提醒（默认值使用-1）
     *
     * @param title--标题
     * @param objType--监控对象类型索引
     * @param query--监控对象为搜索时，搜索语句
     * @param listDropDownIndex--监控对象为收藏夹列表时，收藏夹列表下拉框索引
     * @param otherEmail--是否勾选其它收件人（0；1）
     * @param otherRecipients--其它收件人地址
     * @param frequencyType--频率选项索引
     * @param frequencyDropDownIndex--频率下拉框索引
     * @param durationType--过期类型索引
     * @param contentType--邮件内容类型索引
     */
    public void func_createOrEdit_emailAlert(String title, int objType, String query, int listDropDownIndex, int otherEmail, String otherRecipients, int frequencyType, int frequencyDropDownIndex, int durationType, int contentType) {
        l.entry();
        //标题
        this.input_emailAlertInfo_title().clear();
        this.input_emailAlertInfo_title().sendKeys(title);
        //监控对象
        if (objType != -1) {
            this.label_emailAlertInfo_sourceType_byIndex(objType).click();
            if (objType == 0) {
                //query
                this.textarea_emailAlertInfo_searchContent().clear();
                this.textarea_emailAlertInfo_searchContent().sendKeys(query);
            } else if (objType == 1) {
                //list
                if (listDropDownIndex != -1) {
                    this.select_emailAlertInfo_userListID().selectByIndex(listDropDownIndex);
                }
            }
        }
        //其它收件人
        if (otherEmail == 1) {
            if (!input_emailAlertInfo_sendToOthers().isSelected()) {
                this.input_emailAlertInfo_sendToOthers().click();
            }
            this.textarea_emailAlertInfo_recipients().clear();
            this.textarea_emailAlertInfo_recipients().sendKeys(otherRecipients);
        } else if (otherEmail == 0) {
            if (input_emailAlertInfo_sendToOthers().isSelected()) {
                this.input_emailAlertInfo_sendToOthers().click();
            }
        }
        //频率
        if (frequencyType != -1) {
            this.input_emailAlertInfo_frequency_byIndex(frequencyType).click();
            if (frequencyType == 0) {
                //每周
                if (frequencyDropDownIndex != -1) {
                    this.select_emailAlertInfo_weekly().selectByIndex(frequencyDropDownIndex);
                }
            } else if (frequencyType == 1) {
                //每月
                if (frequencyDropDownIndex != -1) {
                    this.select_emailAlertInfo_monthly().selectByIndex(frequencyDropDownIndex);
                }
            }
        }
        //过期
        if (durationType != -1) {
            this.input_emailAlertInfo_duration_byIndex(durationType).click();
        }
        //邮件内容类型
        if (contentType != -1) {
            try {
                this.input_emailAlertInfo_emailContent_byIndex(contentType).click();
            } catch (Exception e) {
                //do nothing
            }
        }
        //提交
        this.button_emailAlertInfo_submit().click();
        l.exit();
    }

    public boolean func_verify_emailAlert_addEditEmailAlertPage(int type, String title, int objType, String query, int listDropDownIndex, int otherEmail, String otherRecipients, int frequencyType, int frequencyDropDownIndex, int durationType, int contentType) {
        l.entry();
        boolean result = true;
        //标题
        String expTitle = title;
        String actTitle = input_emailAlertInfo_title().getAttribute("value");
        if (expTitle.equals(actTitle)) {
            l.info("++++++++++++++++++++++++++++++ Pass -- [Title], exp value: {}, act value: {}", expTitle, actTitle);
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [Title], exp value: {}, act value: {}", expTitle, actTitle);
            result &= false;
        }
        //监控对象
        boolean objTypeResult = true;
        int expObjType = objType;
        int actObjType = -1;
        if (label_emailAlertInfo_sourceType_byIndex(0).getAttribute("class").contains("selected")) {
            actObjType = 0;
        } else if (label_emailAlertInfo_sourceType_byIndex(1).getAttribute("class").contains("selected")) {
            actObjType = 1;
        }
        if (expObjType == actObjType) {
            l.info("++++++++++++++++++++++++++++++ Pass -- [Monitor Objects], exp value: {}, act value: {}", expObjType, actObjType);
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [Monitor Objects], exp value: {}, act value: {}", expObjType, actObjType);
            objTypeResult = false;
            result &= false;
        }
        //query
        if ((expObjType == 0) && (objTypeResult == true)) {
            //query
            String expQuery = query;
            String actQuery = textarea_emailAlertInfo_searchContent().getText();
            if (expQuery.equals(actQuery)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- [Query], exp value: {}, act value: {}", expQuery, actQuery);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [Query], exp value: {}, act value: {}", expQuery, actQuery);
                result &= false;
            }
        }
        //list
        if ((expObjType == 1) && (objTypeResult == true)) {
            //query
            WebElement expOption = select_emailAlertInfo_userListID().getOptions().get(listDropDownIndex);
            WebElement actOption = select_emailAlertInfo_userListID().getFirstSelectedOption();
            if (expOption.equals(actOption)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- [List Folder], exp value: {}, act value: {}", expOption, actOption);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [List Folder], exp value: {}, act value: {}", expOption, actOption);
                result &= false;
            }
        }
        //其它收件人
        int expOtherEmail = otherEmail;
        int actOtherEmail = -1;
        boolean isOtherEmail = input_emailAlertInfo_sendToOthers().isSelected();
        if (isOtherEmail) {
            actOtherEmail = 1;
        } else if (!isOtherEmail) {
            actOtherEmail = 0;
        }
        if (expOtherEmail == actOtherEmail) {
            l.info("++++++++++++++++++++++++++++++ Pass -- [Is other email], exp value: {}, act value: {}", expOtherEmail, actOtherEmail);
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [Is other email], exp value: {}, act value: {}", expOtherEmail, actOtherEmail);
            result &= false;
        }
        if (expOtherEmail == 1) {
            //验证其它收件人
            String expOtherRecipients = otherRecipients;
            String actOtherRecipients = textarea_emailAlertInfo_recipients().getText();
            if (expOtherRecipients.equals(actOtherRecipients)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- [OtherRecipients], exp value: {}, act value: {}", expOtherRecipients, actOtherRecipients);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [OtherRecipients], exp value: {}, act value: {}", expOtherRecipients, actOtherRecipients);
                result &= false;
            }
        }
        //频率
        //radio
        int expFreq = frequencyType;
        int actFreq = -1;
        if (input_emailAlertInfo_frequency_byIndex(0).isSelected()) {
            actFreq = 0;
        } else if (input_emailAlertInfo_frequency_byIndex(1).isSelected()) {
            actFreq = 1;
        }
        if (expFreq == actFreq) {
            l.info("++++++++++++++++++++++++++++++ Pass -- [Frequency Type], exp value: {}, act value: {}", expFreq, actFreq);
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [Frequency Type], exp value: {}, act value: {}", expFreq, actFreq);
            result &= false;
        }
        //select
        if (expFreq == 0) {
            WebElement expFreq1 = select_emailAlertInfo_weekly().getOptions().get(frequencyDropDownIndex);
            WebElement actFreq1 = select_emailAlertInfo_weekly().getFirstSelectedOption();
            if (expFreq1.equals(actFreq1)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- [Week day], exp value: {}, act value: {}", expFreq1, actFreq1);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [Week day], exp value: {}, act value: {}", expFreq1, actFreq1);
                result &= false;
            }
        } else if (expFreq == 1) {
            WebElement expFreq2 = select_emailAlertInfo_monthly().getOptions().get(frequencyDropDownIndex);
            WebElement actFreq2 = select_emailAlertInfo_monthly().getFirstSelectedOption();
            if (expFreq2.equals(actFreq2)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- [Month day], exp value: {}, act value: {}", expFreq2, actFreq2);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [Month day], exp value: {}, act value: {}", expFreq2, actFreq2);
                result &= false;
            }
        }
        //过期
        int expDuration = durationType;
        int actDuration = -1;
        if (input_emailAlertInfo_duration_byIndex(0).getAttribute("class").contains("selected")) {
            actDuration = 0;
        } else if (input_emailAlertInfo_duration_byIndex(1).getAttribute("class").contains("selected")) {
            actDuration = 1;
        } else if (input_emailAlertInfo_duration_byIndex(2).getAttribute("class").contains("selected")) {
            actDuration = 2;
        }
        if (expDuration == actDuration) {
            l.info("++++++++++++++++++++++++++++++ Pass -- [Duration], exp value: {}, act value: {}", expDuration, actDuration);
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [Duration], exp value: {}, act value: {}", expDuration, actDuration);
            result &= false;
        }
        //邮件内容类型
        if (type == 0) {
            //类型为专利更新提醒
            int expContent = contentType;
            int actContent = -1;
            if (input_emailAlertInfo_emailContent_byIndex(0).isSelected()) {
                actContent = 0;
            } else if (input_emailAlertInfo_emailContent_byIndex(1).isSelected()) {
                actContent = 1;
            }
            if (expContent == actContent) {
                l.info("++++++++++++++++++++++++++++++ Pass -- [Frequency Type], exp value: {}, act value: {}", expContent, actContent);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [Frequency Type], exp value: {}, act value: {}", expContent, actContent);
                result &= false;
            }
        }
        l.exit();
        return result;
    }

    /**
     * 点击新增邮件提醒按钮--专利更新
     */
    public void func_click_newEmailAlert_pu() {
        l.entry();
        this.link_new_emailAlert_pu().click();
        l.exit();
    }

    /**
     * 点击新增邮件提醒按钮--法律状态更新
     */
    public void func_click_newEmailAlert_ls() {
        l.entry();
        this.link_new_emailAlert_ls().click();
        l.exit();
    }

    /**
     * 验证页码是否存在
     *
     * @return
     */
    public boolean func_verify_pageNumIsDisplayed() {
        l.entry();
        boolean result = true;
        if (this.isVisible(By.className("page-number"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Page number is displayed");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Page number is not displayed");
            t.takeScreenshot(d, System.getProperty("user.dir"), "EmailAlertPage_func_verify_pageNumIsDisplayed", "jpg");
            result &= false;
        }
        l.exit();
        return result;
    }

    /**
     * 验证页码是否不存在
     *
     * @return
     */
    public boolean func_verify_pageNumIsNotDisplayed() {
        l.entry();
        boolean result = true;
        if (!this.isVisible(By.className("page-number"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Page number is not displayed");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Page number is displayed");
            t.takeScreenshot(d, System.getProperty("user.dir"), "EmailAlertPage_func_verify_pageNumIsNotDisplayed", "jpg");
            result &= false;
        }
        l.exit();
        return result;
    }

    /**
     * 验证邮件提醒数量
     *
     * @param exp_num
     * @return
     */
    public boolean func_verify_emailAlertNum(int exp_num) {
        l.entry();
        boolean result = true;
        l.info("Exp num of email alert: [{}]", exp_num);
        int act_num = this.func_get_emailAlertNum();
        l.info("Act num of email alert: [{}]", act_num);
        if (exp_num == act_num) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Num of email alert is correct");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Num of email alert is incorrect");
            t.takeScreenshot(d, System.getProperty("user.dir"), "EmailAlertPage_func_verify_emailAlertNum", "jpg");
            result &= false;
        }
        l.exit();
        return result;
    }

    /**
     * 获取当页邮件提醒数量
     *
     * @return
     */
    public int func_get_emailAlertNum() {
        l.entry();
        l.exit();
        return this.div_emailAlert().findElements(By.tagName("li")).size();
    }

    public HashMap<String, String> func_get_emailAlertData(String emailAlertName) {
        l.entry();
        HashMap<String, String> emailAlertData = new HashMap<>();
        WebElement li = this.li_emailAlertInfo(emailAlertName);
        //存储页面邮件提醒数据
        //名称
        emailAlertData.put("name", emailAlertName);
        //类型
        String type = "";
        switch (li.findElement(By.className("alert-name")).getText()) {
            case "Patent Update":
                type = "0";
                break;
            case "Legal Status":
                type = "1";
                break;
        }
        emailAlertData.put("type", type);
        //监控对象
        String obj = "";
        if (li.findElement(By.className("cell-content")).findElement(By.tagName("p")).getText().contains("search query")) {
            obj = "0";
        } else if (li.findElement(By.className("cell-content")).findElement(By.tagName("p")).getText().contains("lists")) {
            obj = "1";
        }
        emailAlertData.put("obj", obj);
        //是否有其他收件人
        String isOtherEmail = "";
        if (li.findElements(By.className("emailalert-other")).size() > 0) {
            isOtherEmail = "1";
        } else {
            isOtherEmail = "0";
        }
        emailAlertData.put("isOtherEmail", isOtherEmail);
        //频率
        String frequencyIndex = li.findElement(By.className("emailalert-frequency")).getText();
        emailAlertData.put("frequencyIndex", String.valueOf(frequencyIndex));
        //专利数量
        String patentNumber = li.findElement(By.className("patent-count")).getText().replaceAll(",", "");
        emailAlertData.put("patentNumber", patentNumber);
        l.exit();
        return emailAlertData;
    }

    public boolean func_verify_emailAlertData(HashMap<String, String> expData, HashMap<String, String> actData) {
        l.entry();
        boolean result = true;
        //验证名称
        for (Map.Entry entry : expData.entrySet()) {
            String key = entry.getKey().toString();
            String expValue = entry.getValue().toString();
            String actValue = actData.get(key);
            l.info("Verify [{}]", key);
            l.info("Exp value: {}", expValue);
            l.info("Act value: {}", actValue);
            if (expValue.equals(actValue)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Value is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Value is incorrect");
                t.takeScreenshot(d, System.getProperty("user.dir"), "EmailAlertPage_func_verify_emailAlertData", "jpg");
                result &= false;
            }
        }
        l.exit();
        return result;
    }

    /**
     * 点击页码
     *
     * @param index
     */
    public void func_click_pageNum(int index) {
        l.entry();
        this.link_pageNum(index).click();
        l.exit();
    }

    /**
     * 验证当前页面是否正确
     *
     * @param exp_pageNum
     * @return
     */
    public boolean func_verify_currentPageNum(int exp_pageNum) {
        l.entry();
        boolean result = true;
        l.info("Exp page num: [{}]", exp_pageNum);
        int act_pageNum = Integer.valueOf(this.link_current_pageNum().getText());
        l.info("Act page num: [{}]", act_pageNum);
        if (exp_pageNum == act_pageNum) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Current page num is correct");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Current page num is incorrect");
            t.takeScreenshot(d, System.getProperty("user.dir"), "EmailAlertPage_func_verify_currentPageNum", "jpg");
            result &= false;
        }
        l.exit();
        return result;
    }

    /**
     * 根据索引获取邮件提醒名称
     *
     * @param index
     * @return
     */
    public String func_get_emailAlertName_byIndex(int index) {
        l.entry();
        try {
            String alertName = this.div_emailAlert().findElements(By.tagName("li")).get(index).findElement(By.tagName("b")).getText();
            l.info("Email alert [{}], name: [{}]", index, alertName);
            l.exit();
            return alertName;
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error -- Can not get name of email alert [{}]", index);
            l.exit();
            return null;
        }
    }

    /**
     * 点击编辑按钮*
     *
     * @param name - 邮件提醒名称
     * @return
     */
    public void func_click_editButton(String name) {
        l.entry();
        link_emailAlertButtonByName(name, 2).click();
        l.exit();
    }

    /**
     * 点击active按钮*
     *
     * @param name - 邮件提醒按钮
     */
    public void func_click_activeButton(String name) {
        l.entry();
        link_emailAlertButtonByName(name, 0).click();
        l.exit();
    }

    /**
     * 点击inactive按钮*
     *
     * @param name - 邮件提醒按钮
     */
    public void func_click_inactiveButton(String name) {
        l.entry();
        link_emailAlertButtonByName(name, 1).click();
        l.exit();
    }

    /**
     * 获取邮件提醒状态*
     *
     * @param name - 邮件提醒名称
     * @return 0：active，1：inactive，-1：出错
     */
    public int func_get_status(String name) {
        l.entry();
        boolean isActive = link_emailAlertButtonByName(name, 0).getAttribute("class").contains("selected");
        boolean isInactive = link_emailAlertButtonByName(name, 1).getAttribute("class").contains("selected");
        if ((isActive) && (!isInactive)) {
            return 0;
        } else if ((!isActive) && (isInactive)) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 验证邮件提醒状态*
     *
     * @param name      - 邮件提醒名称
     * @param expStatus - 期望状态
     * @return
     */
    public boolean func_verify_status(String name, int expStatus) {
        l.entry();
        boolean result = true;
        l.info("Verify email alert status");
        int actStatus = func_get_status(name);
        if (expStatus == actStatus) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Exp status: {}, act status: {}", expStatus, actStatus);
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Exp status: {}, act status: {}", expStatus, actStatus);
            result &= false;
        }
        return result;
    }

    /**
     * 点击提交按钮 -- 编辑邮件提醒页*
     *
     * @return
     */
    public void func_click_submit_editEmailAlertPage() {
        l.entry();
        button_emailAlertInfo_submit().click();
        l.exit();
    }

}
