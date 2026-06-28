
package src.ar.edu.unlam.redsocial;

import java.util.HashSet;
import java.util.Set;

import src.ar.edu.unlam.redsocial.Exceptions.NoSePuedeSeguirseAUnoMismoException;
import src.ar.edu.unlam.redsocial.Exceptions.YaSiguiendoException;

public abstract class Usuario {

    private String username;
    private String biografia;

    private Set<Usuario> seguidos;
    private Set<Usuario> seguidores;

    public Usuario(String username){
        this.username = username;
        this.biografia = "";
        this.seguidos = new HashSet<>();
        this.seguidores = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Set<Usuario> getSeguidos() {
        return seguidos;
    }

    public Set<Usuario> getSeguidores() {
        return seguidores;
    }

    public void seguir(Usuario otro) throws NoSePuedeSeguirseAUnoMismoException, YaSiguiendoException{
        if(this.equals(otro)){
            throw new NoSePuedeSeguirseAUnoMismoException("No puedes seguirte a ti mismo");
        }
        if(this.seguidos.contains(otro)){
            throw new YaSiguiendoException("Ya sigues al usuario @"+ otro.getUsername());
        }
        this.seguidos.add(otro);
        otro.agregarSeguidor(this);
    }

    public void agregarSeguidor(Usuario seguidor){
        this.seguidores.add(seguidor);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.toLowerCase().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        
        if (!(obj instanceof Usuario))
            return false;
        
        Usuario other = (Usuario) obj; 
        
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equalsIgnoreCase(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "@" + username;
    }
    
}
