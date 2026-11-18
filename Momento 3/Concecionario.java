import java.util.ArrayList;
public class Concecionario implements IOperaciones {
    private ArrayList<Motos> motos;
    private ArrayList<Kilometraje> kilometrajes;
    public Concecionario() {
        motos = new ArrayList<>();
        kilometrajes = new ArrayList<>();
    }
    @Override
    public void guardar(Motos moto, Kilometraje kilometraje) {
        motos.add(moto);
        kilometrajes.add(kilometraje);
        System.out.println("Moto guardada correctamente.");
    }
    public void agregarMoto(Motos moto, Kilometraje kilometraje) {
        guardar(moto, kilometraje);
    }
    @Override
    public Motos buscar(String modelo) {
        for (Motos moto : motos) {
            if (moto.getModelo().equalsIgnoreCase(modelo)) {
                return moto;
            }
        }
        return null;
    }
    public int buscarIndice(String modelo) {
        for (int i = 0; i < motos.size(); i++) {
            if (motos.get(i).getModelo().equalsIgnoreCase(modelo)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public boolean eliminar(String modelo) {
        int indice = buscarIndice(modelo);
        if (indice == -1) {
            return false;
        }
        motos.remove(indice);
        kilometrajes.remove(indice);
        return true;
    }
    public boolean actualizar(String modelo, String marca, String nuevoModelo,
                              String cilindrada, String color, int km,
                              int anos, double valor) {
        int indice = buscarIndice(modelo);
        if (indice == -1) {
            return false;
        }
        Motos moto = motos.get(indice);
        moto.setMarca(marca);
        moto.setModelo(nuevoModelo);
        moto.setCilindrada(cilindrada);
        moto.setColor(color);
        Kilometraje datos = kilometrajes.get(indice);
        datos.setKilometraje(km);
        datos.setAnosUso(anos);
        datos.setValor(valor);
        return true;
    }
    public void mostrarMotos() {
        if (motos.isEmpty()) {
            System.out.println("No hay motos registradas.");
            return;
        }
        for (int i = 0; i < motos.size(); i++) {
            System.out.println("\\n--- MOTO " + (i + 1) + " ---");
            motos.get(i).mostrarInformacion();
            kilometrajes.get(i).mostrarInformacion();
        }
    }
    public void mostrarMoto(String modelo) {
        int indice = buscarIndice(modelo);
        if (indice == -1) {
            System.out.println("No se encontró una moto con ese modelo.");
            return;
        }
        motos.get(indice).mostrarInformacion();
        kilometrajes.get(indice).mostrarInformacion();
    }
    public double calcularPromedioKilometraje() {
        if (kilometrajes.isEmpty()) return 0;
        int suma = 0;
        for (Kilometraje k : kilometrajes) suma += k.getKilometraje();
        return (double) suma / kilometrajes.size();
    }
    public double calcularPromedioAnosUso() {
        if (kilometrajes.isEmpty()) return 0;
        int suma = 0;
        for (Kilometraje k : kilometrajes) suma += k.getAnosUso();
        return (double) suma / kilometrajes.size();
    }
    public void mostrarPromedios() {
        System.out.println("--- PROMEDIOS ---");
        System.out.println("Promedio de kilometraje: " + calcularPromedioKilometraje() + " km");
        System.out.println("Promedio de años de uso: " + calcularPromedioAnosUso());
    }
    public void buscarMotosMuchoKilometraje(int kilometrajeMinimo) {
        boolean encontrada = false;
        System.out.println("--- MOTOS CON MUCHO KILOMETRAJE ---");
        for (int i = 0; i < motos.size(); i++) {
            if (kilometrajes.get(i).getKilometraje() >= kilometrajeMinimo) {
                System.out.println(motos.get(i).getMarca() + " " + motos.get(i).getModelo()
                        + " - " + kilometrajes.get(i).getKilometraje() + " km");
                encontrada = true;
            }
        }
        if (encontrada) System.out.println("No se encontraron motos con ese kilometraje.");
    }
    public void buscarMotosConMuchosAnos(int anosMinimos) {
        boolean encontrada = false;
        System.out.println("--- MOTOS CON MUCHOS AÑOS DE USO ---");
        for (int i = 0; i < motos.size(); i++) {
            if (kilometrajes.get(i).getAnosUso() >= anosMinimos) {
                System.out.println(motos.get(i).getMarca() + " " + motos.get(i).getModelo()
                        + " - " + kilometrajes.get(i).getAnosUso() + " años");
                encontrada = true;
            }
        }
        if (encontrada) System.out.println("No se encontraron motos con esos años de uso.");
    }
}
