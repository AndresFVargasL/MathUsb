package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.GrupoDTO;
import co.edu.usbcali.mathusb.modelo.dto.HerramientaDTO;
import co.edu.usbcali.mathusb.modelo.dto.UsuarioDTO;
import co.edu.usbcali.mathusb.presentation.businessDelegate.*;
import co.edu.usbcali.mathusb.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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
public class HerramientaView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(HerramientaView.class);
	private InputText txtDescripcionHerramienta;
	private InputText txtEstadoRegistro;
	private InputText txtValorHerramienta;
	private InputText txtTiheId_TipoHerramienta;
	private InputText txtUsuaId_Usuario;
	private InputText txtHerrId;
	private Calendar txtFechaPublicacion;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<HerramientaDTO> data;
	private HerramientaDTO selectedHerramienta;
	private Herramienta entity;
	private boolean showDialog;

	private InputTextarea txtTituloHerramienta;
	private InputTextarea txtURL;
	private String somTiposDeHerramientas;
	private List<SelectItem> losTiposDeHerramientas;

	private CommandButton btnGuardarNuevaHerramienta;
	private CommandButton btnLimpiarPantallaNuevaHerramienta;

	private List<HerramientaDTO> herramientasOrdenadas;
	private List<HerramientaDTO> herramientasDelUsuario;

	private boolean showDlg;
	private HerramientaDTO herramientaModifica;
	private InputText txtModificaDescripcionHerramienta, txtUrlHerramienta;
	
	
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public HerramientaView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			HerramientaDTO herramientaDTO = (HerramientaDTO) e.getObject();

			if (txtDescripcionHerramienta == null) {
				txtDescripcionHerramienta = new InputText();
			}

			txtDescripcionHerramienta.setValue(herramientaDTO.getDescripcionHerramienta());

			if (txtEstadoRegistro == null) {
				txtEstadoRegistro = new InputText();
			}

			txtEstadoRegistro.setValue(herramientaDTO.getEstadoRegistro());

			if (txtValorHerramienta == null) {
				txtValorHerramienta = new InputText();
			}

			txtValorHerramienta.setValue(herramientaDTO.getValorHerramienta());

			if (txtTiheId_TipoHerramienta == null) {
				txtTiheId_TipoHerramienta = new InputText();
			}

			txtTiheId_TipoHerramienta.setValue(herramientaDTO.getTiheId_TipoHerramienta());

			if (txtUsuaId_Usuario == null) {
				txtUsuaId_Usuario = new InputText();
			}

			txtUsuaId_Usuario.setValue(herramientaDTO.getUsuaId_Usuario());

			if (txtHerrId == null) {
				txtHerrId = new InputText();
			}

			txtHerrId.setValue(herramientaDTO.getHerrId());

			if (txtFechaPublicacion == null) {
				txtFechaPublicacion = new Calendar();
			}

			txtFechaPublicacion.setValue(herramientaDTO.getFechaPublicacion());

			Long herrId = FacesUtils.checkLong(txtHerrId);
			entity = businessDelegatorView.getHerramienta(herrId);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedHerramienta = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedHerramienta = null;

		if (txtDescripcionHerramienta != null) {
			txtDescripcionHerramienta.setValue(null);
			txtDescripcionHerramienta.setDisabled(true);
		}

		if (txtEstadoRegistro != null) {
			txtEstadoRegistro.setValue(null);
			txtEstadoRegistro.setDisabled(true);
		}

		if (txtValorHerramienta != null) {
			txtValorHerramienta.setValue(null);
			txtValorHerramienta.setDisabled(true);
		}

		if (txtTiheId_TipoHerramienta != null) {
			txtTiheId_TipoHerramienta.setValue(null);
			txtTiheId_TipoHerramienta.setDisabled(true);
		}

		if (txtUsuaId_Usuario != null) {
			txtUsuaId_Usuario.setValue(null);
			txtUsuaId_Usuario.setDisabled(true);
		}

		if (txtFechaPublicacion != null) {
			txtFechaPublicacion.setValue(null);
			txtFechaPublicacion.setDisabled(true);
		}

		if (txtHerrId != null) {
			txtHerrId.setValue(null);
			txtHerrId.setDisabled(false);
		}

		if (btnSave != null) {
			btnSave.setDisabled(true);
		}

		if (btnDelete != null) {
			btnDelete.setDisabled(true);
		}

		return "";
	}

	public void listener_txtFechaPublicacion() {
		Date inputDate = (Date) txtFechaPublicacion.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
	}

	public void listener_txtId() {
		try {
			Long herrId = FacesUtils.checkLong(txtHerrId);
			entity = (herrId != null) ? businessDelegatorView.getHerramienta(herrId) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtDescripcionHerramienta.setDisabled(false);
			txtEstadoRegistro.setDisabled(false);
			txtValorHerramienta.setDisabled(false);
			txtTiheId_TipoHerramienta.setDisabled(false);
			txtUsuaId_Usuario.setDisabled(false);
			txtFechaPublicacion.setDisabled(false);
			txtHerrId.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtDescripcionHerramienta.setValue(entity.getDescripcionHerramienta());
			txtDescripcionHerramienta.setDisabled(false);
			txtEstadoRegistro.setValue(entity.getEstadoRegistro());
			txtEstadoRegistro.setDisabled(false);
			txtFechaPublicacion.setValue(entity.getFechaPublicacion());
			txtFechaPublicacion.setDisabled(false);
			txtValorHerramienta.setValue(entity.getValorHerramienta());
			txtValorHerramienta.setDisabled(false);
			txtTiheId_TipoHerramienta.setValue(entity.getTipoHerramienta().getTiheId());
			txtTiheId_TipoHerramienta.setDisabled(false);
			txtUsuaId_Usuario.setValue(entity.getUsuario().getUsuaId());
			txtUsuaId_Usuario.setDisabled(false);
			txtHerrId.setValue(entity.getHerrId());
			txtHerrId.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedHerramienta = (HerramientaDTO) (evt.getComponent().getAttributes().get("selectedHerramienta"));
		txtDescripcionHerramienta.setValue(selectedHerramienta.getDescripcionHerramienta());
		txtDescripcionHerramienta.setDisabled(false);
		txtEstadoRegistro.setValue(selectedHerramienta.getEstadoRegistro());
		txtEstadoRegistro.setDisabled(false);
		txtFechaPublicacion.setValue(selectedHerramienta.getFechaPublicacion());
		txtFechaPublicacion.setDisabled(false);
		txtValorHerramienta.setValue(selectedHerramienta.getValorHerramienta());
		txtValorHerramienta.setDisabled(false);
		txtTiheId_TipoHerramienta.setValue(selectedHerramienta.getTiheId_TipoHerramienta());
		txtTiheId_TipoHerramienta.setDisabled(false);
		txtUsuaId_Usuario.setValue(selectedHerramienta.getUsuaId_Usuario());
		txtUsuaId_Usuario.setDisabled(false);
		txtHerrId.setValue(selectedHerramienta.getHerrId());
		txtHerrId.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedHerramienta == null) && (entity == null)) {
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
			entity = new Herramienta();

			Long herrId = FacesUtils.checkLong(txtHerrId);

			entity.setDescripcionHerramienta(FacesUtils.checkString(txtDescripcionHerramienta));
			entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
			entity.setFechaPublicacion(FacesUtils.checkDate(txtFechaPublicacion));
			entity.setHerrId(herrId);
			entity.setValorHerramienta(FacesUtils.checkString(txtValorHerramienta));
			entity.setTipoHerramienta((FacesUtils.checkLong(txtTiheId_TipoHerramienta) != null)
					? businessDelegatorView.getTipoHerramienta(FacesUtils.checkLong(txtTiheId_TipoHerramienta)) : null);
			entity.setUsuario((FacesUtils.checkLong(txtUsuaId_Usuario) != null)
					? businessDelegatorView.getUsuario(FacesUtils.checkLong(txtUsuaId_Usuario)) : null);
			businessDelegatorView.saveHerramienta(entity);
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
				Long herrId = new Long(selectedHerramienta.getHerrId());
				entity = businessDelegatorView.getHerramienta(herrId);
			}

			entity.setDescripcionHerramienta(FacesUtils.checkString(txtDescripcionHerramienta));
			entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
			entity.setFechaPublicacion(FacesUtils.checkDate(txtFechaPublicacion));
			entity.setValorHerramienta(FacesUtils.checkString(txtValorHerramienta));
			entity.setTipoHerramienta((FacesUtils.checkLong(txtTiheId_TipoHerramienta) != null)
					? businessDelegatorView.getTipoHerramienta(FacesUtils.checkLong(txtTiheId_TipoHerramienta)) : null);
			entity.setUsuario((FacesUtils.checkLong(txtUsuaId_Usuario) != null)
					? businessDelegatorView.getUsuario(FacesUtils.checkLong(txtUsuaId_Usuario)) : null);
			businessDelegatorView.updateHerramienta(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedHerramienta = (HerramientaDTO) (evt.getComponent().getAttributes().get("selectedHerramienta"));

			Long herrId = new Long(selectedHerramienta.getHerrId());
			entity = businessDelegatorView.getHerramienta(herrId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long herrId = FacesUtils.checkLong(txtHerrId);
			entity = businessDelegatorView.getHerramienta(herrId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteHerramienta(entity);
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
			selectedHerramienta = (HerramientaDTO) (evt.getComponent().getAttributes().get("selectedHerramienta"));

			Long herrId = new Long(selectedHerramienta.getHerrId());
			entity = businessDelegatorView.getHerramienta(herrId);
			businessDelegatorView.deleteHerramienta(entity);
			data.remove(selectedHerramienta);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String descripcionHerramienta, String estadoRegistro, Date fechaPublicacion,
			Long herrId, String valorHerramienta, Long tiheId_TipoHerramienta, Long usuaId_Usuario) throws Exception {
		try {
			entity.setDescripcionHerramienta(FacesUtils.checkString(descripcionHerramienta));
			entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
			entity.setFechaPublicacion(FacesUtils.checkDate(fechaPublicacion));
			entity.setValorHerramienta(FacesUtils.checkString(valorHerramienta));
			businessDelegatorView.updateHerramienta(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("HerramientaView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtDescripcionHerramienta() {
		return txtDescripcionHerramienta;
	}

	public void setTxtDescripcionHerramienta(InputText txtDescripcionHerramienta) {
		this.txtDescripcionHerramienta = txtDescripcionHerramienta;
	}

	public InputText getTxtEstadoRegistro() {
		return txtEstadoRegistro;
	}

	public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
		this.txtEstadoRegistro = txtEstadoRegistro;
	}

	public InputText getTxtValorHerramienta() {
		return txtValorHerramienta;
	}

	public void setTxtValorHerramienta(InputText txtValorHerramienta) {
		this.txtValorHerramienta = txtValorHerramienta;
	}

	public InputText getTxtTiheId_TipoHerramienta() {
		return txtTiheId_TipoHerramienta;
	}

	public void setTxtTiheId_TipoHerramienta(InputText txtTiheId_TipoHerramienta) {
		this.txtTiheId_TipoHerramienta = txtTiheId_TipoHerramienta;
	}

	public InputText getTxtUsuaId_Usuario() {
		return txtUsuaId_Usuario;
	}

	public void setTxtUsuaId_Usuario(InputText txtUsuaId_Usuario) {
		this.txtUsuaId_Usuario = txtUsuaId_Usuario;
	}

	public Calendar getTxtFechaPublicacion() {
		return txtFechaPublicacion;
	}

	public void setTxtFechaPublicacion(Calendar txtFechaPublicacion) {
		this.txtFechaPublicacion = txtFechaPublicacion;
	}

	public InputText getTxtHerrId() {
		return txtHerrId;
	}

	public void setTxtHerrId(InputText txtHerrId) {
		this.txtHerrId = txtHerrId;
	}

	public List<HerramientaDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataHerramienta();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<HerramientaDTO> herramientaDTO) {
		this.data = herramientaDTO;
	}

	public HerramientaDTO getSelectedHerramienta() {
		return selectedHerramienta;
	}

	public void setSelectedHerramienta(HerramientaDTO herramienta) {
		this.selectedHerramienta = herramienta;
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

	public InputTextarea getTxtTituloHerramienta() {
		return txtTituloHerramienta;
	}

	public void setTxtTituloHerramienta(InputTextarea txtTituloHerramienta) {
		this.txtTituloHerramienta = txtTituloHerramienta;
	}

	public InputTextarea getTxtURL() {
		return txtURL;
	}

	public void setTxtURL(InputTextarea txtURL) {
		this.txtURL = txtURL;
	}

	public String getSomTiposDeHerramientas() {
		return somTiposDeHerramientas;
	}

	public void setSomTiposDeHerramientas(String somTiposDeHerramientas) {
		this.somTiposDeHerramientas = somTiposDeHerramientas;
	}

	public List<SelectItem> getLosTiposDeHerramientas() {
		try {
			if (losTiposDeHerramientas == null) {
				List<TipoHerramienta> tiposHerramientas = businessDelegatorView
						.obtenerTiposHerramientasOrdenadasAlfabeticamente();
				losTiposDeHerramientas = new ArrayList<SelectItem>();
				for (TipoHerramienta tipoHerramienta : tiposHerramientas) {
					SelectItem selectItem = new SelectItem(tipoHerramienta.getTiheId(),
							tipoHerramienta.getDescripcionHerramienta());
					losTiposDeHerramientas.add(selectItem);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return losTiposDeHerramientas;
	}

	public void setLosTiposDeHerramientas(List<SelectItem> losTiposDeHerramientas) {
		this.losTiposDeHerramientas = losTiposDeHerramientas;
	}

	public CommandButton getBtnGuardarNuevaHerramienta() {
		return btnGuardarNuevaHerramienta;
	}

	public void setBtnGuardarNuevaHerramienta(CommandButton btnGuardarNuevaHerramienta) {
		this.btnGuardarNuevaHerramienta = btnGuardarNuevaHerramienta;
	}

	public CommandButton getBtnLimpiarPantallaNuevaHerramienta() {
		return btnLimpiarPantallaNuevaHerramienta;
	}

	public void setBtnLimpiarPantallaNuevaHerramienta(CommandButton btnLimpiarPantallaNuevaHerramienta) {
		this.btnLimpiarPantallaNuevaHerramienta = btnLimpiarPantallaNuevaHerramienta;
	}

	public List<HerramientaDTO> getHerramientasOrdenadas() {
		try {
			if (herramientasOrdenadas == null) {
				herramientasOrdenadas = businessDelegatorView.herramientasOrdenadasPorFecha();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return herramientasOrdenadas;
	}

	public void setHerramientasOrdenadas(List<HerramientaDTO> herramientasOrdenadas) {
		this.herramientasOrdenadas = herramientasOrdenadas;
	}

	public List<HerramientaDTO> getHerramientasDelUsuario() {
		try {
			if (herramientasDelUsuario == null) {
				herramientasDelUsuario = businessDelegatorView.herramientasDelUsuarioOrdenadasPorFecha();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return herramientasDelUsuario;
	}

	public void setHerramientasDelUsuario(List<HerramientaDTO> herramientasDelUsuario) {
		this.herramientasDelUsuario = herramientasDelUsuario;
	}

	public boolean isShowDlg() {
		return showDlg;
	}

	public void setShowDlg(boolean showDlg) {
		this.showDlg = showDlg;
	}

	public HerramientaDTO getHerramientaModifica() {
		return herramientaModifica;
	}

	public void setHerramientaModifica(HerramientaDTO herramientaModifica) {
		this.herramientaModifica = herramientaModifica;
	}

	public InputText getTxtModificaDescripcionHerramienta() {
		return txtModificaDescripcionHerramienta;
	}

	public void setTxtModificaDescripcionHerramienta(InputText txtModificaDescripcionHerramienta) {
		this.txtModificaDescripcionHerramienta = txtModificaDescripcionHerramienta;
	}

	public InputText getTxtUrlHerramienta() {
		return txtUrlHerramienta;
	}

	public void setTxtUrlHerramienta(InputText txtUrlHerramienta) {
		this.txtUrlHerramienta = txtUrlHerramienta;
	}

	public void guardarNuevaHerramienta() {
		try {
			Herramienta herramienta = new Herramienta();
			herramienta.setDescripcionHerramienta(txtTituloHerramienta.getValue().toString());
			herramienta.setEstadoRegistro("A");
			herramienta.setFechaPublicacion(new Date());
			herramienta.setHerrId(null);
			herramienta.setTipoHerramienta(
					businessDelegatorView.getTipoHerramienta(Long.parseLong(somTiposDeHerramientas)));
			herramienta.setValorHerramienta(txtURL.getValue().toString());

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			com.vortexbird.seguridad.modelo.dto.UsuarioDTO loginUsuario = (com.vortexbird.seguridad.modelo.dto.UsuarioDTO) session
					.getAttribute("usuarioDTO");
			Usuario docente = businessDelegatorView.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());

			herramienta.setUsuario(docente);
			businessDelegatorView.saveHerramienta(herramienta);
			FacesUtils.addInfoMessage("La herramienta ha sido registrada");
			limpiarPantalla();
			herramientasDelUsuario = null;
			getHerramientasDelUsuario();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiarPantalla() {
		txtTituloHerramienta.setValue(null);
		txtURL.setValue(null);
		somTiposDeHerramientas = "0";
	}
	
	public void activarInactivarHerramienta(ActionEvent evt) {
		HerramientaDTO herramientaInactivar = (HerramientaDTO) (evt.getComponent().getAttributes().get("herramientaInactivar"));
		try {
			Herramienta herramienta = businessDelegatorView.getHerramienta(herramientaInactivar.getHerrId());
			if(herramienta.getEstadoRegistro().trim().equals("A")){
				herramienta.setEstadoRegistro("I");
				businessDelegatorView.updateHerramienta(herramienta);
				FacesUtils.addInfoMessage("La herramienta ha sido inactivada");
				herramientasDelUsuario = null;
			}else{
				herramienta.setEstadoRegistro("A");
				businessDelegatorView.updateHerramienta(herramienta);
				FacesUtils.addInfoMessage("La herramienta ha sido activada");
				herramientasDelUsuario = null;
			}
			getHerramientasDelUsuario();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public String abrirDialogoMofificarHerramienta(ActionEvent evt){
		herramientaModifica = (HerramientaDTO) (evt.getComponent().getAttributes().get("herramientaModifica"));
		setShowDlg(true);
		txtModificaDescripcionHerramienta.setValue(herramientaModifica.getDescripcionHerramienta());
		txtUrlHerramienta.setValue(herramientaModifica.getValorHerramienta());
		return "";
	}
	
	public String modificarHerramienta(){
		try {
			Herramienta herramienta = businessDelegatorView.getHerramienta(herramientaModifica.getHerrId());
			herramienta.setDescripcionHerramienta(txtModificaDescripcionHerramienta.getValue().toString());
			businessDelegatorView.updateHerramienta(herramienta);
			FacesUtils.addInfoMessage("Se ha actualizado el título de la Herramienta a: "+herramienta.getDescripcionHerramienta());
			txtModificaDescripcionHerramienta.setDisabled(true);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}
	
	public String cerrarDialogoModificarDescripcionHerramienta() {
        setShowDlg(false);
        txtModificaDescripcionHerramienta.setValue(null);
        txtModificaDescripcionHerramienta.setDisabled(false);
        txtUrlHerramienta.setValue(null);
        herramientasDelUsuario = null;
        return "";
    }
}
