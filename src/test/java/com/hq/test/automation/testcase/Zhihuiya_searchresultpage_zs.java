package com.hq.test.automation.testcase;

import com.hq.test.automation.pageobject.*;
import com.hq.test.framework.page.BasePage;
import com.hq.test.framework.testcase.BaseTestcase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;


/**
 * Created by Administrator on 2015/1/13 0013.
 */
public class Zhihuiya_searchresultpage_zs extends BaseTestcase {
    BasePage p_basePage = new BasePage();
    Zhihuiya_loginPage p_loginPage;
    Zhihuiya_searchPage p_searchPage;
    Zhihuiya_searchResultPage p_searchResultPage;
    Zhihuiya_historyPage p_historyPage;
    Zhihuiya_patentViewPage p_patentviewPage;
    String loginPage_url;
    String loginPage_uid;
    String loginPage_pwd;
    String fieldSearch;
    String binaryQuery;
    int languageType;

    //----------------------------search result-----------------------------------------


    // 初始化数据
    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "fieldSearch",
            "binaryQuery",
            "languageType",

    })
    @BeforeClass
    public void Para(String l_loginPage_url,
                     String l_loginPage_uid,
                     String l_loginPage_pwd,
                     String l_fieldSearch,
                     String l_binaryQuery,
                     int s_languagetype
    ) {
        loginPage_url = l_loginPage_url;
        loginPage_pwd = l_loginPage_pwd;
        loginPage_uid = l_loginPage_uid;
        fieldSearch = l_fieldSearch;
        binaryQuery = l_binaryQuery;
        languageType = s_languagetype;
    }

    //登陆并搜索跳转到SRP
    public void goToSearchResultPage(String loginPage_url,
                                     String loginPage_uid,
                                     String loginPage_pwd,
                                     String fieldSearch,
                                     int languageType) {
        try {
            l.entry();
            //login
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            //search
            p_searchPage = new Zhihuiya_searchPage(d);
            p_searchPage.func_switch_language(languageType);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            p_searchPage.func_searchForQuery(fieldSearch);
            p_searchResultPage = new Zhihuiya_searchResultPage(d);
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }
    //----------------------------Binary search-----------------------------------------
    @Test
    public void searchResultBinary(
    ) {
        try {
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            boolean result = true;
            //切换到tableVIEW
            p_searchResultPage.func_jumpToTableView();
            //输入binary条件并点击refine
            p_searchResultPage.func_input_refineQuery(binaryQuery);
            p_searchResultPage.link_refine().click();
            //确认是否跳转到SRP
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //判断搜索QUERY是否正确
            String expValue = String.format("(%s) AND (%s)", fieldSearch, binaryQuery);
            String actValue = p_searchResultPage.func_getQuery();
            l.info("Exp value: {}", expValue);
            l.info("Act value: {}", actValue);
            if (expValue.equals(actValue)) {
                l.info("Passed");
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //------------------------------------------AssigneeName refine------------------------------------
    @Test
    public void refineAssigneeName() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段为AN
            p_searchResultPage.func_config_displayedFields("AN");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //选择第一个assigneeName
            int index = 0;
            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
            p_searchResultPage.func_click_refineLink();
            //确认结果是否包含该assignee name
            if (p_searchResultPage.func_verify_assigneeNameExistInDataTable(assigneeName) == true) {
                l.info("Refine successfully,filter value:{} ", assigneeName);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------------ApplicationYear refine----------------------------------
    @Test
    public void refineApplicationYear() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段为APD
            p_searchResultPage.func_config_displayedFields("APD");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //choose first one application to refine
            int index = 0;
            String applicatonYear = p_searchResultPage.func_click_filtercondition_APD_YEAR_byIndex(index);
            p_searchResultPage.func_click_refineLink();
            if (p_searchResultPage.func_verify_applicationYearExistInDataTable(applicatonYear) == true) {
                l.info("Refine successfully,, filter value:{} ", applicatonYear);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //-------------------------------------InventorName refine----------------------------------
    @Test

    public void refineInventorName() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段为IN
            p_searchResultPage.func_config_displayedFields("IN");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //choose first one inventorname to refine
            int index = 0;
            String inventorName = p_searchResultPage.func_click_filtercondition_inventorNameByIndex(index);
            p_searchResultPage.func_click_refineLink();
            if (p_searchResultPage.func_verify_inventorNameExistInDataTable(inventorName) == true) {
                l.info("Refine successfully,, filter value:{} ", inventorName);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }


    //-------------------------------------International Classification----------------------------------
    @Test

    public void refineInternationalClassification() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段为IPC
            p_searchResultPage.func_config_displayedFields("IPC");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //choose first one international classification to refine
            int index = 0;
            String internationalClassification = p_searchResultPage.func_click_filtercondition_internationalClassificationByIndex(index);
            p_searchResultPage.func_click_refineLink();
            if (p_searchResultPage.func_verify_internationalClassificationInDataTable(internationalClassification) == true) {
                l.info("Refine successfully,, filter value:{} ", internationalClassification);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //------------------refine--locarno classification-----------
    @Test

    public void refineLocarnoClassification() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段为LOC
            p_searchResultPage.func_config_displayedFields("LOC");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //choose first one international classification to refine
            int index = 0;
            String locarnoClassification = p_searchResultPage.func_click_filtercondition_locarnolClassificationByIndex(index);
            p_searchResultPage.func_click_refineLink();
            if (p_searchResultPage.func_verify_locarnoClassificationInDataTable(locarnoClassification) == true) {
                l.info("Refine successfully,, filter value:{} ", locarnoClassification);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //------------------refine--legal status-----------
    @Test

    public void refineLegalStatus() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段为LEGAL_STATUS
            p_searchResultPage.func_config_displayedFields("LEGAL_STATUS");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //choose first one legalStatus to refine
            int index = 0;
            String legalStatus = p_searchResultPage.func_click_filtercondition_legalStatusByIndex(index);
            p_searchResultPage.func_click_refineLink();
            if (p_searchResultPage.func_verify_legalstatusInDataTable(legalStatus) == true) {
                l.info("Refine successfully,, filter value:{} ", legalStatus);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //--------linkClear--------
    @Test
    public void searchResultClear() {
        try {
            l.entry();
            boolean result = true;
            //login
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //add An
            int index = 0;
            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
            //确认是否添加AN成功
            result &= p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            l.info("filterOption-AN is exist {}", result);
            Assert.assertEquals(result, true);
            //点击过滤后，判断是否清除AN
            p_searchResultPage.func_click_clearRefineLink();
            result &= p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            l.info("filterOption-AN is not exist {}", result);
            Assert.assertEquals(result, false);
            l.exit();

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //linkCopyRefineQuery
//    @Parameters({
//            "loginPage_url",
//            "loginPage_uid",
//            "loginPage_pwd",
//            "fieldSearch",
//            "languageType",
//            "binaryQuery",
//    })
//    @Test
//
//    public void searchResultCopy(
//            String loginPage_url,
//            String loginPage_uid,
//            String loginPage_pwd,
//            String fieldSearch,
//            int languageType,
//            String binaryQuery
//
//    ) {
//        try {
//            l.entry();
//            //login
//            boolean result = true;
//            d.get(loginPage_url);
//            p_loginPage = new Zhihuiya_loginPage(d);
//            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
//            p_searchPage = new Zhihuiya_searchPage(d);
//            Assert.assertEquals(p_searchPage.selfcheck(), true);
//            p_searchPage.func_switch_language(languageType);
//            //search
//            p_searchPage.func_searchForQuery(fieldSearch);
//            p_searchResultPage = new Zhihuiya_searchResultPage(d);
//            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
//            try {
//                p_searchResultPage.func_verify_searchResultExist_tableView();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            //add Binary
//            p_searchResultPage.func_input_refineQuery(binaryQuery);
//            //add An
//            int index=0;
//            String assigneeName=p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
//            //点击Copy
//            p_searchResultPage.func_click_copyLink();
//            try {
//                p_searchResultPage.prompt();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            String prompt = p_searchResultPage.prompt().getText();
//            if(prompt.equals("Refined search query has been copied to your clipboard!"))
//            {
//                result &=true;
//            }else {
//                result &=false;
//            }
//            Assert.assertEquals(result,true);
//            l.exit();
//
//        } catch (Exception e) {
//            l.error("Error!");
//            e.printStackTrace();
//            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
//            Assert.assertEquals(false, true);
//        }
//    }
    //----------------------------------Change logicword---------------------------------
    @Test
    public void filterShownBoxChangeLogicWords() {
        try {
            l.entry();
            //login
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //add An
            int index = 0;
            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
            //确认是否添加AN成功
            result &= p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            Assert.assertEquals(result, true);
            //add applocation year
            String applicationYear = p_searchResultPage.func_click_filtercondition_APD_YEAR_byIndex(index);
            //确认是否添加APyear成功
            result &= p_searchResultPage.func_verify_filterOptionApplicationYear(0);
            Assert.assertEquals(result, true);
            p_searchResultPage.func_click_buttonAnd();
            try {
                result &= true;
            } catch (Exception e) {
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //--------------------------------------Delete filteroption--------------------------
    @Test
    public void filterShownBoxoOptionDelete() {
        try {
            l.entry();
            //login
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //add An
            int index = 0;
            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
            //确认是否添加AN成功
            boolean resultAN = p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            if (resultAN == true) {
                l.info("filterOption-AN is exist {}", resultAN);
                result &= true;
            } else {
                result &= false;
            }
            p_searchResultPage.func_click_buttonDelete(0);
            try {
                p_searchResultPage.filterOptionAssigneeName(0);
                result &= false;
            } catch (Exception e) {
                result &= true;
            }

            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //----------------------------------Recent querylink------------------------------
    @Test
    public void recentLink() {
        l.entry();
        boolean result = true;
        int index = 0;
        goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
        //切换到tableview
        p_searchResultPage.func_click_view_byRel("tablelist");
        //切换到recentTab
        p_searchResultPage.func_click_recentTab();
        try {
            boolean isDisplayed = false;
            int i = 0;
            while ((!isDisplayed) && (i < 10)) {
                try {
                    p_searchResultPage.link_RecentQueryLink(index);
                    isDisplayed = true;
                } catch (Exception e) {
                    l.info("Waiting for search result table...");
                    i++;
                }
            }
            //点击第一个链接
            String query = p_searchResultPage.func_Click_RecentQueryLink(index);
            String actquery = p_searchResultPage.func_getQuery();
            if (query.equals(actquery)) {
            } else {
                result &= false;
            }
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //——————————————————————recent query save——————————————————————
    @Test
    public void recentQuerySave() {
        l.entry();
        boolean result = true;
        goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
        //切换到tableview
        p_searchResultPage.func_click_view_byRel("tablelist");
        p_searchResultPage.func_click_recentTab();
        //----等待获取第一个recent、链接
        int index = 0;
        p_searchResultPage.func_click_recentTab();
        try {
            boolean isDisplayed = false;
            int i = 0;
            while ((!isDisplayed) && (i < 10)) {
                try {
                    p_searchResultPage.link_RecentQueryLink(index);
                    isDisplayed = true;
                } catch (Exception e) {
                    l.info("Waiting for search result table...");
                    i++;
                }
            }
            WebElement qu = p_searchResultPage.link_RecentQueryLink(index);
            Actions act = new Actions(d);
            //移动鼠标到第一个链接
            act.moveToElement(qu).perform();
            //获取它的query
            String link_Query = qu.getText();
            //获取【保存】按钮，并点击
            WebElement e = d.findElement(By.className("btn-21"));
//          WebElement e = d.findElement(By.cssSelector(".btn-21.save-history-query"));
            e.click();
            //验证弹出框的保存按钮是否存在
            String search_query = p_searchResultPage.inputBox_SearchQuery().getAttribute("value");
            try {
                p_searchResultPage.button_Save();
            } catch (Exception a) {
                result &= false;
            }
            //验证弹出框的searchquery和recent内的query是否一致
            link_Query = String.format("(%s)", link_Query);
            if (search_query.equals(link_Query)) {
                result &= true;
            } else {
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //---------------------More Search history----------
    @Test
    public void moreSearchHistory() {
        l.entry();
        //--确认recent列表是否出现
        int index = 0;
        goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
        //切换到tableview
        p_searchResultPage.func_click_view_byRel("tablelist");
        p_searchResultPage.func_click_recentTab();
        try {
            boolean isDisplayed = false;
            int i = 0;
            while ((!isDisplayed) && (i < 10)) {
                try {
                    p_searchResultPage.link_RecentQueryLink(index);
                    isDisplayed = true;
                } catch (Exception e) {
                    l.info("Waiting for search result table...");
                    i++;
                }
            }
            //点击more history
            p_searchResultPage.func_click_moreSearchHistory();
            p_historyPage = new Zhihuiya_historyPage(d);
            boolean selfCheck = p_historyPage.selfcheck();
            Assert.assertEquals(selfCheck, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    ///--------------------------结果列表PN链接--------------------------
//    @Test
//    public void linkofPN() throws Exception {
//        l.entry();
//        try{
//        boolean result = true;
//        goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
//        p_searchResultPage.func_click_view_byRel("tablelist");
//        p_searchResultPage.func_config_displayedFields(toPVP);
//        String expect=p_searchResultPage.func_click_availableLink_patentList("PN");
//        p_patentviewPage=new Zhihuiya_patentViewPage(d);
//        WebElement e=d.findElement(By.cssSelector(".btn-26.primary"));
//        if(e.isDisplayed()){
//        }else {
//            result &=false;
//        }
//        result &= p_patentviewPage.func_verify_valueExistInPatentInfoTable("PN",expect);
//        Assert.assertEquals(result,true);
//        l.exit();}catch (Exception e) {
//            l.error("Error!");
//            e.printStackTrace();
//            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
//            Assert.assertEquals(false, true);
//        }
//    }
    ///---------------------结果列表Pn、Title 链接------------------------
    @Parameters({
            "toPVP"
    })
    @Test
    public void linkOfTitleAndPn(String toPVP) throws Exception {
        l.entry();
        try {
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段
            p_searchResultPage.func_config_displayedFields(toPVP);
            //切换到tableview
            result &= p_searchResultPage.func_jumpToTableView();
            Assert.assertEquals(result, true);
            String expect = p_searchResultPage.func_click_availableLink_patentList(toPVP);
            p_patentviewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentviewPage.selfcheck(), true);
//            d.navigate().refresh();
            p_patentviewPage.func_exit_tip();
            Thread.sleep(2000);
            result &= p_patentviewPage.func_verify_valueExistInPatentInfoTable(toPVP, expect);
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    ///---------------结果列表 AN,APD,PBD,ABST,LEGAL_STATUS,APN
    @Parameters({
            "toSRP"
    })
    @Test
    public void linkOfOthers(String toSRP) throws Exception {
        l.entry();
        try {
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段
            p_searchResultPage.func_config_displayedFields(toSRP);
            //切换到tableview
            result &= p_searchResultPage.func_jumpToTableView();
            Assert.assertEquals(result, true);
            String expValue = p_searchResultPage.func_click_availableLink_patentList(toSRP);
            if (p_searchResultPage.selfcheck() == true) {
                result &= p_searchResultPage.func_verify_valueExistInDataTable(toSRP, expValue);
            } else if (p_searchResultPage.selfcheck() == false) {
                p_searchResultPage.div_prompt().getText().contains(expValue);
                result &= true;
            } else {
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * pagenumber链接验证,click page number,such as 1 2 3 4 5
     * indexOfPageNumber= 1 2 3 4 5 6...
     * patentNumberPerPage=0,1,2(list index)
     */
    @Parameters(
            {
                    "indexOfPageNumber",
                    "patentNumberPerPage"
            }
    )
    @Test
    public void clickPageNumber(int index, int number) throws Exception {
        try {
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //setting patent number per page
            result &= p_searchResultPage.func_config_recordsPerPage(number);
            //click page number
            p_searchResultPage.func_click_pageNum(index);
            //get pagenumber selected
            int act = p_searchResultPage.currentPageNum();
            //获取该页面 第一行数字
            int act1 = p_searchResultPage.func_get_numberOfFirstLine(1, 1);
            //获取每页显示数量
            int pagenumber = p_searchResultPage.func_get_globalSettingPageNumber(number);
            //期望的第一行到数字
            int exp = (index - 1) * pagenumber + 1;
            if (index == act && exp == act1) {
                result &= true;
            } else {
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }

    }

    //jump to
    @Parameters({
            "pageNumber",
            "patentPerPage"
    })
    @Test
    public void jumpTo(String pagenumber, int index) {
        try {
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            p_searchResultPage.func_click_view_byRel("tablelist");
            //setting patent number per page
            result &= p_searchResultPage.func_config_recordsPerPage(index);
            p_searchResultPage.jumpTo(pagenumber);
            //获取实际页码和期望页码
            int actNum = p_searchResultPage.currentPageNum();
            int expNum = Integer.valueOf(pagenumber);
            //获取该页面 第一行数字
            int actLineNum = p_searchResultPage.func_get_numberOfFirstLine(1, 1);
            //获取期望的 第一行数字
            int pagenum = p_searchResultPage.func_get_globalSettingPageNumber(index);
            int expLineNum = (expNum - 1) * pagenum + 1;
            if (expNum == actNum && expLineNum == actLineNum) {
                result &= true;
            } else {
                result &= false;
            }
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //_________________Standardview pn ttl An In

    /**
     * 点击专利PN
     *
     * @param index 为第几个专利
     */
    @Parameters({
            "index"
    })

    @Test
    public void standardViewClickPn(int index) {
        try {
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //点击StandardView，并判断是否跳转成功
            Assert.assertEquals(p_searchResultPage.func_jumpToStandardView(index), true);
            String expPn = p_searchResultPage.get_Pn(index);
            p_searchResultPage.func_Click_Pn(index);
            p_patentviewPage = new Zhihuiya_patentViewPage(d);
//            WebElement e = d.findElement(By.cssSelector(".btn-26.primary"));
//            if (e.isDisplayed()) {
//                e.click();
////                Robot ro=new Robot();
////                ro.keyPress(KeyEvent.VK_ESCAPE);
//            } else {
//                result &= false;
//            }
            Assert.assertEquals(p_patentviewPage.selfcheck(), true);
            result &= p_searchResultPage.func_Check_Pn_PatentView(expPn);
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 点击专利标题
     */
    @Parameters(
            {
                    "title",//设置显示字段
                    "index" //第几个专利
            }
    )
    @Test
    public void standardViewClickTitle(String title, int index) {
        try {
            boolean result = true;
            //登录并搜索car 到搜索结果页
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到standard
            p_searchResultPage.func_click_view_byRel("standard");
            //设置显示字段为title 且默认显示为standard
            p_searchResultPage.func_config_displayedFields(title);
            //点击StandardView，并判断是否跳转成功
            Assert.assertEquals(p_searchResultPage.func_jumpToStandardView(index), true);
            //获取点击的专利PN
            String expPn = p_searchResultPage.get_Pn(index);
            //点击title
            p_searchResultPage.func_Click_Ttl(index);
            //确认是否跳转到专利详情页
            p_patentviewPage = new Zhihuiya_patentViewPage(d);
            Assert.assertEquals(p_patentviewPage.selfcheck(), true);
            //获取pvp页面pn
            String actPn = d.findElement(By.className("patent-info")).findElements(By.tagName("tr")).get(1).findElements(By.className("highlighter")).get(0).getText();
            //判断是否跳转正确到专利详情页
            result &= p_searchResultPage.func_Check_Pn_PatentView(expPn);
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 点击专利标题
     */
    @Parameters(
            {
                    "inventorname",//设置显示字段
                    "index",//第几个专利
                    "indexin"//第几个发明人
            }
    )
    @Test
    public void standardViewClickInventorName(String inventorname, int index, int indexin) {
        try {
            boolean result = true;
            //登录并搜索car 到搜索结果页
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到standard
            p_searchResultPage.func_click_view_byRel("standard");
            //设置显示字段为inventorname 且默认显示为standard
            p_searchResultPage.func_config_displayedFields(inventorname);
            //点击StandardView，并判断是否跳转成功
            Assert.assertEquals(p_searchResultPage.func_jumpToStandardView(index), true);
            //获取发明人信息，用于对比到期望值
            String expIn = p_searchResultPage.linkInventorName(index, indexin).getText();
            //点击发明人
            p_searchResultPage.func_Click_In(index, indexin);
            String actIn = p_searchResultPage.func_getQuery();
            if (actIn.contains(expIn)) {
                result &= true;
            } else {
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //————————————————————————————————————————————————————————————————————————————————————————————————————
    //
    //
    //                                              OKR
    //
    //————————————————————————————————————————————————————————————————————————————————————————————————————
    //___________________________________________________________________________________________________

    //________________________________________refine_________________________________________________

    /**
     * logic word
     */
    @Test
    public void pat825() {
        try {
            l.entry();
            //login
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //add An
            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(0);
            //确认是否添加AN成功
            String assigneeName2 = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(1);
            Assert.assertEquals(p_searchResultPage.func_verify_filterOptionAssigneeName(1), true);
            Assert.assertEquals(p_searchResultPage.filterLogicWordOr().isDisplayed(), true);
            //add applocation year
            String applicationYear = p_searchResultPage.func_click_filtercondition_APD_YEAR_byIndex(0);
            //确认是否添加APyear成功
            Assert.assertEquals(p_searchResultPage.func_verify_filterOptionApplicationYear(0), true);
            Assert.assertEquals(p_searchResultPage.filterLogicWordAnd().isDisplayed(), true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * display after refine
     */
    @Test
    public void pat831() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            int index = 0;
            String applicationYear = p_searchResultPage.func_click_filtercondition_APD_YEAR_byIndex(index);
            p_searchResultPage.func_click_refineLink();
            if (p_searchResultPage.filterOptionApplicationYear(index).getText().contains(applicationYear)) {
                result &= true;
            } else {
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * refine with five options of one kind filter
     */
    @Test
    public void pat715() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段为AN
            p_searchResultPage.func_config_displayedFields("AN");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //选择第五个assigneeName
            int index = 0;
            while (index < 5) {
                String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
                index++;
            }
            p_searchResultPage.func_click_refineLink();
            String an1 = p_searchResultPage.link_filtercondition(0, 0).getText();
            if (p_searchResultPage.func_verify_assigneeNameExistInDataTable(an1) == true) {
                l.info("Refine successfully,filter value:{} ", an1);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 添加单个filter的全部options，并且refine
     */
    @Parameters({
            "index",
    })
    @Test
    public void pat726(int index) {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段为AN
            p_searchResultPage.func_config_displayedFields("AN");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //选择单个filter
            String filterName = p_searchResultPage.func_Check_Refine_DropdownList(index);
            //获取filter option数量
            int num = d.findElement(By.className("cat")).findElements(By.tagName("li")).size();
            int index2 = 0;
            String an1 = p_searchResultPage.refineOption(0, 15).findElement(By.className("ico-text")).getText();
            //全部添加
            while (index2 < num) {
                p_searchResultPage.func_Check_SingleFilterOption(0, index2);
                index2++;
            }
            p_searchResultPage.func_click_refineLink();
            if (p_searchResultPage.func_verify_assigneeNameExistInDataTable(an1) == true) {
                l.info("Refine successfully,filter value:{} ", an1);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * refine with two kind filters
     */

    @Test
    public void pat727() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段为AN
            p_searchResultPage.func_config_displayedFields("AN,APD");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(0);
            String applicationYear = p_searchResultPage.func_click_filtercondition_APD_YEAR_byIndex(1);
            p_searchResultPage.func_click_refineLink();
            //确认结果是否包含该assignee name
            if (p_searchResultPage.func_verify_assigneeNameExistInDataTable(assigneeName) == true && p_searchResultPage.func_verify_applicationYearExistInDataTable(applicationYear) == true) {
                l.info("Refine successfully,filter value:{} ", assigneeName);
                result &= true;
            }
            else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }

    }

    /**
     * 添加很多options
     */
    @Parameters({
            "index1",
            "index2"
    })
    @Test
    public void pat788(int index1, int index2) {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段为AN
            p_searchResultPage.func_config_displayedFields("AN,APD");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //选择单个filter
            String filterName = p_searchResultPage.func_Check_Refine_DropdownList(index1);
            //获取filter option数量
            int num = d.findElement(By.className("cat")).findElements(By.tagName("li")).size();
            int index3 = 0;
            //全部添加assignee
            while (index3 < num) {
                p_searchResultPage.func_Check_SingleFilterOption(0, index3);
                index3++;
            }
            String an1 = p_searchResultPage.refineOption(0,46).findElement(By.className("ico-text")).getText();

            String filterdate = p_searchResultPage.func_Check_Refine_DropdownList(index2);
            //获取filter option数量
            int numnew = d.findElement(By.className("cat")).findElements(By.tagName("li")).size();
            int index4 = 0;
            //全部添加application
            while (index4 < numnew) {
                p_searchResultPage.func_Check_SingleFilterOption(2, index4);
                index4++;
            }
            p_searchResultPage.func_click_refineLink();
            if (p_searchResultPage.func_verify_assigneeNameExistInDataTable(an1) == true) {
                l.info("Refine successfully,filter value:{} ", an1);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 同种类型的2个option 并改变logicword
     */
    @Test
    public void pat789() {
        try {
            l.entry();
            //login
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_config_displayedFields("AN");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //add An
            int index = 0;
            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
            //确认是否添加AN成功
            boolean resultAN = p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            if (resultAN == true) {
                l.info("filterOption-AN is exist {}", resultAN);
                result &= true;
            } else {
                result &= false;
            }
            String an2 = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(1);
            boolean resultAN2 = p_searchResultPage.func_verify_filterOptionAssigneeName(1);
            p_searchResultPage.func_click_buttonOr();
            p_searchResultPage.func_click_refineLink();
            if (p_searchResultPage.func_verify_assigneeNameExistInDataTable(assigneeName) == true && p_searchResultPage.func_verify_assigneeNameExistInDataTable(an2) == true) {
                l.info("Refine successfully,filter value:{} ", assigneeName);
                l.info("Refine successfully,filter value:{} ", an2);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 不同类型的 option 改变逻辑词并搜索
     */
    @Test
    public void pat790() {
        try {
            l.entry();
            //login
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_config_displayedFields("AN,APD");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //add An
            int index = 0;
            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
            //确认是否添加AN成功
            boolean resultAN = p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            if (resultAN == true) {
                l.info("filterOption-AN is exist {}", resultAN);
                result &= true;
            } else {
                result &= false;
            }
            String application = p_searchResultPage.func_click_filtercondition_APD_YEAR_byIndex(0);
            p_searchResultPage.func_click_buttonAnd();
            p_searchResultPage.func_click_refineLink();
            if (p_searchResultPage.func_verify_assigneeNameExistInDataTable(assigneeName) == true) {
                l.info("Refine successfully,filter value:{} ", assigneeName);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 同时改变 and 和 or
     */
    @Test
    public void pat794() {
        try {
            l.entry();
            //login
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_config_displayedFields("AN,APD");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //add An
            int index = 0;
            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
            //确认是否添加AN成功
            boolean resultAN = p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            if (resultAN == true) {
                l.info("filterOption-AN is exist {}", resultAN);
                result &= true;
            } else {
                result &= false;
            }
            String assigneeName2 = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(1);
            String application = p_searchResultPage.func_click_filtercondition_APD_YEAR_byIndex(0);
            p_searchResultPage.func_click_buttonAnd();
            p_searchResultPage.func_click_buttonOr();
            p_searchResultPage.func_click_refineLink();
            if (p_searchResultPage.func_verify_assigneeNameExistInDataTable(assigneeName) == true) {
                l.info("Refine successfully,filter value:{} ", assigneeName);
                l.info("Refine successfully,filter value:{} ", application);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * remove one option
     */

    @Test
    public void pat795() {
        try {
            l.entry();
            //login
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            p_searchResultPage.func_config_displayedFields("AN,APD");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //add An
            int index = 0;
            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
            //确认是否添加AN成功
            boolean resultAN = p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            if (resultAN == true) {
                l.info("filterOption-AN is exist {}", resultAN);
                result &= true;
            } else {
                result &= false;
            }
            String an2 = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(1);
            p_searchResultPage.func_click_buttonDelete(1);
            p_searchResultPage.func_click_refineLink();
            if (p_searchResultPage.func_verify_assigneeNameExistInDataTable(assigneeName) == true) {
                l.info("Refine successfully,filter value:{} ", assigneeName);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * remove few options
     */
    @Test
    public void pat801() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段为AN
            p_searchResultPage.func_config_displayedFields("AN");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //选择第五个assigneeName
            int index = 0;
            while (index < 5) {
                String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
                index++;
            }
            p_searchResultPage.func_click_buttonDelete(4);
            p_searchResultPage.func_click_buttonDelete(3);
            p_searchResultPage.func_click_refineLink();
            String an1 = p_searchResultPage.link_filtercondition(0, 0).getText();
            if (p_searchResultPage.func_verify_assigneeNameExistInDataTable(an1) == true) {
                l.info("Refine successfully,filter value:{} ", an1);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * remove all filter options
     */
    @Parameters({
            "index",
    })
    @Test
    public void pat802(int index) {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //选择单个filter
            String filterName = p_searchResultPage.func_Check_Refine_DropdownList(index);
            //获取filter option数量
            int num = d.findElement(By.className("cat")).findElements(By.tagName("li")).size();
            int index2 = 0;
            //全部添加
            while (index2 < num) {
                p_searchResultPage.func_Check_SingleFilterOption(0, index2);
                index2++;
            }
            int index3 = num - 1;
            while (index3 >= 0) {
                p_searchResultPage.func_click_buttonDelete(index3);
                index3--;
            }
            p_searchResultPage.func_click_refineLink();
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }


    /**
     * binary and filter options
     */
    @Test
    public void pat803(
    ) {
        try {
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            boolean result = true;
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //设置显示字段为AN
            p_searchResultPage.func_config_displayedFields("AN");
            //切换到tableview
            p_searchResultPage.func_jumpToTableView();
            //添加第一个assignee name
            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(0);
            //输入binary条件并点击refine
            p_searchResultPage.func_input_refineQuery(binaryQuery);
            p_searchResultPage.func_click_refineLink();
            //确认是否跳转到SRP
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            //判断搜索QUERY是否正确
            String expValue = String.format("(%s) AND (%s)", fieldSearch, binaryQuery);
            String actValue = p_searchResultPage.func_getQuery();
            l.info("Exp value: {}", expValue);
            l.info("Act value: {}", actValue);
            if (expValue.equals(actValue)) {
                l.info("Passed");
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            if (p_searchResultPage.func_verify_assigneeNameExistInDataTable(assigneeName) == true) {
                l.info("Refine successfully,filter value:{} ", assigneeName);
                result &= true;
            } else {
                l.info("Failed");
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //__________________________________________________________________________________________________________
    //_____________________________________________clear________________________________________________________

    /**
     * 直接clear
     */
    @Test
    public void pat845() {
        try {
            l.entry();
            //login
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //add An
//            int index = 0;
////            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
//            //确认是否添加AN成功
//            boolean result = p_searchResultPage.func_verify_filterOptionAssigneeName();
//            l.info("filterOption-AN is exist {}", result);
//            Assert.assertEquals(result, true);
//            //点击过滤后，判断是否清除AN
            p_searchResultPage.func_click_clearRefineLink();
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            boolean result = p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            l.info("filterOption-AN is not exist {}", result);
            Assert.assertEquals(result, false);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * add and then delete， clear
     */
    @Test
    public void pat847() {
        try {
            l.entry();
            //login
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //add An
            int index = 0;
            String assigneeName = p_searchResultPage.func_click_filtercondition_AN_S_byIndex(index);
            //确认是否添加AN成功
            boolean result = p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            l.info("filterOption-AN is exist {}", result);
            Assert.assertEquals(result, true);
            p_searchResultPage.func_click_buttonDelete(0);
            //点击过滤后，判断是否清除AN
            p_searchResultPage.func_click_clearRefineLink();
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            boolean result2 = p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            l.info("filterOption-AN is not exist {}", result2);
            Assert.assertEquals(result2, false);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 添加20个同类型的options ，clear
     */
    @Test
    public void pat848() {
        try {
            l.entry();
            //login
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //切换到tableview
            p_searchResultPage.func_click_view_byRel("tablelist");
            //选择单个filter（assignee name）
            String filterName = p_searchResultPage.func_Check_Refine_DropdownList(1);
            //获取filter option数量
            int num = d.findElement(By.className("cat")).findElements(By.tagName("li")).size();
            int index2 = 0;
            //添加20个assignee names
            if (num > 20) {
                while (index2 < 20) {
                    p_searchResultPage.func_Check_SingleFilterOption(0, index2);
                    index2++;
                }
//                int i= 19;
//                while (i>=0){
//                    p_searchResultPage.func_click_buttonDelete(i);
//                    i--;
//                }

            } else {
                while (index2 < num) {
                    p_searchResultPage.func_Check_SingleFilterOption(0, index2);
                    index2++;
                }
//                int i= num-1;
//                while (i>=0){
//                    p_searchResultPage.func_click_buttonDelete(i);
//                    i--;
            }
            //点击过滤后，判断是否清除AN
            p_searchResultPage.func_click_clearRefineLink();
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            boolean result2 = p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            Assert.assertEquals(result2, false);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 添加不同类型的options并clear
     */
    @Test
    public void pat849() {
        try {
            l.entry();
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            p_searchResultPage.func_jumpToTableView();
            int num = 0;
            while (num < 5) {
                p_searchResultPage.func_Check_SingleFilterOption(num, 0);
                num++;
            }
            p_searchResultPage.func_click_clearRefineLink();
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            boolean result = p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            Assert.assertEquals(result, false);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }

    }


    /**
     * 添加很多options  并clear
     */
    @Parameters({
            "index1",
            "index2"
    })
    @Test
    public void pat850(int index1, int index2) {
        try {
            l.entry();
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            p_searchResultPage.func_jumpToTableView();
            //选择单个filter
            String filterName = p_searchResultPage.func_Check_Refine_DropdownList(index1);
            //获取filter option数量
            int num = d.findElement(By.className("cat")).findElements(By.tagName("li")).size();
            int index3 = 0;
            //全部添加assignee
            while (index3 < num) {
                p_searchResultPage.func_Check_SingleFilterOption(0, index3);
                index3++;
            }
            String filterdate = p_searchResultPage.func_Check_Refine_DropdownList(index2);
            //获取filter option数量
            int numnew = d.findElement(By.className("cat")).findElements(By.tagName("li")).size();
            int index4 = 0;
            //全部添加application
            while (index4 < numnew) {
                p_searchResultPage.func_Check_SingleFilterOption(1, index4);
                index4++;
            }
            p_searchResultPage.func_click_clearRefineLink();
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            boolean result = p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            Assert.assertEquals(result, false);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * binary search clear
     */

    @Test
    public void pat851() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            p_searchResultPage.func_jumpToTableView();
            p_searchResultPage.func_input_refineQuery(binaryQuery);
            p_searchResultPage.func_click_clearRefineLink();
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            if (binaryQuery.equals(p_searchResultPage.input_binary_editor().getAttribute("value")))
            {
                result &= true;
            } else {
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * binary and option clear
     */
    @Test
    public void pat852() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            p_searchResultPage.func_jumpToTableView();
            p_searchResultPage.func_input_refineQuery(binaryQuery);
            p_searchResultPage.func_Check_SingleFilterOption(0, 0);
            p_searchResultPage.func_click_clearRefineLink();
            Assert.assertEquals(p_searchResultPage.selfcheck(), true);
            if (binaryQuery.equals(p_searchResultPage.input_binary_editor().getText())) {
                result &= true;
            }
            Assert.assertEquals(result, true);
            boolean result2 = p_searchResultPage.func_verify_filterOptionAssigneeName(0);
            Assert.assertEquals(result2, false);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    //————————————————————————————————————————dropdownlist——all filter————————————————————————————————————————

    /**
     * link more
     */
    @Parameters(
            {
                    "index",
            }
    )
    @Test
    public void pat860(int index) {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            p_searchResultPage.func_ClickLinkMore(index);
            List<WebElement> w = d.findElement(By.className("filter-list")).findElements(By.className("cat")).get(index).findElements(By.tagName("li"));
            int i = 0;
            for (WebElement we : w) {
                if (we.isDisplayed()) {
                    i++;
                }
                if (i == 20) {
                    break;
                }
            }
            if (i >= 5) {
                if (p_searchResultPage.link_MoreFilter(index).isDisplayed() == false) {
                    result &= true;
                }
            } else {
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }

    }

    /**
     * filter select (dropdownlist)
     */

    @Test
    public void pat862() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            p_searchResultPage.func_jumpToTableView();
            p_searchResultPage.func_Check_Refine_DropdownList(1);
            if (p_searchResultPage.div_filter(0).isDisplayed() == true && p_searchResultPage.div_filter(1).isDisplayed() == false) {
                result &= true;
            } else {
                result &= false;
            }
            Assert.assertEquals(result, true);
            l.exit();
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 单个filter 选择一个option
     *
     * @param index
     */
    @Parameters({
            "index",
    })

    @Test
    public void pat882(int index) {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            p_searchResultPage.func_jumpToTableView();
            p_searchResultPage.func_Check_Refine_DropdownList(index);
            int i = index - 1;
            p_searchResultPage.func_Check_SingleFilterOption(i, 0);
            if (p_searchResultPage.refineOption(i, 0).isEnabled() == true) {
                result &= true;
            } else {
                result &= false;
            }
            Assert.assertEquals(result, true);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }

    }

    /**
     * 单个filter  选择多个options
     */
    @Parameters({
            "index",
    })

    @Test
    public void pat883(int index) {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            p_searchResultPage.func_jumpToTableView();
            p_searchResultPage.func_Check_Refine_DropdownList(index);
            int j = index - 1;
            int i = 0;
            while (i < 5) {
                p_searchResultPage.func_Check_SingleFilterOption(j, i);
                i++;
            }
            int num = d.findElement(By.className("filter-list")).findElements(By.className("cat")).get(j).findElements(By.className("disabled")).size();
            if (num == 5) {
                result &= true;
            } else {
                result &= false;
            }
            Assert.assertEquals(result, true);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }

    /**
     * 选择 单个filter下的全部options
     */
    @Parameters({
            "index",
    })
    @Test
    public void pat884(int index) {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            p_searchResultPage.func_jumpToTableView();
            p_searchResultPage.func_Check_Refine_DropdownList(index);
            int i = index - 1;
            int num = d.findElements(By.className("cat")).get(i).findElements(By.tagName("li")).size();
            int k = 0;
            while (k < num) {
                p_searchResultPage.func_Check_SingleFilterOption(i, k);
                k++;
            }

            int num2 = d.findElement(By.className("filter-list")).findElements(By.className("cat")).get(i).findElements(By.className("disabled")).size();
            if (num == num2) {
                result &= true;
            } else {
                result &= false;
            }
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }
    }
    /**
     * Recent
     */

    @Test
    public void pat959() {
        try {
            l.entry();
            boolean result = true;
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch, languageType);
            //跳转到表格示图
            p_searchResultPage.func_jumpToTableView();
            //点击最近搜索并且等待列表显示
            p_searchResultPage.func_click_recentTab();
            boolean isDisplayed = false;
            int i = 0;
            while ((isDisplayed == false) && (i < 10)) {
                try {
                    p_searchResultPage.link_RecentQueryLink(0);
                    isDisplayed = true;
                } catch (Exception e) {
                    l.info("Waiting for search result table...");
                    i++;
                }
            }
        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "Pat", "jpg");
            Assert.assertEquals(false, true);
        }

    }



}

