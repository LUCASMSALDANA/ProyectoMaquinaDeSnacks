package view;

import model.Snack;
import service.IServiceInventarioSnacks;
import service.ServiceInventarioImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Inicializamos las variables
        boolean salir = false;
        Scanner scan = new Scanner(System.in);
        IServiceInventarioSnacks inventarioSnacks = new ServiceInventarioImpl();

        List<Snack> productosAgregados = new ArrayList<>();

        //Mostramos el titulo
        System.out.println("*** Maquina de Snacks ***");
        //Llamamos a la funcion mostrarSnacks dentro de: service.ServiceInventarioImpl para mostrar el inventario disponible en la maquina
        inventarioSnacks.mostrarSnacks();

        while (!salir){
            try{
                dibujarMenu();
                //Pido al usuario seleccionar una opcion:
                int opcion = Integer.parseInt(scan.nextLine());
                //Con la opcion llamo al ejecutador de opciones
                salir = ejecutarOpcion(opcion, scan, productosAgregados, inventarioSnacks);
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error!: " + e.getMessage());
            }finally {
                System.out.println();
            }
        }

        }

    private static boolean ejecutarOpcion(int opcion, Scanner scan,
                                          List<Snack> productosAgregados, IServiceInventarioSnacks inventarioSnacks) {
        boolean salir = false;
        switch (opcion){
            case 1 -> comprarSnack(scan, productosAgregados, inventarioSnacks);
            case 2 -> mostrarTicket(productosAgregados);
            case 3 -> inventarioSnacks.mostrarSnacks();
            case 4 -> aumentarStock(scan, inventarioSnacks);
            case 5 -> agregarSnack(scan, inventarioSnacks);
            case 6 -> {
                System.out.println("Regrese pronto!");
                salir = true;
            }
            default -> System.out.println("La opción "+opcion+" es incorrecta");
        }
        return salir;
    }

    private static void aumentarStock(Scanner scan, IServiceInventarioSnacks inventarioSnacks) {
        //Pido el Id que del producto que desea Aumentar el stock
        System.out.print("Ingrese el Snack que agregar Stock (id): ");
        int idSnackAaumentar = Integer.parseInt(scan.nextLine());
        //Verifico que el mismo exista
        boolean idEncontrado=false;
        for(Snack snack : inventarioSnacks.getInventarioSnacks()){
            //Por cada elemento pregunto si el id del snack q desea agregar el usuario es el mismo que el que estoy recorriendo
            if(idSnackAaumentar == snack.getIdSnack()){
                idEncontrado=true;
                int stockActual = snack.getStockDisponible();
                System.out.print("Ingrese cuantas unidades va a agregar: ");
                int unidadesAgregadas = Integer.parseInt(scan.nextLine());
                int nuevoStock = stockActual+unidadesAgregadas;
                snack.setStockDisponible(nuevoStock);
                inventarioSnacks.actualizarStock(snack);
                System.out.println("Usted ha agregado: "+unidadesAgregadas+" unidades al IdSnack: "+idSnackAaumentar+". Nuevo stock: "+nuevoStock);
                break;
            }
        }
        if(!idEncontrado){
            System.out.println("El Id de Snack seleccionado no esta dentro del inventario: "+ idSnackAaumentar);
        }

    }


    private static void comprarSnack(Scanner scan, List<Snack> productosAgregados , IServiceInventarioSnacks inventarioSnacks) {
        //Pido el Id que desea comprar
        System.out.print("Ingrese el Snack que desea comprar (id): ");
        int idSnackAcomprar = Integer.parseInt(scan.nextLine());

        //Verifico que el mismo exista
        boolean idEncontrado = false;

        //voy a recorrer la lista de mi inventario en la maquina de snacks
        for(Snack snack : inventarioSnacks.getInventarioSnacks()){
            //Por cada elemento pregunto si el id del snack q desea agregar el usuario es el mismo que el que estoy recorriendo
            if(idSnackAcomprar == snack.getIdSnack()){
                //Si lo encuentro, cambio el valor idEncontrado y lo agrego a mi lista productosAgregados
                idEncontrado = true;
                // Luego pregunto si el stock disponible de ese Snack es mayor a 0, si es verdadero puedo agregarlo
                if(snack.getStockDisponible()>0){
                    realizarCompra(productosAgregados, snack, inventarioSnacks);
                }else{
                    System.out.println("Este producto se encuentra sin stock");
                }
                break;
            }
        }
        if (!idEncontrado){
            System.out.println("El Id de Snack seleccionado no esta dentro del inventario: "+ idSnackAcomprar);
        }
    }

    private static void realizarCompra(List<Snack> productosAgregados, Snack snack ,IServiceInventarioSnacks inventarioSnacks) {
        int stockDisponibleSnack = snack.getStockDisponible();
        int nuevoStock = stockDisponibleSnack-1;
        //Agregamos el producto a nuestra lista
        productosAgregados.add(snack);
        //mostramos un mensaje y salimos del for.
        System.out.println("Usted adquirió: " + snack.getNombre() + " con un costo de: $"+snack.getPrecio());
        //Descontamos el stock
        snack.setStockDisponible(nuevoStock);
        inventarioSnacks.actualizarStock(snack);
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

    private static void agregarSnack(Scanner scan , IServiceInventarioSnacks inventarioSnacks){
        System.out.print("Nombre del Snack: ");
        String nombre = scan.nextLine();
        System.out.print("Precio del Snack: ");
        double precio = Double.parseDouble(scan.nextLine());
        inventarioSnacks.agregarSnack(new Snack(nombre, precio));
        inventarioSnacks.mostrarSnacks();
    }

    private static void dibujarMenu() {
        System.out.print("""
                Menu:
                1. Comprar Snack
                2. Mostrar Ticket
                3. Mostrar Inventario
                4. Aumentar Stock de Snack
                5. Agregar Snack
                6. Salir
                Elige una opción:\s""");
    }
}