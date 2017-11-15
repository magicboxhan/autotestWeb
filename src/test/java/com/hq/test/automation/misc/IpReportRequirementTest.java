package com.hq.test.automation.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.hq.test.framework.testcase.BaseTestcase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hq.test.automation.pageobject.Zhihuiya_loginPage;
import com.hq.test.automation.pageobject.Zhihuiya_searchPage;


public class IpReportRequirementTest extends BaseTestcase {

    Zhihuiya_loginPage p_loginPage;
    Zhihuiya_searchPage p_searchPage;

    int scale = 16;        //保留小树位数
    int startRow = 1;    //数据起始行数
    int colA = 2;        //参数A的列数
    int colB = 3;        //参数B的列数
    int fTypeCol = 0;    //公式类型列
    int resultCol = 4;    //存放计算结果数据的列
    int blueCol = 5;    //存放计算蓝线数据的列
    int redCol = 6;        //存放计算红线数据的列
    int lastRowNum = 8;    //最后一行行数

    @Parameters({
            "filePath",
            "outputPath",
            "loginPage_url",
            "loginPage_uid",
            "loginPage_pwd",
    })
    @Test
    public void test(
            String filePath,
            String outputPath,
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd
    ) {
        l.entry();
        try {
            File file = new File(filePath);
            if (file.isDirectory()) {
                //是文件夹
                String[] fileNames = file.list();
                for (String fileName : fileNames) {
                    if ((fileName.contains("xls")) && (!fileName.contains("updated"))) {
                        this.analysis(filePath, fileName, outputPath, loginPage_url, loginPage_uid, loginPage_pwd);
                    }

                }
            }
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error when testing");
            e.printStackTrace();
        }

    }

