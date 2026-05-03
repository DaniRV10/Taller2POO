package logica;

import java.util.ArrayList;

public class Gym {
	private int numGym;
	private String lider;
	private String estado;
	private int cantPokemon;
	private ArrayList<Pokemon> pokemons;
	

	public Gym(int numGym, String lider, String estado, int cantPokemon, ArrayList<Pokemon> pokemons) {
		super();
		this.numGym = numGym;
		this.lider = lider;
		this.estado = estado;
		this.cantPokemon = cantPokemon;
		this.pokemons = pokemons;
	}

	

	public int getNumGym() {
		return numGym;
	}



	public String getLider() {
		return lider;
	}



	public String getEstado() {
		return estado;
	}



	public int getCantPokemon() {
		return cantPokemon;
	}



	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}



	@Override
	public String toString() {
		return "Gym [numGym=" + numGym + ", lider=" + lider + ", estado=" + estado + ", cantPokemon=" + cantPokemon
				+ ", pokemons=" + pokemons + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
