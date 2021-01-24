package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "municipios")
public class Municipios implements java.io.Serializable {
	
	@Id
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "idProvincia")
	private int idProvincia;

	//Constructor Vacio
	public Municipios() {
	}
	
	//Constructor Nombre
	public Municipios(String nombre) {
		this.nombre = nombre;
	}
	
	//Constructor Lleno
	public Municipios(String nombre, int idProvincia) {
		this.nombre = nombre;
		this.idProvincia = idProvincia;
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
	@Override
	public String toString() {
		String mandar = "";
		if (idProvincia == 1){
			mandar = "Alava \t" + idProvincia + "\t " + nombre + "\n";
		}else if(idProvincia == 20) {
			mandar = "Guipuzkoa \t" + idProvincia + "\t " + nombre + "\n";
		}else {
			mandar = "Bizkaia \t" + idProvincia + "\t " + nombre + "\n";
		}
		return mandar;
	}



}
