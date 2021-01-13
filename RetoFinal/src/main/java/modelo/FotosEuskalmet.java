package modelo;

public class FotosEuskalmet implements java.io.Serializable {

	private String id;
	private Fotos fotos;

	public FotosEuskalmet() {
	}

	public FotosEuskalmet(Fotos fotos) {
		this.fotos = fotos;
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

}
