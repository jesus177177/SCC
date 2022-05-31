package com.sistemacontrolclinico.web.app.models.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sistemacontrolclinico.web.app.models.entity.Usuario;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	
	public Usuario findByUsername(String username);
}
