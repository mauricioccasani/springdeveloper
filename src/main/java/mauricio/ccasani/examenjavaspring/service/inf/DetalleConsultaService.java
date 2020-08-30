package mauricio.ccasani.examenjavaspring.service.inf;

import java.util.List;

import mauricio.ccasani.examenjavaspring.entity.DetalleConsulta;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;

public interface DetalleConsultaService extends GenericoService<DetalleConsulta, Integer> {
	public  List<DetalleConsulta>buscarDiagnostico(String diagnostico)throws ExceptionService;
	public  List<DetalleConsulta>findByLike(DetalleConsulta detalleConsulta)throws ExceptionService;
}
