package com.sistemacontrolclinico.web.app.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sistemacontrolclinico.web.app.models.entity.Paciente;

@Repository
public interface IPacienteDao extends JpaRepository<Paciente, Long>{

	@Query("select  p from Paciente p where p.nombre like %?1%")
	List<Paciente> findByNombre(String term);
	
	@Query("select p from Paciente p order by p.id desc")
	List<Paciente> finAllOrderByIdDesc();
	
}
