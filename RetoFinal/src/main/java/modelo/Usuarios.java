package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios implements java.io.Serializable {
	
	@Id
	@Column(name = "idUser")
	private String idUser;
	
	@Column(name = "password")
	private String password;

	//Constructor Vacio
	public Usuarios() {
	}

	//Constructor Lleno
	public Usuarios(String idUser, String password) {
		this.idUser = idUser;
		this.password = password;
	}
	
	//Getters y Setters
	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
