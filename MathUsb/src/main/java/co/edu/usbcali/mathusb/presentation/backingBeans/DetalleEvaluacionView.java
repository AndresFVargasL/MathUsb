package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.DetalleEvaluacionDTO;
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
public class DetalleEvaluacionView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DetalleEvaluacionView.class);
    private InputText txtAsunto;
    private InputText txtCorte;
    private InputText txtEvalId_Evaluacion;
    private InputText txtDeevId;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaFinPublicacion;
    private Calendar txtFechaInicioPublicacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<DetalleEvaluacionDTO> data;
    private DetalleEvaluacionDTO selectedDetalleEvaluacion;
    private DetalleEvaluacion entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public DetalleEvaluacionView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            DetalleEvaluacionDTO detalleEvaluacionDTO = (DetalleEvaluacionDTO) e.getObject();

            if (txtAsunto == null) {
                txtAsunto = new InputText();
            }

            txtAsunto.setValue(detalleEvaluacionDTO.getAsunto());

            if (txtCorte == null) {
                txtCorte = new InputText();
            }

            txtCorte.setValue(detalleEvaluacionDTO.getCorte());

            if (txtEvalId_Evaluacion == null) {
                txtEvalId_Evaluacion = new InputText();
            }

            txtEvalId_Evaluacion.setValue(detalleEvaluacionDTO.getEvalId_Evaluacion());

            if (txtDeevId == null) {
                txtDeevId = new InputText();
            }

            txtDeevId.setValue(detalleEvaluacionDTO.getDeevId());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(detalleEvaluacionDTO.getFechaCreacion());

            if (txtFechaFinPublicacion == null) {
                txtFechaFinPublicacion = new Calendar();
            }

            txtFechaFinPublicacion.setValue(detalleEvaluacionDTO.getFechaFinPublicacion());

            if (txtFechaInicioPublicacion == null) {
                txtFechaInicioPublicacion = new Calendar();
            }

            txtFechaInicioPublicacion.setValue(detalleEvaluacionDTO.getFechaInicioPublicacion());

            Long deevId = FacesUtils.checkLong(txtDeevId);
            entity = businessDelegatorView.getDetalleEvaluacion(deevId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedDetalleEvaluacion = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedDetalleEvaluacion = null;

        if (txtAsunto != null) {
            txtAsunto.setValue(null);
            txtAsunto.setDisabled(true);
        }

        if (txtCorte != null) {
            txtCorte.setValue(null);
            txtCorte.setDisabled(true);
        }

        if (txtEvalId_Evaluacion != null) {
            txtEvalId_Evaluacion.setValue(null);
            txtEvalId_Evaluacion.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaFinPublicacion != null) {
            txtFechaFinPublicacion.setValue(null);
            txtFechaFinPublicacion.setDisabled(true);
        }

        if (txtFechaInicioPublicacion != null) {
            txtFechaInicioPublicacion.setValue(null);
            txtFechaInicioPublicacion.setDisabled(true);
        }

        if (txtDeevId != null) {
            txtDeevId.setValue(null);
            txtDeevId.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaCreacion() {
        Date inputDate = (Date) txtFechaCreacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaFinPublicacion() {
        Date inputDate = (Date) txtFechaFinPublicacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaInicioPublicacion() {
        Date inputDate = (Date) txtFechaInicioPublicacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long deevId = FacesUtils.checkLong(txtDeevId);
            entity = (deevId != null)
                ? businessDelegatorView.getDetalleEvaluacion(deevId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtAsunto.setDisabled(false);
            txtCorte.setDisabled(false);
            txtEvalId_Evaluacion.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaFinPublicacion.setDisabled(false);
            txtFechaInicioPublicacion.setDisabled(false);
            txtDeevId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtAsunto.setValue(entity.getAsunto());
            txtAsunto.setDisabled(false);
            txtCorte.setValue(entity.getCorte());
            txtCorte.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaFinPublicacion.setValue(entity.getFechaFinPublicacion());
            txtFechaFinPublicacion.setDisabled(false);
            txtFechaInicioPublicacion.setValue(entity.getFechaInicioPublicacion());
            txtFechaInicioPublicacion.setDisabled(false);
            txtEvalId_Evaluacion.setValue(entity.getEvaluacion().getEvalId());
            txtEvalId_Evaluacion.setDisabled(false);
            txtDeevId.setValue(entity.getDeevId());
            txtDeevId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedDetalleEvaluacion = (DetalleEvaluacionDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedDetalleEvaluacion"));
        txtAsunto.setValue(selectedDetalleEvaluacion.getAsunto());
        txtAsunto.setDisabled(false);
        txtCorte.setValue(selectedDetalleEvaluacion.getCorte());
        txtCorte.setDisabled(false);
        txtFechaCreacion.setValue(selectedDetalleEvaluacion.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaFinPublicacion.setValue(selectedDetalleEvaluacion.getFechaFinPublicacion());
        txtFechaFinPublicacion.setDisabled(false);
        txtFechaInicioPublicacion.setValue(selectedDetalleEvaluacion.getFechaInicioPublicacion());
        txtFechaInicioPublicacion.setDisabled(false);
        txtEvalId_Evaluacion.setValue(selectedDetalleEvaluacion.getEvalId_Evaluacion());
        txtEvalId_Evaluacion.setDisabled(false);
        txtDeevId.setValue(selectedDetalleEvaluacion.getDeevId());
        txtDeevId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedDetalleEvaluacion == null) && (entity == null)) {
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
            entity = new DetalleEvaluacion();

            Long deevId = FacesUtils.checkLong(txtDeevId);

            entity.setAsunto(FacesUtils.checkString(txtAsunto));
            entity.setCorte(FacesUtils.checkLong(txtCorte));
            entity.setDeevId(deevId);
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaFinPublicacion(FacesUtils.checkDate(
                    txtFechaFinPublicacion));
            entity.setFechaInicioPublicacion(FacesUtils.checkDate(
                    txtFechaInicioPublicacion));
            entity.setEvaluacion((FacesUtils.checkLong(txtEvalId_Evaluacion) != null)
                ? businessDelegatorView.getEvaluacion(FacesUtils.checkLong(
                        txtEvalId_Evaluacion)) : null);
            businessDelegatorView.saveDetalleEvaluacion(entity);
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
                Long deevId = new Long(selectedDetalleEvaluacion.getDeevId());
                entity = businessDelegatorView.getDetalleEvaluacion(deevId);
            }

            entity.setAsunto(FacesUtils.checkString(txtAsunto));
            entity.setCorte(FacesUtils.checkLong(txtCorte));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaFinPublicacion(FacesUtils.checkDate(
                    txtFechaFinPublicacion));
            entity.setFechaInicioPublicacion(FacesUtils.checkDate(
                    txtFechaInicioPublicacion));
            entity.setEvaluacion((FacesUtils.checkLong(txtEvalId_Evaluacion) != null)
                ? businessDelegatorView.getEvaluacion(FacesUtils.checkLong(
                        txtEvalId_Evaluacion)) : null);
            businessDelegatorView.updateDetalleEvaluacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedDetalleEvaluacion = (DetalleEvaluacionDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedDetalleEvaluacion"));

            Long deevId = new Long(selectedDetalleEvaluacion.getDeevId());
            entity = businessDelegatorView.getDetalleEvaluacion(deevId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long deevId = FacesUtils.checkLong(txtDeevId);
            entity = businessDelegatorView.getDetalleEvaluacion(deevId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteDetalleEvaluacion(entity);
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
            selectedDetalleEvaluacion = (DetalleEvaluacionDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedDetalleEvaluacion"));

            Long deevId = new Long(selectedDetalleEvaluacion.getDeevId());
            entity = businessDelegatorView.getDetalleEvaluacion(deevId);
            businessDelegatorView.deleteDetalleEvaluacion(entity);
            data.remove(selectedDetalleEvaluacion);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String asunto, Long corte, Long deevId,
        Date fechaCreacion, Date fechaFinPublicacion,
        Date fechaInicioPublicacion, Long evalId_Evaluacion)
        throws Exception {
        try {
            entity.setAsunto(FacesUtils.checkString(asunto));
            entity.setCorte(FacesUtils.checkLong(corte));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaFinPublicacion(FacesUtils.checkDate(
                    fechaFinPublicacion));
            entity.setFechaInicioPublicacion(FacesUtils.checkDate(
                    fechaInicioPublicacion));
            businessDelegatorView.updateDetalleEvaluacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("DetalleEvaluacionView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtAsunto() {
        return txtAsunto;
    }

    public void setTxtAsunto(InputText txtAsunto) {
        this.txtAsunto = txtAsunto;
    }

    public InputText getTxtCorte() {
        return txtCorte;
    }

    public void setTxtCorte(InputText txtCorte) {
        this.txtCorte = txtCorte;
    }

    public InputText getTxtEvalId_Evaluacion() {
        return txtEvalId_Evaluacion;
    }

    public void setTxtEvalId_Evaluacion(InputText txtEvalId_Evaluacion) {
        this.txtEvalId_Evaluacion = txtEvalId_Evaluacion;
    }

    public Calendar getTxtFechaCreacion() {
        return txtFechaCreacion;
    }

    public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
        this.txtFechaCreacion = txtFechaCreacion;
    }

    public Calendar getTxtFechaFinPublicacion() {
        return txtFechaFinPublicacion;
    }

    public void setTxtFechaFinPublicacion(Calendar txtFechaFinPublicacion) {
        this.txtFechaFinPublicacion = txtFechaFinPublicacion;
    }

    public Calendar getTxtFechaInicioPublicacion() {
        return txtFechaInicioPublicacion;
    }

    public void setTxtFechaInicioPublicacion(Calendar txtFechaInicioPublicacion) {
        this.txtFechaInicioPublicacion = txtFechaInicioPublicacion;
    }

    public InputText getTxtDeevId() {
        return txtDeevId;
    }

    public void setTxtDeevId(InputText txtDeevId) {
        this.txtDeevId = txtDeevId;
    }

    public List<DetalleEvaluacionDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataDetalleEvaluacion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<DetalleEvaluacionDTO> detalleEvaluacionDTO) {
        this.data = detalleEvaluacionDTO;
    }

    public DetalleEvaluacionDTO getSelectedDetalleEvaluacion() {
        return selectedDetalleEvaluacion;
    }

    public void setSelectedDetalleEvaluacion(
        DetalleEvaluacionDTO detalleEvaluacion) {
        this.selectedDetalleEvaluacion = detalleEvaluacion;
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
