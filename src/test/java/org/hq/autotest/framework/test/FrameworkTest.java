package org.hq.autotest.framework.test;

import com.hq.test.framework.testcase.BaseTestcase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class FrameworkTest extends BaseTestcase {

	protected Logger l = LogManager.getLogger(this.getClass().getName());
	
	@Test
	public void testcase001() throws InterruptedException{
		d.get("http://www.patsnap.com");
		for(int i=0;i<=30;i++){
//			l.info("Test log {}", i);
			if((i % 7 == 0)&&(i != 0)){
				Thread.sleep(14000);
			}else{
				Thread.sleep(1000);
			}
		}
		
		
//		DBUtil db = new DBUtil("mysql", "192.168.3.60", "3111", "pms_test", "pms_w", "innopms");
//
//		List<HashMap<String, String>> result = db.query("select id, patent_name from patents limit 3");
//		
//		l.info("Row count is {}", result.size());
//		for (HashMap<String, String> row : result) {
//			l.info("Col count is {}", row.size());
//			for (String key : row.keySet()){
//				l.info("Key is {}", key);
//				l.info("Value is {}", row.get(key));
//			}
//		}
//		
//		db.update("update patents set patent_name = '1234' where id = 787");
//		
//		db.disConnnect();
	}

}
