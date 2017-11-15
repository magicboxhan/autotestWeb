package com.hq.test.automation.testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.hq.test.automation.pageobject.*;
import com.hq.test.framework.page.BasePage;
import com.hq.test.framework.testcase.BaseTestcase;
import com.hq.test.framework.util.ExcelUtil;
import com.hq.test.framework.util.FileUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hq.test.automation.misc.IpReportThread;

public class Zhihuiya_smokeTest extends BaseTestcase {

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
    Wangyi_loginPage pwy_loginPage;
    Wangyi_mainPage pwy_mainPage;
    Zhihuiya_imageDiscoveryPage p_imageDiscoveryPage;
    Zhihuiya_imageSearchResultPage p_imageSearchResultPage;

    /**
     * 登录登出 *
     *
     * @param loginPage_url 测试地址
     * @param loginPage_uid 用户名
     * @param loginPage_pwd 密码
     * @param language      测试语言
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "language"
    })
    @Test
    public void loginLogout(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing login and logout function ==============================");
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            if (p_searchPage.selfcheck()) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Login successful");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Login failed");
                t.takeScreenshot(d);
            }
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // logout
            p_searchPage.func_click_logout();
            p_loginPage = new Zhihuiya_loginPage(d);
            if (p_loginPage.selfcheck()) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Logout successful");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Logout failed");
                t.takeScreenshot(d);
            }
            Assert.assertEquals(p_loginPage.selfcheck(), true);
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
     * 字段搜索 *
     *
     * @param loginPage_url                    测试地址
     * @param loginPage_uid                    用户名
     * @param loginPage_pwd                    密码
     * @param searchPage_database              数据库
     * @param searchPage_field                 搜索字段
     * @param searchPage_keyword               搜索关键字
     * @param searchResultPage_displayedFields 搜索结果页展示字段
     * @param searchResultPage_filePath        保存搜索结果数量的文件路径
     * @param searchResultPage_imgCheckingNum  搜索结果页检查图片的数量
     * @param language                         测试语言
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchResultPage_displayedFields",
            "searchResultPage_filePath",
            "searchResultPage_imgCheckingNum",
            "language"
    })
    @Test
    public void fieldSearch(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String searchResultPage_displayedFields,
            String searchResultPage_filePath,
            int searchResultPage_imgCheckingNum,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing field search function ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            long startTime = System.currentTimeMillis();
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //相关设置
            //设置搜索选项0
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_config_resultDisplay(0), true);
            p_searchResultPage.func_config_englishStemming(1);
            //页面自检通过后，记录搜索耗时
            long duration = System.currentTimeMillis() - startTime;
            result &= p_searchResultPage.func_verify_searchResultExist_tableView();
            l.info("Search speed: {}s", duration / 1000);
            //设置显示字段
            p_searchResultPage.func_config_displayedFields(searchResultPage_displayedFields);
            // verify the amount of search result
            int num = p_searchResultPage.func_get_searchResultNumber();
            FileUtil f = new FileUtil();
            int exp_totalNum = 0;
            if (f.readFile(searchResultPage_filePath, false) == null) {
                exp_totalNum = 0;
            } else {
                exp_totalNum = Integer.parseInt(f.readFile(searchResultPage_filePath, false).trim());
            }
            l.info("Expected total result: {}", exp_totalNum);
            l.info("Actual total result: {}", num);
            //数字变化超过10%则报失败
            if (exp_totalNum == 0) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Total result is in correct range of {}", exp_totalNum);
                f.writeFile(searchResultPage_filePath, String.valueOf(num));
                result &= true;
            } else if ((double) Math.abs(num - exp_totalNum) / (double) exp_totalNum <= 0.1) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Total result is in correct range of {}", exp_totalNum);
                f.writeFile(searchResultPage_filePath, String.valueOf(num));
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Total result is out of correct range of {}", exp_totalNum);
                t.takeScreenshot(d);
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing images ==============================");
            //验证图片资源是否可访问
            for (int i = 1; i <= searchResultPage_imgCheckingNum; i++) {
                String patentImgSrc = p_searchResultPage.func_get_patentImgSrc_ByIndex(i);
                result &= p_searchResultPage.func_verify_patentImgSrc(patentImgSrc);
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing abstract ==============================");
            //验证浮出摘要是否存在
            p_searchResultPage.func_verify_tipAbstractExist_byIndex(1);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing sort function ==============================");
            //验证排序
            p_searchResultPage.func_select_sortType_byIndex(3);
            Thread.sleep(2000);
            result &= p_searchResultPage.func_verify_patentListOrder(1, 0, 10);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing group search ==============================");
            //将显示设置修改为1,2,3后，查看搜索是否有结果
            Assert.assertEquals(p_searchResultPage.func_config_resultDisplay(1), true);
            result &= p_searchResultPage.func_verify_searchResultExist_tableView();
            Assert.assertEquals(p_searchResultPage.func_config_resultDisplay(2), true);
            result &= p_searchResultPage.func_verify_searchResultExist_tableView();
            Assert.assertEquals(p_searchResultPage.func_config_resultDisplay(3), true);
            result &= p_searchResultPage.func_verify_searchResultExist_tableView();
            Assert.assertEquals(p_searchResultPage.func_config_resultDisplay(0), true);
            result &= p_searchResultPage.func_verify_searchResultExist_tableView();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing switch view ==============================");
            //切换为各个视图，检查是否切换正确
            p_searchResultPage.func_click_view_byRel("standard");
            result &= p_searchResultPage.func_verify_searchResultExist_standardView();
            p_searchResultPage.func_click_view_byRel("thumb");
            result &= p_searchResultPage.func_verify_searchResultExist_thumbnailView();
            p_searchResultPage.func_click_view_byRel("analysis");
            result &= p_searchResultPage.func_verify_searchResultExist_analyzeView();
            p_searchResultPage.func_click_view_byRel("flipit");
            result &= p_searchResultPage.func_verify_searchResultExist_flipItView();
            p_searchResultPage.func_click_view_byRel("tablelist");
            //logout
            p_searchResultPage.func_click_logout();
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

    /**
     * 批量搜索
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_bulkSearch_keyword
     * @param searchPage_bulkSearch_expNum
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_bulkSearch_keyword",
            "searchPage_bulkSearch_expNum",
            "language"
    })
    @Test
    public void bulkSearch(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_bulkSearch_keyword,
            int searchPage_bulkSearch_expNum,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing bulk search function ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            //点击批量搜索标签
            p_searchPage.func_click_bulkSearch();
            p_searchPage.func_bulkSearch(searchPage_bulkSearch_keyword);
            result &= p_searchPage.func_verifyBulkSearchResultNum(searchPage_bulkSearch_expNum);
            p_searchPage.func_view_bulkSearchResult();
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            int num = p_searchResultPage.func_get_searchResultNumber();
            l.info("Expected total result: {}", searchPage_bulkSearch_expNum);
            l.info("Actual total result: {}", num);
            if (num == searchPage_bulkSearch_expNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Total result is correct");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Total result is incorrect");
                t.takeScreenshot(d);
                result &= false;
            }
            //logout
            p_searchResultPage.func_click_logout();
            // assert
            Assert.assertEquals(result, true);
            l.exit();
        } catch (TimeoutException e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Pageload Timeout");
            Runtime.getRuntime().exec("Taskkill /IM firefox.exe");
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            e.printStackTrace();
            l.error("Error!");
            p_basePage.takeScreenshot(d, Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertEquals(false, true);
        }
    }

    /**
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "language"
    })
    @Test
    public void patentView(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing patent view function ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // 获取第一行的title
            String exp_patentTitle = p_searchResultPage.func_get_patentTitleByIndex(0);
            // 获取第一行的第一个链接
            long startTime = System.currentTimeMillis();
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            p_patentViewPage.func_exit_tip();
            long duration = System.currentTimeMillis() - startTime;
            l.info("Patent view page load time: {} seconds", duration / 1000);
            HashMap<String, String> patentInfo = p_patentViewPage.func_get_patentInfoTableData();
            String actual_patentTitle = patentInfo.get("Title");
            if (actual_patentTitle == null) {
                actual_patentTitle = patentInfo.get("标题");
            }
            l.info("Expected patent title: {}", exp_patentTitle);
            l.info("Actual patent title: {}", actual_patentTitle);
            if (actual_patentTitle.contains(exp_patentTitle)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- patent title is correct");
                result = true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- patent title is incorrect");
                t.takeScreenshot(d);
                result = false;
            }
            //logout
            p_patentViewPage.func_click_logout();
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

    /**
     * 分析专利
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "language"
    })
    @Test
    public void analyzePatent(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing analyze patent ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // click analyze patent
            p_searchResultPage.func_click_analyzePatent();
            // 验证每个Tab的每一个链接的第一个图表有数据，并点击其中链接，看能否跳转到搜索结果页
            for (int i = 0; i < 4; i++) {
                p_searchResultPage.func_click_analyzeTab_byIndex(i);
                int linkNum = p_searchResultPage.func_get_linkNumOfCurrentTab();
                for (int j = 0; j < linkNum; j++) {
                    p_searchResultPage.func_view_analyzeChart(i, j, 0);
                    result &= p_searchResultPage.func_verify_analyzeTable();
                    //点击链接
                    if (!p_searchResultPage.func_click_firstLinkOnAnalyzeTable()) {
                        l.info("Can not click on any link, so continue");
                        continue;
                    }
                    //切换到新窗口
                    Thread.sleep(2000);
                    this.switchToNewWindow();
                    p_searchResultPage = new Zhihuiya_searchResultPage(d);
                    result &= p_searchResultPage.selfcheck();
                    result &= p_searchResultPage.func_verify_searchResultExist_tableView();
                    //关闭新窗口
                    d.close();
                    //切换到老窗口
                    this.switchToOldWindow();
                }
            }
            //logout
            p_searchResultPage.func_click_logout();
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

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "language"
    })
    @Test
    public void analyzePatent_userlist(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing analyze patent (user list) ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // 点击收藏--工具栏
            p_searchResultPage.func_click_addToList_toobar();
            // 添加到收藏夹
            Thread.sleep(2000);
            ArrayList<String> folderNames = new ArrayList<String>();
            String folderName = String.valueOf(System.currentTimeMillis());
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            // 打开收藏页
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证文件夹
            Assert.assertEquals(p_userListPage.func_verify_userListFolderExists(folderNames), true);
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            // click quick chart
            p_userListPage.func_click_quickChart();
            this.switchToNewWindow();
            // 验证每个Tab的第一个链接的第一个图表有数据，并点击其中链接，看能否跳转到搜索结果页
            p_searchResultPage.func_click_analyzeTab_byIndex(0);
            p_searchResultPage.func_view_analyzeChart(0, 0, 0);
            result &= p_searchResultPage.func_verify_analyzeTable();
            //切换到老窗口
            this.switchToOldWindow();
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderNames.get(0));
            //logout
            p_userListPage.func_click_logout();
            l.exit();
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

    /**
     * 验证搜索页页面元素正确
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_exp_tableFields
     * @param searchPage_exp_userMenu
     * @param searchPage_exp_databaseList
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_exp_tableFields",
            "searchPage_exp_userMenu",
            "searchPage_exp_databaseList",
            "language"
    })
    @Test
    public void searchElementsCheck(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_exp_tableFields,
            String searchPage_exp_userMenu,
            String searchPage_exp_databaseList,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing search elements ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            long startTime = System.currentTimeMillis();
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            long duration = (System.currentTimeMillis() - startTime) / 1000;
            l.info("Time of login is {} seconds", duration);
            // check elements
            // search fields
            String[] searchFields = searchPage_exp_tableFields.split(",");
            result &= p_searchPage.func_verifySearchFields(searchFields);
            // user menu
            String[] userMenu = searchPage_exp_userMenu.split(",");
            result &= p_searchPage.func_verifyUserMenu(userMenu);
            // databases
            String[] databaseList = searchPage_exp_databaseList.split(",");
            result &= p_searchPage.func_verify_databaseList(databaseList);
            //logout
            p_searchPage.func_click_logout();
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

    /**
     * ipc搜索
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_ipc_keyword
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_ipc_keyword",
            "language"
    })
    @Test
    public void ipcSearch(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_ipc_keyword,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing IPC search ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            //点击IPC搜索标签
            p_searchPage.func_click_classificationSearch();
            //检查IPC搜索结果
            result &= p_searchPage.func_verify_ipcSearchResultExist();
            //点击左侧链接
            p_searchPage.func_click_leftSideLink_byIndex(0);
            //检查IPC搜索结果
            result &= p_searchPage.func_verify_ipcSearchResultExist();
            //按CN搜索
            p_searchPage.func_ipcSearch_byCN(searchPage_ipc_keyword);
            //检查IPC搜索结果
            result &= p_searchPage.func_verify_ipcSearchResultExist();
            //按关键字搜索
            p_searchPage.func_ipcSearch_byKeyword(searchPage_ipc_keyword);
            //检查IPC搜索结果
            result &= p_searchPage.func_verify_ipcSearchResultExist();
            //logout
            p_searchPage.func_click_logout();
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

    /**
     * 创建、删除邮件提醒
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "language"
    })
    @Test
    public void emailAlert(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing email alert ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // create email alert
            p_searchResultPage.func_click_emailAlert();
            this.switchToNewWindow();
            String emailAlertName = p_searchResultPage.func_create_emailAlert_simple();
            l.info("Email alert name is [{}]", emailAlertName);
            // verify email alert
            Thread.sleep(5000);
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            result &= p_emailAlertPage.func_verify_emailAlertExists(emailAlertName);
            // edit 
            String emailAlertName_updated = String.format("%s_updated", emailAlertName);
            p_emailAlertPage.func_edit_emailAlert(emailAlertName, emailAlertName_updated);
            // verify
            Thread.sleep(5000);
            d.navigate().refresh();
            result &= p_emailAlertPage.func_verify_emailAlertExists(emailAlertName_updated);
            // delete email alert
            p_emailAlertPage.func_delete_emailAlert(emailAlertName_updated);
            // verify
            Thread.sleep(10000);
            result &= p_emailAlertPage.func_verify_emailAlertNotExists(emailAlertName_updated);
            //logout
            p_emailAlertPage.func_click_logout();
            l.exit();
            // assert
            Assert.assertEquals(result, true);
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
     * landscape
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @param landscapePage_sampingValue
     * @param landscapePage_sampingTimeout
     * @param landscapePage_retryTimes     -- 失败重试次数
     * @param landscapePage_loopTimes      -- 循环执行次数
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "landscapePage_sampingValue",
            "landscapePage_sampingTimeout",
            "landscapePage_retryTimes",
            "landscapePage_loopTimes",
            "language"
    })
    @Test
    public void landscape(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String landscapePage_sampingValue,
            int landscapePage_sampingTimeout,
            int landscapePage_retryTimes,
            int landscapePage_loopTimes,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing landscape ==============================");
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            // click landscape
            p_searchResultPage.func_click_landscape();
            this.switchToNewWindow();
            p_landScapePage = new Zhihuiya_landscapePage(d);
            Assert.assertEquals(p_landScapePage.selfcheck(), true);
            // generate result
            boolean landscape_result = true;
            String[] sampingValues = landscapePage_sampingValue.split(",");
            for (int i = 0; i < landscapePage_loopTimes; i++) {
                // 生成随机查询
                Random r = new java.util.Random();
                int randomIndex = Math.abs(r.nextInt()) % 10000;
                String keyword = String.valueOf(randomIndex);
                for (String sampingValue : sampingValues) {
                    l.debug("Requesting landscape for value {}", sampingValue);
                    landscape_result &= p_landScapePage.func_loadLandscape(keyword, sampingValue, landscapePage_sampingTimeout, landscapePage_retryTimes);
                    p_landScapePage.func_nav_close();
                }
            }
            this.switchToOldWindow();
            //logout
            p_landScapePage.func_click_logout();
            Assert.assertEquals(landscape_result, true);
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
     * landscape
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @param landscapePage_sampingValue
     * @param landscapePage_sampingTimeout
     * @param landscapePage_retryTimes     -- 失败重试次数
     * @param landscapePage_loopTimes      -- 循环执行次数
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "landscapePage_sampingValue",
            "landscapePage_sampingTimeout",
            "landscapePage_retryTimes",
            "landscapePage_loopTimes",
            "language"
    })
    @Test
    public void landscape_userlist(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String landscapePage_sampingValue,
            int landscapePage_sampingTimeout,
            int landscapePage_retryTimes,
            int landscapePage_loopTimes,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing landscape (user list) ==============================");
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // 点击收藏--工具栏
            p_searchResultPage.func_click_addToList_toobar();
            // 添加到收藏夹
            Thread.sleep(2000);
            ArrayList<String> folderNames = new ArrayList<String>();
            String folderName = String.valueOf(System.currentTimeMillis());
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            // 打开收藏页
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证文件夹
            Assert.assertEquals(p_userListPage.func_verify_userListFolderExists(folderNames), true);
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            // click landscape
            p_userListPage.func_click_landscape();
            this.switchToNewWindow();
            p_landScapePage = new Zhihuiya_landscapePage(d);
            Assert.assertEquals(p_landScapePage.selfcheck(), true);
            // generate result
            boolean landscape_result = true;
            for (int i = 0; i < landscapePage_loopTimes; i++) {
                l.debug("Requesting landscape for userlist");
                landscape_result &= p_landScapePage.func_loadLandscape_userlist(landscapePage_sampingTimeout, landscapePage_retryTimes);
                p_landScapePage.func_nav_close();
            }
            Assert.assertEquals(landscape_result, true);
            //删除文件夹
            // 打开收藏页
            this.switchToOldWindow();
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderNames.get(0));
            //logout
            p_userListPage.func_click_logout();
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
     * 导出excel--基本字段
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @param download_path
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "download_path",
            "wangyi_url",
            "wangyi_uid",
            "wangyi_pwd",
            "wangyi_emailTimeout",
            "emailTitle",
            "language"
    })
    @Test
    public void exportExcel_basicFields(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            String wangyi_url,
            String wangyi_uid,
            String wangyi_pwd,
            int wangyi_emailTimeout,
            String emailTitle,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing exporting basic fields of excel ==============================");
            boolean result = true;
            //登录网易--删除网易邮件
            d.get(wangyi_url);
            pwy_loginPage = new Wangyi_loginPage(d);
            Assert.assertEquals(pwy_loginPage.selfcheck(), true);
            pwy_loginPage.func_login(wangyi_uid, wangyi_pwd);
            pwy_mainPage = new Wangyi_mainPage(d);
            Assert.assertEquals(pwy_mainPage.selfcheck(), true);
            //进入收件箱
            pwy_mainPage.func_click_receiveEmail();
            pwy_mainPage.func_deleteAllEmails();
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            //在7个专利中，按日期导出其中一个
            Calendar cal = Calendar.getInstance();
            String dateString = String.valueOf(cal.get(Calendar.DAY_OF_WEEK));
            searchPage_keyword += dateString;
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            int expPatentAmount = p_searchResultPage.func_get_searchResultNumber_byGroup();
            //获得搜索结果数据--期望值
            List<HashMap<String, String>> expPatentData = p_searchResultPage.func_getTableData_patentList();
            // click export
            p_searchResultPage.func_click_export();
            this.switchToNewWindow();
            // 导出，并获取期望文件名
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            String partialFileName = p_exportPage.func_export(0, 2, download_path, 1, 1, 1, 1, -1, null);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, 180), true);
            Assert.assertEquals((partialFileName != null), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing downloading excel file ==============================");
            // 验证文件是否下载成功
            Thread.sleep(2000);
            String excelName = p_exportPage.func_verify_fileDownload(download_path, partialFileName, "xls", 60);
            if (excelName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                l.debug("Try another file name");
                long partialFileName_int = Long.parseLong(partialFileName);
                partialFileName_int--;
                String partialFileName_bak = String.valueOf(partialFileName_int);
                excelName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileName_bak, "xls", 60);
            }
            if (excelName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                l.debug("Try another file name");
                long partialFileName_int = Long.parseLong(partialFileName);
                partialFileName_int++;
                String partialFileName_bak_2 = String.valueOf(partialFileName_int);
                excelName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileName_bak_2, "xls", 60);
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
            l.info("============================== Testing patent amount in excel ==============================");
            //验证excel中的专利和标题
            //获取excel
            ExcelUtil excel = new ExcelUtil(download_path, excelName, false);
            List<HashMap<String, String>> actPatentData = excel.readExcelData();
            //比较数量
            l.info("Expected patent amount: {}", expPatentAmount);
            l.info("Actual patent amount: {}", actPatentData.size());
            if ((expPatentData.size() == actPatentData.size()) && (expPatentAmount == actPatentData.size())) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent amount in excel is correct");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent amount in excel is incorrect");
                result &= false;
            }
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing patent titles in excel ==============================");
            //验证excel中的PN和标题
            for (HashMap<String, String> expRow : expPatentData) {
                String expPN = expRow.get("Publication Number");
                if (expPN == null) {
                    expPN = expRow.get("公开(公告)号");
                }
                String expTitle = expRow.get("Title");
                if (expTitle == null) {
                    expTitle = expRow.get("标题");
                }
                l.info("Verifying PN: [{}]. Expected title: [{}]", expPN, expTitle);
                for (HashMap<String, String> actRow : actPatentData) {
                    String actPN = actRow.get("Publication  Number");
                    if (actPN == null) {
                        actPN = actRow.get("公开(公告)号");
                    }
                    String actTitle = actRow.get("Title");
                    if (actTitle == null) {
                        actTitle = actRow.get("专利名称");
                    }
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
                d.navigate().refresh();
                Assert.assertEquals(p_patentViewPage.selfcheck(), true);
                p_patentViewPage.func_exit_tip();
                HashMap<String, String> patentInfo = p_patentViewPage.func_get_patentInfoTableData();
                String actual_pn = patentInfo.get("Publication Number");
                if (actual_pn == null) {
                    actual_pn = patentInfo.get("公开(公告)号");
                }
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
            //删除所有已下载文件
            p_exportCompletePage.func_deleteAllDownloadFiles(download_path);
            //logout
            p_patentViewPage.func_click_logout();
            //测试导出邮件链接
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing links in export email ==============================");
            //登录网易--删除网易邮件
            d.get(wangyi_url);
            pwy_loginPage = new Wangyi_loginPage(d);
            Assert.assertEquals(pwy_loginPage.selfcheck(), true);
            pwy_loginPage.func_login(wangyi_uid, wangyi_pwd);
            Thread.sleep(2000);
            pwy_mainPage = new Wangyi_mainPage(d);
            Assert.assertEquals(pwy_mainPage.selfcheck(), true);
            //进入收件箱
            pwy_mainPage.func_click_receiveEmail();
            //验证邮件
            Assert.assertEquals(pwy_mainPage.func_verify_emailExists(emailTitle, wangyi_emailTimeout), true);
            //点击邮件名称
            pwy_mainPage.func_click_email_byTitle(emailTitle);
            //点击邮件链接
            pwy_mainPage.func_click_emailBodyLink_byIndex(0);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing downloading excel file ==============================");
            // 验证文件是否下载成功
            Thread.sleep(2000);
            excelName = p_exportPage.func_verify_fileDownload(download_path, partialFileName, "xls", 60);
            if (excelName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                l.debug("Try another file name");
                long partialFileName_int = Long.parseLong(partialFileName);
                partialFileName_int--;
                String partialFileName_bak = String.valueOf(partialFileName_int);
                excelName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileName_bak, "xls", 60);
            }
            if (excelName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                l.debug("Try another file name");
                long partialFileName_int = Long.parseLong(partialFileName);
                partialFileName_int++;
                String partialFileName_bak_2 = String.valueOf(partialFileName_int);
                excelName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileName_bak_2, "xls", 60);
            }
            if (excelName != null) {
                result &= true;
            } else {
                result &= false;
            }
            //断言，这里不通过，即下载失败，后面就不用再执行了
            Assert.assertEquals(result, true);
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
     * 导出Excel--被引用字段
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @param download_path
     * @param http_url
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "download_path",
            "http_url",
            "language"
    })
    @Test
    public void exportExcel_citedBy(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            String http_url,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing exporting basic fields of excel ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            //发送请求，获取被引用数量
            String reqUrl = String.format("%s/%s", loginPage_url, http_url);
            l.info("Request url: {}", reqUrl);
            d.get(reqUrl);
            String jsonStr = p_searchPage.func_get_bodyText();
            l.debug("Response json string: {}", jsonStr);
            JSONObject json = new JSONObject(jsonStr);
            JSONArray backward = json.getJSONArray("backward");
//			JSONArray forward = json.getJSONArray("forward");
            int expCitedNum = backward.length();
            l.info("Test PN: {}", searchPage_keyword);
            //回到首页
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            // click export
            p_searchResultPage.func_click_export();
            // 导出，并获取期望文件名
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            String partialFileName = p_exportPage.func_export(0, 4, download_path, 1, -1, 0, 0, -1, null);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, 180), true);
            Assert.assertEquals((partialFileName != null), true);
            // 验证文件是否下载成功
            Thread.sleep(2000);
            String excelName = p_exportPage.func_verify_fileDownload(download_path, partialFileName, "xls", 60);
            if (excelName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                l.debug("Try another file name");
                long partialFileName_int = Long.parseLong(partialFileName);
                partialFileName_int--;
                String partialFileName_bak = String.valueOf(partialFileName_int);
                excelName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileName_bak, "xls", 60);
            }
            if (excelName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                l.debug("Try another file name");
                long partialFileName_int = Long.parseLong(partialFileName);
                partialFileName_int++;
                String partialFileName_bak_2 = String.valueOf(partialFileName_int);
                excelName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileName_bak_2, "xls", 60);
            }
            if (excelName != null) {
                result &= true;
            } else {
                result &= false;
            }
            //断言，这里不通过，即下载失败，后面就不用再执行了
            Assert.assertEquals(result, true);
            //验证excel中的专利和标题
            //获取excel
            ExcelUtil excel = new ExcelUtil(download_path, excelName, false);
            List<HashMap<String, String>> actPatentData = excel.readExcelData();
            //比较数量
            l.info("Expected cited by amount: {}", expCitedNum);
            l.info("Actual cited by amount: {}", actPatentData.size() - 1);
            if (expCitedNum == actPatentData.size() - 1) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Cited by amount in excel is correct");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Cited by amount in excel is incorrect");
                result &= false;
            }
            //logout
            p_searchResultPage.func_click_logout();
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
     * 导出PDF
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @param download_path
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "download_path",
            "wangyi_url",
            "wangyi_uid",
            "wangyi_pwd",
            "wangyi_emailTimeout",
            "emailTitle",
            "language"
    })
    @Test
    public void exportPDF(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            String wangyi_url,
            String wangyi_uid,
            String wangyi_pwd,
            int wangyi_emailTimeout,
            String emailTitle,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing exporting PDF ==============================");
            boolean result = true;
            //登录网易--删除网易邮件
            d.get(wangyi_url);
            pwy_loginPage = new Wangyi_loginPage(d);
            Assert.assertEquals(pwy_loginPage.selfcheck(), true);
            pwy_loginPage.func_login(wangyi_uid, wangyi_pwd);
            pwy_mainPage = new Wangyi_mainPage(d);
            Assert.assertEquals(pwy_mainPage.selfcheck(), true);
            //进入收件箱
            pwy_mainPage.func_click_receiveEmail();
            pwy_mainPage.func_deleteAllEmails();
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            //在7个专利中，按日期导出其中一个
            Calendar cal = Calendar.getInstance();
            String dateString = String.valueOf(cal.get(Calendar.DAY_OF_WEEK));
            cal.add(Calendar.DATE, 1);
            String dateString1 = String.valueOf(cal.get(Calendar.DAY_OF_WEEK));
//			searchPage_keyword += dateString;
            searchPage_keyword = String.format("%S%s OR %s%s", searchPage_keyword, dateString, searchPage_keyword, dateString1);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //获得搜索结果数据--期望值
            List<HashMap<String, String>> expPatentData = p_searchResultPage.func_getTableData_patentList();
            ArrayList<String> expPNs = new ArrayList<String>();
            for (HashMap<String, String> row : expPatentData) {
                String expPN = row.get("Publication Number");
                if (expPN == null) {
                    expPN = row.get("公开(公告)号");
                }
                expPNs.add(expPN);
            }
            // click export
            p_searchResultPage.func_click_export();
            this.switchToNewWindow();
            // 导出，并获取期望文件名
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            String partialFileName = p_exportPage.func_export(1, 0, download_path, 0, 1, 1, 2, -1, null);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, 180), true);
            Assert.assertEquals((partialFileName != null), true);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing downloading PDF ==============================");
            //验证PDF是否下载成功
            Thread.sleep(2000);
            String pdfName = p_exportPage.func_verify_fileDownload(download_path, partialFileName, "zip", 60);
            if (pdfName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                l.debug("Try another file name");
                long partialFileName_int = Long.parseLong(partialFileName);
                partialFileName_int--;
                String partialFileName_bak = String.valueOf(partialFileName_int);
                pdfName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileName_bak, "zip", 60);
            }
            if (pdfName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                l.debug("Try another file name");
                long partialFileName_int = Long.parseLong(partialFileName);
                partialFileName_int++;
                String partialFileName_bak_2 = String.valueOf(partialFileName_int);
                pdfName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileName_bak_2, "zip", 60);
            }
            if (pdfName != null) {
                result &= true;
            } else {
                result &= false;
            }
            //验证zip文件内容
            if (pdfName != null) {
                result &= p_exportPage.func_verify_pdfZip(expPNs, null, String.format("%s//%s", download_path, pdfName), 0);
            }
            //删除所有已下载文件
            p_exportCompletePage.func_deleteAllDownloadFiles(download_path);
            //logout
            p_exportPage.func_click_logout();
            //测试导出邮件链接
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing links in export email ==============================");
            //登录网易--删除网易邮件
            d.get(wangyi_url);
            pwy_loginPage = new Wangyi_loginPage(d);
            Assert.assertEquals(pwy_loginPage.selfcheck(), true);
            pwy_loginPage.func_login(wangyi_uid, wangyi_pwd);
            Thread.sleep(2000);
            pwy_mainPage = new Wangyi_mainPage(d);
            Assert.assertEquals(pwy_mainPage.selfcheck(), true);
            //进入收件箱
            pwy_mainPage.func_click_receiveEmail();
            //验证邮件
            Assert.assertEquals(pwy_mainPage.func_verify_emailExists(emailTitle, wangyi_emailTimeout), true);
            //点击邮件名称
            pwy_mainPage.func_click_email_byTitle(emailTitle);
            //点击邮件链接
            pwy_mainPage.func_click_emailBodyLink_byIndex(0);
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing downloading PDF ==============================");
            //验证PDF是否下载成功
            Thread.sleep(2000);
            pdfName = p_exportPage.func_verify_fileDownload(download_path, partialFileName, "zip", 60);
            if (pdfName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                l.debug("Try another file name");
                long partialFileName_int = Long.parseLong(partialFileName);
                partialFileName_int--;
                String partialFileName_bak = String.valueOf(partialFileName_int);
                pdfName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileName_bak, "zip", 60);
            }
            if (pdfName == null) {
                //如果实际文件名为空，则尝试通过备用文件名来查找文件
                l.debug("Try another file name");
                long partialFileName_int = Long.parseLong(partialFileName);
                partialFileName_int++;
                String partialFileName_bak_2 = String.valueOf(partialFileName_int);
                pdfName = p_searchResultPage.func_verify_fileDownload(download_path, partialFileName_bak_2, "zip", 60);
            }
            if (pdfName != null) {
                result &= true;
            } else {
                result &= false;
            }
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
     * 验证搜素结果页PDF文件下载功能--table view
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @param download_path       默认下载目录
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "download_path",
            "language"
    })
    @Test
    public void pDFDownloadOnSearchResultPage_tableView(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing PDF download function on search result page (table view) ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
//			p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // 获取第一行PN
            String pn = p_searchResultPage.func_get_patentNumber_ByIndex(1);
            // 组合出期望文件名
            String pDFFileName = String.format("%s/%s.pdf", download_path, pn);
            l.info("Expected file name is {}", pDFFileName);
            // 删除文件
            FileUtil f = new FileUtil();
            f.deleteFile(pDFFileName);
            // 点击第一行下载链接
            p_searchResultPage.func_click_pDFDownloadLink_ByIndex_tableView(1);
            // 验证文件是否存在
            Thread.sleep(10000);
            int i = 0;
            boolean downloadFlag = true;
            while (!f.doesFileExist(pDFFileName)) {
                if (i < 6) {
                    Thread.sleep(10000);
                    l.debug("waiting for file to be downloaded...");
                    i++;
                } else {
                    l.debug("timeout");
                    downloadFlag = false;
                    break;
                }
            }
            if (downloadFlag) {
                l.info("++++++++++++++++++++++++++++++ Pass -- PDF file is downloaded successfully");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- download failed");
                //如果当前窗口数大于1，则切换到新窗口，并截图
                if (d.getWindowHandles().size() > 1) {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Unexpected window appears");
                }
                t.takeScreenshot(d);
                result &= false;
            }
            //logout
            p_searchResultPage.func_click_logout();
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

    /**
     * 验证搜素结果页PDF文件下载功能--standard view
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @param download_path       默认下载目录
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "download_path",
            "language"
    })
    @Test
    public void pDFDownloadOnSearchResultPage_standardView(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing PDF download function on search result page (standard view) ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // 获取第一行PN
            String pn = p_searchResultPage.func_get_patentNumber_ByIndex(1);
            // 组合出期望文件名
            String pDFFileName = String.format("%s/%s.pdf", download_path, pn);
            l.info("Expected file name is {}", pDFFileName);
            // 删除文件
            FileUtil f = new FileUtil();
            f.deleteFile(pDFFileName);
            // 切换到 standard view
            p_searchResultPage.func_click_view_byRel("standard");
            // 点击第一行下载链接
            p_searchResultPage.func_click_pDFDownloadLink_ByIndex_standardView(0);
            // 验证文件是否存在
            Thread.sleep(10000);
            int i = 0;
            boolean downloadFlag = true;
            while (!f.doesFileExist(pDFFileName)) {
                if (i < 6) {
                    Thread.sleep(10000);
                    l.debug("waiting for file to be downloaded...");
                    i++;
                } else {
                    l.debug("timeout");
                    downloadFlag = false;
                    break;
                }
            }
            if (downloadFlag) {
                l.info("++++++++++++++++++++++++++++++ Pass -- PDF file is downloaded successfully");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- download failed");
                //如果当前窗口数大于1，则切换到新窗口，并截图
                if (d.getWindowHandles().size() > 1) {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Unexpected window appears");
                }
                t.takeScreenshot(d);
                result &= false;
            }
            //logout
            p_searchResultPage.func_click_logout();
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

    /**
     * 从专利查看页下载PDF文档
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @param download_path
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "download_path",
            "language"
    })
    @Test
    public void pDFDownloadOnPatentViewPage(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing PDF download function on patent view page ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // 获取第一行PN
            String pn = p_searchResultPage.func_get_patentNumber_ByIndex(1);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            // 组合出期望文件名
            String pDFFileName = String.format("%s/%s.pdf", download_path, pn);
            l.info("Expected file name is {}", pDFFileName);
            // 删除文件
            FileUtil f = new FileUtil();
            f.deleteFile(pDFFileName);
            // 点击下载链接
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            p_patentViewPage.func_exit_tip();
            p_patentViewPage.func_click_downloadPDF();
            // 验证文件是否存在
            Thread.sleep(10000);
            int i = 0;
            boolean downloadFlag = true;
            while (!f.doesFileExist(pDFFileName)) {
                if (i < 6) {
                    Thread.sleep(10000);
                    l.debug("waiting for file to be downloaded...");
                    i++;
                } else {
                    l.debug("timeout");
                    downloadFlag = false;
                    break;
                }
            }
            if (downloadFlag) {
                l.info("++++++++++++++++++++++++++++++ Pass -- PDF file is downloaded successfully");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- download failed");
                t.takeScreenshot(d);
                result &= false;
            }
            //logout
            p_patentViewPage.func_click_logout();
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

    /**
     * 搜索所有搜索条件（21个）
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "language"
    })
    @Test
    public void searchForAllFields(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing search for all fields ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            //获取搜索字段和关键字
            String[] fields = searchPage_field.split(",");
            String[] keywords = searchPage_keyword.split(",");
            if (fields.length != keywords.length) {
                l.warn("fields' size is {}, but keywords' size is {}", fields.length, keywords.length);
            }
            //循环执行搜索
            for (int i = 0; i < fields.length; i++) {
                d.get(loginPage_url);
                p_searchPage.func_fieldSearch(searchPage_database, fields[i], keywords[i], null);
                p_searchResultPage = new Zhihuiya_searchResultPage(d);
                result &= p_searchResultPage.func_verify_searchResultExist_tableView();
            }
            //logout
            p_searchResultPage.func_click_logout();
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

    /**
     * 验证引用关系
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param http_url
     * @param testPN
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "http_url",
            "testPN",
            "language"
    })
    @Test
    public void citation(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String http_url,
            String testPN,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Citation ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            //发送请求
            //兼容SME
            if (loginPage_url.contains("/patents/index")) {
                loginPage_url = loginPage_url.replace("/patents/index", "");
            }
            String reqUrl = String.format("%s/%s", loginPage_url, http_url);
            l.info("Request url: {}", reqUrl);
            d.get(reqUrl);
            String jsonStr = p_searchPage.func_get_bodyText();
            l.debug("Response json string: {}", jsonStr);
            JSONObject json = new JSONObject(jsonStr);
            JSONArray backward = json.getJSONArray("backward");
            JSONArray forward = json.getJSONArray("forward");
            l.info("Test PN: {}", testPN);
            if (forward.length() > 0) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Cites list is not empty, and size is {}", forward.length());
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Cites list is empty");
                result &= false;
            }
            if (backward.length() > 0) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Cited by list is not empty, and size is {}", backward.length());
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Cited by list is empty");
                result &= false;
            }
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

    /**
     * 验证专利家族是否有数据
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "language"
    })
    @Test
    public void patentFamily(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing patent family ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // 获取第一行的第一个链接
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            p_patentViewPage.func_exit_tip();
            // 点击专利家族
            p_patentViewPage.func_click_patentFamily();
            result &= p_patentViewPage.verify_familyRender();
            //logout
            p_patentViewPage.func_click_logout();
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

    /**
     * 验证专利法律状态
     *
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "language"
    })
    @Test
    public void legalStatus(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing patent legal status ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // 获取第一行的第一个链接
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            p_patentViewPage.func_exit_tip();
            // 点击法律状态
            p_patentViewPage.func_click_legalStatus();
            result &= p_patentViewPage.verify_legalStatus();
            //logout
            p_patentViewPage.func_click_logout();
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

    /**
     * 验证已保存的搜索是否存在
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param savedSearch_searchName
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "savedSearch_searchName",
            "language"
    })
    @Test
    public void existingSavedSearch(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String savedSearch_searchName,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing existing saved search ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // 进入以保存搜索页
            p_searchPage.func_goto_savedSearchPage();
            p_savedSearchPage = new Zhihuiya_savedSearchPage(d);
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            result &= p_savedSearchPage.func_verify_savedSearch(savedSearch_searchName, null);
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

    /**
     * 验证已保存的邮件提醒是否存在
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param savedSearch_emailAlertName
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "savedSearch_emailAlertName",
            "language"
    })
    @Test
    public void existingEmailAlert(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String savedSearch_emailAlertName,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing existing email alert ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // 进入以保存搜索页
            p_searchPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            result &= p_emailAlertPage.func_verify_emailAlertExists(savedSearch_emailAlertName);
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

    /**
     * 验证已保存的自建库是否存在
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param savedSearch_customDatabaseName
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "savedSearch_customDatabaseName",
            "language"
    })
    @Test
    public void existingCustomDatabase(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String savedSearch_customDatabaseName,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing existing custom database ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // 进入以保存搜索页
            p_searchPage.func_goto_customDatabasePage();
            p_customDatabasePage = new Zhihuiya_customDatabasePage(d);
            Assert.assertEquals(p_customDatabasePage.selfcheck(), true);
            result &= p_customDatabasePage.func_verify_customDatabase(savedSearch_customDatabaseName);
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

    /**
     * 验证已保存收藏夹是否存在
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param savedSearch_userListFolderName
     * @param savedSearch_userListPN
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "savedSearch_userListFolderName",
            "savedSearch_userListPN",
            "language"
    })
    @Test
    public void existingUserList(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String savedSearch_userListFolderName,
            String savedSearch_userListPN,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing existing user list ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // 进入以保存搜索页
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证文件夹
            ArrayList<String> folders = new ArrayList<String>();
            folders.add(savedSearch_userListFolderName);
            //点击全部展开
//			Thread.sleep(5000);
            p_userListPage.func_click_expandAll();
            Assert.assertEquals(p_userListPage.func_verify_userListFolderExists(folders), true);
            //验证专利
            p_userListPage.func_click_userListFolder(savedSearch_userListFolderName);
            Thread.sleep(10000);
            result &= p_userListPage.func_verify_patentExistInUserList(savedSearch_userListPN);
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

    /**
     * 查询landscape后，验证历史记录
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @param landscapePage_sampingValue
     * @param landscapePage_sampingTimeout
     * @param landscapePage_retryTimes
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "landscapePage_sampingValue",
            "landscapePage_sampingTimeout",
            "landscapePage_retryTimes",
            "language"
    })
    @Test
    public void landscapeHistory(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String landscapePage_sampingValue,
            int landscapePage_sampingTimeout,
            int landscapePage_retryTimes,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing landscape history ==============================");
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            // click landscape
            p_searchResultPage.func_click_landscape();
            this.switchToNewWindow();
            p_landScapePage = new Zhihuiya_landscapePage(d);
            Assert.assertEquals(p_landScapePage.selfcheck(), true);
            // generate result
            boolean landscape_result = true;
            String[] sampingValues = landscapePage_sampingValue.split(",");
            // 生成随机查询
            Random r = new java.util.Random();
            int randomIndex = Math.abs(r.nextInt()) % 10000;
            String keyword = String.valueOf(randomIndex);
            for (String sampingValue : sampingValues) {
                l.debug("Requesting landscape for value {}", sampingValue);
                landscape_result &= p_landScapePage.func_loadLandscape(keyword, sampingValue, landscapePage_sampingTimeout, landscapePage_retryTimes);
                p_landScapePage.func_nav_close();
            }
            //验证搜索历史记录
            d.close();
            this.switchToOldWindow();
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            Thread.sleep(10000);
            p_searchResultPage.func_goto_historyPage();
            p_historyPage = new Zhihuiya_historyPage(d);
            Assert.assertEquals(p_historyPage.selfcheck(), true);
            landscape_result = p_historyPage.func_verify_contextExistInHistory(String.format("Landscape %s", keyword));
            //assert
            Assert.assertEquals(landscape_result, true);
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
     * 矩阵分析
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param savedSearch_userListFolderName
     * @param savedSearch_userListPN
     * @param analysis_Timeout
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "savedSearch_userListFolderName",
            "savedSearch_userListPN",
            "analysis_Timeout",
            "language"
    })
    @Test
    public void matrixAnalysis(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String savedSearch_userListFolderName,
            String savedSearch_userListPN,
            int analysis_Timeout,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing matrix analysis ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // 进入以保存搜索页
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证文件夹
            ArrayList<String> folders = new ArrayList<String>();
            folders.add(savedSearch_userListFolderName);
            //点击全部展开
//			Thread.sleep(5000);
            p_userListPage.func_click_expandAll();
            Assert.assertEquals(p_userListPage.func_verify_userListFolderExists(folders), true);
            //验证专利
            p_userListPage.func_click_userListFolder(savedSearch_userListFolderName);
            Thread.sleep(10000);
//			Assert.assertEquals(p_userListPage.func_verify_patentInUserList(savedSearch_userListPN), true);
            //点击矩阵分析
            p_userListPage.func_click_matrixAnalysis();
            this.switchToNewWindow();
            //分析矩阵
            p_matrixAnalysisPage = new Zhihuiya_matrixAnalysisPage(d);
            Assert.assertEquals(p_matrixAnalysisPage.selfcheck(), true);
            p_matrixAnalysisPage.func_createChart();
            //查看分析结果
            Assert.assertEquals(p_matrixAnalysisPage.func_verify_analysisResult(analysis_Timeout, 2), true);
            //查看分析图表
            p_matrixAnalysisPage.func_click_viewChart();
            p_viewChartPage = new Zhihuiya_viewChartPage(d);
            if (p_viewChartPage.selfcheck()) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Chart view page is opened successfully");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Chart view page is not opened correctly");
                t.takeScreenshot(d);
                result &= false;
            }
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

    /**
     * 矩阵分析--新
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param analysis_Timeout
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "analysis_Timeout",
            "language"
    })
    @Test
    public void matrixAnalysis_2(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int analysis_Timeout,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing matrix analysis (new) ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // 点击收藏--工具栏
            p_searchResultPage.func_click_addToList_toobar();
            // 添加到收藏夹
            Thread.sleep(2000);
            ArrayList<String> folderNames = new ArrayList<String>();
            String folderName = String.valueOf(System.currentTimeMillis());
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            // 打开收藏页
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证文件夹
            Assert.assertEquals(p_userListPage.func_verify_userListFolderExists(folderNames), true);
            //点击文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            //点击矩阵分析
            p_userListPage.func_click_matrixAnalysis();
            this.switchToNewWindow();
            //分析矩阵
            p_matrixAnalysisPage = new Zhihuiya_matrixAnalysisPage(d);
            Assert.assertEquals(p_matrixAnalysisPage.selfcheck(), true);
            p_matrixAnalysisPage.func_createChart();
            //查看分析结果
            Assert.assertEquals(p_matrixAnalysisPage.func_verify_analysisResult(analysis_Timeout, 2), true);
            //查看分析图表
            p_matrixAnalysisPage.func_click_viewChart();
            p_viewChartPage = new Zhihuiya_viewChartPage(d);
            if (p_viewChartPage.selfcheck()) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Chart view page is opened successfully");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Chart view page is not opened correctly");
                t.takeScreenshot(d);
                result &= false;
            }
            // assert
            Assert.assertEquals(result, true);
            //删除文件夹
            // 打开收藏页
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderNames.get(0));
            //logout
            p_userListPage.func_click_logout();
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
     * 发送单个邮件
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchResult_emailAddress",
            "wangyi_url",
            "wangyi_uid",
            "wangyi_pwd",
            "wangyi_emailTimeout",
            "language"
    })
    @Test
    public void email_single_tableView(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String searchResult_emailAddress,
            String wangyi_url,
            String wangyi_uid,
            String wangyi_pwd,
            int wangyi_emailTimeout,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing sending email (table view) ==============================");
            boolean result = true;
            //登录网易--删除网易邮件
            d.get(wangyi_url);
            pwy_loginPage = new Wangyi_loginPage(d);
            Assert.assertEquals(pwy_loginPage.selfcheck(), true);
            pwy_loginPage.func_login(wangyi_uid, wangyi_pwd);
            pwy_mainPage = new Wangyi_mainPage(d);
            Assert.assertEquals(pwy_mainPage.selfcheck(), true);
            //进入收件箱
            pwy_mainPage.func_click_receiveEmail();
            pwy_mainPage.func_deleteAllEmails();
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
//			p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // 获取第一行PN
//			String pn = p_searchResultPage.func_get_patentNumber_ByIndex(1);
            // 点击发送Email
            p_searchResultPage.func_click_emailLink_ByIndex_tableView(1);
            // 验证Email对话框
            Thread.sleep(2000);
            result &= p_searchResultPage.func_verify_emailDialog();
            // 发送Email并验证（待定，需要到邮箱验证邮件，有延迟，效率低）
            //发送邮件，并记录邮件标题
            String emailTitle = p_searchResultPage.func_sendEmail(searchResult_emailAddress);
            //logout
            p_searchResultPage.func_click_logout();
            //验证邮件
            //登录网易--删除网易邮件
            d.get(wangyi_url);
            pwy_loginPage = new Wangyi_loginPage(d);
            Assert.assertEquals(pwy_loginPage.selfcheck(), true);
            pwy_loginPage.func_login(wangyi_uid, wangyi_pwd);
            Thread.sleep(2000);
            pwy_mainPage = new Wangyi_mainPage(d);
            Assert.assertEquals(pwy_mainPage.selfcheck(), true);
            //进入收件箱
            pwy_mainPage.func_click_receiveEmail();
            //验证邮件
            result &= pwy_mainPage.func_verify_emailExists(emailTitle, wangyi_emailTimeout);
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

    /**
     * 添加到收藏夹--table view
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchResultPage_numberToAdd",
            "searchResultPage_userListFolderLevel",
            "language"
    })
    @Test
    public void addToList_toolbar(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int searchResultPage_numberToAdd,
            int searchResultPage_userListFolderLevel,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing add to list (toolbar) ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            // 逐行获取PN并勾选
            List<String> pns = new ArrayList<String>();
            String pn = null;
            for (int i = 1; i <= searchResultPage_numberToAdd; i++) {
                pn = p_searchResultPage.func_get_patentNumber_ByIndex(i);
                p_searchResultPage.func_click_patentCheckbox_byIndex(i);
                l.info("Add PN: [{}]", pn);
                pns.add(pn);
            }
            // 点击收藏--工具栏
            p_searchResultPage.func_click_addToList_toobar();
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
//			Thread.sleep(2000);
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //点击全部展开
//			Thread.sleep(5000);
            p_userListPage.func_click_expandAll();
            //验证文件夹
            p_userListPage.func_click_userListFolder(folderNames.get(folderNames.size() - 1));
            //验证专利
            Thread.sleep(10000);
            for (String currentPN : pns) {
                result &= p_userListPage.func_verify_patentExistInUserList(currentPN);
            }
            p_userListPage.func_delete_userListFolder(folderNames.get(0));
            //logout
            p_userListPage.func_click_logout();
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

    /**
     * 添加到收藏夹--单条、按钮
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "searchResultPage_userListFolderLevel",
            "language"
    })
    @Test
    public void addToList_tableView(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int searchResultPage_userListFolderLevel,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing single add to list (table view) ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
//			p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
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
//			Thread.sleep(2000);
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
            //logout
            p_userListPage.func_click_logout();
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

    /**
     * 勾选部分数据库后，验证搜索和分析
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param searchPage_keyword
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "searchPage_keyword",
            "language"
    })
    @Test
    public void database(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            int language
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing database (search & analysis) ==============================");
            boolean result = true;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            // search
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            //获取搜索结果数量
            int expResultNum = p_searchResultPage.func_get_searchResultNumber();
            //验证搜索结果PN仅包含关键字
            result &= p_searchResultPage.func_verify_searchResult_tableView_usa();
            //点击高级分析
            p_searchResultPage.func_click_analyzePatent();
            Thread.sleep(2000);
            //获取高级分析结果数量，与搜索结果对比
            int actResultNum = p_searchResultPage.func_get_searchResultNumber();
            l.info("Result number on search result page: {}", expResultNum);
            l.info("Result number on analysis page: {}", actResultNum);
            if (expResultNum == actResultNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Result number on analysis page is correct");
                result &= true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Result number on analysis page is incorrect");
                t.takeScreenshot(d);
                result &= false;
            }
            //logout
            p_searchResultPage.func_click_logout();
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

    //Image Discovery

    /**
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "imageSearchPage_keyword",
            "language"
    })
    @Test
    public void imageDiscovery(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String imageSearchPage_keyword,
            int language
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing Image Discovery ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            // 切换语言
            p_searchPage.func_switch_language(language);
            //点击图像探索链接
            p_searchPage.func_click_imageDiscover();
            p_imageDiscoveryPage = new Zhihuiya_imageDiscoveryPage(d);
            Assert.assertEquals(p_imageDiscoveryPage.selfcheck(), true);
            //通过关键字搜索
            p_imageDiscoveryPage.func_searchKeyword(imageSearchPage_keyword);
            //验证结果
            p_imageSearchResultPage = new Zhihuiya_imageSearchResultPage(d);
            Assert.assertEquals(p_imageSearchResultPage.selfcheck(), true);
            result &= p_imageSearchResultPage.func_verify_imageSearchResultExist();
            //点击相似图片搜索
            p_imageSearchResultPage.func_click_discover_ByIndex(0);
            //验证结果
            p_imageSearchResultPage = new Zhihuiya_imageSearchResultPage(d);
            Assert.assertEquals(p_imageSearchResultPage.selfcheck(), true);
            result &= p_imageSearchResultPage.func_verify_imageSearchResultExist();
            //logout
            p_imageSearchResultPage.func_click_logout();
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Image Discovery", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 测试solr数据
     *
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param searchPage_database
     * @param searchPage_field
     * @param solr_data
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "searchPage_database",
            "searchPage_field",
            "solr_data"
    })
    @Test
    public void solrData(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String solr_data
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing solr data ({}) ==============================", loginPage_url);
            boolean result = true;
            //解析输入数据
            //1.获取原始字符串，按空格分隔
            l.debug(solr_data);
            String[] strs = solr_data.split(" "); //按空格分隔的字符串数组，未去掉空元素
            l.debug(strs.length);
            List<String> strs2 = Arrays.asList(strs); //按空格分隔的字符串List，未去掉空元素
            l.debug(strs2.size());
            //2.去掉空元素
            List<String> strs3 = new ArrayList<String>(); //按空格分隔的字符串List，已去掉空元素
            for (String s : strs2) {
                if (!s.equals("")) {
                    strs3.add(s);
                }
            }
            l.debug(strs3.size());
            //3.转换成HashMap
            HashMap<String, Integer> countryData = new HashMap<String, Integer>(); //国家--数量，键值对
//			int pairNum = strs3.size() / 2;
            int pairNum = strs3.size() / 3;
            for (int i = 0; i < pairNum; i++) {
//				String key = strs3.get(i * 2).split(":")[0];
                String key = strs3.get(i * 3).split(":")[0];
                l.debug("Key -- [{}]", key);
//				int value = Integer.parseInt(strs3.get(i * 2 + 1));
                int value = Integer.parseInt(strs3.get(i * 3 + 1));
                l.debug("Value -- [{}]", value);
                countryData.put(key, value);
            }
            l.debug(countryData.size());
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //循环执行搜索
            for (String key : countryData.keySet()) {
                l.info("Verifying search result number for country [{}]", key);
                d.get(loginPage_url);
                p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, key, null);
                p_searchResultPage = new Zhihuiya_searchResultPage(d);
                if (!p_searchResultPage.selfcheck()) {
                    continue;
                }
                //验证搜索结果数量
                int actNum = p_searchResultPage.func_get_searchResultNumber();
                if (actNum == countryData.get(key)) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- Expected number: [{}]. Actual number: [{}]", countryData.get(key), actNum);
                    result &= true;
                } else {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [{}]: 期望: {}, 实际: {};", key, countryData.get(key), actNum);
                    t.takeScreenshot(d);
                    result &= false;
                }
            }
            //logout
            p_searchResultPage.func_click_logout();
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

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "assignee"
    })
    @Test
    public void ipReport(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String assignee
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing ipReport for assignee [{}] ==============================", assignee);
            boolean result = false;
            // login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            String url = String.format("%s/ip_report/assignee/settings?ASSIGNEE=%s", loginPage_url, assignee);
            l.info(url);
            d.get(url);
            d.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            d.findElement(By.className("jom-buttonGroup")).findElement(By.tagName("button")).click();
            //开始计时
            long startTime = System.currentTimeMillis();
            //启动各个线程，检查各个图表
            IpReportThread t_4_1 = new IpReportThread("1.1 PUBLICATION TRENDS", d, By.id("api4_1"), By.tagName("svg"), startTime);
            t_4_1.start();
            IpReportThread t_4_1_2 = new IpReportThread("1.2 PATENTING ACTIVITY TRENDS COMPARISON", d, By.id("api4_1_2"), By.tagName("table"), startTime);
            t_4_1_2.start();
            IpReportThread t_5_1_2 = new IpReportThread("2.Patent Type Distribution", d, By.id("api5_1_2"), By.tagName("div"), startTime);
            t_5_1_2.start();
            IpReportThread t_6_1 = new IpReportThread("3.1 PATENT DISTRIBUTION", d, By.id("api6_1"), By.tagName("svg"), startTime);
            t_6_1.start();
            IpReportThread t_6_1_2 = new IpReportThread("3.2 PATENT DISTRIBUTION COMPARISON", d, By.id("api6_1_2"), By.tagName("table"), startTime);
            t_6_1_2.start();
            IpReportThread t_6_2 = new IpReportThread("3.3 PATENT ACTIVITY BY GEOGRAPHY", d, By.id("api6_2"), By.tagName("svg"), startTime);
            t_6_2.start();
            IpReportThread t_7_1 = new IpReportThread("4.1 IPC ClASS DISTRIBUTION", d, By.id("api7_1"), By.tagName("svg"), startTime);
            t_7_1.start();
            IpReportThread t_7_2 = new IpReportThread("4.2 PATENT ACTIVITY OF TOP IPCs", d, By.id("api7_2"), By.tagName("svg"), startTime);
            t_7_2.start();
            IpReportThread t_1_1 = new IpReportThread("5.1 IP STRATEGIC RADAR MAP", d, By.id("api1_1"), By.tagName("svg"), startTime);
            t_1_1.start();
            IpReportThread t_1_3 = new IpReportThread("5.2 IP TECHNOLOGY STRATEGIC MAP", d, By.id("api1_3"), By.tagName("svg"), startTime);
            t_1_3.start();
            IpReportThread t_1_3_2 = new IpReportThread("5.3 IP BUSINESS STRATEGIC MAP", d, By.id("api1_3_2"), By.tagName("svg"), startTime);
            t_1_3_2.start();
            IpReportThread t_2_1 = new IpReportThread("6.Technology Landscape Map", d, By.id("wraper_api2_1"), By.tagName("object"), startTime);
            t_2_1.start();
            IpReportThread t_3_1 = new IpReportThread("7.Claim Analysis Landscape", d, By.id("api3_1"), By.tagName("svg"), startTime);
            t_3_1.start();
            IpReportThread t_8_1 = new IpReportThread("8.1 IP TOP 10 INVENTORS", d, By.id("api8_1"), By.tagName("svg"), startTime);
            t_8_1.start();
            IpReportThread t_8_2 = new IpReportThread("8.2 PATENT ACTIVITY OF TOP 10 INVENTORS", d, By.id("api8_2"), By.tagName("svg"), startTime);
            t_8_2.start();
            IpReportThread t_8_3 = new IpReportThread("8.3 Publication trend of top 10 inventors", d, By.id("api8_3"), By.tagName("table"), startTime);
            t_8_3.start();
            IpReportThread t_8_4 = new IpReportThread("8.4 TECH FOCUS OF TOP 10 INVENTORS", d, By.id("api8_4"), By.tagName("table"), startTime);
            t_8_4.start();
            IpReportThread t_9_1 = new IpReportThread("9.1 TOP 10 CO-ASSIGNEES", d, By.id("api9_1"), By.tagName("svg"), startTime);
            t_9_1.start();
            IpReportThread t_9_1_2 = new IpReportThread("9.2 PATENT ACTIVITY OF TOP 10 CO-ASSIGNEES", d, By.id("api9_1_2"), By.tagName("table"), startTime);
            t_9_1_2.start();
            IpReportThread t_9_3 = new IpReportThread("9.3 TECH FOCUS OF TOP 10 CO-ASSIGNEES", d, By.id("api9_3"), By.tagName("table"), startTime);
            t_9_3.start();
            IpReportThread t_10_1 = new IpReportThread("10.1 YEARLY ABANDONMENT RATE", d, By.id("api10_1"), By.tagName("svg"), startTime);
            t_10_1.start();
            IpReportThread t_10_2 = new IpReportThread("10.2 YEARLY RENEWAL BEHAVIOR", d, By.id("api10_1_2"), By.tagName("svg"), startTime);
            t_10_2.start();
            IpReportThread t_11_1 = new IpReportThread("11.Special Patents", d, By.id("api11_1"), By.tagName("table"), startTime);
            t_11_1.start();

            int i = 0;
            while (((t_4_1.getStatus() == 0)
                    || (t_4_1_2.getStatus() == 0)
                    || (t_5_1_2.getStatus() == 0)
                    || (t_6_1.getStatus() == 0)
                    || (t_6_1_2.getStatus() == 0)
                    || (t_6_2.getStatus() == 0)
                    || (t_7_1.getStatus() == 0)
                    || (t_7_2.getStatus() == 0)
                    || (t_1_1.getStatus() == 0)
                    || (t_1_3.getStatus() == 0)
                    || (t_1_3_2.getStatus() == 0)
                    || (t_2_1.getStatus() == 0)
                    || (t_3_1.getStatus() == 0)
                    || (t_8_1.getStatus() == 0)
                    || (t_8_2.getStatus() == 0)
                    || (t_8_3.getStatus() == 0)
                    || (t_8_4.getStatus() == 0)
                    || (t_9_1.getStatus() == 0)
                    || (t_9_1_2.getStatus() == 0)
                    || (t_9_3.getStatus() == 0)
                    || (t_10_1.getStatus() == 0)
                    || (t_10_2.getStatus() == 0)
                    || (t_11_1.getStatus() == 0)
            )
                    && (i < 330)) {
                l.debug("Waiting for total status...");
                Thread.sleep(1000);
                i++;
            }
//			Thread.sleep(300000);

            if ((t_4_1.getStatus() == 1)
                    && (t_4_1_2.getStatus() == 1)
                    && (t_5_1_2.getStatus() == 1)
                    && (t_6_1.getStatus() == 1)
                    && (t_6_1_2.getStatus() == 1)
                    && (t_6_2.getStatus() == 1)
                    && (t_7_1.getStatus() == 1)
                    && (t_7_2.getStatus() == 1)
                    && (t_1_1.getStatus() == 1)
                    && (t_1_3.getStatus() == 1)
                    && (t_1_3_2.getStatus() == 1)
                    && (t_2_1.getStatus() == 1)
                    && (t_3_1.getStatus() == 1)
                    && (t_8_1.getStatus() == 1)
                    && (t_8_2.getStatus() == 1)
                    && (t_8_3.getStatus() == 1)
                    && (t_8_4.getStatus() == 1)
                    && (t_9_1.getStatus() == 1)
                    && (t_9_1_2.getStatus() == 1)
                    && (t_9_3.getStatus() == 1)
                    && (t_10_1.getStatus() == 1)
                    && (t_10_2.getStatus() == 1)
                    && (t_11_1.getStatus() == 1)
                    ) {
                result = true;
                l.info("++++++++++++++++++++++++++++++ Passed");
            } else {
                result = false;
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Failed");
            }
//			//结束线程
//			t_4_1.interrupt();
//			t_4_1_2.interrupt();
//			t_5_1_2.interrupt();
//			t_6_1.interrupt();
//			t_6_1_2.interrupt();
//			t_6_2.interrupt();
//			t_7_1.interrupt();
//			t_7_2.interrupt();
//			t_1_1.interrupt();
//			t_1_3.interrupt();
//			t_1_3_2.interrupt();
//			t_2_1.interrupt();
//			t_3_1.interrupt();
//			t_8_1.interrupt();
//			t_8_2.interrupt();
//			t_8_3.interrupt();
//			t_8_4.interrupt();
//			t_9_1.interrupt();
//			t_9_1_2.interrupt();
//			t_9_3.interrupt();
//			t_10_1.interrupt();
//			t_10_2.interrupt();
//			t_11_1.interrupt();

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
}