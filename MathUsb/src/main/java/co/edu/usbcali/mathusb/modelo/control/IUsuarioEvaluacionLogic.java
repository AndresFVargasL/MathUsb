package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.modelo.UsuarioEvaluacion;
import co.edu.usbcali.mathusb.modelo.dto.UsuarioEvaluacionDTO;

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
public interface IUsuarioEvaluacionLogic {
    public List<UsuarioEvaluacion> getUsuarioEvaluacion()
        throws Exception;

    /**
         * Save an new UsuarioEvaluacion entity
         */
    public void saveUsuarioEvaluacion(UsuarioEvaluacion entity)
        throws Exception;

    /**
         * Delete an existing UsuarioEvaluacion entity
         *
         */
    public void deleteUsuarioEvaluacion(UsuarioEvaluacion entity)
        throws Exception;

    /**
        * Update an existing UsuarioEvaluacion entity
        *
        */
    public void updateUsuarioEvaluacion(UsuarioEvaluacion entity)
        throws Exception;

    /**
         * Load an existing UsuarioEvaluacion entity
         *
         */
    public UsuarioEvaluacion getUsuarioEvaluacion(Long usevId)
        throws Exception;

    public List<UsuarioEvaluacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<UsuarioEvaluacion> findPageUsuarioEvaluacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberUsuarioEvaluacion() throws Exception;

    public List<UsuarioEvaluacionDTO> getDataUsuarioEvaluacion()
        throws Exception;
}
