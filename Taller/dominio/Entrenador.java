package dominio;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrenador {
	
	private String nombre;
	private ArrayList<String> lideresDerrotados;
	private ArrayList<Pokemon> misPokemon;
	
	public Entrenador(String nombre) {
		
		this.nombre = nombre;
		this.lideresDerrotados = new ArrayList<>();
		this.misPokemon = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}
	
	public boolean tieneAlPokemon(String nombre) {
		for(Pokemon miPokemon : misPokemon) {
			if(miPokemon.getNombre().equalsIgnoreCase(nombre)){
				return true;
			}
		}
		return false;
	}
	
	public void añadirPokemon(Pokemon p) {
		if(tieneAlPokemon(p.getNombre())) {
			System.out.println("Ya tienes este Pokemon");
			return;
		}
		misPokemon.add(p);
		
	}
	public void añadirMedalla(String nombre) {
		lideresDerrotados.add(nombre);
	}
	
	

	public ArrayList<String> getLideresDerrotados() {
		return lideresDerrotados;
	}

	public ArrayList<Pokemon> getMisPokemon() {
		return misPokemon;
	}
	
	public void curarPokemons() {
		if(misPokemon.isEmpty()) {
			System.out.println("No tienes actualmente un equipo");
			return;
		}
		for(Pokemon p: misPokemon) {
			if(p.getEstado().equalsIgnoreCase("Debilitado")) {
				p.setEstado("Vivo");
			}
		}
		System.out.println("Tus pokemon se han curado correctamente");
	}

	public void obtenerEquipo() {
		System.out.println("Equipo actual:");
		
		if(getMisPokemon().isEmpty()) {
			System.out.println("No tienes actualmente un equipo");
			return;
		}
		int cantEquipo = (getMisPokemon().size() <= 6) ? getMisPokemon().size() : 6;
		for(int i = 0; i<cantEquipo; i++) {
			Pokemon p = getMisPokemon().get(i);
			System.out.println((i+1) +") "+ p.getNombre() + "|" + p.getTipo() + "|Stats totales: "+p.getSumaStats());
		}
		
	}
	public void accederPc() {
		Scanner s = new Scanner(System.in);
		boolean salir = false;
		do {
			

			if (misPokemon.isEmpty()) {
				System.out.println("No tienes ningun Pokemon aun");
				return;
			}

			System.out.println("\n====== PC de " + nombre + " =====");
			for (int i = 0; i < misPokemon.size(); i++) {
				Pokemon p = misPokemon.get(i);
				String lugar = (i < 6) ? " [EQUIPO]" : " [PC]";
				System.out.println((i + 1) + ")" + p.getNombre() + " | " + p.getTipo() + " | " + "STATS: "
						+ p.getSumaStats() + " | " + p.getEstado() + lugar);
			}

			System.out.println("");
			System.out.println("1) Cambiar Pokemon");
			System.out.println("2) Salir");
			System.out.println("Ingrese Opcion: ");
			String opcion = s.nextLine();

			switch (opcion) {

			case "1":
				if (misPokemon.size() < 2) {
					System.out.println("Necesitas al menos 2 Pokemon");
					break;
				}

				try {
					System.out.print("Ingrese numero del primer Pokemon: ");
					int pos1 = Integer.parseInt(s.nextLine()) - 1;
					System.out.print("Ingrese numero del segundo Pokemon: ");
					int pos2 = Integer.parseInt(s.nextLine()) - 1;

					if (pos1 < 0 || pos1 >= misPokemon.size() || pos2 < 0 || pos2 >= misPokemon.size()) {
						System.out.println("Numeros ingresado fuera de rango. Intenta de nuevo.");
						break;
					}

					// 1. Guardar temporalmente el primer Pokémon
					Pokemon temp = misPokemon.get(pos1);

					// 2. Poner el segundo en la posición del primero
					misPokemon.set(pos1, misPokemon.get(pos2));

					// 3. Poner el temporal en la posición del segundo
					misPokemon.set(pos2, temp);
					System.out.println("Intercambio realizado con exito");
					System.out.println(misPokemon.get(pos1).getNombre() + " <- -> " + misPokemon.get(pos2).getNombre());
				} catch (Exception e) {
					System.out.println("Ingrese un numero valido.");
				}
				break;

			case "2":
				salir = true;
				break;

			default:
				System.out.println("Opcion invalida");
				break;
			}

		} while (!salir);
	}
	
	public Pokemon primerPokemonVivo() {
		for (int i = 0; i < 6 && i < misPokemon.size(); i++) {
	        if (misPokemon.get(i).getEstado().equalsIgnoreCase("Vivo")) return misPokemon.get(i);
	    }
	    return null;
	}
	
	
	public boolean hayPokemonVivoEnEquipo() {	
	    for (int i = 0; i < 6 && i < misPokemon.size(); i++) {
	        if (misPokemon.get(i).getEstado().equalsIgnoreCase("Vivo")) return true;
	    }
	    return false;
	}

}
