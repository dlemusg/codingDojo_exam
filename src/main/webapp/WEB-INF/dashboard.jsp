<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bienvenida</title>
</head>
<body>
	<h1>¡Bienvenid@ ${userSession.name}</h1>
	<a href="/logout">Cerrar Sesion</a>

</body>
</html>