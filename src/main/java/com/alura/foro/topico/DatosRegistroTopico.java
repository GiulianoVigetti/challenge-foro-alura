package com.alura.foro.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
		@NotBlank
		String titulo, 
		@NotBlank
		String mensaje, 
		@NotNull
		StatusTopico estatus,
		@NotBlank
		String autor, 
		@NotBlank
		String curso) {

}
