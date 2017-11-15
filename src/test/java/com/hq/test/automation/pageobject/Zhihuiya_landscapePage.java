package com.hq.test.automation.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * landscape页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_landscapePage extends Zhihuiya_basePage {

    public Zhihuiya_landscapePage(WebDriver driver) {
        l.entry();
        d = driver;
        selfcheckSelector = By.className("section-submit");
        l.exit();
    }

    /********** Elements **********/

    /**
     * li -- 查询数量
     *
     * @param value -- 需要选择的数量
     * @return
     */
    public WebElement li_selectSamping(String value) {
        l.entry();
        List<WebElement> lis = d.findElement(By.className("section-simping")).findElements(By.tagName("li"));
        WebElement li_selectSimping = null;
        for (WebElement li : lis) {
            WebElement strong = li.findElement(By.tagName("strong"));
            if (strong.getText().equals(value)) {
                li_selectSimping = li;
                break;
            }
        }
        if (li_selectSimping == null) {
            l.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< li_selectSimping is not found");
        }
        return li_selectSimping;
    }

    /**
     * 按钮 -- 生成 landscape
     *
     * @return
     */
    public WebElement button_loadLandscape() {
        l.entry();
        l.debug("button is {}", d.findElement(By.className("section-submit")).findElement(By.className("btn-26")));
        return d.findElement(By.className("section-submit")).findElement(By.className("btn-26"));
    }

    /**
     * 链接 -- 关闭
     *
     * @return
     */
    public WebElement link_nav_close() {
        l.entry();
        return d.findElement(By.className("nav-close")).findElement(By.tagName("a"));
    }

    /**
     * span -- 提示信息
     *
     * @return
     */
    public WebElement span_content() {
        l.entry();
        return d.findElement(By.className("content"));
    }

    /**
     * textarea -- 查询
     *
     * @return
     */
    public WebElement textarea_query() {
        l.entry();
        return d.findElement(By.tagName("textarea"));
    }

    /********** Functions **********/

    /**
     * @param value
     * @return
     * @throws Exception
     */
    public boolean func_loadLandscape(String keyword, String value, int timeout, int retryTimes) throws Exception {
        l.entry();
        l.info("Loading landscape. Query: {}. Number of Documents: {}", keyword, value);
        int i = 0;
        while (!this.textarea_query().isDisplayed()) {
            if (i < 60) {
                Thread.sleep(1000);
                i++;
                l.debug("Waiting for landscape query textarea...");
            } else {
                l.debug("Wait timeout");
                break;
            }
        }
        this.textarea_query().clear();
        this.textarea_query().sendKeys(keyword);
        if (this.li_selectSamping(value) == null) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Element [Number of documents] is not found");
            t.takeScreenshot(d);
            return false;
        }
        this.li_selectSamping(value).click();
        Thread.sleep(2000);
        //点击查询按钮，开始查询
        boolean result = false;
        boolean timeout_flag = false;
        long duration = 0;
        this.button_loadLandscape().click();
        //正式进入循环
        int j = 0;
        while (((!result) || (timeout_flag)) && (j < retryTimes)) {
            l.info("Trying to get result for the [{}] time......", j + 1);
            long startTime = System.currentTimeMillis();
            int watiTime = 0;
            //当提示信息不存在时，判断是否已经成功
            while (!this.doesExist(By.className("content"))) {
                //如果已经成功，则跳出循环
                if (this.doesExist(By.className("group-clear"))) {
                    result = true;
                    duration = System.currentTimeMillis() - startTime;
                    break;
                } else if (watiTime < timeout) {
                    //不成功，则判断是否超时，超时则报错，不超时则等待
                    l.debug("Waiting for result...");
                    watiTime++;
                } else {
                    //超时，则报错，跳出循环
                    result = false;
                    timeout_flag = true;
                    break;
                }
            }
            j++;
        }

        //如果超时，则报错
        if (timeout_flag) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Timeout");
            t.takeScreenshot(d);
        } else if (result) {
            //非超时导致的跳出循环，可能是出现提示信息，或者查询出结果了。这里先判断是否已查询到结果
//			long duration = System.currentTimeMillis() - startTime;
            l.info("++++++++++++++++++++++++++++++ Pass -- Landscape is load successfully. The time of analysis for {} is {} seconds", value, duration / 1000);
        } else {
            //最后剩下一种可能，报错了
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- The error message is: {}", this.span_content().getText());
            t.takeScreenshot(d);
            result = false;
        }

        return result;
    }

    /**
     * @param value
     * @return
     * @throws Exception
     */
    public boolean func_loadLandscape_userlist(int timeout, int retryTimes) throws Exception {
        l.entry();
        l.info("Loading landscape for userlist");
        //点击查询按钮，开始查询
        boolean result = false;
        boolean timeout_flag = false;
        long duration = 0;
        Thread.sleep(5000);
        this.button_loadLandscape().click();
        //正式进入循环
        int j = 0;
        while (((!result) || (timeout_flag)) && (j < retryTimes)) {
            l.info("Trying to get result for the [{}] time......", j + 1);
            long startTime = System.currentTimeMillis();
            int watiTime = 0;
            //当提示信息不存在时，判断是否已经成功
            while (!this.doesExist(By.className("content"))) {
                //如果已经成功，则跳出循环
                if (this.doesExist(By.className("group-clear"))) {
                    result = true;
                    duration = System.currentTimeMillis() - startTime;
                    break;
                } else if (watiTime < timeout) {
                    //不成功，则判断是否超时，超时则报错，不超时则等待
                    l.debug("Waiting for result...");
                    watiTime++;
                } else {
                    //超时，则报错，跳出循环
                    result = false;
                    timeout_flag = true;
                    break;
                }
            }
            j++;
        }

        //如果超时，则报错
        if (timeout_flag) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Timeout");
            t.takeScreenshot(d);
        } else if (result) {
            //非超时导致的跳出循环，可能是出现提示信息，或者查询出结果了。这里先判断是否已查询到结果
//			long duration = System.currentTimeMillis() - startTime;
            l.info("++++++++++++++++++++++++++++++ Pass -- Landscape is load successfully. The time of analysis for userlist is {} seconds", duration / 1000);
        } else {
            //最后剩下一种可能，报错了
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- The error message is: {}", this.span_content().getText());
            t.takeScreenshot(d);
            result = false;
        }

        return result;
    }

    /**
     * 关闭当前查询
     */
    public void func_nav_close() {
        l.entry();
        this.link_nav_close().click();
        l.exit();
    }
}
