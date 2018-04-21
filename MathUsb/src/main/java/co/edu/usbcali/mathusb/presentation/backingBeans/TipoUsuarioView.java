package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.TipoUsuarioDTO;
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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class TipoUsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoUsuarioView.class);
    private InputText txtDescripcionTipoDeUsuario;
    private InputText txtNombreCorto;
    private InputText txtTiusId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TipoUsuarioDTO> data;
    private TipoUsuarioDTO selectedTipoUsuario;
    private TipoUsuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TipoUsuarioView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TipoUsuarioDTO tipoUsuarioDTO = (TipoUsuarioDTO) e.getObject();

            if (txtDescripcionTipoDeUsuario == null) {
                txtDescripcionTipoDeUsuario = new InputText();
            }

            txtDescripcionTipoDeUsuario.setValue(tipoUsuarioDTO.getDescripcionTipoDeUsuario());

            if (txtNombreCorto == null) {
                txtNombreCorto = new InputText();
            }

            txtNombreCorto.setValue(tipoUsuarioDTO.getNombreCorto());

            if (txtTiusId == null) {
                txtTiusId = new InputText();
            }

            txtTiusId.setValue(tipoUsuarioDTO.getTiusId());

            Long tiusId = FacesUtils.checkLong(txtTiusId);
            entity = businessDelegatorView.getTipoUsuario(tiusId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTipoUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTipoUsuario = null;

        if (txtDescripcionTipoDeUsuario != null) {
            txtDescripcionTipoDeUsuario.setValue(null);
            txtDescripcionTipoDeUsuario.setDisabled(true);
        }

        if (txtNombreCorto != null) {
            txtNombreCorto.setValue(null);
            txtNombreCorto.setDisabled(true);
        }

        if (txtTiusId != null) {
            txtTiusId.setValue(null);
            txtTiusId.setDisabled(false);
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
            Long tiusId = FacesUtils.checkLong(txtTiusId);
            entity = (tiusId != null)
                ? businessDelegatorView.getTipoUsuario(tiusId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionTipoDeUsuario.setDisabled(false);
            txtNombreCorto.setDisabled(false);
            txtTiusId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionTipoDeUsuario.setValue(entity.getDescripcionTipoDeUsuario());
            txtDescripcionTipoDeUsuario.setDisabled(false);
            txtNombreCorto.setValue(entity.getNombreCorto());
            txtNombreCorto.setDisabled(false);
            txtTiusId.setValue(entity.getTiusId());
            txtTiusId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTipoUsuario = (TipoUsuarioDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedTipoUsuario"));
        txtDescripcionTipoDeUsuario.setValue(selectedTipoUsuario.getDescripcionTipoDeUsuario());
        txtDescripcionTipoDeUsuario.setDisabled(false);
        txtNombreCorto.setValue(selectedTipoUsuario.getNombreCorto());
        txtNombreCorto.setDisabled(false);
        txtTiusId.setValue(selectedTipoUsuario.getTiusId());
        txtTiusId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTipoUsuario == null) && (entity == null)) {
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
            entity = new TipoUsuario();

            Long tiusId = FacesUtils.checkLong(txtTiusId);

            entity.setDescripcionTipoDeUsuario(FacesUtils.checkString(
                    txtDescripcionTipoDeUsuario));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            entity.setTiusId(tiusId);
            businessDelegatorView.saveTipoUsuario(entity);
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
                Long tiusId = new Long(selectedTipoUsuario.getTiusId());
                entity = businessDelegatorView.getTipoUsuario(tiusId);
            }

            entity.setDescripcionTipoDeUsuario(FacesUtils.checkString(
                    txtDescripcionTipoDeUsuario));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            businessDelegatorView.updateTipoUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTipoUsuario = (TipoUsuarioDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedTipoUsuario"));

            Long tiusId = new Long(selectedTipoUsuario.getTiusId());
            entity = businessDelegatorView.getTipoUsuario(tiusId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long tiusId = FacesUtils.checkLong(txtTiusId);
            entity = businessDelegatorView.getTipoUsuario(tiusId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTipoUsuario(entity);
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
            selectedTipoUsuario = (TipoUsuarioDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedTipoUsuario"));

            Long tiusId = new Long(selectedTipoUsuario.getTiusId());
            entity = businessDelegatorView.getTipoUsuario(tiusId);
            businessDelegatorView.deleteTipoUsuario(entity);
            data.remove(selectedTipoUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionTipoDeUsuario,
        String nombreCorto, Long tiusId) throws Exception {
        try {
            entity.setDescripcionTipoDeUsuario(FacesUtils.checkString(
                    descripcionTipoDeUsuario));
            entity.setNombreCorto(FacesUtils.checkString(nombreCorto));
            businessDelegatorView.updateTipoUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipoUsuarioView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionTipoDeUsuario() {
        return txtDescripcionTipoDeUsuario;
    }

    public void setTxtDescripcionTipoDeUsuario(
        InputText txtDescripcionTipoDeUsuario) {
        this.txtDescripcionTipoDeUsuario = txtDescripcionTipoDeUsuario;
    }

    public InputText getTxtNombreCorto() {
        return txtNombreCorto;
    }

    public void setTxtNombreCorto(InputText txtNombreCorto) {
        this.txtNombreCorto = txtNombreCorto;
    }

    public InputText getTxtTiusId() {
        return txtTiusId;
    }

    public void setTxtTiusId(InputText txtTiusId) {
        this.txtTiusId = txtTiusId;
    }

    public List<TipoUsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTipoUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipoUsuarioDTO> tipoUsuarioDTO) {
        this.data = tipoUsuarioDTO;
    }

    public TipoUsuarioDTO getSelectedTipoUsuario() {
        return selectedTipoUsuario;
    }

    public void setSelectedTipoUsuario(TipoUsuarioDTO tipoUsuario) {
        this.selectedTipoUsuario = tipoUsuario;
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

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
