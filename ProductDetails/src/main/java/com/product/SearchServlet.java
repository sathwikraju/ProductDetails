package com.product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve the product ID parameter from the request
        String productid=request.getParameter("id");

        // Use the DBConnection class to get the database connection
        Connection conn = DBConnection.getConnection();
        try {
            conn = DBConnection.getConnection();

            // Prepare a SQL SELECT statement to query the product table for the given product ID
            String sql = "SELECT * FROM product WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, productid);

            // Execute the SQL statement and retrieve the result set
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {       	
                out.println("<h1>Product Details</h1>");
                out.println("<table border=2>");
                out.println("<tr>"
                +"<th>Product Id</th>"
                +"<th>Product Name</th>"
                +"<th>Price</th>"
                +"<th>Model</th>"
                +"</tr>");
                
    				out.println("<tr>");
    				out.print("<td>"+rs.getString("id")+"</td>");
    				out.print("<td>"+rs.getString("name")+"</td>");
    				out.print("<td>"+rs.getFloat("price")+"</td>");
    				out.print("<td>"+rs.getString("description")+"</td>");
    				out.println("</tr>");
    			
                out.println("</table>");
                out.println("<a href=index.html>Back to Home</a>");
            } else {
                // If the result set is empty, display an error message
                out.println("<html><body>");
                out.println("<h1>Error</h1>");
                out.println("<p>Product not found.</p>");
                out.println("<a href=index.html>Back to Home</a>");
                out.println("</body></html>");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
    }
}
