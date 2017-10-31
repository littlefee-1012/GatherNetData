package com.briup.utils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtils {
	private static DBUtils du;
	private static Properties properties;

	// 获取单例对象：
	// 1.私有化构造器
	private DBUtils(){};

	// 2.提供共有的获取对象的方法，该方法中获得的对象时单例的
	public static DBUtils getInstance(){
		//  避免过度加锁行为
		if(du==null){
			synchronized(DBUtils.class){
				// 真正的生成单例对象
				if(du==null){
					du=new DBUtils();
				}
			}
		}
		return du;
	}

	static{
		properties=new Properties();
		try{
			properties.load(
				ClassLoader.getSystemResourceAsStream(
					"db.properties"));
			Class.forName(properties.getProperty("driver"));

		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	public static Connection getConn() throws SQLException{
		/*String url=properties.getProperty("protocol")
			+properties.getProperty("host_ip")+":"
			+properties.getProperty("port")+"/"
			+properties.getProperty("db_name")+"?user=root&password=root?useSSL=true";*/
		String url = properties.getProperty("url");
		return DriverManager.getConnection(url,properties);
	}

	public static void close(ResultSet resultSet,
					  Statement statement,
					  Connection connection){
		if(resultSet!=null){
			try{
				resultSet.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		if(statement!=null){
			try{
				statement.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		if(connection!=null){
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
