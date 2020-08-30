package mauricio.ccasani.examenjavaspring.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mauricio.ccasani.examenjavaspring.dto.HistorialClinica;

@Repository
public class HistorialClinicaDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	public List<HistorialClinica> findAll() {
		String sql="select pa.nombres,pa.apellidos,cd.diagnostico,cd.tratamiento from consulta_detalle cd INNER JOIN paciente pa on cd.id=pa.id  ";
		return jdbcTemplate.query(sql,
				(rs, rowNum) -> 
							new HistorialClinica(
									
									rs.getString("nombres"),
									rs.getString("apellidos"),
									rs.getString("diagnostico"),
									rs.getString("tratamiento")
									)
							);
	}

}
