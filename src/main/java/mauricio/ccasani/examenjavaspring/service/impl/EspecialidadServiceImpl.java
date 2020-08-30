package mauricio.ccasani.examenjavaspring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mauricio.ccasani.examenjavaspring.entity.Especialidad;
import mauricio.ccasani.examenjavaspring.repository.EspecialidadRepository;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.EspecialidadService;

@Service
public class EspecialidadServiceImpl implements EspecialidadService{
	@Autowired
	private EspecialidadRepository especialidadRepository;

	@Override
	public List<Especialidad> findAll() throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getEspecialidadRepository().findAll();
	}

	@Override
	public Especialidad save(Especialidad t) throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getEspecialidadRepository().save(t);
	}

	@Override
	public Optional<Especialidad> findById(Integer id) throws ExceptionService {
		// TODO Auto-generated method stub
		return this.findById(id);
	}

	@Override
	public void deleteById(Integer id) throws ExceptionService {
		// TODO Auto-generated method stub
		this.getEspecialidadRepository().deleteById(id);
	}

	
	public EspecialidadRepository getEspecialidadRepository() {
		return especialidadRepository;
	}

	

}
