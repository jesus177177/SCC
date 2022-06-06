package com.sistemacontrolclinico.web.app.models.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemacontrolclinico.web.app.models.entity.Role;

@Service
public class RoleServiceImpl implements IRoleService {

	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void save(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)
	public Role findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
