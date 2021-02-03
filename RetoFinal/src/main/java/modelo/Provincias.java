package modelo;

public class Provincias implements java.io.Serializable {

	private int id;
	private String nombre;

	public Provincias() {
	}

	public Provincias(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
