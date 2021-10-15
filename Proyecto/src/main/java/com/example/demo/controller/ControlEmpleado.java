package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Empleado;
import com.example.demo.service.ServicioEmpleado;

@Controller
public class ControlEmpleado {

	@Autowired
	private ServicioEmpleado servicioempleados;

	// Muestra la lista de empleados.
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listaEmpleados", servicioempleados.getAllEmpleado());
		return "index";
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		
		return "new_empleado";
	}
	
	@PostMapping("/guardarEmpleado")
	public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
		//Guardo empleado a la base de datos
		servicioempleados.guardarEmpleado(empleado);
		return "redirect:/";
	}

	
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") long id, Model model) {
		//Obtengo los valores de los empleados desde el ServicioEmpleados
		Empleado empleado = servicioempleados.getEmpleadoporId(id);
		
		//Se prellena el formulario con el modelo
		model.addAttribute("empleado",empleado);
		return "update_empleado";
		
	}
}