package controllers;

import db.DBwork;
import model.UserPojo;

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
		RequestDispatcher rd = req.getRequestDispatcher ("WEB-INF/views/formaRegistration.jsp");
		rd.forward (req, resp);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		boolean showForm = true;
		boolean isError = false;
		StringBuilder errorText = new StringBuilder ();
		errorText.append ("<form id='loginForm' style='margin-top: 20px'> <div class='field'>");
		HttpSession httpSession = request.getSession ();

		String login = request.getParameter ("login");
		String password = request.getParameter ("password");
		String rePassword = request.getParameter ("rePassword");
		String name = request.getParameter ("name");
		String gender = request.getParameter ("gender");
		String genderM = "";
		String genderF = "";
		String address = request.getParameter ("address");
		String addressLrn = "";
		String addressDnr = "";
		String addressCrimea = "";
		String comment = request.getParameter ("comment");
		String agree = request.getParameter ("agree");

		if (login != null) {

			if (login.isEmpty ()) {
				errorText.append ("<label>The 'login' is empty.</label>");
				isError = true;
			}
			Pattern pattern = Pattern.compile ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher matcher = pattern.matcher (login);
			boolean loginBol = matcher.matches ();
			if (! loginBol) {
				errorText.append ("<label>The 'login' is wrong. It should look like 'name@gmail.com'.</label>");
				isError = true;
			}
			if (password.isEmpty ()) {
				errorText.append ("<label>The 'password' is empty.</label>");
				isError = true;
			}
			if (rePassword.isEmpty ()) {
				errorText.append ("<label>The 'rePassword' is empty.</label>");
				isError = true;
			}
			if (! password.isEmpty () && ! rePassword.isEmpty () && password.equals (rePassword)) { //если не пусті і однакові
				Pattern patternP = Pattern.compile ("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
				Matcher matcherP = patternP.matcher (password);
				boolean passwordBol = matcherP.matches ();
				if (! passwordBol) {
					errorText.append ("<label>The 'password' and 'rePassword' must be the same and at least 8 characters, contains at least one digit, contains at least one lower alpha char and one upper alpha char, no whitespace allowed in the entire string.</label>");
					isError = true;
				}
			} else {
				errorText.append ("<label>The 'password' and 'rePassword' must be the same.</label>");
				isError = true;
			}
			if (name.isEmpty ()) {
				errorText.append ("<label>The 'name' is empty.</label>");
				isError = true;
			}
			Pattern patternN = Pattern.compile ("^(?=.*[a-z])(?=.*[A-Z]).{3,}$");
			Matcher matcherN = patternN.matcher (name);
			boolean passwordName = matcherN.matches ();
			if (! passwordName) {
				errorText.append ("<label>The 'Name' must be the correct. At least 3 characters, contains at least one lower alpha char and one upper alpha char, no whitespace allowed in the entire string</label>");
				isError = true;
			}
			if (gender.isEmpty()) {
				errorText.append ("<label>The 'gender' is empty.</label>");
				genderM = "checked";
				isError = true;
			} else if (gender.equals ("male")) {
				genderM = "checked";
			} else  if (gender.equals ("female")) {
				genderF = "checked";
			}

			if (address == "") {
				errorText.append ("<label>The 'address' is empty.</label>");
				isError = true;
			} else if (address != ""){
				if (address.equals ("lrn")) {
					addressLrn = "selected";
				}
				if (address.equals ("dnr")) {
					addressDnr = "selected";
				}
				if (address.equals ("crimea")) {
					addressCrimea = "selected";
				}
			}
			if (comment.isEmpty ()) {
				errorText.append ("<label>The 'comment' is empty.</label>");
				isError = true;
			}
			if (agree == null) {
				errorText.append ("<label>The 'agree' is empty.</label>");
				isError = true;
			}
			errorText.append ("</div></form>");
			if (! isError) {
				UserPojo userPojo = new UserPojo ();
				if (login != null) {
					userPojo.setName (name);
					userPojo.setLogin (login);
					userPojo.setComment (comment);
					userPojo.setAddress (address);
					userPojo.setGender (gender);
					userPojo.setPassword (password);

					DBwork dBwork = new DBwork ();
					dBwork.setUser (userPojo);
					httpSession.setAttribute ("userPojo", userPojo);
				}
				RequestDispatcher rd = request.getRequestDispatcher ("WEB-INF/views/regSuccessful.jsp");
				request.setAttribute ("login", login);
				rd.forward (request, resp);

				showForm = false;
			}
		}
		errorText.append ("</table>");
		if (showForm) {

			RequestDispatcher rd = request.getRequestDispatcher ("WEB-INF/views/formaRegistration.jsp");
			request.setAttribute ("login", login);
			request.setAttribute ("password", password);
			request.setAttribute ("name", name);
			request.setAttribute ("gender", gender);
			request.setAttribute ("genderM", genderM);
			request.setAttribute ("genderF", genderF);
			request.setAttribute ("addressLrn", addressLrn);
			request.setAttribute ("addressDnr", addressDnr);
			request.setAttribute ("addressCrimea", addressCrimea);
			request.setAttribute ("comment", comment);
			request.setAttribute ("ERROR", errorText.toString ());
			rd.forward (request, resp);
		}

	}
}
