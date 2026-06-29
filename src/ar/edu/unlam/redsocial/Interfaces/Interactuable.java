package src.ar.edu.unlam.redsocial.Interfaces;

import src.ar.edu.unlam.redsocial.Comentario;
import src.ar.edu.unlam.redsocial.Usuario;
import src.ar.edu.unlam.redsocial.Exceptions.CuentaPrivadaException;

public interface Interactuable {
    void darLike (Usuario usuario) throws CuentaPrivadaException;
    void quitarLike (Usuario usuario);
    void agregarComentario(Comentario comentario);
}
