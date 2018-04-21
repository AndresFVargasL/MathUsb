package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.dataaccess.dao.*;
import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.DetalleEvaluacionDTO;
import co.edu.usbcali.mathusb.utilities.Utilities;

import org.hibernate.usertype.LoggableUserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.nio.ReadOnlyBufferException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("DetalleEvaluacionLogic")
public class DetalleEvaluacionLogic implements IDetalleEvaluacionLogic {
	private static final Logger log = LoggerFactory.getLogger(DetalleEvaluacionLogic.class);

	/**
	 * DAO injected by Spring that manages DetalleEvaluacion entities
	 *
	 */
	@Autowired
	private IDetalleEvaluacionDAO detalleEvaluacionDAO;

	/**
	 * Logic injected by Spring that manages Evaluacion entities
	 *
	 */
	@Autowired
	IEvaluacionLogic logicEvaluacion1;

	@Transactional(readOnly = true)
	public List<DetalleEvaluacion> getDetalleEvaluacion() throws Exception {
		log.debug("finding all DetalleEvaluacion instances");

		List<DetalleEvaluacion> list = new ArrayList<DetalleEvaluacion>();

		try {
			list = detalleEvaluacionDAO.findAll();
		} catch (Exception e) {
			log.error("finding all DetalleEvaluacion failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "DetalleEvaluacion");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveDetalleEvaluacion(DetalleEvaluacion entity) throws Exception {
		log.info("Inicio guardando Detalle Evaluacion, Fecha/Hora: " + new Date());

		try {
			if (entity.getEvaluacion() == null) {
				throw new ZMessManager().new ForeignException("evaluacion");
			}

			if (entity.getCorte() == null) {
				throw new ZMessManager().new EmptyFieldException("corte");
			}

			if (entity.getAsunto().trim().equals("") == true) {
				throw new Exception(
						"En el campo Asunto debe ingresar un título y una descripción (opcional) para su evaluación");
			}

			if (entity.getFechaCreacion() == null) {
				throw new ZMessManager().new EmptyFieldException("fechaCreacion");
			}

			if (entity.getFechaFinPublicacion() == null) {
				throw new ZMessManager().new EmptyFieldException("Fecha de Finalización de Publicación");
			}

			if (entity.getFechaInicioPublicacion() == null) {
				throw new ZMessManager().new EmptyFieldException("Fecha de Inicio de Publicación");
			}

			if (entity.getEvaluacion().getEvalId() == null) {
				throw new ZMessManager().new EmptyFieldException("evalId_Evaluacion");
			}

			if (entity.getFechaFinPublicacion().before(entity.getFechaInicioPublicacion())) {
				throw new Exception("La fecha final de publicación no puede ser antes de la fecha inicial");
			}

			if (entity.getFechaFinPublicacion().equals(entity.getFechaInicioPublicacion())) {
				throw new Exception("Las fechas de publicación no pueden ser iguales");
			}

			if (entity.getCorte() == 0) {
				throw new Exception("Debe seleccionar un corte");
			}

			detalleEvaluacionDAO.save(entity);

			log.info("Se ha guardado Detalle Evaluacion, Fecha/Hora: " + new Date());
		} catch (Exception e) {
			log.error("Error guardando Detalle Evaluacion", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteDetalleEvaluacion(DetalleEvaluacion entity) throws Exception {
		log.debug("deleting DetalleEvaluacion instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("DetalleEvaluacion");
		}

		if (entity.getDeevId() == null) {
			throw new ZMessManager().new EmptyFieldException("deevId");
		}

		try {
			detalleEvaluacionDAO.delete(entity);

			log.debug("delete DetalleEvaluacion successful");
		} catch (Exception e) {
			log.error("delete DetalleEvaluacion failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateDetalleEvaluacion(DetalleEvaluacion entity) throws Exception {
		log.debug("updating DetalleEvaluacion instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("DetalleEvaluacion");
			}

			if (entity.getEvaluacion() == null) {
				throw new ZMessManager().new ForeignException("evaluacion");
			}

			if ((entity.getAsunto() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getAsunto(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("asunto");
			}

			if (entity.getCorte() == null) {
				throw new ZMessManager().new EmptyFieldException("corte");
			}

			if (entity.getDeevId() == null) {
				throw new ZMessManager().new EmptyFieldException("deevId");
			}

			if (entity.getFechaCreacion() == null) {
				throw new ZMessManager().new EmptyFieldException("fechaCreacion");
			}

			if (entity.getFechaFinPublicacion() == null) {
				throw new ZMessManager().new EmptyFieldException("fechaFinPublicacion");
			}

			if (entity.getFechaInicioPublicacion() == null) {
				throw new ZMessManager().new EmptyFieldException("fechaInicioPublicacion");
			}

			if (entity.getEvaluacion().getEvalId() == null) {
				throw new ZMessManager().new EmptyFieldException("evalId_Evaluacion");
			}

			detalleEvaluacionDAO.update(entity);

			log.debug("update DetalleEvaluacion successful");
		} catch (Exception e) {
			log.error("update DetalleEvaluacion failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<DetalleEvaluacionDTO> getDataDetalleEvaluacion() throws Exception {
		try {
			List<DetalleEvaluacion> detalleEvaluacion = detalleEvaluacionDAO.findAll();

			List<DetalleEvaluacionDTO> detalleEvaluacionDTO = new ArrayList<DetalleEvaluacionDTO>();

			for (DetalleEvaluacion detalleEvaluacionTmp : detalleEvaluacion) {
				DetalleEvaluacionDTO detalleEvaluacionDTO2 = new DetalleEvaluacionDTO();

				detalleEvaluacionDTO2.setDeevId(detalleEvaluacionTmp.getDeevId());
				detalleEvaluacionDTO2.setAsunto(
						(detalleEvaluacionTmp.getAsunto() != null) ? detalleEvaluacionTmp.getAsunto() : null);
				detalleEvaluacionDTO2
						.setCorte((detalleEvaluacionTmp.getCorte() != null) ? detalleEvaluacionTmp.getCorte() : null);
				detalleEvaluacionDTO2.setFechaCreacion(detalleEvaluacionTmp.getFechaCreacion());
				detalleEvaluacionDTO2.setFechaFinPublicacion(detalleEvaluacionTmp.getFechaFinPublicacion());
				detalleEvaluacionDTO2.setFechaInicioPublicacion(detalleEvaluacionTmp.getFechaInicioPublicacion());

				if (detalleEvaluacionTmp.getEvaluacion() != null) {
					detalleEvaluacionDTO2.setEvalId_Evaluacion(detalleEvaluacionTmp.getEvaluacion().getEvalId());
				} else {
					detalleEvaluacionDTO2.setEvalId_Evaluacion(null);
				}

				detalleEvaluacionDTO.add(detalleEvaluacionDTO2);
			}

			return detalleEvaluacionDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public DetalleEvaluacion getDetalleEvaluacion(Long deevId) throws Exception {
		log.debug("getting DetalleEvaluacion instance");

		DetalleEvaluacion entity = null;

		try {
			entity = detalleEvaluacionDAO.findById(deevId);
		} catch (Exception e) {
			log.error("get DetalleEvaluacion failed", e);
			throw new ZMessManager().new FindingException("DetalleEvaluacion");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<DetalleEvaluacion> findPageDetalleEvaluacion(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<DetalleEvaluacion> entity = null;

		try {
			entity = detalleEvaluacionDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("DetalleEvaluacion Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberDetalleEvaluacion() throws Exception {
		Long entity = null;

		try {
			entity = detalleEvaluacionDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("DetalleEvaluacion Count");
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
	public List<DetalleEvaluacion> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<DetalleEvaluacion> list = new ArrayList<DetalleEvaluacion>();
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
			list = detalleEvaluacionDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	public boolean detalleEvaluacionIsOk(DetalleEvaluacion entity) throws Exception {
		try {
			if (entity.getEvaluacion() == null) {
				throw new ZMessManager().new ForeignException("evaluacion");
			}

			if (entity.getCorte() == null) {
				throw new ZMessManager().new EmptyFieldException("corte");
			}

			if (entity.getAsunto().trim().equals("") == true) {
				throw new Exception(
						"En el campo Asunto debe ingresar un título y una descripción (opcional) para su documento");
			}

			if (entity.getFechaCreacion() == null) {
				throw new ZMessManager().new EmptyFieldException("fechaCreacion");
			}

			if (entity.getFechaFinPublicacion() == null) {
				throw new ZMessManager().new EmptyFieldException("Fecha de Finalización de Publicación");
			}

			if (entity.getFechaInicioPublicacion() == null) {
				throw new ZMessManager().new EmptyFieldException("Fecha de Inicio de Publicación");
			}

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaHoy = new Date();
			String fechaString = simpleDateFormat.format(fechaHoy);
			fechaHoy = simpleDateFormat.parse(fechaString);

			if (entity.getFechaInicioPublicacion().before(fechaHoy)) {
				throw new Exception("La fecha de inicio del documento no puede ser anterior a la fecha actual");
			}

			/*
			 * if (entity.getFechaFinPublicacion().before(new Date())) { throw
			 * new Exception(
			 * "La fecha de disponibilidad de la evaluación ya pasó"); }
			 */

			if (entity.getFechaFinPublicacion().before(entity.getFechaInicioPublicacion())) {
				throw new Exception("La fecha final de publicación no puede ser antes de la fecha inicial");
			}

			if (entity.getFechaFinPublicacion().equals(entity.getFechaInicioPublicacion())) {
				throw new Exception("Las fechas de publicación no pueden ser iguales");
			}

			if (entity.getCorte() == 0) {
				throw new Exception("Debe seleccionar un corte");
			}

			return true;

		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Override
	@Transactional(readOnly = true)
	public DetalleEvaluacion obtenerDetalleEvaluacionDadoIdEvaluacion(Evaluacion evaluacion) throws Exception {
		try {
			if (evaluacion == null) {
				throw new Exception("Evaluacion no puede ser nulo");
			}
			Long idEvaluacion = evaluacion.getEvalId();

			DetalleEvaluacion detalleEvaluacion = detalleEvaluacionDAO.obtenerDetalleEvaluacionDadoIdEval(idEvaluacion);
			return detalleEvaluacion;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

}
