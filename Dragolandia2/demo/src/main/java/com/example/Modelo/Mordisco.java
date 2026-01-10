package com.example.Modelo;

import jakarta.persistence.Entity;

/**
 * Hechizo sencillo que representa un ataque leve (mordisco): resta 1 punto
 * de vida.
 */
@Entity
public class Mordisco extends Hechizo {

    public Mordisco() {
        this.nombre = "Mordisco";
    }

    @Override
    public void aplicar(Monstruo monstruo) {
        monstruo.setVida(monstruo.getVida() - 1);
    }
    
}
