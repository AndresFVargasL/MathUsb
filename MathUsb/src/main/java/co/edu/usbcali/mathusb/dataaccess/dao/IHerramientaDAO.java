package co.edu.usbcali.mathusb.dataaccess.dao;

import java.util.List;

import co.edu.usbcali.mathusb.dataaccess.api.Dao;
import co.edu.usbcali.mathusb.modelo.Herramienta;


/**
* Interface for   HerramientaDAO.
*
*/
public interface IHerramientaDAO extends Dao<Herramienta, Long> {
	public List<Herramienta> herramientasOrdenadasPorFecha();
	public List<Herramienta> herramientasDelUsuarioOrdenadasPorFecha(String usuLogin);
	
}
