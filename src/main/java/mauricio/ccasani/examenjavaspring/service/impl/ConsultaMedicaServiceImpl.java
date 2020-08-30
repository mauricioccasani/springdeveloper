package mauricio.ccasani.examenjavaspring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mauricio.ccasani.examenjavaspring.entity.ConsultaMedica;
import mauricio.ccasani.examenjavaspring.repository.ConsultaMedicaRepository;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.ConsultaMedicaService;
@Service
@Transactional
public class ConsultaMedicaServiceImpl implements ConsultaMedicaService {
	@Autowired
	private ConsultaMedicaRepository consultaMedicaRepository;

	@Override
	public List<ConsultaMedica> findAll() throws ExceptionService {
		return this.getConsultaMedicaRepository().findAll();//consultaMedicaRepository.findAll();
	}

	@Override
	public ConsultaMedica save(ConsultaMedica t) throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getConsultaMedicaRepository().save(t);
	}

	@Override
	public Optional<ConsultaMedica> findById(Integer id) throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getConsultaMedicaRepository().findById(id);
	}

	@Override
	public void deleteById(Integer id) throws ExceptionService {
		this.getConsultaMedicaRepository().deleteById(id);

	}

	public ConsultaMedicaRepository getConsultaMedicaRepository() {
		return consultaMedicaRepository;
	}

}
