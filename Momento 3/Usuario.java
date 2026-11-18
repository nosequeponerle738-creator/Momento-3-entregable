public abstract class Usuario {
    private String nombre;
    private String documento;
    public Usuario(String nombre, String documento) {
        this.nombre = nombre;
        this.documento = documento;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDocumento() {
        return documento;
    }
    public abstract void mostrarTipoUsuario();
}