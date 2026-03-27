package com.rays.ctl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.MarksheetDTO;
import com.rays.form.MarksheetForm;
import com.rays.service.MarksheetServiceInt;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Jasper functionality Controller. Performs operation for Print pdf of
 * MarksheetMeriteList
 *
 * @author Mayank mishra
 */
/**
 * The Class JasperCtl.
 *
 * @author krati
 */

@RestController
@RequestMapping(value = "Jasper")
public class JasperCtl extends BaseCtl<MarksheetDTO, MarksheetServiceInt, MarksheetForm> {

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Display.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws JRException  the JR exception
	 * @throws SQLException the SQL exception
	 * @throws IOException  Signals that an I/O exception has occurred.
	 */
	@GetMapping(value = "/report", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void display(HttpServletRequest request, HttpServletResponse response)
			throws JRException, SQLException, IOException {

		System.out.println("*** Jasper Ctl ***");

		ORSResponse res = new ORSResponse();

		ResourceBundle rb = ResourceBundle.getBundle("application");

		Connection con = null;

		java.io.InputStream jrxmlStream = getClass().getClassLoader().getResourceAsStream("report10.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

//      JasperReport jasperReport = JasperCompileManager.compileReport(rb.getString("jasper"));

		Map<String, Object> map = new HashMap<String, Object>();

		Session session = (Session) entityManager.unwrap(Session.class);

		con = ((SessionImpl) session).connection();

//      con = ((SessionImpl) entityManager.unwrap(Session.class)).connection();

//      Report fill karo
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, con);

//      PDF generate karo
		byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

//      Browser me show karo
		response.setContentType("application/pdf");

		response.getOutputStream().write(pdf);

		response.getOutputStream().flush();

		System.out.println("Report Generated Successfully");
	}
}