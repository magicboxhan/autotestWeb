package com.hq.test.automation.pageobject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.hq.test.framework.page.BasePage;
import com.hq.test.framework.util.ExcelUtil;
import com.hq.test.framework.util.FileUtil;

import org.apache.commons.httpclient.HttpException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * 智慧芽页面对象基类
 *
 * @author hanqing
 */
public class Zhihuiya_basePage extends BasePage {


    /********** Elements **********/

    /**
     * 通过name获取元素
     *
     * @param name
     * @return
     */
    public WebElement getElementByName(String name) {
        l.entry();
        return d.findElement(By.name(name));
    }

    /**
     * 通过id获取元素
     *
     * @param id
     * @return
     */
    public WebElement getElementById(String id) {
        l.entry();
        return d.findElement(By.id(id));
    }

    /**
     * 链接--邮件提醒
     *
     * @return
     */
    public WebElement link_emailAlert() {
        l.entry();
        WebElement return_link = null;
        for (WebElement a : d.findElement(By.className("feature-list")).findElements(By.tagName("a"))) {
            if (a.getAttribute("href").toLowerCase().contains("/alert/email")) {
                return_link = a;
                break;
            }
        }
        if (return_link == null) {
            l.warn("Email alert link is null");
        }
        return return_link;
    }

    /**
     * 链接--以保存搜索
     *
     * @return
     */
    public WebElement link_savedSearch() {
        l.entry();
        WebElement return_link = null;
        for (WebElement a : d.findElement(By.className("feature-list")).findElements(By.tagName("a"))) {
            if (a.getAttribute("href").toLowerCase().contains("/userdatasets/savedquerylist")) {
                return_link = a;
                break;
            }
        }
        if (return_link == null) {
            l.warn("Saved search link is null");
        }
        return return_link;
    }

    /**
     * 链接--自定义库
     *
     * @return
     */
    public WebElement link_customDatabase() {
        l.entry();
        WebElement return_link = null;
        for (WebElement a : d.findElement(By.className("feature-list")).findElements(By.tagName("a"))) {
            if (a.getAttribute("href").toLowerCase().contains("/cdb")) {
                return_link = a;
                break;
            }
        }
        if (return_link == null) {
            l.warn("Custom database link is null");
        }
        return return_link;
    }

    /**
     * 链接--收藏夹
     *
     * @return
     */
    public WebElement link_userList() {
        l.entry();
        WebElement return_link = null;
        for (WebElement a : d.findElement(By.className("feature-list")).findElements(By.tagName("a"))) {
            if (a.getAttribute("href").toLowerCase().contains("/userdatasets/index")) {
                return_link = a;
                break;
            }
        }
        if (return_link == null) {
            l.warn("User list link is null");
        }
        return return_link;
    }

    /**
     * 链接--搜索历史
     *
     * @return
     */
    public WebElement link_history() {
        l.entry();
        WebElement return_link = null;
        for (WebElement a : d.findElement(By.className("feature-list")).findElements(By.tagName("a"))) {
            if (a.getAttribute("href").toLowerCase().contains("/history")) {
                return_link = a;
                break;
            }
        }
        if (return_link == null) {
            l.warn("History link is null");
        }
        return return_link;
    }

    /**
     * 链接--登出
     *
     * @return
     */
    public WebElement link_logout() {
        l.entry();
        WebElement return_link = null;
        for (WebElement a : d.findElement(By.className("support-btns")).findElements(By.tagName("a"))) {
            if (a.getAttribute("href").toLowerCase().contains("/logout")) {
                return_link = a;
                break;
            }
        }
        if (return_link == null) {
            l.warn("Logout link is null");
        }
        return return_link;
    }

    /**
     * 链接--账户
     *
     * @return
     */
    public WebElement link_account() {
        l.entry();
        WebElement return_link = null;
        for (WebElement a : d.findElement(By.className("profile")).findElements(By.tagName("a"))) {
            if (a.getAttribute("href").toLowerCase().contains("/account")) {
                return_link = a;
                break;
            }
        }
        if (return_link == null) {
            l.warn("Account link is null");
        }
        return return_link;
    }

    /**
     * 链接--用户名
     *
     * @return
     */
    public WebElement link_userName() {
        l.entry();
        return d.findElement(By.cssSelector(".user-name.tools"));
    }

    /**
     * 搜索按钮
     *
     * @return
     */
    public WebElement link_search() {
        l.entry();
        return d.findElement(By.className("btn-search"));
    }

    /**
     * 用户菜单
     *
     * @return
     */
    public WebElement div_userMenu() {
        l.entry();
        return d.findElement(By.className("user-menu"));
    }

    /**
     * 链接--选择数据库
     *
     * @return
     */
    public WebElement link_databaseSelector() {
        l.entry();
        return d.findElement(By.id("databaseSelector"));
    }

    public WebElement div_query(){
        l.entry();
        return d.findElement(By.className("search-input"));
    }

    /**
     * 表格--选择数据库
     *
     * @return
     */
    public WebElement table_databaseSelectorTable() {
        l.entry();
        return d.findElement(By.cssSelector(".form-table.database-selector-table"));
    }

    /**
     * 文本框--查询语句
     *
     * @return
     */
    public WebElement input_query() {
        l.entry();
        return d.findElement(By.id("q"));
    }

    /**
     * html body
     *
     * @return
     */
    public WebElement body_html() {
        l.entry();
        return d.findElement(By.tagName("body"));
    }

    /**
     * span -- 提示信息
     *
     * @return
     */
    public WebElement div_content() {
        l.entry();
        return d.findElement(By.className("inner"));
    }

    /**
     * ul -- 页脚链接
     *
     * @return
     */
    public WebElement ul_footerLinks() {
        l.entry();
        return d.findElement(By.className("footer-nav"));
    }

    /**
     * div -- 语言选择
     *
     * @return
     */
    public WebElement div_languageSelect() {
        l.entry();
        return d.findElement(By.className("language-select"));
    }

    /**
     * ul -- 语言选择列表
     *
     * @return
     */
    public WebElement ul_languageList() {
        l.entry();
        l.exit();
        return d.findElement(By.className("drop-menu"));
    }


    /********** Functions **********/

    /**
     * convert table data into List<HashMap> (only used by analysis table)
     *
     * @param table
     * @return
     */
    public ArrayList<HashMap<String, String>> getTableData_analysisTable(WebElement table) {
        l.entry();
        if (table == null) {
            l.error("Source table is null");
            return null;
        }
        //返回数据
        ArrayList<HashMap<String, String>> returnData = new ArrayList<HashMap<String, String>>();
        //表头数组
        ArrayList<String> headArray = new ArrayList<String>();
        //所有行
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        l.debug("row amount is {}", rows.size());
        //将表头所有列加入表头数组
        List<WebElement> cols_head = rows.get(0).findElements(By.tagName("th"));
        for (WebElement th : cols_head) {
            String colText = th.getText();
            l.debug("column text is {}", colText);
            headArray.add(th.getText());
        }
        l.debug("column amount is {}", headArray.size());
        //表身，每一行
        for (int i = 1; i < rows.size(); i++) {
            HashMap<String, String> rowHash = new HashMap<String, String>();
            WebElement tr = rows.get(i);
            //第一列比较特殊，数据元素为th
            String key = headArray.get(0);
            l.debug("col head is {}", key);
            String value = tr.findElement(By.tagName("th")).getText();
            l.debug("col value is {}", value);
            rowHash.put(key, value);
            List<WebElement> tds = tr.findElements(By.tagName("td"));
            //每一列，从第二列开始
            for (int j = 1; j < headArray.size(); j++) {
                key = headArray.get(j);
                l.debug("col head is {}", key);
                value = tds.get(j - 1).getText();
                l.debug("col value is {}", value);
                rowHash.put(key, value);
            }
            returnData.add(rowHash);
        }
//		//验证数据
//		l.debug("===== verify data =====");
//		l.debug("array size is {}", returnData.size());
//		for (HashMap<String, String> hash : returnData){
//			l.debug("=== new hash ===");
//			for (String key : hash.keySet()){
//				l.debug("Key is {}", key);
//				l.debug("Value is {}", hash.get(key));
//			}
//		}
        return returnData;
    }

