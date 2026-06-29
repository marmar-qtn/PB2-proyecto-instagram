package src.ar.edu.unlam.redsocial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import src.ar.edu.unlam.redsocial.Exceptions.CuentaInactivaException;
import src.ar.edu.unlam.redsocial.Exceptions.DuracionInvalidaException;
import src.ar.edu.unlam.redsocial.Exceptions.UsuarioNoEncontradoException;
import src.ar.edu.unlam.redsocial.Exceptions.UsuarioYaExisteException;

public class RedSocial {
	private String nombre;
	private Map<String, Usuario> usuarios;
	private List<Publicacion> feed;

	public RedSocial(String nombre) {
		this.nombre = nombre;
		this.usuarios = new HashMap<>();
		this.feed = new ArrayList<>();
	}

	public void registrarUsuario(String username) throws UsuarioYaExisteException {
		if (this.usuarios.containsKey(username)) {
			throw new UsuarioYaExisteException("El nombre de usuario @" + username + " ya está registrado");
		}
		Usuario nuevoUsuario = new UsuarioNormal(username);
		this.usuarios.put(username, nuevoUsuario);
	}

	public Usuario obtenerUsuario(String username) throws UsuarioNoEncontradoException {
		if (!this.usuarios.containsKey(username)) {
			throw new UsuarioNoEncontradoException("No se encontró al usuario @" + username);
		}
		return this.usuarios.get(username);
	}

	public List<Publicacion> obtenerFeed(Usuario usuario) {
		List<Publicacion> feedFiltrado = new ArrayList<>();

		for (Publicacion publicacion : this.feed) {
			if (usuario.getSeguidos().contains(publicacion.getAutor())) {
				feedFiltrado.add(publicacion);
			}
		}
		return feedFiltrado;
	}

//	public void publicar(Publicacion publicacion) {
//		this.feed.add(publicacion);
//	}

	public void publicar(Publicacion publicacion) throws CuentaInactivaException {
		if (publicacion instanceof Video) {
			Video video = (Video) publicacion;
			if (video.getDuracionSegundos() <= 0) {
				throw new DuracionInvalidaException("La duración del video no puede ser negativa o cero");
			}
		}
		
		if(publicacion.getAutor().getEstado() != EstadoCuenta.ACTIVA) {
			throw new CuentaInactivaException("La cuenta no esta activa y no puede realizar acciones en este momento");
		}
		this.feed.add(publicacion);
	}

	public List<Publicacion> getFeed() {
		return this.feed;
	}

	public TreeSet<Publicacion> obtenerPublicacionesOrdenadasPorCantidadDeLikes() {

		OrdenPublicacionesPorLikesDes orden = new OrdenPublicacionesPorLikesDes();

		TreeSet<Publicacion> ordenadosPorLikes = new TreeSet<Publicacion>(orden);
		ordenadosPorLikes.addAll(this.feed);

		return ordenadosPorLikes;
	}

	public TreeSet<Usuario> obtenerSeguidoresPorOrdenDescendente() {

		OrdenSeguidoresDes orden = new OrdenSeguidoresDes();

		TreeSet<Usuario> ordenadosDes = new TreeSet<Usuario>(orden);
		ordenadosDes.addAll(this.usuarios.values());
		return ordenadosDes;
	}
}
