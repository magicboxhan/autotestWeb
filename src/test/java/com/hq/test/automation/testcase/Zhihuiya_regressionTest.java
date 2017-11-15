package com.hq.test.automation.testcase;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.hq.test.automation.pageobject.*;
import com.hq.test.framework.page.BasePage;
import com.hq.test.framework.testcase.BaseTestcase;
import com.hq.test.framework.util.ExcelUtil;
import com.hq.test.framework.util.FileUtil;

import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Zhihuiya_regressionTest extends BaseTestcase {

    BasePage p_basePage = new BasePage();
    Zhihuiya_loginPage p_loginPage;
    Zhihuiya_searchPage p_searchPage;
    Zhihuiya_searchResultPage p_searchResultPage;
    Zhihuiya_landscapePage p_landScapePage;
    Zhihuiya_emailAlertPage p_emailAlertPage;
    Zhihuiya_exportPage p_exportPage;
    Zhihuiya_exportCompletePage p_exportCompletePage;
    Zhihuiya_patentViewPage p_patentViewPage;
    Zhihuiya_savedSearchPage p_savedSearchPage;
    Zhihuiya_customDatabasePage p_customDatabasePage;
    Zhihuiya_userListPage p_userListPage;
    Zhihuiya_historyPage p_historyPage;
    Zhihuiya_matrixAnalysisPage p_matrixAnalysisPage;
    Zhihuiya_viewChartPage p_viewChartPage;
    Zhihuiya_accountPage p_accountPage;
    Wangyi_loginPage pwy_loginPage;
    Wangyi_mainPage pwy_mainPage;


    //======================================================== Search Result ===================================================

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchResultPage_displayedFields",
            "searchResultPage_defaultView"
    })
    @Test
    public void searchResult_1(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String searchResultPage_displayedFields,
            String searchResultPage_defaultView
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            //login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //search
            //获取期望query
            String exp_query = p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            //获取已勾选数据库
            List<String> expCheckedId = p_searchPage.func_get_checkedDatabaseList();
            //search result
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            //==========初始化搜索字符串==========
            String an_s = null; //不带搜索条件的关键字
            String an_s_refine = null; //待搜索条件的关键字
            String act_query = p_searchResultPage.func_get_queryText(); //当前搜索
            //===Query Display
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Query Display ==============================");
            l.info("Expected query is {}", exp_query);
            l.info("Actual query is {}", act_query);
            if (exp_query.equals(act_query)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- query is correct");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- query is incorrect");
                t.takeScreenshot(d);
                result &= false;
            }
            //===Checked databases Display
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Checked databases Display ==============================");
            result &= p_searchResultPage.func_verify_checkedDatabaseList(expCheckedId);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing setting displayed fields ==============================");
            result &= p_searchResultPage.func_config_displayedFields(searchResultPage_displayedFields);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing search preferences fields (table view) ==============================");
            p_searchResultPage.func_click_view_byRel("tablelist");
            result &= p_searchResultPage.func_verify_searchPreferences("tablelist");
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing search preferences fields (standard view) ==============================");
            p_searchResultPage.func_click_view_byRel("standard");
            result &= p_searchResultPage.func_verify_searchPreferences("standard");
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing search preferences fields (thumbnail view) ==============================");
            p_searchResultPage.func_click_view_byRel("thumb");
            result &= p_searchResultPage.func_verify_searchPreferences("thumb");
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing search preferences fields (analysis view) ==============================");
            p_searchResultPage.func_click_view_byRel("analysis");
            result &= p_searchResultPage.func_verify_searchPreferences("analysis");
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing search preferences fields (flipit view) ==============================");
            p_searchResultPage.func_click_view_byRel("flipit");
            result &= p_searchResultPage.func_verify_searchPreferences("flipit");
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing default view ==============================");
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_config_defaultDisplay(searchResultPage_defaultView);
            d.get(loginPage_url);
            p_searchResultPage.func_searchForQuery(act_query);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            result &= p_searchResultPage.func_verify_viewIsSelected(searchResultPage_defaultView);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing patent amount on table view ==============================");
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_config_defaultDisplay("tablelist");
            p_searchResultPage.func_config_resultDisplay(0);
            result &= p_searchResultPage.func_verify_searchResultNum();
            p_searchResultPage.func_config_resultDisplay(1);
            result &= p_searchResultPage.func_verify_searchResultNum();
            p_searchResultPage.func_config_resultDisplay(2);
            result &= p_searchResultPage.func_verify_searchResultNum();
            p_searchResultPage.func_config_resultDisplay(3);
            result &= p_searchResultPage.func_verify_searchResultNum();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing patent amount on last page ==============================");
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_config_resultDisplay(0);
            int numPerPage = p_searchResultPage.func_get_numberPerPage();
            int expTotalNum = p_searchResultPage.func_get_searchResultNumber();
            int totalPage = p_searchResultPage.func_get_totalPageNum();
            l.info("Number per page: [{}]", numPerPage);
            //点击末页
            p_searchResultPage.func_click_theLastPageNum();
            Thread.sleep(5000);
            //获取当前页专利数量
            int numOfTheLastPage = p_searchResultPage.func_get_patentListNum_tableView();
            l.info("Number of the last page: [{}]", numOfTheLastPage);
            //验证结果
            int actTotelNum = numPerPage * (totalPage - 1) + numOfTheLastPage;
            l.info("Expected total number: [{}]", expTotalNum);
            l.info("Actual total number: [{}]", actTotelNum);
            if (expTotalNum == actTotelNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- patent number on last page is correct");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- patent number on last page is incorrect");
                t.takeScreenshot(d);
                result &= false;
            }
            //========== 切换到第二页 ==========
            //切换到表格视图
            p_searchResultPage.func_click_view_byRel("tablelist");
            //切换页码
            p_searchResultPage.func_click_pageNum(1);
            Thread.sleep(2000);
            p_searchResultPage.func_click_pageNum(2);
            //====================
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing analysis view ==============================");
            p_searchResultPage.func_click_view_byRel("analysis");
            result &= p_searchResultPage.func_verify_searchResultExist_analyzeView();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing flipit view ==============================");
            p_searchResultPage.func_click_view_byRel("flipit");
            //验证是否全屏
            Thread.sleep(2000);
            result &= p_searchResultPage.func_verify_fullScreen_flipitView();
            //验证列表
            //点击专利
            p_searchResultPage.func_click_firstPatent_flipitView();
            //下翻
            l.info("Scroll to bottom");
            int oldNum = p_searchResultPage.func_get_patentListNum_flipitView();
            p_searchResultPage.func_scrollToBottem_flipitView();
            Thread.sleep(5000);
            int newNum = p_searchResultPage.func_get_patentListNum_flipitView();
            //验证列表数量增长
            l.info("Patent list number (old): [{}]", oldNum);
            l.info("Patent list number (new): [{}]", newNum);
            if (newNum > oldNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is increased");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail --  Patent number is not increased");
                t.takeScreenshot(d);
                result &= false;
            }
            oldNum = newNum;
            //上翻
            l.info("Scroll to top");
            p_searchResultPage.func_scrollToTop_flipitView();
            Thread.sleep(2000);
            //验证列表数量增长
            newNum = p_searchResultPage.func_get_patentListNum_flipitView();
            l.info("Patent list number (old): [{}]", oldNum);
            l.info("Patent list number (new): [{}]", newNum);
            if (newNum > oldNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is increased");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail --  Patent number is not increased");
                t.takeScreenshot(d);
                result &= false;
            }
            //===Filter Function (Filter Option)
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Filter Function (Filter Option) ==============================");
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_config_defaultDisplay("tablelist");
            //记录过滤前数量
            int exp_resultNum = p_searchResultPage.func_get_searchResultNumber();
            //通过点击过滤条件过滤
            an_s = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(0);
            l.info("Filtercondition: Assignee Name; value: {}", an_s);
            //拼接过滤条件
            an_s_refine = String.format("AN_S:\"%s\"", an_s);
            l.info("Refine query: {}", an_s_refine);
            p_searchResultPage.func_click_refineLink();
            //验证过滤结果
            result &= p_searchResultPage.func_verify_assigneeNameExistInDataTable(an_s);
            //===Clear (Filter Edit Box)
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Clear (Filter Option) ==============================");
            //清除过滤条件
            p_searchResultPage.func_click_clearRefineLink();
            p_searchResultPage.func_click_refineLink();
            //填写过滤条件
            p_searchResultPage.func_input_refineQuery(an_s_refine);
            int act_resultNum = p_searchResultPage.func_get_searchResultNumber();
            l.info("Expected search result number: {}", exp_resultNum);
            l.info("Actual search result number: {}", act_resultNum);
            if (act_resultNum == exp_resultNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Search result is correct after clearing query");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search result is incorrect after clearing query");
                t.takeScreenshot(d);
                result &= false;
            }
            //===Filter Function (Filter Edit Box)
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Filter Function (Filter Edit Box) ==============================");
            //通过输入过滤条件过滤
            //先清空过滤结果
            p_searchResultPage.func_click_clearRefineLink();
            p_searchResultPage.func_click_refineLink();
            //再过滤
            l.info("Filtercondition: Assignee Name; value: {}", an_s);
            //拼接过滤条件
            l.info("Refine query: {}", an_s_refine);
            //填写过滤条件
            p_searchResultPage.func_input_refineQuery(an_s_refine);
            p_searchResultPage.func_click_refineLink();
            //验证过滤结果
            result &= p_searchResultPage.func_verify_assigneeNameExistInDataTable(an_s);
            //===Logical Word in one field
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Logical Word in one field ==============================");
            //初始化搜索
            p_searchResultPage.func_searchForQuery(act_query);
            //点击过滤条件
            an_s = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(0);
            String an_s_2 = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(1);
            l.info("Filtercondition: Assignee Name; value 1: {}, value 2: {}", an_s, an_s_2);
            //拼接过滤条件
            an_s_refine = String.format("AN_S:\"%s\" or AN_S:\"%s\"", an_s, an_s_2);
            l.info("Refine query: {}", an_s_refine);
            p_searchResultPage.func_click_refineLink();
            //验证过滤结果
            result &= p_searchResultPage.func_verify_assigneeNameExistInDataTable(an_s);
            result &= p_searchResultPage.func_verify_assigneeNameExistInDataTable(an_s_2);
            //清空结果
            p_searchResultPage.func_click_clearRefineLink();
            p_searchResultPage.func_click_refineLink();
            // Testing Logical Word in mutiple fields
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Logical Word in mutiple fields ==============================");
            //初始化搜索
            p_searchResultPage.func_searchForQuery(act_query);
            //点击过滤条件
            an_s = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(0);
            String apd_year = p_searchResultPage.func_click_filtercondition_APD_YEAR_byIndex(4);
            l.info("Filtercondition: Assignee Name: {}, Application Year: {}", an_s, apd_year);
            //拼接过滤条件
            an_s_refine = String.format("AN_S:\"%s\" or APD_YEAR:\"%s\"", an_s, apd_year);
            l.info("Refine query: {}", an_s_refine);
            p_searchResultPage.func_click_refineLink();
            //验证过滤结果
            result &= p_searchResultPage.func_verify_assigneeNameExistInDataTable(an_s);
            result &= p_searchResultPage.func_verify_applicationYearExistInDataTable(apd_year);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing paste ==============================");
            //初始化搜索
            p_searchResultPage.func_searchForQuery(act_query);
            //输入并记录过滤条件
            an_s = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(0);
            an_s_refine = String.format("AN_S:\"%s\"", an_s);
            p_searchResultPage.func_input_refineQuery(an_s_refine);
            l.info("Query: [{}]", act_query);
            l.info("Condition in edit box: [{}]", an_s_refine);
            l.info("Condition: [{}]", an_s);
            Thread.sleep(5000);
            p_searchResultPage.func_click_copyLink();
            p_searchResultPage.func_paste_queryEditbox();
            //===Most Relevant
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Most Relevant ==============================");
            p_searchResultPage.func_select_sortType_byIndex(0);
            result &= p_searchResultPage.func_verify_searchResultExist_tableView();
            //===Latest Application
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Latest Application ==============================");
            p_searchResultPage.func_select_sortType_byIndex(1);
            result &= p_searchResultPage.func_verify_patentListOrder(0, 0, 10);
            //===Oldest Application
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Oldest Application ==============================");
            p_searchResultPage.func_select_sortType_byIndex(2);
            result &= p_searchResultPage.func_verify_patentListOrder(0, 1, 10);
            //===Latest Publication
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Latest Publication ==============================");
            p_searchResultPage.func_select_sortType_byIndex(3);
            result &= p_searchResultPage.func_verify_patentListOrder(1, 0, 10);
            //===Oldest Publication
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Oldest Publication ==============================");
            p_searchResultPage.func_select_sortType_byIndex(4);
            result &= p_searchResultPage.func_verify_patentListOrder(1, 1, 10);
            //assert
            Thread.sleep(5000);
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchResultPage_userListFolderLevel"
    })
    @Test
    public void searchResult_2(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int searchResultPage_userListFolderLevel
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            //login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            //search result
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            //===Query Display
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing add to list (table view) ==============================");
            // 获取第一行PN
            String pn = p_searchResultPage.func_get_patentNumber_ByIndex(1);
            // 点击收藏
            p_searchResultPage.func_click_addToListLink_ByIndex_tableView(1);
            // 添加到收藏夹
            Thread.sleep(2000);
            ArrayList<String> folderNames = new ArrayList<String>();
            for (int i = 0; i < searchResultPage_userListFolderLevel; i++) {
                String folderName = String.valueOf(System.currentTimeMillis());
                folderNames.add(folderName);
                Thread.sleep(10);
            }
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            // 打开收藏页
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //点击全部展开
//			Thread.sleep(5000);
            p_userListPage.func_click_expandAll();
            //验证文件夹
            Assert.assertEquals(p_userListPage.func_verify_userListFolderExists(folderNames), true);
            //验证专利
            p_userListPage.func_click_userListFolder(folderNames.get(folderNames.size() - 1));
            Thread.sleep(10000);
            result &= p_userListPage.func_verify_patentExistInUserList(pn);
            p_userListPage.func_delete_userListFolder(folderNames.get(0));
            //assert
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchResultPage_displayedFields",
            "searchResultPage_expLegalStatus"
    })
    @Test
    public void searchResult_3(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String searchResultPage_displayedFields,
            String expLegalStatus
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            //login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            //search result
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing legal status (standard view) ==============================");
            p_searchResultPage.func_click_view_byRel("standard");
            p_searchResultPage.func_config_displayedFields(searchResultPage_displayedFields);
            result &= p_searchResultPage.func_verify_legalStatusByPN(expLegalStatus, searchPage_keyword);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing H key (thumbnail view) ==============================");
            p_searchResultPage.func_click_view_byRel("thumb");
            result &= p_searchResultPage.func_verify_H_key_thumbnailView();
            //assert
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchResultPage_displayedFields",
            "searchResultPage_displayedFields2"
    })
    @Test
    public void searchResult_4(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String searchResultPage_displayedFields,
            String searchResultPage_displayedFields2
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            //login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //search
            String query = p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            //search result
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks (group 1) (table view) ==============================");
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_config_displayedFields(searchResultPage_displayedFields);
            String expValue = null; //链接文字
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of PN ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("PN");
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            result &= p_patentViewPage.func_verify_valueExistInPatentInfoTable("PN", expValue);
            p_patentViewPage.func_exit_tip();
            p_patentViewPage.func_searchForQuery(query);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of TITLE ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("TITLE");
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            result &= p_patentViewPage.func_verify_valueExistInPatentInfoTable("TITLE", expValue);
            p_patentViewPage.func_exit_tip();
            p_patentViewPage.func_searchForQuery(query);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of APN ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("APN");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("APN", expValue);
            p_searchResultPage.func_searchForQuery(query);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of AN ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("AN");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("AN", expValue);
            p_searchResultPage.func_searchForQuery(query);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of AN_ADD ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("AN_ADD");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("AN_ADD", expValue);
            p_searchResultPage.func_searchForQuery(query);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of AN_ST ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("AN_ST");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("AN_ST", expValue);
            p_searchResultPage.func_searchForQuery(query);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of IN ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("IN");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("IN", expValue);
            p_searchResultPage.func_searchForQuery(query);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks (group 2) (table view) ==============================");
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_config_displayedFields(searchResultPage_displayedFields2);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of IN_ST ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("IN_ST");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("IN_ST", expValue);
            p_searchResultPage.func_searchForQuery(query);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of AT ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("AT");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("AT", expValue);
            p_searchResultPage.func_searchForQuery(query);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of ATC ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("ATC");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("ATC", expValue);
            p_searchResultPage.func_searchForQuery(query);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of IPC ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("IPC");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("IPC", expValue);
            p_searchResultPage.func_searchForQuery("LOC:(09)");
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of LOC ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("LOC");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("LOC", expValue);
            p_searchResultPage.func_searchForQuery(query);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of UPC ==============================");
            expValue = p_searchResultPage.func_click_availableLink_patentList("UPC");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("UPC", expValue);
            p_searchResultPage.func_searchForQuery(query);
            //assert
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchResultPage_displayedFields"
    })
    @Test
    public void searchResult_5(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String searchResultPage_displayedFields
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            //login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            //search result
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing English Stemming (on) ==============================");
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_config_displayedFields(searchResultPage_displayedFields);
            //打开
            p_searchResultPage.func_config_englishStemming(1);
            //验证Title包含Testing
            result &= p_searchResultPage.func_verify_valueExistInDataTable("title", "testing");
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing English Stemming (off) ==============================");
            //关闭
            p_searchResultPage.func_config_englishStemming(0);
            //验证Title不包含Testing
            result &= p_searchResultPage.func_verify_valueNotExistInDataTable("title", "testing");
            //assert
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchQuery",
            "queryName"
    })
    @Test
    public void searchResult_6(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchQuery,
            String queryName
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            //login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing save query ==============================");
            //删除现有搜索
            p_searchPage.func_goto_savedSearchPage();
            p_savedSearchPage = new Zhihuiya_savedSearchPage(d);
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            p_savedSearchPage.func_delete_savedSearch(queryName);
            //创建新搜索
            p_savedSearchPage.func_searchForQuery(searchQuery);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //保存语句
            p_searchResultPage.func_click_saveQuery();
            HashMap<String, String> savedSearchResult = p_searchResultPage.func_save_query(queryName);
            String fullQueryName = savedSearchResult.get("name");
            String queryString = savedSearchResult.get("string");
            //验证已保存语句
            Thread.sleep(5000);
            p_searchResultPage.func_goto_savedSearchPage();
            p_savedSearchPage = new Zhihuiya_savedSearchPage(d);
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            result &= p_savedSearchPage.func_verify_savedSearch(fullQueryName, queryString);
            //assert
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }

