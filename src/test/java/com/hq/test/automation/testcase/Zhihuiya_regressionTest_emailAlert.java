package com.hq.test.automation.testcase;

import java.io.IOException;
import java.util.HashMap;

import com.hq.test.automation.pageobject.*;
import com.hq.test.framework.page.BasePage;
import com.hq.test.framework.testcase.BaseTestcase;

import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Zhihuiya_regressionTest_emailAlert extends BaseTestcase {

    BasePage p_basePage = new BasePage();
    Zhihuiya_loginPage p_loginPage;
    Zhihuiya_searchPage p_searchPage;
    Zhihuiya_searchResultPage p_searchResultPage;
    Zhihuiya_emailAlertPage p_emailAlertPage;
    Zhihuiya_userListPage p_userListPage;
    Wangyi_loginPage pwy_loginPage;
    Wangyi_mainPage pwy_mainPage;

    String loginPage_url;
    String loginPage_uid;
    String loginPage_pwd;
    int language;

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "language"
    })
    @BeforeClass
    public void init(
            String p_loginPage_url,
            String p_loginPage_uid,
            String p_loginPage_pwd,
            int p_language
    ) {
        l.entry();
        //初始化变量
        loginPage_url = p_loginPage_url;
        loginPage_uid = p_loginPage_uid;
        loginPage_pwd = p_loginPage_pwd;
        language = p_language;
        l.exit();
    }

    /**
     * 公共方法 -- 登录+切换语言*
     */
    public void loginAndSwitchLanguage() {
        l.entry();
        //登录
        d.get(loginPage_url);
        p_loginPage = new Zhihuiya_loginPage(d);
        p_loginPage.func_login(loginPage_uid, loginPage_pwd);
        p_searchPage = new Zhihuiya_searchPage(d);
        Assert.assertEquals(p_searchPage.selfcheck(), true);
        //切换语言
        p_searchPage.func_switch_language(language);
        l.exit();
    }

    //================================================== Email Alert New ==================================================

    /**
     * 从邮件提醒页创建邮件提醒 *
     *
     * @param emailAlertName 名称
     * @param emailAlertType 类型：0 - patent update, 1 - legal status
     * @param minObjType     监控对象：0 - query, 1 - list
     * @param query          搜索语句
     * @param listIndex      收藏夹下拉列表索引
     * @param isOtherEmail   是否勾选其它收件箱：0 - 不勾选, 1 - 勾选
     * @param otherEmail     其它邮箱列表
     * @param frequency      频率：0 - 每周, 1 - 每月
     * @param frequencyIndex 频率下拉框索引
     * @param duration       过期：0 - 1 month, 1 - 3 months, 2 - never expire
     * @param content        内容选项：0 - 所有专利, 1 - 最近更新
     * @param stemming       截词开关：0 - 关, 1 - 开
     * @throws IOException
     */
    @Parameters({
            "emailAlertName",
            "emailAlertType",
            "minObjType",
            "query",
            "listIndex",
            "isOtherEmail",
            "otherEmail",
            "frequency",
            "frequencyIndex",
            "duration",
            "content",
            "stemming"
    })
    @Test
    public void emailAlert_createEmailAlert_emailAlertPage(
            String emailAlertName,
            int emailAlertType,
            int minObjType,
            String query,
            int listIndex,
            int isOtherEmail,
            String otherEmail,
            int frequency,
            int frequencyIndex,
            int duration,
            int content,
            int stemming
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //变量
            int patentNumber = 0;
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== EA - Email Alert can be created correctly (create from email alert page) ==============================");
            loginAndSwitchLanguage();
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            if ((stemming == 0) || (stemming == 1)) {
                //搜索query设置英文截词
                p_searchPage.func_searchForQuery(query);
                p_searchResultPage = new Zhihuiya_searchResultPage(d);
                Assert.assertEquals(p_searchResultPage.selfcheck(), true);
                p_searchResultPage.func_config_englishStemming(stemming);
                if (minObjType == 0) {
                    //如果监控对象为搜索语句，记录搜索结果数
                    Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
                    patentNumber = p_searchResultPage.func_get_searchResultNumber();
                }
            }
            if (minObjType == 1) {
                //如果监控对象是收藏夹，记录收藏夹中的专利数量
                p_searchResultPage.func_goto_userListPage();
                p_userListPage = new Zhihuiya_userListPage(d);
                Assert.assertEquals(p_userListPage.selfcheck(), true);
                p_userListPage.func_click_userListFolder(listIndex - 1);
                Thread.sleep(5000);
                patentNumber = p_userListPage.func_get_NumPatent();
            }
            //进入邮件提醒页面
            p_searchPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            //删除老邮件提醒
            p_emailAlertPage.func_delete_emailAlert(emailAlertName);
            //创建邮件提醒
            if (emailAlertType == 0) {
                p_emailAlertPage.func_click_newEmailAlert_pu();
            } else if (emailAlertType == 1) {
                p_emailAlertPage.func_click_newEmailAlert_ls();
            }
            p_emailAlertPage.func_createOrEdit_emailAlert(emailAlertName, minObjType, query, listIndex, isOtherEmail, otherEmail, frequency, frequencyIndex, duration, content);
            //验证结果
            //是否存在
            Assert.assertEquals(p_emailAlertPage.func_verify_emailAlertExists(emailAlertName), true);
            //验证详细数据(邮件提醒列表页)
            //期望数据
            HashMap<String, String> expAlertData = new HashMap<>();
            expAlertData.put("name", emailAlertName);
            expAlertData.put("type", String.valueOf(emailAlertType));
            expAlertData.put("obj", String.valueOf(minObjType));
            expAlertData.put("isOtherEmail", String.valueOf(isOtherEmail));
            expAlertData.put("patentNumber", String.valueOf(patentNumber));
            //实际数据
            HashMap<String, String> actAlertData = p_emailAlertPage.func_get_emailAlertData(emailAlertName);
            //验证数据
            result &= p_emailAlertPage.func_verify_emailAlertData(expAlertData, actAlertData);
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "CreateEmailAlertFromEmailAlertPage", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 从搜索结果页创建邮件提醒 *
     *
     * @param emailAlertName 名称
     * @param emailAlertType 类型：0 - patent update, 1 - legal status
     * @param minObjType     监控对象：0 - query, 1 - list
     * @param query          搜索语句
     * @param listIndex      收藏夹下拉列表索引
     * @param isOtherEmail   是否勾选其它收件箱：0 - 不勾选, 1 - 勾选
     * @param otherEmail     其它邮箱列表
     * @param frequency      频率：0 - 每周, 1 - 每月
     * @param frequencyIndex 频率下拉框索引
     * @param duration       过期：0 - 1 month, 1 - 3 months, 2 - never expire
     * @param content        内容选项：0 - 所有专利, 1 - 最近更新
     * @param stemming       截词开关：0 - 关, 1 - 开
     * @throws IOException
     */
    @Parameters({
            "emailAlertName",
            "emailAlertType",
            "minObjType",
            "query",
            "listIndex",
            "isOtherEmail",
            "otherEmail",
            "frequency",
            "frequencyIndex",
            "duration",
            "content",
            "stemming"
    })
    @Test
    public void emailAlert_createEmailAlert_searchResultPage(
            String emailAlertName,
            int emailAlertType,
            int minObjType,
            String query,
            int listIndex,
            int isOtherEmail,
            String otherEmail,
            int frequency,
            int frequencyIndex,
            int duration,
            int content,
            int stemming
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //变量
            int patentNumber = 0;
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== EA - Email Alert can be created correctly (create from search result page) ==============================");
            loginAndSwitchLanguage();
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            if ((stemming == 0) || (stemming == 1)) {
                //搜索query设置英文截词
                p_searchPage.func_searchForQuery(query);
                p_searchResultPage = new Zhihuiya_searchResultPage(d);
                Assert.assertEquals(p_searchResultPage.selfcheck(), true);
                p_searchResultPage.func_config_englishStemming(stemming);
                if (minObjType == 0) {
                    //如果监控对象为搜索语句，记录搜索结果数
                    Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
                    patentNumber = p_searchResultPage.func_get_searchResultNumber();
                }
            }
            if (minObjType == 1) {
                //如果监控对象是收藏夹，记录收藏夹中的专利数量
                p_searchResultPage.func_goto_userListPage();
                p_userListPage = new Zhihuiya_userListPage(d);
                Assert.assertEquals(p_userListPage.selfcheck(), true);
                p_userListPage.func_click_userListFolder(listIndex - 1);
                Thread.sleep(5000);
                patentNumber = p_userListPage.func_get_NumPatent();
            }
            //进入邮件提醒页面
            p_searchPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            //删除老邮件提醒
            p_emailAlertPage.func_delete_emailAlert(emailAlertName);
            //搜索query
            p_emailAlertPage.func_searchForQuery(query);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //创建邮件提醒
            if (emailAlertType == 0) {
                p_searchResultPage.func_click_emailAlert();
            } else if (emailAlertType == 1) {
                //预留，当前网站无此逻辑
            }
            switchToNewWindow();
            p_emailAlertPage.func_createOrEdit_emailAlert(emailAlertName, minObjType, query, listIndex, isOtherEmail, otherEmail, frequency, frequencyIndex, duration, content);
            //验证结果
            //是否存在
            Assert.assertEquals(p_emailAlertPage.func_verify_emailAlertExists(emailAlertName), true);
            //验证详细数据(邮件提醒列表页)
            //期望数据
            HashMap<String, String> expAlertData = new HashMap<>();
            expAlertData.put("name", emailAlertName);
            expAlertData.put("type", String.valueOf(emailAlertType));
            expAlertData.put("obj", String.valueOf(minObjType));
            expAlertData.put("isOtherEmail", String.valueOf(isOtherEmail));
            expAlertData.put("patentNumber", String.valueOf(patentNumber));
            //实际数据
            HashMap<String, String> actAlertData = p_emailAlertPage.func_get_emailAlertData(emailAlertName);
            //验证数据
            result &= p_emailAlertPage.func_verify_emailAlertData(expAlertData, actAlertData);
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "CreateEmailAlertFromEmailAlertPage", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Parameters({
            "emailAlertName",
            "emailAlertType",
            "minObjType",
            "query",
            "listIndex",
            "isOtherEmail",
            "otherEmail",
            "frequency",
            "frequencyIndex",
            "duration",
            "content",
            "emailAlertName_2",
            "minObjType_2",
            "query_2",
            "listIndex_2",
            "isOtherEmail_2",
            "otherEmail_2",
            "frequency_2",
            "frequencyIndex_2",
            "duration_2",
            "content_2",
    })
    @Test
    public void emailAlert_updateEmailAlert(
            String emailAlertName,
            int emailAlertType,
            int minObjType,
            String query,
            int listIndex,
            int isOtherEmail,
            String otherEmail,
            int frequency,
            int frequencyIndex,
            int duration,
            int content,
            String emailAlertName_2,
            int minObjType_2,
            String query_2,
            int listIndex_2,
            int isOtherEmail_2,
            String otherEmail_2,
            int frequency_2,
            int frequencyIndex_2,
            int duration_2,
            int content_2
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //变量
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== EA - Email Alert can be updated correctly ==============================");
            loginAndSwitchLanguage();
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            //进入邮件提醒页面
            p_searchPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            //删除老邮件提醒
            p_emailAlertPage.func_delete_emailAlert(emailAlertName);
            //创建邮件提醒
            if (emailAlertType == 0) {
                p_emailAlertPage.func_click_newEmailAlert_pu();
            } else if (emailAlertType == 1) {
                p_emailAlertPage.func_click_newEmailAlert_ls();
            }
            p_emailAlertPage.func_createOrEdit_emailAlert(emailAlertName, minObjType, query, listIndex, isOtherEmail, otherEmail, frequency, frequencyIndex, duration, content);
            //验证结果
            //是否存在
            Assert.assertEquals(p_emailAlertPage.func_verify_emailAlertExists(emailAlertName), true);
            //修改邮件提醒
            p_emailAlertPage.func_click_editButton(emailAlertName);
            p_emailAlertPage.func_createOrEdit_emailAlert(emailAlertName_2, minObjType_2, query_2, listIndex_2, isOtherEmail_2, otherEmail_2, frequency_2, frequencyIndex_2, duration_2, content_2);
            //验证结果
            //是否存在
            Assert.assertEquals(p_emailAlertPage.func_verify_emailAlertExists(emailAlertName_2), true);
            //验证详细数据(新增、修改邮件提醒页)
            p_emailAlertPage.func_click_editButton(emailAlertName_2);
            result &= p_emailAlertPage.func_verify_emailAlert_addEditEmailAlertPage(emailAlertType, emailAlertName_2, minObjType_2, query_2, listIndex_2, isOtherEmail_2, otherEmail_2, frequency_2, frequencyIndex_2, duration_2, content_2);
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "UpdateEmailAlertFromEmailAlertPage", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Parameters({
            "emailAlertName",
            "emailAlertType",
            "minObjType",
            "query",
            "listIndex",
            "isOtherEmail",
            "otherEmail",
            "frequency",
            "frequencyIndex",
            "duration",
            "content",
            "status",
            "status_2",
    })
    @Test
    public void emailAlert_updateEmailAlertStatus(
            String emailAlertName,
            int emailAlertType,
            int minObjType,
            String query,
            int listIndex,
            int isOtherEmail,
            String otherEmail,
            int frequency,
            int frequencyIndex,
            int duration,
            int content,
            int status,
            int status_2
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //变量
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== EA - Email Alert status can be updated correctly ==============================");
            loginAndSwitchLanguage();
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            //进入邮件提醒页面
            p_searchPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            //删除老邮件提醒
            p_emailAlertPage.func_delete_emailAlert(emailAlertName);
            //创建邮件提醒
            if (emailAlertType == 0) {
                p_emailAlertPage.func_click_newEmailAlert_pu();
            } else if (emailAlertType == 1) {
                p_emailAlertPage.func_click_newEmailAlert_ls();
            }
            p_emailAlertPage.func_createOrEdit_emailAlert(emailAlertName, minObjType, query, listIndex, isOtherEmail, otherEmail, frequency, frequencyIndex, duration, content);
            //验证结果
            //设置邮件提醒状态
            if (status == 0) {
                p_emailAlertPage.func_click_activeButton(emailAlertName);
            } else if (status == 1) {
                p_emailAlertPage.func_click_inactiveButton(emailAlertName);
            }
            Thread.sleep(1000);
            //刷新页面
            d.navigate().refresh();
            //更新邮件提醒状态
            if (status_2 == 0) {
                p_emailAlertPage.func_click_activeButton(emailAlertName);
            } else if (status_2 == 1) {
                p_emailAlertPage.func_click_inactiveButton(emailAlertName);
            }
            Thread.sleep(1000);
            //刷新页面
            d.navigate().refresh();
            //验证结果
            result &= p_emailAlertPage.func_verify_status(emailAlertName, status_2);
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "UpdateEmailAlertFromEmailAlertPage", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Parameters({
            "emailAlertName",
            "emailAlertType",
            "minObjType",
            "query",
            "listIndex",
            "isOtherEmail",
            "otherEmail",
            "frequency",
            "frequencyIndex",
            "duration",
            "content"
    })
    @Test
    public void emailAlert_updateEmailAlertByChangingStemming(
            String emailAlertName,
            int emailAlertType,
            int minObjType,
            String query,
            int listIndex,
            int isOtherEmail,
            String otherEmail,
            int frequency,
            int frequencyIndex,
            int duration,
            int content
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //变量
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== EA - Email Alert can be updated correctly by changing English stemming ==============================");
            loginAndSwitchLanguage();
            //搜索query设置英文截词
            p_searchPage.func_searchForQuery(query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_config_englishStemming(0);
            //进入邮件提醒页面
            p_searchPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            //删除老邮件提醒
            p_emailAlertPage.func_delete_emailAlert(emailAlertName);
            //创建邮件提醒
            if (emailAlertType == 0) {
                p_emailAlertPage.func_click_newEmailAlert_pu();
            } else if (emailAlertType == 1) {
                p_emailAlertPage.func_click_newEmailAlert_ls();
            }
            p_emailAlertPage.func_createOrEdit_emailAlert(emailAlertName, minObjType, query, listIndex, isOtherEmail, otherEmail, frequency, frequencyIndex, duration, content);
            //验证结果
            //是否存在
            Assert.assertEquals(p_emailAlertPage.func_verify_emailAlertExists(emailAlertName), true);
            //修改截词
            //搜索query设置英文截词
            p_searchPage.func_searchForQuery(query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_config_englishStemming(1);
            //如果监控对象为搜索语句，记录搜索结果数
            Assert.assertEquals(p_searchResultPage.func_verify_searchResultExist_tableView(), true);
            int patentNumber = p_searchResultPage.func_get_searchResultNumber();
            //进入邮件提醒页面
            p_searchResultPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            //修改邮件提醒
            p_emailAlertPage.func_click_editButton(emailAlertName);
            p_emailAlertPage.func_click_submit_editEmailAlertPage();
            //验证结果
            //验证详细数据(邮件提醒列表页)
            //期望数据
            HashMap<String, String> expAlertData = new HashMap<>();
            expAlertData.put("name", emailAlertName);
            expAlertData.put("type", String.valueOf(emailAlertType));
            expAlertData.put("obj", String.valueOf(minObjType));
            expAlertData.put("isOtherEmail", String.valueOf(isOtherEmail));
            expAlertData.put("patentNumber", String.valueOf(patentNumber));
            //实际数据
            HashMap<String, String> actAlertData = p_emailAlertPage.func_get_emailAlertData(emailAlertName);
            //验证数据
            result &= p_emailAlertPage.func_verify_emailAlertData(expAlertData, actAlertData);
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "UpdateEmailAlertFromEmailAlertPage", "jpg");
            Assert.assertEquals(false, true);
        }
    }


    //================================================== Email Alert Old ==================================================

    //Pat-406:EA-UI of Email Alert
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd"
    })
    @Test
    public void emailAlert_pat_406(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-406:EA-UI of Email Alert ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入邮件提醒页面
            p_searchPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            //验证页面UI
            result &= p_emailAlertPage.func_verify_ui();
            //切换为简体中文
            p_emailAlertPage.func_switch_language(1);
            result &= p_emailAlertPage.func_verify_translation_cn();
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-406", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-304:EA-List of Alert
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd"
    })
    @Test
    public void emailAlert_pat_304(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-304:EA-List of Alert ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入邮件提醒页面
            p_searchPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            //删除所有邮件提醒
            p_emailAlertPage.func_delete_all_alerts();
            //添加10个邮件提醒
            for (int i = 0; i < 10; i++) {
                String alertName = String.format("TestAlert_%d", i);
                p_emailAlertPage.func_click_newEmailAlert_pu();
                p_emailAlertPage.func_createOrEdit_emailAlert(alertName, 0, "trip1", -1, -1, "", -1, -1, -1, -1);
            }
            //验证分页相关控件不存在
            result &= p_emailAlertPage.func_verify_pageNumIsNotDisplayed();
            //添加第11个邮件提醒
            p_emailAlertPage.func_click_newEmailAlert_pu();
            p_emailAlertPage.func_createOrEdit_emailAlert("TestAlert_11", 0, "trip1", -1, -1, "", -1, -1, -1, -1);
            //验证分页相关控件存在
            result &= p_emailAlertPage.func_verify_pageNumIsDisplayed();
            //验证首页专利数量
            result &= p_emailAlertPage.func_verify_emailAlertNum(10);
            //验证页面切换功能
            p_emailAlertPage.func_click_pageNum(2);
            result &= p_emailAlertPage.func_verify_currentPageNum(2);
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-304", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-411:EA-Delete Alert(UDP)(First page)
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd"
    })
    @Test
    public void emailAlert_pat_411_1(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-411:EA-Delete Alert(UDP)(First page) ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入邮件提醒页面
            p_searchPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            //删除所有邮件提醒
            p_emailAlertPage.func_delete_all_alerts();
            //创建邮件提醒
            String alertName = "TestAlert_11";
            p_emailAlertPage.func_click_newEmailAlert_pu();
            p_emailAlertPage.func_createOrEdit_emailAlert(alertName, 0, "trip1", -1, -1, "", -1, -1, -1, -1);
            //验证邮件提醒存在
            Assert.assertEquals(p_emailAlertPage.func_verify_emailAlertExists(alertName), true);
            //点删除并取消
            p_emailAlertPage.func_deleteAndCancel_emailAlert(alertName);
            //验证邮件提醒存在
            Assert.assertEquals(p_emailAlertPage.func_verify_emailAlertExists(alertName), true);
            //点删除并确定
            p_emailAlertPage.func_delete_emailAlert(alertName);
            //验证邮件提醒不存在
            result &= p_emailAlertPage.func_verify_emailAlertNotExists(alertName);
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-411(First page)", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-411:EA-Delete Alert(UDP)(Second page)

    /**
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd"
    })
    @Test
    public void emailAlert_pat_411_2(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-411:EA-Delete Alert(UDP)(Second page) ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入邮件提醒页面
            p_searchPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            //删除所有邮件提醒
            p_emailAlertPage.func_delete_all_alerts();
            //添加11个邮件提醒
            for (int i = 0; i < 12; i++) {
                String alertName = String.format("TestAlert_%d", i);
                p_emailAlertPage.func_click_newEmailAlert_pu();
                p_emailAlertPage.func_createOrEdit_emailAlert(alertName, 0, "trip1", -1, -1, "", -1, -1, -1, -1);
            }
            //切换到第二页
            p_emailAlertPage.func_click_pageNum(2);
            //获取第二个邮件提醒名称
            String alertName = p_emailAlertPage.func_get_emailAlertName_byIndex(1);
            //点删除并取消
            p_emailAlertPage.func_deleteAndCancel_emailAlert(alertName);
            //验证邮件提醒存在
            Assert.assertEquals(p_emailAlertPage.func_verify_emailAlertExists(alertName), true);
            //点删除并确定
            p_emailAlertPage.func_delete_emailAlert(alertName);
            //验证邮件提醒不存在
            result &= p_emailAlertPage.func_verify_emailAlertNotExists(alertName);
            //切换到第二页，验证邮件提醒不存在
            p_emailAlertPage.func_click_pageNum(2);
            result &= p_emailAlertPage.func_verify_emailAlertNotExists(alertName);
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-411(Second page)", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-466:EA-Delete Alert(LS)(First page)

    /**
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd"
    })
    @Test
    public void emailAlert_pat_466_1(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-466:EA-Delete Alert(LS)(First page) ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入邮件提醒页面
            p_searchPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            //删除所有邮件提醒
            p_emailAlertPage.func_delete_all_alerts();
            //创建邮件提醒
            String alertName = "TestAlert_11";
            p_emailAlertPage.func_click_newEmailAlert_ls();
            p_emailAlertPage.func_createOrEdit_emailAlert(alertName, 0, "trip1", -1, -1, "", -1, -1, -1, -1);
            //验证邮件提醒存在
            Assert.assertEquals(p_emailAlertPage.func_verify_emailAlertExists(alertName), true);
            //点删除并取消
            p_emailAlertPage.func_deleteAndCancel_emailAlert(alertName);
            //验证邮件提醒存在
            Assert.assertEquals(p_emailAlertPage.func_verify_emailAlertExists(alertName), true);
            //点删除并确定
            p_emailAlertPage.func_delete_emailAlert(alertName);
            //验证邮件提醒不存在
            result &= p_emailAlertPage.func_verify_emailAlertNotExists(alertName);
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-466(First page)", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-466:EA-Delete Alert(LS)(Second page)

    /**
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd"
    })
    @Test
    public void emailAlert_pat_466_2(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-466:EA-Delete Alert(LS)(Second page) ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入邮件提醒页面
            p_searchPage.func_goto_emailAlertPage();
            p_emailAlertPage = new Zhihuiya_emailAlertPage(d);
            Assert.assertEquals(p_emailAlertPage.selfcheck(), true);
            //删除所有邮件提醒
            p_emailAlertPage.func_delete_all_alerts();
            //添加11个邮件提醒
            for (int i = 0; i < 12; i++) {
                String alertName = String.format("TestAlert_%d", i);
                p_emailAlertPage.func_click_newEmailAlert_ls();
                p_emailAlertPage.func_createOrEdit_emailAlert(alertName, 0, "trip1", -1, -1, "", -1, -1, -1, -1);
            }
            //切换到第二页
            p_emailAlertPage.func_click_pageNum(2);
            //获取第二个邮件提醒名称
            String alertName = p_emailAlertPage.func_get_emailAlertName_byIndex(1);
            //点删除并取消
            p_emailAlertPage.func_deleteAndCancel_emailAlert(alertName);
            //验证邮件提醒存在
            Assert.assertEquals(p_emailAlertPage.func_verify_emailAlertExists(alertName), true);
            //点删除并确定
            p_emailAlertPage.func_delete_emailAlert(alertName);
            //验证邮件提醒不存在
            result &= p_emailAlertPage.func_verify_emailAlertNotExists(alertName);
            //切换到第二页，验证邮件提醒不存在
            p_emailAlertPage.func_click_pageNum(2);
            result &= p_emailAlertPage.func_verify_emailAlertNotExists(alertName);
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-466(Second page)", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //============================== Test scenarios after receiving email ==============================

    @Parameters({
            "wangyi_url",
            "wangyi_uid",
            "wangyi_pwd",
            "wangyi_emailTimeout"
    })
    @Test
    public void email_single_tableView(
            String wangyi_url,
            String wangyi_uid,
            String wangyi_pwd,
            int wangyi_emailTimeout
    ) throws IOException {
        try {
            l.entry();
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Testing sending email (table view) ==============================");
            boolean result = true;
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
            result &= pwy_mainPage.func_verify_emailExists("auto test", wangyi_emailTimeout);
            //点击邮件
            pwy_mainPage.func_click_email_byTitle("auto test");
            //获取邮件正文
            String content = pwy_mainPage.func_get_emailContent();
            l.info("Email content: [{}]", content);
            Thread.sleep(5000);
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
