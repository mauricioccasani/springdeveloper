package mauricio.ccasani.examenjavaspring.service.inf;

import java.util.List;
import java.util.Optional;

import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;


public interface GenericoService <T, ID> {
	List<T> findAll() throws ExceptionService;

	T save(T t) throws ExceptionService;

	// T update( T t ) throws ExceptionService;
	Optional<T> findById(ID id) throws ExceptionService;

	void deleteById(ID id) throws ExceptionService;

	//void deleteAll() throws ExceptionService;

}
