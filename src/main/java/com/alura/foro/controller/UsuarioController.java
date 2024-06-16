package com.alura.foro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.foro.topico.DatosActualizarTopico;
import com.alura.foro.topico.Topico;
import com.alura.foro.usuario.DatosActualizarUsuario;
import com.alura.foro.usuario.DatosRegistroUsuario;
import com.alura.foro.usuario.Usuario;
import com.alura.foro.usuario.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public void registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
		if(usuarioRepository.existsByUsuario(datosRegistroUsuario.usuario())) {
			throw new IllegalArgumentException("Ya existe un usuario con ese nombre");
		}
		usuarioRepository.save(new Usuario(datosRegistroUsuario));
	}
	
	@PutMapping
	@Transactional
	public void actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
		Usuario usuario  = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
		usuario.actualizarDatos(datosActualizarUsuario);
	}
	
}
