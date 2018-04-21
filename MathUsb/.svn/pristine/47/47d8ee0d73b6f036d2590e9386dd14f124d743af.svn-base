package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.dataaccess.dao.*;
import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.UsuarioDTO;
import co.edu.usbcali.mathusb.utilities.Constantes;
import co.edu.usbcali.mathusb.utilities.FacesUtils;
import co.edu.usbcali.mathusb.utilities.SessionVariables;
import co.edu.usbcali.mathusb.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.seguridad.modelo.dto.ResultadoCrearUsuarioDTO;
import com.vortexbird.seguridad.rest.LoginRestServiceClient;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("UsuarioLogic")
public class UsuarioLogic implements IUsuarioLogic {
	private static final Logger log = LoggerFactory.getLogger(UsuarioLogic.class);

	/**
	 * DAO injected by Spring that manages Usuario entities
	 *
	 */
	@Autowired
	private IUsuarioDAO usuarioDAO;

	/**
	 * DAO injected by Spring that manages Comentario entities
	 *
	 */
	@Autowired
	private IComentarioDAO comentarioDAO;

	/**
	 * DAO injected by Spring that manages GrupoUsuario entities
	 *
	 */
	@Autowired
	private IGrupoUsuarioDAO grupoUsuarioDAO;

	/**
	 * DAO injected by Spring that manages Herramienta entities
	 *
	 */
	@Autowired
	private IHerramientaDAO herramientaDAO;

	/**
	 * DAO injected by Spring that manages UsuarioEvaluacion entities
	 *
	 */
	@Autowired
	private IUsuarioEvaluacionDAO usuarioEvaluacionDAO;

	/**
	 * Logic injected by Spring that manages TipoUsuario entities
	 *
	 */
	@Autowired
	ITipoUsuarioLogic logicTipoUsuario1;
	
	@Autowired
	private IParametroLogic parametroLogic;

