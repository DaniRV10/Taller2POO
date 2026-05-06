package logica;

import java.util.ArrayList;

public class AltoMando {
	
	private int numAltoMando;
	private String nombre;
	private ArrayList<Pokemon> pokemons;
	
	
	public AltoMando(int numAltoMando, String nombre, ArrayList<Pokemon> pokemons) {
		super();
		this.numAltoMando = numAltoMando;
		this.nombre = nombre;
		this.pokemons = pokemons;
	}


	
	
	public int getNumAltoMando() {
		return numAltoMando;
	}




	public String getNombre() {
		return nombre;
	}




	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}




	@Override
	public String toString() {
		return "AltoMando [numAltoMando=" + numAltoMando + ", nombre=" + nombre + ", pokemons=" + pokemons + "]";
	}
	
	
	
	
	
	
	

}
