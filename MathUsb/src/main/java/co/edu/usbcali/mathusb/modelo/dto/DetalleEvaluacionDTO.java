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
public class DetalleEvaluacionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DetalleEvaluacionDTO.class);
    private String asunto;
    private Long corte;
    private Long deevId;
    private Date fechaCreacion;
    private Date fechaFinPublicacion;
    private Date fechaInicioPublicacion;
    private Long evalId_Evaluacion;

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Long getCorte() {
        return corte;
    }

    public void setCorte(Long corte) {
        this.corte = corte;
    }

    public Long getDeevId() {
        return deevId;
    }

    public void setDeevId(Long deevId) {
        this.deevId = deevId;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFinPublicacion() {
        return fechaFinPublicacion;
    }

    public void setFechaFinPublicacion(Date fechaFinPublicacion) {
        this.fechaFinPublicacion = fechaFinPublicacion;
    }

    public Date getFechaInicioPublicacion() {
        return fechaInicioPublicacion;
    }

    public void setFechaInicioPublicacion(Date fechaInicioPublicacion) {
        this.fechaInicioPublicacion = fechaInicioPublicacion;
    }

    public Long getEvalId_Evaluacion() {
        return evalId_Evaluacion;
    }

    public void setEvalId_Evaluacion(Long evalId_Evaluacion) {
        this.evalId_Evaluacion = evalId_Evaluacion;
    }
}
