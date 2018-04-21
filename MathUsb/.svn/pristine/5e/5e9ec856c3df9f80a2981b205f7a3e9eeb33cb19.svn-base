package co.edu.usbcali.mathusb.dataaccess.dao;

import java.util.List;

import co.edu.usbcali.mathusb.dataaccess.api.Dao;
import co.edu.usbcali.mathusb.modelo.Evaluacion;
import co.edu.usbcali.mathusb.modelo.dto.EvaluacionReporteDTO;


/**
* Interface for   EvaluacionDAO.
*
*/
public interface IEvaluacionDAO extends Dao<Evaluacion, Long> {
	
	public List<Evaluacion> obtenerEvaluacionesDadoIdUsuario(Long idUsuario); //Método obtener lista evaluaciones cualquier usuario
	
	public List<Evaluacion> obtenerEvaluacionesDelGrupo(Long idGrupo);
	
	public List<Evaluacion> obtenerEvaluacionesDelGrupoDisponiblesEnFechaHoy(Long idGrupo) throws  Exception;
	
	public List<EvaluacionReporteDTO> consutaEvaluacionParaReporte(Long evalId) throws Exception;
 	
}
