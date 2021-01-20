package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estaciones_metereologicas")
public class EstacionesMetereologicas implements java.io.Serializable {
	
	@Id
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "nomMunicipio")
	private String municipios;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "latitud")
	private Double latitud;
	
	@Column(name = "longitud")
	private Double longitud;
	
	//Constructor Vacio
	public EstacionesMetereologicas() {
	}

	//Constructor nombre
	public EstacionesMetereologicas(String nombre) {
		this.nombre = nombre;
	}
	
	//Constructor Lleno
	public EstacionesMetereologicas(String nombre, String municipios, String direccion, Double latitud,
			Double longitud) {
		this.nombre = nombre;
		this.municipios = municipios;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	//Getters y Setters
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(String municipios) {
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
