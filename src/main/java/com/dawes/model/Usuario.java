package com.dawes.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre", length = 45, nullable = false)
	private String nombre;

	@Column(name = "apellidos", length = 45, nullable = false)
	private String apellidos;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "dni", length = 9, nullable = false)
	private String dni;

	@Column(name = "contraseña", length = 45, nullable = false)
	private String contrasenia;
	
	@JsonBackReference(value="citas")
	@OneToMany(mappedBy="usuario",cascade = {CascadeType.ALL})
	private List<Cita> citas;

	@ManyToOne
	@JoinColumn(name="idroles")
	private Rol rol;
//	
//	@JsonBackReference(value="voluntario")
//	@OneToOne
//	@JoinColumn(name = "voluntario_id", referencedColumnName = "id")	
//	private Voluntario voluntario;
//	
//	
//	
	@JsonManagedReference(value="actividades")
	@OneToMany(mappedBy="usuario",cascade = {CascadeType.ALL})
	private List<ActividadUsuario> actividades;

	
//	public List<ActividadUsuario> getActividades() {
//		return actividades;
//	}
//
//	public void setActividades(List<ActividadUsuario> actividades) {
//		this.actividades = actividades;
//	}
//
//	public Voluntario getVoluntario() {
//		return voluntario;
//	}
//
//	public void setVoluntario(Voluntario voluntario) {
//		this.voluntario = voluntario;
//	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public List<ActividadUsuario> getActividades() {
		return actividades;
	}

	public void setActividades(List<ActividadUsuario> actividades) {
		this.actividades = actividades;
	}
//
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
//
//

	public Usuario(String nombre, String apellidos, String email, String dni, String contrasenia,List<Cita> citas, Rol rol, List<ActividadUsuario> actividades) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.dni = dni;
		this.contrasenia = contrasenia;
		this.citas = citas;
		this.rol=rol;
//		this.voluntario=voluntario;
		this.actividades=actividades;

	}

	public Usuario(int id, String nombre, String apellidos, String email, String dni, String contrasenia,
		List<Cita> citas, Rol rol, List<ActividadUsuario> actividades) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.apellidos = apellidos;
	this.email = email;
	this.dni = dni;
	this.contrasenia = contrasenia;
	this.citas = citas;
	this.rol=rol;
//	this.voluntario=voluntario;
//	this.actividades=actividades;
}

	public Usuario() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, citas, contrasenia, dni, email, id, nombre,rol, actividades);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(citas, other.citas)
				&& Objects.equals(contrasenia, other.contrasenia) && Objects.equals(dni, other.dni)
				&& Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(nombre, other.nombre) && Objects.equals(rol, other.rol)&& Objects.equals(actividades, other.actividades);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", dni="
				+ dni + ", contrasenia=" + contrasenia + ", citas=" + citas + ", rol=" + rol +", actividades=" + actividades +"]";
	}

	

}