    /**
     * 分析单个图表
     *
     * @param filePath
     * @param fileName
     * @param outputPath
     * @param loginPage_url
     * @param loginPage_uid
     * @param loginPage_pwd
     */
    public void analysis(
            String filePath,
            String fileName,
            String outputPath,
            String loginPage_url,
            String loginPage_uid,
            String loginPage_pwd
    ) {
        l.entry();
        try {
            l.info("");
            l.info("");
            l.info("");
            l.info("============================== File name: [{}] ==============================", fileName);
            //读取excel文件
            Workbook excel = new XSSFWorkbook(new FileInputStream(String.format("%s//%s", filePath, fileName)));

            //变量
//			int startRow = 1;	//数据起始行数
//			int colA = 2;		//参数A的列数
//			int colB = 3;		//参数B的列数
//			int fTypeCol = 0;	//公式类型列
//			int resultCol = 4;	//存放计算结果数据的列
//			int blueCol = 5;	//存放计算蓝线数据的列
//			int redCol = 6;		//存放计算红线数据的列
//			int lastRowNum = 8;	//最后一行行数
            HashMap<Integer, ArrayList<Double>> results_all = new HashMap<Integer, ArrayList<Double>>();

            String an = "";            //待分析an
            ArrayList<String> ans_c = new ArrayList<String>(); //竞争对手an列表

            l.info("==================== Get results ====================");

            //按sheet循环，计算各个发明者的各个指标
            for (int i = 0; i < excel.getNumberOfSheets(); i++) {
                l.info("========== sheet[{}] ==========", i);
                try {
                    //每个sheet操作
                    Sheet s = excel.getSheetAt(i);
                    //记录各个sheet的an
                    if (i != 0) {
                        ans_c.add(s.getSheetName());
                    } else {
                        an = s.getSheetName();
                    }
                    //按数据行循环
                    for (int j = startRow; j < lastRowNum + 1; j++) {
                        l.info("========== File name: [{}], sheet[{}], row[{}] ==========", fileName, i, j);
                        try {
                            //每个行的操作
                            Row r = s.getRow(j);
                            //0.循环变量
                            HashMap<String, ArrayList<Double>> p = new HashMap<String, ArrayList<Double>>();    //参数列表
                            int fType = -1;    //公式类型
                            //1.获取参数和公式类型
                            try {
                                switch (r.getCell(fTypeCol).getStringCellValue().trim()) {
                                    case "growth in patent stock":
                                        fType = 0;
                                        break;
                                    case "cuerrent relevance（CII）":
                                        fType = 1;
                                        break;
                                    case "scientific content":
                                        fType = 2;
                                        break;
                                    case "co-patenting":
                                        fType = 3;
                                        break;
                                    case "internationalization":
                                        fType = 4;
                                        break;
                                    case "tech specialization":
                                        fType = 5;
                                        break;
                                    case "patent complexity":
                                        fType = 6;
                                        break;
                                    case "patent influence(TII)":
                                        fType = 7;
                                        break;
                                }
                            } catch (Exception e) {
                                //获取失败，则跳过本行计算
                                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail to get formula id");
                                e.printStackTrace();
                                continue;
                            }
                            //********** 获取参数（读取到最右侧的非空单元） **********
                            //a
                            l.info("Parameter a:");
                            ArrayList<Double> p_a = new ArrayList<Double>();
                            if (r.getCell(colA) != null) {
                                try {
                                    String cellString = "";
                                    if (r.getCell(colA).getCellType() == Cell.CELL_TYPE_STRING) {
                                        cellString = r.getCell(colA).getStringCellValue();
                                    } else if (r.getCell(colA).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                        cellString = String.valueOf(r.getCell(colA).getNumericCellValue());
                                    }
                                    if (cellString.length() > 0) {
                                        for (String a : cellString.split(",")) {
                                            p_a.add(Double.valueOf(a));
                                            l.info(a);
                                        }
                                    } else {
                                        l.info("Empty cell");
                                    }
                                } catch (Exception e) {
                                    //获取失败，则跳过本行计算
                                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail to get parameter a");
                                    e.printStackTrace();
                                }
                            } else {
                                l.info("Parameter a is null");
                            }
                            //b
                            l.info("Parameter b:");
                            ArrayList<Double> p_b = new ArrayList<Double>();
                            if (r.getCell(colB) != null) {
                                try {
                                    String cellString = "";
                                    if (r.getCell(colB).getCellType() == Cell.CELL_TYPE_STRING) {
                                        cellString = r.getCell(colB).getStringCellValue();
                                    } else if (r.getCell(colB).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                        cellString = String.valueOf(r.getCell(colB).getNumericCellValue());
                                    }
                                    if (cellString.length() > 0) {
                                        for (String b : cellString.split(",")) {
                                            p_b.add(Double.valueOf(b));
                                            l.info(b);
                                        }
                                    } else {
                                        l.info("Empty cell");
                                    }
                                } catch (Exception e) {
                                    //获取失败，则跳过本行计算
                                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail to get parameter b");
                                    e.printStackTrace();
                                }
                            } else {
                                l.info("Parameter b is null");
                            }
                            p.put("a", p_a);
                            p.put("b", p_b);
                            //2.根据公式，计算结果
                            double result = this.getResult(fType, p);
                            l.info("========== Result: [{}] ==========", result);
                            if (result == -1) {
                                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail to get result of sheet[{}] row[{}]", i, j);
                                continue;
                            }
                            //3.回填结果到excel
                            try {
//								Cell c = r.createCell(resultCol);
//								r.removeCell(c);
                                r.createCell(resultCol).setCellType(Cell.CELL_TYPE_NUMERIC);
                                r.getCell(resultCol).setCellValue(result);
                            } catch (Exception e) {
                                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail when writing result back to excel");
                                continue;
                            }
                        } catch (Exception e) {
                            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail when processing sheet[{}] row[{}]", i, j);
                            e.printStackTrace();
                            continue;
                        }

                    }

                } catch (Exception e) {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail when processing sheet[{}]", i);
                    e.printStackTrace();
                    continue;
                }
            }

            //收集红蓝线数据
            for (int i = 0; i < lastRowNum + 1 - startRow; i++) {
                //每一个公式
                ArrayList<Double> results_row = new ArrayList<Double>();
                for (int j = 0; j < excel.getNumberOfSheets(); j++) {
                    //每一个sheet
                    try {
                        l.info("========== Collecting redline data for sheet[{}], row[{}], cell[{}]", j, i + startRow, resultCol);
                        results_row.add(excel.getSheetAt(j).getRow(i + startRow).getCell(resultCol).getNumericCellValue());
                    } catch (Exception e) {
                        l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail when reading result value");
                        e.printStackTrace();
                        continue;
                    }
                }
                results_all.put(i, results_row);
            }

            //计算蓝线
            for (int i = 0; i < results_all.size(); i++) {
                //每一个公式
                ArrayList<Double> blueLines = new ArrayList<Double>();
                for (int j = 0; j < results_all.get(i).size(); j++) {
                    //每一个发明者
                    //计算蓝线
                    double blueLine = this.transformed(results_all.get(i), j);
//			        BigDecimal bd = new BigDecimal(blueLine);   
//			        bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
//			        blueLine = bd.doubleValue();
                    l.info("========== Blue Line (sheet[{}],row[{}]): [{}] ==========", j, (i + startRow), blueLine);
                    //填入excel
                    excel.getSheetAt(j).getRow(i + startRow).createCell(blueCol).setCellValue(blueLine);
                    blueLines.add(blueLine);
                }
                //计算红线
                double redLine = this.mean(blueLines);
//		        BigDecimal bd = new BigDecimal(redLine);
//		        bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
//		        redLine = bd.doubleValue();
                l.info("========== Red Line (row[{}]): [{}] ==========", i, redLine);
                for (int j = 0; j < results_all.get(i).size(); j++) {
                    excel.getSheetAt(j).getRow(i + startRow).createCell(redCol).setCellValue(redLine);
                }
            }

//			//保存excel
//			try{
//				String updatedFileName = String.format("%s_updated.xlsx",fileName.split("//.")[0]);
//				FileOutputStream out = new FileOutputStream(String.format("%s//%s", filePath, updatedFileName));
//			    excel.write(out);
//			    l.info("Excel is saved -- {}//updated_{}", filePath, fileName);
//			}catch(Exception e){
//				l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail when saving excel file");
//			}

            //组装json数据
            l.info("==================== Create json object ====================");

            JSONObject json_strategyMapBean = new JSONObject();    //strategyMapBean
            JSONObject json_main = new JSONObject();            //主节点
            JSONObject json_blue = new JSONObject();            //蓝线数据
            JSONObject json_red = new JSONObject();                //红线数据
            JSONArray jsona_industry = new JSONArray();            //industry节点下的数组
            JSONObject json_an = new JSONObject();                //an数据

            try {
                //创建an节点
                json_an.put("name", excel.getSheetName(0));
                json_an.put("numberOfPatents", excel.getSheetAt(0).getRow(startRow + 2).getCell(colB).getNumericCellValue());
                json_main.put("assignee", json_an);

                //蓝线数据 --> 蓝线节点
                json_blue.put("internationalization", excel.getSheetAt(0).getRow(startRow + 4).getCell(blueCol).getNumericCellValue());
                json_blue.put("growthInPatentStock", excel.getSheetAt(0).getRow(startRow + 0).getCell(blueCol).getNumericCellValue());
                json_blue.put("coPateting", excel.getSheetAt(0).getRow(startRow + 3).getCell(blueCol).getNumericCellValue());
                json_blue.put("currentRelevance", excel.getSheetAt(0).getRow(startRow + 1).getCell(blueCol).getNumericCellValue());
                json_blue.put("patentComplexity", excel.getSheetAt(0).getRow(startRow + 6).getCell(blueCol).getNumericCellValue());
                json_blue.put("technologySpecialization", excel.getSheetAt(0).getRow(startRow + 5).getCell(blueCol).getNumericCellValue());
                json_blue.put("patentInfluence", excel.getSheetAt(0).getRow(startRow + 7).getCell(blueCol).getNumericCellValue());
                json_blue.put("scientificContent", excel.getSheetAt(0).getRow(startRow + 2).getCell(blueCol).getNumericCellValue());
                //蓝线节点 --> 主节点
                json_main.put("BLUE", json_blue);

                //红线数据 --> 红线节点
                json_red.put("internationalization", excel.getSheetAt(0).getRow(startRow + 4).getCell(redCol).getNumericCellValue());
                json_red.put("growthInPatentStock", excel.getSheetAt(0).getRow(startRow + 0).getCell(redCol).getNumericCellValue());
                json_red.put("coPateting", excel.getSheetAt(0).getRow(startRow + 3).getCell(redCol).getNumericCellValue());
                json_red.put("currentRelevance", excel.getSheetAt(0).getRow(startRow + 1).getCell(redCol).getNumericCellValue());
                json_red.put("patentComplexity", excel.getSheetAt(0).getRow(startRow + 6).getCell(redCol).getNumericCellValue());
                json_red.put("technologySpecialization", excel.getSheetAt(0).getRow(startRow + 5).getCell(redCol).getNumericCellValue());
                json_red.put("patentInfluence", excel.getSheetAt(0).getRow(startRow + 7).getCell(redCol).getNumericCellValue());
                json_red.put("scientificContent", excel.getSheetAt(0).getRow(startRow + 2).getCell(redCol).getNumericCellValue());
                //红线节点 --> 主节点
                json_main.put("RED", json_red);

                //结果数据 --> 主节点
                json_main.put("internationalization", excel.getSheetAt(0).getRow(startRow + 4).getCell(resultCol).getNumericCellValue());
                json_main.put("growthInPatentStock", excel.getSheetAt(0).getRow(startRow + 0).getCell(resultCol).getNumericCellValue());
                json_main.put("coPateting", excel.getSheetAt(0).getRow(startRow + 3).getCell(resultCol).getNumericCellValue());
                json_main.put("currentRelevance", excel.getSheetAt(0).getRow(startRow + 1).getCell(resultCol).getNumericCellValue());
                json_main.put("patentComplexity", excel.getSheetAt(0).getRow(startRow + 6).getCell(resultCol).getNumericCellValue());
                json_main.put("technologySpecialization", excel.getSheetAt(0).getRow(startRow + 5).getCell(resultCol).getNumericCellValue());
                json_main.put("patentInfluence", excel.getSheetAt(0).getRow(startRow + 7).getCell(resultCol).getNumericCellValue());
                json_main.put("scientificContent", excel.getSheetAt(0).getRow(startRow + 2).getCell(resultCol).getNumericCellValue());

                //从第二个sheet开始，将数据加入industry节点
                for (int i = 1; i < excel.getNumberOfSheets(); i++) {
//					l.info("========== sheet[{}] ==========", i);
                    try {
                        //针对每个sheet，创建json对象
                        JSONObject json_blue_c = new JSONObject();    //竞争对手蓝线节点
                        JSONObject json_main_c = new JSONObject();    //竞争对手主节点
                        JSONObject json_an_c = new JSONObject();    //an数据
                        //创建an节点
                        json_an_c.put("name", excel.getSheetName(i));
                        json_an_c.put("numberOfPatents", excel.getSheetAt(i).getRow(startRow + 2).getCell(colB).getNumericCellValue());
                        //an节点 --> 蓝线节点， an节点 --> 主节点
                        json_blue_c.put("assignee", json_an_c);
                        json_main_c.put("assignee", json_an_c);
                        //蓝线数据 --> 蓝线节点
                        json_blue_c.put("internationalization", excel.getSheetAt(i).getRow(startRow + 4).getCell(blueCol).getNumericCellValue());
                        json_blue_c.put("growthInPatentStock", excel.getSheetAt(i).getRow(startRow + 0).getCell(blueCol).getNumericCellValue());
                        json_blue_c.put("coPateting", excel.getSheetAt(i).getRow(startRow + 3).getCell(blueCol).getNumericCellValue());
                        json_blue_c.put("currentRelevance", excel.getSheetAt(i).getRow(startRow + 1).getCell(blueCol).getNumericCellValue());
                        json_blue_c.put("patentComplexity", excel.getSheetAt(i).getRow(startRow + 6).getCell(blueCol).getNumericCellValue());
                        json_blue_c.put("technologySpecialization", excel.getSheetAt(i).getRow(startRow + 5).getCell(blueCol).getNumericCellValue());
                        json_blue_c.put("patentInfluence", excel.getSheetAt(i).getRow(startRow + 7).getCell(blueCol).getNumericCellValue());
                        json_blue_c.put("scientificContent", excel.getSheetAt(i).getRow(startRow + 2).getCell(blueCol).getNumericCellValue());
                        //结果数据 --> 主节点
                        json_main_c.put("internationalization", excel.getSheetAt(i).getRow(startRow + 4).getCell(resultCol).getNumericCellValue());
                        json_main_c.put("growthInPatentStock", excel.getSheetAt(i).getRow(startRow + 0).getCell(resultCol).getNumericCellValue());
                        json_main_c.put("coPateting", excel.getSheetAt(i).getRow(startRow + 3).getCell(resultCol).getNumericCellValue());
                        json_main_c.put("currentRelevance", excel.getSheetAt(i).getRow(startRow + 1).getCell(resultCol).getNumericCellValue());
                        json_main_c.put("patentComplexity", excel.getSheetAt(i).getRow(startRow + 6).getCell(resultCol).getNumericCellValue());
                        json_main_c.put("technologySpecialization", excel.getSheetAt(i).getRow(startRow + 5).getCell(resultCol).getNumericCellValue());
                        json_main_c.put("patentInfluence", excel.getSheetAt(i).getRow(startRow + 7).getCell(resultCol).getNumericCellValue());
                        json_main_c.put("scientificContent", excel.getSheetAt(i).getRow(startRow + 2).getCell(resultCol).getNumericCellValue());
                        //蓝线数据 --> 主节点
                        json_main_c.put("BLUE", json_blue_c);
                        //红线数据 --> 主节点
                        json_main_c.put("RED", json_red);
                        //主线 --> industry数组
                        jsona_industry.put(json_main_c);
                    } catch (Exception e) {
                        l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail when creating json of sheet[{}]", i);
                        continue;
                    }
                }
                //industry数组--> 主节点
                json_main.put("industry", jsona_industry);
                json_strategyMapBean.put("strategyMapBean", json_main);

                l.info(json_strategyMapBean.toString());
            } catch (Exception e) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail when creating json for excel");
                e.printStackTrace();
                return;
            }


            //发送测试数据
            l.info("==================== Run ipreport analysis ====================");
            //拼接URL
            String url_analysis = String.format("%s/ip_report/assignee?", loginPage_url);
            url_analysis += String.format("ASSIGNEE=%s&", an);
            for (String an_c : ans_c) {
                url_analysis += String.format("COMPETE[]=%s&", an_c);
            }
            url_analysis += "selected[]=strategic_priorities_analysis&SIMULATE=1";
            l.info(url_analysis);
            d.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d);
            Assert.assertEquals(p_searchPage.selfcheck(), true);
            d.get(url_analysis);
//			Thread.sleep(10000);
            //获取JobId
            int i = 0;
            while (d.findElements(By.className("ghost-str")).size() == 0) {
                if (i < 10) {
                    Thread.sleep(1000);
                    i++;
                } else {
                    l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Timeout when getting job id");
                    return;
                }
            }

