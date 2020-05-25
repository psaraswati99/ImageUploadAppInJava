package com;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.jdbc.PreparedStatement;

@MultipartConfig
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter printWriter=response.getWriter();
		
		//creating connection object
		MysqlConnection mysqlConnection = new MysqlConnection();
		Connection connection = mysqlConnection.getConnection();
		
		//getting data from request object
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		System.out.println("ID: "+id+ " Name: "+name);
		
		//getting image source as a part object
		Part part = request.getPart("image");
		
		//get input stream 
		InputStream fileContent = part.getInputStream();
		OutputStream outputStream =null;
		String imagePath = null;
		
		if (fileContent != null) {
			//store image in specified folder
			imagePath = "C:\\Users\\sprajapati\\Documents\\Eclipse_WorkSpace\\ImageCRUDOperation\\Images\\"+"Img_"+id+".jpeg";
			outputStream = new FileOutputStream(new File(imagePath));
			int read = 0;
			byte[] bytes = new byte[1024];
			while((read = fileContent.read(bytes)) !=-1){
				outputStream.write(bytes, 0, read);
			}
		}
		
		try {  
			String sql = "INSERT INTO TB_ImageUpload(ID,NAME,IMAGE,IMAGEPATH) VALUES(?,?,?,?)";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, name);
			if (fileContent != null) {
                // fetches input stream of the upload file for the blob column
                preparedStatement.setBlob(3, fileContent);
                preparedStatement.setString(4, imagePath);
            }
			int row = preparedStatement.executeUpdate();
			if(row > 0) {
				request.getRequestDispatcher("Success.jsp").forward(request, response);
			}

		} catch (Exception e) {
			printWriter.println("Error: "+e);
		} finally {
			try {
				outputStream.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				printWriter.println("Error: "+e);
			}
		}
	}

}
