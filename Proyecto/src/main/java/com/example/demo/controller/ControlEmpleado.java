package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.model.Empleado;
import com.example.demo.service.ServicioEmpleado;

@Controller
public class ControlEmpleado {

	@Autowired
	private ServicioEmpleado servicioempleados;

	// Muestra la lista de empleados.
	@GetMapping("/")
	public String viewHomePage(Model model) {
		String keyword=null;
		return findPaginated(1,"id","asc",keyword,model);
		
	}

	//Genereo la pagina para agregar un nuevo usuario y lo relaciono con los valores de la tabla en "Empleados"
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		
		return "new_empleado";
	}
	
	
	//Guardo empleado a la hora de crear uno nuevo, obtengo el correo y la hora de forma automatica.
	@PostMapping("/guardarEmpleado")
	public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
		//Guardo empleado a la base de datos
		
		//Declaro las variables con los valores necesarios para crear el correo electronico.
		String nombre = empleado.getPrimerNombre();
		String ape2 = empleado.getPrimerApellido();
		String ape = ape2.replaceAll("\\s+", "");
		String pais = empleado.getPaisEmpleo();
		String correoJs, correoJsTemp;
		
		//Obtengo la hora de registro con las librerias de fecha
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		
		//Pregenero el nombre del correo con el primer nombre y apellido.
		correoJs=nombre+ "." + ape;
		
		//Subo los valores a la tabla "empleado" para generar la id de manera automatica.
		servicioempleados.guardarEmpleado(empleado);

		//Cargo los valores de la fecha y el correo pregenerado.
		empleado.setFechaHora(strDate);
		empleado.setCorreo(correoJs);
		
		
		//Con la id generada se la cargo al correo.
		nombre=nombre.toLowerCase();
		ape=ape.toLowerCase();
		correoJs=nombre+ "." + ape+"."+empleado.getId();
		
		//Valido tama??o del correo. (Por ahora el valor del string no puede ser menor al valor para cortarlo, tira error 500)
//		if(correoJs.length()>300) {
//			correoJs=correoJs.substring(0,300);
//		}
//		else {
//			return correoJs;
//		}	
		
		//valido donde esta ubicado el empleado para generar el dominio correspondiente. 
		if (pais.equals("Colombia")) {

			correoJs = correoJs + "@cidenet.com.co";
		} else {
			correoJs = correoJs +"@cidenet.com.us";
		}
		//Le cargo el correo completo con domino a la columna "correo"
		empleado.setCorreo(correoJs);
		
		//Actualizo la tabla con el correo que incluye la Id.
		servicioempleados.guardarEmpleado(empleado);
		
		return "redirect:/";
	}

	
	//Guarto empleado a la hora de actualizar.
	@PostMapping("/guardarEmpleado2")
	public String guardarEmpleado2(@ModelAttribute("empleado") Empleado empleado) {
		//Guardo empleado a la base de datos
		String nombre = empleado.getPrimerNombre();
		String ape2 = empleado.getPrimerApellido();
		String ape = ape2.replaceAll("\\s+", "");
		String pais = empleado.getPaisEmpleo();
		String correoJs;
		
		//Actualizo el correo en caso de ser necesario.
		nombre=nombre.toLowerCase();
		ape=ape.toLowerCase();
		correoJs=nombre+ "." + ape+"."+empleado.getId();
		//Valido tama??o del correo. (Por ahora el valor del string no puede ser menor al valor para cortarlo, tira error 500)
//		if(correoJs.length()>10) {
//			correoJs=correoJs.substring(0,10);
//		}
//		else {
//			return correoJs;
//		}
		
		//valido donde esta ubicado el empleado para generar el dominio correspondiente. 
		if (pais.equals("Colombia")) {

			correoJs = correoJs + "@cidenet.com.co";
		} else {
			correoJs = correoJs +"@cidenet.com.us";
		}
		
		//Obtengo la hora de registro con las librerias de fecha
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		//Especifico que es una actualizacion
		strDate = "Actualizado el: "+dateFormat.format(date);
				
		
		empleado.setFechaHora(strDate);
		empleado.setCorreo(correoJs);
		servicioempleados.guardarEmpleado2(empleado);
				
		return "redirect:/";
		
	}
	
	//Genero la pagina para el formulario de actualizacion de un empleado ya creado.
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") long id, Model model) {
		
		
		//Obtengo los valores de los empleados desde el ServicioEmpleados
		Empleado empleado = servicioempleados.getEmpleadoporId(id);
		
		//Se prellena el formulario con el modelo
		model.addAttribute("empleado",empleado);
		return "update_empleado";
	}
	
	//Genero la opcion de poder eliminar el emepleado por su id.
	@GetMapping("/deleteEmpleado/{id}")	
	public String deleteEmpleado(@PathVariable (value = "id")long id) {
	
		//Borrar empleado
		
		this.servicioempleados.deleteEmpleadoById(id);
		return "redirect:/";
		
	}
	
	
	//Aplico la paginacion y el sort para la tabla de datos de un tama??o de 10 elementos por pagina.
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			@RequestParam("keyword")String keyword,
			Model model) {
		int pageSize =10;
		
		Page<Empleado> page = servicioempleados.findPaginated(pageNo, pageSize, sortField, sortDir,keyword);
		List<Empleado> listaEmpleados = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sorDir",sortDir);
		model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("keyword",keyword);
			
		
		model.addAttribute("listaEmpleados",listaEmpleados);
		
		return "index";
		
	}
	
}
