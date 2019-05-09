/**
 * @author Administrator
 * 2019年4月26日 下午12:36:55 
 */
package com.mf.entry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class DBUtils {
	private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dbURL = "jdbc:sqlserver://192.168.3.116:1433;DatabaseName=yun";
	private static String userName = "sa";
	private static String userPwd = "123456";

	private static Connection connection = null;

	public static synchronized Connection getConnection() {
		if (connection == null)
			try {
				Class.forName(driverName);
				connection = DriverManager.getConnection(dbURL, userName, userPwd);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		return connection;
	}
	
	public static void newMoFang(int sup_objectNo,String sup_objectId,int twologincount,int newcount,int dayreten,String objectId,Date date_origin,Date date_grab) {
		String sql = "insert into [yun].[dbo].[tb_mofang](sup_objectNo,sup_objectId,twologincount,newcount,dayreten,objectId,date_origin,date_grab) values(?,?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement st = null;
		try {
			connection = getConnection();
			st = connection.prepareStatement(sql);
			System.out.println("::::::::st"+st);
			
			st.setInt(1, sup_objectNo);
			st.setString(2, sup_objectId);
			st.setInt(3, twologincount);
			st.setInt(4, newcount);
			st.setInt(5, dayreten);
			st.setString(6, objectId);
			st.setTimestamp(7, new Timestamp(date_origin.getTime()));
			st.setTimestamp(8, new Timestamp(date_grab.getTime()));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
}
