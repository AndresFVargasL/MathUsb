package co.edu.usbcali.mathusb.dataaccess.dao;

import java.util.List;

import co.edu.usbcali.mathusb.dataaccess.api.Dao;
import co.edu.usbcali.mathusb.modelo.Grupo;


/**
* Interface for   GrupoDAO.
*
*/
public interface IGrupoDAO extends Dao<Grupo, Long> {
	public List<Grupo> traerGruposDeDocentes();
	
	public List<Grupo> listaDeGruposDelUsuario(Long idUsuario);
	
	public Grupo obtenerGrupoDadoIdUsuarioYDescripcion(Long idUsuario, String descripcion);
}
