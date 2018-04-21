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
public class ParametroDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ParametroDTO.class);
    private String descripcionParametros;
    private String estadoRegistro;
    private Long paramId;
    private String valorParametro;

    public String getDescripcionParametros() {
        return descripcionParametros;
    }

    public void setDescripcionParametros(String descripcionParametros) {
        this.descripcionParametros = descripcionParametros;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }
}
