package com;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnection {
	
	private static String url="jdbc:mysql://localhost:3306/DB_ImageUpload", user="root", password="root";
	
	public Connection getConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);	
			return connection;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error : "+e);
		}
		return null;
	}
}
