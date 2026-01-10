package com.example.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Clase base abstracta para los hechizos. Se usa estrategia JOINED para la
 * herencia en JPA: cada subclase tendrá su propia tabla con una relación a la
 * tabla `Hechizo`.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Hechizo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String nombre;

    // Relación ManyToOne con Mago: cada hechizo puede pertenecer a un mago
    @ManyToOne
    @JoinColumn(name = "mago_id")
    private Mago mago;

    public void setMago(Mago mago) {
        this.mago = mago;
    }

    /**
     * Método que las subclases deben implementar para aplicar el efecto del
     * hechizo sobre un `Monstruo`.
     */
    public abstract void aplicar(Monstruo monstruo);

    public String getNombre() {
        return nombre;
    }
}

