package com.sistemacontrolclinico.web.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemacontrolclinico.web.app.models.dao.IHistoriaDao;
import com.sistemacontrolclinico.web.app.models.entity.Historia;

@Service
public class HistoriaServiceImpl implements IHistoriaService{
	
	@Autowired
	private IHistoriaDao repositorio;

	@Override
	public Historia finById(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public void save(Historia historia) {
		repositorio.save(historia);
	}

	@Override
	public void delete(Long id) {
		repositorio.deleteById(id);
	}

}
