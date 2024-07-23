package com.semillero.ecosistema.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semillero.ecosistema.entidad.Usuario;
import com.semillero.ecosistema.servicio.UsuarioServicioImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioControlador {

	@Autowired
	private UsuarioServicioImpl usuarioServicioImpl;
	
	@PostMapping
	public ResponseEntity<String> guardarUsuario(@Valid@RequestBody Usuario usuario) {
	        usuarioServicioImpl.guardar(usuario);
	        return ResponseEntity.ok("Usuario creado con éxito");
	    }
	

    @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/desactivar/{id}")
	public ResponseEntity<String> desactivarUsuario(@PathVariable Long id){
		boolean desactivado = usuarioServicioImpl.desactivarUsuario(id);

        if (desactivado==true) {
            return ResponseEntity.ok("El usuario se desactivó con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el usuario con el id proporcionado");
        }
	}
}

