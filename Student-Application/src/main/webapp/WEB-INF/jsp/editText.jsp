	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<style>
input[type=submit]{
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 16px 32px;
  text-decoration: none;
  margin: 4px 2px;
  cursor: pointer;
}
</style>
</head>
<body>


	Your Snippet Text is :
	
	 ${name}
	
	<br>
	Click on "Edit Text" button to change text
	<form:form modelAttribute="TextSnipper" method="POST" action="/textSnippet/">
	<!-- Please enter text :
	 <input path="inputText" /> --> 
	    <input type="hidden" name ="hiddenText" value="${name}">
		<input type="submit" value="Edit Text">
	</form:form>
</body>
</html>