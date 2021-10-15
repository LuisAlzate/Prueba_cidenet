package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Empleado;
import com.example.demo.repository.RepositorioEmpleados;

@Service

public class ServicioEmpleadolmpl implements ServicioEmpleado{

	@Autowired
	private RepositorioEmpleados repositorioempleados;
	
	@Override
	public List<Empleado> getAllEmpleado() {
		return repositorioempleados.findAll();
	}
	
	@Override
	public void guardarEmpleado(Empleado empleado) {
		this.repositorioempleados.save(empleado);
	}

	@Override
	public Empleado getEmpleadoporId(long id) {
		Optional < Empleado > optional = repositorioempleados.findById(id);
		Empleado empleado = null;
		if(optional.isPresent()) {
			empleado = optional.get();
		}else {
			throw new RuntimeException("Empleado no encontrado por id ::" + id);
		}
		return empleado;
				
	}

}