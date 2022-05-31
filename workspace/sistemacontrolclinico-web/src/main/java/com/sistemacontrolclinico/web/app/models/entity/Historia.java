package com.sistemacontrolclinico.web.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "historias")
public class Historia implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String consulta;

	@Temporal(TemporalType.DATE)
	private Date creacion;

	@ManyToOne(fetch = FetchType.LAZY)
	private Paciente paciente;
	
	public Historia() {
		super();
	}

	public Historia(String consulta,Paciente paciente) {
		super();
		this.consulta = consulta;
		this.paciente = paciente;
	}

	@PrePersist
	public void prePersist() {
		this.creacion = new Date();
	} 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	} 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
