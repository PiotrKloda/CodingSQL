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

@WebServlet("/groupsList")
public class Servlet_groupsList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			User_group[] ugList = User_groupDAO.loadAllUser_groups();

			request.setAttribute("ugList", ugList);
			System.out.println("Servlet_groupsList works correctly");
			getServletContext().getRequestDispatcher("/view/groupsList_view.jsp").forward(request, response);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
