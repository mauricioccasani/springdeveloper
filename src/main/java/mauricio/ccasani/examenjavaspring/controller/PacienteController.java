package mauricio.ccasani.examenjavaspring.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import mauricio.ccasani.examenjavaspring.controller.DoctorController.EliminarResponse;
import mauricio.ccasani.examenjavaspring.entity.Doctor;
import mauricio.ccasani.examenjavaspring.entity.Especialidad;
import mauricio.ccasani.examenjavaspring.entity.Paciente;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.IUploadFileService;
import mauricio.ccasani.examenjavaspring.service.inf.PacienteService;

@Controller
//@RequestMapping("/pacientes")
@SessionAttributes("paciente")
public class PacienteController {
	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private HttpSession session;


	@GetMapping("/listar")
	public String name(Model model) throws ExceptionService {
		// Doctor doctor=(Doctor) this.session.getAttribute("doctorLogin");
		List<Paciente> pacientes = this.getPacienteService().findAll();
		model.addAttribute("pacientes", pacientes);
		return "views/paciente/listar_paciente";
	}

	@GetMapping("/nuevo")
	public String nueva(ModelMap model) throws ExceptionService {
		model.put("paciente", new Paciente());
		return "views/paciente/registrar_paciente";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid @ModelAttribute("paciente") Paciente paciente, BindingResult result, Model model,
			 RedirectAttributes flash)
			throws ExceptionService {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Paciente");
			return "views/paciente/registrar_paciente";
		}

		String mensajeFlash = (paciente.getId() != null) ? "Paciente editado con éxito!" : "Paciente creado con éxito!";
		this.getPacienteService().save(paciente);
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		try {
			Optional<Paciente> optional = this.getPacienteService().findById(id);// getDoctorService().findById(id);//
																					// getProductoService().findById(id);
			model.addAttribute("titulo", " Actualizar Paciente");
			if (optional.isPresent()) {
				model.addAttribute("paciente", optional.get());
			} else {
				return "redirect:/listar";
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", " Paciente no encontrado " + e.getMessage());
		}
		return "views/paciente/registrar_paciente";
	}

	@PostMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<EliminarResponse> eliminarMedico(@PathVariable(value = "id") Integer id,
			RedirectAttributes flash) throws ExceptionService {
		Optional<Paciente> row = this.getPacienteService().findById(id);
		ResponseEntity<EliminarResponse> response = null;
		if (row.isPresent()) {
			
			this.getPacienteService().deleteById(id);

			response = new ResponseEntity<EliminarResponse>(new EliminarResponse("Se eliminó correctamente"),
					HttpStatus.OK);
		} else {
			response = new ResponseEntity<EliminarResponse>(new EliminarResponse("No se eliminó"),
					HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	@Data
	@AllArgsConstructor
	class EliminarResponse {
		private String message;
	}
}
