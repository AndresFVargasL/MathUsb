package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.modelo.Comentario;
import co.edu.usbcali.mathusb.modelo.dto.ComentarioDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.primefaces.model.StreamedContent;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IComentarioLogic {
    public List<Comentario> getComentario() throws Exception;

    /**
         * Save an new Comentario entity
         */
    public void saveComentario(Comentario entity) throws Exception;

    /**
         * Delete an existing Comentario entity
         *
         */
    public void deleteComentario(Comentario entity) throws Exception;

    /**
        * Update an existing Comentario entity
        *
        */
    public void updateComentario(Comentario entity) throws Exception;

    /**
         * Load an existing Comentario entity
         *
         */
    public Comentario getComentario(Long comenId) throws Exception;

    public List<Comentario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Comentario> findPageComentario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberComentario() throws Exception;

    public List<ComentarioDTO> getDataComentario() throws Exception;
    
    public void hacerPregunta(Comentario pregunta) throws Exception;
    
    public List<ComentarioDTO> obtenerLasPreguntasDelForo() throws Exception;
    
    public StreamedContent getStreamedContent(ComentarioDTO comentarioDTO) throws Exception;
    
    public void responderPregunta(Comentario respuesta) throws Exception;
    
    public List<ComentarioDTO> obtenerLasRespuestasAUnaPreguntaDelForo(Long idPregunta) throws Exception;
    
    public List<ComentarioDTO> listaPreguntasForoDadoIdUsuario(Long idUsuario) throws Exception;
}
