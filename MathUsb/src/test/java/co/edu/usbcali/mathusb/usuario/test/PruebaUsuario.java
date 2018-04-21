package co.edu.usbcali.mathusb.usuario.test;

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

import co.edu.usbcali.mathusb.dataaccess.dao.IUsuarioDAO;
import co.edu.usbcali.mathusb.modelo.Usuario;
import co.edu.usbcali.mathusb.modelo.control.IUsuarioLogic;
import co.edu.usbcali.mathusb.modelo.dto.UsuarioDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional

public class PruebaUsuario {
	@Autowired
	IUsuarioDAO usuarioDAO;
	
	@Autowired
	IUsuarioLogic usuarioLogic;

	private static Logger logger = LoggerFactory.getLogger(PruebaUsuario.class);

	public PruebaUsuario() {
	}

	
	public void listaEstudiantesDadoIdGrupo() {
		List<Usuario> losEstudiantes = usuarioDAO.obtenerListaDeEstudiantesDadoIdGrupo(2L);
		for (Usuario usuario : losEstudiantes) {
			logger.info(usuario.getApellidoUsuario() + ", " + usuario.getNombreUsuario());
		}
	}
	
	@Test
	public void listaEstudiantesDadoIdGrupoLogica() throws Exception {
		List<UsuarioDTO> losEstudiantesDTO = usuarioLogic.obtenerListaDeEstudiantesDadoIdGrupo(2L);
		for (UsuarioDTO usuarioDTO : losEstudiantesDTO) {
			logger.info(usuarioDTO.getApellidoUsuario() + ", " + usuarioDTO.getNombreUsuario()+", Psw: "+usuarioDTO.getPasswordUsuario());
		}
	}

}
