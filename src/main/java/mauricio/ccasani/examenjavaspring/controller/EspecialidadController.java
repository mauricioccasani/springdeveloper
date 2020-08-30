package mauricio.ccasani.examenjavaspring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mauricio.ccasani.examenjavaspring.entity.Especialidad;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.EspecialidadService;

@Controller
@RequestMapping("/especialidad")
public class EspecialidadController {
	@Autowired
	private EspecialidadService especialidadService;

	@GetMapping("/listar")
	public String name(Model model) throws ExceptionService {
		List<Especialidad> especialidads = this.getEspecialidadService().findAll();
		model.addAttribute("especialidades", especialidads);
		return "views/especialidad/listar_especialidad";
	}

	@GetMapping("/nuevo")
	public String nueva(ModelMap model) throws ExceptionService {
		model.put("especialidad", new Especialidad());
		return "views/especialidad/registrar_especialidad.html";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid @ModelAttribute("especialidad") Especialidad especialidad, BindingResult result, Model model,
			RedirectAttributes flash) throws ExceptionService {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Especialidad");
			return "views/especialidad/registrar_especialidad.html";
		}

		String mensajeFlash = (especialidad.getId() != null) ? "Paciente editado con éxito!" : "Paciente creado con éxito!";
		this.getEspecialidadService().save(especialidad);
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/especialidad/listar";
	}

	public EspecialidadService getEspecialidadService() {
		return especialidadService;
	}

}
