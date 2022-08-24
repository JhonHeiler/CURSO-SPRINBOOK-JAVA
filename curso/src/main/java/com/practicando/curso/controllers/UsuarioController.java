package com.practicando.curso.controllers;

import com.practicando.curso.dao.UsuarioDao;
import com.practicando.curso.models.Rol;
import com.practicando.curso.models.Usuario;
import com.practicando.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios",method = RequestMethod.GET)
    public List<Usuario> getUsuario(){

        return usuarioDao.getUsuario();
    }
    @RequestMapping(value = "api/rol",method = RequestMethod.GET)
    public List<Rol> getRol(){

        return usuarioDao.getRol();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios/{id}",method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Authorization") String token, @PathVariable Long id){
        if (!validarToken(token)) { return; }
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/usuarios",method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024,1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/mostrarUsuarios/{id}",method = RequestMethod.GET)
    public Usuario mostrarUsuario( @PathVariable Long id){
       return usuarioDao.mostrarUsuario(id);
    }

    @RequestMapping(value = "api/editarUsuarios",method = RequestMethod.PUT)
    public void editUsuario(@RequestBody Usuario usuario){
        usuarioDao.modificarUsuario(usuario);
    }

}
