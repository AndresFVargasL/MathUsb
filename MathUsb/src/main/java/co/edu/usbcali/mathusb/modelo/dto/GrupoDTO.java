package co.edu.usbcali.mathusb.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public class GrupoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(GrupoDTO.class);
    private String descripcionGrupo;
    private Long grupId;
    private Long asigId_Asignatura;
    
    private String nombreAsignatura, nombreCompletoDocente;

    public String getDescripcionGrupo() {
        return descripcionGrupo;
    }

    public void setDescripcionGrupo(String descripcionGrupo) {
        this.descripcionGrupo = descripcionGrupo;
    }

    public Long getGrupId() {
        return grupId;
    }

    public void setGrupId(Long grupId) {
        this.grupId = grupId;
    }

    public Long getAsigId_Asignatura() {
        return asigId_Asignatura;
    }

    public void setAsigId_Asignatura(Long asigId_Asignatura) {
        this.asigId_Asignatura = asigId_Asignatura;
    }

	public String getNombreCompletoDocente() {
		return nombreCompletoDocente;
	}

	public void setNombreCompletoDocente(String nombreCompletoDocente) {
		this.nombreCompletoDocente = nombreCompletoDocente;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}
}
