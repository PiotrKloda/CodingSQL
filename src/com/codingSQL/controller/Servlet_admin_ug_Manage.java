package com.codingSQL.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codingSQL.model.User_group;
import com.codingSQL.model.User_groupDAO;

@WebServlet("/admin_ug")
public class Servlet_admin_ug_Manage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			User_group[] ugList = User_groupDAO.loadAllUser_groups();
			
			request.setAttribute("ugList", ugList);
			getServletContext().getRequestDispatcher("/view/admin_ug_Manage_view.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if ( request.getParameter("name")!=null ) {
			try {
				String name=request.getParameter("name");
				User_group ug = new User_group(name);
				User_groupDAO.saveToDB(ug);
				response.sendRedirect("http://localhost:8080/codingSQL/admin_ug");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if ( (Integer.parseInt(request.getParameter("num"))) > 0  ){
			try {
				int ugId=Integer.parseInt( request.getParameter("num"));
				User_groupDAO.delete(ugId);
				response.sendRedirect("http://localhost:8080/codingSQL/admin_ug");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("http://localhost:8080/codingSQL/admin_ug");
		}
		
		
	}

}
