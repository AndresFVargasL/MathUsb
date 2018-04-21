package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.modelo.Tema;
import co.edu.usbcali.mathusb.modelo.dto.TemaDTO;

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
public interface ITemaLogic {
    public List<Tema> getTema() throws Exception;

    /**
         * Save an new Tema entity
         */
    public void saveTema(Tema entity) throws Exception;

    /**
         * Delete an existing Tema entity
         *
         */
    public void deleteTema(Tema entity) throws Exception;

    /**
        * Update an existing Tema entity
        *
        */
    public void updateTema(Tema entity) throws Exception;

    /**
         * Load an existing Tema entity
         *
         */
    public Tema getTema(Long temaId) throws Exception;

    public List<Tema> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Tema> findPageTema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTema() throws Exception;

    public List<TemaDTO> getDataTema() throws Exception;
    
    public List<TemaDTO> obtenerTemaDadoPalabraClave(String palabraClave) throws Exception;
}
