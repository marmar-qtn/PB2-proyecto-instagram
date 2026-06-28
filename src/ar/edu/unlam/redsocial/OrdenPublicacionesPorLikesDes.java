package src.ar.edu.unlam.redsocial;

import java.util.Comparator;

public class OrdenPublicacionesPorLikesDes implements Comparator<Publicacion>{

	@Override
	public int compare(Publicacion o1, Publicacion o2) {
		// TODO Auto-generated method stub
		int resultado = Integer.compare(o2.getCantidadLikes(), o1.getCantidadLikes());
		
		if (resultado == 0) {
            resultado = o1.getDescripcion().compareTo(o2.getDescripcion());
        }
		return resultado;
	}

}
