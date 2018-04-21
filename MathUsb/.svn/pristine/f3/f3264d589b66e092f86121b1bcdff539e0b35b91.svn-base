package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.ComentarioDTO;
import co.edu.usbcali.mathusb.modelo.dto.DetalleEvaluacionDTO;
import co.edu.usbcali.mathusb.modelo.dto.EvaluacionDTO;
import co.edu.usbcali.mathusb.modelo.dto.GrupoDTO;
import co.edu.usbcali.mathusb.modelo.dto.HerramientaDTO;
import co.edu.usbcali.mathusb.modelo.dto.PreguntaDTO;
import co.edu.usbcali.mathusb.presentation.businessDelegate.*;
import co.edu.usbcali.mathusb.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class EvaluacionView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(EvaluacionView.class);
	private InputText txtEstadoRegistro;
	private InputText txtGrupId_Grupo;
	private InputText txtTievId_TipoEvaluacion;
	private InputText txtEvalId;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<EvaluacionDTO> data;
	private EvaluacionDTO selectedEvaluacion;
	private Evaluacion entity;
	private boolean showDialog;

	private String somCortesString;
	private List<SelectItem> losCortes;

	private String somTiposDeEvaluacionString;
	private List<SelectItem> losTiposDeEvaluacion;

	private InputTextarea txtAsunto;
	private Calendar dateFechaInicioEvaluacion;
	private Calendar dateFechaFinEvaluacion;

	private List<EvaluacionDTO> lasEvaluacionesDelDocente, lasEvaluacionesDelGrupo;

	private String somGrupoAsignaturaString;
	private List<SelectItem> losGruposAsignaturas;

	private EvaluacionDTO evaluacionModifica;

	private boolean showDlg;

	private InputTextarea txtAsuntoUpdate;
	private Calendar dateFechaInicioEvaluacionUpdate, dateFechaFinEvaluacionUpdate;
	private String somCortesStringUpdate, somTiposDeEvaluacionStringUpdate;
	
	private String asuntoString, fechaInicioString, fechaFinString, corteString, tipoEvaluacionString, grupoAsignaturaString;
	private String asuntoEvaluacion, fechaInicioEvaluacion, fechaFinEvaluacion, corteEvaluacion;
	
	private List<PreguntaDTO> lasPreguntasDeLaEvaluacion, preguntasEvaluacionEstudiante;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	
	private StreamedContent fileReporteGenerado;

	public EvaluacionView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			EvaluacionDTO evaluacionDTO = (EvaluacionDTO) e.getObject();

			if (txtEstadoRegistro == null) {
				txtEstadoRegistro = new InputText();
			}

			txtEstadoRegistro.setValue(evaluacionDTO.getEstadoRegistro());

			if (txtGrupId_Grupo == null) {
				txtGrupId_Grupo = new InputText();
			}

			txtGrupId_Grupo.setValue(evaluacionDTO.getGrupId_Grupo());

			if (txtTievId_TipoEvaluacion == null) {
				txtTievId_TipoEvaluacion = new InputText();
			}

			txtTievId_TipoEvaluacion.setValue(evaluacionDTO.getTievId_TipoEvaluacion());

			if (txtEvalId == null) {
				txtEvalId = new InputText();
			}

			txtEvalId.setValue(evaluacionDTO.getEvalId());

			Long evalId = FacesUtils.checkLong(txtEvalId);
			entity = businessDelegatorView.getEvaluacion(evalId);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedEvaluacion = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedEvaluacion = null;

		if (txtEstadoRegistro != null) {
			txtEstadoRegistro.setValue(null);
			txtEstadoRegistro.setDisabled(true);
		}

		if (txtGrupId_Grupo != null) {
			txtGrupId_Grupo.setValue(null);
			txtGrupId_Grupo.setDisabled(true);
		}

		if (txtTievId_TipoEvaluacion != null) {
			txtTievId_TipoEvaluacion.setValue(null);
			txtTievId_TipoEvaluacion.setDisabled(true);
		}

		if (txtEvalId != null) {
			txtEvalId.setValue(null);
			txtEvalId.setDisabled(false);
		}

		if (btnSave != null) {
			btnSave.setDisabled(true);
		}

		if (btnDelete != null) {
			btnDelete.setDisabled(true);
		}

		return "";
	}

	public void listener_txtId() {
		try {
			Long evalId = FacesUtils.checkLong(txtEvalId);
			entity = (evalId != null) ? businessDelegatorView.getEvaluacion(evalId) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtEstadoRegistro.setDisabled(false);
			txtGrupId_Grupo.setDisabled(false);
			txtTievId_TipoEvaluacion.setDisabled(false);
			txtEvalId.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtEstadoRegistro.setValue(entity.getEstadoRegistro());
			txtEstadoRegistro.setDisabled(false);
			txtGrupId_Grupo.setValue(entity.getGrupo().getGrupId());
			txtGrupId_Grupo.setDisabled(false);
			txtTievId_TipoEvaluacion.setValue(entity.getTipoEvaluacion().getTievId());
			txtTievId_TipoEvaluacion.setDisabled(false);
			txtEvalId.setValue(entity.getEvalId());
			txtEvalId.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedEvaluacion = (EvaluacionDTO) (evt.getComponent().getAttributes().get("selectedEvaluacion"));
		txtEstadoRegistro.setValue(selectedEvaluacion.getEstadoRegistro());
		txtEstadoRegistro.setDisabled(false);
		txtGrupId_Grupo.setValue(selectedEvaluacion.getGrupId_Grupo());
		txtGrupId_Grupo.setDisabled(false);
		txtTievId_TipoEvaluacion.setValue(selectedEvaluacion.getTievId_TipoEvaluacion());
		txtTievId_TipoEvaluacion.setDisabled(false);
		txtEvalId.setValue(selectedEvaluacion.getEvalId());
		txtEvalId.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedEvaluacion == null) && (entity == null)) {
				action_create();
			} else {
				action_modify();
			}

			data = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_create() {
		try {
			entity = new Evaluacion();

			Long evalId = FacesUtils.checkLong(txtEvalId);

			entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
			entity.setEvalId(evalId);
			entity.setGrupo((FacesUtils.checkLong(txtGrupId_Grupo) != null)
					? businessDelegatorView.getGrupo(FacesUtils.checkLong(txtGrupId_Grupo)) : null);
			entity.setTipoEvaluacion((FacesUtils.checkLong(txtTievId_TipoEvaluacion) != null)
					? businessDelegatorView.getTipoEvaluacion(FacesUtils.checkLong(txtTievId_TipoEvaluacion)) : null);
			businessDelegatorView.saveEvaluacion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			if (entity == null) {
				Long evalId = new Long(selectedEvaluacion.getEvalId());
				entity = businessDelegatorView.getEvaluacion(evalId);
			}

			entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
			entity.setGrupo((FacesUtils.checkLong(txtGrupId_Grupo) != null)
					? businessDelegatorView.getGrupo(FacesUtils.checkLong(txtGrupId_Grupo)) : null);
			entity.setTipoEvaluacion((FacesUtils.checkLong(txtTievId_TipoEvaluacion) != null)
					? businessDelegatorView.getTipoEvaluacion(FacesUtils.checkLong(txtTievId_TipoEvaluacion)) : null);
			businessDelegatorView.updateEvaluacion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedEvaluacion = (EvaluacionDTO) (evt.getComponent().getAttributes().get("selectedEvaluacion"));

			Long evalId = new Long(selectedEvaluacion.getEvalId());
			entity = businessDelegatorView.getEvaluacion(evalId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long evalId = FacesUtils.checkLong(txtEvalId);
			entity = businessDelegatorView.getEvaluacion(evalId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteEvaluacion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data = null;
		} catch (Exception e) {
			throw e;
		}
	}

	public String action_closeDialog() {
		setShowDialog(false);
		action_clear();

		return "";
	}

	public String actionDeleteDataTableEditable(ActionEvent evt) {
		try {
			selectedEvaluacion = (EvaluacionDTO) (evt.getComponent().getAttributes().get("selectedEvaluacion"));

			Long evalId = new Long(selectedEvaluacion.getEvalId());
			entity = businessDelegatorView.getEvaluacion(evalId);
			businessDelegatorView.deleteEvaluacion(entity);
			data.remove(selectedEvaluacion);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String estadoRegistro, Long evalId, Long grupId_Grupo, Long tievId_TipoEvaluacion)
			throws Exception {
		try {
			entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
			businessDelegatorView.updateEvaluacion(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("EvaluacionView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtEstadoRegistro() {
		return txtEstadoRegistro;
	}

	public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
		this.txtEstadoRegistro = txtEstadoRegistro;
	}

	public InputText getTxtGrupId_Grupo() {
		return txtGrupId_Grupo;
	}

	public void setTxtGrupId_Grupo(InputText txtGrupId_Grupo) {
		this.txtGrupId_Grupo = txtGrupId_Grupo;
	}

	public InputText getTxtTievId_TipoEvaluacion() {
		return txtTievId_TipoEvaluacion;
	}

	public void setTxtTievId_TipoEvaluacion(InputText txtTievId_TipoEvaluacion) {
		this.txtTievId_TipoEvaluacion = txtTievId_TipoEvaluacion;
	}

	public InputText getTxtEvalId() {
		return txtEvalId;
	}

	public void setTxtEvalId(InputText txtEvalId) {
		this.txtEvalId = txtEvalId;
	}

	public List<EvaluacionDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataEvaluacion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<EvaluacionDTO> evaluacionDTO) {
		this.data = evaluacionDTO;
	}

	public EvaluacionDTO getSelectedEvaluacion() {
		return selectedEvaluacion;
	}

	public void setSelectedEvaluacion(EvaluacionDTO evaluacion) {
		this.selectedEvaluacion = evaluacion;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(CommandButton btnModify) {
		this.btnModify = btnModify;
	}

	public CommandButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(CommandButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public CommandButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(CommandButton btnClear) {
		this.btnClear = btnClear;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public String getSomCortesString() {
		return somCortesString;
	}

	public void setSomCortesString(String somCortesString) {
		this.somCortesString = somCortesString;
	}

	public List<SelectItem> getLosCortes() {
		try {
			if (losCortes == null) {
				losCortes = new ArrayList<SelectItem>();
				for (int i = 1; i <= 3; i++) {

					SelectItem selectItem = new SelectItem(i, "Corte " + i);
					losCortes.add(selectItem);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return losCortes;
	}

	public void setLosCortes(List<SelectItem> losCortes) {
		this.losCortes = losCortes;
	}

	public List<SelectItem> getLosTiposDeEvaluacion() {
		try {
			if (losTiposDeEvaluacion == null) {
				losTiposDeEvaluacion = new ArrayList<SelectItem>();
				List<TipoEvaluacion> tiposEvaluacion = businessDelegatorView.getTipoEvaluacion();
				for (TipoEvaluacion tipoEvaluacion : tiposEvaluacion) {
					SelectItem selectItem = new SelectItem(tipoEvaluacion.getTievId(),
							tipoEvaluacion.getDescripcionTipoEvaluacion());
					losTiposDeEvaluacion.add(selectItem);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return losTiposDeEvaluacion;
	}

	public void setLosTiposDeEvaluacion(List<SelectItem> losTiposDeEvaluacion) {
		this.losTiposDeEvaluacion = losTiposDeEvaluacion;
	}

	public String getSomTiposDeEvaluacionString() {
		return somTiposDeEvaluacionString;
	}

	public void setSomTiposDeEvaluacionString(String somTiposDeEvaluacionString) {
		this.somTiposDeEvaluacionString = somTiposDeEvaluacionString;
	}

	public InputTextarea getTxtAsunto() {
		return txtAsunto;
	}

	public void setTxtAsunto(InputTextarea txtAsunto) {
		this.txtAsunto = txtAsunto;
	}

	public List<EvaluacionDTO> getLasEvaluacionesDelDocente() {
		try {
			if (lasEvaluacionesDelDocente == null) {

				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(true);
				com.vortexbird.seguridad.modelo.dto.UsuarioDTO loginUsuario = (com.vortexbird.seguridad.modelo.dto.UsuarioDTO) session
						.getAttribute("usuarioDTO");
				Usuario docente = businessDelegatorView.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());

				lasEvaluacionesDelDocente = businessDelegatorView
						.obtenerListaEvaluacionesDelDocente(docente.getUsuaId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lasEvaluacionesDelDocente;
	}

	public void setLasEvaluacionesDelDocente(List<EvaluacionDTO> lasEvaluacionesDelDocente) {
		this.lasEvaluacionesDelDocente = lasEvaluacionesDelDocente;
	}

	public String getSomGrupoAsignaturaString() {
		return somGrupoAsignaturaString;
	}

	public void setSomGrupoAsignaturaString(String somGrupoAsignaturaString) {
		this.somGrupoAsignaturaString = somGrupoAsignaturaString;
	}

	public List<SelectItem> getLosGruposAsignaturas() {
		try {
			if (losGruposAsignaturas == null) {
				losGruposAsignaturas = new ArrayList<SelectItem>();
				List<GrupoDTO> losGruposDelDocente = businessDelegatorView.listaDeGruposDelDocente();
				for (GrupoDTO grupoDTO : losGruposDelDocente) {
					SelectItem selectItem = new SelectItem(grupoDTO.getGrupId(),
							grupoDTO.getDescripcionGrupo() + " - " + grupoDTO.getNombreAsignatura());
					losGruposAsignaturas.add(selectItem);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return losGruposAsignaturas;
	}

	public void setLosGruposAsignaturas(List<SelectItem> losGruposAsignaturas) {
		this.losGruposAsignaturas = losGruposAsignaturas;
	}

	public String guardarNuevaEvalucion() {
		Evaluacion evaluacion;
		DetalleEvaluacion detalleEvaluacion;
		Usuario usuario;
		try {
			evaluacion = new Evaluacion();

			evaluacion.setEstadoRegistro("A");
			evaluacion.setTipoEvaluacion(
					businessDelegatorView.getTipoEvaluacion(Long.parseLong(somTiposDeEvaluacionString)));

			evaluacion.setGrupo(businessDelegatorView.getGrupo(Long.parseLong(somGrupoAsignaturaString)));

			detalleEvaluacion = new DetalleEvaluacion();
			detalleEvaluacion.setAsunto(txtAsunto.getValue().toString());
			detalleEvaluacion.setCorte(Long.parseLong(somCortesString));
			detalleEvaluacion.setFechaCreacion(new Date());
			detalleEvaluacion.setFechaInicioPublicacion(FacesUtils.checkDate(dateFechaInicioEvaluacion));
			detalleEvaluacion.setFechaFinPublicacion(FacesUtils.checkDate(dateFechaFinEvaluacion));

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			com.vortexbird.seguridad.modelo.dto.UsuarioDTO loginUsuario = (com.vortexbird.seguridad.modelo.dto.UsuarioDTO) session
					.getAttribute("usuarioDTO");
			usuario = businessDelegatorView.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());

			businessDelegatorView.guardarNuevaEvaluacion(evaluacion, detalleEvaluacion, usuario);
			FacesUtils.addInfoMessage("El documento ha sido creado exitosamente");

			limpiarPantallaCrearEvaluacion();

		} catch (Exception e) {
			evaluacion = null;
			detalleEvaluacion = null;
			usuario = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void limpiarPantallaCrearEvaluacion() {
		somTiposDeEvaluacionString = "0";
		txtAsunto.setValue(null);
		somCortesString = "0";
		somGrupoAsignaturaString = "0";
		dateFechaInicioEvaluacion.setValue(null);
		dateFechaFinEvaluacion.setValue(null);
		lasEvaluacionesDelDocente = null;
	}

	public Calendar getDateFechaInicioEvaluacion() {
		return dateFechaInicioEvaluacion;
	}

	public Calendar getDateFechaFinEvaluacion() {
		return dateFechaFinEvaluacion;
	}

	public void setDateFechaInicioEvaluacion(Calendar dateFechaInicioEvaluacion) {
		this.dateFechaInicioEvaluacion = dateFechaInicioEvaluacion;
	}

	public void setDateFechaFinEvaluacion(Calendar dateFechaFinEvaluacion) {
		this.dateFechaFinEvaluacion = dateFechaFinEvaluacion;
	}

	public EvaluacionDTO getEvaluacionModifica() {
		return evaluacionModifica;
	}

	public void setEvaluacionModifica(EvaluacionDTO evaluacionModifica) {
		this.evaluacionModifica = evaluacionModifica;
	}

	public boolean isShowDlg() {
		return showDlg;
	}

	public void setShowDlg(boolean showDlg) {
		this.showDlg = showDlg;
	}

	public InputTextarea getTxtAsuntoUpdate() {
		return txtAsuntoUpdate;
	}

	public Calendar getDateFechaInicioEvaluacionUpdate() {
		return dateFechaInicioEvaluacionUpdate;
	}

	public Calendar getDateFechaFinEvaluacionUpdate() {
		return dateFechaFinEvaluacionUpdate;
	}

	public String getSomCortesStringUpdate() {
		return somCortesStringUpdate;
	}

	public String getSomTiposDeEvaluacionStringUpdate() {
		return somTiposDeEvaluacionStringUpdate;
	}

	public void setTxtAsuntoUpdate(InputTextarea txtAsuntoUpdate) {
		this.txtAsuntoUpdate = txtAsuntoUpdate;
	}

	public void setDateFechaInicioEvaluacionUpdate(Calendar dateFechaInicioEvaluacionUpdate) {
		this.dateFechaInicioEvaluacionUpdate = dateFechaInicioEvaluacionUpdate;
	}

	public void setDateFechaFinEvaluacionUpdate(Calendar dateFechaFinEvaluacionUpdate) {
		this.dateFechaFinEvaluacionUpdate = dateFechaFinEvaluacionUpdate;
	}

	public void setSomCortesStringUpdate(String somCortesStringUpdate) {
		this.somCortesStringUpdate = somCortesStringUpdate;
	}

	public void setSomTiposDeEvaluacionStringUpdate(String somTiposDeEvaluacionStringUpdate) {
		this.somTiposDeEvaluacionStringUpdate = somTiposDeEvaluacionStringUpdate;
	}

	public String getAsuntoString() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		EvaluacionDTO evaluacionMostrar = (EvaluacionDTO) session.getAttribute("evaluacionMostrar");

		
		asuntoString = evaluacionMostrar.getAsunto();
		fechaInicioString = evaluacionMostrar.getFechaInicioPublicacionString();
		fechaFinString = evaluacionMostrar.getFechaFinPublicacionString();
		corteString = evaluacionMostrar.getCorte().toString();
		tipoEvaluacionString = evaluacionMostrar.getTipoEvaluacion();
		grupoAsignaturaString = evaluacionMostrar.getNombreGrupoYAsignatura();

		return asuntoString;
	}

	public String getFechaInicioString() {
		return fechaInicioString;
	}

	public String getFechaFinString() {
		return fechaFinString;
	}

	public String getCorteString() {
		return corteString;
	}

	public String getTipoEvaluacionString() {
		return tipoEvaluacionString;
	}

	public String getGrupoAsignaturaString() {
		return grupoAsignaturaString;
	}

	public void setAsuntoString(String asuntoString) {
		this.asuntoString = asuntoString;
	}

	public void setFechaInicioString(String fechaInicioString) {
		this.fechaInicioString = fechaInicioString;
	}

	public void setFechaFinString(String fechaFinString) {
		this.fechaFinString = fechaFinString;
	}

	public void setCorteString(String corteString) {
		this.corteString = corteString;
	}

	public void setTipoEvaluacionString(String tipoEvaluacionString) {
		this.tipoEvaluacionString = tipoEvaluacionString;
	}

	public void setGrupoAsignaturaString(String grupoAsignaturaString) {
		this.grupoAsignaturaString = grupoAsignaturaString;
	}

	public List<EvaluacionDTO> getLasEvaluacionesDelGrupo() {
		
		try {
			
			if(lasEvaluacionesDelGrupo == null)
			{
				lasEvaluacionesDelGrupo = new ArrayList<>();
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				GrupoDTO elGrupo = (GrupoDTO) session.getAttribute("grupoId");
				lasEvaluacionesDelGrupo = businessDelegatorView.obtenerEvaluacionesDelGrupoDisponiblesEnFechaHoy(elGrupo.getGrupId());				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lasEvaluacionesDelGrupo;
	}

	public void setLasEvaluacionesDelGrupo(
			List<EvaluacionDTO> lasEvaluacionesDelGrupo) {
		this.lasEvaluacionesDelGrupo = lasEvaluacionesDelGrupo;
	}

	public String getAsuntoEvaluacion() {
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EvaluacionDTO evaluacionMostrar = (EvaluacionDTO) session.getAttribute("preguntaDeEvaluacion");
		
		asuntoEvaluacion = evaluacionMostrar.getAsunto();
		fechaInicioEvaluacion = evaluacionMostrar.getFechaInicioPublicacionString();
		fechaFinEvaluacion = evaluacionMostrar.getFechaFinPublicacionString();
		corteEvaluacion = evaluacionMostrar.getCorte().toString();
		
		return asuntoEvaluacion;
	}

	public void setAsuntoEvaluacion(String asuntoEvaluacion) {
		this.asuntoEvaluacion = asuntoEvaluacion;
	}

	public String getFechaInicioEvaluacion() {
		return fechaInicioEvaluacion;
	}

	public void setFechaInicioEvaluacion(String fechaInicioEvaluacion) {
		this.fechaInicioEvaluacion = fechaInicioEvaluacion;
	}

	public String getFechaFinEvaluacion() {
		return fechaFinEvaluacion;
	}

	public void setFechaFinEvaluacion(String fechaFinEvaluacion) {
		this.fechaFinEvaluacion = fechaFinEvaluacion;
	}

	public String getCorteEvaluacion() {
		return corteEvaluacion;
	}

	public void setCorteEvaluacion(String corteEvaluacion) {
		this.corteEvaluacion = corteEvaluacion;
	}

	public void activarInactivarEvaluacion(ActionEvent evt) {
		EvaluacionDTO evaluacionInactivar = (EvaluacionDTO) (evt.getComponent().getAttributes()
				.get("evaluacionInactivar"));
		try {
			Evaluacion evaluacion = businessDelegatorView.getEvaluacion(evaluacionInactivar.getEvalId());
			if (evaluacion.getEstadoRegistro().trim().equals("A")) {
				evaluacion.setEstadoRegistro("I");
				businessDelegatorView.updateEvaluacion(evaluacion);
				FacesUtils.addInfoMessage("El Documento ha sido INACTIVADO");
				lasEvaluacionesDelDocente = null;
			} else {
				evaluacion.setEstadoRegistro("A");
				businessDelegatorView.updateEvaluacion(evaluacion);
				FacesUtils.addInfoMessage("El Documento ha sido ACTIVADO");
				lasEvaluacionesDelDocente = null;
			}
			getLasEvaluacionesDelDocente();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String abrirDialogoModificarEvaluacion(ActionEvent evt) {
		evaluacionModifica = (EvaluacionDTO) (evt.getComponent().getAttributes().get("evaluacionModifica"));

		txtAsuntoUpdate.setDisabled(false);
		txtAsuntoUpdate.setValue(evaluacionModifica.getAsunto());

		dateFechaInicioEvaluacionUpdate.setDisabled(false);
		dateFechaInicioEvaluacionUpdate.setValue(evaluacionModifica.getFechaInicioPublicacion());

		dateFechaFinEvaluacionUpdate.setDisabled(false);
		dateFechaFinEvaluacionUpdate.setValue(evaluacionModifica.getFechaFinPublicacion());

		somCortesStringUpdate = evaluacionModifica.getCorte().toString();

		somTiposDeEvaluacionStringUpdate = evaluacionModifica.getTievId_TipoEvaluacion().toString();
		btnModify.setDisabled(false);
		setShowDlg(true);

		return "";
	}

	public String modificarEvaluacion() {
		Evaluacion evaluacion;
		DetalleEvaluacion detalleEvaluacion;
		Usuario usuario;
		try {
			evaluacion = businessDelegatorView.getEvaluacion(evaluacionModifica.getEvalId());

			evaluacion.setTipoEvaluacion(
					businessDelegatorView.getTipoEvaluacion(Long.parseLong(somTiposDeEvaluacionStringUpdate)));

			detalleEvaluacion = businessDelegatorView.obtenerDetalleEvaluacionDadoIdEvaluacion(evaluacion);
			detalleEvaluacion.setAsunto(txtAsuntoUpdate.getValue().toString());
			detalleEvaluacion.setCorte(Long.parseLong(somCortesStringUpdate));
			detalleEvaluacion.setFechaInicioPublicacion(FacesUtils.checkDate(dateFechaInicioEvaluacionUpdate));
			detalleEvaluacion.setFechaFinPublicacion(FacesUtils.checkDate(dateFechaFinEvaluacionUpdate));

			businessDelegatorView.actualizarUnaEvaluacion(evaluacion, detalleEvaluacion);
			FacesUtils.addInfoMessage("El documento ha sido actualizado exitosamente");
			lasEvaluacionesDelDocente = null;
			limpiarPantallaModificarEvaluacion();

		} catch (Exception e) {
			evaluacion = null;
			detalleEvaluacion = null;
			usuario = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void limpiarPantallaModificarEvaluacion() {
		txtAsuntoUpdate.setDisabled(true);
		dateFechaInicioEvaluacionUpdate.setDisabled(true);
		dateFechaFinEvaluacionUpdate.setDisabled(true);
		somCortesStringUpdate = "0";
		somTiposDeEvaluacionStringUpdate = "0";
		btnModify.setDisabled(true);
	}

	public String cerrarDialogoModificarEvaluacion() {
		getLasEvaluacionesDelDocente();
		setShowDlg(false);
		return "";
	}
	
	public String abrirPantallaMostrarPreguntasEvaluacion(ActionEvent evt) {
		try {
			
			EvaluacionDTO evaluacionMostrar = (EvaluacionDTO) (evt.getComponent().getAttributes().get("evaluacionMostrar"));
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("evaluacionMostrar", evaluacionMostrar);
			
			/*Metodo para redireccionar a cualquier parte de la app**/
			
			//FacesUtils.resetManagedBean("preguntaForoView");
			
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.redirect(context.getRequestContextPath()
					+ "/Presentation/verPreguntasEvaluacion.xhtml");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return "";
	}
	
	public String imprimirEvaluacion(ActionEvent evt) {
		try {
			
			EvaluacionDTO evaluacionImprimir = (EvaluacionDTO) (evt.getComponent().getAttributes().get("evaluacionImprimir"));
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("evaluacionImprimir", evaluacionImprimir);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return "";
	}
	
	public StreamedContent getImprimirPdf() {
		try {
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			EvaluacionDTO evaluacionImprimir = (EvaluacionDTO) session.getAttribute("evaluacionImprimir");

  			ByteArrayInputStream bais = businessDelegatorView.generarReporteEvaluacion(Constantes.REPORT_OUTPUT_TYPE.PDF, evaluacionImprimir.getEvalId());
  			fileReporteGenerado = new DefaultStreamedContent(bais, "application/pdf", evaluacionImprimir.getAsunto()+"-" + new Date().getTime() + ".pdf");
  			return fileReporteGenerado;
		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public List<PreguntaDTO> getLasPreguntasDeLaEvaluacion() {
		try {
			if(lasPreguntasDeLaEvaluacion==null){
				lasPreguntasDeLaEvaluacion = new ArrayList<>();
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				EvaluacionDTO evaluacionMostrar = (EvaluacionDTO) session.getAttribute("evaluacionMostrar");
				Evaluacion evaluacion = businessDelegatorView.getEvaluacion(evaluacionMostrar.getEvalId());
				lasPreguntasDeLaEvaluacion = businessDelegatorView.consultarPreguntaDadoIdEval(evaluacion.getEvalId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lasPreguntasDeLaEvaluacion;
	}

	public void setLasPreguntasDeLaEvaluacion(List<PreguntaDTO> lasPreguntasDeLaEvaluacion) {
		this.lasPreguntasDeLaEvaluacion = lasPreguntasDeLaEvaluacion;
	}
	
	public List<PreguntaDTO> getPreguntasEvaluacionEstudiante() {
		try {
			if(preguntasEvaluacionEstudiante==null){
				preguntasEvaluacionEstudiante = new ArrayList<>();
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				EvaluacionDTO evaluacionMostrar = (EvaluacionDTO) session.getAttribute("preguntaDeEvaluacion");
				Evaluacion evaluacion = businessDelegatorView.getEvaluacion(evaluacionMostrar.getEvalId());
				preguntasEvaluacionEstudiante = businessDelegatorView.consultarPreguntaDadoIdEval(evaluacion.getEvalId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return preguntasEvaluacionEstudiante;
	}

	public void setPreguntasEvaluacionEstudiante(
			List<PreguntaDTO> preguntasEvaluacionEstudiante) {
		this.preguntasEvaluacionEstudiante = preguntasEvaluacionEstudiante;
	}

	public void refrescarPantalla(){
		lasPreguntasDeLaEvaluacion = null;
		getLasPreguntasDeLaEvaluacion();
	}
	
	public String abrirPantallaPreguntaEvaluacionGrupo(ActionEvent evt) {
		try {
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			EvaluacionDTO evaluacionGrupo = (EvaluacionDTO) (evt.getComponent().getAttributes().get("preguntaDeEvaluacion"));

			session.setAttribute("preguntaDeEvaluacion", evaluacionGrupo);			
			
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.redirect(context.getRequestContextPath()
					+ "/Presentation/verPreguntasDeLaEvaluacionDelGrupo.xhtml");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return "";
	}

	public StreamedContent getFileReporteGenerado() {
		return fileReporteGenerado;
	}

	public void setFileReporteGenerado(StreamedContent fileReporteGenerado) {
		this.fileReporteGenerado = fileReporteGenerado;
	}

}
