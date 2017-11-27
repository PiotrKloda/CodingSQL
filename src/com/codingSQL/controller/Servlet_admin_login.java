package com.codingSQL.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin_login")
public class Servlet_admin_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String password = request.getParameter("password");
		
		if (password.equals("coderslab")) {
			response.sendRedirect("http://localhost:8080/codingSQL/panel");
		} else {
			System.out.println("Wrong password, enter again");
			response.sendRedirect("http://localhost:8080/codingSQL/view/admin_login_form.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String password = request.getParameter("password");
		
		if (password.equals("coderslab")) {
			response.sendRedirect("http://localhost:8080/codingSQL/panel");
		} else {
			System.out.println("Wrong password, enter again");
			response.sendRedirect("http://localhost:8080/codingSQL/view/admin_login_form.jsp");
		}

		
	}

}
