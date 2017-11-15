package com.hq.test.automation.testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.hq.test.automation.pageobject.*;
import com.hq.test.framework.page.BasePage;
import com.hq.test.framework.testcase.BaseTestcase;
import com.hq.test.framework.util.CsvUtil;
import com.hq.test.framework.util.XMLUtil;
import com.hq.test.framework.util.ExcelUtil;
import com.hq.test.framework.util.RTFUtil;

import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Zhihuiya_regressionTest_export extends BaseTestcase {

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
    
    int language;

    @Parameters({
            "language",
    })
    @BeforeClass
    public void init(int p_language){
        language = p_language;
    }

    //========================================================= export(new) ==========================================================

    //Pat-336:Exp-Selected-Excel(SRP)

    /**
     * 导出excel，来源为搜索结果页，选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "exportNum",
            "download_timeout",
            "compression",
            "includeThumbnail",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "templateFields"
    })
    @Test
    public void export_pat_336(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int exportNum,
            int download_timeout,
            int compression,
            int includeThumbnail,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-336:Exp-Selected-Excel(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //选择专利
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = 0; i < exportNum; i++) {
                p_searchResultPage.func_click_patentCheckbox_byIndex(i + 1);
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (exportType == 0) {
                    pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i + 1));
                }
            }
            //点击导出
            p_searchResultPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 6) {
                //6为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(0, fields, download_path, includeThumbnail, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "xls", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            ExcelUtil excel = null;
            if (compression == 0) {
                //非压缩文件
                excel = new ExcelUtil(download_path, fullFileName, false);
            } else if (compression == 1) {
                //压缩文件
                excel = new ExcelUtil(download_path, fullFileName, true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) excel.readExcelData();
            //验证数量
            l.info("============================== Verify number of patents in excel ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证图片
            l.info("============================== Verify image in excel ==============================");
            if (excel.getExcelImageNumber() > 0) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Image exists in excel");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Image does not exist in excel");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_excelData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-362:Exp-Order-Excel(SRP)

    /**
     * 导出excel，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "includeThumbnail",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "templateFields"
    })
    @Test
    public void export_pat_362(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int includeThumbnail,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-362:Exp-Order-Excel(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            int exportNum = endNum - startNum + 1;    //导出数量
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //选择专利
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (exportType == 0) {
                    pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
                }
            }
            //点击导出
            p_searchResultPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 6) {
                //6为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(0, fields, download_path, includeThumbnail, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "xls", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            ExcelUtil excel = null;
            if (compression == 0) {
                //非压缩文件
                excel = new ExcelUtil(download_path, fullFileName, false);
            } else if (compression == 1) {
                //压缩文件
                excel = new ExcelUtil(download_path, fullFileName, true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) excel.readExcelData();
            //验证数量
            l.info("============================== Verify number of patents in excel ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证图片
            l.info("============================== Verify image in excel ==============================");
            if (excel.getExcelImageNumber() > 0) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Image exists in excel");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Image does not exist in excel");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_excelData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-368:Exp-Selected-Excel(UL)

    /**
     * 导出excel，来源为收藏夹页，选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "exportNum",
            "download_timeout",
            "compression",
            "includeThumbnail",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "templateFields"
    })
    @Test
    public void export_pat_368(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int exportNum,
            int download_timeout,
            int compression,
            int includeThumbnail,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-368:Exp-Selected-Excel(UL) ==============================");
            l.info("Preconditions -- Login and Create one folder with some patents, enter into this folder");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            String folderName = "ExportTest";    //收藏夹文件夹名称
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            //删除收藏夹文件夹
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderName);
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //添加到收藏夹
            ArrayList<String> folderNames = new ArrayList<String>();
            p_searchResultPage.func_click_addToList_toobar();
            Thread.sleep(2000);
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //进入收藏夹页
            p_searchResultPage.func_goto_userListPage();
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //选择新建的文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            //选择专利
            for (int i = 0; i < exportNum; i++) {
                p_userListPage.func_click_patentCheckbox_byIndex(i + 1);
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (exportType == 0) {
                    pns.add(p_userListPage.func_get_patentNumber_ByIndex(i + 1));
                }
            }
            //点击导出
            p_userListPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
//			if(fields == 6){
//				//6为自定义模板
//				//删除原有同名模板
//				p_exportPage.func_delete_template(templateName);
//				Thread.sleep(2000);
//				//创建模板
//				p_exportPage.func_create_template(templateFields, templateName);
//			}
            //导出
            String partialFileName = p_exportPage.func_export(0, fields, download_path, includeThumbnail, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "xls", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            ExcelUtil excel = null;
            if (compression == 0) {
                //非压缩文件
                excel = new ExcelUtil(download_path, fullFileName, false);
            } else if (compression == 1) {
                //压缩文件
                excel = new ExcelUtil(download_path, fullFileName, true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) excel.readExcelData();
            //验证数量
            l.info("============================== Verify number of patents in excel ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证图片
            l.info("============================== Verify image in excel ==============================");
            if (excel.getExcelImageNumber() > 0) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Image exists in excel");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Image does not exist in excel");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_excelData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-372:Exp-Order-Excel(UL)

    /**
     * 导出excel，来源为收藏夹页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "includeThumbnail",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "exportNum",
            "templateFields"
    })
    @Test
    public void export_pat_372(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int includeThumbnail,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            int exportNum,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-372:Exp-Order-Excel(UL) ==============================");
            l.info("Preconditions -- Login and Create one folder with some patents, enter into this folder");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            String folderName = "ExportTest";    //收藏夹文件夹名称
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            //删除收藏夹文件夹
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderName);
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //添加到收藏夹
            ArrayList<String> folderNames = new ArrayList<String>();
            p_searchResultPage.func_click_addToList_toobar();
            Thread.sleep(2000);
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //进入收藏夹页
            p_searchResultPage.func_goto_userListPage();
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //选择新建的文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            //选择专利
            for (int i = 0; i < exportNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (exportType == 0) {
                    pns.add(p_userListPage.func_get_patentNumber_ByIndex(i + 1));
                }
            }
            //点击导出
            p_userListPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
//			if(fields == 6){
//				//6为自定义模板
//				//删除原有同名模板
//				p_exportPage.func_delete_template(templateName);
//				Thread.sleep(2000);
//				//创建模板
//				p_exportPage.func_create_template(templateFields, templateName);
//			}
            //导出
            String partialFileName = p_exportPage.func_export(0, fields, download_path, includeThumbnail, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "xls", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            ExcelUtil excel = null;
            if (compression == 0) {
                //非压缩文件
                excel = new ExcelUtil(download_path, fullFileName, false);
            } else if (compression == 1) {
                //压缩文件
                excel = new ExcelUtil(download_path, fullFileName, true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) excel.readExcelData();
            //验证数量
            l.info("============================== Verify number of patents in excel ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证图片
            l.info("============================== Verify image in excel ==============================");
            if (excel.getExcelImageNumber() > 0) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Image exists in excel");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Image does not exist in excel");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_excelData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-377:Exp-Order-Excel(SS)

    /**
     * 导出excel，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "includeThumbnail",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "templateFields",
            "queryName"
    })
    @Test
    public void export_pat_377(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int includeThumbnail,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            String templateFields,
            String queryName
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-377:Exp-Order-Excel(SS) ==============================");
            l.info("Preconditions -- Login and Add one query to Save Search, currently on Saved Search page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            int exportNum = endNum - startNum + 1;    //导出数量
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            //删除现有搜索
            p_searchPage.func_goto_savedSearchPage();
            p_savedSearchPage = new Zhihuiya_savedSearchPage(d);
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            p_savedSearchPage.func_delete_savedSearch(queryName);
            //创建新搜索
            p_savedSearchPage.func_searchForQuery(searchPage_keyword);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (exportType == 0) {
                    pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
                }
            }
            //保存语句
            p_searchResultPage.func_click_saveQuery();
            HashMap<String, String> savedSearchResult = p_searchResultPage.func_save_query(queryName);
            String fullQueryName = savedSearchResult.get("name");
            //进入已保存搜索页
            Thread.sleep(5000);
            p_searchResultPage.func_goto_savedSearchPage();
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            //点击导出
            p_savedSearchPage.func_export_savedSearch(fullQueryName);
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 6) {
                //6为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(0, fields, download_path, includeThumbnail, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "xls", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            ExcelUtil excel = null;
            if (compression == 0) {
                //非压缩文件
                excel = new ExcelUtil(download_path, fullFileName, false);
            } else if (compression == 1) {
                //压缩文件
                excel = new ExcelUtil(download_path, fullFileName, true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) excel.readExcelData();
            //验证数量
            l.info("============================== Verify number of patents in excel ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证图片
            l.info("============================== Verify image in excel ==============================");
            if (excel.getExcelImageNumber() > 0) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Image exists in excel");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Image does not exist in excel");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_excelData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-381:Exp-Order-Excel(BS)

    /**
     * 导出excel，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "download_path",
            "download_timeout",
            "compression",
            "includeThumbnail",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "templateFields",
            "searchPage_bulkSearch_keyword",
            "exportNum"
    })
    @Test
    public void export_pat_381(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String download_path,
            int download_timeout,
            int compression,
            int includeThumbnail,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            String templateFields,
            String searchPage_bulkSearch_keyword,
            int exportNum
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-381:Exp-Order-Excel(BS) ==============================");
            l.info("Preconditions -- Login and Input some patent numbers in inputbox on Bulk Search page, then click Add button,Then Display number of added successfully");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            for (String pn : searchPage_bulkSearch_keyword.split(" ")) {
                pns.add(pn.trim());
            }
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_bulkSearch_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_bulkSearch_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            //点击批量搜索标签
            p_searchPage.func_click_bulkSearch();
            //批量添加专利
            p_searchPage.func_bulkSearch(searchPage_bulkSearch_keyword);
            //点击导出
            p_searchPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 6) {
                //6为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(0, fields, download_path, includeThumbnail, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "xls", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            ExcelUtil excel = null;
            if (compression == 0) {
                //非压缩文件
                excel = new ExcelUtil(download_path, fullFileName, false);
            } else if (compression == 1) {
                //压缩文件
                excel = new ExcelUtil(download_path, fullFileName, true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) excel.readExcelData();
            //验证数量
            l.info("============================== Verify number of patents in excel ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证图片
            l.info("============================== Verify image in excel ==============================");
            if (excel.getExcelImageNumber() > 0) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Image exists in excel");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Image does not exist in excel");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_excelData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-385:Exp-Order-Excel(CD)

    /**
     * 导出excel，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "includeThumbnail",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "templateFields",
            "cdb_name",
            "cdb_queryTitle_root"
    })
    @Test
    public void export_pat_385(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int includeThumbnail,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            String templateFields,
            String cdb_name,
            String cdb_queryTitle_root
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-385:Exp-Order-Excel(CD) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            int exportNum = endNum - startNum + 1;    //导出数量
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            p_searchPage.func_goto_customDatabasePage();
            //删除自建库
            p_customDatabasePage = new Zhihuiya_customDatabasePage(d);
            p_customDatabasePage.func_delete_CDB_byName(cdb_name, loginPage_pwd);
            //搜索
            d.get(loginPage_url);
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //选择专利
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (exportType == 0) {
                    pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
                }
            }
            //添加到自建库，需新建自建库，并将搜索加入到根目录
            p_searchResultPage.func_click_addToCDB();
            Thread.sleep(2000);
            p_searchResultPage.func_addToCDB(cdb_queryTitle_root, cdb_name, "Root Directory");
            //进入自建库页
            p_searchResultPage.func_goto_customDatabasePage();
            Assert.assertEquals(p_customDatabasePage.selfcheck(), true);
            //进入专利列表
            p_customDatabasePage.func_click_manage_byCDBName(cdb_name);
            //点击导出
            p_customDatabasePage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 6) {
                //6为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(0, fields, download_path, includeThumbnail, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "xls", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            ExcelUtil excel = null;
            if (compression == 0) {
                //非压缩文件
                excel = new ExcelUtil(download_path, fullFileName, false);
            } else if (compression == 1) {
                //压缩文件
                excel = new ExcelUtil(download_path, fullFileName, true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) excel.readExcelData();
            //验证数量
            l.info("============================== Verify number of patents in excel ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证图片
            l.info("============================== Verify image in excel ==============================");
            if (excel.getExcelImageNumber() > 0) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Image exists in excel");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Image does not exist in excel");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_excelData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-359:Exp-Selected-PDF(SRP)

    /**
     * 导出pdf，来源为搜索结果页，选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param pns_noPDF_string    -- 没有PDF的PN列表
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
            "exportNum",
            "download_timeout",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "pns_noPDF_string"
    })
    @Test
    public void export_pat_359(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int exportNum,
            int download_timeout,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String pns_noPDF_string
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-359:Exp-Selected-PDF(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            ArrayList<String> pns_noPDF = new ArrayList<String>();    //没有PDF的PN列表
            if (pns_noPDF_string != null) {
                for (String pn_noPDF : pns_noPDF_string.split(",")) {
                    pns_noPDF.add(pn_noPDF.trim());
                }
            }
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //选择专利
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = 0; i < exportNum; i++) {
                p_searchResultPage.func_click_patentCheckbox_byIndex(i + 1);
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (fields == 0) {
                    pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i + 1));
                }
            }
            //点击导出
            p_searchResultPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //导出
            String partialFileName = p_exportPage.func_export(1, fields, download_path, -1, numType, startNum, endNum, -1, null);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            Assert.assertEquals(fullFileName != null, true);
            //验证zip文件内容
            //如果导出选项选择了只导出首页，则用文件名代替pn
            if (fields == 1) {
                pns.add(fullFileName.split("\\.")[0]);
            }
            result &= p_exportPage.func_verify_pdfZip(pns, pns_noPDF, String.format("%s//%s", download_path, fullFileName), fields);
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

    //Pat-364:Exp-Order-PDF(SRP)

    /**
     * 导出pdf，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param download_timeout    -- 下载超时时间
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param pns_noPDF_string    -- 没有PDF的PN列表
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
            "download_timeout",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "pns_noPDF_string"
    })
    @Test
    public void export_pat_364(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String pns_noPDF_string
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-364:Exp-Order-PDF(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            ArrayList<String> pns_noPDF = new ArrayList<String>();    //没有PDF的PN列表
            if (pns_noPDF_string != null) {
                for (String pn_noPDF : pns_noPDF_string.split(",")) {
                    pns_noPDF.add(pn_noPDF.trim());
                }
            }
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //选择专利
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (fields == 0) {
                    pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
                }
            }
            //点击导出
            p_searchResultPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //导出
            String partialFileName = p_exportPage.func_export(1, fields, download_path, -1, numType, startNum, endNum, -1, null);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            Assert.assertEquals(fullFileName != null, true);
            //验证zip文件内容
            //如果导出选项选择了只导出首页，则用文件名代替pn
            if (fields == 1) {
                pns.add(fullFileName.split("\\.")[0]);
            }
            result &= p_exportPage.func_verify_pdfZip(pns, pns_noPDF, String.format("%s//%s", download_path, fullFileName), fields);
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

    //Pat-371:Exp-Selected-PDF(UL)

    /**
     * 导出pdf，来源为收藏夹页，选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param pns_noPDF_string    -- 没有PDF的PN列表
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
            "exportNum",
            "download_timeout",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "pns_noPDF_string"
    })
    @Test
    public void export_pat_371(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int exportNum,
            int download_timeout,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String pns_noPDF_string
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-371:Exp-Selected-PDF(UL) ==============================");
            l.info("Preconditions -- Login and create one folder with some patents, enter into this folder");
            //变量
            String folderName = "ExportTest";    //收藏夹文件夹名称
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            ArrayList<String> pns_noPDF = new ArrayList<String>();    //没有PDF的PN列表
            if (pns_noPDF_string != null) {
                for (String pn_noPDF : pns_noPDF_string.split(",")) {
                    pns_noPDF.add(pn_noPDF.trim());
                }
            }
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //删除收藏夹文件夹
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderName);
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //添加到收藏夹
            ArrayList<String> folderNames = new ArrayList<String>();
            p_searchResultPage.func_click_addToList_toobar();
            Thread.sleep(2000);
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //进入收藏夹页
            p_searchResultPage.func_goto_userListPage();
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //选择新建的文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            //选择专利
            for (int i = 0; i < exportNum; i++) {
                p_userListPage.func_click_patentCheckbox_byIndex(i + 1);
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (fields == 0) {
                    pns.add(p_userListPage.func_get_patentNumber_ByIndex(i + 1));
                }
            }
            //点击导出
            p_userListPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            Thread.sleep(5000);
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //导出
            String partialFileName = p_exportPage.func_export(1, fields, download_path, -1, numType, startNum, endNum, -1, null);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            Assert.assertEquals(fullFileName != null, true);
            //验证zip文件内容
            //如果导出选项选择了只导出首页，则用文件名代替pn
            if (fields == 1) {
                pns.add(fullFileName.split("\\.")[0]);
            }
            result &= p_exportPage.func_verify_pdfZip(pns, pns_noPDF, String.format("%s//%s", download_path, fullFileName), fields);
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

    //Pat-373:Exp-Order-PDF(UL)

    /**
     * 导出pdf，来源为收藏夹页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param download_timeout    -- 下载超时时间
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param pns_noPDF_string    -- 没有PDF的PN列表
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
            "download_timeout",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "pns_noPDF_string",
            "exportNum"
    })
    @Test
    public void export_pat_373(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String pns_noPDF_string,
            int exportNum
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-373:Exp-Order-PDF(UL) ==============================");
            l.info("Preconditions -- Login and create one folder with 120 patents, enter into this folder");
            //变量
            String folderName = "ExportTest";    //收藏夹文件夹名称
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            ArrayList<String> pns_noPDF = new ArrayList<String>();    //没有PDF的PN列表
            if (pns_noPDF_string != null) {
                for (String pn_noPDF : pns_noPDF_string.split(",")) {
                    pns_noPDF.add(pn_noPDF.trim());
                }
            }
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //删除收藏夹文件夹
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderName);
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //添加到收藏夹
            ArrayList<String> folderNames = new ArrayList<String>();
            p_searchResultPage.func_click_addToList_toobar();
            Thread.sleep(2000);
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //进入收藏夹页
            p_searchResultPage.func_goto_userListPage();
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //选择新建的文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            //选择专利
            for (int i = 0; i < exportNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (fields == 0) {
                    pns.add(p_userListPage.func_get_patentNumber_ByIndex(i + 1));
                }
            }
            //点击导出
            p_userListPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //导出
            String partialFileName = p_exportPage.func_export(1, fields, download_path, -1, numType, startNum, endNum, -1, null);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            Assert.assertEquals(fullFileName != null, true);
            //验证zip文件内容
            //如果导出选项选择了只导出首页，则用文件名代替pn
            if (fields == 1) {
                pns.add(fullFileName.split("\\.")[0]);
            }
            result &= p_exportPage.func_verify_pdfZip(pns, pns_noPDF, String.format("%s//%s", download_path, fullFileName), fields);
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

    //Pat-356:Exp-Order-PDF(SS)

    /**
     * 导出pdf，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param download_timeout    -- 下载超时时间
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param pns_noPDF_string    -- 没有PDF的PN列表
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
            "download_timeout",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "pns_noPDF_string",
            "queryName"
    })
    @Test
    public void export_pat_356(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String pns_noPDF_string,
            String queryName
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-356:Exp-Order-PDF(SS) ==============================");
            l.info("Preconditions -- Login and Add one query to Save Search, currently on Saved Search page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            ArrayList<String> pns_noPDF = new ArrayList<String>();    //没有PDF的PN列表
            if (pns_noPDF_string != null) {
                for (String pn_noPDF : pns_noPDF_string.split(",")) {
                    pns_noPDF.add(pn_noPDF.trim());
                }
            }
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //删除现有搜索
            p_searchPage.func_goto_savedSearchPage();
            p_savedSearchPage = new Zhihuiya_savedSearchPage(d);
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            p_savedSearchPage.func_delete_savedSearch(queryName);
            //创建新搜索
            p_savedSearchPage.func_searchForQuery(searchPage_keyword);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (fields == 0) {
                    pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
                }
            }
            //保存语句
            p_searchResultPage.func_click_saveQuery();
            HashMap<String, String> savedSearchResult = p_searchResultPage.func_save_query(queryName);
            String fullQueryName = savedSearchResult.get("name");
            //进入已保存搜索页
            Thread.sleep(5000);
            p_searchResultPage.func_goto_savedSearchPage();
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            //点击导出
            p_savedSearchPage.func_export_savedSearch(fullQueryName);
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //导出
            String partialFileName = p_exportPage.func_export(1, fields, download_path, -1, numType, startNum, endNum, -1, null);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            Assert.assertEquals(fullFileName != null, true);
            //验证zip文件内容
            //如果导出选项选择了只导出首页，则用文件名代替pn
            if (fields == 1) {
                pns.add(fullFileName.split("\\.")[0]);
            }
            result &= p_exportPage.func_verify_pdfZip(pns, pns_noPDF, String.format("%s//%s", download_path, fullFileName), fields);
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

    //Pat-355:Exp-Order-PDF(BS)

    /**
     * 导出pdf，来源为批量搜索页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param download_timeout    -- 下载超时时间
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param pns_noPDF_string    -- 没有PDF的PN列表
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "download_path",
            "download_timeout",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "pns_noPDF_string",
            "searchPage_bulkSearch_keyword"
    })
    @Test
    public void export_pat_355(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String download_path,
            int download_timeout,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String pns_noPDF_string,
            String searchPage_bulkSearch_keyword
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-355:Exp-Order-PDF(BS) ==============================");
            l.info("Preconditions -- Login and Input some patent numbers in inputbox on Bulk Search page, then click Add button,Then Display number of added successfully");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            ArrayList<String> pns_noPDF = new ArrayList<String>();    //没有PDF的PN列表
            if (pns_noPDF_string != null) {
                for (String pn_noPDF : pns_noPDF_string.split(",")) {
                    pns_noPDF.add(pn_noPDF.trim());
                }
            }
            for (String pn : searchPage_bulkSearch_keyword.split(" ")) {
                pns.add(pn.trim());
            }
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //点击批量搜索标签
            p_searchPage.func_click_bulkSearch();
            //批量添加专利
            p_searchPage.func_bulkSearch(searchPage_bulkSearch_keyword);
            //点击导出
            p_searchPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //导出
            String partialFileName = p_exportPage.func_export(1, fields, download_path, -1, numType, startNum, endNum, -1, null);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            Assert.assertEquals(fullFileName != null, true);
            //验证zip文件内容
            //如果导出选项选择了只导出首页，则用文件名代替pn
            if (fields == 1) {
                pns.add(fullFileName.split("\\.")[0]);
            }
            result &= p_exportPage.func_verify_pdfZip(pns, pns_noPDF, String.format("%s//%s", download_path, fullFileName), fields);
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

    //Pat-357:Exp-From Custom Database

    /**
     * 导出pdf，来源为批量搜索页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param download_timeout    -- 下载超时时间
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param pns_noPDF_string    -- 没有PDF的PN列表
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
            "download_timeout",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "pns_noPDF_string",
            "cdb_name",
            "cdb_queryTitle_root"
    })
    @Test
    public void export_pat_357(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String pns_noPDF_string,
            String cdb_name,
            String cdb_queryTitle_root
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-357:Exp-From Custom Database ==============================");
            l.info("Preconditions -- Login and currently on Custom Database page, already added one database with one query");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            ArrayList<String> pns_noPDF = new ArrayList<String>();    //没有PDF的PN列表
            if (pns_noPDF_string != null) {
                for (String pn_noPDF : pns_noPDF_string.split(",")) {
                    pns_noPDF.add(pn_noPDF.trim());
                }
            }
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //进入自建库页
            p_searchPage.func_goto_customDatabasePage();
            //删除自建库
            p_customDatabasePage = new Zhihuiya_customDatabasePage(d);
            p_customDatabasePage.func_delete_CDB_byName(cdb_name, loginPage_pwd);
            //搜索
            d.get(loginPage_url);
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //选择专利
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (fields == 0) {
                    pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
                }
            }
            //添加到自建库，需新建自建库，并将搜索加入到根目录
            p_searchResultPage.func_click_addToCDB();
            Thread.sleep(2000);
            p_searchResultPage.func_addToCDB(cdb_queryTitle_root, cdb_name, "Root Directory");
            //进入自建库页
            p_searchResultPage.func_goto_customDatabasePage();
            Assert.assertEquals(p_customDatabasePage.selfcheck(), true);
            //进入专利列表
            p_customDatabasePage.func_click_manage_byCDBName(cdb_name);
            //点击导出
            p_customDatabasePage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //导出
            String partialFileName = p_exportPage.func_export(1, fields, download_path, -1, numType, startNum, endNum, -1, null);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            Assert.assertEquals(fullFileName != null, true);
            //验证zip文件内容
            //如果导出选项选择了只导出首页，则用文件名代替pn
            if (fields == 1) {
                pns.add(fullFileName.split("\\.")[0]);
            }
            result &= p_exportPage.func_verify_pdfZip(pns, pns_noPDF, String.format("%s//%s", download_path, fullFileName), fields);
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

    //Pat-360:Exp-Selected-RTF(SRP)

    /**
     * 导出rtf，来源为搜索结果页，选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
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
            "exportNum",
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "templateFields"
    })
    @Test
    public void export_pat_360(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int exportNum,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-360:Exp-Selected-RTF(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //选择专利
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = 0; i < exportNum; i++) {
                p_searchResultPage.func_click_patentCheckbox_byIndex(i + 1);
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i + 1));
            }
            //点击导出
            p_searchResultPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 4) {
                //4为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(2, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "rtf", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            RTFUtil rtf = null;
            if (compression == 0) {
                //非压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<String> actPNs = rtf.getlinkTexts();
            String rtfContent = rtf.getText();
            ArrayList<HashMap<String, String>> actPatentData = p_exportCompletePage.func_getPatentDataFromRTF(actPNs, rtfContent);
            l.info("============================== Verify number of patents in RTF ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_RTFData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-365:Exp-Order-RTF(SRP)

    /**
     * 导出RTF，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "templateFields"
    })
    @Test
    public void export_pat_365(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-365:Exp-Order-RTF(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            int exportNum = endNum - startNum + 1;    //导出数量
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //选择专利
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = startNum; i <= endNum; i++) {
                pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
            }
            //点击导出
            p_searchResultPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 4) {
                //6为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(2, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "rtf", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            RTFUtil rtf = null;
            if (compression == 0) {
                //非压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<String> actPNs = rtf.getlinkTexts();
            String rtfContent = rtf.getText();
            ArrayList<HashMap<String, String>> actPatentData = p_exportCompletePage.func_getPatentDataFromRTF(actPNs, rtfContent);
            l.info("============================== Verify number of patents in RTF ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_RTFData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-367:Exp-Selected-RTF(UL)

    /**
     * 导出RTF，来源为收藏夹页，选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "exportNum",
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "templateFields"
    })
    @Test
    public void export_pat_367(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int exportNum,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-367:Exp-Selected-RTF(UL) ==============================");
            l.info("Preconditions -- Login and create one Folder with patents in User List, currently enter into this folder");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            String folderName = "ExportTest";    //收藏夹文件夹名称
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //删除收藏夹文件夹
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderName);
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //添加到收藏夹
            ArrayList<String> folderNames = new ArrayList<String>();
            p_searchResultPage.func_click_addToList_toobar();
            Thread.sleep(2000);
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //进入收藏夹页
            p_searchResultPage.func_goto_userListPage();
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //选择新建的文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            //选择专利
            for (int i = 0; i < exportNum; i++) {
                p_userListPage.func_click_patentCheckbox_byIndex(i + 1);
                pns.add(p_userListPage.func_get_patentNumber_ByIndex(i + 1));

            }
            //点击导出
            p_userListPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            //导出
            String partialFileName = p_exportPage.func_export(2, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "rtf", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            RTFUtil rtf = null;
            if (compression == 0) {
                //非压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<String> actPNs = rtf.getlinkTexts();
            String rtfContent = rtf.getText();
            ArrayList<HashMap<String, String>> actPatentData = p_exportCompletePage.func_getPatentDataFromRTF(actPNs, rtfContent);
            l.info("============================== Verify number of patents in RTF ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_RTFData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-375:Exp-Order-RTF(UL)

    /**
     * 导出excel，来源为收藏夹页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportNum",
            "templateFields"
    })
    @Test
    public void export_pat_375(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportNum,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-375:Exp-Order-RTF(UL) ==============================");
            l.info("Preconditions -- Login and create one folder with 120 patents, then enter into this folder");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            String folderName = "ExportTest";    //收藏夹文件夹名称
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //删除收藏夹文件夹
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderName);
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //添加到收藏夹
            ArrayList<String> folderNames = new ArrayList<String>();
            p_searchResultPage.func_click_addToList_toobar();
            Thread.sleep(2000);
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //进入收藏夹页
            p_searchResultPage.func_goto_userListPage();
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //选择新建的文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            //选择专利
            for (int i = 0; i < exportNum; i++) {
                pns.add(p_userListPage.func_get_patentNumber_ByIndex(i + 1));
            }
            //点击导出
            p_userListPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            //导出
            String partialFileName = p_exportPage.func_export(2, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "rtf", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            RTFUtil rtf = null;
            if (compression == 0) {
                //非压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<String> actPNs = rtf.getlinkTexts();
            String rtfContent = rtf.getText();
            ArrayList<HashMap<String, String>> actPatentData = p_exportCompletePage.func_getPatentDataFromRTF(actPNs, rtfContent);
            l.info("============================== Verify number of patents in RTF ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_RTFData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-379:Exp-Order-RTF(SS)

    /**
     * 导出excel，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "templateFields",
            "queryName"
    })
    @Test
    public void export_pat_379(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String templateFields,
            String queryName
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-379:Exp-Order-RTF(SS) ==============================");
            l.info("Preconditions -- Login and Add one query to Save Search, currently on Saved Search page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            int exportNum = endNum - startNum + 1;    //导出数量
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //删除现有搜索
            p_searchPage.func_goto_savedSearchPage();
            p_savedSearchPage = new Zhihuiya_savedSearchPage(d);
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            p_savedSearchPage.func_delete_savedSearch(queryName);
            //创建新搜索
            p_savedSearchPage.func_searchForQuery(searchPage_keyword);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
            }
            //保存语句
            p_searchResultPage.func_click_saveQuery();
            HashMap<String, String> savedSearchResult = p_searchResultPage.func_save_query(queryName);
            String fullQueryName = savedSearchResult.get("name");
            //进入已保存搜索页
            Thread.sleep(5000);
            p_searchResultPage.func_goto_savedSearchPage();
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            //点击导出
            p_savedSearchPage.func_export_savedSearch(fullQueryName);
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 4) {
                //4为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(2, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "rtf", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            RTFUtil rtf = null;
            if (compression == 0) {
                //非压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<String> actPNs = rtf.getlinkTexts();
            String rtfContent = rtf.getText();
            ArrayList<HashMap<String, String>> actPatentData = p_exportCompletePage.func_getPatentDataFromRTF(actPNs, rtfContent);
            l.info("============================== Verify number of patents in RTF ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_RTFData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-383:Exp-Order-RTF(BS)

    /**
     * 导出excel，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "download_path",
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "templateFields",
            "searchPage_bulkSearch_keyword",
            "exportNum"
    })
    @Test
    public void export_pat_383(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String templateFields,
            String searchPage_bulkSearch_keyword,
            int exportNum
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-383:Exp-Order-RTF(BS) ==============================");
            l.info("Preconditions -- Login and Input some patent numbers in inputbox on Bulk Search page, then click Add button,Then Display number of added successfully");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            for (String pn : searchPage_bulkSearch_keyword.split(" ")) {
                pns.add(pn.trim());
            }
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //点击批量搜索标签
            p_searchPage.func_click_bulkSearch();
            //批量添加专利
            p_searchPage.func_bulkSearch(searchPage_bulkSearch_keyword);
            //点击导出
            p_searchPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 4) {
                //4为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(2, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "rtf", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            RTFUtil rtf = null;
            if (compression == 0) {
                //非压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<String> actPNs = rtf.getlinkTexts();
            String rtfContent = rtf.getText();
            ArrayList<HashMap<String, String>> actPatentData = p_exportCompletePage.func_getPatentDataFromRTF(actPNs, rtfContent);
            l.info("============================== Verify number of patents in RTF ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_RTFData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-387:Exp-Order-RTF(SRP)

    /**
     * 导出excel，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "templateFields",
            "cdb_name",
            "cdb_queryTitle_root"
    })
    @Test
    public void export_pat_387(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String templateFields,
            String cdb_name,
            String cdb_queryTitle_root
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-387:Exp-Order-RTF(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            int exportNum = endNum - startNum + 1;    //导出数量
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            p_searchPage.func_goto_customDatabasePage();
            //删除自建库
            p_customDatabasePage = new Zhihuiya_customDatabasePage(d);
            p_customDatabasePage.func_delete_CDB_byName(cdb_name, loginPage_pwd);
            //搜索
            d.get(loginPage_url);
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //选择专利
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
            }
            //添加到自建库，需新建自建库，并将搜索加入到根目录
            p_searchResultPage.func_click_addToCDB();
            Thread.sleep(2000);
            p_searchResultPage.func_addToCDB(cdb_queryTitle_root, cdb_name, "Root Directory");
            //进入自建库页
            p_searchResultPage.func_goto_customDatabasePage();
            Assert.assertEquals(p_customDatabasePage.selfcheck(), true);
            //进入专利列表
            p_customDatabasePage.func_click_manage_byCDBName(cdb_name);
            //点击导出
            p_customDatabasePage.func_click_export();
            this.switchToNewWindow();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 4) {
                //4为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(2, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "rtf", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            RTFUtil rtf = null;
            if (compression == 0) {
                //非压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                rtf = new RTFUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<String> actPNs = rtf.getlinkTexts();
            String rtfContent = rtf.getText();
            ArrayList<HashMap<String, String>> actPatentData = p_exportCompletePage.func_getPatentDataFromRTF(actPNs, rtfContent);
            l.info("============================== Verify number of patents in RTF ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_RTFData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-358:Exp-Selected-CSV(SRP)

    /**
     * 导出csv，来源为搜索结果页，选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "exportNum",
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "templateFields"
    })
    @Test
    public void export_pat_358(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int exportNum,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-358:Exp-Selected-CSV(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //选择专利
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = 0; i < exportNum; i++) {
                p_searchResultPage.func_click_patentCheckbox_byIndex(i + 1);
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (exportType == 0) {
                    pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i + 1));
                }
            }
            //点击导出
            p_searchResultPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 6) {
                //6为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(4, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "csv", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            CsvUtil csv = null;
            if (compression == 0) {
                //非压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", false);
            } else if (compression == 1) {
                //压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) csv.readData();
            //验证数量
            l.info("============================== Verify number of patents in csv ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_csvData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-363:Exp-Order-CSV(SRP)

    /**
     * 导出csv，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "templateFields"
    })
    @Test
    public void export_pat_363(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-363:Exp-Order-CSV(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            int exportNum = endNum - startNum + 1;    //导出数量
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //选择专利
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (exportType == 0) {
                    pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
                }
            }
            //点击导出
            p_searchResultPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 6) {
                //6为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(4, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "csv", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            CsvUtil csv = null;
            if (compression == 0) {
                //非压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", false);
            } else if (compression == 1) {
                //压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) csv.readData();
            //验证数量
            l.info("============================== Verify number of patents in csv ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_csvData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-370:Exp-Selected-CSV(UL)

    /**
     * 导出csv，来源为收藏夹页，选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "exportNum",
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "templateFields"
    })
    @Test
    public void export_pat_370(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int exportNum,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-370:Exp-Selected-CSV(UL) ==============================");
            l.info("Preconditions -- Login and Create one folder with 120 patents, enter into this folder");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            String folderName = "ExportTest";    //收藏夹文件夹名称
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            //删除收藏夹文件夹
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderName);
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //添加到收藏夹
            ArrayList<String> folderNames = new ArrayList<String>();
            p_searchResultPage.func_click_addToList_toobar();
            Thread.sleep(2000);
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //进入收藏夹页
            p_searchResultPage.func_goto_userListPage();
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //选择新建的文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            //选择专利
            for (int i = 0; i < exportNum; i++) {
                p_userListPage.func_click_patentCheckbox_byIndex(i + 1);
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (exportType == 0) {
                    pns.add(p_userListPage.func_get_patentNumber_ByIndex(i + 1));
                }
            }
            //点击导出
            p_userListPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            //导出
            String partialFileName = p_exportPage.func_export(4, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "csv", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            CsvUtil csv = null;
            if (compression == 0) {
                //非压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", false);
            } else if (compression == 1) {
                //压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) csv.readData();
            //验证数量
            l.info("============================== Verify number of patents in csv ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_csvData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-374:Exp-Order-CSV(UL)

    /**
     * 导出csv，来源为收藏夹页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "exportNum",
            "templateFields"
    })
    @Test
    public void export_pat_374(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            int exportNum,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-374:Exp-Order-CSV(UL) ==============================");
            l.info("Preconditions -- Login and Create one folder with 120 patents, enter into this folder");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            String folderName = "ExportTest";    //收藏夹文件夹名称
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            //删除收藏夹文件夹
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderName);
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //添加到收藏夹
            ArrayList<String> folderNames = new ArrayList<String>();
            p_searchResultPage.func_click_addToList_toobar();
            Thread.sleep(2000);
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //进入收藏夹页
            p_searchResultPage.func_goto_userListPage();
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //选择新建的文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            //选择专利
            for (int i = 0; i < exportNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (exportType == 0) {
                    pns.add(p_userListPage.func_get_patentNumber_ByIndex(i + 1));
                }
            }
            //点击导出
            p_userListPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            //导出
            String partialFileName = p_exportPage.func_export(4, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "csv", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            CsvUtil csv = null;
            if (compression == 0) {
                //非压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", false);
            } else if (compression == 1) {
                //压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) csv.readData();
            //验证数量
            l.info("============================== Verify number of patents in csv ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_csvData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-378:Exp-Order-CSV(SS)

    /**
     * 导出excel，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "templateFields",
            "queryName"
    })
    @Test
    public void export_pat_378(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            String templateFields,
            String queryName
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-378:Exp-Order-CSV(SS) ==============================");
            l.info("Preconditions -- Login and Add one query to Save Search, currently on Saved Search page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            int exportNum = endNum - startNum + 1;    //导出数量
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            //删除现有搜索
            p_searchPage.func_goto_savedSearchPage();
            p_savedSearchPage = new Zhihuiya_savedSearchPage(d);
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            p_savedSearchPage.func_delete_savedSearch(queryName);
            //创建新搜索
            p_savedSearchPage.func_searchForQuery(searchPage_keyword);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (exportType == 0) {
                    pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
                }
            }
            //保存语句
            p_searchResultPage.func_click_saveQuery();
            HashMap<String, String> savedSearchResult = p_searchResultPage.func_save_query(queryName);
            String fullQueryName = savedSearchResult.get("name");
            //进入已保存搜索页
            Thread.sleep(5000);
            p_searchResultPage.func_goto_savedSearchPage();
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            //点击导出
            p_savedSearchPage.func_export_savedSearch(fullQueryName);
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 6) {
                //6为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(4, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "csv", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            CsvUtil csv = null;
            if (compression == 0) {
                //非压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", false);
            } else if (compression == 1) {
                //压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) csv.readData();
            //验证数量
            l.info("============================== Verify number of patents in csv ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_csvData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-382:Exp-Order-CSV(BS)

    /**
     * 导出csv，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "download_path",
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "templateFields",
            "searchPage_bulkSearch_keyword",
            "exportNum"
    })
    @Test
    public void export_pat_382(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            String templateFields,
            String searchPage_bulkSearch_keyword,
            int exportNum
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-382:Exp-Order-CSV(BS) ==============================");
            l.info("Preconditions -- Login and Input some patent numbers in inputbox on Bulk Search page, then click Add button,Then Display number of added successfully");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            for (String pn : searchPage_bulkSearch_keyword.split(" ")) {
                pns.add(pn.trim());
            }
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_bulkSearch_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_bulkSearch_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            //点击批量搜索标签
            p_searchPage.func_click_bulkSearch();
            //批量添加专利
            p_searchPage.func_bulkSearch(searchPage_bulkSearch_keyword);
            //点击导出
            p_searchPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 6) {
                //6为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(4, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "csv", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            CsvUtil csv = null;
            if (compression == 0) {
                //非压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", false);
            } else if (compression == 1) {
                //压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) csv.readData();
            //验证数量
            l.info("============================== Verify number of patents in csv ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_csvData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-386:Exp-Order-CSV(SRP)

    /**
     * 导出excel，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportType",
            "templateFields",
            "cdb_name",
            "cdb_queryTitle_root"
    })
    @Test
    public void export_pat_386(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportType,
            String templateFields,
            String cdb_name,
            String cdb_queryTitle_root
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-386:Exp-Order-CSV(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            int exportNum = endNum - startNum + 1;    //导出数量
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //如果导出涉及引用关系，将专利相关的专利，加入待验证专利列表
            if (exportType == 1) {
                if (fields == 3) {
                    //===== cites
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cites");
                } else if (fields == 4) {
                    //===== cited by
                    pns = p_searchPage.func_get_citationDetail(searchPage_keyword, loginPage_url).get("cited");
                } else {
                    //参数错误
                    l.error("Parameter [fields] is invalid");
                    Assert.assertEquals(true, false);
                }
            }
            p_searchPage.func_goto_customDatabasePage();
            //删除自建库
            p_customDatabasePage = new Zhihuiya_customDatabasePage(d);
            p_customDatabasePage.func_delete_CDB_byName(cdb_name, loginPage_pwd);
            //搜索
            d.get(loginPage_url);
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //选择专利
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                if (exportType == 0) {
                    pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
                }
            }
            //添加到自建库，需新建自建库，并将搜索加入到根目录
            p_searchResultPage.func_click_addToCDB();
            Thread.sleep(2000);
            p_searchResultPage.func_addToCDB(cdb_queryTitle_root, cdb_name, "Root Directory");
            //进入自建库页
            p_searchResultPage.func_goto_customDatabasePage();
            Assert.assertEquals(p_customDatabasePage.selfcheck(), true);
            //进入专利列表
            p_customDatabasePage.func_click_manage_byCDBName(cdb_name);
            //点击导出
            p_customDatabasePage.func_click_export();
            this.switchToNewWindow();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 6) {
                //6为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(4, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xls
                fullFileName = this.verify_download(download_path, partialFileName, "csv", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            //获取实际数据
            CsvUtil csv = null;
            if (compression == 0) {
                //非压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", false);
            } else if (compression == 1) {
                //压缩文件
                csv = new CsvUtil(String.format("%s//%S", download_path, fullFileName), "UTF-8", true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) csv.readData();
            //验证数量
            l.info("============================== Verify number of patents in csv ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            if (fields == 3 || fields == 4) {
                //验证引用关系时
                actExportNum = actPatentData.size() - 1;
            } else {
                //验证非引用关系时
                actExportNum = actPatentData.size();
            }
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_csvData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-361:Exp-Selected-XML(SRP)

    /**
     * 导出xml，来源为搜索结果页，选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
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
            "exportNum",
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "templateFields"
    })
    @Test
    public void export_pat_361(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int exportNum,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-361:Exp-Selected-XML(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //选择专利
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = 0; i < exportNum; i++) {
                p_searchResultPage.func_click_patentCheckbox_byIndex(i + 1);
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i + 1));
            }
            //点击导出
            p_searchResultPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Thread.sleep(5000);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 4) {
                //4为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(3, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xml
                fullFileName = this.verify_download(download_path, partialFileName, "xml", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            XMLUtil xml = null;
            if (compression == 0) {
                //非压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) xml.readData_forExport();
            l.info("============================== Verify number of patents in xml ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_xmlData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-366:Exp-Order-XML(SRP)

    /**
     * 导出xml，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "templateFields"
    })
    @Test
    public void export_pat_366(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-366:Exp-Order-XML(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            int exportNum = endNum - startNum + 1;    //导出数量
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //选择专利
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = startNum; i <= endNum; i++) {
                pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
            }
            //点击导出
            p_searchResultPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 4) {
                //6为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(3, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xml
                fullFileName = this.verify_download(download_path, partialFileName, "xml", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            XMLUtil xml = null;
            if (compression == 0) {
                //非压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) xml.readData_forExport();
            l.info("============================== Verify number of patents in xml ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_xmlData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-354:Exp-Selected-XML(UL)

    /**
     * 导出xml，来源为收藏夹页，选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "exportNum",
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "templateFields"
    })
    @Test
    public void export_pat_354(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int exportNum,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-354:Exp-Selected-XML(UL) ==============================");
            l.info("Preconditions -- Login and create one Folder with patents in User List, currently enter into this folder");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            String folderName = "ExportTest";    //收藏夹文件夹名称
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //删除收藏夹文件夹
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderName);
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //添加到收藏夹
            ArrayList<String> folderNames = new ArrayList<String>();
            p_searchResultPage.func_click_addToList_toobar();
            Thread.sleep(2000);
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //进入收藏夹页
            p_searchResultPage.func_goto_userListPage();
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //选择新建的文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            //选择专利
            for (int i = 0; i < exportNum; i++) {
                p_userListPage.func_click_patentCheckbox_byIndex(i + 1);
                pns.add(p_userListPage.func_get_patentNumber_ByIndex(i + 1));

            }
            //点击导出
            p_userListPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            //导出
            String partialFileName = p_exportPage.func_export(3, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xml
                fullFileName = this.verify_download(download_path, partialFileName, "xml", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            XMLUtil xml = null;
            if (compression == 0) {
                //非压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) xml.readData_forExport();
            l.info("============================== Verify number of patents in xml ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_xmlData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-376:Exp-Order-XML(UL)

    /**
     * 导出xml，来源为收藏夹页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "exportNum",
            "templateFields"
    })
    @Test
    public void export_pat_376(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            int exportNum,
            String templateFields
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== Pat-376:Exp-Order-XML(UL) ==============================");
            l.info("Preconditions -- Login and create one folder with 120 patents, then enter into this folder");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            String folderName = "ExportTest";    //收藏夹文件夹名称
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //删除收藏夹文件夹
            p_searchPage.func_goto_userListPage();
            p_userListPage = new Zhihuiya_userListPage(d);
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            p_userListPage.func_delete_userListFolder(folderName);
            d.get(loginPage_url);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //搜索
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //添加到收藏夹
            ArrayList<String> folderNames = new ArrayList<String>();
            p_searchResultPage.func_click_addToList_toobar();
            Thread.sleep(2000);
            folderNames.add(folderName);
            p_searchResultPage.func_addToList(folderNames, -1, -1, -1);
            //进入收藏夹页
            p_searchResultPage.func_goto_userListPage();
            Assert.assertEquals(p_userListPage.selfcheck(), true);
            //选择新建的文件夹
            p_userListPage.func_click_userListFolder(folderName);
            Thread.sleep(10000);
            //选择专利
            for (int i = 0; i < exportNum; i++) {
                pns.add(p_userListPage.func_get_patentNumber_ByIndex(i + 1));
            }
            //点击导出
            p_userListPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            //导出
            String partialFileName = p_exportPage.func_export(3, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xml
                fullFileName = this.verify_download(download_path, partialFileName, "xml", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            XMLUtil xml = null;
            if (compression == 0) {
                //非压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) xml.readData_forExport();
            l.info("============================== Verify number of patents in xml ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_xmlData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-380:Exp-Order-XML(SS)

    /**
     * 导出xml，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "templateFields",
            "queryName"
    })
    @Test
    public void export_pat_380(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String templateFields,
            String queryName
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-380:Exp-Order-XML(SS) ==============================");
            l.info("Preconditions -- Login and Add one query to Save Search, currently on Saved Search page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            int exportNum = endNum - startNum + 1;    //导出数量
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //删除现有搜索
            p_searchPage.func_goto_savedSearchPage();
            p_savedSearchPage = new Zhihuiya_savedSearchPage(d);
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            p_savedSearchPage.func_delete_savedSearch(queryName);
            //创建新搜索
            p_savedSearchPage.func_searchForQuery(searchPage_keyword);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
            }
            //保存语句
            p_searchResultPage.func_click_saveQuery();
            HashMap<String, String> savedSearchResult = p_searchResultPage.func_save_query(queryName);
            String fullQueryName = savedSearchResult.get("name");
            //进入已保存搜索页
            Thread.sleep(5000);
            p_searchResultPage.func_goto_savedSearchPage();
            Assert.assertEquals(p_savedSearchPage.selfcheck(), true);
            //点击导出
            p_savedSearchPage.func_export_savedSearch(fullQueryName);
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 4) {
                //4为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(3, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xml
                fullFileName = this.verify_download(download_path, partialFileName, "xml", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            XMLUtil xml = null;
            if (compression == 0) {
                //非压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) xml.readData_forExport();
            l.info("============================== Verify number of patents in xml ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_xmlData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-384:Exp-Order-XML(BS)

    /**
     * 导出xml，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
     * @throws IOException
     */
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "download_path",
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "templateFields",
            "searchPage_bulkSearch_keyword",
            "exportNum"
    })
    @Test
    public void export_pat_384(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String templateFields,
            String searchPage_bulkSearch_keyword,
            int exportNum
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-384:Exp-Order-XML(BS) ==============================");
            l.info("Preconditions -- Login and Input some patent numbers in inputbox on Bulk Search page, then click Add button,Then Display number of added successfully");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            for (String pn : searchPage_bulkSearch_keyword.split(" ")) {
                pns.add(pn.trim());
            }
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            //点击批量搜索标签
            p_searchPage.func_click_bulkSearch();
            //批量添加专利
            p_searchPage.func_bulkSearch(searchPage_bulkSearch_keyword);
            //点击导出
            p_searchPage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 4) {
                //4为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(3, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xml
                fullFileName = this.verify_download(download_path, partialFileName, "xml", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            XMLUtil xml = null;
            if (compression == 0) {
                //非压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) xml.readData_forExport();
            l.info("============================== Verify number of patents in xml ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_xmlData(expPatentData, actPatentData, fields, templateFields);
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

    //Pat-388:Exp-Order-XML(SRP)

    /**
     * 导出excel，来源为搜索结果页，不选择专利
     *
     * @param loginPage_url       -- 测试URL
     * @param loginPage_uid       -- 测试UID
     * @param loginPage_pwd       -- 测试密码
     * @param searchPage_database -- 选择数据库
     * @param searchPage_field    -- 选择搜索字段
     * @param searchPage_keyword  -- 对应字段关键字
     * @param download_path       -- 下载路径
     * @param exportNum           -- 导出数量
     * @param download_timeout    -- 下载超时时间
     * @param compression         -- 是否压缩（导出）
     * @param includeThumbnail    -- 是否包含缩略图（导出）
     * @param fields              -- 导出字段下拉框索引
     * @param numType             -- 导出数据类型：0--选择，1--顺序
     * @param startNum            -- 顺序导出时，起始数字
     * @param endNum              -- 顺序导出时，结束数字
     * @param exportType          -- 导出的类型，0--只涉及专利本身，1--涉及引用关系
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
            "download_timeout",
            "compression",
            "fields",
            "numType",
            "startNum",
            "endNum",
            "templateFields",
            "cdb_name",
            "cdb_queryTitle_root"
    })
    @Test
    public void export_pat_388(
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd,
            String searchPage_database,
            String searchPage_field,
            String searchPage_keyword,
            String download_path,
            int download_timeout,
            int compression,
            int fields,
            int numType,
            int startNum,
            int endNum,
            String templateFields,
            String cdb_name,
            String cdb_queryTitle_root
    ) throws IOException {
        try {
            l.entry();
            //测试结果
            boolean result = true;
            //处理入参
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== 	Pat-388:Exp-Order-XML(SRP) ==============================");
            l.info("Preconditions -- Login and complete one search,currently on search result page");
            //变量
            ArrayList<String> pns = new ArrayList<String>();    //待验证专利列表
            int exportNum = endNum - startNum + 1;    //导出数量
            //登录
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d); p_loginPage.func_switch_language(language);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            p_searchPage.func_goto_customDatabasePage();
            //删除自建库
            p_customDatabasePage = new Zhihuiya_customDatabasePage(d);
            p_customDatabasePage.func_delete_CDB_byName(cdb_name, loginPage_pwd);
            //搜索
            d.get(loginPage_url);
            p_searchPage.func_fieldSearch(searchPage_database, searchPage_field, searchPage_keyword, null);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //选择专利
            for (int i = startNum; i <= endNum; i++) {
                //如果导出只涉及专利本身，将选中的专利加入待验证专利列表
                pns.add(p_searchResultPage.func_get_patentNumber_ByIndex(i));
            }
            //添加到自建库，需新建自建库，并将搜索加入到根目录
            p_searchResultPage.func_click_addToCDB();
            Thread.sleep(2000);
            p_searchResultPage.func_addToCDB(cdb_queryTitle_root, cdb_name, "Root Directory");
            //进入自建库页
            p_searchResultPage.func_goto_customDatabasePage();
            Assert.assertEquals(p_customDatabasePage.selfcheck(), true);
            //进入专利列表
            p_customDatabasePage.func_click_manage_byCDBName(cdb_name);
            //点击导出
            p_customDatabasePage.func_click_export();
            this.switchToNewWindow();
            //进入导出页面
            p_exportPage = new Zhihuiya_exportPage(d);
            Assert.assertEquals(p_exportPage.selfcheck(), true);
            //处理自定义模板
            String templateName = "AutoTest";    //这里先写死，暂时不用参数化
            if (fields == 4) {
                //4为自定义模板
                //删除原有同名模板
                p_exportPage.func_delete_template(templateName);
                Thread.sleep(2000);
                //创建模板
                p_exportPage.func_create_template(templateFields, templateName);
            }
            //导出
            String partialFileName = p_exportPage.func_export(3, fields, download_path, -1, numType, startNum, endNum, compression, templateName);
            p_exportCompletePage = new Zhihuiya_exportCompletePage(d);
            Assert.assertEquals(p_exportCompletePage.selfcheck(), true);
            //下载
            Assert.assertEquals(p_exportCompletePage.func_download(download_path, download_timeout * 3), true);
            ;
            //验证下载
            Thread.sleep(2000);
            String fullFileName = null;
            if (compression == 0) {
                //非压缩，验证xml
                fullFileName = this.verify_download(download_path, partialFileName, "xml", download_timeout);
            } else if (compression == 1) {
                //压缩，验证zip
                fullFileName = this.verify_download(download_path, partialFileName, "zip", download_timeout);
            }
            Assert.assertEquals(fullFileName != null, true);
            //获取期望数据
            ArrayList<HashMap<String, String>> expPatentData = this.get_patentInfo(pns);
            XMLUtil xml = null;
            if (compression == 0) {
                //非压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), false);
            } else if (compression == 1) {
                //压缩文件
                xml = new XMLUtil(String.format("%s\\%s", download_path, fullFileName), true);
            }
            ArrayList<HashMap<String, String>> actPatentData = (ArrayList<HashMap<String, String>>) xml.readData_forExport();
            l.info("============================== Verify number of patents in xml ==============================");
            exportNum = pns.size();
            l.info("Exp patent amount: {}", exportNum);
            int actExportNum = 0;
            actExportNum = actPatentData.size();
            l.info("Act patent amount: {}", actExportNum);
            if (pns.size() == actExportNum) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Patent number is correct");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent number is incorrect");
                result &= false;
            }
            //验证详细数据
            result &= p_exportPage.func_verify_xmlData(expPatentData, actPatentData, fields, templateFields);
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



    //========================================================= other ==========================================================


    //========================================================= 公共方法  ==========================================================

    /**
     * 验证文件是否下载成功
     *
     * @param download_path    -- 期望下载路径
     * @param partialFileName  -- 期望部分文件名
     * @param extName          -- 期望扩展名小写
     * @param download_timeout -- 验证超时（会根据该时间验证三次，所以总超时时间需乘以三）
     * @return 下载文件的全名，如为空，则下载失败
     * @throws InterruptedException
     */
    public String verify_download(String download_path, String partialFileName, String extName, int download_timeout) throws InterruptedException {
        l.entry();
        String fullFileName = p_exportPage.func_verify_fileDownload(download_path, partialFileName, extName, download_timeout);
        if (fullFileName == null) {
            //如果实际文件名为空，则尝试通过备用文件名来查找文件
            l.debug("Try another file name");
            long partialFileName_int = Long.parseLong(partialFileName);
            partialFileName_int--;
            String partialFileName_bak = String.valueOf(partialFileName_int);
            fullFileName = p_exportPage.func_verify_fileDownload(download_path, partialFileName_bak, extName, download_timeout);
        }
        if (fullFileName == null) {
            //如果实际文件名为空，则尝试通过备用文件名来查找文件
            l.debug("Try another file name");
            long partialFileName_int = Long.parseLong(partialFileName);
            partialFileName_int++;
            String partialFileName_bak_2 = String.valueOf(partialFileName_int);
            fullFileName = p_exportPage.func_verify_fileDownload(download_path, partialFileName_bak_2, extName, download_timeout);
        }
        if (fullFileName != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Download successful");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Download failed");
        }
        return fullFileName;
    }

    /**
     * 获取专利详细信息
     *
     * @param pns -- 专利号列表
     * @return 专利数据列表
     * @throws InterruptedException
     */
    public ArrayList<HashMap<String, String>> get_patentInfo(ArrayList<String> pns) throws InterruptedException {
        l.entry();
        ArrayList<HashMap<String, String>> expPatentData = new ArrayList<HashMap<String, String>>();    //数据集合
        for (String pn : pns) {
            HashMap<String, String> patentInfo = new HashMap<String, String>();    //每个专利数据
            //访问专利查看页
            p_exportPage.func_searchForQuery(pn);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_verify_searchResultExist_tableView();
            try {
                p_searchResultPage.func_click_patentLink_byPN(pn);
            } catch (Exception e) {
                continue;
            }
            p_patentViewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentViewPage.selfcheck(), true);
            //记录专利数据
            Thread.sleep(2000);
            patentInfo = p_patentViewPage.func_get_patentInfoTableData();
            expPatentData.add(patentInfo);
        }
        return expPatentData;
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
