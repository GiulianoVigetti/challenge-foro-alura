package com.alura.foro.controller;



import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.foro.topico.DatosActualizarTopico;
import com.alura.foro.topico.DatosListadoTopico;
import com.alura.foro.topico.DatosRegistroTopico;
import com.alura.foro.topico.Topico;
import com.alura.foro.topico.TopicoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@PostMapping
	public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
		if(topicoRepository.existsByTituloOrMensaje(datosRegistroTopico.titulo(),datosRegistroTopico.mensaje())) {
			throw new IllegalArgumentException("Ya existe un topico con el mismo titulo o mensaje");
		}
		topicoRepository.save(new Topico(datosRegistroTopico));
	}
	
	@GetMapping
	public Page<DatosListadoTopico> listaTopicos(@PageableDefault(size = 10) Pageable paginacion){
		return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
	}
	
	@GetMapping("/{id}")
	public Page<Topico> obtenerTopicoPorId(@PathVariable Long id, @PageableDefault(size = 1) Pageable paginacion) {
	    Optional<Topico> topico = topicoRepository.findById(id);

	    if (topico.isPresent()) {
	        List<Topico> topicoList = Collections.singletonList(topico.get());
	        return new PageImpl<>(topicoList, paginacion, 1);
	    } else {
	        return Page.empty();
	    }
	}
	
	
	@PutMapping
	@Transactional
	public void actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
		Topico topico  = topicoRepository.getReferenceById(datosActualizarTopico.id());
		topico.actualizarDatos(datosActualizarTopico);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void eliminarTopico(@PathVariable Long id) {
		Topico topico  = topicoRepository.getReferenceById(id);
		topicoRepository.delete(topico);
	}
}
