package mauricio.ccasani.examenjavaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mauricio.ccasani.examenjavaspring.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

}
