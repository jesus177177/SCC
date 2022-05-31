package com.sistemacontrolclinico.web.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sistemacontrolclinico.web.app.models.entity.Cita;


public interface ICitaDao extends JpaRepository<Cita, Long>{
	
	@Query("select  c from Cita c where c.nombre like %?1%")
	List<Cita> findByNombre(String term);
	
	@Query("select c from Cita c order by c.id desc")
	List<Cita> findAllOrderByIdDesc();
}
