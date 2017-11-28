package com.codingSQL.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codingSQL.model.Exercise;
import com.codingSQL.model.ExerciseDAO;
import com.codingSQL.model.Solution;
import com.codingSQL.model.SolutionDAO;
import com.codingSQL.model.User;
import com.codingSQL.model.UserDAO;

@WebServlet("/solutionDetails")
public class Servlet_solutionDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int sol_Id = Integer.parseInt(request.getParameter("sol_id"));
			Solution s = SolutionDAO.loadSolutionById(sol_Id);
			Exercise e = ExerciseDAO.loadExerciseById(s.getExercise_id());
			User u = UserDAO.loadUserById(s.getUsers_id());

			request.setAttribute("solution", s);
			request.setAttribute("user", u);
			request.setAttribute("exercise", e);
			getServletContext().getRequestDispatcher("/view/solutionDetails_view.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
