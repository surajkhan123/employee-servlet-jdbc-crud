package com.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteEmployee extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException{
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		int id = Integer.parseInt(req.getParameter("id"));
		
        String url =
        "jdbc:postgresql://localhost:5433/employee_servlet_jdbc";

        String user = "postgres";
        String password = "1234";
        
        
        try {
			Class.forName("org.postgresql.Driver");

			Connection con =
			DriverManager.getConnection(url,user,password);
			
			String sql = "DELETE FROM employee WHERE id=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			int result = ps.executeUpdate();
			
			if(result > 0) {
			    out.println("<h2>Employee Deleted Successfully</h2>");
			} else {
			    out.println("<h2>Employee ID Not Found</h2>");
			}

			
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
