package co.edu.usbcali.mathusb.grupo.test;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.mathusb.dataaccess.dao.IGrupoDAO;
import co.edu.usbcali.mathusb.exceptions.ZMessManager;
import co.edu.usbcali.mathusb.modelo.Grupo;
import co.edu.usbcali.mathusb.modelo.control.IAsignaturaLogic;
import co.edu.usbcali.mathusb.modelo.control.IGrupoLogic;
import co.edu.usbcali.mathusb.modelo.dto.GrupoDTO;
import co.edu.usbcali.mathusb.utilities.FacesUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional

public class PruebaGrupo {
	@Autowired
	IGrupoLogic grupoLogic;

	@Autowired
	IAsignaturaLogic asignaturaLogic;
	
	@Autowired 
	IGrupoDAO grupoDAO;

	private static Logger logger = LoggerFactory.getLogger(PruebaGrupo.class);

	public PruebaGrupo() {
	}

	
	public void consultarLosGrupos() throws Exception {
		List<Grupo> losGrupos = grupoLogic.traerGruposDeDocentes();

		for (Grupo grupo : losGrupos) {
			logger.info("#### grup_id: " + grupo.getGrupId() + ", nombre: " + grupo.getDescripcionGrupo());
		}
	}

	
	public void consultarGruposDTO() throws Exception {
		List<GrupoDTO> losGrupos = grupoLogic.traerGruposConDocentesYNombreAsignatura();
		logger.info("#### Consulta grupos DTO ####");
		for (GrupoDTO grupoDTO : losGrupos) {
			logger.info("grupId: " + grupoDTO.getGrupId() + ", descr: " + grupoDTO.getDescripcionGrupo() + ", nomAsig: "
					+ grupoDTO.getNombreAsignatura() + ", nomDoc: " + grupoDTO.getNombreCompletoDocente());
		}
	}
	
	@Test
	public void consultarGrupoPorDescripcionYIdUsuario(){
		Long id = new Long (2);
		String descripcion = "Grupo 1 Diferencial";
		Grupo grupo = grupoDAO.obtenerGrupoDadoIdUsuarioYDescripcion(id, descripcion);
		
		if(grupo!=null){
			logger.info("Ha llegado el grupo: "+grupo.getGrupId());
		}else{
			logger.info("No ha llegado el grupo");
		}
	}

}
