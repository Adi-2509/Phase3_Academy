package com.simplileatn.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.jdbc.util.JDBCUtil;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con = null;
	Statement pStmt = null;
	ResultSet rs=null;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		
		try {
		
			// get a connection
				con=JDBCUtil.getMySqlConnection();
			// create sql stmt
			
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM students"); /*PLACE HOLDER*/
			// execute query
				rs= pstmt.executeQuery();
			//rs = pstmt.executeQuery();

			// process result
				while (rs.next()) {

				// retrieve data from result set row
					String fname = rs.getString("fname");
					out.println(fname);

				}

			} catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
			}
		
	}

	
}
