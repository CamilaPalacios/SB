package com.systematic.app.biblioteca.services.editorial;

import com.systematic.app.biblioteca.models.Editorial;
import java.util.List;

public interface EditorialService {
    Editorial crearEditorial(Editorial editorial);
    Editorial actualizarEditorial(Integer id, Editorial editorialActualizada);
    boolean eliminarEditorial(Integer id);
    Editorial buscarEditorialPorId(Integer id);
    List<Editorial> listarEditoriales();
}
