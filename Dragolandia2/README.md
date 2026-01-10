```mermaid
classDiagram
direction TB
    class Monstruo {
	    +int id
	    +String nombre
	    +int vida
	    +int fuerza
	    +atacar(Mago)
    }

    class Ogro {
    }

    class Troll {
    }

    class Espectro {
    }

    class Mago {
	    +int id
	    +String nombre
	    +int vida
	    +int nivelMagia
	    +List conjuros
	    +lanzarHechizo(Monstruo)
	    +lanzarHechizoMago(Monstruo, Hechizo)
	    +aprenderHechizo(Hechizo)
    }

    class Hechizo {
	    +int id
	    +String nombre
	    +aplicar(Monstruo)
    }

    class BoladeFuego {
	    +aplicar(Monstruo)
    }

    class Rayo {
	    +aplicar(Monstruo)
    }

    class BoladeNieve {
	    +aplicar(Monstruo)
    }

    class Mordisco {
	    +aplicar(Monstruo)
    }

    class Dragon {
	    +String nombre
	    +int intensidadFuego
	    +int resistencia
	    +exhalar(Monstruo)
    }

    class Bosque {
	    +int id
	    +String nombre
	    +int nivelPeligro
	    +Monstruo monstruoJefe
	    +List monstruos
	    +añadirMonstruo(Monstruo)
	    +eliminarMonstruo(Monstruo)
	    +mostrarMonstruos()
    }

    Monstruo <|-- Ogro
    Monstruo <|-- Troll
    Monstruo <|-- Espectro
    Hechizo <|-- BoladeFuego
    Hechizo <|-- Rayo
    Hechizo <|-- BoladeNieve
    Hechizo <|-- Mordisco
    Mago "1" -- "0..*" Hechizo
    Bosque "1" -- "0..*" Monstruo
    Bosque "1" -- "0..1" Dragon
```
Ampliacion del juego:
Yo de ampliacion pondria que los monstruos pueden atacar al dragon y este morir y tambien que puedan haber hordas de enemigos, asi como atacan varios magos a la vez que te puedan atacar varios monstruos a la vez
