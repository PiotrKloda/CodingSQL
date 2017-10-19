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

@WebServlet("/userDetails")
public class Servlet_userDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int user_id = Integer.parseInt(request.getParameter("u_id"));
		
		try {
			User u = UserDAO.loadUserById(user_id);
			Solution[] sList = SolutionDAO.loadAllByUserId(user_id);
			Exercise[] eList = ExerciseDAO.loadExercisesOfLatestSolution(sList);
			
			
			request.setAttribute("sList", sList);
			request.setAttribute("eList", eList);
			request.setAttribute("u", u);
			System.out.println("Servlet_userDetails works correctly");
			getServletContext().getRequestDispatcher("/view/userDetails_view.jsp").forward(request, response);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
