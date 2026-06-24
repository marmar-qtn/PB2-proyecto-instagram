package src.ar.edu.unlam.redsocial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.ar.edu.unlam.redsocial.Exceptions.UsuarioNoEncontradoException;
import src.ar.edu.unlam.redsocial.Exceptions.UsuarioYaExisteException;

public class RedSocial {
    private String nombre;
    private Map<String, Usuario> usuarios;
    private List<Publicacion> feed;

    public RedSocial(String nombre){
        this.nombre = nombre;
        this.usuarios = new HashMap<>();
        this.feed = new ArrayList<>();
    }

    public void registrarUsuario(String username) throws UsuarioYaExisteException {
        if(this.usuarios.containsKey(username)){
            throw new UsuarioYaExisteException("El nombre de usuario @"+ username + " ya está registrado");
        }
        Usuario nuevoUsuario = new UsuarioNormal(username);
        this.usuarios.put(username, nuevoUsuario);
    }

    public Usuario obtenerUsuario(String username) throws UsuarioNoEncontradoException{
        if(!this.usuarios.containsKey(username)){
            throw new UsuarioNoEncontradoException("No se encontró al usuario @" + username);
        }
        return this.usuarios.get(username);
    }

    public List<Publicacion> obtenerFeed(Usuario usuario){
        List<Publicacion> feedFiltrado = new ArrayList<>();

        for (Publicacion publicacion : this.feed) {
           if(usuario.getSeguidos().contains(publicacion.getAutor())){
            feedFiltrado.add(publicacion);
           }
        }
        return feedFiltrado;
    }


    public void publicar(Publicacion publicacion) {
        this.feed.add(publicacion);
    }

    public List<Publicacion> getFeed() {
        return this.feed;
    }
}
