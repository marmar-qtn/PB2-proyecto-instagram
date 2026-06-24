package src.ar.edu.unlam.redsocial;

public class Foto extends Publicacion{
    private String filtro;
    private String resolucion;

    public Foto(Usuario autor, String descripcion, String filtro, String resolucion){
        super(autor, descripcion);
        this.filtro = filtro;
        this.resolucion = resolucion;
    }

    public String getFiltro() {
        return filtro;
    }

    public String getResolucion() {
        return resolucion;
    }

    @Override
    public String getDetalleTipo() {
        return "Foto [Filtro: " + filtro + "| Resolución: " + resolucion + "]";
    }
}
