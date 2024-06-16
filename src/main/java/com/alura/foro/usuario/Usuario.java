package com.alura.foro.usuario;

import java.time.LocalDateTime;

import com.alura.foro.topico.StatusTopico;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="usuarios")
@Entity(name="Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String usuario;
	private String password;

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Usuario(@Valid DatosRegistroUsuario datosRegistroUsuario) {
		this.usuario = datosRegistroUsuario.usuario();
		this.password = datosRegistroUsuario.password();
	}

	public void actualizarDatos(@Valid DatosActualizarUsuario datosActualizarUsuario) {
		System.out.println(datosActualizarUsuario.password());
		System.out.println(this.password);
		if(this.password.equals(datosActualizarUsuario.password())) {
			this.password = datosActualizarUsuario.nuevoPassword();
		}else {
			throw new IllegalArgumentException("La contrasenia ingresada no es correcta");
		}
		
	}

	

}
