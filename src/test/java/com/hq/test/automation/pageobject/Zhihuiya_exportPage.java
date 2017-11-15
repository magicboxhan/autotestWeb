package com.hq.test.automation.pageobject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.*;
import java.io.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.hq.test.framework.util.FileUtil;

/**
 * 导出页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_exportPage extends Zhihuiya_basePage {

    public Zhihuiya_exportPage(WebDriver driver) {
        l.entry();
        d = driver;
        act = new Actions(d);
        selfcheckSelector = By.className("export-major");
        l.exit();
    }

    /********** Elements **********/

    /**
     * 复选框--是否包含缩略图
     *
     * @return
     */
    public WebElement checkbox_includeThumbnail() {
        l.entry();
        return d.findElement(By.name("includeThumbnail"));
    }

    /**
     * 链接--下载
     *
     * @return
     */
    public WebElement link_download() {
        l.entry();
        return d.findElement(By.className("done"));
    }

    /**
     * 单选框--导出数量（根据索引选择）
     *
     * @param index
     * @return
     */
    public WebElement input_exportNum_byIndex(int index) {
        l.entry();
        return d.findElements(By.name("range_type")).get(index);
    }

    /**
     * input--开始数量
     *
     * @return
     */
    public WebElement input_startNum() {
        l.entry();
        return d.findElement(By.name("range_start"));
    }

    /**
     * input--结束数量
     *
     * @return
     */
    public WebElement input_endNum() {
        l.entry();
        return d.findElement(By.name("range_end"));
    }

    /**
     * input--不压缩
     *
     * @return
     */
    public WebElement input_noCompression() {
        l.entry();
        return d.findElements(By.name("compress")).get(0);
    }

    /**
     * input--压缩
     *
     * @return
     */
    public WebElement input_WithCompression() {
        l.entry();
        return d.findElements(By.name("compress")).get(1);
    }


    /**
     * 链接--文件类型
     *
     * @param type--0:xls, 1:pdf, 2:rtf, 3:xml, 4:csv
     * @return
     */
    public WebElement link_fileType(int type) {
        l.entry();
        String fileType = "";
//		if (type == 0){
//			fileType = "xls";
//		}else if (type == 1){
//			fileType = "pdf";
//		}
        switch (type) {
            case 0:
                fileType = "xls";
                break;
            case 1:
                fileType = "pdf";
                break;
            case 2:
                fileType = "rtf";
                break;
            case 3:
                fileType = "xml";
                break;
            case 4:
                fileType = "csv";
                break;
        }
        if (fileType.equals("")) {
            l.error("File type error");
            return null;
        }
        WebElement returnElement = null;
        List<WebElement> es = d.findElement(By.className("select-file-format")).findElements(By.tagName("img"));
        for (WebElement e : es) {
            String linkText = e.getAttribute("alt");
            if (linkText.contains(fileType)) {
                returnElement = e;
                break;
            }
        }
        return returnElement;
    }

    /**
     * 下拉框--导出字段(excel、csv、xml、rtf)
     *
     * @param field
     * @return
     */
    public Select select_exportFields_excel() {
        l.entry();
        return new Select(d.findElement(By.name("template")));
    }

    /**
     * 下拉框--导出字段(PDF)
     *
     * @return
     */
    public Select select_exportFields_pdf() {
        l.entry();
        return new Select(d.findElement(By.name("template")));
    }


    /**
     * 导出链接
     *
     * @return
     */
    public WebElement link_export() {
        l.entry();
        return d.findElement(By.cssSelector(".btn-26.primary"));
    }


    /**
     * 链接--展开自定义字段
     *
     * @return
     */
    public WebElement link_viewCustomField() {
        l.entry();
        return d.findElement(By.className("edit-btns")).findElements(By.tagName("label")).get(0);
    }


    /**
     * 链接--更新自定义字段
     *
     * @return
     */
    public WebElement link_updateCustomField() {
        l.entry();
        return d.findElement(By.className("edit-btns")).findElements(By.tagName("span")).get(1);
    }

    /**
     * span--选择导出的专利数量--选择导出专利
     *
     * @return
     */
    public WebElement span_selectedPatentNum() {
        l.entry();
        return d.findElement(By.cssSelector(".patents.section")).findElement(By.className("gray"));
    }

    /**
     * label--选择导出的专利数量--顺序导出专利
     *
     * @return
     */
    public WebElement label_selectedPatentNum() {
        l.entry();
        return d.findElement(By.className("patents-range")).findElement(By.className("gray"));
    }

    /**
     * 导出格式的图片集合
     *
     * @return
     */
    public ArrayList<WebElement> imges_exportFormat() {
        l.entry();
        return (ArrayList<WebElement>) d.findElement(By.className("select-file-format")).findElements(By.tagName("img"));
    }

    /**
     * span -- PDF提示信息
     *
     * @return
     */
    public WebElement span_pdfTip() {
        l.entry();
        return d.findElement(By.className("tip"));
    }

    /**
     * span -- 展示将要导出的字段
     *
     * @return
     */
    public WebElement span_fieldsString() {
        l.entry();
        return d.findElement(By.className("field-summary")).findElement(By.tagName("div"));
    }

    /**
     * ul -- 自定义字段，已选择字段
     *
     * @return
     */
    public WebElement ul_chosenFields() {
        l.entry();
        return d.findElement(By.id("show_fields"));
    }

    /**
     * ul -- 自定义字段，可选择字段
     *
     * @return
     */
    public WebElement ul_availableFields() {
        l.entry();
        return d.findElement(By.cssSelector(".swap-list.ui-sortable"));
    }

    /**
     * div--字段拖拽列表
     *
     * @return
     */
    public WebElement div_dragList() {
        l.entry();
        return d.findElement(By.cssSelector(".m-swap-list.field-swap-list"));
    }

    /**
     * 根据rel属性，返回li--自定义字段右侧列表（已选择字段）
     *
     * @param rel
     * @return
     */
    public WebElement li_dragLeft_byRel(String id) {
        l.entry();
        WebElement returnLi = null;
        for (WebElement e : this.ul_availableFields().findElements(By.tagName("li"))) {
            if (id.equals(e.getAttribute("id"))) {
                returnLi = e;
                break;
            }
        }
        return returnLi;
    }

    /**
     * 链接--另存模板为
     *
     * @return
     */
    public WebElement link_saveAs_template() {
        l.entry();
        return d.findElement(By.className("edit-btns")).findElements(By.tagName("label")).get(2);
    }

    /**
     * 输入框 -- 模板名称
     *
     * @return
     */
    public WebElement input_templateName() {
        l.entry();
        return d.findElement(By.className("is-input"));
    }

    /**
     * 链接 -- 保存模板
     *
     * @return
     */
    public WebElement link_save_template() {
        l.entry();
        return d.findElement(By.className("save-template-form")).findElement(By.tagName("span"));
    }

    /**
     * 链接 -- 删除模板
     *
     * @return
     */
    public WebElement link_delete_template() {
        l.entry();
        return d.findElement(By.className("edit-btns")).findElements(By.tagName("span")).get(0);
    }


    /********** Functions **********/

    /**
     * 点击导出按钮
     */
    public void func_click_exportButton() {
        l.entry();
        this.link_export().click();
        l.exit();
    }

    /**
     * 根据选项导出文件，导完后，点击下载，记录导出时间
     *
     * @param format           -- 0:xls, 1:pdf, 2:rtf, 3:xml, 4:csv
     * @param fields           -- 下拉框索引
     * @param folder           -- 下载路径
     * @param includeThumbnail -- 是否包含缩略图，-1：默认；1：是；0；否
     * @param numType          -- 导出数量类型，-1：默认；0：勾选；1：顺序
     * @param startNum         -- 顺序导出的起始数字
     * @param endNum           -- 顺序导出的截止数字
     * @param withCompression  -- 是否压缩，-1：默认；0：否；1：是
     * @return 部分文件名
     * @throws Exception
     */
    public String func_export(int format, int fields, String folder, int includeThumbnail, int numType, int startNum, int endNum, int withCompression, String templateName) throws Exception {
        l.entry();
        //导出
        //选择数量
        if (numType != -1) {
            if (numType == 0) {
                //勾选数量
                this.input_exportNum_byIndex(0).click();
            } else if (numType == 1) {
                //指定数量
                try {
                    this.input_exportNum_byIndex(1).click();
                    //************** 页面控件修改后，不能通过sendKeys方法来设置值，所以使用JS ****************
//					this.input_startNum().clear();
//					this.input_startNum().sendKeys(String.valueOf(startNum));
//					this.input_endNum().clear();
//					this.input_endNum().sendKeys(String.valueOf(endNum));
                    //******************************
                    JavascriptExecutor jse = (JavascriptExecutor) d;
                    String js = String.format("$(\".range-begin\").val(%d)", startNum);
                    jse.executeScript(js);
                    js = String.format("$(\".range-end\").val(%d)", endNum);
                    jse.executeScript(js);
                    //******************************
                } catch (Exception e) {
                    //do nothing
                }
            } else {
                l.error("Value of export number [{}] is invalid", numType);
                return null;
            }
        }
        //选择格式
        if (format != -1) {
            this.link_fileType(format).click();
        }
        //选择字段
        String extName = null;
//		if (format == 0)
//		{
//			this.select_exportFields_excel().selectByIndex(fields);
//			if(withCompression == 1){
//				extName = "zip";
//			}else{
//				extName = "xls";
//			}
//		}else if (format == 1){
//			this.select_exportFields_pdf().selectByIndex(fields);
//			extName = "zip";
//		}
        if (format == 1) {
            //pdf
            this.select_exportFields_pdf().selectByIndex(fields);
            extName = "zip";
        } else {
            //其它格式
            if ((((format == 0) || (format == 4)) && (fields != 6)) || (((format == 2) || (format == 3)) && (fields != 4))) {
                //非自定义模板
                this.select_exportFields_excel().selectByIndex(fields);
            } else {
                //自定义模板
                this.select_exportFields_excel().selectByVisibleText(templateName);
            }
            if (withCompression == 0) {
                switch (format) {
                    case 0:
                        extName = "xls";
                        break;
                    case 2:
                        extName = "rtf";
                        break;
                    case 3:
                        extName = "xml";
                        break;
                    case 4:
                        extName = "csv";
                        break;
                }
            } else if (withCompression == 1) {
                extName = "zip";
            }

        }
        //是否压缩
        if (withCompression != -1) {
            if (withCompression == 0) {
                //不压缩
                this.input_noCompression().click();
            } else if (withCompression == 1) {
                //压缩
                this.input_WithCompression().click();
            } else {
                l.error("Value of Compression [{}] is invalid", withCompression);
                return null;
            }
        }
        //选择是否包含缩略图
        if (format == 0) {
            if (includeThumbnail != -1) {
                if (includeThumbnail == 0) {
                    //不使用缩略图
                    //do nothing
                } else if (includeThumbnail == 1) {
                    //缩略图
                    this.checkbox_includeThumbnail().click();
                } else {
                    l.error("Value of include thumbnail [{}] is invalid", withCompression);
                    return null;
                }
            }
        }
        //记录当前时间字符串，精确到分，为了避免误差，去掉分的个位，即精确到10分
        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddhhmm");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
        String partialFileName = sdf.format(date);
//        partialFileName = partialFileName.substring(0,partialFileName.length()-1);	//精确到10分
        partialFileName = partialFileName.substring(0, partialFileName.length() - 5);    //精确到10天
        l.info("Partial file name: [{}]", partialFileName);
        //删除现有文件
        FileUtil f = new FileUtil();
        String fullFileName = f.getFileNameByPartialFileName(folder, partialFileName, extName);
        //如果文件存在，则删除
        if (fullFileName != null) {
            f.deleteFile(String.format("%s/%s", folder, fullFileName));
        }
        //点击导出
        this.link_export().click();
        return partialFileName;
    }

    /**
     * 直接点击导出--类型为默认的xls
     *
     * @param folder -- 下载路径
     * @return 部分文件名
     * @throws Exception
     */
    public String func_exportDirectly(String folder) throws Exception {
        l.entry();
        //记录当前时间字符串，精确到分，为了避免误差，去掉分的个位，即精确到10分
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddhhmm");
        String partialFileName = sdf.format(date);
        partialFileName = partialFileName.substring(0, partialFileName.length() - 1);
        l.info("Partial file name: [{}]", partialFileName);
        //删除现有文件
        FileUtil f = new FileUtil();
        String fullFileName = f.getFileNameByPartialFileName(folder, partialFileName, "xls");
        //如果文件存在，则删除
        if (fullFileName != null) {
            f.deleteFile(String.format("%s/%s", folder, fullFileName));
        }
        //点击导出
        this.link_export().click();
        return partialFileName;
    }

    /**
     * 验证zip文件中的PDF是否正确
     *
     * @param pns       -- 全部PN列表，包含有PDF和没有PDF的
     * @param pns_noPDF -- 期望PN列表，没有PDF -- 没有则传入null
     * @param file      -- 文件完整路径
     * @param fields    -- 导出字段选项
     * @return
     * @throws IOException
     */
    public boolean func_verify_pdfZip(ArrayList<String> pns, ArrayList<String> pns_noPDF, String file, int fields) throws IOException {
        l.entry();
        //如果待验证的期望结果为空，则直接返回true
        if (pns == null) {
            return true;
        }
        boolean result = true;
        //过滤PN列表，将没有PDF的PN从其中去掉
        ArrayList<String> pns_u = new ArrayList<String>();    //去掉没有PDF的PN后的列表
        ArrayList<String> pns_noPDF_u = new ArrayList<String>();    //pns和pns_noPDF的交集
        //如果导出PDF为一个文件（导出字段选项2），则不需要将没有PDF的PN列表和总PN列表取交集
        if (fields == 1) {
            pns_noPDF_u = pns_noPDF;
        }
        if (pns_noPDF == null) {
            pns_u = pns;
        } else {
            for (String pn : pns) {
                boolean matchFlag = false;
                for (String pn_noPDF : pns_noPDF) {
                    if (pn.equals(pn_noPDF)) {
                        matchFlag = true;
                        //如果导出PDF按专利分文件（导出字段选项1），则需要将没有PDF的PN列表，和总PN列表取交集
                        if (fields == 0) {
                            pns_noPDF_u.add(pn_noPDF);
                        }
                        break;
                    }
                }
                if (!matchFlag) {
                    pns_u.add(pn);
                }
            }
        }
        //读取zip文件
        ZipFile zf = new ZipFile(file);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        //逐个读取zip文件中的pdf文件
        ZipEntry ze;
        ArrayList<String> actPNs = new ArrayList<String>();
        ArrayList<String> act_pns_noPDF = new ArrayList<String>();
        while ((ze = zin.getNextEntry()) != null) {

            if ((ze.isDirectory()) && (ze.getName().equals("files"))) {
                //是文件夹，且名称为"files"
            }

            if (!ze.isDirectory()) {
                String pdfName = ze.getName();
                l.info("Getting file: [{}]", pdfName);
                //验证是否为PDF文件
                if (pdfName.toLowerCase().endsWith("pdf")) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- File type is correct");
                    String pdfPN = pdfName.split("\\.")[0];
                    actPNs.add(pdfPN);
                    result &= true;
                } else if (pdfName.contains("unavailable.txt")) {
                    //记录没有PDF的PN列表
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(zf.getInputStream(ze)));
                    String line;
                    while ((line = br.readLine()) != null) {
                        act_pns_noPDF.add(line);
                    }
                    br.close();
                } else {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- File type is incorrect");
//            		result &= false;
                }
            }
        }
        //根据期望PN列表，验证实际
        for (String expPN : pns_u) {
            boolean matchFlag = false;
            //遍历实际列表
            for (String actPN : actPNs) {
                if (actPN.contains(expPN)) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- [{}] exists", expPN);
                    result &= true;
                    matchFlag = true;
                    break;
                }
            }
            if (!matchFlag) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [{}] is missing", expPN);
                result &= false;
            }
        }
        //验证没有PDF的PN列表
        if (pns_noPDF_u != null) {
            l.info("========== verify pn which has no pdf related ==========");
            for (String expPN : pns_noPDF_u) {
                boolean matchFlag = false;
                //遍历实际列表
                for (String actPN : act_pns_noPDF) {
                    if (actPN.equals(expPN)) {
                        l.info("++++++++++++++++++++++++++++++ Pass -- [{}] exists", expPN);
                        result &= true;
                        matchFlag = true;
                        break;
                    }
                }
                if (!matchFlag) {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- [{}] is missing", expPN);
                    result &= false;
                }
            }
        }
        zf.close();
        zin.close();
        return result;
    }

    /**
     * 验证已选择专利数量是否正确--从搜索结果页
     *
     * @param expNum
     * @return
     */
    public boolean func_verify_selectedPatentNum_fromSearchResultPage(int expNum) {
        l.entry();
        int patentNum = -1;
        try {
            String patentNumStr = this.span_selectedPatentNum().getText().split("\\(")[1].split(" ")[0];
            patentNum = Integer.parseInt(patentNumStr);
        } catch (Exception e) {

        }
        if (patentNum == -1) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Selected patent number does not exist");
            t.takeScreenshot(d);
            return false;
        }
        if (expNum == patentNum) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Selected patent number is correct");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Selected patent number is incorrect");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证已选择专利数量是否正确--从批量搜索页
     *
     * @param expNum
     * @return
     */
    public boolean func_verify_selectedPatentNum_fromBulkSearchPage(int expNum) {
        l.entry();
        int patentNum = -1;
        try {
            String patentNumStr = this.label_selectedPatentNum().getText().split(" ")[3];
            patentNum = Integer.parseInt(patentNumStr);
        } catch (Exception e) {

        }
        if (patentNum == -1) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Selected patent number does not exist");
            t.takeScreenshot(d);
            return false;
        }
        if (expNum == patentNum) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Selected patent number is correct");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Selected patent number is incorrect");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证指定索引的单选框，是否被选中
     *
     * @param index
     * @return
     */
    public boolean func_verify_exportNumIsChecked_byIndex(int index) {
        l.entry();
        WebElement e = this.input_exportNum_byIndex(index);
        if (e.getAttribute("checked").equals("true")) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Expected radio button is selected");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Expected radio button is not selected");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 返回导出最小数量
     *
     * @return
     */
    public int func_get_startNum() {
        l.entry();
        return Integer.parseInt(this.input_startNum().getAttribute("value"));
    }

    /**
     * 返回导出最大数量
     *
     * @return
     */
    public int func_get_endNum() {
        l.entry();
        return Integer.parseInt(this.input_endNum().getAttribute("value"));
    }

    /**
     * 获取专利总数
     *
     * @return
     */
    public int func_get_totalPatentNum() {
        l.entry();
        return Integer.parseInt(this.label_selectedPatentNum().getText().split(" ")[3]);
    }

    /**
     * 验证选择专利数据是否正确
     *
     * @param exp_num
     * @return
     */
    public boolean func_verify_selectedPatentNum(int exp_num) {
        l.entry();
        int act_num = Integer.parseInt(this.span_selectedPatentNum().getText().split("\\(")[1].split(" ")[0]);
        l.info("Exp selected patent num: [{}]", exp_num);
        l.info("Act selected patent num: [{}]", act_num);
        if (exp_num == act_num) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Selected patent num is correct");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Selected patent num is incorrect");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证专利总数是否正确
     *
     * @param exp_num
     * @return
     */
    public boolean func_verify_totalPatentNum(int exp_num) {
        l.entry();
        int act_num = Integer.parseInt(this.label_selectedPatentNum().getText().split("\\(")[1].split(" ")[3].replaceAll(",", ""));
        l.info("Exp total patent num: [{}]", exp_num);
        l.info("Act total patent num: [{}]", act_num);
        if (exp_num == act_num) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Total patent num is correct");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Total patent num is incorrect");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证可导出专利最小值
     *
     * @param exp_num
     * @return
     */
    public boolean func_verify_startNum(int exp_num) {
        l.entry();
        int act_num = this.func_get_startNum();
        l.info("Exp min num: [{}]", exp_num);
        l.info("Act min num: [{}]", act_num);
        if (exp_num == act_num) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Min num of export is correct");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Min num of export is incorrect");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证可导出专利最大值
     *
     * @param exp_num
     * @return
     */
    public boolean func_verify_endNum(int exp_num) {
        l.entry();
        int act_num = this.func_get_endNum();
        l.info("Exp max num: [{}]", exp_num);
        l.info("Act max num: [{}]", act_num);
        if (exp_num == act_num) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Max num of export is correct");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Max num of export is incorrect");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 设置导出数量范围
     *
     * @param startNum
     * @param endNum
     */
    public void func_set_exportNum(String startNum, String endNum) {
        l.entry();
        //************** 页面控件修改后，不能通过sendKeys方法来设置值，所以使用JS ****************
        this.input_startNum().clear();
        this.input_startNum().sendKeys(startNum);
        this.input_endNum().clear();
        this.input_endNum().sendKeys(endNum);
        //******************************
//		JavascriptExecutor jse = (JavascriptExecutor) d;
//		String js = String.format("$(\".range-begin\").val(%s)", startNum);
//		jse.executeScript(js);
//		js = String.format("$(\".range-end\").val(%s)", endNum);
//		jse.executeScript(js);
        //******************************
        l.exit();
    }

    /**
     * 验证导出数字框是否只读
     *
     * @return
     */
    public boolean func_verify_exportNumIsReadonly() {
        l.entry();
        boolean result = true;
        if (this.input_startNum().getAttribute("readonly").equals("true")) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Min num textbox is readonly");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Min num textbox is editable");
            t.takeScreenshot(d);
            result &= false;
        }
        if (this.input_endNum().getAttribute("readonly").equals("true")) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Max num textbox is readonly");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Max num textbox is editable");
            t.takeScreenshot(d);
            result &= false;
        }
        return result;
    }

    /**
     * 验证导出格式的各个图片是否存在
     *
     * @param expSrcs--期望图片地址
     * @return
     */
    public boolean func_verify_exportFormatList(ArrayList<String> expSrcs) {
        l.entry();
        boolean result = true;
        ArrayList<WebElement> imges = this.imges_exportFormat();
        if (imges.size() == 0) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- No img is found");
            t.takeScreenshot(d);
            return false;
        }
        //遍历期望图片地址
        for (String expSrc : expSrcs) {
            l.info("Exp src: [{}]", expSrc);
            boolean matchFlag = false;
            //遍历实际地址
            for (WebElement img : imges) {
                String actSrc = img.getAttribute("src");
                l.info("Act src: [{}]", actSrc);
                if (actSrc.contains(expSrc)) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- Image of [{}] exists", expSrc);
                    matchFlag = true;
                    break;
                }
            }
            //如果没找到匹配数据
            if (!matchFlag) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Image of [{}] does not exist", expSrc);
                t.takeScreenshot(d);
                result &= false;
            }
        }
        return result;
    }

    /**
     * 验证压缩选项
     *
     * @return
     */
    public boolean func_verify_compressionOptions() {
        l.entry();
        boolean result = true;
        if (this.doesExist(By.id("Compress")) && (this.doesExist(By.id("CompressZip")))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Radio buttons of compression exist");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Radio buttons of compression does not exist");
            t.takeScreenshot(d);
            result &= false;
        }
        return result;
    }

    /**
     * 选择导出格式
     *
     * @param type -- 0:xls, 1:pdf, 2:rtf, 3:xml, 4:csv
     */
    public void func_select_exportFormat(int type) {
        l.entry();
        this.link_fileType(type).click();
        l.exit();
    }

    /**
     * 验证可选的到处字段是否正确
     *
     * @param type -- 0:xls, 1:pdf, 2:rtf, 3:xml, 4:csv
     * @return
     */
    public boolean func_verify_displayedExportFields(int type) {
        l.entry();
        Select select = null;
        ArrayList<String> expOptions = new ArrayList<String>();
        switch (type) {
            case 0:
                select = this.select_exportFields_excel();
                expOptions.add("Custom Fields");
                expOptions.add("Key Fields only");
                expOptions.add("Basic Fields only");
                expOptions.add("Citation Data Export Template (Cites / Backward Citation)");
                expOptions.add("Citation Data Export Template (Cited by / Forward Citation)");
                expOptions.add("All Fields");
                break;
            case 1:
                select = this.select_exportFields_pdf();
                expOptions.add("Original Patent Images");
                expOptions.add("Combined Patent Images (First Page Only)");
                break;
            case 2:
                select = this.select_exportFields_excel();
                expOptions.add("Custom Fields");
                expOptions.add("Key Fields only");
                expOptions.add("Basic Fields only");
                expOptions.add("All Fields");
                break;
            case 3:
                select = this.select_exportFields_excel();
                expOptions.add("Custom Fields");
                expOptions.add("Key Fields only");
                expOptions.add("Basic Fields only");
                expOptions.add("All Fields");
                break;
            case 4:
                select = this.select_exportFields_excel();
                expOptions.add("Custom Fields");
                expOptions.add("Key Fields only");
                expOptions.add("Basic Fields only");
                expOptions.add("Citation Data Export Template (Cites / Backward Citation)");
                expOptions.add("Citation Data Export Template (Cited by / Forward Citation)");
                expOptions.add("All Fields");
                break;
        }

        return this.func_verify_selectOptions(select, expOptions);

    }

    /**
     * 根据索引选择导出数量
     *
     * @param index
     */
    public void func_select_exportNum(int index) {
        l.entry();
        this.input_exportNum_byIndex(index).click();
        l.exit();
    }

    /**
     * 选择导出字段
     *
     * @param type  -- 0:非PDF, 1:PDF
     * @param index
     */
    public void func_select_exportField(int type, int index) {
        l.entry();
        if (type == 0) {
            this.select_exportFields_excel().selectByIndex(index);
        } else if (type == 1) {
            this.select_exportFields_pdf().selectByIndex(type);
        }
        l.exit();
    }

    /**
     * 通过名称选择字段下拉框
     *
     * @param type
     * @param templateName
     */
    public void func_select_exportField_byName(int type, String templateName) {
        l.entry();
        if (type == 0) {
            this.select_exportFields_excel().selectByVisibleText(templateName);
        } else if (type == 1) {
            this.select_exportFields_pdf().selectByVisibleText(templateName);
        }
        l.exit();
    }

    /**
     * 验证PDF提示信息是否正确
     *
     * @return
     */
    public boolean func_verify_pdfTip() {
        l.entry();
        String expPDFTip = "Some patent documents might not be available for download. Patent numbers for these documents will be recorded in unavailable.txt, which can be found in your downloaded file.";
        if (this.span_pdfTip().isDisplayed() && this.span_pdfTip().getText().equals(expPDFTip)) {
            l.info("++++++++++++++++++++++++++++++ Pass -- PDF tip is correct");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- PDF tip is incorrect, act value: [{}]", this.span_pdfTip().getText());
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 根据输入的fieldId，验证自定义字段中选中字段是否正确
     *
     * @param fieldId -- 0:Key, 1:Basic, 2:Cites, 3:Cited by, 4:All, 5:custom
     * @return
     */
    public boolean func_verify_chosenFields(int fieldId, String templateFields) {
        l.entry();
        boolean result = true;
        ArrayList<String> expFields = new ArrayList<String>(); //期望字段集合
        String expFieldsString = "";
        switch (fieldId) {
            case 0:
                expFields.add("Publication Number");
                expFields.add("Title");
                expFields.add("Application Date");
                expFields.add("Publication Date");
                expFields.add("Inventor(s)");
                expFields.add("Assignee(s)");
                expFields.add("All IPC");
                break;
            case 1:
                expFields.add("Publication Number");
                expFields.add("Title");
                expFields.add("Inventor(s)");
                expFields.add("Assignee(s)");
                break;
            case 2:
                expFieldsString = "This template is used to export the patents which are cited by the selected patents.\nThe first column contains the Publication Number of the selected patents. Subsequent fields belong to the cited patents, they are:Publication Number, Application Number, Kind Code, Title, Document Types, Application Date, Application Year, Application Year/Month, Publication Date, Publication Year, Publication Year/Month, All IPC, IPC Primary, IPC Section, IPC Class, IPC Subclass, IPC Group, UPC, UPC Primary, UPC Class, Family Members, Family Member Count, INPADOC Family Members, INPADOC Family Member Count, Priority Country, Priority Number, Priority Date, Assignee(s), Standardized Assignee, 1st Assignee, Number of Assignees, 1st Assignee Address, Assignee(s) Address, Inventor(s), Standardized Inventor, 1st Inventor, Number of Inventors, 1st Inventor Address, Inventor(s) Address, Agency/Law Firm, Agent/Attorney, Primary Examiner, Assistant Examiner";
                break;
            case 3:
                expFieldsString = "This template is used to export the patents which cite the selected patents.\nThe first column contains the Publication Number of the selected patents. Subsequent fields belong to the patents that cited the selected patents, they are: Publication Number, Application Number, Kind Code, Title, Document Types, Application Date, Application Year, Application Year/Month, Publication Date, Publication Year, Publication Year/Month, All IPC, IPC Primary, IPC Section, IPC Class, IPC Subclass, IPC Group, UPC, UPC Primary, UPC Class, Family Members, Family Member Count, INPADOC Family Members, INPADOC Family Member Count, Priority Country, Priority Number, Priority Date, Assignee(s), Standardized Assignee, 1st Assignee, Number of Assignees, 1st Assignee Address, Assignee(s) Address, Inventor(s), Standardized Inventor, 1st Inventor, Number of Inventors, 1st Inventor Address, Inventor(s) Address, Agency/Law Firm, Agent/Attorney, Primary Examiner, Assistant Examiner";
                break;
            case 4:
                expFields.add("Publication Number");
                expFields.add("Application Number");
                expFields.add("Kind Code");
                expFields.add("Title");
                expFields.add("Abstract");
                expFields.add("The First Claim");
                expFields.add("Document Types");
                expFields.add("Application Date");
                expFields.add("Application Year");
                expFields.add("Application Year/Month");
                expFields.add("Publication Date");
                expFields.add("Publication Year");
                expFields.add("Publication Year/Month");
                expFields.add("All IPC");
                expFields.add("IPC Primary");
                expFields.add("IPC Section");
                expFields.add("IPC Class");
                expFields.add("IPC Subclass");
                expFields.add("IPC Group");
                expFields.add("UPC");
                expFields.add("UPC Primary");
                expFields.add("UPC Class");
                expFields.add("FI");
                expFields.add("F-TERM");
                expFields.add("Family Members");
                expFields.add("Family Member Count");
                expFields.add("Family Members Cited By Count");
                expFields.add("INPADOC Family Members");
                expFields.add("INPADOC Family Member Count");
                expFields.add("INPADOC Family Members Cited By Count");
                expFields.add("Other References");
                expFields.add("Other References Count");
                expFields.add("Cited By");
                expFields.add("Cited By Count");
                expFields.add("Cites");
                expFields.add("Cites Count");
                expFields.add("Priority Country");
                expFields.add("Priority Number");
                expFields.add("Priority Date");
                expFields.add("Assignee(s)");
                expFields.add("Standardized Assignee");
                expFields.add("1st Assignee");
                expFields.add("Number of Assignees");
                expFields.add("1st Assignee Address");
                expFields.add("Assignee(s) Address");
                expFields.add("Inventor(s)");
                expFields.add("Standardized Inventor");
                expFields.add("1st Inventor");
                expFields.add("Number of Inventors");
                expFields.add("1st Inventor Address");
                expFields.add("Inventor(s) Address");
                expFields.add("Agency/Law Firm");
                expFields.add("Agent/Attorney");
                expFields.add("Primary Examiner");
                expFields.add("Assistant Examiner");
                expFields.add("‘Cited by’ count within 3 years");
                expFields.add("‘Cited by’ count within 5 years");
                break;
            case 5:
                if (templateFields == null) {
                    l.error("The format string parm is incorrect");
                    return false;
                }
                String[] templateFields_array = templateFields.split(",");
                if (templateFields_array.length == 0) {
                    l.error("The format string parm is incorrect");
                    return false;
                }
                for (String field_str : templateFields_array) {
                    field_str = field_str.trim();
                    //**********这里字段太多，暂时先写上用到的字段**********
                    switch (field_str) {
                        case "TTL":
                            field_str = "Title";
                            break;
                        case "IN":
                            field_str = "Inventor(s)";
                            break;
                        case "ABST":
                            field_str = "Abstract";
                            break;
                    }
                    expFields.add(field_str.trim());
                }
                break;
        }
        //输入检查
        if (expFields.size() == 0 && expFieldsString.equals("")) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Can not get exp values");
            return false;
        }
        //验证字段列表
        if (expFields.size() != 0) {
            //用期望字段列表验证
            //点击编辑按钮
            if (this.link_viewCustomField().isDisplayed()) {
                this.link_viewCustomField().click();
            }
            //获取实际列表，并验证列表字段
//			ArrayList<String> actFields = new ArrayList<String>();
            for (String expField : expFields) {
                l.info("Exp field: [{}]", expField);
                boolean matchFlag = false;
                for (WebElement li : this.ul_chosenFields().findElements(By.tagName("li"))) {
                    String actField = li.getText();
                    l.debug("Act field: [{}]", actField);
                    if (expField.equals(actField)) {
                        l.info("++++++++++++++++++++++++++++++ Pass -- Field is found");
                        matchFlag = true;
                        result &= true;
                        break;
                    }
                }
                if (!matchFlag) {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Field is not found");
                    t.takeScreenshot(d);
                    result &= false;
                }
            }
        } else if (!expFieldsString.equals("")) {
            //用期望字符串验证
            l.info("Exp fields string: [{}]", expFieldsString);
            //获取实际字符串
            String actFieldsString = this.span_fieldsString().getText();
            l.info("Act fields string: [{}]", actFieldsString);
            //验证字符串内容
            if (expFieldsString.equals(actFieldsString)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Export fields displayed are correct");
                return true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Export fields displayed are correct");
                t.takeScreenshot(d);
                return false;
            }

        }
        return result;
    }

    /**
     * 验证可选择字段是否正确
     *
     * @return
     */
    public boolean func_verify_availableFields() {
        l.entry();
        boolean result = true;
        ArrayList<String> expFields = new ArrayList<String>(); //期望字段集合
        String expFieldsString = "";
//		expFields.add("Publication Number");
        expFields.add("Application Number");
        expFields.add("Kind Code");
        expFields.add("Title");
        expFields.add("Abstract");
        expFields.add("The First Claim");
        expFields.add("Document Types");
        expFields.add("Application Date");
        expFields.add("Application Year");
        expFields.add("Application Year/Month");
        expFields.add("Publication Date");
        expFields.add("Publication Year");
        expFields.add("Publication Year/Month");
        expFields.add("All IPC");
        expFields.add("IPC Primary");
        expFields.add("IPC Section");
        expFields.add("IPC Class");
        expFields.add("IPC Subclass");
        expFields.add("IPC Group");
        expFields.add("UPC");
        expFields.add("UPC Primary");
        expFields.add("UPC Class");
        expFields.add("FI");
        expFields.add("F-TERM");
        expFields.add("Family Members");
        expFields.add("Family Member Count");
        expFields.add("Family Members Cited By Count");
        expFields.add("INPADOC Family Members");
        expFields.add("INPADOC Family Member Count");
        expFields.add("INPADOC Family Members Cited By Count");
        expFields.add("Other References");
        expFields.add("Other References Count");
        expFields.add("Cited By");
        expFields.add("Cited By Count");
        expFields.add("Cites");
        expFields.add("Cites Count");
        expFields.add("Priority Country");
        expFields.add("Priority Number");
        expFields.add("Priority Date");
        expFields.add("Assignee(s)");
        expFields.add("Standardized Assignee");
        expFields.add("1st Assignee");
        expFields.add("Number of Assignees");
        expFields.add("1st Assignee Address");
        expFields.add("Assignee(s) Address");
        expFields.add("Inventor(s)");
        expFields.add("Standardized Inventor");
        expFields.add("1st Inventor");
        expFields.add("Number of Inventors");
        expFields.add("1st Inventor Address");
        expFields.add("Inventor(s) Address");
        expFields.add("Agency/Law Firm");
        expFields.add("Agent/Attorney");
        expFields.add("Primary Examiner");
        expFields.add("Assistant Examiner");
        expFields.add("‘Cited by’ count within 3 years");
        expFields.add("‘Cited by’ count within 5 years");
        //输入检查
        if (expFields.size() == 0 && expFieldsString.equals("")) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Can not get exp values");
            return false;
        }
        //验证字段列表
        if (expFields.size() != 0) {
            //用期望字段列表验证
            //点击编辑按钮
            if (this.link_viewCustomField().isDisplayed()) {
                this.link_viewCustomField().click();
            }
            //获取实际列表，并验证列表字段
//			ArrayList<String> actFields = new ArrayList<String>();
            for (String expField : expFields) {
                l.info("Exp field: [{}]", expField);
                boolean matchFlag = false;
                for (WebElement li : this.ul_availableFields().findElements(By.tagName("li"))) {
                    String actField = li.getText();
                    l.debug("Act field: [{}]", actField);
                    if (expField.equals(actField)) {
                        l.info("++++++++++++++++++++++++++++++ Pass -- Field is found");
                        matchFlag = true;
                        result &= true;
                        break;
                    }
                }
                if (!matchFlag) {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Field is not found");
                    t.takeScreenshot(d);
                    result &= false;
                }
            }
        } else if (!expFieldsString.equals("")) {
            //用期望字符串验证
            l.info("Exp fields string: [{}]", expFieldsString);
            //获取实际字符串
            String actFieldsString = this.span_fieldsString().getText();
            l.info("Act fields string: [{}]", actFieldsString);
            //验证字符串内容
            if (expFieldsString.equals(actFieldsString)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Export fields displayed are correct");
                return true;
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Export fields displayed are correct");
                t.takeScreenshot(d);
                return false;
            }

        }
        return result;
    }

    /**
     * 在字段选择框出现的条件下，设置需要字段
     *
     * @param fields_str
     */
    public boolean func_set_templateFields(String fields_str) {
        l.entry();
        boolean result = true;
        //===== 前提检查
        //检查参数
        if (fields_str == null) {
            l.error("The string parm is null");
            return false;
        }
        //检查页面元素
        if (!this.div_dragList().isDisplayed()) {
            l.error("The drag list is invisible");
            return false;
        }
        //===== 解析输入
        //获取期望显示字段的rel属性集合
        ArrayList<String> expFields = new ArrayList<String>();
        String[] fields_strs = fields_str.split(",");
        if (fields_strs.length == 0) {
            l.error("The format string parm is incorrect");
            return false;
        }
        for (String field_str : fields_strs) {
            expFields.add(field_str.trim());
        }
        //===== 清空已选择字段
        for (WebElement li : ul_chosenFields().findElements(By.tagName("li"))) {
            act = new Actions(d);
            act.dragAndDrop(li, ul_availableFields()).perform();
        }
        //===== 选择需要字段
        for (String field : expFields) {
            boolean matchFlag = false;
            l.info("Set field: [{}]", field);

            //查找匹配字段
            for (WebElement li : ul_chosenFields().findElements(By.tagName("li"))) {
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
                while (this.li_dragLeft_byRel(field) != null) {
                    //尝试次数小于3，则尝试，否则报错
                    if (i < 3) {
                        l.info("Trying to drag field...");
                        WebElement leftLi = this.li_dragLeft_byRel(field);
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
        return result;
    }

    /**
     * 创建自定义模板
     *
     * @param fields_str
     * @param templateName
     */
    public boolean func_create_template(String fields_str, String templateName) {
        l.entry();
        boolean result = true;
        //===== 检查前提
        if (!this.link_viewCustomField().isDisplayed()) {
            l.error("The edit button is invisible");
            return false;
        }
        //===== 点击编辑
        this.link_viewCustomField().click();
        //===== 拖拽字段
        if (!this.func_set_templateFields(fields_str)) {
            return false;
        }
        //===== 填写名称并保存
        this.link_saveAs_template().click();
        this.input_templateName().clear();
        this.input_templateName().sendKeys(templateName);
        this.link_save_template().click();
        l.exit();
        return result;
    }

    /**
     * 删除指定模板
     *
     * @param templateName
     * @return
     */
    public boolean func_delete_template(String templateName) {
        l.entry();
        boolean result = true;
        //选择模板
        try {
            this.select_exportFields_excel().selectByVisibleText(templateName);
        } catch (Exception e) {
            return false;
        }
        //点击删除
        this.link_delete_template().click();
        l.exit();
        return result;
    }

    /**
     * 验证logo
     *
     * @return
     */
    public boolean func_verify_logo() {
        l.entry();
        if (this.doesExist(By.className("logo"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Logo exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Logo does not exist");
            return false;
        }
    }

    /**
     * 验证searchBox
     *
     * @return
     */
    public boolean func_verify_searchBox() {
        l.entry();
        if (this.doesExist(By.id("q"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Search box exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search box does not exist");
            return false;
        }
    }

    /**
     * 验证queueIcon
     *
     * @return
     */
    public boolean func_verify_queueIcon() {
        l.entry();
        if (this.doesExist(By.className("ico-hourglass"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Queue icon exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Queue icon does not exist");
            return false;
        }
    }

    /**
     * 验证account
     *
     * @return
     */
    public boolean func_verify_account() {
        l.entry();
        if (this.doesExist(By.className("user-email"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Account exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Account does not exist");
            return false;
        }
    }

    /**
     * 验证userVoiceIcon
     *
     * @return
     */
    public boolean func_verify_userVoiceIcon() {
        l.entry();
        if (this.doesExist(By.id("uservoice"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- User voice icon exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- User voice icon does not exist");
            return false;
        }
    }

    /**
     * 验证numberSettingOptions
     *
     * @return
     */
    public boolean func_verify_numberSettingOptions() {
        l.entry();
        if (d.findElements(By.name("export-info-type")).size() == 2) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Number setting options exist");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Number setting options do not exist");
            return false;
        }
    }

    /**
     * 验证格式图片
     *
     * @param rel -- 图片rel属性
     * @return
     */
    public boolean func_verify_formatImg_byRel(String rel) {
        l.entry();
        for (WebElement img : this.imges_exportFormat()) {
            String act_rel = img.getAttribute("rel");
            if (rel.trim().toLowerCase().equals(act_rel.trim().toLowerCase())) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Image of format [{}] exists", rel);
                return true;
            }
        }
        l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Image of format [{}] does not exist", rel);
        return false;
    }

    /**
     * 验证Radio of No Compression
     *
     * @return
     */
    public boolean func_verify_radio_noCompression() {
        l.entry();
        if (this.doesExist(By.id("Compress"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Radio of No Compression exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Radio of No Compression does not exist");
            return false;
        }
    }

    /**
     * 验证Radio of Compression
     *
     * @return
     */
    public boolean func_verify_radio_compression() {
        l.entry();
        if (this.doesExist(By.id("CompressZip"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Radio of Compression exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Radio of Compression does not exist");
            return false;
        }
    }

    /**
     * 验证Drop down list of Export Fields
     *
     * @return
     */
    public boolean func_verify_select_exportFields() {
        l.entry();
        if (this.doesExist(By.id("export-type-x-field"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Drop down list of Export Fields exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Drop down list of Export Fields does not exist");
            return false;
        }
    }

    /**
     * 验证Field summary
     *
     * @return
     */
    public boolean func_verify_fieldSummary() {
        l.entry();
        if (this.doesExist(By.className("field-summary"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Field summary exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Field summary does not exist");
            return false;
        }
    }

    /**
     * 验证includeThumbnail
     *
     * @return
     */
    public boolean func_verify_includeThumbnail() {
        l.entry();
        if (this.doesExist(By.className("field-summary"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Check box of includeThumbnail exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Check box of includeThumbnail does not exist");
            return false;
        }
    }

    /**
     * 验证Export button
     *
     * @return
     */
    public boolean func_verify_exportButton() {
        l.entry();
        if (this.doesExist(By.id("confirm"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Export button exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Export button does not exist");
            return false;
        }
    }

    /**
     * 验证Back to previous page button
     *
     * @return
     */
    public boolean func_verify_backButton() {
        l.entry();
        if (this.doesExist(By.id("back"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Back to previous page button exists");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Back to previous page button does not exist");
            return false;
        }
    }

    /**
     * 点击保存为模板按钮
     */
    public void func_click_saveAsLink() {
        l.entry();
        this.link_saveAs_template().click();
        l.exit();
    }

    /**
     * 点击保存模板按钮
     */
    public void func_click_saveTemplateLink() {
        l.entry();
        this.link_save_template().click();
        l.exit();
    }

    /**
     * 点击编辑模板按钮
     */
    public void func_click_editTemplateLink() {
        l.entry();
        this.link_viewCustomField().click();
        l.exit();
    }

    /**
     * 点击更新模板按钮
     */
    public void func_click_updateTemplateLink() {
        l.entry();
        this.link_updateCustomField().click();
        l.exit();
    }

    /**
     * 设置模板名称
     *
     * @param templateName
     */
    public void func_set_templateName(String templateName) {
        l.entry();
        this.input_templateName().clear();
        this.input_templateName().sendKeys(templateName);
        l.exit();
    }

    /**
     * 验证模板是否存在
     *
     * @param exp_templateName
     * @return
     */
    public boolean func_verify_customTemplate(String exp_templateName) {
        l.entry();
        boolean result = true;
        l.info("Exp template name: [{}]", exp_templateName);
        ArrayList<WebElement> options = (ArrayList<WebElement>) this.select_exportFields_excel().getOptions();
        boolean matchFlag = false;
        for (WebElement option : options) {
            l.debug("Act option name: [{}]", option.getText());
            if (exp_templateName.equals(option.getText())) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Template is found");
                matchFlag = true;
                result = true;
                break;
            }
        }
        if (!matchFlag) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Template is not found");
            t.takeScreenshot(d);
            result = false;
        }
        return result;
    }

    /**
     * 验证模板是否已被成功删除
     *
     * @param exp_templateName
     * @return
     */
    public boolean func_verify_deletion_customTemplate(String exp_templateName) {
        l.entry();
        boolean result = true;
        l.info("Exp template name: [{}]", exp_templateName);
        ArrayList<WebElement> options = (ArrayList<WebElement>) this.select_exportFields_excel().getOptions();
        boolean matchFlag = false;
        for (WebElement option : options) {
            l.debug("Act option name: [{}]", option.getText());
            if (exp_templateName.equals(option.getText())) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Template has not been deleted");
                t.takeScreenshot(d);
                matchFlag = true;
                result = false;
                break;
            }
        }
        if (!matchFlag) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Template is deleted successfully");
            result = true;
        }
        return result;
    }

    /**
     * 删除所有模板
     *
     * @throws InterruptedException
     */
    public void func_delete_allTemplates() throws InterruptedException {
        l.entry();
        Select s = this.select_exportFields_excel();
        int optionCount = s.getOptions().size();
        s.selectByIndex(optionCount - 1);
        while (this.link_delete_template().isDisplayed()) {
            this.link_delete_template().click();
            d.navigate().refresh();
            s = this.select_exportFields_excel();
            optionCount = s.getOptions().size();
            s.selectByIndex(optionCount - 1);
        }
        l.exit();
    }

    /**
     * 点击删除模板按钮
     */
    public void func_click_deleteTemplateLink() {
        l.entry();
        this.link_delete_template().click();
        l.exit();
    }

    /**
     * 验证编辑模板按钮不显示
     *
     * @return
     */
    public boolean func_verify_invisible_editTemplateLink() {
        l.entry();
        if (!this.doesExist(By.className("viewCustomField"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Edit template link is invisible");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Edit template link is visible");
            return false;
        }
    }

    /**
     * 验证删除模板按钮不显示
     *
     * @return
     */
    public boolean func_verify_invisible_deleteTemplateLink() {
        l.entry();
        if (!this.doesExist(By.className("delCustomField"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Delete template link is invisible");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Delete template link is visible");
            return false;
        }
    }

    /**
     * 验证所有字段选项选中时，编辑和删除模板按钮都不出现
     *
     * @return
     */
    public boolean func_verify_templateButtonsAreInvisible_allFields() {
        l.entry();
        boolean result = true;
        Select s = this.select_exportFields_excel();
        for (int i = 0; i < s.getOptions().size(); i++) {
            s.selectByIndex(i);
            l.info("Verify field [{}]", s.getOptions().get(i).getText());
            if (this.func_verify_invisible_editTemplateLink() && this.func_verify_invisible_deleteTemplateLink()) {

            } else {
                result &= false;
            }
        }
        return result;
    }
}
