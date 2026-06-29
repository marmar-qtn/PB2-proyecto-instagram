package src.ar.edu.unlam.redsocial;

import java.time.LocalDateTime;

import src.ar.edu.unlam.redsocial.Exceptions.HistoriaExpiradaException;
import src.ar.edu.unlam.redsocial.Interfaces.Reproducible;

public class Historia extends Publicacion implements Reproducible{

    private LocalDateTime fechaExpiracion;

    public Historia(Usuario autor, String descripcion){
        super(autor, descripcion);
        this.fechaExpiracion = getFecha().plusHours(24);
    }

    public Boolean estaExpirada(){
        return LocalDateTime.now().isAfter(fechaExpiracion);
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }
    
    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public String getDetalleTipo() {
       return "Historia [Expiración: " + fechaExpiracion + "]";
    }

    @Override
    public String reproducir() {
    	if(estaExpirada()) {
    		throw new HistoriaExpiradaException("La historia ya expiró y no puede reproducirse");
    	}
         return "Mostrando historia de @" + getAutor().getUsername() + "...";
    }
    
}
