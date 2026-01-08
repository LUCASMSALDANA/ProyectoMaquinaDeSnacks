package service;

import model.Snack;

import java.util.List;

public interface IServiceInventarioSnacks {
     void agregarSnack(Snack snackNuevo);
     void mostrarSnacks();
     List<Snack> getInventarioSnacks();
     void actualizarStock(Snack snack);
}
