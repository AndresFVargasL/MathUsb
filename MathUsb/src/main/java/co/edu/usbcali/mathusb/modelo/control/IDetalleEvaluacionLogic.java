package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.modelo.DetalleEvaluacion;
import co.edu.usbcali.mathusb.modelo.Evaluacion;
import co.edu.usbcali.mathusb.modelo.dto.DetalleEvaluacionDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IDetalleEvaluacionLogic {
    public List<DetalleEvaluacion> getDetalleEvaluacion()
        throws Exception;

    /**
         * Save an new DetalleEvaluacion entity
         */
    public void saveDetalleEvaluacion(DetalleEvaluacion entity)
        throws Exception;

    /**
         * Delete an existing DetalleEvaluacion entity
         *
         */
    public void deleteDetalleEvaluacion(DetalleEvaluacion entity)
        throws Exception;

    /**
        * Update an existing DetalleEvaluacion entity
        *
        */
    public void updateDetalleEvaluacion(DetalleEvaluacion entity)
        throws Exception;

    /**
         * Load an existing DetalleEvaluacion entity
         *
         */
    public DetalleEvaluacion getDetalleEvaluacion(Long deevId)
        throws Exception;

    public List<DetalleEvaluacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<DetalleEvaluacion> findPageDetalleEvaluacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberDetalleEvaluacion() throws Exception;

    public List<DetalleEvaluacionDTO> getDataDetalleEvaluacion()
        throws Exception;
    
    public boolean detalleEvaluacionIsOk(DetalleEvaluacion entity) throws Exception;
    
    public DetalleEvaluacion obtenerDetalleEvaluacionDadoIdEvaluacion(Evaluacion evaluacion) throws Exception;
}
