package com.sistemacontrolclinico.web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistemacontrolclinico.web.app.models.entity.Role;
import com.sistemacontrolclinico.web.app.models.entity.Usuario;
import com.sistemacontrolclinico.web.app.models.service.IRoleService;

@Secured("ROLE_ADMIN")
@Controller
public class RoleController {
	
	@Autowired
	private IRoleService servicio;
	
	
	

}
