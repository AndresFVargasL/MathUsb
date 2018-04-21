package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.modelo.TipoUsuario;
import co.edu.usbcali.mathusb.modelo.dto.TipoUsuarioDTO;

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
public interface ITipoUsuarioLogic {
    public List<TipoUsuario> getTipoUsuario() throws Exception;

    /**
         * Save an new TipoUsuario entity
         */
    public void saveTipoUsuario(TipoUsuario entity) throws Exception;

    /**
         * Delete an existing TipoUsuario entity
         *
         */
    public void deleteTipoUsuario(TipoUsuario entity) throws Exception;

    /**
        * Update an existing TipoUsuario entity
        *
        */
    public void updateTipoUsuario(TipoUsuario entity) throws Exception;

    /**
         * Load an existing TipoUsuario entity
         *
         */
    public TipoUsuario getTipoUsuario(Long tiusId) throws Exception;

    public List<TipoUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TipoUsuario> findPageTipoUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoUsuario() throws Exception;

    public List<TipoUsuarioDTO> getDataTipoUsuario() throws Exception;
    
    public TipoUsuario obtenerTipoUsuarioPorNombreCorto(String nombreCorto) throws Exception;
}
