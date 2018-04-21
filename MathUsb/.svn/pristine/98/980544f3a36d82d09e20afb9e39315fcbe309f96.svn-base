package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.dataaccess.dao.*;
import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.GrupoDTO;
import co.edu.usbcali.mathusb.modelo.dto.GrupoUsuarioDTO;
import co.edu.usbcali.mathusb.modelo.dto.UsuarioDTO;
import co.edu.usbcali.mathusb.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("GrupoUsuarioLogic")
public class GrupoUsuarioLogic implements IGrupoUsuarioLogic {
	private static final Logger log = LoggerFactory.getLogger(GrupoUsuarioLogic.class);

	/**
	 * DAO injected by Spring that manages GrupoUsuario entities
	 *
	 */
	@Autowired
	private IGrupoUsuarioDAO grupoUsuarioDAO;

	/**
	 * Logic injected by Spring that manages Grupo entities
	 *
	 */
	@Autowired
	IGrupoLogic logicGrupo1;

	/**
	 * Logic injected by Spring that manages Usuario entities
	 *
	 */
	@Autowired
	IUsuarioLogic logicUsuario2;

	@Transactional(readOnly = true)
	public List<GrupoUsuario> getGrupoUsuario() throws Exception {
		log.debug("finding all GrupoUsuario instances");

		List<GrupoUsuario> list = new ArrayList<GrupoUsuario>();

		try {
			list = grupoUsuarioDAO.findAll();
		} catch (Exception e) {
			log.error("finding all GrupoUsuario failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "GrupoUsuario");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveGrupoUsuario(GrupoUsuario entity) throws Exception {
		log.info("# Creando un GrupoUsuario. Fecha: " + new Date() + " #");

		try {
			if (entity.getGrupo() == null) {
				throw new ZMessManager().new ForeignException("grupo");
			}

			if (entity.getUsuario() == null) {
				throw new ZMessManager().new ForeignException("usuario");
			}

			if (entity.getGrupo().getGrupId() == null) {
				throw new ZMessManager().new EmptyFieldException("grupId_Grupo");
			}

			if (entity.getUsuario().getUsuaId() == null) {
				throw new ZMessManager().new EmptyFieldException("usuaId_Usuario");
			}

			grupoUsuarioDAO.save(entity);

			log.info("# La creacion del GrupoUsuario ha finalizado con exito a las " + new Date() + " #");
		} catch (Exception e) {
			log.error("# Ha ocurrido un error creando un Grupo Usuario #", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteGrupoUsuario(GrupoUsuario entity) throws Exception {
		log.debug("deleting GrupoUsuario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("GrupoUsuario");
		}

		if (entity.getGrusId() == null) {
			throw new ZMessManager().new EmptyFieldException("grusId");
		}

		try {
			grupoUsuarioDAO.delete(entity);

			log.debug("delete GrupoUsuario successful");
		} catch (Exception e) {
			log.error("delete GrupoUsuario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateGrupoUsuario(GrupoUsuario entity) throws Exception {
		log.debug("updating GrupoUsuario instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("GrupoUsuario");
			}

			if (entity.getGrupo() == null) {
				throw new ZMessManager().new ForeignException("grupo");
			}

			if (entity.getUsuario() == null) {
				throw new ZMessManager().new ForeignException("usuario");
			}

			if (entity.getGrusId() == null) {
				throw new ZMessManager().new EmptyFieldException("grusId");
			}

			if (entity.getGrupo().getGrupId() == null) {
				throw new ZMessManager().new EmptyFieldException("grupId_Grupo");
			}

			if (entity.getUsuario().getUsuaId() == null) {
				throw new ZMessManager().new EmptyFieldException("usuaId_Usuario");
			}

			grupoUsuarioDAO.update(entity);

			log.debug("update GrupoUsuario successful");
		} catch (Exception e) {
			log.error("update GrupoUsuario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<GrupoUsuarioDTO> getDataGrupoUsuario() throws Exception {
		try {
			List<GrupoUsuario> grupoUsuario = grupoUsuarioDAO.findAll();

			List<GrupoUsuarioDTO> grupoUsuarioDTO = new ArrayList<GrupoUsuarioDTO>();

			for (GrupoUsuario grupoUsuarioTmp : grupoUsuario) {
				GrupoUsuarioDTO grupoUsuarioDTO2 = new GrupoUsuarioDTO();

				grupoUsuarioDTO2.setGrusId(grupoUsuarioTmp.getGrusId());

				if (grupoUsuarioTmp.getGrupo() != null) {
					grupoUsuarioDTO2.setGrupId_Grupo(grupoUsuarioTmp.getGrupo().getGrupId());
				} else {
					grupoUsuarioDTO2.setGrupId_Grupo(null);
				}

				if (grupoUsuarioTmp.getUsuario() != null) {
					grupoUsuarioDTO2.setUsuaId_Usuario(grupoUsuarioTmp.getUsuario().getUsuaId());
				} else {
					grupoUsuarioDTO2.setUsuaId_Usuario(null);
				}

				grupoUsuarioDTO.add(grupoUsuarioDTO2);
			}

			return grupoUsuarioDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public GrupoUsuario getGrupoUsuario(Long grusId) throws Exception {
		log.debug("getting GrupoUsuario instance");

		GrupoUsuario entity = null;

		try {
			entity = grupoUsuarioDAO.findById(grusId);
		} catch (Exception e) {
			log.error("get GrupoUsuario failed", e);
			throw new ZMessManager().new FindingException("GrupoUsuario");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<GrupoUsuario> findPageGrupoUsuario(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<GrupoUsuario> entity = null;

		try {
			entity = grupoUsuarioDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("GrupoUsuario Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberGrupoUsuario() throws Exception {
		Long entity = null;

		try {
			entity = grupoUsuarioDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("GrupoUsuario Count");
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
	public List<GrupoUsuario> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<GrupoUsuario> list = new ArrayList<GrupoUsuario>();
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
			list = grupoUsuarioDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public GrupoUsuario obtenerGrupoUsuarioPorIdDeGrupoYUsuarioDocente(Long grupId) throws Exception {
		if(grupId==null){
			throw new Exception("El id del grupo no puede ser nulo");
		}
		if(grupId.equals(0)==true){
			throw new Exception("El id del grupo no puede ser 0");
		}
		return grupoUsuarioDAO.obtenerGrupoUsuarioPorIdDeGrupoYUsuarioDocente(grupId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void matricularEstudianteEnUnGrupo(GrupoDTO grupoDTO) throws Exception {
		Grupo grupo = logicGrupo1.getGrupo(grupoDTO.getGrupId());
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		com.vortexbird.seguridad.modelo.dto.UsuarioDTO loginUsuario = (com.vortexbird.seguridad.modelo.dto.UsuarioDTO) session.getAttribute("usuarioDTO");
		Usuario estudiante = logicUsuario2.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());
		
		GrupoUsuario grupoUsuario = new GrupoUsuario();
		grupoUsuario.setGrupo(grupo);
		grupoUsuario.setUsuario(estudiante);
		grupoUsuario.setGrusId(null);
		saveGrupoUsuario(grupoUsuario);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean estudianteEstaMatriculadoEnElCurso(Long grupId, Long usuaId) throws Exception {
		if(grupId == null){
			throw new Exception("No ha llegado el Id del Grupo");
		}
		if(usuaId == null){
			throw new Exception("No ha llegado el Id del Estudiante");
		}
		return grupoUsuarioDAO.estudianteEstaMatriculadoEnElCurso(grupId, usuaId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void desmatricularEstudianteEnUnGrupo(GrupoDTO grupoDTO) throws Exception {
		Grupo grupo = logicGrupo1.getGrupo(grupoDTO.getGrupId());
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		com.vortexbird.seguridad.modelo.dto.UsuarioDTO loginUsuario = (com.vortexbird.seguridad.modelo.dto.UsuarioDTO) session.getAttribute("usuarioDTO");
		Usuario estudiante = logicUsuario2.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());
		
		GrupoUsuario grupoUsuario = grupoUsuarioDAO.obtenerGrupoUsuarioPorIdGrupoYIdEstudiante(grupo.getGrupId(), estudiante.getUsuaId());
		deleteGrupoUsuario(grupoUsuario);
	}
}
