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


	@Override
	public String toString() {
		return "AltoMando [numAltoMando=" + numAltoMando + ", nombre=" + nombre + ", pokemons=" + pokemons + "]";
	}
	
	
	
	
	
	
	

}
