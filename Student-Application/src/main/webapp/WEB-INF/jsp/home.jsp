<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<title>Text Snippet Application</title>
</head>
<body>
	<h2>Welcome to Text Snippet Application</h2>
	<form:form modelAttribute="TextSnipper" method="POST" action="/textSnippet/welcome">
	<textarea rows="8" cols="100" name="inputText">${name}</textarea> 
	<br>
		<input  type="submit" value="Save">
	</form:form>
</body>
</html>