package mauricio.ccasani.examenjavaspring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mauricio.ccasani.examenjavaspring.entity.Paciente;
import mauricio.ccasani.examenjavaspring.repository.PacienteRepository;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.PacienteService;
@Service
public class PacienteServiceImpl implements PacienteService {
	@Autowired
	private PacienteRepository pacienteRepository;

	public PacienteServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Paciente> findAll() throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getPacienteRepository().findAll();
	}

	@Override
	public Paciente save(Paciente t) throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getPacienteRepository().save(t);
	}

	@Override
	public Optional<Paciente> findById(Integer id) throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getPacienteRepository().findById(id);
	}

	@Override
	public void deleteById(Integer id) throws ExceptionService {
		this.getPacienteRepository().deleteById(id);
	}

	public PacienteRepository getPacienteRepository() {
		return pacienteRepository;
	}

}
