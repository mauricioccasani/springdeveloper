package mauricio.ccasani.examenjavaspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig  implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/inicio").setViewName("views/inicio/inicio");
		registry.addViewController("/noAutorizado").setViewName("views/error/403");
		//registry.addViewController("/notFound").setViewName("error/404");
		//registry.addViewController("/badRequest").setViewName("views/error/400");
	}
	

}
