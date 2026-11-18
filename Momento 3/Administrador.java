public class Administrador extends Usuario {
    public Administrador(String nombre, String documento) {
        super(nombre, documento);
    }
    @Override
    public void mostrarTipoUsuario() {
        System.out.println("Bienvenido administrador: " + getNombre());
    }
}
