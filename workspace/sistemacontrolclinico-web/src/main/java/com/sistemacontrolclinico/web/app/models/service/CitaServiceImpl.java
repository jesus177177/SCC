package com.sistemacontrolclinico.web.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemacontrolclinico.web.app.models.dao.ICitaDao;
import com.sistemacontrolclinico.web.app.models.dao.IHistoriaDao;
import com.sistemacontrolclinico.web.app.models.entity.Cita;
import com.sistemacontrolclinico.web.app.models.entity.Historia;

@Service
public class CitaServiceImpl implements ICitaService{
	
	@Autowired
	private ICitaDao citaDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Cita> findAll() {
		return (List<Cita>) citaDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cita cita) {
		citaDao.save(cita);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Cita findOne(Long id) {
		return citaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		citaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cita> finAll(Pageable pageable) {
		return citaDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cita> findByNombre(String term) {
		return citaDao.findByNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cita> findAllOrderByIdDesc() {
		return citaDao.findAllOrderByIdDesc();
	}

	

	
}
