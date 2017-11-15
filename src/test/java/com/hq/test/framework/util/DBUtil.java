package com.hq.test.framework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 数据库工具
 * @author hanqing
 *
 */
public class DBUtil {

	Logger l = LogManager.getLogger(this.getClass().getName());
	String driver;
	String url;
	String uid;
	String pwd;
	Connection conn;
	
	/**
	 * 构造方法
	 * @param driverType -- 驱动类型（mysql, oracle, sqlserver）
	 * @param server -- 服务器地址
	 * @param port -- 端口
	 * @param database -- 数据库
	 * @param p_uid -- 用户名
	 * @param p_pwd -- 密码
	 */
	public DBUtil(String driverType, String server, String port, String database, String p_uid, String p_pwd){
		try{
			l.entry();
			if (driverType.toLowerCase().equals("mysql")){
				l.debug("Using mysql");
				driver = "com.mysql.jdbc.Driver";
			}else{
				l.debug("Invalid database type");
				driver = null;
			}
			url = String.format("jdbc:mysql://%s:%s/%s", server, port, database);
			uid = p_uid;
			pwd = p_pwd;
			l.exit();
		}catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
		}

	}

	/**
	 * 连接数据库
	 */
	public void connnect() {
		try {
			l.entry();
			if (conn == null){
				l.debug("Connection does not exist, so create a new one");
	            Class.forName(driver);
	            conn = DriverManager.getConnection(url, uid, pwd);
			}else{
				l.trace("Connection exists");
			}
		} catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 断开数据库连接
	 */
	public void disConnnect() {
		try {
			l.entry();
			if (conn == null){
				l.debug("Connection is already closed");
			}else{
				l.debug("Close connection");
				conn.close();
			}
		} catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询数据库
	 * @param sql语句
	 * @return 查询返回数据
	 */
	public List<HashMap<String, String>> query(String sql){
		try{
			l.entry();
			List<HashMap<String, String>> queryResult = new ArrayList<HashMap<String, String>>();
			this.connnect();		
            Statement statement = conn.createStatement();
            l.debug("Sql: {}", sql);
            ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<String, String>();
				int colCount = rs.getMetaData().getColumnCount();
				for (int i=1;i<=colCount;i++){
					String key = rs.getMetaData().getColumnName(i);
					l.debug("Key is {}", key);
					String value = rs.getString(key);
					l.debug("Value is {}", value);
					row.put(key, value);
				}
				queryResult.add(row);
			}
			return queryResult;	
		}catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 更新数据库
	 * @param sql语句
	 * @return 成功更新记录条数
	 */
	public int update(String sql) {
		try {
			l.entry();
			this.connnect();
            Statement statement = conn.createStatement();
            l.debug("Sql: {}", sql);
            int result = statement.executeUpdate(sql);
            l.debug("Affected rows: {}", result);
            return result;
		} catch (Exception e) {
			l.error("Error!");
			e.printStackTrace();
			l.debug("Affected rows: 0");
			return 0;
		}
	}
}
