package mauricio.ccasani.examenjavaspring.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class HistorialClinica {
	//private Integer id;
	private String nombres;
	private String apellidos;
	private String diagnostico;
	private String tratamiento;

}
