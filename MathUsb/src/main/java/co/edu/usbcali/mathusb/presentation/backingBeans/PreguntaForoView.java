package co.edu.usbcali.mathusb.presentation.backingBeans;

import co.edu.usbcali.mathusb.modelo.Comentario;
import co.edu.usbcali.mathusb.modelo.Parametro;
import co.edu.usbcali.mathusb.modelo.Usuario;
import co.edu.usbcali.mathusb.modelo.dto.ComentarioDTO;
import co.edu.usbcali.mathusb.modelo.dto.PreguntaDTO;
import co.edu.usbcali.mathusb.presentation.businessDelegate.*;
import co.edu.usbcali.mathusb.utilities.FacesUtils;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class PreguntaForoView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PreguntaForoView.class);

	private String txtUsuarioQuePregunta, txtTituloPregunta;
	private ComentarioDTO preguntaMostrar, respuestaForoDTO;
	private StreamedContent sc, imagenRespuestaForo, imagenPreguntaDoc, imagenPreguntaModificar;

	private InputText txtTituloRespuestaForo, txtContenidoRespuesta, txtTituloPreguntaModificar, txtContenidoPreguntaModificar;

	private List<ComentarioDTO> lasRespuestasDeLaPregunta;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public PreguntaForoView() {
		super();
	}

	@PostConstruct
	public void PreguntaForoViewPC() {
		try {
			sc = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}

	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public String getTxtUsuarioQuePregunta() {
		if (txtUsuarioQuePregunta == null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			preguntaMostrar = (ComentarioDTO) session.getAttribute("preguntaMostrar");
			txtUsuarioQuePregunta = new String();
			txtUsuarioQuePregunta = preguntaMostrar.getNombreUsuarioQueComenta();
			sc = null;

		}
		return txtUsuarioQuePregunta;
	}

	public String getTxtTituloPregunta() {
		if (txtTituloPregunta == null) {
			txtTituloPregunta = new String();
			txtTituloPregunta = preguntaMostrar.getTituloComentario();
		}
		return txtTituloPregunta;
	}

	public void setTxtUsuarioQuePregunta(String txtUsuarioQuePregunta) {
		this.txtUsuarioQuePregunta = txtUsuarioQuePregunta;
	}

	public void setTxtTituloPregunta(String txtTituloPregunta) {
		this.txtTituloPregunta = txtTituloPregunta;
	}

	public StreamedContent getImage() {
		try {
			if (sc == null) {

				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
				ServletContext sctx = (ServletContext) context.getContext();
				URL resourceUrl = sctx.getResource("/images/img/");
				Parametro parametro = businessDelegatorView.getParametroPorDescripcion("rutaImagenPreguntaForo");
				parametro.setValorParametro(resourceUrl.getPath());
				businessDelegatorView.updateParametro(parametro);
				sc = businessDelegatorView.getStreamedContent(preguntaMostrar);
			}
			return sc;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
			return sc;
		}
	}

	public InputText getTxtTituloRespuestaForo() {
		return txtTituloRespuestaForo;
	}

	public InputText getTxtContenidoRespuesta() {
		return txtContenidoRespuesta;
	}

	public void setTxtTituloRespuestaForo(InputText txtTituloRespuestaForo) {
		this.txtTituloRespuestaForo = txtTituloRespuestaForo;
	}

	public void setTxtContenidoRespuesta(InputText txtContenidoRespuesta) {
		this.txtContenidoRespuesta = txtContenidoRespuesta;
	}

	public List<ComentarioDTO> getLasRespuestasDeLaPregunta() {
		try {
			if (lasRespuestasDeLaPregunta == null) {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(true);
				preguntaMostrar = (ComentarioDTO) session.getAttribute("preguntaMostrar");
				lasRespuestasDeLaPregunta = new ArrayList<>();
				lasRespuestasDeLaPregunta = businessDelegatorView
						.obtenerLasRespuestasAUnaPreguntaDelForo(preguntaMostrar.getComenId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lasRespuestasDeLaPregunta;
	}

	public void setLasRespuestasDeLaPregunta(List<ComentarioDTO> lasRespuestasDeLaPregunta) {
		this.lasRespuestasDeLaPregunta = lasRespuestasDeLaPregunta;
	}

	public StreamedContent getImagenRespuestaForo() {

		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			respuestaForoDTO = (ComentarioDTO) session.getAttribute("respuestaMostrar");

			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			ServletContext sctx = (ServletContext) context.getContext();
			URL resourceUrl = sctx.getResource("/images/img/");
			Parametro parametro = businessDelegatorView.getParametroPorDescripcion("rutaImagenPreguntaForo");
			parametro.setValorParametro(resourceUrl.getPath());
			businessDelegatorView.updateParametro(parametro);
			imagenRespuestaForo = businessDelegatorView.getStreamedContent(respuestaForoDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imagenRespuestaForo;
	}

	public void setImagenRespuestaForo(StreamedContent imagenRespuestaForo) {
		this.imagenRespuestaForo = imagenRespuestaForo;
	}

	public StreamedContent getImagenPreguntaDoc() {

		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			PreguntaDTO preguntaDocMostrar = (PreguntaDTO) session.getAttribute("preguntaDocMostrar");

			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			ServletContext sctx = (ServletContext) context.getContext();
			URL resourceUrl = sctx.getResource("/images/img/");
			Parametro parametro = businessDelegatorView.getParametroPorDescripcion("rutaImagenPreguntaForo");
			parametro.setValorParametro(resourceUrl.getPath());
			businessDelegatorView.updateParametro(parametro);
			imagenPreguntaDoc = businessDelegatorView.getStreamedContent(preguntaDocMostrar);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imagenPreguntaDoc;
	}

	public void setImagenPreguntaDoc(StreamedContent imagenPreguntaDoc) {
		this.imagenPreguntaDoc = imagenPreguntaDoc;
	}

	public ComentarioDTO getPreguntaMostrar() {
		return preguntaMostrar;
	}

	public ComentarioDTO getRespuestaForoDTO() {
		return respuestaForoDTO;
	}

	public StreamedContent getImagenPreguntaModificar() {

		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			ComentarioDTO preguntaModificar = (ComentarioDTO) session.getAttribute("preguntaForoModificar");
			
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			ServletContext sctx = (ServletContext) context.getContext();
			URL resourceUrl = sctx.getResource("/images/img/");
			Parametro parametro = businessDelegatorView.getParametroPorDescripcion("rutaImagenPreguntaForo");
			parametro.setValorParametro(resourceUrl.getPath());
			businessDelegatorView.updateParametro(parametro);
			imagenPreguntaModificar = businessDelegatorView.getStreamedContent(preguntaModificar);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imagenPreguntaModificar;
	}
	
	public InputText getTxtTituloPreguntaModificar() {
		try {
			txtTituloPreguntaModificar = new InputText();
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			ComentarioDTO preguntaModificar = (ComentarioDTO) session.getAttribute("preguntaForoModificar");
			Comentario pregunta = businessDelegatorView.getComentario(preguntaModificar.getComenId());
			txtTituloPreguntaModificar.setValue(pregunta.getTituloComentario());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return txtTituloPreguntaModificar;
	}

	public InputText getTxtContenidoPreguntaModificar() {
		return txtContenidoPreguntaModificar;
	}

	public void setPreguntaMostrar(ComentarioDTO preguntaMostrar) {
		this.preguntaMostrar = preguntaMostrar;
	}

	public void setRespuestaForoDTO(ComentarioDTO respuestaForoDTO) {
		this.respuestaForoDTO = respuestaForoDTO;
	}

	public void setImagenPreguntaModificar(StreamedContent imagenPreguntaModificar) {
		this.imagenPreguntaModificar = imagenPreguntaModificar;
	}

	public void setTxtTituloPreguntaModificar(InputText txtTituloPreguntaModificar) {
		this.txtTituloPreguntaModificar = txtTituloPreguntaModificar;
	}

	public void setTxtContenidoPreguntaModificar(InputText txtContenidoPreguntaModificar) {
		this.txtContenidoPreguntaModificar = txtContenidoPreguntaModificar;
	}

	public void responderPregunta() {
		try {
			Comentario respuesta = new Comentario();
			respuesta.setComenId(null);
			respuesta.setComentario(null);
			respuesta.setTituloComentario(txtTituloRespuestaForo.getValue().toString());
			respuesta.setContenidoComentario(FacesUtils.checkString(txtContenidoRespuesta));

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			com.vortexbird.seguridad.modelo.dto.UsuarioDTO loginUsuario = (com.vortexbird.seguridad.modelo.dto.UsuarioDTO) session
					.getAttribute("usuarioDTO");
			Usuario usuario = businessDelegatorView.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());

			respuesta.setUsuario(usuario);
			preguntaMostrar = (ComentarioDTO) session.getAttribute("preguntaMostrar");
			Comentario pregunta = businessDelegatorView.getComentario(preguntaMostrar.getComenId());

			respuesta.setComentario(pregunta);
			businessDelegatorView.responderPregunta(respuesta);
			txtContenidoRespuesta.setValue("La respuesta ha sido guardada");
			txtTituloRespuestaForo.setValue(null);
			lasRespuestasDeLaPregunta = null;
			getLasRespuestasDeLaPregunta();
		} catch (Exception e) {
			txtContenidoRespuesta.setValue(e.getMessage());
		}
	}
	
	public void ponerEnSessionRespuestaForo(ActionEvent evt){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		ComentarioDTO comentarioDTO = (ComentarioDTO) (evt.getComponent().getAttributes().get("respuestaMostrar"));
		
		session.setAttribute("respuestaMostrar", comentarioDTO);
	}

	public void modificarPregunta(){
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			ComentarioDTO preguntaModificar = (ComentarioDTO) session.getAttribute("preguntaForoModificar");
			
			Comentario pregunta = businessDelegatorView.getComentario(preguntaModificar.getComenId());
			
			pregunta.setTituloComentario(txtTituloPreguntaModificar.getValue().toString());
			pregunta.setContenidoComentario(FacesUtils.checkString(txtContenidoPreguntaModificar));
			
			
			businessDelegatorView.updateComentario(pregunta);
			txtContenidoPreguntaModificar.setValue("La pregunta ha sido modificada");
			
		} catch (Exception e) {
			txtContenidoRespuesta.setValue(e.getMessage());
		}
	}
}
