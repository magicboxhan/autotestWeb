package com.hq.test.automation.pageobject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * 专利查看页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_patentViewPage extends Zhihuiya_basePage {

    public Zhihuiya_patentViewPage(WebDriver driver) {
        l.entry();
        d = driver;
        act = new Actions(d);
        selfcheckSelector = By.className("patent-title");
        l.exit();
    }


    /********** Elements **********/

    /**
     * 退出提示按钮
     *
     * @return
     */
    public WebElement link_exit_tip() {
        l.entry();
        return d.findElement(By.className("help-close")).findElement(By.className("primary"));
    }

    /**
     * p--专利标题（PN+Title）
     *
     * @return
     */
    public WebElement patentTitle() {
        l.entry();
        return d.findElement(By.cssSelector(".patent-title.highlighter"));
//		return d.findElement(By.className("patent-title highlighter"));
    }

    /**
     * 按钮--返回按钮
     *
     * @return
     */
    public WebElement button_returnBack() {
        l.entry();
        return d.findElement(By.className("return-back"));
    }

    /**
     * 按钮--收藏
     */
    public WebElement button_addToList() {
        l.entry();
        return d.findElement(By.cssSelector(".addtolist-single.btn-26"));
//		return d.findElement(By.className("addtolist-single btn-26"));
    }

    /**
     * PDF下载链接
     *
     * @return
     */
    public WebElement link_PDFDownload() {
        l.entry();
        return d.findElement(By.className("pdf-download"));
    }

    /**
     * 下载按钮
     */
    public WebElement button_email() {
        l.entry();
        return d.findElement(By.cssSelector(".emailto.btn-26"));
    }


    /**
     * 链接--专利家族
     *
     * @return
     */
    public WebElement link_patentFamily() {
        l.entry();
        return d.findElement(By.id("family"));
    }

    /*查找专利分布地图元素*/
    public WebElement patentFamilyDistributionMap() {
        l.entry();
        return d.findElement(By.id("world"));
    }

    /*
    查找分布地图WIPO元素
     */
    public WebElement distributionMapWIPO() {
        l.entry();
        return d.findElement(By.id("extra-area")).findElement(By.id("WIPO"));
    }

    /*
    查找分布地图EPO元素
     */
    public WebElement distributionMapEPO() {
        l.entry();
        return d.findElement(By.id("extra-area")).findElement(By.id("EPO"));
    }

    /*
    查找分布地图TOTAL元素
     */
    public WebElement distributionMapTotal() {
        l.entry();
        return d.findElement(By.id("total"));
    }

    /*
    查找分布地图图例元素
     */
    public WebElement distributionMapLegend() {
        l.entry();
        return d.findElement(By.id("legend"));
    }

    /*
    查找同族专利序号元素
     */
    public WebElement patentFamilySeq() {
        l.entry();
        return d.findElement(By.className("seq"));
    }

    /*
    查找同族专利PN元素
     */
    public WebElement link_patentFamilyPN() {
        l.entry();
        return d.findElement(By.className("pn-number"));
    }

    /*
    查找同族专利标题元素
     */
    public WebElement patentFamilyTitle() {
        l.entry();
        return d.findElement(By.className("pn-title"));
    }

    /*
    查找同族专利PDF下载按钮元素
     */
    public WebElement patentFamilyPDF() {
        l.entry();
        return d.findElement(By.cssSelector(".i16.ico-download-pdf"));
    }

    /*
    查找同族专利Priority Data元素
     */
    public WebElement patentFamilyPriority() {
        l.entry();
        return d.findElement(By.id("family-priority"));
    }

    /*
    查找同族专利Priority Data数据元素
     */
    public WebElement patentFamilyPriorityData() {
        l.entry();
        return d.findElement(By.className("family-priority-content")).findElement(By.cssSelector(".priority.light"));
    }

    /*
    查找同族专利Assignee Name元素
     */
    public WebElement patentFamilyAssigneeName() {
        l.entry();
        return d.findElement(By.id("family-an"));
    }

    /*
    查找同族专利Assignee Name数据元素
     */
    public WebElement patentFamilyAssigneeNameLink() {
        l.entry();
        return d.findElement(By.className("family-an-content")).findElement(By.cssSelector(".name-link.highlighter"));
    }

    /*
    查找同族专利Inventor Name元素
     */
    public WebElement patentFamilyInventorName() {
        l.entry();
        return d.findElement(By.id("family-in"));
    }

    /*
    查找同族专利Inventor Name数据元素
     */
    public WebElement patentFamilyInventorNameLink() {
        l.entry();
        return d.findElement(By.className("family-in-content")).findElement(By.cssSelector(".name-link.highlighter"));
    }

    /*
    查找同族专利IPC元素
     */
    public WebElement patentFamilyIPC() {
        l.entry();
        return d.findElement(By.id("family-ipc"));
    }

    /*
    查找同族专利IPC数据元素
     */
    public WebElement patentFamilyIPCLink() {
        l.entry();
        return d.findElement(By.className("family-ipc-content"));
    }

    /*
    查找同族专利Abstract元素
     */
    public WebElement patentFamilyAbstract() {
        l.entry();
        return d.findElement(By.id("family-abst"));
    }

    /*
    查找同族专利Abstract数据元素
     */
    public WebElement patentFamilyAbstractContent() {
        l.entry();
        return d.findElement(By.className("family-abst-content"));
    }

    /*
    查找同族专利Legal Status元素
     */
    public WebElement patentFamilyLegalStatus() {
        l.entry();
        return d.findElement(By.id("family-legal"));
    }

    /*
    查找同族专利Legal Status数据元素
     */
    public WebElement patentFamilyLegalStatusContent() {
        l.entry();
        return d.findElement(By.className("family-legal-content")).findElement(By.cssSelector(".item.default-show"));
    }

    /**
     * 链接--引用分析
     *
     * @return
     */
    public WebElement link_citationAnalysis() {
        l.entry();
        return d.findElement(By.id("citation"));
    }

    /**
     * 链接--法律状态
     *
     * @return
     */
    public WebElement link_legalStatus() {
        l.entry();
        return d.findElement(By.id("legal"));
    }

    /**
     * p--专利标题
     *
     * @return
     */
    public WebElement p_patentTitle() {
        l.entry();
        return d.findElement(By.id("header")).findElement(By.tagName("p"));
    }

    /**
     * 链接--overview
     *
     * @return
     */
    public WebElement link_overView() {
        l.entry();
        return d.findElement(By.id("detail"));
    }

    /**
     * 链接--dualview
     *
     * @return
     */
    public WebElement link_dualView() {
        l.entry();
        return d.findElement(By.id("dual"));
    }

    /**
     * 查找dualview-left模块
     */
    public WebElement dualView_left() {
        l.entry();
        return d.findElement(By.id("view-dual-left"));
    }

    /**
     * 查找dualview left patent title模块
     */
    public WebElement dualViewPatentTitle() {
        l.entry();
        return d.findElement(By.id("view-dual-left")).findElement(By.className("patent-title"));
    }

    /**
     * 查找dualview left patent content
     */
    public WebElement dualViewPatentContent() {
        l.entry();
        return d.findElement(By.id("view-dual-left")).findElement(By.className("section"));
    }

    /**
     * 查找dualview-right模块
     */
    public WebElement dualView_right() {
        l.entry();
        return d.findElement(By.id("view-dual-right"));
    }

    /**
     * 左边标签image
     */
    public WebElement tab_image() {
        l.entry();
        return d.findElement(By.className("patentimg"));
    }

    /**
     * 左边标签imageEmpty
     */
//	public WebElement tab_imageEmpty(){
//		l.entry();
//		return d.findElement(By.className("patentimg")).getAttribute("src")
//	}
    public HashMap<String, Double> func_get_imgURL() {
        l.entry();
        HashMap<String, Double> values = new HashMap<>();
        String style = img_patentImage().getAttribute("src");
        for (String attribute : style.split(";")) {
            if (attribute.contains("noimage.png")) {
                //宽度
                Double value = Double.valueOf(attribute.split(":")[1].trim().replace("px", ""));
                values.put("width", value);
            }
        }
        l.exit();
        return values;
    }

    /**
     * 定义图片缩放元素
     */
    public WebElement zoom_image() {
        l.entry();
        return d.findElement(By.cssSelector(".flip-major.loaded")).findElement(By.className("pic"));
    }

    /**
     * 定义图片放大按钮
     */
    public WebElement zoomIn() {
        l.entry();
        return d.findElement(By.className("ico-zoom"));
    }

    /**
     * 定义图片缩小按钮
     */
    public WebElement zoomOut() {
        l.entry();
        return d.findElement(By.cssSelector(".flip-major.loaded"));
    }

    /**
     * 验证摘要
     */
    public WebElement tab_abstract() {
        l.entry();
        return d.findElement(By.id("abst-render"));
    }


    public ArrayList<String> func_get_abstractFields() throws Exception {
        l.entry();
        ArrayList<String> fields = new ArrayList<>();
        for (WebElement th : table_patentInfo().findElements(By.tagName("th"))) {
            fields.add(th.getText());
        }
        return fields;
    }

    /**
     * 权利要求按钮
     */
    public WebElement tab_claims() {
        l.entry();
        return d.findElement(By.id("clms"));
    }

    /**
     * 验证权利要求页面
     */
    public WebElement page_claims() {
        l.entry();
        return d.findElement(By.id("detail-clms"));
    }

    /**
     * 说明书按钮
     */
    public WebElement tab_desc() {
        l.entry();
        return d.findElement(By.id("desc"));
    }

    /**
     * 验证说明书页面
     */
    public WebElement page_desc() {
        l.entry();
        return d.findElement(By.id("detail-desc"));
    }

    /**
     * 引用专利标签按钮
     */
    public WebElement tab_cites() {
        l.entry();
        return d.findElement(By.id("cites"));
    }

    /**
     * 引用专利标签列表
     */
    public WebElement citesList() {
        l.entry();
        return d.findElement(By.id("cites-render")).findElement(By.className("section-cites"));
    }

    /**
     * other references
     */
    public WebElement otherReferences() {
        l.entry();
        return d.findElement(By.className("section-other-references"));
    }

    /**
     * 验证引用专利页面
     */
    public WebElement page_description() {
        l.entry();
        return d.findElement(By.id("detail-desc"));
    }

    /**
     * 验证被引用标签
     */
    public WebElement tab_cited() {
        l.entry();
        return d.findElement(By.id("cited"));
    }

    /**
     * 查找被引用专利标签列表
     */
    public WebElement citedList() {
        l.entry();
        return d.findElement(By.id("cited-render")).findElement(By.className("section-cites"));
    }

    /**
     * 验证其他语言
     */
    public WebElement tab_otherLanguage() {
        l.entry();
        return d.findElement(By.id("lang-render"));
    }

    /**
     * 专利详情列表
     *
     * @return
     */
    public WebElement table_patentInfo() {
        l.entry();
        try {
            return d.findElement(By.className("patent-info"));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 表格--引用分析（没有则返回空）
     *
     * @return
     */
    public WebElement table_citationRender() {
        l.entry();
        try {
            return d.findElement(By.id("citation")).findElements(By.tagName("table")).get(0);
        } catch (Exception e) {
            l.debug("Citation table does not exist");
            return null;
        }
    }

    /**
     * 表格--专利家族（没有则返回空）
     *
     * @return
     */
    public WebElement table_familyRender() {
        l.entry();
        try {
            return d.findElement(By.id("family-render")).findElements(By.tagName("table")).get(0);
        } catch (Exception e) {
            l.debug("Family render table does not exist");
            return null;
        }
    }

    /**
     * 表格--法律状态（没有则返回空）
     *
     * @return
     */
    public WebElement table_legalStatus() {
        l.entry();
        try {
            return d.findElement(By.id("legal-view")).findElements(By.tagName("table")).get(0);
        } catch (Exception e) {
            l.debug("Legal table does not exist");
            return null;
        }
    }

    /**
     * div-摘要
     *
     * @return
     */
    public ArrayList<WebElement> divs_abstract() {
        l.entry();
        ArrayList<WebElement> divs = (ArrayList<WebElement>) d.findElement(By.id("abst-render")).findElements(By.className("translate"));
        return divs;
    }

    /**
     * 链接--主要IPC
     *
     * @return
     */
    public WebElement link_ipc_0() {
        l.entry();
        return d.findElement(By.id("ICL0"));
    }

    /**
     * li -- overview下的缩略图标签*
     *
     * @return
     */
    public WebElement li_thumb() {
        l.entry();
        return d.findElement(By.id("thumb"));
    }

    /**
     * img - 专利图片*
     *
     * @return
     */
    public WebElement img_patentImage() {
        l.entry();
        return d.findElement(By.id("thumb-render")).findElement(By.className("pic"));
    }

    /**
     * div-添加搜索框-文件夹列表
     * @return 不存在返回为空
     */
    public WebElement div_folder(){
        l.entry();
        try{
            return d.findElement(By.id("addtolist-div")).findElement(By.className("ztree"));
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 按钮--新增文件夹--加号按钮
     * @return
     */
    public WebElement btn_add_addToListDialog(){
        l.entry();
        return d.findElement(By.id("addtolist-div")).findElement(By.className("add"));
    }
    /**
     * AddToList窗口中的rename按钮(BS)
     */
    public WebElement btn_rename_addToListDialog(){
        l.entry();
        return d.findElement(By.id("addtolist-div")).findElement(By.className("edit"));
    }

    /**
     * 链接-根据名称获取文件夹列表（SP）
     * @param
     * @return
     */
    public WebElement link_listfolder_byName_bs(String name){
        l.entry();
        if(this.div_folder() == null){
            l.error("cannot find folder");
            return null;
        }
        List<WebElement> links = this.div_folder().findElements(By.tagName("a"));
        for (WebElement link : links){
            if(link.getText().equals(name)){
                return link;
            }
        }
        l.warn("User list folder link is null");
        return null;
    }

    /**
     * input--新文件夹名称--添加搜藏对话框
     * @return
     */
    public WebElement input_folderName_addToListDialog(){
        l.entry();
        return d.findElement(By.id("addtolist-div")).findElement(By.className("rename"));
    }

    /**
     * 按钮--添加到收藏夹
     *
     * @return
     */
    public WebElement btnAddToList() {
        l.entry();
        return d.findElement(By.className("addtolist-single"));
    }

    /**
     * 按钮--新建文件夹_在AddToList窗口中
     *
     * @return
     */
    public WebElement btnCreateNewFolder_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist-div")).findElement(By.className("create-new-folder"));
    }

    /**
     * 链接-根据名称获取文件夹列表（PVP）
     *
     * @param
     * @return
     */
    public WebElement link_listfolder_byName_pvp(String name) {
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
    
    
    
    
    
    


    /********** Functions **********/

    /**
     * 获取专利详细信息
     *
     * @return
     * @throws InterruptedException
     */
    public HashMap<String, String> func_get_patentInfoTableData() throws InterruptedException {
        l.entry();
        //返回数据
        HashMap<String, String> returnData = new HashMap<String, String>();
        //前置操作
        //需要展示所有隐藏字段，否则取数据会漏掉隐藏的值
        JavascriptExecutor jse = (JavascriptExecutor) d;
        String js = "$(\".hide\").removeClass('hide')";
        jse.executeScript(js);
        //获取表格
        int i = 0;
        while (table_patentInfo() == null) {
            if (i < 30) {
                i++;
                Thread.sleep(1000);
            } else {
                break;
            }
        }
        WebElement table = this.table_patentInfo();
        if (table == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Can not find patent info table");
            t.takeScreenshot(d);
            return null;
        }
        //获取所有单元格
        List<WebElement> ths = table.findElements(By.tagName("th"));
        l.debug("th size is {}", ths.size());
        List<WebElement> tds = table.findElements(By.tagName("td"));
        l.debug("td size is {}", tds.size());
        //键值对数量
        int keyAmount = ths.size();
        //将单元格内容存入hashtable
        for (i = 0; i < keyAmount; i++) {
            String key = ths.get(i).getText();
            l.debug("key -- {}", key);
            String value = tds.get(i).getText();
            l.debug("value -- {}", value);
            returnData.put(key, value);
        }
        //========== 替换特殊字段 ==========
        //处理 Title
        String value_Title = "";
        for (WebElement e : table.findElements(By.tagName("tr")).get(0).findElements(By.tagName("div"))) {
            try {
                if (e.getText().length() != 0) {
                    value_Title += e.getText();
                    value_Title += " | ";
                }
            } catch (Exception e2) {
                //Do nothing
            }
        }
        value_Title = value_Title.substring(0, value_Title.length() - 3);
        returnData.replace("Title", value_Title);
        //处理 Assignee Name
        if (this.doesExist(By.id("view-table-an"))) {
            String value_AssigneeName = "";
            for (WebElement e : d.findElement(By.id("view-table-an")).findElements(By.tagName("a"))) {
                try {
                    value_AssigneeName += e.getText();
                    value_AssigneeName += " | ";
                } catch (Exception e2) {
                    //Do nothing
                }
            }
            if (value_AssigneeName.length() != 0) {
                value_AssigneeName = value_AssigneeName.substring(0, value_AssigneeName.length() - 3);
            }
            returnData.replace("Assignee Name", value_AssigneeName);
        }
        //处理 Inventor Name
        if (this.doesExist(By.id("view-table-in"))) {
            String value_InventorName = "";
            for (WebElement e : d.findElement(By.id("view-table-in")).findElements(By.tagName("a"))) {
                try {
                    value_InventorName += e.getText();
                    value_InventorName += " | ";
                } catch (Exception e2) {
                    //Do nothing
                }

            }
            if (value_InventorName.length() != 0) {
                value_InventorName = value_InventorName.substring(0, value_InventorName.length() - 3);
            }
            returnData.replace("Inventor Name", value_InventorName);
        }
        //处理 International Classification
        try {
            returnData.replace("International Classification", returnData.get("International Classification").replace("IPC(1-7): ", "").replace("IPC(8): ", "").replace(" ", " | "));
            returnData.replace("International Classification", returnData.get("International Classification").replace("\n", " | "));
        } catch (Exception e) {
            //do nothing
        }
        //处理 US Classification
        try {
            returnData.replace("US Classification", returnData.get("US Classification").replace(" ", " | "));
        } catch (Exception e) {
            //do nothing
        }
        //处理 FI
        try {
            returnData.replace("FI", returnData.get("FI").replace(" ", " | "));
        } catch (Exception e) {
            //do nothing
        }
        //处理 Standardized Assignee
        try {
            returnData.replace("Standardized Assignee", returnData.get("Standardized Assignee").replace("\n", " | "));
        } catch (Exception e) {
            //do nothing
        }
        //处理 Standardized Inventor
        try {
            returnData.replace("Standardized Inventor", returnData.get("Standardized Inventor").replace("\n", " | "));
        } catch (Exception e) {
            //do nothing
        }
        //处理 Agency
        try {
            returnData.replace("Agency", returnData.get("Agency").replace("\n", " | "));
        } catch (Exception e) {
            //do nothing
        }
        //处理 Attorney Name
        try {
            returnData.replace("Attorney Name", returnData.get("Attorney Name").replace("\n", " | "));
        } catch (Exception e) {
            //do nothing
        }

        //===== 添加表格以外数据 =====
        //Abstract
        String value_Abstract = "";
        ArrayList<WebElement> divs_abstract_updated = new ArrayList<WebElement>();
        for (WebElement div : this.divs_abstract()) {
            if (div.getAttribute("id").contains("EN")) {
                divs_abstract_updated.add(div);
                break;
            }
        }
        for (WebElement div : this.divs_abstract()) {
            if (div.getAttribute("id").contains("CN")) {
                divs_abstract_updated.add(div);
                break;
            }
        }
        for (WebElement div : this.divs_abstract()) {
            if (div.getAttribute("id").contains("JP")) {
                divs_abstract_updated.add(div);
                break;
            }
        }
        for (WebElement div : divs_abstract_updated) {
            value_Abstract += div.getText();
            value_Abstract += " | ";
        }
        if (value_Abstract.length() != 0) {
            value_Abstract = value_Abstract.substring(0, value_Abstract.length() - 3);
        }
        returnData.put("Abstract", value_Abstract);
        //IPC Primary
        try {
            returnData.put("IPC Primary", this.link_ipc_0().getText());
        } catch (Exception e) {
            returnData.put("IPC Primary", "-");
        }


        return returnData;

    }

    /**
     * 验证专利详情表中的指定字段的值正确
     *
     * @param col
     * @param exp_value
     * @return
     * @throws InterruptedException
     */
    public boolean func_verify_valueExistInPatentInfoTable(String col, String exp_value) throws InterruptedException {
        l.entry();
        String key = null;
        if (col.toLowerCase().equals("pn")) {
            key = "Publication Number";
        } else if (col.toLowerCase().equals("title")) {
            key = "Title";
        }
        l.info("Expected column: {}", key);
        l.info("Expected value: {}", exp_value);
        int i = 0;
        while (table_patentInfo() == null) {
            if (i < 30) {
                i++;
            } else {
                break;
            }
        }
        WebElement table = this.table_patentInfo();
        if (table == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Can not find patent info table");
            t.takeScreenshot(d);
            return false;
        }
        if (exp_value == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Expected value is null");
            t.takeScreenshot(d);
            return false;
        }
        HashMap<String, String> tableData = this.func_get_patentInfoTableData();
        String act_value = tableData.get(key);
        l.debug("Current [{}]: [{}]", key, act_value);
        if (act_value.contains(exp_value)) {
            l.info("++++++++++++++++++++++++++++++ Pass -- [{}] exists in patent info", key);
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [{}] does not exist in patent info", key);
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 获取页面顶端的专利标题
     *
     * @return
     */
    public String func_get_patentTitle() {
        l.entry();
        return this.patentTitle().getText();
    }

    /**
     * 点击PN前的返回按钮
     */
    public void func_click_returnBack() {
        l.entry();
        this.button_returnBack().click();
        l.exit();
    }

    /**
     * 点击收藏按钮
     */
    public void func_click_addToList() {
        l.entry();
        this.button_addToList().click();
        l.exit();
    }

    /**
     * 点击邮寄按钮
     */
    public void func_click_email() {
        l.entry();
        this.button_email().click();
        l.exit();
    }

    /**
     * 点击PDF下载链接
     */
    public void func_click_downloadPDF() {
        l.entry();
        this.link_PDFDownload().click();
        l.exit();
    }


    /**
     * 点击overview
     */
    public void func_click_overView() {
        l.entry();
        this.link_overView().click();
        l.exit();
    }

    /**
     * 点击专利图片
     */
    public void func_click_image() {
        l.entry();
        this.tab_image().click();
        l.exit();
    }

    /**
     * 点击放大专利图片
     */
    public void func_click_zoomIn() {
        l.entry();
        this.zoomIn().click();
        l.exit();
    }

    /**
     * 点击缩小专利图片
     */
    public void func_click_zoomOut() {
        l.entry();
        this.zoomOut().click();
        l.exit();
    }

    /**
     * 点击权利要求
     */
    public void func_click_clms() {
        l.entry();
        this.tab_claims().click();
        l.exit();
    }

    /**
     * 点击说明书
     */
    public void func_click_desc() {
        l.entry();
        this.tab_desc().click();
        l.exit();
    }

    /**
     * 点击引用专利
     */
    public void func_click_cites() {
        l.entry();
        this.tab_cites().click();
        l.exit();
    }

    /**
     * 点击被引用专利
     */
    public void func_click_cited() {
        l.entry();
        this.tab_cited().click();
        l.exit();
    }

    /**
     * 点击dual view
     */
    public void func_click_dualView() {
        l.entry();
        this.link_dualView().click();
        l.exit();
    }

    /**
     * 点击专利家族
     */
    public void func_click_patentFamily() {
        l.entry();
        this.link_patentFamily().click();
        l.exit();
    }

    /**
     * 点击专利家族列表的PN
     */
    public void func_click_link_patentFamilyPN() {
        l.entry();
        this.link_patentFamilyPN().click();
        l.exit();
    }

    /**
     * 点击家族专利-assignee name按钮
     */
    public void func_click_patentFamilyAssigneeName() {
        l.entry();
        this.patentFamilyAssigneeName().click();
        l.exit();
    }

    /**
     * 点击家族专利-assignee name 数据连接
     */
    public void func_click_patentFamilyAssigneeNameLink() {
        l.entry();
        this.patentFamilyAssigneeNameLink().click();
        l.exit();
    }

    /**
     * 点击家族专利-inventor name按钮
     */
    public void func_click_patentFamilyInventorName() {
        l.entry();
        this.patentFamilyInventorName().click();
        l.exit();
    }

    /**
     * 点击家族专利-inventor name数据连接
     */
    public void func_click_patentFamilyInventorNameLink() {
        l.entry();
        this.patentFamilyInventorNameLink().click();
        l.exit();
    }

    /**
     * 点击家族专利-IPC按钮
     */
    public void func_click_patentFamilyIPC() {
        l.entry();
        this.patentFamilyIPC().click();
        l.exit();
    }

    /**
     * 点击家族专利-IPC链接
     */
    public void func_click_patentFamilyIPCLink() {
        l.entry();
        this.patentFamilyIPCLink().click();
        l.exit();
    }

    /**
     * 点击家族专利-Abstract按钮
     */
    public void func_click_patentFamilyAbstract() {
        l.entry();
        this.patentFamilyAbstract().click();
        l.exit();
    }

    /**
     * 点击家族专利-LegalStatus按钮
     */
    public void func_click_patentFamilyLegalStatus() {
        l.entry();
        this.patentFamilyLegalStatus().click();
        l.exit();
    }

    /**
     * 点击法律状态
     */
    public void func_click_legalStatus() {
        l.entry();
        this.link_legalStatus().click();
        l.exit();
    }

    /**
     * 点击引用分析
     */
    public void func_click_citationAnalysis() {
        l.entry();
        this.link_citationAnalysis().click();
        l.exit();
    }

    /**
     * 点击退出提示按钮
     */
    public void func_exit_tip() {
        l.entry();
        if (this.link_exit_tip().isDisplayed()) {
            l.debug("exit tip link exists");
            this.link_exit_tip().click();
        }
        l.exit();
    }

    /**
     * 验证专利家族是否有数据
     *
     * @return
     */
    public boolean verify_familyRender() {
        l.entry();
        if (this.table_familyRender() != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Patent family data exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent family data does not exist");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证专利法律状态是否有数据
     *
     * @return
     */
    public boolean verify_legalStatus() {
        l.entry();
        if (this.table_legalStatus() != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Patent legal status data exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Patent legal status data does not exist");
            t.takeScreenshot(d);
            return false;
        }
    }

//	public boolean func_verifyExist_overview(){
//		return doesExist(By.id("detail"));
//	}

    /**
     * ********************检查页面元素是否存在****************
     */
    //检查帮助信息
    public boolean func_verifyExist_newUserGuide() {
        l.entry();
        try {
            this.link_exit_tip();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查返回按钮
    public boolean func_verifyExist_returnBack() {
        l.entry();
        try {
            this.button_returnBack();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查专利标题
    public boolean func_verifyExist_patentTitle() {
        l.entry();
        try {
            this.patentTitle();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查overView标签
    public boolean func_verifyExist_overview() {
        l.entry();
        try {
            this.link_overView();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查image
    public boolean func_verifyExist_image() {
        l.entry();
        try {
            this.tab_image();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查图片缩放
    public boolean func_verifyExist_zoomImage() {
        l.entry();
        try {
            this.zoom_image();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查abst标签
    public boolean func_verifyExist_abst() {
        l.entry();
        try {
            this.tab_abstract();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查claims标签
    public boolean func_verifyExist_claims() {
        l.entry();
        try {
            this.tab_claims();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查claims页面
    public boolean func_verifyExist_claimsPage() {
        l.entry();
        try {
            this.page_claims();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查desc标签
    public boolean func_verifyExist_desc() {
        l.entry();
        try {
            this.tab_desc();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查claims页面
    public boolean func_verifyExist_descPage() {
        l.entry();
        try {
            this.page_description();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查cites标签
    public boolean func_verifyExist_cites() {
        l.entry();
        try {
            this.tab_cites();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查cites list
    public boolean func_verifyExist_citesList() {
        l.entry();
        try {
            this.citesList();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查other references
    public boolean func_verifyExist_otherReferences() {
        l.entry();
        try {
            this.otherReferences();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查cited标签
    public boolean func_verifyExist_cited() {
        l.entry();
        try {
            this.tab_cited();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查cited list
    public boolean func_verifyExist_citedList() {
        l.entry();
        try {
            this.citedList();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查desc标签
    public boolean func_verifyExist_otherLanguage() {
        l.entry();
        try {
            this.tab_otherLanguage();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查dualView标签
    public boolean func_verifyExist_dualview() {
        l.entry();
        try {
            this.link_dualView();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查dualView左边文本是否显示
    public boolean func_verifyExist_dualview_left() {
        l.entry();
        try {
            this.dualView_left();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查dualView右边PDF是否显示
    public boolean func_verifyExist_dualview_right() {
        l.entry();
        try {
            this.dualView_right();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查dualView左边title是否显示
    public boolean func_verifyExist_dualviewPatentTitle() {
        l.entry();
        try {
            this.dualViewPatentTitle();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查dualView左边content是否显示
    public boolean func_verifyExist_dualviewPatentContent() {
        l.entry();
        try {
            this.dualViewPatentContent();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查citationAnalysis标签
    public boolean func_verifyExist_citationAnalysis() {
        l.entry();
        try {
            this.link_citationAnalysis();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily标签
    public boolean func_verifyExist_patentFamily() {
        l.entry();
        try {
            this.link_patentFamily();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily专利分布地图
    public boolean func_verifyExist_patentFamilyDistributionMap() {
        l.entry();
        try {
            this.patentFamilyDistributionMap();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利分布地图WIPO是否存在
    public boolean func_verifyExist_distributionMapWIPO() {
        l.entry();
        try {
            this.distributionMapWIPO();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利分布地图EPO是否存在
    public boolean func_verifyExist_distributionMapEPO() {
        l.entry();
        try {
            this.distributionMapEPO();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利分布地图total是否存在
    public boolean func_verifyExist_distributionMapTotal() {
        l.entry();
        try {
            this.distributionMapTotal();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利分布地图LEGEND是否存在
    public boolean func_verifyExist_distributionMapLegend() {
        l.entry();
        try {
            this.distributionMapLegend();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利序号是否存在
    public boolean func_verifyExist_patentFamilySeq() {
        l.entry();
        try {
            this.patentFamilySeq();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利标题是否存在
    public boolean func_verifyExist_patentFamilyTitle() {
        l.entry();
        try {
            this.patentFamilyTitle();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利PN是否存在
    public boolean func_verifyExist_link_patentFamilyPN() {
        l.entry();
        try {
            this.link_patentFamilyPN();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利PDF下载按钮是否存在
    public boolean func_verifyExist_patentFamilyPDF() {
        l.entry();
        try {
            this.patentFamilyPDF();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利Priority Data是否存在
    public boolean func_verifyExist_patentFamilyPriority() {
        l.entry();
        try {
            this.patentFamilyPriority();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利Priority Data数据是否存在
    public boolean func_verifyExist_patentFamilyPriorityData() {
        l.entry();
        try {
            this.patentFamilyPriorityData();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利Assignee Name是否存在
    public boolean func_verifyExist_patentFamilyAssigneeName() {
        l.entry();
        try {
            this.patentFamilyAssigneeName();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利Assignee Name数据是否存在
    public boolean func_verifyExist_patentFamilyAssigneeNameLink() {
        l.entry();
        try {
            this.patentFamilyAssigneeNameLink();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利Inventor Name是否存在
    public boolean func_verifyExist_patentFamilyInventorName() {
        l.entry();
        try {
            this.patentFamilyInventorName();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利Inventor Name数据是否存在
    public boolean func_verifyExist_patentFamilyInventorNameLink() {
        l.entry();
        try {
            this.patentFamilyInventorNameLink();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利IPC是否存在
    public boolean func_verifyExist_patentFamilyIPC() {
        l.entry();
        try {
            this.patentFamilyIPC();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利IPC数据是否存在
    public boolean func_verifyExist_patentFamilyIPCLink() {
        l.entry();
        try {
            this.patentFamilyIPCLink();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利Abstract是否存在
    public boolean func_verifyExist_patentFamilyAbstract() {
        l.entry();
        try {
            this.patentFamilyAbstract();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利Abstract content是否存在
    public boolean func_verifyExist_patentFamilyAbstractContent() {
        l.entry();
        try {
            this.patentFamilyAbstractContent();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利Legal Status是否存在
    public boolean func_verifyExist_patentFamilyLegalStatus() {
        l.entry();
        try {
            this.patentFamilyLegalStatus();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查patentFamily 专利Legal Status CONTENT是否存在
    public boolean func_verifyExist_patentFamilyLegalStatusContent() {
        l.entry();
        try {
            this.patentFamilyLegalStatusContent();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查legalStatus标签
    public boolean func_verifyExist_legalStatus() {
        l.entry();
        try {
            this.link_legalStatus();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //检查收藏夹按钮是否存在
    public boolean func_verifyExist_addToList() {
        l.entry();
        try {
            this.button_addToList();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //	检查PDF下载按钮是否存在
    public boolean func_verifyExist_PDFDownload() {
        l.entry();
        try {
            this.link_PDFDownload();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //	检查邮寄按钮是否存在
    public boolean func_verifyExist_email() {
        l.entry();
        try {
            this.button_email();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
//	public boolean func_verifyExist_overview_3(){
//		l.entry();
//		if (d.findElements(By.id("detail")).size() > 0){
//			return true;
//		}else{
//			return false;
//		}
//	}


    public void func_click_thumbView() {
        l.entry();
        li_thumb().click();
        l.exit();
    }

    public void func_click_patentImage() {
        l.entry();
        img_patentImage().click();
        l.exit();
    }

    public HashMap<String, Double> func_get_imgWidthHeight() {
        l.entry();
        HashMap<String, Double> values = new HashMap<>();
        String style = img_patentImage().getAttribute("style");
        for (String attribute : style.split(";")) {
            if (attribute.contains("width")) {
                //宽度
                Double value = Double.valueOf(attribute.split(":")[1].trim().replace("px", ""));
                values.put("width", value);
            }
            if (attribute.contains("height")) {
                //宽度
                Double value = Double.valueOf(attribute.split(":")[1].trim().replace("px", ""));
                values.put("height", value);
            }
        }
        l.exit();
        return values;
    }

    /**
     * 新建子收藏夹（From Bulk Search Page）

     * @throws InterruptedException
     * @throws AWTException
     */
    public  void FunCreatChildFolderBS(String foldername,String foldername1) throws InterruptedException,AWTException {
        l.entry();
        if(this.link_listfolder_byName_bs(foldername)!=null) {
            act = new Actions(d);
            act.moveToElement(this.link_listfolder_byName_bs(foldername));
            act.perform();
            this.link_listfolder_byName_bs(foldername).click();
            this.btn_add_addToListDialog().click();
            Thread.sleep(3000);
            this.input_folderName_addToListDialog().clear();
            this.input_folderName_addToListDialog().sendKeys(foldername1);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
        }else {
            l.error("<<<<<<<<<<<<<Fail ---Userlist folder [{}] does not exist", foldername);
        }
    }
    /**
     * 编辑收藏夹名称（From Bulk Search）
     */
    public void func_editListNameBS(String name,String newname)throws InterruptedException,AWTException {
        l.entry();
        if(this.link_listfolder_byName_bs(name)!= null){
            this.link_listfolder_byName_bs(name).click();
            this.btn_rename_addToListDialog().click();
            this.input_folderName_addToListDialog().clear();
            this.input_folderName_addToListDialog().sendKeys(newname);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
        }else{
            l.error("<<<<<<<<<<<<<<<Fail -- User list folder [{}] does not exist", name);
        }
    }

    /**
     * 新建收藏夹
     */
    public void func_create_folder_pvp(String folderName) throws InterruptedException, AWTException {
        l.entry();
        this.btnAddToList().click();
        this.btnCreateNewFolder_addToListDialog().click();
        //通过参数来传递列表名
        this.input_folderName_addToListDialog().clear();
        this.input_folderName_addToListDialog().sendKeys(folderName);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
    }

    /**
     * 新建收藏夹_default
     */
    public void func_create_folder_default_pvp() throws InterruptedException, AWTException {
        l.entry();
        this.btnAddToList().click();
        this.btnCreateNewFolder_addToListDialog().click();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
    }

    /**
     * 按钮--新增文件夹--取消按钮
     *
     * @return
     */
    public WebElement btnCancel_addToListDialog() {
        l.entry();
        return d.findElement(By.id("addtolist-div")).findElement(By.id("cancel"));
    }

    /**
     * 编辑收藏夹名称（From PVP）
     */
    public void func_editListNamePVP(String name, String newname) throws InterruptedException, AWTException {
        l.entry();
        if (this.link_listfolder_byName_pvp(name) != null) {
            this.link_listfolder_byName_pvp(name).click();
            this.btn_rename_addToListDialog().click();
            this.input_folderName_addToListDialog().clear();
            this.input_folderName_addToListDialog().sendKeys(newname);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
        } else {
            l.error("<<<<<<<<<<<<<<<Fail -- User list folder [{}] does not exist", name);
        }
    }
}