package com.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/create")

public class CreateTable extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String url="jdbc:postgresql://localhost:5433/employee_servlet_jdbc";
		String user ="postgres";
		String password = "1234";
		
		try {
			//Step 1: Load Driver
			Class.forName("org.postgresql.Driver");
			
			//Step 2: Create Connection
			Connection con = DriverManager.getConnection(url, user,password);
			System.out.println("Database Connected Successfully");
			//Step 3: Create Statement
			Statement st = con.createStatement();
			
			//Step 4: SQL Query
			String sql = "CREATE TABLE IF NOT EXISTS employee("
					+ "id INT PRIMARY KEY,"
					+ "name VARCHAR(100),"
					+ "email VARCHAR(100),"
					+ "password VARCHAR(100),"
					+ "salary DOUBLE PRECISION)";
			
			//Step 5: Execute Query
			st.execute(sql);
			
			out.println("<h2>Employee Table Created Successfully</h2>");
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			/*
			 * TASK:
			 * 
			 * Create a form to accept 3 numbers 2 integer and 1 decimal
			 * create servlet class read data and perform addition operation
			 * for all 3 numbers... and display result in console and browser......
			 * 
			 * jdbc+servlet(read data from form)
			 * Create Servlet web app maven project called employee-servlet-jdbc
			 * --Add Servlet and postgre dependency
			 * --Create a database in postgre called employee-servlet-jdbc
			 * 
			 * Create a Servlet class, give url, override doGet(), in which we need to write jdbc 5 steps
			 * and create a table (Use Statement interface)
			 * table called employee with columns id,name,email,password,salary
			 * 
			 * Create a register.html form and accept id,name,email,password,salary in form
			 * Create a Servlet class RegisterEmployee and read data from register.html form
			 * do necessary convertion
			 * and store it in database using PreparedStatement
			 * hare no main method is used
			 * write everything in doGet() method itself 
			 * 
			 * do update name based on id
			 * do delete based id
			 * -------------------------------------------------------
			 * create html page,create a form to accept id
			 * create servlet class in which read id from form, do conversions
			 * and write jdbc code to fetch one emp data from table based on id
			 * transfer data(entire Resultset obj itself) to another servlet class
			 * and display details in console and browser
			 */
		}
	}

}
