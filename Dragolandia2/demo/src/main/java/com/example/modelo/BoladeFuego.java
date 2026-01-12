package com.example.modelo;

import jakarta.persistence.Entity;

/**
 * Hechizo que resta 5 puntos de vida al monstruo objetivo.
 */
@Entity
public class BoladeFuego extends Hechizo {
    public BoladeFuego() {
        this.nombre = "Bola de fuego";
    }

    @Override
    public void aplicar(Monstruo monstruo) {
        monstruo.setVida(monstruo.getVida() - 5);
    }
}
