package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.Empleado;

public interface ServicioEmpleado {
	
	List<Empleado> getAllEmpleado();
	void guardarEmpleado(Empleado empleado);
	void guardarEmpleado2(Empleado empleado);
	Empleado getEmpleadoporId(long id);
	void deleteEmpleadoById(long id);
	Page<Empleado> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);
	
	
}
