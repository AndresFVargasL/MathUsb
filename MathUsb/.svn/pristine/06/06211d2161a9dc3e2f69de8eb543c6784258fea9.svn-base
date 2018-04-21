package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.GrupoDTO;
import co.edu.usbcali.mathusb.modelo.dto.TemaDTO;
import co.edu.usbcali.mathusb.modelo.dto.UsuarioDTO;
import co.edu.usbcali.mathusb.presentation.businessDelegate.*;
import co.edu.usbcali.mathusb.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class GrupoView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(GrupoView.class);
	private InputText txtDescripcionGrupo;
	private InputText txtAsigId_Asignatura;
	private InputText txtGrupId;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<GrupoDTO> data;
	private GrupoDTO selectedGrupo;
	private Grupo entity;
	private boolean showDialog;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private List<SelectItem> lasAsignaturas;
	private String somAsignaturasString;
	private InputText txtDescripcionGrupoCrear;
	private CommandButton btnGuardarNuevoGrupo;
	private CommandButton btnLimpiarPantallaNuevoGrupo;
	private InputText txtModificaDescripcionGrupo;
	
	private List<GrupoDTO> losGruposAsignaturaDocente;
	private List<GrupoDTO> losGruposDelEstudiante;
	private List<GrupoDTO> losGruposDelDocente;
	private List<UsuarioDTO> losEstudiantesDelGrupo;
	private String detalleGrupo;
	private boolean showDlg;
	
	private GrupoDTO grupoModifica;
	private String descripcionGrupo;
	

	public GrupoView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			GrupoDTO grupoDTO = (GrupoDTO) e.getObject();

			if (txtDescripcionGrupo == null) {
				txtDescripcionGrupo = new InputText();
			}

			txtDescripcionGrupo.setValue(grupoDTO.getDescripcionGrupo());

			if (txtAsigId_Asignatura == null) {
				txtAsigId_Asignatura = new InputText();
			}

			txtAsigId_Asignatura.setValue(grupoDTO.getAsigId_Asignatura());

			if (txtGrupId == null) {
				txtGrupId = new InputText();
			}

			txtGrupId.setValue(grupoDTO.getGrupId());

			Long grupId = FacesUtils.checkLong(txtGrupId);
			entity = businessDelegatorView.getGrupo(grupId);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedGrupo = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedGrupo = null;

		if (txtDescripcionGrupo != null) {
			txtDescripcionGrupo.setValue(null);
			txtDescripcionGrupo.setDisabled(true);
		}

		if (txtAsigId_Asignatura != null) {
			txtAsigId_Asignatura.setValue(null);
			txtAsigId_Asignatura.setDisabled(true);
		}

		if (txtGrupId != null) {
			txtGrupId.setValue(null);
			txtGrupId.setDisabled(false);
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
			Long grupId = FacesUtils.checkLong(txtGrupId);
			entity = (grupId != null) ? businessDelegatorView.getGrupo(grupId) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtDescripcionGrupo.setDisabled(false);
			txtAsigId_Asignatura.setDisabled(false);
			txtGrupId.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtDescripcionGrupo.setValue(entity.getDescripcionGrupo());
			txtDescripcionGrupo.setDisabled(false);
			txtAsigId_Asignatura.setValue(entity.getAsignatura().getAsigId());
			txtAsigId_Asignatura.setDisabled(false);
			txtGrupId.setValue(entity.getGrupId());
			txtGrupId.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedGrupo = (GrupoDTO) (evt.getComponent().getAttributes().get("selectedGrupo"));
		txtDescripcionGrupo.setValue(selectedGrupo.getDescripcionGrupo());
		txtDescripcionGrupo.setDisabled(false);
		txtAsigId_Asignatura.setValue(selectedGrupo.getAsigId_Asignatura());
		txtAsigId_Asignatura.setDisabled(false);
		txtGrupId.setValue(selectedGrupo.getGrupId());
		txtGrupId.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedGrupo == null) && (entity == null)) {
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
			entity = new Grupo();

			Long grupId = FacesUtils.checkLong(txtGrupId);

			entity.setDescripcionGrupo(FacesUtils.checkString(txtDescripcionGrupo));
			entity.setGrupId(grupId);
			entity.setAsignatura((FacesUtils.checkLong(txtAsigId_Asignatura) != null)
					? businessDelegatorView.getAsignatura(FacesUtils.checkLong(txtAsigId_Asignatura)) : null);
			businessDelegatorView.saveGrupo(entity);
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
				Long grupId = new Long(selectedGrupo.getGrupId());
				entity = businessDelegatorView.getGrupo(grupId);
			}

			entity.setDescripcionGrupo(FacesUtils.checkString(txtDescripcionGrupo));
			entity.setAsignatura((FacesUtils.checkLong(txtAsigId_Asignatura) != null)
					? businessDelegatorView.getAsignatura(FacesUtils.checkLong(txtAsigId_Asignatura)) : null);
			businessDelegatorView.updateGrupo(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedGrupo = (GrupoDTO) (evt.getComponent().getAttributes().get("selectedGrupo"));

			Long grupId = new Long(selectedGrupo.getGrupId());
			entity = businessDelegatorView.getGrupo(grupId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long grupId = FacesUtils.checkLong(txtGrupId);
			entity = businessDelegatorView.getGrupo(grupId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteGrupo(entity);
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
			selectedGrupo = (GrupoDTO) (evt.getComponent().getAttributes().get("selectedGrupo"));

			Long grupId = new Long(selectedGrupo.getGrupId());
			entity = businessDelegatorView.getGrupo(grupId);
			businessDelegatorView.deleteGrupo(entity);
			data.remove(selectedGrupo);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String descripcionGrupo, Long grupId, Long asigId_Asignatura) throws Exception {
		try {
			entity.setDescripcionGrupo(FacesUtils.checkString(descripcionGrupo));
			businessDelegatorView.updateGrupo(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("GrupoView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public String guardarNuevoGrupo() {
		try {
			Grupo grupo = new Grupo();
			grupo.setGrupId(null);
			grupo.setDescripcionGrupo(FacesUtils.checkString(txtDescripcionGrupoCrear));
			grupo.setAsignatura(businessDelegatorView.getAsignatura(Long.parseLong(somAsignaturasString)));
			businessDelegatorView.saveGrupo(grupo);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			limpiarPantallaCrearGrupo();
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void matricularEstudianteEnUnGrupo(ActionEvent evt) {
		GrupoDTO grupoSeleccionado = (GrupoDTO) (evt.getComponent().getAttributes().get("grupoSeleccionado"));
		try {
			businessDelegatorView.matricularEstudianteEnUnGrupo(grupoSeleccionado);
			FacesUtils.addInfoMessage("Has quedado matriculado en el curso " + grupoSeleccionado.getDescripcionGrupo());
			getLosGruposAsignaturaDocente();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void desmatricularEstudianteEnUnGrupo(ActionEvent evt) {
		GrupoDTO grupoSeleccionado = (GrupoDTO) (evt.getComponent().getAttributes().get("grupoBorrar"));
		try {
			businessDelegatorView.desmatricularEstudianteEnUnGrupo(grupoSeleccionado);
			FacesUtils.addInfoMessage("Has quedado fuera del curso " + grupoSeleccionado.getDescripcionGrupo());
			getLosGruposDelEstudiante();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void mostrarListaDeEstudiantes(ActionEvent evt) {
		GrupoDTO grupoLista = (GrupoDTO) (evt.getComponent().getAttributes().get("grupoLista"));
		try {
			Long idGrupo = grupoLista.getGrupId();
			detalleGrupo = grupoLista.getDescripcionGrupo();
			losEstudiantesDelGrupo = businessDelegatorView.obtenerListaDeEstudiantesDadoIdGrupo(idGrupo);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String limpiarPantallaCrearGrupo() {
		txtDescripcionGrupoCrear.setValue(null);
		somAsignaturasString = "0";
		return "";
	}
	
	public String abrirDialogoMofificarNombreGrupo(ActionEvent evt) {
		grupoModifica = (GrupoDTO) (evt.getComponent().getAttributes().get("grupoModifica"));
        txtModificaDescripcionGrupo.setDisabled(false);
		txtModificaDescripcionGrupo.setValue(grupoModifica.getDescripcionGrupo());
		btnModify.setDisabled(false);
        setShowDlg(true);

        return "";
    }
	
	public String modificarGrupo(){
        try {
			Grupo grupo = businessDelegatorView.getGrupo(grupoModifica.getGrupId());
			grupo.setDescripcionGrupo(txtModificaDescripcionGrupo.getValue().toString());
			businessDelegatorView.updateGrupo(grupo);
			FacesUtils.addInfoMessage("Se ha actualizado el nombre del grupo a: "+grupo.getDescripcionGrupo());
			txtModificaDescripcionGrupo.setDisabled(true);
			btnModify.setDisabled(true);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
        return "";
	}

	public String cerrarDialogoModificarNombreGrupo() {
        setShowDlg(false);
        txtModificaDescripcionGrupo.setValue(null);     
        return "";
    }
	
	public InputText getTxtDescripcionGrupo() {		
		return txtDescripcionGrupo;
	}

	public void setTxtDescripcionGrupo(InputText txtDescripcionGrupo) {
		this.txtDescripcionGrupo = txtDescripcionGrupo;
	}

	public InputText getTxtAsigId_Asignatura() {
		return txtAsigId_Asignatura;
	}

	public void setTxtAsigId_Asignatura(InputText txtAsigId_Asignatura) {
		this.txtAsigId_Asignatura = txtAsigId_Asignatura;
	}

	public InputText getTxtGrupId() {
		return txtGrupId;
	}

	public void setTxtGrupId(InputText txtGrupId) {
		this.txtGrupId = txtGrupId;
	}

	public List<GrupoDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataGrupo();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<GrupoDTO> grupoDTO) {
		this.data = grupoDTO;
	}

	public GrupoDTO getSelectedGrupo() {
		return selectedGrupo;
	}

	public void setSelectedGrupo(GrupoDTO grupo) {
		this.selectedGrupo = grupo;
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

	public List<SelectItem> getLasAsignaturas() {
		try {
			if (lasAsignaturas == null) {
				lasAsignaturas = new ArrayList<SelectItem>();
				List<Asignatura> asignaturas = businessDelegatorView.obtenerAsignaturasOrdenadasPorNombre();
				for (Asignatura asignatura : asignaturas) {
					SelectItem selectItem = new SelectItem(asignatura.getAsigId(), asignatura.getNombreAsignatura());
					lasAsignaturas.add(selectItem);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lasAsignaturas;
	}

	public void setLasAsignaturas(List<SelectItem> lasAsignaturas) {
		this.lasAsignaturas = lasAsignaturas;
	}

	public String getSomAsignaturasString() {
		return somAsignaturasString;
	}

	public void setSomAsignaturasString(String somAsignaturasString) {
		this.somAsignaturasString = somAsignaturasString;
	}

	public InputText getTxtDescripcionGrupoCrear() {
		return txtDescripcionGrupoCrear;
	}

	public void setTxtDescripcionGrupoCrear(InputText txtDescripcionGrupoCrear) {
		this.txtDescripcionGrupoCrear = txtDescripcionGrupoCrear;
	}

	public CommandButton getBtnGuardarNuevoGrupo() {
		return btnGuardarNuevoGrupo;
	}

	public void setBtnGuardarNuevoGrupo(CommandButton btnGuardarNuevoGrupo) {
		this.btnGuardarNuevoGrupo = btnGuardarNuevoGrupo;
	}

	public CommandButton getBtnLimpiarPantallaNuevoGrupo() {
		return btnLimpiarPantallaNuevoGrupo;
	}

	public void setBtnLimpiarPantallaNuevoGrupo(CommandButton btnLimpiarPantallaNuevoGrupo) {
		this.btnLimpiarPantallaNuevoGrupo = btnLimpiarPantallaNuevoGrupo;
	}

	public InputText getTxtModificaDescripcionGrupo() {
		return txtModificaDescripcionGrupo;
	}

	public void setTxtModificaDescripcionGrupo(InputText txtModificaDescripcionGrupo) {
		this.txtModificaDescripcionGrupo = txtModificaDescripcionGrupo;
	}

	public List<GrupoDTO> getLosGruposAsignaturaDocente() {
		try {
			losGruposAsignaturaDocente = businessDelegatorView.traerGruposConDocentesYNombreAsignatura();
		} catch (Exception e) {
			FacesUtils.addErrorMessage("No se han cargado los grupos");
		}

		return losGruposAsignaturaDocente;
	}

	public void setLosGruposAsignaturaDocente(List<GrupoDTO> losGruposAsignaturaDocente) {
		this.losGruposAsignaturaDocente = losGruposAsignaturaDocente;
	}

	public List<GrupoDTO> getLosGruposDelEstudiante() {
		try {
			losGruposDelEstudiante = businessDelegatorView.listaDeGruposDelEstudiante();
		} catch (Exception e) {
			FacesUtils.addErrorMessage("No se han cargado los grupos");
		}
		return losGruposDelEstudiante;
	}

	public void setLosGruposDelEstudiante(List<GrupoDTO> losGruposDelEstudiante) {
		this.losGruposDelEstudiante = losGruposDelEstudiante;
	}

	public List<GrupoDTO> getLosGruposDelDocente() {
		try {
			losGruposDelDocente = businessDelegatorView.listaDeGruposDelDocente();
		} catch (Exception e) {
			FacesUtils.addErrorMessage("No se han cargado los grupos");
		}
		return losGruposDelDocente;
	}

	public void setLosGruposDelDocente(List<GrupoDTO> losGruposDelDocente) {
		this.losGruposDelDocente = losGruposDelDocente;
	}

	public List<UsuarioDTO> getLosEstudiantesDelGrupo() {
		return losEstudiantesDelGrupo;
	}

	public void setLosEstudiantesDelGrupo(List<UsuarioDTO> losEstudiantesDelGrupo) {
		this.losEstudiantesDelGrupo = losEstudiantesDelGrupo;
	}

	public String getDetalleGrupo() {
		return detalleGrupo;
	}

	public void setDetalleGrupo(String detalleGrupo) {
		this.detalleGrupo = detalleGrupo;
	}

	public boolean isShowDlg() {
		return showDlg;
	}

	public void setShowDlg(boolean showDlg) {
		this.showDlg = showDlg;
	}	
	
	public String getDescripcionGrupo() {
		
		try {		
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			GrupoDTO elGrupo = (GrupoDTO) session.getAttribute("grupoId");			
			descripcionGrupo = elGrupo.getDescripcionGrupo();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return descripcionGrupo;
	}

	public void setDescripcionGrupo(String descripcionGrupo) {
		this.descripcionGrupo = descripcionGrupo;
	}

	public String abrirPantallaEvaluacionesGrupo(ActionEvent evt)
	{
		try {
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			GrupoDTO grupoDTO = (GrupoDTO) (evt.getComponent().getAttributes().get("grupoId"));
			
			session.setAttribute("grupoId", grupoDTO);
			
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.redirect(context.getRequestContextPath()
					+ "/Presentation/verEvaluacionesDelGrupo.xhtml");
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return "";		
		
	}
}
