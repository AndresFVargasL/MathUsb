package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.dataaccess.dao.*;
import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.GrupoDTO;
import co.edu.usbcali.mathusb.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.seguridad.modelo.dto.UsuarioDTO;

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
@Service("GrupoLogic")
public class GrupoLogic implements IGrupoLogic {
	private static final Logger log = LoggerFactory.getLogger(GrupoLogic.class);

	/**
	 * DAO injected by Spring that manages Grupo entities
	 *
	 */
	@Autowired
	private IGrupoDAO grupoDAO;

	/**
	 * DAO injected by Spring that manages GrupoUsuario entities
	 *
	 */
	@Autowired
	private IGrupoUsuarioDAO grupoUsuarioDAO;

	/**
	 * Logic injected by Spring that manages Asignatura entities
	 *
	 */
	@Autowired
	IAsignaturaLogic logicAsignatura1;

	@Autowired
	IUsuarioLogic usuarioLogic;

	@Autowired
	IGrupoUsuarioLogic grupoUsuarioLogic;

	@Transactional(readOnly = true)
	public List<Grupo> getGrupo() throws Exception {
		log.debug("finding all Grupo instances");

		List<Grupo> list = new ArrayList<Grupo>();

		try {
			list = grupoDAO.findAll();
		} catch (Exception e) {
			log.error("finding all Grupo failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "Grupo");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveGrupo(Grupo entity) throws Exception {
		log.info("# Creando un grupo. Fecha: " + new Date() + " #");

		try {
			if (entity.getAsignatura() == null) {
				throw new ZMessManager().new ForeignException("asignatura");
			}

			if (entity.getDescripcionGrupo() == null) {
				throw new ZMessManager().new EmptyFieldException("descripcionGrupo");
			}

			if ((entity.getDescripcionGrupo() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getDescripcionGrupo(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("descripcionGrupo");
			}

			if (entity.getAsignatura().getAsigId() == null) {
				throw new ZMessManager().new EmptyFieldException("asigId_Asignatura");
			}
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			UsuarioDTO loginUsuario = (UsuarioDTO) session.getAttribute("usuarioDTO");
			Usuario usuario = usuarioLogic.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());
			
			Grupo grupoConsultar = grupoDAO.obtenerGrupoDadoIdUsuarioYDescripcion(usuario.getUsuaId(), entity.getDescripcionGrupo());
			if(grupoConsultar!=null){
				throw new Exception("Ya tienes asignado un grupo con este nombre: "+grupoConsultar.getDescripcionGrupo());
			}

			grupoDAO.save(entity);

			GrupoUsuario grupoUsuario = new GrupoUsuario();
			grupoUsuario.setGrupo(entity);
			grupoUsuario.setUsuario(usuario);
			grupoUsuario.setGrusId(null);

			grupoUsuarioLogic.saveGrupoUsuario(grupoUsuario);

			log.info("# La creacion del grupo ha finalizado con exito a las " + new Date() + " #");
		} catch (Exception e) {
			log.error("# Ha ocurrido un error creando un grupo #", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteGrupo(Grupo entity) throws Exception {
		log.debug("deleting Grupo instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Grupo");
		}

		if (entity.getGrupId() == null) {
			throw new ZMessManager().new EmptyFieldException("grupId");
		}

		List<GrupoUsuario> grupoUsuarios = null;

		try {
			grupoUsuarios = grupoUsuarioDAO.findByProperty("grupo.grupId", entity.getGrupId());

			if (Utilities.validationsList(grupoUsuarios) == true) {
				throw new ZMessManager().new DeletingException("grupoUsuarios");
			}

			grupoDAO.delete(entity);

			log.debug("delete Grupo successful");
		} catch (Exception e) {
			log.error("delete Grupo failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateGrupo(Grupo entity) throws Exception {
		log.debug("updating Grupo instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Grupo");
			}

			if (entity.getAsignatura() == null) {
				throw new ZMessManager().new ForeignException("asignatura");
			}

			if (entity.getDescripcionGrupo() == null) {
				throw new ZMessManager().new EmptyFieldException("descripcionGrupo");
			}

			if ((entity.getDescripcionGrupo() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getDescripcionGrupo(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("descripcionGrupo");
			}

			if (entity.getGrupId() == null) {
				throw new ZMessManager().new EmptyFieldException("grupId");
			}

			if (entity.getAsignatura().getAsigId() == null) {
				throw new ZMessManager().new EmptyFieldException("asigId_Asignatura");
			}

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			UsuarioDTO loginUsuario = (UsuarioDTO) session.getAttribute("usuarioDTO");
			Usuario usuario = usuarioLogic.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());
			
			Grupo grupoConsultar = grupoDAO.obtenerGrupoDadoIdUsuarioYDescripcion(usuario.getUsuaId(), entity.getDescripcionGrupo());
			if(grupoConsultar!=null){
				throw new Exception("Ya tienes asignado un grupo con este nombre: "+grupoConsultar.getDescripcionGrupo());
			}
			
			grupoDAO.update(entity);

			log.debug("update Grupo successful");
		} catch (Exception e) {
			log.error("update Grupo failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<GrupoDTO> getDataGrupo() throws Exception {
		try {
			List<Grupo> grupo = grupoDAO.findAll();

			List<GrupoDTO> grupoDTO = new ArrayList<GrupoDTO>();

			for (Grupo grupoTmp : grupo) {
				GrupoDTO grupoDTO2 = new GrupoDTO();

				grupoDTO2.setGrupId(grupoTmp.getGrupId());
				grupoDTO2.setDescripcionGrupo(
						(grupoTmp.getDescripcionGrupo() != null) ? grupoTmp.getDescripcionGrupo() : null);

				if (grupoTmp.getAsignatura() != null) {
					grupoDTO2.setAsigId_Asignatura(grupoTmp.getAsignatura().getAsigId());
				} else {
					grupoDTO2.setAsigId_Asignatura(null);
				}

				grupoDTO.add(grupoDTO2);
			}

			return grupoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Grupo getGrupo(Long grupId) throws Exception {
		log.debug("getting Grupo instance");

		Grupo entity = null;

		try {
			entity = grupoDAO.findById(grupId);
		} catch (Exception e) {
			log.error("get Grupo failed", e);
			throw new ZMessManager().new FindingException("Grupo");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<Grupo> findPageGrupo(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		List<Grupo> entity = null;

		try {
			entity = grupoDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Grupo Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberGrupo() throws Exception {
		Long entity = null;

		try {
			entity = grupoDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Grupo Count");
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
	public List<Grupo> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<Grupo> list = new ArrayList<Grupo>();
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
			list = grupoDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grupo> traerGruposDeDocentes() throws Exception {
		return grupoDAO.traerGruposDeDocentes();
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrupoDTO> traerGruposConDocentesYNombreAsignatura() throws Exception {

		List<Grupo> gruposDocentes = grupoDAO.traerGruposDeDocentes();
		List<GrupoDTO> gruposDocentesDTO = new ArrayList<>();
		
		for (Grupo grupo : gruposDocentes) {
			GrupoDTO grupoDTO = new GrupoDTO();
			grupoDTO.setGrupId(grupo.getGrupId());
			grupoDTO.setDescripcionGrupo(grupo.getDescripcionGrupo());
			grupoDTO.setNombreAsignatura(logicAsignatura1.getAsignatura(grupo.getAsignatura().getAsigId()).getNombreAsignatura());
			GrupoUsuario grupoUsuario = grupoUsuarioLogic.obtenerGrupoUsuarioPorIdDeGrupoYUsuarioDocente(grupo.getGrupId());
			Usuario usuario = usuarioLogic.getUsuario(grupoUsuario.getUsuario().getUsuaId());
			String nombreCompletoDocente = usuario.getApellidoUsuario()+", "+usuario.getNombreUsuario();
			grupoDTO.setNombreCompletoDocente(nombreCompletoDocente);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			UsuarioDTO loginUsuario = (UsuarioDTO) session.getAttribute("usuarioDTO");
			Usuario estudiante = usuarioLogic.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());
			boolean estudianteEstaMatriculado = grupoUsuarioLogic.estudianteEstaMatriculadoEnElCurso(grupo.getGrupId(), estudiante.getUsuaId());
			if(estudianteEstaMatriculado==false){
				gruposDocentesDTO.add(grupoDTO);
			}
		}

		return gruposDocentesDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrupoDTO> listaDeGruposDelEstudiante() throws Exception {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		UsuarioDTO loginUsuario = (UsuarioDTO) session.getAttribute("usuarioDTO");
		Usuario estudiante = usuarioLogic.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());
		if(estudiante==null){
			throw new Exception("No hay informacion del estudiante");
		}
		List<Grupo> gruposEstudiante = grupoDAO.listaDeGruposDelUsuario(estudiante.getUsuaId());
		List<GrupoDTO> gruposEstudianteDTO = new ArrayList<>();
		for (Grupo grupo : gruposEstudiante) {
			GrupoDTO grupoDTO = new GrupoDTO();
			grupoDTO.setGrupId(grupo.getGrupId());
			grupoDTO.setDescripcionGrupo(grupo.getDescripcionGrupo());
			grupoDTO.setNombreAsignatura(logicAsignatura1.getAsignatura(grupo.getAsignatura().getAsigId()).getNombreAsignatura());
			GrupoUsuario grupoUsuario = grupoUsuarioLogic.obtenerGrupoUsuarioPorIdDeGrupoYUsuarioDocente(grupo.getGrupId());
			Usuario usuario = usuarioLogic.getUsuario(grupoUsuario.getUsuario().getUsuaId());
			String nombreCompletoDocente = usuario.getApellidoUsuario()+", "+usuario.getNombreUsuario();
			grupoDTO.setNombreCompletoDocente(nombreCompletoDocente);
			gruposEstudianteDTO.add(grupoDTO);
			
		}
		
		return gruposEstudianteDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrupoDTO> listaDeGruposDelDocente() throws Exception {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		UsuarioDTO loginUsuario = (UsuarioDTO) session.getAttribute("usuarioDTO");
		Usuario docente = usuarioLogic.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());
		if(docente==null){
			throw new Exception("No hay informacion del docente");
		}
		List<Grupo> gruposDocente = grupoDAO.listaDeGruposDelUsuario(docente.getUsuaId());
		List<GrupoDTO> gruposDocenteDTO = new ArrayList<>();
		for (Grupo grupo : gruposDocente) {
			GrupoDTO grupoDTO = new GrupoDTO();
			grupoDTO.setGrupId(grupo.getGrupId());
			grupoDTO.setDescripcionGrupo(grupo.getDescripcionGrupo());
			grupoDTO.setNombreAsignatura(logicAsignatura1.getAsignatura(grupo.getAsignatura().getAsigId()).getNombreAsignatura());
			GrupoUsuario grupoUsuario = grupoUsuarioLogic.obtenerGrupoUsuarioPorIdDeGrupoYUsuarioDocente(grupo.getGrupId());
			Usuario usuario = usuarioLogic.getUsuario(grupoUsuario.getUsuario().getUsuaId());
			String nombreCompletoDocente = usuario.getApellidoUsuario()+", "+usuario.getNombreUsuario();
			grupoDTO.setNombreCompletoDocente(nombreCompletoDocente);
			gruposDocenteDTO.add(grupoDTO);
			
		}
		
		return gruposDocenteDTO;
	
	}

	@Override
	@Transactional(readOnly = true)
	public GrupoDTO obtenerGrupoDTODadoGrupId(Long grupId) throws Exception {
		if(grupId==null){
			throw new Exception("El Id del grupo no puede ser nulo");
		}
		if(grupId.equals(0)==true){
			throw new Exception("El Id del grupo no puede ser 0 (cero)");
		}
		Grupo grupo = grupoDAO.findById(grupId);
		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setDescripcionGrupo(grupo.getDescripcionGrupo());
		Asignatura asignatura = logicAsignatura1.getAsignatura(grupo.getAsignatura().getAsigId());
		grupoDTO.setNombreAsignatura(asignatura.getNombreAsignatura());
		grupoDTO.setAsigId_Asignatura(asignatura.getAsigId());
		GrupoUsuario grupoUsuario = grupoUsuarioLogic.obtenerGrupoUsuarioPorIdDeGrupoYUsuarioDocente(grupo.getGrupId());
		Usuario usuario = usuarioLogic.getUsuario(grupoUsuario.getUsuario().getUsuaId());
		String nombreCompletoDocente = usuario.getApellidoUsuario()+", "+usuario.getNombreUsuario();
		grupoDTO.setNombreCompletoDocente(nombreCompletoDocente);
		grupoDTO.setGrupId(grupo.getGrupId());
		return grupoDTO;
	}
}
