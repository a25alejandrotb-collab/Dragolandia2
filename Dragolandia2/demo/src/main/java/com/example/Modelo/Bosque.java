package com.example.Modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entidad `Bosque` que contiene monstruos y referencia al monstruo jefe.
 *
 * Provee métodos para añadir, eliminar y mostrar monstruos. La relación
 * OneToMany guarda la colección de monstruos en la base de datos con la
 * columna foránea `bosque_id`.
 */
@Entity
@Table(name = "Bosque")
public class Bosque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int nivelPeligro;

    @OneToOne
    private Monstruo monstruoJefe;

    @OneToMany(targetEntity = Monstruo.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "bosque_id")
    private List<Monstruo> listaMonstruos = new ArrayList<>();

    public Bosque() {
    }

    // Constructor de conveniencia
    public Bosque(String nombre, int nivelPeligro) {
        this.nombre = nombre;
        this.nivelPeligro = nivelPeligro;
        this.listaMonstruos = new ArrayList<>();
    }
                        
    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelPeligro() {
        return nivelPeligro;
    }

    public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    public List<Monstruo> getMonstruos() {
        return listaMonstruos;
    }

    public void setMonstruos(List<Monstruo> listaMonstruos) {
        this.listaMonstruos = listaMonstruos;
    }

    /**
     * Muestra por consola los monstruos del bosque. Protege contra listas
     * nulas o vacías.
     */
    public void mostrarMonstruos() {
        if (listaMonstruos == null || listaMonstruos.isEmpty()) {
            System.out.println("Este bosque no tiene ningún monstruo.");
            return;
        }

        System.out.println("Monstruos del bosque " + nombre + ":");
        for (Monstruo m : listaMonstruos) {
            System.out.println("- " + m);
        }
    }

    /**
     * Añade un monstruo a la colección del bosque.
     */
    public void añadirMonstruo(Monstruo monstruo) {
        listaMonstruos.add(monstruo);
    }

    /**
     * Elimina un monstruo de la colección.
     */
    public void eliminarMonstruo(Monstruo monstruo) {
        listaMonstruos.remove(monstruo);
    }

    @Override
    public String toString() {
        return "Bosque [id=" + id + ", nombre=" + nombre + ", nivelPeligro=" + nivelPeligro + ", monstruoJefe="
                + monstruoJefe + ", monstruos=" + listaMonstruos.size() + "]";
    }
}
