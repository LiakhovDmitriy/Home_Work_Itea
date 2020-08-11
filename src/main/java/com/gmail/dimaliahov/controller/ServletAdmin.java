package com.gmail.dimaliahov.controller;

import com.gmail.dimaliahov.db.DbProduct;
import com.gmail.dimaliahov.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServletAdmin extends HttpServlet {
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/admin.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		boolean showForm = true;
		boolean isError = false;
		StringBuilder errorText = new StringBuilder();
		errorText.append("<form id='loginForm' style='margin-top: 20px'> <div class='field'>");

		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String categoryID = request.getParameter("categoryID");
		String rating = request.getParameter("rating");
		String description = request.getParameter("description");
		String shortDescription = request.getParameter("shortDescription");
		String discount = request.getParameter("discount");

		if (name != null) {
			if (name.isEmpty()) {
				errorText.append("<label>The 'name' is empty.</label>");
				isError = true;
			}

			if (price.isEmpty()) {
				errorText.append("<label><br>The 'price' is empty.</label>");
				isError = true;
			}
			if (categoryID.isEmpty()) {
				errorText.append("<label><br>The 'categoryID' is empty.</label>");
				isError = true;
			}
			if (rating.isEmpty()) {
				errorText.append("<label><br>The 'rating' is empty.</label>");
				isError = true;
			}
			if (description.isEmpty()) {
				errorText.append("<label><br>The 'description' is empty.</label>");
				isError = true;
			}
			if (shortDescription.isEmpty()) {
				errorText.append("<label><br>The 'shortDescription' is empty.</label>");
				isError = true;
			}
			errorText.append("</div></form>");
			if (!isError) {
				Product product = new Product();

				product.setName(name);
				product.setPrice(Integer.parseInt(price));
				product.setCategoryID(Integer.parseInt(categoryID));
				product.setRating(Integer.parseInt(rating));
				product.setDescription(description);
				product.setShortDescription(shortDescription);
				product.setDiscount(Integer.parseInt(discount));

				DbProduct dbProduct = new DbProduct();
				dbProduct.setProduct(product);
				System.out.println(" servlet admin ");

				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/shop.jsp");
				rd.forward(request, resp);
				showForm = false;
			}
		}
		errorText.append("</table>");
		if (showForm) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/admin.jsp");
			request.setAttribute("name", name);
			request.setAttribute("price", price);
			request.setAttribute("categoryID", categoryID);
			request.setAttribute("rating", rating);
			request.setAttribute("description", description);
			request.setAttribute("shortDescription", shortDescription);
			request.setAttribute("discount", discount);
			request.setAttribute("ERROR", errorText.toString());
			rd.forward(request, resp);
		}

	}
}
