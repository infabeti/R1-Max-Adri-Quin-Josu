package modelo;

public class EstacionesMetereologicas implements java.io.Serializable {

	private String nombre;
	private Municipios municipios;
	private String direccion;
	private Double latitud;
	private Double longitud;

	public EstacionesMetereologicas() {
	}

	public EstacionesMetereologicas(String nombre) {
		this.nombre = nombre;
	}

	public EstacionesMetereologicas(String nombre, Municipios municipios, String direccion, Double latitud,
			Double longitud) {
		this.nombre = nombre;
		this.municipios = municipios;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Municipios getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(Municipios municipios) {
		this.municipios = municipios;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Double getLatitud() {
		return this.latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

}
