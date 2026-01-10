package com.example.Modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidad `Mago`.
 *
 * Representa a un mago jugador o NPC. Contiene validaciones sencillas en los
 * setters para evitar valores negativos en `vida` y `nivelMagia`. Mantiene una
 * relación OneToMany con `Hechizo` para los conjuros que conoce.
 */
@Entity
@Table(name = "Mago")
public class Mago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int vida;
    private int nivelMagia;

    @OneToMany(mappedBy = "mago", cascade = CascadeType.ALL)
    private List<Hechizo> hechizos = new ArrayList<>();

    // Constructor por defecto requerido por JPA
    public Mago() {
    }

    // Constructor de conveniencia para crear instancias en memoria
    public Mago(String nombre, int vida, int nivelMagia) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivelMagia = nivelMagia;
        this.hechizos = new ArrayList<>();
    }

    // Getters / Setters básicos
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

    public int getVida() {
        return vida;
    }

    /**
     * Setter que protege contra valores negativos: si se asigna un valor
     * negativo, la vida queda en 0.
     */
    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }

    public int getNivelMagia() {
        return nivelMagia;
    }

    /**
     * Nivel de magia no puede ser negativo; se normaliza a 0 si se pasa un
     * valor negativo.
     */
    public void setNivelMagia(int nivelMagia) {
        if (nivelMagia < 0) {
            this.nivelMagia = 0;
        } else {
            this.nivelMagia = nivelMagia;
        }
    }

    /**
     * Ataca a un monstruo usando el nivel de magia del mago (daño fijo igual
     * a `nivelMagia`). Método simple para la dinámica del juego.
     */
    public void lanzarHechizo(Monstruo monstruo) {
        monstruo.setVida(monstruo.getVida() - getNivelMagia());
    }

    /**
     * Lanza un hechizo concreto. Si el hechizo pertenece al mago, se aplica al
     * monstruo; si no, el mago recibe 1 punto de daño.
     */
    public void lanzarHechizoMago(Monstruo monstruo, Hechizo hechizo) {
        if (hechizos.contains(hechizo)) {
            hechizo.aplicar(monstruo);
        } else {
            setVida(getVida() - 1);
        }
    }

    /**
     * Añade un hechizo conocido al mago y establece la relación inversa.
     */
    public void aprenderHechizo(Hechizo hechizo) {
        hechizos.add(hechizo);
        hechizo.setMago(this);
    }

    @Override
    public String toString() {
        return "Mago [id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", nivelMagia=" + nivelMagia + "]";
    }

}
