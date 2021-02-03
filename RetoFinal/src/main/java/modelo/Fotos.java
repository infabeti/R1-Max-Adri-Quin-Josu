package modelo;

public class Fotos implements java.io.Serializable {

	private String id;
	private Municipios municipios;
//	private FotosEuskalmet fotosEuskalmet;
//	private FotosUsuarios fotosUsuarios;

	public Fotos() {
	}

	public Fotos(String id) {
		this.id = id;
	}

//	public Fotos(String id, Municipios municipios, FotosEuskalmet fotosEuskalmet, FotosUsuarios fotosUsuarios) {
//		this.id = id;
//		this.municipios = municipios;
//		this.fotosEuskalmet = fotosEuskalmet;
//		this.fotosUsuarios = fotosUsuarios;
//	}

	public Fotos(String id, Municipios municipios) {
		this.id = id;
		this.municipios = municipios;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Municipios getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(Municipios municipios) {
		this.municipios = municipios;
	}

//	public FotosEuskalmet getFotosEuskalmet() {
//		return this.fotosEuskalmet;
//	}
//
//	public void setFotosEuskalmet(FotosEuskalmet fotosEuskalmet) {
//		this.fotosEuskalmet = fotosEuskalmet;
//	}
//
//	public FotosUsuarios getFotosUsuarios() {
//		return this.fotosUsuarios;
//	}
//
//	public void setFotosUsuarios(FotosUsuarios fotosUsuarios) {
//		this.fotosUsuarios = fotosUsuarios;
//	}

}
