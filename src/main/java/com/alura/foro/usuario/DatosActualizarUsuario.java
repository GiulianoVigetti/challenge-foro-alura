package com.alura.foro.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(@NotNull Long id, @NotBlank String password,
		@NotBlank String nuevoPassword) {

}
