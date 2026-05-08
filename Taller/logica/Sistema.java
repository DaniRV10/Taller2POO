package logica;
//Carlos Alberto Montenegro Perez 22154893-0 ICCI
//Daniel Alexanders Robles Valdenegro 20738244-2 ICCI

import dominio.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
	
	
	private static ArrayList<Pokemon> pokedex = new ArrayList<>();
	private static ArrayList<String> habitats = new ArrayList<>();
	private static Entrenador jugador;
	private static ArrayList<AltoMando> altoMando = new ArrayList<>();
	private static ArrayList<Gym> lideresGym = new ArrayList<>();
	static Scanner s = new Scanner(System.in);
	

	public static void main(String[] args) throws FileNotFoundException{
		
		cargarPokedex();
		cargarHabitats();
		cargarAltoMando();
		cargarGyms();
		menuInicial();
		
		
	}
	
	private static void menuInicial()  throws FileNotFoundException{	
		boolean flag = true;
		do {
			System.out.println("1) Continuar.");
			System.out.println("2) Nueva Partida.");
			System.out.println("3) Salir.");
			System.out.print("Ingrese Opcion: ");
			String opcion = s.nextLine();
			
			switch (opcion) {
			case "1":
				if(menuContinuar()) {
					menu();
					flag = false;
				}
				break;
			case "2":
				if(menuNuevaPartida()) {
					menu();
					flag = false;
				}
				break;
			case "3":
				flag=false;
				break;
			default:
				System.out.println("Entregue una opcion valida porfavor");
				break;
			}
		} while (flag);	
	}

	private static void menu() {
		boolean flag = true;
		do {
			System.out.println();
			System.out.println(jugador.getNombre() + ", que deseas hacer?");
			System.out.println("1) Revisar equipo.");
			System.out.println("2) Salir a capturar.");
			System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
			System.out.println("4) Retar un gimnasio.");
			System.out.println("5) Desafío al Alto Mando.");
			System.out.println("6) Curar Pokémon.");
			System.out.println("7) Guardar.");
			System.out.println("8) Guardar y Salir.");
			System.out.print("Ingrese Opcion: ");
			String opcion = s.nextLine();
			switch(opcion) {
			case "1":
				jugador.obtenerEquipo();
				break;
			case "2":
				capturarPokemon();
				break;
			case "3":
				jugador.accederPc();
				break;
			case "4":
				retarGimnasio();
				break;
			case "5":
				retarAltoMando();
				break;
			case "6":
				jugador.curarPokemons();
				break;
			case "7":
				guardarPartida(jugador);
				break;
			case "8":
				guardarPartida(jugador);
				flag = false;
				System.out.println("Nos vemos entrenador...");
				break;
			default:
				System.out.println("Entregue una opcion valida porfavor");
				break;	
			}
			
		} while (flag);
		
		
	}

