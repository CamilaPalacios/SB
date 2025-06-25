<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.systematic.app.biblioteca.models.CategoriaLibro"%>
<%
    List<CategoriaLibro> listaCategorias = (List<CategoriaLibro>) request.getAttribute("categorias");
    System.out.println("categorias = " + listaCategorias);
%>
<div class="container-fluid pt-3">
    <div class="row">
        <div class="col-12">

            <div class="row mb-2">
                <div class="col-sm-6">
                    <h3>Categorías de Libros</h3>
                </div>
                <div class="col-sm-6 text-right">
                    <button class="btn btn-primary" id="btnNuevaCategoria">
                        <i class="fas fa-plus"></i> Nueva Categoría
                    </button>
                </div>
            </div>

            <div class="card">
                <div class="card-body">
                    <table id="tablaCategorias" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Categoria</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (listaCategorias != null) {
                                    for (CategoriaLibro categoriaLibro : listaCategorias) {
                            %>
                            <tr>
                                <td><%= categoriaLibro.getIdCategoria()%></td>
                                <td><%= categoriaLibro.getNombreCategoria()%></td>
                                <td>
                                    <button class="btn btn-sm btn-warning btnEditar" data-id="<%= categoriaLibro.getIdCategoria()%>"
                                            data-nombre="<%=categoriaLibro.getNombreCategoria()%>">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn btn-sm btn-danger btnEliminar" data-id="<%= categoriaLibro.getIdCategoria()%>">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                            <%   }
                                }%>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- cargar el script categorias-datatables.js -->
<script src="${pageContext.request.contextPath}/js/categorias.js"></script>