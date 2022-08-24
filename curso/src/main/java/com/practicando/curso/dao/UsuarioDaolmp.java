package com.practicando.curso.dao;

import com.practicando.curso.models.Rol;
import com.practicando.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaolmp implements UsuarioDao{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public  List<Usuario> getUsuario(){
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Rol> getRol() {
        String query = "FROM Rol";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id){
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return lista.get(0);
        }
        return null;
    }


    @Override
    public Usuario mostrarUsuario(Long id){
        Usuario usuario = entityManager.find(Usuario.class, id);
        return usuario;
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        String query = "UPDATE Usuario SET nombre = :nombre, apellido = :apellido, email = :email, telefono = :telefono WHERE id = :id";
        entityManager.createQuery(query)
                .setParameter("nombre", usuario.getNombre())
                .setParameter("apellido", usuario.getApellido())
                .setParameter("email", usuario.getEmail())
                .setParameter("telefono", usuario.getTelefono())
                .setParameter("id", usuario.getId())

                .executeUpdate();
    }


}
