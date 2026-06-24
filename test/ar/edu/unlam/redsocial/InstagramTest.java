package test.ar.edu.unlam.redsocial;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
//import src.ar.edu.unlam.redsocial.Carrusel;
import src.ar.edu.unlam.redsocial.Exceptions.*;
import src.ar.edu.unlam.redsocial.*;

public class InstagramTest {
    
    @Test
    public void testRegistroUsuarioExitoso() throws UsuarioNoEncontradoException, UsuarioYaExisteException {
        RedSocial app = new RedSocial("Instagram");
        app.registrarUsuario("marmar.qtn");
        Usuario usuarioEncontrado = app.obtenerUsuario("marmar.qtn");
        
        assertNotNull("El usuario debería existir en el sistema y no ser null", usuarioEncontrado);
        assertEquals("El username recuperado debería ser @marmar.qtn", "marmar.qtn", usuarioEncontrado.getUsername());
    }

    @Test(expected = UsuarioYaExisteException.class)
    public void testFallaRegistroUsuarioDuplicado() throws UsuarioYaExisteException {
        RedSocial app = new RedSocial("Instagram");

        app.registrarUsuario("marmar.qtn");

        // Intentamos registrar exactamente el mismo usuario (debe lanzar UsuarioYaExisteException)
        app.registrarUsuario("marmar.qtn"); 
    }

    @Test(expected = NoSePuedeSeguirseAUnoMismoException.class)
    public void testEvitarSeguirseASiMismo() throws UsuarioNoEncontradoException, NoSePuedeSeguirseAUnoMismoException, UsuarioYaExisteException, YaSiguiendoException {
        RedSocial app = new RedSocial("Instagram");
        app.registrarUsuario("oriwiis");
        Usuario ori = app.obtenerUsuario("oriwiis");
        ori.seguir(ori);
    }

    @Test(expected = YaSiguiendoException.class)
    public void testFallaAlSeguirDosVecesAlMismoUsuario() throws UsuarioNoEncontradoException, NoSePuedeSeguirseAUnoMismoException, YaSiguiendoException, UsuarioYaExisteException {
        RedSocial app = new RedSocial("Instagram");
        app.registrarUsuario("oriwiis");
        app.registrarUsuario("marmar.qtn");
        Usuario ori = app.obtenerUsuario("oriwiis");
        Usuario maca = app.obtenerUsuario("marmar.qtn");

        ori.seguir(maca);
        ori.seguir(maca);
    }

    @Test
    public void testUnicidadDeLikesEnPublicacion() throws UsuarioNoEncontradoException, UsuarioYaExisteException {

        RedSocial app = new RedSocial("Instagram");
        
        app.registrarUsuario("marmar.qtn");
        app.registrarUsuario("oriwiis");
        Usuario maca = app.obtenerUsuario("marmar.qtn");
        Usuario ori = app.obtenerUsuario("oriwiis");
        
        Publicacion foto = new Foto(maca, "Foto de Haru durmiendo", "Aro de Luz", "2992x2992");
        
        foto.darLike(maca);
        foto.darLike(maca);
        foto.darLike(ori);
        
        assertEquals("La publicación debe tener exactamente 2 likes", 2, foto.getCantidadLikes());
    }

    @Test
    public void testObtenerFeedSoloContienePosteosDeUsuariosSeguidos() throws UsuarioNoEncontradoException, NoSePuedeSeguirseAUnoMismoException, YaSiguiendoException, UsuarioYaExisteException {
        
        RedSocial app = new RedSocial("Instagram");
        
        app.registrarUsuario("marmar.qtn");
        app.registrarUsuario("oriwiis");
        app.registrarUsuario("usuario1");
        
        Usuario maca = app.obtenerUsuario("marmar.qtn");
        Usuario ori = app.obtenerUsuario("oriwiis");
        Usuario desconocido = app.obtenerUsuario("usuario1");
        
        
        maca.seguir(ori); // 'maca' sigue a 'ori' pero NO a 'usuario1'
        
        Publicacion postOri = new Foto(ori, "Lindo día!", "Vintage", "2992x2992");
        Publicacion postDesconocido = new Video(desconocido, "Mi nuevo setup", 15, "4k");
        
        app.publicar(postOri);
        app.publicar(postDesconocido);

        List<Publicacion> feedDeMaca = app.obtenerFeed(maca);
        
        assertEquals("El feed de maca debe de tener 1 sola publicacion", 1, feedDeMaca.size());
        assertTrue("La unica publicacion en el feed deberia ser de ori", feedDeMaca.get(0).getAutor()==ori);
    }

    // @Test
    // public void testCrearCarruselConCantidadDeFotosValida() throws UsuarioNoEncontradoException, UsuarioYaExisteException {
    //     RedSocial red = new RedSocial();
    //     red.registrarUsuario("creator");
    //     Usuario creator = red.obtenerUsuario("creator");

    //     // Creamos un carrusel de 5 fotos (rango válido: 1 a 20)
    //     Carrusel miCarrusel = new Carrusel(creator, "Mi carrusel de vacaciones", 5);

    //     assertNotNull("El carrusel debería haberse creado", miCarrusel);
    //     assertEquals("La lista interna de fotos debe contener 5 elementos", 5, miCarrusel.getFotos().size());
    //     assertEquals("El detalle tipo debe mostrar las 5 fotos", "Carrusel [Fotos cargadas: 5/20]", miCarrusel.getDetalleTipo());
    // }

    // @Test(expected = IllegalArgumentException.class)
    // public void testFallaAlCrearCarruselConMasDeVeinteFotos() throws UsuarioNoEncontradoException, UsuarioYaExisteException {
    //     RedSocial red = new RedSocial();
    //     red.registrarUsuario("creator");
    //     Usuario creator = red.obtenerUsuario("creator");

    //     // Intentamos crear un carrusel con 25 fotos (debe lanzar IllegalArgumentException)
    //     new Carrusel(creator, "Fotos de mi perro", 25);
    // }
}
