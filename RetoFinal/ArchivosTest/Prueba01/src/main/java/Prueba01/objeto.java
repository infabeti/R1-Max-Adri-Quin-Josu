package Prueba01;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import org.hibernate.annotations.OptimisticLockType;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, optimisticLock = OptimisticLockType.ALL)
@Table(name = "tablaparapruebas", uniqueConstraints = {
		@UniqueConstraint(columnNames = "Id"),
		@UniqueConstraint(columnNames = "Nombre"),
		@UniqueConstraint(columnNames = "Apellido"),
		@UniqueConstraint(columnNames = "Numero"),
		@UniqueConstraint(columnNames = "UnBooleano")})
public class objeto{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private int id;
	@Column(name = "Nombre", unique = true, nullable = false, length = 20)
	private String nombre;
	@Column(name = "Apellido", unique = false, nullable = false, length = 20)
	private String Apellido;
	@Column(name = "Numero", unique = false, nullable = false)
	private int numero;
	@Column(name = "UnBooleano", unique = false, nullable = true, length = 5)
	private Boolean UnBooleano;
	
	public objeto() {
	}
	public objeto(String nombre, String apellido, int numero, Boolean unBooleano) {
		this.nombre = nombre;
		Apellido = apellido;
		this.numero = numero;
		UnBooleano = unBooleano;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Boolean getUnBooleano() {
		return UnBooleano;
	}
	public void setUnBooleano(Boolean unBooleano) {
		UnBooleano = unBooleano;
	}	
}
