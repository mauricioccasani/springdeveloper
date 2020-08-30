package mauricio.ccasani.examenjavaspring.service.inf;

import mauricio.ccasani.examenjavaspring.entity.Doctor;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;

public interface DoctorService extends GenericoService<Doctor,Integer>{
	public Doctor obtenerDoctorXdni(String documento)throws ExceptionService;
}
