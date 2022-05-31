package com.sistemacontrolclinico.web.app.models.service;

import com.sistemacontrolclinico.web.app.models.entity.Historia;

public interface IHistoriaService {

	public Historia finById(Long id);
	
	public void save(Historia historia);
	
	public void delete(Long id);
	
}
