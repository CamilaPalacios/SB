<!-- modalEditarCategoria.jsp -->
<div class="modal fade" id="modalEditarCategoria" tabindex="-1" role="dialog" aria-labelledby="editarCategoriaLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="formEditarCategoria" action="${pageContext.request.contextPath}/categorias" method="POST">
                <input type="hidden" name="accion" value="editar">
                <div class="modal-header">
                    <h5 class="modal-title" id="editarCategoriaLabel">Editar Categoría</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="editIdCategoria" name="idCategoria">
                    <div class="form-group">
                        <label for="editNombreCategoria">Nombre de la Categoría</label>
                        <input type="text" class="form-control" id="editNombreCategoria" name="nombreCategoria" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</div>
