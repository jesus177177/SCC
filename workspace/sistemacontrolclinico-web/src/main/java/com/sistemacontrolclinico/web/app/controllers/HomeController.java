package com.sistemacontrolclinico.web.app.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;

import com.sistemacontrolclinico.web.app.models.entity.Cita;
import com.sistemacontrolclinico.web.app.models.service.ICitaService;

@Controller
public class HomeController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ICitaService citaServicio;

	@Secured("ROLE_USER")
	@GetMapping({"/index","/home"})
	public String index(Model model, Authentication authentication, HttpServletRequest request) {
		//3 formas de obtener el acceso de los roles.
		if(authentication!=null) {
			logger.info("Hola usuario autenticado, tu username es: "+authentication.getName());
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth != null) {
			logger.info("Utilizando forma estática SecurityContextHolder.getContext().getAuthentication(): Usuario autenticado: ".concat(auth.getName()));
		}
		if(hasRole("ROLE_ADMIN")) {
			logger.info("Hola ".concat(auth.getName().concat(" tienes acceso.")));
		} else {
			logger.info("Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
		}
		
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
		
		if(securityContext.isUserInRole("ADMIN")) {
			logger.info("Forma usando SecurityContextHolderAwareRequestWrapper: Hola ".concat(auth.getName()).concat(" tienes acceso!"));
		} else {
			logger.info("Forma usando SecurityContextHolderAwareRequestWrapper: Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
		}
		
		if(request.isUserInRole("ROLE_ADMIN")) {
			logger.info("Forma usando HttpServletRequest: Hola ".concat(auth.getName()).concat(" tienes acceso!"));
		} else {
			logger.info("Forma usando HttpServletRequest: Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
		}	
		List<Cita> citas = citaServicio.findAll();
		int numeroCitas = citas.size();
		model.addAttribute("numerocitas", numeroCitas);
		
		return "index";
	}
	
	@Secured("ROLE_USER")
	@GetMapping
	public String layout(Model model) {
		List<Cita> citas = citaServicio.findAll();
		int numeroCitas = citas.size();
		model.addAttribute("numerocitas", numeroCitas);
		return "layout/layout";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/help")
	public String ayuda() {
		return "ayuda";
	}
	
	
private boolean hasRole(String role) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		
		if(context == null) {
			return false;
		}
		
		Authentication auth = (Authentication) context.getAuthentication();
		
		if(auth == null) {
			return false;
		}
		
		Collection<? extends GrantedAuthority> authorities = ((org.springframework.security.core.Authentication) auth).getAuthorities();
		
		return authorities.contains(new SimpleGrantedAuthority(role));
		
	}
}
