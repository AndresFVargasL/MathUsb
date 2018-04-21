package co.edu.usbcali.mathusb.dataaccess.dao;

import java.util.List;

import co.edu.usbcali.mathusb.dataaccess.api.Dao;
import co.edu.usbcali.mathusb.modelo.Comentario;


/**
* Interface for   ComentarioDAO.
*
*/
public interface IComentarioDAO extends Dao<Comentario, Long> {
	
	public List<Comentario> listaPreguntasForoOrdenadosDelMasRecienteAlMasAntiguo();
	
	public List<Comentario> listaRespuestasForoAUnaPreguntaOrdenadosDelMasRecienteAlMasAntiguo(Long idPregunta);
	
	public Long cantidadDeRespuestasDeUnaPreguntaEnElForoDadoIdPregunta(Long idPregunta);
	
	public List<Comentario> listaPreguntasForoDadoIdUsuario(Long idUsuario);
}
