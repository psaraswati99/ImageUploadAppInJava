package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/View")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter=response.getWriter();
		
		
		//creating connection object
		MysqlConnection mysqlConnection = new MysqlConnection();
		Connection connection = mysqlConnection.getConnection();
		
		//Define variable
		String id,name,imagePath;
		id = name = imagePath = null;
		
		try {
			
			String sql = "select * from TB_ImageUpload";
			Statement statement=(Statement) connection.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			
			printWriter.println("Insert Record : <a href='Insert.html'>Add</a><table border='1'><tr><th>ID</th><th>NAME</th><th>IMAGE</th><th>UPDATE</th><th>DELETE</th></tr>");
			
			while(rs.next())
			{
				id = rs.getString(1);
				name = rs.getString(2);
				imagePath = rs.getString(4);
				
				printWriter.print("<tr><td>"+ id +"</td><td>"+ name +"</td><td><img src="+ imagePath +" height='50' width='50'></td>"
						+ "<td><a href='./Update.jsp?id="+ id +"'>Update</a></td>"
								+ "<td><a href='./Delete?id="+ id +"'>Delete</a></td></tr>");
			}
			
			printWriter.print("</table>");
			
		} catch (Exception e) {
			printWriter.println("Error: "+e);
			
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				printWriter.println("Error: "+e);
			}  
		}
	}
}
