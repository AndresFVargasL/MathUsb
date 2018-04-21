package co.edu.usbcali.mathusb.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;

/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
public class EvaluacionDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(EvaluacionDTO.class);
	private String estadoRegistro;
	private Long evalId;
	private Long grupId_Grupo;
	private Long tievId_TipoEvaluacion;

	// Atributos de DetalleEvaluacionDTO;
	private String asunto;
	private Long corte;
	private Date fechaCreacion;
	private Date fechaFinPublicacion;
	private Date fechaInicioPublicacion;
	
	private String fechaFinPublicacionString;
	private String fechaInicioPublicacionString;

	private String nombreGrupoYAsignatura;
	
	// Atributos de Usuario;
	private String nombreCompletoDocente;

	private String tipoEvaluacion;

	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public Long getEvalId() {
		return evalId;
	}

	public void setEvalId(Long evalId) {
		this.evalId = evalId;
	}

	public Long getGrupId_Grupo() {
		return grupId_Grupo;
	}

	public void setGrupId_Grupo(Long grupId_Grupo) {
		this.grupId_Grupo = grupId_Grupo;
	}

	public Long getTievId_TipoEvaluacion() {
		return tievId_TipoEvaluacion;
	}

	public void setTievId_TipoEvaluacion(Long tievId_TipoEvaluacion) {
		this.tievId_TipoEvaluacion = tievId_TipoEvaluacion;
	}

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

	public String getNombreCompletoDocente() {
		return nombreCompletoDocente;
	}

	public void setNombreCompletoDocente(String nombreCompletoDocente) {
		this.nombreCompletoDocente = nombreCompletoDocente;
	}

	public String getNombreGrupoYAsignatura() {
		return nombreGrupoYAsignatura;
	}

	public void setNombreGrupoYAsignatura(String nombreGrupoYAsignatura) {
		this.nombreGrupoYAsignatura = nombreGrupoYAsignatura;
	}

	public String getTipoEvaluacion() {
		return tipoEvaluacion;
	}

	public void setTipoEvaluacion(String tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}

	public String getFechaFinPublicacionString() {
		return fechaFinPublicacionString;
	}

	public String getFechaInicioPublicacionString() {
		return fechaInicioPublicacionString;
	}

	public void setFechaFinPublicacionString(String fechaFinPublicacionString) {
		this.fechaFinPublicacionString = fechaFinPublicacionString;
	}

	public void setFechaInicioPublicacionString(String fechaInicioPublicacionString) {
		this.fechaInicioPublicacionString = fechaInicioPublicacionString;
	}
}