//	@Parameters({ 
//		"loginPage_url",
//		"loginPage_uid",
//		"loginPage_pwd",
//		"searchQuery",
//		"emailAlert_title",
//		"emailAlert_status",
//		"emailAlert_frequency",
//		"emailAlert_expirationDate",
//		"emailAlert_content"
//		})
//	@Test
//	public void searchResult_7(
//			String loginPage_url,
//			String loginPage_uid,
//			String loginPage_pwd,
//			String searchQuery,
//			String emailAlert_title,
//			String emailAlert_status,
//			String emailAlert_frequency,
//			String emailAlert_expirationDate,
//			String emailAlert_content
//			) throws IOException {
//		try {
//			l.entry();
//			boolean result = true;
//			//login
//			d.get(loginPage_url);
//			p_loginPage = new Zhihuiya_loginPage(d);
//			p_loginPage.func_login(loginPage_uid, loginPage_pwd);
//			p_searchPage = new Zhihuiya_searchPage(d);
//			Assert.assertEquals(p_searchPage.selfcheck(), true);
//			l.info("");
//			l.info("");
//			l.info("");
//			l.info("============================== Testing create email alert ==============================");
//			//搜索
//			p_searchPage.func_searchForQuery(searchQuery);
//			p_searchResultPage = new Zhihuiya_searchResultPage(d);
//			Assert.assertEquals(p_searchResultPage.selfcheck(), true);
//			p_searchResultPage.func_click_view_byRel("tablelist");
//			//保存邮件提醒
//			p_searchResultPage.func_click_emailAlert();
//			HashMap<String, String> emailAlertHash = p_searchResultPage.func_create_emailAlert_complex(emailAlert_title, emailAlert_status, emailAlert_frequency, emailAlert_expirationDate, emailAlert_content);
//			//验证邮件提醒
//			Thread.sleep(5000);
//			p_searchResultPage.func_goto_emailAlertPage();
//			p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
//			Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
//			result &= p_emailAlertPage.func_verify_emailAlert_complex(emailAlertHash);
//			//删除邮件提醒
//			p_emailAlertPage.func_delete_emailAlert(emailAlertHash.get("title"));
//			//assert
//			Assert.assertEquals(result, true);
//			l.exit();
//		} catch (TimeoutException e) {
//			l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
//			Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
//			Assert.assertEquals(false, true);
//		} catch (Exception e) {
//			l.error("Error!");
//			e.printStackTrace();
//			p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
//			Assert.assertEquals(false, true);
//		}
//	}

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword_root",
            "searchPage_keyword_leaf",
            "cdb_name",
            "cdb_queryTitle_root",
            "cdb_queryTitle_leaf"
    })
    @Test
    public void searchResult_8(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword_root,
            String searchPage_keyword_leaf,
            String cdb_name,
            String cdb_queryTitle_root,
            String cdb_queryTitle_leaf
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing create custom database (root node) ==============================");
            //login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入自建库列表页
            p_searchPage.func_goto_customDatabasePage();
            //删除自建库
            p_customDatabasePage = new Zhihuiya_customDatabasePage(d);
            p_customDatabasePage.func_delete_CDB_byName(cdb_name, loginPage_pwd);
            //搜索
            d.get(loginPage_url);
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword_root, null);
//			p_searchPage.func_searchForQuery(searchQuery_root); //这样搜会有问题，添加自建库的时候，document_type不出现
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //获取搜索结果数
            int exp_resultNum = p_searchResultPage.func_get_searchResultNumber();
            //添加到自建库，需新建自建库，并将搜索加入到根目录
            p_searchResultPage.func_click_addToCDB();
            Thread.sleep(5000);
            p_searchResultPage.func_addToCDB(cdb_queryTitle_root, cdb_name, "Root Directory");
            l.info("Expected CDB name: [{}]", cdb_name);
            Thread.sleep(2000); //等待数据同步
            //进入自建库列表页
            p_searchResultPage.func_goto_customDatabasePage();
            //点击自建库管理
            Assert.assertEquals(p_customDatabasePage.selfcheck(), true);
            Assert.assertEquals(p_customDatabasePage.func_verify_customDatabase(cdb_name), true);
            p_customDatabasePage.func_click_manage_byCDBName(cdb_name);
            //验证根目录存在，并点击
            Assert.assertEquals(p_customDatabasePage.func_verify_query_and_click(cdb_queryTitle_root), true);
            Thread.sleep(2000);
            //验证数据量正确，同搜索结果页数量
            int act_resultNum = p_customDatabasePage.func_get_searchResultNumber();
            l.info("Expected total result is [{}]", exp_resultNum);
            l.info("Actual total result is [{}]", act_resultNum);
            if (exp_resultNum == act_resultNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Total result is correct");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Total result is incorrect");
                t.takeScreenshot(d);
                result = false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing create custom database (leaf node) ==============================");
            //login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword_leaf, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //获取搜索结果数
            exp_resultNum = p_searchResultPage.func_get_searchResultNumber();
            //添加到自建库，需新建自建库，并将搜索加入到根目录
            p_searchResultPage.func_click_addToCDB();
            Thread.sleep(2000);
            p_searchResultPage.func_addToCDB(cdb_queryTitle_leaf, cdb_name, cdb_queryTitle_root);
            l.info("Expected CDB name: [{}]", cdb_name);
            Thread.sleep(5000); //等待数据同步
            //进入自建库列表页
            p_searchResultPage.func_goto_customDatabasePage();
            //点击自建库管理
            p_customDatabasePage = new Zhihuiya_customDatabasePage(d);
            Assert.assertEquals(p_customDatabasePage.selfcheck(), true);
            Assert.assertEquals(p_customDatabasePage.func_verify_customDatabase(cdb_name), true);
            p_customDatabasePage.func_click_manage_byCDBName(cdb_name);
            //验证根目录存在，并点击
            Assert.assertEquals(p_customDatabasePage.func_verify_query_and_click(cdb_queryTitle_leaf), true);
            Thread.sleep(2000);
            //验证数据量正确，同搜索结果页数量
            act_resultNum = p_customDatabasePage.func_get_searchResultNumber();
            l.info("Expected total result is [{}]", exp_resultNum);
            l.info("Actual total result is [{}]", act_resultNum);
            if (exp_resultNum == act_resultNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Total result is correct");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Total result is incorrect");
                t.takeScreenshot(d);
                result = false;
            }
            //assert
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }

    //======================================================== User List ===================================================

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchResultPage_userListFolderLevel",
            "searchResultPage_displayedFields",
            "searchResultPage_displayedFields2",
            "userListPage_displayedFields",
            "userListPage_displayedFields2",
            "shared_uid_1",
            "shared_pwd_1",
            "shared_uid_2",
            "shared_pwd_2",
            "download_path"
    })
    @Test
    public void userList_1(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int searchResultPage_userListFolderLevel,
            String searchResultPage_displayedFields,
            String searchResultPage_displayedFields2,
            String userListPage_displayedFields,
            String userListPage_displayedFields2,
            String shared_uid_1,
            String shared_pwd_1,
            String shared_uid_2,
            String shared_pwd_2,
            String download_path
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing expand user list folders ==============================");
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //点击收藏
            p_searchResultPage.func_click_addToList_toobar();
            //添加到收藏夹
            Thread.sleep(2000);
            ArrayList<String> folderNames = new ArrayList<String>();
            for (int i = 0; i < searchResultPage_userListFolderLevel; i++) {
                String folderName = String.valueOf(System.currentTimeMillis());
                folderNames.add(folderName);
                Thread.sleep(10);
            }
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //打开收藏页
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //点击展开前，记录文件夹数量（自建，分享）
            int folderNum_before = p_userListPage.func_get_folderNum();
            int sharedFolderNum_before = p_userListPage.func_get_sharedFolderNum();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击展开后，验证文件夹数量是否增加了（自建，分享）
            int folderNum_after = p_userListPage.func_get_folderNum();
            int sharedFolderNum_after = p_userListPage.func_get_sharedFolderNum();
            //验证展开
            l.info("Folder number: before expand: [{}], after expand: [{}]", folderNum_before, folderNum_after);
            l.info("Shared folder number: before expand: [{}], after expand: [{}]", sharedFolderNum_before, sharedFolderNum_after);
            if ((folderNum_before < folderNum_after) && (sharedFolderNum_before < sharedFolderNum_after)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Expand successful");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Expand failed");
                t.takeScreenshot(d);
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing collapse user list folders ==============================");
            //点击收起
            p_userListPage.func_click_collapseAll();
            //记录收起后文件夹数量
            folderNum_before = p_userListPage.func_get_folderNum();
            sharedFolderNum_before = p_userListPage.func_get_sharedFolderNum();
            //验证收起
            l.info("Folder number: before collapse: [{}], after collapse: [{}]", folderNum_after, folderNum_before);
            l.info("Shared folder number: before collapse: [{}], after collapse: [{}]", sharedFolderNum_after, sharedFolderNum_before);
            if ((folderNum_before < folderNum_after) && (sharedFolderNum_before < sharedFolderNum_after)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Collapse successful");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Collapse failed");
                t.takeScreenshot(d);
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing export ==============================");
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //获得搜索结果数据--期望值
            List<HashMap<String, String>> expPatentData = p_userListPage.func_getTableData_patentList();
            // click export
            p_userListPage.func_click_export();
            // 导出，并获取期望文件名
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            String partialFileName = p_exportPage.func_export(0, 2, download_path, 1, -1, 0, 0, -1, null);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            p_exportCompletePage.func_download(download_path, 300);
//			Assert.assertEquals((partialFileName != null), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing downloading excel file ==============================");
            // 验证文件是否下载成功
            Thread.sleep(2000);
            String excelName = p_exportPage.func_verify_fileDownload(download_path, partialFileName, "xls", 300);
            if (excelName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                l.debug("Try another file name");
                long partialFileName_int = Long.parseLong(partialFileName);
                partialFileName_int--;
                String partialFileName_bak = String.valueOf(partialFileName_int);
                excelName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileName_bak, "xls", 300);
            }
            if (excelName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                l.debug("Try another file name");
                long partialFileName_int = Long.parseLong(partialFileName);
                partialFileName_int++;
                String partialFileName_bak_2 = String.valueOf(partialFileName_int);
                excelName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileName_bak_2, "xls", 300);
            }
            if (excelName != null) {
                result &= true;
            } else {
                result &= false;
            }
            //断言，这里不通过，即下载失败，后面就不用再执行了
            Assert.assertEquals(result, true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing patent titles in excel ==============================");
            ExcelUtil excel = new ExcelUtil(download_path, excelName, false);
            List<HashMap<String, String>> actPatentData = excel.readExcelData();
            //验证excel中的PN和标题
            for (HashMap<String, String> expRow : expPatentData) {
                String expPN = expRow.get("Publication Num.");
                String expTitle = expRow.get("Title");
                l.info("Verifying PN: [{}]. Expected title: [{}]", expPN, expTitle);
                for (HashMap<String, String> actRow : actPatentData) {
                    String actPN = actRow.get("Publication  Number");
                    String actTitle = actRow.get("Title");
                    l.debug("Current PN: [{}]", actPN);
                    l.debug("Current Title: [{}]", actTitle);
                    //找到PN，则比较Title
                    if (expPN.equals(actPN)) {
                        if (actTitle.contains(expTitle)) {
                            l.info("++++++++++++++++++++++++++++++ Pass -- Title of [{}] is correct, exp: [{}], act: [{}]", expPN, expTitle, actTitle);
                            result &= true;
                            break;
                        } else {
                            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Title of [{}] is incorrect, exp: [{}], act: [{}]", expPN, expTitle, actTitle);
                            result &= true;
                        }
                    }
                }
            }
            //验证图片数量不为0
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing patent images in excel ==============================");
            int imageNumber = excel.getExcelImageNumber();
            if (imageNumber > 0) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Image number is [{}]", imageNumber);
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- No image in excel");
                result &= true;
            }
            //验证专利链接正确(检查一个链接)
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing patent links in excel ==============================");
            HashMap<String, String> links = excel.getExcelLinks();
            if (links.size() == 0) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- No link in excel");
            }
            for (String key : links.keySet()) {
                String linkPN = key;
                String linkUrl = links.get(key);
                l.info("PN in excel: [{}]", linkPN);
                l.info("PN link url: [{}]", linkUrl);
                //访问URL
                d.get(linkUrl);
                p_patentViewPage = new Zhihuiya_patentViewPage(d);
                Assert.assertEquals(p_patentViewPage.selfcheck(), true);
                p_patentViewPage.func_exit_tip();
                HashMap<String, String> patentInfo = p_patentViewPage.func_get_patentInfoTableData();
                String actual_pn = patentInfo.get("Publication Number");
                l.info("Expected Publication Number: {}", linkPN);
                l.info("Actual Publication Number: {}", actual_pn);
                if (actual_pn.contains(linkPN)) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- Publication Number is correct");
                    result &= true;
                } else {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Publication Number is incorrect");
                    t.takeScreenshot(d);
                    result &= false;
                }
                break;
            }
            //回到收藏夹页
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing add and view comments ==============================");
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //获取PN
            String pn = p_userListPage.func_get_anyPN();
            //添加备注
            String commentsContent = "Test comments";
            p_userListPage.func_set_comments(pn, commentsContent);
            //获取备注
            HashMap<String, String> hash_comments = p_userListPage.func_get_latestComments(pn);
            //验证备注作者为本人
            if (hash_comments.get("author").equals("Me")) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments author is correct, exp:[Me], act:[{}]", hash_comments.get("author"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments author is incorrect, exp:[Me], act:[{}]", hash_comments.get("author"));
                t.takeScreenshot(d);
                result &= false;
            }
            //验证备注内容正确
            if (hash_comments.get("content").equals(commentsContent)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments is correct, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments is incorrect, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
                t.takeScreenshot(d);
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing edit comments ==============================");
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //添加备注
            commentsContent = "Test comments updated";
            p_userListPage.func_set_comments(pn, commentsContent);
            //获取备注
            hash_comments = p_userListPage.func_get_latestComments(pn);
            //验证备注作者为本人
            if (hash_comments.get("author").equals("Me")) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments author is correct, exp:[Me], act:[{}]", hash_comments.get("author"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments author is incorrect, exp:[Me], act:[{}]", hash_comments.get("author"));
                t.takeScreenshot(d);
                result &= false;
            }
            //验证备注内容正确
            if (hash_comments.get("content").equals(commentsContent)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments is correct, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments is incorrect, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
                t.takeScreenshot(d);
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing delete comments ==============================");
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //删除备注
            commentsContent = "";
            p_userListPage.func_set_comments(pn, commentsContent);
            //获取备注
            hash_comments = p_userListPage.func_get_latestComments(pn);
            //验证备注不存在
            if (hash_comments == null) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments is deleted");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments still exist");
                t.takeScreenshot(d);
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing add and view custom field (text) ==============================");
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //添加自定义字段
            String fieldName = "TestCustomField(text)";
            p_userListPage.func_add_customField(fieldName, 0, null);
            //获取PN
            pn = p_userListPage.func_get_anyPN();
            //添加备注
            commentsContent = "Test comments";
            p_userListPage.func_set_customField_text(pn, commentsContent, 0);
            //获取备注
            String act_context = p_userListPage.func_get_customField_text(pn, 0);
            //验证备注作者为本人
            if (commentsContent.equals(act_context)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (text) context is correct, exp:[{}], act:[{}]", commentsContent, act_context);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (text) context is incorrect, exp:[{}], act:[{}]", commentsContent, act_context);
                t.takeScreenshot(d);
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing edit custom field (text) ==============================");
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //添加备注
            commentsContent = "Test comments updated";
            p_userListPage.func_set_customField_text(pn, commentsContent, 0);
            //获取备注
            act_context = p_userListPage.func_get_customField_text(pn, 0);
            //验证备注作者为本人
            if (commentsContent.equals(act_context)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (text) context is correct, exp:[{}], act:[{}]", commentsContent, act_context);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (text) context is incorrect, exp:[{}], act:[{}]", commentsContent, act_context);
                t.takeScreenshot(d);
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing delete custom field (text) ==============================");
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //删除备注
            commentsContent = "";
            p_userListPage.func_set_customField_text(pn, commentsContent, 0);
            //获取备注
            act_context = p_userListPage.func_get_customField_text(pn, 0);
            //验证备注不存在
            if (act_context.equals("")) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (text) is deleted");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (text) still exist");
                t.takeScreenshot(d);
                result &= false;
            }
            //=====删除字段列=====
            p_userListPage.func_delete_customField(fieldName);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing add and view custom field (date) ==============================");
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //添加自定义字段
            fieldName = "TestCustomField(date)";
            p_userListPage.func_add_customField(fieldName, 1, null);
            //获取PN
            pn = p_userListPage.func_get_anyPN();
            //添加备注
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            commentsContent = df.format(cal.getTime());
            p_userListPage.func_set_customField_date(pn, commentsContent, 0);
            //获取备注
            act_context = p_userListPage.func_get_customField_text(pn, 0);
            Calendar act_cal = Calendar.getInstance();
            DateFormat act_df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
            Date act_date = act_df.parse(act_context);
            act_cal.setTime(act_date);
            //验证备注作者为本人
            if (cal.get(Calendar.DAY_OF_YEAR) == act_cal.get(Calendar.DAY_OF_YEAR) && cal.get(Calendar.YEAR) == act_cal.get(Calendar.YEAR)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (date) context is correct, exp:[{}], act:[{}]", cal, act_cal);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (date) context is incorrect, exp:[{}], act:[{}]", cal, act_cal);
                t.takeScreenshot(d);
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing edit custom field (date) ==============================");
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //添加备注
            date = new Date();
            cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 1);
            df = new SimpleDateFormat("dd-MM-yyyy");
            commentsContent = df.format(cal.getTime());
            p_userListPage.func_set_customField_date(pn, commentsContent, 0);
            //获取备注
            act_context = p_userListPage.func_get_customField_text(pn, 0);
            act_cal = Calendar.getInstance();
            act_df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
            act_date = act_df.parse(act_context);
            act_cal.setTime(act_date);
            //验证备注作者为本人
            if (cal.get(Calendar.DAY_OF_YEAR) == act_cal.get(Calendar.DAY_OF_YEAR) && cal.get(Calendar.YEAR) == act_cal.get(Calendar.YEAR)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (date) context is correct, exp:[{}], act:[{}]", cal, act_cal);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (date) context is incorrect, exp:[{}], act:[{}]", cal, act_cal);
                t.takeScreenshot(d);
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing delete custom field (date) ==============================");
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //删除备注
            commentsContent = "";
            p_userListPage.func_set_customField_date(pn, commentsContent, 0);
            //获取备注
            act_context = p_userListPage.func_get_customField_text(pn, 0);
            //验证备注不存在
            if (act_context.equals("")) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (text) is deleted");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (text) still exist");
                t.takeScreenshot(d);
                result &= false;
            }
            //=====删除字段列=====
            p_userListPage.func_delete_customField(fieldName);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing add and view custom field (drop down menu) ==============================");
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //添加自定义字段
            fieldName = "TestCustomField(drop down menu)";
            ArrayList<String> options = new ArrayList<String>();
            options.add("1");
            options.add("2");
            options.add("3");
            p_userListPage.func_add_customField(fieldName, 2, options);
            //获取PN
            pn = p_userListPage.func_get_anyPN();
            //添加备注
            commentsContent = "1";
            p_userListPage.func_set_customField_select(pn, commentsContent, 0);
            //获取备注
            act_context = p_userListPage.func_get_customField_text(pn, 0);
            //验证备注作者为本人
            if (commentsContent.equals(act_context)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (drop down menu) context is correct, exp:[{}], act:[{}]", commentsContent, act_context);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (drop down menu) context is incorrect, exp:[{}], act:[{}]", commentsContent, act_context);
                t.takeScreenshot(d);
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing edit custom field (drop down menu) ==============================");
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //添加备注
            commentsContent = "2";
            p_userListPage.func_set_customField_select(pn, commentsContent, 0);
            //获取备注
            act_context = p_userListPage.func_get_customField_text(pn, 0);
            //验证备注作者为本人
            if (commentsContent.equals(act_context)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (drop down menu) context is correct, exp:[{}], act:[{}]", commentsContent, act_context);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (drop down menu) context is incorrect, exp:[{}], act:[{}]", commentsContent, act_context);
                t.takeScreenshot(d);
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing delete custom field (drop down menu) ==============================");
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //删除备注
            commentsContent = null;
            p_userListPage.func_set_customField_select(pn, commentsContent, 0);
            //获取备注
            act_context = p_userListPage.func_get_customField_text(pn, 0);
            //验证备注不存在
            if (act_context.equals("")) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (drop down menu) is deleted");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (drop down menu) still exist");
                t.takeScreenshot(d);
                result &= false;
            }
            //=====删除字段列=====
            p_userListPage.func_delete_customField(fieldName);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks (group 1) (user list) ==============================");
