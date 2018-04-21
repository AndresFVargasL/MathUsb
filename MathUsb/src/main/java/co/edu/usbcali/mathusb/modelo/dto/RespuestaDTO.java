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
public class RespuestaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RespuestaDTO.class);
    private String descripcionRespuesta;
    private Long respId;

    public String getDescripcionRespuesta() {
        return descripcionRespuesta;
    }

    public void setDescripcionRespuesta(String descripcionRespuesta) {
        this.descripcionRespuesta = descripcionRespuesta;
    }

    public Long getRespId() {
        return respId;
    }

    public void setRespId(Long respId) {
        this.respId = respId;
    }
}
