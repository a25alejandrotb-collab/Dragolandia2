package com.example;

import java.util.List;

import com.example.controlador.ControladorBosque;
import com.example.controlador.ControladorMago;
import com.example.controlador.ControladorMonstruo;
import com.example.modelo.Bosque;
import com.example.modelo.Dragon;
import com.example.modelo.Mago;
import com.example.modelo.Monstruo;
import com.example.util.HibernateUtil;
import com.example.vista.Combate;

/**
 * Clase principal del juego Dragolandia.
 *
 * Este `main` crea los participantes (magos, monstruos y dragón), los persiste
 * mediante los controladores y simula la batalla según la dinámica definida en
 * el enunciado. Los comentarios explican cada paso para facilitar su revisión.
 */
public class Main {

    public static void main(String[] args) {
        // Vista/Utilidad para crear objetos de ejemplo
        Combate combate = new Combate();

        // Controladores
        ControladorMago Cmago = new ControladorMago();
        ControladorMonstruo Cmonstruo = new ControladorMonstruo();
        ControladorBosque Cbosque = new ControladorBosque();

        // 1. Crear magos y persistirlos
        // Combate.crearMagos() devuelve una lista de magos con sus conjuros
        List<Mago> magos = combate.crearMagos();
        System.out.println("=== MAGOS CREADOS ===");
        for (Mago m : magos) {
            // Mostrar datos básicos y guardar en la BD mediante el controlador
            System.out.println(m.getNombre() + " - Vida: " + m.getVida() + ", Magia: " + m.getNivelMagia());
            Cmago.guardarMago(m);
        }

        // 2. Crear bosque y sus monstruos
        Bosque bosque = combate.crearBosque();
        System.out.println("=== BOSQUE CREADO ===");
        System.out.println("Bosque: " + bosque.getNombre() + " (Nivel Peligro: " + bosque.getNivelPeligro() + ")");
        System.out.println("Monstruos: " + (bosque.getMonstruos() == null ? 0 : bosque.getMonstruos().size()));
        if (bosque.getMonstruoJefe() != null) {
            System.out.println("Monstruo Jefe: " + bosque.getMonstruoJefe().getNombre());
        }

        // 3. Guardar monstruos y bosque en la BD
        if (bosque.getMonstruos() != null) {
            for (Monstruo m : bosque.getMonstruos()) {
                Cmonstruo.guardarMonstruo(m);
            }
        }
        Cbosque.guardarBosque(bosque);

        // 4. Crear y mostrar el dragón protector
        Dragon dragon = combate.crearDragon();
        System.out.println("=== DRAGON CREADO ===");
        System.out.println("Dragon: " + dragon.getNombre() + " - Intensidad: " + dragon.getIntensidadFuego() + ", Resistencia: " + dragon.getResistencia());

        // 5. Verificar que hay suficientes participantes para simular la batalla
        if (magos.isEmpty() || bosque.getMonstruos() == null || bosque.getMonstruos().isEmpty()) {
            System.out.println("No hay participantes suficientes para la batalla.");
            return;
        }

        // 6. Seleccionar el primer mago y el monstruo jefe del bosque
        Mago mago = magos.get(0);
        Monstruo monstruoJefe = bosque.getMonstruoJefe();

        // Encabezado de la simulación, con repeat repetimos un signo cierto numeros de veces, las que nosostros indiquemos
        System.out.println("" + "=".repeat(20));
        System.out.println("COMIENZA LA BATALLA");
        System.out.println("=".repeat(20));

        // 7. Simulación de la batalla: bucle principal por turnos
        int turno = 1;
        boolean batallaContinua = true;
        boolean dragoneInterviene = false; // controla la intervención del dragón

        while (batallaContinua) {
            // 7.a Condición de fin de partida
            if (magos.isEmpty() || bosque.getMonstruos().isEmpty()) {
                System.out.println("" + "=".repeat(20));
                System.out.println("FIN DE LA PARTIDA");
                System.out.println("=".repeat(20));

                if (magos.isEmpty()) {
                    System.out.println("No quedan magos. El bosque esta dominado por los monstruos.");
                } else {
                    System.out.println("No quedan monstruos. Los magos controlan el bosque.");
                }
                batallaContinua = false;
                break;
            }

            // 7.b Si el monstruo jefe ha muerto, se elimina y se asigna uno nuevo
            if (monstruoJefe.getVida() <= 0) {
                bosque.eliminarMonstruo(monstruoJefe);
                System.out.println(monstruoJefe.getNombre() + " ha muerto y es eliminado del juego.");
                
                if (bosque.getMonstruos().isEmpty()) {
                    System.out.println("No quedan monstruos. La batalla termina.");
                    batallaContinua = false;
                    break;
                }

                monstruoJefe = bosque.getMonstruos().get(0);
                bosque.setMonstruoJefe(monstruoJefe);
                System.out.println("Nuevo monstruo jefe: " + monstruoJefe.getNombre());
            }

            // 7.c Si el mago actual muere, se elimina y se selecciona otro
            if (mago.getVida() <= 0) {
                magos.remove(mago);
                System.out.println(mago.getNombre() + " ha muerto y es eliminado del juego.");
                
                if (magos.isEmpty()) {
                    System.out.println("No quedan magos. La batalla termina.");
                    batallaContinua = false;
                    break;
                }

                mago = magos.get(0);
                System.out.println("Nuevo mago en batalla: " + mago.getNombre());
            }

            System.out.println("--- Turno " + turno + " ---");

            // 7.d Orden de acciones: el monstruo ataca primero al mago
            monstruoJefe.atacar(mago);
            System.out.println(monstruoJefe.getNombre() + " ataca a " + mago.getNombre() + ". Vida de " + mago.getNombre() + ": " + mago.getVida());

            // Si el mago murió tras el ataque, se pasa al siguiente turno
            if (mago.getVida() <= 0) {
                turno++;
                continue;
            }

            // 7.e El mago lanza un hechizo al monstruo jefe
            mago.lanzarHechizo(monstruoJefe);
            System.out.println(mago.getNombre() + " lanza un hechizo a " + monstruoJefe.getNombre() + ". Vida de " + monstruoJefe.getNombre() + ": " + monstruoJefe.getVida());

            // 7.g El dragón interviene a partir del turno 3
            if (!dragoneInterviene && turno >= 3) {
                dragoneInterviene = true;
                System.out.println("*** " + dragon.getNombre() + " interviene en la batalla ***");
            }

            // 7.f El dragón ataca al monstruo
            if (dragoneInterviene && monstruoJefe.getVida() > 0) {
                dragon.exhalar(monstruoJefe);
                System.out.println(dragon.getNombre() + " exhala fuego a " + monstruoJefe.getNombre() + ". Vida de " + monstruoJefe.getNombre() + ": " + monstruoJefe.getVida());
            }

            turno++;
        }

        // 8. Resultado final de la partida
        System.out.println("\n" + "=".repeat(60));
        if (!magos.isEmpty() && bosque.getMonstruos().isEmpty()) {
            System.out.println(mago.getNombre() + " y los magos dominan el bosque.");
        } else if (magos.isEmpty()) {
            System.out.println("Los monstruos del bosque han ganado.");
        }
        System.out.println("=".repeat(60));

    }
}