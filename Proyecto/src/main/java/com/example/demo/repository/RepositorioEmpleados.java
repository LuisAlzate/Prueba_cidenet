package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Empleado;

@Repository
public interface RepositorioEmpleados extends  JpaRepository<Empleado,Long>{
	
	@Query("SELECT p FROM Empleado p WHERE p.primerNombre LIKE %?1%"
			+ "OR  p.segundoNombre LIKE %?1%"
			+ "OR  p.primerApellido LIKE %?1%"
			+ "OR  p.segundoApellido LIKE %?1%"
			+ "OR  p.identificacion LIKE %?1%"
			+ "OR  p.numeroIdenti LIKE %?1%"
			+ "OR  p.paisEmpleo LIKE %?1%"
			+ "OR  p.correo LIKE %?1%"
			+ "OR  p.estado LIKE %?1%")
	public Page<Empleado> findAll(String keyword, Pageable pageable);

}
