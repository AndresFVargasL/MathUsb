package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.modelo.Herramienta;
import co.edu.usbcali.mathusb.modelo.dto.HerramientaDTO;

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
public interface IHerramientaLogic {
    public List<Herramienta> getHerramienta() throws Exception;

    /**
         * Save an new Herramienta entity
         */
    public void saveHerramienta(Herramienta entity) throws Exception;

    /**
         * Delete an existing Herramienta entity
         *
         */
    public void deleteHerramienta(Herramienta entity) throws Exception;

    /**
        * Update an existing Herramienta entity
        *
        */
    public void updateHerramienta(Herramienta entity) throws Exception;

    /**
         * Load an existing Herramienta entity
         *
         */
    public Herramienta getHerramienta(Long herrId) throws Exception;

    public List<Herramienta> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Herramienta> findPageHerramienta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberHerramienta() throws Exception;

    public List<HerramientaDTO> getDataHerramienta() throws Exception;
    
    public List<HerramientaDTO> herramientasOrdenadasPorFecha() throws Exception;
    
    public List<HerramientaDTO> herramientasDelUsuarioOrdenadasPorFecha() throws Exception;
}
