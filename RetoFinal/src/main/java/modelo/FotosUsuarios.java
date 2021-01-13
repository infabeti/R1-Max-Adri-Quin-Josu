package modelo;

public class FotosUsuarios implements java.io.Serializable {

	private String id;
	private Fotos fotos;
	private Usuarios usuarios;

	//Constructor Vacio
	public FotosUsuarios() {
	}

	//Constructor con Fotos
	public FotosUsuarios(Fotos fotos) {
		this.fotos = fotos;
	}

	//Constructor lleno
	public FotosUsuarios(Fotos fotos, Usuarios usuarios) {
		this.fotos = fotos;
		this.usuarios = usuarios;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Fotos getFotos() {
		return this.fotos;
	}

	public void setFotos(Fotos fotos) {
		this.fotos = fotos;
	}

	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

}
