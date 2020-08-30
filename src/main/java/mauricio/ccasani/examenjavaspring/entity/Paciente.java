package mauricio.ccasani.examenjavaspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String nombres;
	@NotBlank
	private String apellidos;
	@NotBlank
	private String dni;
	@NotBlank
	@Column(name = "numero_historial_clinico")
	private String numeroHistorialClinico;
	
	//@Column(name = "foto")
	//private String foto;
	
}
