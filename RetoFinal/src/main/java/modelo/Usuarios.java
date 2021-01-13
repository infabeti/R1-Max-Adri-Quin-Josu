package modelo;

public class Usuarios implements java.io.Serializable {

	private String idUser;
	private String password;

	public Usuarios() {
	}

	public Usuarios(String idUser, String password) {
		this.idUser = idUser;
		this.password = password;
	}

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
