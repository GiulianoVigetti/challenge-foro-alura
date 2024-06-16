package com.alura.foro.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByUsuario(String usuario);

}
