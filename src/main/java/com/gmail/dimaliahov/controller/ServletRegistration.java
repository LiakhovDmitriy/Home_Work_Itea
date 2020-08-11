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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServletRegistration extends HttpServlet {
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/register.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		boolean showForm = true;
		boolean isError = false;
		StringBuilder errorText = new StringBuilder();
		errorText.append("<form id='loginForm' style='margin-top: 20px'> <div class='field'>");
		HttpSession httpSession = request.getSession();

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		if (login != null) {
			if (login.isEmpty()) {
				errorText.append("<label>The 'login' is empty.</label>");
				isError = true;
			}
			Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher matcher = pattern.matcher(login);
			boolean loginBol = matcher.matches();
			if (!loginBol) {
				errorText.append("<label><br>The 'login' is wrong. It should look like 'name@gmail.com'.</label>");
				isError = true;
			}
			if (password.isEmpty()) {
				errorText.append("<label><br>The 'password' is empty.</label>");
				isError = true;
			}
			if (!password.isEmpty()){
				Pattern patternP = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
				Matcher matcherP = patternP.matcher(password);
				boolean passwordBol = matcherP.matches();
				if (!passwordBol) {
					errorText.append("<label><br>The 'password'at least 8 characters, contains at least one digit, contains at least one lower alpha char and one upper alpha char, no whitespace allowed in the entire string.</label>");
					isError = true;
				}
			} else {
				errorText.append("<label><br>The 'password' and 'rePassword' must be the same.</label>");
				isError = true;
			}
			if (name.isEmpty()) {
				errorText.append("<label><br>The 'name' is empty.</label>");
				isError = true;
			}
			Pattern patternN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z]).{3,}$");
			Matcher matcherN = patternN.matcher(name);
			boolean passwordName = matcherN.matches();
			if (!passwordName) {
				errorText.append("<label><br>The 'Name' must be the correct. At least 3 characters, contains at least one lower alpha char and one upper alpha char, no whitespace allowed in the entire string</label>");
				isError = true;
			}
			if (gender.isEmpty()) {
				errorText.append("<label><br>The 'gender' is empty.</label>");
				isError = true;
			}
			if (country.isEmpty()) {
				errorText.append("<label><br>The 'country' is empty.</label>");
				isError = true;
			}
			if (city.isEmpty()) {
				errorText.append("<label><br>The 'City' is empty.</label>");
				isError = true;
			}
			if (address.isEmpty()) {
				errorText.append("<label><br>The 'address' is empty.</label>");
				isError = true;
			}if (phone.isEmpty()) {
				errorText.append("<label><br>The 'phone' is empty.</label>");
				isError = true;
			}

			errorText.append("</div></form>");
			if (!isError) {
				UserPojo userPojo = new UserPojo();
				if (login != null) {
					userPojo.setName(name);
					userPojo.setLogin(login);
					userPojo.setPassword(password);
					userPojo.setGender(gender);
					userPojo.setAddress(address);
					userPojo.setCity(city);
					userPojo.setCountry(country);
					userPojo.setPhone(phone);

					DbUser dbUser = new DbUser();
					dbUser.setUser(userPojo);
					httpSession.setAttribute("userPojo", userPojo);
				}
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/index.jsp");
				request.setAttribute("login", login);
				rd.forward(request, resp);
				showForm = false;
			}
		}
		errorText.append("</table>");
		if (showForm) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/register.jsp");
			request.setAttribute("login", login);
			request.setAttribute("password", password);
			request.setAttribute("name", name);
			request.setAttribute("gender", gender);
			request.setAttribute("address", address);
			request.setAttribute("city", city);
			request.setAttribute("country", country);
			request.setAttribute("phone", phone);
			request.setAttribute("ERROR", errorText.toString());
			rd.forward(request, resp);
		}

	}
}
