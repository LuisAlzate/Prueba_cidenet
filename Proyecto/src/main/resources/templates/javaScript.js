//Generar nuevo correo de manera automatica
let usrPrimerNombre = document.getElementById('PrimerNombre');
let usrPrimerApellido = document.getElementById('PrimerApellido');
let usrPais = document.getElementById('Pais');
let correoJs = 0;

const resultado = document.getElementById('salidaC');

function crearCorreo(Nombre,Apellido,Pais){
	const nom = Nombre;
	const ape= Apellido;
	const pais = Pais;
	
	if (pais == "Colombia"){
	
		correoJs = nom +ape+"@cidenet.com.co";
	}else
	{
		correoJs = nom +ape+"@cidenet.com.us";
	}
	
 
   document.getElementById('resultadoC') = correoJs;
	
}

crearCorreo(usrPrimerNombre,usrPrimerApellido,usrPais);