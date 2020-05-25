<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*" session="true"%>
    
<%

HttpSession httpSession = request.getSession(true);
String id = request.getParameter("id");
httpSession.setAttribute("id", id);

%> 
<h2>Update Records:</h2>
<form method="post" action="Update" enctype="multipart/form-data">
	<table>
		<tr>
			<td>Id:</td>
			<td><input type="text" name="id" value="<%=id%>" disabled="disabled"></td>
		<tr>
		<tr>
			<td>Name:</td>
			<td><input type="text" name="name" required="required"></td>
		<tr>
		<tr>
			<td>Upload Image:</td>
			<td><input type="file" name="image" required="required"></td>
		<tr>
			<td><input type="reset" value="Reset"></td>
			<td><input type="submit" value="Update"></td>
		</tr>
		<tr>
			<td><a href="View">View Record</a></td>
		</tr>
	</table>
</form>