	@Transactional(readOnly = true)
	public List<Usuario> getUsuario() throws Exception {
		log.debug("finding all Usuario instances");

		List<Usuario> list = new ArrayList<Usuario>();

		try {
			list = usuarioDAO.findAll();
		} catch (Exception e) {
			log.error("finding all Usuario failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "Usuario");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUsuario(Usuario entity) throws Exception {
		log.info("# Creando un usuario. Fecha: " + new Date() + " #");

		try {
			if (entity.getTipoUsuario() == null) {
				throw new ZMessManager().new ForeignException("tipoUsuario");
			}

			if (entity.getApellidoUsuario() == null) {
				throw new ZMessManager().new EmptyFieldException("apellidoUsuario");
			}

			if ((entity.getApellidoUsuario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getApellidoUsuario(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("apellidoUsuario");
			}

			if (entity.getCodigoUsuario() == null) {
				throw new ZMessManager().new EmptyFieldException("codigoUsuario");
			}

			if ((entity.getCodigoUsuario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getCodigoUsuario(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("codigoUsuario");
			}

			if (entity.getEmailUsuario() == null) {
				throw new ZMessManager().new EmptyFieldException("emailUsuario");
			}

			if ((entity.getEmailUsuario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getEmailUsuario(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("emailUsuario");
			}

			if (entity.getEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException("estadoRegistro");
			}

			if ((entity.getEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("estadoRegistro");
			}

			if (entity.getNombreUsuario() == null) {
				throw new ZMessManager().new EmptyFieldException("nombreUsuario");
			}

			if ((entity.getNombreUsuario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getNombreUsuario(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("nombreUsuario");
			}

			if (entity.getPasswordUsuario() == null) {
				throw new ZMessManager().new EmptyFieldException("Clave");
			}

			if ((entity.getPasswordUsuario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getPasswordUsuario(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("Clave");
			}

			if (entity.getTipoUsuario().getTiusId() == null) {
				throw new ZMessManager().new EmptyFieldException("Tipo de Usuario");
			}

			Usuario usuario = usuarioDAO.findEntityByProperty("emailUsuario", entity.getEmailUsuario().toLowerCase());
			if (usuario != null) {
				throw new RuntimeException(usuario.getEmailUsuario() + " ya esta registrado");
			}
			
			String emailMinusculas = entity.getEmailUsuario().toLowerCase();
			entity.setEmailUsuario(emailMinusculas);

			usuario = usuarioDAO.findEntityByProperty("codigoUsuario", entity.getCodigoUsuario());
			if (usuario != null) {
				throw new RuntimeException("El usuario con código: " + usuario.getCodigoUsuario() + " ya esta registrado");
			}
			usuarioDAO.save(entity);
			
			//TODO: Se añade registro al sistema de seguridad.
			
			ResultadoCrearUsuarioDTO resultadoCrearUsuarioDTO = LoginRestServiceClient.crearUsuario(
            		entity.getApellidoUsuario(),
            		Constantes.CODIGO_INTERNO,
            		entity.getPasswordUsuario(),
            		entity.getEmailUsuario(), 
            		"1",
            		0L,
            		1L,
            		entity.getEmailUsuario(),
            		entity.getNombreUsuario(),
            		0L,
            		getRolCodigoByNombreCortoTipoUsuario(entity.getTipoUsuario().getNombreCorto()),
            		"1",
            		"2",
            		parametroLogic.getParametroPorDescripcion(Constantes.URLREST).getValorParametro());
            
            if(resultadoCrearUsuarioDTO.getExito()==false){
            	throw new RuntimeException(resultadoCrearUsuarioDTO.getMensaje());
            }

			log.info("# La creacion del usuario ha finalizado con exito a las " + new Date() + " #");
		} catch (RuntimeException e) {
			log.error("# Ha ocurrido un error creando un usuario #", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteUsuario(Usuario entity) throws Exception {
		log.debug("deleting Usuario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Usuario");
		}

		if (entity.getUsuaId() == null) {
			throw new ZMessManager().new EmptyFieldException("usuaId");
		}

		List<Comentario> comentarios = null;
		List<GrupoUsuario> grupoUsuarios = null;
		List<Herramienta> herramientas = null;
		List<UsuarioEvaluacion> usuarioEvaluacions = null;

		try {
			comentarios = comentarioDAO.findByProperty("usuario.usuaId", entity.getUsuaId());

			if (Utilities.validationsList(comentarios) == true) {
				throw new ZMessManager().new DeletingException("comentarios");
			}

			grupoUsuarios = grupoUsuarioDAO.findByProperty("usuario.usuaId", entity.getUsuaId());

			if (Utilities.validationsList(grupoUsuarios) == true) {
				throw new ZMessManager().new DeletingException("grupoUsuarios");
			}

			herramientas = herramientaDAO.findByProperty("usuario.usuaId", entity.getUsuaId());

			if (Utilities.validationsList(herramientas) == true) {
				throw new ZMessManager().new DeletingException("herramientas");
			}

			usuarioEvaluacions = usuarioEvaluacionDAO.findByProperty("usuario.usuaId", entity.getUsuaId());

			if (Utilities.validationsList(usuarioEvaluacions) == true) {
				throw new ZMessManager().new DeletingException("usuarioEvaluacions");
			}

			usuarioDAO.delete(entity);

			log.debug("delete Usuario successful");
		} catch (Exception e) {
			log.error("delete Usuario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateUsuario(Usuario entity) throws Exception {
		log.debug("updating Usuario instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Usuario");
			}

			if (entity.getTipoUsuario() == null) {
				throw new ZMessManager().new ForeignException("tipoUsuario");
			}

			if (entity.getApellidoUsuario() == null) {
				throw new ZMessManager().new EmptyFieldException("apellidoUsuario");
			}

			if ((entity.getApellidoUsuario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getApellidoUsuario(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("apellidoUsuario");
			}

			if (entity.getCodigoUsuario() == null) {
				throw new ZMessManager().new EmptyFieldException("codigoUsuario");
			}

			if ((entity.getCodigoUsuario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getCodigoUsuario(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("codigoUsuario");
			}

			if (entity.getEmailUsuario() == null) {
				throw new ZMessManager().new EmptyFieldException("emailUsuario");
			}

			if ((entity.getEmailUsuario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getEmailUsuario(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("emailUsuario");
			}

			if (entity.getEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException("estadoRegistro");
			}

			if ((entity.getEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("estadoRegistro");
			}

			if (entity.getNombreUsuario() == null) {
				throw new ZMessManager().new EmptyFieldException("nombreUsuario");
			}

			if ((entity.getNombreUsuario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getNombreUsuario(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("nombreUsuario");
			}

			if (entity.getPasswordUsuario() == null) {
				throw new ZMessManager().new EmptyFieldException("passwordUsuario");
			}

			if ((entity.getPasswordUsuario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getPasswordUsuario(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("passwordUsuario");
			}

			if (entity.getTipoUsuario().getTiusId() == null) {
				throw new ZMessManager().new EmptyFieldException("tiusId_TipoUsuario");
			}

			usuarioDAO.update(entity);

			log.debug("update Usuario successful");
		} catch (Exception e) {
			log.error("update Usuario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<UsuarioDTO> getDataUsuario() throws Exception {
		try {
			List<Usuario> usuario = usuarioDAO.findAll();

			List<UsuarioDTO> usuarioDTO = new ArrayList<UsuarioDTO>();

			for (Usuario usuarioTmp : usuario) {
				UsuarioDTO usuarioDTO2 = new UsuarioDTO();

				usuarioDTO2.setUsuaId(usuarioTmp.getUsuaId());
				usuarioDTO2.setApellidoUsuario(
						(usuarioTmp.getApellidoUsuario() != null) ? usuarioTmp.getApellidoUsuario() : null);
				usuarioDTO2.setCodigoUsuario(
						(usuarioTmp.getCodigoUsuario() != null) ? usuarioTmp.getCodigoUsuario() : null);
				usuarioDTO2
						.setEmailUsuario((usuarioTmp.getEmailUsuario() != null) ? usuarioTmp.getEmailUsuario() : null);
				usuarioDTO2.setEstadoRegistro(
						(usuarioTmp.getEstadoRegistro() != null) ? usuarioTmp.getEstadoRegistro() : null);
				usuarioDTO2.setNombreUsuario(
						(usuarioTmp.getNombreUsuario() != null) ? usuarioTmp.getNombreUsuario() : null);
				usuarioDTO2.setPasswordUsuario(
						(usuarioTmp.getPasswordUsuario() != null) ? usuarioTmp.getPasswordUsuario() : null);
				usuarioDTO2.setTiusId_TipoUsuario((usuarioTmp.getTipoUsuario().getTiusId() != null)
						? usuarioTmp.getTipoUsuario().getTiusId() : null);
				usuarioDTO.add(usuarioDTO2);
			}

			return usuarioDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Usuario getUsuario(Long usuaId) throws Exception {
		log.debug("getting Usuario instance");

		Usuario entity = null;

		try {
			entity = usuarioDAO.findById(usuaId);
		} catch (Exception e) {
			log.error("get Usuario failed", e);
			throw new ZMessManager().new FindingException("Usuario");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<Usuario> findPageUsuario(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		List<Usuario> entity = null;

		try {
			entity = usuarioDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Usuario Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberUsuario() throws Exception {
		Long entity = null;

		try {
			entity = usuarioDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Usuario Count");
		} finally {
		}

		return entity;
	}

	/**
	 *
	 * @param varibles
	 *            este arreglo debera tener:
	 *
	 *            [0] = String variable = (String) varibles[i]; representa como
	 *            se llama la variable en el pojo
	 *
	 *            [1] = Boolean booVariable = (Boolean) varibles[i + 1];
	 *            representa si el valor necesita o no ''(comillas simples)usado
	 *            para campos de tipo string
	 *
	 *            [2] = Object value = varibles[i + 2]; representa el valor que
	 *            se va a buscar en la BD
	 *
	 *            [3] = String comparator = (String) varibles[i + 3]; representa
	 *            que tipo de busqueda voy a hacer.., ejemplo: where
	 *            nombre=william o where nombre<>william, en este campo iria el
	 *            tipo de comparador que quiero si es = o <>
	 *
	 *            Se itera de 4 en 4..., entonces 4 registros del arreglo
	 *            representan 1 busqueda en un campo, si se ponen mas pues el
	 *            continuara buscando en lo que se le ingresen en los otros 4
	 *
	 *
	 * @param variablesBetween
	 *
	 *            la diferencia son estas dos posiciones
	 *
	 *            [0] = String variable = (String) varibles[j]; la variable ne
	 *            la BD que va a ser buscada en un rango
	 *
	 *            [1] = Object value = varibles[j + 1]; valor 1 para buscar en
	 *            un rango
	 *
	 *            [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en
	 *            un rango ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria
	 *            value2
	 *
	 *            [3] = String comparator1 = (String) varibles[j + 3];
	 *            comparador 1 ejemplo: a comparator1 1 and a < 5
	 *
	 *            [4] = String comparator2 = (String) varibles[j + 4];
	 *            comparador 2 ejemplo: a comparador1>1 and a comparador2<5 (el
	 *            original: a > 1 and a < 5) *
	 * @param variablesBetweenDates(en
	 *            este caso solo para mysql) [0] = String variable = (String)
	 *            varibles[k]; el nombre de la variable que hace referencia a
	 *            una fecha
	 *
	 *            [1] = Object object1 = varibles[k + 2]; fecha 1 a
	 *            comparar(deben ser dates)
	 *
	 *            [2] = Object object2 = varibles[k + 3]; fecha 2 a
	 *            comparar(deben ser dates)
	 *
	 *            esto hace un between entre las dos fechas.
	 *
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<Usuario> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<Usuario> list = new ArrayList<Usuario>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null) && (variables[i + 2] != null)
						&& (variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " \'" + value + "\' )")
								: (tempWhere + " AND (model." + variable + " " + comparator + " \'" + value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " " + value + " )")
								: (tempWhere + " AND (model." + variable + " " + comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null) && (variablesBetween[j + 1] != null)
						&& (variablesBetween[j + 2] != null) && (variablesBetween[j + 3] != null)
						&& (variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0)
							? ("(" + value + " " + comparator1 + " " + variable + " and " + variable + " " + comparator2
									+ " " + value2 + " )")
							: (tempWhere + " AND (" + value + " " + comparator1 + " " + variable + " and " + variable
									+ " " + comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null) && (variablesBetweenDates[k + 1] != null)
						&& (variablesBetweenDates[k + 2] != null)) {
					String variable = (String) variablesBetweenDates[k];
					Object object1 = variablesBetweenDates[k + 1];
					Object object2 = variablesBetweenDates[k + 2];
					String value = null;
					String value2 = null;

					try {
						Date date1 = (Date) object1;
						Date date2 = (Date) object2;
						value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
						value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
					} catch (Exception e) {
						list = null;
						throw e;
					}

					tempWhere = (tempWhere.length() == 0)
							? ("(model." + variable + " between \'" + value + "\' and \'" + value2 + "\')")
							: (tempWhere + " AND (model." + variable + " between \'" + value + "\' and \'" + value2
									+ "\')");
				}

				k = k + 2;
			}
		}

		if (tempWhere.length() == 0) {
			where = null;
		} else {
			where = "(" + tempWhere + ")";
		}

		try {
			list = usuarioDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario obtenerUsuarioPorEmail(String mail) throws Exception {
		if (mail.trim().equals("") == true) {
			throw new Exception("No ha llegado el mail del usuario");
		}
		return usuarioDAO.findEntityByProperty("emailUsuario", mail.toLowerCase());
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioDTO> obtenerListaDeEstudiantesDadoIdGrupo(Long idGrupo) throws Exception {
		if (idGrupo == null) {
			throw new Exception("No ha seleccionado un grupo para ver la lista");
		}
		List<Usuario> listaEstudiantes = usuarioDAO.obtenerListaDeEstudiantesDadoIdGrupo(idGrupo);

		if (listaEstudiantes == null) {
			return null;
		}
		List<UsuarioDTO> listaEstudiantesDTO = new ArrayList<>();
		for (Usuario usuario : listaEstudiantes) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setApellidoUsuario(usuario.getApellidoUsuario());
			usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
			usuarioDTO.setCodigoUsuario(usuario.getCodigoUsuario());
			usuarioDTO.setEmailUsuario(usuario.getEmailUsuario());
			usuarioDTO.setPasswordUsuario(null);
			listaEstudiantesDTO.add(usuarioDTO);
		}
		return listaEstudiantesDTO;
	}
	
	public String getRolCodigoByNombreCortoTipoUsuario(String nombreCorto) throws RuntimeException {
		if(nombreCorto.trim().toUpperCase().equals(Constantes.TIPO_USUARIO_DOCENTE)){
			return "3";
		}else if(nombreCorto.trim().toUpperCase().equals(Constantes.TIPO_USUARIO_ESTUDIANTE)){
			return "4";
		}else if(nombreCorto.trim().toUpperCase().equals(Constantes.TIPO_USUARIO_SECRETARIO)){
			return "2";
		}else{
			new RuntimeException("Registro No Autorizado. Comuníquese con el area técnica.");
		}
		return "";
	}
}
