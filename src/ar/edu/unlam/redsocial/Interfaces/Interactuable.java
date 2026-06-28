package src.ar.edu.unlam.redsocial.Interfaces;

import src.ar.edu.unlam.redsocial.Comentario;
import src.ar.edu.unlam.redsocial.Usuario;

public interface Interactuable {
    void darLike (Usuario usuario);
    void quitarLike (Usuario usuario);
    void agregarComentario(Comentario comentario);
}
