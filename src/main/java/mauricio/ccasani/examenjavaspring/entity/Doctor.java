package mauricio.ccasani.examenjavaspring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity

public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// [^abc] negacion
	@NotBlank // [a-zA-Z0-9\\s]+
	@Pattern(regexp = "[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")

	private String nombres;

	@NotBlank
	@Size(min = 3, message = "El apellido debe tener maximo 3 digitos ")
	@Pattern(regexp = "[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")
	private String apellidos;

	@NotBlank
	@Pattern(regexp = "\\d+")
	@Size(min = 8, message = "Campo dni es igual a 8 dígitos")
	private String dni;

	@NotBlank
	//@Pattern(regexp = "[a-zA-Z\\s]+")
	private String clave;

	@NotBlank
	private String rol;

	@ManyToOne
	@JoinColumn(name = "id_especialidad", updatable = false)
	@NotNull
	//@Valid
	//@Pattern(regexp = "[a-zA-Z\\s]+")
	private Especialidad especialidad;

}
