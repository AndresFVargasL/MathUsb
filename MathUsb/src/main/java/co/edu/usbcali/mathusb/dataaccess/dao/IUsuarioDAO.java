package co.edu.usbcali.mathusb.dataaccess.dao;

import java.util.List;

import co.edu.usbcali.mathusb.dataaccess.api.Dao;
import co.edu.usbcali.mathusb.modelo.Usuario;


/**
* Interface for   UsuarioDAO.
*
*/
public interface IUsuarioDAO extends Dao<Usuario, Long> {
	public List<Usuario> obtenerListaDeEstudiantesDadoIdGrupo(Long idGrupo);
}
