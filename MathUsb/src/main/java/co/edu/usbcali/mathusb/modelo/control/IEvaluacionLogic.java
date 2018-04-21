package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.modelo.DetalleEvaluacion;
import co.edu.usbcali.mathusb.modelo.Evaluacion;
import co.edu.usbcali.mathusb.modelo.Usuario;
import co.edu.usbcali.mathusb.modelo.dto.EvaluacionDTO;
import co.edu.usbcali.mathusb.utilities.Constantes.REPORT_OUTPUT_TYPE;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IEvaluacionLogic {
    public List<Evaluacion> getEvaluacion() throws Exception;

    /**
         * Save an new Evaluacion entity
         */
    public void saveEvaluacion(Evaluacion entity) throws Exception;

    /**
         * Delete an existing Evaluacion entity
         *
         */
    public void deleteEvaluacion(Evaluacion entity) throws Exception;

    /**
        * Update an existing Evaluacion entity
        *
        */
    public void updateEvaluacion(Evaluacion entity) throws Exception;

    /**
         * Load an existing Evaluacion entity
         *
         */
    public Evaluacion getEvaluacion(Long evalId) throws Exception;

    public List<Evaluacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Evaluacion> findPageEvaluacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEvaluacion() throws Exception;

    public List<EvaluacionDTO> getDataEvaluacion() throws Exception;
    
    public void guardarNuevaEvaluacion(Evaluacion evaluacion, DetalleEvaluacion detalleEvaluacion, Usuario usuario) throws Exception;
    
    public List<EvaluacionDTO> obtenerListaEvaluacionesDelDocente(Long idDocente) throws Exception;
    
    public List<EvaluacionDTO> obtenerEvaluacionesDelGrupo(Long idGrupo) throws Exception;
    
    public String obtenerFechaDecenteDadoDate(Date fechaHora) throws Exception;
    
    public void actualizarUnaEvaluacion(Evaluacion evaluacion, DetalleEvaluacion detalleEvaluacion) throws Exception;
    
    public List<EvaluacionDTO> obtenerEvaluacionesDelGrupoDisponiblesEnFechaHoy(Long idGrupo) throws Exception;
    
    public ByteArrayInputStream generarReporteEvaluacion(REPORT_OUTPUT_TYPE reportOutputTye, Long eval_Id) throws Exception;
}
