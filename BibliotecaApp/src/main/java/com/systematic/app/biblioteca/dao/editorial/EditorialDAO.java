package com.systematic.app.biblioteca.dao.editorial;

import com.systematic.app.biblioteca.models.Editorial;
import java.util.List;
import java.util.Optional;

public interface EditorialDAO {
    Editorial crear(Editorial editorial);
    Editorial actualizar(Editorial editorial);
    boolean eliminar(Integer id);
    Editorial buscarPorId(Integer id);
    List<Editorial> listarTodos();
    Optional<Editorial> findById(Integer id);
    int contar();


}
