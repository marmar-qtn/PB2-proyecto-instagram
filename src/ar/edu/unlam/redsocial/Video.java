package src.ar.edu.unlam.redsocial;

import src.ar.edu.unlam.redsocial.Interfaces.Reproducible;

public class Video extends Publicacion implements Reproducible{
    private int duracionSegundos;
    private String resolucion;

    public Video (Usuario autor, String descripcion, int duracionSegundos, String resolucion){
        super(autor, descripcion);
        this.duracionSegundos = duracionSegundos;
        this.resolucion = resolucion;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public String getResolucion() {
        return resolucion;
    }

    @Override
    public String getDetalleTipo() {
        return "Video [Duración: " + duracionSegundos + "s | Resolución: " + resolucion + "]";
    }

    @Override
    public String reproducir() {
        return "▶️ Reproduciendo video de " + duracionSegundos + " segundos de @" + getAutor().getUsername();
    }
    
}