//Validaciones retar altomando
	private static void retarAltoMando() {
		for (Gym g : lideresGym) {
			if (g.getEstado().equalsIgnoreCase("Sin Derrotar")) {
				System.out.println("Debes derrotar todos los gimnasios para retar al alto mando");
				System.out.println("Derrota primero a: "+ g.getLider());
				return;
			}	
		}
		
		 if (!jugador.hayPokemonVivoEnEquipo()) {
             System.out.println("No tienes Pokemon vivos en tu equipo! Curalos primero.");
             return; 
		 }
		 
		 System.out.println("\n===== ALTO MANDO =====");
		 
		 boolean victoria = batallarAltoMando();
		 if (victoria) {
			 System.out.println("\nFELICITACIONES " + jugador.getNombre() +"!!!");
			 System.out.println("Has derrota al alto mando y eres el nuevo Campeon de la Liga Pokemon.");
		 }
	}

	private static boolean batallarAltoMando() {
		for (AltoMando miembro : altoMando) {
			System.out.println("\nDesafiando a " + miembro.getNombre() + "!!");

			// Copia los pokemon del miembro
			ArrayList<Pokemon> pokemonsMiembro = new ArrayList<>();
			for (Pokemon p : miembro.getPokemons()) {
				pokemonsMiembro.add(obtenerPokemonDeLaPokedex(p.getNombre()));
			}

			// Misma logica que la batalla de gimnasio

			Pokemon pokemonRival = pokemonsMiembro.get(0);
			Pokemon pokemonJugador = jugador.primerPokemonVivo(); // En caso de que no esten todos vivos es necesario verificar
															// el primer pokemon vivo

			System.out.println(miembro.getNombre() + " saca a " + pokemonRival.getNombre() + "!");
			System.out.println(jugador.getNombre() + " saca a " + pokemonJugador.getNombre() + "!");
			boolean batallaActiva = true;
			int indiceRival = 0;
			boolean miembroDerrotado = false; //variable para saber que el miembro fue derrotado, ya que no tiene estado

			while (batallaActiva) {
				System.out.println("\nQue deseas hacer?");
				System.out.println("1) Atacar");
				System.out.println("2) Cambiar de Pokemon");
				System.out.println("3) Rendirse");
				System.out.print("Ingrese Opcion: ");
				String opcion = s.nextLine();

				switch (opcion) {
				case "1":
					pokemonJugador = simularAtaque(pokemonJugador, pokemonRival);

					// Si el pokemon del jugador fue derrotado
					if (pokemonJugador == null) {
						if (!jugador.hayPokemonVivoEnEquipo()) {
							System.out.println("Te has quedado sin pokemons en tu equipo!");
							System.out.println("Volviendo al menu...");
							batallaActiva = false;
							break;
						}
						pokemonJugador = elegirPokemonParaCombate();
						if (pokemonJugador == null) {
							batallaActiva = false;
							break;
						}
						System.out.println(jugador.getNombre() + " saca a " + pokemonJugador.getNombre() + "!");
						break;
					}

					// Si el pokemon rival fue derrotado
					if (pokemonRival.getEstado().equalsIgnoreCase("Debilitado")) {
						indiceRival++;
						if (indiceRival >= pokemonsMiembro.size()) {
							// Jugador gana
							System.out.println("Has derrotado a " + miembro.getNombre() + "!!");
							miembroDerrotado = true;
							batallaActiva = false;
						} else {
							pokemonRival = pokemonsMiembro.get(indiceRival);
							System.out.println(miembro.getNombre() + " saca a " + pokemonRival.getNombre() + "!");
						}
					}
					break;

				case "2":
					Pokemon nuevo = elegirPokemonParaCombate();
					if (nuevo != null && !nuevo.getNombre().equals(pokemonJugador.getNombre())) {
						pokemonJugador = nuevo;
						System.out.println(jugador.getNombre() + " saca a " + pokemonJugador.getNombre() + "!");
					}
					break;

				case "3":
					System.out.println("Te has rendido. Volviendo al menu...");
					batallaActiva = false;
					break;

				default:
					System.out.println("Opcion invalida.");
					break;
				}
			}
			if (!miembroDerrotado) return false; //Si en algun momento no es derrotado el miembro, retorna false cortando los combates que faltan
		}
		return true;
	}

	private static void retarGimnasio() {
		//Mostrar gimnasios disponibles
		if(jugador.getMisPokemon().isEmpty()) {
			System.out.println("No tienes equipo pokemon para retar los gimnasios, vuelve con un equipo pokemon.");
			return;
			
		}
		System.out.println("===== GIMNASIOS =====");
		for (Gym g : lideresGym) {
	        System.out.println(g.getNumGym() + ") " + g.getLider() + " - " + g.getEstado());
	    }
	    System.out.println(lideresGym.size() + 1 + ") Volver al menu.");
	    
	    boolean fin = true;
	    do {
	        try {
	            System.out.print("Elige un gimnasio: ");
	            int eleccion = Integer.parseInt(s.nextLine()) - 1;

	            // Opcion volver
	            if (eleccion == lideresGym.size()) {
	                fin = false;
	                break;
	            }

	            if (eleccion < 0 || eleccion >= lideresGym.size()) {
	                System.out.println("Numero fuera de rango.");
	                continue;
	            }

	            // Verificar progreso
	            if (eleccion > 0 && lideresGym.get(eleccion - 1).getEstado().equalsIgnoreCase("Sin derrotar")) {
	                System.out.println("Debes derrotar primero a " + lideresGym.get(eleccion - 1).getLider() + "!");
	                continue;
	            }

	            // Verificar estado gym
	            Gym gymElegido = lideresGym.get(eleccion);
	            if (gymElegido.getEstado().equalsIgnoreCase("Derrotado")) {
	                System.out.println("Ya derrotaste a este lider. Elige otro.");
	                continue;
	            }

	            // Verificar que el jugador tenga al menos 1 pokemon vivo en su equipo
	            if (!jugador.hayPokemonVivoEnEquipo()) {
	                System.out.println("No tienes Pokemon vivos en tu equipo! Curalos primero.");
	                continue;
	            }

	            batallarContraGym(gymElegido);
	            fin = false;

	        } catch (Exception e) {
	            System.out.println("Ingresa un numero valido.");
	        }
	    } while (fin);
	    
	}



	private static void batallarContraGym(Gym gymElegido) {
		System.out.println("\nDesafiando a " + gymElegido.getLider() + "!");
		
		//Copia de los pokemon del gym
		ArrayList<Pokemon> pokemonsGym = new ArrayList<>();
		for (Pokemon pokemon : gymElegido.getPokemons()) {
			pokemonsGym.add(obtenerPokemonDeLaPokedex(pokemon.getNombre()));
			
		}
		
		Pokemon pokemonRival = pokemonsGym.get(0);
		Pokemon pokemonJugador = jugador.primerPokemonVivo(); //En caso de que no esten todos vivos es necesario verificar el primer pokemon vivo
		
		System.out.println(gymElegido.getLider() + " saca a " + pokemonRival.getNombre() + "!");
		System.out.println(jugador.getNombre() + " saca a " + pokemonJugador.getNombre() + "!");
		boolean batallaActiva = true;
		   int indiceRival = 0;
		   
		   while (batallaActiva) {
		       System.out.println("\nQue deseas hacer?");
		       System.out.println("1) Atacar");
		       System.out.println("2) Cambiar de Pokemon");
		       System.out.println("3) Rendirse");
		       System.out.print("Ingrese Opcion: ");
		       String opcion = s.nextLine();

		       switch (opcion) {
		           case "1":
		               pokemonJugador = simularAtaque(pokemonJugador, pokemonRival);

		               // Si el pokemon del jugador fue derrotado
		               if (pokemonJugador == null) {
		                   if (!jugador.hayPokemonVivoEnEquipo()) {
		                       System.out.println("Te has quedado sin pokemons en tu equipo!");
		                       System.out.println("Volviendo al menu...");
		                       batallaActiva = false;
		                       break;
		                   }
		                   pokemonJugador = elegirPokemonParaCombate();
		                   if (pokemonJugador == null) {
		                       batallaActiva = false;
		                       break;
		                   }
		                   System.out.println(jugador.getNombre() + " saca a " + pokemonJugador.getNombre() + "!");
		                   break;
		               }

		               // Si el pokemon rival fue derrotado
		               if (pokemonRival.getEstado().equalsIgnoreCase("Debilitado")) {
		                   indiceRival++;
		                   if (indiceRival >= pokemonsGym.size()) {
		                       // Jugador gana
		                       System.out.println("Has derrotado a " + gymElegido.getLider() + "!!");
		                       System.out.println("Has obtenido la medalla de " + gymElegido.getLider() + "!");
		                       gymElegido.setEstado("Derrotado");
		                       jugador.añadirMedalla(gymElegido.getLider());
		                       batallaActiva = false;
		                   } else {
		                       pokemonRival = pokemonsGym.get(indiceRival);
		                       System.out.println(gymElegido.getLider() + " saca a " + pokemonRival.getNombre() + "!");
		                    }
		               }
		               break;
		               
		           case "2":
		               Pokemon nuevo = elegirPokemonParaCombate();
		               if (nuevo != null && !nuevo.getNombre().equals(pokemonJugador.getNombre())) {
		                   pokemonJugador = nuevo;
		                   System.out.println(jugador.getNombre() + " saca a " + pokemonJugador.getNombre() + "!");
		               }
		               break;

		           case "3":
		               System.out.println("Te has rendido. El gimnasio sigue en pie...");
		               batallaActiva = false;
		               break;

		           default:
		               System.out.println("Opcion invalida.");
		               break;
		       }
		   }
	}

	private static Pokemon elegirPokemonParaCombate() {
		ArrayList<Pokemon> equipo = jugador.getMisPokemon();

	    System.out.println("\nElige tu Pokemon:");
	    for (int i = 0; i < 6 && i < equipo.size(); i++) {
	        Pokemon p = equipo.get(i);
	        System.out.println((i + 1) + ") " + p.getNombre() + " | " + p.getTipo() 
	            + " | Stats: " + p.getSumaStats() + " | " + p.getEstado());
	    }

	    boolean flag = true;
	    while (flag) {
	        try {
	            System.out.print("Ingrese numero: ");
	            int eleccion = Integer.parseInt(s.nextLine()) - 1;

	            if (eleccion < 0 || eleccion >= 6) {
	                System.out.println("Numero fuera de rango.");
	                continue;
	            }

	            Pokemon elegido = equipo.get(eleccion);
	            if (elegido.getEstado().equalsIgnoreCase("Debilitado")) {
	                System.out.println(elegido.getNombre() + " esta Debilitado y no puede combatir!");
	                continue;
	            }
	            return elegido;

	        } catch (Exception e) {
	            System.out.println("Ingresa un numero valido.");
	        }
	    }
	    return null;
	}

	private static Pokemon simularAtaque(Pokemon atacante, Pokemon defensor) {
		    double efectividad = TablaTipos.getEfectividad(atacante.getTipo(), defensor.getTipo());
		    double statsAtacante = atacante.getSumaStats() * efectividad;
		    double statsDefensor = defensor.getSumaStats();

		    System.out.println("\n" + atacante.getNombre() + " -> " + (int) atacante.getSumaStats() + " puntos");
		    System.out.println(defensor.getNombre() + " -> " + (int) statsDefensor + " puntos");

		    if (efectividad == 2.0) {
		        System.out.println(atacante.getNombre() + " es muy efectivo contra " + defensor.getNombre() + "!");
		    } else if (efectividad == 0.5) {
		        System.out.println(atacante.getNombre() + " no es efectivo contra " + defensor.getNombre() + "!");
		    } else if (efectividad == 0.0) {
		        System.out.println(atacante.getNombre() + " no tiene efecto contra " + defensor.getNombre() + "!");
		    }

		    System.out.println("Nuevo puntaje:");
		    System.out.println(atacante.getNombre() + " -> " + (int) statsAtacante + " puntos");
		    System.out.println(defensor.getNombre() + " -> " + (int) statsDefensor + " puntos");

		    if (statsAtacante >= statsDefensor) {
		        System.out.println("Ha ganado " + atacante.getNombre() + "! " + defensor.getNombre() + " ha sido derrotado...");
		        defensor.setEstado("Debilitado");
		        return atacante; // jugador sigue en pie
		    } else {
		        System.out.println("Ha ganado " + defensor.getNombre() + "! " + atacante.getNombre() + " ha sido derrotado...");
		        atacante.setEstado("Debilitado");
		        return null; // el pokemon del jugador cayó
		    }
	}


	private static void capturarPokemon() {
		System.out.println("Donde deseas ir a explorar?");
		System.out.println("Zonas disponibles:");
		int i = 1;
		for(String s: habitats) {
			System.out.println(i+") "+s);
			i++;
		}
		System.out.println(i+") Volver al menu.");
		boolean flag = true;
		do {	
			try {
				System.out.print("Ingrese Zona: ");
				int numZona = s.nextInt();
				s.nextLine();//Limpiar buffer
				if(numZona <= i) {
					encontrarPokemon(habitats.get(numZona-1));
					flag=false;
				}else {
					System.out.println("Ingrese una opcion valida");
				}	
			} catch (Exception e) {
				System.out.println("Ingrese un numero porfavor");
				s.nextLine(); //Limpiar buffer
			}	
		} while (flag);
	}

	private static void encontrarPokemon(String zonaElegida) {
		ArrayList<Pokemon> posibles = new ArrayList<>();
		for (Pokemon p : pokedex) {
		    if (p.getHabitat().equalsIgnoreCase(zonaElegida) && !jugador.tieneAlPokemon(p.getNombre())) {
		        posibles.add(p);
		    }
		}

		//En caso de haber capturado todos los pokemones de la zona	
		if (posibles.isEmpty()) {
		    System.out.println("Ya has capturado todos los Pokémon de esta zona.");
		    return;
		}
		
		// Logica para obtener un pokemon aleatorio
		double suerte = Math.random(); // numero entre 0  y 1 sin contar 1
		double sumaProbabilidad = 0;
		Pokemon pokemonEncontrado = null;
		for(Pokemon p: posibles) {
			sumaProbabilidad += p.getPorcentajeAparicion();
			if(suerte <= sumaProbabilidad) {
				pokemonEncontrado = obtenerPokemonDeLaPokedex(p.getNombre()); // Para obtener una copia del pokemon
				break;
			}
		}
		
		if(pokemonEncontrado == null) {
			System.out.println("Buscaste con cuidado, pero no encontraste nada nuevo...");
			return;
		}
		
		System.out.println("Oh!! Ha aparecido un increible "+ pokemonEncontrado.getNombre()+"!!");
		boolean flag = true;
		do {
			System.out.println();
			System.out.println("Que deseas hacer?");
			System.out.println("1) Capturar");
			System.out.println("2) Huir");
			System.out.print("Ingrese opcion: ");
			String opcion = s.nextLine();
			switch (opcion) {
			case "1":
				System.out.println(pokemonEncontrado.getNombre() + " capturado con exito!!");
				jugador.añadirPokemon(pokemonEncontrado);
				flag=false;
				break;
			case "2":
				System.out.println("Has logrado escapar con exito");
				flag = false;
				break;
			default:
				System.out.println("Ingrese una opcion valida");
				break;
			}
			
			
			
			
		} while (flag);
		
		
		
		

	}

	private static boolean menuContinuar() throws FileNotFoundException{
		if(cargarPartidaAnterior()) {
			System.out.println("Bienvenido "+ jugador.getNombre()+"!!");
			return true;
		}
		return false;
		
		
	}

	private static boolean cargarPartidaAnterior() throws FileNotFoundException{
		try {
			File archivo = new File("txts/Registros.txt");
			Scanner lector = new Scanner(archivo);
			
			if(!lector.hasNextLine()) {
				System.out.println("No se encontro una partida guardada");
				lector.close();
				return false;
			}
			
			String linea = lector.nextLine();
			String[] partes = linea.split(";");
			
			jugador = new Entrenador(partes[0]);
			if(!partes[1].equalsIgnoreCase("none")) {
				String[] medallas = partes[1].split(",");
				for(int i = 0; i < medallas.length ; i++) {
					jugador.añadirMedalla(medallas[i]);
				}
			}
			
			while(lector.hasNextLine()) {
				linea = lector.nextLine();
				partes = linea.split(";");
				Pokemon p = obtenerPokemonDeLaPokedex(partes[0]);
				p.setEstado(partes[1]);
				jugador.añadirPokemon(p);
			}	
			
			// Actualizar estado de gimnasios segun medallas
			for (Gym g : lideresGym) {
			    if (jugador.getLideresDerrotados().contains(g.getLider())) {
			        g.setEstado("Derrotado");
			    }
			}
			
			return true;
	
		} catch (Exception e) {
			System.out.println("No se encontro el archivo");
			return false;
		}
		
		
		
	}

	private static boolean menuNuevaPartida() {
		try {
			System.out.print("Ingrese un apodo: ");
			String apodo = s.nextLine();
			jugador = new Entrenador(apodo);
			guardarPartida(jugador);
			System.out.println("Bienvenido "+ jugador.getNombre()+"!!");
			return true;
		} catch (Exception e) {
			System.out.println("Ocurrio un error no se pudo crear una nueva partida");
			return false;
		}
		
	}

	private static void guardarPartida(Entrenador jugador2) {
		String medallas = jugador2.getLideresDerrotados().isEmpty()? "none": String.join(",", jugador2.getLideresDerrotados());
		
		try {
			FileWriter fw = new FileWriter("txts/Registros.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
	        
	        bw.write(jugador2.getNombre() + ";" + medallas );
	        bw.newLine();
	        for(Pokemon p : jugador2.getMisPokemon()) {
	        	bw.write(p.getNombre() + ";" + p.getEstado());
	        	bw.newLine();
	        }
	        
	        bw.close();
	        fw.close();
	        
		}catch (Exception e) {
			System.out.println("Error al escribir en el archivo");
		}
			
		
	}

	public static Pokemon obtenerPokemonDeLaPokedex(String nombreBuscado) {
        for (Pokemon p : pokedex) {
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
                // Retornamos una copia para no alterar la Pokedex original
                return new Pokemon(
                    p.getNombre(), p.getHabitat(), p.getPorcentajeAparicion(),
                    p.getVida(), p.getAtaque(), p.getDefensa(),
                    p.getSpAtaque(), p.getSpDefensa(), p.getVelocidad(),
                    p.getTipo()
                );
            }
        }
        return null; // Si no existe
    }

	private static void cargarGyms() throws FileNotFoundException{
		try {
			File archivo = new File("txts/Gimnasios.txt");
			Scanner lector = new Scanner(archivo);
			
			while(lector.hasNextLine()) {
				String linea = lector.nextLine().trim();
				String[] partes = linea.split(";");
				int numGym = Integer.parseInt(partes[0]);
				String nombre = partes[1];
				String estado = partes[2];
				int cantPokemon = Integer.parseInt(partes[3]);
				ArrayList<Pokemon> pokemons = new ArrayList<>(cantPokemon);
				for(int i = 4; i<cantPokemon+4 ; i++) {
					Pokemon p = obtenerPokemonDeLaPokedex(partes[i]);
					pokemons.add(p);
				}
				
				lideresGym.add(new Gym(numGym,nombre, estado,cantPokemon,pokemons));	
				
			}
				
		} catch (Exception e) {
			System.out.println("No se encontro el archivo");
		}
		
	}

	private static void cargarAltoMando() throws FileNotFoundException{
		try {
			File archivo = new File("txts/Alto Mando.txt");
			Scanner lector = new Scanner(archivo);
			
			while(lector.hasNextLine()) {
				String linea = lector.nextLine().trim();
				String[] partes = linea.split(";");
				int numAltoMando = Integer.parseInt(partes[0]);
				String nombre = partes[1];
				ArrayList<Pokemon> pokemons = new ArrayList<>(6);
				for(int i = 2; i<8 ; i++) {
					Pokemon p = obtenerPokemonDeLaPokedex(partes[i]);
					pokemons.add(p);
				}
				
				altoMando.add(new AltoMando(numAltoMando,nombre,pokemons));	
				
			}
				
		} catch (Exception e) {
			System.out.println("No se encontro el archivo");
		}
		
	}

	private static void cargarHabitats() throws FileNotFoundException{
		try {
			File archivo = new File("txts/Habitats.txt");
			Scanner lector = new Scanner(archivo);
			
			while(lector.hasNextLine()) {
				String linea = lector.nextLine().trim();
				habitats.add(linea);
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("No se encontro el archivo");
		}
		
	}

	private static void cargarPokedex() throws FileNotFoundException{
		try {
			File archivo = new File("txts/Pokedex.txt");
			Scanner lector = new Scanner(archivo);
			while(lector.hasNextLine()) {
				String linea = lector.nextLine();
				String[] partes = linea.split(";");
				
				Pokemon pokemon = new Pokemon(partes[0],partes[1],Double.parseDouble(partes[2]), // nombre, habitat, aparicion
						Integer.parseInt(partes[3]),Integer.parseInt(partes[4]),Integer.parseInt(partes[5]), // vida, ataque , defensa
						Integer.parseInt(partes[6]),Integer.parseInt(partes[7]),Integer.parseInt(partes[8]), //SpAtack, SpDefense, velo
						partes[9]); // tipo
				
				pokedex.add(pokemon);
			}
			
		} catch (Exception e) {
			System.out.println("No se encontro el archivo");
		}
		
	}

}
