package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.AsignaturaDTO;
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
public class AsignaturaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(AsignaturaView.class);
    private InputText txtNombreAsignatura;
    private InputText txtAsigId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<AsignaturaDTO> data;
    private AsignaturaDTO selectedAsignatura;
    private Asignatura entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    
    //Nuevo Atributos
    private InputText txtModifyNombreAsignatura;
    private InputText txtModifyAsigId;
    private InputText txtDeleteNombreAsignatura;
    private InputText txtDeleteAsigId;
    private InputText txtCreateNombreAsignatura;
    private boolean showDlg;
    
    public AsignaturaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            AsignaturaDTO asignaturaDTO = (AsignaturaDTO) e.getObject();

            if (txtNombreAsignatura == null) {
                txtNombreAsignatura = new InputText();
            }

            txtNombreAsignatura.setValue(asignaturaDTO.getNombreAsignatura());

            if (txtAsigId == null) {
                txtAsigId = new InputText();
            }

            txtAsigId.setValue(asignaturaDTO.getAsigId());

            Long asigId = FacesUtils.checkLong(txtAsigId);
            entity = businessDelegatorView.getAsignatura(asigId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedAsignatura = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedAsignatura = null;

        if (txtNombreAsignatura != null) {
            txtNombreAsignatura.setValue(null);
            txtNombreAsignatura.setDisabled(true);
        }

        if (txtAsigId != null) {
            txtAsigId.setValue(null);
            txtAsigId.setDisabled(false);
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
            Long asigId = FacesUtils.checkLong(txtAsigId);
            entity = (asigId != null)
                ? businessDelegatorView.getAsignatura(asigId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtNombreAsignatura.setDisabled(false);
            txtAsigId.setDisabled(false);
            btnModify.setDisabled(false);
        } else {
        	txtNombreAsignatura.setValue(entity.getNombreAsignatura());
        	txtNombreAsignatura.setDisabled(false);
        	txtAsigId.setValue(entity.getAsigId());
        	txtAsigId.setDisabled(true);
            btnModify.setDisabled(true);
            
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedAsignatura = (AsignaturaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedAsignatura"));
        txtNombreAsignatura.setValue(selectedAsignatura.getNombreAsignatura());
        txtNombreAsignatura.setDisabled(false);
        txtAsigId.setValue(selectedAsignatura.getAsigId());
        txtAsigId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedAsignatura == null) && (entity == null)) {
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
            //entity = new Asignatura();
        	Asignatura asignatura = new Asignatura();
            //Long asigId = FacesUtils.checkLong(txtAsigId);
        	
        	asignatura.setAsigId(null);
        	asignatura.setNombreAsignatura(txtCreateNombreAsignatura.getValue().toString());
            businessDelegatorView.saveAsignatura(asignatura);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            //action_clear();            
            txtCreateNombreAsignatura.setDisabled(true);
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }    
    

    public String action_modify() {
        try {
            if (entity == null) {
                Long asigId = new Long(selectedAsignatura.getAsigId());
            	//Long asigId = FacesUtils.checkLong(txtAsigId.getValue());
                entity = businessDelegatorView.getAsignatura(asigId);
            }

            entity.setNombreAsignatura(FacesUtils.checkString(
                    txtNombreAsignatura));
            businessDelegatorView.updateAsignatura(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            txtNombreAsignatura.setDisabled(true);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedAsignatura = (AsignaturaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedAsignatura"));

            Long asigId = new Long(selectedAsignatura.getAsigId());
            entity = businessDelegatorView.getAsignatura(asigId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long asigId = FacesUtils.checkLong(txtAsigId);
            entity = businessDelegatorView.getAsignatura(asigId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteAsignatura(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            txtAsigId.setValue(null);
            txtNombreAsignatura.setValue(null);
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
            selectedAsignatura = (AsignaturaDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedAsignatura"));

            Long asigId = new Long(selectedAsignatura.getAsigId());
            entity = businessDelegatorView.getAsignatura(asigId);
            businessDelegatorView.deleteAsignatura(entity);
            data.remove(selectedAsignatura);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long asigId, String nombreAsignatura)
        throws Exception {
        try {
            entity.setNombreAsignatura(FacesUtils.checkString(nombreAsignatura));
            businessDelegatorView.updateAsignatura(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("AsignaturaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtNombreAsignatura() {
        return txtNombreAsignatura;
    }

    public void setTxtNombreAsignatura(InputText txtNombreAsignatura) {
        this.txtNombreAsignatura = txtNombreAsignatura;
    }

    public InputText getTxtAsigId() {
        return txtAsigId;
    }

    public void setTxtAsigId(InputText txtAsigId) {
        this.txtAsigId = txtAsigId;
    }

    public List<AsignaturaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataAsignatura();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<AsignaturaDTO> asignaturaDTO) {
        this.data = asignaturaDTO;
    }

    public AsignaturaDTO getSelectedAsignatura() {
        return selectedAsignatura;
    }

    public void setSelectedAsignatura(AsignaturaDTO asignatura) {
        this.selectedAsignatura = asignatura;
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
    
    //Metodos Nuevos
    
    public InputText getTxtModifyNombreAsignatura() {
		return txtModifyNombreAsignatura;
	}

	public void setTxtModifyNombreAsignatura(InputText txtModifyNombreAsignatura) {
		this.txtModifyNombreAsignatura = txtModifyNombreAsignatura;
	}

	public InputText getTxtModifyAsigId() {
		return txtModifyAsigId;
	}

	public void setTxtModifyAsigId(InputText txtModifyAsigId) {
		this.txtModifyAsigId = txtModifyAsigId;
	}

	public InputText getTxtDeleteNombreAsignatura() {
		return txtDeleteNombreAsignatura;
	}

	public void setTxtDeleteNombreAsignatura(InputText txtDeleteNombreAsignatura) {
		this.txtDeleteNombreAsignatura = txtDeleteNombreAsignatura;
	}

	public InputText getTxtDeleteAsigId() {
		return txtDeleteAsigId;
	}

	public void setTxtDeleteAsigId(InputText txtDeleteAsigId) {
		this.txtDeleteAsigId = txtDeleteAsigId;
	}

	public InputText getTxtCreateNombreAsignatura() {
		return txtCreateNombreAsignatura;
	}

	public void setTxtCreateNombreAsignatura(InputText txtCreateNombreAsignatura) {
		this.txtCreateNombreAsignatura = txtCreateNombreAsignatura;
	}

	public boolean isShowDlg() {
		return showDlg;
	}

	public void setShowDlg(boolean showDlg) {
		this.showDlg = showDlg;
	}
	
	public String action_closeDlg() {
        setShowDlg(false);
        txtCreateNombreAsignatura.setValue(null);
        txtCreateNombreAsignatura.setDisabled(false);

        return "";
    }
	
	public String action_openDlg() {
               
        setShowDlg(true);

        return "";
    }
}
