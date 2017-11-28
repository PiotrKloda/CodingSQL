package com.codingSQL.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codingSQL.model.User;
import com.codingSQL.model.UserDAO;
import com.codingSQL.model.User_group;
import com.codingSQL.model.User_groupDAO;

@WebServlet("/ugDetails")
public class Servlet_groupDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int ug_id = Integer.parseInt(request.getParameter("ug_id"));
			User_group ug = User_groupDAO.loadUser_groupById(ug_id);
			User[] u = UserDAO.loadAllByGroupId(ug_id);

			request.setAttribute("ug", ug);
			request.setAttribute("u", u);
			System.out.println("Servlet_groupDetails works correctly");
			getServletContext().getRequestDispatcher("/view/groupDetails_view.jsp").forward(request, response);

		} catch (SQLException e) {
			System.out.println("Failed to load User_group details");
			e.printStackTrace();
		}

	}

}
