package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.TipoEvaluacionDTO;
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
public class TipoEvaluacionView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoEvaluacionView.class);
    private InputText txtDescripcionTipoEvaluacion;
    private InputText txtNombreCorto;
    private InputText txtTievId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TipoEvaluacionDTO> data;
    private TipoEvaluacionDTO selectedTipoEvaluacion;
    private TipoEvaluacion entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TipoEvaluacionView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TipoEvaluacionDTO tipoEvaluacionDTO = (TipoEvaluacionDTO) e.getObject();

            if (txtDescripcionTipoEvaluacion == null) {
                txtDescripcionTipoEvaluacion = new InputText();
            }

            txtDescripcionTipoEvaluacion.setValue(tipoEvaluacionDTO.getDescripcionTipoEvaluacion());

            if (txtNombreCorto == null) {
                txtNombreCorto = new InputText();
            }

            txtNombreCorto.setValue(tipoEvaluacionDTO.getNombreCorto());

            if (txtTievId == null) {
                txtTievId = new InputText();
            }

            txtTievId.setValue(tipoEvaluacionDTO.getTievId());

            Long tievId = FacesUtils.checkLong(txtTievId);
            entity = businessDelegatorView.getTipoEvaluacion(tievId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTipoEvaluacion = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTipoEvaluacion = null;

        if (txtDescripcionTipoEvaluacion != null) {
            txtDescripcionTipoEvaluacion.setValue(null);
            txtDescripcionTipoEvaluacion.setDisabled(true);
        }

        if (txtNombreCorto != null) {
            txtNombreCorto.setValue(null);
            txtNombreCorto.setDisabled(true);
        }

        if (txtTievId != null) {
            txtTievId.setValue(null);
            txtTievId.setDisabled(false);
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
            Long tievId = FacesUtils.checkLong(txtTievId);
            entity = (tievId != null)
                ? businessDelegatorView.getTipoEvaluacion(tievId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionTipoEvaluacion.setDisabled(false);
            txtNombreCorto.setDisabled(false);
            txtTievId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionTipoEvaluacion.setValue(entity.getDescripcionTipoEvaluacion());
            txtDescripcionTipoEvaluacion.setDisabled(false);
            txtNombreCorto.setValue(entity.getNombreCorto());
            txtNombreCorto.setDisabled(false);
            txtTievId.setValue(entity.getTievId());
            txtTievId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTipoEvaluacion = (TipoEvaluacionDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedTipoEvaluacion"));
        txtDescripcionTipoEvaluacion.setValue(selectedTipoEvaluacion.getDescripcionTipoEvaluacion());
        txtDescripcionTipoEvaluacion.setDisabled(false);
        txtNombreCorto.setValue(selectedTipoEvaluacion.getNombreCorto());
        txtNombreCorto.setDisabled(false);
        txtTievId.setValue(selectedTipoEvaluacion.getTievId());
        txtTievId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTipoEvaluacion == null) && (entity == null)) {
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
            entity = new TipoEvaluacion();

            Long tievId = FacesUtils.checkLong(txtTievId);

            entity.setDescripcionTipoEvaluacion(FacesUtils.checkString(
                    txtDescripcionTipoEvaluacion));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            entity.setTievId(tievId);
            businessDelegatorView.saveTipoEvaluacion(entity);
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
                Long tievId = new Long(selectedTipoEvaluacion.getTievId());
                entity = businessDelegatorView.getTipoEvaluacion(tievId);
            }

            entity.setDescripcionTipoEvaluacion(FacesUtils.checkString(
                    txtDescripcionTipoEvaluacion));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            businessDelegatorView.updateTipoEvaluacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTipoEvaluacion = (TipoEvaluacionDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedTipoEvaluacion"));

            Long tievId = new Long(selectedTipoEvaluacion.getTievId());
            entity = businessDelegatorView.getTipoEvaluacion(tievId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long tievId = FacesUtils.checkLong(txtTievId);
            entity = businessDelegatorView.getTipoEvaluacion(tievId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTipoEvaluacion(entity);
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
            selectedTipoEvaluacion = (TipoEvaluacionDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedTipoEvaluacion"));

            Long tievId = new Long(selectedTipoEvaluacion.getTievId());
            entity = businessDelegatorView.getTipoEvaluacion(tievId);
            businessDelegatorView.deleteTipoEvaluacion(entity);
            data.remove(selectedTipoEvaluacion);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionTipoEvaluacion,
        String nombreCorto, Long tievId) throws Exception {
        try {
            entity.setDescripcionTipoEvaluacion(FacesUtils.checkString(
                    descripcionTipoEvaluacion));
            entity.setNombreCorto(FacesUtils.checkString(nombreCorto));
            businessDelegatorView.updateTipoEvaluacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipoEvaluacionView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionTipoEvaluacion() {
        return txtDescripcionTipoEvaluacion;
    }

    public void setTxtDescripcionTipoEvaluacion(
        InputText txtDescripcionTipoEvaluacion) {
        this.txtDescripcionTipoEvaluacion = txtDescripcionTipoEvaluacion;
    }

    public InputText getTxtNombreCorto() {
        return txtNombreCorto;
    }

    public void setTxtNombreCorto(InputText txtNombreCorto) {
        this.txtNombreCorto = txtNombreCorto;
    }

    public InputText getTxtTievId() {
        return txtTievId;
    }

    public void setTxtTievId(InputText txtTievId) {
        this.txtTievId = txtTievId;
    }

    public List<TipoEvaluacionDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTipoEvaluacion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipoEvaluacionDTO> tipoEvaluacionDTO) {
        this.data = tipoEvaluacionDTO;
    }

    public TipoEvaluacionDTO getSelectedTipoEvaluacion() {
        return selectedTipoEvaluacion;
    }

    public void setSelectedTipoEvaluacion(TipoEvaluacionDTO tipoEvaluacion) {
        this.selectedTipoEvaluacion = tipoEvaluacion;
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
