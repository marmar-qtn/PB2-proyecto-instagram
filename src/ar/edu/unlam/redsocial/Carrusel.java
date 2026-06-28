package src.ar.edu.unlam.redsocial;

import java.util.ArrayList;
import java.util.List;

public class Carrusel extends Publicacion{

    private List<String> fotos;

    public Carrusel(Usuario autor, String descripcion, int cantidadDeFotos){
        super(autor, descripcion);
        if(cantidadDeFotos<=0 || cantidadDeFotos>20){
            throw new IllegalArgumentException("Un carrusel debe contener entre 1 y 20 fotos");
        }
        this.fotos = new ArrayList<>();
        for (int i = 1; i <= cantidadDeFotos; i++) {
            this.fotos.add("slide_foto_" + i + ".png");
        }
    }
    public Carrusel(Usuario autor, String descripcion, List<String> listaFotos) {
        super(autor, descripcion);
        
        if (listaFotos == null || listaFotos.isEmpty() || listaFotos.size() > 20) {
            throw new IllegalArgumentException("El carrusel debe contener entre 1 y 20 fotos.");
        }
        
        this.fotos = new ArrayList<>(listaFotos);
    }
    public List<String> getFotos() {
        return fotos;
    }

    @Override
    public String getDetalleTipo() {
        return "Carrusel [Fotos cargadas: " + fotos.size() + "/20]";
    }
}
