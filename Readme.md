# Taller 2: Programación Orientada a Objetos

## Descripcion del Proyecto
Este software es un simulador interactivo de Pokémon basado en consola, desarrollado bajo el paradigma de Programación Orientada a Objetos (POO) en Java. El sistema permite cargar la base de datos Pokedex entregada, gestionar hábitats de exploración, simular capturas aleatorias basadas en probabilidades, administrar un equipo principal (primeros 6 Pokémon) y un almacenamiento total (PC). Además, cuenta con un sistema de combates lineales contra Líderes de Gimnasio y un desafío consecutivo contra el Alto Mando, aplicando multiplicadores reales por tabla de efectividad de tipos. Todo el progreso, incluyendo el estado de salud de las criaturas ("Vivo" o "Debilitado") y las medallas obtenidas, permitiendo guardar el progreso realizado si es que se quiere dejar de jugar o crear nuevas partidas.

## Integrantes
- Carlos Alberto Montenegro Pérez - 22.154.893-0 - Akr0yy
- Daniel Alexanders Robles Valdenegro - 20.738.244-2 - DaniRV10


## Estructura del proyecto
El codigo se encuentra organizado bajo una arquitectura dividida en dos paquetes para separar los .java que dan la logica al simulador:
* **dominio**: Contiene las clases de las entidades fundamentales del modelo.
    * `Pokemon.java`: Modela las estadisticas base, tipo, habitat, porcentaje de aparicion y estado que tenga el pokemon.
    *  `Entrenador.java`: Representa al usuario quien juega, con su apodo, el historial de líderes derrotados y su colección dinámica de Pokémon.
    * `Gym.java`: Modela a los lideres de Gymnasios, con su nombre, estado y equipo que posee.
    * `AltoMando.java`: Modela al AltoMando de la simulacion, con su nombre y su equipo pokemon.
    * `TablaTipos.java`: Contiene la matriz bidimensional estatica de efectividades entre tipos de pokemon para calcular las bonificaciones o penalizaciones de daño en combate.
* **logica**:Contiene el motor principal del software.
    * `Sistema.java`: Clase maestra controladora que ejecuta el flujo del programa, renderiza los menús por consola, procesa la lectura/escritura de los archivos `.txt` y ejecuta los algoritmos de combate y captura probabilística.
* **`txts/`**: Carpeta raíz externa que contiene el guardado(`Registros.txt`) y base de datos del juego:
    * `Pokedex.txt`, `Habitats.txt`, `Gimnasios.txt`, `Alto Mando.txt` y `Registros.txt`.


## Instrucciones de ejecución.

Para ejecutar el programa se debe tener instalado un entorno como **Eclipse** o **Java JDK 21**.

En caso de hacerlo en un entorno que no sea el de eclipse como powershell primero se compila.

```bash
javac -d bin Taller/logica/Sistema.java
```
Luego se ejecuta con el siguiente codigo.

```bash
java -cp bin logica.Sistema
```
Tambien para que el software funcione correctamente, asegurar que en la carpeta txts encuentren los archivos .txts que se indican en la estructura del proyecto