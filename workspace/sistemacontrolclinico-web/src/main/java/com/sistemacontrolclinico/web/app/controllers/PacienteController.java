package com.sistemacontrolclinico.web.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sistemacontrolclinico.web.app.models.dto.PacienteDto;
import com.sistemacontrolclinico.web.app.models.entity.Historia;
import com.sistemacontrolclinico.web.app.models.entity.Paciente;
import com.sistemacontrolclinico.web.app.models.service.IHistoriaService;
import com.sistemacontrolclinico.web.app.models.service.IPacienteService;
import com.sistemacontrolclinico.web.app.util.paginator.PageRender;

@Secured("ROLE_ADMIN")
@Controller
@SessionAttributes("pacienteDto")
public class PacienteController {
	
	@Autowired
	private IPacienteService servicio;
	
	@Autowired
	private IHistoriaService servicioHistoria;
	
	@GetMapping("/pacientes")
	public String listaPacientes(/*@RequestParam(name ="page", defaultValue = "0") int page,*/ Model model) {		
			/*Pageable pageRequest = PageRequest.of(page, 4);
			Page<Paciente> pacientes = servicio.finAll(pageRequest);
			PageRender<Paciente> pageRender = new PageRender<>("/pacientes", pacientes);
			model.addAttribute("pacientes", pacientes);
			model.addAttribute("page", pageRender);*/
		List<Paciente> pacientes = servicio.finAllOrderByIdDesc();
		model.addAttribute("pacientes", pacientes);
		return "listarPacientes";
	}
	
	@GetMapping("/nuevo")
	public String nuevoPaciente(Model model) {
		PacienteDto pacienteDto = new PacienteDto();
		model.addAttribute("pacienteDto", pacienteDto);
		return "pacientes/formularioPaciente";
	}
	
	@GetMapping("/editar/{id}")
	public String actualizarPaciente(@PathVariable(value="id") Long id, Model model) {
		Paciente paciente = servicio.findOne(id);
		Historia historia = new Historia();
		historia.setPaciente(paciente);
		PacienteDto pacienteDto = new PacienteDto();
		if(id > 0) {
		pacienteDto.setId(paciente.getId());
		pacienteDto.setNombre(paciente.getNombre() + " ");
		pacienteDto.setApellidos(" "+ paciente.getApellidos());
		pacienteDto.setDireccion(paciente.getDireccion());
		pacienteDto.setPeso(paciente.getPeso());
		pacienteDto.setAltura(paciente.getAltura());
		pacienteDto.setFecha(paciente.getFecha());
		pacienteDto.setEdad(paciente.getEdad());
		pacienteDto.setTalla(paciente.getTalla());
		pacienteDto.setImc(paciente.getImc());
		pacienteDto.setConsulta(historia.getConsulta());
		}
		model.addAttribute("pacienteDto", pacienteDto);
		return "pacientes/formularioPaciente";
	}
	
	@PostMapping("/guardarPaciente")
	public String guardarPaciente(PacienteDto pacienteDto, SessionStatus status) {
		Paciente paciente = new Paciente();
		if(pacienteDto != null) {
			paciente.setId(pacienteDto.getId());
			paciente.setNombre(pacienteDto.getNombre());
			paciente.setApellidos(pacienteDto.getApellidos());
			paciente.setDireccion(pacienteDto.getDireccion());
			paciente.setPeso(pacienteDto.getPeso());
			paciente.setAltura(pacienteDto.getAltura());
			paciente.setFecha(pacienteDto.getFecha());
			paciente.setEdad(pacienteDto.getEdad());
			paciente.setTalla(pacienteDto.getTalla());
			paciente.setImc(pacienteDto.getImc());
			servicio.save(paciente);
			status.setComplete();
		}
		paciente.setNombre(pacienteDto.getNombre()+"");
		paciente.setApellidos(pacienteDto.getApellidos());
		paciente.setDireccion(pacienteDto.getDireccion());
		paciente.setPeso(pacienteDto.getPeso());
		paciente.setAltura(pacienteDto.getAltura());
		paciente.setFecha(pacienteDto.getFecha());
		paciente.setEdad(pacienteDto.getEdad());
		paciente.setTalla(pacienteDto.getTalla());
		Historia historia = new Historia(pacienteDto.getConsulta(), paciente);
		System.out.println(pacienteDto.getConsulta());
		paciente.agregarHistoria(historia);
		servicio.save(paciente);
		status.setComplete();
		
		return "redirect:pacientes";
	}

	@GetMapping("/eliminarPaciente/{id}")
	public String eliminarPaciente(@PathVariable(value="id") Long id) {
		if(id > 0) {
			servicio.delete(id);
		}
		return "redirect:/pacientes";
	}
	
	
	@GetMapping(value = "/cargar-pacientes/{term}", produces = { "application/json" })
	public @ResponseBody List<Paciente> buscarPaciente(@PathVariable String term, Model model){
		return servicio.findByNombre(term);
		
	}
	
}
