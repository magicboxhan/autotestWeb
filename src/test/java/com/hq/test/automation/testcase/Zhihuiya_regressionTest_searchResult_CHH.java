package com.hq.test.automation.testcase;


import com.hq.test.automation.misc.ConstantString;
import com.hq.test.automation.pageobject.Zhihuiya_loginPage;
import com.hq.test.automation.pageobject.Zhihuiya_searchPage;
import com.hq.test.automation.pageobject.Zhihuiya_searchResultPage;
import com.hq.test.framework.page.BasePage;
import com.hq.test.framework.testcase.BaseTestcase;

import org.testng.Assert;
import org.testng.annotations.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhaihuan on 2015/1/14.
 */
public class Zhihuiya_regressionTest_searchResult_CHH extends BaseTestcase {
    BasePage p_basePage = new BasePage();
    Zhihuiya_loginPage p_loginPage;
    Zhihuiya_searchPage p_searchPage;
    Zhihuiya_searchResultPage p_searchResultPage;

    private String loginPage_url;
    private String loginPage_uid;
    private String loginPage_pwd;
    private String fieldSearch;

    @Parameters({
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
            "fieldSearch"
    })
    @BeforeClass
    public void init(
            String p_loginPage_url,
            String p_loginPage_uid,
            String p_loginPage_pwd,
            String p_fieldSearch
    ) {
        loginPage_url = p_loginPage_url;
        loginPage_uid = p_loginPage_uid;
        loginPage_pwd = p_loginPage_pwd;
        fieldSearch = p_fieldSearch;
    }


    public void goToSearchResultPage(String loginPage_url,
                                     String loginPage_uid,
                                     String loginPage_pwd,
                                     String fieldSearch) {

        d.get(loginPage_url);
        p_loginPage = new Zhihuiya_loginPage(d);
        p_loginPage.func_login(loginPage_uid, loginPage_pwd);
        p_searchPage = new Zhihuiya_searchPage(d);
        Assert.assertEquals(p_searchPage.selfcheck(), true);

        l.info("switch to English language");
        p_searchPage.func_switch_language(ConstantString.EN);

        l.info("search with " + fieldSearch);
        p_searchPage.func_searchForQuery(fieldSearch);

        l.info("go to search result page");
        p_searchResultPage = new Zhihuiya_searchResultPage(d);
        Assert.assertEquals(p_searchResultPage.selfcheck(), true);

    }

    public int getExpectRecordsPerPage(int index){

        int expect;

        if(index == 0){
            expect = 20;
        }else if (index == 1){
            expect = 50;
        }else {
            expect = 100;
        }

        return expect;

    }

    public String getExpectResultDisplayKeyWord(int index){

        String expect;

        if(index == 0){
            expect = "records";
        }else if (index == 1){
            expect = "applications";
        }else if (index ==2){
            expect = "INPADOC family representatives";
        }else{
            expect = "simple family representatives";
        }

        return expect;

    }

    public boolean compare(int a, int b){
        if(a >= b){
            return true;
        }else {
            return false;
        }
    }

    public String  replaceRelToColumn(String rel) throws IOException{

        String column = "";

        if (rel.equals("PN")) {
            column = "Publication Number";
        } else if (rel.equals("APN")) {
            column = "Application Number";
        } else if (rel.equals("TITLE")) {
            column = "Title";
        } else if (rel.equals("AN")) {
            column = "Assignee Name";
        } else if (rel.equals("AN_ADD")) {
            column = "Assignee Address";
        } else if (rel.equals("AN_ST")) {
            column = "Standardized Assignee";
        } else if (rel.equals("IN")) {
            column = "Inventor Name";
        } else if (rel.equals("IN_ST")) {
            column = "Standardized Inventor";
        } else if (rel.equals("AT")) {
            column = "Attorney Name";
        } else if (rel.equals("ATC")) {
            column = "Agency";
        } else if (rel.equals("IPC")) {
            column = "International Classification";
        } else if (rel.equals("LOC")) {
            column = "Locarno Classification";
        } else if (rel.equals("UPC")) {
            column = "US Classification";
        } else if (rel.equals("APD")) {
            column = "Application Date";
        } else if (rel.equals("PBD")) {
            column = "Publication Date";
        } else if (rel.equals("LEGAL_STATUS")){
            column = "Legal Status (CN)";
        } else if (rel.equals("CPC")){
            column = "Cooperative Classification";
        } else if(rel.equals("CITED_COUNT")){
            column = "Cited by count";
        } else if(rel.equals("INPADOC_FAMILY_COUNT")){
            column = "Family count";
        } else if(rel.equals("PRIORITY")){
            column = "Priority Data";
        }

        return column;
    }

