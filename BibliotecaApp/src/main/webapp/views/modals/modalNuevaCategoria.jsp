<!-- Modal Nueva Categoría -->
<div class="modal fade" id="modalNuevaCategoria" tabindex="-1" role="dialog" aria-labelledby="modalNuevaCategoriaLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header bg-primary">
                <h5 class="modal-title" id="modalNuevaCategoriaLabel">Registrar Nueva Categoría</h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            
            <form id="formNuevaCategoria">
                <input type="hidden" name="accion" value="registrar">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombreCategoria">Nombre de Categoría</label>
                        <input type="text" class="form-control" id="txtNombreCategoria" name="txtNombreCategoria" required>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>

        </div>
    </div>
</div>
