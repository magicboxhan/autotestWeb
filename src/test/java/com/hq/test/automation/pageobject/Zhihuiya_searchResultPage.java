package com.hq.test.automation.pageobject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * 搜索结果页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_searchResultPage extends Zhihuiya_basePage {

    public Zhihuiya_searchResultPage(WebDriver driver) {
        l.entry();
        d = driver;
        act = new Actions(d);
        selfcheckSelector = By.id("filter_enter");
//		selfcheckSelector = By.className("refine-form");
//		selfcheckSelector = null;

        l.exit();
    }

    /********** Elements **********/

    /**
     * 链接 -- 搜索专利
     *
     * @return
     */
    public WebElement link_searchPatent() {
        l.entry();
        return d.findElement(By.cssSelector(".tab-mode.mode-search")).findElements(By.tagName("a")).get(0);
    }

    //SRP搜索框
    public WebElement input_query() {
        l.entry();
        return d.findElement(By.className("search-input"));
    }

    //成功或者错误提示框
    public WebElement prompt() {
        l.entry();
        return d.findElement(By.className("inner"));
    }

    /**
     * currentPageNumber
     */
    public WebElement currentPageNumber() {
        l.entry();
        return d.findElement(By.cssSelector(".current.perpage"));
    }

    /**
     * 链接 -- 分析专利
     *
     * @return
     */
    public WebElement link_analyzePatent() {
        l.entry();
        return d.findElement(By.cssSelector(".tab-mode.mode-search")).findElements(By.tagName("a")).get(1);
    }

    /**
     * table视图下搜索结果到某一行
     *
     * @return
     */
    public WebElement lineOfTableView(int index) {
        l.entry();
        return d.findElement(By.cssSelector(".data-table.patent-search-table")).findElements(By.tagName("tr")).get(index);
    }

    //refine逻辑词and
    public WebElement filterLogicWordAnd() {
        l.entry();
        return d.findElement(By.className("and"));

    }
    //单个refine filter div

    public WebElement div_filter(int index){
        l.entry();
        return d.findElement(By.className("filter-list")).findElements(By.className("cat")).get(index);
    }
    // refine单个fitler内的所有option
        public WebElement refineOption(int index,int index2){
        l.entry();
        return d.findElement(By.className("filter-list")).findElements(By.className("cat")).get(index).findElements(By.tagName("li")).get(index2);
    }

    /**
     * refine dropdownList
     *
     * @return
     */
    public WebElement refineDropDownList(int index) {
        l.entry();
        return d.findElement(By.id("filter-result-select")).findElement(By.className("drop-menu")).findElements(By.tagName("a")).get(index);
    }

    /**
     * 选择的filter name
     * @return
     */
    public WebElement filterSelected(){
        l.entry();
        return d.findElement(By.id("filter_result")).findElement(By.className("select-text"));
    }

    /**
     * link more
     * index 0 1 2 3 4  5
     * @return
     */
    public WebElement link_MoreFilter(int index){
        l.entry();
        return d.findElement(By.className("filter-list")).findElements(By.className("cat")).get(index).findElement(By.className("more"));
    }


    //refine框逻辑词or
    public WebElement filterLogicWordOr() {
        l.entry();
        return d.findElement(By.className("or"));
    }

    //refine delete
    public WebElement filterOptionDelete(int index) {
        l.entry();
        return d.findElement(By.className("tags-box")).findElements(By.className("an_s")).get(index).findElement(By.className("del"));
    }

    //jumpto
    public WebElement link_JumpTo() {
        l.entry();
        return d.findElement(By.className("jump-to-link"));
    }

    //jumpto --inputbox
    public WebElement inputBox_JumpTo() {
        l.entry();
        return d.findElement(By.className("jump-text"));
    }

    //jumpto --buttonJump
    public WebElement button_JumpTo() {
        l.entry();
        return d.findElement(By.id("pagego"));
    }

    /**
     * Standard 视图
     */
    //单个div
    public WebElement div_Patent(int index) {
        l.entry();
        return d.findElement(By.id("standard-list")).findElements(By.cssSelector(".searchdata.patents")).get(index);
    }

    //picture
    public WebElement linkPicture(int index) {
        return this.div_Patent(index).findElement(By.className("patentimage"));
    }

    //Pn
    public WebElement linkPn(int index) {
        l.entry();
        return this.div_Patent(index).findElement(By.className("PN"));
    }

    //title
    public WebElement linkTtl(int index) {
        l.entry();
        return this.div_Patent(index).findElement(By.className("TTL"));
    }

    //An
    public WebElement linkAn(int index) {
        return this.div_Patent(index).findElement(By.className("AN"));
    }

    //InventorName
    public WebElement linkInventorName(int divindex, int INindex) {
        return this.div_Patent(divindex).findElements(By.className("IN")).get(INindex);
    }


    //ApplicationNumber
    public WebElement linkApplicationNumber(int index) {
        return this.div_Patent(index).findElement(By.className("APN"));
    }


    /**
     * div -- 查询结果记录数
     *
     * @return
     */
    public WebElement div_searchResultNumber() {
        l.entry();
        return d.findElement(By.className("result-total"));
    }

    /**
     * span -- 结果显示关键字
     *
     * @return
     */
    public WebElement span_searchResultNumber() {
        l.entry();
        return d.findElement(By.className("result-total")).findElement(By.tagName("span"));
    }

    /**
     * span -- 底部搜索结果数
     *
     * @return
     */
    public WebElement span_searchResultNumber_bottom() {
        l.entry();
        return d.findElement(By.className("count-selector")).findElement(By.tagName("span"));
    }

    /**
     * div -- 过滤结果记录数
     *
     * @return
     */
    public WebElement div_refineResultNumber() {
        l.entry();
        return d.findElement(By.id("filter_group"));
    }

    /**
     * 专利列表--表格视图
     *
     * @return
     */
    public WebElement table_patentList_tableView() {
        l.entry();
        try {
            return d.findElement(By.id("table-list"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 专利列表--标准视图
     *
     * @return
     */
    public WebElement table_patentList_standardView() {
        l.entry();
        try {
            return d.findElement(By.id("standardlist"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 表格--用户显示设置
     *
     * @return
     */
    public WebElement table_searchPreferences() {
        l.entry();
        return d.findElement(By.id("settingform")).findElement(By.className("form-table"));
    }

    /**
     * 专利列表--标准视图
     *
     * @return
     */
    public WebElement div_patentList_standardView() {
        l.entry();
        return d.findElement(By.id("standard-list"));
    }

    //filter options shownbox
    public WebElement optionsShownBox() {
        l.entry();
        return d.findElement(By.className("tags-box"));
    }

    //AssigneeName in shownbox
    public WebElement filterOptionAssigneeName(int index) {
        l.entry();
        return d.findElement(By.className("tags-box")).findElements(By.className("an_s")).get(index);
    }

    //添加多个assignee name后确认每个name的正确性
    public WebElement filterOptionAssigneeName_2(String name) {
        l.entry();
        for (WebElement e : d.findElements(By.className("an_s"))) {
            String actname = e.getText();
            if (name.equals(actname)) {
                return e;
            }
        }
        return null;
    }
    //filter button
    public WebElement buttonSelectFilterType(){
        l.entry();
        return d.findElement(By.id("filter-result-select")).findElement(By.className("btn-26"));
    }

    //ApplicationYear in shownbox
    public WebElement filterOptionApplicationYear(int index) {
        l.entry();
        return d.findElement(By.className("tags-box")).findElements(By.className("apd_year")).get(index);
    }

    //InventorName in shownbox
    public WebElement filterOptionInventorName() {
        l.entry();
        return d.findElement(By.className("in_s"));
    }

    //InternationalClassification in shownbox
    public WebElement filterOptionInternationalClassification() {
        l.entry();
        return d.findElement(By.className("icl_s"));
    }

    //LocarnoClassification in shownbox
    public WebElement filterOptionLocarnoClassification() {
        l.entry();
        return d.findElement(By.className("loc_s"));
    }

    //LegalStatus in shownbox
    public WebElement filterOptionLegalStatus() {
        l.entry();
        return d.findElement(By.className("legal_status"));
    }

    /**
     * 根据PN返回PN所在DIV--标准视图
     *
     * @param expPN--期望PN
     * @return
     */
    public WebElement div_patent_standardView(String expPN) {
        l.entry();
        WebElement returnDiv = null;
        for (WebElement div : div_patentList_standardView().findElements(By.className("patents"))) {
            if (div.getAttribute("data-pn").contains(expPN)) {
                returnDiv = div;
                break;
            }
        }
        return returnDiv;
    }

    /**
     * 专利列表--缩略图视图
     *
     * @return
     */
    public WebElement div_patentList_thumbnailView() {
        l.entry();
        return d.findElement(By.id("thumbnail-list"));
    }

    /**
     * 专利列表--快速浏览
     *
     * @return
     */
    public WebElement ul_patentList_flipItView() {
        l.entry();
        return d.findElement(By.id("flip_nav_content"));
    }

    /**
     * 根据rel属性，返回li--选择显示字段--隐藏字段列表
     *
     * @param rel
     * @return
     */
    public WebElement li_hiddenFields_byRel(String rel) {
        l.entry();
        WebElement returnLi = null;
        for (WebElement e : d.findElement(By.id("hidden_fields")).findElements(By.tagName("li"))) {
            if (rel.equals(e.getAttribute("rel"))) {
                returnLi = e;
                break;
            }
        }
        return returnLi;
    }

    /**
     * 根据序号获取专利分析tab链接
     *
     * @param index
     * @return
     */
    public WebElement link_analyzeTab_byIndex(int index) {
        l.entry();
        l.debug("before finding analyze-tab");
        return d.findElement(By.className("analyze-tab")).findElements(By.tagName("a")).get(index);
    }

    /**
     * 根据序号获取专利分析链接
     *
     * @param index
     * @return
     */
    public WebElement link_analyzeLink_byIndex(int index) {
        l.entry();
        WebElement currentTab = null;
        List<WebElement> tabs = d.findElement(By.className("analyze-wrap")).findElements(By.className("category"));
        for (WebElement tab : tabs) {
            if (tab.isDisplayed()) {
                currentTab = tab;
                l.debug("Link group is found");
                break;
            }
        }
        if (currentTab == null) {
            l.debug("Link group is not found");
            return null;
        }
        return currentTab.findElements(By.className("analysis-link")).get(index);
    }

    /**
     * 根据序号获取图表类型链接
     *
     * @param index
     * @return
     */
    public WebElement link_analyzeType_byIndex(int index) {
        l.entry();
        List<WebElement> es = d.findElement(By.className("chart-control")).findElement(By.className("viewer-btns")).findElements(By.className("chart_type"));
        if (es.size() > 0) {
            return es.get(index);
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Analysis type button does not exist");
            return null;
        }
    }

    /**
     * 专利分析数据表格
     *
     * @return
     */
    public WebElement table_analyzeTable() {
        l.entry();
        return d.findElement(By.id("patent_analysis")).findElement(By.className("data-table"));
    }

    /**
     * 可见的PDF下载链接--table view
     *
     * @return
     */
    public WebElement link_downloadPDF_tableView() {
        l.entry();
        WebElement downloadLink = null;
        List<WebElement> es = d.findElements(By.cssSelector(".download"));
        for (WebElement e : es) {
            if (e.isDisplayed()) {
                l.debug("the download link is visible");
                downloadLink = e;
                break;
            } else {
                l.debug("current download link is invisible");
            }
        }
        return downloadLink;
    }

    public WebElement link_downloadPDF_forbit_tableView() {
        l.entry();
        WebElement downloadLink = null;
        List<WebElement> es = d.findElements(By.cssSelector(".download.forbit"));
        for (WebElement e : es) {
            if (e.isDisplayed()) {
                l.debug("the forbit link is visible");
                downloadLink = e;
                break;
            } else {
                l.debug("current forbit link is invisible");
            }
        }
        return downloadLink;
    }

    /**
     * 可见的收藏链接--table view
     *
     * @return
     */
    public WebElement link_addToList_tableView() {
        l.entry();
        WebElement downloadLink = null;
        List<WebElement> es = d.findElements(By.className("addtolist-single"));
        for (WebElement e : es) {
            if (e.isDisplayed()) {
                l.debug("the add to list link is visible");
                downloadLink = e;
                break;
            } else {
                l.debug("current add to list link is invisible");
            }
        }
        return downloadLink;
    }

    /**
     * 添加搜藏对话框--确认按钮
     *
     * @return
     */
    public WebElement link_confirm_addToListDialog() {
        l.entry();
        return d.findElement(By.id("confirm"));
    }

    /**
     * 可见的email链接--table view
     *
     * @return
     */
    public WebElement link_email_tableView() {
        l.entry();
        WebElement downloadLink = null;
        List<WebElement> es = d.findElements(By.className("emailto"));
        for (WebElement e : es) {
            if (e.isDisplayed()) {
                l.debug("the email link is visible");
                downloadLink = e;
                break;
            } else {
                l.debug("current email link is invisible");
            }
        }
        return downloadLink;
    }

    /**
     * 根据序号获取PDF下载链接--standard view
     *
     * @param index
     * @return
     */
    public WebElement link_downloadPDF_standardView_byIndex(int index) {
        l.entry();
        List<WebElement> es = d.findElements(By.cssSelector(".searchdata.patents"));
        if (es.size() != 0) {
            return d.findElements(By.cssSelector(".searchdata.patents")).get(index).findElement(By.className("btns-line")).findElement(By.className("download"));
        } else {
            l.warn("Can not find download link");
            return null;
        }
    }

    /**
     * 根据序号获取PDF下载链接(灰色)--standard view
     *
     * @param index
     * @return
     */
    public WebElement link_disabledDownloadPDF_standardView_byIndex(int index) {
        try {
            l.entry();
            WebElement e = d.findElements(By.cssSelector(".searchdata.patents")).get(index).findElement(By.className("btns-line")).findElement(By.cssSelector(".download.forbit"));
            l.debug("Disabled download link is found");
            return e;
        } catch (Exception e) {
            l.debug("Can not find disabled download link");
            return null;
        }
    }

    /**
     * 左侧过滤tab，根据序号选择
     *
     * @param index
     * @return
     */
    public WebElement link_sidebarTab_byIndex(int index) {
        l.entry();
        return d.findElement(By.id("tab_filter")).findElements(By.tagName("a")).get(index);
    }

    //--recent query-link
    public WebElement link_RecentQueryLink(int index) {
        l.entry();
        return d.findElement(By.className("recent-search-list")).findElement(By.className("query")).findElements(By.tagName("a")).get(index);
    }

    /**
     * 链接 --视图切换--通过索引
     *
     * @param index
     * @return
     */
    public WebElement link_viewType_byIndex(int index) {
        l.entry();
        return d.findElement(By.id("result-trigger")).findElements(By.tagName("a")).get(index);
    }

    /**
     * 链接 --视图切换--通过Rel
     *
     * @param rel
     * @return
     */
    public WebElement link_viewType_byRel(String rel) {
        l.entry();
        List<WebElement> es = d.findElement(By.id("result-trigger")).findElements(By.tagName("a"));
        WebElement returnLink = null;
        for (WebElement e : es) {
            if (rel.equals(e.getAttribute("rel"))) {
                returnLink = e;
                break;
            }
        }
        return returnLink;
    }

    /**
     * 链接--末页
     *
     * @return
     */
    public WebElement link_theLastPage() {
        l.entry();
        List<WebElement> links = d.findElement(By.className("page-number")).findElements(By.className("perpage"));
        return links.get(links.size() - 1);
    }

    /**
     * 链接--页码
     *
     * @param index
     * @return
     */
    public WebElement link_pageNum(int index) {
        l.entry();
        return d.findElement(By.className("page-number")).findElement(By.linkText(String.valueOf(index)));
    }

    /**
     * 链接--页码--standard view
     *
     * @param index
     * @return
     */
    public WebElement link_pageNum_standardView(int index) {
        l.entry();
        return d.findElement(By.id("standard_page")).findElement(By.className("page-number")).findElement(By.linkText(String.valueOf(index)));
    }

    /**
     * 按钮--退出全屏--快速浏览页
     *
     * @return
     */
    public WebElement link_exitFullScreen_flipitView() {
        l.entry();
        return d.findElement(By.id("screenswitch"));
    }

    /**
     * 链接--发送邮件
     *
     * @return
     */
    public WebElement link_sendEmail_emailDialog() {
        l.entry();
        return d.findElement(By.id("email-to-friend-div")).findElement(By.className("primary"));
    }

    /**
     * 链接--导出分析结果
     *
     * @return
     */
    public WebElement link_export_analysis() {
        l.entry();
        return d.findElement(By.className("save-picture"));
    }

    /**
     * 链接--导出到jpg
     *
     * @return
     */
    public WebElement link_export_analysis_jpg() {
        l.entry();
        return d.findElement(By.cssSelector(".save_as.jpg"));
    }

    /**
     * 链接--导出到csv
     *
     * @return
     */
    public WebElement link_export_analysis_csv() {
        l.entry();
        return d.findElement(By.cssSelector(".save_as.csv"));
    }

    /**
     * div--Email对话框
     *
     * @return
     */
    public WebElement div_emailDialog() {
        l.entry();
        return d.findElement(By.id("email-to-friend-div"));
    }

    /**
     * 分析视图--application_trends
     *
     * @return
     */
    public WebElement svg_application_trends() {
        l.entry();
        try {
            return d.findElement(By.id("application_trends")).findElement(By.tagName("svg"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 分析视图--public_trends
     *
     * @return
     */
    public WebElement svg_public_trends() {
        l.entry();
        try {
            return d.findElement(By.id("public_trends")).findElement(By.tagName("svg"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 分析视图--ipc_tree_map
     *
     * @return
     */
    public WebElement svg_ipc_tree_map() {
        l.entry();
        try {
            return d.findElement(By.id("ipc_tree_map")).findElement(By.tagName("svg"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 分析视图--jurisdictions
     *
     * @return
     */
    public WebElement svg_jurisdictions() {
        l.entry();
        try {
            return d.findElement(By.id("jurisdictions")).findElement(By.tagName("svg"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 分析视图--applicants
     *
     * @return
     */
    public WebElement svg_applicants() {
        l.entry();
        try {
            return d.findElement(By.id("applicants")).findElement(By.tagName("svg"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 分析视图--inventors
     *
     * @return
     */
    public WebElement svg_inventors() {
        l.entry();
        try {
            return d.findElement(By.id("inventors")).findElement(By.tagName("svg"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 链接 --更多查询历史
     *
     * @return
     */
    public WebElement link_moreSearchHistory() {
        l.entry();
        return d.findElement(By.className("historylog")).findElement(By.className("more"));
    }

    /**
     * 链接 -- 过滤条件
     *
     * @param category (0: by-AN_S; 1: by-APD_YEAR; 2: by-IN_S; 3: by-ICL_S; 4: by-LOC_FACET; 5: by-LEGAL_STATUS)
     * @param index
     * @return
     */
    public WebElement link_filtercondition(int category, int index) {
        l.entry();
        //获取页面元素class
//		String categoryClassName = null;
//		switch (category){
//		case 0:
//			categoryClassName = "by-AN_S"; break;
//		case 1:
//			categoryClassName = "by-APD_YEAR"; break;
//		case 2:
//			categoryClassName = "by-IN_S"; break;
//		case 3:
//			categoryClassName = "by-ICL_S"; break;
//		case 4:
//			categoryClassName = "by-LOC_FACET"; break;
//		case 5:
//			categoryClassName = "by-LEGAL_STATUS"; break;
//		default:
//			categoryClassName = null;
//		}
//		if (categoryClassName == null){
//			l.error("can not get filtercondition by category {}", category);
//			return null;
//		}
//		l.debug("Class of filtercondition is {}", categoryClassName);
        return d.findElement(By.className("filter-list")).findElements(By.className("cat")).get(category).findElements(By.tagName("li")).get(index).findElement(By.className("ico-text"));
    }

    /**
     * input--过滤条件编辑框
     *
     * @return
     */
    public WebElement input_binary_editor() {
        l.entry();
        return d.findElement(By.id("filter_text"));
    }

    /**
     * 复选框--全选，专利分析
     *
     * @return
     */
    public WebElement input_selectAll_analysis() {
        l.entry();
        return d.findElement(By.className("analyze-side")).findElement(By.className("select-all"));
    }

    /**
     * 按钮--刷新，专利分析
     *
     * @return
     */
    public WebElement input_refresh_analysis() {
        l.entry();
        return d.findElement(By.className("analyze-side")).findElement(By.className("refresh"));
    }

    /**
     * input--新增邮件对话框
     *
     * @return
     */
    public WebElement input_emailAddress() {
        l.entry();
        return d.findElement(By.id("emailaddress"));
    }

    /**
     * input--新文件夹名称--添加搜藏对话框
     *
     * @return
     */
    public WebElement input_folderName_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist_div")).findElement(By.className("rename"));
    }
    /**
     * 无搜索结果提示
     */
    public WebElement div_prompt(){
        l.entry();
        return d.findElement(By.id("flashMessage"));
    }

    /**
     * input--搜索名称--保存搜索对话框
     *
     * @return
     */
    public WebElement input_queryName_saveQueryDialog() {
        l.entry();
        return d.findElement(By.id("add_query_save")).findElement(By.className("name-str"));
    }

    /**
     * input--搜索内容--保存搜索对话框
     *
     * @return
     */
    public WebElement input_queryString_saveQueryDialog() {
        l.entry();
        return d.findElement(By.id("add_query_save")).findElement(By.className("query-str"));
    }

    /**
     * link--保存搜索--保存搜索对话框
     *
     * @return
     */
    public WebElement link_saveQuery_saveQueryDialog() {
        l.entry();
        return d.findElement(By.id("add_query_save")).findElement(By.className("primary"));
    }

    /**
     * div--浮出信息（摘要图片）
     *
     * @return
     */
    public WebElement div_tipBox() {
        l.entry();
//		return d.findElement(By.id("tip_box"));
        return d.findElement(By.className("tool-tip-box"));
    }

    /**
     * 按钮--新增文件夹--添加收藏按钮
     *
     * @return
     */
    public WebElement btn_createNewFolder_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist_div")).findElement(By.className("create-new-folder"));
    }

    /**
     * 按钮--新增文件夹--加号按钮
     *
     * @return
     */
    public WebElement btn_add_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist_div")).findElement(By.className("add"));
    }

    /**
     * 添加到收藏夹对话框--文件夹列表
     *
     * @return
     */
    public WebElement div_addToListDialog_folderList() {
        l.entry();
        return d.findElement(By.id("zTreeAddList"));
    }

    /**
     * 单选框 -- 选择数量，添加收藏对话框
     *
     * @return
     */
    public WebElement input_selectNum_addToListDialog() {
        l.entry();
        return d.findElement(By.id("selecttype"));
    }

    /**
     * 单选框 -- 指定顺序数量，添加收藏对话框
     *
     * @return
     */
    public WebElement input_orderNum_addToListDialog() {
        l.entry();
        return d.findElement(By.id("query_num"));
    }

    /**
     * 单选框 -- 指定顺序开始数量，添加收藏对话框
     *
     * @return
     */
    public WebElement input_startNum_addToListDialog() {
        l.entry();
        return d.findElement(By.id("startnum"));
    }

    /**
     * 单选框 -- 指定顺序结束数量，添加收藏对话框
     *
     * @return
     */
    public WebElement input_endNum_addToListDialog() {
        l.entry();
        return d.findElement(By.id("endnum"));
    }

    /**
     * 链接 -- 过滤
     *
     * @return
     */
    public WebElement link_refine() {
        l.entry();
        return d.findElement(By.id("filter_enter"));
    }


    /**
     * 链接--复制到剪贴板
     *
     * @return
     */
    public WebElement link_copy() {
        l.entry();
//		return d.findElement(By.id("copy-link"));
//		return d.findElement(By.id("zclip-ZeroClipboardMovie_1"));
        return d.findElement(By.id("ZeroClipboardMovie_1"));
    }

    /**
     * 链接--清除过滤条件
     *
     * @return
     */
    public WebElement link_clearRefine() {
        l.entry();
        return d.findElement(By.id("clear_query"));
    }

    /**
     * div--过滤条件列表
     *
     * @return
     */
    public WebElement div_filterList() {
        l.entry();
        return d.findElement(By.className("filter-list"));
    }

    /**
     * span--排序方法
     *
     * @return
     */
    public WebElement span_sortType() {
        l.entry();
        return d.findElement(By.id("sort_type"));
    }

    /**
     * span--当前已选择字段数量--定义显示字段框
     *
     * @return
     */
    public WebElement span_currentFieldsNum() {
        l.entry();
        return d.findElement(By.className("count-now"));
    }

    /**
     * span--字段数量上限--定义显示字段框
     *
     * @return
     */
    public WebElement span_limitFieldsNum() {
        l.entry();
        return d.findElement(By.className("count-limit"));
    }

    /**
     * span--邮件标题
     *
     * @return
     */
    public WebElement span_emailTitle() {
        l.entry();
        return d.findElement(By.id("email-to-friend-div")).findElement(By.className("ttl"));
    }

    /**
     * 链接--根据索引获取排序选项
     *
     * @param index
     * @return
     */
    public WebElement link_sortType_byIndex(int index) {
        l.entry();
        return d.findElement(By.id("result_sort")).findElements(By.tagName("a")).get(index);
    }

    /**
     * 链接--用户设置
     *
     * @return
     */
    public WebElement link_userSetting() {
        l.entry();
        return d.findElement(By.id("usersetting"));
    }


    //-----------Save search 弹出框-------------

    public WebElement inputBox_QueryName() {
        l.entry();
        return d.findElement(By.className("name-str"));
    }

    public WebElement inputBox_SearchQuery() {
        l.entry();
        return d.findElement(By.className("query-str"));
    }

    public WebElement button_Save() {
        l.entry();
        return d.findElement(By.className("confirm-searchquery"));
    }

    public WebElement button_Cancel() {
        l.entry();
        return d.findElement(By.className("close-searchquery"));
    }

    /**
     * 按钮--新增文件夹--取消按钮
     *
     * @return
     */
    public WebElement btn_cancel_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist_div")).findElement(By.id("cancel"));
    }

    /**
     * 按钮--新增文件夹--确认按钮
     *
     * @return
     */
    public WebElement btn_confirm_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist_div")).findElement(By.id("confirm"));
    }

    /**
     * AddToList窗口中的rename按钮(SRP)
     */
    public WebElement btn_rename_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist_div")).findElement(By.className("edit"));
    }

    /**
     * div-添加搜索框-文件夹列表
     *
     * @return 不存在返回为空
     */
    public WebElement div_folder() {
        l.entry();
        try {
            return d.findElement(By.id("addtolist_div")).findElement(By.className("ztree"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 链接-Jump To Page
     */
    public WebElement link_JumpToPage() {
        return d.findElement(By.className("count-selector")).findElement(By.className("jump-to-link"));
    }

    /**
     * input-页码输入框
     */
    public WebElement input_Num() {
        return d.findElement(By.className("jump-to")).findElement(By.className("jump-text"));
    }

    /**
     * button-跳转按钮
     */
    public WebElement btn_Go() {
        return d.findElement(By.className("jump-to")).findElement(By.id("pagego"));
    }

    /********** Functions **********/

    /**
     * 获取查询结果记录数
     *
     * @return
     * @throws Exception
     */
    public int func_get_searchResultNumber() throws Exception {
        l.entry();
        int i = 0;
        while (this.div_searchResultNumber().getText().equals("")) {
            if (i < 60) {
                l.debug("waiting for total result text");
                Thread.sleep(1000);
                i++;
            } else {
                l.debug("timeout");
                break;
            }
        }
        String strNum = this.div_searchResultNumber().getText();
        l.debug("Total number text: {}", strNum);
        String[] arrayStr = strNum.split(" ");
        int num = 0;
        String s;
        if (arrayStr.length > 3) {
            //英文
            l.debug("using English");
            s = arrayStr[arrayStr.length - 4];
        } else {
            //中文
            l.debug("using Chinese");
            s = strNum.split("共")[1].split("条")[0];
            String[] ss = s.split("析");
            if (ss.length > 1) {
                s = ss[1];
            }
        }
        s = s.replaceAll(",", "");
        num = Integer.parseInt(s);
        return num;
    }

    /**
     * 获取底部搜索结果
     *
     * @return
     * @throws Exception
     */
    public int func_get_searchResultNumber_bottom() throws Exception {
        l.entry();
        int i = 0;
        while (this.span_searchResultNumber_bottom().getText().equals("")) {
            if (i < 60) {
                l.debug("waiting for total result text on bottom");
                Thread.sleep(1000);
                i++;
            } else {
                l.debug("timeout");
                break;
            }
        }
        String strNum = this.span_searchResultNumber_bottom().getText();
        l.debug("Total number text: {}", strNum);
        String[] arrayStr = strNum.split(" ");
        int num = 0;
        String s;
        s = arrayStr[4];
        s = s.replaceAll(",", "");
        num = Integer.parseInt(s);
        return num;
    }

    /**
     * 获取查询结果数（分组）
     *
     * @return
     * @throws Exception
     */
    public int func_get_searchResultNumber_byGroup() throws Exception {
        l.entry();
        int i = 0;
        while (this.div_searchResultNumber().getText().equals("")) {
            if (i < 60) {
                l.debug("waiting for total result text");
                Thread.sleep(1000);
                i++;
            } else {
                l.debug("timeout");
                break;
            }
        }
        String strNum = this.div_searchResultNumber().getText();
        l.debug("Total number text: {}", strNum);
        String[] arrayStr = strNum.split(" ");
        int num = 0;
        String s;
        if (arrayStr.length > 3) {
            //英文
            l.debug("using English");
            s = arrayStr[2];
        } else {
            //中文
            l.debug("using Chinese");
            s = strNum.split("共")[1].split("组")[0].split("条")[0];
        }
        s = s.replaceAll(",", "");
        num = Integer.parseInt(s);
        return num;
    }

    /**
     * 获取过滤结果记录数
     *
     * @return
     * @throws Exception
     */
    public int func_get_refineResultNumber() throws Exception {
        l.entry();
        int i = 0;
        while (this.div_refineResultNumber().getText().equals("")) {
            if (i < 60) {
                l.debug("waiting for total result text");
                Thread.sleep(1000);
                i++;
            } else {
                l.debug("timeout");
                break;
            }
        }
        String strNum = this.div_refineResultNumber().getText();
        l.debug("Total number text: {}", strNum);
        String[] arrayStr = strNum.split(" ");
        int num = 0;
        String s;
        s = arrayStr[2];
        s = s.replaceAll(",", "");
        num = Integer.parseInt(s);
        return num;
    }

    /**
     * 获取每页专利数据
     *
     * @return
     * @throws Exception
     */
    public int func_get_numberPerPage() throws Exception {
        l.entry();
        int i = 0;
        while (this.div_searchResultNumber().getText().equals("")) {
            if (i < 60) {
                l.debug("waiting for total result text");
                Thread.sleep(1000);
                i++;
            } else {
                l.debug("timeout");
                break;
            }
        }
        String strNum = this.div_searchResultNumber().getText();
        l.debug("Total number text: {}", strNum);
        String[] arrayStr = strNum.split(" ");
        int num = 0;
        String s;
        s = arrayStr[0];
        s = s.split("-")[1];
        num = Integer.parseInt(s);
        return num;
    }

    /**
     * 获取结果显示关键字
     *
     * @return
     * @throws Exception
     */
    public String func_get_resultDisplayKeyWord() throws Exception {
        l.entry();
        int i = 0;

        while (this.div_searchResultNumber().getText().equals("")) {
            if (i < 60) {
                l.debug("waiting for total result text");
                Thread.sleep(1000);
                i++;
            } else {
                l.debug("timeout");
                break;
            }
        }

        String totalResult = div_searchResultNumber().getText();
        String[] arrayStr = totalResult.split(",");
        String s;
        s = arrayStr[0];
        s = s.substring(s.length()-7, s.length());
        if(s.equals("records")){
            return  "records";
        }else {

            return this.span_searchResultNumber().getText();
        }

    }

    /**
     * 获取检索结果页面的列名（显示字段）元素
     *
     * @return
     * @throws Exception
     */
    public List<WebElement> func_get_displayedFieldsElements() throws Exception{
        l.entry();

        Thread.sleep(3000);
        return table_patentList_tableView().findElement(By.cssSelector(".data-table.patent-search-table")).findElements(By.tagName("th"));

    }

    /**
     * 获取检索结果页面的列名（显示字段）class 字符串
     *
     * @return
     * @throws Exception
     */
    public List func_get_displayedFieldsString() throws Exception{
        l.entry();

        List result =  new ArrayList();

        for (int i = 2; i < func_get_displayedFieldsElements().size(); i ++){
            result.add(func_get_displayedFieldsElements().get(i).getText());
        }

        return result;
    }

    /**
     * 返回末页页码
     *
     * @return
     */
    public int func_get_totalPageNum() {
        l.entry();
        return Integer.parseInt(this.link_theLastPage().getText());
    }

    /**
     * 返回搜索结果专利数据
     */
    public List<HashMap<String, String>> func_getTableData_patentList() {
        l.entry();
        WebElement table = this.table_patentList_tableView();
        //返回数据
        return this.getTableData(table);
    }

    /**
     * 返回专利分析数据
     */
    public ArrayList<HashMap<String, String>> func_getTableData_analysis() {
        l.entry();
        WebElement table = this.table_analyzeTable();
        //返回数据
        return this.getTableData_analysisTable(table);
    }

    /**
     * 获取列表中的专利数量
     *
     * @return
     */
    public int func_get_patentListNum_flipitView() {
        l.entry();
        return this.ul_patentList_flipItView().findElements(By.tagName("li")).size();
    }

    /**
     * 返回当前页专利数
     *
     * @return
     */
    public int func_get_patentListNum_tableView() {
        l.entry();
        return this.table_patentList_tableView().findElements(By.tagName("tr")).size() - 1;
    }

    /**
     * 点击 landscape 链接
     */
    public void func_click_landscape() {
        l.entry();
        this.getElementById("landscape").click();
        l.exit();
    }

    /**
     * 点击 email alert 链接
     */
    public void func_click_emailAlert() {
        l.entry();
        this.getElementById("emailalert").click();
        l.exit();
    }

    /**
     * 点击保存搜索链接
     */
    public void func_click_saveQuery() {
        l.entry();
        this.getElementById("searchquery").click();
        l.exit();
    }

    /**
     * 点击 export 链接
     */
    public void func_click_export() {
        l.entry();
        this.getElementById("exportx").click();
        l.exit();
    }

    /**
     * 点击添加收藏链接--工具栏
     */
    public void func_click_addToList_toobar() {
        l.entry();
        this.getElementById("addtolist_all").click();
        l.exit();
    }

    /**
     * 点击加入自建库链接
     */
    public void func_click_addToCDB() {
        l.entry();
        this.getElementById("addQueryToCBD").click();
        l.exit();
    }

    /**
     * 根据列名，点击列中的第一个可用链接，并返回链接文字
     *
     * @param col
     * @return 链接文字
     */
    public String func_click_availableLink_patentList(String col) {
        l.entry();
        WebElement returnLink = null;
        //1.根据入参，确定td的class
        String tdClassName = null;
        if (col.toLowerCase().equals("pn")) {
            tdClassName = "col-pn";
        } else if (col.toLowerCase().equals("apn")) {
            tdClassName = "col-apn";
        } else if (col.toLowerCase().equals("title")) {
            tdClassName = "col-title";
        } else if (col.toLowerCase().equals("an")) {
            tdClassName = "col-an";
        } else if (col.toLowerCase().equals("an_add")) {
            tdClassName = "col-an_add";
        } else if (col.toLowerCase().equals("an_st")) {
            tdClassName = "col-an_st";
        } else if (col.toLowerCase().equals("in")) {
            tdClassName = "col-in";
        } else if (col.toLowerCase().equals("in_st")) {
            tdClassName = "col-in_st";
        } else if (col.toLowerCase().equals("at")) {
            tdClassName = "col-at";
        } else if (col.toLowerCase().equals("atc")) {
            tdClassName = "col-atc";
        } else if (col.toLowerCase().equals("ipc")) {
            tdClassName = "col-ipc";
        } else if (col.toLowerCase().equals("loc")) {
            tdClassName = "col-loc";
        } else if (col.toLowerCase().equals("upc")) {
            tdClassName = "col-upc";
        }
        l.info("Column class: [{}]", tdClassName);
        //2.获取表格
        WebElement table = this.table_patentList_tableView();
        if (table == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent table is not found");
            t.takeScreenshot(d);
            return null;
        }
        //3.遍历行，如果行中指定列有链接，则点击链接，并返回链接文字
        boolean matchFlag = false;
        for (WebElement row : table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"))) {
            List<WebElement> links = row.findElement(By.className(tdClassName)).findElements(By.tagName("a"));
            if (links.size() != 0) {
                returnLink = links.get(0);
                l.info("++++++++++++++++++++++++++++++ Pass -- Link is found");
                matchFlag = true;
                break;
            }
        }
        if (!matchFlag) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Link is not found");
            t.takeScreenshot(d);
        }
        //返回链接文字
        String linkText = null;
        if (returnLink != null) {
            linkText = returnLink.getText();
            l.info("Link url: {}", returnLink.getAttribute("href"));
            returnLink.click();
        } else {
            linkText = null;
        }
        return linkText;
    }

    /**
     * 点击 分析专利 链接
     */
    public void func_click_analyzePatent() {
        l.entry();
        this.link_analyzePatent().click();
        l.exit();
    }

    /**
     * 获取专利标题
     *
     * @param index
     * @return
     */
    public String func_get_patentTitleByIndex(int index) {
        l.entry();
        String title = null;
        List<HashMap<String, String>> patentListData = this.getTableData(this.table_patentList_tableView());
        if (patentListData.size() > 0) {
            title = patentListData.get(index).get("Title");
            if (title == null) {
                l.debug("using Chinese");
                title = patentListData.get(index).get("标题");
            }
        }
        l.debug("title is []", title);
        return title;
    }

    /**
     * 点击PN链接
     *
     * @param index
     */
    public void func_click_patentLink_ByIndex(int index) {
        l.entry();
        WebElement link_pn;
        List<WebElement> trs = this.table_patentList_tableView().findElements(By.tagName("tr"));
        if (trs.size() > 0) {
            link_pn = trs.get(index).findElement(By.className("name-link"));
            l.info("click PN: {}", link_pn.getText());
            link_pn.click();
        } else {
            l.warn("can not find PN link");
        }
        l.exit();
    }

    /**
     * 点击PN链接
     *
     * @param index
     */
    public void func_click_ANLink_ByIndex(int index) {
        l.entry();
        WebElement link_an;
        List<WebElement> trs = this.table_patentList_tableView().findElements(By.tagName("tr"));
        if (trs.size() > 0) {
            link_an = trs.get(index).findElement(By.className("name-link"));
            l.info("click AN: {}", link_an.getText());
            link_an.click();
        } else {
            l.warn("can not find AN link");
        }
        l.exit();
    }

    /**
     * 根据PN点击链接
     *
     * @param pn
     */
    public void func_click_patentLink_byPN(String pn) {
        l.entry();
        this.table_patentList_tableView().findElement(By.linkText(pn)).click();
        l.exit();
    }

    /**
     * 点击 table view 中的PDF下载链接--通过索引
     *
     * @param index -- 1开始
     * @throws Exception
     */
    public void func_click_pDFDownloadLink_ByIndex_tableView(int index) throws Exception {
        l.entry();
        WebElement link_pn;
        List<WebElement> trs = this.table_patentList_tableView().findElements(By.tagName("tr"));
        if (trs.size() > 0) {
            link_pn = trs.get(index).findElement(By.className("PN"));
            l.info("PN: {}", link_pn.getText());
            act = new Actions(d);
            act.moveToElement(link_pn);
            act.perform();
            Thread.sleep(1000);
            WebElement e;
            int i = 0;
            while ((this.link_downloadPDF_tableView() == null) || (this.link_downloadPDF_forbit_tableView() != null)) {
                if (i < 60) {
                    l.info("Trying to get download link...");
                    act = new Actions(d);
                    act.moveToElement(this.input_query());
                    act.perform();
                    act.moveToElement(link_pn);
                    act.perform();
                    Thread.sleep(1000);
                    i++;
                } else {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Timeout");
                    break;
                }
            }
            e = this.link_downloadPDF_tableView();
            if (e != null) {
                l.info("Download link is displayed");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Can not find download link");
            }
            act.moveToElement(e);
            act.perform();
            Thread.sleep(1000);
            e.click();
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Can not find PN link");
        }
        l.exit();
    }

    /**
     * 加入收藏夹
     *
     * @throws InterruptedException
     * @throws AWTException
     */
    public boolean func_addToList(ArrayList<String> folders, int numType, int startNum, int endNum) throws InterruptedException, AWTException {
        l.entry();
        WebElement span_lastFolder = null; //最后一次选中的文件夹
        for (int i = 0; i < folders.size(); i++) {
            String folder = folders.get(i);
            if (i == 0) {
                //父文件夹
                l.info("Expected parent folder name is [{}]", folder);
            } else {
                //先判断有无父文件夹
                if (span_lastFolder == null) {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Parent folder is null");
                    t.takeScreenshot(d);
                    return false;
                }
                //子文件夹
                l.info("Expected level [{}] sub folder name is [{}]", i, folder);
                //双击父文件夹，使其展开
                act = new Actions(d);
                act.moveToElement(span_lastFolder).doubleClick().perform();
            }

            WebElement span_existingFolder = null;
            //确定文件夹是否已存在
            boolean matchFlag = false; //是否找到匹配文件夹
            List<WebElement> lis_folder = null;
            if (i == 0) {
                lis_folder = this.div_addToListDialog_folderList().findElements(By.tagName("span"));
            } else {
                lis_folder = span_lastFolder.findElements(By.tagName("span"));
            }
            if (lis_folder.size() == 0) {
                //文件夹列表为空
                matchFlag = false;
            } else {
                for (WebElement span : lis_folder) {
                    //如果文件夹存在且可见，才选中
                    if (span.isDisplayed()) {
                        String actFolderName = span.getText();
                        if (actFolderName.equals(folder)) {
                            //找到疲累文件夹
                            l.info("Folder exists", folder);
                            span_existingFolder = span;
                            matchFlag = true;
                            break;
                        }
                    }
                }
            }
            //文件夹不存在，则新建文件夹
            if (matchFlag == false) {
                //文件夹不存在
                l.info("Folder does not exist, so create a new one");
                //点击新建按钮
                if (i == 0) {
                    this.btn_createNewFolder_addToListDialog().click();
                } else {
                    span_lastFolder.click();
                    this.btn_add_addToListDialog().click();
                }
                //输入文件夹名称
                this.input_folderName_addToListDialog().sendKeys(folder);
//				//使输入文件夹名称生效
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_ENTER);
//				this.input_folderName_addToListDialog().sendKeys(Keys.ENTER);
//				this.input_folderName_addToListDialog().click();
                this.getElementById("zTreeAddList").findElement(By.tagName("a")).click();
                //重新寻找并选中文件夹span
                List<WebElement> lis_folder_afterCreation = this.div_addToListDialog_folderList().findElements(By.tagName("span"));
                for (WebElement span : lis_folder_afterCreation) {
                    String actFolderName = span.getText();
                    if (actFolderName.equals(folder)) {
                        //找到疲累文件夹
                        l.info("Folder exists", folder);
                        span.click();
                        span_lastFolder = span;
                        break;
                    }
                }
            } else {
                //文件夹存在，选中它
                span_existingFolder.click();
                span_lastFolder = span_existingFolder;
            }
            l.info("The last folder is [{}]", span_lastFolder);
        }
        //选择数量（0或1以外的情况，视为默认）
        if (numType == 0) {
            //选择数量
            this.input_selectNum_addToListDialog().click();
        } else if (numType == 1) {
            //指定顺序数量
            this.input_orderNum_addToListDialog().click();
            this.input_startNum_addToListDialog().clear();
            this.input_startNum_addToListDialog().sendKeys(String.valueOf(startNum));
            this.input_endNum_addToListDialog().clear();
            this.input_endNum_addToListDialog().sendKeys(String.valueOf(endNum));
        }
        //点击提交
        this.link_confirm_addToListDialog().click();
        return true;
    }

    /**
     * 发送邮件
     *
     * @param address--收件人地址
     * @return 邮件标题
     */
    public String func_sendEmail(String address) {
        l.entry();
        String emailTitle = null;
        if (this.doesExist(By.id("email-to-friend-div"))) {
            //对话框存在，则发邮件
            this.input_emailAddress().clear();
            this.input_emailAddress().sendKeys(address);
            emailTitle = this.span_emailTitle().getText();
            this.link_sendEmail_emailDialog().click();
            l.info("Email is send, and recipients are [{}]", address);
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Email dialog does not exist");
            t.takeScreenshot(d);
            return null;
        }
        return emailTitle;
    }

    /**
     * 点击收藏链接--通过索引
     *
     * @param index -- 1开始
     * @throws Exception
     */
    public void func_click_addToListLink_ByIndex_tableView(int index) throws Exception {
        l.entry();
        WebElement link_pn;
        List<WebElement> trs = this.table_patentList_tableView().findElements(By.tagName("tr"));
        if (trs.size() > 0) {
            link_pn = trs.get(index).findElement(By.className("PN"));
            l.info("PN: {}", link_pn.getText());
            act = new Actions(d);
            act.moveToElement(link_pn);
            act.perform();
            WebElement e;
            int i = 0;
            while (this.link_addToList_tableView() == null) {
                if (i < 60) {
                    l.info("Trying to get add to list link...");
                    act = new Actions(d);
                    act.moveToElement(this.input_query());
                    act.perform();
                    act.moveToElement(link_pn);
                    act.perform();
                    Thread.sleep(1000);
                    i++;
                } else {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Timeout");
                    break;
                }
            }
            e = this.link_addToList_tableView();
            if (e != null) {
                l.info("Add to list link is displayed");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Can not find add to list link");
            }
            act.moveToElement(e);
            act.perform();
            Thread.sleep(1000);
            e.click();
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Can not find PN link");
        }
        l.exit();
    }

    /**
     * 点击email链接--table view
     *
     * @param index
     * @throws Exception
     */
    public void func_click_emailLink_ByIndex_tableView(int index) throws Exception {
        l.entry();
        WebElement link_pn;
        List<WebElement> trs = this.table_patentList_tableView().findElements(By.tagName("tr"));
        if (trs.size() > 0) {
            link_pn = trs.get(index).findElement(By.className("PN"));
            l.info("PN: {}", link_pn.getText());
            WebElement e;
            int i = 0;
            while (this.link_email_tableView() == null) {
                if (i < 60) {
                    l.info("Trying to get email link...");
                    act = new Actions(d);
                    act.moveToElement(this.input_query());
                    act.perform();
                    act.moveToElement(link_pn);
                    act.perform();
                    Thread.sleep(1000);
                    i++;
                } else {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Timeout");
                    break;
                }
            }
            e = this.link_email_tableView();
            if (e != null) {
                l.info("Email link is displayed");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Can not find email link");
            }
            act.moveToElement(e);
            act.perform();
            Thread.sleep(1000);
            e.click();
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Can not find PN link");
        }
        l.exit();
    }

    /**
     * 点击 standard view 中的PDF下载链接
     *
     * @param index
     * @throws Exception
     */
    public void func_click_pDFDownloadLink_ByIndex_standardView(int index) throws Exception {
        l.entry();
        int i = 0;
//		while (this.isVisible(By.cssSelector(".download.forbit"))){
        while (this.link_disabledDownloadPDF_standardView_byIndex(index) != null) {
            if (i < 60) {
                Thread.sleep(1000);
                i++;
                l.debug("Waiting for download link...");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Download link is disabled");
                t.takeScreenshot(d);
                return;
            }
        }
        if (this.link_downloadPDF_standardView_byIndex(index) != null) {
            l.debug("PDF download link is visible");
            this.link_downloadPDF_standardView_byIndex(index).click();
        } else {
            return;
        }

        l.exit();
    }

    /**
     * 获得PN
     *
     * @param index
     * @return
     */
    public String func_get_patentNumber_ByIndex(int index) {
        l.entry();
        String pn = null;
        WebElement link_pn;
        List<WebElement> trs = this.table_patentList_tableView().findElements(By.tagName("tr"));
        if (trs.size() > 0) {
            link_pn = trs.get(index).findElement(By.className("PN"));
            pn = link_pn.getText();
            l.info("PN: {}", pn);
        } else {
            l.warn("can not find PN link");
        }
        return pn;
    }

    /**
     * 获取PN链接的图片资源地址
     *
     * @param index
     * @return 图片资源地址
     */
    public String func_get_patentImgSrc_ByIndex(int index) {
        l.entry();
        WebElement link_pn;
        String imgSrc = null;
        List<WebElement> trs = this.table_patentList_tableView().findElements(By.tagName("tr"));
        if (trs.size() > 0) {
            link_pn = trs.get(index).findElement(By.className("PN"));
//			imgSrc = link_pn.getAttribute("data-img-src");
            imgSrc = trs.get(index).getAttribute("data-pic");
            l.info("Current PN: {}", link_pn.getText());
            l.info("Patent img src: {}", imgSrc);
        } else {
            l.warn("Can not find PN link");
        }
        return imgSrc;
    }

    public boolean func_verify_patentImgSrc(String patentImgSrc) throws Exception, IOException {
        l.entry();
        boolean result = true;
        if (patentImgSrc != null) {
            //发请求，验证返回结果
            HttpClient client = new HttpClient();
            HttpMethod method = new GetMethod(patentImgSrc);
            client.executeMethod(method);
            int resStatus = method.getStatusLine().getStatusCode();
            String resPath = method.getPath();
            l.debug("response status: {}", resStatus);
            l.debug("response path: {}", resPath);
//			if (resStatus == 200){
            if (!resPath.contains("noimage.png")) {
                l.info("++++++++++++++++++++++++++++++ Pass -- image src is accessible");
                result = true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- no image");
                t.takeScreenshot(d);
                result = false;
            }
        } else {
            //没有图片
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- image src is null");
            t.takeScreenshot(d);
            result = false;
        }
        return result;
    }


    /**
     * 查看专利分析图表
     *
     * @param tabIndex  Tab序号
     * @param linkIndex 链接序号
     * @param chatIndex 图表序号
     */
    public void func_view_analyzeChart(int tabIndex, int linkIndex, int chatIndex) {
        l.entry();
        this.link_analyzeTab_byIndex(tabIndex).click();
        l.info("Tab [{}]", this.link_analyzeTab_byIndex(tabIndex).getText());
        this.link_analyzeLink_byIndex(linkIndex).click();
        l.info("Link [{}]", this.link_analyzeLink_byIndex(linkIndex).getText());
        if (this.link_analyzeType_byIndex(chatIndex) != null) {
            this.link_analyzeType_byIndex(chatIndex).click();
        }
        l.exit();
    }

    /**
     * 根据索引点击专利分析tab
     *
     * @param tabIndex
     */
    public void func_click_analyzeTab_byIndex(int tabIndex) {
        l.entry();
        this.link_analyzeTab_byIndex(tabIndex).click();
        l.exit();
    }

    /**
     * 获取当前分析Tab下的链接数量
     *
     * @return
     */
    public int func_get_linkNumOfCurrentTab() {
        l.entry();
        WebElement currentTab = null;
        List<WebElement> tabs = d.findElement(By.className("analyze-wrap")).findElements(By.className("category"));
        for (WebElement tab : tabs) {
            if (tab.isDisplayed()) {
                currentTab = tab;
                l.debug("Link group is found");
                break;
            }
        }
        if (currentTab == null) {
            l.debug("Link group is not found");
            return 0;
        }
        return currentTab.findElements(By.className("analysis-link")).size();
    }

    /**
     * 验证专利分析结果表格有数据
     *
     * @return
     */
    public boolean func_verify_analyzeTable() {
        l.entry();
        try {
            if (!this.doesExist(By.className("data-table"))) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Analyze data table does not exist");
            }
            if (this.table_analyzeTable().findElements(By.tagName("tr")).size() > 1) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Data exist in analyze data table");
                return true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Analyze data table is empty");
                t.takeScreenshot(d);
                return false;
            }
        } catch (Exception e) {
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 点击专利分析列表中的第一个链接，验证是否跳转到搜索结果页
     *
     * @return 是否点击成功
     */
    public boolean func_click_firstLinkOnAnalyzeTable() {
        l.entry();
        List<WebElement> links = this.table_analyzeTable().findElements(By.tagName("a"));
        List<WebElement> links_des = new ArrayList<WebElement>();
        //去掉隐藏链接
        for (WebElement link : links) {
            if (link.isDisplayed() && (!link.getText().equals("0"))) {
                links_des.add(link);
            }
        }
        if (links_des.size() > 0) {
            links_des.get(0).click();
            l.info("link url: {}", links_des.get(0).getAttribute("href"));
            return true;
        } else {
            l.warn("Can not find any link in table");
            t.takeScreenshot(d);
            l.info("Select all and refresh");
            this.input_selectAll_analysis().click();
            this.input_refresh_analysis().click();
            return false;
        }
    }

    /**
     * 点击最近查询tab
     */
    public void func_click_recentTab() {
        l.entry();
        this.link_sidebarTab_byIndex(1).click();
        l.exit();
    }

    /**
     * 点击过滤tab
     */
    public void func_click_refineTab() {
        l.entry();
        this.link_sidebarTab_byIndex(0).click();
        l.exit();
    }

    /**
     * 验证最近搜索列表数量是否满足期望
     *
     * @param exp_num
     * @return
     */
    public boolean func_verify_recentSearchNumber(int exp_num) {
        l.entry();
        int act_num = d.findElement(By.className("recent-search-list")).findElements(By.tagName("li")).size();
        l.info("Expected recent search list number is {}", exp_num);
        l.info("Actual recent search list number is {}", act_num);
        if (act_num == exp_num) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Recent search list number is correct");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Recent search list number is incorrect");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 通过索引点击view
     */
    public void func_click_view_byIndex(int index) {
        l.entry();
        this.link_viewType_byIndex(index).click();
        l.exit();
    }

    /**
     * 切换视图
     *
     * @param rel -- tablelist, standard, flipit, thumb, analysis
     */
    public void func_click_view_byRel(String rel) {
        l.entry();
        this.link_viewType_byRel(rel).click();
        l.exit();
    }

    /**
     * 点击列表中的第一个专利
     */
    public void func_click_firstPatent_flipitView() {
        l.entry();
        this.ul_patentList_flipItView().findElements(By.className("patents")).get(0).click();
        l.exit();
    }

    /**
     * 创建 email alert -- 只指定名字，其它默认
     *
     * @return email alert name
     */
    public String func_create_emailAlert_simple() {
        l.entry();
        String emailAlertName = String.format("%s", System.currentTimeMillis());
        //========== update ==========
//		this.getElementById("name").sendKeys(emailAlertName);
//		this.getElementById("savealert").click();
        //========== update end ==========

        //========== update ==========
        this.getElementByName("title").sendKeys(emailAlertName);
        d.findElement(By.cssSelector(".btn-26.primary")).click();
        //========== update end ==========
        return emailAlertName;
    }

//	/**
//	 * 将CSV数据转换为list
//	 * @param lines
//	 * @return
//	 */
//	public List<HashMap<String, String>> func_convertCSVData(ArrayList<String> lines){
//		l.entry();
//		
//	}

    /**
     * 创建邮件提醒--全字段
     *
     * @param title
     * @param status
     * @param frequency
     * @param expirationDate
     * @param content
     * @return 键值对
     */
    public HashMap<String, String> func_create_emailAlert_complex(String title, String status, String frequency, String expirationDate, String content) {
        l.entry();
        HashMap<String, String> returnHash = new HashMap<String, String>();
        //名称
        String emailAlertName = String.format("%s %s", title, System.currentTimeMillis());
        this.getElementById("name").clear();
        this.getElementById("name").sendKeys(emailAlertName);
        returnHash.put("title", emailAlertName);
        //搜索
        returnHash.put("query", this.getElementById("alertq").getAttribute("value"));
        //频率
        if (frequency.equals("Sunday") || frequency.equals("Monday") || frequency.equals("Tuesday") || frequency.equals("Wednesday") || frequency.equals("Thursday") || frequency.equals("Friday") || frequency.equals("Saturday")) {
            this.getElementById("freq_weekly").click();
            Select select_w = new Select(this.getElementById("choiceweek"));
            select_w.selectByVisibleText(frequency);
        } else {
            this.getElementById("freq_monthly").click();
            Select select_m = new Select(this.getElementById("choicemonth"));
            select_m.selectByVisibleText(frequency);
        }
        returnHash.put("frequency", frequency);
        //过期日期
        if (expirationDate.equals("In 1 month")) {
            this.getElementById("expiration0").click();
        } else if (expirationDate.equals("In 3 months")) {
            this.getElementById("expiration1").click();
        } else if (expirationDate.equals("Never expire")) {
            this.getElementById("expiration2").click();
        }
        returnHash.put("expirationDate", expirationDate);
        //内容
        Select select_c = new Select(this.getElementById("sendtype"));
        select_c.selectByVisibleText(content);
        returnHash.put("content", content);
        //状态
        returnHash.put("status", status);
        if (status.equals("Inactive")) {
            this.getElementById("savealert").click();
        } else if (status.equals("Active")) {
            this.getElementById("activatealert").click();
        }
        return returnHash;
    }

    /**
     * 将搜索保存到自建库
     *
     * @param cdbName  -- 自建库名称
     * @param position -- 保存位置
     * @throws InterruptedException
     */
    public void func_addToCDB(String queryName, String cdbName, String position) throws InterruptedException {
        l.entry();
        WebElement div_dialog = d.findElement(By.id("query_add_to_cbd"));
        //name
        div_dialog.findElement(By.className("query_name")).sendKeys(queryName);
        //addto
//		cdbName = String.format("%s %s", cdbName, System.currentTimeMillis());
        boolean cdbExist = false; //指定cdb是否已经存在
        Select cdbList = new Select(div_dialog.findElement(By.className("cbd_folder")));
        for (WebElement op : cdbList.getOptions()) {
            if (op.getText().equals(cdbName)) {
                l.info("CDB named [{}] exists", cdbName);
                cdbList.selectByVisibleText(cdbName);
                cdbExist = true;
                break;
            }
        }
        //如果cdb不存在，则新建
        if (!cdbExist) {
            cdbList.selectByVisibleText("Create a new database");
            div_dialog.findElement(By.id("db_more")).sendKeys(cdbName);
        }
        //position
        Select posList = new Select(div_dialog.findElement(By.className("cbd_query")));
        posList.selectByVisibleText(position);
        //保存
        div_dialog.findElement(By.className("add-query-submit")).click();
//		return cdbName;
    }

    /**
     * 保存搜索--随机名称
     *
     * @param name--名称前缀
     * @return -- hashtable -- name:名字，string:字符串
     */
    public HashMap<String, String> func_save_query(String name) {
        l.entry();
        HashMap<String, String> returnHash = new HashMap<String, String>();
        String queryName = String.format("%s %s", name, System.currentTimeMillis());
        this.input_queryName_saveQueryDialog().clear();
        this.input_queryName_saveQueryDialog().sendKeys(queryName);
        String queryString = this.input_queryString_saveQueryDialog().getAttribute("value");
        this.link_saveQuery_saveQueryDialog().click();
        returnHash.put("name", queryName);
        returnHash.put("string", queryString);
        return returnHash;
    }

    /**
     * 保存搜索--随机名称，指定搜索语句
     *
     * @param name--名称
     * @param query--搜索语句
     * @return 搜索名称
     */
    public String func_save_query(String name, String query) {
        l.entry();
        String queryName = String.format("%s %s", name, System.currentTimeMillis());
        this.input_queryName_saveQueryDialog().clear();
        this.input_queryName_saveQueryDialog().sendKeys(queryName);
        this.input_queryString_saveQueryDialog().clear();
        this.input_queryString_saveQueryDialog().sendKeys(query);
        this.link_saveQuery_saveQueryDialog().click();
        return queryName;
    }

    /**
     * 滚动到顶端
     */
    public void func_scrollToTop_flipitView() {
        l.entry();
        WebElement e = this.ul_patentList_flipItView().findElements(By.className("patents")).get(0);
        e.sendKeys(Keys.HOME);
        l.exit();
    }

    /**
     * 滚动到底端
     */
    public void func_scrollToBottem_flipitView() {
        l.entry();
        WebElement e = this.ul_patentList_flipItView().findElements(By.className("patents")).get(0);
        e.sendKeys(Keys.END);
        l.exit();
    }

    /**
     * 点击页码
     *
     * @param index
     */
    public void func_click_pageNum(int index) {
        l.entry();
        this.link_pageNum(index).click();
        l.exit();
    }

    /**
     * 点击页码--standard view
     *
     * @param index
     */
    public void func_click_pageNum_standardView(int index) {
        l.entry();
        this.link_pageNum_standardView(index).click();
        l.exit();
    }

    /**
     * 点击末页链接
     */
    public void func_click_theLastPageNum() {
        l.entry();
        this.link_theLastPage().click();
        l.exit();
    }

    /**
     * 根据索引勾选专利
     *
     * @param index
     */
    public void func_click_patentCheckbox_byIndex(int index) {
        l.entry();
        WebElement checkbox = this.table_patentList_tableView().findElements(By.tagName("tr")).get(index).findElement(By.className("patent-checkbox"));
        //如果未被选中，则选择
        if (!this.table_patentList_tableView().findElements(By.tagName("tr")).get(index).getAttribute("class").contains("selected")) {
            checkbox.click();
        }
        l.exit();
    }


    /**
     * 根据索引获得PN号
     *
     * @param index
     * @return
     */
    public String func_GetPatentPN_byIndex(int index) {
        l.entry();
        WebElement checkbox = this.table_patentList_tableView().findElements(By.tagName("tr")).get(index).findElement(By.className("patent-checkbox"));
        WebElement pn = this.table_patentList_tableView().findElements(By.tagName("tr")).get(index).findElement(By.className("name-link"));
        //如果未被选中，则选择
        if (!this.table_patentList_tableView().findElements(By.tagName("tr")).get(index).getAttribute("class").contains("selected")) {
            checkbox.click();
        }
        l.exit();
        return pn.getText();
    }

    /**
     * 根据索引勾选专利--standard view
     *
     * @param index
     */
    public void func_click_patentCheckbox_byIndex_standardView(int index) {
        l.entry();
        WebElement checkbox = this.table_patentList_standardView().findElements(By.className("patents")).get(index).findElement(By.className("patent-checkbox"));
        //如果未被选中，则选择
        if (!checkbox.getAttribute("class").contains("selected")) {
            checkbox.click();
        }
        l.exit();
    }

    /**
     * 点击更多搜索历史
     */
    public void func_click_moreSearchHistory() {
        l.entry();
        this.link_moreSearchHistory().click();
        l.exit();
    }

    /**
     * 根据序号点击过滤条件--发明者姓名
     *
     * @param index
     * @return 过滤条件文字
     */

//	public  void func_click_filter_assigneename(int index){
//		l.entry();
//		this.link_filtercondition(0,index).click();
//		l.exit();
//	}
    public String func_click_filtercondition_AN_S_byIndex(int index) {
        l.entry();
        this.link_filtercondition(0, index).click();
        return this.link_filtercondition(0, index).getText();
    }

    /**
     * 根据序号点击过滤条件--申请年份
     *
     * @param index
     * @return 过滤条件文字
     */
    public String func_click_filtercondition_APD_YEAR_byIndex(int index) {
        l.entry();
        this.link_filtercondition(2, index).click();
        return this.link_filtercondition(2, index).getText();
    }


    //filter--inventor name
    public String func_click_filtercondition_inventorNameByIndex(int index) {
        l.entry();
        this.link_filtercondition(3, index).click();
        return this.link_filtercondition(3, index).getText();
    }

    //filter-international classification
    public String func_click_filtercondition_internationalClassificationByIndex(int index) {
        l.entry();
        this.link_filtercondition(4, index).click();
        return this.link_filtercondition(4, index).getText();
    }

    //filter-locarno classification

    public String func_click_filtercondition_locarnolClassificationByIndex(int index) {
        l.entry();
        this.link_filtercondition(5, index).click();
        return this.link_filtercondition(5, index).getText();
    }

    //filter-locarno classification

    public String func_click_filtercondition_legalStatusByIndex(int index) {
        l.entry();
        this.link_filtercondition(6, index).click();
        return this.link_filtercondition(6, index).getText();
    }

    /**
     * 填写过滤条件编辑框
     *
     * @param query
     */
    public void func_input_refineQuery(String query) {
        l.entry();
        this.input_binary_editor().sendKeys(query);
        l.exit();
    }

    /**
     * 粘贴文字到搜索框
     *
     * @throws AWTException
     */
    public void func_paste_queryEditbox() throws AWTException {
        l.entry();
        this.input_query().clear();
        this.input_query().click();
        this.func_paste();
        l.exit();
    }

    /**
     * 点击过滤按钮
     */
    public void func_click_refineLink() {
        l.entry();
        this.link_refine().click();
        l.exit();
    }

    /**
     * 点击复制到剪贴板链接
     */
    public void func_click_copyLink() {
        l.entry();
        this.link_copy().click();
//		act = new Actions(d);
//		act.moveToElement(this.link_copy()).click(this.link_copy()).perform();
        l.exit();
    }

    /**
     * 点击清除过滤条件按钮
     */
    public void func_click_clearRefineLink() {
        l.entry();
        this.link_clearRefine().click();
        l.exit();
    }

    /**
     * 验证期望发明者姓名是否存在于搜索结果列表
     *
     * @param exp_value
     * @return
     */
    public boolean func_verify_assigneeNameExistInDataTable(String exp_value) {
        l.entry();
        l.info("Expected assignee name: {}", exp_value);
        List<HashMap<String, String>> tableData = this.getTableData(this.table_patentList_tableView());
        boolean matchFlag = false;
        for (HashMap<String, String> row : tableData) {
            String act_value = row.get("Assignee Name");
            l.debug("Current assignee name: {}", act_value);
            if (act_value.contains(exp_value)) {
                l.info("Data found");
                matchFlag = true;
                break;
            }
        }
        if (matchFlag == true) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Assignee name exists in patent list");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Assignee name does not exist in patent list");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证期望申请年份是否存在于搜索结果列表
     *
     * @param exp_value
     * @return
     */
    public boolean func_verify_applicationYearExistInDataTable(String exp_value) {
        l.entry();
        l.info("Expected assignee name: {}", exp_value);
        List<HashMap<String, String>> tableData = this.getTableData(this.table_patentList_tableView());
        boolean matchFlag = false;
        for (HashMap<String, String> row : tableData) {
            String act_value = row.get("Application Date");
            l.debug("Current Application Date: {}", act_value);
            if (act_value.contains(exp_value)) {
                l.info("Data found");
                matchFlag = true;
                break;
            }
        }
        if (matchFlag == true) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Application year exists in patent list");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Application year does not exist in patent list");
            t.takeScreenshot(d);
            return false;
        }
    }

    //----Check filter inventor name----
    public boolean func_verify_inventorNameExistInDataTable(String exp_value) {
        l.entry();
        l.info("Expected inventor name: {}", exp_value);
        List<HashMap<String, String>> tableData = this.getTableData(this.table_patentList_tableView());
        boolean matchFlag = false;
        for (HashMap<String, String> row : tableData) {
            String act_value = row.get("Inventor Name");
            l.debug("Current inventor name: {}", act_value);
            if (act_value.contains(exp_value)) {
                l.info("Data found");
                matchFlag = true;
                break;
            }
        }
        if (matchFlag == true) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Inventor name exists in patent list");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- inventor name does not exist in patent list");
            t.takeScreenshot(d);
            return false;
        }
    }

    //--Check filter internationalClassification-----

    public boolean func_verify_internationalClassificationInDataTable(String exp_value) {
        l.entry();
        l.info("Expected International Classification: {}", exp_value);
        List<HashMap<String, String>> tableData = this.getTableData(this.table_patentList_tableView());
        boolean matchFlag = false;
        for (HashMap<String, String> row : tableData) {
            String act_value = row.get("International Classification");
            l.debug("Current International Classification: {}", act_value);
            if (act_value.contains(exp_value)) {
                l.info("Data found");
                matchFlag = true;
                break;
            }
        }
        if (matchFlag == true) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Inventor name exists in patent list");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- inventor name does not exist in patent list");
            t.takeScreenshot(d);
            return false;
        }
    }

    //--Check filter locarno classification
    public boolean func_verify_locarnoClassificationInDataTable(String exp_value) {
        l.entry();
        l.info("Expected locarno Classification: {}", exp_value);
        List<HashMap<String, String>> tableData = this.getTableData(this.table_patentList_tableView());
        boolean matchFlag = false;
        for (HashMap<String, String> row : tableData) {
            String act_value = row.get("Locarno Classification");
            l.debug("Current locarno Classification: {}", act_value);
            if (act_value.contains(exp_value)) {
                l.info("Data found");
                matchFlag = true;
                break;
            }
        }
        if (matchFlag == true) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Inventor name exists in patent list");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- inventor name does not exist in patent list");
            t.takeScreenshot(d);
            return false;
        }
    }

    //--Check filter legal status
    public boolean func_verify_legalstatusInDataTable(String exp_value) {
        l.entry();
        l.info("Expected Legal Status (CN): {}", exp_value);
        List<HashMap<String, String>> tableData = this.getTableData(this.table_patentList_tableView());
        boolean matchFlag = false;
        for (HashMap<String, String> row : tableData) {
            String act_value = row.get("Legal Status (CN)");
            l.debug("Current Legal Status (CN): {}", act_value);
            if (act_value.contains(exp_value)) {
                l.info("Data found");
                matchFlag = true;
                break;
            }
        }
        if (matchFlag == true) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Inventor name exists in patent list");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- inventor name does not exist in patent list");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证期望数据是否存在于专利列表的指定列中
     *
     * @param col
     * @param exp_value
     * @return
     * @throws ParseException
     */
    public boolean func_verify_valueExistInDataTable(String col, String exp_value) throws ParseException {
        l.entry();
        String key = null;
        if (col.toLowerCase().equals("pn")) {
            key = "Publication Number";
        } else if (col.toLowerCase().equals("apn")) {
            key = "Application Number";
        } else if (col.toLowerCase().equals("title")) {
            key = "Title";
        } else if (col.toLowerCase().equals("an")) {
            key = "Assignee Name";
        } else if (col.toLowerCase().equals("an_add")) {
            key = "Assignee Address";
        } else if (col.toLowerCase().equals("an_st")) {
            key = "Standardized Assignee";
        } else if (col.toLowerCase().equals("in")) {
            key = "Inventor Name";
        } else if (col.toLowerCase().equals("in_st")) {
            key = "Standardized Inventor";
        } else if (col.toLowerCase().equals("at")) {
            key = "Attorney Name";
        } else if (col.toLowerCase().equals("atc")) {
            key = "Agency";
        } else if (col.toLowerCase().equals("ipc")) {
            key = "International Classification";
        } else if (col.toLowerCase().equals("loc")) {
            key = "Locarno Classification";
        } else if (col.toLowerCase().equals("upc")) {
            key = "US Classification";
        } else if (col.toLowerCase().equals("apd")) {
            key = "Application Date";
        } else if (col.toLowerCase().equals("pbd")) {
            key = "Publication Date";
        }
        l.info("Expected column: {}", key);
        l.info("Expected value: {}", exp_value);
        WebElement table = this.table_patentList_tableView();
        if (table == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Can not find patent list table");
            t.takeScreenshot(d);
            return false;
        }
        if (exp_value == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Expected value is null");
            t.takeScreenshot(d);
            return false;
        }
        List<HashMap<String, String>> tableData = this.getTableData(table);
        boolean matchFlag = false;
        for (HashMap<String, String> row : tableData) {
            String act_value = row.get(key);
            l.debug("Current [{}]: [{}]", key, act_value);

            if (key.equals("Application Date") || key.equals("Publication Date")) {
                //如果比较字段为日期	
                //转换期望值为日期类型
                Calendar exp_cal = Calendar.getInstance();
                DateFormat exp_df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                Date exp_date = exp_df.parse(exp_value);
                exp_cal.setTime(exp_date);
                //转换实际值为日期类型
                Calendar act_cal = Calendar.getInstance();
                DateFormat act_df = new SimpleDateFormat("yyyy-MM-dd");
                Date act_date = act_df.parse(act_value);
                act_cal.setTime(act_date);
                //比较期望和实际日期
                if (exp_cal.equals(act_cal)) {
                    l.info("Data found");
                    matchFlag = true;
                    break;
                }
            } else {
                //如果比较字段为字符串
                if (act_value.contains(exp_value)) {
                    l.info("Data found");
                    matchFlag = true;
                    break;
                }
            }
        }
        if (matchFlag == true) {
            l.info("++++++++++++++++++++++++++++++ Pass -- [{}] exists in patent list", key);
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [{}] does not exist in patent list", key);
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证期望数据是否不存在于专利列表的指定列中
     *
     * @param col
     * @param exp_value
     * @return
     */
    public boolean func_verify_valueNotExistInDataTable(String col, String exp_value) {
        l.entry();
        String key = null;
        if (col.toLowerCase().equals("pn")) {
            key = "Publication Number";
        } else if (col.toLowerCase().equals("apn")) {
            key = "Aplication Number";
        } else if (col.toLowerCase().equals("title")) {
            key = "Title";
        } else if (col.toLowerCase().equals("an")) {
            key = "Assignee Name";
        } else if (col.toLowerCase().equals("an_add")) {
            key = "Assignee Address";
        } else if (col.toLowerCase().equals("an_st")) {
            key = "Standardized Assignee";
        } else if (col.toLowerCase().equals("in")) {
            key = "Inventor Name";
        } else if (col.toLowerCase().equals("in_st")) {
            key = "Standardized Inventor";
        } else if (col.toLowerCase().equals("at")) {
            key = "Attorney Name";
        } else if (col.toLowerCase().equals("atc")) {
            key = "Agency";
        } else if (col.toLowerCase().equals("ipc")) {
            key = "International Classification";
        } else if (col.toLowerCase().equals("loc")) {
            key = "Locarno Classification";
        } else if (col.toLowerCase().equals("upc")) {
            key = "US Classification";
        }
        l.info("Expected column: {}", key);
        l.info("Expected value: {}", exp_value);
        WebElement table = this.table_patentList_tableView();
        if (table == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Can not find patent list table");
            t.takeScreenshot(d);
            return false;
        }
        if (exp_value == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Expected value is null");
            t.takeScreenshot(d);
            return false;
        }
        List<HashMap<String, String>> tableData = this.getTableData(table);
        boolean matchFlag = false;
        for (HashMap<String, String> row : tableData) {
            String act_value = row.get(key);
            l.debug("Current [{}]: [{}]", key, act_value);
            if (act_value.contains(exp_value)) {
                l.info("Data found");
                matchFlag = true;
                break;
            }
        }
        if (matchFlag == true) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [{}] exists in patent list", key);
            t.takeScreenshot(d);
            return false;
        } else {
            l.info("++++++++++++++++++++++++++++++ Pass -- [{}] does not exist in patent list", key);
            return true;
        }
    }

    /**
     * 验证过滤类别数量是否满足期望
     *
     * @param exp_num
     * @return
     */
    public boolean func_verify_filterFieldsNum(int exp_num) {
        l.entry();
        int act_num = this.div_filterList().findElements(By.className("cat")).size();
        l.info("Expected filter fields num is: {}", exp_num);
        l.info("Actual filter fields num is: {}", act_num);
        if (exp_num == act_num) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Filter fields num is correct");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Filter fields num is incorrect");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 根据索引选择排序方法
     *
     * @param index
     */
    public void func_select_sortType_byIndex(int index) {
        l.entry();
        act = new Actions(d);
        act.moveToElement(this.span_sortType());
        act.perform();
        if (this.link_sortType_byIndex(index).isDisplayed()) {
            this.link_sortType_byIndex(index).click();
        }
        l.exit();
    }

    /**
     * 验证查询是否返回数据(表格视图)
     *
     * @return
     * @throws InterruptedException
     */
    public boolean func_verify_searchResultExist_tableView() throws InterruptedException {
        l.entry();
//		Thread.sleep(5000);
        //等待搜索结果出现
        boolean isTableDisplayed = false;
        int i = 0;
        while ((!isTableDisplayed) && (i < 2)) {
            try {
                this.table_patentList_tableView().findElement(By.tagName("tr"));
                isTableDisplayed = true;
            } catch (Exception e) {
                l.info("Waiting for search result table...");
                Thread.sleep(1000);
                i++;
            }
        }
        //判断结果
        int rowNum = 0;
        try {
            rowNum = this.table_patentList_tableView().findElements(By.tagName("tr")).size() - 1;
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent list table does not exist in table view");
            t.takeScreenshot(d);
            return false;
        }
        if (rowNum < 0) {
            rowNum = 0;
        }
        l.info("Result row number is {}", rowNum);
        if (rowNum == 0) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search result does not exist in table view");
            t.takeScreenshot(d);
            return false;
        } else {
            l.info("++++++++++++++++++++++++++++++ Pass -- Search result exists in table view");
            return true;
        }
    }

    /**
     * 验证查询是否返回数据（标准视图）
     *
     * @return
     */
    public boolean func_verify_searchResultExist_standardView() {
        l.entry();
        try {
            this.div_patentList_standardView();
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent list table does not exist in standard view");
            t.takeScreenshot(d);
            return false;
        }
        int rowNum = this.div_patentList_standardView().findElements(By.cssSelector(".searchdata.patents")).size() - 1;
        l.info("Result row number is {}", rowNum);
        if (rowNum == 0) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search result does not exist in standard view");
            t.takeScreenshot(d);
            return false;
        } else {
            l.info("++++++++++++++++++++++++++++++ Pass -- Search result exists in standard view");
            return true;
        }
    }

    /**
     * 验证查询是否返回数据（缩略视图）
     *
     * @return
     */
    public boolean func_verify_searchResultExist_thumbnailView() {
        l.entry();
        try {
            this.div_patentList_thumbnailView();
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent list table does not exist in thumbnail view");
            t.takeScreenshot(d);
            return false;
        }
        int rowNum = this.div_patentList_thumbnailView().findElements(By.cssSelector(".thumbview.patentimgdiv")).size() - 1;
        l.info("Result row number is {}", rowNum);
        if (rowNum == 0) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search result does not exist in thumbnail view");
            t.takeScreenshot(d);
            return false;
        } else {
            l.info("++++++++++++++++++++++++++++++ Pass -- Search result exists in thumbnail view");
            return true;
        }
    }

    /**
     * 验证期望视图是否被选中
     *
     * @param rel
     * @return
     */
    public boolean func_verify_viewIsSelected(String rel) {
        l.entry();
        WebElement viewLink = this.link_viewType_byRel(rel);
        if (viewLink.getAttribute("class").contains("selected")) {
            l.info("++++++++++++++++++++++++++++++ Pass -- View [{}] is selected", rel);
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search result does not exist in thumbnail view");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证期望排序是否被选中
     *
     * @param index
     * @return
     */
    public boolean func_verify_sortIsSelected(int index) {

        l.entry();
        String expected = "";
        switch (index) {
            case 0:
                expected = "Most Relevant";
                break;
            case 1:
                expected = "Latest Application";
                break;
            case 2:
                expected = "Oldest Application";
                break;
            case 3:
                expected = "Latest Publication";
                break;
            case 4:
                expected = "Oldest Publication";
                break;

        }

        String result = this.span_sortType().getText();

        if (expected.equals(result)) {
            l.info("++++++++++++++++++++++++++++++ Pass");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证各个视图的设置项是否正确（只检查项是否存在，不检查项的值）
     *
     * @param rel
     * @return
     */
    public boolean func_verify_searchPreferences(String rel) {
        l.entry();
        boolean result = true;
        this.link_userSetting().click();
        //有两个tab的情况，点击第二个tab
        if (rel.equals("tablelist") || rel.equals("standard")) {
            d.findElement(By.id("search_custom_field")).findElement(By.className("m-tab")).findElements(By.tagName("a")).get(1).click();
        }
        //获取期望表头列表
        List<String> exp_ths = new ArrayList<String>();
        exp_ths.add("Result display"); //0,1,2,3,4
        exp_ths.add("Default display"); //0,1,2,3,4
        if (!rel.equals("analysis")) {
            exp_ths.add("Sort by"); //0,1,2,4
            exp_ths.add("Records per page"); //0,1,2,4
            exp_ths.add("English Stemming"); //0,1,2,4
        }
        //获取实际表头列表
        List<String> act_ths = new ArrayList<String>();
        for (WebElement th : this.table_searchPreferences().findElements(By.tagName("th"))) {
            act_ths.add(th.getText());
        }
        //遍历期望列表，验证实际列表
        for (String exp_th : exp_ths) {
            boolean matchFlag = false;
            l.info("Verifying [{}]", exp_th);
            for (String act_th : act_ths) {
                if (exp_th.equals(act_th)) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- Item exists");
                    matchFlag = true;
                    result &= true;
                    break;
                }
            }
            if (!matchFlag) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Item does not exist");
                t.takeScreenshot(d);
                result &= false;
            }
        }
        if (d.findElement(By.className("cancel")).isDisplayed()) {
            d.findElement(By.className("cancel")).click();
        }
        return result;
    }

    /**
     * 验证搜索结果页数据是否为usa数据库的
     *
     * @return
     */
    public boolean func_verify_searchResult_tableView_usa() {
        l.entry();
        boolean result = true;
        List<HashMap<String, String>> data = this.getTableData(this.table_patentList_tableView());
        for (HashMap<String, String> row : data) {
            boolean tempResult = true;
            //每行数据
            //检查是否以US开头
            String pn = null;
            if (row.get("Publication Number") != null) {
                pn = row.get("Publication Number");
            } else {
                pn = row.get("公开(公告)号");
            }
            tempResult &= pn.startsWith("US");
            //检查倒数第二个字符是否为A
            String pn_sub = pn.substring(0, pn.length() - 1);
            tempResult &= pn_sub.endsWith("A");
            //判断结果
            if (tempResult) {
                l.info("++++++++++++++++++++++++++++++ Pass -- PN [{}] is correct", pn);
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- PN [{}] is incorrect", pn);
            }
            result &= tempResult;
        }
        //总结测试结果
        if (result) {
            l.info("++++++++++++++++++++++++++++++ Pass -- All pns are correct");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- There are incorrect pns");
            t.takeScreenshot(d);
        }
        return result;
    }

    /**
     * 验证搜索结果是否正确
     *
     * @return
     * @throws Exception
     */
    public boolean func_verify_searchResultNum() throws Exception {
        l.entry();
        int num_refine = this.func_get_refineResultNumber();
        int num_result = this.func_get_searchResultNumber_byGroup();
        int num_bottom = this.func_get_searchResultNumber_bottom();
        String output = String.format("[Total result for all]: [%d]. [Total result on bottom]: [%d], [Total refine result]: [%d]", num_result, num_bottom, num_refine);
        //总结测试结果
        if ((num_refine == num_result) && (num_refine == num_bottom)) {
            l.info("++++++++++++++++++++++++++++++ Pass -- {}", output);
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- {}", output);
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证查询是否返回数据（分析视图）
     *
     * @return
     */
    public boolean func_verify_searchResultExist_analyzeView() {
        l.entry();
        boolean result = true;
        //验证图表application_trends
        if (this.svg_application_trends() != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Search result exists in analyze view (application_trends)");
            result &= true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search result does not exist in analyze view (application_trends)");
            t.takeScreenshot(d);
            result &= false;
        }
        //验证图表public_trends
        if (this.svg_public_trends() != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Search result exists in analyze view (public_trends)");
            result &= true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search result does not exist in analyze view (public_trends)");
            t.takeScreenshot(d);
            result &= false;
        }
        //验证图表svg_ipc_tree_map
        if (this.svg_ipc_tree_map() != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Search result exists in analyze view (svg_ipc_tree_map)");
            result &= true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search result does not exist in analyze view (svg_ipc_tree_map)");
            t.takeScreenshot(d);
            result &= false;
        }
        //验证图表svg_jurisdictions
        if (this.svg_jurisdictions() != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Search result exists in analyze view (svg_jurisdictions)");
            result &= true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search result does not exist in analyze view (svg_jurisdictions)");
            t.takeScreenshot(d);
            result &= false;
        }
        //验证图表applicants
        if (this.svg_applicants() != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Search result exists in analyze view (applicants)");
            result &= true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search result does not exist in analyze view (applicants)");
            t.takeScreenshot(d);
            result &= false;
        }
        //验证图表inventors
        if (this.svg_inventors() != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Search result exists in analyze view (inventors)");
            result &= true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search result does not exist in analyze view (inventors)");
            t.takeScreenshot(d);
            result &= false;
        }
        return result;
    }

    /**
     * 验证查询是否返回数据（快速浏览视图）
     *
     * @return
     */
    public boolean func_verify_searchResultExist_flipItView() {
        l.entry();
        try {
            this.ul_patentList_flipItView();
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent list table does not exist in flipIt view");
            t.takeScreenshot(d);
            return false;
        }
        int rowNum = this.ul_patentList_flipItView().findElements(By.tagName("a")).size() - 1;
        l.info("Result row number is {}", rowNum);
        if (rowNum == 0) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search result does not exist in flipIt view");
            t.takeScreenshot(d);
            return false;
        } else {
            l.info("++++++++++++++++++++++++++++++ Pass -- Search result exists in flipIt view");
            return true;
        }
    }

    /**
     * 验证指定索引的专利的浮出摘要存在
     *
     * @return
     * @throws InterruptedException
     */
    public boolean func_verify_tipAbstractExist_byIndex(int index) throws InterruptedException {
        l.entry();
        WebElement link_pn;
        List<WebElement> trs = this.table_patentList_tableView().findElements(By.tagName("tr"));
        String abst = null;
        if (trs.size() > 0) {
            link_pn = trs.get(index).findElement(By.className("PN"));
            l.info("PN: {}", link_pn.getText());
            act = new Actions(d);
            act.moveToElement(link_pn);
            act.perform();
            Thread.sleep(5000);
            //这里很诡异，下载链接有一定几率不出现
            int i = 0;
            while (this.div_tipBox().isDisplayed() == false) {
                if (i < 60) {
                    l.debug("trying to get tip box...");
                    i++;
                } else {
                    l.debug("timeout");
                    break;
                }
            }
            abst = this.div_tipBox().getText();
            l.info("===== Abstract =====");
            l.info(abst);
            l.info("===== Abstract end =====");
        } else {
            l.warn("can not find PN link");
        }


        if (!abst.equals("")) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Abstract exists in tip box");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Abstract does not exist in tip box");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证Email对话框是否可见
     *
     * @return
     */
    public boolean func_verify_emailDialog() {
        l.entry();
        if (this.div_emailDialog().isDisplayed()) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Email dialog is displayed");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Email dialog is invisible");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证H键锁定浮出框
     *
     * @return
     * @throws InterruptedException
     */
    public boolean func_verify_H_key_thumbnailView() throws InterruptedException {
        l.entry();
        WebElement div = this.div_patentList_thumbnailView().findElement(By.className("thumbdata"));
        act = new Actions(d);
        act.moveToElement(div).perform();
        Thread.sleep(2000);
        div.sendKeys("H");
        Thread.sleep(2000);
        if (this.div_tipBox().getAttribute("class").contains("is-hold")) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Tip box is held after pressing H key");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Tip box is not held after pressing H key");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证是否全屏
     *
     * @return
     */
    public boolean func_verify_fullScreen_flipitView() {
        l.entry();
        if (this.link_exitFullScreen_flipitView().isDisplayed()) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Current view is full screen");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Current view is not full screen");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证指定PN的法律状态 -- 标准视图
     *
     * @param expLegalStatus
     * @param pn
     * @return
     */
    public boolean func_verify_legalStatusByPN(String expLegalStatus, String pn) {
        l.entry();
        String actLegalStatus = null; //实际法律状态
        WebElement div = this.div_patent_standardView(pn);
        if (div == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Can not find div for PN [{}]", pn);
            t.takeScreenshot(d);
            return false;
        } else {
            actLegalStatus = div.findElement(By.className("legal-tag")).getText();
            l.info("Expected legal status: [{}]", expLegalStatus);
            l.info("Actual legal status: [{}]", actLegalStatus);
        }
        if (expLegalStatus.equals(actLegalStatus)) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Legal status is correct");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Legal status is incorrect");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证专利按照指定字段排序
     *
     * @param sortCol  -- 0: 申请日期; 1: 公开日期
     * @param sortType -- 0: 倒序; 1: 顺序
     * @return
     * @throws Exception
     */
    public boolean func_verify_patentListOrder(int sortCol, int sortType, int verificationNum) throws Exception {
        l.entry();
        String sortColStr;
        String sortColStr_cn;
        //确定排序字段
        switch (sortCol) {
            case 0:
                sortColStr = "Application Date";
                sortColStr_cn = "申请日";
                break;
            case 1:
                sortColStr = "Publication Date";
                sortColStr_cn = "公开日";
                break;
            default:
                sortColStr = null;
                sortColStr_cn = null;
        }
        if (sortColStr == null) {
            l.error("Invalid sort column: {}", sortCol);
            return false;
        }
        List<HashMap<String, String>> patentListData = this.getTableData(this.table_patentList_tableView());
        List<String> dates = new ArrayList<String>();
        if (patentListData.size() > 0) {
            int i = 0;
            for (HashMap<String, String> row : patentListData) {
                if (i < verificationNum) {
                    if (row.get(sortColStr) != null) {
                        dates.add(row.get(sortColStr));
                    } else {
                        dates.add(row.get(sortColStr_cn));
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
        //验证排序
        boolean result = this.verifyListOrder(dates, sortType);
        if (result) {
            l.info("++++++++++++++++++++++++++++++ Pass -- [{}] is sorted correctly", sortColStr);
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [{}] is sorted incorrectly", sortColStr);
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证传入的list是否按照正确顺序排序
     *
     * @param list     -- 待验证的list
     * @param sortType -- 0: 倒序; 1: 顺序
     * @return
     * @throws Exception
     */
    public boolean verifyListOrder(List<String> list, int sortType) throws Exception {
        l.entry();
        if (list == null) {
            l.error("List is null");
            return false;
        }
        int listNum = list.size();
        if (listNum == 0) {
            l.error("List is empty");
            return false;
        }
        boolean result = true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf_bak = new SimpleDateFormat("yyyy-MM-dd");

        //如果需要验证是否为顺序，则先将list倒序排列，再验证是否倒序
        if (sortType == 1) {
            //需要验证是否顺序，则先倒序排列
            List<String> temp = new ArrayList<String>();
            for (int k = 0; k < listNum; k++) {
                temp.add(list.get(listNum - k - 1));
            }
            list = temp;
        }
        //验证是否倒序
        for (int i = 0; i < listNum; i++) {
            for (int j = i + 1; j < listNum; j++) {
                l.debug("Comparing [{}] and [{}]", list.get(i), list.get(j));
                try {
                    Date d1;
                    Date d2;
                    try {
                        d1 = sdf.parse(list.get(i));
                    } catch (Exception e) {
                        d1 = sdf_bak.parse(list.get(i));
                    }
                    try {
                        d2 = sdf.parse(list.get(j));
                    } catch (Exception e) {
                        d2 = sdf_bak.parse(list.get(i));
                    }
                    if (!d1.before(d2)) {
                        //Pass
                        l.debug("Pass");
                        result &= true;
                    } else {
                        //Fail
                        l.debug("Fail");
                        result &= false;
                        break;
                    }
                } catch (Exception e) {
                    l.error("Null date value");
                    result &= false;
                    break;
                }

            }
            if (result == false) {
                break;
            }
        }
        return result;
    }

    /**
     * 设置搜索选项
     *
     * @param resultDisplay -- 0,1,2,3 分别代表单选框的4个选项
     * @throws Exception
     */
    public boolean func_config_resultDisplay(int resultDisplay) throws Exception {
        l.entry();
        //点击用户设置
        this.link_userSetting().click();
        l.info("Set result display to option {}", resultDisplay);
        if (!this.doesExist(By.id("search_custom_field"))) {
            Thread.sleep(1500);
        }
        //点击搜索选项tab
        d.findElement(By.id("search_custom_field")).findElement(By.className("m-tab")).findElements(By.tagName("a")).get(1).click();
        //选择resultDisplay
        d.findElement(By.id("search_custom_field")).findElement(By.className("result-display")).findElements(By.name("search_mode")).get(resultDisplay).click();
        //点击提交
        d.findElement(By.id("search_custom_field")).findElement(By.className("submit-form")).click();
        Thread.sleep(10000); //等待页面刷新
        if (!this.doesExist(By.id("search_custom_field")) || (d.findElement(By.id("search_custom_field")).isDisplayed() == false)) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Result display is saved successfully");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- User setting dialog is still displayed");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * @param value 0:Off; 1:On
     * @return
     * @throws Exception
     */
    public boolean func_config_englishStemming(int value) throws Exception {
        l.entry();
        //点击用户设置
        this.link_userSetting().click();
        l.info("Set english stemming to option {}", value);
        if (!this.doesExist(By.id("search_custom_field"))) {
            Thread.sleep(1500);
        }
        //点击搜索选项tab
        d.findElement(By.id("search_custom_field")).findElement(By.className("m-tab")).findElements(By.tagName("a")).get(1).click();
        //选择english stemming
        d.findElement(By.id("search_custom_field")).findElement(By.className("stemming")).findElements(By.name("search_stemming")).get(value).click();
        //点击提交
        d.findElement(By.id("search_custom_field")).findElement(By.className("submit-form")).click();
        Thread.sleep(3000); //等待页面刷新
        if (!this.doesExist(By.id("search_custom_field")) || (d.findElement(By.id("search_custom_field")).isDisplayed() == false)) {
            l.info("++++++++++++++++++++++++++++++ Pass -- English Stemming is saved successfully");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- User setting dialog is still displayed");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 设置每页显示数量
     *
     * @param index -- 下拉框索引
     * @throws Exception
     */
    public boolean func_config_recordsPerPage(int index) throws Exception {
        l.entry();
        //点击用户设置
        this.link_userSetting().click();
        l.info("Set records per page to option {}", index);
        if (!this.doesExist(By.id("search_custom_field"))) {
            Thread.sleep(1500);
        }
        //点击搜索选项tab
        d.findElement(By.id("search_custom_field")).findElement(By.className("m-tab")).findElements(By.tagName("a")).get(1).click();
        //选择每页显示专利数
        Select s = new Select(d.findElement(By.id("search_custom_field")).findElement(By.name("defaultrows")));
        s.selectByIndex(index);
        //点击提交
        d.findElement(By.id("search_custom_field")).findElement(By.className("submit-form")).click();
        Thread.sleep(3000); //等待页面刷新
        if (!this.doesExist(By.id("search_custom_field")) || (d.findElement(By.id("search_custom_field")).isDisplayed() == false)) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Records per page is saved successfully");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- User setting dialog is still displayed");
            t.takeScreenshot(d);
            return false;
        }
    }

    /* 获取每页显示数量  index 为 0 1 2 3
    */
    public int func_get_globalSettingPageNumber(int index) {
        l.entry();
        this.link_userSetting().click();
        l.info("Set records per page to option {}", index);
        //点击搜索选项tab
        d.findElement(By.id("search_custom_field")).findElement(By.className("m-tab")).findElements(By.tagName("a")).get(1).click();
        //选择每页显示专利数
        Select s = new Select(d.findElement(By.id("search_custom_field")).findElement(By.name("defaultrows")));
        int number = Integer.valueOf(s.getOptions().get(index).getText());
        return number;
    }

    //获取结果页行号
    public int func_get_numberOfFirstLine(int trindex, int tdindex) {
        l.entry();
        String num = this.lineOfTableView(trindex).findElements(By.tagName("td")).get(tdindex).getText();
        int number = Integer.valueOf(num);
        return number;
    }

    /**
     * 设置默认排序
     *
     * @param index -- 下拉框索引
     * @throws Exception
     */
    public boolean func_config_sortBy(int index) throws Exception {
        l.entry();
        //点击用户设置
        this.link_userSetting().click();
        l.info("Set sort by to option {}", index);
        if (!this.doesExist(By.id("search_custom_field"))) {
            Thread.sleep(1500);
        }
        //点击搜索选项tab
        d.findElement(By.id("search_custom_field")).findElement(By.className("m-tab")).findElements(By.tagName("a")).get(1).click();
        //选择每页显示专利数
        Select s = new Select(d.findElement(By.id("search_custom_field")).findElement(By.name("defaultsort")));
        s.selectByIndex(index);
        //点击提交
        d.findElement(By.id("search_custom_field")).findElement(By.className("submit-form")).click();
        Thread.sleep(3000); //等待页面刷新
        if (!this.doesExist(By.id("search_custom_field")) || (d.findElement(By.id("search_custom_field")).isDisplayed() == false)) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Sort by is saved successfully");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- User setting dialog is still displayed");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 设置默认视图
     *
     * @param rel
     * @return
     * @throws Exception
     */
    public boolean func_config_defaultDisplay(String rel) throws Exception {
        l.entry();
        String option = null;
        if (rel.equals("tablelist")) {
            option = "tablelist";
        } else if (rel.equals("standard")) {
            option = "standard";
        } else if (rel.equals("thumb")) {
            option = "thumb";
        } else if (rel.equals("analysis")) {
            option = "analysis";
        } else if (rel.equals("flipit")) {
            option = "flipit";
        }
        //点击用户设置
        this.link_userSetting().click();
        l.info("Set default display to option {}", option);
        //如果不存在，则等待
        if (!this.doesExist(By.id("search_custom_field"))) {
            Thread.sleep(2000);
        }
        //点击搜索选项tab
        d.findElement(By.id("search_custom_field")).findElement(By.className("m-tab")).findElements(By.tagName("a")).get(1).click();
        //选择defaultDisplay
        Select s = new Select(d.findElement(By.id("search_custom_field")).findElement(By.className("default-viewtype")));
        s.selectByValue(option);
        //点击提交
        d.findElement(By.id("search_custom_field")).findElement(By.className("submit-form")).click();
        Thread.sleep(3500); //等待页面刷新
        if (!this.doesExist(By.id("search_custom_field")) || (d.findElement(By.id("search_custom_field")).isDisplayed() == false)) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Default display is saved successfully");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- User setting dialog is still displayed");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 配置显示字段
     *
     * @param fields_str -- 字符串，以逗号分隔
     * @return
     * @throws Exception
     */
    public boolean func_config_displayedFields(String fields_str) throws Exception {
        l.entry();
        if (fields_str == null) {
            l.error("The string parm is null");
            return false;
        }
        ArrayList<String> expFields = new ArrayList<String>();
        String[] fields_strs = fields_str.split(",");
        if (fields_strs.length == 0) {
            l.error("The format string parm is incorrect");
            return false;
        }
        for (String field_str : fields_strs) {
            expFields.add(field_str.trim());
        }
        boolean result = true;
        //点击用户设置
        this.link_userSetting().click();
        if (!this.doesExist(By.id("search_custom_field"))) {
            Thread.sleep(1500);
        }
        //先将右侧字段清空
        for (WebElement li : d.findElement(By.id("show_fields")).findElements(By.cssSelector(".right.ui-state-default"))) {
            act = new Actions(d);
            act.dragAndDrop(li, d.findElement(By.id("hidden_fields"))).perform();
        }
        //遍历期望字段集合，将没有选的字段，拖进右侧框内
        for (String field : expFields) {
            l.info("Checking field: [{}]", field);
            //检查数量是否达到上限
            if (Integer.parseInt(this.span_currentFieldsNum().getText()) >= Integer.parseInt(this.span_limitFieldsNum().getText())) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Can not drag any more field");
                result &= false;
                break;
            }
            //数量没有达到上限，才会拖拽
            boolean matchFlag = false;
            //查找匹配字段
            for (WebElement li : d.findElement(By.id("show_fields")).findElements(By.tagName("li"))) {
                if (field.equals(li.getAttribute("rel"))) {
                    matchFlag = true;
                    l.info("Field is found");
                    break;
                }
            }
            //如果没有找到，则拖进去
            if (!matchFlag) {
                l.info("Field not found, so drag it into shown list");
                //拖拽失败，则重试
                int i = 0;
                while (this.li_hiddenFields_byRel(field) != null) {
                    //尝试次数小于3，则尝试，否则报错
                    if (i < 3) {
                        l.info("Trying to drag field...");
                        WebElement leftLi = this.li_hiddenFields_byRel(field);
                        act = new Actions(d);
                        act.dragAndDrop(leftLi, d.findElement(By.id("show_fields"))).perform();
                        i++;
                    } else {
                        l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Drag failed");
                        result &= false;
                        break;
                    }
                }
                l.info("++++++++++++++++++++++++++++++ Pass -- Drag successfully");
            }
        }
        //点击提交
        d.findElement(By.id("search_custom_field")).findElement(By.className("submit-form")).click();
        Thread.sleep(3000); //等待页面刷新
        if (!this.doesExist(By.id("search_custom_field")) || (d.findElement(By.id("search_custom_field")).isDisplayed() == false)) {
            if (result) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Displayed fields is saved successfully");
                return true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Not all the fields are configured successfully");
                t.takeScreenshot(d);
                return false;
            }
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- User setting dialog is still displayed");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 导出分析结果
     *
     * @param type--0:jpg; 1:csv
     * @return 包含两个字符串的list，第一个为精确名称，第二个为精确名称+1分钟的名称，防止发生误差
     * @throws InterruptedException
     */
    public ArrayList<String> func_export_analysisData(int type) throws InterruptedException {
        l.entry();
        //计算文件名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HHmm");
//		Date d = new Date();
        Calendar c = Calendar.getInstance();
        //备用分钟数，防止误差
        Calendar c_bak = Calendar.getInstance();
        c_bak.add(Calendar.MINUTE, -1);
        Calendar c_bak_2 = Calendar.getInstance();
        c_bak_2.add(Calendar.MINUTE, 1);
        //根据小时数据，决定AM还是PM
        int hour = c.get(Calendar.HOUR_OF_DAY);
        String section = "";
        if ((hour - 12) < 0) {
            //如果小于12，则AM
            section = "AM";
        } else {
            //否则，PM
            section = "PM";
        }
        String fileName = String.format("%s%s", sdf.format(c.getTime()), section);
        String fileName_bak = String.format("%s%s", sdf.format(c_bak.getTime()), section);
        String fileName_bak_2 = String.format("%s%s", sdf.format(c_bak_2.getTime()), section);
        l.info("Exp file name contains: [{}]", fileName);
        //点击导出
        this.link_export_analysis().click();
        Thread.sleep(2000);
        WebElement link_export;
        switch (type) {
            case 0:
                link_export = this.link_export_analysis_jpg();
                break;
            case 1:
                link_export = this.link_export_analysis_csv();
                break;
            default:
                link_export = null;
        }
        link_export.click();
        //返回文件名
        ArrayList<String> fileNames = new ArrayList<String>();
        fileNames.add(fileName);
        fileNames.add(fileName_bak);
        fileNames.add(fileName_bak_2);
        return fileNames;
    }

    public boolean func_verify_csvData_analysis(ArrayList<HashMap<String, String>> pageData, ArrayList<HashMap<String, String>> csvData) throws Exception {
        l.entry();
        boolean result = true;
        //检查输入参数
        if ((pageData == null) || (pageData.size() == 0)) {
            l.error("Data of page is null");
            return false;
        }
        if ((csvData == null) || (csvData.size() == 0)) {
            l.error("Data of csv is null");
            return false;
        }
        //转换实际数据集中的key，因为实际数据中会有乱码，需要替换掉
        ArrayList<HashMap<String, String>> csvData_u = new ArrayList<HashMap<String, String>>();    //保存更新后的实际值数据集
        for (HashMap<String, String> act_row : csvData) {
            HashMap<String, String> temp_hash = new HashMap<String, String>();
//			String key_u = "";
            for (String key : act_row.keySet()) {
                String value_u = act_row.get(key);                                //需要保存的value
                if ((key.length() == 1)
                        || (key.contains("Assignee"))
                        || (key.contains("Inventor"))
                        || (key.contains("Application Date"))
                        || (key.contains("Publication Date"))
                        || (key.contains("International Classification"))
                        || (key.contains("Locarno Classification"))
                        || (key.contains("US Classification"))
//						|| (key.contains("Application Date"))
                        ) {
                    key = key.substring(1, key.length());
                }
                key = key.replace("Number of Patents", "Patent num");    //更新后的key
//				key_u = key_u.replace("�ｻｿ", "");
//				key_u = key_u.replace("锘�", "");
                temp_hash.put(key, value_u);
            }
            csvData_u.add(temp_hash);
        }
        //遍历期望数据
        for (HashMap<String, String> exp_row : pageData) {
            //对于期望数据的每一行，遍历实际数据，找到对应行
            //获取关键列（期望和实际数据）
            String exp_id = null;    //期望数据中，当前行的关键列的 value
            String key_id = null;    //关键列的 key
            for (String key : exp_row.keySet()) {
                exp_id = exp_row.get(key);
                key_id = key;
                break;
            }
            boolean matchFlag = false;
            for (HashMap<String, String> act_row : csvData_u) {
                //test--start
//				for(String key : act_row.keySet()){
//					l.info("exp key -- [{}] , act key -- [{}]", key_id, key);
////					l.info("value -- [{}]", act_row.get(key));
////					l.info("len: {}", key_id.length());
////					l.info("len 2 -- {}", key.length());
////					key = key.substring(1, key.length());
////					if(key_id.equals(key)){
////						l.info("equal");
////					}
//				}
                //test--end
                //如果找到匹配行，则逐一比较各个字段
                l.debug("Key column: [{}], exp value: [{}], act value: [{}]", key_id, exp_id, act_row.get(key_id));
                if (exp_id.equals(act_row.get(key_id))) {
                    l.info("Act row is found");
                    //遍历期望行的每个key，对比实际行中的对应值
                    for (String key : exp_row.keySet()) {
                        String exp_value = exp_row.get(key);
                        String act_value = act_row.get(key);
                        if (act_value == null) {
                            act_value = "";
                        }
                        String outputStr = String.format("Verify column [%s], exp value: [%s], act value: [%s]", key, exp_value, act_value);
                        if (exp_value.equals(act_value)) {
                            l.info("++++++++++++++++++++++++++++++ Pass -- {}", outputStr);
                        } else {
                            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- {}", outputStr);
                            result &= false;
                        }
                    }
                    matchFlag = true;
                    break;
                }
            }
            if (!matchFlag) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Row is not found");
                result &= false;
            }
        }
        return result;
    }

    /**
     * 确认assignee name 是否添加成功
     *
     * @return
     */
    public boolean func_verify_filterOptionAssigneeName(int index) {
        l.entry();
        try {
            this.filterOptionAssigneeName(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 确认ApplicationYear 是否添加成功
     *
     * @return
     */
    public boolean func_verify_filterOptionApplicationYear(int index) {
        l.entry();
        try {
            this.filterOptionApplicationYear(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * refine框点击and
     */
    public void func_click_buttonAnd() {
        l.entry();
        this.filterLogicWordAnd().click();
    }

    /**
     * refine框点击or
     */
    public void func_click_buttonOr() {
        l.entry();
        this.filterLogicWordOr().click();
    }
    //refine 框点击delete

    public void func_click_buttonDelete(int index) {
        l.entry();
        this.filterOptionDelete(index).click();
    }

    /**
     * 点击recent 内的query 并返回该query
     *
     * @param
     * @return
     */
    public String func_Click_RecentQueryLink(int index) {
        l.entry();
        String query = this.link_RecentQueryLink(index).getText();
        this.link_RecentQueryLink(index).click();
        return query;
    }

    //
    public int currentPageNum() throws InterruptedException {
        l.entry();
        int i = 0;
        while ((!d.findElement(By.className("page-number")).isDisplayed()) && (i < 10)) {
            l.info("Waiting for page number...");
            i++;
            Thread.sleep(1000);
        }
        int index = Integer.valueOf(this.currentPageNumber().getText());
        return index;

    }

    //输入页码并跳转
    public void jumpTo(String number) {
        this.link_JumpTo().click();
        this.inputBox_JumpTo().sendKeys(number);
        this.button_JumpTo().click();
    }

    //Standard链接

    //点击picture
    public void func_Click_Picture(int index) {
        this.linkPicture(index).click();
    }

    //点击pn
    public void func_Click_Pn(int index) {
        this.linkPn(index).click();
    }

    //点击ttl
    public void func_Click_Ttl(int index) {
        this.linkTtl(index).click();
    }

    //点击An
    public void func_Click_An(int index) {
        this.linkAn(index).click();
    }

    //点击In
    public void func_Click_In(int index, int inIndex) {
        this.linkInventorName(index, inIndex).click();
    }

    //getpn
    public String get_Pn(int index) {
        String pn = this.linkPn(index).getText();
        return pn;
    }

    //切换到tableview，并确认已加载完毕
    public boolean func_jumpToTableView() throws InterruptedException {

        this.func_click_view_byRel("tablelist");
        int i = 0;
        if (!this.table_patentList_tableView().isDisplayed() && i < 20) {
            l.info("wait for {}", i);
            i++;
            Thread.sleep(500);
            return false;
        } else {
            return true;

        }
    }

    //切换到standardview,并确认已加载完毕
    //index为第几个专利
    public boolean func_jumpToStandardView(int index) throws InterruptedException {

        this.func_click_view_byRel("standard");
        int i = 0;
        if (!this.linkPn(index).isDisplayed() && i < 20) {
            l.info("wait for {}", i);
            i++;
            Thread.sleep(500);
            return false;
        } else {
            return true;
        }
    }

    //standardview点击专利后，跳转到匹配的pvp页面
    //index为第几个专利
    public boolean func_Check_Pn_PatentView(String expPn) {
        String actPn = d.findElement(By.className("patent-info")).findElements(By.tagName("tr")).get(1).findElements(By.className("highlighter")).get(0).getText();
        if (expPn.equals(actPn)) {
            return true;
        } else {
            return false;
        }
    }
    //选择单个filter index,返回值

    public String func_Check_Refine_DropdownList(int index) {
        this.buttonSelectFilterType().click();
        this.refineDropDownList(index).click();
        return this.filterSelected().getText();
    }
    //单个filter 选择后，选取option
    public void func_Check_SingleFilterOption(int index1,int index2){
        this.refineOption(index1,index2).click();
    }

    /**
     * 链接-根据名称获取文件夹列表（SRP）
     *
     * @param
     * @return
     */
    public WebElement link_listfolder_byName_srp(String name) {
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
     * 新建收藏夹（From SRP）
     *
     * @return
     * @throws InterruptedException
     * @throws AWTException
     */
    public void func_create_folder_srp(String folderName) throws InterruptedException, AWTException {
        l.entry();
        this.func_click_addToList_toobar();
        this.btn_createNewFolder_addToListDialog().click();
        //通过参数来传递列表名
        this.input_folderName_addToListDialog().clear();
        this.input_folderName_addToListDialog().sendKeys(folderName);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
    }

    /**
     * 新建收藏夹（From SRP）
     *
     * @return
     * @throws InterruptedException
     * @throws AWTException
     */
    public void func_create_folder_srp1(String folderName) throws InterruptedException, AWTException {
        l.entry();
        this.btn_createNewFolder_addToListDialog().click();
        //通过参数来传递列表名
        this.input_folderName_addToListDialog().clear();
        this.input_folderName_addToListDialog().sendKeys(folderName);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
    }

    /**
     * 新建收藏夹_default name（From SRP）
     *
     * @return
     * @throws InterruptedException
     * @throws AWTException
     */
    public void func_create_folder_default_srp() throws InterruptedException, AWTException {
        l.entry();
        this.func_click_addToList_toobar();
        this.btn_createNewFolder_addToListDialog().click();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
    }

    /**
     * 新建子收藏夹（From SRP）
     *
     * @throws InterruptedException
     * @throws AWTException
     */
    public void funCreatChildFolderSRP(String foldername, String foldername1) throws InterruptedException, AWTException {
        l.entry();
        if (this.link_listfolder_byName_srp(foldername) != null) {
            act = new Actions(d);
            act.moveToElement(this.link_listfolder_byName_srp(foldername));
            act.perform();
            this.link_listfolder_byName_srp(foldername).click();
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
     * 新建子收藏夹_Default Name（From SRP）
     *
     * @throws InterruptedException
     * @throws AWTException
     */
    public void funCreatChildFolderSRP_default(String foldername) throws InterruptedException, AWTException {
        l.entry();
        if (this.link_listfolder_byName_srp(foldername) != null) {
            act = new Actions(d);
            act.moveToElement(this.link_listfolder_byName_srp(foldername));
            act.perform();
            this.link_listfolder_byName_srp(foldername).click();
            this.btn_add_addToListDialog().click();
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
        } else {
            l.error("<<<<<<<<<<<<<Fail ---Userlist folder [{}] does not exist", foldername);
        }
    }

    /**
     * 编辑收藏夹名称（From SRP）
     */
    public void func_editListNameSRP(String name, String newname) throws InterruptedException, AWTException {
        l.entry();
        if (this.link_listfolder_byName_srp(name) != null) {
            this.link_listfolder_byName_srp(name).click();
            this.btn_rename_addToListDialog().click();
            this.input_folderName_addToListDialog().clear();
            this.input_folderName_addToListDialog().sendKeys(newname);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
        } else {
            l.error("<<<<<<<<<<<<<<<Fail -- User list folder [{}] does not exist", name);
        }
    }

    /**
     * 跳转至任意页面
     */
    public void func_JumpToPage(String Num) throws InterruptedException, AWTException {
        l.entry();
        this.link_JumpToPage().click();
        this.input_Num().clear();
        this.input_Num().sendKeys(Num);
        this.btn_Go().click();
    }

    /**
     * 点击refine 条件下方到more
     */
    public void func_ClickLinkMore(int index){
        this.link_MoreFilter(index).click();

    }
}

