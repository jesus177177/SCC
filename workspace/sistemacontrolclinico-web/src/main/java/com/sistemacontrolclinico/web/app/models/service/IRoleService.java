package com.sistemacontrolclinico.web.app.models.service;

import java.util.List;

import com.sistemacontrolclinico.web.app.models.entity.Role;

public interface IRoleService {

public List<Role> findAll();
	
	public void save(Role role);
	
	public Role findOne(Long id);
	
	public void delete(Long id);

}
