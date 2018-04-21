package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.UsuarioDTO;
import co.edu.usbcali.mathusb.presentation.businessDelegate.*;
import co.edu.usbcali.mathusb.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
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
import javax.servlet.http.HttpSession;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class UsuarioView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UsuarioView.class);
	private InputText txtApellidoUsuario;
	private InputText txtCodigoUsuario;
	private InputText txtEmailUsuario;
	private InputText txtEstadoRegistro;
	private InputText txtNombreUsuario;
	private InputText txtPasswordUsuario;
	private InputText txtTiusId_TipoUsuario;
	private InputText txtUsuaId;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<UsuarioDTO> data;
	private UsuarioDTO selectedUsuario;
	private Usuario entity;
	private boolean showDialog;

	private InputText txtApellidoDocente;
	private InputText txtNombreDocente;
	private InputText txtCodigoDocente;
	private InputText txtEmailDocente;
	private Password pswPasswordDocente;
	private Password pswPasswordDocenteConfirma;
	private CommandButton btnRegistrarDocente;
	private CommandButton btnLimpiarPantallaRegistroDocente;
	
	private InputText txtApellidoEstudiante;
	private InputText txtNombreEstudiante;
	private InputText txtCodigoEstudiante;
	private InputText txtEmailEstudiante;
	private Password pswPasswordEstudiante;
	private Password pswPasswordEstudianteConfirma;
	private CommandButton btnRegistrarEstudiante;
	private CommandButton btnLimpiarPantallaRegistroEstudiante;
	
	private String nombreUsuarioString;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public UsuarioView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			UsuarioDTO usuarioDTO = (UsuarioDTO) e.getObject();

			if (txtApellidoUsuario == null) {
				txtApellidoUsuario = new InputText();
			}

			txtApellidoUsuario.setValue(usuarioDTO.getApellidoUsuario());

			if (txtCodigoUsuario == null) {
				txtCodigoUsuario = new InputText();
			}

			txtCodigoUsuario.setValue(usuarioDTO.getCodigoUsuario());

			if (txtEmailUsuario == null) {
				txtEmailUsuario = new InputText();
			}

			txtEmailUsuario.setValue(usuarioDTO.getEmailUsuario());

			if (txtEstadoRegistro == null) {
				txtEstadoRegistro = new InputText();
			}

			txtEstadoRegistro.setValue(usuarioDTO.getEstadoRegistro());

			if (txtNombreUsuario == null) {
				txtNombreUsuario = new InputText();
			}

			txtNombreUsuario.setValue(usuarioDTO.getNombreUsuario());

			if (txtPasswordUsuario == null) {
				txtPasswordUsuario = new InputText();
			}

			txtPasswordUsuario.setValue(usuarioDTO.getPasswordUsuario());

			if (txtTiusId_TipoUsuario == null) {
				txtTiusId_TipoUsuario = new InputText();
			}

			txtTiusId_TipoUsuario.setValue(usuarioDTO.getTiusId_TipoUsuario());

			if (txtUsuaId == null) {
				txtUsuaId = new InputText();
			}

			txtUsuaId.setValue(usuarioDTO.getUsuaId());

			Long usuaId = FacesUtils.checkLong(txtUsuaId);
			entity = businessDelegatorView.getUsuario(usuaId);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedUsuario = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedUsuario = null;

		if (txtApellidoUsuario != null) {
			txtApellidoUsuario.setValue(null);
			txtApellidoUsuario.setDisabled(true);
		}

		if (txtCodigoUsuario != null) {
			txtCodigoUsuario.setValue(null);
			txtCodigoUsuario.setDisabled(true);
		}

		if (txtEmailUsuario != null) {
			txtEmailUsuario.setValue(null);
			txtEmailUsuario.setDisabled(true);
		}

		if (txtEstadoRegistro != null) {
			txtEstadoRegistro.setValue(null);
			txtEstadoRegistro.setDisabled(true);
		}

		if (txtNombreUsuario != null) {
			txtNombreUsuario.setValue(null);
			txtNombreUsuario.setDisabled(true);
		}

		if (txtPasswordUsuario != null) {
			txtPasswordUsuario.setValue(null);
			txtPasswordUsuario.setDisabled(true);
		}

		if (txtTiusId_TipoUsuario != null) {
			txtTiusId_TipoUsuario.setValue(null);
			txtTiusId_TipoUsuario.setDisabled(true);
		}

		if (txtUsuaId != null) {
			txtUsuaId.setValue(null);
			txtUsuaId.setDisabled(false);
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
			Long usuaId = FacesUtils.checkLong(txtUsuaId);
			entity = (usuaId != null) ? businessDelegatorView.getUsuario(usuaId) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtApellidoUsuario.setDisabled(false);
			txtCodigoUsuario.setDisabled(false);
			txtEmailUsuario.setDisabled(false);
			txtEstadoRegistro.setDisabled(false);
			txtNombreUsuario.setDisabled(false);
			txtPasswordUsuario.setDisabled(false);
			txtTiusId_TipoUsuario.setDisabled(false);
			txtUsuaId.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtApellidoUsuario.setValue(entity.getApellidoUsuario());
			txtApellidoUsuario.setDisabled(false);
			txtCodigoUsuario.setValue(entity.getCodigoUsuario());
			txtCodigoUsuario.setDisabled(false);
			txtEmailUsuario.setValue(entity.getEmailUsuario());
			txtEmailUsuario.setDisabled(false);
			txtEstadoRegistro.setValue(entity.getEstadoRegistro());
			txtEstadoRegistro.setDisabled(false);
			txtNombreUsuario.setValue(entity.getNombreUsuario());
			txtNombreUsuario.setDisabled(false);
			txtPasswordUsuario.setValue(entity.getPasswordUsuario());
			txtPasswordUsuario.setDisabled(false);
			txtTiusId_TipoUsuario.setValue(entity.getTipoUsuario().getTiusId());
			txtTiusId_TipoUsuario.setDisabled(false);
			txtUsuaId.setValue(entity.getUsuaId());
			txtUsuaId.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes().get("selectedUsuario"));
		txtApellidoUsuario.setValue(selectedUsuario.getApellidoUsuario());
		txtApellidoUsuario.setDisabled(false);
		txtCodigoUsuario.setValue(selectedUsuario.getCodigoUsuario());
		txtCodigoUsuario.setDisabled(false);
		txtEmailUsuario.setValue(selectedUsuario.getEmailUsuario());
		txtEmailUsuario.setDisabled(false);
		txtEstadoRegistro.setValue(selectedUsuario.getEstadoRegistro());
		txtEstadoRegistro.setDisabled(false);
		txtNombreUsuario.setValue(selectedUsuario.getNombreUsuario());
		txtNombreUsuario.setDisabled(false);
		txtPasswordUsuario.setValue(selectedUsuario.getPasswordUsuario());
		txtPasswordUsuario.setDisabled(false);
		txtTiusId_TipoUsuario.setValue(selectedUsuario.getTiusId_TipoUsuario());
		txtTiusId_TipoUsuario.setDisabled(false);
		txtUsuaId.setValue(selectedUsuario.getUsuaId());
		txtUsuaId.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedUsuario == null) && (entity == null)) {
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
			entity = new Usuario();

			Long usuaId = FacesUtils.checkLong(txtUsuaId);

			entity.setApellidoUsuario(FacesUtils.checkString(txtApellidoUsuario));
			entity.setCodigoUsuario(FacesUtils.checkString(txtCodigoUsuario));
			entity.setEmailUsuario(FacesUtils.checkString(txtEmailUsuario));
			entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
			entity.setNombreUsuario(FacesUtils.checkString(txtNombreUsuario));
			entity.setPasswordUsuario(FacesUtils.checkString(txtPasswordUsuario));
			entity.setUsuaId(usuaId);
			entity.setTipoUsuario((FacesUtils.checkLong(txtTiusId_TipoUsuario) != null)
					? businessDelegatorView.getTipoUsuario(FacesUtils.checkLong(txtTiusId_TipoUsuario)) : null);
			businessDelegatorView.saveUsuario(entity);
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
				Long usuaId = new Long(selectedUsuario.getUsuaId());
				entity = businessDelegatorView.getUsuario(usuaId);
			}

			entity.setApellidoUsuario(FacesUtils.checkString(txtApellidoUsuario));
			entity.setCodigoUsuario(FacesUtils.checkString(txtCodigoUsuario));
			entity.setEmailUsuario(FacesUtils.checkString(txtEmailUsuario));
			entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
			entity.setNombreUsuario(FacesUtils.checkString(txtNombreUsuario));
			entity.setPasswordUsuario(FacesUtils.checkString(txtPasswordUsuario));
			entity.setTipoUsuario((FacesUtils.checkLong(txtTiusId_TipoUsuario) != null)
					? businessDelegatorView.getTipoUsuario(FacesUtils.checkLong(txtTiusId_TipoUsuario)) : null);
			businessDelegatorView.updateUsuario(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes().get("selectedUsuario"));

			Long usuaId = new Long(selectedUsuario.getUsuaId());
			entity = businessDelegatorView.getUsuario(usuaId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long usuaId = FacesUtils.checkLong(txtUsuaId);
			entity = businessDelegatorView.getUsuario(usuaId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteUsuario(entity);
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
			selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes().get("selectedUsuario"));

			Long usuaId = new Long(selectedUsuario.getUsuaId());
			entity = businessDelegatorView.getUsuario(usuaId);
			businessDelegatorView.deleteUsuario(entity);
			data.remove(selectedUsuario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String apellidoUsuario, String codigoUsuario, String emailUsuario,
			String estadoRegistro, String nombreUsuario, String passwordUsuario, Long usuaId, Long tiusId_TipoUsuario)
					throws Exception {
		try {
			entity.setApellidoUsuario(FacesUtils.checkString(apellidoUsuario));
			entity.setCodigoUsuario(FacesUtils.checkString(codigoUsuario));
			entity.setEmailUsuario(FacesUtils.checkString(emailUsuario));
			entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
			entity.setNombreUsuario(FacesUtils.checkString(nombreUsuario));
			entity.setPasswordUsuario(FacesUtils.checkString(passwordUsuario));
			businessDelegatorView.updateUsuario(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("UsuarioView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public String registrarNuevoDocente() {
		String pswDocente = FacesUtils.checkString(pswPasswordDocente);
		String pswDocenteConfirma = FacesUtils.checkString(pswPasswordDocenteConfirma);

		if (pswDocente.trim().equals(pswDocenteConfirma.trim()) == false) {
			FacesUtils.addInfoMessage("Las contraseñas no coinciden");
		} else {
			try {
				Usuario docente = new Usuario();
				docente.setUsuaId(null);
				TipoUsuario tipoUsuario = businessDelegatorView.obtenerTipoUsuarioPorNombreCorto("DOC");
				docente.setTipoUsuario(tipoUsuario);
				docente.setEstadoRegistro("A");
				docente.setApellidoUsuario(FacesUtils.checkString(txtApellidoDocente));
				docente.setNombreUsuario(FacesUtils.checkString(txtNombreDocente));
				docente.setEmailUsuario(FacesUtils.checkString(txtEmailDocente));
				docente.setCodigoUsuario(FacesUtils.checkString(txtCodigoDocente));
				docente.setPasswordUsuario(FacesUtils.checkString(pswPasswordDocente));

				businessDelegatorView.saveUsuario(docente);
				FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
				limpiarPantallaRegistrarDocente();
				
				ExternalContext context = FacesContext.getCurrentInstance()
						.getExternalContext();
				context.redirect(context.getRequestContextPath()
						+ "/login.xhtml?usrCrtdSflly=true");
			} catch (Exception e) {
				entity = null;
				FacesUtils.addErrorMessage(e.getMessage());
			}
		}
		return "goToLogin";
	}

	public String limpiarPantallaRegistrarDocente() {
		txtApellidoDocente.setValue(null);
		txtNombreDocente.setValue(null);
		txtEmailDocente.setValue(null);
		txtCodigoDocente.setValue(null);
		pswPasswordDocente.setValue(null);
		pswPasswordDocenteConfirma.setValue(null);
		return "";
	}

	public String registrarNuevoEstudiante() {
		String pswEstudiante = FacesUtils.checkString(pswPasswordEstudiante);
		String pswEstudianteConfirma = FacesUtils.checkString(pswPasswordEstudianteConfirma);

		if (pswEstudiante.trim().equals(pswEstudianteConfirma.trim()) == false) {
			FacesUtils.addInfoMessage("Las contraseñas no coinciden");
		} else {
			try {
				Usuario estudiante = new Usuario();
				estudiante.setUsuaId(null);
				TipoUsuario tipoUsuario = businessDelegatorView.obtenerTipoUsuarioPorNombreCorto("EST");
				estudiante.setTipoUsuario(tipoUsuario);
				estudiante.setEstadoRegistro("A");
				estudiante.setApellidoUsuario(FacesUtils.checkString(txtApellidoEstudiante));
				estudiante.setNombreUsuario(FacesUtils.checkString(txtNombreEstudiante));
				estudiante.setEmailUsuario(FacesUtils.checkString(txtEmailEstudiante));
				estudiante.setCodigoUsuario(FacesUtils.checkString(txtCodigoEstudiante));
				estudiante.setPasswordUsuario(FacesUtils.checkString(pswPasswordEstudiante));

				businessDelegatorView.saveUsuario(estudiante);
				FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
				limpiarPantallaRegistrarEstudiante();
				
				ExternalContext context = FacesContext.getCurrentInstance()
						.getExternalContext();
				context.redirect(context.getRequestContextPath()
						+ "/login.xhtml?usrCrtdSflly=true");
			} catch (Exception e) {
				entity = null;
				FacesUtils.addErrorMessage(e.getMessage());
			}
		}
		return "goToLogin";
	}
	
	public String limpiarPantallaRegistrarEstudiante() {
		txtApellidoEstudiante.setValue(null);
		txtNombreEstudiante.setValue(null);
		txtEmailEstudiante.setValue(null);
		txtCodigoEstudiante.setValue(null);
		pswPasswordEstudiante.setValue(null);
		pswPasswordEstudianteConfirma.setValue(null);
		return "";
	}
	
	public InputText getTxtApellidoUsuario() {
		return txtApellidoUsuario;
	}

	public void setTxtApellidoUsuario(InputText txtApellidoUsuario) {
		this.txtApellidoUsuario = txtApellidoUsuario;
	}

	public InputText getTxtCodigoUsuario() {
		return txtCodigoUsuario;
	}

	public void setTxtCodigoUsuario(InputText txtCodigoUsuario) {
		this.txtCodigoUsuario = txtCodigoUsuario;
	}

	public InputText getTxtEmailUsuario() {
		return txtEmailUsuario;
	}

	public void setTxtEmailUsuario(InputText txtEmailUsuario) {
		this.txtEmailUsuario = txtEmailUsuario;
	}

	public InputText getTxtEstadoRegistro() {
		return txtEstadoRegistro;
	}

	public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
		this.txtEstadoRegistro = txtEstadoRegistro;
	}

	public InputText getTxtNombreUsuario() {
		return txtNombreUsuario;
	}

	public void setTxtNombreUsuario(InputText txtNombreUsuario) {
		this.txtNombreUsuario = txtNombreUsuario;
	}

	public InputText getTxtPasswordUsuario() {
		return txtPasswordUsuario;
	}

	public void setTxtPasswordUsuario(InputText txtPasswordUsuario) {
		this.txtPasswordUsuario = txtPasswordUsuario;
	}

	public InputText getTxtTiusId_TipoUsuario() {
		return txtTiusId_TipoUsuario;
	}

	public void setTxtTiusId_TipoUsuario(InputText txtTiusId_TipoUsuario) {
		this.txtTiusId_TipoUsuario = txtTiusId_TipoUsuario;
	}

	public InputText getTxtUsuaId() {
		return txtUsuaId;
	}

	public void setTxtUsuaId(InputText txtUsuaId) {
		this.txtUsuaId = txtUsuaId;
	}

	public List<UsuarioDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataUsuario();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<UsuarioDTO> usuarioDTO) {
		this.data = usuarioDTO;
	}

	public UsuarioDTO getSelectedUsuario() {
		return selectedUsuario;
	}

	public void setSelectedUsuario(UsuarioDTO usuario) {
		this.selectedUsuario = usuario;
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

	public InputText getTxtApellidoDocente() {
		return txtApellidoDocente;
	}

	public void setTxtApellidoDocente(InputText txtApellidoDocente) {
		this.txtApellidoDocente = txtApellidoDocente;
	}

	public InputText getTxtNombreDocente() {
		return txtNombreDocente;
	}

	public void setTxtNombreDocente(InputText txtNombreDocente) {
		this.txtNombreDocente = txtNombreDocente;
	}

	public InputText getTxtCodigoDocente() {
		return txtCodigoDocente;
	}

	public void setTxtCodigoDocente(InputText txtCodigoDocente) {
		this.txtCodigoDocente = txtCodigoDocente;
	}

	public InputText getTxtEmailDocente() {
		return txtEmailDocente;
	}

	public void setTxtEmailDocente(InputText txtEmailDocente) {
		this.txtEmailDocente = txtEmailDocente;
	}

	public Password getPswPasswordDocente() {
		return pswPasswordDocente;
	}

	public void setPswPasswordDocente(Password pswPasswordDocente) {
		this.pswPasswordDocente = pswPasswordDocente;
	}

	public Password getPswPasswordDocenteConfirma() {
		return pswPasswordDocenteConfirma;
	}

	public void setPswPasswordDocenteConfirma(Password pswPasswordDocenteConfirma) {
		this.pswPasswordDocenteConfirma = pswPasswordDocenteConfirma;
	}

	public CommandButton getBtnRegistrarDocente() {
		return btnRegistrarDocente;
	}

	public void setBtnRegistrarDocente(CommandButton btnRegistrarDocente) {
		this.btnRegistrarDocente = btnRegistrarDocente;
	}

	public CommandButton getBtnLimpiarPantallaRegistroDocente() {
		return btnLimpiarPantallaRegistroDocente;
	}

	public void setBtnLimpiarPantallaRegistroDocente(CommandButton btnLimpiarPantallaRegistroDocente) {
		this.btnLimpiarPantallaRegistroDocente = btnLimpiarPantallaRegistroDocente;
	}

	public InputText getTxtApellidoEstudiante() {
		return txtApellidoEstudiante;
	}

	public void setTxtApellidoEstudiante(InputText txtApellidoEstudiante) {
		this.txtApellidoEstudiante = txtApellidoEstudiante;
	}

	public InputText getTxtNombreEstudiante() {
		return txtNombreEstudiante;
	}

	public void setTxtNombreEstudiante(InputText txtNombreEstudiante) {
		this.txtNombreEstudiante = txtNombreEstudiante;
	}

	public InputText getTxtCodigoEstudiante() {
		return txtCodigoEstudiante;
	}

	public void setTxtCodigoEstudiante(InputText txtCodigoEstudiante) {
		this.txtCodigoEstudiante = txtCodigoEstudiante;
	}

	public InputText getTxtEmailEstudiante() {
		return txtEmailEstudiante;
	}

	public void setTxtEmailEstudiante(InputText txtEmailEstudiante) {
		this.txtEmailEstudiante = txtEmailEstudiante;
	}

	public Password getPswPasswordEstudiante() {
		return pswPasswordEstudiante;
	}

	public void setPswPasswordEstudiante(Password pswPasswordEstudiante) {
		this.pswPasswordEstudiante = pswPasswordEstudiante;
	}

	public Password getPswPasswordEstudianteConfirma() {
		return pswPasswordEstudianteConfirma;
	}

	public void setPswPasswordEstudianteConfirma(Password pswPasswordEstudianteConfirma) {
		this.pswPasswordEstudianteConfirma = pswPasswordEstudianteConfirma;
	}

	public CommandButton getBtnRegistrarEstudiante() {
		return btnRegistrarEstudiante;
	}

	public void setBtnRegistrarEstudiante(CommandButton btnRegistrarEstudiante) {
		this.btnRegistrarEstudiante = btnRegistrarEstudiante;
	}

	public CommandButton getBtnLimpiarPantallaRegistroEstudiante() {
		return btnLimpiarPantallaRegistroEstudiante;
	}

	public void setBtnLimpiarPantallaRegistroEstudiante(CommandButton btnLimpiarPantallaRegistroEstudiante) {
		this.btnLimpiarPantallaRegistroEstudiante = btnLimpiarPantallaRegistroEstudiante;
	}

	public String getNombreUsuarioString() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			com.vortexbird.seguridad.modelo.dto.UsuarioDTO loginUsuario = (com.vortexbird.seguridad.modelo.dto.UsuarioDTO) session
					.getAttribute("usuarioDTO");
			Usuario usuario = businessDelegatorView.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());
			nombreUsuarioString = new String();
			nombreUsuarioString = usuario.getNombreUsuario()+" "+usuario.getApellidoUsuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nombreUsuarioString;
	}

	public void setNombreUsuarioString(String nombreUsuarioString) {
		this.nombreUsuarioString = nombreUsuarioString;
	}
}
