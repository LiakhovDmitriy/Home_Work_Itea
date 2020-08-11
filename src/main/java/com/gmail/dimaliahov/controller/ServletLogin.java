package com.gmail.dimaliahov.controller;

import com.gmail.dimaliahov.db.DbUser;
import com.gmail.dimaliahov.model.UserPojo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ServletLogin extends HttpServlet {
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher ("WEB-INF/views/login.jsp");
		rd.forward (req, resp);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		String login = request.getParameter("f1");
		String password = request.getParameter("f2");
		String logout = request.getParameter("logout");
		HttpSession session = request.getSession();


		StringBuilder stringBuilder = new StringBuilder();


		if (login == "" && password == "" && login.length() == 0 && password.length() == 0) {
			stringBuilder.append("Please input value of login and password first");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/login.jsp");
			request.setAttribute("error", stringBuilder);
			rd.forward(request, resp);
		} else {
			DbUser dbWork = new DbUser();
			UserPojo userPojo = null;
			try {
				userPojo = dbWork.getUser(login, password);
			} catch (SQLException throwables){
				throwables.printStackTrace();
			}
			session.setAttribute("userPojo", userPojo);
			if (session.getAttribute("userPojo") != null) {
				session.setAttribute("login", login);
				session.setAttribute("password", password);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/login.jsp");
				String roleAccess = userPojo.getRole();
				session.setAttribute("role", roleAccess);
				rd.forward(request, resp);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/login.jsp");
				request.setAttribute("login", null);
				request.setAttribute("password", null);
				stringBuilder.append("Access denied!");
				request.setAttribute("error", stringBuilder);
				rd.forward(request, resp);

			}
		}
		if (logout != null) {
			System.out.println("LOGOUT");
			session.removeAttribute("login");
			session.removeAttribute("password");
			session.removeAttribute("userPojo");
			session.removeAttribute("role");
		}
	}
}
