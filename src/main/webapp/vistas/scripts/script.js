"use strict";
const menuPrincipal = document.querySelector(".btn-menu-principal");
const tipoUsuario = document.getElementById("tipoUsuario");
const adminViews = document.querySelectorAll(".admin-view");
const clientViews = document.querySelectorAll(".client-view");
const profViews = document.querySelectorAll(".prof-view");
const allViews = [adminViews,clientViews,profViews];

const btnEnviar = document.querySelector(".btn-enviar-form");
const extraField1 = document.querySelector(".extra-field1");
const extraField2 = document.querySelector(".extra-field2");
const extraField3 = document.querySelector(".extra-field3");
const btnsTipo = document.querySelectorAll(".btn-tipo");
const camposOpcionales = document.querySelectorAll(".opcional");
camposOpcionales.forEach(co => co.style.display = "none");

const rut = document.querySelector(".opt-rut");
const titulo = document.querySelector(".opt-titulo");
const area = document.querySelector(".opt-area");


//Formulario
btnEnviar.style.display = "none";
btnsTipo.forEach(c => {
	
	c.addEventListener("click", function() {
			btnEnviar.style.display = "block";
			if (c.textContent === "Cliente") {
				rut.style.display = "block";
				titulo.style.display = "none";
				area.style.display = "none";
				
				
			}
			if (c.textContent === "Profesional") {
				rut.style.display = "none";
				titulo.style.display = "block";
				area.style.display = "none";
			
			}
			if (c.textContent === "Administrativo") {
				rut.style.display = "none";
				titulo.style.display = "none";
				area.style.display = "block";
				
			}
		
	})
	
})

  //guardar valor formulario
  btnsTipo.forEach(bt => {
	  bt.addEventListener("change", function() {
		  document.getElementById("TipoUsuario").value = this.value;
	  })
  })


// magia admin->client->prof

const hideView = function(arr) {
	arr.forEach(e => {
		e.style.display = "none";
	})
}

const showView = function(arr) {
	arr.forEach(e => {
		e.style.display = "block";
	})
}


menuPrincipal.addEventListener("click", function () {
	
	if (tipoUsuario.textContent.valueOf().toUpperCase().trim() === "ADMINISTRATIVO") {
		console.log("work")
		hideView(clientViews);
		hideView(profViews);
		showView(adminViews)
	}
	if (tipoUsuario.textContent.valueOf().toUpperCase().trim() === "CLIENTE") {
		console.log("work")
		hideView(adminViews);
		hideView(profViews);
		showView(clientViews)
	}
	if (tipoUsuario.textContent.valueOf().toUpperCase().trim() === "PROFESIONAL") {
		console.log("work")
		hideView(clientViews);
		hideView(adminViews);
		showView(profViews)
	}
	
	
});

