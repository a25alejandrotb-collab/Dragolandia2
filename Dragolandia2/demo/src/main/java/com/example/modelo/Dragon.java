package com.example.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entidad `Dragon` que protege un `Bosque` y puede atacar a monstruos con
 * su `exhalar` (daño basado en `intensidadFuego`).
 */
@Entity
@Table(name="Dragon")
public class Dragon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int intensidadFuego;
    private int resistencia;

    @OneToOne
    private Bosque bosque;
    
    // Constructor de conveniencia (no hay constructor por defecto explícito)
    public Dragon(String nombre, int intensidadFuego, int resistencia) {
        this.nombre = nombre;
        this.intensidadFuego = intensidadFuego;
        this.resistencia = resistencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIntensidadFuego() {
        return intensidadFuego;
    }

    public void setIntensidadFuego(int intensidadFuego) {
        this.intensidadFuego = intensidadFuego;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    /**
     * Aplica daño a un `Monstruo` restándole `intensidadFuego` de vida.
     */
    public void exhalar(Monstruo monstruo){
        monstruo.setVida(monstruo.getVida()-getIntensidadFuego());
    }

    /**
     * El dragón recibe daño de un monstruo, reduciendo su resistencia.
     * La resistencia no puede ser negativa.
     * 
     * @param monstruo el monstruo que ataca al dragón
     */
    public void recibirDaño(Monstruo monstruo) {
        int daño = monstruo.getFuerza();
        this.resistencia -= daño;
        if (this.resistencia < 0) {
            this.resistencia = 0;
        }
    }

    /**
     * Verifica si el dragón está vivo.
     * 
     * @return `true` si la resistencia es mayor a 0, `false` en caso contrario
     */
    public boolean estaVivo() {
        return this.resistencia > 0;
    }
}
