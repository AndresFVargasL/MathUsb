package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.UsuarioEvaluacionDTO;
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
public class UsuarioEvaluacionView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuarioEvaluacionView.class);
    private InputText txtEvalId_Evaluacion;
    private InputText txtUsuaId_Usuario;
    private InputText txtUsevId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsuarioEvaluacionDTO> data;
    private UsuarioEvaluacionDTO selectedUsuarioEvaluacion;
    private UsuarioEvaluacion entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UsuarioEvaluacionView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            UsuarioEvaluacionDTO usuarioEvaluacionDTO = (UsuarioEvaluacionDTO) e.getObject();

            if (txtEvalId_Evaluacion == null) {
                txtEvalId_Evaluacion = new InputText();
            }

            txtEvalId_Evaluacion.setValue(usuarioEvaluacionDTO.getEvalId_Evaluacion());

            if (txtUsuaId_Usuario == null) {
                txtUsuaId_Usuario = new InputText();
            }

            txtUsuaId_Usuario.setValue(usuarioEvaluacionDTO.getUsuaId_Usuario());

            if (txtUsevId == null) {
                txtUsevId = new InputText();
            }

            txtUsevId.setValue(usuarioEvaluacionDTO.getUsevId());

            Long usevId = FacesUtils.checkLong(txtUsevId);
            entity = businessDelegatorView.getUsuarioEvaluacion(usevId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedUsuarioEvaluacion = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedUsuarioEvaluacion = null;

        if (txtEvalId_Evaluacion != null) {
            txtEvalId_Evaluacion.setValue(null);
            txtEvalId_Evaluacion.setDisabled(true);
        }

        if (txtUsuaId_Usuario != null) {
            txtUsuaId_Usuario.setValue(null);
            txtUsuaId_Usuario.setDisabled(true);
        }

        if (txtUsevId != null) {
            txtUsevId.setValue(null);
            txtUsevId.setDisabled(false);
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
            Long usevId = FacesUtils.checkLong(txtUsevId);
            entity = (usevId != null)
                ? businessDelegatorView.getUsuarioEvaluacion(usevId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEvalId_Evaluacion.setDisabled(false);
            txtUsuaId_Usuario.setDisabled(false);
            txtUsevId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEvalId_Evaluacion.setValue(entity.getEvaluacion().getEvalId());
            txtEvalId_Evaluacion.setDisabled(false);
            txtUsuaId_Usuario.setValue(entity.getUsuario().getUsuaId());
            txtUsuaId_Usuario.setDisabled(false);
            txtUsevId.setValue(entity.getUsevId());
            txtUsevId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedUsuarioEvaluacion = (UsuarioEvaluacionDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedUsuarioEvaluacion"));
        txtEvalId_Evaluacion.setValue(selectedUsuarioEvaluacion.getEvalId_Evaluacion());
        txtEvalId_Evaluacion.setDisabled(false);
        txtUsuaId_Usuario.setValue(selectedUsuarioEvaluacion.getUsuaId_Usuario());
        txtUsuaId_Usuario.setDisabled(false);
        txtUsevId.setValue(selectedUsuarioEvaluacion.getUsevId());
        txtUsevId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsuarioEvaluacion == null) && (entity == null)) {
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
            entity = new UsuarioEvaluacion();

            Long usevId = FacesUtils.checkLong(txtUsevId);

            entity.setUsevId(usevId);
            entity.setEvaluacion((FacesUtils.checkLong(txtEvalId_Evaluacion) != null)
                ? businessDelegatorView.getEvaluacion(FacesUtils.checkLong(
                        txtEvalId_Evaluacion)) : null);
            entity.setUsuario((FacesUtils.checkLong(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.saveUsuarioEvaluacion(entity);
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
                Long usevId = new Long(selectedUsuarioEvaluacion.getUsevId());
                entity = businessDelegatorView.getUsuarioEvaluacion(usevId);
            }

            entity.setEvaluacion((FacesUtils.checkLong(txtEvalId_Evaluacion) != null)
                ? businessDelegatorView.getEvaluacion(FacesUtils.checkLong(
                        txtEvalId_Evaluacion)) : null);
            entity.setUsuario((FacesUtils.checkLong(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.updateUsuarioEvaluacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsuarioEvaluacion = (UsuarioEvaluacionDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedUsuarioEvaluacion"));

            Long usevId = new Long(selectedUsuarioEvaluacion.getUsevId());
            entity = businessDelegatorView.getUsuarioEvaluacion(usevId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long usevId = FacesUtils.checkLong(txtUsevId);
            entity = businessDelegatorView.getUsuarioEvaluacion(usevId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsuarioEvaluacion(entity);
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
            selectedUsuarioEvaluacion = (UsuarioEvaluacionDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedUsuarioEvaluacion"));

            Long usevId = new Long(selectedUsuarioEvaluacion.getUsevId());
            entity = businessDelegatorView.getUsuarioEvaluacion(usevId);
            businessDelegatorView.deleteUsuarioEvaluacion(entity);
            data.remove(selectedUsuarioEvaluacion);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long usevId, Long evalId_Evaluacion,
        Long usuaId_Usuario) throws Exception {
        try {
            businessDelegatorView.updateUsuarioEvaluacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsuarioEvaluacionView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEvalId_Evaluacion() {
        return txtEvalId_Evaluacion;
    }

    public void setTxtEvalId_Evaluacion(InputText txtEvalId_Evaluacion) {
        this.txtEvalId_Evaluacion = txtEvalId_Evaluacion;
    }

    public InputText getTxtUsuaId_Usuario() {
        return txtUsuaId_Usuario;
    }

    public void setTxtUsuaId_Usuario(InputText txtUsuaId_Usuario) {
        this.txtUsuaId_Usuario = txtUsuaId_Usuario;
    }

    public InputText getTxtUsevId() {
        return txtUsevId;
    }

    public void setTxtUsevId(InputText txtUsevId) {
        this.txtUsevId = txtUsevId;
    }

    public List<UsuarioEvaluacionDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsuarioEvaluacion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsuarioEvaluacionDTO> usuarioEvaluacionDTO) {
        this.data = usuarioEvaluacionDTO;
    }

    public UsuarioEvaluacionDTO getSelectedUsuarioEvaluacion() {
        return selectedUsuarioEvaluacion;
    }

    public void setSelectedUsuarioEvaluacion(
        UsuarioEvaluacionDTO usuarioEvaluacion) {
        this.selectedUsuarioEvaluacion = usuarioEvaluacion;
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
