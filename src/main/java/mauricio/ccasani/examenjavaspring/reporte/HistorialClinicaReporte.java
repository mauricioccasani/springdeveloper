package mauricio.ccasani.examenjavaspring.reporte;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mauricio.ccasani.examenjavaspring.repository.HistorialClinicaDAO;
import mauricio.ccasani.examenjavaspring.service.exception.ExceptionService;
import mauricio.ccasani.examenjavaspring.service.inf.DoctorService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Component
@RequestMapping("/reporteHiCl")

public class HistorialClinicaReporte {

	//private static final String PATH = "src/main/resources/reportes";
	private static final String PATH = "src/main/resources/templates/views/reportes";
	@Autowired
	private HistorialClinicaDAO clinicaDAO;

	@GetMapping("/reporte01")
	public void reporte01(HttpServletResponse response, @RequestParam(value = "mode", required = false) String mode)
			throws JRException, IOException, ExceptionService {

		byte[] data = createPDF();

		strearmReport(response, data, "historial_clinca.pdf", mode);
	}

	private byte[] createPDF() throws JRException, ExceptionService {
		JasperReport report = JasperCompileManager.compileReport(PATH + "/historial_clinca.jrxml");

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("Titulo", "Historial Clinca");

		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(this.clinicaDAO.findAll());

		JasperPrint print = JasperFillManager.fillReport(report, parameters, source);

		return JasperExportManager.exportReportToPdf(print);
	}

	private void strearmReport(HttpServletResponse response, byte[] data, String name, String mode) throws IOException {

		if (mode != null && mode.equals("inline")) {
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename= " + name);
		} else {
			response.setContentType("application/x-download");
			response.setHeader("Content-disposition", "attachment; filename= " + name);
		}

		response.setContentLength(data.length);
		response.getOutputStream().write(data);
		response.getOutputStream().flush();
	}

}
