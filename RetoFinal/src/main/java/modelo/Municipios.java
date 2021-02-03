package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "municipios")
public class Municipios implements Serializable {
	private static final long serialVersionUID = 4390482518182625971L;

	@Id
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "idProvincia")
	private int idProvincia;

	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "latitud")
	private double latitud;
	
	@Column(name = "longitud")
	private double longitud;


	//Constructor Vacio
	public Municipios() {
	}
	
	//Constructor Nombre
	public Municipios(String nombre) {
		this.nombre = nombre;
	}
	
	//Constructor Nombre, idProvincia
	public Municipios(String nombre, int idProvincia) {
		this.nombre = nombre;
		this.idProvincia = idProvincia;
	}
	
	//Constructor Lleno
	public Municipios(String nombre, int idProvincia, String descripcion, double latitud, double longitud) {
		this.nombre = nombre;
		this.idProvincia = idProvincia;
		this.descripcion = descripcion;
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

	public int getIdProvincias() {
		return this.idProvincia;
	}

	public void setIdProvincias(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	@Override
	public String toString() {
		String mandar = "";
		if (idProvincia == 1){
			mandar =  nombre + "\t" + " " + idProvincia + "\t " +"Alava \n";
		}else if(idProvincia == 20) {
			mandar = nombre + "\t" + " " + idProvincia + "\t " +"Guipuzkoa \n";
		}else {
			mandar = nombre + "\t" + " " + idProvincia + "\t " +"Bizkaia \n";
		}
		return mandar;
	}


}
