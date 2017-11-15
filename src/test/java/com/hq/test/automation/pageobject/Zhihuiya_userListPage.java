package com.hq.test.automation.pageobject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


/**
 * 收藏夹页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_userListPage extends Zhihuiya_basePage {

    public Zhihuiya_userListPage(WebDriver driver) {
        l.entry();
        d = driver;
        selfcheckSelector = By.className("user-center-menu");
        l.exit();
    }


    /********** Elements **********/

    /**
     * 链接--根据名称获取收藏夹文件夹
     *
     * @param name
     * @return
     */
    public WebElement link_listFolder_byName(String name) {
        l.entry();
        if (this.div_folderTree() == null) {
            l.error("Can not find folder tree");
            return null;
        }
        List<WebElement> links = this.div_folderTree().findElements(By.tagName("a"));
        for (WebElement link : links) {
            l.debug("Current folder text: []", link.getText());
            if (link.getText().equals(name)) {
                return link;
            }
        }
        l.warn("User list folder link is null");
        return null;
    }

    public WebElement link_listFolder_byIndex(int index) {
        l.entry();
        if (this.div_folderTree() == null) {
            l.error("Can not find folder tree");
            return null;
        }
        return this.div_folderTree().findElements(By.tagName("a")).get(index);
    }

    /**
     * 链接--根据名称获取被分享收藏夹文件夹
     *
     * @param name
     * @return
     */
    public WebElement link_sharedListFolder_byName(String name) {
        l.entry();
        if (this.div_folderTree() == null) {
            l.error("Can not find shared folder tree");
            return null;
        }
        List<WebElement> links = this.div_sharedFolderTree().findElements(By.tagName("a"));
        for (WebElement link : links) {
            l.debug("Current folder text: []", link.getText());
            if (link.getText().contains(name)) {
                return link;
            }
        }
        l.warn("Shared user list folder link is null");
        return null;
    }

    /**
     * 链接--矩阵分析
     *
     * @return
     */
    public WebElement link_matrixAnalysis() {
        l.entry();
        return d.findElement(By.id("userlist_analyze"));
    }

    /**
     * span -- 分析按钮
     *
     * @return
     */
    public WebElement span_analysis() {
        l.entry();
        l.exit();
        return d.findElement(By.id("btn-analysis"));
    }

    /**
     * 删除按钮--右键菜单
     *
     * @return
     */
    public WebElement link_rMenu_delete() {
        l.entry();
        return d.findElement(By.id("rMenu")).findElement(By.className("remove_node")).findElement(By.tagName("a"));
    }

    /**
     * 编辑按钮--右键菜单
     *
     * @return
     */
    public WebElement link_rMenu_edit() {
        l.entry();
        return d.findElement(By.id("rMenu")).findElement(By.className("edit_node")).findElement(By.tagName("a"));
    }

    /**
     * 分享按钮--右键菜单
     *
     * @return
     */
    public WebElement link_rMenu_share() {
        l.entry();
        return d.findElement(By.id("rMenu")).findElement(By.className("share_node")).findElement(By.tagName("a"));
    }

    /**
     * 创建按钮--右键菜单
     *
     * @return
     */
    public WebElement link_rMenu_create() {
        l.entry();
        return d.findElement(By.id("rMenu")).findElement(By.className("add_node")).findElement(By.tagName("a"));
    }


    /**
     * 新增文件夹按钮
     *
     * @return
     */
    public WebElement link_createNewFolder() {
        l.entry();
        return d.findElement(By.className("create-new-folder"));
    }

    /**
     * 链接--删除专利
     *
     * @return
     */
    public WebElement link_deletePatent() {
        l.entry();
        return d.findElement(By.className("toolbar")).findElement(By.className("delete-patent"));
    }

    /**
     * 链接--添加自定义字段
     *
     * @return
     */
    public WebElement link_addCustomField() {
        l.entry();
        return d.findElement(By.className("toolbar")).findElement(By.className("create-new-customfield"));
    }

    /**
     * 输入框--文件夹名称
     *
     * @return
     */
    public WebElement input_folderName() {
        l.entry();
        return d.findElement(By.className("rename"));
    }

    /**
     * 输入框--自定义字段，名称
     *
     * @return
     */
    public WebElement input_fieldName() {
        l.entry();
        return d.findElement(By.id("fieldname"));
    }

    /**
     * 单选框--自定义字段，类型
     *
     * @param index -- 0:text, 1:date, 2:drop-down menu
     * @return
     */
    public WebElement input_fieldType(int index) {
        l.entry();
        if (index == 0 || index == 1 || index == 2) {
            return d.findElements(By.name("fieldtype")).get(index);
        } else {
            l.error("Custom field type index is out of range");
            return null;
        }
    }

    /**
     * 输入框--自定义字段下拉框名称
     *
     * @return
     */
    public WebElement input_optionText() {
        l.entry();
        return d.findElement(By.id("optionsinput"));
    }

    /**
     * 自定义字段--添加自定义字段下拉框选项
     *
     * @return
     */
    public WebElement button_addOption() {
        l.entry();
        return d.findElement(By.id("addcustomfield_btn"));
    }

    /**
     * 链接--保存新增自定义字段
     *
     * @return
     */
    public WebElement link_addNewField() {
        l.entry();
        return d.findElement(By.id("add_new_field"));
    }

    /**
     * div -- 文件夹列表
     *
     * @return 不存在则返回空
     */
    public WebElement div_folderTree() {
        l.entry();
        try {
            return d.findElement(By.className("side-list")).findElement(By.className("ztree"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * div -- 分享文件夹列表
     *
     * @return 不存在则返回空
     */
    public WebElement div_sharedFolderTree() {
        l.entry();
        try {
            return d.findElement(By.className("side-list")).findElement(By.className("shared_from_list")).findElement(By.className("ztree"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 专利列表
     *
     * @return
     */
    public WebElement table_patentList_tableView() {
        l.entry();
        try {
            return d.findElement(By.id("tablelist"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 链接--用户设置
     *
     * @return
     */
    public WebElement link_userSetting() {
        l.entry();
        return d.findElement(By.cssSelector(".btn-26.btn-setup"));
    }

    /**
     * 链接--分享信息
     *
     * @return
     */
    public WebElement link_shareInfo() {
        l.entry();
        return d.findElement(By.className("share-info"));
    }

    /**
     * span--当前已选择字段数量--定义显示字段框
     *
     * @return
     */
    public WebElement span_currentFieldsNum() {
        l.entry();
        return d.findElement(By.className("count-now"));
    }

    /**
     * span--字段数量上限--定义显示字段框
     *
     * @return
     */
    public WebElement span_limitFieldsNum() {
        l.entry();
        return d.findElement(By.className("count-limit"));
    }

    /**
     * 根据rel属性，返回li--选择显示字段--隐藏字段列表
     *
     * @param rel
     * @return
     */
    public WebElement li_hiddenFields_byRel(String rel) {
        l.entry();
        WebElement returnLi = null;
        for (WebElement e : d.findElement(By.className("left-dock")).findElement(By.cssSelector(".swap-list.ui-sortable")).findElements(By.tagName("li"))) {
            if (rel.equals(e.getAttribute("rel"))) {
                returnLi = e;
                break;
            }
        }
        return returnLi;
    }

    /**
     * input -- 过滤条件输入框
     *
     * @return
     */
    public WebElement input_filter() {
        l.entry();
        return d.findElement(By.className("userlist-query-form")).findElement(By.name("q"));
    }

    /**
     * link -- 过滤条件搜索链接
     *
     * @return
     */
    public WebElement link_filter() {
        l.entry();
        return d.findElement(By.className("m-btn-group")).findElement(By.tagName("button"));
    }

    /**
     * 文本框--页码
     *
     * @return
     */
    public WebElement input_pageNum() {
        l.entry();
        return d.findElement(By.className("jump_to_page")).findElement(By.name("jumpto"));
    }

    /**
     * 按钮 -- 页面跳转
     *
     * @return
     */
    public WebElement input_pageGo() {
        l.entry();
        return d.findElement(By.id("pagego"));
    }

    /**
     * 勾选按钮---自动收录
     *
     * @return
     */
    public WebElement auto_collect() {
        l.entry();
        return d.findElement(By.className("checkbox-collect"));
    }

    /**
     * query输入框---自动收录
     *
     * @return
     */
    public WebElement query_input() {
        l.entry();
        return d.findElement(By.className("field-query")).findElement(By.tagName("textarea"));
    }

    /**
     * 预览按钮---自动收录
     *
     * @return
     */
    public WebElement preview_btn() {
        l.entry();
        return d.findElement(By.id("collect-query-wrapper")).findElement(By.tagName("a"));
    }

    /**
     * btn--i know
     *
     * @return
     */
    public WebElement btn_iknow() {
        l.entry();
        return d.findElement(By.cssSelector(".btn-26.btn-notice"));
    }

    /**
     * span--底部收录专利数目
     */
    public WebElement span_UserlistNum_bottom() {
        l.entry();
        return d.findElement(By.cssSelector(".num.total_num"));
    }

    /**
     * btn -logo
     *
     * @return
     */
    public WebElement btn_logo() {
        l.entry();
        return d.findElement(By.className("logo"));
    }

    /**
     * div—收藏夹为空
     */
    public WebElement div_ListEmpty() {
        l.entry();
        return d.findElement(By.id("main")).findElement(By.className("table-wrap")).findElement(By.className("no_result_warning"));
    }


    /********** Functions **********/

    /**
     * 验证收藏夹文件夹是否存在
     *
     * @param -- 期望名称
     * @return 是否存在
     */
    public boolean func_verify_userListFolderExists(ArrayList<String> names) {
        l.entry();
        boolean result = true;
        for (String name : names) {
            if (this.link_listFolder_byName(name) != null) {
                l.info("++++++++++++++++++++++++++++++ Pass -- User list folder [{}] exists", name);
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- User list folder [{}] does not exist", name);
                t.takeScreenshot(d);
                result &= false;
            }
        }
        return result;
    }

    /**
     * 验证分享信息
     *
     * @param exp_uid
     * @return
     */
    public boolean func_verify_shareInfo(String exp_uid) {
        l.entry();
        String act_uid = this.link_shareInfo().getText();
        if ((act_uid != null) && (act_uid.contains(exp_uid))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Share info is correct, exp: [{}], act: [{}]", exp_uid, act_uid);
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Share info is incorrect, exp: [{}], act: [{}]", exp_uid, act_uid);
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证收藏夹为空
     */
    public boolean func_verify_empty(String exp_info) {
        l.entry();
        String act_info = this.div_ListEmpty().getText();
        if (act_info.contains(exp_info)) {
            l.info("+++++++++++++++++++++++++Pass --The list is empty");
            return true;
        } else {
            l.error("++++++++++++++++++++++++Fail -- The list is not empty");
            t.takeScreenshot(d);
            return false;
        }
    }


    /**
     * 验证文件夹是否不存在
     *
     * @param names
     * @return
     */
    public boolean func_verify_userListFolderDoesNotExist(ArrayList<String> names) {
        l.entry();
        boolean result = true;
        for (String name : names) {
            if (this.link_listFolder_byName(name) == null) {
                l.info("++++++++++++++++++++++++++++++ Pass -- User list folder [{}] does not exists", name);
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- User list folder [{}] exist", name);
                t.takeScreenshot(d);
                result &= false;
            }
        }
        return result;
    }

    /**
     * 验证被分享的文件夹是否存在
     *
     * @param names
     * @return
     */
    public boolean func_verify_sharedUserListFolderExists(ArrayList<String> names) {
        l.entry();
        boolean result = true;
        for (String name : names) {
            if (this.link_sharedListFolder_byName(name) != null) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Shared user list folder [{}] exists", name);
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Shared user list folder [{}] does not exist", name);
                t.takeScreenshot(d);
                result &= false;
            }
        }
        return result;
    }

    /**
     * 验证被分享的文件夹不存在
     */
    public boolean func_verify_sharedUserListFolderDoesNotExist(ArrayList<String> names) {
        l.entry();
        boolean result = true;
        for (String name : names) {
            if (this.link_sharedListFolder_byName(name) == null) {
                l.info("++++++++++++++++++++++++ Pass --User List folder [{}] does not exist", name);
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<< Fail -- User List folder [{}] exist", name);
                t.takeScreenshot(d);
                result &= false;
            }
        }
        return result;
    }


    /**
     * 点击收藏夹文件夹
     *
     * @param name
     */
    public void func_click_userListFolder(String name) {
        l.entry();
        if (this.link_listFolder_byName(name) != null) {
            this.link_listFolder_byName(name).click();
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- User list folder [{}] does not exist", name);
            t.takeScreenshot(d);
        }
        l.exit();
    }

    public void func_click_userListFolder(int index) {
        l.entry();
        if (this.link_listFolder_byIndex(index) != null) {
            this.link_listFolder_byIndex(index).click();
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- User list folder [{}] does not exist", index);
            t.takeScreenshot(d);
        }
        l.exit();
    }

    /**
     * 点击被分享的文件夹
     *
     * @param name
     */
    public void func_click_sharedUserListFolder(String name) {
        l.entry();
        if (this.link_sharedListFolder_byName(name) != null) {
            this.link_sharedListFolder_byName(name).click();
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Shared user list folder [{}] does not exist", name);
            t.takeScreenshot(d);
        }
        l.exit();
    }

    /**
     * 点击展开按钮--收藏夹页
     *
     * @throws InterruptedException
     */
    public void func_click_expandAll() throws InterruptedException {
        l.entry();
        int i = 0;
        while (this.getElementById("expand_tree").getText().contains("+")) {
            if (i < 10) {
                l.debug("Trying to expand tree...");
                this.getElementById("expand_tree").click();
                Thread.sleep(1000);
                i++;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Fail to expand folder tree");
                break;
            }
        }
        l.exit();
    }

    /**
     * 点击导出按钮
     */
    public void func_click_export() {
        l.entry();
        this.getElementById("userlist_export").click();
        l.exit();
    }

    /**
     * 点击收起按钮--收藏夹页
     *
     * @throws InterruptedException
     */
    public void func_click_collapseAll() throws InterruptedException {
        l.entry();
        int i = 0;
        while (this.getElementById("expand_tree").getText().contains("-")) {
            if (i < 10) {
                l.debug("Trying to collapse tree...");
                this.getElementById("expand_tree").click();
                Thread.sleep(1000);
                i++;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Fail to collapse folder tree");
                break;
            }
        }
        l.exit();
    }

    /**
     * 删除文件夹
     *
     * @param name
     * @throws InterruptedException
     */
    public void func_delete_userListFolder(String name) throws InterruptedException {
        l.entry();
        if (this.link_listFolder_byName(name) != null) {
            //点右键
            act = new Actions(d);
            act.contextClick(this.link_listFolder_byName(name)).perform();
            //等待出现
            if (!this.link_rMenu_delete().isDisplayed()) {
                Thread.sleep(2000);
            }
            //点击删除链接
            this.link_rMenu_delete().click();
            d.switchTo().alert().accept();
        } else {
            l.debug("User list folder [{}] does not exist", name);
            t.takeScreenshot(d);
        }
        l.exit();
    }

    /**
     * 删除指定专利
     *
     * @param pn
     */
    public void func_delete_patent_inCurrentFolder(String pn) {
        l.entry();
        WebElement tr = this.func_get_tr_byPN(pn);
        tr.findElement(By.className("patent-checkbox")).click();
        this.link_deletePatent().click();
        d.switchTo().alert().accept();
        l.exit();
    }

    /**
     * 为指定专利添加备注
     *
     * @param pn
     * @param content
     */
    public void func_set_comments(String pn, String content) {
        l.entry();
        WebElement tr = this.func_get_tr_byPN(pn);
        //点击备注
        WebElement cell_comments = tr.findElement(By.className("col-comments"));
        cell_comments.click();
        //填写备注内容
        cell_comments.findElement(By.className("custom-field-text")).clear();
        cell_comments.findElement(By.className("custom-field-text")).sendKeys(content);
        //点击提交
        cell_comments.findElement(By.className("submit-comments-field")).click();
        l.exit();
    }

    /**
     * 根据PN获取最新备注内容
     *
     * @param pn
     * @return -- author, content
     */
    public HashMap<String, String> func_get_latestComments(String pn) {
        l.entry();
        HashMap<String, String> returnHash = new HashMap<String, String>();
        WebElement tr = this.func_get_tr_byPN(pn);
        //获取备注全文
        WebElement cell_comments = tr.findElement(By.className("col-comments"));
        //如果备注不存在，返回null
        WebElement div_comments = null;
        try {
            List<WebElement> divs_comments = cell_comments.findElements(By.tagName("div"));
            div_comments = divs_comments.get(divs_comments.size() - 1);
        } catch (Exception e) {
            l.info("Comment div is null");
            return null;
        }
        //获取备注内容
        String fullContent = div_comments.getText();
        String author = fullContent.split(":")[0].trim();
        String content = fullContent.split(":")[1].trim();
        returnHash.put("author", author);
        returnHash.put("content", content);
        return returnHash;
    }

    /**
     * 很据PN删除备注
     *
     * @param pn
     */
    public void func_delete_comments(String pn) {
        l.entry();
        WebElement tr = this.func_get_tr_byPN(pn);
        //点击备注
        WebElement cell_comments = tr.findElement(By.className("col-comments"));
        cell_comments.click();
        //填写备注内容
        cell_comments.findElement(By.className("custom-field-text")).clear();
        //点击提交
        cell_comments.findElement(By.className("submit-comments-field")).click();
        l.exit();
    }

    /**
     * @param fieldName
     * @param type      0:text, 1:date, 2:drop-down menu
     * @param options   下拉框选项列表
     */
    public void func_add_customField(String fieldName, int type, ArrayList<String> options) {
        l.entry();
        //点击添加自定义字段
        this.link_addCustomField().click();
        //填写字段属性
        this.input_fieldName().clear();
        this.input_fieldName().sendKeys(fieldName);
        this.input_fieldType(type).click();
        //如果为下拉菜单属性，则需添加选项
        if (type == 2) {
            for (String option : options) {
                this.input_optionText().clear();
                this.input_optionText().sendKeys(option);
                this.button_addOption().click();
            }
        }
        this.link_addNewField().click();
        l.exit();
    }

    /**
     * 根据字段名称删除自定义字段
     *
     * @param fieldName
     * @throws InterruptedException
     */
    public void func_delete_customField(String fieldName) throws InterruptedException {
        l.entry();
        JavascriptExecutor jse = (JavascriptExecutor) d;
        String js = String.format("$(\"th:contains('%s') .btn-open-menu\").click()", fieldName);
        jse.executeScript(js);
        //点击删除
        Thread.sleep(2000);
        js = String.format("$(\"th:contains('%s') .delete-custom-field\").click()", fieldName);
        jse.executeScript(js);
        //点击Alert
        try {
            d.switchTo().alert().accept();
            d.switchTo().alert().accept();
        } catch (Exception e) {
            //do nothing
        }
        l.exit();
    }

    /**
     * 为指定PN设置自定义字段--文本类型
     *
     * @param pn
     * @param content
     */
    public void func_set_customField_text(String pn, String content, int fieldIndex) {
        l.entry();
        WebElement tr = this.func_get_tr_byPN(pn);
        //点击备注
        WebElement cell_customField = tr.findElements(By.className("col-customize")).get(fieldIndex);
        cell_customField.click();
        //填写备注内容
        cell_customField.findElement(By.className("custom-field-text")).clear();
        cell_customField.findElement(By.className("custom-field-text")).sendKeys(content);
        //点击提交
        cell_customField.findElement(By.className("submit-defined-field")).click();
        l.exit();
    }

    /**
     * 为指定PN设置自定义字段--日期类型
     *
     * @param pn
     * @param content
     */
    public void func_set_customField_date(String pn, String content, int fieldIndex) {
        l.entry();
        WebElement tr = this.func_get_tr_byPN(pn);
        //点击备注
        WebElement cell_customField = tr.findElements(By.className("col-customize")).get(fieldIndex);
        cell_customField.click();
        //填写备注内容
        cell_customField.findElement(By.className("custom-field-date")).clear();
        cell_customField.findElement(By.className("custom-field-date")).sendKeys(content);
        //点击提交
        cell_customField.findElement(By.className("submit-defined-field")).click();
        l.exit();
    }

    /**
     * 为指定PN设置自定义字段--下拉菜单类型
     *
     * @param pn
     * @param
     */
    public void func_set_customField_select(String pn, String context, int fieldIndex) {
        l.entry();
        WebElement tr = this.func_get_tr_byPN(pn);
        //点击备注
        WebElement cell_customField = tr.findElements(By.className("col-customize")).get(fieldIndex);
        cell_customField.click();
        //填写备注内容
        Select s = new Select(cell_customField.findElement(By.className("custom-field-select")));
        //如果入参为空，则只清空；不为空，则选择
        s.deselectAll();
        if (context != null) {
            s.selectByVisibleText(context);
        }
        //点击提交
        cell_customField.findElement(By.className("submit-defined-field")).click();
        l.exit();
    }

    /**
     * 根据PN获取自定义字段值--文本类型
     *
     * @param pn
     * @param fieldIndex
     * @return
     */
    public String func_get_customField_text(String pn, int fieldIndex) {
        l.entry();
        WebElement tr = this.func_get_tr_byPN(pn);
        //获取备注全文
        WebElement cell_customField = tr.findElements(By.className("col-customize")).get(fieldIndex);
        return cell_customField.findElement(By.className("custom_field")).getText();
    }

    /**
     * @param name
     * @param usersToShare
     * @param type         -- 0: 1-to-1; 1: group
     */
    public void func_share_userListFolder(String name, ArrayList<String> usersToShare, int type) {
        l.entry();
        if (this.link_listFolder_byName(name) != null) {
            //点右键
            act = new Actions(d);
            act.contextClick(this.link_listFolder_byName(name)).perform();
            //点击删除链接
            this.link_rMenu_share().click();
            //分享操作
            //拼接用户字符串
            String inputUserStr = "";
            for (String user : usersToShare) {
                inputUserStr += String.format("%s,", user);
            }
            //去掉末尾字符串
            inputUserStr = inputUserStr.substring(0, inputUserStr.length() - 1);
            //输入被分享用户
            this.getElementById("emailaddress").clear();
            this.getElementById("emailaddress").sendKeys(inputUserStr);
            switch (type) {
                //选择分享类型
                case 0:
                    this.getElementById("selecttype").click();
                    break;
                case 1:
                    this.getElementById("query_num").click();
                    break;
            }
            //提交
            this.getElementById("sendshare").click();
        } else {
            l.debug("User list folder [{}] does not exist", name);
            t.takeScreenshot(d);
        }
        l.exit();
    }

    /**
     * 分享文件夹（1-1 share）
     */
    public void funcShareList_1To1Share(String folderName, String email) throws InterruptedException, AWTException {
        l.entry();
        if (this.link_listFolder_byName(folderName) != null) {
            act = new Actions(d);
            act.contextClick(this.link_listFolder_byName(folderName)).perform();
            this.link_rMenu_share().click();
            this.getElementById("emailaddress").clear();
            this.getElementById("emailaddress").sendKeys(email);
            this.getElementById("selecttype").click();
            Thread.sleep(2000);
            this.getElementById("sendshare").click();
        } else {
            l.debug("User list folder [{}] does not exist", folderName);
            t.takeScreenshot(d);
        }
    }

    /**
     * 分享文件夹（group share）
     */
    public void funcShareList_GroupShare(String folderName, String email) throws InterruptedException, AWTException {
        l.entry();
        if (this.link_listFolder_byName(folderName) != null) {
            act = new Actions(d);
            act.contextClick(this.link_listFolder_byName(folderName)).perform();
            this.link_rMenu_share().click();
            this.getElementById("emailaddress").clear();
            this.getElementById("emailaddress").sendKeys(email);
            this.getElementById("query_num").click();
            Thread.sleep(2000);
            this.getElementById("sendshare").click();
        } else {
            l.debug("User list folder [{}] does not exist", folderName);
            t.takeScreenshot(d);
        }
    }


    /**
     * 编辑文件夹
     *
     * @param name
     * @param newName
     */
    public void func_edit_userListFolder(String name, String newName) {
        l.entry();
        if (this.link_listFolder_byName(name) != null) {
            //点右键
            act = new Actions(d);
            act.contextClick(this.link_listFolder_byName(name)).perform();
            //点击编辑链接
            this.link_rMenu_edit().click();
            //输入新名称
            this.getElementById("name").clear();
            this.getElementById("name").sendKeys(newName);
            //保存
            this.getElementById("name").submit();
        } else {
            l.debug("User list folder [{}] does not exist", name);
            t.takeScreenshot(d);
        }
        l.exit();
    }

    /**
     * 点击矩阵分析链接
     */
    public void func_click_matrixAnalysis() {
        l.entry();
        Actions act = new Actions(d);
        act.moveToElement(this.span_analysis()).perform();
        this.span_analysis().findElements(By.tagName("a")).get(1).click();
        l.exit();
    }

    /**
     * 点击landscape
     */
    public void func_click_landscape() {
        l.entry();
        Actions act = new Actions(d);
        act.moveToElement(this.span_analysis()).perform();
        this.span_analysis().findElements(By.tagName("a")).get(0).click();
        l.exit();
    }

    /**
     * 点击 quick chart
     */
    public void func_click_quickChart() {
        l.entry();
        Actions act = new Actions(d);
        act.moveToElement(this.span_analysis()).perform();
        this.span_analysis().findElements(By.tagName("a")).get(2).click();
        l.exit();
    }

    /**
     * 验证专利是否存在于列表中
     *
     * @param pn
     * @return
     */
    public boolean func_verify_patentExistInUserList(String pn) {
        l.entry();
        boolean result = false;
        boolean matchFlag = false;
        l.info("Expected patent name is [{}]", pn);
        WebElement table = this.table_patentList_tableView();
        if (table == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent list table does not exist");
            t.takeScreenshot(d);
            return false;
        }
        //获取表格数据
        List<HashMap<String, String>> tableData = this.getTableData(table);
        //验证邮件提醒是否存在
        for (HashMap<String, String> row : tableData) {
            String actName = row.get("Publication Number");
            if (actName == null) {
                actName = row.get("公开(公告)号");
            }
            l.debug("Current actual pn is [{}]", actName);
            if ((actName != null) && (actName.equals(pn))) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent is found");
                result = true;
                matchFlag = true;
                break;
            }
        }
        if (!matchFlag) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent is not found");
            t.takeScreenshot(d);
            result = false;
        }
        return result;
    }

    /**
     * 验证专利是否不存在
     *
     * @param pn
     * @return
     */
    public boolean func_verify_patentDoesNotExistInUserList(String pn) {
        l.entry();
        boolean result = false;
        boolean matchFlag = false;
        l.info("Expected patent name is [{}]", pn);
        WebElement table = this.table_patentList_tableView();
        if (table == null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Patent list table does not exist");
            return true;
        }
        //获取表格数据
        List<HashMap<String, String>> tableData = this.getTableData(table);
        //验证邮件提醒是否存在
        for (HashMap<String, String> row : tableData) {
            String actName = row.get("Publication Number");
            l.debug("Current actual pn is [{}]", actName);
            if ((actName != null) && (actName.equals(pn))) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent is found");
                result = false;
                matchFlag = true;
                t.takeScreenshot(d);
                break;
            }
        }
        if (!matchFlag) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Patent is not found");
            result = true;
        }
        return result;
    }

    /**
     * 返回用户自建的收藏夹文件夹数量
     *
     * @return
     */
    public int func_get_folderNum() {
        l.entry();
        List<WebElement> links = this.div_folderTree().findElements(By.tagName("a"));
        int i = 0;
        for (WebElement link : links) {
            if (link.isDisplayed()) {
                i++;
            }
        }
        return i;
    }

    /**
     * 返回任意PN
     *
     * @return
     */
    public String func_get_anyPN() {
        l.entry();
        WebElement table = this.table_patentList_tableView();
        if (table == null) {
            l.error("Patent list table does not exist");
            return null;
        }
        return table.findElement(By.tagName("tbody")).findElement(By.className("col-pn")).findElement(By.tagName("a")).getText();
    }


    /**
     * 获取专利表格数据
     *
     * @return
     */
    public List<HashMap<String, String>> func_getTableData_patentList() {
        l.entry();
        WebElement table = this.table_patentList_tableView();
        //返回数据
        return this.getTableData(table);
    }

    /**
     * 根据PN返回其所在TR
     *
     * @param pn
     * @return
     */
    public WebElement func_get_tr_byPN(String pn) {
        l.entry();
        WebElement returnTr = null;
        WebElement table = this.table_patentList_tableView();
        if (table == null) {
            l.error("Patent list table does not exist");
            return null;
        }
        l.info("Expected PN: [{}]", pn);
        for (WebElement tr : table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"))) {
            String act_pn = tr.findElement(By.className("col-pn")).getText();
            l.debug("Current PN: [{}]", act_pn);
            if (pn.equals(act_pn)) {
                //找到专利
                l.info("Patent found");
                returnTr = tr;
                break;
            }
        }
        return returnTr;
    }

    /**
     * 返回分享的收藏夹文件夹数量
     *
     * @return
     */
    public int func_get_sharedFolderNum() {
        l.entry();
        if (this.div_sharedFolderTree() == null) {
            l.info("Shared folder list does not exist");
            return 0;
        }
        List<WebElement> links = this.div_sharedFolderTree().findElements(By.tagName("a"));
        int i = 0;
        for (WebElement link : links) {
            if (link.isDisplayed()) {
                i++;
            }
        }
        return i;
    }

    /**
     * 创建文件夹
     *
     * @return
     * @throws InterruptedException
     * @throws AWTException
     */
    public String func_create_folder() throws InterruptedException, AWTException {
        l.entry();
        //点击新增文件夹按钮
        this.link_createNewFolder().click();
        //生成文件夹名称
        String folderName = String.valueOf(System.currentTimeMillis());
        //输入文件夹名称
        this.input_folderName().clear();
        this.input_folderName().sendKeys(folderName);
        //点击回车
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        return folderName;
    }

    /**
     * 创建文件夹_使用默认名称
     *
     * @throws InterruptedException
     * @throws AWTException
     */
    public void func_create_folder_default() throws InterruptedException, AWTException {
        l.entry();
        //点击新增文件夹按钮
        this.link_createNewFolder().click();
        //点击回车
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        l.exit();
    }

    /**
     * 创建子文件夹——使用默认名称
     *
     * @throws InterruptedException
     * @throws AWTException
     */
    public void funcCreateChildFolderDefault(String parentfolder) throws InterruptedException, AWTException {
        l.entry();
        //右击文件夹
        if (this.link_listFolder_byName(parentfolder) != null) {
            act = new Actions(d);
            act.contextClick(this.link_listFolder_byName(parentfolder)).perform();
            //点击新增按钮
            this.link_rMenu_create().click();
            //点击回车
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            l.exit();
        }
    }


    /**
     * 创建文件夹_使用输入名称
     *
     * @throws InterruptedException
     * @throws AWTException
     */
    public void func_create_folder_input(String foldername) throws InterruptedException, AWTException {
        l.entry();
        //点击新增文件夹按钮
        this.link_createNewFolder().click();
        //通过参数来传递列表名
        this.input_folderName().clear();
        this.input_folderName().sendKeys(foldername);
        //点击回车
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        l.exit();
    }

    /**
     * 创建子文件夹——使用输入名称
     *
     * @throws InterruptedException
     * @throws AWTException
     */
    public void funcCreateChildFolderInput(String parentfolder, String childfolder) throws InterruptedException, AWTException {
        l.entry();
        //右击文件夹
        if (this.link_listFolder_byName(parentfolder) != null) {
            act = new Actions(d);
            act.contextClick(this.link_listFolder_byName(parentfolder)).perform();
            //点击新增按钮
            this.link_rMenu_create().click();
            this.input_folderName().clear();
            this.input_folderName().sendKeys(childfolder);
            //点击回车
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            l.exit();
        }
    }


    /**
     * 编辑文件夹名称
     */
    public void func_edit_foldername(String name, String newname) {
        l.entry();
        if (this.link_listFolder_byName(name) != null) {
            act = new Actions(d);
            act.contextClick(this.link_listFolder_byName(name)).perform();
            //点击编辑链接
            this.link_rMenu_edit().click();
            //输入新名称
            this.getElementById("name").clear();
            this.getElementById("name").sendKeys(newname);
            this.getElementById("name").submit();
        } else {
            l.debug("User list folder [{}] does not exist", name);
            t.takeScreenshot(d);
        }
        l.exit();
    }

    /**
     * 编辑子文件夹名称
     */
    public void func_edit_Childfoldername(String name, String newname) {
        l.entry();
        if (this.link_listFolder_byName(name) != null) {
            act = new Actions(d);
            act.contextClick(this.link_listFolder_byName(name)).perform();
            //点击编辑链接
            this.link_rMenu_edit().click();
            //输入新名称
            this.getElementById("name").clear();
            this.getElementById("name").sendKeys(newname);
            this.getElementById("name").submit();
        } else {
            l.debug("User list folder [{}] does not exist", name);
            t.takeScreenshot(d);
        }
        l.exit();
    }

    /**
     * 将文件夹加入另一个文件夹，作为子文件夹
     *
     * @param sourceFolderName
     * @param targetFolderName
     */
    public void func_create_subFolder(String sourceFolderName, String targetFolderName) {
        l.entry();
        act = new Actions(d);
        act.dragAndDrop(this.link_listFolder_byName(sourceFolderName), this.link_listFolder_byName(targetFolderName)).perform();
        l.exit();
    }

    /**
     * 拖拽专利
     *
     * @param pn               -- 专利
     * @param targetFolderName -- 目标文件夹
     */
    public void func_drag_patentToFolder(String pn, String targetFolderName) {
        l.entry();
        WebElement tr = this.func_get_tr_byPN(pn);
        act = new Actions(d);
        act.dragAndDrop(tr.findElement(By.className("btn-drag")), this.link_listFolder_byName(targetFolderName)).perform();

    }

    /**
     * 复制或移动专利
     *
     * @param index--0：复制；1：移动
     * @param pn--需要移动的专利号
     * @param targetFolderName--目标文件夹
     */
    public void func_copyOrMove_patent(int index, String pn, String targetFolderName) {
        l.entry();
        d.findElement(By.id("copy-move-action")).findElements(By.tagName("a")).get(index).click();
        this.func_drag_patentToFolder(pn, targetFolderName);
        l.exit();
    }

    /**
     * 复制被分享的专利到指定文件夹
     *
     * @param pn
     * @param targetFolderName
     */
    public void func_copy_shredPatent(String pn, String targetFolderName) {
        l.entry();
        this.func_drag_patentToFolder(pn, targetFolderName);
        l.exit();
    }

    /**
     * 根据列名，点击列中的第一个可用链接，并返回链接文字
     *
     * @param col
     * @return 链接文字
     */
    public String func_click_availableLink_patentList(String col) {
        l.entry();
        WebElement returnLink = null;
        //1.根据入参，确定td的class
        String tdClassName = null;
        if (col.toLowerCase().equals("pn")) {
            tdClassName = "col-pn";
        } else if (col.toLowerCase().equals("apn")) {
            tdClassName = "APNO";
        } else if (col.toLowerCase().equals("an")) {
            tdClassName = "AN";
        } else if (col.toLowerCase().equals("an_add")) {
            tdClassName = "AN_ADDRESS";
        } else if (col.toLowerCase().equals("in")) {
            tdClassName = "IN";
        } else if (col.toLowerCase().equals("at")) {
            tdClassName = "AT";
        } else if (col.toLowerCase().equals("atc")) {
            tdClassName = "ATC";
        } else if (col.toLowerCase().equals("ipc")) {
            tdClassName = "ICL";
        } else if (col.toLowerCase().equals("loc")) {
            tdClassName = "LOC";
        } else if (col.toLowerCase().equals("upc")) {
            tdClassName = "CCL";
        } else if (col.toLowerCase().equals("pbd")) {
            tdClassName = "PBDT";
        } else if (col.toLowerCase().equals("apd")) {
            tdClassName = "APD";
        }
        l.info("Column class: [{}]", tdClassName);
        //2.获取表格
        WebElement table = this.table_patentList_tableView();
        if (table == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent table is not found");
            t.takeScreenshot(d);
            return null;
        }
        //3.遍历行，如果行中指定列有链接，则点击链接，并返回链接文字
        boolean matchFlag = false;
        for (WebElement row : table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"))) {
            List<WebElement> links = row.findElement(By.className(tdClassName)).findElements(By.tagName("a"));
            if (links.size() != 0) {
                returnLink = links.get(0);
                l.info("++++++++++++++++++++++++++++++ Pass -- Link is found");
                matchFlag = true;
                break;
            }
        }
        if (!matchFlag) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Link is not found");
            t.takeScreenshot(d);
        }
        //返回链接文字
        String linkText = null;
        if (returnLink != null) {
            linkText = returnLink.getText();
            l.info("Link url: {}", returnLink.getAttribute("href"));
            returnLink.click();
        } else {
            linkText = null;
        }
        return linkText;
    }

    /**
     * 配置显示字段
     *
     * @param fields_str -- 字符串，以逗号分隔
     * @return
     * @throws Exception
     */
    public boolean func_config_displayedFields(String fields_str) throws Exception {
        l.entry();
        if (fields_str == null) {
            l.error("The string parm is null");
            return false;
        }
        ArrayList<String> expFields = new ArrayList<String>();
        String[] fields_strs = fields_str.split(",");
        if (fields_strs.length == 0) {
            l.error("The format string parm is incorrect");
            return false;
        }
        for (String field_str : fields_strs) {
            expFields.add(field_str.trim());
        }
        boolean result = true;
        //点击用户设置
        this.link_userSetting().click();
        //先将右侧字段清空
        for (WebElement li : d.findElement(By.className("right-dock")).findElement(By.cssSelector(".swap-list.ui-sortable")).findElements(By.cssSelector(".right.ui-state-default"))) {
            act = new Actions(d);
            act.dragAndDrop(li, d.findElement(By.className("left-dock")).findElement(By.cssSelector(".swap-list.ui-sortable"))).perform();
        }
        //遍历期望字段集合，将没有选的字段，拖进右侧框内
        for (String field : expFields) {
            l.info("Checking field: [{}]", field);
            //检查数量是否达到上限
            if (Integer.parseInt(this.span_currentFieldsNum().getText()) >= Integer.parseInt(this.span_limitFieldsNum().getText())) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Can not drag any more field");
                result &= false;
                break;
            }
            //数量没有达到上限，才会拖拽
            boolean matchFlag = false;
            //查找匹配字段
            for (WebElement li : d.findElement(By.className("right-dock")).findElement(By.cssSelector(".swap-list.ui-sortable")).findElements(By.tagName("li"))) {
                if (field.equals(li.getAttribute("rel"))) {
                    matchFlag = true;
                    l.info("Field is found");
                    break;
                }
            }
            //如果没有找到，则拖进去
            if (!matchFlag) {
                l.info("Field not found, so drag it into shown list");
                //拖拽失败，则重试
                int i = 0;
                while (this.li_hiddenFields_byRel(field) != null) {
                    //尝试次数小于3，则尝试，否则报错
                    if (i < 3) {
                        l.info("Trying to drag field...");
                        WebElement leftLi = this.li_hiddenFields_byRel(field);
                        act = new Actions(d);
                        act.dragAndDrop(leftLi, d.findElement(By.className("right-dock")).findElement(By.cssSelector(".swap-list.ui-sortable"))).perform();
                        i++;
                    } else {
                        l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Drag failed");
                        result &= false;
                        break;
                    }
                }
                l.info("++++++++++++++++++++++++++++++ Pass -- Drag successfully");
            }
        }
        //点击提交
        d.findElement(By.id("settingform")).findElement(By.cssSelector(".btn-26.submit-form.primary")).click();
        Thread.sleep(2000); //等待页面刷新
        if (this.doesExist(By.id("settingform")) == false) {
            if (result) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Displayed fields is saved successfully");
                return true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Not all the fields are configured successfully");
                t.takeScreenshot(d);
                return false;
            }
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- User setting dialog is still displayed");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 获得PN
     *
     * @param index
     * @return
     */
    public String func_get_patentNumber_ByIndex(int index) {
        l.entry();
        String pn = null;
        WebElement link_pn;
        List<WebElement> trs = this.table_patentList_tableView().findElements(By.tagName("tr"));
        if (trs.size() > 0) {
            link_pn = trs.get(index).findElement(By.className("PN"));
            pn = link_pn.getText();
            l.info("PN: {}", pn);
        } else {
            l.warn("can not find PN link");
        }
        return pn;
    }

    /**
     * 根据索引勾选专利
     *
     * @param index
     */
    public void func_click_patentCheckbox_byIndex(int index) {
        l.entry();
        WebElement checkbox = this.table_patentList_tableView().findElements(By.tagName("tr")).get(index).findElement(By.className("patent-checkbox"));
        //如果未被选中，则选择
        if (!this.table_patentList_tableView().findElements(By.tagName("tr")).get(index).getAttribute("class").contains("selected")) {
            checkbox.click();
        }
        l.exit();
    }

    /**
     * 根据索引获得PN号
     *
     * @param index
     * @return
     */
    public String func_GetPatentPN_byIndex(int index) {
        l.entry();
        WebElement checkbox = this.table_patentList_tableView().findElements(By.tagName("tr")).get(index).findElement(By.className("patent-checkbox"));
        WebElement pn = this.table_patentList_tableView().findElements(By.tagName("tr")).get(index).findElement(By.className("view"));
        //如果未被选中，则选择
        if (!this.table_patentList_tableView().findElements(By.tagName("tr")).get(index).getAttribute("class").contains("selected")) {
            checkbox.click();
        }
        l.exit();
        return pn.getText();
    }

    /**
     * 验证添加的专利是否正确
     */
    public boolean func_verify_PatentsRight(String act, String exp) {
        l.entry();
        if (!exp.equals(act)) {
            l.info("===================Add patents fail=======");
            return false;
        } else {
            l.info("==========Add patents success============");
            return true;
        }
    }

    /**
     * 过滤
     *
     * @param keyword -- 过滤关键字
     */
    public void func_filter(String keyword) {
        l.entry();
        this.input_filter().clear();
        this.input_filter().sendKeys(keyword);
        this.input_filter().submit();
//		this.link_filter().click();
        l.exit();
    }

    /**
     * 点击页码
     *
     * @param index
     */
    public void func_goToPage(int index) {
        l.entry();
        this.input_pageNum().clear();
        this.input_pageNum().sendKeys(String.valueOf(index));
        this.input_pageGo().click();
        l.exit();
    }

    /**
     * 自动收录
     *
     * @param name
     * @throws InterruptedException
     * @throws AWTException
     */
    public void func_auto_collect(String name, String query, int type) throws InterruptedException, AWTException {
        l.entry();
        if (this.link_listFolder_byName(name) != null) {
            //右击收藏夹
            act = new Actions(d);
            act.contextClick(this.link_listFolder_byName(name)).perform();
            //点击编辑链接
            this.link_rMenu_edit().click();
            this.auto_collect().click();
            this.query_input().clear();
            this.query_input().sendKeys(query);
            this.preview_btn().click();
            switch (type) {
                //选择收录范围
                case 0:
                    Select s0 = new Select(d.findElement(By.name("collectRange")));
                    s0.selectByValue("all");
                    break;
                case 1:
                    Select s1 = new Select(d.findElement(By.name("collectRange")));
                    s1.selectByValue("dateAfter");
                    break;
            }
            this.query_input().submit();
            Thread.sleep(2000);
            this.btn_iknow().click();
        }
    }

    /**
     * 获取收藏夹专利的数目
     */
    public int func_get_NumPatent() throws InterruptedException, AWTException {
        l.entry();
        String str = this.span_UserlistNum_bottom().getText();
        String str1 = str.replace(",", "");
        int act_Num = Integer.parseInt(str1);
        return act_Num;
    }

    /**
     * 验证自动收录的专利数是否正确
     */
    public boolean func_VerifyNumRight(int exp, int act) {
        l.entry();
        if (exp != act) {
            l.info("===================Add patents fail=======");
            return false;
        } else {
            l.info("==========Add patents success============");
            return true;
        }
    }

    /**
     * 返回当前收藏夹的所有PN*
     *
     * @return
     */
    public ArrayList<String> func_get_allPN() {
        l.entry();
        ArrayList<String> pns = new ArrayList<>();
        for (WebElement pnLink : table_patentList_tableView().findElements(By.className("PN"))) {
            pns.add(pnLink.getText());
        }
        return pns;
    }

    public void func_waitFor_patentList(int timeout) throws InterruptedException {
        int i = 0;
        while ((doesExist(By.className("inner"))) && (i < timeout)) {
            l.info("Waiting for patent list...");
            Thread.sleep(1000);
            i++;
        }
    }

    /**
     *验证手动收藏的专利
     */
    public boolean compareList(ArrayList<String> listA, ArrayList<String> listB) {
        boolean result = true;
        for (String expA : listA) {
            l.info("Exp value: {}", expA);
            boolean matchFlag = false;
            for (String actB : listB) {
                l.info("Act value: {}", actB);
                if (expA.equals(actB)) {
                    l.info("Correct");
                    matchFlag = true;
                    break;
                }
            }
            if (!matchFlag) {
                result &= false;
                l.error("Incorrect");
            }
        }
        return result;
    }




}
