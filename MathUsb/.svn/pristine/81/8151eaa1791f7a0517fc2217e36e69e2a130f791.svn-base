package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.EvaPregResDTO;
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
public class EvaPregResView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EvaPregResView.class);
    private InputText txtIndicePregunta;
    private InputText txtEvalId_Evaluacion;
    private InputText txtPregId_Pregunta;
    private InputText txtRespId_Respuesta;
    private InputText txtEvprId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<EvaPregResDTO> data;
    private EvaPregResDTO selectedEvaPregRes;
    private EvaPregRes entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public EvaPregResView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            EvaPregResDTO evaPregResDTO = (EvaPregResDTO) e.getObject();

            if (txtIndicePregunta == null) {
                txtIndicePregunta = new InputText();
            }

            txtIndicePregunta.setValue(evaPregResDTO.getIndicePregunta());

            if (txtEvalId_Evaluacion == null) {
                txtEvalId_Evaluacion = new InputText();
            }

            txtEvalId_Evaluacion.setValue(evaPregResDTO.getEvalId_Evaluacion());

            if (txtPregId_Pregunta == null) {
                txtPregId_Pregunta = new InputText();
            }

            txtPregId_Pregunta.setValue(evaPregResDTO.getPregId_Pregunta());

            if (txtRespId_Respuesta == null) {
                txtRespId_Respuesta = new InputText();
            }

            txtRespId_Respuesta.setValue(evaPregResDTO.getRespId_Respuesta());

            if (txtEvprId == null) {
                txtEvprId = new InputText();
            }

            txtEvprId.setValue(evaPregResDTO.getEvprId());

            Long evprId = FacesUtils.checkLong(txtEvprId);
            entity = businessDelegatorView.getEvaPregRes(evprId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedEvaPregRes = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedEvaPregRes = null;

        if (txtIndicePregunta != null) {
            txtIndicePregunta.setValue(null);
            txtIndicePregunta.setDisabled(true);
        }

        if (txtEvalId_Evaluacion != null) {
            txtEvalId_Evaluacion.setValue(null);
            txtEvalId_Evaluacion.setDisabled(true);
        }

        if (txtPregId_Pregunta != null) {
            txtPregId_Pregunta.setValue(null);
            txtPregId_Pregunta.setDisabled(true);
        }

        if (txtRespId_Respuesta != null) {
            txtRespId_Respuesta.setValue(null);
            txtRespId_Respuesta.setDisabled(true);
        }

        if (txtEvprId != null) {
            txtEvprId.setValue(null);
            txtEvprId.setDisabled(false);
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
            Long evprId = FacesUtils.checkLong(txtEvprId);
            entity = (evprId != null)
                ? businessDelegatorView.getEvaPregRes(evprId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtIndicePregunta.setDisabled(false);
            txtEvalId_Evaluacion.setDisabled(false);
            txtPregId_Pregunta.setDisabled(false);
            txtRespId_Respuesta.setDisabled(false);
            txtEvprId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtIndicePregunta.setValue(entity.getIndicePregunta());
            txtIndicePregunta.setDisabled(false);
            txtEvalId_Evaluacion.setValue(entity.getEvaluacion().getEvalId());
            txtEvalId_Evaluacion.setDisabled(false);
            txtPregId_Pregunta.setValue(entity.getPregunta().getPregId());
            txtPregId_Pregunta.setDisabled(false);
            txtRespId_Respuesta.setValue(entity.getRespuesta().getRespId());
            txtRespId_Respuesta.setDisabled(false);
            txtEvprId.setValue(entity.getEvprId());
            txtEvprId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedEvaPregRes = (EvaPregResDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedEvaPregRes"));
        txtIndicePregunta.setValue(selectedEvaPregRes.getIndicePregunta());
        txtIndicePregunta.setDisabled(false);
        txtEvalId_Evaluacion.setValue(selectedEvaPregRes.getEvalId_Evaluacion());
        txtEvalId_Evaluacion.setDisabled(false);
        txtPregId_Pregunta.setValue(selectedEvaPregRes.getPregId_Pregunta());
        txtPregId_Pregunta.setDisabled(false);
        txtRespId_Respuesta.setValue(selectedEvaPregRes.getRespId_Respuesta());
        txtRespId_Respuesta.setDisabled(false);
        txtEvprId.setValue(selectedEvaPregRes.getEvprId());
        txtEvprId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedEvaPregRes == null) && (entity == null)) {
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
            entity = new EvaPregRes();

            Long evprId = FacesUtils.checkLong(txtEvprId);

            entity.setEvprId(evprId);
            entity.setIndicePregunta(FacesUtils.checkLong(txtIndicePregunta));
            entity.setEvaluacion((FacesUtils.checkLong(txtEvalId_Evaluacion) != null)
                ? businessDelegatorView.getEvaluacion(FacesUtils.checkLong(
                        txtEvalId_Evaluacion)) : null);
            entity.setPregunta((FacesUtils.checkLong(txtPregId_Pregunta) != null)
                ? businessDelegatorView.getPregunta(FacesUtils.checkLong(
                        txtPregId_Pregunta)) : null);
            entity.setRespuesta((FacesUtils.checkLong(txtRespId_Respuesta) != null)
                ? businessDelegatorView.getRespuesta(FacesUtils.checkLong(
                        txtRespId_Respuesta)) : null);
            businessDelegatorView.saveEvaPregRes(entity);
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
                Long evprId = new Long(selectedEvaPregRes.getEvprId());
                entity = businessDelegatorView.getEvaPregRes(evprId);
            }

            entity.setIndicePregunta(FacesUtils.checkLong(txtIndicePregunta));
            entity.setEvaluacion((FacesUtils.checkLong(txtEvalId_Evaluacion) != null)
                ? businessDelegatorView.getEvaluacion(FacesUtils.checkLong(
                        txtEvalId_Evaluacion)) : null);
            entity.setPregunta((FacesUtils.checkLong(txtPregId_Pregunta) != null)
                ? businessDelegatorView.getPregunta(FacesUtils.checkLong(
                        txtPregId_Pregunta)) : null);
            entity.setRespuesta((FacesUtils.checkLong(txtRespId_Respuesta) != null)
                ? businessDelegatorView.getRespuesta(FacesUtils.checkLong(
                        txtRespId_Respuesta)) : null);
            businessDelegatorView.updateEvaPregRes(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedEvaPregRes = (EvaPregResDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedEvaPregRes"));

            Long evprId = new Long(selectedEvaPregRes.getEvprId());
            entity = businessDelegatorView.getEvaPregRes(evprId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long evprId = FacesUtils.checkLong(txtEvprId);
            entity = businessDelegatorView.getEvaPregRes(evprId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteEvaPregRes(entity);
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
            selectedEvaPregRes = (EvaPregResDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedEvaPregRes"));

            Long evprId = new Long(selectedEvaPregRes.getEvprId());
            entity = businessDelegatorView.getEvaPregRes(evprId);
            businessDelegatorView.deleteEvaPregRes(entity);
            data.remove(selectedEvaPregRes);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long evprId, Long indicePregunta,
        Long evalId_Evaluacion, Long pregId_Pregunta, Long respId_Respuesta)
        throws Exception {
        try {
            entity.setIndicePregunta(FacesUtils.checkLong(indicePregunta));
            businessDelegatorView.updateEvaPregRes(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("EvaPregResView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtIndicePregunta() {
        return txtIndicePregunta;
    }

    public void setTxtIndicePregunta(InputText txtIndicePregunta) {
        this.txtIndicePregunta = txtIndicePregunta;
    }

    public InputText getTxtEvalId_Evaluacion() {
        return txtEvalId_Evaluacion;
    }

    public void setTxtEvalId_Evaluacion(InputText txtEvalId_Evaluacion) {
        this.txtEvalId_Evaluacion = txtEvalId_Evaluacion;
    }

    public InputText getTxtPregId_Pregunta() {
        return txtPregId_Pregunta;
    }

    public void setTxtPregId_Pregunta(InputText txtPregId_Pregunta) {
        this.txtPregId_Pregunta = txtPregId_Pregunta;
    }

    public InputText getTxtRespId_Respuesta() {
        return txtRespId_Respuesta;
    }

    public void setTxtRespId_Respuesta(InputText txtRespId_Respuesta) {
        this.txtRespId_Respuesta = txtRespId_Respuesta;
    }

    public InputText getTxtEvprId() {
        return txtEvprId;
    }

    public void setTxtEvprId(InputText txtEvprId) {
        this.txtEvprId = txtEvprId;
    }

    public List<EvaPregResDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataEvaPregRes();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<EvaPregResDTO> evaPregResDTO) {
        this.data = evaPregResDTO;
    }

    public EvaPregResDTO getSelectedEvaPregRes() {
        return selectedEvaPregRes;
    }

    public void setSelectedEvaPregRes(EvaPregResDTO evaPregRes) {
        this.selectedEvaPregRes = evaPregRes;
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
