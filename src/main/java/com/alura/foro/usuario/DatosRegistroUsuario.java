package com.alura.foro.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
		@NotBlank
		String usuario,
		@NotBlank
		String password) {

}
