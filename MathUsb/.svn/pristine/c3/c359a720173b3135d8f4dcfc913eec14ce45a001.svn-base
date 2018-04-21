package co.edu.usbcali.mathusb.comentario.test;

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

import co.edu.usbcali.mathusb.dataaccess.dao.IComentarioDAO;
import co.edu.usbcali.mathusb.modelo.Comentario;
import co.edu.usbcali.mathusb.modelo.control.IComentarioLogic;
import co.edu.usbcali.mathusb.modelo.dto.ComentarioDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional

public class PruebaComentario {
	@Autowired
	IComentarioDAO comentarioDAO;
	
	@Autowired
	IComentarioLogic comentarioLogic;
	
	
	private static Logger logger = LoggerFactory.getLogger(PruebaComentario.class);

	public PruebaComentario() {
	}

	
	
	public void consultarPreguntasDesdeComentarioDAO() throws Exception {
		logger.info("Preguntas del foro del recienta al antiguo");
		List<Comentario> comentarios = comentarioDAO.listaPreguntasForoOrdenadosDelMasRecienteAlMasAntiguo();
		for (Comentario comentario : comentarios) {
			logger.info("Comen_id: "+comentario.getComenId()+", titulo: "+comentario.getTituloComentario());
		}
		logger.info("Fin lista de preguntas del foro");
	}
	
	@Test
	public void consultarPreguntasDesdeComentarioLogic() throws Exception {
		
		List<ComentarioDTO> comentariosDTO = comentarioLogic.listaPreguntasForoDadoIdUsuario(3L);
		for (ComentarioDTO comentario : comentariosDTO) {
			logger.info("Comen_id: "+comentario.getComenId()+", titulo: "+comentario.getTituloComentario());
		}
		logger.info("Fin lista de preguntas del foro");
	}
}
