import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Inicializamos las variables
        boolean salir = false;
        Scanner scan = new Scanner(System.in);
        List<Snack> productosAgregados = new ArrayList<>();

        //Mostramos el titulo
        System.out.println("*** Maquina de Snacks ***");
        //Llamamos a la funcion mostrarSnacks dentro de: InventarioSnacksEnMaquina para mostrar el inventario disponible en la maquina
        InventarioSnacksEnMaquina.mostrarSnacks();

        while (!salir){
            try{
                dibujarMenu();
                //Pido al usuario seleccionar una opcion:
                int opcion = Integer.parseInt(scan.nextLine());
                //Con la opcion llamo al ejecutador de opciones
                salir = ejecutarOpcion(opcion, scan, productosAgregados);
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error!" + e.getMessage());
            }finally {
                System.out.println();
            }
        }

        }

    private static boolean ejecutarOpcion(int opcion, Scanner scan,
                                          List productosAgregados) {
        boolean salir = false;
        switch (opcion){
            case 1 -> comprarSnack(scan, productosAgregados);
            case 2 -> mostrarTicket(productosAgregados);
            case 3 -> agregarSnack(scan);
            case 4 -> InventarioSnacksEnMaquina.mostrarSnacks();
            case 5 -> {
                System.out.println("Regrese pronto!");
                salir = true;
            }
            default -> System.out.println("La opción "+opcion+" es incorrecta");
        }
        return salir;
    }


    private static void comprarSnack(Scanner scan, List<Snack> productosAgregados) {
        //Pido el Id que desea comprar
        System.out.print("Ingrese el Snack que desea comprar (id): ");
        int idSnackAcomprar = Integer.parseInt(scan.nextLine());
        //Verifico que el mismo exista
        boolean idEncontrado = false;

        //voy a recorrer la lista de mi inventario en la maquina de snacks
        for(Snack snack : InventarioSnacksEnMaquina.inventarioSnacks){
            //Por cada elemento pregunto si el id del snack q desea agregar el usuario es el mismo que el que estoy recorriendo
            if(idSnackAcomprar == snack.getIdSnack()){
                //Si lo encuentro, cambio el valor idEncontrado y lo agrego a mi lista productosAgregados
                idEncontrado = true;
                productosAgregados.add(snack);
                //mostramos un mensaje y salimos del for.
                System.out.println("El Snack agregado es: " + snack);
                break;
            }
        }
        if (!idEncontrado){
            System.out.println("El Id de Snack seleccionado no esta dentro del inventario: "+ idSnackAcomprar);
        }
    }

    private static void mostrarTicket(List<Snack> productosAgregados) {
        String ticket = "";
        double total = 0.0;
        for(Snack snack : productosAgregados){
            ticket+= "\t"+ snack.getNombre() + " - $"+ snack.getPrecio()+"\n";
            total+= snack.getPrecio();
        }
        System.out.println("\n"+ticket);
        System.out.println("\t"+"Total ticket: $"+total);
    }

    private static void agregarSnack(Scanner scan){
        System.out.print("Nombre del Snack: ");
        String nombre = scan.nextLine();
        System.out.print("Precio del Snack: ");
        double precio = Double.parseDouble(scan.nextLine());
        InventarioSnacksEnMaquina.agregarSnack(new Snack(nombre, precio));
        InventarioSnacksEnMaquina.mostrarSnacks();
    }

    private static void dibujarMenu() {
        System.out.print("""
                Menu:
                1. Comprar Snack
                2. Mostrar Ticket
                3. Agregar Snack
                4. Mostrar Inventario
                5. Salir
                Elige una opción:\s""");
    }
}