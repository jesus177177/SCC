package com.sistemacontrolclinico.web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistemacontrolclinico.web.app.models.dto.PacienteDto;
import com.sistemacontrolclinico.web.app.models.entity.Historia;
import com.sistemacontrolclinico.web.app.models.entity.Paciente;
import com.sistemacontrolclinico.web.app.models.service.IHistoriaService;
import com.sistemacontrolclinico.web.app.models.service.IPacienteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Secured("ROLE_ADMIN")
@Controller
public class HistoriaController {

	@Autowired
	private IHistoriaService servicio;
	
	@Autowired
	private IPacienteService servicioPaciente;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/verHistorias/{id}")
	public String verHistorias(@PathVariable("id") Long id, Model model) {
		Paciente paciente = servicioPaciente.findOne(id);
		model.addAttribute("paciente", paciente);
		model.addAttribute("historias", servicioPaciente.findByPaciente(paciente));
		return "pacientes/historias";
	}
	
	@GetMapping("/ver/{id}")
	public String verHistoria(@PathVariable("id") Long id, Model model) {		
			Historia historiaEncontrada = servicio.finById(id);
			model.addAttribute("historia", historiaEncontrada);	
		return "pacientes/verHistoria";
	}
	
	@GetMapping("/nuevaHistoria/{id}")
	public String nuevaHistoria(@PathVariable("id") Long id, Model model) {
		Paciente paciente = servicioPaciente.findOne(id);
		System.out.println(paciente.getPeso()+" ::"+paciente.getNombre());
		Historia historia = new Historia();
		historia.setPaciente(paciente);
		System.out.println(historia.getPaciente().getNombre());
		System.out.println(historia.getConsulta());
		model.addAttribute("historia", historia);
		return "pacientes/nuevaHistoria";
	}
	
	@PostMapping("/nuevaHistoria")
	public String guardarHistoria(Historia historia) {
		Paciente paciente = historia.getPaciente();
		paciente.agregarHistoria(historia);
		servicio.save(historia);
		return "redirect:/pacientes";
	}
	
	@GetMapping("/eliminarhistoria/{id}")
	public String eliminarHistoria(@PathVariable("id") Long id) {
		servicio.delete(id);
		return "redirect:historias";
	}
	
}
