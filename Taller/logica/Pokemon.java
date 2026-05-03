package logica;

public class Pokemon {
	private String nombre;
	private String habitat;
	private double porcentajeAparicion;
	private int vida,ataque,defensa,SpAtaque,SpDefensa,velocidad;
	private String tipo;
	private String estado;
	
	public Pokemon(String nombre, String habitat, double porcentajeAparicion, int vida, int ataque, int defensa,
			int spAtaque, int spDefensa, int velocidad, String tipo) {
		super();
		this.nombre = nombre;
		this.habitat = habitat;
		this.porcentajeAparicion = porcentajeAparicion;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		SpAtaque = spAtaque;
		SpDefensa = spDefensa;
		this.velocidad = velocidad;
		this.tipo = tipo;
		this.estado = "Vivo";
	}
	
	public int sumaStats() {
		return vida + ataque + defensa + SpAtaque + SpDefensa + velocidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public String getHabitat() {
		return habitat;
	}

	public double getPorcentajeAparicion() {
		return porcentajeAparicion;
	}

	public int getVida() {
		return vida;
	}

	public int getAtaque() {
		return ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public int getSpAtaque() {
		return SpAtaque;
	}

	public int getSpDefensa() {
		return SpDefensa;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public String getTipo() {
		return tipo;
	}
	
	public int getSumaStats() {
		return sumaStats();
	}

	@Override
	public String toString() {
		return "Pokemon [nombre=" + nombre + ", estado=" + estado + "]";
	}

	
	
	
	
	

}
