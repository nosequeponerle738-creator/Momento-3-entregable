public interface IOperaciones {
    void guardar(Motos moto, Kilometraje kilometraje);
    Motos buscar(String modelo);
    boolean eliminar(String modelo);
}
