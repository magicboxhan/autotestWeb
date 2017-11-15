package com.hq.test.automation.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * 搜索历史页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_historyPage extends Zhihuiya_basePage {

    public Zhihuiya_historyPage(WebDriver driver) {
        l.entry();
        d = driver;
        act = new Actions(d);
        selfcheckSelector = By.className("user-center-menu");
        l.exit();
    }

    /********** Elements **********/

    /**
     * 列表--搜索历史列表
     *
     * @return
     */
    public WebElement table_history() {
        l.entry();
        return d.findElement(By.className("history-table"));
    }

    /********** Functions **********/

    /**
     * 验证历史记录列表中是否存在输入关键字
     *
     * @param exp_content
     * @return
     */
    public boolean func_verify_contextExistInHistory(String exp_content) {
        l.entry();
        exp_content = exp_content.toLowerCase();
        l.info("Expected content: {}", exp_content);
        List<WebElement> trs = this.table_history().findElements(By.tagName("tr"));
        int dataRowNum = trs.size() - 1;
        if (dataRowNum == 0) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- No data exists in history table");
            t.takeScreenshot(d);
            return false;
        }
        String act_content = "";
        for (int i = 0; i < dataRowNum - 1; i++) {
            if (trs.get(i + 2).findElements(By.tagName("td")).size() == 3) {
                act_content = trs.get(i + 2).findElements(By.tagName("td")).get(2).getText().toLowerCase();
                l.info("Current actual content: {}", act_content);
                if (act_content.contains(exp_content)) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- Content is found");
                    return true;
                }
            }
        }

        l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Content is not found");
        t.takeScreenshot(d);
        return false;
    }

    /**
     * 验证指定行中不存在指定信息
     *
     * @param exp_content
     * @param rowIndex
     * @return
     */
    public boolean func_verify_contextDoesNotExistInHistory_atRow(String exp_content, int rowIndex) {
        l.entry();
        l.info("Expected content: {}", exp_content);
        List<WebElement> trs = this.table_history().findElements(By.tagName("tr"));
        int dataRowNum = trs.size() - 1;
        if (dataRowNum == 0) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- No data exists in history table");
            t.takeScreenshot(d);
            return false;
        }
        String act_content = "";
        act_content = trs.get(rowIndex + 2).findElements(By.tagName("td")).get(2).getText();
        l.info("Current actual content: {}", act_content);
        if (act_content.contains(exp_content)) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Content is found");
            t.takeScreenshot(d);
            return false;
        } else {
            l.info("++++++++++++++++++++++++++++++ Pass -- Content is not found");
            return true;
        }
    }

    /**
     * 验证期望query是否与搜索历史中的一致
     *
     * @param exp_query
     * @return
     */
    public boolean func_verify_queryInHistory(String exp_query) {
        l.entry();
        l.info("Expected last query (history page): {}", exp_query);
        List<WebElement> trs = this.table_history().findElements(By.tagName("tr"));
        int dataRowNum = trs.size() - 1;
        if (dataRowNum == 0) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- No data exists in history table (history page)");
            t.takeScreenshot(d);
            return false;
        }
        String act_query = "";
        for (int i = 0; i < dataRowNum; i++) {
            if (trs.get(i + 2).findElements(By.tagName("td")).get(2).findElements(By.tagName("a")).size() != 0) {
                act_query = trs.get(i + 2).findElements(By.tagName("td")).get(2).findElement(By.tagName("a")).getText();
                l.info("Current actual query (history page): {}", act_query);
                if (exp_query.equals(act_query)) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- Query is found (history page)");
                    return true;
                }
            }
        }

        l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Query is not found (history page)");
        t.takeScreenshot(d);
        return false;
    }

    /**
     * 验证query是否存在，同时验证时间是否正确
     *
     * @param exp_query
     * @param exp_hour
     * @param exp_ampm
     * @return
     */
    public boolean func_verify_queryInHistory_export(String exp_query, int exp_hour, int exp_ampm) {
        l.entry();
        l.info("Expected last query (history page): {}", exp_query);
        List<WebElement> trs = this.table_history().findElements(By.tagName("tr"));
        int dataRowNum = trs.size() - 1;
        if (dataRowNum == 0) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- No data exists in history table (history page)");
            t.takeScreenshot(d);
            return false;
        }
        String act_query = "";
        for (int i = 0; i < dataRowNum; i++) {
            if (trs.get(i + 2).findElements(By.tagName("td")).get(2).findElements(By.tagName("a")).size() != 0) {
                act_query = trs.get(i + 2).findElements(By.tagName("td")).get(2).findElement(By.tagName("a")).getText();
                l.info("Current actual query (history page): {}", act_query);
                if ((act_query.contains(exp_query)) && (trs.get(i + 2).findElements(By.tagName("td")).get(2).getText().toLowerCase().contains("export"))) {
                    l.info("++++++++++++++++++++++++++++++ Pass -- Query is found (history page)");
                    //获取时间字符串
                    String act_timeStr = trs.get(i + 2).findElements(By.tagName("td")).get(1).getText();
                    //获取小时
                    int act_hour = Integer.valueOf(act_timeStr.split(":")[0]);
                    //验证小时
                    l.info("===== verify export time =====");
                    boolean result = true;
                    l.info("Exp export hour: [{}]", exp_hour);
                    l.info("Act export hour: [{}]", act_hour);
                    if ((act_hour <= exp_hour + 1) && (act_hour >= exp_hour - 1)) {
                        l.info("++++++++++++++++++++++++++++++ Pass -- Export time (hour) is correct");
                    } else {
                        l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Export time (hour) is incorrect");
                        t.takeScreenshot(d);
                        result &= false;
                    }
                    //获取ampm
                    String act_ampm_str = act_timeStr.split(" ")[1].trim();
                    int act_ampm = -1;
                    if (act_ampm_str.toLowerCase().equals("am")) {
                        act_ampm = 0;
                    } else if (act_ampm_str.toLowerCase().equals("pm")) {
                        act_ampm = 1;
                    }
                    //验证ampm
                    l.info("Exp export ampm: [{}]", exp_ampm);
                    l.info("Act export ampm: [{}]", act_ampm);
                    if (exp_ampm == act_ampm) {
                        l.info("++++++++++++++++++++++++++++++ Pass -- Export time (am_pm) is correct");
                    } else {
                        l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Export time (am_pm) is incorrect");
                        t.takeScreenshot(d);
                        result &= false;
                    }
                    return result;
                }
            }
        }

        l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Query is not found (history page)");
        t.takeScreenshot(d);
        return false;
    }

    /**
     * 点击query链接
     *
     * @param query
     */
    public void func_click_query(String query) {
        l.entry();
        this.table_history().findElement(By.partialLinkText(query)).click();
        l.exit();
    }
}