package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "existe")
public class RelacionExiste implements java.io.Serializable {
	@Id
	@Column(name = "nomMunicipio")
	private String nomMunicipio;
	
	@Id
	@Column(name = "nomEspNat")
	private String nomEspNat;

	// Constructor vacio
	public RelacionExiste() {
	}

	// Constructor lleno
	public RelacionExiste(String nomMunicipio, String nomEspNat) {
		this.nomMunicipio = nomMunicipio;
		this.nomEspNat = nomEspNat;
	}

	public String getNomMunicipio() {
		return nomMunicipio;
	}

	public void setNomMunicipio(String nomMunicipio) {
		this.nomMunicipio = nomMunicipio;
	}

	public String getNomEspNat() {
		return nomEspNat;
	}

	public void setNomEspNat(String nomEspNat) {
		this.nomEspNat = nomEspNat;
	}
}
