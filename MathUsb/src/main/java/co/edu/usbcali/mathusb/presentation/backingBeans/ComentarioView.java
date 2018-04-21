package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.ComentarioDTO;
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
import javax.servlet.http.HttpSession;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ComentarioView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ComentarioView.class);
	private InputText txtContenidoComentario;
	private InputText txtTituloComentario;
	private InputText txtComenId_Comentario;
	private InputText txtUsuaId_Usuario;
	private InputText txtComenId;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<ComentarioDTO> data;
	private ComentarioDTO selectedComentario;
	private Comentario entity;
	private boolean showDialog;
	private boolean showDlg;

	private List<ComentarioDTO> lasPreguntasDelForo, preguntasDelUsuario;

	private InputText txtTituloPreguntaForo, txtContenidoPregunta;

	private InputText txtUsuarioQuePregunta, txtTituloPregunta;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public ComentarioView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			ComentarioDTO comentarioDTO = (ComentarioDTO) e.getObject();

			if (txtContenidoComentario == null) {
				txtContenidoComentario = new InputText();
			}

			txtContenidoComentario.setValue(comentarioDTO.getContenidoComentario());

			if (txtTituloComentario == null) {
				txtTituloComentario = new InputText();
			}

			txtTituloComentario.setValue(comentarioDTO.getTituloComentario());

			if (txtComenId_Comentario == null) {
				txtComenId_Comentario = new InputText();
			}

			txtComenId_Comentario.setValue(comentarioDTO.getComenId_Comentario());

			if (txtUsuaId_Usuario == null) {
				txtUsuaId_Usuario = new InputText();
			}

			txtUsuaId_Usuario.setValue(comentarioDTO.getUsuaId_Usuario());

			if (txtComenId == null) {
				txtComenId = new InputText();
			}

			txtComenId.setValue(comentarioDTO.getComenId());

			Long comenId = FacesUtils.checkLong(txtComenId);
			entity = businessDelegatorView.getComentario(comenId);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedComentario = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedComentario = null;

		if (txtContenidoComentario != null) {
			txtContenidoComentario.setValue(null);
			txtContenidoComentario.setDisabled(true);
		}

		if (txtTituloComentario != null) {
			txtTituloComentario.setValue(null);
			txtTituloComentario.setDisabled(true);
		}

		if (txtComenId_Comentario != null) {
			txtComenId_Comentario.setValue(null);
			txtComenId_Comentario.setDisabled(true);
		}

		if (txtUsuaId_Usuario != null) {
			txtUsuaId_Usuario.setValue(null);
			txtUsuaId_Usuario.setDisabled(true);
		}

		if (txtComenId != null) {
			txtComenId.setValue(null);
			txtComenId.setDisabled(false);
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
			Long comenId = FacesUtils.checkLong(txtComenId);
			entity = (comenId != null) ? businessDelegatorView.getComentario(comenId) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtContenidoComentario.setDisabled(false);
			txtTituloComentario.setDisabled(false);
			txtComenId_Comentario.setDisabled(false);
			txtUsuaId_Usuario.setDisabled(false);
			txtComenId.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtContenidoComentario.setValue(entity.getContenidoComentario());
			txtContenidoComentario.setDisabled(false);
			txtTituloComentario.setValue(entity.getTituloComentario());
			txtTituloComentario.setDisabled(false);
			txtComenId_Comentario.setValue(entity.getComentario().getComenId());
			txtComenId_Comentario.setDisabled(false);
			txtUsuaId_Usuario.setValue(entity.getUsuario().getUsuaId());
			txtUsuaId_Usuario.setDisabled(false);
			txtComenId.setValue(entity.getComenId());
			txtComenId.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedComentario = (ComentarioDTO) (evt.getComponent().getAttributes().get("selectedComentario"));
		txtContenidoComentario.setValue(selectedComentario.getContenidoComentario());
		txtContenidoComentario.setDisabled(false);
		txtTituloComentario.setValue(selectedComentario.getTituloComentario());
		txtTituloComentario.setDisabled(false);
		txtComenId_Comentario.setValue(selectedComentario.getComenId_Comentario());
		txtComenId_Comentario.setDisabled(false);
		txtUsuaId_Usuario.setValue(selectedComentario.getUsuaId_Usuario());
		txtUsuaId_Usuario.setDisabled(false);
		txtComenId.setValue(selectedComentario.getComenId());
		txtComenId.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedComentario == null) && (entity == null)) {
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
			entity = new Comentario();

			Long comenId = FacesUtils.checkLong(txtComenId);

			entity.setComenId(comenId);
			entity.setContenidoComentario(FacesUtils.checkString(txtContenidoComentario));
			entity.setTituloComentario(FacesUtils.checkString(txtTituloComentario));
			entity.setComentario((FacesUtils.checkLong(txtComenId_Comentario) != null)
					? businessDelegatorView.getComentario(FacesUtils.checkLong(txtComenId_Comentario)) : null);
			entity.setUsuario((FacesUtils.checkLong(txtUsuaId_Usuario) != null)
					? businessDelegatorView.getUsuario(FacesUtils.checkLong(txtUsuaId_Usuario)) : null);
			businessDelegatorView.saveComentario(entity);
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
				Long comenId = new Long(selectedComentario.getComenId());
				entity = businessDelegatorView.getComentario(comenId);
			}

			entity.setContenidoComentario(FacesUtils.checkString(txtContenidoComentario));
			entity.setTituloComentario(FacesUtils.checkString(txtTituloComentario));
			entity.setComentario((FacesUtils.checkLong(txtComenId_Comentario) != null)
					? businessDelegatorView.getComentario(FacesUtils.checkLong(txtComenId_Comentario)) : null);
			entity.setUsuario((FacesUtils.checkLong(txtUsuaId_Usuario) != null)
					? businessDelegatorView.getUsuario(FacesUtils.checkLong(txtUsuaId_Usuario)) : null);
			businessDelegatorView.updateComentario(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedComentario = (ComentarioDTO) (evt.getComponent().getAttributes().get("selectedComentario"));

			Long comenId = new Long(selectedComentario.getComenId());
			entity = businessDelegatorView.getComentario(comenId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long comenId = FacesUtils.checkLong(txtComenId);
			entity = businessDelegatorView.getComentario(comenId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteComentario(entity);
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
			selectedComentario = (ComentarioDTO) (evt.getComponent().getAttributes().get("selectedComentario"));

			Long comenId = new Long(selectedComentario.getComenId());
			entity = businessDelegatorView.getComentario(comenId);
			businessDelegatorView.deleteComentario(entity);
			data.remove(selectedComentario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(Long comenId, String contenidoComentario, String tituloComentario,
			Long comenId_Comentario, Long usuaId_Usuario) throws Exception {
		try {
			entity.setContenidoComentario(FacesUtils.checkString(contenidoComentario));
			entity.setTituloComentario(FacesUtils.checkString(tituloComentario));
			businessDelegatorView.updateComentario(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("ComentarioView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtContenidoComentario() {
		return txtContenidoComentario;
	}

	public void setTxtContenidoComentario(InputText txtContenidoComentario) {
		this.txtContenidoComentario = txtContenidoComentario;
	}

	public InputText getTxtTituloComentario() {
		return txtTituloComentario;
	}

	public void setTxtTituloComentario(InputText txtTituloComentario) {
		this.txtTituloComentario = txtTituloComentario;
	}

	public InputText getTxtComenId_Comentario() {
		return txtComenId_Comentario;
	}

	public void setTxtComenId_Comentario(InputText txtComenId_Comentario) {
		this.txtComenId_Comentario = txtComenId_Comentario;
	}

	public InputText getTxtUsuaId_Usuario() {
		return txtUsuaId_Usuario;
	}

	public void setTxtUsuaId_Usuario(InputText txtUsuaId_Usuario) {
		this.txtUsuaId_Usuario = txtUsuaId_Usuario;
	}

	public InputText getTxtComenId() {
		return txtComenId;
	}

	public void setTxtComenId(InputText txtComenId) {
		this.txtComenId = txtComenId;
	}

	public List<ComentarioDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataComentario();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<ComentarioDTO> comentarioDTO) {
		this.data = comentarioDTO;
	}

	public ComentarioDTO getSelectedComentario() {
		return selectedComentario;
	}

	public void setSelectedComentario(ComentarioDTO comentario) {
		this.selectedComentario = comentario;
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

	public InputText getTxtTituloPreguntaForo() {
		return txtTituloPreguntaForo;
	}

	public void setTxtTituloPreguntaForo(InputText txtTituloPreguntaForo) {
		this.txtTituloPreguntaForo = txtTituloPreguntaForo;
	}

	public InputText getTxtContenidoPregunta() {
		return txtContenidoPregunta;
	}

	public void setTxtContenidoPregunta(InputText txtContenidoPregunta) {
		this.txtContenidoPregunta = txtContenidoPregunta;
	}

	public List<ComentarioDTO> getLasPreguntasDelForo() {
		try {
			if (lasPreguntasDelForo == null) {
				lasPreguntasDelForo = businessDelegatorView.obtenerLasPreguntasDelForo();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lasPreguntasDelForo;
	}

	public void setLasPreguntasDelForo(List<ComentarioDTO> lasPreguntasDelForo) {
		this.lasPreguntasDelForo = lasPreguntasDelForo;
	}

	public boolean isShowDlg() {
		return showDlg;
	}

	public void setShowDlg(boolean showDlg) {
		this.showDlg = showDlg;
	}

	public InputText getTxtUsuarioQuePregunta() {
		return txtUsuarioQuePregunta;
	}

	public InputText getTxtTituloPregunta() {
		return txtTituloPregunta;
	}

	public void setTxtUsuarioQuePregunta(InputText txtUsuarioQuePregunta) {
		this.txtUsuarioQuePregunta = txtUsuarioQuePregunta;
	}

	public void setTxtTituloPregunta(InputText txtTituloPregunta) {
		this.txtTituloPregunta = txtTituloPregunta;
	}

	public List<ComentarioDTO> getPreguntasDelUsuario() {
		try {
			if(preguntasDelUsuario==null){
				preguntasDelUsuario = new ArrayList<>();
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				com.vortexbird.seguridad.modelo.dto.UsuarioDTO loginUsuario = (com.vortexbird.seguridad.modelo.dto.UsuarioDTO) session
						.getAttribute("usuarioDTO");
				Usuario usuario = businessDelegatorView.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());
				preguntasDelUsuario = businessDelegatorView.listaPreguntasForoDadoIdUsuario(usuario.getUsuaId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return preguntasDelUsuario;
	}

	public void setPreguntasDelUsuario(List<ComentarioDTO> preguntasDelUsuario) {
		this.preguntasDelUsuario = preguntasDelUsuario;
	}

	public String preguntar() {
		try {
			Comentario pregunta = new Comentario();
			pregunta.setComenId(null);
			pregunta.setComentario(null);
			pregunta.setTituloComentario(txtTituloPreguntaForo.getValue().toString());
			pregunta.setContenidoComentario(FacesUtils.checkString(txtContenidoPregunta));

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			com.vortexbird.seguridad.modelo.dto.UsuarioDTO loginUsuario = (com.vortexbird.seguridad.modelo.dto.UsuarioDTO) session
					.getAttribute("usuarioDTO");
			Usuario usuario = businessDelegatorView.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());

			pregunta.setUsuario(usuario);
			pregunta.setComentario(null);
			businessDelegatorView.hacerPregunta(pregunta);
			txtContenidoPregunta.setValue("La pregunta ha sido guardada");
			lasPreguntasDelForo = null;
			limpiarPantallaHacerPregunta();
		} catch (Exception e) {
			txtContenidoPregunta.setValue(e.getMessage());
			log.debug("Método preguntar fecha/hora: " + new Date() + " " + e.getMessage());
		}
		return "";
	}

	public void limpiarPantallaHacerPregunta() {
		txtTituloPreguntaForo.setValue(null);
		// txtContenidoPregunta.setValue(null);

	}

	public String abrirPantallaMostrarPregunta(ActionEvent evt) {
		try {
			
			ComentarioDTO preguntaMostrar = (ComentarioDTO) (evt.getComponent().getAttributes().get("preguntaMostrar"));
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("preguntaMostrar", preguntaMostrar);
			log.info("Ha entrado al método abrirPantallaMostrarPregunta, ComentarioDTO: " + preguntaMostrar.getComenId());
			
			/*Metodo para redireccionar a cualquier parte de la app**/
			
			FacesUtils.resetManagedBean("preguntaForoView");
			
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.redirect(context.getRequestContextPath()
					+ "/Presentation/responderPreguntaForo.xhtml");
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return "";
	}
	
	public void ampliarRespuestaForo(ActionEvent evt){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		ComentarioDTO comentarioDTO = (ComentarioDTO) evt.getComponent().getAttributes().get("respuestaMostrar");
		session.setAttribute("respuestaMostrar", comentarioDTO);
	}

	public String abrirPantallaModificarPregunta(ActionEvent evt) {
		try {
			
			ComentarioDTO preguntaForoModificar = (ComentarioDTO) (evt.getComponent().getAttributes().get("preguntaForoModificar"));
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("preguntaForoModificar", preguntaForoModificar);
			log.info("IngresÃ³ a modificar pregunta foro");
			/*Metodo para redireccionar a cualquier parte de la app**/
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.redirect(context.getRequestContextPath()
					+ "/Presentation/modificarPreguntaForo.xhtml");
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return "";
	}

}
