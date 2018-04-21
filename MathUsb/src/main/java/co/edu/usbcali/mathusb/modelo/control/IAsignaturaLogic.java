package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.modelo.Asignatura;
import co.edu.usbcali.mathusb.modelo.dto.AsignaturaDTO;

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
public interface IAsignaturaLogic {
    public List<Asignatura> getAsignatura() throws Exception;

    /**
         * Save an new Asignatura entity
         */
    public void saveAsignatura(Asignatura entity) throws Exception;

    /**
         * Delete an existing Asignatura entity
         *
         */
    public void deleteAsignatura(Asignatura entity) throws Exception;

    /**
        * Update an existing Asignatura entity
        *
        */
    public void updateAsignatura(Asignatura entity) throws Exception;

    /**
         * Load an existing Asignatura entity
         *
         */
    public Asignatura getAsignatura(Long asigId) throws Exception;

    public List<Asignatura> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Asignatura> findPageAsignatura(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAsignatura() throws Exception;

    public List<AsignaturaDTO> getDataAsignatura() throws Exception;
    
    public List<Asignatura> obtenerAsignaturasOrdenadasPorNombre() throws Exception;
}
