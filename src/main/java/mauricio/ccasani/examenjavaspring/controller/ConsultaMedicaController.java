package mauricio.ccasani.examenjavaspring.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import mauricio.ccasani.examenjavaspring.controller.DoctorController.EliminarResponse;
import mauricio.ccasani.examenjavaspring.entity.ConsultaMedica;
import mauricio.ccasani.examenjavaspring.entity.Doctor;
import mauricio.ccasani.examenjavaspring.entity.Especialidad;
import mauricio.ccasani.examenjavaspring.entity.Paciente;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.ConsultaMedicaService;
import mauricio.ccasani.examenjavaspring.service.inf.DoctorService;
import mauricio.ccasani.examenjavaspring.service.inf.PacienteService;

@Controller
@RequestMapping("/consultaMedica")
public class ConsultaMedicaController {
	@Autowired
	private ConsultaMedicaService consultaMedicaService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PacienteService pacienteService;

	@GetMapping("/listar")
	public String name(Model model) throws ExceptionService {
		List<ConsultaMedica> consultaMedicas = this.getConsultaMedicaService().findAll();
		//consultaMedicas.forEach(x -> System.out.println(x));
		model.addAttribute("consultaMedicas", consultaMedicas);
		return "views/consulta_medica/listar_consulta_medica";
	}

	@GetMapping("/nuevo")
	public String nuevaTarjeta(Model model) throws ExceptionService {
		List<Paciente> pacientes = this.getPacienteService().findAll();
		List<Doctor> doctors = this.getDoctorService().findAll();
		doctors.forEach(x -> System.out.println(x));
		model.addAttribute("pacientes", pacientes);
		model.addAttribute("doctors", doctors);
		model.addAttribute("consultaMedica", new ConsultaMedica());

		return "views/consulta_medica/registrar_consulta_medica";
	}

	@PostMapping("/registrar")
	public String registrar(@Valid @ModelAttribute("consultaMedica") ConsultaMedica consultaMedica,
			BindingResult bindingResult, ModelMap model) throws ExceptionService {
		// String resultPage = null;
		List<Paciente> list=this.pacienteService.findAll();
		List<Doctor>list2=this.doctorService.findAll();

		if (bindingResult.hasErrors()) {
			model.put("pacientes", list);
			model.put("doctors", list2);
			return "views/consulta_medica/registrar_consulta_medica";
		}
		try {

			this.getConsultaMedicaService().save(consultaMedica);
			return "redirect:/consultaMedica/listar";
		} catch (Exception e) {
			e.getMessage();
			// redirectAttributes.addFlashAttribute("error", " Producto no guardado ");
			e.printStackTrace();
		}
		return "views/consulta_medica/registrar_consulta_medica";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		try {
			Optional<ConsultaMedica> optional = this.getConsultaMedicaService().findById(id);// getDoctorService().findById(id);//
																								// getProductoService().findById(id);
			model.addAttribute("pacientes", this.getPacienteService().findAll());
			model.addAttribute("doctors", this.getDoctorService().findAll());
			model.addAttribute("titulo", " Actualizar Consulta Médica");
			if (optional.isPresent()) {
				
				model.addAttribute("consultaMedica", optional.get());
			} else {
				return "redirect:/consultaMedica/listar";
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", " Medico no guardado " + e.getMessage());
		}
		return "views/consulta_medica/registrar_consulta_medica";
	}

	@PostMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<EliminarResponse> eliminarMedico(@PathVariable(value = "id") Integer id)
			throws ExceptionService {
		// Integer row = tarjetaService.eliminarTarjeta(idTarjeta);
		Optional<ConsultaMedica> row = this.getConsultaMedicaService().findById(id);//this.getDoctorService().findById(id);
		ResponseEntity<EliminarResponse> response = null;
		if (row.isPresent()) {
			this.getConsultaMedicaService().deleteById(id);
			response = new ResponseEntity<EliminarResponse>(new EliminarResponse("Se eliminó correctamente"),
					HttpStatus.OK);
		} else {
			response = new ResponseEntity<EliminarResponse>(new EliminarResponse("No se eliminó"),
					HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	public ConsultaMedicaService getConsultaMedicaService() {
		return consultaMedicaService;
	}

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	public DoctorService getDoctorService() {
		return doctorService;
	}

	@Data
	@AllArgsConstructor
	class EliminarResponse {
		private String message;
	}

}
