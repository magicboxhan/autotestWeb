package com.hq.test.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * 矩阵分析页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_matrixAnalysisPage extends Zhihuiya_basePage {

    public Zhihuiya_matrixAnalysisPage(WebDriver driver) {
        l.entry();
        d = driver;
        selfcheckSelector = By.name("title");
        l.exit();
    }


    /********** Elements **********/

    /**
     * 下拉框--x
     *
     * @return
     */
    public Select select_x() {
        l.entry();
        Select select = new Select(d.findElement(By.name("x")));
        return select;
    }

    /**
     * 下拉框--y
     *
     * @return
     */
    public Select select_y() {
        l.entry();
        Select select = new Select(d.findElement(By.name("y")));
        return select;
    }

    /**
     * 按钮--创建分析视图
     *
     * @return
     */
    public WebElement input_createChart() {
        l.entry();
        return d.findElement(By.cssSelector(".btn-26.primary"));
    }

    /**
     * 链接--查看分析视图
     *
     * @return
     */
    public WebElement link_viewChart() {
        l.entry();
        return d.findElement(By.className("section-process-waiting")).findElement(By.cssSelector(".btn-26.primary"));
    }


    /********** Functions **********/


    /**
     * 点击矩阵分析链接
     */
    public void func_createChart() {
        l.entry();
        this.select_x().selectByIndex(1);
        this.select_y().selectByIndex(1);
        this.input_createChart().click();
        l.exit();
    }

    /**
     * 矩阵分析结果
     *
     * @param timeout
     * @return
     * @throws Exception
     */
    public boolean func_verify_analysisResult(int timeout, int tryTimes) throws Exception {
        l.entry();
        boolean result = true;
        int i = 0; //超时
        int j = 0; //重试
        if (tryTimes == 0) {
            tryTimes = 1;
        }
        while (j < tryTimes) {
            i = 0;
            l.info("Trying for the [{}] time", j + 1);
            while (!this.link_viewChart().getAttribute("href").contains("/patents/analyze/view")) {
                if (i < timeout) {
                    Thread.sleep(1000);
                    i++;
                    l.debug("Waiting for analysis...");
                } else {
                    l.error("Timeout");
                    result = false;
                    break;
                }
            }
            if (!result) {
                d.navigate().refresh();
                j++;
            } else {
                break;
            }
        }
        if (result) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Analysis completed");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Timeout");
            t.takeScreenshot(d);
            return false;
        }

    }

    /**
     * 点击查看图表链接
     *
     * @return
     */
    public void func_click_viewChart() {
        l.entry();
        this.link_viewChart().click();
    }

}
