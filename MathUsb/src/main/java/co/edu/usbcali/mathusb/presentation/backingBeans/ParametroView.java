package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.ParametroDTO;
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
public class ParametroView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ParametroView.class);
    private InputText txtDescripcionParametros;
    private InputText txtEstadoRegistro;
    private InputText txtValorParametro;
    private InputText txtParamId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ParametroDTO> data;
    private ParametroDTO selectedParametro;
    private Parametro entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ParametroView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ParametroDTO parametroDTO = (ParametroDTO) e.getObject();

            if (txtDescripcionParametros == null) {
                txtDescripcionParametros = new InputText();
            }

            txtDescripcionParametros.setValue(parametroDTO.getDescripcionParametros());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(parametroDTO.getEstadoRegistro());

            if (txtValorParametro == null) {
                txtValorParametro = new InputText();
            }

            txtValorParametro.setValue(parametroDTO.getValorParametro());

            if (txtParamId == null) {
                txtParamId = new InputText();
            }

            txtParamId.setValue(parametroDTO.getParamId());

            Long paramId = FacesUtils.checkLong(txtParamId);
            entity = businessDelegatorView.getParametro(paramId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedParametro = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedParametro = null;

        if (txtDescripcionParametros != null) {
            txtDescripcionParametros.setValue(null);
            txtDescripcionParametros.setDisabled(true);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtValorParametro != null) {
            txtValorParametro.setValue(null);
            txtValorParametro.setDisabled(true);
        }

        if (txtParamId != null) {
            txtParamId.setValue(null);
            txtParamId.setDisabled(false);
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
            Long paramId = FacesUtils.checkLong(txtParamId);
            entity = (paramId != null)
                ? businessDelegatorView.getParametro(paramId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionParametros.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtValorParametro.setDisabled(false);
            txtParamId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionParametros.setValue(entity.getDescripcionParametros());
            txtDescripcionParametros.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtValorParametro.setValue(entity.getValorParametro());
            txtValorParametro.setDisabled(false);
            txtParamId.setValue(entity.getParamId());
            txtParamId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedParametro = (ParametroDTO) (evt.getComponent().getAttributes()
                                               .get("selectedParametro"));
        txtDescripcionParametros.setValue(selectedParametro.getDescripcionParametros());
        txtDescripcionParametros.setDisabled(false);
        txtEstadoRegistro.setValue(selectedParametro.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtValorParametro.setValue(selectedParametro.getValorParametro());
        txtValorParametro.setDisabled(false);
        txtParamId.setValue(selectedParametro.getParamId());
        txtParamId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedParametro == null) && (entity == null)) {
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
            entity = new Parametro();

            Long paramId = FacesUtils.checkLong(txtParamId);

            entity.setDescripcionParametros(FacesUtils.checkString(
                    txtDescripcionParametros));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setParamId(paramId);
            entity.setValorParametro(FacesUtils.checkString(txtValorParametro));
            businessDelegatorView.saveParametro(entity);
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
                Long paramId = new Long(selectedParametro.getParamId());
                entity = businessDelegatorView.getParametro(paramId);
            }

            entity.setDescripcionParametros(FacesUtils.checkString(
                    txtDescripcionParametros));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setValorParametro(FacesUtils.checkString(txtValorParametro));
            businessDelegatorView.updateParametro(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedParametro = (ParametroDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedParametro"));

            Long paramId = new Long(selectedParametro.getParamId());
            entity = businessDelegatorView.getParametro(paramId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long paramId = FacesUtils.checkLong(txtParamId);
            entity = businessDelegatorView.getParametro(paramId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteParametro(entity);
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
            selectedParametro = (ParametroDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedParametro"));

            Long paramId = new Long(selectedParametro.getParamId());
            entity = businessDelegatorView.getParametro(paramId);
            businessDelegatorView.deleteParametro(entity);
            data.remove(selectedParametro);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionParametros,
        String estadoRegistro, Long paramId, String valorParametro)
        throws Exception {
        try {
            entity.setDescripcionParametros(FacesUtils.checkString(
                    descripcionParametros));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setValorParametro(FacesUtils.checkString(valorParametro));
            businessDelegatorView.updateParametro(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ParametroView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionParametros() {
        return txtDescripcionParametros;
    }

    public void setTxtDescripcionParametros(InputText txtDescripcionParametros) {
        this.txtDescripcionParametros = txtDescripcionParametros;
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtValorParametro() {
        return txtValorParametro;
    }

    public void setTxtValorParametro(InputText txtValorParametro) {
        this.txtValorParametro = txtValorParametro;
    }

    public InputText getTxtParamId() {
        return txtParamId;
    }

    public void setTxtParamId(InputText txtParamId) {
        this.txtParamId = txtParamId;
    }

    public List<ParametroDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataParametro();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ParametroDTO> parametroDTO) {
        this.data = parametroDTO;
    }

    public ParametroDTO getSelectedParametro() {
        return selectedParametro;
    }

    public void setSelectedParametro(ParametroDTO parametro) {
        this.selectedParametro = parametro;
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
