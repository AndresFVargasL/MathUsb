package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.modelo.GrupoUsuario;
import co.edu.usbcali.mathusb.modelo.dto.GrupoDTO;
import co.edu.usbcali.mathusb.modelo.dto.GrupoUsuarioDTO;

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
public interface IGrupoUsuarioLogic {
    public List<GrupoUsuario> getGrupoUsuario() throws Exception;

    /**
         * Save an new GrupoUsuario entity
         */
    public void saveGrupoUsuario(GrupoUsuario entity) throws Exception;

    /**
         * Delete an existing GrupoUsuario entity
         *
         */
    public void deleteGrupoUsuario(GrupoUsuario entity)
        throws Exception;

    /**
        * Update an existing GrupoUsuario entity
        *
        */
    public void updateGrupoUsuario(GrupoUsuario entity)
        throws Exception;

    /**
         * Load an existing GrupoUsuario entity
         *
         */
    public GrupoUsuario getGrupoUsuario(Long grusId) throws Exception;

    public List<GrupoUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<GrupoUsuario> findPageGrupoUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberGrupoUsuario() throws Exception;

    public List<GrupoUsuarioDTO> getDataGrupoUsuario()
        throws Exception;
    
    public GrupoUsuario obtenerGrupoUsuarioPorIdDeGrupoYUsuarioDocente(Long grupId) throws Exception;
    
    public void matricularEstudianteEnUnGrupo(GrupoDTO grupoDTO) throws Exception;
    
    public boolean estudianteEstaMatriculadoEnElCurso(Long grupId, Long usuaId) throws Exception;
    
    public void desmatricularEstudianteEnUnGrupo(GrupoDTO grupoDTO) throws Exception;
}
