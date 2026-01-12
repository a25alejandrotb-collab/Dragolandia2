package com.example.modelo;

import jakarta.persistence.Entity;

/**
 * Hechizo de alto daño: resta 10 puntos de vida al monstruo objetivo.
 */
@Entity
public class Rayo extends Hechizo {

    public Rayo() {
        this.nombre = "Rayo";
    }

    @Override
    public void aplicar(Monstruo monstruo) {
        monstruo.setVida(monstruo.getVida() - 10);
    }
}