            JavascriptExecutor jse = (JavascriptExecutor) d;
            String js = "return $(\".ghost-str\").text()";
            String jobId = (String) jse.executeScript(js);
            l.info("========== JobId: [{}] ==========", jobId);

            //新增驱动
            WebDriver d2 = new FirefoxDriver();
            d2.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            d2.get(loginPage_url);
            p_loginPage = new Zhihuiya_loginPage(d2);
            p_loginPage.func_login(loginPage_uid, loginPage_pwd);
            p_searchPage = new Zhihuiya_searchPage(d2);

            //填写JobId
            String url_data = String.format("%s/ip_report/assignee/manual_callback", loginPage_url);
            l.info(url_data);
            d2.get(url_data);
            d2.findElement(By.linkText("simulate callback")).click();
            d2.findElement(By.id("jobId")).sendKeys(jobId);
            d2.findElement(By.id("result")).sendKeys(json_strategyMapBean.toString());
            d2.findElement(By.id("result")).submit();
            Thread.sleep(5000);
            d2.quit();
            Thread.sleep(5000);
            //获取ipreport截图
            t.takeScreenshot(d, outputPath, fileName, "jpg");

            //保存excel
            try {
                String updatedFileName = String.format("%s_updated.xlsx", fileName.split("\\.")[0]);
                FileOutputStream out = new FileOutputStream(String.format("%s//%s", filePath, updatedFileName));
                excel.write(out);
                l.info("Excel is saved -- {}//{}_updated.xlsx", filePath, fileName);
            } catch (Exception e) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fail when saving excel file");
            }

        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error when running analysis");
            e.printStackTrace();
        }
    }

    /**
     * 计算结果
     *
     * @param fType -- 公式类型
     * @param p     -- 入参列表
     * @return
     */
    public double getResult(int fType, HashMap<String, ArrayList<Double>> p) {
        try {
            double result = -1;
            switch (fType) {
                case 0:
                    result = this.getResult_f0(p);
                    break;
                case 1:
                    result = this.getResult_f1(p);
                    break;
                case 2:
                    result = this.getResult_f2(p);
                    break;
                case 3:
                    result = this.getResult_f3(p);
                    break;
                case 4:
                    result = this.getResult_f4(p);
                    break;
                case 5:
                    result = this.getResult_f5(p);
                    break;
                case 6:
                    result = this.getResult_f6(p);
                    break;
                case 7:
                    result = this.getResult_f7(p);
                    break;
            }
//			//精度
//	        BigDecimal bd = new BigDecimal(result);   
//	        bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
//	        result = bd.doubleValue();
            return result;
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error when getting result");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * growth in patent stock -- ((a/b)^(1/5))-1
     *
     * @param p
     * @return
     */
    public double getResult_f0(HashMap<String, ArrayList<Double>> p) {
        try {
            l.info("growth in patent stock -- ((a/b)^(1/5))-1");
            if ((p.get("a").size() == 0) || (p.get("b").size() == 0)) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Parameter is empty");
                return -1;
            }
            return Math.pow((p.get("a").get(0) / p.get("b").get(0)), 0.2) - 1;
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error when getting result (formula 0)");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * cuerrent relevance（CII） -- (a1b1+a2b2+a3b3+a4b4+a5b5)/(b1+b2+b3+b4+b5)
     *
     * @param p
     * @return
     */
    public double getResult_f1(HashMap<String, ArrayList<Double>> p) {
        try {
            l.info("cuerrent relevance（CII） -- (a1b1+a2b2+a3b3+a4b4+a5b5)/(b1+b2+b3+b4+b5)");
            if ((p.get("a").size() == 0) || (p.get("b").size() == 0)) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Parameter is empty");
                return -1;
            }
            return (p.get("a").get(0) * p.get("b").get(0) + p.get("a").get(1) * p.get("b").get(1) + p.get("a").get(2) * p.get("b").get(2) + p.get("a").get(3) * p.get("b").get(3) + p.get("a").get(4) * p.get("b").get(4)) / (p.get("b").get(0) + p.get("b").get(1) + p.get("b").get(2) + p.get("b").get(3) + p.get("b").get(4));
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error when getting result (formula 1)");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * scientific content -- a/b
     *
     * @param p
     * @return
     */
    public double getResult_f2(HashMap<String, ArrayList<Double>> p) {
        try {
            l.info("scientific content -- a/b");
            if ((p.get("a").size() == 0) || (p.get("b").size() == 0)) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Parameter is empty");
                return -1;
            }
            return p.get("a").get(0) / p.get("b").get(0);
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error when getting result (formula 2)");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * co-patenting -- a/b
     *
     * @param p
     * @return
     */
    public double getResult_f3(HashMap<String, ArrayList<Double>> p) {
        try {
            l.info("co-patenting -- a/b");
            if ((p.get("a").size() == 0) || (p.get("b").size() == 0)) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Parameter is empty");
                return -1;
            }
            return p.get("a").get(0) / p.get("b").get(0);
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error when getting result (formula 3)");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * internationalization -- a/b
     *
     * @param p
     * @return
     */
    public double getResult_f4(HashMap<String, ArrayList<Double>> p) {
        try {
            l.info("internationalization -- a/b");
            if ((p.get("a").size() == 0) || (p.get("b").size() == 0)) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Parameter is empty");
                return -1;
            }
            return p.get("a").get(0) / p.get("b").get(0);
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error when getting result (formula 4)");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * tech specialization -- a^2+b^2+c^2+d^2+…+n^2
     *
     * @param p
     * @return
     */
    public double getResult_f5(HashMap<String, ArrayList<Double>> p) {
        try {
            l.info("tech specialization -- a^2+b^2+c^2+d^2+…+n^2");
            double result = 0;
            if (p.get("a").size() == 0) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Parameter is empty");
                return -1;
            }
            for (double d : p.get("a")) {
                result += Math.pow(d, 2);
            }
            return result;
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error when getting result (formula 5)");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * patent complexity -- a/b
     *
     * @param p
     * @return
     */
    public double getResult_f6(HashMap<String, ArrayList<Double>> p) {
        try {
            l.info("patent complexity -- a/b");
            if ((p.get("a").size() == 0) || (p.get("b").size() == 0)) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Parameter is empty");
                return -1;
            }
            return p.get("a").get(0) / p.get("b").get(0);
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error when getting result (formula 6)");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * patent influence(TII) -- ((b1+b2+b3+b4+b5)/(a1+a2+a3+a4+a5))/0.05
     *
     * @param p
     * @return
     */
    public double getResult_f7(HashMap<String, ArrayList<Double>> p) {
        try {
            l.info("influence(TII) -- ((b1+b2+b3+b4+b5)/(a1+a2+a3+a4+a5))/0.05");
            if ((p.get("a").size() == 0) || (p.get("b").size() == 0)) {
                l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Parameter is empty");
                return -1;
            }
            return (p.get("b").get(0) + p.get("b").get(1) + p.get("b").get(2) + p.get("b").get(3) + p.get("b").get(4)) / (p.get("a").get(0) + p.get("a").get(1) + p.get("a").get(2) + p.get("a").get(3) + p.get("a").get(4)) / 0.05;
        } catch (Exception e) {
            l.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Error when getting result (formula 7)");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 返回数组最大值
     *
     * @param ps
     * @return
     */
    public double max(ArrayList<Double> ps) {
        double result = ps.get(0);
        for (double p : ps) {
            if (p > result) {
                result = p;
            }
        }
        return result;
    }

    /**
     * 返回数组最小值
     *
     * @param ps
     * @return
     */
    public double min(ArrayList<Double> ps) {
        double result = ps.get(0);
        for (double p : ps) {
            if (p < result) {
                result = p;
            }
        }
        return result;
    }

    /**
     * norm
     *
     * @param ps
     * @param anIndex -- 发明者索引
     * @return
     */
    public double norm(ArrayList<Double> ps, int anIndex) {
        return (ps.get(anIndex) - min(ps)) / (max(ps) - min(ps));
    }

    /**
     * mean
     *
     * @param ps
     * @return
     */
    public double mean(ArrayList<Double> ps) {
        double result = 0;
        for (int i = 0; i < ps.size(); i++) {
//			result += this.norm(ps, i);
            result += ps.get(i);
        }
        return result / ps.size();
    }

    /**
     * 蓝线
     *
     * @param ps
     * @param anIndex
     * @return
     */
    public double transformed(ArrayList<Double> ps, int anIndex) {
        ArrayList<Double> norms = new ArrayList<Double>();
        for (int i = 0; i < ps.size(); i++) {
            norms.add(this.norm(ps, i));
        }
        return (this.norm(ps, anIndex) / this.mean(norms)) - 1;
    }
}
