package com.vortexbird.seguridad.modelo;
// Generated 14/11/2012 05:54:52 PM by Zathura powered by Hibernate Tools 3.2.4.GA


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * SegSistemaCia generated by Zathura powered by Hibernate-tools(hbm2java)
 */
@Entity
@Table(name="SEG_SISTEMA_CIA"
,schema="PUBLIC"
		)
public class SegSistemaCia  implements java.io.Serializable {


	private Long sicCodigo;
	private SegUsuario segUsuario;
	private SegCompania segCompania;
	private SegSistema segSistema;
	private String sicEstadoRegistro;
	private Set<SegPermiso> segPermisos = new HashSet<SegPermiso>(0);

	public SegSistemaCia() {
	}


	public SegSistemaCia(Long sicCodigo, SegCompania segCompania, SegSistema segSistema) {
		this.sicCodigo = sicCodigo;
		this.segCompania = segCompania;
		this.segSistema = segSistema;
	}
	public SegSistemaCia(Long sicCodigo, SegUsuario segUsuario, SegCompania segCompania, SegSistema segSistema, String sicEstadoRegistro, Set<SegPermiso> segPermisos) {
		this.sicCodigo = sicCodigo;
		this.segUsuario = segUsuario;
		this.segCompania = segCompania;
		this.segSistema = segSistema;
		this.segPermisos = segPermisos;
		this.sicEstadoRegistro = sicEstadoRegistro;
	}

	@Id 
	@SequenceGenerator(name = "seg_sistema_cia_sic_codigo_seq", sequenceName = "seg_sistema_cia_sic_codigo_seq")
	@GeneratedValue(generator = "seg_sistema_cia_sic_codigo_seq", strategy = GenerationType.SEQUENCE)
	@Column(name="SIC_CODIGO", unique=true, nullable=false)
	public Long getSicCodigo() {
		return this.sicCodigo;
	}

	public void setSicCodigo(Long sicCodigo) {
		this.sicCodigo = sicCodigo;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MOD_USU_CODIGO")
	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SEG_COMPANIA_CIA_CODIGO", nullable=false)
	public SegCompania getSegCompania() {
		return this.segCompania;
	}

	public void setSegCompania(SegCompania segCompania) {
		this.segCompania = segCompania;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SEG_SISTEMA_SIS_CODIGO", nullable=false)
	public SegSistema getSegSistema() {
		return this.segSistema;
	}

	public void setSegSistema(SegSistema segSistema) {
		this.segSistema = segSistema;
	}


	@Column(name="SIC_ESTADO_REGISTRO", length=1)
	public String getSicEstadoRegistro() {
		return this.sicEstadoRegistro;
	}

	public void setSicEstadoRegistro(String sicEstadoRegistro) {
		this.sicEstadoRegistro = sicEstadoRegistro;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="segSistemaCia")
	public Set<SegPermiso> getSegPermisos() {
		return this.segPermisos;
	}

	public void setSegPermisos(Set<SegPermiso> segPermisos) {
		this.segPermisos = segPermisos;
	}


}


