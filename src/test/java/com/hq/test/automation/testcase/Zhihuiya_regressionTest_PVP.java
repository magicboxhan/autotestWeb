package com.hq.test.automation.testcase;

import com.hq.test.automation.pageobject.Zhihuiya_loginPage;
import com.hq.test.automation.pageobject.Zhihuiya_patentViewPage;
import com.hq.test.automation.pageobject.Zhihuiya_searchPage;
import com.hq.test.automation.pageobject.Zhihuiya_searchResultPage;
import com.hq.test.framework.testcase.BaseTestcase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by zjd on 2015/1/14.
 */

public class Zhihuiya_regressionTest_PVP extends BaseTestcase {


    Zhihuiya_loginPage p_loginPage;
    Zhihuiya_searchPage p_searchPage;
    Zhihuiya_searchResultPage p_searchResultPage;
    Zhihuiya_patentViewPage p_patentViewPage;

    String loginPage_url;
    String loginPage_uid;
    String loginPage_pwd;

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @BeforeClass
    public void init(
            String p_loginPage_url,
            String p_loginPage_uid,
            String p_loginPage_pwd
    ) {
        try {
            l.entry();
            //初始化变量
            loginPage_url = p_loginPage_url;
            loginPage_uid = p_loginPage_uid;
            loginPage_pwd = p_loginPage_pwd;
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "error", "jpg");
            Assert.assertEquals(false, true);
        }

    }

    /**
     * 公共方法 -- 登录+切换语言*
     */
    public void login() {
        try {
            l.entry();
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //切换语言
//            p_searchPage.func_switch_language(language);
//            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-406", "jpg");
            Assert.assertEquals(false, true);
        }

    }


    //----------------------Pat-129/Pat-52/Pat-3138/Pat-3139--------------------//

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void patentView_UI(
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
            l.info("============================== Pat-129/Pat-52/Pat-3138/Pat-3139 ==============================");
            //登录(调用公共方法)
            init(loginPage_url, loginPage_uid, loginPage_pwd);
            login();
//            d.get(loginPage_url);
//            p_loginPage = new Zhihuiya_loginPage(d);
//            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
//            p_searchPage = new Zhihuiya_searchPage(d);
//            Assert.assertEquals(p_searchPage.selfcheck(), true);
//            输入关键字查询
            p_searchPage.func_searchForQuery("apple");
//            输入关键字查询
//            d.findElement(By.id("q")).sendKeys("apple");
//            d.findElement(By.className("btn-search")).click();
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查帮助信息是否显示
            boolean newUserGuideExist = p_patentViewPage.func_verifyExist_newUserGuide();
            if (newUserGuideExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //检查返回按钮是否显示
            boolean returnBackExist = p_patentViewPage.func_verifyExist_returnBack();
            if (returnBackExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查专利标题栏是否显示
            boolean patentTitleExist = p_patentViewPage.func_verifyExist_patentTitle();
            if (patentTitleExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }

            //检查overview tab标签
            boolean overviewExist = p_patentViewPage.func_verifyExist_overview();
            if (overviewExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查image
            boolean imageExist = p_patentViewPage.func_verifyExist_image();
            if (imageExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查abst
            boolean abstractExist = p_patentViewPage.func_verifyExist_abst();
            if (abstractExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查claims
            boolean claimsExist = p_patentViewPage.func_verifyExist_claims();
            if (claimsExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查description
            boolean descriptionExist = p_patentViewPage.func_verifyExist_desc();
            if (descriptionExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查cites
            boolean citesExist = p_patentViewPage.func_verifyExist_cites();
            if (citesExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查cited
            boolean citedExist = p_patentViewPage.func_verifyExist_cited();
            if (citedExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查otherLanguage
            boolean otherLanguageExist = p_patentViewPage.func_verifyExist_otherLanguage();
            if (otherLanguageExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }

            //检查dualview标签
            boolean dualviewExist = p_patentViewPage.func_verifyExist_dualview();
            if (dualviewExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }

            //检查citation analysis标签
            boolean citationAnalysisExist = p_patentViewPage.func_verifyExist_citationAnalysis();
            if (citationAnalysisExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }

            //检查patent family标签
            boolean patentFamilyExist = p_patentViewPage.func_verifyExist_patentFamily();
            if (patentFamilyExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }

            //检查legal status标签
            boolean legalStatusExist = p_patentViewPage.func_verifyExist_legalStatus();
            if (legalStatusExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }

            //检查收藏夹是否存在
            boolean addToListExist = p_patentViewPage.func_verifyExist_addToList();
            if (addToListExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //PDF下载
            boolean PDFDownloadExist = p_patentViewPage.func_verifyExist_PDFDownload();
            if (PDFDownloadExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //邮寄按钮
            boolean emailExist = p_patentViewPage.func_verifyExist_email();
            if (emailExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }

            //点击返回按钮
            p_patentViewPage.func_click_returnBack();
            boolean returnButton = p_searchResultPage.selfcheck();
            l.info("selfCheckResult:{}", returnButton);
            Assert.assertEquals(returnButton, true);

            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-129/Pat-52/Pat-3138/Pat-3139", "jpg");
            Assert.assertEquals(false, true);
        }
    }
    //----------------------Pat-52-other language--------------------

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "query",
    })
    @Test
    public void patentView_UI_otherLanguage(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String query
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-52-other language ==============================");
            //登录(调用公共方法)
            init(loginPage_url, loginPage_uid, loginPage_pwd);
            login();
            //输入query搜索
            p_searchPage.func_searchForQuery(query);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //找到PN进入专利详情页
            p_searchResultPage.func_click_patentLink_byPN("CN102760467A");
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查帮助信息是否显示
            boolean newUserGuideExist = p_patentViewPage.func_verifyExist_newUserGuide();
            if (newUserGuideExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //检查otherLanguage
            boolean otherLanguageExist = p_patentViewPage.func_verifyExist_otherLanguage();
            if (otherLanguageExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }

            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-52-other language", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat - 135-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void overView_image(
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
            l.info("============================== Pat-135 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_byPN("DE102010027586A1");
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查帮助信息是否显示
            boolean newUserGuideExist = p_patentViewPage.func_verifyExist_newUserGuide();
            if (newUserGuideExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击image
            p_patentViewPage.func_click_image();
            //检查图片是否显示
            boolean verifyExist_zoomImage = p_patentViewPage.func_verifyExist_zoomImage();
            if (verifyExist_zoomImage) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //获取图片长度、宽度
            HashMap<String, Double> values_normal = p_patentViewPage.func_get_imgWidthHeight();
            double widthBefore = values_normal.get("width");
            double heightBefore = values_normal.get("height");
            l.info("Width: {}", values_normal.get("width"));
            l.info("Height: {}", values_normal.get("height"));
            //点击按钮放大
            p_patentViewPage.func_click_zoomIn();
            //获取图片长度、宽度
            HashMap<String, Double> values_maximum = p_patentViewPage.func_get_imgWidthHeight();
            double widthAfter = values_maximum.get("width");
            double heightAfter = values_maximum.get("height");
            l.info("Width: {}", values_maximum.get("width"));
            l.info("Height: {}", values_maximum.get("height"));
//            //再次点击按钮缩小
//            p_patentViewPage.func_click_zoomOut();
//            //获取图片长度、宽度
//            HashMap<String, Double> values_minimum = p_patentViewPage.func_get_imgWidthHeight();
//            l.info("Width: {}", values_minimum.get("width"));
//            l.info("Height: {}", values_minimum.get("height"));
            //验证图片是否放大
            if ((widthAfter > widthBefore) && (heightAfter > heightBefore)) {
                l.info("pass");
            } else {
                l.info("fail");
            }

            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-135", "jpg");
            Assert.assertEquals(false, true);
        }
    }

//    //-------------------------------pat - 3152-----------------------------
//    @Parameters({
//            "loginPage_url",
//            "loginPage_uid",
//            "loginPage_pwd",
//            "query",
//    })
//    @Test
//    public void patentView_imageEmpty(
//            String loginPage_url,
//            String loginPage_uid,
//            String loginPage_pwd,
//            String query
//    ) throws IOException {
//        try {
//            l.entry();
//            //测试结果
//            boolean result = true;
//            //处理入参
//            l.info("");
//            l.info("");
//            l.info("");
//            l.info("============================== Pat-3152 ==============================");
//            //登录(调用公共方法)
//            login();
//            //输入关键字查询
//            p_searchPage.func_searchForQuery(query);
//            p_searchResultPage = new Zhihuiya_searchResultPage(d);
//            Thread.sleep(5000);
//            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
//            p_searchResultPage.func_click_patentLink_byPN("DE78C");
//            p_patentViewPage = new Zhihuiya_patentViewPage(d);
//            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
//            //检查帮助信息是否显示
//            boolean newUserGuideExist = p_patentViewPage.func_verifyExist_newUserGuide();
//            if (newUserGuideExist) {
//                l.info("passed");
//            } else {
//                l.info("failed");
//                result &= false;
//            }
//            //点击退出按钮
//            p_patentViewPage.func_exit_tip();
//            //点击image
//            p_patentViewPage.func_click_image();
//            //检查图片是否显示
//            boolean verifyExist_zoomImage = p_patentViewPage.func_verifyExist_zoomImage();
//            if (verifyExist_zoomImage) {
//                l.info("passed");
//            } else {
//                l.info("failed");
//                result &= false;
//            }
//            //获取图片长度、宽度
//            HashMap<String, Double> values_normal = p_patentViewPage.func_get_imgWidthHeight();
//            double widthBefore = values_normal.get("width");
//            double heightBefore = values_normal.get("height");
//            l.info("Width: {}", values_normal.get("width"));
//            l.info("Height: {}", values_normal.get("height"));
//            //点击按钮放大
//            p_patentViewPage.func_click_zoomIn();
//            //获取图片长度、宽度
//            HashMap<String, Double> values_maximum = p_patentViewPage.func_get_imgWidthHeight();
//            double widthAfter = values_maximum.get("width");
//            double heightAfter = values_maximum.get("height");
//            l.info("Width: {}", values_maximum.get("width"));
//            l.info("Height: {}", values_maximum.get("height"));
////            //再次点击按钮缩小
////            p_patentViewPage.func_click_zoomOut();
////            //获取图片长度、宽度
////            HashMap<String, Double> values_minimum = p_patentViewPage.func_get_imgWidthHeight();
////            l.info("Width: {}", values_minimum.get("width"));
////            l.info("Height: {}", values_minimum.get("height"));
//            if ((widthAfter > widthBefore) && (heightAfter > heightBefore)){
//                l.info("pass");
//            }else {
//                l.info("fail");
//            }
//
//            //断言
//            Assert.assertEquals(result, true);
//            l.exit();
//        } catch (Exception e) {
//            l.error("Error!");
//            e.printStackTrace();
//            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-135", "jpg");
//            Assert.assertEquals(false, true);
//        }
//    }

//    //-------------------------------pat - 286/-----------------------------
//    @Parameters({
//            "loginPage_url",
//            "loginPage_uid",
//            "loginPage_pwd",
//    })
//    @Test
//    public void patentView_fixFields(
//            String loginPage_url,
//            String loginPage_uid,
//            String loginPage_pwd
//    ) throws IOException {
//        try {
//            l.entry();
//            //测试结果
//            boolean result = true;
//            //处理入参
//            l.info("");
//            l.info("");
//            l.info("");
//            l.info("============================== Pat-286/ ==============================");
//            //登录(调用公共方法)
//            login();
//            //输入关键字查询
//            p_searchPage.func_searchForQuery("apple");
//            p_searchResultPage = new Zhihuiya_searchResultPage(d);
//            Thread.sleep(5000);
//            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
//            p_searchResultPage.func_click_patentLink_ByIndex(1);
//            p_patentViewPage = new Zhihuiya_patentViewPage(d);
//            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
//            //检查帮助信息是否显示
//            boolean newUserGuideExist = p_patentViewPage.func_verifyExist_newUserGuide();
//            if (newUserGuideExist) {
//                l.info("passed");
//            } else {
//                l.info("failed");
//                result &= false;
//            }
//            //点击退出按钮
//            p_patentViewPage.func_exit_tip();
//
//
//
//
//            //断言
//            Assert.assertEquals(result, true);
//            l.exit();
//        } catch (Exception e) {
//            l.error("Error!");
//            e.printStackTrace();
//            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-135", "jpg");
//            Assert.assertEquals(false, true);
//        }
//    }

    //-------------------------------pat - 289-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void overtView_claims(
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
            l.info("============================== Pat-289 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查帮助信息是否显示
            boolean newUserGuideExist = p_patentViewPage.func_verifyExist_newUserGuide();
            if (newUserGuideExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击权利要求
            p_patentViewPage.func_click_clms();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查权利要求页面是否显示
            boolean claimsPageExist = p_patentViewPage.func_verifyExist_claimsPage();
            if (claimsPageExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }


            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-289", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat - 59-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void overView_description(
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
            l.info("============================== Pat-59 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查帮助信息是否显示
            boolean newUserGuideExist = p_patentViewPage.func_verifyExist_newUserGuide();
            if (newUserGuideExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击说明书
            p_patentViewPage.func_click_desc();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查说明书页面是否显示
            boolean descriptionPageExist = p_patentViewPage.func_verifyExist_descPage();
            if (descriptionPageExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }

            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-59", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat - 3263/137-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void overView_cites(
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
            l.info("============================== Pat-3263/137 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击引用按钮
            p_patentViewPage.func_click_cites();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查引用列表是否显示
            boolean citesListExist = p_patentViewPage.func_verifyExist_citesList();
            if (citesListExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查其他引用文献列表是否显示
            boolean otherReferencesExist = p_patentViewPage.func_verifyExist_otherReferences();
            if (otherReferencesExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }

            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-3263/137", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat - 160-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void overView_cited(
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
            l.info("============================== Pat-160 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击被引用按钮
            p_patentViewPage.func_click_cited();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查被引用列表是否显示
            boolean citedListExist = p_patentViewPage.func_verifyExist_citedList();
            if (citedListExist) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }

            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-160", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat - 55/3316-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void dualView_UI(
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
            l.info("============================== Pat-55/3316 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击dual view按钮
            p_patentViewPage.func_click_dualView();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查左边文本是否显示
            boolean dualView_left = p_patentViewPage.func_verifyExist_dualview_left();
            if (dualView_left) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            boolean dualViewPatentTitle = p_patentViewPage.func_verifyExist_patentTitle();
            if (dualViewPatentTitle) {
                l.info("passed");
            } else {
                l.info("failed-dualView patent title can not exist");
                result &= false;
            }
            boolean dualViewPatentContent = p_patentViewPage.func_verifyExist_dualviewPatentContent();
            if (dualViewPatentContent) {
                l.info("passed");
            } else {
                l.info("failed-dualView content not exist");
                result &= false;
            }
            //检查右边PDF是否显示
            boolean dualView_right = p_patentViewPage.func_verifyExist_dualview_right();
            if (dualView_right) {
                l.info("passed");
            } else {
                l.info("failed-dualView PDF not exist");
                result &= false;
            }
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-55/3316", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat - 56-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void citationAnalysis_UI(
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
            l.info("============================== Pat-56 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击citation analysis tab按钮
            p_patentViewPage.func_click_citationAnalysis();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查citation analysis page是否显示
            boolean citationAnalysis = p_patentViewPage.func_verifyExist_citationAnalysis();
            if (citationAnalysis) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-56", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat-3355-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void patentFamily_distributionMap(
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
            l.info("============================== Pat-3355 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击patent family tab按钮
            p_patentViewPage.func_click_patentFamily();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查distribute map是否显示
            boolean patentFamilyDistributeMap = p_patentViewPage.func_verifyExist_patentFamilyDistributionMap();
            if (patentFamilyDistributeMap) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图WIPO是否显示
            boolean distributionMapWIPO = p_patentViewPage.func_verifyExist_distributionMapWIPO();
            if (distributionMapWIPO) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图EPO是否显示
            boolean distributionMapEPO = p_patentViewPage.func_verifyExist_distributionMapEPO();
            if (distributionMapEPO) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图total是否显示
            boolean distributionMapTotal = p_patentViewPage.func_verifyExist_distributionMapTotal();
            if (distributionMapTotal) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图Legend是否显示
            boolean distributionMapLegend = p_patentViewPage.func_verifyExist_distributionMapLegend();
            if (distributionMapLegend) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }

            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-3355", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat-3361-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void patentFamily_PatentList(
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
            l.info("============================== Pat-3361 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击patent family tab按钮
            p_patentViewPage.func_click_patentFamily();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查分布地图同族专利序列号是否显示
            boolean distributionMapSeq = p_patentViewPage.func_verifyExist_patentFamilySeq();
            if (distributionMapSeq) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图同族专利PN是否显示
            boolean distributionMapPN = p_patentViewPage.func_verifyExist_link_patentFamilyPN();
            if (distributionMapPN) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图同族专利标题是否显示
            boolean distributionMapTitle = p_patentViewPage.func_verifyExist_patentFamilyTitle();
            if (distributionMapTitle) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图同族专利PDF下载按钮是否显示
            boolean distributionMapPDF = p_patentViewPage.func_verifyExist_patentFamilyPDF();
            if (distributionMapPDF) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //点击专利PN
            p_patentViewPage.func_click_link_patentFamilyPN();
            switchToNewWindow();
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Thread.sleep(5000);


            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-3361", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat-3408-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void patentFamily_PriorityData(
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
            l.info("============================== Pat-3408 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击patent family tab按钮
            p_patentViewPage.func_click_patentFamily();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查分布地图同族专利Priority Data按钮是否显示
            boolean distributionMapPriority = p_patentViewPage.func_verifyExist_patentFamilyPriority();
            if (distributionMapPriority) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图同族专利Priority Data数据是否显示
            boolean distributionMapPriorityData = p_patentViewPage.func_verifyExist_patentFamilyPriorityData();
            if (distributionMapPriorityData) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-3048", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat-3412-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void patentFamily_AssigneeName(
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
            l.info("============================== Pat-3412 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击patent family tab按钮
            p_patentViewPage.func_click_patentFamily();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查分布地图同族专利Assignee Name按钮是否显示
            boolean distributionMapAssigneeName = p_patentViewPage.func_verifyExist_patentFamilyAssigneeName();
            if (distributionMapAssigneeName) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图同族专利Assignee Name数据是否显示
            boolean distributionMapAssigneeNameLink = p_patentViewPage.func_verifyExist_patentFamilyAssigneeNameLink();
            if (distributionMapAssigneeNameLink) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //点击assignee name按钮
            p_patentViewPage.func_click_patentFamilyAssigneeName();
            //点击申请人姓名,进入搜索结果页
            p_patentViewPage.func_click_patentFamilyAssigneeNameLink();
            switchToNewWindow();
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);

            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-3412", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat-4454-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void patentFamily_InventorName(
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
            l.info("============================== Pat-4454 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击patent family tab按钮
            p_patentViewPage.func_click_patentFamily();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //检查分布地图同族专利Inventor Name按钮是否显示
            boolean distributionMapInventorName = p_patentViewPage.func_verifyExist_patentFamilyInventorName();
            if (distributionMapInventorName) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图同族专利Inventor Name数据是否显示
            boolean distributionMapInventorNameLink = p_patentViewPage.func_verifyExist_patentFamilyInventorNameLink();
            if (distributionMapInventorNameLink) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //点击inventor name按钮
            p_patentViewPage.func_click_patentFamilyInventorName();
            //点击inventor name，进入搜索结果页
            p_patentViewPage.func_click_patentFamilyInventorNameLink();
            switchToNewWindow();
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);

            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-4454", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat-3424-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void patentFamily_IPC(
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
            l.info("============================== Pat-3424 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击patent family tab按钮
            p_patentViewPage.func_click_patentFamily();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击IPC按钮
            p_patentViewPage.func_click_patentFamilyIPC();
            //检查分布地图同族专利IPC是否显示
            boolean distributionMapIPC = p_patentViewPage.func_verifyExist_patentFamilyIPC();
            if (distributionMapIPC) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图同族专利IPC数据是否显示
            boolean distributionMapIPCLink = p_patentViewPage.func_verifyExist_patentFamilyIPCLink();
            if (distributionMapIPCLink) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
//            //点击IPC按钮
//            p_patentViewPage.func_click_patentFamilyIPC();
//            //点击inventor name，进入搜索结果页
//            p_patentViewPage.func_click_patentFamilyIPCLink();
//            switchToNewWindow();
//            p_searchResultPage = new Zhihuiya_searchResultPage(d);
//            Thread.sleep(5000);
//            Assert.assertEquals(p_searchResultPage.selfcheck(), true);

            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-3424", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat-3429-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void patentFamily_abstract(
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
            l.info("============================== Pat-3429 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_ByIndex(1);
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击patent family tab按钮
            p_patentViewPage.func_click_patentFamily();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击Abstract按钮
            p_patentViewPage.func_click_patentFamilyAbstract();
            //检查分布地图同族专利Abstract是否显示
            boolean distributionMapAbstract = p_patentViewPage.func_verifyExist_patentFamilyAbstract();
            if (distributionMapAbstract) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图同族专利Abstract Content是否显示
            boolean distributionMapAbstractContent = p_patentViewPage.func_verifyExist_patentFamilyAbstractContent();
            if (distributionMapAbstractContent) {
                l.info("passed");
            } else {
                l.info("failed--can not find patent_family abstract content");
                result &= false;
            }
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-3429", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------pat-3433-----------------------------
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void patentFamily_LegalStatus(
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
            l.info("============================== Pat-3433 ==============================");
            //登录(调用公共方法)
            login();
            //输入关键字查询
            p_searchPage.func_searchForQuery("apple");
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_patentLink_byPN("CN102760467A");
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击退出按钮
            p_patentViewPage.func_exit_tip();
            //点击patent family tab按钮
            p_patentViewPage.func_click_patentFamily();
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //点击Legal Status按钮
            p_patentViewPage.func_click_patentFamilyLegalStatus();
            //检查分布地图同族专利Legal Status是否显示
            boolean distributionMapLegalStatus = p_patentViewPage.func_verifyExist_patentFamilyLegalStatus();
            if (distributionMapLegalStatus) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //检查分布地图同族专利Legal Status Content是否显示
            boolean distributionMapLegalStatusContent = p_patentViewPage.func_verifyExist_patentFamilyLegalStatusContent();
            if (distributionMapLegalStatusContent) {
                l.info("passed");
            } else {
                l.info("failed");
                result &= false;
            }
            //断言
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat-3433", "jpg");
            Assert.assertEquals(false, true);
        }
    }

}