package mauricio.ccasani.examenjavaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mauricio.ccasani.examenjavaspring.entity.ConsultaMedica;
@Repository
public interface ConsultaMedicaRepository extends JpaRepository<ConsultaMedica,Integer> {

}
