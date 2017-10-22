package com.codingSQL.controller;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codingSQL.model.Solution;
import com.codingSQL.model.SolutionDAO;


@WebServlet("/hp")
public class Servlet_HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			int limit = Integer.parseInt(getServletContext().getInitParameter(("numberSolutions")));
//			response.getWriter().append(limit + "sdf");
//		
//			Solution[] sList = SolutionDAO.loadAllSolutions(limit);
//			Exercise[] eList = ExerciseDAO.loadExercisesOfLatestSolution(sList);
//			User[] uList = UserDAO.loadUsersOfLatestSolution(sList);
//			int sListLength = sList.length;
			
			Solution[] sList = SolutionDAO.loadAllSolutions(limit);
			request.setAttribute("sList", sList);
			
			
//			request.setAttribute("eList", eList);
//			request.setAttribute("uList", uList);
//			request.setAttribute("sListLength", sListLength);
			System.out.println("Servler HomePage zaladowany");
			getServletContext().getRequestDispatcher("/view/HomePage_view.jsp").forward(request, response);
			
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
