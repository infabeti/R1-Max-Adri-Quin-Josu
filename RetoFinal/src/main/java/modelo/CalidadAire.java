package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calidad_aire")
public class CalidadAire implements java.io.Serializable {
	
	@Id
	@Column(name = "nomEstMet")
	private String nomEstMet;
	
	@Column(name = "Comgm3")
	private String comgm3;
	
	@Column(name = "CO8hmgm3")
	private String co8hmgm3;
	
	@Column(name = "Nogm3")
	private String nogm3;
	
	@Column(name = "NO2gm3")
	private String no2gm3;
	
	@Column(name = "NOXgm3")
	private String noxgm3;
	
	@Column(name = "PM10gm3")
	private String pm10gm3;
	
	@Column(name = "PM25gm3")
	private String pm25gm3;
	
	@Column(name = "SO2gm3")
	private String so2gm3;
	
	@Column(name = "fecha_hora")
	private String fechaHora;
	
	//Constructor Vacio
	public CalidadAire() {
	}

	//Constructor fechaHora
	public CalidadAire(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	//Constructor Lleno
	public CalidadAire(String fechaHora, String comgm3,
			String co8hmgm3, String nogm3, String no2gm3, String noxgm3, String pm10gm3, String pm25gm3,
			String so2gm3, String nomEstMet) {

		this.fechaHora = fechaHora;
		this.comgm3 = comgm3;
		this.co8hmgm3 = co8hmgm3;
		this.nogm3 = nogm3;
		this.no2gm3 = no2gm3;
		this.noxgm3 = noxgm3;
		this.pm10gm3 = pm10gm3;
		this.pm25gm3 = pm25gm3;
		this.so2gm3 = so2gm3;
		this.nomEstMet = nomEstMet;
	}

	//Getters y Setters
	public String getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getComgm3() {
		return this.comgm3;
	}

	public void setComgm3(String comgm3) {
		this.comgm3 = comgm3;
	}

	public String getCo8hmgm3() {
		return this.co8hmgm3;
	}

	public void setCo8hmgm3(String co8hmgm3) {
		this.co8hmgm3 = co8hmgm3;
	}

	public String getNogm3() {
		return this.nogm3;
	}

	public void setNogm3(String nogm3) {
		this.nogm3 = nogm3;
	}

	public String getNo2gm3() {
		return this.no2gm3;
	}

	public void setNo2gm3(String no2gm3) {
		this.no2gm3 = no2gm3;
	}

	public String getNoxgm3() {
		return this.noxgm3;
	}

	public void setNoxgm3(String noxgm3) {
		this.noxgm3 = noxgm3;
	}

	public String getPm10gm3() {
		return this.pm10gm3;
	}

	public void setPm10gm3(String pm10gm3) {
		this.pm10gm3 = pm10gm3;
	}

	public String getPm25gm3() {
		return this.pm25gm3;
	}

	public void setPm25gm3(String pm25gm3) {
		this.pm25gm3 = pm25gm3;
	}

	public String getSo2gm3() {
		return this.so2gm3;
	}

	public void setSo2gm3(String so2gm3) {
		this.so2gm3 = so2gm3;
	}
	public String getNomEstMet() {
		return this.nomEstMet;
	}

	public void setNomEstMet(String nomEstMet) {
		this.nomEstMet = nomEstMet;
	}

	@Override
	public String toString() {
		String mandar = "";
		mandar = "Comgm3=" + comgm3 + "\n CO8hmgm3=" + co8hmgm3 + "\n Nogm3=" + nogm3 
				+ "\n NOgm3=" + no2gm3 + "\n NOXgm3=" + noxgm3 + "\n PM10gm3=" + pm10gm3 + "\n PM25gm3=" + pm25gm3
				+ "\n SO2gm3=" + so2gm3 + "\n fechaHora=" + fechaHora;
		return mandar;
	}
	
}
