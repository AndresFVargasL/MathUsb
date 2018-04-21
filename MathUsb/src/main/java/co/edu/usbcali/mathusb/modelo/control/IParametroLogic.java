package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.modelo.Parametro;
import co.edu.usbcali.mathusb.modelo.dto.ParametroDTO;

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
public interface IParametroLogic {
    public List<Parametro> getParametro() throws Exception;

    /**
         * Save an new Parametro entity
         */
    public void saveParametro(Parametro entity) throws Exception;

    /**
         * Delete an existing Parametro entity
         *
         */
    public void deleteParametro(Parametro entity) throws Exception;

    /**
        * Update an existing Parametro entity
        *
        */
    public void updateParametro(Parametro entity) throws Exception;

    /**
         * Load an existing Parametro entity
         *
         */
    public Parametro getParametro(Long paramId) throws Exception;

    public List<Parametro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Parametro> findPageParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberParametro() throws Exception;

    public List<ParametroDTO> getDataParametro() throws Exception;
    
    public Parametro getParametroPorDescripcion(String descripcion) throws Exception;
}
