package logica;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
	
	
	private static ArrayList<Pokemon> pokedex = new ArrayList<>();
	private static ArrayList<String> habitats = new ArrayList<>();
	private static Entrenador jugador;
	private static ArrayList<AltoMando> altoMando = new ArrayList<>();
	private static ArrayList<Gym> lideresGym = new ArrayList<>();
	static Scanner s = new Scanner(System.in);
	

	public static void main(String[] args) {
		
		cargarPokedex();
		cargarHabitats();
		//cargarAltoMando();
		//cargarGyms();

		
		
		
	}


	private static void cargarGyms() {
		// TODO Auto-generated method stub
		
	}


	private static void cargarAltoMando() {
		// TODO Auto-generated method stub
		
	}


	private static void cargarHabitats() {
		try {
			File archivo = new File("Habitats.txt");
			Scanner lector = new Scanner(archivo);
			
			while(lector.hasNextLine()) {
				String linea = lector.nextLine();
				habitats.add(linea);
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("No se encontro el archivo");
		}
		
	}


	private static void cargarPokedex() {
		try {
			File archivo = new File("Pokedex.txt");
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
