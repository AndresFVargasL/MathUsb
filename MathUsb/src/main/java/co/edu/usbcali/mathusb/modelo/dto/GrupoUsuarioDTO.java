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
public class GrupoUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(GrupoUsuarioDTO.class);
    private Long grusId;
    private Long grupId_Grupo;
    private Long usuaId_Usuario;

    public Long getGrusId() {
        return grusId;
    }

    public void setGrusId(Long grusId) {
        this.grusId = grusId;
    }

    public Long getGrupId_Grupo() {
        return grupId_Grupo;
    }

    public void setGrupId_Grupo(Long grupId_Grupo) {
        this.grupId_Grupo = grupId_Grupo;
    }

    public Long getUsuaId_Usuario() {
        return usuaId_Usuario;
    }

    public void setUsuaId_Usuario(Long usuaId_Usuario) {
        this.usuaId_Usuario = usuaId_Usuario;
    }
}
