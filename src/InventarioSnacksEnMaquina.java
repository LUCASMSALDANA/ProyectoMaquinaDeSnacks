import java.util.ArrayList;
import java.util.List;

public class InventarioSnacksEnMaquina {
    public static final List<Snack> inventarioSnacks = new ArrayList<>();
    //Inicializamos la lista estaticamente
    static{
        inventarioSnacks.add(new Snack("Papas Fritas lays 230g", 8200.00));
        inventarioSnacks.add(new Snack("Papas Fritas Pehuamar 230g", 8200.00));
        inventarioSnacks.add(new Snack("Rueditas PEP 120g", 3070.00));
        inventarioSnacks.add(new Snack("Papas Fritas Pringles 109g", 5355.00));
        inventarioSnacks.add(new Snack("Agua Glaciar 500ml", 1196.00));
        inventarioSnacks.add(new Snack("Agua Nestle 1.5lts", 1300.00));
        inventarioSnacks.add(new Snack("Agua Nestle 1.5lts", 1300.00));
        inventarioSnacks.add(new Snack("Coca Cola Zero 354ml", 1400.07));
        inventarioSnacks.add(new Snack("Coca Cola 354ml", 1400.07));
        inventarioSnacks.add(new Snack("Coca Cola 1.25lts", 2384.25));
        inventarioSnacks.add(new Snack("Coca Cola 2.25lts", 3787.50));

    }

    public static void agregarSnack(Snack snackNuevo){
        inventarioSnacks.add(snackNuevo);
    }

    public static void mostrarSnacks(){
        String inventarioAmostrar="";
        for(Snack snack : inventarioSnacks){
            inventarioAmostrar += "\t" + snack.toString() +"\n";
        }
        System.out.println("Inventario de Snacks disponibles: ");
        System.out.println(inventarioAmostrar);
    }

    public static List<Snack> getInventarioSnacks(){
        return inventarioSnacks;
    }

}
