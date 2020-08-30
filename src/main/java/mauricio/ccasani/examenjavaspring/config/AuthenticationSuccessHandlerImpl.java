package mauricio.ccasani.examenjavaspring.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import mauricio.ccasani.examenjavaspring.entity.Doctor;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.DoctorService;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private HttpSession session;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	enum ROLES {
		ADMIN, USER;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String username = ((User) authentication.getPrincipal()).getUsername();

		Doctor doctor = null;
		try {
			doctor = this.doctorService.obtenerDoctorXdni(username);
		} catch (ExceptionService e) {
			e.printStackTrace();
		} 

		if (doctor != null) {
			session.setAttribute("doctorLogin", doctor);
		}

		String url = "/inicio";
		/*
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			String authorityName = authority.getAuthority();
			if (ROLES.ADMIN == ROLES.valueOf(authorityName)) {
				url = "/inicio";
				break;
			} else if (ROLES.USER == ROLES.valueOf(authorityName)) {
				url = "/doctor/list";
				break;
			}
		}
		*/
		//redirectStrategy.sendRedirect(request, response, url);
		redirectStrategy.sendRedirect(request, response, url);
	}

}
