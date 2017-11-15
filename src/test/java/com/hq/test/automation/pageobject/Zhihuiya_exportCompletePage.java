package com.hq.test.automation.pageobject;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


/**
 * 导出成功页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_exportCompletePage extends Zhihuiya_basePage {

    public Zhihuiya_exportCompletePage(WebDriver driver) {
        l.entry();
        d = driver;
        act = new Actions(d);
        selfcheckSelector = By.className("export-file-info");
        l.exit();
    }

    /********** Elements **********/

    /**
     * 链接--下载
     *
     * @return
     */
    public WebElement link_download() {
        l.entry();
        return d.findElement(By.cssSelector(".btn-26.primary"));
    }

    /**
     * 表格--导出信息
     *
     * @return
     */
    public WebElement table_exportInfo() {
        l.entry();
        return d.findElement(By.className("export-file-info")).findElement(By.className("info-table"));
    }


    /********** Functions **********/

    /**
     * 获取导出信息
     *
     * @return
     */
    public HashMap<String, String> func_get_exportInfo() {
        l.entry();
        HashMap<String, String> returnHash = new HashMap<String, String>();
        //获取表格
        WebElement table = this.table_exportInfo();
        //遍历表格，获取数据
        for (WebElement tr : table.findElements(By.tagName("tr"))) {
            String key = tr.findElement(By.tagName("th")).getText();
            String value = tr.findElement(By.tagName("td")).getText();
            returnHash.put(key, value);
        }
        return returnHash;
    }

    /**
     * 验证导出信息
     *
     * @param query   -- 搜索语句
     * @param count   -- 导出数量
     * @param format  -- 导出格式
     * @param content -- 导出内容，列名
     * @param date    -- 导出日期
     * @return 是否验证通过
     * @throws ParseException
     */
    public boolean func_verify_exportInfo(String query, int count, String format, String content, Calendar date) throws ParseException {
        l.entry();
        boolean result = true;
        //获取导出信息表格数据
        HashMap<String, String> exportInfo = this.func_get_exportInfo();
        if ((exportInfo == null) || (exportInfo.size() == 0)) {
            l.error("Export info table is empty");
            return false;
        }
        //验证query
        l.info("Exp Search Query: [{}]", query);
        l.info("Act Search Query: [{}]", exportInfo.get("Search Query"));
        if (exportInfo.get("Search Query").contains(query)) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Search Query is correct");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Search Query is incorrect");
            result &= false;
        }
        //验证导出数量
        l.info("Exp Count: [{}]", count);
        l.info("Act Count: [{}]", exportInfo.get("Patent Number"));
        if (count == Integer.parseInt(exportInfo.get("Patent Number"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Count is correct");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Count is incorrect");
            result &= false;
        }
        //验证导出格式
        l.info("Exp Format: [{}]", format);
        l.info("Act Format: [{}]", exportInfo.get("Export Format"));
        if (format.toLowerCase().equals(exportInfo.get("Export Format").toLowerCase())) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Format is correct");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Format is incorrect");
            result &= false;
        }
        //验证导出内容
        l.info("Exp Content: [{}]", content);
        l.info("Act Content: [{}]", exportInfo.get("Content"));
        if (content.equals(exportInfo.get("Content"))) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Content is correct");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Content is incorrect");
            result &= false;
        }
        //验证导出日期
        int exp_year = date.get(Calendar.YEAR);
        int exp_date = date.get(Calendar.DAY_OF_YEAR);
        String act_calString = exportInfo.get("Export Date");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date act_dateOfCal = df.parse(act_calString);
        Calendar act_cal = Calendar.getInstance();
        act_cal.setTime(act_dateOfCal);
        int act_year = act_cal.get(Calendar.YEAR);
        int act_date = act_cal.get(Calendar.DAY_OF_YEAR);
        l.info("Exp year: [{}], date: [{}]", exp_year, exp_date);
        l.info("Act year: [{}], date: [{}]", act_year, act_date);
        if ((exp_year == act_year) && (exp_date == act_date)) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Export Date is correct");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Export Date is incorrect");
            result &= false;
        }
        //截图
        if (!result) {
            t.takeScreenshot(d);
        }
        //返回结果
        return result;
    }

    /**
     * 下载
     *
     * @param timeout
     * @throws InterruptedException
     */
    public boolean func_download(String filePath, int timeout) throws InterruptedException {
        l.entry();
        //删除所有文件
        File file = new File(filePath);
        if (file.isDirectory()) {
            //是文件夹
            String[] fileNames = file.list();
            for (String fileName : fileNames) {
                //尝试删除文件
                try {
                    File file_inFolder = new File(String.format("%s//%s", filePath, fileName));
                    file_inFolder.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
        int watiTime = 0;
        boolean isDownloadVisible = true;
        while (!this.doesExist(By.cssSelector(".btn-26.primary")) || (!this.isEnabled(By.cssSelector(".btn-26.primary"))) || (!this.isVisible(By.cssSelector(".btn-26.primary")))) {
            if (watiTime < timeout) {
                l.info("Waiting for download button...");
                Thread.sleep(1000);
                watiTime++;
            } else {
                isDownloadVisible = false;
                t.takeScreenshot(d);
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Wait timeout");
                return false;
            }
        }
        if (isDownloadVisible == true) {
            this.link_download().click();
            return true;
        }
        return false;
    }

    /**
     * 删除所有已下载文件
     *
     * @param filePath
     * @throws InterruptedException
     */
    public void func_deleteAllDownloadFiles(String filePath) throws InterruptedException {
        l.entry();
        //删除所有文件
        File file = new File(filePath);
        if (file.isDirectory()) {
            //是文件夹
            String[] fileNames = file.list();
            for (String fileName : fileNames) {
                //尝试删除文件
                try {
                    File file_inFolder = new File(String.format("%s//%s", filePath, fileName));
                    file_inFolder.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

}
