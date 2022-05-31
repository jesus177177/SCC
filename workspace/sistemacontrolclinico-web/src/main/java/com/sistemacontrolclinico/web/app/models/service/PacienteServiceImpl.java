package com.sistemacontrolclinico.web.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemacontrolclinico.web.app.models.dao.IHistoriaDao;
import com.sistemacontrolclinico.web.app.models.dao.IPacienteDao;
import com.sistemacontrolclinico.web.app.models.entity.Historia;
import com.sistemacontrolclinico.web.app.models.entity.Paciente;

@Service
public class PacienteServiceImpl implements IPacienteService{
	
	@Autowired
	private IPacienteDao repo;
	
	@Autowired
	private IHistoriaDao historiaDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<Paciente> findAll() {
		return (List<Paciente>) repo.findAll();
	}

	@Override
	@Transactional
	public void save(Paciente paciente) {
		repo.save(paciente);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Paciente findOne(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Page<Paciente> finAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Historia findHistoriaById(Long id) {
		return historiaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Historia> findByPaciente(Paciente paciente) {
		return historiaDao.findByPaciente(paciente);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Paciente> findByNombre(String term) {
		return repo.findByNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Paciente> finAllOrderByIdDesc() {
		return repo.finAllOrderByIdDesc();
	}
	
	
}
