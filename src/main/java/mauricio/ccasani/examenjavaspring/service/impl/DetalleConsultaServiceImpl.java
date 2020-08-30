package mauricio.ccasani.examenjavaspring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mauricio.ccasani.examenjavaspring.entity.DetalleConsulta;
import mauricio.ccasani.examenjavaspring.repository.DetalleConsultaRepository;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.DetalleConsultaService;

@Service
public class DetalleConsultaServiceImpl implements DetalleConsultaService {
	@Autowired
	private DetalleConsultaRepository detalleConsultaRepository;

	@Override
	public List<DetalleConsulta> findAll() throws ExceptionService {
		return this.getDetalleConsultaRepository().findAll();
	}

	@Override
	public DetalleConsulta save(DetalleConsulta t) throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getDetalleConsultaRepository().save(t);
	}

	@Override
	public Optional<DetalleConsulta> findById(Integer id) throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getDetalleConsultaRepository().findById(id);
	}

	@Override
	public void deleteById(Integer id) throws ExceptionService {
		this.getDetalleConsultaRepository().deleteById(id);

	}

	@Override
	public List<DetalleConsulta> buscarDiagnostico(String diagnostico) throws ExceptionService {
		return this.getDetalleConsultaRepository().findByDiagnosticoLikeIgnoreCase("%" + diagnostico + "%");
	}

	@Override
	public List<DetalleConsulta> findByLike(DetalleConsulta detalleConsulta) throws ExceptionService {
		List<DetalleConsulta>consultas;
		String diagnostico=detalleConsulta.getDiagnostico();
		diagnostico= (diagnostico==null)?"":diagnostico;
		try {
			consultas=this.getDetalleConsultaRepository().findByLike("%"+diagnostico+"%");
			return consultas;
		} catch (Exception e) {
			throw new  ExceptionService(e.getMessage());
		}
	}

	public DetalleConsultaRepository getDetalleConsultaRepository() {
		return detalleConsultaRepository;
	}

}
