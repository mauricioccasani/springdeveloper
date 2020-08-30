package mauricio.ccasani.examenjavaspring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import mauricio.ccasani.examenjavaspring.dto.HistorialClinica;
import mauricio.ccasani.examenjavaspring.entity.Doctor;
import mauricio.ccasani.examenjavaspring.entity.Especialidad;
import mauricio.ccasani.examenjavaspring.repository.HistorialClinicaDAO;
import mauricio.ccasani.examenjavaspring.service.inf.DoctorService;
import mauricio.ccasani.examenjavaspring.service.inf.EspecialidadService;

@SpringBootApplication
// implements CommandLineRunner
public class ExamenspringwebApplication {
	/*
	BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	EspecialidadService especialidadService;
	*/
	//@Autowired
	//HistorialClinicaDAO clinicaDAO;
	public static void main(String[] args) {
		SpringApplication.run(ExamenspringwebApplication.class, args);
	}
	/*
	@Override
	public void run(String... args) throws Exception {
		
		Especialidad especialidad=new Especialidad();
		//especialidad.setNombre("Traumatología");
		//this.especialidadService.save(especialidad);
		
		//especialidad.setNombre("Administrador");
		//this.especialidadService.save(especialidad);
		
		Doctor doctor=new Doctor();
		doctor.setNombres("Santiago ");
		doctor.setApellidos("Mozo Ore");
		doctor.setDni("66666666");
		especialidad.setId(2);
		doctor.setEspecialidad(especialidad);
		doctor.setClave(this.bCryptPasswordEncoder.encode("Santiago"));
		doctor.setRol("ADMIN");
		this.doctorService.save(doctor);
	}
	*/
	/*
	@Override
	public void run(String... args) throws Exception {
		List<HistorialClinica>h=this.clinicaDAO.findAll();
		for (HistorialClinica historialClinica : h) {
			System.out.println(historialClinica);
		}
		
	}
	*/
}
