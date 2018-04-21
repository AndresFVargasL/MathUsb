package co.edu.usbcali.mathusb.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class PreguntaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PreguntaDTO.class);
    private String descripcionPregunta;
    private Long pregId;
    private Long temaId_Tema;
    
    private String temaString, perteneceAEvaluacion;
    private boolean perteneceALaEvaluacion;
    

    public String getDescripcionPregunta() {
        return descripcionPregunta;
    }

    public void setDescripcionPregunta(String descripcionPregunta) {
        this.descripcionPregunta = descripcionPregunta;
    }

    public Long getPregId() {
        return pregId;
    }

    public void setPregId(Long pregId) {
        this.pregId = pregId;
    }

    public Long getTemaId_Tema() {
        return temaId_Tema;
    }

    public void setTemaId_Tema(Long temaId_Tema) {
        this.temaId_Tema = temaId_Tema;
    }

	public String getTemaString() {
		return temaString;
	}

	public void setTemaString(String temaString) {
		this.temaString = temaString;
	}

	public String getPerteneceAEvaluacion() {
		return perteneceAEvaluacion;
	}

	public boolean isPerteneceALaEvaluacion() {
		return perteneceALaEvaluacion;
	}

	public void setPerteneceAEvaluacion(String perteneceAEvaluacion) {
		this.perteneceAEvaluacion = perteneceAEvaluacion;
	}

	public void setPerteneceALaEvaluacion(boolean perteneceALaEvaluacion) {
		this.perteneceALaEvaluacion = perteneceALaEvaluacion;
	}
}
