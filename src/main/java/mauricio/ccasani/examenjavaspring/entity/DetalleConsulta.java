package mauricio.ccasani.examenjavaspring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "consulta_detalle")
public class DetalleConsulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	private String diagnostico = "";
	@NotEmpty
	private String tratamiento;
	@ManyToOne
	@JoinColumn(name = "id_consulta_medica")
	private ConsultaMedica consultaMedica;

}
