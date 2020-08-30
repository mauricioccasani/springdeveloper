package mauricio.ccasani.examenjavaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mauricio.ccasani.examenjavaspring.entity.Doctor;

@Repository
public interface DoctorRepository  extends JpaRepository<Doctor, Integer>{
	Doctor findByDni(String dni);
}
