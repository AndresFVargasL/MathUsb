package co.edu.usbcali.mathusb.dataaccess.dao;

import co.edu.usbcali.mathusb.dataaccess.api.Dao;
import co.edu.usbcali.mathusb.modelo.GrupoUsuario;


/**
* Interface for   GrupoUsuarioDAO.
*
*/
public interface IGrupoUsuarioDAO extends Dao<GrupoUsuario, Long> {
	public GrupoUsuario obtenerGrupoUsuarioPorIdDeGrupoYUsuarioDocente(Long grupId);
	
	public boolean estudianteEstaMatriculadoEnElCurso(Long grupId, Long usuaId);
	
	public GrupoUsuario obtenerGrupoUsuarioPorIdGrupoYIdEstudiante(Long grupId, Long usuaId);
	
}
