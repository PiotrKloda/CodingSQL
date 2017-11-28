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

@WebServlet("/ug_edit")
public class Servlet_admin_ug_edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int id = Integer.parseInt(request.getParameter("ug_Id"));
			User_group ug = User_groupDAO.loadUser_groupById(id);

			request.setAttribute("ug_id", ug);
			getServletContext().getRequestDispatcher("/view/form_ug_edit.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
