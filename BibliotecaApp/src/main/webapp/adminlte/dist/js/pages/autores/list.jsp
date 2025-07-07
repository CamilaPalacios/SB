<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado de Autores</title>
</head>
<body>
    <h1>Autores</h1>
    
    <c:if test="${not empty mensajeExito}">
        <div class="alert alert-success">${mensajeExito}</div>
        <c:remove var="mensajeExito" scope="session"/>
    </c:if>
    
    <a href="${pageContext.request.contextPath}/autores?action=new" class="btn btn-primary">Nuevo Autor</a>
    
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellido Paterno</th>
                <th>Apellido Materno</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${autores}" var="autor">
                <tr>
                    <td>${autor.idAutor}</td>
                    <td>${autor.nombreAutor}</td>
                    <td>${autor.apellidoPaterno}</td>
                    <td>${autor.apellidoMaterno}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/autores?action=edit&id=${autor.idAutor}" class="btn btn-warning">Editar</a>
                        <a href="${pageContext.request.contextPath}/autores?action=delete&id=${autor.idAutor}" class="btn btn-danger">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>