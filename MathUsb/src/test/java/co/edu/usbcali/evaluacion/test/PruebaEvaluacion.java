package co.edu.usbcali.evaluacion.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jfree.util.Log;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.mathusb.dataaccess.dao.IEvaluacionDAO;
import co.edu.usbcali.mathusb.exceptions.ZMessManager;
import co.edu.usbcali.mathusb.modelo.Evaluacion;
import co.edu.usbcali.mathusb.modelo.Grupo;
import co.edu.usbcali.mathusb.modelo.control.IAsignaturaLogic;
import co.edu.usbcali.mathusb.modelo.control.IEvaluacionLogic;
import co.edu.usbcali.mathusb.modelo.control.IGrupoLogic;
import co.edu.usbcali.mathusb.modelo.dto.EvaluacionDTO;
import co.edu.usbcali.mathusb.modelo.dto.GrupoDTO;
import co.edu.usbcali.mathusb.utilities.FacesUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional

public class PruebaEvaluacion {
	@Autowired
	IEvaluacionDAO evaluacionDAO;
	
	@Autowired
	IEvaluacionLogic evaluacionLogic;
	
	
	private static Logger logger = LoggerFactory.getLogger(PruebaEvaluacion.class);

	public PruebaEvaluacion() {
	}

	//@Test
	public void consultarEvaluacionesDelDocente() throws Exception {
		logger.info("Evaluaciones Docente id = 2");
		
		List<Evaluacion> listaEvaluaciones = evaluacionDAO.obtenerEvaluacionesDadoIdUsuario(2L);

		for (Evaluacion evaluacion : listaEvaluaciones) {
			logger.info("Evaluacion ID: "+evaluacion.getEvalId()+", Estado: "+evaluacion.getEstadoRegistro());
		}
		
		logger.info("Fin lista evaluaciones docente id = 2");
	}

	//@Test
	public void consultarEvaluacionesDelEstudiante() throws Exception {
		logger.info("Evaluaciones Estudiante id = 1");
		
		List<Evaluacion> listaEvaluaciones = evaluacionDAO.obtenerEvaluacionesDadoIdUsuario(1L);

		for (Evaluacion evaluacion : listaEvaluaciones) {
			logger.info("Evaluacion ID: "+evaluacion.getEvalId()+", Estado: "+evaluacion.getEstadoRegistro());
		}
		
		logger.info("Fin lista evaluaciones estudiante id = 1");
	}

	
	//@Test
	public void consultarEvaluacionesDelDocenteDesdeLogica() throws Exception {
		logger.info("Evaluacion desde Logic");
		
		List<EvaluacionDTO> listaEvaluaciones = evaluacionLogic.obtenerListaEvaluacionesDelDocente(2L);

		for (EvaluacionDTO evaluacion : listaEvaluaciones) {
			logger.info("Evaluacion ID: "+evaluacion.getEvalId()+", FechaCreación: "+evaluacion.getFechaCreacion()+", TiEv: "+evaluacion.getTipoEvaluacion());
		}
		
		logger.info("Fin lista evaluaciones docente id = 2");
	}
	
	@Test
	public void pruebaFechas(){
		try {
			Date fechaCualquiera;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			String fechaA = "22/05/2016";
			fechaCualquiera = simpleDateFormat.parse(fechaA);
			
			Date fechaHoy = new Date();
			
			String fechaString = simpleDateFormat.format(fechaHoy);
			fechaHoy = simpleDateFormat.parse(fechaString);
			
			if(fechaCualquiera.before(fechaHoy)){
				logger.info("La fecha del parcial no puede ser anterior a la fecha de hoy");
				logger.info("fechaHoy= "+fechaHoy+" fechaCualquiera: "+fechaCualquiera);
			}else{
				logger.info("No es anterior");
				logger.info("fechaHoy= "+fechaHoy+" fechaCualquiera: "+fechaCualquiera);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
