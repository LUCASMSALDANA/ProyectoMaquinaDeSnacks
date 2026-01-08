package service;

import model.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServiceInventarioImpl implements IServiceInventarioSnacks{

   private final String NOMBRE_DE_ARCHIVO = "inventario_Snacks.txt";
   private List<Snack> inventarioSnacks = new ArrayList<>();

    public ServiceInventarioImpl(){
        File archivo = new File(NOMBRE_DE_ARCHIVO);
        boolean Archivoexiste = archivo.exists();

        if(Archivoexiste){
            cargarSnacks();
        }else{
            try{
                PrintWriter salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                inicializarInventario();
                System.out.printf("Se ha creado el archivo: ´%s' con éxito\n",archivo.getName());
            } catch (Exception e) {
                System.out.println("Se produjo un error al crear el archivo: "+archivo.getName());
                e.getStackTrace();
            }
        }

    }

    private void cargarSnacks() {
        try{
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_DE_ARCHIVO));
            for(String linea: lineas){
                String[] lineaSnackSplit = linea.split(",");
                String nombre = lineaSnackSplit[1];
                double precio = Double.parseDouble(lineaSnackSplit[2]);
                int stockDisponible = Integer.parseInt(lineaSnackSplit[3]);
                inventarioSnacks.add(new Snack(nombre,precio,stockDisponible));
            }

        } catch (IOException e) {
            System.out.printf("Se ha producido un error al abrir el archivo: ´%s´\n",NOMBRE_DE_ARCHIVO);
            e.getStackTrace();
        }
    }

    private void inicializarInventario() {
        agregarSnack(new Snack("Papas Fritas lays 230g", 8200.00, 2));
        agregarSnack(new Snack("Papas Fritas Pehuamar 230g", 8200.00, 2));
        agregarSnack(new Snack("Rueditas PEP 120g", 3070.00 ));
        agregarSnack(new Snack("Papas Fritas Pringles 109g", 5355.00 , 2));
        agregarSnack(new Snack("Agua Glaciar 500ml", 1196.00 ,2));
        agregarSnack(new Snack("Agua Nestle 1.5lts", 1300.00 ,2));
        agregarSnack(new Snack("Agua Nestle 1.5lts", 1300.00 ,2));
        agregarSnack(new Snack("Coca Cola Zero 354ml", 1400.07 ,2));
        agregarSnack(new Snack("Coca Cola 354ml", 1400.07 ,2));
        agregarSnack(new Snack("Coca Cola 1.25lts", 2384.25 ,2));
        agregarSnack(new Snack("Coca Cola 2.25lts", 3787.50));
    }

    @Override
    public void agregarSnack(Snack snackNuevo) {
        //Agregamos el Snack a la lista de inventario
        inventarioSnacks.add(snackNuevo);
        //Lo guardamos en nuestro archivo
        guardarInventarioEnArchivo(snackNuevo);
    }

    private void guardarInventarioEnArchivo(Snack snackNuevo) {
        File archivo = new File(NOMBRE_DE_ARCHIVO);
        boolean anexar = archivo.exists();
        try{
            PrintWriter linea = new PrintWriter(new FileWriter(archivo,anexar));
            linea.println(snackNuevo.formateo());
            linea.close();
        }catch (IOException e){
            System.out.println("Se produjo un error al grabar el inventario en el archivo: "+archivo.getName());
            e.getStackTrace();
        }
    }

    public List<Snack> getInventarioSnacks(){
        return this.inventarioSnacks;
    }

    @Override
    public void actualizarStock(Snack snack) {
        //Leemos el archivo nuevamente:
        int idSnackAactualizar = snack.getIdSnack();
        try{
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_DE_ARCHIVO));
            int numeroLinea=0;
            for(String linea : lineas){
                String[] lineaSnackSplit = linea.split(",");
                int idSnackDeLinea = Integer.parseInt(lineaSnackSplit[0]);
                if(idSnackDeLinea == idSnackAactualizar){
                    lineas.set(numeroLinea,snack.formateo());
                    break;
                }
                numeroLinea++;
            }
            Files.write(Paths.get(NOMBRE_DE_ARCHIVO),lineas);
        } catch (IOException e) {
            System.out.printf("Se ha producido un error al abrir el archivo: ´%s´\n",NOMBRE_DE_ARCHIVO);
            e.getStackTrace();
        }

    }

    @Override
    public void mostrarSnacks() {
        System.out.println("*** Inventario Snacks ***");
        String inventarioAmostrar="";
        for(Snack snack: inventarioSnacks){
            inventarioAmostrar+="\t"+ snack.toString()+"\n";
        }
        System.out.println(inventarioAmostrar);
    }
}
