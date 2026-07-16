package com.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/display")
public class DisplayEmployee extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		
		
			try {
				
				ResultSet rs = (ResultSet) req.getAttribute("data");
		
				
				PrintWriter pw = resp.getWriter();
				
				
					if(rs != null && rs.next()) {
						
						//
						/*int id = rs.getInt("id");
						String name = rs.getString("name");
				        String email = rs.getString("email");
				        double salary = rs.getDouble("salary");*/
				        
				      //Console
						System.out.println("ID: "+rs.getInt("id"));
						System.out.println("Name: "+rs.getString("name"));
						System.out.println("Email: "+rs.getString("email"));
						System.out.println("Salary: "+rs.getDouble("salary"));
						
				        /*System.out.println("ID = " + id);
				        System.out.println("Name = " + name);
				        System.out.println("Email = " + email);
				        System.out.println("Salary = " + salary);*/
				        
						//Browser
						/*pw.println("ID : "+id+"<br>");
					    pw.println("Name : " + name + "<br>");
				        pw.println("Email : " + email + "<br>");
				        pw.println("Salary : " + salary);*/
						
						resp.setContentType("text/html");
						resp.getWriter().println(
								"ID: "+rs.getInt("id")+"<br>"+
								"Name: "+ rs.getString("name")+"<br>"+
								"Email: "+rs.getString("email")+"<br>"+
								"Salary: "+rs.getDouble("salary")
								);
					} else {
						resp.getWriter().println("Employee Not Found");
					
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
	
}
