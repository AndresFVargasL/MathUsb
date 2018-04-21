package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.GrupoUsuarioDTO;
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
public class GrupoUsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(GrupoUsuarioView.class);
    private InputText txtGrupId_Grupo;
    private InputText txtUsuaId_Usuario;
    private InputText txtGrusId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<GrupoUsuarioDTO> data;
    private GrupoUsuarioDTO selectedGrupoUsuario;
    private GrupoUsuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public GrupoUsuarioView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            GrupoUsuarioDTO grupoUsuarioDTO = (GrupoUsuarioDTO) e.getObject();

            if (txtGrupId_Grupo == null) {
                txtGrupId_Grupo = new InputText();
            }

            txtGrupId_Grupo.setValue(grupoUsuarioDTO.getGrupId_Grupo());

            if (txtUsuaId_Usuario == null) {
                txtUsuaId_Usuario = new InputText();
            }

            txtUsuaId_Usuario.setValue(grupoUsuarioDTO.getUsuaId_Usuario());

            if (txtGrusId == null) {
                txtGrusId = new InputText();
            }

            txtGrusId.setValue(grupoUsuarioDTO.getGrusId());

            Long grusId = FacesUtils.checkLong(txtGrusId);
            entity = businessDelegatorView.getGrupoUsuario(grusId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedGrupoUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedGrupoUsuario = null;

        if (txtGrupId_Grupo != null) {
            txtGrupId_Grupo.setValue(null);
            txtGrupId_Grupo.setDisabled(true);
        }

        if (txtUsuaId_Usuario != null) {
            txtUsuaId_Usuario.setValue(null);
            txtUsuaId_Usuario.setDisabled(true);
        }

        if (txtGrusId != null) {
            txtGrusId.setValue(null);
            txtGrusId.setDisabled(false);
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
            Long grusId = FacesUtils.checkLong(txtGrusId);
            entity = (grusId != null)
                ? businessDelegatorView.getGrupoUsuario(grusId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtGrupId_Grupo.setDisabled(false);
            txtUsuaId_Usuario.setDisabled(false);
            txtGrusId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtGrupId_Grupo.setValue(entity.getGrupo().getGrupId());
            txtGrupId_Grupo.setDisabled(false);
            txtUsuaId_Usuario.setValue(entity.getUsuario().getUsuaId());
            txtUsuaId_Usuario.setDisabled(false);
            txtGrusId.setValue(entity.getGrusId());
            txtGrusId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedGrupoUsuario = (GrupoUsuarioDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedGrupoUsuario"));
        txtGrupId_Grupo.setValue(selectedGrupoUsuario.getGrupId_Grupo());
        txtGrupId_Grupo.setDisabled(false);
        txtUsuaId_Usuario.setValue(selectedGrupoUsuario.getUsuaId_Usuario());
        txtUsuaId_Usuario.setDisabled(false);
        txtGrusId.setValue(selectedGrupoUsuario.getGrusId());
        txtGrusId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedGrupoUsuario == null) && (entity == null)) {
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
            entity = new GrupoUsuario();

            Long grusId = FacesUtils.checkLong(txtGrusId);

            entity.setGrusId(grusId);
            entity.setGrupo((FacesUtils.checkLong(txtGrupId_Grupo) != null)
                ? businessDelegatorView.getGrupo(FacesUtils.checkLong(
                        txtGrupId_Grupo)) : null);
            entity.setUsuario((FacesUtils.checkLong(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.saveGrupoUsuario(entity);
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
                Long grusId = new Long(selectedGrupoUsuario.getGrusId());
                entity = businessDelegatorView.getGrupoUsuario(grusId);
            }

            entity.setGrupo((FacesUtils.checkLong(txtGrupId_Grupo) != null)
                ? businessDelegatorView.getGrupo(FacesUtils.checkLong(
                        txtGrupId_Grupo)) : null);
            entity.setUsuario((FacesUtils.checkLong(txtUsuaId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtUsuaId_Usuario)) : null);
            businessDelegatorView.updateGrupoUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedGrupoUsuario = (GrupoUsuarioDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedGrupoUsuario"));

            Long grusId = new Long(selectedGrupoUsuario.getGrusId());
            entity = businessDelegatorView.getGrupoUsuario(grusId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long grusId = FacesUtils.checkLong(txtGrusId);
            entity = businessDelegatorView.getGrupoUsuario(grusId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteGrupoUsuario(entity);
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
            selectedGrupoUsuario = (GrupoUsuarioDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedGrupoUsuario"));

            Long grusId = new Long(selectedGrupoUsuario.getGrusId());
            entity = businessDelegatorView.getGrupoUsuario(grusId);
            businessDelegatorView.deleteGrupoUsuario(entity);
            data.remove(selectedGrupoUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long grusId, Long grupId_Grupo,
        Long usuaId_Usuario) throws Exception {
        try {
            businessDelegatorView.updateGrupoUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("GrupoUsuarioView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtGrupId_Grupo() {
        return txtGrupId_Grupo;
    }

    public void setTxtGrupId_Grupo(InputText txtGrupId_Grupo) {
        this.txtGrupId_Grupo = txtGrupId_Grupo;
    }

    public InputText getTxtUsuaId_Usuario() {
        return txtUsuaId_Usuario;
    }

    public void setTxtUsuaId_Usuario(InputText txtUsuaId_Usuario) {
        this.txtUsuaId_Usuario = txtUsuaId_Usuario;
    }

    public InputText getTxtGrusId() {
        return txtGrusId;
    }

    public void setTxtGrusId(InputText txtGrusId) {
        this.txtGrusId = txtGrusId;
    }

    public List<GrupoUsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataGrupoUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<GrupoUsuarioDTO> grupoUsuarioDTO) {
        this.data = grupoUsuarioDTO;
    }

    public GrupoUsuarioDTO getSelectedGrupoUsuario() {
        return selectedGrupoUsuario;
    }

    public void setSelectedGrupoUsuario(GrupoUsuarioDTO grupoUsuario) {
        this.selectedGrupoUsuario = grupoUsuario;
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
