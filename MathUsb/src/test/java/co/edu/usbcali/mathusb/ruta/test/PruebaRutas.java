package co.edu.usbcali.mathusb.ruta.test;


import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional

public class PruebaRutas {
	
	private static Logger logger = LoggerFactory.getLogger(PruebaRutas.class);

	public PruebaRutas() {
	}

	@Test
	public void consultarLaRuta() throws Exception {
		File file = new File(".");
		logger.info(file.getAbsolutePath ());
		file.delete();
		}

	

}
