package com.sistemacontrolclinico.web.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.Map;

import com.sistemacontrolclinico.web.app.models.dao.ICitaDao;
import com.sistemacontrolclinico.web.app.models.entity.Cita;
import com.sistemacontrolclinico.web.app.models.service.ICitaService;
import com.sistemacontrolclinico.web.app.util.paginator.PageRender;

@Secured("ROLE_USER")
@Controller
@SessionAttributes("cita")
public class CitaController {
	
	@Autowired
	private ICitaService citaService;


	@GetMapping("/citas")
	public String listarCitas(/*@RequestParam(name ="page", defaultValue = "0") int page,*/ Model model) {
		//Pageable pageRequest = PageRequest.of(page, 4);
		
		//Page<Cita> citas = citaService.finAll(pageRequest);
		
		//PageRender<Cita> pageRender = new PageRender<>("/citas", citas);
		
		//model.addAttribute("citas", citas);
		//model.addAttribute("page", pageRender);
		List<Cita> citas = citaService.findAllOrderByIdDesc();
		model.addAttribute("citas", citas);
		return "citas/listarCitas";
		
	}
	
	@GetMapping("/form")
	public String crearCita(Map<String, Object> model) {
		Cita cita = new Cita();
		model.put("cita", cita);
		return "citas/form";
	}
	
	@GetMapping("/form/{id}")
	public String actualizarCita(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Cita cita = null;
		if(id>0) {
			cita = citaService.findOne(id);
		}else {
			return "redirect:citas/listarCitas";
		}
		model.put("cita", cita);
		return "citas/form";
	}
	
	@PostMapping("/form")
	public String guardarCita(@Valid Cita cita, BindingResult bindingResult, SessionStatus status) {
		if(bindingResult.hasErrors()) {
			return "citas/form";
		}
		citaService.save(cita);
		status.setComplete();
		return "redirect:citas";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminarCita(@PathVariable(value="id") Long id) {
		if(id > 0) {
			citaService.delete(id);
		}
		return "redirect:/citas";
	}
	
	@GetMapping(value = "/cargar-citas/{term}", produces = { "application/json" })
	public @ResponseBody List<Cita> cargarCitas(@PathVariable String term) {
		return citaService.findByNombre(term);
	}
	
}
