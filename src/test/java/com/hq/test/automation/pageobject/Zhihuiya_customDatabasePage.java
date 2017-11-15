package com.hq.test.automation.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * 自建库页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_customDatabasePage extends Zhihuiya_basePage {

    public Zhihuiya_customDatabasePage(WebDriver driver) {
        l.entry();
        d = driver;
        act = new Actions(d);
        selfcheckSelector = By.cssSelector(".db-card.new");
        l.exit();
    }

    /********** Elements **********/

    /**
     * 搜索结果总数
     *
     * @return
     */
    public WebElement span_searchResultNumber() {
        l.entry();
        return d.findElement(By.cssSelector(".num.total_num"));
    }

    /**
     * 链接--导出
     *
     * @return
     */
    public WebElement link_export() {
        l.entry();
        return d.findElement(By.className("btn-export-patents"));
    }

    /**
     * div--通过名字获取CDB
     *
     * @param name
     * @return
     */
    public WebElement div_CDB_byName(String name) {
        l.entry();
        WebElement return_div = null;
        boolean matchFlag = false;
        l.info("Expected custom database name is [{}]", name);
        List<WebElement> divs = d.findElements(By.cssSelector(".db-card.own"));
        if (divs.size() == 0) {
            l.info("Custom database div is not found");
            t.takeScreenshot(d);
            return null;
        }
        //验证自建库提醒是否存在
        for (WebElement div : divs) {
            String actName = div.findElement(By.className("db-name")).getAttribute("title");
            l.info("Current actual custom database name is [{}]", actName);
            if (actName.equals(name)) {
                l.info("Custom database is found");
                matchFlag = true;
                return_div = div;
                break;
            }
        }
        if (!matchFlag) {
            l.info("Custom database is not found");
            t.takeScreenshot(d);
            return null;
        }
        return return_div;
    }

    /**
     * div--通过名字获取CDB(被分享的)
     *
     * @param name
     * @return
     */
    public WebElement div_CDB_byName_shared(String name) {
        l.entry();
        WebElement return_div = null;
        boolean matchFlag = false;
        l.info("Expected custom database name is [{}]", name);
        List<WebElement> divs = d.findElements(By.cssSelector(".db-card.share"));
        if (divs.size() == 0) {
            l.info("Custom database div is not found");
            t.takeScreenshot(d);
            return null;
        }
        //验证自建库提醒是否存在
        for (WebElement div : divs) {
            String actName = div.findElement(By.className("db-name")).getAttribute("title");
            l.info("Current actual custom database name is [{}]", actName);
            if (actName.equals(name)) {
                l.info("Custom database is found");
                matchFlag = true;
                return_div = div;
                break;
            }
        }
        if (!matchFlag) {
            l.info("Custom database is not found");
            t.takeScreenshot(d);
            return null;
        }
        return return_div;
    }

    /**
     * input -- 过滤条件输入框
     *
     * @return
     */
    public WebElement input_filter() {
        l.entry();
        return d.findElement(By.id("quicksearch_keyword"));
    }

    /**
     * link -- 过滤条件搜索链接
     *
     * @return
     */
    public WebElement link_filter() {
        l.entry();
        return d.findElement(By.id("quicksearch_btn"));
    }

    /**
     * 链接--根据索引获取排序选项
     *
     * @param index
     * @return
     */
    public WebElement link_sortType_byIndex(int index) {
        l.entry();
        return d.findElement(By.className("drop-menu")).findElements(By.tagName("a")).get(index);
    }

    /**
     * 链接 --视图切换--通过Rel
     *
     * @param rel
     * @return
     */
    public WebElement link_viewType_byRel(String rel) {
        l.entry();
        List<WebElement> es = d.findElement(By.cssSelector(".m-btn-group.btn-views")).findElements(By.tagName("a"));
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
     * 文本框--页码
     *
     * @return
     */
    public WebElement input_pageNum() {
        l.entry();
        return d.findElement(By.name("jumpto"));
    }

    /**
     * 按钮 -- 页面跳转
     *
     * @return
     */
    public WebElement input_pageGo() {
        l.entry();
        return d.findElement(By.id("pagego"));
    }

    /**
     * 链接 -- 分享自建库
     *
     * @return
     */
    public WebElement link_share() {
        l.entry();
        return d.findElement(By.className("btn-share-manage"));
    }

    /**
     * 文本框 -- 被分享用户名
     *
     * @return
     */
    public WebElement input_sharedUID() {
        l.entry();
        return d.findElement(By.id("share_emails"));
    }

    /**
     * 按钮 -- 分享自建库
     *
     * @return
     */
    public WebElement input_shareCDB() {
        l.entry();
        return d.findElement(By.id("sharedb_btn"));
    }


    /********** Functions **********/

    /**
     * 验证自建库是否存在
     *
     * @param exp_name
     * @return
     */
    public boolean func_verify_customDatabase(String name) {
        l.entry();
        if (this.div_CDB_byName(name) != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Custom database is found");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom database div is not found");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证自建库是否存在(被分享的)
     *
     * @param exp_name
     * @return
     */
    public boolean func_verify_customDatabase_shared(String name) {
        l.entry();
        if (this.div_CDB_byName_shared(name) != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Custom database is found");
            return true;
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom database div is not found");
            t.takeScreenshot(d);
            return false;
        }
    }

    /**
     * 验证query存在，并且点击链接
     *
     * @param name
     * @return
     */
    public boolean func_verify_query_and_click(String name) {
        l.entry();
        boolean result = false;
        boolean matchFlag = false;
        l.info("Expected query name is [{}]", name);
        //展开所有节点
        for (WebElement btn : d.findElement(By.id("query-list")).findElements(By.className("root_close"))) {
            btn.click();
        }
        List<WebElement> links = d.findElement(By.id("query-list")).findElements(By.tagName("a"));
        if (links.size() == 0) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Query list is empty");
            t.takeScreenshot(d);
            return false;
        }
        //验证query是否存在
        for (WebElement link : links) {
            String actName = link.getText();
            l.info("Current actual query name is [{}]", actName);
            if (actName.contains(name)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Query is found, and click the link");
                link.click();
                result = true;
                matchFlag = true;
                break;
            }
        }
        if (!matchFlag) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Query is not found");
            t.takeScreenshot(d);
            result = false;
        }
        return result;
    }

    /**
     * 点击自建库的管理链接
     *
     * @param name
     */
    public void func_click_manage_byCDBName(String name) {
        l.entry();
        WebElement div = this.div_CDB_byName(name);
        if (div != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Custom database is found");
            div.findElement(By.cssSelector(".btn-26.primary.enter")).click();
            l.info("Manage button is clicked");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom database div is not found");
            t.takeScreenshot(d);
        }
        l.exit();
    }

    /**
     * 点击自建库的管理链接--被分享的，查看
     *
     * @param name
     */
    public void func_click_view_byCDBName(String name) {
        l.entry();
        WebElement div = this.div_CDB_byName_shared(name);
        if (div != null) {
            l.info("++++++++++++++++++++++++++++++ Pass -- Custom database is found");
            div.findElement(By.cssSelector(".btn-26.primary.enter")).click();
            l.info("View button is clicked");
        } else {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Custom database div is not found");
            t.takeScreenshot(d);
        }
        l.exit();
    }

    /**
     * 根据名称删除CDB
     *
     * @param name
     */
    public void func_delete_CDB_byName(String name, String pwd) {
        l.entry();
        WebElement div = this.div_CDB_byName(name);
        if (div != null) {
            l.info("Custom database is found");
            div.findElement(By.cssSelector(".btn-26.del")).click();
            //点击删除按钮
            this.getElementById("CdbMFolderPassword").clear();
            this.getElementById("CdbMFolderPassword").sendKeys(pwd);
            this.getElementById("CdbMFolderPassword").submit();
            l.info("CDB is deleted");
        } else {
            l.error("Custom database div is not found");
            t.takeScreenshot(d);
        }
        l.exit();
    }

    /**
     * 获取搜索结果总数
     *
     * @return
     * @throws Exception
     */
    public int func_get_searchResultNumber() throws Exception {
        l.entry();
        int i = 0;
        while (this.span_searchResultNumber().getText().equals("")) {
            if (i < 60) {
                l.debug("waiting for total result text");
                Thread.sleep(1000);
                i++;
            } else {
                l.debug("timeout");
                break;
            }
        }
        String strNum = this.span_searchResultNumber().getText();
        l.debug("Total number text: {}", strNum);
        int num = 0;
        strNum = strNum.replaceAll(",", "");
        num = Integer.parseInt(strNum);
        return num;
    }

    /**
     * 点击导出按钮
     */
    public void func_click_export() {
        l.entry();
        this.link_export().click();
        l.exit();
    }

    /**
     * 过滤
     *
     * @param keyword -- 过滤关键字
     */
    public void func_filter(String keyword) {
        l.entry();
        this.input_filter().clear();
        this.input_filter().sendKeys(keyword);
        this.link_filter().click();
        l.exit();
    }

    /**
     * div--排序方法
     *
     * @return
     */
    public WebElement div_sortType() {
        l.entry();
        return d.findElement(By.cssSelector(".m-drop-menu.order-menu"));
    }

    /**
     * 根据索引选择排序方法
     *
     * @param index
     */
    public void func_select_sortType_byIndex(int index) {
        l.entry();
        act = new Actions(d);
        act.moveToElement(this.div_sortType());
        act.perform();
        if (this.link_sortType_byIndex(index).isDisplayed()) {
            this.link_sortType_byIndex(index).click();
        }
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
     * 点击文件夹
     *
     * @param queryName
     */
    public void func_shareCDB(String uid) {
        l.entry();
        this.link_share().click();
        this.input_sharedUID().sendKeys(uid);
        this.input_shareCDB().click();
        l.exit();
    }

    /**
     * 点击页码
     *
     * @param index
     */
    public void func_goToPage(int index) {
        l.entry();
        this.input_pageNum().clear();
        this.input_pageNum().sendKeys(String.valueOf(index));
        this.input_pageGo().click();
        l.exit();
    }
}