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
import mauricio.ccasani.examenjavaspring.entity.ConsultaMedica;
import mauricio.ccasani.examenjavaspring.entity.DetalleConsulta;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.ConsultaMedicaService;
import mauricio.ccasani.examenjavaspring.service.inf.DetalleConsultaService;

@Controller
@RequestMapping("/detalleConsulta")
public class DetalleConsultaController {
	@Autowired
	private DetalleConsultaService detalleConsultaService;

	@Autowired
	private ConsultaMedicaService consultaMedicaService;

	@GetMapping("/listar")
	public String listar( DetalleConsulta detalleConsultaBean, Model model) throws ExceptionService {
		List<DetalleConsulta> detalleConsultas;
		try {
			detalleConsultas = this.getDetalleConsultaService().findByLike(new DetalleConsulta());
			model.addAttribute("detalleConsulta", detalleConsultas);
		} catch (Exception e) {
			throw new ExceptionService(e);
		}

		return "views/detalle_consulta/listar_detalle_consulta";
	}
	@PostMapping("/buscar")
	public String buscar(DetalleConsulta detalleConsulta, Model model) throws Exception {
		List<DetalleConsulta> detalleConsultas;
		try {
			detalleConsultas = this.getDetalleConsultaService().findByLike(detalleConsulta);
			model.addAttribute("detalleConsulta", detalleConsultas);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return "views/detalle_consulta/listar_detalle_consulta";
	}

	@GetMapping("/nuevo")
	public String nueva(ModelMap model) throws ExceptionService {
		List<ConsultaMedica> consultaMedicas = this.getConsultaMedicaService().findAll();
		model.put("consultaMedicas", consultaMedicas);
		model.put("detalleConsulta", new DetalleConsulta());
		return "views/detalle_consulta/registrar_detalle_consulta";
	}

	@PostMapping("/registrar")
	public String registrar(@Valid @ModelAttribute("detalleConsulta") DetalleConsulta detalleConsulta,
			BindingResult bindingResult, HttpSession session, ModelMap model) throws ExceptionService {
		if (bindingResult.hasErrors()) {
			model.put("consultaMedicas", this.getConsultaMedicaService().findAll());
			return "views/detalle_consulta/registrar_detalle_consulta";
		}
		try {
			this.getDetalleConsultaService().save(detalleConsulta);
			// return "redirect:/doctor/listar";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/detalleConsulta/listar";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		try {
			Optional<DetalleConsulta> optional = this.getDetalleConsultaService().findById(id);// getProductoService().findById(id);
			List<ConsultaMedica> consultaMedicas = this.getConsultaMedicaService().findAll();
			model.addAttribute("consultaMedicas", consultaMedicas);
			model.addAttribute("titulo", " Actualizar Médico");
			if (optional.isPresent()) {
				model.addAttribute("detalleConsulta", optional.get());
			} else {
				return "redirect:/detalleConsulta/listar";
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", " Medico no guardado " + e.getMessage());
		}
		return "views/detalle_consulta/registrar_detalle_consulta";
	}

	@PostMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<EliminarResponse> eliminarConsultaMedica(@PathVariable(value = "id") Integer id)
			throws ExceptionService {
		Optional<DetalleConsulta> row = this.getDetalleConsultaService().findById(id);
		ResponseEntity<EliminarResponse> response = null;
		if (row.isPresent()) {
			this.getDetalleConsultaService().deleteById(id);
			response = new ResponseEntity<EliminarResponse>(new EliminarResponse("Se eliminó correctamente"),
					HttpStatus.OK);
		} else {
			response = new ResponseEntity<EliminarResponse>(new EliminarResponse("No se eliminó"),
					HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	public DetalleConsultaService getDetalleConsultaService() {
		return detalleConsultaService;
	}

	public ConsultaMedicaService getConsultaMedicaService() {
		return consultaMedicaService;
	}

	@Data
	@AllArgsConstructor
	class EliminarResponse {
		private String message;
	}
}
