package com.example.Vista;

import java.util.ArrayList;
import java.util.List;

import com.example.Modelo.Bosque;
import com.example.Modelo.Dragon;
import com.example.Modelo.Mago;
import com.example.Modelo.Monstruo;

/**
 * Clase auxiliar que construye objetos de ejemplo para las pruebas y la
 * simulación de combate. Esta clase actúa como "vista" ligera que genera
 * datos ficticios sin interacción con el usuario.
 */
public class Combate {

    /**
     * Crea una lista de magos de ejemplo.
     *
     * Los magos creados aquí son estáticos y sirven para poblar la base de datos
     * y ejecutar la dinámica del juego durante las pruebas.
     */
    public List<Mago> crearMagos() {
        List<Mago> magos = new ArrayList<>();
        // Mago de prueba con muy poca vida
        magos.add(new Mago("Perro de Jhon Wick", 1, 1));
        // Mago muy poderoso para comprobar comportamientos extremos
        magos.add(new Mago("Jhon Wick", 1000000, 1000));
        return magos;
    }

    /**
     * Crea una lista de monstruos de ejemplo con distintos tipos y fuerzas.
     *
     * Los parámetros son: nombre, vida, fuerza y tipo (OGRO, TROLL, ESPECTRO).
     */
    public List<Monstruo> crearMonstruos() {
        List<Monstruo> lista = new ArrayList<>();
        lista.add(new Monstruo("Ogro A", 200, 25, Monstruo.tipoMonstruo.OGRO));
        lista.add(new Monstruo("Troll B", 150, 20, Monstruo.tipoMonstruo.TROLL));
        lista.add(new Monstruo("Espectro C", 120, 30, Monstruo.tipoMonstruo.ESPECTRO));
        return lista;
    }

    /**
     * Construye un `Bosque` poblado con los monstruos creados por
     * `crearMonstruos()` y establece un monstruo jefe (el primero de la lista).
     */
    public Bosque crearBosque() {
        Bosque bosque = new Bosque("Bosque Encantado", 5);
        List<Monstruo> lista = crearMonstruos();
        for (Monstruo m : lista) {
            // Añadir cada monstruo al bosque
            bosque.añadirMonstruo(m);
        }
        // Establecer un monstruo jefe (el primero) si hay elementos
        if (!lista.isEmpty()) {
            bosque.setMonstruoJefe(lista.get(0));
        }
        return bosque;
    }

    /**
     * Crea un `Dragon` de ejemplo que actuará como protector del bosque en la
     * simulación.
     */
    public Dragon crearDragon(){
        Dragon dragon = new Dragon("Dracarys", 50, 500);
        return dragon;
    }
}