    public List getExpectedFields(String s) throws IOException{

        String[] expect;

        List expectArrays = new ArrayList();

        expect = s.split(",");

        for(int i=0;i<expect.length; i++){
            expectArrays.add(replaceRelToColumn(expect[i]));
        }

        return expectArrays;

    }

    //比较两个list里面是否含有相同的元素
    public boolean compareList(List<String> list1, List<String> list2) throws IOException{

        List<String> listA = new ArrayList<>(list1);

        listA.removeAll(list2);

        if(listA.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

//    ============================== Set default view mode start ==============================

    @DataProvider(name = "viewMode")
    public Object[][] getViewMode() {
        String value1 = ConstantString.TABLE;
        String value2 = ConstantString.STANDARD;
        String value3 = ConstantString.FLIPIT;
        String value4 = ConstantString.THUMBNAIL;
        String value5 = ConstantString.ANALYSIS;
        return new Object[][]{{"table", value1}, {"standard", value2}, {"flip_It", value3},
                {"thumbnail", value4}, {"analysis", value5}};
    }

    @Test(dataProvider = "viewMode")
    public void setDefaultViewModeInTableList(
            String testName,
            String viewMode) throws IOException {
        try {
            l.entry();

            boolean result;

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with table list");
            p_searchResultPage.func_config_defaultDisplay(ConstantString.TABLE);

            l.info("set default view mode with " + viewMode);
            p_searchResultPage.func_config_defaultDisplay(viewMode);

            l.info("verify the result view mode " + viewMode);
            result = p_searchResultPage.func_verify_viewIsSelected(viewMode);

            Assert.assertEquals(result, true);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setDefaultViewModeInTableList " + viewMode, "jpg");
            Assert.assertEquals(false, true);
        }

    }

    @Test(dataProvider = "viewMode")
    public void setDefaultViewModeInStandard(
            String testName,
            String viewMode) throws IOException {
        try {
            l.entry();

            boolean result;

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with table list");
            p_searchResultPage.func_config_defaultDisplay(ConstantString.STANDARD);

            l.info("set default view mode with " + viewMode);
            p_searchResultPage.func_config_defaultDisplay(viewMode);

            l.info("verify the result view mode " + viewMode);
            result = p_searchResultPage.func_verify_viewIsSelected(viewMode);

            Assert.assertEquals(result, true);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setDefaultViewModeInStandard " + viewMode, "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Test(dataProvider = "viewMode")
    public void setDefaultViewModeInFlipit(
            String testName,
            String viewMode) throws IOException {
        try {
            l.entry();

            boolean result;

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with table list");
            p_searchResultPage.func_config_defaultDisplay(ConstantString.FLIPIT);

            l.info("set default view mode with " + viewMode);
            p_searchResultPage.func_config_defaultDisplay(viewMode);

            l.info("verify the result view mode " + viewMode);
            result = p_searchResultPage.func_verify_viewIsSelected(viewMode);

            Assert.assertEquals(result, true);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setDefaultViewModeInFlipit" + viewMode, "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Test(dataProvider = "viewMode")
    public void setDefaultViewModeInThumbnail(
            String testName,
            String viewMode) throws IOException {
        try {
            l.entry();

            boolean result;

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with table list");
            p_searchResultPage.func_config_defaultDisplay(ConstantString.THUMBNAIL);

            l.info("set default view mode with " + viewMode);
            p_searchResultPage.func_config_defaultDisplay(viewMode);

            l.info("verify the result view mode " + viewMode);
            result = p_searchResultPage.func_verify_viewIsSelected(viewMode);

            Assert.assertEquals(result, true);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setDefaultViewModeInThumbnail" + viewMode, "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Test(dataProvider = "viewMode")
    public void setDefaultViewModeInAnalysis(
            String testName,
            String viewMode) throws IOException {
        try {
            l.entry();

            boolean result;

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with table list");
            p_searchResultPage.func_config_defaultDisplay(ConstantString.ANALYSIS);

            l.info("set default view mode with " + viewMode);
            p_searchResultPage.func_config_defaultDisplay(viewMode);

            l.info("verify the result view mode " + viewMode);
            result = p_searchResultPage.func_verify_viewIsSelected(viewMode);

            Assert.assertEquals(result, true);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setDefaultViewModeInAnalysis " + viewMode, "jpg");
            Assert.assertEquals(false, true);
        }
    }

//       ============================== Set default view mode end ==============================


//       ============================== Set default sorting start ==============================
    @DataProvider(name = "sorting")
    public Object[][] getSorting() {
        int value1 = ConstantString.MOST_RELEVANT_INDEX;
        int value2 = ConstantString.LATEST_APPLICATION_INDEX;
        int value3 = ConstantString.OLDEST_APPLICATION_INDEX;
        int value4 = ConstantString.LATEST_PUBLICATION_INDEX;
        int value5 = ConstantString.OLDEST_PUBLICATION_INDEX;
        return new Object[][]{{"Most Relevant", value1}, {"Latest Application", value2}, {"Oldest Application", value3},
                {"Latest Publication", value4}, {"Oldest Publication", value5}};
    }

    @Test(dataProvider = "sorting")
    public void setDefaultSortingInTable(
            String testName,
            int index) throws IOException {
        try {
            l.entry();

            boolean result;

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.TABLE);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.TABLE);

            l.info("set default sorting " + index);
            p_searchResultPage.func_config_sortBy(index);

            l.info("verify the sort by " + index);
            result = p_searchResultPage.func_verify_sortIsSelected(index);

            Assert.assertEquals(result, true);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setDefaultSortingInTable " + index, "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Test(dataProvider = "sorting")
    public void setDefaultSortingInStandard(
            String testName,
            int index) throws IOException {
        try {
            l.entry();

            boolean result;

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.STANDARD);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.STANDARD);

            l.info("set default sorting " + index);
            p_searchResultPage.func_config_sortBy(index);

            l.info("verify the sort by " + index);
            result = p_searchResultPage.func_verify_sortIsSelected(index);

            Assert.assertEquals(result, true);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setDefaultSortingInStandard " + index, "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Test(dataProvider = "sorting")
    public void setDefaultSortingInFlipit(
            String testName,
            int index) throws IOException {
        try {
            l.entry();

            boolean result;

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.FLIPIT);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.FLIPIT);

            l.info("Exit the full screen");
            p_searchResultPage.link_exitFullScreen_flipitView();

            l.info("set default sorting " + index);
            p_searchResultPage.func_config_sortBy(index);

            l.info("verify the sort by " + index);
            result = p_searchResultPage.func_verify_sortIsSelected(index);

            Assert.assertEquals(result, true);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setDefaultSortingInFlipit " + index, "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Test(dataProvider = "sorting")
    public void setDefaultSortingInThumbnail(
            String testName,
            int index) throws IOException {
        try {
            l.entry();

            boolean result;

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.THUMBNAIL);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.THUMBNAIL);

            l.info("set default sorting " + index);
            p_searchResultPage.func_config_sortBy(index);

            l.info("verify the sort by " + index);
            result = p_searchResultPage.func_verify_sortIsSelected(index);

            Assert.assertEquals(result, true);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setDefaultSortingInThumbnail " + index, "jpg");
            Assert.assertEquals(false, true);
        }
    }

//       ============================== Set default sorting end ==============================



//       ============================== Set records per page start ==============================

    @DataProvider(name = "recordPerPage")
    public Object[][] getRecordPerPage() {
        int value1 = ConstantString.RECORD_PER_PAGE_INDEX_0;
        int value2 = ConstantString.RECORD_PER_PAGE_INDEX_1;
        int value3 = ConstantString.RECORD_PER_PAGE_INDEX_2;
        return new Object[][]{{"record per page 20", value1}, {"record per page 50", value2},
                {"record per page 100", value3}};
    }


    @Test(dataProvider = "recordPerPage")
    public void setRecordPerPageInTable(
            String testName,
            int index) throws IOException {
        try {
            l.entry();

            int expect;
            int result;

            expect = getExpectRecordsPerPage(index);

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.TABLE);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.TABLE);

            l.info("set record per page " + index);
            p_searchResultPage.func_config_recordsPerPage(index);

            l.info("get the record number per page " + index);
            result = p_searchResultPage.func_get_numberPerPage();

            Assert.assertEquals(result, expect);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setRecordPerPageInTable " + index, "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Test(dataProvider = "recordPerPage")
    public void setRecordPerPageInStandard(
            String testName,
            int index) throws IOException {
        try {
            l.entry();

            int expect;
            int result;

            expect = getExpectRecordsPerPage(index);

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.STANDARD);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.STANDARD);

            l.info("set record per page " + index);
            p_searchResultPage.func_config_recordsPerPage(index);

            l.info("get the record number per page " + index);
            result = p_searchResultPage.func_get_numberPerPage();

            Assert.assertEquals(result, expect);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setRecordPerPageInStandard " + index, "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Test(dataProvider = "recordPerPage")
    public void setRecordPerPageInThumbnail(
            String testName,
            int index) throws IOException {
        try {
            l.entry();

            int expect;
            int result;

            expect = getExpectRecordsPerPage(index);

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.THUMBNAIL);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.THUMBNAIL);

            l.info("set record per page " + index);
            p_searchResultPage.func_config_recordsPerPage(index);

            l.info("get the record number per page " + index);
            result = p_searchResultPage.func_get_numberPerPage();

            Assert.assertEquals(result, expect);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setRecordPerPageInThumbnail " + index, "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Test(dataProvider = "recordPerPage")
    public void setRecordPerPageInFlipit(
            String testName,
            int index) throws IOException {
        try {
            l.entry();

            int expect;
            int result;

            expect = getExpectRecordsPerPage(index);

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.FLIPIT);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.FLIPIT);

            l.info("set record per page " + index);
            p_searchResultPage.func_config_recordsPerPage(index);

            p_searchResultPage.link_exitFullScreen_flipitView();

            l.info("get the record number per page " + expect);
            result = p_searchResultPage.func_get_numberPerPage();

            Assert.assertEquals(result, expect);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setRecordPerPageInFlipit " + index, "jpg");
            Assert.assertEquals(false, true);
        }
    }

//       ============================== Set records per page end ==============================




//       ============================== Set result display start ==============================

    @DataProvider(name = "resultDisplay")
    public Object[][] getResultDisplay() {
        int value1 = ConstantString.RESULT_DISPLAY_RECORDS_INDEX;
        int value2 = ConstantString.RESULT_DISPLAY_DOCUMENT_INDEX;
        int value3 = ConstantString.RESULT_DISPLAY_INPADOC_FAMILY_INDEX;
        int value4 = ConstantString.RESULT_DISPLAY_SIMPLE_FAMILY_INDEX;
        return new Object[][]{{"All the search results", value1}, {"One document per application", value2},
                {"One INPADOC family representative per group", value3}, {"One simple family representative per group", value4}};
    }

        @Test(dataProvider = "resultDisplay")
        public void setResultDisplayInTable(
                String testName,
        int index) throws IOException {
            try {
                l.entry();

                String expect;
                String result;

                expect = getExpectResultDisplayKeyWord(index);

                l.info("go to search result page");
                goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

                l.info("set default view mode with " + ConstantString.TABLE);
                p_searchResultPage.func_config_defaultDisplay(ConstantString.TABLE);

                l.info("set result display " + index);
                p_searchResultPage.func_config_resultDisplay(index);

                l.info("verify the result display " + expect);
                result = p_searchResultPage.func_get_resultDisplayKeyWord();

                Assert.assertEquals(result, expect);

            } catch (Exception e) {
                l.error("Error!");
                e.printStackTrace();
                t.takeScreenshot(d, System.getProperty("user.dir"), "setResultDisplayInTable " + index, "jpg");
                Assert.assertEquals(false, true);
            }
        }

    @Test(dataProvider = "resultDisplay")
    public void setResultDisplayInStandard(
            String testName,
            int index) throws IOException {
        try {
            l.entry();

            String expect;
            String result;

            expect = getExpectResultDisplayKeyWord(index);

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.STANDARD);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.STANDARD);

            l.info("set result display " + expect);
            p_searchResultPage.func_config_resultDisplay(index);

            l.info("verify the result display " + expect);
            result = p_searchResultPage.func_get_resultDisplayKeyWord();

            Assert.assertEquals(result, expect);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setResultDisplayInStandard " + index, "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Test(dataProvider = "resultDisplay")
    public void setResultDisplayInThumbnail(
            String testName,
            int index) throws IOException {
        try {
            l.entry();

            String expect;
            String result;

            expect = getExpectResultDisplayKeyWord(index);

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.THUMBNAIL);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.THUMBNAIL);

            l.info("set result display " + expect);
            p_searchResultPage.func_config_resultDisplay(index);

            l.info("verify the result display " + expect);
            result = p_searchResultPage.func_get_resultDisplayKeyWord();

            Assert.assertEquals(result, expect);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setResultDisplayInThumbnail " + index, "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Test(dataProvider = "resultDisplay")
    public void setResultDisplayInFlipit(
            String testName,
            int index) throws IOException {
        try {
            l.entry();

            String expect;
            String result;

            expect = getExpectResultDisplayKeyWord(index);

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.FLIPIT);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.FLIPIT);

            l.info("set result display " + expect);
            p_searchResultPage.func_config_resultDisplay(index);

            l.info("exit FlipIT full screen");
            p_searchResultPage.link_exitFullScreen_flipitView();

            l.info("verify the result display " + expect);
            result = p_searchResultPage.func_get_resultDisplayKeyWord();

            Assert.assertEquals(result, expect);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setResultDisplayInFlipit " + index, "jpg");
            Assert.assertEquals(false, true);
        }
    }

    @Test(dataProvider = "resultDisplay")
    public void setResultDisplayInAnalysis(
            String testName,
            int index) throws IOException {
        try {
            l.entry();

            String expect;
            String result;

            expect = getExpectResultDisplayKeyWord(index);

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.ANALYSIS);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.ANALYSIS);

            l.info("set result display " + expect);
            p_searchResultPage.func_config_resultDisplay(index);

            l.info("verify the result display " + expect);
            result = p_searchResultPage.func_get_resultDisplayKeyWord();

            Assert.assertEquals(result, expect);

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setResultDisplayInAnalysis " + index, "jpg");
            Assert.assertEquals(false, true);
        }
    }

//       ============================== Set result display end ==============================



//       ============================== Set fields displayed start ==============================
    @DataProvider(name = "displayFields")
    public Object[][] getDisplayFields() {
            String value1 = "PN";
            String value2 = "PN,TITLE";
            String value3 = "PN,TITLE,AN";
            String value4 = "PN,TITLE,AN,IN";
            String value5 = "PN,TITLE,AN,IN,APD";
            String value6 = "PN,TITLE,AN,IN,APD,PBD";
            String value7 = "PN,TITLE,AN,IN,APD,PBD,APN";
            String value8 = "PN,TITLE,AN,IN,APD,PBD,APN,AN_ADD";
            String value9 = "PN,AN_ST,IN_ST";
            String value10 = "PN,AT,ATC,IPC,LOC,UPC";
            String value11 = "PN,LEGAL_STATUS,CPC,CITED_COUNT,INPADOC_FAMILY_COUNT,PRIORITY";


            return new Object[][]{{"PN", value1}, {"PN,TITLE", value2}, {"PN,TITLE,AN", value3}, {"PN,TITLE,AN,IN", value4},
                    {"PN,TITLE,AN,IN,APD", value5},{"PN,TITLE,AN,IN,APD,PBD", value6},{"PN,TITLE,AN,IN,APD,PBD,APN", value7},{"PN,TITLE,AN,IN,APD,PBD,APN,AN_ADD", value8},
                    {"PN,AN_ST,IN_ST", value9}, {"PN,AT,ATC,IPC,LOC,UPC", value10}, {"PN,LEGAL_STATUS,CPC,CITED_COUNT,INPADOC_FAMILY_COUNT,PRIORITY", value11}};
    }