//			p_searchPage.func_goto_userListPage(); 					//测试用，要删掉
//			p_userListPage = new Zhihuiya_userListPage(d);			//测试用，要删掉
//			Assert.assertEquals(p_userListPage.selfcheck(), true);	//测试用，要删掉
            String expValue = null; //链接文字
            p_userListPage.func_config_displayedFields(userListPage_displayedFields);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of PN ==============================");
            expValue = p_userListPage.func_click_availableLink_patentList("PN");
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            result &= p_patentViewPage.func_verify_valueExistInPatentInfoTable("PN", expValue);
            p_patentViewPage.func_exit_tip();
            //进入收藏页
            p_patentViewPage.func_goto_userListPage();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of APN ==============================");
            expValue = p_userListPage.func_click_availableLink_patentList("APN");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            p_searchResultPage.func_config_defaultDisplay("tablelist");
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_config_displayedFields(searchResultPage_displayedFields);
            result &= p_searchResultPage.func_verify_valueExistInDataTable("APN", expValue);
            //进入收藏页
            p_patentViewPage.func_goto_userListPage();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of AN ==============================");
            expValue = p_userListPage.func_click_availableLink_patentList("AN");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("AN", expValue);
            //进入收藏页
            p_patentViewPage.func_goto_userListPage();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of AN_ADD ==============================");
            expValue = p_userListPage.func_click_availableLink_patentList("AN_ADD");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("AN_ADD", expValue);
            //进入收藏页
            p_patentViewPage.func_goto_userListPage();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of IN ==============================");
            expValue = p_userListPage.func_click_availableLink_patentList("IN");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("IN", expValue);
            //进入收藏页
            p_patentViewPage.func_goto_userListPage();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of AT ==============================");
            expValue = p_userListPage.func_click_availableLink_patentList("AT");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("AT", expValue);
            //进入收藏页
            p_patentViewPage.func_goto_userListPage();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks (group 2) (user list) ==============================");
            p_userListPage.func_config_displayedFields(userListPage_displayedFields2);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of ATC ==============================");
            expValue = p_userListPage.func_click_availableLink_patentList("ATC");
            p_searchResultPage.func_config_displayedFields(searchResultPage_displayedFields2);
            result &= p_searchResultPage.func_verify_valueExistInDataTable("ATC", expValue);
            //进入收藏页
            p_patentViewPage.func_goto_userListPage();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of APD ==============================");
            expValue = p_userListPage.func_click_availableLink_patentList("APD");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("APD", expValue);
            //进入收藏页
            p_patentViewPage.func_goto_userListPage();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of PBD ==============================");
            expValue = p_userListPage.func_click_availableLink_patentList("PBD");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("PBD", expValue);
            //进入收藏页
            p_patentViewPage.func_goto_userListPage();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of IPC ==============================");
            expValue = p_userListPage.func_click_availableLink_patentList("IPC");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("IPC", expValue);
            //进入收藏页
            p_patentViewPage.func_goto_userListPage();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing hyperlinks of UPC ==============================");
            expValue = p_userListPage.func_click_availableLink_patentList("UPC");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("UPC", expValue);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing delete patent in folder ==============================");
            //进入收藏页
            p_searchResultPage.func_goto_userListPage();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //获取任意PN
            pn = p_userListPage.func_get_anyPN();
            //删除专利
            p_userListPage.func_delete_patent_inCurrentFolder(pn);
            //等待
            Thread.sleep(2000);
            //验证专利不存在
            result &= p_userListPage.func_verify_patentDoesNotExistInUserList(pn);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing copy patent in folder ==============================");
            //获取任意PN
            pn = p_userListPage.func_get_anyPN();
            //复制专利到父文件夹
            p_userListPage.func_copyOrMove_patent(0, pn, folderNames.get(0));
            //验证专利复制后仍存在
            result &= p_userListPage.func_verify_patentExistInUserList(pn);
            //点击父文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(0));
            //等待
            Thread.sleep(10000);
            //验证专利存在
            result &= p_userListPage.func_verify_patentExistInUserList(pn);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing move patent in folder ==============================");
            //点击子文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            //等待
            Thread.sleep(10000);
            //获取任意PN
            pn = p_userListPage.func_get_anyPN();
            //移动专利到父文件夹
            p_userListPage.func_copyOrMove_patent(1, pn, folderNames.get(0));
            //刷新页面
            d.navigate().refresh();
            //验证专利复制后仍存在
            result &= p_userListPage.func_verify_patentDoesNotExistInUserList(pn);
            //点击父文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(0));
            //等待
            Thread.sleep(10000);
            //验证专利存在
            result &= p_userListPage.func_verify_patentExistInUserList(pn);
