package com.sistemacontrolclinico.web.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemacontrolclinico.web.app.models.entity.Cita;
import com.sistemacontrolclinico.web.app.models.entity.Historia;

public interface ICitaService {
	
	public List<Cita> findAll();
	
	public Page<Cita> finAll(Pageable pageable);
	
	public void save(Cita cita);
	
	public Cita findOne(Long id);
	
	public void delete(Long id);
	
	List<Cita> findByNombre(String term);
	
	List<Cita> findAllOrderByIdDesc();
	
}
