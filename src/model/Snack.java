package model;

import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable {

    private static int contadorSnacks = 0;
    private final int idSnack;
    private String nombre;
    private double precio;
    private int stockDisponible = 0;

    public Snack (){
        this.idSnack = ++contadorSnacks;
    }

    public Snack (String nombre, double precio){
        this();
        this.nombre = nombre;
        this.precio = precio;
    }

    public Snack (String nombre, double precio, int stockDisponible){
        this();
        this.nombre = nombre;
        this.precio = precio;
        this.stockDisponible = stockDisponible;
    }


    public int getIdSnack() {
        return idSnack;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return idSnack == snack.idSnack && Double.compare(precio, snack.precio) == 0 && Objects.equals(nombre, snack.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, nombre, precio);
    }

    @Override
    public String toString() {
        String snack = "{" +
                "idSnack=" + idSnack +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stockDisponible +
                '}';
        if (this.stockDisponible==0){ snack+=" *** AGOTADO ***";}
        return snack;
    }

    public String formateo() {
        return idSnack +","+nombre+","+precio+","+stockDisponible;
    }
}
