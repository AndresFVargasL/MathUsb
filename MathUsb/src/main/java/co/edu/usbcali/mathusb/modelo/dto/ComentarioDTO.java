package co.edu.usbcali.mathusb.modelo.dto;

import org.primefaces.model.StreamedContent;
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
public class ComentarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ComentarioDTO.class);
    private Long comenId;
    private String contenidoComentario;
    private String tituloComentario;
    private Long comenId_Comentario;
    private Long usuaId_Usuario;
    
    private String nombreUsuarioQueComenta;
    private Long cantidadRespuestas;

    private StreamedContent streamedContent;
    
    public Long getComenId() {
        return comenId;
    }

    public void setComenId(Long comenId) {
        this.comenId = comenId;
    }

    public String getContenidoComentario() {
        return contenidoComentario;
    }

    public void setContenidoComentario(String contenidoComentario) {
        this.contenidoComentario = contenidoComentario;
    }

    public String getTituloComentario() {
        return tituloComentario;
    }

    public void setTituloComentario(String tituloComentario) {
        this.tituloComentario = tituloComentario;
    }

    public Long getComenId_Comentario() {
        return comenId_Comentario;
    }

    public void setComenId_Comentario(Long comenId_Comentario) {
        this.comenId_Comentario = comenId_Comentario;
    }

    public Long getUsuaId_Usuario() {
        return usuaId_Usuario;
    }

    public void setUsuaId_Usuario(Long usuaId_Usuario) {
        this.usuaId_Usuario = usuaId_Usuario;
    }

	public String getNombreUsuarioQueComenta() {
		return nombreUsuarioQueComenta;
	}

	public void setNombreUsuarioQueComenta(String nombreUsuarioQueComenta) {
		this.nombreUsuarioQueComenta = nombreUsuarioQueComenta;
	}

	public StreamedContent getStreamedContent() {
		return streamedContent;
	}

	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}

	public Long getCantidadRespuestas() {
		return cantidadRespuestas;
	}

	public void setCantidadRespuestas(Long cantidadRespuestas) {
		this.cantidadRespuestas = cantidadRespuestas;
	}
}
