package co.edu.usbcali.mathusb.dataaccess.dao;

import java.util.List;

import co.edu.usbcali.mathusb.dataaccess.api.Dao;
import co.edu.usbcali.mathusb.modelo.Tema;


/**
* Interface for   TemaDAO.
*
*/
public interface ITemaDAO extends Dao<Tema, Long> {
	
	public List<Tema> obtenerTemaDadoPalabraClaveConTilde(String palabraClave);
	
	public List<Tema> obtenerTemaDadoPalabraClaveSinTilde(String palabraClave);
}
