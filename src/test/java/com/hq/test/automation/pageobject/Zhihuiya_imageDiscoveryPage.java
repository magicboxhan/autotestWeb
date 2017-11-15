package com.hq.test.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 图像探索页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_imageDiscoveryPage extends Zhihuiya_basePage {

    public Zhihuiya_imageDiscoveryPage(WebDriver driver) {
        l.entry();
        d = driver;
        selfcheckSelector = By.id("keyword");
        l.exit();
    }

    /********** Elements **********/

    /**
     * 文本框 -- 查询
     *
     * @return
     */
    public WebElement input_keyword() {
        l.entry();
        l.exit();
        return d.findElement(By.id("keyword"));
    }


    /********** Functions **********/

    /**
     * 关键字搜索
     *
     * @param keyword
     */
    public void func_searchKeyword(String keyword) {
        l.entry();
        this.input_keyword().clear();
        this.input_keyword().sendKeys(keyword);
        this.input_keyword().submit();
        l.exit();
    }

}
