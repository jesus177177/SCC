package com.sistemacontrolclinico.web.app.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.sistemacontrolclinico.web.app.models.dao.IUsuarioDao;
import com.sistemacontrolclinico.web.app.models.dto.UsuarioDto;
import com.sistemacontrolclinico.web.app.models.entity.Role;
import com.sistemacontrolclinico.web.app.models.entity.Usuario;
import com.sistemacontrolclinico.web.app.models.service.IRoleService;
import com.sistemacontrolclinico.web.app.models.service.IUsuarioService;



@Controller
public class UsuarioController {
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUsuarioService servicio;
	
	@Autowired
	private IRoleService servicioo;
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/saveRole")
	public void roleGuardar(Role role) {
		System.out.println("RoleController >> "+role.getAuthority());
		servicioo.save(role);
	}
	
	@Autowired
	private IUsuarioDao servicioDao;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/listaUsuarios")
	public String listaUsuario(Model model, Authentication authentication){
		List<Usuario> usarios = servicio.findAll();
		model.addAttribute("usuarios", usarios);
		return "listaUsuarios";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/formularioUsuario")
	public String formularioUsuario(Model model, Authentication authentication){
		UsuarioDto usuarioDto = new UsuarioDto();
		model.addAttribute("usuarios", usuarioDto);
		return "usuario/formularioUsuario";
	}
	
	
	@Secured("ROLE_USER")
	@GetMapping("/configuracion")
	public String configuracionInicio(Model model,  Authentication authentication) {
		Usuario usuario = servicioDao.findByUsername(authentication.getName());
		model.addAttribute("usuario", usuario);
		return "configuracion";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/saveUsuario")
	public String configuracionGuardar(Usuario usuario) {
		Role role = new Role();
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setEnabled(true);
		servicio.save(usuario);
		role.setUser_id(usuario.getId());
		role.setAuthority(usuario.getRol());
		role.setId(null);
		System.out.println("Aqui>>"+role.getId());
		//service.save(role);
		roleGuardar(role);
		return "redirect:/listaUsuarios";
	}
	@Secured("ROLE_ADMIN")
	@GetMapping("/deleteuser/{id}")
	public String eleminarUsuario(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/listaUsuarios";
		
	}
	
	/*@Secured("ROLE_ADMIN")
	@GetMapping("/edituser/{id}")
	public String formulario(@PathVariable("id") Long id, Model model) {
		Usuario usuarioEncontrado = servicio.findOne(id);
		model.addAttribute("usuario", usuarioEncontrado);
		return "usuario/formularioUsuario";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/saveUsuario")
	public String guardarUsuario(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario();
		usuario.setNombre(usuarioDto.getNombre());
		usuario.setApellidos(usuarioDto.getApellidos());
		usuario.setUsername(usuarioDto.getUsername());
		usuario.setPassword(usuarioDto.getPassword());
		usuario.setEmail(usuarioDto.getEmail());
		Role role = new Role();
		role.setAuthority("ROLE_USER");
		servicio.save(usuario);
		return "redirect:/usuarios";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/deleteuser/{id}")
	public String eleminarUsuario(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:/usuarios";
		
	}*/
	
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
