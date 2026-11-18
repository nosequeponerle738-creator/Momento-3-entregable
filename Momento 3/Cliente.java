public class Cliente extends Usuario {
    public Cliente(String nombre, String documento) {
        super(nombre, documento);
    }

    @Override
    public void mostrarTipoUsuario() {
        System.out.println("Bienvenido cliente: " + getNombre());
    }
}
