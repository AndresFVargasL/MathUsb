package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.RespuestaDTO;
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
public class RespuestaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RespuestaView.class);
    private InputText txtDescripcionRespuesta;
    private InputText txtRespId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<RespuestaDTO> data;
    private RespuestaDTO selectedRespuesta;
    private Respuesta entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RespuestaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            RespuestaDTO respuestaDTO = (RespuestaDTO) e.getObject();

            if (txtDescripcionRespuesta == null) {
                txtDescripcionRespuesta = new InputText();
            }

            txtDescripcionRespuesta.setValue(respuestaDTO.getDescripcionRespuesta());

            if (txtRespId == null) {
                txtRespId = new InputText();
            }

            txtRespId.setValue(respuestaDTO.getRespId());

            Long respId = FacesUtils.checkLong(txtRespId);
            entity = businessDelegatorView.getRespuesta(respId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedRespuesta = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRespuesta = null;

        if (txtDescripcionRespuesta != null) {
            txtDescripcionRespuesta.setValue(null);
            txtDescripcionRespuesta.setDisabled(true);
        }

        if (txtRespId != null) {
            txtRespId.setValue(null);
            txtRespId.setDisabled(false);
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
            Long respId = FacesUtils.checkLong(txtRespId);
            entity = (respId != null)
                ? businessDelegatorView.getRespuesta(respId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionRespuesta.setDisabled(false);
            txtRespId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionRespuesta.setValue(entity.getDescripcionRespuesta());
            txtDescripcionRespuesta.setDisabled(false);
            txtRespId.setValue(entity.getRespId());
            txtRespId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRespuesta = (RespuestaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedRespuesta"));
        txtDescripcionRespuesta.setValue(selectedRespuesta.getDescripcionRespuesta());
        txtDescripcionRespuesta.setDisabled(false);
        txtRespId.setValue(selectedRespuesta.getRespId());
        txtRespId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRespuesta == null) && (entity == null)) {
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
            entity = new Respuesta();

            Long respId = FacesUtils.checkLong(txtRespId);

            entity.setDescripcionRespuesta(FacesUtils.checkString(
                    txtDescripcionRespuesta));
            entity.setRespId(respId);
            businessDelegatorView.saveRespuesta(entity);
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
                Long respId = new Long(selectedRespuesta.getRespId());
                entity = businessDelegatorView.getRespuesta(respId);
            }

            entity.setDescripcionRespuesta(FacesUtils.checkString(
                    txtDescripcionRespuesta));
            businessDelegatorView.updateRespuesta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRespuesta = (RespuestaDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedRespuesta"));

            Long respId = new Long(selectedRespuesta.getRespId());
            entity = businessDelegatorView.getRespuesta(respId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long respId = FacesUtils.checkLong(txtRespId);
            entity = businessDelegatorView.getRespuesta(respId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRespuesta(entity);
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
            selectedRespuesta = (RespuestaDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedRespuesta"));

            Long respId = new Long(selectedRespuesta.getRespId());
            entity = businessDelegatorView.getRespuesta(respId);
            businessDelegatorView.deleteRespuesta(entity);
            data.remove(selectedRespuesta);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionRespuesta, Long respId)
        throws Exception {
        try {
            entity.setDescripcionRespuesta(FacesUtils.checkString(
                    descripcionRespuesta));
            businessDelegatorView.updateRespuesta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("RespuestaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionRespuesta() {
        return txtDescripcionRespuesta;
    }

    public void setTxtDescripcionRespuesta(InputText txtDescripcionRespuesta) {
        this.txtDescripcionRespuesta = txtDescripcionRespuesta;
    }

    public InputText getTxtRespId() {
        return txtRespId;
    }

    public void setTxtRespId(InputText txtRespId) {
        this.txtRespId = txtRespId;
    }

    public List<RespuestaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRespuesta();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<RespuestaDTO> respuestaDTO) {
        this.data = respuestaDTO;
    }

    public RespuestaDTO getSelectedRespuesta() {
        return selectedRespuesta;
    }

    public void setSelectedRespuesta(RespuestaDTO respuesta) {
        this.selectedRespuesta = respuesta;
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
