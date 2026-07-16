package com.first;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchEmployee extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException{
		
		try {
			//System.out.println("Search Servlet Called");
			
			int id = Integer.parseInt(req.getParameter("id"));
			
			
			Class.forName("org.postgresql.Driver");
			Connection con =
			        DriverManager.getConnection(
			                "jdbc:postgresql://localhost:5433/employee_servlet_jdbc",
			                "postgres",
			                "1234");
			String sql = "SELECT * FROM employee WHERE id=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			//System.out.println("Query Executed");
			
			
			req.setAttribute("data", rs);
			
			
			req.getRequestDispatcher("display").forward(req, resp);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
