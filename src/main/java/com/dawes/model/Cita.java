package com.dawes.model;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
					property  = "id", 
					scope = Integer.class)
@Entity
@Table(name="citas")
public class Cita {
	@Id
	@Column(name="idcitas")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="fecha")
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn(name="idanimal")
	private Animal animal;
	
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Cita(int id, LocalDate fecha, Animal animal, Usuario usuario) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.animal = animal;
		this.usuario=usuario;
	}

	public Cita(LocalDate fecha, Animal animal, Usuario usuario) {
		super();
		this.fecha = fecha;
		this.animal = animal;
		this.usuario=usuario;
	}

	public Cita() {
		super();
	}

	@Override
	public String toString() {
		return "Cita [id=" + id + ", fecha=" + fecha + ", animal=" + animal +", usuario=" + usuario +  "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(animal, fecha, id, animal, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		return Objects.equals(animal, other.animal) && Objects.equals(fecha, other.fecha)&& Objects.equals(usuario, other.usuario) && id == other.id;
	}

	
	
}
