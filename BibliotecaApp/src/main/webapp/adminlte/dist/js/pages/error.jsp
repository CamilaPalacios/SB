<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error</title>
</head>
<body>
    <h1>Error</h1>
    
    <div class="alert alert-danger">${error}</div>
    
    <a href="${pageContext.request.contextPath}/autores" class="btn btn-primary">Volver al listado</a>
</body>
</html>