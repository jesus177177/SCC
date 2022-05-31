package com.sistemacontrolclinico.web.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pacientes")
public class Paciente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private String apellidos;

	private String direccion;

	private Double peso;

	private Double altura;

	private String fecha;

	private String edad;

	private Double talla;

	private Double imc;
	
	@JsonIgnore
	@OneToMany(mappedBy = "paciente",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Historia> historias;
	

	public Paciente() {
		this.historias = new ArrayList<Historia>();
	}
	
	@PrePersist
	public void prePersist() {
		this.imc = this.peso / (this.altura * this.altura);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public Double getTalla() {
		return talla;
	}

	public void setTalla(Double talla) {
		this.talla = talla;
	}

	public Double getImc() {
		return imc;
	}

	public void setImc(Double imc) {
		this.imc = imc;
	}

	
	public List<Historia> getHistorias() {
		return historias;
	}

	public void setHistorias(List<Historia> historias) {
		this.historias = historias;
	}

	public void agregarHistoria(Historia historia) {
		this.historias.add(historia);
	}

	private static final long serialVersionUID = 1L;

}
