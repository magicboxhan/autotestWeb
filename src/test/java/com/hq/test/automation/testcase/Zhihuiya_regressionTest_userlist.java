package com.hq.test.automation.testcase;

import com.hq.test.automation.pageobject.*;
import com.hq.test.framework.page.BasePage;
import com.hq.test.framework.testcase.BaseTestcase;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Patsnap_Xcb on 2015/1/12.
 */
public class Zhihuiya_regressionTest_userlist extends BaseTestcase {

    BasePage p_basePage = new BasePage();
    Zhihuiya_loginPage p_loginPage;
    Zhihuiya_searchPage p_searchPage;
    Zhihuiya_userListPage p_userListPage;
    Zhihuiya_searchResultPage p_searchResultPage;
    Zhihuiya_patentViewPage p_patentViewPage;


    //Pat_784 Create new parent folder from Userlist page using default name
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ExpFolder"
    })
    @Test
    public void userlist_Pat_784(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ExpFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat_784 Create new parent folder from Userlist page using default name ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入我的收藏页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ExpFolder);
            d.navigate().refresh();
            //新建收藏夹
            p_userListPage.func_create_folder_default();
            //验证新建的收藏夹
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(ExpFolder);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹`
            p_userListPage.func_click_userListFolder(ExpFolder);
            p_userListPage.func_delete_userListFolder(ExpFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat_784 Create new parent folder from Userlist page using default name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-785  Create new parent folder from Userlist page using English/Chinese name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "Folder1"
    })
    @Test
    public void userlist_Pat_785(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String Folder1
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-785  Create new parent folder from Userlist page using English/Chinese name ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入我的收藏页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(Folder1);
            d.navigate().refresh();
            //新建收藏夹
            p_userListPage.func_create_folder_input(Folder1);
            //验证新建的收藏夹
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(Folder1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹`
            p_userListPage.func_click_userListFolder(Folder1);
            p_userListPage.func_delete_userListFolder(Folder1);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-785  Create new parent folder from Userlist page using English/Chinese name", "jpg");
            Assert.assertEquals(false, true);
        }
    }


    //Pat-786 Create new parent folder from Userlist page using special characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "FolderName"
    })
    @Test
    public void userlist_Pat_786(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String FolderName
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("==============================Pat-786 Create new parent folder from Userlist page using special characters ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入我的收藏页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(FolderName);
            d.navigate().refresh();
            //新建收藏夹
            p_userListPage.func_create_folder_input(FolderName);
            //验证新建的收藏夹
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹`
            p_userListPage.func_click_userListFolder(FolderName);
            p_userListPage.func_delete_userListFolder(FolderName);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-786 Create new parent folder from Userlist page using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-787 Create new parent folder from Userlist page using number
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "FolderName2"
    })
    @Test
    public void userlist_Pat_787(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String FolderName2
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("==============================Pat-787 Create new parent folder from Userlist page using number ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入我的收藏页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(FolderName2);
            d.navigate().refresh();
            //新建收藏夹
            p_userListPage.func_create_folder_input(FolderName2);
            //验证新建的收藏夹
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹`
            p_userListPage.func_click_userListFolder(FolderName2);
            p_userListPage.func_delete_userListFolder(FolderName2);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-787 Create new parent folder from Userlist page using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }


    //Pat-791 Create new parent folder from Userlist page which contains some spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "FolderName4"
    })
    @Test
    public void userlist_Pat_791(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String FolderName4
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("==============================Pat-791 Create new parent folder from Userlist page which contains some spaces ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入我的收藏页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(FolderName4);
            d.navigate().refresh();
            //新建收藏夹
            p_userListPage.func_create_folder_input(FolderName4);
            //验证新建的收藏夹
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹`
            p_userListPage.func_click_userListFolder(FolderName4);
            p_userListPage.func_delete_userListFolder(FolderName4);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-791 Create new parent folder from Userlist page which contains some spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-792 Create new parent folder from Userlist page which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "FolderName3",
            "Folder1"
    })
    @Test
    public void userlist_Pat_792(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String FolderName3,
            String Folder1
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("==============================//Pat-792 Create new parent folder from Userlist page which contains html tag ==============================");
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入我的收藏页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(Folder1);
            //新建收藏夹
            p_userListPage.func_create_folder_input(FolderName3);
            //验证新建的收藏夹
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹`
            p_userListPage.func_click_userListFolder(FolderName3);
            p_userListPage.func_delete_userListFolder(FolderName3);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "//Pat-792 Create new parent folder from Userlist page which contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-805 Create new folder from SRP using default name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ExpFolder1",
            "Query"
    })
    @Test
    public void userlist_Pat_805(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ExpFolder1,
            String Query
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("==============================//Pat-805 Create new folder from SRP using default name ==============================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ExpFolder1);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //搜索并进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_default_srp();
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            Thread.sleep(5000);
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(ExpFolder1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹`
            Thread.sleep(5000);
            p_userListPage.func_click_userListFolder(ExpFolder1);
            p_userListPage.func_delete_userListFolder(ExpFolder1);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "//Pat-805 Create new folder from SRP using default name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-808 Create new folder from SRP using English/Chinese characters
     /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "Folder1",
            "Query"
    })
    @Test
    public void userlist_Pat_808(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String Folder1,
            String Query
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Create new parent folder from SRP using chinese/english characters ==============================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(Folder1);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //搜索并进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(Folder1);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            Thread.sleep(5000);
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(Folder1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹`
            Thread.sleep(5000);
            p_userListPage.func_click_userListFolder(Folder1);
            p_userListPage.func_delete_userListFolder(Folder1);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-808 Create new folder from SRP using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-809 Create new folder from SRP using special characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "FolderName",
            "Query"
    })
    @Test
    public void userlist_Pat_809(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String FolderName,
            String Query
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-809 Create new folder from SRP using special characters ==============================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(FolderName);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //搜索并进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(FolderName);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            Thread.sleep(5000);
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹`
            Thread.sleep(5000);
            p_userListPage.func_click_userListFolder(FolderName);
            p_userListPage.func_delete_userListFolder(FolderName);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-809 Create new folder from SRP using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-810 Create new folder from SRP using number
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "FolderName2",
            "Query"
    })
    @Test
    public void userlist_Pat_810(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String FolderName2,
            String Query
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-810 Create new folder from SRP using numbers ==============================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(FolderName2);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //搜索并进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(FolderName2);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            Thread.sleep(5000);
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹`
            Thread.sleep(5000);
            p_userListPage.func_click_userListFolder(FolderName2);
            p_userListPage.func_delete_userListFolder(FolderName2);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-810 Create new folder from SRP using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-812 Create new folder from SRP which contains space
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "FolderName2",
            "Query"
    })
    @Test
    public void userlist_Pat_812(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String FolderName4,
            String Query
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-812 Create new folder from SRP which contains space==============================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(FolderName4);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //搜索并进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(FolderName4);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            Thread.sleep(5000);
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹`
            Thread.sleep(5000);
            p_userListPage.func_click_userListFolder(FolderName4);
            p_userListPage.func_delete_userListFolder(FolderName4);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-812 Create new folder from SRP which contains space", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-813 Create new folder from SRP which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "Folder1",
            "FolderName3",
            "Query"
    })
    @Test
    public void userlist_Pat_813(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String Folder1,
            String FolderName3,
            String Query
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-813 Create new folder from SRP which contains html tag==============================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(Folder1);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //搜索并进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(FolderName3);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            Thread.sleep(5000);
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹`
            Thread.sleep(5000);
            p_userListPage.func_click_userListFolder(FolderName3);
            p_userListPage.func_delete_userListFolder(FolderName3);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-813 Create new folder from SRP which contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-864 Create new parent folder from Bulk Search using default name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "ExpFolder"
    })
    @Test
    public void userlist_Pat_864(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String ExpFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== //Pat-864 Create new parent folder from Bulk Search using default name ==============================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ExpFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            //新建收藏夹
            p_searchPage.func_create_parent_folder_default_bs();
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            ArrayList<String> names = new ArrayList<String>();
            names.add(ExpFolder);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ExpFolder);
            p_userListPage.func_delete_userListFolder(ExpFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "//Pat-864 Create new parent folder from Bulk Search using default name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-865 Create new parent folder from Bulk Search using English/Chinese characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "Folder1"
    })
    @Test
    public void userlist_Pat_865(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String Folder1
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("==============================Pat-865 Create new parent folder from Bulk Search using English/Chinese characters ==============================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(Folder1);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            //新建收藏夹
            p_searchPage.func_create_parent_folder_bs(Folder1);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            ArrayList<String> names = new ArrayList<String>();
            names.add(Folder1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(Folder1);
            p_userListPage.func_delete_userListFolder(Folder1);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-865 Create new parent folder from Bulk Search using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-866 Create new parent folder from Bulk Search using special characters
    /*
    * @param loginPage_url
    * @Param loginPage_uid
    * @Param loginPage_pwd
    * @Param PublicationNumber
    * @Param FolderName
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "FolderName"
    })
    @Test
    public void userlist_Pat_866(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String FolderName
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-866 Create new parent folder from Bulk Search using special characters ==============================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(FolderName);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            //新建收藏夹
            p_searchPage.func_create_parent_folder_bs(FolderName);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(FolderName);
            p_userListPage.func_delete_userListFolder(FolderName);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-866 Create new parent folder from Bulk Search using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-867 Create new parent folder from Bulk Search page using number
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "FolderName2"
    })
    @Test
    public void userlist_Pat_867(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String FolderName2
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-867 Create new parent folder from Bulk Search page using number ==============================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(FolderName2);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            //新建收藏夹
            p_searchPage.func_create_parent_folder_bs(FolderName2);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(FolderName2);
            p_userListPage.func_delete_userListFolder(FolderName2);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-867 Create new parent folder from Bulk Search page using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-868 Create new parent folder from Bulk Search page which contains spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "FolderName4"
    })
    @Test
    public void userlist_Pat_868(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String FolderName4
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Create new parent folder from Bulk Search page which contains spaces ==============================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(FolderName4);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            //新建收藏夹
            p_searchPage.func_create_parent_folder_bs(FolderName4);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(FolderName4);
            p_userListPage.func_delete_userListFolder(FolderName4);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Create new parent folder from Bulk Search page which contains spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-869 Create new parent folder from Bulk Search page which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "Folder1",
            "FolderName3"
    })
    @Test
    public void userlist_Pat_869(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String Folder1,
            String FolderName3
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-869 Create new parent folder from Bulk Search page which contains html tag ==============================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(Folder1);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            //新建收藏夹
            p_searchPage.func_create_parent_folder_bs(FolderName3);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(FolderName3);
            p_userListPage.func_delete_userListFolder(FolderName3);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-869 Create new parent folder from Bulk Search page which contains html tags", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-815 Create new parent folder from PVP using default name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "Query",
            "ExpFolder"
    })
    @Test
    public void userlist_Pat_815(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String Query,
            String ExpFolder
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("==============Pat-815 Create new parent folder from PVP using default name====================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ExpFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建收藏夹
            p_patentViewPage.func_create_folder_default_pvp();
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            ArrayList<String> names = new ArrayList<String>();
            names.add(ExpFolder);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ExpFolder);
            p_userListPage.func_delete_userListFolder(ExpFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-815 Create new parent folder from PVP using default name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-816 Create new parent folder from PVP using English/Chinese characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "Query",
            "Folder1"
    })
    @Test
    public void userlist_Pat_816(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String Query,
            String Folder1
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("===============Pat-816 Create new parent folder from PVP using English/Chinese characters====================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(Folder1);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建收藏夹
            p_patentViewPage.func_create_folder_pvp(Folder1);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            ArrayList<String> names = new ArrayList<String>();
            names.add(Folder1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(Folder1);
            p_userListPage.func_delete_userListFolder(Folder1);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-816 Create new parent folder from PVP using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-817 Create new parent folder from PVP using special characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "Query",
            "FolderName"
    })
    @Test
    public void userlist_Pat_817(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String Query,
            String FolderName
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("===============Pat-817 Create new parent folder from PVP using special characters====================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(FolderName);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建收藏夹
            p_patentViewPage.func_create_folder_pvp(FolderName);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(FolderName);
            p_userListPage.func_delete_userListFolder(FolderName);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-817 Create new parent folder from PVP using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-818 Create new parent folder from PVP using number
    /*
    * @Param loginPage_url;
    * @Param loginPage_uid;
    * @Param loginPage_pwd;
    * @Param Query;
    * @Param FolderName1
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "Query",
            "FolderName2"
    })
    @Test
    public void userlist_Pat_818(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String Query,
            String FolderName2
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("===============Create new parent folder from PVP using number====================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(FolderName2);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建收藏夹
            p_patentViewPage.func_create_folder_pvp(FolderName2);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(FolderName2);
            p_userListPage.func_delete_userListFolder(FolderName2);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Create new parent folder from PVP using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-819 Create new folder from PVP which contains some spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "Query",
            "FolderName4"
    })
    @Test
    public void userlist_Pat_819(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String Query,
            String FolderName4
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("===============Pat-819 Create new folder from PVP which contains some spaces====================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(FolderName4);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建收藏夹
            p_patentViewPage.func_create_folder_pvp(FolderName4);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(FolderName4);
            p_userListPage.func_delete_userListFolder(FolderName4);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-819 Create new folder from PVP which contains some spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-820 Create new folder from PVP which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "Query",
            "Folder1",
            "FolderName3"
    })
    @Test
    public void userlist_Pat_820(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String Query,
            String Folder1,
            String FolderName3
    ) throws IOException {
        try {
            l.entry();
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("===============Pat-820 Create new folder from PVP which contains html tag====================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(Folder1);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建收藏夹
            p_patentViewPage.func_create_folder_pvp(FolderName3);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(FolderName3);
            p_userListPage.func_delete_userListFolder(FolderName3);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-820 Create new folder from PVP which contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-828 Create new child folder from Userlist page using default name
    /*
     *  @param loginPage_url
     *  @param loginPage_uid
     *  @param loginPage_pwd
     *  @param ParentFolder
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "ExpFolder"
    })
    @Test
    public void userlist_Pat_828(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String ExpFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-828 Create new child folder from Userlist page using default name========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            Thread.sleep(3000);
            //新建子文件夹
            p_userListPage.funcCreateChildFolderDefault(ParentFolder);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(ExpFolder);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-828 Create new child folder from Userlist page using default name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-832 Create new child folder from Userlist page using English/Chinese characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "FolderName1"
    })
    @Test
    public void userlist_Pat_832(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String FolderName1
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-832 Create new child folder from Userlist page using English/Chinese characters========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            Thread.sleep(3000);
            //新建子文件夹
            p_userListPage.funcCreateChildFolderInput(ParentFolder,FolderName1);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-832 Create new child folder from Userlist page using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-833 Create new child folder from Userlist page using special characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "FolderName"
    })
    @Test
    public void userlist_Pat_833(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String FolderName
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-833 Create new child folder from Userlist page using special characters========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            Thread.sleep(3000);
            //新建子文件夹
            p_userListPage.funcCreateChildFolderInput(ParentFolder, FolderName);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-833 Create new child folder from Userlist page using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-834 Create new child folder from Userlist page using number
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "FolderName2"
    })
    @Test
    public void userlist_Pat_834(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String FolderName2
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-834 Create new child folder from Userlist page using number========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            Thread.sleep(3000);
            //新建子文件夹
            p_userListPage.funcCreateChildFolderInput(ParentFolder, FolderName2);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-834 Create new child folder from Userlist page using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-835 Create new child folder which title contains spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "FolderName4"
    })
    @Test
    public void userlist_Pat_835(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String FolderName4
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-835 Create new child folder which title contains spaces========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            Thread.sleep(3000);
            //新建子文件夹
            p_userListPage.funcCreateChildFolderInput(ParentFolder, FolderName4);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-835 Create new child folder which title contains spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-838 Create new child folder which title contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "FolderName3"
    })
    @Test
    public void userlist_Pat_838(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String FolderName3
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-838 Create new child folder which title contains html tag========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            Thread.sleep(3000);
            //新建子文件夹
            p_userListPage.funcCreateChildFolderInput(ParentFolder, FolderName3);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-838 Create new child folder which title contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-870 Create new child folder from SRP using default name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "ExpFolder",
            "Query"
    })
    @Test
    public void userlist_Pat_870(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String ExpFolder,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-870 Create new child folder from SRP using default name========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检查页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo();
            //搜索进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(ParentFolder);
            //新建子文件夹
            p_searchResultPage.funCreatChildFolderSRP_default(ParentFolder);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(ExpFolder);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-870 Create new child folder from SRP using default name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-871 Create new child folder from SRP using English/Chinese characters
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param ParentFolder
    * @param FolderName1
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "FolderName1",
            "Query"
    })
    @Test
    public void userlist_Pat_871(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String FolderName1,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-871 Create new child folder from SRP using English/Chinese characters========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检查页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo();
            //搜索进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(ParentFolder);
            //新建子文件夹
            p_searchResultPage.funCreatChildFolderSRP(ParentFolder, FolderName1);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-871 Create new child folder from SRP using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-872 Create new child folder from SRP using special characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "FolderName",
            "Query"
    })
    @Test
    public void userlist_Pat_872(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String FolderName,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-872 Create new child folder from SRP using special characters========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检查页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo();
            //搜索进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(ParentFolder);
            //新建子文件夹
            p_searchResultPage.funCreatChildFolderSRP(ParentFolder, FolderName);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-872 Create new child folder from SRP using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-873 Create new child folder from SRP using number
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "FolderName2",
            "Query"
    })
    @Test
    public void userlist_Pat_873(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String FolderName2,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-873 Create new child folder from SRP using number========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检查页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo();
            //搜索进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(ParentFolder);
            //新建子文件夹
            p_searchResultPage.funCreatChildFolderSRP(ParentFolder, FolderName2);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-873 Create new child folder from SRP using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-874 Create new child folder from SRP which title contains spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "FolderName4",
            "Query"
    })
    @Test
    public void userlist_Pat_874(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String FolderName4,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-874 Create new child folder from SRP which title contains spaces========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检查页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo();
            //搜索进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(ParentFolder);
            //新建子文件夹
            p_searchResultPage.funCreatChildFolderSRP(ParentFolder, FolderName4);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-874 Create new child folder from SRP which title contains spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-875 Create new child folder from SRP which title contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "FolderName3",
            "Query"
    })
    @Test
    public void userlist_Pat_875(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String FolderName3,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-875 Create new child folder from SRP which title contains html tag========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检查页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo();
            //搜索进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(ParentFolder);
            //新建子文件夹
            p_searchResultPage.funCreatChildFolderSRP(ParentFolder, FolderName3);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-875 Create new child folder from SRP which title contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-793 Create new child folder from Bulk search using default name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "ParentFolder",
            "ExpFolder"
    })
    @Test
    public void userlist_Pat_793(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String ParentFolder,
            String ExpFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================Pat-793 Create new child folder from Bulk search using default name======== ");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检查该页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            p_searchPage.func_create_parent_folder_bs(ParentFolder);
            p_searchPage.funCreatChildFolderBS(ParentFolder, ExpFolder);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(ExpFolder);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-793 Create new child folder from Bulk search using default name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

   //Pat-796 Create new child folder from Bulk search using English/Chinese characters
   @Parameters({
           "loginPage_url",
           "loginPage_uid",
           "loginPage_pwd",
           "PublicationNumber",
           "ParentFolder",
           "FolderName1"
   })
   @Test
   public void userlist_Pat_796(
           String loginPage_url,
           String loginPage_uid,
           String loginPage_pwd,
           String PublicationNumber,
           String ParentFolder,
           String FolderName1
   ) throws IOException {
       try {
           l.entry();
           //测试结果
           boolean result = true;
           //处理入参
           l.info("");
           l.info("");
           l.info("");
           l.info("============================Pat-796 Create new child folder from Bulk search using English/Chinese characters======== ");
           //登陆
           d.get(loginPage_url);
           p_loginPage = new Zhihuiya_loginPage(d);
           p_loginPage.func_switch_language(2);
           p_loginPage.func_login(loginPage_uid, loginPage_pwd);
           p_searchPage = new Zhihuiya_searchPage(d);
           Assert.assertEquals(p_searchPage.selfcheck(), true);
           //进入收藏夹页面并检查该页面
           p_searchPage.func_goto_userListPage();
           p_userListPage = new Zhihuiya_userListPage(d);
           p_userListPage.func_delete_userListFolder(ParentFolder);
           d.navigate().refresh();
           p_userListPage.btn_logo().click();
           //切换至bulk search tab下
           p_searchPage.func_click_bulkSearch();
           p_searchPage.FuncBulkSearchSimple(PublicationNumber);
           Thread.sleep(5000);
           p_searchPage.func_create_parent_folder_bs(ParentFolder);
           p_searchPage.funCreatChildFolderBS(ParentFolder, FolderName1);
           p_searchPage.btnCancel_addToListDialog().click();
           //进入收藏夹页面
           p_searchPage.func_goto_userListPage();
           p_userListPage = new Zhihuiya_userListPage(d);
           p_userListPage.func_click_expandAll();
           ArrayList<String> names = new ArrayList<String>();
           names.add(FolderName1);
           result &= p_userListPage.func_verify_userListFolderExists(names);
           Assert.assertEquals(result, true);
           //删除所建的收藏夹
           p_userListPage.func_click_userListFolder(ParentFolder);
           p_userListPage.func_delete_userListFolder(ParentFolder);
           d.navigate().refresh();
           p_userListPage.func_click_logout();
           l.exit();
       } catch (Exception e) {
           l.error("Error!");
           e.printStackTrace();
           t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-796 Create new child folder from Bulk search using English/Chinese characters", "jpg");
           Assert.assertEquals(false, true);
       }
   }


    //Pat-797 Create new child folder from Bulk search using special characters
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param PublicationNumber
    * @param ParentFolder
    * @param FolderName
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "ParentFolder",
            "FolderName"
    })
    @Test
    public void userlist_Pat_797(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String ParentFolder,
            String FolderName
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================Pat_797 Create new child folder from Bulk search using special characters======== ");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检查该页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            p_searchPage.func_create_parent_folder_bs(ParentFolder);
            p_searchPage.funCreatChildFolderBS(ParentFolder, FolderName);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat_797 Create new child folder from Bulk search using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-798 Create new child folder from Bulk search using number
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "ParentFolder",
            "FolderName2"
    })
    @Test
    public void userlist_Pat_798(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String ParentFolder,
            String FolderName2
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================Pat-798 Create new child folder from Bulk search using number======== ");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检查该页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            p_searchPage.func_create_parent_folder_bs(ParentFolder);
            p_searchPage.funCreatChildFolderBS(ParentFolder, FolderName2);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-798 Create new child folder from Bulk search using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-799 Create new child folder from Bulk search which contains spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "ParentFolder",
            "FolderName4"
    })
    @Test
    public void userlist_Pat_799(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String ParentFolder,
            String FolderName4
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================Pat-799 Create new child folder from Bulk search which contains spaces======== ");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检查该页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            p_searchPage.func_create_parent_folder_bs(ParentFolder);
            p_searchPage.funCreatChildFolderBS(ParentFolder, FolderName4);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-799 Create new child folder from Bulk search which contains spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-800 Create new child folder from Bulk search which contains some html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "ParentFolder",
            "FolderName3"
    })
    @Test
    public void userlist_Pat_800(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String ParentFolder,
            String FolderName3
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("===========================Pat-800 Create new child folder from Bulk search which contains some html tag======== ");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检查该页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            p_searchPage.func_create_parent_folder_bs(ParentFolder);
            p_searchPage.funCreatChildFolderBS(ParentFolder, FolderName3);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-800 Create new child folder from Bulk search which contains some html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-876 Create new child folder from PVP using default name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "Query",
            "ExpFolder"
    })
    @Test
    public void userlist_Pat_876(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String Query,
            String ExpFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-876 Create new child folder from PVP using default name========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建子收藏夹
            p_patentViewPage.func_create_folder_pvp(ParentFolder);
            p_patentViewPage.FunCreatChildFolderBS(ParentFolder, ExpFolder);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(ExpFolder);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-876 Create new child folder from PVP using default name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-877 Create new child folder from PVP using English/Chinese characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "Query",
            "FolderName1"
    })
    @Test
    public void userlist_Pat_877(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String Query,
            String FolderName1
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-877 Create new child folder from PVP using English/Chinese characters========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建子收藏夹
            p_patentViewPage.func_create_folder_pvp(ParentFolder);
            p_patentViewPage.FunCreatChildFolderBS(ParentFolder, FolderName1);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-877 Create new child folder from PVP using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-878 Create new child folder from PVP using special characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "Query",
            "FolderName"
    })
    @Test
    public void userlist_Pat_878(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String Query,
            String FolderName
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-878 Create new child folder from PVP using special characters========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建子收藏夹
            p_patentViewPage.func_create_folder_pvp(ParentFolder);
            p_patentViewPage.FunCreatChildFolderBS(ParentFolder, FolderName);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-878 Create new child folder from PVP using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-879 Create new child folder from PVP using number
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param ParentFolder
    * @param Query
    * @param FolderName2
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "Query",
            "FolderName2"
    })
    @Test
    public void userlist_Pat_879(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String Query,
            String FolderName2
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-879 Create new child folder from PVP using number========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建子收藏夹
            p_patentViewPage.func_create_folder_pvp(ParentFolder);
            p_patentViewPage.FunCreatChildFolderBS(ParentFolder, FolderName2);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-879 Create new child folder from PVP using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-880 Create new child folder from PVP which contains spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "Query",
            "FolderName4"
    })
    @Test
    public void userlist_Pat_880(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String Query,
            String FolderName4
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-880 Create new child folder from PVP which contains spaces========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建子收藏夹
            p_patentViewPage.func_create_folder_pvp(ParentFolder);
            p_patentViewPage.FunCreatChildFolderBS(ParentFolder, FolderName4);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-880 Create new child folder from PVP which contains spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-881 Create new folder from PVP which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "Query",
            "FolderName3"
    })
    @Test
    public void userlist_Pat_881(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String Query,
            String FolderName3
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-881 Create new folder from PVP which contains html tag========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建子收藏夹
            p_patentViewPage.func_create_folder_pvp(ParentFolder);
            p_patentViewPage.FunCreatChildFolderBS(ParentFolder, FolderName3);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(FolderName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(ParentFolder);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-881 Create new folder from PVP which contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-910 Edit parent folder name from Userlist page using original name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "EditName1",
            "PreFolder"
    })
    @Test
    public void userlist_Pat_910(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String EditName1,
            String PreFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("==================Pat-910 Edit parent folder name from Userlist page using original name==========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检查该页面
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(PreFolder);
            //编辑文件夹名称
            p_userListPage.func_edit_userListFolder(PreFolder, EditName1);
            //刷新页面
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName1);
            p_userListPage.func_delete_userListFolder(EditName1);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-910 Edit parent folder name from Userlist page using original name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-911 Edit parent folder name from Userlist page using English/Chinese characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "EditName2",
            "PreFolder"
    })
    @Test
    public void userlist_Pat_911(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String EditName2,
            String PreFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("==================Pat-917 Edit folder name from Userlist page using number==========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检查该页面
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(PreFolder);
            //编辑文件夹名称
            p_userListPage.func_edit_userListFolder(PreFolder, EditName2);
            //刷新页面
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName2);
            p_userListPage.func_delete_userListFolder(EditName2);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-917 Edit folder name from Userlist page using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-914 Edit parent folder name from Userlist page using specail characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "EditName4",
            "PreFolder"
    })
    @Test
    public void userlist_Pat_914(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String EditName4,
            String PreFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("==================Pat-914 Edit parent folder name from Userlist page using specail characters==========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检查该页面
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(PreFolder);
            //编辑文件夹名称
            p_userListPage.func_edit_userListFolder(PreFolder, EditName4);
            //刷新页面
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName4);
            p_userListPage.func_delete_userListFolder(EditName4);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-914 Edit parent folder name from Userlist page using specail characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-917 Edit folder name from Userlist page using number
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param EditName3
    * @param PreFolder
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "EditName3",
            "PreFolder"
    })
    @Test
    public void userlist_Pat_917(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String EditName3,
            String PreFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("==================Pat-917 Edit folder name from Userlist page using number==========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检查该页面
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(PreFolder);
            //编辑文件夹名称
            p_userListPage.func_edit_userListFolder(PreFolder, EditName3);
            //刷新页面
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName3);
            p_userListPage.func_delete_userListFolder(EditName3);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-917 Edit folder name from Userlist page using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-920 Edit parent folder name from Userlist page which title contains spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "EditName5",
            "PreFolder"
    })
    @Test
    public void userlist_Pat_920(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String EditName5,
            String PreFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("==================Pat-920 Edit parent folder name from Userlist page which title contains spaces==========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检查该页面
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(PreFolder);
            //编辑文件夹名称
            p_userListPage.func_edit_userListFolder(PreFolder, EditName5);
            //刷新页面
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName5);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName5);
            p_userListPage.func_delete_userListFolder(EditName5);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-920 Edit parent folder name from Userlist page which title contains spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-922 Edit parent folder name from Userlist page which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "EditName6",
            "PreFolder"
    })
    @Test
    public void userlist_Pat_922(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String EditName6,
            String PreFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("==================Pat-922 Edit parent folder name from Userlist page which contains html tag==========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检查该页面
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(PreFolder);
            //编辑文件夹名称
            p_userListPage.func_edit_userListFolder(PreFolder, EditName6);
            //刷新页面
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName6);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName6);
            p_userListPage.func_delete_userListFolder(EditName6);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-922 Edit parent folder name from Userlist page which contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-962 Edit parent folder name from SRP using original name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName1",
            "Query"
    })
    @Test
    public void userlist_Pat_962(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName1,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-962 Edit parent folder name from SRP using original name======");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(),true);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //简单搜索后进入到搜索结果页
            p_searchPage.func_field_simple(Query);
            //搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            p_searchResultPage.func_create_folder_srp(PreFolder);
            p_searchResultPage.func_editListNameSRP(PreFolder, EditName1);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName1);
            p_userListPage.func_delete_userListFolder(EditName1);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-962 Edit parent folder name from SRP using original name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-963 Edit parent folder name from SRP using English/Chinese characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName2",
            "Query"
    })
    @Test
    public void userlist_Pat_963(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName2,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-963 Edit parent folder name from SRP using English/Chinese characters======");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(),true);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //简单搜索后进入到搜索结果页
            p_searchPage.func_field_simple(Query);
            //搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            p_searchResultPage.func_create_folder_srp(PreFolder);
            p_searchResultPage.func_editListNameSRP(PreFolder, EditName2);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName2);
            p_userListPage.func_delete_userListFolder(EditName2);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-963 Edit parent folder name from SRP using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-964 Edit folder name from SRP using special characters
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param PreFolder
    * @param EditName4
    * @param Query
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName4",
            "Query"
    })
    @Test
    public void userlist_Pat_964(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName4,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-964 Edit folder name from SRP using special characters======");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(),true);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //简单搜索后进入到搜索结果页
            p_searchPage.func_field_simple(Query);
            //搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            p_searchResultPage.func_create_folder_srp(PreFolder);
            p_searchResultPage.func_editListNameSRP(PreFolder, EditName4);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName4);
            p_userListPage.func_delete_userListFolder(EditName4);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-964 Edit folder name from SRP using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-965 Edit parent folder name from SRP using number
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName3",
            "Query"
    })
    @Test
    public void userlist_Pat_965(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName3,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("====================Pat-965 Edit parent folder name from SRP using number======");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(),true);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //简单搜索后进入到搜索结果页
            p_searchPage.func_field_simple(Query);
            //搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            p_searchResultPage.func_create_folder_srp(PreFolder);
            p_searchResultPage.func_editListNameSRP(PreFolder, EditName3);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName3);
            p_userListPage.func_delete_userListFolder(EditName3);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-965 Edit parent folder name from SRP using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-966 Edit parent folder name from SRP which contains spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName5",
            "Query"
    })
    @Test
    public void userlist_Pat_966(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName5,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("====================Pat-966 Edit parent folder name from SRP which contains spaces======");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(),true);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //简单搜索后进入到搜索结果页
            p_searchPage.func_field_simple(Query);
            //搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            p_searchResultPage.func_create_folder_srp(PreFolder);
            p_searchResultPage.func_editListNameSRP(PreFolder, EditName5);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName5);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName5);
            p_userListPage.func_delete_userListFolder(EditName5);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-966 Edit parent folder name from SRP which contains spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-967 Edit parent folder name from SRP which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName6",
            "Query"
    })
    @Test
    public void userlist_Pat_967(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName6,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("====================Pat-967 Edit parent folder name from SRP which contains html tag======");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(),true);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //简单搜索后进入到搜索结果页
            p_searchPage.func_field_simple(Query);
            //搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            p_searchResultPage.func_create_folder_srp(PreFolder);
            p_searchResultPage.func_editListNameSRP(PreFolder, EditName6);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName6);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName6);
            p_userListPage.func_delete_userListFolder(EditName6);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-967 Edit parent folder name from SRP which contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-930 Edit parent folder name from BS using original name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName1",
            "PublicationNumber"
    })
    @Test
    public void userlist_Pat_930(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName1,
            String PublicationNumber
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======Pat-930 Edit parent folder name from BS using original name======");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            //新建
            p_searchPage.func_create_parent_folder_bs(PreFolder);
            p_searchPage.func_editListNameBS(PreFolder, EditName1);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName1);
            p_userListPage.func_delete_userListFolder(EditName1);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-930 Edit parent folder name from BS using original name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-931 Edit folder name from BS using English/Chinese characters
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginpage_pwd
    * @param PreFolder
    * @param EditName3
    * @Param PublicationNumber
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName3",
            "PublicationNumber"
    })
    @Test
    public void userlist_Pat_931(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName3,
            String PublicationNumber
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======Pat-931 Edit folder name from BS using English/Chinese characters======");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            //新建
            p_searchPage.func_create_parent_folder_bs(PreFolder);
            p_searchPage.func_editListNameBS(PreFolder, EditName3);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName3);
            p_userListPage.func_delete_userListFolder(EditName3);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-931 Edit folder name from BS using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-939 Edit parent folder name from BS using special characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName4",
            "PublicationNumber"
    })
    @Test
    public void userlist_Pat_939(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName4,
            String PublicationNumber
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======Pat-939 Edit parent folder name from BS using special characters======");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            //新建
            p_searchPage.func_create_parent_folder_bs(PreFolder);
            p_searchPage.func_editListNameBS(PreFolder, EditName4);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName4);
            p_userListPage.func_delete_userListFolder(EditName4);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-939 Edit parent folder name from BS using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-940 Edit parent folder name from BS using number
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName3",
            "PublicationNumber"
    })
    @Test
    public void userlist_Pat_940(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName3,
            String PublicationNumber
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======Pat-940 Edit parent folder name from BS using number======");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            //新建
            p_searchPage.func_create_parent_folder_bs(PreFolder);
            p_searchPage.func_editListNameBS(PreFolder, EditName3);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName3);
            p_userListPage.func_delete_userListFolder(EditName3);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-940 Edit parent folder name from BS using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-948 Edit parent folder name from BS which contains spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName5",
            "PublicationNumber"
    })
    @Test
    public void userlist_Pat_948(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName5,
            String PublicationNumber
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======Pat-948 Edit parent folder name from BS which contains spaces======");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            //新建
            p_searchPage.func_create_parent_folder_bs(PreFolder);
            p_searchPage.func_editListNameBS(PreFolder, EditName5);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName5);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName5);
            p_userListPage.func_delete_userListFolder(EditName5);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-948 Edit parent folder name from BS which contains spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-949 Edit folder name from BS which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName6",
            "PublicationNumber"
    })
    @Test
    public void userlist_Pat_949(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName6,
            String PublicationNumber
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======Pat-949 Edit folder name from BS which contains html tag======");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //字段搜索页
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            //新建
            p_searchPage.func_create_parent_folder_bs(PreFolder);
            p_searchPage.func_editListNameBS(PreFolder, EditName6);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName6);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName6);
            p_userListPage.func_delete_userListFolder(EditName6);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-949 Edit folder name from BS which contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-950 Edit parent folder name from PVP using original name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName1",
            "Query"
    })
    @Test
    public void userlist_Pat_950(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName1,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("===============Pat-950 Edit parent folder name from PVP using original name====================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建收藏夹
            p_patentViewPage.func_create_folder_pvp(PreFolder);
            p_patentViewPage.func_editListNamePVP(PreFolder, EditName1);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName1);
            p_userListPage.func_delete_userListFolder(EditName1);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-950 Edit parent folder name from PVP using original name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-952 Edit parent folder name from PVP using English/Chinese characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName2",
            "Query"
    })
    @Test
    public void userlist_Pat_952(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName2,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("===============Pat-952 Edit parent folder name from PVP using English/Chinese characters===================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建收藏夹
            p_patentViewPage.func_create_folder_pvp(PreFolder);
            p_patentViewPage.func_editListNamePVP(PreFolder, EditName2);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName2);
            p_userListPage.func_delete_userListFolder(EditName2);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-952 Edit parent folder name from PVP using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-953 Edit folder name from PVP using special characters
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param PreFolder
    * @param EditName4
    * @param Query
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName4",
            "Query"
    })
    @Test
    public void userlist_Pat_953(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName4,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("===============Pat-953 Edit folder name from PVP using special characters====================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建收藏夹
            p_patentViewPage.func_create_folder_pvp(PreFolder);
            p_patentViewPage.func_editListNamePVP(PreFolder, EditName4);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName4);
            p_userListPage.func_delete_userListFolder(EditName4);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-953 Edit folder name from PVP using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-954 Edit parent folder name from PVP using number
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName3",
            "Query"
    })
    @Test
    public void userlist_Pat_954(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName3,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("===============Pat-954 Edit parent folder name from PVP using number====================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建收藏夹
            p_patentViewPage.func_create_folder_pvp(PreFolder);
            p_patentViewPage.func_editListNamePVP(PreFolder, EditName3);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName3);
            p_userListPage.func_delete_userListFolder(EditName3);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-954 Edit parent folder name from PVP using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-955 Edit parent folder name from PVP which contains spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName5",
            "Query"
    })
    @Test
    public void userlist_Pat_955(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName5,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("===============Pat-955 Edit parent folder name from PVP which contains spaces====================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建收藏夹
            p_patentViewPage.func_create_folder_pvp(PreFolder);
            p_patentViewPage.func_editListNamePVP(PreFolder, EditName5);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName5);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName5);
            p_userListPage.func_delete_userListFolder(EditName5);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-955 Edit parent folder name from PVP which contains spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-956 Edit parent folder name from PVP which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "EditName6",
            "Query"
    })
    @Test
    public void userlist_Pat_956(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String EditName6,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("===============Pat-956 Edit parent folder name from PVP which contains html tag====================");
            //登陆
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建收藏夹
            p_patentViewPage.func_create_folder_pvp(PreFolder);
            p_patentViewPage.func_editListNamePVP(PreFolder, EditName6);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName6);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(EditName6);
            p_userListPage.func_delete_userListFolder(EditName6);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-956 Edit parent folder name from PVP which contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-974 Edit child folder name from Userlist page using original name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "EditName1"
    })

    @Test
    public void userlist_Pat_974(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String EditName1
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-974 Edit child folder name from Userlist page using original name========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //先检测下
            p_userListPage.func_delete_userListFolder(PreChildFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(PreFolder);
            Thread.sleep(3000);
            //新建子文件夹
            p_userListPage.funcCreateChildFolderInput(PreFolder, PreChildFolder);
            //编辑子文件夹
            p_userListPage.func_edit_userListFolder(PreChildFolder, EditName1);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-974 Edit child folder name from Userlist page using original name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-975 Edit Child folder name from Userlist Page using English/Chinese characters
    /*
     *  @param loginPage_url
     *  @param loginPage_uid
     *  @param loginPage_pwd
     *  @param PreFolder
     *  @param PreChildFolder
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "EditName2"
    })
    @Test
    public void userlist_Pat_975(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String EditName2
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-975 Edit Child folder name from Userlist Page using English/Chinese characters========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //先检测下
            p_userListPage.func_delete_userListFolder(PreChildFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(PreFolder);
            Thread.sleep(3000);
            //新建子文件夹
            p_userListPage.funcCreateChildFolderInput(PreFolder, PreChildFolder);
            //编辑子文件夹
            p_userListPage.func_edit_userListFolder(PreChildFolder, EditName2);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-975 Edit Child folder name from Userlist Page using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-976 Edit child folder name from Userlist page using special characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "EditName4"
    })
    @Test
    public void userlist_Pat_976(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String EditName4
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-976 Edit child folder name from Userlist page using special characters========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //先检测下
            p_userListPage.func_delete_userListFolder(PreChildFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(PreFolder);
            Thread.sleep(3000);
            //新建子文件夹
            p_userListPage.funcCreateChildFolderInput(PreFolder, PreChildFolder);
            //编辑子文件夹
            p_userListPage.func_edit_userListFolder(PreChildFolder, EditName4);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-976 Edit child folder name from Userlist page using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-977 Edit child folder name from Userlist page using number
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "EditName3"
    })
    @Test
    public void userlist_Pat_977(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String EditName3
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-977 Edit child folder name from Userlist page using number========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //先检测下
            p_userListPage.func_delete_userListFolder(PreChildFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(PreFolder);
            Thread.sleep(3000);
            //新建子文件夹
            p_userListPage.funcCreateChildFolderInput(PreFolder, PreChildFolder);
            //编辑子文件夹
            p_userListPage.func_edit_userListFolder(PreChildFolder, EditName3);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-977 Edit child folder name from Userlist page using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-978 Edit child folder name from Userlist page which contains spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "EditName5"
    })
    @Test
    public void userlist_Pat_978(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String EditName5
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-978 Edit child folder name from Userlist page which contains spaces========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //先检测下
            p_userListPage.func_delete_userListFolder(PreChildFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(PreFolder);
            Thread.sleep(3000);
            //新建子文件夹
            p_userListPage.funcCreateChildFolderInput(PreFolder, PreChildFolder);
            //编辑子文件夹
            p_userListPage.func_edit_userListFolder(PreChildFolder, EditName5);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName5);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-978 Edit child folder name from Userlist page which contains spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-979 Edit child folder name from Userlist page which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "EditName6"
    })
    @Test
    public void userlist_Pat_979(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String EditName6
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=======================Pat-979 Edit child folder name from Userlist page which contains html tag========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //先检测下
            p_userListPage.func_delete_userListFolder(PreChildFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(PreFolder);
            Thread.sleep(3000);
            //新建子文件夹
            p_userListPage.funcCreateChildFolderInput(PreFolder, PreChildFolder);
            //编辑子文件夹
            p_userListPage.func_edit_userListFolder(PreChildFolder, EditName6);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName6);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-979 Edit child folder name from Userlist page which contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-998 Edit child folder name from SRP using original name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "Query",
            "EditName1"
    })
    @Test
    public void userlist_Pat_998(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String Query,
            String EditName1
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-998 Edit child folder name from SRP using original name========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //搜索进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(PreFolder);
            //新建子文件夹
            p_searchResultPage.funCreatChildFolderSRP(PreFolder, PreChildFolder);
            p_searchResultPage.func_editListNameSRP(PreChildFolder, EditName1);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-998 Edit child folder name from SRP using original name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-999 Edit child folder name from SRP using English/Chinese characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "Query",
            "EditName2"
    })
    @Test
    public void userlist_Pat_999(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String Query,
            String EditName2
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-999 Edit child folder name from SRP using English/Chinese characters========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //搜索进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(PreFolder);
            //新建子文件夹
            p_searchResultPage.funCreatChildFolderSRP(PreFolder, PreChildFolder);
            p_searchResultPage.func_editListNameSRP(PreChildFolder, EditName2);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-999 Edit child folder name from SRP using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1000 Edit child folder name from SRP using special characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "Query",
            "EditName4"
    })
    @Test
    public void userlist_Pat_1000(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String Query,
            String EditName4
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-1000 Edit child folder name from SRP using special characters========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //搜索进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(PreFolder);
            //新建子文件夹
            p_searchResultPage.funCreatChildFolderSRP(PreFolder, PreChildFolder);
            p_searchResultPage.func_editListNameSRP(PreChildFolder, EditName4);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-1000 Edit child folder name from SRP using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1001 Edit child folder name from SRP using number
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param ParentFolder
    * @param EditName3
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "Query",
            "EditName3"
    })
    @Test
    public void userlist_Pat_1001(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String Query,
            String EditName3
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-1001 Edit child folder name from SRP using number========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //搜索进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(PreFolder);
            //新建子文件夹
            p_searchResultPage.funCreatChildFolderSRP(PreFolder, PreChildFolder);
            p_searchResultPage.func_editListNameSRP(PreChildFolder, EditName3);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-1001 Edit child folder name from SRP using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1002 Edit child folder name from SRP which contans spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "Query",
            "EditName5"
    })
    @Test
    public void userlist_Pat_1002(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String Query,
            String EditName5
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-1002 Edit child folder name from SRP which contans spaces========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //搜索进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(PreFolder);
            //新建子文件夹
            p_searchResultPage.funCreatChildFolderSRP(PreFolder, PreChildFolder);
            p_searchResultPage.func_editListNameSRP(PreChildFolder, EditName5);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName5);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-1002 Edit child folder name from SRP which contans spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1003 Edit child folder name from SRP which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "Query",
            "EditName6"
    })
    @Test
    public void userlist_Pat_1003(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String Query,
            String EditName6
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-1003 Edit child folder name from SRP which contains html tag========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //搜索进入搜索结果页
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_create_folder_srp(PreFolder);
            //新建子文件夹
            p_searchResultPage.funCreatChildFolderSRP(PreFolder, PreChildFolder);
            p_searchResultPage.func_editListNameSRP(PreChildFolder, EditName6);
            p_searchResultPage.btn_cancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName6);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-1003 Edit child folder name from SRP which contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-984 Edit child folder name from BS using original name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "PreFolder",
            "PreChildFolder",
            "EditName1"
    })
    @Test
    public void userlist_Pat_984(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String PreFolder,
            String PreChildFolder,
            String EditName1
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================Pat-984 Edit child folder name from BS using original name======== ");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            p_searchPage.func_create_parent_folder_bs(PreFolder);
            p_searchPage.funCreatChildFolderBS(PreFolder, PreChildFolder);
            p_searchPage.func_editListNameBS(PreChildFolder, EditName1);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-984 Edit child folder name from BS using original name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-985 Edit child folder name from BS using English/Chinese characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "PreFolder",
            "PreChildFolder",
            "EditName2"
    })
    @Test
    public void userlist_Pat_985(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String PreFolder,
            String PreChildFolder,
            String EditName2
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================Pat-985 Edit child folder name from BS using English/Chinese characters======== ");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            p_searchPage.func_create_parent_folder_bs(PreFolder);
            p_searchPage.funCreatChildFolderBS(PreFolder, PreChildFolder);
            p_searchPage.func_editListNameBS(PreChildFolder, EditName2);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-985 Edit child folder name from BS using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-986 Edit child folder name from BS using special characters
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param PublicationNumber
    * @param PreFolder
    * @param PreChildFolder
    * @param EditName4
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "PreFolder",
            "PreChildFolder",
            "EditName4"
    })
    @Test
    public void userlist_Pat_986(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String PreFolder,
            String PreChildFolder,
            String EditName4
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================Pat-986 Edit child folder name from BS using special characters======== ");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            p_searchPage.func_create_parent_folder_bs(PreFolder);
            p_searchPage.funCreatChildFolderBS(PreFolder, PreChildFolder);
            p_searchPage.func_editListNameBS(PreChildFolder, EditName4);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-986 Edit child folder name from BS using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-987 Edit child folder name from BS using number
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "PreFolder",
            "PreChildFolder",
            "EditName3"
    })
    @Test
    public void userlist_Pat_987(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String PreFolder,
            String PreChildFolder,
            String EditName3
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("===========================Pat-987 Edit child folder name from BS using number======== ");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            p_searchPage.func_create_parent_folder_bs(PreFolder);
            p_searchPage.funCreatChildFolderBS(PreFolder, PreChildFolder);
            p_searchPage.func_editListNameBS(PreChildFolder, EditName3);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-987 Edit child folder name from BS using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-988 Edit child folder name from BS which contains space
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "PreFolder",
            "PreChildFolder",
            "EditName5"
    })
    @Test
    public void userlist_Pat_988(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String PreFolder,
            String PreChildFolder,
            String EditName5
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("===========================Pat-988 Edit child folder name from BS which contains space======== ");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            p_searchPage.func_create_parent_folder_bs(PreFolder);
            p_searchPage.funCreatChildFolderBS(PreFolder, PreChildFolder);
            p_searchPage.func_editListNameBS(PreChildFolder, EditName5);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName5);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-988 Edit child folder name from BS which contains space", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-989 Edit child folder name from BS which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PublicationNumber",
            "PreFolder",
            "PreChildFolder",
            "EditName6"
    })
    @Test
    public void userlist_Pat_989(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PublicationNumber,
            String PreFolder,
            String PreChildFolder,
            String EditName6
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("===========================Pat-989 Edit child folder name from BS which contains html tag======== ");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //切换至bulk search tab下
            p_searchPage.func_click_bulkSearch();
            p_searchPage.FuncBulkSearchSimple(PublicationNumber);
            Thread.sleep(5000);
            p_searchPage.func_create_parent_folder_bs(PreFolder);
            p_searchPage.funCreatChildFolderBS(PreFolder, PreChildFolder);
            p_searchPage.func_editListNameBS(PreChildFolder, EditName6);
            p_searchPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName6);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-989 Edit child folder name from BS which contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-991 Edit child folder name from PVP using original name
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "Query",
            "EditName1"
    })
    @Test
    public void userlist_Pat_991(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String Query,
            String EditName1
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-991 Edit child folder name from PVP using original name========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行简单搜索
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建子收藏夹
            p_patentViewPage.func_create_folder_pvp(PreFolder);
            p_patentViewPage.FunCreatChildFolderBS(PreFolder, PreChildFolder);
            p_patentViewPage.func_editListNamePVP(PreChildFolder, EditName1);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName1);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-991 Edit child folder name from PVP using original name", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-992 Edit child folder name from PVP using English/Chinese characters
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param PreFolder
    * @param PreChildFolder
    * @param Query
    * @param EditName2
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "Query",
            "EditName2"
    })
    @Test
    public void userlist_Pat_992(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String Query,
            String EditName2
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-992 Edit child folder name from PVP using English/Chinese characters========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行简单搜索
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建子收藏夹
            p_patentViewPage.func_create_folder_pvp(PreFolder);
            p_patentViewPage.FunCreatChildFolderBS(PreFolder, PreChildFolder);
            p_patentViewPage.func_editListNamePVP(PreChildFolder, EditName2);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName2);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-992 Edit child folder name from PVP using English/Chinese characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-993 Edit child folder name from PVP using special characters
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "Query",
            "EditName4"
    })
      @Test
      public void userlist_Pat_993(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String Query,
            String EditName4
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-993 Edit child folder name from PVP using special characters========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行简单搜索
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建子收藏夹
            p_patentViewPage.func_create_folder_pvp(PreFolder);
            p_patentViewPage.FunCreatChildFolderBS(PreFolder, PreChildFolder);
            p_patentViewPage.func_editListNamePVP(PreChildFolder, EditName4);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName4);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-993 Edit child folder name from PVP using special characters", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-994 Edit child folder name from PVP using number
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "Query",
            "EditName3"
    })
    @Test
    public void userlist_Pat_994(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String Query,
            String EditName3
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-994 Edit child folder name from PVP using number========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行简单搜索
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建子收藏夹
            p_patentViewPage.func_create_folder_pvp(PreFolder);
            p_patentViewPage.FunCreatChildFolderBS(PreFolder, PreChildFolder);
            p_patentViewPage.func_editListNamePVP(PreChildFolder, EditName3);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName3);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-994 Edit child folder name from PVP using number", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-995 Edit child folder name from PVP which contains spaces
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "Query",
            "EditName5"
    })
    @Test
    public void userlist_Pat_995(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String Query,
            String EditName5
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-995 Edit child folder name from PVP which contains spaces========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行简单搜索
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建子收藏夹
            p_patentViewPage.func_create_folder_pvp(PreFolder);
            p_patentViewPage.FunCreatChildFolderBS(PreFolder, PreChildFolder);
            p_patentViewPage.func_editListNamePVP(PreChildFolder, EditName5);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName5);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-995 Edit child folder name from PVP which contains spaces", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-996 Edit child folder name from PVP which contains html tag
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "PreChildFolder",
            "Query",
            "EditName6"
    })
    @Test
    public void userlist_Pat_996(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String PreChildFolder,
            String Query,
            String EditName6
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-996 Edit child folder name from PVP which contains html tag========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(PreFolder);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //进行简单搜索
            p_searchPage.func_field_simple(Query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //点击某一专利的PN号
            Thread.sleep(5000);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            //进入搜索结果页
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //新建子收藏夹
            p_patentViewPage.func_create_folder_pvp(PreFolder);
            p_patentViewPage.FunCreatChildFolderBS(PreFolder, PreChildFolder);
            p_patentViewPage.func_editListNamePVP(PreChildFolder, EditName6);
            p_patentViewPage.btnCancel_addToListDialog().click();
            //进入收藏夹页面
            p_patentViewPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(EditName6);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除所建的收藏夹
            p_userListPage.func_click_userListFolder(PreFolder);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-996 Edit child folder name from PVP which contains html tag", "jpg");
            Assert.assertEquals(false, true);
        }
    }


    //Pat-1062 Delete parent folder which without child folder
    /*
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     * @param ParentFolder
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder"
    })
    @Test
    public void userlist_Pat_1062(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-1062 Delete parent folder which without child folder========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_create_folder_input(ParentFolder);
            Thread.sleep(5000);
            //删除新建的收藏夹
            p_userListPage.func_delete_userListFolder(ParentFolder);
            //刷新页面
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            names.add(ParentFolder);
            result &= p_userListPage.func_verify_userListFolderDoesNotExist(names);
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-1062 Delete parent folder which without child folder", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1063 Delete parent folder which contains child folder
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param ParentFolder
    * @param ChildFolder
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "ChildFolder"
    })
    @Test
    public void userlist_Pat_1063(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String ChildFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Delete parent folder which contains child folder========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_create_folder_input(ParentFolder);
            p_userListPage.funcCreateChildFolderInput(ParentFolder, ChildFolder);
            //删除指定的收藏夹
            p_userListPage.func_delete_userListFolder(ParentFolder);
            //刷新页面
            d.navigate().refresh();
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> name1 = new ArrayList<String>();
            names.add(ParentFolder);
            name1.add(ChildFolder);
            result &= p_userListPage.func_verify_userListFolderDoesNotExist(names);
            result &= p_userListPage.func_verify_userListFolderDoesNotExist(name1);
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Delete parent folder which contains child folder", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1065 Delete child list
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param PreFolder
    * @param ChildFolder
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "PreFolder",
            "ChildFolder"
    })
    @Test
    public void userlist_Pat_1065(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String PreFolder,
            String ChildFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-1065 Delete child list========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_create_folder_input(PreFolder);
            p_userListPage.funcCreateChildFolderInput(PreFolder, ChildFolder);
            //展开
            p_userListPage.func_click_expandAll();
            //删除子收藏夹
            p_userListPage.func_delete_userListFolder(ChildFolder);
            //刷新页面
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> name1 = new ArrayList<String>();
            names.add(PreFolder);
            name1.add(ChildFolder);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            result &= p_userListPage.func_verify_userListFolderDoesNotExist(name1);
            Assert.assertEquals(result, true);
            p_userListPage.func_delete_userListFolder(PreFolder);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();


            t.takeScreenshot(d, System.getProperty("user.dir"), "Delete child list", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1969 Check expand function which contains only parent folder
    @Parameters ({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder"
    })
    @Test
    public  void userlist_Pat_1969 (
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-1969 Check expand function which contains only parent folder========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            //验证
            ArrayList<String> names = new ArrayList<String>();
            names.add(ParentFolder);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            Assert.assertEquals(result, true);
            //删除该收藏夹
            p_userListPage.func_delete_userListFolder(ParentFolder);
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-1969 Check expand function which contains only parent folder", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1970 Check expand function when contains parent folder and child folder
    @Parameters ({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ParentFolder",
            "ChildFolder"
    })
    @Test
    public  void userlist_Pat_1970 (
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ParentFolder,
            String ChildFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-1970 Check expand function when contains parent folder and child folder========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检验
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            p_userListPage.funcCreateChildFolderInput(ParentFolder, ChildFolder);
            d.navigate().refresh();
            Thread.sleep(3000);
            //验证
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> name1 = new ArrayList<String>();
            names.add(ParentFolder);
            name1.add(ChildFolder);
            result &= p_userListPage.func_verify_userListFolderExists(names);
            result &= p_userListPage.func_verify_userListFolderDoesNotExist(name1);
            p_userListPage.func_click_expandAll();
            result &= p_userListPage.func_verify_userListFolderExists(names);
            result &= p_userListPage.func_verify_userListFolderExists(name1);
            Assert.assertEquals(result, true);
            //删除该收藏夹
            p_userListPage.func_delete_userListFolder(ParentFolder);
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-1970 Check expand function when contains parent folder and child folder", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1083 Share folder which not contain child folder(1 to 1 share)
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "loginPage_uid1",
            "loginPage_pwd1",
            "ParentFolder"
    })
    @Test
    public void userlist_Pat_1083(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String loginPage_uid1,
            String loginPage_pwd1,
            String ParentFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-1083 Share folder which not contain child folder(1 to 1 share)========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            //1-1 分享收藏夹
            p_userListPage.funcShareList_1To1Share(ParentFolder, loginPage_uid1);
            p_userListPage.func_click_userListFolder(ParentFolder);
            Thread.sleep(3000);
            result &= p_userListPage.func_verify_shareInfo("List shared with 1 person");
            p_userListPage.func_click_logout();
            //被分享的人登陆查看收藏夹
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid1, loginPage_pwd1);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证是否存在分享的收藏夹
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(ParentFolder);
            result &= p_userListPage.func_verify_sharedUserListFolderExists(names);
            Assert.assertEquals(result, true);
            p_userListPage.func_click_logout();
            //删除该收藏夹
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-1083 Share folder which not contain child folder(1 to 1 share)", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1087 Share folder which not contain child folder(group share)
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "loginPage_uid1",
            "loginPage_pwd1",
            "ParentFolder"
    })
    @Test
    public void userlist_Pat_1087(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String loginPage_uid1,
            String loginPage_pwd1,
            String ParentFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-1087 Share folder which not contain child folder(group share)========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            //group  分享收藏夹
            p_userListPage.funcShareList_GroupShare(ParentFolder, loginPage_uid1);
            p_userListPage.func_click_userListFolder(ParentFolder);
            Thread.sleep(3000);
            result &= p_userListPage.func_verify_shareInfo("List shared with 1 person");
            p_userListPage.func_click_logout();
            //被分享的人登陆查看收藏夹
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid1, loginPage_pwd1);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证是否存在分享的收藏夹
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            names.add(ParentFolder);
            result &= p_userListPage.func_verify_sharedUserListFolderExists(names);
            Assert.assertEquals(result, true);
            p_userListPage.func_click_logout();
            //删除该收藏夹
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-1087 Share folder which not contain child folder(group share)", "jpg");
            Assert.assertEquals(false, true);
        }
    }


    //Pat-1090 Share folder which contains child folder(1 to 1 share)
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param loginPage_uid1
    * @param loginPage_pwd1
    * @param ParentFolder
    * @param ChildFolder
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "loginPage_uid1",
            "loginPage_pwd1",
            "ParentFolder",
            "ChildFolder"
    })
    @Test
    public void userlist_Pat_1090(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String loginPage_uid1,
            String loginPage_pwd1,
            String ParentFolder,
            String ChildFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-1090 Share folder which contains child folder(1 to 1 share)========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            p_userListPage.funcCreateChildFolderInput(ParentFolder, ChildFolder);
            //1-1 分享收藏夹
            p_userListPage.funcShareList_1To1Share(ParentFolder, loginPage_uid1);
            p_userListPage.func_click_userListFolder(ParentFolder);
            Thread.sleep(3000);
            result &= p_userListPage.func_verify_shareInfo("List shared with 1 person");
            p_userListPage.func_click_logout();
            //被分享的人登陆查看收藏夹
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid1, loginPage_pwd1);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证是否存在分享的收藏夹
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> name1 = new ArrayList<String>();
            names.add(ParentFolder);
            name1.add(ChildFolder);
            result &= p_userListPage.func_verify_sharedUserListFolderExists(names);
            result &= p_userListPage.func_verify_sharedUserListFolderExists(name1);
            Assert.assertEquals(result, true);
            p_userListPage.func_click_logout();
            //删除该收藏夹
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Share folder which contains child folder(1 to 1 share)", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1094 Share folder which contains child folder(group share)
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param loginPage_uid1
    * @param loginPage_pwd1
    * @param ParentFolder
    * @param ChildFolder
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "loginPage_uid1",
            "loginPage_pwd1",
            "ParentFolder",
            "ChildFolder"
    })
    @Test
    public void userlist_Pat_1094(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String loginPage_uid1,
            String loginPage_pwd1,
            String ParentFolder,
            String ChildFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-1094 Share folder which contains child folder(group share)========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            p_userListPage.funcCreateChildFolderInput(ParentFolder, ChildFolder);
            //1-1 分享收藏夹
            p_userListPage.funcShareList_GroupShare(ParentFolder, loginPage_uid1);
            p_userListPage.func_click_userListFolder(ParentFolder);
            Thread.sleep(3000);
            result &= p_userListPage.func_verify_shareInfo("List shared with 1 person");
            Assert.assertEquals(result, true);
            p_userListPage.func_click_logout();
            //被分享的人登陆查看收藏夹
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid1, loginPage_pwd1);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证是否存在分享的收藏夹
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> name1 = new ArrayList<String>();
            names.add(ParentFolder);
            name1.add(ChildFolder);
            result &= p_userListPage.func_verify_sharedUserListFolderExists(names);
            result &= p_userListPage.func_verify_sharedUserListFolderExists(name1);
            Assert.assertEquals(result, true);
            p_userListPage.func_click_logout();
            //删除该收藏夹
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Share folder which contains child folder(group share)", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1098 Share child folder(1-1 share)
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param loginPage_uid1
    * @param loginPage_pwd1
    * @param ParentFolder
    * @param ChildFolder
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "loginPage_uid1",
            "loginPage_pwd1",
            "ParentFolder",
            "ChildFolder"
    })
    @Test
    public void userlist_Pat_1098(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String loginPage_uid1,
            String loginPage_pwd1,
            String ParentFolder,
            String ChildFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-1098 Share child folder(1-1 share)========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            p_userListPage.funcCreateChildFolderInput(ParentFolder, ChildFolder);
            p_userListPage.func_click_expandAll();
            //1-1 分享收藏夹
            p_userListPage.funcShareList_1To1Share(ChildFolder, loginPage_uid1);
            p_userListPage.func_click_userListFolder(ChildFolder);
            Thread.sleep(3000);
            result &= p_userListPage.func_verify_shareInfo("List shared with 1 person");
            Assert.assertEquals(result, true);
            p_userListPage.func_click_logout();
            //被分享的人登陆查看收藏夹
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid1, loginPage_pwd1);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证是否存在分享的收藏夹
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> name1 = new ArrayList<String>();
            names.add(ParentFolder);
            name1.add(ChildFolder);
            result &= p_userListPage.func_verify_sharedUserListFolderDoesNotExist(names);
            result &= p_userListPage.func_verify_sharedUserListFolderExists(name1);
            Assert.assertEquals(result, true);
            p_userListPage.func_click_logout();
            //删除该收藏夹
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Share child folder(1-1 share)", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1099 Share child folder(group share)
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param loginPage_uid1
    * @param loginPage_pwd1
    * @param ParentFolder
    * @param ChildFolder
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "loginPage_uid1",
            "loginPage_pwd1",
            "ParentFolder",
            "ChildFolder"
    })
    @Test
    public void userlist_Pat_1099(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String loginPage_uid1,
            String loginPage_pwd1,
            String ParentFolder,
            String ChildFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("======================Pat-1099 Share child folder(group share)=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //检测页面
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            //设定前提条件
            p_userListPage.func_create_folder_input(ParentFolder);
            p_userListPage.funcCreateChildFolderInput(ParentFolder, ChildFolder);
            p_userListPage.func_click_expandAll();
            //group 分享收藏夹
            p_userListPage.funcShareList_GroupShare(ChildFolder, loginPage_uid1);
            p_userListPage.func_click_userListFolder(ChildFolder);
            Thread.sleep(3000);
            result &= p_userListPage.func_verify_shareInfo("List shared with 1 person");
            Assert.assertEquals(result, true);
            p_userListPage.func_click_logout();
            //被分享的人登陆查看收藏夹
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid1, loginPage_pwd1);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //验证是否存在分享的收藏夹
            p_userListPage.func_click_expandAll();
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> name1 = new ArrayList<String>();
            names.add(ParentFolder);
            name1.add(ChildFolder);
            result &= p_userListPage.func_verify_sharedUserListFolderDoesNotExist(names);
            result &= p_userListPage.func_verify_sharedUserListFolderExists(name1);
            Assert.assertEquals(result, true);
            p_userListPage.func_click_logout();
            //删除该收藏夹
            l.entry();
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(ParentFolder);
            d.navigate().refresh();
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Share child folder(group share)", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-5145 Add one patent which in first page from SRP_Table View
    /*
    * @param loginPage_url
    * @param loginPage_uid
    * @param loginPage_pwd
    * @param ManualAddList
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ManualAddList",
            "Query"
    })
    @Test
    public void userlist_Pat_5145(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ManualAddList,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-5145 Add one patent which in first page from SRP_Table View=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ManualAddList);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //返回字段搜索页并进行简单搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //选择其中的一个专利并添加到收藏夹
            p_searchResultPage.func_click_patentCheckbox_byIndex(5);
            String Exp = p_searchResultPage.func_GetPatentPN_byIndex(5);
            l.info("PN is:{}", Exp);
            p_searchResultPage.func_create_folder_srp(ManualAddList);
            p_searchResultPage.btn_confirm_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_click_userListFolder(ManualAddList);
            Thread.sleep(3000);
            p_userListPage.func_click_patentCheckbox_byIndex(1);
            String Act = p_userListPage.func_GetPatentPN_byIndex(1);
            l.info("PN is:{}", Act);
            result &= p_userListPage.func_verify_PatentsRight(Act, Exp);
            Assert.assertEquals(result, true);
            //删除所新建的收藏夹
            p_userListPage.func_click_userListFolder(ManualAddList);
            p_userListPage.func_delete_userListFolder(ManualAddList);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Add one patent which in first page from SRP_Table View", "jpg");
            Assert.assertEquals(false, true);
        }
    }


    //Pat-5146 Add one patent into parent folder which is not in first page from SRP_Table View
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ManualAddList",
            "Query",
            "PageNum"
    })
    @Test
    public void userlist_Pat_5146(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ManualAddList,
            String Query,
            String PageNum
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-5146 Add one patent which is not in first page from SRP_Table View=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ManualAddList);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //返回字段搜索页并进行简单搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //切换至PageNum页
            p_searchResultPage.func_JumpToPage(PageNum);
            //选择其中的一个专利并添加到收藏夹
            Thread.sleep(3000);
            p_searchResultPage.func_click_patentCheckbox_byIndex(20);
            String Exp = p_searchResultPage.func_GetPatentPN_byIndex(20);
            l.info("PN is:{}", Exp);
            p_searchResultPage.func_create_folder_srp(ManualAddList);
            p_searchResultPage.btn_confirm_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_click_userListFolder(ManualAddList);
            Thread.sleep(3000);
            p_userListPage.func_click_patentCheckbox_byIndex(1);
            String Act = p_userListPage.func_GetPatentPN_byIndex(1);
            l.info("PN is:{}", Act);
            result &= p_userListPage.func_verify_PatentsRight(Act, Exp);
            Assert.assertEquals(result, true);
            //删除所新建的收藏夹
            p_userListPage.func_click_userListFolder(ManualAddList);
            p_userListPage.func_delete_userListFolder(ManualAddList);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Add one patent which is not in first page from SRP_Table View", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-5147 Add continuous patents into parent folder from SRP_Table View
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ManualAddList",
            "Query",
            "pnIndexes1"
    })
    @Test
    public void userlist_Pat_5147(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ManualAddList,
            String Query,
            String pnIndexes1
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-5147 Add continuous patents from SRP_Table View=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ManualAddList);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //返回字段搜索页并进行简单搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //按照索引勾选专利，并保存PN
            ArrayList<String> expPNs = clickAndGetPn(pnIndexes1);
            ArrayList<String> folderNames = new ArrayList<>();
            p_searchResultPage.func_click_addToList_toobar();
            folderNames.add(ManualAddList);
            p_searchResultPage.func_addToList(folderNames, 0, -1, -1);
            p_searchResultPage.func_goto_userListPage();
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //点击收藏夹
            p_userListPage.func_click_userListFolder(ManualAddList);
            p_userListPage.func_waitFor_patentList(10);
            //获取所有PN
            ArrayList<String> actPNs = p_userListPage.func_get_allPN();
            //比较两个List
            result &= compareList(expPNs, actPNs);
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Add continuous patents from SRP_Table View", "jpg");
            Assert.assertEquals(false, true);
        }
    }


    //Pat-5148 Add discontinuous patents into parent folder from SRP_Table View
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ManualAddList",
            "Query",
            "pnIndexes2",
            "PageNum"
    })
    @Test
    public void userlist_Pat_5148(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ManualAddList,
            String Query,
            String pnIndexes2,
            String PageNum
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-5148 Add discontinuous patents from SRP_Table View=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ManualAddList);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //返回字段搜索页并进行简单搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //切换至PageNum页
            p_searchResultPage.func_JumpToPage(PageNum);
            Thread.sleep(3000);
            //按照索引勾选专利，并保存PN
            ArrayList<String> expPNs = clickAndGetPn(pnIndexes2);
            ArrayList<String> folderNames = new ArrayList<>();
            p_searchResultPage.func_click_addToList_toobar();
            folderNames.add(ManualAddList);
            p_searchResultPage.func_addToList(folderNames, 0, -1, -1);
            p_searchResultPage.func_goto_userListPage();
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //点击收藏夹
            p_userListPage.func_click_userListFolder(ManualAddList);
            p_userListPage.func_waitFor_patentList(10);
            //获取所有PN
            ArrayList<String> actPNs = p_userListPage.func_get_allPN();
            //比较两个List
            result &= compareList(expPNs, actPNs);
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Add discontinuous patents from SRP_Table View", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-5149 Add patents into parent folder by click "add to list" for one patent from SRP_Table View
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "ManualAddList",
            "Query"
    })
    @Test
    public void userlist_Pat_5149(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String ManualAddList,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-5149 Add patents into parent folder by click \"add to list\" for one patent from SRP_Table View=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面并检测
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_delete_userListFolder(ManualAddList);
            d.navigate().refresh();
            p_userListPage.btn_logo().click();
            //返回字段搜索页并进行简单搜索
            p_searchPage.func_field_simple(Query);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentCheckbox_byIndex(8);
            String Exp = p_searchResultPage.func_GetPatentPN_byIndex(8);
            l.info("PN is:{}", Exp);
            p_searchResultPage.func_click_addToListLink_ByIndex_tableView(8);
            p_searchResultPage.func_create_folder_srp1(ManualAddList);
            p_searchResultPage.btn_confirm_addToListDialog().click();
            //进入收藏夹页面
            p_searchResultPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_click_userListFolder(ManualAddList);
            Thread.sleep(3000);
            p_userListPage.func_click_patentCheckbox_byIndex(1);
            String Act = p_userListPage.func_GetPatentPN_byIndex(1);
            l.info("PN is:{}", Act);
            result &= p_userListPage.func_verify_PatentsRight(Act, Exp);
            Assert.assertEquals(result, true);
            //删除所新建的收藏夹
            p_userListPage.func_click_userListFolder(ManualAddList);
            p_userListPage.func_delete_userListFolder(ManualAddList);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-5149 Add patents into parent folder by click \"add to list\" for one patent from SRP_Table View", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1168 Auto collect patents with long query_All Results(Global Setting: Show all results)
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "LongQuery",
            "AutoList"
    })
    @Test
    public void userlist_Pat_1168(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String LongQuery,
            String AutoList
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-1168 Auto collect patents with long query_All Results(Global Setting: Show all results)=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(AutoList);
            d.navigate().refresh();
            //新建一收藏夹
            p_userListPage.func_create_folder_input(AutoList);
            p_userListPage.func_click_userListFolder(AutoList);
            //自动收录
            p_userListPage.func_auto_collect(AutoList, LongQuery, 0);
            Thread.sleep(25000);
            p_userListPage.func_click_userListFolder(AutoList);
            Thread.sleep(3000);
            int act_Num, exp_Num;
            //取得自动收录的专利数量
            act_Num = p_userListPage.func_get_NumPatent();
            l.info("act_Num is:{}", act_Num);
            p_userListPage.btn_logo().click();
            //进入字段搜索页
            p_searchPage.func_fieldSearch("ALL_DB", "ALL", LongQuery, null);
            //进入搜索结果页
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //获取专利数量
            exp_Num = p_searchResultPage.func_get_searchResultNumber_bottom();
            l.info("exp_Num is : {}", exp_Num);
            p_searchResultPage.func_goto_userListPage();
            result &= p_userListPage.func_VerifyNumRight(exp_Num, act_Num);
            Assert.assertEquals(result, true);
            //删除所新建的收藏夹
            p_userListPage.func_click_userListFolder(AutoList);
            p_userListPage.func_delete_userListFolder(AutoList);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Auto collect patents with long query_All Results(Global Setting: Show all results)", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1172 Auto collect patents with long query_Updated Results(Global Setting: Show all results)
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "LongQuery",
            "AutoList",
            "Exp_info"
    })
    @Test
    public void userlist_Pat_1172(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String LongQuery,
            String AutoList,
            String Exp_info
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-1172 Auto collect patents with long query_Updated Results(Global Setting: Show All results)=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(AutoList);
            d.navigate().refresh();
            //新建一收藏夹
            p_userListPage.func_create_folder_input(AutoList);
            p_userListPage.func_click_userListFolder(AutoList);
            //自动收录
            p_userListPage.func_auto_collect(AutoList, LongQuery, 1);
            Thread.sleep(10000);
            p_userListPage.func_click_userListFolder(AutoList);
            Thread.sleep(3000);
            //验证
            result &= p_userListPage.func_verify_empty(Exp_info);
            Assert.assertEquals(result, true);
            //删除所新建的收藏夹
            p_userListPage.func_click_userListFolder(AutoList);
            p_userListPage.func_delete_userListFolder(AutoList);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Auto collect patents with long query_Updated Results(Global Setting: Show all results)", "jpg");
            Assert.assertEquals(false, true);
        }
    }


    //Pat-1214 Drag one patent which in first page to parent folder
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "Folder",
            "ParFolder",
            "Query"
    })
    @Test
    public void userlist_Pat_1214(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String Folder,
            String ParFolder,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-1214 Drag one patent which in first page to parent folder=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(Folder);
            Thread.sleep(5000);
            p_userListPage.func_delete_userListFolder(ParFolder);
            d.navigate().refresh();
            //新建2个收藏夹
            p_userListPage.func_create_folder_input(Folder);
            d.navigate().refresh();
            p_userListPage.func_create_folder_input(ParFolder);
            d.navigate().refresh();
            p_userListPage.func_click_userListFolder(Folder);
            Thread.sleep(3000);
            p_userListPage.func_auto_collect(Folder, Query, 0);
            Thread.sleep(20000);
            p_userListPage.func_click_userListFolder(Folder);
            Thread.sleep(3000);
            p_userListPage.func_click_patentCheckbox_byIndex(1);
            String Exp = p_userListPage.func_GetPatentPN_byIndex(1);
            l.info("Pn is :{}", Exp);
            //复制专利到另外一个收藏夹
            p_userListPage.func_copyOrMove_patent(0, Exp, ParFolder);
            //验证
            Thread.sleep(3000);
            result &= p_userListPage.func_verify_patentExistInUserList(Exp);
            Thread.sleep(3000);
            p_userListPage.func_click_userListFolder(ParFolder);
            Thread.sleep(3000);
            result &= p_userListPage.func_verify_patentExistInUserList(Exp);
            Assert.assertEquals(result, true);
            //删除收藏夹
            p_userListPage.func_delete_userListFolder(Folder);
            d.navigate().refresh();
            p_userListPage.func_delete_userListFolder(ParFolder);
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Drag one patent which in first page to parent folder", "jpg");
            Assert.assertEquals(false, true);
        }
    }


    //Pat-1225 Drag one patent which in other page to child folder
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "Folder",
            "ChildFolder",
            "Query"
    })
    @Test
    public void userlist_Pat_1225(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String Folder,
            String ChildFolder,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-1225  Drag one patent which in other  page to child folder=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(Folder);
            d.navigate().refresh();
            //新建收藏夹
            p_userListPage.func_create_folder_input(Folder);
            Thread.sleep(2000);
            p_userListPage.funcCreateChildFolderInput(Folder, ChildFolder);
            Thread.sleep(2000);
            p_userListPage.func_click_userListFolder(Folder);
            p_userListPage.func_auto_collect(Folder, Query, 0);
            Thread.sleep(20000);
            p_userListPage.func_click_userListFolder(Folder);
            Thread.sleep(8000);
            p_userListPage.func_goToPage(3);
            Thread.sleep(8000);
            p_userListPage.func_click_patentCheckbox_byIndex(1);
            String Exp = p_userListPage.func_GetPatentPN_byIndex(1);
            l.info("Pn is :{}", Exp);
            //复制专利到另外一个收藏夹
            p_userListPage.func_copyOrMove_patent(0, Exp, ChildFolder);
            //验证
            result &= p_userListPage.func_verify_patentExistInUserList(Exp);
            Thread.sleep(10000);
            p_userListPage.func_click_userListFolder(ChildFolder);
            Thread.sleep(2000);
            result &= p_userListPage.func_verify_patentExistInUserList(Exp);
            Thread.sleep(3000);
            Assert.assertEquals(result, true);
            //删除收藏夹
            p_userListPage.func_delete_userListFolder(Folder);
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Drag one patent which in other  page to child folder", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1771 Drag one shared patent which in first page to parent folder
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "loginPage_uid1",
            "loginPage_pwd1",
            "ShareFolder",
            "Query",
            "ParFolder"
    })
    @Test
    public void userlist_Pat_1771(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String loginPage_uid1,
            String loginPage_pwd1,
            String ShareFolder,
            String Query,
            String ParFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-1771 Drag one shared patent which in first page to parent folder=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid1, loginPage_pwd1);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(ShareFolder);
            d.navigate().refresh();
            //新建一收藏夹，收录些专利并分享
            p_userListPage.func_create_folder_input(ShareFolder);
            p_userListPage.func_click_userListFolder(ShareFolder);
            p_userListPage.func_auto_collect(ShareFolder, Query, 0);
            Thread.sleep(2000);
            p_userListPage.funcShareList_1To1Share(ShareFolder, loginPage_uid);
            p_userListPage.func_click_logout();
            //另一个用户登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(ParFolder);
            d.navigate().refresh();
            //新建一收藏夹
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_create_folder_input(ParFolder);
            d.navigate().refresh();
            //点击分享的收藏夹
            p_userListPage.func_click_sharedUserListFolder(ShareFolder);
            Thread.sleep(6000);
            p_userListPage.func_click_patentCheckbox_byIndex(1);
            String Exp = p_userListPage.func_GetPatentPN_byIndex(1);
            l.info("Pn is :{}", Exp);
            //拖动专利到自建的收藏夹
            p_userListPage.func_copy_shredPatent(Exp, ParFolder);
            //验证
            result &= p_userListPage.func_verify_patentExistInUserList(Exp);
            Thread.sleep(10000);
            p_userListPage.func_click_userListFolder(ParFolder);
            Thread.sleep(5000);
            result &= p_userListPage.func_verify_patentExistInUserList(Exp);
            Thread.sleep(3000);
            Assert.assertEquals(result, true);
            //删除收藏夹
            p_userListPage.func_delete_userListFolder(ParFolder);
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Drag one shared patent which in first page to parent folder", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1776 Drag one shared patent which in other page to child folder
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "loginPage_uid1",
            "loginPage_pwd1",
            "ShareFolder",
            "Query",
            "ParFolder",
            "ChildFolder"
    })
    @Test
    public void userlist_Pat_1776(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String loginPage_uid1,
            String loginPage_pwd1,
            String ShareFolder,
            String Query,
            String ParFolder,
            String ChildFolder
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-1776 Drag one shared patent which in other page to child folder=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid1, loginPage_pwd1);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(ShareFolder);
            d.navigate().refresh();
            //新建一收藏夹，收录些专利并分享
            p_userListPage.func_create_folder_input(ShareFolder);
            p_userListPage.func_click_userListFolder(ShareFolder);
            p_userListPage.func_auto_collect(ShareFolder, Query, 0);
            Thread.sleep(2000);
            p_userListPage.funcShareList_1To1Share(ShareFolder, loginPage_uid);
            p_userListPage.func_click_logout();
            //另一个用户登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(ParFolder);
            d.navigate().refresh();
            //新建子文件夹
            p_userListPage = new Zhihuiya_userListPage(d);
            p_userListPage.func_create_folder_input(ParFolder);
            p_userListPage.funcCreateChildFolderInput(ParFolder, ChildFolder);
            d.navigate().refresh();
            p_userListPage.func_click_expandAll();
            //点击shared folder
            p_userListPage.func_click_sharedUserListFolder(ShareFolder);
            Thread.sleep(8000);
            p_userListPage.func_goToPage(3);
            Thread.sleep(8000);
            p_userListPage.func_click_patentCheckbox_byIndex(1);
            String Exp = p_userListPage.func_GetPatentPN_byIndex(1);
            l.info("Pn is :{}", Exp);
            //复制专利到另外一个收藏夹
            p_userListPage.func_copy_shredPatent(Exp, ChildFolder);
            //验证
            result &= p_userListPage.func_verify_patentExistInUserList(Exp);
            p_userListPage.func_click_userListFolder(ChildFolder);
            Thread.sleep(5000);
            result &= p_userListPage.func_verify_patentExistInUserList(Exp);
            Thread.sleep(3000);
            Assert.assertEquals(result, true);
            //删除收藏夹
            p_userListPage.func_delete_userListFolder(ParFolder);
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Drag one shared patent which in other page to child folder", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //Pat-1230 Drag(move) one patent which is in first page to parent folder
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "Folder",
            "ParFolder",
            "Query"
    })
    @Test
    public void userlist_Pat_1230(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String Folder,
            String ParFolder,
            String Query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            l.info("");
            l.info("");
            l.info("");
            l.info("=====================Pat-1230 Drag(move) one patent which is in first page to parent folder=========");
            //登陆
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_switch_language(2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //进入字段搜索页面
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入收藏夹页面
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(Folder);
            Thread.sleep(5000);
            p_userListPage.func_delete_userListFolder(ParFolder);
            d.navigate().refresh();
            //新建2个收藏夹
            p_userListPage.func_create_folder_input(Folder);
            d.navigate().refresh();
            p_userListPage.func_create_folder_input(ParFolder);
            d.navigate().refresh();
            p_userListPage.func_click_userListFolder(Folder);
            p_userListPage.func_auto_collect(Folder, Query, 0);
            Thread.sleep(20000);
            p_userListPage.func_click_userListFolder(Folder);
            Thread.sleep(6000);
            p_userListPage.func_click_patentCheckbox_byIndex(1);
            String Exp = p_userListPage.func_GetPatentPN_byIndex(1);
            l.info("Pn is :{}", Exp);
            //复制专利到另外一个收藏夹
            p_userListPage.func_copyOrMove_patent(1, Exp, ParFolder);
            d.navigate().refresh();
            p_userListPage.func_click_userListFolder(Folder);
            Thread.sleep(10000);
            //验证
            result &= p_userListPage.func_verify_patentDoesNotExistInUserList(Exp);
            p_userListPage.func_click_userListFolder(ParFolder);
            Thread.sleep(7000);
            result &= p_userListPage.func_verify_patentExistInUserList(Exp);
            Assert.assertEquals(result, true);
            //删除收藏夹
            p_userListPage.func_delete_userListFolder(Folder);
            d.navigate().refresh();
            p_userListPage.func_delete_userListFolder(ParFolder);
            p_userListPage.func_click_logout();
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Drag(move) one patent which is in first page to parent folder", "jpg");
            Assert.assertEquals(false, true);
        }
    }




     //根据输入的PN索引，勾选PN并返回PN集合
    public ArrayList<String> clickAndGetPn(String pnIndexes) {
        ArrayList<String> pns = new ArrayList<>();
        //处理字符串
        ArrayList<Integer> indexes = new ArrayList<>();
        for (String index : pnIndexes.split(",")) {
            indexes.add(Integer.valueOf(index.trim()));
        }
        //勾选并记录专利
        for (int index : indexes) {
            p_searchResultPage.func_click_patentCheckbox_byIndex(index);
            pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(index));
        }
        return pns;
    }

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












