package com.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ViewProducts")
public class ViewProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con = DBConnection.getConnection();
			String query = "select * from product";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			PrintWriter out = response.getWriter();
			out.println("<table border=2>");
			out.println("<tr>"
					+"<th>Product ID</th>"
					+"<th>Product Name</th>"
					+"<th>Price</th>"
					+"<th>Model</th>");
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>"+rs.getInt("id")+"</td>");
				out.println("<td>"+rs.getString("name")+"</td>");
				out.println("<td>"+rs.getFloat("price")+"</td>");
				out.println("<td>"+rs.getString("description")+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<a href=index.html>Back to Home</a>");
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
