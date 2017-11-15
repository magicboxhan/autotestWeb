package com.hq.test.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 矩阵分析查看页面对像类
 *
 * @author hanqing
 */
public class Zhihuiya_viewChartPage extends Zhihuiya_basePage {

    public Zhihuiya_viewChartPage(WebDriver driver) {
        l.entry();
        d = driver;
        selfcheckSelector = By.id("chart-field");
        l.exit();
    }


    /********** Elements **********/


    /********** Functions **********/


}
