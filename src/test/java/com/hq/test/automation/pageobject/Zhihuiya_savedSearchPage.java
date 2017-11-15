package com.hq.test.automation.pageobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * 以保存搜索页面对象类
 *
 * @author hanqing
 */
public class Zhihuiya_savedSearchPage extends Zhihuiya_basePage {

    public Zhihuiya_savedSearchPage(WebDriver driver) {
        l.entry();
        d = driver;
        act = new Actions(d);
        selfcheckSelector = By.className("user-center-menu");
        l.exit();
    }

    /********** Elements **********/

    /**
     * 列表--以保存搜索的列表
     *
     * @return
     */
    public WebElement table_savedSearch() {
        l.entry();
        return d.findElement(By.className("saved-query-table"));
    }

    /**
     * 删除按钮
     *
     * @return
     */
    public WebElement link_delete() {
        l.entry();
        return d.findElement(By.className("btn-group")).findElement(By.className("del"));
    }

    /**
     * 合并按钮
     *
     * @return
     */
    public WebElement link_combine() {
        l.entry();
        return d.findElement(By.className("btn-group")).findElement(By.className("combine"));
    }

    /**
     * 单选框--合并类型选择
     *
     * @param index
     * @return
     */
    public WebElement input_combineType(int index) {
        l.entry();
        return d.findElement(By.id("combine_query")).findElements(By.name("data[logic]")).get(index);
    }

    public WebElement link_confirmCombine() {
        l.entry();
        return d.findElement(By.id("COMBINE-QUERY-SUBMIT"));
    }


    /********** Functions **********/

    /**
     * 验证以保存搜索存在
     *
     * @param exp_name
     * @return
     */
    public boolean func_verify_savedSearch(String name, String query) {
        l.entry();
        boolean result = true;
        boolean matchFlag = false;
        l.info("Expected query name is [{}]", name);
        if (!this.doesExist(By.className("saved-query-table"))) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Saved search table is not found");
            t.takeScreenshot(d);
            return false;
        }
        WebElement table = this.table_savedSearch();
        //获取表格数据
        List<HashMap<String, String>> tableData = this.getTableData(table);
        //验证邮件提醒是否存在
        for (HashMap<String, String> row : tableData) {
            String actName = row.get("Query Name");
            l.info("Current actual query name is [{}]", actName);
            if (actName.equals(name)) {
                l.info("++++++++++++++++++++++++++++++ Pass -- Saved search is found");
                result &= true;
                matchFlag = true;
                //验证搜索语句
                if (query != null) {
                    String act_query = row.get("Saved Search Query");
                    l.info("Expected query string: {}", query);
                    l.info("Actual query string: {}", act_query);
                    if ((act_query != null) && (act_query.contains(query))) {
                        l.info("++++++++++++++++++++++++++++++ Pass -- Query string is correct");
                    } else {
                        l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Query string is incorrect");
                        t.takeScreenshot(d);
                        result &= false;
                    }
                }
                break;
            }
        }
        if (!matchFlag) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail -- Saved search is not found");
            t.takeScreenshot(d);
            result &= false;
        }
        return result;
    }

    /**
     * 删除搜索语句
     *
     * @param name--名字包含此参数的全部删除
     */
    public void func_delete_savedSearch(String name) {
        l.entry();
        l.info("Expected query name contains [{}]", name);
        //没有列表，则返回，认为删除成功
        if (!this.doesExist(By.className("saved-query-table"))) {
            l.info("Saved search table is not found");
            return;
        }
        //获取表格
        WebElement table = this.table_savedSearch();
        for (WebElement tr : table.findElements(By.tagName("tr"))) {
            //如果非表头tr
            if (tr.getAttribute("name") != null) {
                String act_name = tr.findElement(By.className("col-name")).getText();
                //如果名字包含入参
                if (act_name.contains(name)) {
                    //删除
                    tr.findElement(By.className("btn-delete")).click();
                    d.switchTo().alert().accept();
                    l.info("Query [{}] is deleted", act_name);
                    break;
                }
            }
        }
    }

    /**
     * 导出搜索语句
     *
     * @param name--包含此搜索
     */
    public void func_export_savedSearch(String name) {
        l.entry();
        l.info("Expected query name contains [{}]", name);
        //没有列表，则返回，认为删除成功
        if (!this.doesExist(By.className("saved-query-table"))) {
            l.info("Saved search table is not found");
        }
        //获取表格
        WebElement table = this.table_savedSearch();
        for (WebElement tr : table.findElements(By.tagName("tr"))) {
            //如果非表头tr
            if (tr.getAttribute("name") != null) {
                String act_name = tr.findElement(By.className("col-name")).getText();
                //如果名字包含入参
                if (act_name.contains(name)) {
                    //删除
                    tr.findElement(By.className("btn-export")).click();
                    break;
                }
            }
        }
    }

    /**
     * 删除所有搜索
     */
    public void func_delete_all_savedSearch() {
        l.entry();
        //没有列表，则返回，认为删除成功
        if (!this.doesExist(By.className("saved-query-table"))) {
            l.info("Saved search table is not found");
            return;
        }
        //获取表格
        WebElement table = this.table_savedSearch();
        for (WebElement tr : table.findElements(By.tagName("tr"))) {
            //如果非表头tr
            if (tr.getAttribute("name") != null) {
                //勾选复选框
                tr.findElement(By.className("col-checkbox")).findElement(By.tagName("input")).click();
            }
        }
        //点击删除按钮
        this.link_delete().click();
        d.switchTo().alert().accept();
    }

    /**
     * 合并搜索语句
     *
     * @param queries--搜索语句
     * @param type--类型，0：OR，1：AND
     */
    public void func_combine_savedSearch(ArrayList<String> queries, int type) {
        l.entry();
        //没有列表，则返回，认为删除成功
        if (!this.doesExist(By.className("saved-query-table"))) {
            l.error("Saved search table is not found");
            return;
        }
        //获取表格
        WebElement table = this.table_savedSearch();
        //根据输入的query，找到对应的复选框，并勾选
        for (String query : queries) {
            for (WebElement tr : table.findElements(By.tagName("tr"))) {
                //如果非表头tr
                if (tr.getAttribute("name") != null) {
                    //如果名称一致
                    if (query.equals(tr.findElement(By.className("col-name")).getText())) {
                        //勾选复选框
                        tr.findElement(By.className("col-checkbox")).findElement(By.tagName("input")).click();
                    }
                }
            }
        }
        //点击合并按钮
        this.link_combine().click();
        this.input_combineType(type).click();
        this.link_confirmCombine().click();
    }

    /**
     * 根据搜索名称，点击搜索链接
     *
     * @param name
     */
    public void func_click_queryLink_byName(String name) {
        l.entry();
        //没有列表，则返回，认为删除成功
        if (!this.doesExist(By.className("saved-query-table"))) {
            l.error("Saved search table is not found");
            return;
        }
        //获取表格
        WebElement table = this.table_savedSearch();
        for (WebElement tr : table.findElements(By.tagName("tr"))) {
            //如果非表头tr
            if (tr.getAttribute("name") != null) {
                //如果名称一致
                if (name.equals(tr.findElement(By.className("col-name")).getText())) {
                    //勾选复选框
                    tr.findElement(By.className("col-query")).findElement(By.tagName("a")).click();
                    break;
                }
            }
        }
    }
}