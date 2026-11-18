import jakarta.persistence.*;

@Entity
@Table(name = "kilometrajes")
public class Kilometraje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column(nullable = false)
    private int kilometraje;
    
    @Column(name = "anos_uso", nullable = false)
    private int anosUso;
    
    @Column(nullable = false)
    private double valor;
   
    @OneToOne
  
    @JoinColumn(name = "moto_modelo", referencedColumnName = "modelo")
    private Motos moto;

    public Kilometraje() {}

    public Kilometraje(int kilometraje, int anosUso, double valor) {
        this.kilometraje = kilometraje;
        this.anosUso = anosUso;
        this.valor = valor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getKilometraje() { return kilometraje; }
    public void setKilometraje(int kilometraje) { this.kilometraje = kilometraje; }

    public int getAnosUso() { return anosUso; }
    public void setAnosUso(int anosUso) { this.anosUso = anosUso; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public Motos getMoto() { return moto; }
    public void setMoto(Motos moto) { this.moto = moto; }

    public boolean esSegundaMano() {
        return kilometraje > 0 || anosUso > 0;
    }

    public void mostrarInformacion() {
        System.out.println("Kilometraje: " + kilometraje + " km");
        System.out.println("Años de uso: " + anosUso);
        System.out.println("Valor: $" + valor);
        System.out.println("Estado: " + (esSegundaMano() ? "Segunda mano" : "Nueva"));
    }
}