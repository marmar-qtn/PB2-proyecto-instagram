package src.ar.edu.unlam.redsocial;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import src.ar.edu.unlam.redsocial.Interfaces.Interactuable;

public abstract class Publicacion implements Interactuable, Comparable<Publicacion> {
	private Usuario autor;
	private String descripcion;
	private LocalDateTime fecha;

	private Set<Usuario> likes;
	private List<Comentario> comentarios;

	public Publicacion(Usuario autor, String descripcion) {
		this.autor = autor;
		this.descripcion = descripcion;
		this.fecha = LocalDateTime.now();
		this.likes = new HashSet<>();
		this.comentarios = new ArrayList<>();
	}

	public abstract String getDetalleTipo();

	@Override
	public void agregarComentario(Comentario comentario) {
		this.comentarios.add(comentario);
	}

	@Override
	public void darLike(Usuario usuario) {
		this.likes.add(usuario);
	}

	@Override
	public void quitarLike(Usuario usuario) {
		this.likes.remove(usuario);
	}

	public Usuario getAutor() {
		return autor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public int getCantidadLikes() {
		return this.likes.size();
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	@Override
	public int compareTo(Publicacion otra) {

		int resultado = Integer.compare(otra.getCantidadLikes(), this.getCantidadLikes());

		if (resultado == 0) {
			resultado = this.getDescripcion().compareTo(otra.getDescripcion());
		}
		return resultado;

	}

}
