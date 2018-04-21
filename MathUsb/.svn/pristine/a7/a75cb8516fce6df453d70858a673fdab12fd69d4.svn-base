package co.edu.usbcali.tipoherramienta.test;

import java.util.Date;
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

import co.edu.usbcali.mathusb.modelo.TipoHerramienta;
import co.edu.usbcali.mathusb.modelo.control.ITipoHerramientaLogic;
import co.edu.usbcali.mathusb.presentation.businessDelegate.IBusinessDelegatorView;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional

public class PruebaTipoHerramienta {
	@Autowired
	IBusinessDelegatorView businessDelegatorView;

	private static Logger logger = LoggerFactory.getLogger(PruebaTipoHerramienta.class);

	public PruebaTipoHerramienta() {
	}

	@Test
	public void consultarTodas() throws Exception {
		List<TipoHerramienta> listaTodas = businessDelegatorView.getTipoHerramienta();
		logger.info("Inicio test consultar todas, Fecha/Hora "+new Date());
		for (TipoHerramienta tipoHerramienta : listaTodas) {
			logger.info("Descripcion: "+tipoHerramienta.getDescripcionHerramienta()+", Nombre corto: "+tipoHerramienta.getNombreCorto());
		}
		logger.info("Finaliza test consultar todas, Fecha/Hora "+new Date());
	}
	
	@Test
	public void consultarTodasAlfabeticamente() throws Exception {
		List<TipoHerramienta> listaTodasOrderBy = businessDelegatorView.obtenerTiposHerramientasOrdenadasAlfabeticamente();
		logger.info("Inicio test consultar todas alfabéticamente, Fecha/Hora "+new Date());
		for (TipoHerramienta tipoHerramienta : listaTodasOrderBy) {
			logger.info("Descripcion: "+tipoHerramienta.getDescripcionHerramienta()+", Nombre corto: "+tipoHerramienta.getNombreCorto());
		}
		logger.info("Finaliza test consultar todas alfabéticamente, Fecha/Hora "+new Date());
	}

}
