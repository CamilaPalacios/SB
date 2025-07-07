<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Confirmar Eliminación</title>
</head>
<body>
    <h1>Confirmar Eliminación</h1>
    
    <p>¿Está seguro que desea eliminar al autor ${autor.nombreCompleto}?</p>
    
    <form action="${pageContext.request.contextPath}/autores" method="POST">
        <input type="hidden" name="action" value="confirm-delete">
        <input type="hidden" name="id" value="${autor.idAutor}">
        
        <button type="submit" class="btn btn-danger">Confirmar Eliminación</button>
        <a href="${pageContext.request.contextPath}/autores" class="btn btn-secondary">Cancelar</a>
    </form>
</body>
</html>