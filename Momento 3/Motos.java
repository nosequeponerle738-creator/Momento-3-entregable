import jakarta.persistence.*;

@Entity
@Table(name = "motos")
public class Motos {
    @Id
    @Column(name = "modelo", unique = true, nullable = false)
    private String modelo; 
    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String cilindrada;

    @Column(nullable = false)
    private String color;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "moto", fetch = FetchType.LAZY)
    private Kilometraje kilometraje;

    public Motos() {}
    public Motos(String marca, String modelo, String cilindrada, String color) {
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
        this.color = color;
    }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
   
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
   
    public String getCilindrada() { return cilindrada; }
    public void setCilindrada(String cilindrada) { this.cilindrada = cilindrada; }
   
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
   
    public Kilometraje getKilometraje() { return kilometraje; }
    public void setKilometraje(Kilometraje kilometraje) {
        this.kilometraje = kilometraje;
        if (kilometraje != null) {
            kilometraje.setMoto(this);
        }
    }
    public void mostrarInformacion() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Cilindrada: " + cilindrada);
        System.out.println("Color: " + color);
    }
}
