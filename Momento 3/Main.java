import java.util.Scanner;
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Concecionario concecionario = new Concecionario();
        cargarMotosIniciales(concecionario);
        System.out.println("=== CONCESIONARIO DE MOTOS ===");
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su documento: ");
        String documento = scanner.nextLine();
        System.out.println("Seleccione el tipo de usuario:");
        System.out.println("1. Administrador");
        System.out.println("2. Cliente");
        int tipo = leerEntero("Opción: ");
        Usuario usuario;
        if (tipo == 1) {
            usuario = new Administrador(nombre, documento);
        } else {
            usuario = new Cliente(nombre, documento);
        }
        usuario.mostrarTipoUsuario();
        boolean esAdministrador = usuario instanceof Administrador;
        int opcion;
        do {
            System.out.println("\\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Listar motos");
            System.out.println("2. Buscar moto");
            if (esAdministrador) {
                System.out.println("3. Registrar moto");
                System.out.println("4. Actualizar moto");
                System.out.println("5. Eliminar moto");
                System.out.println("6. Mostrar promedios");
                System.out.println("7. Buscar por kilometraje mínimo");
                System.out.println("8. Buscar por años de uso mínimos");
            }
            System.out.println("0. Salir");
            opcion = leerEntero("Seleccione una opción: ");
            if (!esAdministrador && opcion != 0 && opcion != 1 && opcion != 2) {
                System.out.println("Los clientes solo pueden listar y buscar motos.");
                continue;
            }
            switch (opcion) {
                case 1:
                    concecionario.mostrarMotos();
                    break;
                case 2:
                    System.out.print("Modelo que desea buscar: ");
                    concecionario.mostrarMoto(scanner.nextLine());
                    break;
                case 3:
                    if (esAdministrador) registrarMoto(concecionario);
                    else opcionInvalida();
                    break;
                case 4:
                    if (esAdministrador) actualizarMoto(concecionario);
                    else opcionInvalida();
                    break;
                case 5:
                    if (esAdministrador) eliminarMoto(concecionario);
                    else opcionInvalida();
                    break;
                case 6:
                    if (esAdministrador) concecionario.mostrarPromedios();
                    else opcionInvalida();
                    break;
                case 7:
                    if (esAdministrador) {
                        concecionario.buscarMotosMuchoKilometraje(leerEntero("Kilometraje mínimo: "));
                    } else opcionInvalida();
                    break;
                case 8:
                    if (esAdministrador) {
                        concecionario.buscarMotosConMuchosAnos(leerEntero("Años mínimos de uso: "));
                    } else opcionInvalida();
                    break;
                case 0:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        scanner.close();
    }
    private static void cargarMotosIniciales(Concecionario c) {
        c.guardar(new Motos("Yamaha", "MT-03", "321 cc", "Azul"),
                new Kilometraje(15000, 2, 18000000));
        c.guardar(new Motos("Honda", "CB 190R", "184 cc", "Rojo"),
                new Kilometraje(500, 0, 14000000));
        c.guardar(new Motos("Suzuki", "GSX 150", "147 cc", "Negro"),
                new Kilometraje(35000, 5, 9000000));
    }
    private static void registrarMoto(Concecionario c) {
        System.out.println("--- REGISTRAR MOTO ---");
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        if (c.buscar(modelo) != null) {
            System.out.println("Ya existe una moto registrada con ese modelo.");
            return;
        }
        System.out.print("Cilindrada: ");
        String cilindrada = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        int km = leerEntero("Kilometraje: ");
        int anos = leerEntero("Años de uso: ");
        double valor = leerDouble("Valor: $");
        c.guardar(new Motos(marca, modelo, cilindrada, color),
                new Kilometraje(km, anos, valor));
    }
    private static void actualizarMoto(Concecionario c) {
        System.out.print("Modelo actual de la moto: ");
        String modeloActual = scanner.nextLine();
        if (c.buscar(modeloActual) == null) {
            System.out.println("No se encontró esa moto.");
            return;
        }
        System.out.print("Nueva marca: ");
        String marca = scanner.nextLine();
        System.out.print("Nuevo modelo: ");
        String nuevoModelo = scanner.nextLine();
        System.out.print("Nueva cilindrada: ");
        String cilindrada = scanner.nextLine();
        System.out.print("Nuevo color: ");
        String color = scanner.nextLine();
        int km = leerEntero("Nuevo kilometraje: ");
        int anos = leerEntero("Nuevos años de uso: ");
        double valor = leerDouble("Nuevo valor: $");
        if (c.actualizar(modeloActual, marca, nuevoModelo, cilindrada, color, km, anos, valor)) {
            System.out.println("Moto actualizada correctamente.");
        } else {
            System.out.println("No fue posible actualizar la moto.");
        }
    }
    private static void eliminarMoto(Concecionario c) {
        System.out.print("Modelo de la moto que desea eliminar: ");
        String modelo = scanner.nextLine();
        if (c.eliminar(modelo)) {
            System.out.println("Moto eliminada correctamente.");
        } else {
            System.out.println("No se encontró esa moto.");
        }
    }
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor < 0) {
                    System.out.println("Ingrese un número igual o mayor que cero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
            }
        }
    }
    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
                if (valor < 0) {
                    System.out.println("Ingrese un valor igual o mayor que cero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número válido.");
            }
        }
    }
    private static void opcionInvalida() {
        System.out.println("Opción no disponible para este tipo de usuario.");
    }
}
