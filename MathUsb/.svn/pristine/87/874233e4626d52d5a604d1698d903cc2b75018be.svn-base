package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.dataaccess.dao.*;
import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.TemaDTO;
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

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("TemaLogic")
public class TemaLogic implements ITemaLogic {
	private static final Logger log = LoggerFactory.getLogger(TemaLogic.class);

	/**
	 * DAO injected by Spring that manages Tema entities
	 *
	 */
	@Autowired
	private ITemaDAO temaDAO;

	/**
	 * DAO injected by Spring that manages Evaluacion entities
	 *
	 */
	@Autowired
	private IEvaluacionDAO evaluacionDAO;

	@Transactional(readOnly = true)
	public List<Tema> getTema() throws Exception {
		log.debug("finding all Tema instances");

		List<Tema> list = new ArrayList<Tema>();

		try {
			list = temaDAO.findAll();
		} catch (Exception e) {
			log.error("finding all Tema failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "Tema");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveTema(Tema entity) throws Exception {
		log.info("Guardando un nuevo tema, Fecha/hora: " + new Date());

		try {
			if (entity.getDescripcionTema() == null) {
				throw new ZMessManager().new EmptyFieldException("Descripción del tema");
			}
			
			if(entity.getDescripcionTema().trim().equals("")==true){
				throw new ZMessManager().new EmptyFieldException("Descripción del tema");
			}

			if ((entity.getDescripcionTema() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getDescripcionTema(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("Descripción del tema");
			}

			if (entity.getPalabrasClave() == null) {
				throw new ZMessManager().new EmptyFieldException("Palabras claves");
			}

			if ((entity.getPalabrasClave() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getPalabrasClave(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("Palabras claves");
			}
			
			if(entity.getPalabrasClave().trim().equals("")==true){
				throw new ZMessManager().new EmptyFieldException("Palabras claves");
			}

			if (entity.getTituloTema() == null) {
				throw new ZMessManager().new EmptyFieldException("Título del tema");
			}
			
			if ((entity.getTituloTema() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getTituloTema(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("Título del tema");
			}

			if(entity.getTituloTema().trim().equals("")==true){
				throw new ZMessManager().new EmptyFieldException("Título del tema");
			}
			
			temaDAO.save(entity);

			log.info("Tema guardado satisfactoriamente, Fecha/Hora: "+new Date());
		} catch (Exception e) {
			log.error("Error guardando tema ", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteTema(Tema entity) throws Exception {
		log.debug("deleting Tema instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Tema");
		}

		if (entity.getTemaId() == null) {
			throw new ZMessManager().new EmptyFieldException("temaId");
		}

		List<Evaluacion> evaluacions = null;

		try {
			evaluacions = evaluacionDAO.findByProperty("tema.temaId", entity.getTemaId());

			if (Utilities.validationsList(evaluacions) == true) {
				throw new ZMessManager().new DeletingException("evaluacions");
			}

			temaDAO.delete(entity);

			log.debug("delete Tema successful");
		} catch (Exception e) {
			log.error("delete Tema failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateTema(Tema entity) throws Exception {
		log.debug("updating Tema instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Tema");
			}

			if (entity.getDescripcionTema() == null) {
				throw new ZMessManager().new EmptyFieldException("descripcionTema");
			}

			if ((entity.getDescripcionTema() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getDescripcionTema(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("descripcionTema");
			}

			if (entity.getPalabrasClave() == null) {
				throw new ZMessManager().new EmptyFieldException("palabrasClave");
			}

			if ((entity.getPalabrasClave() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getPalabrasClave(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("palabrasClave");
			}

			if (entity.getTemaId() == null) {
				throw new ZMessManager().new EmptyFieldException("temaId");
			}

			if ((entity.getTituloTema() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getTituloTema(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("tituloTema");
			}

			temaDAO.update(entity);

			log.debug("update Tema successful");
		} catch (Exception e) {
			log.error("update Tema failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<TemaDTO> getDataTema() throws Exception {
		try {
			List<Tema> tema = temaDAO.findAll();

			List<TemaDTO> temaDTO = new ArrayList<TemaDTO>();

			for (Tema temaTmp : tema) {
				TemaDTO temaDTO2 = new TemaDTO();

				temaDTO2.setTemaId(temaTmp.getTemaId());
				temaDTO2.setDescripcionTema(
						(temaTmp.getDescripcionTema() != null) ? temaTmp.getDescripcionTema() : null);
				temaDTO2.setPalabrasClave((temaTmp.getPalabrasClave() != null) ? temaTmp.getPalabrasClave() : null);
				temaDTO2.setTituloTema((temaTmp.getTituloTema() != null) ? temaTmp.getTituloTema() : null);
				temaDTO.add(temaDTO2);
			}

			return temaDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Tema getTema(Long temaId) throws Exception {
		log.debug("getting Tema instance");

		Tema entity = null;

		try {
			entity = temaDAO.findById(temaId);
		} catch (Exception e) {
			log.error("get Tema failed", e);
			throw new ZMessManager().new FindingException("Tema");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<Tema> findPageTema(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		List<Tema> entity = null;

		try {
			entity = temaDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Tema Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberTema() throws Exception {
		Long entity = null;

		try {
			entity = temaDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Tema Count");
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
	public List<Tema> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<Tema> list = new ArrayList<Tema>();
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
			list = temaDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	@Transactional (readOnly = true)
	public List<TemaDTO> obtenerTemaDadoPalabraClave(String palabraClave) throws Exception {
		
		if(palabraClave.trim().equals("")==true){
			return getDataTema();
		}
		
		List<TemaDTO> losTemasPorPalabraClave = new ArrayList<>();
		List<Tema> temasTilde = temaDAO.obtenerTemaDadoPalabraClaveConTilde(palabraClave.toUpperCase());
		List<Tema> temasSinTilde = temaDAO.obtenerTemaDadoPalabraClaveSinTilde(palabraClave.toUpperCase()); 
		
		for (Tema temaTmp : temasTilde) {
			TemaDTO temaDTO2 = new TemaDTO();

			temaDTO2.setTemaId(temaTmp.getTemaId());
			temaDTO2.setDescripcionTema(
					(temaTmp.getDescripcionTema() != null) ? temaTmp.getDescripcionTema() : null);
			temaDTO2.setPalabrasClave((temaTmp.getPalabrasClave() != null) ? temaTmp.getPalabrasClave() : null);
			temaDTO2.setTituloTema((temaTmp.getTituloTema() != null) ? temaTmp.getTituloTema() : null);
			losTemasPorPalabraClave.add(temaDTO2);
		}

		for (Tema temaTmp : temasSinTilde) {
			TemaDTO temaDTO2 = new TemaDTO();

			temaDTO2.setTemaId(temaTmp.getTemaId());
			temaDTO2.setDescripcionTema(
					(temaTmp.getDescripcionTema() != null) ? temaTmp.getDescripcionTema() : null);
			temaDTO2.setPalabrasClave((temaTmp.getPalabrasClave() != null) ? temaTmp.getPalabrasClave() : null);
			temaDTO2.setTituloTema((temaTmp.getTituloTema() != null) ? temaTmp.getTituloTema() : null);
			losTemasPorPalabraClave.add(temaDTO2);
		}

		
		return losTemasPorPalabraClave;
	}
}
