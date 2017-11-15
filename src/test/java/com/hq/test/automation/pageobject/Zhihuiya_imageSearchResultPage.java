package com.hq.test.automation.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 图像探索结果页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_imageSearchResultPage extends Zhihuiya_basePage {

    public Zhihuiya_imageSearchResultPage(WebDriver driver) {
        l.entry();
        d = driver;
        selfcheckSelector = By.className("result-right");
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

    /**
     * ul--搜索结果列表
     *
     * @return
     */
    public WebElement ul_imageList() {
        l.entry();
        l.exit();
        return d.findElement(By.className("result-patents"));
    }

    /**
     * 链接列表--探索按钮（图片列表页）
     *
     * @return
     */
    public List<WebElement> links_discover() {
        l.entry();
        l.exit();
        try {
            return d.findElements(By.cssSelector(".result-patent-action.icon-discover"));
        } catch (Exception e) {
            return null;
        }
    }


    /********** Functions **********/

    /**
     * 关键字搜索
     *
     * @param keyword
     */
    public void func_searchKeyword(String keyword) {
        l.entry();
        l.info("Search image by keyword [{}]", keyword);
        this.input_keyword().clear();
        this.input_keyword().sendKeys(keyword);
        this.input_keyword().submit();
        l.exit();
    }

    /**
     * 验证图片关键字搜索结果是否存在
     *
     * @return
     */
    public boolean func_verify_imageSearchResultExist() {
        l.entry();
        l.info("Verify image search result");
        boolean result = true;
        if (this.doesExist(By.className("result-patents"))) {
            if (this.ul_imageList().findElements(By.tagName("li")).size() != 0) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Image search result table exists");
            } else {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Image search result table does not exist");
                t.takeScreenshot(d, System.getProperty("user.dir"), "ImageSearchResultPage_func_verify_imageSearchResultExist", "jpg");
                result &= false;
            }
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Image search result table does not exist");
            t.takeScreenshot(d, System.getProperty("user.dir"), "ImageSearchResultPage_func_verify_imageSearchResultExist", "jpg");
            result &= false;
        }
        l.exit();
        return result;
    }

    /**
     * 点击探索链接--根据索引
     *
     * @param index
     */
    public void func_click_discover_ByIndex(int index) {
        l.entry();
        l.info("Click discover link [{}] in image search result table", index);
        this.links_discover().get(index).click();
        l.exit();
    }

}
