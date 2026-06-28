package src.ar.edu.unlam.redsocial;

import java.util.Comparator;

public class OrdenSeguidoresDes implements Comparator<Usuario> {

	@Override
	public int compare(Usuario o1, Usuario o2) {
		// TODO Auto-generated method stub
		int resultado = Integer.compare(o2.getSeguidores().size(), o1.getSeguidores().size());
		
		if (resultado == 0) {
			resultado = o1.getUsername().compareTo(o2.getUsername());
		}
		return resultado;
	}

}