//			//========================删除文件夹=======================
//			p_userListPage.func_delete_userListFolder(folderNames.get(0));
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing create parent folder ==============================");
//			p_searchPage.func_goto_userListPage(); 					//测试用，要删掉
//			p_userListPage = new Zhihuiya_userListPage(d);			//测试用，要删掉
//			Assert.assertEquals(p_userListPage.selfcheck(), true);	//测试用，要删掉
            //创建文件夹
            String pFolderName = p_userListPage.func_create_folder();
            //刷新页面
            d.navigate().refresh();
            //验证文件夹
            ArrayList<String> names = new ArrayList<String>();
            names.add(pFolderName);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing create sub folder ==============================");
            Thread.sleep(2000); //等待页面刷新，不然第二个文件夹命名失败
            //创建文件夹
            String sFolderName = p_userListPage.func_create_folder();
            names.clear();
            names.add(sFolderName);
            //将子文件夹拖入父文件夹
            p_userListPage.func_create_subFolder(sFolderName, pFolderName);
            //验证文件夹
            result &= p_userListPage.func_verify_userListFolderExists(names);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing edit parent folder ==============================");
            //生成更新后的文件夹名称
            String pFolderName_updated = String.format("%s_updated", pFolderName);
            names.clear();
            names.add(pFolderName_updated);
            //更新文件夹
            p_userListPage.func_edit_userListFolder(pFolderName, pFolderName_updated);
            //验证文件夹
            Thread.sleep(2000); //等待页面刷新
            result &= p_userListPage.func_verify_userListFolderExists(names);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing edit sub folder ==============================");
            //刷新页面
            d.navigate().refresh();
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //生成更新后的文件夹名称
            String sFolderName_updated = String.format("%s_updated", sFolderName);
            names.clear();
            names.add(sFolderName_updated);
            //更新文件夹
            p_userListPage.func_edit_userListFolder(sFolderName, sFolderName_updated);
            //验证文件夹
            Thread.sleep(2000); //等待页面刷新
            result &= p_userListPage.func_verify_userListFolderExists(names);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing share sub folder (1 to 1) ==============================");
            //获取父子文件夹名称
            String share_pFilderName = folderNames.get(0);
            String share_sFilderName = folderNames.get(searchResultPage_userListFolderLevel - 1);
            //分享文件夹
            names.clear();
            names.add(shared_uid_1);
            p_userListPage.func_share_userListFolder(share_sFilderName, names, 0);
            //验证分享文件夹
            //登出
            p_userListPage.func_click_logout();
            //被分享用户登录
            p_loginPage.func_login(shared_uid_1, shared_pwd_1);
            //进入收藏夹列表
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证文件夹是否存在
            names.clear();
            names.add(share_sFilderName);
            p_userListPage.func_click_expandAll();
            result &= p_userListPage.func_verify_sharedUserListFolderExists(names);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing share information (1 to 1) ==============================");
            //创建自有文件夹
            String tFolderName = p_userListPage.func_create_folder();
            //点击分享文件夹
            p_userListPage.func_click_sharedUserListFolder(share_sFilderName);
            //验证分享信息
            result &= p_userListPage.func_verify_shareInfo(loginPage_uid);
            //获取任意PN
            pn = p_userListPage.func_get_anyPN();
            //复制专利到目标文件夹
            p_userListPage.func_copy_shredPatent(pn, tFolderName);
            //验证专利复制后仍存在
            result &= p_userListPage.func_verify_patentExistInUserList(pn);
            //点击目标文件夹
            p_userListPage.func_click_userListFolder(tFolderName);
            //等待
            Thread.sleep(10000);
            //验证专利存在
            result &= p_userListPage.func_verify_patentExistInUserList(pn);
            //删除文件夹
            p_userListPage.func_delete_userListFolder(tFolderName);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing share parent folder (1 to 1) ==============================");
            //登出
            p_userListPage.func_click_logout();
            //分享用户登录
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入收藏夹列表
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //分享文件夹
            names.clear();
            names.add(shared_uid_1);
            p_userListPage.func_share_userListFolder(share_pFilderName, names, 0);
            //验证分享文件夹
            //登出
            p_userListPage.func_click_logout();
            //被分享用户登录
            p_loginPage.func_login(shared_uid_1, shared_pwd_1);
            //进入收藏夹列表
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证文件夹是否存在
            names.clear();
            names.add(share_pFilderName);
            p_userListPage.func_click_expandAll();
            result &= p_userListPage.func_verify_sharedUserListFolderExists(names);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing share sub folder (group) ==============================");
            //登出
            p_userListPage.func_click_logout();
            //分享用户登录
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入收藏夹列表
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //分享文件夹
            names.clear();
            names.add(shared_uid_1);
            names.add(shared_uid_2);
            p_userListPage.func_click_expandAll();
            p_userListPage.func_share_userListFolder(share_sFilderName, names, 1);
            //验证分享文件夹
            //登出
            p_userListPage.func_click_logout();
            //被分享用户登录
            p_loginPage.func_login(shared_uid_1, shared_pwd_1);
            //进入收藏夹列表
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证文件夹是否存在
            names.clear();
            names.add(share_sFilderName);
            p_userListPage.func_click_expandAll();
            result &= p_userListPage.func_verify_sharedUserListFolderExists(names);
            //登出
            p_userListPage.func_click_logout();
            //被分享用户登录
            p_loginPage.func_login(shared_uid_2, shared_pwd_2);
            //进入收藏夹列表
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证文件夹是否存在
            names.clear();
            names.add(share_sFilderName);
            p_userListPage.func_click_expandAll();
            result &= p_userListPage.func_verify_sharedUserListFolderExists(names);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing share parent folder (group) ==============================");
            //登出
            p_userListPage.func_click_logout();
            //分享用户登录
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入收藏夹列表
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //分享文件夹
            names.clear();
            names.add(shared_uid_1);
            names.add(shared_uid_2);
            p_userListPage.func_share_userListFolder(share_pFilderName, names, 1);
            //验证分享文件夹
            //登出
            p_userListPage.func_click_logout();
            //被分享用户登录
            p_loginPage.func_login(shared_uid_1, shared_pwd_1);
            //进入收藏夹列表
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证文件夹是否存在
            names.clear();
            names.add(share_pFilderName);
            p_userListPage.func_click_expandAll();
            result &= p_userListPage.func_verify_sharedUserListFolderExists(names);
            //登出
            p_userListPage.func_click_logout();
            //被分享用户登录
            p_loginPage.func_login(shared_uid_2, shared_pwd_2);
            //进入收藏夹列表
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证文件夹是否存在
            names.clear();
            names.add(share_pFilderName);
            p_userListPage.func_click_expandAll();
            result &= p_userListPage.func_verify_sharedUserListFolderExists(names);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing delete sub folder ==============================");
            //登出
            p_userListPage.func_click_logout();
            //分享用户登录
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入收藏夹列表
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_click_expandAll();
            //删除文件夹
            p_userListPage.func_delete_userListFolder(share_sFilderName);
            //验证文件夹不存在
            Thread.sleep(2000); //等待页面刷新
            names.clear();
            names.add(share_sFilderName);
            result &= p_userListPage.func_verify_userListFolderDoesNotExist(names);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing delete parent folder ==============================");
            //删除文件夹
            p_userListPage.func_delete_userListFolder(pFolderName_updated);
            //验证文件夹不存在
            Thread.sleep(2000); //等待页面刷新
            names.clear();
            names.add(pFolderName);
            result &= p_userListPage.func_verify_userListFolderDoesNotExist(names);
            //========================删除文件夹=======================
            p_userListPage.func_delete_userListFolder(folderNames.get(0));
            //assert
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }


    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchResultPage_userListFolderLevel",
            "shared_uid_1",
            "shared_pwd_1",
            "shared_uid_2",
            "shared_pwd_2"
    })
    @Test
    public void userList_2(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int searchResultPage_userListFolderLevel,
            String shared_uid_1,
            String shared_pwd_1,
            String shared_uid_2,
            String shared_pwd_2
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing view/edit comments/custom field of shared folder (1 to 1)  ==============================");
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //点击收藏
            p_searchResultPage.func_click_addToList_toobar();
            //添加到收藏夹
            Thread.sleep(2000);
            ArrayList<String> folderNames = new ArrayList<String>();
            for (int i = 0; i < searchResultPage_userListFolderLevel; i++) {
                String folderName = String.valueOf(System.currentTimeMillis());
                folderNames.add(folderName);
                Thread.sleep(10);
            }
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //打开收藏页
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //添加备注
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            Thread.sleep(10000);
            String pn = p_userListPage.func_get_anyPN();
            String commentsContent = "Test comments";
            p_userListPage.func_set_comments(pn, commentsContent);
            //添加自定义字段
            String fieldName = "TestCustomField(text)";
            p_userListPage.func_add_customField(fieldName, 0, null);
            pn = p_userListPage.func_get_anyPN();
            String customFieldContent = "Test custom field";
            p_userListPage.func_set_customField_text(pn, customFieldContent, 0);
            //分享文件夹--一对一
            String share_pFilderName = folderNames.get(0);
            String share_sFilderName = folderNames.get(searchResultPage_userListFolderLevel - 1);
            ArrayList<String> names = new ArrayList<String>();
            names.clear();
            names.add(shared_uid_1);
            p_userListPage.func_share_userListFolder(share_sFilderName, names, 0);
            //切换为被分享用户
            p_userListPage.func_click_logout();
            p_loginPage.func_login(shared_uid_1, shared_pwd_1);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            names.clear();
            names.add(share_sFilderName);
            p_userListPage.func_click_expandAll();
            //在确认文件夹存在的前提下，验证备注
            Assert.assertEquals(p_userListPage.func_verify_sharedUserListFolderExists(names), true);
            p_userListPage.func_click_sharedUserListFolder(share_sFilderName);
            Thread.sleep(2000);
            HashMap<String, String> hash_comments = p_userListPage.func_get_latestComments(pn);
            if (loginPage_uid.equals(hash_comments.get("author"))) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments author is correct, exp:[{}], act:[{}]", loginPage_uid, hash_comments.get("author"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments author is incorrect, exp:[{}], act:[{}]", loginPage_uid, hash_comments.get("author"));
                t.takeScreenshot(d);
                result &= false;
            }
            if (commentsContent.equals(hash_comments.get("content"))) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments is correct, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments is incorrect, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
                t.takeScreenshot(d);
                result &= false;
            }
            //验证自定义字段
            String act_context = p_userListPage.func_get_customField_text(pn, 0);
            if (customFieldContent.equals(act_context)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (text) context is correct, exp:[{}], act:[{}]", customFieldContent, act_context);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (text) context is incorrect, exp:[{}], act:[{}]", customFieldContent, act_context);
                t.takeScreenshot(d);
                result &= false;
            }
            //用户B添加备注，并验证
            commentsContent = "Test comments (after share)";
            p_userListPage.func_set_comments(pn, commentsContent);
            hash_comments = p_userListPage.func_get_latestComments(pn);
            if (hash_comments.get("author").equals("Me")) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments author is correct, exp:[Me], act:[{}]", hash_comments.get("author"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments author is incorrect, exp:[Me], act:[{}]", hash_comments.get("author"));
                t.takeScreenshot(d);
                result &= false;
            }
            if (hash_comments.get("content").equals(commentsContent)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments is correct, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments is incorrect, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
                t.takeScreenshot(d);
                result &= false;
            }
            //用户B添加自定义字段，并验证
            fieldName = "TestCustomField(text)(afterShare)";
            p_userListPage.func_add_customField(fieldName, 0, null);
            Thread.sleep(2000);
            customFieldContent = "Test custom field (after share)";
            p_userListPage.func_set_customField_text(pn, customFieldContent, 1);
            act_context = p_userListPage.func_get_customField_text(pn, 1);
            if (customFieldContent.equals(act_context)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (text) context is correct, exp:[{}], act:[{}]", customFieldContent, act_context);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (text) context is incorrect, exp:[{}], act:[{}]", customFieldContent, act_context);
                t.takeScreenshot(d);
                result &= false;
            }
            //切换为分享用户
            p_userListPage.func_click_logout();
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_click_expandAll();
            //删除分享文件夹(父文件夹)
            p_userListPage.func_delete_userListFolder(share_pFilderName);
            //assert
            Thread.sleep(5000);
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }


    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchResultPage_userListFolderLevel",
            "shared_uid_1",
            "shared_pwd_1",
            "shared_uid_2",
            "shared_pwd_2"
    })
    @Test
    public void userList_3(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int searchResultPage_userListFolderLevel,
            String shared_uid_1,
            String shared_pwd_1,
            String shared_uid_2,
            String shared_pwd_2
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing view/edit comments/custom field of shared folder (group)  ==============================");
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //点击收藏
            p_searchResultPage.func_click_addToList_toobar();
            //添加到收藏夹
            Thread.sleep(2000);
            ArrayList<String> folderNames = new ArrayList<String>();
            for (int i = 0; i < searchResultPage_userListFolderLevel; i++) {
                String folderName = String.valueOf(System.currentTimeMillis());
                folderNames.add(folderName);
                Thread.sleep(10);
            }
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //打开收藏页
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //点击全部展开
            p_userListPage.func_click_expandAll();
            //添加备注
            p_userListPage.func_click_userListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            Thread.sleep(10000);
            String pn = p_userListPage.func_get_anyPN();
            String commentsContent = "Test comments";
            p_userListPage.func_set_comments(pn, commentsContent);
            //添加自定义字段
            String fieldName = "TestCustomField(text)";
            p_userListPage.func_add_customField(fieldName, 0, null);
            pn = p_userListPage.func_get_anyPN();
            String customFieldContent = "Test custom field";
            p_userListPage.func_set_customField_text(pn, customFieldContent, 0);
            //分享文件夹--分组
            String share_pFilderName = folderNames.get(0);
            String share_sFilderName = folderNames.get(searchResultPage_userListFolderLevel - 1);
            ArrayList<String> names = new ArrayList<String>();
            names.clear();
            names.add(shared_uid_1);
            names.add(shared_uid_2);
            p_userListPage.func_share_userListFolder(share_sFilderName, names, 1);
            //切换为被分享用户
            p_userListPage.func_click_logout();
            p_loginPage.func_login(shared_uid_1, shared_pwd_1);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            names.clear();
            names.add(share_sFilderName);
            p_userListPage.func_click_expandAll();
            //在确认文件夹存在的前提下，验证备注
            Assert.assertEquals(p_userListPage.func_verify_sharedUserListFolderExists(names), true);
            p_userListPage.func_click_sharedUserListFolder(share_sFilderName);
            Thread.sleep(2000);
            HashMap<String, String> hash_comments = p_userListPage.func_get_latestComments(pn);
            if (loginPage_uid.equals(hash_comments.get("author"))) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments author is correct, exp:[{}], act:[{}]", loginPage_uid, hash_comments.get("author"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments author is incorrect, exp:[{}], act:[{}]", loginPage_uid, hash_comments.get("author"));
                t.takeScreenshot(d);
                result &= false;
            }
            if (commentsContent.equals(hash_comments.get("content"))) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments is correct, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments is incorrect, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
                t.takeScreenshot(d);
                result &= false;
            }
            //验证自定义字段
            String act_context = p_userListPage.func_get_customField_text(pn, 0);
            if (customFieldContent.equals(act_context)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (text) context is correct, exp:[{}], act:[{}]", customFieldContent, act_context);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (text) context is incorrect, exp:[{}], act:[{}]", customFieldContent, act_context);
                t.takeScreenshot(d);
                result &= false;
            }
            //用户B添加备注，并验证
            commentsContent = "Test comments (after share)";
            p_userListPage.func_set_comments(pn, commentsContent);
            hash_comments = p_userListPage.func_get_latestComments(pn);
            if (hash_comments.get("author").equals("Me")) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments author is correct, exp:[Me], act:[{}]", hash_comments.get("author"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments author is incorrect, exp:[Me], act:[{}]", hash_comments.get("author"));
                t.takeScreenshot(d);
                result &= false;
            }
            if (hash_comments.get("content").equals(commentsContent)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments is correct, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments is incorrect, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
                t.takeScreenshot(d);
                result &= false;
            }
            //用户B添加自定义字段，并验证
            fieldName = "TestCustomField(text)(afterShare)";
            p_userListPage.func_add_customField(fieldName, 0, null);
            Thread.sleep(2000);
            customFieldContent = "Test custom field (after share)";
            p_userListPage.func_set_customField_text(pn, customFieldContent, 1);
            act_context = p_userListPage.func_get_customField_text(pn, 1);
            if (customFieldContent.equals(act_context)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (text) context is correct, exp:[{}], act:[{}]", customFieldContent, act_context);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (text) context is incorrect, exp:[{}], act:[{}]", customFieldContent, act_context);
                t.takeScreenshot(d);
                result &= false;
            }
            //切换为被分享用户C
            p_userListPage.func_click_logout();
            p_loginPage.func_login(shared_uid_2, shared_pwd_2);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            names.clear();
            names.add(share_sFilderName);
            p_userListPage.func_click_expandAll();
            p_userListPage.func_click_sharedUserListFolder(folderNames.get(searchResultPage_userListFolderLevel - 1));
            Thread.sleep(2000);
            //验证用户B的备注
            hash_comments = p_userListPage.func_get_latestComments(pn);
            if (hash_comments.get("author").equals(shared_uid_1)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments author is correct, exp:[{}], act:[{}]", shared_uid_1, hash_comments.get("author"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments author is incorrect, exp:[{}], act:[{}]", shared_uid_1, hash_comments.get("author"));
                t.takeScreenshot(d);
                result &= false;
            }
            if (hash_comments.get("content").equals(commentsContent)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Comments is correct, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Comments is incorrect, exp:[{}], act:[{}]", commentsContent, hash_comments.get("content"));
                t.takeScreenshot(d);
                result &= false;
            }
            //验证用户B的自定义字段
            act_context = p_userListPage.func_get_customField_text(pn, 1);
            if (customFieldContent.equals(act_context)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Custom field (text) context is correct, exp:[{}], act:[{}]", customFieldContent, act_context);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom field (text) context is incorrect, exp:[{}], act:[{}]", customFieldContent, act_context);
                t.takeScreenshot(d);
                result &= false;
            }
            //切换为分享用户
            p_userListPage.func_click_logout();
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_click_expandAll();
            //删除分享文件夹(父文件夹)
            p_userListPage.func_delete_userListFolder(share_pFilderName);
            //assert
            Thread.sleep(5000);
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }


    //======================================================== search ==============================================================

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchPage_exclusionField",
            "searchPage_exclusionKeyword"
    })
    @Test
    public void search_1(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String searchPage_exclusionField,
            String searchPage_exclusionKeyword
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing search with exclusion criteria ==============================");
            //处理字符串
            HashMap<String, String> parms_exclusionFields = new HashMap<String, String>();
            String[] fields = searchPage_exclusionField.split(",");
            String[] keywords = searchPage_exclusionKeyword.split(",");
            if (fields.length != keywords.length) {
                l.error("Parameter number is not match, fields: [{}], keywords: [{}]", fields.length, keywords.length);
                Assert.assertEquals(false, true);
            }
            int fieldsNum = fields.length;
            for (int i = 0; i < fieldsNum; i++) {
                parms_exclusionFields.put(fields[i], keywords[i]);
            }
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, parms_exclusionFields);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //验证搜索结果中不包含已排除数据
            result &= p_searchResultPage.func_verify_valueNotExistInDataTable("pn", "US6500001"); //这里先写死
            result &= p_searchResultPage.func_verify_valueNotExistInDataTable("pn", "US6500002"); //这里先写死
            //assert
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }


    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_cmd_conditions",
            "searchPage_cmd_fields",
            "searchPage_cmd_keywords"
    })
    @Test
    public void search_2(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_cmd_conditions,
            String searchPage_cmd_fields,
            String searchPage_cmd_keywords
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing command search ==============================");
            //点击命令搜索
            p_searchPage.func_click_commandSearch();
            //调用命令搜索方法
            p_searchPage.func_commandSearch(searchPage_database, searchPage_cmd_conditions, searchPage_cmd_fields, searchPage_cmd_keywords);
            //验证搜索结果
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("pn", "US6500001"); //这里先写死
            result &= p_searchResultPage.func_verify_valueNotExistInDataTable("pn", "US6500002"); //这里先写死
            //assert
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }


    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_bulkSearch_keyword",
            "searchPage_bulkSearch_targetPN"
    })
    @Test
    public void search_3(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_bulkSearch_keyword,
            String searchPage_bulkSearch_targetPN
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing bulk search ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //点击批量搜索标签
            p_searchPage.func_click_bulkSearch();
            //点击允许替换
            p_searchPage.func_click_allowReplacement();
            //批量添加专利
            String act_addResult = p_searchPage.func_bulkSearch(searchPage_bulkSearch_keyword);
            //验证添加日志
            String exp_addResult = String.format("use %s replace", searchPage_bulkSearch_targetPN);
            if (act_addResult.contains(exp_addResult)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Replace successful");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Replace failed ");
                t.takeScreenshot(d);
                result &= false;
            }
            //点击搜索
            p_searchPage.func_view_bulkSearchResult();
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //验证搜索结果
            result &= p_searchResultPage.func_verify_valueExistInDataTable("pn", searchPage_bulkSearch_targetPN);
            result &= p_searchResultPage.func_verify_valueNotExistInDataTable("pn", searchPage_bulkSearch_keyword);
            // assert
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }

    //========================================================= toolbox ==========================================================

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "loginPage_superPassword",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "download_path"
    })
    @Test
    public void toolbox_1(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String loginPage_superPassword,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing history of login ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入历史记录页
            p_searchPage.func_goto_historyPage();
            p_historyPage = new Zhihuiya_historyPage(d);
            Assert.assertEquals(p_historyPage.selfcheck(), true);
            //验证登录信息存在于列表中
            result &= p_historyPage.func_verify_contextExistInHistory("Login");
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing history of search and view patent ==============================");
            //登录
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            String exp_query = p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //查看专利
            List<HashMap<String, String>> tableData = p_searchResultPage.getTableData(p_searchResultPage.table_patentList_tableView());
            String pn = tableData.get(0).get("Publication Number");
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            p_patentViewPage.func_exit_tip();
            String title = p_patentViewPage.func_get_patentTitle();
            //进入历史记录页
            p_searchPage.func_goto_historyPage();
            p_historyPage = new Zhihuiya_historyPage(d);
            Assert.assertEquals(p_historyPage.selfcheck(), true);
            //验证搜索和查看专利信息存在于列表中
            result &= p_historyPage.func_verify_contextExistInHistory(String.format("Search %s", exp_query));
            result &= p_historyPage.func_verify_contextExistInHistory(pn);
            result &= p_historyPage.func_verify_contextExistInHistory(title);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing history of analysis ==============================");
            //登录
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            exp_query = p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_analyzePatent();
            Thread.sleep(5000);
            //进入历史记录页
            p_searchPage.func_goto_historyPage();
            p_historyPage = new Zhihuiya_historyPage(d);
            Assert.assertEquals(p_historyPage.selfcheck(), true);
            //验证登录信息存在于列表中
            result &= p_historyPage.func_verify_contextExistInHistory(String.format("Analyze (%s)", exp_query));
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing history of export ==============================");
            //搜索页
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            // click export
            p_searchResultPage.func_click_export();
            // 导出，并获取期望文件名
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            p_exportPage.func_export(0, 2, download_path, 1, -1, 0, 0, -1, null);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            p_exportCompletePage.func_download(download_path, 300);
            //进入历史记录页
            p_searchPage.func_goto_historyPage();
            p_historyPage = new Zhihuiya_historyPage(d);
            Assert.assertEquals(p_historyPage.selfcheck(), true);
            //去左右括号
            exp_query = exp_query.substring(1, exp_query.length() - 1);
            //验证登录信息存在于列表中
            result &= p_historyPage.func_verify_contextExistInHistory(String.format("Export %s", exp_query));
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing history of super password ==============================");
            //登出
            p_historyPage.func_click_logout();
            //超级密码登录
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_superPassword);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入历史记录页
            p_searchPage.func_goto_historyPage();
            p_historyPage = new Zhihuiya_historyPage(d);
            Assert.assertEquals(p_historyPage.selfcheck(), true);
            //验证登录信息存在于列表中
            result &= p_historyPage.func_verify_contextDoesNotExistInHistory_atRow("Login", 0);
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }


    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "queryName",
            "query_or_1",
            "query_or_2",
            "query_and_1",
            "query_and_2",
            "pn_or_1",
            "pn_or_2",
            "pn_and_1",
            "pn_and_2"
    })
    @Test
    public void toolbox_2(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String queryName,
            String query_or_1,
            String query_or_2,
            String query_and_1,
            String query_and_2,
            String pn_or_1,
            String pn_or_2,
            String pn_and_1,
            String pn_and_2
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing combine queries ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入已保存搜索页
            p_searchPage.func_goto_savedSearchPage();
            p_savedSearchPage = new Zhihuiya_savedSearchPage(d);
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            //删除所有query
            p_savedSearchPage.func_delete_all_savedSearch();
            //进入搜索结果页
            p_savedSearchPage.func_searchForQuery("car");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //保存搜索1(OR)
            p_searchResultPage.func_click_saveQuery();
            String actQName_or_1 = p_searchResultPage.func_save_query(queryName, query_or_1);
            Thread.sleep(2000);
            //保存搜索2(OR)
            p_searchResultPage.func_click_saveQuery();
            String actQName_or_2 = p_searchResultPage.func_save_query(queryName, query_or_2);
            Thread.sleep(2000);
            //保存搜索1(AND)
            p_searchResultPage.func_click_saveQuery();
            String actQName_and_1 = p_searchResultPage.func_save_query(queryName, query_and_1);
            Thread.sleep(2000);
            //保存搜索2(AND)
            p_searchResultPage.func_click_saveQuery();
            String actQName_and_2 = p_searchResultPage.func_save_query(queryName, query_and_2);
            Thread.sleep(2000);
            //进入已保存搜索页面
            p_searchResultPage.func_goto_savedSearchPage();
            //组合搜索(OR)
            ArrayList<String> queries_or = new ArrayList<String>();
            queries_or.add(actQName_or_1);
            queries_or.add(actQName_or_2);
            p_savedSearchPage.func_combine_savedSearch(queries_or, 0);
            //验证组合结果
            d.navigate().refresh();
            String exp_name_or = String.format("(%s OR %s)", actQName_or_1, actQName_or_2);
            String exp_query_or = String.format("((%s) OR (%s))", query_or_1, query_or_2);
            result &= p_savedSearchPage.func_verify_savedSearch(exp_name_or, exp_query_or);
            //点击query链接
            p_savedSearchPage.func_click_queryLink_byName(exp_name_or);
            //验证搜索结果
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("pn", pn_or_1);
            result &= p_searchResultPage.func_verify_valueExistInDataTable("pn", pn_or_2);
            //进入已保存搜索页面
            p_searchResultPage.func_goto_savedSearchPage();
            //组合搜索(AND)
            ArrayList<String> queries_and = new ArrayList<String>();
            queries_and.add(actQName_and_1);
            queries_and.add(actQName_and_2);
            p_savedSearchPage.func_combine_savedSearch(queries_and, 1);
            //验证组合结果
            d.navigate().refresh();
            String exp_name_and = String.format("(%s AND %s)", actQName_and_1, actQName_and_2);
            String exp_query_and = String.format("((%s) AND (%s))", query_and_1, query_and_2);
            result &= p_savedSearchPage.func_verify_savedSearch(exp_name_and, exp_query_and);
            //点击query链接
            p_savedSearchPage.func_click_queryLink_byName(exp_name_and);
            //验证搜索结果
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            result &= p_searchResultPage.func_verify_valueExistInDataTable("pn", pn_and_2);
            result &= p_searchResultPage.func_verify_valueNotExistInDataTable("pn", pn_and_1);
            //断言
            Assert.assertEquals(result, true);
            //进入已保存搜索页
            p_searchResultPage.func_goto_savedSearchPage();
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            //删除所有query
            p_savedSearchPage.func_delete_all_savedSearch();
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "loginPage_pwd_new"
    })
    @Test
    public void toolbox_3(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String loginPage_pwd_new
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing change password ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //点击account链接
            p_searchPage.func_goto_accountPage();
            //修改密码
            p_accountPage = new Zhihuiya_accountPage(d);
            p_accountPage.func_changePassword(loginPage_pwd, loginPage_pwd_new);
            l.info("Password is changed");
            Thread.sleep(2000);
            //登出
            p_accountPage.func_click_logout();
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd_new);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            l.info("Login successful with new password");
            //点击account链接
            p_searchPage.func_goto_accountPage();
            //修改密码
            p_accountPage = new Zhihuiya_accountPage(d);
            p_accountPage.func_changePassword(loginPage_pwd_new, loginPage_pwd);
            Thread.sleep(2000);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }

    //========================================================= analysis ==========================================================

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "download_path"
    })
    @Test
    public void analysis_1(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing export analysis result (jpg & csv) ==============================");
            //登录
            boolean result = true;
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击分析专利
            p_searchResultPage.func_click_analyzePatent();
            //========== 导出JPG ==========
            //查看指定专利分析列表
            p_searchResultPage.func_view_analyzeChart(0, 0, 2);
            //点击导出到jpg--需获取文件名
            ArrayList<String> partialFileNames = p_searchResultPage.func_export_analysisData(0);
            //验证文件是否被正确保存
            Thread.sleep(2000);
            String fileName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileNames.get(0), "jpg", 10);
            if (fileName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                fileName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileNames.get(1), "jpg", 10);
            }
            if (fileName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                fileName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileNames.get(2), "jpg", 10);
            }
            if (fileName != null) {
                result &= true;
            } else {
                result &= false;
            }
            //========== 导出csv ==========
            //遍历所有表格
            ArrayList<HashMap<String, String>> analysisData;
            for (int i = 0; i < 4; i++) {
                p_searchResultPage.func_click_analyzeTab_byIndex(i);
                int linkNum = p_searchResultPage.func_get_linkNumOfCurrentTab();
                for (int j = 0; j < linkNum; j++) {
                    p_searchResultPage.func_view_analyzeChart(i, j, 0);
                    //获取表格数据
                    analysisData = p_searchResultPage.func_getTableData_analysis();
                    //点击导出
                    ArrayList<String> partialFileNames_csv = p_searchResultPage.func_export_analysisData(1);
                    //验证文件是否被正确保存
                    Thread.sleep(2000);
                    fileName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileNames_csv.get(0), "csv", 10);
                    if (fileName == null) {
                        //如果实际文件名为空，则尝试通过备用文件名来查找文件
                        fileName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileNames_csv.get(1), "csv", 10);
                    }
                    if (fileName == null) {
                        //如果实际文件名为空，则尝试通过备用文件名来查找文件
                        fileName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileNames_csv.get(2), "csv", 10);
                    }
                    if (fileName != null) {
                        result &= true;
                    } else {
                        result &= false;
                    }
                    //获取文件内容
                    FileUtil f = new FileUtil();
                    ArrayList<HashMap<String, String>> csvData = f.readCsv(String.format("%s//%s", download_path, fileName), null);
                    //验证文件内容
                    result &= p_searchResultPage.func_verify_csvData_analysis(analysisData, csvData);
                }
            }
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 临时存放代码用
     */
    public void temp() {
        //==========
//		//验证输入
//		p_exportPage.func_set_exportNum("1", "10001");
//		p_exportPage.func_click_exportButton();
//		Thread.sleep(1000);
//		String expAlertContent = String.format("You can export %d family groups each time.", 10000);
//		result &= p_exportPage.func_verify_alertContent(expAlertContent);
//		//刷新页面
//		d.navigate().refresh();

        //==========
//		if(excel != null){
//			l.info("++++++++++++++++++++++++++++++ Pass -- Format is correct");
//		}else{
//			l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Format is incorrect");
//			result &= false;
//		}
    }


}
