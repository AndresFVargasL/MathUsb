package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.TemaDTO;
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
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class TemaView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(TemaView.class);
	private InputText txtDescripcionTema;
	private InputText txtPalabrasClave;
	private InputText txtTituloTema;
	private InputText txtTemaId;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<TemaDTO> data;
	private TemaDTO selectedTema;
	private Tema entity;
	private boolean showDialog;

	private InputTextarea txtAreaDescripcionTema; 
	private CommandButton btnGuardarNuevoTema;
	private CommandButton btnLimpiarPantallaNuevoTema;
	

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public TemaView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			TemaDTO temaDTO = (TemaDTO) e.getObject();

			if (txtDescripcionTema == null) {
				txtDescripcionTema = new InputText();
			}

			txtDescripcionTema.setValue(temaDTO.getDescripcionTema());

			if (txtPalabrasClave == null) {
				txtPalabrasClave = new InputText();
			}

			txtPalabrasClave.setValue(temaDTO.getPalabrasClave());

			if (txtTituloTema == null) {
				txtTituloTema = new InputText();
			}

			txtTituloTema.setValue(temaDTO.getTituloTema());

			if (txtTemaId == null) {
				txtTemaId = new InputText();
			}

			txtTemaId.setValue(temaDTO.getTemaId());

			Long temaId = FacesUtils.checkLong(txtTemaId);
			entity = businessDelegatorView.getTema(temaId);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedTema = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedTema = null;

		if (txtDescripcionTema != null) {
			txtDescripcionTema.setValue(null);
			txtDescripcionTema.setDisabled(true);
		}

		if (txtPalabrasClave != null) {
			txtPalabrasClave.setValue(null);
			txtPalabrasClave.setDisabled(true);
		}

		if (txtTituloTema != null) {
			txtTituloTema.setValue(null);
			txtTituloTema.setDisabled(true);
		}

		if (txtTemaId != null) {
			txtTemaId.setValue(null);
			txtTemaId.setDisabled(false);
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
			Long temaId = FacesUtils.checkLong(txtTemaId);
			entity = (temaId != null) ? businessDelegatorView.getTema(temaId) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtDescripcionTema.setDisabled(false);
			txtPalabrasClave.setDisabled(false);
			txtTituloTema.setDisabled(false);
			txtTemaId.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtDescripcionTema.setValue(entity.getDescripcionTema());
			txtDescripcionTema.setDisabled(false);
			txtPalabrasClave.setValue(entity.getPalabrasClave());
			txtPalabrasClave.setDisabled(false);
			txtTituloTema.setValue(entity.getTituloTema());
			txtTituloTema.setDisabled(false);
			txtTemaId.setValue(entity.getTemaId());
			txtTemaId.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedTema = (TemaDTO) (evt.getComponent().getAttributes().get("selectedTema"));
		txtDescripcionTema.setValue(selectedTema.getDescripcionTema());
		txtDescripcionTema.setDisabled(false);
		txtPalabrasClave.setValue(selectedTema.getPalabrasClave());
		txtPalabrasClave.setDisabled(false);
		txtTituloTema.setValue(selectedTema.getTituloTema());
		txtTituloTema.setDisabled(false);
		txtTemaId.setValue(selectedTema.getTemaId());
		txtTemaId.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedTema == null) && (entity == null)) {
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
			entity = new Tema();

			Long temaId = FacesUtils.checkLong(txtTemaId);

			entity.setDescripcionTema(FacesUtils.checkString(txtDescripcionTema));
			entity.setPalabrasClave(FacesUtils.checkString(txtPalabrasClave));
			entity.setTemaId(temaId);
			entity.setTituloTema(FacesUtils.checkString(txtTituloTema));
			businessDelegatorView.saveTema(entity);
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
				Long temaId = new Long(selectedTema.getTemaId());
				entity = businessDelegatorView.getTema(temaId);
			}

			entity.setDescripcionTema(FacesUtils.checkString(txtDescripcionTema));
			entity.setPalabrasClave(FacesUtils.checkString(txtPalabrasClave));
			entity.setTituloTema(FacesUtils.checkString(txtTituloTema));
			businessDelegatorView.updateTema(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedTema = (TemaDTO) (evt.getComponent().getAttributes().get("selectedTema"));

			Long temaId = new Long(selectedTema.getTemaId());
			entity = businessDelegatorView.getTema(temaId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long temaId = FacesUtils.checkLong(txtTemaId);
			entity = businessDelegatorView.getTema(temaId);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteTema(entity);
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
			selectedTema = (TemaDTO) (evt.getComponent().getAttributes().get("selectedTema"));

			Long temaId = new Long(selectedTema.getTemaId());
			entity = businessDelegatorView.getTema(temaId);
			businessDelegatorView.deleteTema(entity);
			data.remove(selectedTema);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String descripcionTema, String palabrasClave, Long temaId, String tituloTema)
			throws Exception {
		try {
			entity.setDescripcionTema(FacesUtils.checkString(descripcionTema));
			entity.setPalabrasClave(FacesUtils.checkString(palabrasClave));
			entity.setTituloTema(FacesUtils.checkString(tituloTema));
			businessDelegatorView.updateTema(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("TemaView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtDescripcionTema() {
		return txtDescripcionTema;
	}

	public void setTxtDescripcionTema(InputText txtDescripcionTema) {
		this.txtDescripcionTema = txtDescripcionTema;
	}

	public InputText getTxtPalabrasClave() {
		return txtPalabrasClave;
	}

	public void setTxtPalabrasClave(InputText txtPalabrasClave) {
		this.txtPalabrasClave = txtPalabrasClave;
	}

	public InputText getTxtTituloTema() {
		return txtTituloTema;
	}

	public void setTxtTituloTema(InputText txtTituloTema) {
		this.txtTituloTema = txtTituloTema;
	}

	public InputText getTxtTemaId() {
		return txtTemaId;
	}

	public void setTxtTemaId(InputText txtTemaId) {
		this.txtTemaId = txtTemaId;
	}

	public List<TemaDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataTema();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<TemaDTO> temaDTO) {
		this.data = temaDTO;
	}

	public TemaDTO getSelectedTema() {
		return selectedTema;
	}

	public void setSelectedTema(TemaDTO tema) {
		this.selectedTema = tema;
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

	public InputTextarea getTxtAreaDescripcionTema() {
		return txtAreaDescripcionTema;
	}

	public void setTxtAreaDescripcionTema(InputTextarea txtAreaDescripcionTema) {
		this.txtAreaDescripcionTema = txtAreaDescripcionTema;
	}

	public CommandButton getBtnGuardarNuevoTema() {
		return btnGuardarNuevoTema;
	}

	public void setBtnGuardarNuevoTema(CommandButton btnGuardarNuevoTema) {
		this.btnGuardarNuevoTema = btnGuardarNuevoTema;
	}

	public CommandButton getBtnLimpiarPantallaNuevoTema() {
		return btnLimpiarPantallaNuevoTema;
	}

	public void setBtnLimpiarPantallaNuevoTema(CommandButton btnLimpiarPantallaNuevoTema) {
		this.btnLimpiarPantallaNuevoTema = btnLimpiarPantallaNuevoTema;
	}

	

	

	public void guardarNuevoTema() {
		try {
			Tema tema = new Tema();
			tema.setDescripcionTema(txtAreaDescripcionTema.getValue().toString().toUpperCase());
			tema.setTituloTema(txtTituloTema.getValue().toString().toUpperCase());
			tema.setPalabrasClave(txtPalabrasClave.getValue().toString().toUpperCase());
			tema.setTemaId(null);
			businessDelegatorView.saveTema(tema);
			FacesUtils.addInfoMessage("Se ha guardado el tema: " + tema.getTituloTema());
			limpiarPantallaCrearTema();
			data = null;
			getData();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void limpiarPantallaCrearTema() {
		txtAreaDescripcionTema.setValue(null);
		txtTituloTema.setValue(null);
		txtPalabrasClave.setValue(null);
	}
	
	
}
