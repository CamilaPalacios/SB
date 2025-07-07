<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${accion == 'create' ? 'Nuevo Autor' : 'Editar Autor'}</title>
</head>
<body>
    <h1>${accion == 'create' ? 'Nuevo Autor' : 'Editar Autor'}</h1>
    
    <form action="${pageContext.request.contextPath}/autores" method="POST">
        <input type="hidden" name="action" value="${accion}">
        
        <c:if test="${accion == 'update'}">
            <input type="hidden" name="id" value="${autor.idAutor}">
        </c:if>
        
        <div class="form-group">
            <label>Nombre:</label>
            <input type="text" name="nombre" value="${autor.nombreAutor}" required class="form-control">
        </div>
        
        <div class="form-group">
            <label>Apellido Paterno:</label>
            <input type="text" name="apellidoPaterno" value="${autor.apellidoPaterno}" required class="form-control">
        </div>
        
        <div class="form-group">
            <label>Apellido Materno:</label>
            <input type="text" name="apellidoMaterno" value="${autor.apellidoMaterno}" class="form-control">
        </div>
        
        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="${pageContext.request.contextPath}/autores" class="btn btn-secondary">Cancelar</a>
    </form>
</body>
</html>