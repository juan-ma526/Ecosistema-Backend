package com.semillero.ecosistema.entidad;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Proveedor {
	public enum EstadoProveedor {
		REVISION_INICIAL,
        ACEPTADO,
        DENEGADO,
        REQUIERE_CAMBIOS,
        CAMBIOS_REALIZADOS
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "El nombre no puede estar en blanco")
	private String nombre;
	
	@NotBlank(message = "La descripcion no puede estar en blanco")
	@Size(max = 50, message = "La descripcion no puede tener mas de 50 caracteres")
	private String descripcion;
	
	@NotBlank(message = "El telefono no puede estar en blanco")
	private String telefono;
	
	@NotBlank(message = "El email no puede estar en blanco")
	@Email
	
	private String email;
	
	private String facebook;
	
	private String instagram;
	@ManyToOne
	@JoinColumn(name="pais_id")
	private Pais pais;
	
	@ManyToOne
	@JoinColumn(name="provincia_id")
	private Provincia provincia;
	
	@NotBlank(message = "La ciudad no puede estar en blanco")
	private String ciudad;
	
	@Enumerated(EnumType.STRING)
	private EstadoProveedor estado;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@NotNull
	@Column(columnDefinition = "boolean default false")
	private boolean deleted=false;
	
	private String feedback;
	
	
	private List<Imagen> imagenes;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	
}
