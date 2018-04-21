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
public class TipoHerramientaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoHerramientaDTO.class);
    private String descripcionHerramienta;
    private String nombreCorto;
    private Long tiheId;

    public String getDescripcionHerramienta() {
        return descripcionHerramienta;
    }

    public void setDescripcionHerramienta(String descripcionHerramienta) {
        this.descripcionHerramienta = descripcionHerramienta;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public Long getTiheId() {
        return tiheId;
    }

    public void setTiheId(Long tiheId) {
        this.tiheId = tiheId;
    }
}
