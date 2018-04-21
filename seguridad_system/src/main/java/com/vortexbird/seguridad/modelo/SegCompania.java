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
 * SegCompania generated by Zathura powered by Hibernate-tools(hbm2java)
 */
@Entity
@Table(name="SEG_COMPANIA"
,schema="PUBLIC"
		)
public class SegCompania  implements java.io.Serializable {


	private Long ciaCodigo;
	private SegUsuario segUsuario;
	private String ciaCodigoInterno;
	private String ciaNombre;
	private String ciaEstadoRegistro;
	private Set<SegSucursal> segSucursals = new HashSet<SegSucursal>(0);
	private Set<SegSistemaCia> segSistemaCias = new HashSet<SegSistemaCia>(0);
	private Set<SegUsuario> segUsuarios = new HashSet<SegUsuario>(0);

	public SegCompania() {
	}


	public SegCompania(Long ciaCodigo, String ciaCodigoInterno, String ciaNombre, String ciaEstadoRegistro) {
		this.ciaCodigo = ciaCodigo;
		this.ciaCodigoInterno = ciaCodigoInterno;
		this.ciaNombre = ciaNombre;
		this.ciaEstadoRegistro = ciaEstadoRegistro;
	}
	public SegCompania(Long ciaCodigo, SegUsuario segUsuario, String ciaCodigoInterno, String ciaNombre, String ciaEstadoRegistro, Set<SegSucursal> segSucursals, Set<SegSistemaCia> segSistemaCias) {
		this.ciaCodigo = ciaCodigo;
		this.segUsuario = segUsuario;
		this.ciaCodigoInterno = ciaCodigoInterno;
		this.ciaNombre = ciaNombre;
		this.ciaEstadoRegistro = ciaEstadoRegistro;
		this.segSucursals = segSucursals;
		this.segSistemaCias = segSistemaCias;
	}

	@Id 
	@SequenceGenerator(name = "seg_compania_cia_codigo_seq", sequenceName = "seg_compania_cia_codigo_seq")
	@GeneratedValue(generator = "seg_compania_cia_codigo_seq", strategy = GenerationType.SEQUENCE)
	@Column(name="CIA_CODIGO", unique=true, nullable=false)
	public Long getCiaCodigo() {
		return this.ciaCodigo;
	}

	public void setCiaCodigo(Long ciaCodigo) {
		this.ciaCodigo = ciaCodigo;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MOD_USU_CODIGO")
	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}


	@Column(name="CIA_CODIGO_INTERNO", nullable=false, length=15)
	public String getCiaCodigoInterno() {
		return this.ciaCodigoInterno;
	}

	public void setCiaCodigoInterno(String ciaCodigoInterno) {
		this.ciaCodigoInterno = ciaCodigoInterno;
	}


	@Column(name="CIA_NOMBRE", nullable=false, length=50)
	public String getCiaNombre() {
		return this.ciaNombre;
	}

	public void setCiaNombre(String ciaNombre) {
		this.ciaNombre = ciaNombre;
	}


	@Column(name="CIA_ESTADO_REGISTRO", nullable=false, length=1)
	public String getCiaEstadoRegistro() {
		return this.ciaEstadoRegistro;
	}

	public void setCiaEstadoRegistro(String ciaEstadoRegistro) {
		this.ciaEstadoRegistro = ciaEstadoRegistro;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="segCompania")
	public Set<SegSucursal> getSegSucursals() {
		return this.segSucursals;
	}

	public void setSegSucursals(Set<SegSucursal> segSucursals) {
		this.segSucursals = segSucursals;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="segCompania")
	public Set<SegSistemaCia> getSegSistemaCias() {
		return this.segSistemaCias;
	}

	public void setSegSistemaCias(Set<SegSistemaCia> segSistemaCias) {
		this.segSistemaCias = segSistemaCias;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="usuCompaniaCiaCodigo")
	public Set<SegUsuario> getSegUsuarios() {
		return segUsuarios;
	}


	public void setSegUsuarios(Set<SegUsuario> segUsuarios) {
		this.segUsuarios = segUsuarios;
	}




}

