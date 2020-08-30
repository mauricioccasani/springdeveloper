package mauricio.ccasani.examenjavaspring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mauricio.ccasani.examenjavaspring.entity.Doctor;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.DoctorService;

@Service
public class DoctorSecurityService implements UserDetailsService {
	@Autowired
	private DoctorService doctorService;

	public DoctorSecurityService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Doctor doctor=null;
		try {
			doctor = this.doctorService.obtenerDoctorXdni(username);
		} catch (ExceptionService e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//obtenerCliente(username);//clienteService.obtenerCliente(username);

		Collection<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(doctor.getRol()));

		UserDetails userDetails = new User(doctor.getDni(), doctor.getClave(), roles);

		return userDetails;
	}

}
