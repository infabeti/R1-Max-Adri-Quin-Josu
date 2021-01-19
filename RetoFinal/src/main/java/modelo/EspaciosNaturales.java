package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "espacios_naturales")
public class EspaciosNaturales implements java.io.Serializable {
	
	@Id
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="web")
	private String web;
	
	//Constructor Vacio
	public EspaciosNaturales() {
	}
	
	//Constructor nombre
	public EspaciosNaturales(String nombre) {
		this.nombre = nombre;
	}
	
	//Constructor Lleno
	public EspaciosNaturales(String nombre, String descripcion, String tipo, String web) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.web = web;
	}

	//Getters y Setters
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
