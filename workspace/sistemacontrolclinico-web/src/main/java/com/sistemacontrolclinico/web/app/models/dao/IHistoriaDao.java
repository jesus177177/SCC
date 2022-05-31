package com.sistemacontrolclinico.web.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemacontrolclinico.web.app.models.entity.Historia;
import com.sistemacontrolclinico.web.app.models.entity.Paciente;

@Repository
public interface IHistoriaDao extends JpaRepository<Historia, Long> {

	public List<Historia> findByPaciente(Paciente paciente);
}