    /**
     * 返回当前query
     *
     * @return
     */
    public String func_get_queryText() {
        l.entry();
        return this.input_query().getAttribute("value");
    }

    /**
     * 获取已勾选的数据库
     *
     * @return 勾选数据库元素ID集合
     */
    public List<String> func_get_checkedDatabaseList() {
        l.entry();
        List<String> checkedIds = new ArrayList<String>();
        this.link_databaseSelector().click();
        if (this.table_databaseSelectorTable().isDisplayed()) {
            //获取所有input
            List<WebElement> checkboxes = this.table_databaseSelectorTable().findElements(By.tagName("input"));
            //如果input的checked属性为true，加入返回集合
            for (WebElement cb : checkboxes) {
                boolean isChecked = (cb.getAttribute("checked") != null);
                if (isChecked) {
                    checkedIds.add(cb.getAttribute("id"));
                }
            }
        }
        this.link_databaseSelector().click();
        return checkedIds;
    }

    /**
     * 验证数据库列表内容是否正确
     *
     * @param fields
     * @return
     */
    public boolean func_verify_databaseList(String[] fields) {
        l.entry();
        this.link_databaseSelector().click();
        String stringFields = this.table_databaseSelectorTable().getText();
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
            l.info("++++++++++++++++++++++++++++++ Pass -- databaseListCheck");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- databaseListCheck");
            t.takeScreenshot(d);
        }
        return result;
    }

    /**
     * 验证应该勾选的数据库是否被勾选了
     *
     * @param checkedIds--已勾选的Id
     * @return
     */
    public boolean func_verify_checkedDatabaseList(List<String> checkedIds) {
        l.entry();
        boolean result = true;
        List<String> actCheckedId = this.func_get_checkedDatabaseList();
        for (String expId : checkedIds) {
            boolean matchFlag = false;
            for (String actId : actCheckedId) {
                if (expId.equals(actId)) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- [{}] is checked", expId);
                    matchFlag = true;
                    break;
                }
            }
            if (matchFlag) {
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [{}] is not checked", expId);
                result &= false;
            }
        }
        if (result) {
            l.info("++++++++++++++++++++++++++++++ Pass -- All checked database are correct");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Some databases are not checked");
            t.takeScreenshot(d);
        }
        return result;
    }

    /**
     * 验证文件是否下载成功
     *
     * @param download_path   -- 下载目录
     * @param partialFileName -- 部分文件名
     * @param extName         -- 扩展名
     * @param timeout         -- 超时时间（秒）
     * @return 已下载文件名
     * @throws InterruptedException
     */
    public String func_verify_fileDownload(String download_path, String partialFileName, String extName, int timeout) throws InterruptedException {
        l.entry();
        FileUtil f = new FileUtil();
        // 验证文件是否下载成功
        int i = 0;
        boolean downloadFlag = true;
        String fileName = null;
        while ((f.getFileNameByPartialFileName(download_path, partialFileName, extName) == null) || (f.getFileNameByPartialFileName(download_path, partialFileName, "part") != null)) {
            if (i < timeout) {
                Thread.sleep(1000);
                l.debug("waiting for file to be downloaded...");
                i++;
            } else {
                l.debug("timeout");
                downloadFlag = false;
                break;
            }
        }
        //判断结果
        if (downloadFlag) {
            fileName = f.getFileNameByPartialFileName(download_path, partialFileName, extName);
            l.info("++++++++++++++++++++++++++++++ Pass -- File is downloaded successfully, and the file name is [{}]", fileName);
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- File with partial name [{}] does not exist", partialFileName);
        }
        return fileName;
    }

    /**
     * 验证导出Excel的数据
     *
     * @param expData -- 专利查看页获取的数据
     * @param actData -- Excel中获取的数据
     * @param type    -- 字段类型 -- 0: Custom Fields; 1: Key Fields only; 2: Basic Fields only; 3: Cites; 4: Cited By; 5: All Fields;  6: template
     * @return 是否验证通过
     * @throws ParseException
     */
    public boolean func_verify_excelData(ArrayList<HashMap<String, String>> expData, ArrayList<HashMap<String, String>> actData, int type, String templateFields) throws ParseException {
        l.entry();
        l.info("============================== Verify data in excel ==============================");
        boolean result = true;
        //1.输入检查
        if (expData == null || expData.size() == 0 || actData == null || actData.size() == 0) {
            l.error("Parameter is invalid");
            return false;
        }
        //根据导出类型，验证字段
        ArrayList<String> expTitles = new ArrayList<String>();    //保存期望列名
        switch (type) {
            case 0:
                expTitles.add("Publication  Number");
                break;
            case 1:
                expTitles.add("Publication  Number");
                expTitles.add("Title");
                expTitles.add("Application Date");
                expTitles.add("Publication Date");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                expTitles.add("All IPC");
                break;
            case 2:
                expTitles.add("Publication  Number");
                expTitles.add("Title");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                break;
            case 3:
                expTitles.add("Publication  Number");
                expTitles.add("Cites Patent Number");
                expTitles.add("Title");
                expTitles.add("Application Date");
                expTitles.add("Publication Date");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                expTitles.add("All IPC");
//			expTitles.add("Abstract");
                expTitles.add("IPC Primary");
                expTitles.add("All UPC");
                expTitles.add("Agency/Law Firm");
                expTitles.add("Agent/Attorney");
                break;
            case 4:
                expTitles.add("Publication  Number");
                expTitles.add("Cited Patent Number");
                expTitles.add("Title");
                expTitles.add("Application Date");
                expTitles.add("Publication Date");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                expTitles.add("All IPC");
//			expTitles.add("Abstract");
                expTitles.add("IPC Primary");
                expTitles.add("All UPC");
                expTitles.add("Agency/Law Firm");
                expTitles.add("Agent/Attorney");
                break;
            case 5:
                expTitles.add("Publication  Number");
                expTitles.add("Title");
                expTitles.add("Application Date");
                expTitles.add("Publication Date");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                expTitles.add("All IPC");
                expTitles.add("Abstract");
                expTitles.add("IPC Primary");
                expTitles.add("All UPC");
                expTitles.add("Agency/Law Firm");
                expTitles.add("Agent/Attorney");
                break;
        }
        if (expTitles.size() == 0 && type != 6) {
            l.error("Input field is invalid");
            return false;
        }
        //遍历期望列名列表，搜索匹配列
        if (type != 6) {
            //不使用模板，才验证字段
            l.info("========== Verify columns in excel ==========");
            for (String expKey : expTitles) {
                l.info("Exp col: [{}]", expKey);
                boolean matchFlag = false;
                //遍历实际数据，搜索匹配列
                for (String actKey : actData.get(0).keySet()) {
                    l.debug("Act col: [{}]", actKey);
                    if (expKey.equals(actKey)) {
                        l.info("++++++++++++++++++++++++++++++ Pass -- Column exists");
                        matchFlag = true;
                        break;
                    }
                }
                if (!matchFlag) {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Column does not exist");
                    result &= false;
                }
            }
        } else {
            int exp_colNum = templateFields.split(",").length + 2;
            int act_colNum = actData.get(0).keySet().size();
            if (exp_colNum == act_colNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Exp col num: {}, act col num: {}", exp_colNum, act_colNum);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Exp col num: {}, act col num: {}", exp_colNum, act_colNum);
                result &= false;
            }
        }
        //转换数据
        ArrayList<HashMap<String, String>> actData_u = new ArrayList<HashMap<String, String>>(); //转存后的数据集合
        //遍历实际数据，替换其中的key，并转存为新的数据集合
        for (HashMap<String, String> patent : actData) {
            HashMap<String, String> patent_u = new HashMap<String, String>();    //转存后的单条专利数据
            //遍历每个key
            for (String key : patent.keySet()) {
                String key_u = "";    //新的key
                String value = patent.get(key);
                if ((value == null) || (value.equals("-"))) {
                    value = "";
                }
                switch (key) {
                    case "Publication  Number":
                        if (type != 3 && type != 4) {
                            key_u = "Publication Number";
                        }
                        break;
                    case "Cites Patent Number":
                        key_u = "Publication Number";
                        break;
                    case "Cited Patent Number":
                        key_u = "Publication Number";
                        break;
                    case "Inventor(s)":
                        key_u = "Inventor Name";
                        break;
                    case "Assignee(s)":
                        key_u = "Assignee Name";
                        break;
                    case "All IPC":
                        key_u = "International Classification";
                        break;
                    case "All UPC":
                        key_u = "US Classification";
                        break;
                    case "Agency/Law Firm":
                        key_u = "Agency";
                        break;
                    case "Agent/Attorney":
                        key_u = "Attorney Name";
                        break;
                    default:
                        key_u = key;
                }
                patent_u.put(key_u, value);
            }
            actData_u.add(patent_u);
        }
        l.info("========== Verify data in excel ==========");
        //2.遍历期望数据，逐个验证
        for (HashMap<String, String> expPatent : expData) {
            String expPN = expPatent.get("Publication Number");
            l.info("Exp PN: [{}]", expPN);
            //3.遍历实际数据，找到匹配数据
            boolean matchFlag = false; //标记是否找到匹配
            for (HashMap<String, String> actPatent : actData_u) {
                String actPN = actPatent.get("Publication Number");
                l.debug("Act PN: [{}]", actPN);
                if (expPN.equals(actPN)) {
                    //4.找到匹配，验证具体数据
                    l.info("Patent is found, so verify data");
                    //遍历期望专利的key，验证数据
                    for (String expKey : expPatent.keySet()) {
//					for(String expKey : expTitles){
                        String expValue = expPatent.get(expKey);
                        String actValue = actPatent.get(expKey);
                        if (actValue == null) {
                            l.debug("No such column in excel, so ignor");
                            continue;
                        }
                        //由于数据格式差异，正式比较之前，需要按一定规则转换实际数据
                        //a.转换分隔符
//						if(!expKey.equals("Assignee Name") 
//								&& !expKey.equals("Inventor Name") 
//								&& !expKey.equals("Title") 
//								&& !expKey.equals("International Classification")
//								&& !expKey.equals("US Classification")
//								&& !expKey.equals("Abstract")
//								){
//							actValue = actValue.replace(" | ", "\n");
//						}
                        //b.转换时间格式
                        if (expKey.equals("Publication Date") || expKey.equals("Application Date")) {
                            SimpleDateFormat sdf_toDate = new SimpleDateFormat("yyyy-MM-dd");
                            Date d = sdf_toDate.parse(actValue);
                            SimpleDateFormat sdf_toString = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                            actValue = sdf_toString.format(d);
                        }
                        l.info("Verify column: [{}]", expKey);
                        l.info("Exp value: [{}]", expValue);
                        l.info("Act value: [{}]", actValue);
                        if (expValue.equals(actValue)) {
                            l.info("++++++++++++++++++++++++++++++ Pass -- Value is correct");
                        } else {
                            //尝试分割字符串，验证各个子字符串是否一致
                            try {
                                String[] exp_strings = expValue.split("|");
                                String[] act_strings = actValue.split("|");
                                boolean result_2 = true;
                                for (String exp_stirng : exp_strings) {
                                    boolean matchFlag_2 = false;
                                    for (String act_string : act_strings) {
                                        if (exp_stirng.trim().equals(act_string.trim())) {
                                            matchFlag_2 = true;
                                            break;
                                        }
                                    }
                                    if (!matchFlag_2) {
                                        result_2 &= false;
                                    }
                                }
                                if (result_2) {
                                    l.info("++++++++++++++++++++++++++++++ Pass -- Value is correct");
                                } else {
                                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Value is incorrect");
                                    result &= false;
                                }
                            } catch (Exception e) {
                                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Value is incorrect");
                                result &= false;
                            }
                        }
                    }
                    //5.验证后续处理
                    matchFlag = true;
                    break;
                }
            }
            //6.如果没找到，则报错
            if (!matchFlag) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- PN [{}] is not found in excel", expPN);
                result &= false;
            }
        }
        return result;
    }

    /**
     * 验证导出excel中自定义字段内容
     *
     * @param expData
     * @param actData
     * @return
     */
    public boolean func_verify_excelData_customFields(ArrayList<HashMap<String, String>> expData, ArrayList<HashMap<String, String>> actData) {
        l.entry();
        l.info("============================== Verify custom fields in excel ==============================");
        boolean result = true;
        //1.输入检查
        if (expData == null || expData.size() == 0 || actData == null || actData.size() == 0) {
            l.error("Parameter is invalid");
            return false;
        }
        //根据导出类型，验证字段
        ArrayList<String> expTitles = new ArrayList<String>();    //保存期望列名
        for (String key : expData.get(0).keySet()) {
            expTitles.add(key);
        }
        if (expTitles.size() == 0) {
            l.error("Input field is invalid");
            return false;
        }
        //遍历期望列名列表，搜索匹配列
        //不使用模板，才验证字段
        l.info("========== Verify custom fields columns in excel ==========");
        for (String expKey : expTitles) {
            l.info("Exp col: [{}]", expKey);
            boolean matchFlag = false;
            //遍历实际数据，搜索匹配列
            for (String actKey : actData.get(0).keySet()) {
                l.debug("Act col: [{}]", actKey);
                if (expKey.equals(actKey)) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- Column exists");
                    matchFlag = true;
                    break;
                }
            }
            if (!matchFlag) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Column does not exist");
                result &= false;
            }
        }

        //转换数据
        ArrayList<HashMap<String, String>> actData_u = new ArrayList<HashMap<String, String>>(); //转存后的数据集合
        //遍历实际数据，替换其中的key，并转存为新的数据集合
        for (HashMap<String, String> patent : actData) {
            HashMap<String, String> patent_u = new HashMap<String, String>();    //转存后的单条专利数据
            //遍历每个key
            for (String key : patent.keySet()) {
                String key_u = "";    //新的key
                String value = patent.get(key);
                if ((value == null) || (value.equals("-"))) {
                    value = "";
                }
                switch (key) {
                    case "Publication  Number":
                        key_u = "Publication Number";
                        break;
                    case "Cites Patent Number":
                        key_u = "Publication Number";
                        break;
                    case "Cited Patent Number":
                        key_u = "Publication Number";
                        break;
                    default:
                        key_u = key;
                }
                patent_u.put(key_u, value);
            }
            actData_u.add(patent_u);
        }
        l.info("========== Verify custom fields data in excel ==========");
        //2.遍历期望数据，逐个验证
        for (HashMap<String, String> expPatent : expData) {
            String expPN = expPatent.get("Publication  Number");
            l.info("Exp PN: [{}]", expPN);
            //3.遍历实际数据，找到匹配数据
            boolean matchFlag = false; //标记是否找到匹配
            for (HashMap<String, String> actPatent : actData_u) {
                String actPN = actPatent.get("Publication Number");
                l.debug("Act PN: [{}]", actPN);
                if (expPN.equals(actPN)) {
                    //4.找到匹配，验证具体数据
                    l.info("Patent is found, so verify data");
                    //遍历期望专利的key，验证数据
                    for (String expKey : expPatent.keySet()) {
//					for(String expKey : expTitles){
                        String expValue = expPatent.get(expKey);
                        String actValue = actPatent.get(expKey);
                        if (actValue == null) {
                            l.debug("No such column in excel, so ignor");
                            continue;
                        }
                        l.info("Verify column: [{}]", expKey);
                        l.info("Exp value: [{}]", expValue);
                        l.info("Act value: [{}]", actValue);
                        if (expValue.equals(actValue)) {
                            l.info("++++++++++++++++++++++++++++++ Pass -- Value is correct");
                        } else if (expValue.contains("|")) {
                            //尝试分割字符串，验证各个子字符串是否一致
                            try {
                                String[] exp_strings = expValue.split("|");
                                String[] act_strings = actValue.split("|");
                                boolean result_2 = true;
                                for (String exp_stirng : exp_strings) {
                                    boolean matchFlag_2 = false;
                                    for (String act_string : act_strings) {
                                        if (exp_stirng.trim().equals(act_string.trim())) {
                                            matchFlag_2 = true;
                                            break;
                                        }
                                    }
                                    if (!matchFlag_2) {
                                        result_2 &= false;
                                    }
                                }
                                if (result_2) {
                                    l.info("++++++++++++++++++++++++++++++ Pass -- Value is correct");
                                } else {
                                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Value is incorrect");
                                    result &= false;
                                }
                            } catch (Exception e) {
                                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Value is incorrect");
                                result &= false;
                            }
                        } else {
                            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Value is incorrect");
                            result &= false;
                        }
                    }
                    //5.验证后续处理
                    matchFlag = true;
                    break;
                }
            }
            //6.如果没找到，则报错
            if (!matchFlag) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- PN [{}] is not found in excel", expPN);
                result &= false;
            }
        }
        return result;
    }

    /**
     * 验证导出csv的数据
     *
     * @param expData -- 专利查看页获取的数据
     * @param actData -- Excel中获取的数据
     * @param type    -- 字段类型 -- 0: Custom Fields; 1: Key Fields only; 2: Basic Fields only; 3: Cites; 4: Cited By; 5: All Fields;  6: template
     * @return 是否验证通过
     * @throws ParseException
     */
    public boolean func_verify_csvData(ArrayList<HashMap<String, String>> expData, ArrayList<HashMap<String, String>> actData, int type, String templateFields) throws ParseException {
        l.entry();
        l.info("============================== Verify data in csv ==============================");
        boolean result = true;
        //1.输入检查
        if (expData == null || expData.size() == 0 || actData == null || actData.size() == 0) {
            l.error("Parameter is invalid");
            return false;
        }
        //根据导出类型，验证字段
        ArrayList<String> expTitles = new ArrayList<String>();    //保存期望列名
        switch (type) {
            case 0:
                expTitles.add("Publication  Number");
                break;
            case 1:
                expTitles.add("Publication  Number");
                expTitles.add("Title");
                expTitles.add("Application Date");
                expTitles.add("Publication Date");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                expTitles.add("All IPC");
                break;
            case 2:
                expTitles.add("Publication  Number");
                expTitles.add("Title");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                break;
            case 3:
                expTitles.add("Publication  Number");
                expTitles.add("Cites Patent Number");
                expTitles.add("Title");
                expTitles.add("Application Date");
                expTitles.add("Publication Date");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                expTitles.add("All IPC");
//			expTitles.add("Abstract");
                expTitles.add("IPC Primary");
                expTitles.add("All UPC");
                expTitles.add("Agency/Law Firm");
                expTitles.add("Agent/Attorney");
                break;
            case 4:
                expTitles.add("Publication  Number");
                expTitles.add("Cited Patent Number");
                expTitles.add("Title");
                expTitles.add("Application Date");
                expTitles.add("Publication Date");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                expTitles.add("All IPC");
//			expTitles.add("Abstract");
                expTitles.add("IPC Primary");
                expTitles.add("All UPC");
                expTitles.add("Agency/Law Firm");
                expTitles.add("Agent/Attorney");
                break;
            case 5:
                expTitles.add("Publication  Number");
                expTitles.add("Title");
                expTitles.add("Application Date");
                expTitles.add("Publication Date");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                expTitles.add("All IPC");
                expTitles.add("Abstract");
                expTitles.add("IPC Primary");
                expTitles.add("All UPC");
                expTitles.add("Agency/Law Firm");
                expTitles.add("Agent/Attorney");
                break;
        }
        if (expTitles.size() == 0 && type != 6) {
            l.error("Input field is invalid");
            return false;
        }
        //遍历期望列名列表，搜索匹配列
        if (type != 6) {
            //不使用模板，才验证字段
            l.info("========== Verify columns in csv ==========");
            for (String expKey : expTitles) {
                l.info("Exp col: [{}]", expKey);
                boolean matchFlag = false;
                //遍历实际数据，搜索匹配列
                for (String actKey : actData.get(0).keySet()) {
//					if(actKey.contains("Publication  Number")){
//						//解析出的csv表头中的第一个，最前面会多一个空字符，这里去掉
//						actKey = actKey.substring(1, actKey.length());
//					}
                    l.debug("Act col: [{}]", actKey);
                    if (expKey.equals(actKey)) {
                        l.info("++++++++++++++++++++++++++++++ Pass -- Column exists");
                        matchFlag = true;
                        break;
                    }
                }
                if (!matchFlag) {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Column does not exist");
                    result &= false;
                }
            }
        } else {
            int exp_colNum = templateFields.split(",").length + 1;
            int act_colNum = actData.get(0).keySet().size();
            if (exp_colNum == act_colNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Exp col num: {}, act col num: {}", exp_colNum, act_colNum);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Exp col num: {}, act col num: {}", exp_colNum, act_colNum);
                result &= false;
            }
        }
        //转换数据
        ArrayList<HashMap<String, String>> actData_u = new ArrayList<HashMap<String, String>>(); //转存后的数据集合
        //遍历实际数据，替换其中的key，并转存为新的数据集合
        for (HashMap<String, String> patent : actData) {
            HashMap<String, String> patent_u = new HashMap<String, String>();    //转存后的单条专利数据
            //遍历每个key
            for (String key : patent.keySet()) {
                String key_u = "";    //新的key
                String value = patent.get(key);
//				if(key.contains("Publication  Number")){
//					//解析出的csv表头中的第一个，最前面会多一个空字符，这里去掉
//					key = key.substring(1, key.length());
//				}
                if (value.equals("-")) {
                    value = "";
                }
                switch (key) {
                    case "Publication  Number":
                        if (type != 3 && type != 4) {
                            key_u = "Publication Number";
                        }
                        break;
                    case "Cites Patent Number":
                        key_u = "Publication Number";
                        break;
                    case "Cited Patent Number":
                        key_u = "Publication Number";
                        break;
                    case "Inventor(s)":
                        key_u = "Inventor Name";
                        break;
                    case "Assignee(s)":
                        key_u = "Assignee Name";
                        break;
                    case "All IPC":
                        key_u = "International Classification";
                        break;
                    case "All UPC":
                        key_u = "US Classification";
                        break;
                    case "Agency/Law Firm":
                        key_u = "Agency";
                        break;
                    case "Agent/Attorney":
                        key_u = "Attorney Name";
                        break;
                    default:
                        key_u = key;
                }
                patent_u.put(key_u, value);
            }
            actData_u.add(patent_u);
        }
        l.info("========== Verify data in csv ==========");
        //2.遍历期望数据，逐个验证
        for (HashMap<String, String> expPatent : expData) {
            String expPN = expPatent.get("Publication Number");
            l.info("Exp PN: [{}]", expPN);
            //3.遍历实际数据，找到匹配数据
            boolean matchFlag = false; //标记是否找到匹配
            for (HashMap<String, String> actPatent : actData_u) {
                String actPN = actPatent.get("Publication Number");
                l.debug("Act PN: [{}]", actPN);
                if (expPN.equals(actPN)) {
                    //4.找到匹配，验证具体数据
                    l.info("Patent is found, so verify data");
                    //遍历期望专利的key，验证数据
                    for (String expKey : expPatent.keySet()) {
//					for(String expKey : expTitles){
                        String expValue = expPatent.get(expKey);
                        String actValue = actPatent.get(expKey);
                        if (actValue == null) {
                            l.debug("No such column in csv, so ignor");
                            continue;
                        }
                        //由于数据格式差异，正式比较之前，需要按一定规则转换实际数据
                        //b.转换时间格式
                        if (expKey.equals("Publication Date") || expKey.equals("Application Date")) {
                            SimpleDateFormat sdf_toDate = new SimpleDateFormat("yyyy-MM-dd");
                            Date d = sdf_toDate.parse(actValue);
                            SimpleDateFormat sdf_toString = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                            actValue = sdf_toString.format(d);
                        }
                        l.info("Verify column: [{}]", expKey);
                        l.info("Exp value: [{}]", expValue);
                        l.info("Act value: [{}]", actValue);
                        if (expValue.equals(actValue)) {
                            l.info("++++++++++++++++++++++++++++++ Pass -- Value is correct");
                        } else {
                            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Value is incorrect");
                            result &= false;
                        }
                    }
                    //5.验证后续处理
                    matchFlag = true;
                    break;
                }
            }
            //6.如果没找到，则报错
            if (!matchFlag) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- PN [{}] is not found in csv", expPN);
                result &= false;
            }
        }
        return result;
    }

    /**
     * 验证导出RTF的数据
     *
     * @param expData -- 专利查看页获取的数据
     * @param actData -- RTF中获取的数据
     * @param type    -- 字段类型 -- 0: Custom Fields; 1: Key Fields only; 2: Basic Fields only; 3: All Fields;  4: template
     * @return 是否验证通过
     * @throws ParseException
     */
    public boolean func_verify_RTFData(ArrayList<HashMap<String, String>> expData, ArrayList<HashMap<String, String>> actData, int type, String templateFields) throws ParseException {
        l.entry();
        l.info("============================== Verify data in RTF ==============================");
        boolean result = true;
        //1.输入检查
        if (expData == null || expData.size() == 0 || actData == null || actData.size() == 0) {
            l.error("Parameter is invalid");
            return false;
        }
        //根据导出类型，验证字段
        ArrayList<String> expTitles = new ArrayList<String>();    //保存期望列名
        switch (type) {
            case 0:
                expTitles.add("Publication  Number");
                break;
            case 1:
                expTitles.add("Publication  Number");
                expTitles.add("Title");
                expTitles.add("Application Date");
                expTitles.add("Publication Date");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                expTitles.add("All IPC");
                break;
            case 2:
                expTitles.add("Publication  Number");
                expTitles.add("Title");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                break;
            case 3:
                expTitles.add("Publication  Number");
                expTitles.add("Title");
                expTitles.add("Application Date");
                expTitles.add("Publication Date");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                expTitles.add("All IPC");
                expTitles.add("Abstract");
                expTitles.add("IPC Primary");
                expTitles.add("All UPC");
//			expTitles.add("Agency/Law Firm");
//			expTitles.add("Agent/Attorney");
                break;
        }
        if (expTitles.size() == 0 && type != 4) {
            l.error("Input field is invalid");
            return false;
        }
        //如果列值为空，RTF里不会存在该列名，所以无法验证列是否存在
//		//遍历期望列名列表，搜索匹配列
//		if(type != 4){
//			//不使用模板，才验证字段
//			l.info("========== Verify columns in RTF ==========");
//			for(String expKey : expTitles){
//				l.info("Exp col: [{}]", expKey);
//				boolean matchFlag = false;
//				//遍历实际数据，搜索匹配列
//				for(String actKey : actData.get(0).keySet()){
//					l.debug("Act col: [{}]", actKey);
//					if(expKey.equals(actKey)){
//						l.info("++++++++++++++++++++++++++++++ Pass -- Column exists");
//						matchFlag = true;
//						break;
//					}
//				}
//				if(!matchFlag){
//					l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Column does not exist");
//					result &= false;
//				}
//			}
//		}else{
//			int exp_colNum = templateFields.split(",").length + 1;
//			int act_colNum = actData.get(0).keySet().size();
//			if(exp_colNum == act_colNum){
//				l.info("++++++++++++++++++++++++++++++ Pass -- Exp col num: {}, act col num: {}", exp_colNum, act_colNum);
//			}else{
//				l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Exp col num: {}, act col num: {}", exp_colNum, act_colNum);
//				result &= false;
//			}
//		}
        //转换数据
        ArrayList<HashMap<String, String>> actData_u = new ArrayList<HashMap<String, String>>(); //转存后的数据集合
        //遍历实际数据，替换其中的key，并转存为新的数据集合
        for (HashMap<String, String> patent : actData) {
            HashMap<String, String> patent_u = new HashMap<String, String>();    //转存后的单条专利数据
            //遍历每个key
            for (String key : patent.keySet()) {
                String key_u = "";    //新的key
                String value = patent.get(key);
                if (value.equals("-")) {
                    value = "";
                }
                switch (key) {
                    case "Publication  Number":
                        key_u = "Publication Number";
                        break;
                    case "Cites Patent Number":
                        key_u = "Publication Number";
                        break;
                    case "Cited Patent Number":
                        key_u = "Publication Number";
                        break;
                    case "Inventor(s)":
                        key_u = "Inventor Name";
                        break;
                    case "Assignee(s)":
                        key_u = "Assignee Name";
                        break;
                    case "All IPC":
                        key_u = "International Classification";
                        break;
                    case "All UPC":
                        key_u = "US Classification";
                        break;
                    case "Agency/Law Firm":
                        key_u = "Agency";
                        break;
                    case "Agent/Attorney":
                        key_u = "Attorney Name";
                        break;
                    default:
                        key_u = key;
                }
                patent_u.put(key_u, value);
            }
            actData_u.add(patent_u);
        }
        l.info("========== Verify data in RTF ==========");
        //2.遍历期望数据，逐个验证
        for (HashMap<String, String> expPatent : expData) {
            String expPN = expPatent.get("Publication Number");
            l.info("Exp PN: [{}]", expPN);
            //3.遍历实际数据，找到匹配数据
            boolean matchFlag = false; //标记是否找到匹配
            for (HashMap<String, String> actPatent : actData_u) {
                String actPN = actPatent.get("Publication Number");
                l.debug("Act PN: [{}]", actPN);
                if (expPN.equals(actPN)) {
                    //4.找到匹配，验证具体数据
                    l.info("Patent is found, so verify data");
                    //遍历期望专利的key，验证数据
                    for (String expKey : expPatent.keySet()) {
//					for(String expKey : expTitles){
                        String expValue = expPatent.get(expKey);
                        String actValue = actPatent.get(expKey);
                        if (actValue == null) {
                            l.debug("No such column in excel, so ignor");
                            continue;
                        }
                        //由于数据格式差异，正式比较之前，需要按一定规则转换实际数据
                        //b.转换时间格式
                        if (expKey.equals("Publication Date") || expKey.equals("Application Date")) {
                            SimpleDateFormat sdf_toDate = new SimpleDateFormat("yyyy-MM-dd");
                            Date d = sdf_toDate.parse(actValue);
                            SimpleDateFormat sdf_toString = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                            actValue = sdf_toString.format(d);
                        }
                        l.info("Verify column: [{}]", expKey);
                        l.info("Exp value: [{}]", expValue);
                        l.info("Act value: [{}]", actValue);
                        if (expValue.equals(actValue)) {
                            l.info("++++++++++++++++++++++++++++++ Pass -- Value is correct");
                        } else {
                            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Value is incorrect");
                            result &= false;
                        }
                    }
                    //5.验证后续处理
                    matchFlag = true;
                    break;
                }
            }
            //6.如果没找到，则报错
            if (!matchFlag) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- PN [{}] is not found in rtf", expPN);
                result &= false;
            }
        }
        return result;
    }

    /**
     * 验证excel字段
     *
     * @param download_path
     * @param fullFileName
     * @param fieldId       -- 0:Key, 1:Basic, 4:All
     * @return
     */
    public boolean func_verify_excelColumns(String download_path, String fullFileName, int fieldId) {
        l.entry();
        boolean result = true;
        //期望列集合
        ArrayList<String> expFields = new ArrayList<String>(); //期望字段集合
        switch (fieldId) {
            case 0:
                expFields.add("Publication  Number");
                expFields.add("Title");
                expFields.add("Application Date");
                expFields.add("Publication Date");
                expFields.add("Inventor(s)");
                expFields.add("Assignee(s)");
                expFields.add("All IPC");
                break;
            case 1:
                expFields.add("Publication  Number");
                expFields.add("Title");
                expFields.add("Inventor(s)");
                expFields.add("Assignee(s)");
                break;
            case 4:
                expFields.add("Publication  Number");
                expFields.add("Application Number");
                expFields.add("Kind Code");
                expFields.add("Title");
                expFields.add("Abstract");
                expFields.add("The First Claim");
                expFields.add("Document Types");
                expFields.add("Application Date");
                expFields.add("Application Year");
                expFields.add("Application(Year/Month)");
                expFields.add("Publication Date");
                expFields.add("Publication Year");
                expFields.add("Publication(Year/Month)");
                expFields.add("All IPC");
                expFields.add("IPC Primary");
                expFields.add("IPC Section");
                expFields.add("IPC Class");
                expFields.add("IPC Subclass");
                expFields.add("IPC Group");
                expFields.add("All UPC");
                expFields.add("UPC Primary");
                expFields.add("UPC Class");
                expFields.add("FI");
                expFields.add("F-TERM");
                expFields.add("Family Members");
                expFields.add("Family Member Count");
                expFields.add("Family Members Cited By Count");
                expFields.add("INPADOC Family Members");
                expFields.add("INPADOC Family Member Count");
                expFields.add("INPADOC Family Members Cited By Count");
                expFields.add("Other References");
                expFields.add("Other References Count");
                expFields.add("Cited By");
                expFields.add("Cited By Count");
                expFields.add("Cites");
                expFields.add("Cites Count");
                expFields.add("Priority Country");
                expFields.add("Priority Number");
                expFields.add("Priority Date");
                expFields.add("Assignee(s)");
                expFields.add("Standardized Assignee");
                expFields.add("1st Assignee");
                expFields.add("Number of Assignees");
                expFields.add("1st Assignee Address");
                expFields.add("Assignee(s) Address");
                expFields.add("Inventor(s)");
                expFields.add("Standardized Inventor");
                expFields.add("1st Inventor");
                expFields.add("Number of Inventors");
                expFields.add("1st Inventor Address");
                expFields.add("Inventor(s) Address");
                expFields.add("Agency/Law Firm");
                expFields.add("Agent/Attorney");
                expFields.add("Primary Examiner");
                expFields.add("Assistant Examiner");
                expFields.add("Cited By Within 3 Years");
                expFields.add("Cited By Within 5 Years");
                break;
        }
        //输入检查
        if (expFields.size() == 0) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Can not get exp values");
            return false;
        }
        //实际列集合
        ExcelUtil excel = new ExcelUtil(download_path, fullFileName, false);
        ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) excel.readExcelData();

        //遍历期望列名列表，搜索匹配列
        l.info("========== Verify columns in excel ==========");
        for (String expKey : expFields) {
            l.info("Exp col: [{}]", expKey);
            boolean matchFlag = false;
            //遍历实际数据，搜索匹配列
            for (String actKey : actPatentData.get(0).keySet()) {
                l.debug("Act col: [{}]", actKey);
                if (expKey.equals(actKey)) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- Column exists");
                    matchFlag = true;
                    break;
                }
            }
            if (!matchFlag) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Column does not exist");
                result &= false;
            }
        }
        return result;
    }


    /**
     * 返回HTML页面数据
     *
     * @return
     */
    public String func_get_bodyText() {
        l.entry();
        return this.body_html().getText();
    }

    /**
     * 执行粘贴操作
     *
     * @throws AWTException
     */
    public void func_paste() throws AWTException {
        l.entry();
//		act = new Actions(d);
//		act.keyDown(Keys.CONTROL);
//		act.keyDown(Keys.valueOf("V"));
//		act.keyUp(Keys.getKeyFromUnicode('v'));
//		act.keyUp(Keys.CONTROL);
//		act.perform();

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        l.exit();
    }

    /**
     * 点击 邮件提醒 链接
     */
    public void func_goto_emailAlertPage() {
        l.entry();
        act = new Actions(d);
        act.moveToElement(this.link_userName());
        act.perform();
        this.link_emailAlert().click();
        l.exit();
    }

    /**
     * 点击已保存搜索链接
     */
    public void func_goto_savedSearchPage() {
        l.entry();
        act = new Actions(d);
        act.moveToElement(this.link_userName());
        act.perform();
        this.link_savedSearch().click();
        l.exit();
    }

    /**
     * 点击自建库链接
     */
    public void func_goto_customDatabasePage() {
        l.entry();
        act = new Actions(d);
        act.moveToElement(this.link_userName());
        act.perform();
        this.link_customDatabase().click();
        l.exit();
    }

    /**
     * 点击收藏夹链接
     *
     * @throws InterruptedException
     */
    public void func_goto_userListPage() throws InterruptedException {
        l.entry();
        act = new Actions(d);
        act.moveToElement(this.link_userName());
        act.perform();
        int i = 0;
        while (!this.link_userList().isDisplayed()) {
            if (i < 30) {
                Thread.sleep(1000);
                l.debug("Waiting for user list link...");
                i++;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Timeout");
                t.takeScreenshot(d);
                return;
            }
        }
        this.link_userList().click();
        l.exit();
    }

    public void func_goto_accountPage() throws InterruptedException {
        l.entry();
        act = new Actions(d);
        act.moveToElement(this.link_userName());
        act.perform();
        int i = 0;
        while (!this.link_userList().isDisplayed()) {
            if (i < 30) {
                Thread.sleep(1000);
                l.debug("Waiting for user list link...");
                i++;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Timeout");
                t.takeScreenshot(d);
                return;
            }
        }
        this.link_account().click();
        l.exit();
    }

    /**
     * 顶部搜索
     *
     * @param query
     */
    public void func_searchForQuery(String query) {
        l.entry();
        this.input_query().clear();
        this.input_query().sendKeys(query);
        this.link_search().click();
        l.exit();
    }

    /**
     * 点击搜索历史链接
     */
    public void func_goto_historyPage() {
        l.entry();
        act = new Actions(d);
        act.moveToElement(this.link_userName());
        act.perform();
        this.link_history().click();
        l.exit();
    }

    /**
     * 点击登出
     */
    public void func_click_logout() {
        l.entry();
        act = new Actions(d);
        act.moveToElement(this.link_userName());
        act.perform();
        this.link_logout().click();
        l.exit();
    }

    /**
     * 获取弹出提示信息
     *
     * @return
     */
    public String func_get_alertContent() {
        l.entry();
        return this.div_content().getText();
    }

    /**
     * 验证下拉框选项是否正确
     *
     * @param select  -- 下拉框元素
     * @param -- 期望选项集合
     * @return
     */
    public boolean func_verify_selectOptions(Select select, ArrayList<String> expOptions) {
        l.entry();
        boolean result = true;
        //前提条件检查
        if (expOptions.size() == 0 || expOptions == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Input exp option list is empty");
            return false;
        }
        List<WebElement> weOptions = new ArrayList<WebElement>();
        try {
            weOptions = select.getOptions();
        } catch (Exception e) {
            //元素获取异常
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Can not get select options");
            e.printStackTrace();
            return false;
        }
        if (weOptions.size() == 0) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Act option list is empty");
            return false;
        }
        //遍历期望选项集合，查找匹配的实际选项
        for (String expOption : expOptions) {
            boolean matchFlag = false;
            l.info("Exp option: [{}]", expOption);
            //遍历实际选项集合
            for (WebElement weOption : weOptions) {
                String actOption = weOption.getText();
                l.debug("Act option: [{}]", actOption);
                if (expOption.equals(actOption)) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- Option is correct");
                    matchFlag = true;
                    break;
                }
            }
            if (!matchFlag) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Option does not exist");
                t.takeScreenshot(d);
                result &= false;
            }
        }
        return result;
    }

    /**
     * 根据输入专利PN，获取引用、被引用专利PN列表
     *
     * @param pn        -- 主要专利PN
     * @param  -- 当前测试站点地址
     * @return 两个Key：cited -- 被引用专利PN列表；cites -- 引用专利PN列表
     * @throws IOException
     * @throws HttpException
     */
    public HashMap<String, ArrayList<String>> func_get_citationDetail(String pn, String currenUrl) throws HttpException, IOException {
        l.entry();
        HashMap<String, ArrayList<String>> returnData = new HashMap<String, ArrayList<String>>();
        //========== 拼接请求字符串 ==========
        String requestUrl = String.format("%s/patent/getCitationDetail?mode=root&pn=%s", currenUrl, pn);
        //========== 发送请求 ==========
        String res = "";
        //通过浏览器访问
        //获取当前地址
        String oldUrl = d.getCurrentUrl();
        d.get(requestUrl);
        res = this.func_get_bodyText();
        d.get(oldUrl);
//		//通过请求访问
//		HttpClient client = new HttpClient();
//		HttpMethod method = new GetMethod(requestUrl);
//		if(currenUrl.contains("www") || currenUrl.contains("cn")){
//			method.setRequestHeader("Cookie", "locale=en; __uvt=; __utma=154121145.263409467.1405048905.1405679635.1405929782.6; __utmz=154121145.1405048905.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); PATSNAP=64ddr3un381lnq60q93o2umgq0; Hm_lvt_08aef87c23bea8598ebeb45852ed812e=1406168274,1406169918,1406170003,1406255159; Hm_lpvt_08aef87c23bea8598ebeb45852ed812e=1406257397; dbmaps=all%3D1%26EP%3D1%26EPA%3D1%26EPB%3D1%26WO%3D1%26US%3D1%26USA%3D1%26USB%3D1%26USD%3D1%26CN%3D1%26CNA%3D1%26CNB%3D1%26CNU%3D1%26CND%3D1%26JP%3D1%26JPA%3D1%26JPB%3D1%26JPU%3D1%26GB%3D1%26FR%3D1%26DE%3D1%26DEA%3D1%26DEB%3D1%26DEU%3D1%26RU%3D1%26CH%3D1%26KR%3D1%26KRA%3D1%26KRB%3D1%26KRU%3D1%26AU%3D1%26CA%3D1%26IT%3D1%26TW%3D1%26TWA%3D1%26TWB%3D1%26TWU%3D1%26TWD%3D1%26DOCDB%3D1; email=hanqing%40zhihuiya.com; uvts=1q6CsbIXt22pNI86; visited=true");
//		}else if(currenUrl.contains("test")){
//			method.setRequestHeader("Cookie", "__uvt=; locale=en; __utma=154121145.263409467.1405048905.1405679635.1405929782.6; __utmz=154121145.1405048905.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); PATSNAP=uqjsdkhbe6drb8a2g9b2v358j4; Hm_lvt_08aef87c23bea8598ebeb45852ed812e=1406169918,1406170003,1406255159,1406260229; Hm_lpvt_08aef87c23bea8598ebeb45852ed812e=1406260243; dbmaps=all%3D1%26EP%3D1%26EPA%3D1%26EPB%3D1%26WO%3D1%26US%3D1%26USA%3D1%26USB%3D1%26USD%3D1%26CN%3D1%26CNA%3D1%26CNB%3D1%26CNU%3D1%26CND%3D1%26JP%3D1%26JPA%3D1%26JPB%3D1%26JPU%3D1%26GB%3D1%26FR%3D1%26DE%3D1%26DEA%3D1%26DEB%3D1%26DEU%3D1%26RU%3D1%26CH%3D1%26KR%3D1%26KRA%3D1%26KRB%3D1%26KRU%3D1%26AU%3D1%26CA%3D1%26IT%3D1%26TW%3D1%26TWA%3D1%26TWB%3D1%26TWU%3D1%26TWD%3D1%26DOCDB%3D1; email=hanqing%40zhihuiya.com; visited=true; uvts=1q6CsbIXt22pNI86");
//		}
//		client.executeMethod(method);
//		res = method.getResponseBodyAsString();

        //========== 解析返回Json ==========
        JSONObject json = new JSONObject(res);
        l.debug(res);
        //被引用列表
        JSONArray backward = json.getJSONArray("backward"); //被引用列表
        ArrayList<String> citedPNs = new ArrayList<String>();
        int citedNum = backward.length();
        for (int i = 0; i < citedNum; i++) {
            citedPNs.add(backward.getJSONObject(i).get("_id").toString());
        }
        returnData.put("cited", citedPNs);
        //引用列表
        JSONArray forward = json.getJSONArray("forward");    //引用列表
        ArrayList<String> citesPNs = new ArrayList<String>();
        int citesNum = forward.length();
        for (int i = 0; i < citesNum; i++) {
            citesPNs.add(forward.getJSONObject(i).get("_id").toString());
        }
        returnData.put("cites", citesPNs);
        //========== 返回数据 ==========
        return returnData;
    }

    /**
     * 从RTF数据获取专利数据
     *
     * @param pns
     * @param content
     * @return
     */
    public ArrayList<HashMap<String, String>> func_getPatentDataFromRTF(ArrayList<String> pns, String content) {
        l.entry();
        ArrayList<HashMap<String, String>> patentsData = new ArrayList<HashMap<String, String>>();
        //===== 处理RTF文本 =====
        //获取各个专利的文本信息
        String[] patentSections = content.split("Publication  Number:");
        for (int i = 1; i < patentSections.length; i++) {
            HashMap<String, String> patentData = new HashMap<String, String>();
            l.debug("Publication  Number: {}", pns.get(i - 1));
            patentData.put("Publication  Number", pns.get(i - 1));
            //单个专利的文本信息
            String patentSection = patentSections[i];
            l.debug("Patent section: {}", patentSection);
            //获取单个专利的各个字段信息
            String[] patentAttributes = patentSection.split("\n");
            for (int j = 1; j < patentAttributes.length; j++) {
                String patentAttribute = patentAttributes[j];
                l.debug("Patent attribute: {}", patentAttribute);
                //获取专利属性的key和value
                String key = "";
                String value = "";
                if (patentAttribute.startsWith("Abstract: ")) {
                    //先判断是否需要处理处理value包含冒号的文本
                    key = "Abstract";
                    value = patentAttribute.split("Abstract: ")[1];
                } else {
                    //一般处理方式
                    String[] subStrings = patentAttribute.split(": ");
                    key = subStrings[0];
                    value = subStrings[1];
                }
                l.debug("Key -- {}", key);
                l.debug("Value -- {}", value);
                patentData.put(key, value);
            }
            patentsData.add(patentData);
        }
        return patentsData;
    }

    /**
     * 验证导出xml的数据
     *
     * @param expData -- 专利查看页获取的数据
     * @param actData -- Excel中获取的数据
     * @param type    -- 字段类型 -- 0: Custom Fields; 1: Key Fields only; 2: Basic Fields only; 3: Cites; 4: Cited By; 5: All Fields;  6: template
     * @return 是否验证通过
     * @throws ParseException
     */
    public boolean func_verify_xmlData(ArrayList<HashMap<String, String>> expData, ArrayList<HashMap<String, String>> actData, int type, String templateFields) throws ParseException {
        l.entry();
        l.info("============================== Verify data in xml ==============================");
        boolean result = true;
        //1.输入检查
        if (expData == null || expData.size() == 0 || actData == null || actData.size() == 0) {
            l.error("Parameter is invalid");
            return false;
        }
        //根据导出类型，验证字段
        ArrayList<String> expTitles = new ArrayList<String>();    //保存期望列名
        switch (type) {
            case 0:
                expTitles.add("Publication  Number");
                break;
            case 1:
                expTitles.add("Publication  Number");
                expTitles.add("Title");
                expTitles.add("Application Date");
                expTitles.add("Publication Date");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                expTitles.add("All IPC");
                break;
            case 2:
                expTitles.add("Publication  Number");
                expTitles.add("Title");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                break;
            case 3:
                expTitles.add("Publication  Number");
                expTitles.add("Title");
                expTitles.add("Application Date");
                expTitles.add("Publication Date");
                expTitles.add("Inventor(s)");
                expTitles.add("Assignee(s)");
                expTitles.add("All IPC");
                expTitles.add("Abstract");
                expTitles.add("IPC Primary");
                expTitles.add("All UPC");
                expTitles.add("Agency/Law Firm");
                expTitles.add("Agent/Attorney");
                break;
        }
        if (expTitles.size() == 0 && type != 4) {
            l.error("Input field is invalid");
            return false;
        }
        //不验证每个列是否存在，因为当某个列没有数据时，遍不显示该节点
//		//遍历期望列名列表，搜索匹配列
//		if(type != 4){
//			//不使用模板，才验证字段
//			l.info("========== Verify columns in xml ==========");
//			for(String expKey : expTitles){
//				l.info("Exp col: [{}]", expKey);
//				boolean matchFlag = false;
//				//遍历实际数据，搜索匹配列
//				for(String actKey : actData.get(0).keySet()){
//					l.debug("Act col: [{}]", actKey);
//					if(expKey.equals(actKey)){
//						l.info("++++++++++++++++++++++++++++++ Pass -- Column exists");
//						matchFlag = true;
//						break;
//					}
//				}
//				if(!matchFlag){
//					l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Column does not exist");
//					result &= false;
//				}
//			}
//		}else{
//			int exp_colNum = templateFields.split(",").length + 1;
//			int act_colNum = actData.get(0).keySet().size();
//			if(exp_colNum == act_colNum){
//				l.info("++++++++++++++++++++++++++++++ Pass -- Exp col num: {}, act col num: {}", exp_colNum, act_colNum);
//			}else{
//				l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Exp col num: {}, act col num: {}", exp_colNum, act_colNum);
//				result &= false;
//			}
//		}
		//转换数据
		ArrayList<HashMap<String, String>> actData_u = new ArrayList<HashMap<String, String>>(); //转存后的数据集合
		//遍历实际数据，替换其中的key，并转存为新的数据集合
		for(HashMap<String, String> patent : actData){
			HashMap<String, String> patent_u = new HashMap<String, String>();	//转存后的单条专利数据
			//遍历每个key
			for(String key : patent.keySet()){
				String key_u = "";	//新的key
				String value = patent.get(key);
				if(value.equals("-")){
					value = "";
				}
				switch (key){
				case "Publication  Number":
					key_u = "Publication Number";
					break;
				case "Cites Patent Number":
					key_u = "Publication Number";
					break;
				case "Cited Patent Number":
					key_u = "Publication Number";
					break;
				case "Inventor(s)":
					key_u = "Inventor Name";
					break;
				case "Assignee(s)":
					key_u = "Assignee Name";
					break;
				case "All IPC":
					key_u = "International Classification";
					break;
				case "All UPC":
					key_u = "US Classification";
					break;
				case "Agency/Law Firm":
					key_u = "Agency";
					break;
				case "Agent/Attorney":
					key_u = "Attorney Name";
					break;
				default:
					key_u = key;
				}
				patent_u.put(key_u, value);
			}
			actData_u.add(patent_u);
		}
		l.info("========== Verify data in xml ==========");
		//2.遍历期望数据，逐个验证
		for(HashMap<String, String> expPatent : expData){
			String expPN = expPatent.get("Publication Number");
			l.info("Exp PN: [{}]", expPN);
			//3.遍历实际数据，找到匹配数据
			boolean matchFlag = false; //标记是否找到匹配
			for(HashMap<String, String> actPatent : actData_u){
				String actPN = actPatent.get("Publication Number");
				l.debug("Act PN: [{}]", actPN);
				if(expPN.equals(actPN)){
					//4.找到匹配，验证具体数据
					l.info("Patent is found, so verify data");
					//遍历期望专利的key，验证数据
					for(String expKey : expPatent.keySet()){
						String expValue = expPatent.get(expKey);
						String actValue = actPatent.get(expKey);
						if(actValue == null){
							l.debug("No such column in xml, so ignor");
							continue;
						}
						//由于数据格式差异，正式比较之前，需要按一定规则转换实际数据
						//b.转换时间格式
						if(expKey.equals("Publication Date") || expKey.equals("Application Date")){
							SimpleDateFormat sdf_toDate = new SimpleDateFormat("yyyy-MM-dd");
							Date d = sdf_toDate.parse(actValue);
							SimpleDateFormat sdf_toString = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
							actValue =  sdf_toString.format(d);
						}
						l.info("Verify column: [{}]", expKey);
						l.info("Exp value: [{}]", expValue);
						l.info("Act value: [{}]", actValue);
						if(expValue.equals(actValue)){
							l.info("++++++++++++++++++++++++++++++ Pass -- Value is correct");
						}else{
							l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Value is incorrect");
							result &= false;
						}
					}
					//5.验证后续处理
					matchFlag = true;
					break;
				}
			}
			//6.如果没找到，则报错
			if(!matchFlag){
				l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- PN [{}] is not found in xml", expPN);
				result &= false;
			}
		}
		return result;
	}
	
	/**
	 * 验证页面顶部提示信息是否和期望一致
	 * @param exp_content
	 * @return
	 */
	public boolean func_verify_alertContent(String exp_content){
		l.entry();
		boolean result = true;
		String act_content = this.func_get_alertContent();
		l.info("Exp alert content: [{}]", exp_content);
		l.info("Act alert content: [{}]", act_content);
		if(exp_content.equals(act_content)){
			l.info("++++++++++++++++++++++++++++++ Pass -- Alert content is correct");
		}else{
			l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Alert content is incorrect");
			t.takeScreenshot(d);
			result = false;
		}
		return result;
	}
	
	/**
	 * 获取引用关系excel中的原原专利数量
	 * @param actPatentData
	 * @return
	 */
	public int func_get_patentNumberOfCitation(ArrayList<HashMap<String, String>> actPatentData){
		l.entry();
		int result = 0;
		for(HashMap<String, String> row : actPatentData){
			if((row.get("Publication  Number") != null) && (!row.get("Publication  Number").equals(""))){
				result++;
			}
		}
		return result;
	}
	
	/**
	 * 切换语言
	 * @param type -- 0:cn, 1:tw, 2:en
	 */
	public void func_switch_language(int type){
		l.entry();
		Actions act = new Actions(d);
		act.moveToElement(this.div_languageSelect()).perform();
		this.ul_languageList().findElements(By.tagName("a")).get(type).click();
		l.exit();
	}

	public String func_getQuery(){
		l.entry();
		return this.div_query().getAttribute("value");
	}
	
}
