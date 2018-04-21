package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.modelo.Pregunta;
import co.edu.usbcali.mathusb.modelo.dto.ComentarioDTO;
import co.edu.usbcali.mathusb.modelo.dto.PreguntaDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.primefaces.model.StreamedContent;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IPreguntaLogic {
    public List<Pregunta> getPregunta() throws Exception;

    /**
         * Save an new Pregunta entity
         */
    public void savePregunta(Pregunta entity) throws Exception;

    /**
         * Delete an existing Pregunta entity
         *
         */
    public void deletePregunta(Pregunta entity) throws Exception;

    /**
        * Update an existing Pregunta entity
        *
        */
    public void updatePregunta(Pregunta entity) throws Exception;

    /**
         * Load an existing Pregunta entity
         *
         */
    public Pregunta getPregunta(Long pregId) throws Exception;

    public List<Pregunta> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Pregunta> findPagePregunta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPregunta() throws Exception;

    public List<PreguntaDTO> getDataPregunta() throws Exception;
    
    public List<PreguntaDTO> consultarPreguntaDadoIdEval(Long evalId) throws Exception;
    
    public StreamedContent getStreamedContent(PreguntaDTO preguntaDTO) throws Exception;
    
    public List<PreguntaDTO> consultarPreguntasDadoIdTema(Long temaId) throws Exception;
}
