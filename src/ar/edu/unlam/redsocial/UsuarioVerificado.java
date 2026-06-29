package src.ar.edu.unlam.redsocial;

public class UsuarioVerificado extends Usuario {
	private String insignia;

	public UsuarioVerificado(String username) {
		super(username);
		this.insignia = "★";
	}

	public String getInsignia() {
		return insignia;
	}

	public void setInsignia(String insignia) {
		this.insignia = insignia;
	}

}
