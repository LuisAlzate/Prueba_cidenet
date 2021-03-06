package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Empleados")
public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="primer_Apellido")
	private String primerApellido;
	
	@Column(name="segundo_Apellido")
	private String segundoApellido;
	
	@Column(name="primero_Nombre")
	private String primerNombre;
	
	@Column(name="segundo_Nombre")
	private String segundoNombre;
	
	@Column(name="pais_Empleo")
	private String paisEmpleo;
	
	@Column(name="identificacion")
	private String identificacion;
	
	@Column(name="numeroIdenti")
	private String numeroIdenti;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="fecha_de_ingreso")
	private String fechaIn;
	
	@Column(name="area")
	private String area;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="fechaHora")
	private String fechaHora;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getPaisEmpleo() {
		return paisEmpleo;
	}
	public void setPaisEmpleo(String paisEmpleo) {
		this.paisEmpleo = paisEmpleo;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNumeroIdenti() {
		return numeroIdenti;
	}
	public void setNumeroIdenti(String numeroIdenti) {
		this.numeroIdenti = numeroIdenti;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getFechaIn() {
		return fechaIn;
	}
	public void setFechaIn(String fechaIn) {
		this.fechaIn = fechaIn;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	
}