    @Test(dataProvider = "displayFields")
    public void setDisplayedColumnsInTable(String testNaame, String value) throws IOException {
        try {
            l.entry();

            List resultArrays =  new ArrayList();
            List expectArrays = new ArrayList();

            l.info("get expected list");
            expectArrays = getExpectedFields(value);

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);

            l.info("set default view mode with " + ConstantString.TABLE);
            p_searchResultPage.func_config_defaultDisplay(ConstantString.TABLE);

            l.info("set displayed fields " + value);
            p_searchResultPage.func_config_displayedFields(value);

            l.info("verify the displayed fields " + expectArrays);
            resultArrays = p_searchResultPage.func_get_displayedFieldsString();

            Assert.assertEquals(true, compareList(expectArrays, resultArrays));

        } catch (Exception e) {
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "setDisplayedColumnsInTable " , "jpg");
            Assert.assertEquals(false, true);
        }
    }

//       ============================== Set fields displayed end ==============================



//       ============================== Configure English Stream start ==============================

    @Test(dataProvider = "viewMode")
    public void configureEnglishStream(String testName, String viewMode) throws Exception{
        try{
            l.entry();

            int totalResultOff;
            int totalResultOn;

            l.info("go to search result page");
            goToSearchResultPage(loginPage_url, loginPage_uid, loginPage_pwd, fieldSearch);


            if(!ConstantString.ANALYSIS.equals(viewMode)) {
                l.info("set default view mode with " + viewMode);
                p_searchResultPage.func_config_defaultDisplay(viewMode);

                l.info("set English stream off");
                p_searchResultPage.func_config_englishStemming(0);

                if(ConstantString.FLIPIT.equals(viewMode)){
                    p_searchResultPage.link_exitFullScreen_flipitView();
                }
                totalResultOff = p_searchResultPage.func_get_searchResultNumber();

                l.info("set English stream on");
                p_searchResultPage.func_config_englishStemming(1);

                if(ConstantString.FLIPIT.equals(viewMode)){
                    p_searchResultPage.link_exitFullScreen_flipitView();
                }
                totalResultOn = p_searchResultPage.func_get_searchResultNumber();

                Assert.assertEquals(true, compare(totalResultOn, totalResultOff));
            }else {
                //nothing to do
            }

        }catch (Exception e){
            l.error("Error!");
            e.printStackTrace();
            t.takeScreenshot(d, System.getProperty("user.dir"), "configureEnglishStream_" + viewMode , "jpg");
            Assert.assertEquals(false, true);
        }
    }




//       ============================== Configure English Stream end ==============================



}

