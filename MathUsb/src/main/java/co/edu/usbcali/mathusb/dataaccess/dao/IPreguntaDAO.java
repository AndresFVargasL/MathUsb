package co.edu.usbcali.mathusb.dataaccess.dao;

import java.util.List;

import co.edu.usbcali.mathusb.dataaccess.api.Dao;
import co.edu.usbcali.mathusb.modelo.Pregunta;


/**
* Interface for   PreguntaDAO.
*
*/
public interface IPreguntaDAO extends Dao<Pregunta, Long> {
	
	public List<Pregunta> consultarPreguntaDadoIdEval(Long evalId);
	
	public List<Pregunta> consultarPreguntasDadoIdTema(Long temaId);
}
