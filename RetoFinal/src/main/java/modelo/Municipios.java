package modelo;

public class Municipios implements java.io.Serializable {

	private String nombre;
	private Provincias provincias;

	public Municipios() {
	}

	public Municipios(String nombre) {
		this.nombre = nombre;
	}

	public Municipios(String nombre, Provincias provincias) {
		this.nombre = nombre;
		this.provincias = provincias;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincias getProvincias() {
		return this.provincias;
	}

	public void setProvincias(Provincias provincias) {
		this.provincias = provincias;
	}

}
