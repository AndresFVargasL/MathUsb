package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.TipoHerramientaDTO;
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
public class TipoHerramientaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoHerramientaView.class);
    private InputText txtDescripcionHerramienta;
    private InputText txtNombreCorto;
    private InputText txtTiheId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TipoHerramientaDTO> data;
    private TipoHerramientaDTO selectedTipoHerramienta;
    private TipoHerramienta entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TipoHerramientaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TipoHerramientaDTO tipoHerramientaDTO = (TipoHerramientaDTO) e.getObject();

            if (txtDescripcionHerramienta == null) {
                txtDescripcionHerramienta = new InputText();
            }

            txtDescripcionHerramienta.setValue(tipoHerramientaDTO.getDescripcionHerramienta());

            if (txtNombreCorto == null) {
                txtNombreCorto = new InputText();
            }

            txtNombreCorto.setValue(tipoHerramientaDTO.getNombreCorto());

            if (txtTiheId == null) {
                txtTiheId = new InputText();
            }

            txtTiheId.setValue(tipoHerramientaDTO.getTiheId());

            Long tiheId = FacesUtils.checkLong(txtTiheId);
            entity = businessDelegatorView.getTipoHerramienta(tiheId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTipoHerramienta = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTipoHerramienta = null;

        if (txtDescripcionHerramienta != null) {
            txtDescripcionHerramienta.setValue(null);
            txtDescripcionHerramienta.setDisabled(true);
        }

        if (txtNombreCorto != null) {
            txtNombreCorto.setValue(null);
            txtNombreCorto.setDisabled(true);
        }

        if (txtTiheId != null) {
            txtTiheId.setValue(null);
            txtTiheId.setDisabled(false);
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
            Long tiheId = FacesUtils.checkLong(txtTiheId);
            entity = (tiheId != null)
                ? businessDelegatorView.getTipoHerramienta(tiheId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionHerramienta.setDisabled(false);
            txtNombreCorto.setDisabled(false);
            txtTiheId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionHerramienta.setValue(entity.getDescripcionHerramienta());
            txtDescripcionHerramienta.setDisabled(false);
            txtNombreCorto.setValue(entity.getNombreCorto());
            txtNombreCorto.setDisabled(false);
            txtTiheId.setValue(entity.getTiheId());
            txtTiheId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTipoHerramienta = (TipoHerramientaDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedTipoHerramienta"));
        txtDescripcionHerramienta.setValue(selectedTipoHerramienta.getDescripcionHerramienta());
        txtDescripcionHerramienta.setDisabled(false);
        txtNombreCorto.setValue(selectedTipoHerramienta.getNombreCorto());
        txtNombreCorto.setDisabled(false);
        txtTiheId.setValue(selectedTipoHerramienta.getTiheId());
        txtTiheId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTipoHerramienta == null) && (entity == null)) {
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
            entity = new TipoHerramienta();

            Long tiheId = FacesUtils.checkLong(txtTiheId);

            entity.setDescripcionHerramienta(FacesUtils.checkString(
                    txtDescripcionHerramienta));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            entity.setTiheId(tiheId);
            businessDelegatorView.saveTipoHerramienta(entity);
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
                Long tiheId = new Long(selectedTipoHerramienta.getTiheId());
                entity = businessDelegatorView.getTipoHerramienta(tiheId);
            }

            entity.setDescripcionHerramienta(FacesUtils.checkString(
                    txtDescripcionHerramienta));
            entity.setNombreCorto(FacesUtils.checkString(txtNombreCorto));
            businessDelegatorView.updateTipoHerramienta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTipoHerramienta = (TipoHerramientaDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedTipoHerramienta"));

            Long tiheId = new Long(selectedTipoHerramienta.getTiheId());
            entity = businessDelegatorView.getTipoHerramienta(tiheId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long tiheId = FacesUtils.checkLong(txtTiheId);
            entity = businessDelegatorView.getTipoHerramienta(tiheId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTipoHerramienta(entity);
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
            selectedTipoHerramienta = (TipoHerramientaDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedTipoHerramienta"));

            Long tiheId = new Long(selectedTipoHerramienta.getTiheId());
            entity = businessDelegatorView.getTipoHerramienta(tiheId);
            businessDelegatorView.deleteTipoHerramienta(entity);
            data.remove(selectedTipoHerramienta);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionHerramienta,
        String nombreCorto, Long tiheId) throws Exception {
        try {
            entity.setDescripcionHerramienta(FacesUtils.checkString(
                    descripcionHerramienta));
            entity.setNombreCorto(FacesUtils.checkString(nombreCorto));
            businessDelegatorView.updateTipoHerramienta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipoHerramientaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionHerramienta() {
        return txtDescripcionHerramienta;
    }

    public void setTxtDescripcionHerramienta(
        InputText txtDescripcionHerramienta) {
        this.txtDescripcionHerramienta = txtDescripcionHerramienta;
    }

    public InputText getTxtNombreCorto() {
        return txtNombreCorto;
    }

    public void setTxtNombreCorto(InputText txtNombreCorto) {
        this.txtNombreCorto = txtNombreCorto;
    }

    public InputText getTxtTiheId() {
        return txtTiheId;
    }

    public void setTxtTiheId(InputText txtTiheId) {
        this.txtTiheId = txtTiheId;
    }

    public List<TipoHerramientaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTipoHerramienta();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipoHerramientaDTO> tipoHerramientaDTO) {
        this.data = tipoHerramientaDTO;
    }

    public TipoHerramientaDTO getSelectedTipoHerramienta() {
        return selectedTipoHerramienta;
    }

    public void setSelectedTipoHerramienta(TipoHerramientaDTO tipoHerramienta) {
        this.selectedTipoHerramienta = tipoHerramienta;
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
