package modelo;

public class EspaciosNaturales implements java.io.Serializable {

	private String nombre;
	private String descripcion;
	private String tipo;
	private String web;

	public EspaciosNaturales() {
	}

	public EspaciosNaturales(String nombre) {
		this.nombre = nombre;
	}

	public EspaciosNaturales(String nombre, String descripcion, String tipo, String web) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.web = web;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

}
