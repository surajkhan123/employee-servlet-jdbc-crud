package com.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginEmployee extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		try {
			//read data from form
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			Class.forName("org.postgresql.Driver");
			
			Connection con =
	                DriverManager.getConnection(
	                        "jdbc:postgresql://localhost:5433/employee_servlet_jdbc",
	                        "postgres",
	                        "1234");
			
			String sql = 
					"select * from employee where email=? and password=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			resp.setContentType("text/html");
			
			PrintWriter p =resp.getWriter();
			
			if(rs.next()) {
				p.print("<h2>Login Success</h2>");
			}else {
				p.print("<h2>Login Failed</h2>");
				req.getRequestDispatcher("login.html").include(req, resp);
			
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
}
