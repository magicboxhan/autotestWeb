package com.hq.test.automation.pageobject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * 搜索页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_searchPage extends Zhihuiya_basePage {

    public Zhihuiya_searchPage(WebDriver driver) {
        l.entry();
        d = driver;
//		selfcheckSelector = By.cssSelector(".user-name.tools");
        selfcheckSelector = By.className("btn-search");
        l.exit();
    }

    /********** Elements **********/

    /**
     * 链接 -- 查询
     *
     * @return
     */
    public WebElement link_search_fieldSearch() {
        l.entry();
        return d.findElement(By.cssSelector(".primary.btn-search"));
    }

    /**
     * 链接--展开条件--命令搜索
     *
     * @return
     */
    public WebElement link_add_condition() {
        l.entry();
        return d.findElement(By.id("buildDeleteIMG"));
    }

    /**
     * 链接--收起条件--命令搜索
     *
     * @return
     */
    public WebElement link_delete_condition() {
        l.entry();
        return d.findElement(By.id("commandline_page")).findElement(By.className("deletefields_searchCommon"));
    }

    /**
     * 链接--导出
     *
     * @return
     */
    public WebElement link_export() {
        l.entry();
        return d.findElement(By.className("button-export"));
    }

    /**
     * 下拉框--搜素连接条件--命令搜索
     *
     * @return
     */
    public Select select_condition_commandSearch() {
        l.entry();
        return new Select(d.findElement(By.id("searchcommon")).findElement(By.className("collection")));
    }

    /**
     * 下拉框--搜素字段--命令搜索
     *
     * @return
     */
    public Select select_field_commandSearch() {
        l.entry();
        return new Select(d.findElement(By.id("hidden")));
    }

    /**
     * 下拉框--搜索关键字--命令搜索
     *
     * @return
     */
    public WebElement input_keyword_commandSearch() {
        l.entry();
        return d.findElement(By.id("searchcommon")).findElement(By.className("inputtext"));
    }

    /**
     * 链接--添加搜索
     *
     * @return
     */
    public WebElement link_addQuery_commandSearch() {
        l.entry();
        return d.findElement(By.id("searchCommonConfirm"));
    }

    /**
     * 链接--搜索
     *
     * @return
     */
    public WebElement link_search_commandSearch() {
        l.entry();
        return d.findElement(By.id("commandline_page")).findElement(By.className("btn-search"));
    }

    /**
     * div -- 当前查询
     *
     * @return
     */
    public WebElement div_query() {
        l.entry();
        return d.findElement(By.className("query-monitor"));
    }

    /**
     * textArea--当前查询--命令搜索
     *
     * @return
     */
    public WebElement textArea_query() {
        l.entry();
        return d.findElement(By.id("code"));
    }

    /**
     * table -- 字段搜索
     *
     * @return
     */
    public WebElement table_fields() {
        l.entry();
        return d.findElement(By.cssSelector(".fields-table.rule-in"));
    }

    /**
     * 根据索引返回高级搜索链接
     *
     * @param index
     * @return
     */
    public WebElement link_advancedSearchTab_byIndex(int index) {
        l.entry();
        return d.findElement(By.className("advance-search-tab")).findElements(By.tagName("a")).get(index);
    }

    /**
     * div--IPC搜索
     *
     * @return 如果元素不存在，返回空
     */
    public WebElement div_ipcResultGroup() {
        l.entry();
        try {
            return d.findElement(By.className("ipc-result")).findElement(By.className("group"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过索引获取左侧链接
     *
     * @param index
     * @return
     */
    public WebElement link_leftSide_byIndex(int index) {
        l.entry();
        return d.findElement(By.className("side")).findElement(By.className("pick")).findElements(By.tagName("a")).get(index);
    }

    /**
     * 单选框--通过CN查询
     *
     * @return
     */
    public WebElement radio_ipcSearchMode_byCN() {
        l.entry();
        return d.findElement(By.id("mode1"));
    }

    /**
     * 单选框--通过关键字查询
     *
     * @return
     */
    public WebElement radio_ipcSearchMode_byKeyword() {
        l.entry();
        return d.findElement(By.id("mode2"));
    }

    /**
     * 文本框--批量搜索关键字
     *
     * @return
     */
    public WebElement input_bulkSearchKeyword() {
        l.entry();
        return d.findElement(By.className("module-batch-form")).findElement(By.tagName("textarea"));
    }

    /**
     * 文本框--IPC搜索框
     *
     * @return
     */
    public WebElement input_ipcKeyword() {
        l.entry();
        return d.findElement(By.id("ipctext"));
    }

    /**
     * 链接--允许替换--批量搜索
     *
     * @return
     */
    public WebElement input_allowReplacement() {
        l.entry();
        return d.findElement(By.id("module-rp"));
    }

    /**
     * 按钮--批量搜索--添加
     *
     * @return
     */
    public WebElement link_bulkSearch_add() {
        l.entry();
        return d.findElement(By.className("module-button-wrapper")).findElement(By.className("button-add"));
    }

    /**
     * 按钮--批量搜索-搜索
     *
     * @return
     */
    public WebElement link_bulkSearch_search() {
        l.entry();
        return d.findElement(By.id("batch-view-form")).findElement(By.className("button-view"));
    }

    /**
     * 按钮--IPC搜索
     *
     * @return
     */
    public WebElement link_ipcSearch() {
        l.entry();
        return d.findElement(By.className("ipc-search-box")).findElement(By.className("ipc-search"));
    }

    /**
     * 搜索结果--批量搜索
     *
     * @return
     */
    public WebElement textArea_bulkSearchResult() {
        l.entry();
        return d.findElement(By.id("batch-log-wrapper")).findElement(By.tagName("textarea"));
    }

    /**
     * span--等待批量搜索结果
     *
     * @return
     */
    public WebElement span_bulkSearchLoading() {
        l.entry();
        return d.findElement(By.className("module-button-wrapper")).findElement(By.className("loading"));
    }

    /**
     * strong--批量搜索结果数量
     *
     * @return
     */
    public WebElement strong_bulkSearchResultNum() {
        l.entry();
        return d.findElement(By.id("batch-count-wrapper")).findElement(By.tagName("strong"));
    }

    /**
     * 按钮--搜索后（addtolist button）
     *
     * @return
     */
    public WebElement addTolist_BulkSearchResult() {
        l.entry();
        return d.findElement(By.id("batch-log-box")).findElement(By.id("addtolist"));
    }

    /**
     * 表格--排除搜索字段
     *
     * @return
     */
    public WebElement table_exclusionFields() {
        l.entry();
        return d.findElement(By.className("rule-out"));
    }

    /**
     * 下拉框--排除条件字段下拉选项--通过索引
     *
     * @param index
     * @return
     */
    public Select select_exclusionFields_byIndex(int index) {
        l.entry();
        List<WebElement> selects = this.table_exclusionFields().findElements(By.className("ConditionSelect"));
        if (selects.size() == 0) {
            l.error("Select of exclusion fields does not exist");
            t.takeScreenshot(d);
            return null;
        }
        return new Select(selects.get(index));
    }

    /**
     * 输入框--排除条件关键字输入框--通过索引
     *
     * @param index
     * @return
     */
    public WebElement input_setExclusionFields_byIndex(int index) {
        l.entry();
        List<WebElement> inputs = this.table_exclusionFields().findElements(By.className("is-input"));
        if (inputs.size() == 0) {
            l.error("Exclusion fields does not exist");
            t.takeScreenshot(d);
            return null;
        }
        return inputs.get(index);
    }

    /**
     * 链接--排除条件删除链接--通过索引
     *
     * @param index
     * @return
     */
    public WebElement link_deleteExclusionFields_byIndex(int index) {
        l.entry();
        List<WebElement> links = this.table_exclusionFields().findElements(By.className("del"));
        if (links.size() == 0) {
            l.error("Delete link of exclusion fields does not exist");
            t.takeScreenshot(d);
            return null;
        }
        return links.get(index);
    }

    /**
     * 链接--添加排除条件
     *
     * @return
     */
    public WebElement link_addExclusionFields() {
        l.entry();
        return d.findElement(By.className("btn-add-rule-out"));
    }

    /**
     * 链接--展开排除条件
     *
     * @return
     */
    public WebElement link_addExclusionCriteria() {
        l.entry();
        return d.findElement(By.className("btn-enable-rule-out"));
    }

    /**
     * 按钮--清空搜索条件
     *
     * @return
     */
    public WebElement btn_clear() {
        l.entry();
        return d.findElement(By.className("btn-clear"));
    }

    /**
     * 上面的总的输入框
     */
    public WebElement input_total() {
        l.entry();
        return d.findElement(By.id("q"));
    }

    /**
     * 上面的搜索按钮
     */
    public WebElement btn_search() {
        l.entry();
        return d.findElement(By.className("btn-search"));
    }

    /**
     * 按钮--新增文件夹--添加收藏按钮
     *
     * @return
     */
    public WebElement btnCreateNewFolder_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist-div")).findElement(By.className("create-new-folder"));
    }

    /**
     * input--新文件夹名称--添加搜藏对话框
     *
     * @return
     */
    public WebElement input_folderName_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist-div")).findElement(By.className("rename"));
    }

    /**
     * 按钮--新增文件夹--取消按钮
     *
     * @return
     */
    public WebElement btnCancel_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist-div")).findElement(By.id("cancel"));
    }

    /**
     * div-添加搜索框-文件夹列表
     *
     * @return 不存在返回为空
     */
    public WebElement div_folder() {
        l.entry();
        try {
            return d.findElement(By.id("addtolist-div")).findElement(By.className("ztree"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 按钮--新增文件夹--加号按钮
     *
     * @return
     */
    public WebElement btn_add_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist-div")).findElement(By.className("add"));
    }

    /**
     * AddToList窗口中的rename按钮(BS)
     */
    public WebElement btn_rename_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist-div")).findElement(By.className("edit"));
    }


    /********** Functions **********/

    /**
     * 字段搜索
     *
     * @param databases_id    -- 勾选数据库，逗号分隔
     * @param fields_name     -- 搜索用条件，逗号分隔
     * @param keywords_string -- 关键字，逗号分隔，与搜索条件一一对应
     * @param parmPairs       -- 排除条件键值对
     * @return 当前query
     */
    public String func_fieldSearch(String databases_id, String fields_name, String keywords_string, HashMap<String, String> parmPairs) {
        l.entry();
        //清除数据库勾选
        WebElement checkbox_AllDB = this.getElementById("ALL_DB");
        //有时候checked属性会取不到，当做没有勾选
        if (checkbox_AllDB.getAttribute("checked") != null) {
            if (checkbox_AllDB.getAttribute("checked").toLowerCase().equals("true")) {
                checkbox_AllDB.click();
            }
        } else {
            //如果没有勾选，则全选，再取消
            checkbox_AllDB.click(); //勾选
            checkbox_AllDB.click(); //取消勾选
        }
        //勾选数据库
        String[] databases = databases_id.split(",");
        for (String database : databases) {
            WebElement checkbox_DB = this.getElementById(database.trim());
            if (checkbox_DB.getAttribute("checked") == null) {
                l.info("Check database [{}]", database.trim());
                checkbox_DB.click();
            }
        }
        //清除所有搜索条件
//		for(WebElement el : this.table_fields().findElements(By.tagName("input"))){
//			try{
//				el.clear();
//			}catch(Exception e){
//				//Do nothing
//			}
//		}
        this.btn_clear().click();
        //填写搜索关键字
        String[] fields = fields_name.split(",");
        int fieldAmount = fields.length;
        String[] keywords = keywords_string.split(",");
        int keywordAmount = keywords.length;
        int amount = 0;
        if (fieldAmount != keywordAmount) {
            l.warn("搜索条件和搜索关键字数量不匹配，搜索条件数：{}，关键字数：{}", fieldAmount, keywordAmount);
        }
        if (fieldAmount < keywordAmount) {
            amount = fieldAmount;
        } else {
            amount = keywordAmount;
        }
        for (int i = 0; i < amount; i++) {
            this.getElementByName(fields[i].trim()).clear();
            this.getElementByName(fields[i].trim()).sendKeys(keywords[i].trim());
            l.info("Search field:{}, keyword:{}", fields[i], keywords[i].trim());
        }
        //填写排除条件
        if (parmPairs != null) {
            this.func_add_ExclusionFields(parmPairs);
        }
        //返回当前query
        String queryText = this.func_getQuery();
        l.info("Query: {}", queryText);
        this.link_search_fieldSearch().click();
        return queryText;
    }

    /**
     * 搜索--命令搜索
     *
     * @param databases_id
     * @param searchPage_cmd_conditions
     * @param searchPage_cmd_fields
     * @param searchPage_cmd_keywords
     * @return
     */
    public String func_commandSearch(String databases_id, String searchPage_cmd_conditions, String searchPage_cmd_fields, String searchPage_cmd_keywords) {
        l.entry();
        //清除数据库勾选
        WebElement checkbox_AllDB = this.getElementById("ALL_DB");
        //有时候checked属性会取不到，当做没有勾选
        if (checkbox_AllDB.getAttribute("checked") != null) {
            if (checkbox_AllDB.getAttribute("checked").toLowerCase().equals("true")) {
                checkbox_AllDB.click();
            }
        } else {
            //如果没有勾选，则全选，再取消
            checkbox_AllDB.click(); //勾选
            checkbox_AllDB.click(); //取消勾选
        }
        //勾选数据库
        String[] databases = databases_id.split(",");
        for (String database : databases) {
            WebElement checkbox_DB = this.getElementById(database.trim());
            if (checkbox_DB.getAttribute("checked") == null) {
                l.info("Check database [{}]", database.trim());
                checkbox_DB.click();
            }
        }
        //填写搜索关键字
        String[] conditions = searchPage_cmd_conditions.split(",");
        int conditionAmount = conditions.length;
        String[] fields = searchPage_cmd_fields.split(",");
        int fieldAmount = fields.length;
        String[] keywords = searchPage_cmd_keywords.split(",");
        int keywordAmount = keywords.length;
        int amount = 0;
        if ((conditionAmount != fieldAmount) || (conditionAmount != keywordAmount)) {
            l.warn("搜索条件和搜索关键字数量不匹配，连接条件数：{}，搜索条件数：{}，关键字数：{}", conditionAmount, fieldAmount, keywordAmount);
        }
        //取最小值
        if (fieldAmount < keywordAmount) {
            amount = fieldAmount;
        } else {
            amount = keywordAmount;
        }
        if (amount > conditionAmount) {
            amount = conditionAmount;
        }
        //遍历搜索条件，逐个输入
        for (int i = 0; i < amount; i++) {
            //如果条件为空字符串，则尝试点击删除条件按钮;否则，尝试点击展开条件按钮
            if (conditions[i].trim().equals("")) {
                try {
                    this.link_delete_condition().click();
                } catch (Exception e) {
                    //do nothing
                }
            } else {
                try {
                    this.link_add_condition().click();
                } catch (Exception e) {
                    //do nothing
                }
                //选择条件
                this.select_condition_commandSearch().selectByVisibleText(conditions[i].trim());
            }
            this.select_field_commandSearch().selectByVisibleText(fields[i].trim());
            this.input_keyword_commandSearch().clear();
            this.input_keyword_commandSearch().sendKeys(keywords[i].trim());
            this.link_addQuery_commandSearch().click();
            l.info("Condition [{}], search field:[{}], keyword:[{}]", conditions[i].trim(), fields[i].trim(), keywords[i].trim());
        }
        //返回当前query
        String queryText = this.textArea_query().getAttribute("value");
        l.info("Query: {}", queryText);
        this.link_search_commandSearch().click();
        return queryText;
    }

    /**
     * 获取当前查询
     *
     * @return query
     */
    public String func_getQuery() {
        l.entry();
        return this.div_query().getText();
    }


    /**
     * 验证搜索字段是否正确
     *
     * @param fields -- 要检查的字段
     * @return 是否正确
     */
    public boolean func_verifySearchFields(String[] fields) {
        l.entry();
        String stringFields = this.table_fields().getText();
        l.debug("table text is {}", stringFields);
        boolean result = true;
        for (String s : fields) {
            s = s.trim();
            l.info("Checking [{}]", s);
            if (!stringFields.contains(s)) {
                result = false;
                l.error("[{}] is missing", s);
            }
        }
        if (result == true) {
            l.info("++++++++++++++++++++++++++++++ Pass -- searchFieldsCheck");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- searchFieldsCheck");
            t.takeScreenshot(d);
        }
        return result;
    }

    /**
     * 验证用户菜单内容是否正确
     *
     * @param fields -- 要检查的字段
     * @return
     * @throws Exception
     */
    public boolean func_verifyUserMenu(String[] fields) throws Exception {
        l.entry();
        act = new Actions(d);
        act.moveToElement(this.link_userName()).perform();
        Thread.sleep(3000);
        String stringFields = this.div_userMenu().getText();
        l.debug("div text is {}", stringFields);
        boolean result = true;
        for (String s : fields) {
            s = s.trim();
            l.info("Checking [{}]", s);
            if (!stringFields.contains(s)) {
                result = false;
                l.error("[{}] is missing", s);
                t.takeScreenshot(d);
            }
        }
        if (result == true) {
            l.info("++++++++++++++++++++++++++++++ Pass -- userMenuCheck");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- userMenuCheck");
            t.takeScreenshot(d);
        }
        return result;
    }

    /**
     * 点击搜索按钮
     */
    public void func_click_searchButton() {
        l.entry();
        this.link_search_fieldSearch().click();
        l.exit();
    }

    /**
     * 点击导出按钮--批量搜索
     */
    public void func_click_export() {
        l.entry();
        this.link_export().click();
        l.exit();
    }

    /**
     * 勾选允许替换
     */
    public void func_click_allowReplacement() {
        l.entry();
        this.input_allowReplacement().click();
        l.exit();
    }

    /**
     * 点击批量搜索
     */
    public void func_click_bulkSearch() {
        l.entry();
        this.link_advancedSearchTab_byIndex(2).click();
        l.exit();
    }

    /**
     * 点击命令搜索
     */
    public void func_click_commandSearch() {
        l.entry();
        this.link_advancedSearchTab_byIndex(1).click();
        l.exit();
    }

    /**
     * 点击图片探索
     */
    public void func_click_imageDiscover() {
        l.entry();
        this.link_advancedSearchTab_byIndex(3).click();
        l.exit();
    }

    /**
     * 点击IPC搜索
     */
    public void func_click_classificationSearch() {
        l.entry();
        this.link_advancedSearchTab_byIndex(4).click();
        l.exit();
    }

    /**
     * 验证IPC搜索结果是否存在
     *
     * @return
     */
    public boolean func_verify_ipcSearchResultExist() {
        l.entry();
        if (this.div_ipcResultGroup() != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- IPC search result exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- IPC search result does not exist");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 通过索引点击左侧链接
     *
     * @param index
     */
    public void func_click_leftSideLink_byIndex(int index) {
        l.entry();
        l.info("Clicking left side link: {}", this.link_leftSide_byIndex(index).getText());
        this.link_leftSide_byIndex(index).click();
        l.exit();
    }

    /**
     * 添加排除条件
     *
     * @param parmPairs
     */
    public void func_add_ExclusionFields(HashMap<String, String> parmPairs) {
        l.entry();
        //判断输入是否为空
        if (parmPairs.size() == 0) {
            l.info("Input parameter is empty");
            return;
        }
        //展开过滤条件列表
        this.link_addExclusionCriteria().click();
        //遍历所有输入字段，添加并填写关键字
        int i = 0;
        for (String field : parmPairs.keySet()) {
            //尝试添加，不成功则继续
            try {
                l.debug("Setting exclusion field:[{}], value:[{}]", field, parmPairs.get(field));
                //点击添加关键字
                this.link_addExclusionFields().click();
                Thread.sleep(2000);
                //选择字段
                this.select_exclusionFields_byIndex(i).selectByVisibleText(field.trim());
                //填写关键字
                this.input_setExclusionFields_byIndex(i).clear();
                this.input_setExclusionFields_byIndex(i).sendKeys(parmPairs.get(field).trim());
            } catch (Exception e) {
                e.printStackTrace();
                t.takeScreenshot(d);
                i++;
                continue;
            }
            i++;
        }
    }

    /**
     * 批量搜索功能
     *
     * @param keyword
     * @throws InterruptedException
     */
    public String func_bulkSearch(String keyword) throws InterruptedException {
        l.entry();
        l.info("Bulk search, and keyword is [{}]", keyword);
        this.input_bulkSearchKeyword().clear();
        this.input_bulkSearchKeyword().sendKeys(keyword);
        this.link_bulkSearch_add().click();
        //等待搜索结果
        int i = 0;
        while (this.span_bulkSearchLoading().isDisplayed()) {
            if (i < 10) {
                Thread.sleep(1000);
                i++;
                l.debug("Waiting for bulk search result...");
            } else {
                l.debug("Wait timeout");
                break;
            }
        }
        //获取搜索结果
        String result = this.textArea_bulkSearchResult().getAttribute("value");
        l.info("========== Start bulk search result ==========");
        l.info(result);
        l.info("========== End bulk search result ==========");
        return result;
    }

    /**
     * 验证批量搜索结果数量
     *
     * @param expNum
     * @return
     */
    public boolean func_verifyBulkSearchResultNum(int expNum) {
        l.entry();
        l.info("Expected result number: [{}]", expNum);
        int actNum = Integer.parseInt(this.strong_bulkSearchResultNum().getText());
        l.info("Actual result number: [{}]", actNum);
        if (actNum == expNum) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Bulk search result num is correct");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Bulk search result num is incorrect");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * IPC搜索--通过CN
     *
     * @param keyword
     */
    public void func_ipcSearch_byCN(String keyword) {
        l.entry();
        l.info("IPC search by classification, and keyword is [{}]", keyword);
        this.input_ipcKeyword().clear();
        this.input_ipcKeyword().sendKeys(keyword);
        this.radio_ipcSearchMode_byCN().click();
        this.link_ipcSearch().click();
        l.exit();
    }

    /**
     * IPC搜索--通过关键字
     *
     * @param keyword
     */
    public void func_ipcSearch_byKeyword(String keyword) {
        l.entry();
        l.info("IPC search by keyword, and keyword is [{}]", keyword);
        this.input_ipcKeyword().clear();
        this.input_ipcKeyword().sendKeys(keyword);
        this.radio_ipcSearchMode_byKeyword().click();
        this.link_ipcSearch().click();
        l.exit();
    }

    /**
     * 查看批量搜索结果
     */
    public void func_view_bulkSearchResult() {
        l.entry();
        this.link_bulkSearch_search().click();
        l.exit();
    }

    /**
     * 查看ipc搜索结果
     */
    public void func_view_ipcSearchResult(int groupIndex, int rowIndex) {
        l.entry();
        WebElement e = d.findElement(By.className("ipc-result")).findElements(By.className("group")).get(groupIndex).findElements(By.tagName("tr")).get(rowIndex);
        Actions a = new Actions(d);
        a.moveToElement(e).perform();
        e.findElement(By.className("ipc-pop-search")).click();
        d.findElement(By.className("float-jump-box")).findElement(By.className("bigbtn")).click();
        l.exit();
    }

    /**
     * 搜索--字段搜索（只做简单搜索）
     */
    public void func_field_simple(String query) {
        l.entry();
        //在all字段输入query
        this.input_total().click();
        this.input_total().sendKeys(query);
        this.btn_search().click();
    }

    /**
     * 搜索——批量搜索（只做简单搜索）
     */
    public void FuncBulkSearchSimple(String KeyWord) {
        l.entry();
        this.input_bulkSearchKeyword().clear();
        this.input_bulkSearchKeyword().sendKeys(KeyWord);
        this.link_bulkSearch_add().click();
    }

    /**
     * 点击添加到收藏夹按钮
     */
    public void func_click_addTolist_button() {
        l.entry();
        this.addTolist_BulkSearchResult().click();
    }

    /**
     * 链接-根据名称获取文件夹列表（SP）
     *
     * @param
     * @return
     */
    public WebElement link_listfolder_byName_bs(String name) {
        l.entry();
        if (this.div_folder() == null) {
            l.error("cannot find folder");
            return null;
        }
        List<WebElement> links = this.div_folder().findElements(By.tagName("a"));
        for (WebElement link : links) {
            if (link.getText().equals(name)) {
                return link;
            }
        }
        l.warn("User list folder link is null");
        return null;
    }

    /**
     * 新建收藏夹（from bulk search）
     */
    public void func_create_parent_folder_bs(String foldername) throws InterruptedException, AWTException {
        l.entry();
        this.func_click_addTolist_button();
        this.btnCreateNewFolder_addToListDialog().click();
        //通过参数传递列表名
        this.input_folderName_addToListDialog().clear();
        this.input_folderName_addToListDialog().sendKeys(foldername);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
    }

    /**
     * 新建收藏夹_default（from bulk search）
     */
    public void func_create_parent_folder_default_bs() throws InterruptedException, AWTException {
        l.entry();
        this.func_click_addTolist_button();
        this.btnCreateNewFolder_addToListDialog().click();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
    }

    /**
     * 新建子收藏夹（From Bulk Search Page）
     *
     * @throws InterruptedException
     * @throws AWTException
     */
    public void funCreatChildFolderBS(String foldername, String foldername1) throws InterruptedException, AWTException {
        l.entry();
        if (this.link_listfolder_byName_bs(foldername) != null) {
            act = new Actions(d);
            act.moveToElement(this.link_listfolder_byName_bs(foldername));
            act.perform();
            this.link_listfolder_byName_bs(foldername).click();
            this.btn_add_addToListDialog().click();
            Thread.sleep(3000);
            this.input_folderName_addToListDialog().clear();
            this.input_folderName_addToListDialog().sendKeys(foldername1);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
        } else {
            l.error("<<<<<<<<<<<<<Fail ---Userlist folder [{}] does not exist", foldername);
        }
    }

    /**
     * 编辑收藏夹名称（From Bulk Search）
     */
    public void func_editListNameBS(String name, String newname) throws InterruptedException, AWTException {
        l.entry();
        if (this.link_listfolder_byName_bs(name) != null) {
            this.link_listfolder_byName_bs(name).click();
            this.btn_rename_addToListDialog().click();
            this.input_folderName_addToListDialog().clear();
            this.input_folderName_addToListDialog().sendKeys(newname);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
        } else {
            l.error("<<<<<<<<<<<<<<<Fail -- User list folder [{}] does not exist", name);
        }
    }

}
