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
public class UsuarioEvaluacionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuarioEvaluacionDTO.class);
    private Long usevId;
    private Long evalId_Evaluacion;
    private Long usuaId_Usuario;

    public Long getUsevId() {
        return usevId;
    }

    public void setUsevId(Long usevId) {
        this.usevId = usevId;
    }

    public Long getEvalId_Evaluacion() {
        return evalId_Evaluacion;
    }

    public void setEvalId_Evaluacion(Long evalId_Evaluacion) {
        this.evalId_Evaluacion = evalId_Evaluacion;
    }

    public Long getUsuaId_Usuario() {
        return usuaId_Usuario;
    }

    public void setUsuaId_Usuario(Long usuaId_Usuario) {
        this.usuaId_Usuario = usuaId_Usuario;
    }
}
