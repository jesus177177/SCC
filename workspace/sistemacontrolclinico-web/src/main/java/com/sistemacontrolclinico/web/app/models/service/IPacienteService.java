package com.sistemacontrolclinico.web.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemacontrolclinico.web.app.models.entity.Historia;
import com.sistemacontrolclinico.web.app.models.entity.Paciente;

public interface IPacienteService {

	public List<Paciente> findAll();
		
	public void save(Paciente paciente);
	
	public Paciente findOne(Long id);
	
	public void delete(Long id);
	
	public Page<Paciente> finAll(Pageable pageable);
	
	public Historia findHistoriaById(Long id);
	
	public List<Historia> findByPaciente(Paciente paciente);
	
	public List<Paciente> findByNombre(String term);
	
	public List<Paciente> finAllOrderByIdDesc();
}
