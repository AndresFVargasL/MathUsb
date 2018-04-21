package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.modelo.Grupo;
import co.edu.usbcali.mathusb.modelo.dto.GrupoDTO;

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
public interface IGrupoLogic {
    public List<Grupo> getGrupo() throws Exception;

    /**
         * Save an new Grupo entity
         */
    public void saveGrupo(Grupo entity) throws Exception;

    /**
         * Delete an existing Grupo entity
         *
         */
    public void deleteGrupo(Grupo entity) throws Exception;

    /**
        * Update an existing Grupo entity
        *
        */
    public void updateGrupo(Grupo entity) throws Exception;

    /**
         * Load an existing Grupo entity
         *
         */
    public Grupo getGrupo(Long grupId) throws Exception;

    public List<Grupo> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Grupo> findPageGrupo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberGrupo() throws Exception;

    public List<GrupoDTO> getDataGrupo() throws Exception;
    
    public List<Grupo> traerGruposDeDocentes() throws Exception;
    
    public List<GrupoDTO> traerGruposConDocentesYNombreAsignatura() throws Exception;
    
    public List<GrupoDTO> listaDeGruposDelEstudiante() throws Exception;
    
    public List<GrupoDTO> listaDeGruposDelDocente() throws Exception;
    
    public GrupoDTO obtenerGrupoDTODadoGrupId(Long grupId) throws Exception;
}
