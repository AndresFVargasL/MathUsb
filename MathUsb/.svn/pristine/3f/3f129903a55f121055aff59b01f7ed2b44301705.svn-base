package co.edu.usbcali.mathusb.presentation.backingBeans.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.seguridad.modelo.dto.GrupoDTO;
import com.vortexbird.seguridad.modelo.dto.OpcionDTO;
import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;
import com.vortexbird.seguridad.rest.LoginRestServiceClient;

import co.edu.usbcali.mathusb.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.mathusb.utilities.Constantes;
import co.edu.usbcali.mathusb.utilities.FacesUtils;
import co.edu.usbcali.mathusb.utilities.SessionVariables;



/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class MenuView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MenuView.class);
	private String nombrePersona;
	private MenuModel model;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	private List<GrupoDTO> listaOpcionesHijas = null;
	private List<GrupoDTO> listaOpcionesPadres = null;
	private List<GrupoDTO> listaOpciones = null;

	public MenuView() {
		super();

	}

	@PostConstruct
	public void infoPersonaTopBar(){
		
		UsuarioDTO usuarioDTO = (UsuarioDTO) FacesUtils.getManagedBeanFromSession(SessionVariables.USUARIO_DTO);
		
		Integer empresa = (Integer) FacesUtils.getManagedBeanFromSession(SessionVariables.STRING_EMPRESA_SESSION);
		
//		//Se asigna el nombre de la persona y la empresa para la que actualmente esta registrada en el TOP BAR
		setNombrePersona(usuarioDTO.getUsu_nombres());
//		//Se traen las opciones por el rol del usuario.
		listaOpciones=null;
		listaOpciones = new ArrayList<GrupoDTO>();
		try {
			listaOpciones = LoginRestServiceClient.getOpcionesPorRol(usuarioDTO.getUsu_login(), empresa.toString().trim(),businessDelegatorView.getParametroPorDescripcion(Constantes.URLREST).getValorParametro());
		} catch (Exception e) {
			e.printStackTrace();
		}
		listaOpcionesPadres = null;
		listaOpcionesHijas = null;
		listaOpcionesPadres = new ArrayList<GrupoDTO>();
		listaOpcionesHijas = new ArrayList<GrupoDTO>();
		for (GrupoDTO grupoDTO : listaOpciones) {
			if(grupoDTO.getGru_codigo_padre() != null 
			&& grupoDTO.getGru_codigo_padre().trim().equals("") == false){
				listaOpcionesHijas.add(grupoDTO);	
			}else{
				listaOpcionesPadres.add(grupoDTO);
			}
		}
		
		
		//Se declara el modelo que contendra todas las opciones
		model = new DefaultMenuModel();

		DefaultMenuItem dashboard = new DefaultMenuItem();
		dashboard.setId("sm_dashboard");
		dashboard.setValue("Inicio");
		dashboard.setIcon("ui-icon-home");
		dashboard.setOutcome("dashboard");
		dashboard.setContainerStyleClass("layout-menubar-active");
		model.addElement(dashboard);
		
		//se itera cada grupo de la lista de opciones
		for (GrupoDTO grupoDTO : listaOpcionesPadres) {
			DefaultSubMenu subMenu = new DefaultSubMenu();
			subMenu.setId(grupoDTO.getGru_codigo());
			subMenu.setLabel(grupoDTO.getGru_descripcion());
			subMenu.setIcon(grupoDTO.getGru_icono());
			
			for (GrupoDTO grupoDTOHija : listaOpcionesHijas) {
				if(grupoDTO.getGru_codigo().trim().equals(grupoDTOHija.getGru_codigo_padre().trim())){
					
					DefaultSubMenu subMenuHija = new DefaultSubMenu();
					subMenuHija.setId(grupoDTOHija.getGru_codigo());
					subMenuHija.setLabel(grupoDTOHija.getGru_descripcion());
					subMenuHija.setIcon(grupoDTOHija.getGru_icono());
					//se itera cada una de las opciones por grupo
					if(grupoDTOHija.getOpciones().size() > 0){
						for (OpcionDTO opcionHija : grupoDTOHija.getOpciones()) {
							//para cada opcion, se crea un item y se agrega al submenu
							DefaultMenuItem itemHija = new DefaultMenuItem();
							itemHija.setId(opcionHija.getOpc_codigo());
							itemHija.setValue(opcionHija.getOpc_nombre());
							itemHija.setUrl(opcionHija.getOpc_llave_acceso());
							subMenuHija.addElement(itemHija);

						}
					}
					subMenu.addElement(subMenuHija);
					
				}
			}
			//se itera cada una de las opciones por grupo
			if(grupoDTO.getOpciones().size() > 0){
				for (OpcionDTO opcion : grupoDTO.getOpciones()) {
					//para cada opcion, se crea un item y se agrega al submenu
					DefaultMenuItem item = new DefaultMenuItem();
					item.setId(opcion.getOpc_codigo());
					item.setValue(opcion.getOpc_nombre());
					item.setUrl(opcion.getOpc_llave_acceso());
					subMenu.addElement(item);

				}
			}
			model.addElement(subMenu);
		}
		
		//Para cerrar sesion
		DefaultMenuItem salir = new DefaultMenuItem();
		salir.setId("salir");
		salir.setValue("Cerrar Sesión");
		salir.setIcon("ui-icon-close");
		salir.setCommand("#{menuView.action_exit()}");
		salir.setRendered(true);
		model.addElement(salir);
		
	}
	
	public void action_exit(){
		try {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.redirect(context.getRequestContextPath()
					+ "/" + SessionVariables.urlSalidaSpringSecurity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public List<GrupoDTO> getListaOpcionesHijas() {
		return listaOpcionesHijas;
	}

	public void setListaOpcionesHijas(List<GrupoDTO> listaOpcionesHijas) {
		this.listaOpcionesHijas = listaOpcionesHijas;
	}

	public List<GrupoDTO> getListaOpciones() {
		return listaOpciones;
	}

	public void setListaOpciones(List<GrupoDTO> listaOpciones) {
		this.listaOpciones = listaOpciones;
	}

	public List<GrupoDTO> getListaOpcionesPadres() {
		return listaOpcionesPadres;
	}

	public void setListaOpcionesPadres(List<GrupoDTO> listaOpcionesPadres) {
		this.listaOpcionesPadres = listaOpcionesPadres;
	}



}
