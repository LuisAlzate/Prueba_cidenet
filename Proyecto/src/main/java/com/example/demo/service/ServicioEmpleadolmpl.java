package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public void guardarEmpleado2(Empleado empleado) {
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
	
	@Override
	public void deleteEmpleadoById(long id) {
		this.repositorioempleados.deleteById(id);
	}

	@Override
	public Page<Empleado> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection,String keyword) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
			Sort.by(sortField).descending();
	
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize,sort);
		
		if(keyword != null) {
			return repositorioempleados.findAll(keyword,pageable);
		}
		return repositorioempleados.findAll(pageable);
	}
}
