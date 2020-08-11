package com.gmail.dimaliahov.controller;

import com.gmail.dimaliahov.db.DbProduct;
import com.gmail.dimaliahov.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletSingleProd extends HttpServlet {

	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/single.jsp");

		String productID = req.getParameter("idProduct");
		System.out.println("productID" + productID);
		DbProduct dbProduct = new DbProduct();

		Product product ;
		product = dbProduct.getProductByID(Integer.parseInt(productID));
		req.setAttribute("productForSinglPag",product);
		rd.forward(req, resp);
	}


}
