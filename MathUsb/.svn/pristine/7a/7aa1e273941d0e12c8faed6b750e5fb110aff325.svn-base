package co.edu.usbcali.mathusb.usuario.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.mathusb.modelo.TipoUsuario;
import co.edu.usbcali.mathusb.modelo.control.ITipoUsuarioLogic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional

public class PruebaTipoUsuario {
	@Autowired
	ITipoUsuarioLogic tipoUsuarioLogic;

	private static Logger logger = LoggerFactory.getLogger(PruebaTipoUsuario.class);

	public PruebaTipoUsuario() {
	}

	@Test
	public void consultarPorNombreCorto() throws Exception {
		TipoUsuario tipoUsuario = tipoUsuarioLogic.obtenerTipoUsuarioPorNombreCorto("DOC");

		logger.info("El tipo de usuario con nombre corto: DOC es: " + tipoUsuario.getDescripcionTipoDeUsuario());
	}

}
