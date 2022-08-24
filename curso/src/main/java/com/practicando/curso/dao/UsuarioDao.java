package com.practicando.curso.dao;
import com.practicando.curso.models.Rol;
import com.practicando.curso.models.Usuario;

import java.util.List;
public interface UsuarioDao {
    List<Usuario> getUsuario();
    List<Rol> getRol();
    void eliminar(Long id);
    void registrar(Usuario usuario);
    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
    Usuario mostrarUsuario(Long id);
    void modificarUsuario(Usuario usuario);
}
