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
public class TipoEvaluacionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoEvaluacionDTO.class);
    private String descripcionTipoEvaluacion;
    private String nombreCorto;
    private Long tievId;

    public String getDescripcionTipoEvaluacion() {
        return descripcionTipoEvaluacion;
    }

    public void setDescripcionTipoEvaluacion(String descripcionTipoEvaluacion) {
        this.descripcionTipoEvaluacion = descripcionTipoEvaluacion;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public Long getTievId() {
        return tievId;
    }

    public void setTievId(Long tievId) {
        this.tievId = tievId;
    }
}
