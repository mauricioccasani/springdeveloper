package mauricio.ccasani.examenjavaspring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mauricio.ccasani.examenjavaspring.entity.Doctor;
import mauricio.ccasani.examenjavaspring.repository.DoctorRepository;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.DoctorService;
@Service
public class DoctorServiceImpl implements DoctorService{
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public List<Doctor> findAll() throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getDoctorRepository().findAll();
	}

	@Override
	public Doctor save(Doctor t) throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getDoctorRepository().save(t);
	}

	@Override
	public Optional<Doctor> findById(Integer id) throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getDoctorRepository().findById(id);
	}

	@Override
	public void deleteById(Integer id) throws ExceptionService {
		// TODO Auto-generated method stub
		this.getDoctorRepository().deleteById(id);
	}

	public DoctorRepository getDoctorRepository() {
		return doctorRepository;
	}

	@Override
	public Doctor obtenerDoctorXdni(String documento) throws ExceptionService {
		// TODO Auto-generated method stub
		return this.getDoctorRepository().findByDni(documento);
	}

	

	

}
