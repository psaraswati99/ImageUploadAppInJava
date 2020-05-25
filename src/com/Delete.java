package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter=response.getWriter();
		
		//creating connection object
		MysqlConnection mysqlConnection = new MysqlConnection();
		Connection connection = mysqlConnection.getConnection();
		
		//getting data from request object
		String id = request.getParameter("id");
		System.out.println("ID: "+id);		
		
		try {  
			String sql = "DELETE FROM TB_ImageUpload WHERE ID="+id+"";
			Statement statement = connection.createStatement();
			int row = statement.executeUpdate(sql);
			if(row > 0) {
				request.getRequestDispatcher("View").forward(request, response);
			}

		} catch (Exception e) {
			printWriter.println("Error: "+e);
			
		} finally {
			try{
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				printWriter.println("Error: "+e);
			}
		}
	}
}

