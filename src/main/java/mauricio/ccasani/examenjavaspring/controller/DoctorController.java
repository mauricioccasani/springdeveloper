package mauricio.ccasani.examenjavaspring.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
import mauricio.ccasani.examenjavaspring.entity.Doctor;
import mauricio.ccasani.examenjavaspring.entity.Especialidad;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.DoctorService;
import mauricio.ccasani.examenjavaspring.service.inf.EspecialidadService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private BCryptPasswordEncoder bc;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private EspecialidadService especialidadService;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private HttpSession session;

	@GetMapping("/listar")
	public String listar(Model model)  {
		List<Doctor> doctors=null;
		try {
			doctors = this.getDoctorService().findAll();
		} catch (ExceptionService e) {
			model.addAttribute("error", "Error: "+e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("doctores", doctors);
		return "views/doctor/listar_doctor";
	}

	@GetMapping("/nuevo")
	public String nueva(ModelMap model) throws ExceptionService {
		List<Especialidad> especialidads = this.getEspecialidadService().findAll();
		model.put("especialidad", especialidads);
		// model.put("opcionMenu", "tarjeta-nuevo");
		model.put("doctor", new Doctor());

		return "views/doctor/registrar_doctor";
	}

	@PostMapping("/registrar")
	public String registrar(@Valid @ModelAttribute("doctor") Doctor doctor, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, ModelMap model) throws ExceptionService {
		List<Especialidad> especialidads = this.getEspecialidadService().findAll();

		if (bindingResult.hasErrors()) {
			model.put("especialidad",especialidads);
			return  "views/doctor/registrar_doctor";
		}
		
		try {
			model.put("especialidad",especialidads);
			Doctor doc=(Doctor) this.session.getAttribute("doctorLogin");
			String n=doc.getNombres();
			System.out.println("Nombre "+n);
			System.out.println("SESSIO: "+doc);
			
			String clave=doctor.getClave();
			doctor.setClave(bc.encode(clave));
			this.getDoctorService().save(doctor);
			
			 redirectAttributes.addFlashAttribute("success", " Medico guardado con éxito");
			return "redirect:/doctor/listar";
		} catch (Exception e) {	
			// redirectAttributes.addFlashAttribute("error", " Producto no guardado ");
			e.printStackTrace();
		}
		return "views/doctor/registrar_doctor";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		try {
			Optional<Doctor> optional = this.getDoctorService().findById(id);
			List<Especialidad> list = this.getEspecialidadService().findAll();
			model.addAttribute("especialidad", list);
			model.addAttribute("titulo", " Actualizar Médico");
			if (optional.isPresent()) {
				model.addAttribute("doctor", optional.get());
			} else {
				return "redirect:/doctor/listar";
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", " Medico no guardado " + e.getMessage());
		}
		return "views/doctor/registrar_doctor";
	}

	@PostMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<EliminarResponse> eliminarConsultaMedica(@PathVariable(value = "id") Integer id) throws ExceptionService {
		Optional<Doctor>row=this.getDoctorService().findById(id);
		ResponseEntity<EliminarResponse> response = null;
		if (row.isPresent()) {
			this.getDoctorService().deleteById(id);
			response = new ResponseEntity<EliminarResponse>(new EliminarResponse("Se eliminó correctamente"),
					HttpStatus.OK);
		} else {
			response = new ResponseEntity<EliminarResponse>(new EliminarResponse("No se eliminó"),
					HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	public DoctorService getDoctorService() {
		return doctorService;
	}

	public EspecialidadService getEspecialidadService() {
		return especialidadService;
	}

	@Data
	@AllArgsConstructor
	class EliminarResponse {
		private String message;
	}

}
