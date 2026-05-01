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
	
	public void añadirPokemon(Pokemon p) {
		for(Pokemon miPokemon : misPokemon) {
			if(miPokemon.getNombre().equalsIgnoreCase(p.getNombre())){
				System.out.println("Ya tienes este Pokemon");
				return;
			}
			
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



	
	
	
	
	
	

}
