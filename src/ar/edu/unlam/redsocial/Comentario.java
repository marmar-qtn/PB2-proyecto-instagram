package src.ar.edu.unlam.redsocial;

import java.time.LocalDateTime;

public class Comentario {
private String texto;
private Usuario autor;
private LocalDateTime fecha;

public Comentario(String texto, Usuario autor) {
        this.texto = texto;
        this.autor = autor;
        this.fecha = LocalDateTime.now();
    }

    public String getTexto() {
        return texto;
    }

    public Usuario getAutor() {
        return autor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return autor.getUsername() + ": " + texto;
    }
    
}
