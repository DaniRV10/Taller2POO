package logica;

import java.util.ArrayList;

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
		System.out.println(p.getNombre() + "ha sido agregado a tu equipo!");
		
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
		
		if(misPokemon.isEmpty()) {
			System.out.println("No tienes actualmente un equipo");
			return;
		}
		int cantEquipo = (misPokemon.size() <= 6) ? misPokemon.size() : 6;
		for(int i = 0; i<cantEquipo; i++) {
			Pokemon p = misPokemon.get(i);
			System.out.println((i+1) +") "+ p.getNombre() + "|" + p.getTipo() + "|Stats totales: "+p.sumaStats());
		}
		
	}



	
	
	
	
	
	

}
