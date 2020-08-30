package mauricio.ccasani.examenjavaspring.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mauricio.ccasani.examenjavaspring.entity.DetalleConsulta;

@Repository
public interface DetalleConsultaRepository extends JpaRepository<DetalleConsulta,Integer>{
	public  List<DetalleConsulta>findByDiagnosticoLikeIgnoreCase(String diagnostico);
	
	@Query("SELECT d FROM DetalleConsulta d WHERE d.diagnostico LIKE :diagnostico ORDER BY id desc")
	public  List<DetalleConsulta>findByLike(@Param("diagnostico") String diagnostico);
	
	

}
