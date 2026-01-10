package com.example.Modelo;

import jakarta.persistence.Entity;

/**
 * Hechizo que deja al monstruo sin vida (vida = 0). Use con precaución en
 * simulaciones ya que es un efecto definitivo.
 */
@Entity
public class BoladeNieve extends Hechizo {

    public BoladeNieve() {
        this.nombre = "Bola de nieve";
    }

    @Override
    public void aplicar(Monstruo monstruo) {
        monstruo.setVida(0);
    }
}

