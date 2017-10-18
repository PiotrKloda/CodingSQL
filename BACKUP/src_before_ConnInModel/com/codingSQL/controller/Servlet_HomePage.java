package com.codingSQL.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.JDBCType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class Servlet_HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			int limit = Integer.parseInt(getServletContext().getInitParameter(("numberSolutions")));
			response.getWriter().append(limit + "sdf");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			response.getWriter().append( "sdf");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/codingSQL?useSSL=false","root","coderslab");
		
			//Solution[] sList = Solution.loadAllSolutions(conn, limit)
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
