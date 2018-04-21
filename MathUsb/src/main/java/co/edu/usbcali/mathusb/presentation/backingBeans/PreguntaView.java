package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.ComentarioDTO;
import co.edu.usbcali.mathusb.modelo.dto.EvaluacionDTO;
import co.edu.usbcali.mathusb.modelo.dto.GrupoDTO;
import co.edu.usbcali.mathusb.modelo.dto.PreguntaDTO;
import co.edu.usbcali.mathusb.modelo.dto.TemaDTO;
import co.edu.usbcali.mathusb.presentation.businessDelegate.*;
import co.edu.usbcali.mathusb.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.net.URL;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PreguntaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PreguntaView.class);
    private InputText txtDescripcionPregunta;
    private InputText txtTemaId_Tema;
    private InputText txtPregId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PreguntaDTO> data;
    private PreguntaDTO selectedPregunta;
    private Pregunta entity;
    private boolean showDialog;
    
    private InputText txtPalabraClave;
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    private String somTemasString;
	private List<SelectItem> losTemas;
    
	private InputText txtContenidoPreguntaEvaluacion;
	private String temaDeLaPregunta;
	
	private List<PreguntaDTO> lasPreguntasDadoTema;
	private List<TemaDTO> temasRepositorioPreguntas;
	
	private String palabraClave = "";
	
    public PreguntaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PreguntaDTO preguntaDTO = (PreguntaDTO) e.getObject();

            if (txtDescripcionPregunta == null) {
                txtDescripcionPregunta = new InputText();
            }

            txtDescripcionPregunta.setValue(preguntaDTO.getDescripcionPregunta());

            if (txtTemaId_Tema == null) {
                txtTemaId_Tema = new InputText();
            }

            txtTemaId_Tema.setValue(preguntaDTO.getTemaId_Tema());

            if (txtPregId == null) {
                txtPregId = new InputText();
            }

            txtPregId.setValue(preguntaDTO.getPregId());

            Long pregId = FacesUtils.checkLong(txtPregId);
            entity = businessDelegatorView.getPregunta(pregId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPregunta = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPregunta = null;

        if (txtDescripcionPregunta != null) {
            txtDescripcionPregunta.setValue(null);
            txtDescripcionPregunta.setDisabled(true);
        }

        if (txtTemaId_Tema != null) {
            txtTemaId_Tema.setValue(null);
            txtTemaId_Tema.setDisabled(true);
        }

        if (txtPregId != null) {
            txtPregId.setValue(null);
            txtPregId.setDisabled(false);
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
            Long pregId = FacesUtils.checkLong(txtPregId);
            entity = (pregId != null)
                ? businessDelegatorView.getPregunta(pregId) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionPregunta.setDisabled(false);
            txtTemaId_Tema.setDisabled(false);
            txtPregId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionPregunta.setValue(entity.getDescripcionPregunta());
            txtDescripcionPregunta.setDisabled(false);
            txtTemaId_Tema.setValue(entity.getTema().getTemaId());
            txtTemaId_Tema.setDisabled(false);
            txtPregId.setValue(entity.getPregId());
            txtPregId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPregunta = (PreguntaDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPregunta"));
        txtDescripcionPregunta.setValue(selectedPregunta.getDescripcionPregunta());
        txtDescripcionPregunta.setDisabled(false);
        txtTemaId_Tema.setValue(selectedPregunta.getTemaId_Tema());
        txtTemaId_Tema.setDisabled(false);
        txtPregId.setValue(selectedPregunta.getPregId());
        txtPregId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPregunta == null) && (entity == null)) {
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
            entity = new Pregunta();

            Long pregId = FacesUtils.checkLong(txtPregId);

            entity.setDescripcionPregunta(FacesUtils.checkString(
                    txtDescripcionPregunta));
            entity.setPregId(pregId);
            entity.setTema((FacesUtils.checkLong(txtTemaId_Tema) != null)
                ? businessDelegatorView.getTema(FacesUtils.checkLong(
                        txtTemaId_Tema)) : null);
            businessDelegatorView.savePregunta(entity);
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
                Long pregId = new Long(selectedPregunta.getPregId());
                entity = businessDelegatorView.getPregunta(pregId);
            }

            entity.setDescripcionPregunta(FacesUtils.checkString(
                    txtDescripcionPregunta));
            entity.setTema((FacesUtils.checkLong(txtTemaId_Tema) != null)
                ? businessDelegatorView.getTema(FacesUtils.checkLong(
                        txtTemaId_Tema)) : null);
            businessDelegatorView.updatePregunta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPregunta = (PreguntaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPregunta"));

            Long pregId = new Long(selectedPregunta.getPregId());
            entity = businessDelegatorView.getPregunta(pregId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long pregId = FacesUtils.checkLong(txtPregId);
            entity = businessDelegatorView.getPregunta(pregId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePregunta(entity);
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
            selectedPregunta = (PreguntaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPregunta"));

            Long pregId = new Long(selectedPregunta.getPregId());
            entity = businessDelegatorView.getPregunta(pregId);
            businessDelegatorView.deletePregunta(entity);
            data.remove(selectedPregunta);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionPregunta, Long pregId,
        Long temaId_Tema) throws Exception {
        try {
            entity.setDescripcionPregunta(FacesUtils.checkString(
                    descripcionPregunta));
            businessDelegatorView.updatePregunta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PreguntaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionPregunta() {
        return txtDescripcionPregunta;
    }

    public void setTxtDescripcionPregunta(InputText txtDescripcionPregunta) {
        this.txtDescripcionPregunta = txtDescripcionPregunta;
    }

    public InputText getTxtTemaId_Tema() {
        return txtTemaId_Tema;
    }

    public void setTxtTemaId_Tema(InputText txtTemaId_Tema) {
        this.txtTemaId_Tema = txtTemaId_Tema;
    }

    public InputText getTxtPregId() {
        return txtPregId;
    }

    public void setTxtPregId(InputText txtPregId) {
        this.txtPregId = txtPregId;
    }

    public List<PreguntaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPregunta();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PreguntaDTO> preguntaDTO) {
        this.data = preguntaDTO;
    }

    public PreguntaDTO getSelectedPregunta() {
        return selectedPregunta;
    }

    public void setSelectedPregunta(PreguntaDTO pregunta) {
        this.selectedPregunta = pregunta;
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

	public String getSomTemasString() {
		return somTemasString;
	}

	public List<SelectItem> getLosTemas() {
		try {
			if (losTemas == null) {
				losTemas = new ArrayList<SelectItem>();
				List<TemaDTO> losTemasDTO = businessDelegatorView.getDataTema();
				for (TemaDTO temaDTO: losTemasDTO) {
					SelectItem selectItem = new SelectItem(temaDTO.getTemaId(),
							temaDTO.getTituloTema());
					losTemas.add(selectItem);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return losTemas;
	}

	public void setSomTemasString(String somTemasString) {
		this.somTemasString = somTemasString;
	}

	public void setLosTemas(List<SelectItem> losTemas) {
		this.losTemas = losTemas;
	}

	public InputText getTxtContenidoPreguntaEvaluacion() {
		return txtContenidoPreguntaEvaluacion;
	}

	public void setTxtContenidoPreguntaEvaluacion(InputText txtContenidoPreguntaEvaluacion) {
		this.txtContenidoPreguntaEvaluacion = txtContenidoPreguntaEvaluacion;
	}
	
	public String getTemaDeLaPregunta() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			TemaDTO elTema = (TemaDTO) session.getAttribute("preguntaTema");
			temaDeLaPregunta = elTema.getTituloTema();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temaDeLaPregunta;
	}

	public void setTemaDeLaPregunta(String temaDeLaPregunta) {
		this.temaDeLaPregunta = temaDeLaPregunta;
	}
	
	/*Metodos*/	

	public List<PreguntaDTO> getLasPreguntasDadoTema() {
		return lasPreguntasDadoTema;
	}

	public void setLasPreguntasDadoTema(List<PreguntaDTO> lasPreguntasDadoTema) {
		this.lasPreguntasDadoTema = lasPreguntasDadoTema;
	}
	
	public InputText getTxtPalabraClave() {
		return txtPalabraClave;
	}

	public void setTxtPalabraClave(InputText txtPalabraClave) {
		this.txtPalabraClave = txtPalabraClave;
	}
	
	public List<TemaDTO> getTemasRepositorioPreguntas() {
		try {
			temasRepositorioPreguntas = new ArrayList<>();
			temasRepositorioPreguntas = businessDelegatorView.obtenerTemaDadoPalabraClave(palabraClave);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temasRepositorioPreguntas;
	}

	public void setTemasRepositorioPreguntas(List<TemaDTO> temasRepositorioPreguntas) {
		this.temasRepositorioPreguntas = temasRepositorioPreguntas;
	}

	public void registrarPregunta(){
		try {
			log.info("Entró a registrar pregunta");
			Pregunta pregunta = new Pregunta();
			EvaPregRes evaPregRes = new EvaPregRes();
			pregunta.setPregId(null);
			pregunta.setDescripcionPregunta(FacesUtils.checkString(txtContenidoPreguntaEvaluacion));
			pregunta.setTema(businessDelegatorView.getTema(Long.parseLong(somTemasString)));
			log.info("Pregunta creada");
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			EvaluacionDTO evaluacionMostrar = (EvaluacionDTO) session.getAttribute("evaluacionMostrar");
			Evaluacion evaluacion = businessDelegatorView.getEvaluacion(evaluacionMostrar.getEvalId());
			businessDelegatorView.savePregunta(pregunta);
			log.info("Pregunta guardada en bd");
			evaPregRes.setEvprId(null);
			evaPregRes.setEvaluacion(evaluacion);
			evaPregRes.setPregunta(pregunta);
			evaPregRes.setIndicePregunta(new Long(0));
			businessDelegatorView.saveEvaPregRes(evaPregRes);
			txtContenidoPreguntaEvaluacion.setValue("Se ha guardado la pregunta en la evaluación satisfactoriamente");
			log.info("Pregunta guardada");
			FacesUtils.resetManagedBean("evaluacionView");
		} catch (Exception e) {
			txtContenidoPreguntaEvaluacion.setValue(e.getMessage());
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void ponerEnSessionPreguntaDocumento(ActionEvent evt){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		PreguntaDTO preguntaDTO = (PreguntaDTO) (evt.getComponent().getAttributes().get("preguntaDocMostrar"));
		
		session.setAttribute("preguntaDocMostrar", preguntaDTO);
	}
	
	public void registrarPreguntaTema(){
		try {
			log.info("Entró a registrar pregunta");
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			TemaDTO elTema = (TemaDTO) session.getAttribute("preguntaTema");
			
			Pregunta pregunta = new Pregunta();
			pregunta.setPregId(null);
			pregunta.setDescripcionPregunta(FacesUtils.checkString(txtContenidoPreguntaEvaluacion));
			pregunta.setTema(businessDelegatorView.getTema(elTema.getTemaId()));
			log.info("Pregunta creada");
			businessDelegatorView.savePregunta(pregunta);
			log.info("Pregunta guardada en bd");
			txtContenidoPreguntaEvaluacion.setValue("Se ha asociado la pregunta al tema satisfactoriamente");
			log.info("Pregunta guardada");
		} catch (Exception e) {
			txtContenidoPreguntaEvaluacion.setValue(e.getMessage());
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void ponerEnSessionTema(ActionEvent evt)
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		TemaDTO temaDTO = (TemaDTO) (evt.getComponent().getAttributes().get("preguntaTema"));
		
		session.setAttribute("preguntaTema", temaDTO);
	}
	
	public void mostrarListaDePreguntasDadoTema (ActionEvent evt) {
		TemaDTO temaDTO = (TemaDTO) (evt.getComponent().getAttributes().get("temaPreguntas"));
		try {
			Long idTema = temaDTO.getTemaId();
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("temaPreguntaEval", temaDTO);
			lasPreguntasDadoTema = businessDelegatorView.consultarPreguntasDadoIdTema(idTema);
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void agregarPreguntaEvaluacionDesdeRepositorio(ActionEvent evt){
		try {
			PreguntaDTO preguntaDTO = (PreguntaDTO) (evt.getComponent().getAttributes().get("preguntaDocAgregar"));
			Pregunta pregunta = businessDelegatorView.getPregunta(preguntaDTO.getPregId());
			EvaPregRes evaPregRes = new EvaPregRes();
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			EvaluacionDTO evaluacionMostrar = (EvaluacionDTO) session.getAttribute("evaluacionMostrar");
			Evaluacion evaluacion = businessDelegatorView.getEvaluacion(evaluacionMostrar.getEvalId());

			evaPregRes.setEvprId(null);
			evaPregRes.setEvaluacion(evaluacion);
			evaPregRes.setPregunta(pregunta);
			evaPregRes.setIndicePregunta(new Long(0));
			businessDelegatorView.saveEvaPregRes(evaPregRes);
			log.info("Pregunta guardada");
			FacesUtils.addInfoMessage("La pregunta ha sido agregada al documento");
			TemaDTO temaDTO = (TemaDTO) session.getAttribute("temaPreguntaEval");
			Long idTema = temaDTO.getTemaId();
			lasPreguntasDadoTema = businessDelegatorView.consultarPreguntasDadoIdTema(idTema);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void obtenerTemasPorPalabraClave(){
		try {
			palabraClave = txtPalabraClave.getValue().toString().toUpperCase();
			temasRepositorioPreguntas = null;
			getTemasRepositorioPreguntas();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
