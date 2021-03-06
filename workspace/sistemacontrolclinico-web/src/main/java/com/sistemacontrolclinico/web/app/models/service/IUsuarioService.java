package com.sistemacontrolclinico.web.app.models.service;

import java.util.List;

import com.sistemacontrolclinico.web.app.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public void save(Usuario usuario);
	
	public Usuario findOne(Long id);
	
	public void delete(Long id);

}
