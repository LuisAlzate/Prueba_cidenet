package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Empleado;

public interface ServicioEmpleado {
	
	List<Empleado> getAllEmpleado();
	void guardarEmpleado(Empleado empleado);
	Empleado getEmpleadoporId(long id);
		
	
}