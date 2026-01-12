package com.example.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad `Monstruo`.
 *
 * Modela los monstruos que habitan en el bosque. Incluye un enumerado para
 * el tipo de monstruo y un método `atacar` que reduce la vida de un `Mago`.
 */
@Entity
@Table(name = "Monstruo")
public class Monstruo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int vida;
    private int fuerza;

    @Enumerated (EnumType.STRING)
    private tipoMonstruo tipo;

    // Constructores
    public Monstruo() {
    }

    public Monstruo(String nombre, int vida, int fuerza, tipoMonstruo tipo) {
        this.nombre = nombre;
        this.vida = vida;
        this.fuerza = fuerza;
        this.tipo = tipo;
    }

    // Tipos de monstruo posibles
    public enum tipoMonstruo {
        OGRO,
        TROLL,
        ESPECTRO
    };

    // Getters / Setters
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
     * Evita valores negativos en la vida; si se recibe < 0, queda en 0.
     */
    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public tipoMonstruo getTipo() {
        return tipo;
    }

    public void setTipo(tipoMonstruo tipo) {
        this.tipo = tipo;
    }

    /**
     * Ataca a un `Mago`, reduciendo su vida en función de la `fuerza` del
     * monstruo.
     */
    public void atacar(Mago mago) {
        mago.setVida(mago.getVida() - getFuerza());
    }

    /**
     * Ataca al `Dragon`, reduciéndole su resistencia mediante el método
     * `recibirDaño()`. El dragón perderá resistencia igual a la fuerza del monstruo.
     * 
     * @param dragon el dragón que recibe el ataque
     */
    public void atacarDragon(Dragon dragon) {
        dragon.recibirDaño(this);
    }
}
