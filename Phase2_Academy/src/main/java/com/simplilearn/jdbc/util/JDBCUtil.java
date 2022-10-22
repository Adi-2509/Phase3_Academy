package com.simplilearn.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCUtil {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // MySql Driver
			//Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle Drive
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getOracleConnection() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1511:XE", "system", "****");
		return con;
	}

	public static Connection getMySqlConnection() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simplilearn", "root","root@123");	
		return con;
	}

	public static void cleanup(Statement st, Connection con) {
		try {
			if (st != null)
				((Connection) st).close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void cleanup(ResultSet rs, Statement st, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (con != null)
				con.close();
			if (st != null)
				((Connection) st).close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
