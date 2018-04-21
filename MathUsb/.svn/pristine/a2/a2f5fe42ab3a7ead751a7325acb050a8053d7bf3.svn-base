package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.dataaccess.dao.*;
import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.EvaluacionDTO;
import co.edu.usbcali.mathusb.modelo.dto.PreguntaDTO;
import co.edu.usbcali.mathusb.utilities.Utilities;
import net.sourceforge.jeuclid.MutableLayoutContext;
import net.sourceforge.jeuclid.context.LayoutContextImpl;
import net.sourceforge.jeuclid.context.Parameter;
import net.sourceforge.jeuclid.converter.Converter;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("PreguntaLogic")
public class PreguntaLogic implements IPreguntaLogic {
	private static final Logger log = LoggerFactory.getLogger(PreguntaLogic.class);

	/**
	 * DAO injected by Spring that manages Pregunta entities
	 *
	 */
	@Autowired
	private IPreguntaDAO preguntaDAO;

	/**
	 * DAO injected by Spring that manages EvaPregRes entities
	 *
	 */
	@Autowired
	private IEvaPregResDAO evaPregResDAO;

	/**
	 * Logic injected by Spring that manages Tema entities
	 *
	 */
	@Autowired
	ITemaLogic logicTema1;
	
	@Autowired
	IParametroLogic parametroLogic;

	@Transactional(readOnly = true)
	public List<Pregunta> getPregunta() throws Exception {
		log.debug("finding all Pregunta instances");

		List<Pregunta> list = new ArrayList<Pregunta>();

		try {
			list = preguntaDAO.findAll();
		} catch (Exception e) {
			log.error("finding all Pregunta failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "Pregunta");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePregunta(Pregunta entity) throws Exception {
		log.debug("saving Pregunta instance");

		try {
			if (entity.getTema() == null) {
				throw new ZMessManager().new ForeignException("tema");
			}

			if (entity.getDescripcionPregunta() == null) {
				throw new ZMessManager().new EmptyFieldException("descripcionPregunta");
			}

			if ((entity.getDescripcionPregunta() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getDescripcionPregunta(), 10485760) == false)) {
				throw new ZMessManager().new NotValidFormatException("descripcionPregunta");
			}

			if (entity.getTema().getTemaId() == null) {
				throw new ZMessManager().new EmptyFieldException("temaId_Tema");
			}
			
			Parametro parametro = parametroLogic.getParametroPorDescripcion("editorVacio");
			String valorVacioEditor = parametro.getValorParametro();
			if (entity.getDescripcionPregunta().trim().equals(valorVacioEditor.trim()) == true) {
				throw new Exception("Debe ingresar un valor en el editor de texto matemático");
			}



			preguntaDAO.save(entity);

			log.debug("save Pregunta successful");
		} catch (Exception e) {
			log.error("save Pregunta failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePregunta(Pregunta entity) throws Exception {
		log.debug("deleting Pregunta instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Pregunta");
		}

		if (entity.getPregId() == null) {
			throw new ZMessManager().new EmptyFieldException("pregId");
		}

		List<EvaPregRes> evaPregReses = null;

		try {
			evaPregReses = evaPregResDAO.findByProperty("pregunta.pregId", entity.getPregId());

			if (Utilities.validationsList(evaPregReses) == true) {
				throw new ZMessManager().new DeletingException("evaPregReses");
			}

			preguntaDAO.delete(entity);

			log.debug("delete Pregunta successful");
		} catch (Exception e) {
			log.error("delete Pregunta failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePregunta(Pregunta entity) throws Exception {
		log.debug("updating Pregunta instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Pregunta");
			}

			if (entity.getTema() == null) {
				throw new ZMessManager().new ForeignException("tema");
			}

			if (entity.getDescripcionPregunta() == null) {
				throw new ZMessManager().new EmptyFieldException("descripcionPregunta");
			}

			if ((entity.getDescripcionPregunta() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getDescripcionPregunta(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("descripcionPregunta");
			}

			if (entity.getPregId() == null) {
				throw new ZMessManager().new EmptyFieldException("pregId");
			}

			if (entity.getTema().getTemaId() == null) {
				throw new ZMessManager().new EmptyFieldException("temaId_Tema");
			}

			preguntaDAO.update(entity);

			log.debug("update Pregunta successful");
		} catch (Exception e) {
			log.error("update Pregunta failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<PreguntaDTO> getDataPregunta() throws Exception {
		try {
			List<Pregunta> pregunta = preguntaDAO.findAll();

			List<PreguntaDTO> preguntaDTO = new ArrayList<PreguntaDTO>();

			for (Pregunta preguntaTmp : pregunta) {
				PreguntaDTO preguntaDTO2 = new PreguntaDTO();

				preguntaDTO2.setPregId(preguntaTmp.getPregId());
				preguntaDTO2.setDescripcionPregunta(
						(preguntaTmp.getDescripcionPregunta() != null) ? preguntaTmp.getDescripcionPregunta() : null);

				if (preguntaTmp.getTema() != null) {
					preguntaDTO2.setTemaId_Tema(preguntaTmp.getTema().getTemaId());
				} else {
					preguntaDTO2.setTemaId_Tema(null);
				}

				preguntaDTO.add(preguntaDTO2);
			}

			return preguntaDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Pregunta getPregunta(Long pregId) throws Exception {
		log.debug("getting Pregunta instance");

		Pregunta entity = null;

		try {
			entity = preguntaDAO.findById(pregId);
		} catch (Exception e) {
			log.error("get Pregunta failed", e);
			throw new ZMessManager().new FindingException("Pregunta");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<Pregunta> findPagePregunta(String sortColumnName, boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		List<Pregunta> entity = null;

		try {
			entity = preguntaDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Pregunta Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberPregunta() throws Exception {
		Long entity = null;

		try {
			entity = preguntaDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Pregunta Count");
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
	public List<Pregunta> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<Pregunta> list = new ArrayList<Pregunta>();
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
			list = preguntaDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PreguntaDTO> consultarPreguntaDadoIdEval(Long evalId) throws Exception {
		if(evalId==null){
			throw new Exception("No ha llegado el id de la evaluación");
		}
		List<Pregunta> listaPreguntasDadoEval = preguntaDAO.consultarPreguntaDadoIdEval(evalId);
		List<PreguntaDTO> listaPreguntaDTO = new ArrayList<>();
		for (Pregunta pregunta : listaPreguntasDadoEval) {
			PreguntaDTO preguntaDTO = new PreguntaDTO();
			preguntaDTO.setDescripcionPregunta(pregunta.getDescripcionPregunta());
			preguntaDTO.setPregId(pregunta.getPregId());
			preguntaDTO.setTemaId_Tema(pregunta.getTema().getTemaId());
			Tema tema = logicTema1.getTema(pregunta.getTema().getTemaId());
			preguntaDTO.setTemaString(tema.getTituloTema());
			listaPreguntaDTO.add(preguntaDTO);
		}
		return listaPreguntaDTO;
	}

	@Override
	public StreamedContent getStreamedContent(PreguntaDTO preguntaDTO) throws Exception {
		try {
			log.info("Obteniendo StreamedContent a las: " + new Date());
			Parametro ruta = parametroLogic.getParametroPorDescripcion("rutaImagenPreguntaForo");
			String rutaString = ruta.getValorParametro();
			
			//Aquí se debe poner la ruta en un string
			
			File fInput = new File(rutaString+"input.html");
			FileWriter fw = new FileWriter(fInput);
			fw.write(preguntaDTO.getDescripcionPregunta());
			fw.close();

			File fOutput = new File(rutaString+"pregunta.jpg");

			Converter converter = Converter.getInstance();

			MutableLayoutContext params = new LayoutContextImpl(LayoutContextImpl.getDefaultLayoutContext());
			params.setParameter(Parameter.MATHSIZE, 35f);

			converter.convert(fInput, fOutput, "image/jpeg", params);
			
			FileInputStream fis = new FileInputStream(fOutput);
			byte[] bytes = new byte[(int) fOutput.length()];
			fis.read(bytes);
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			fis.close();


			
			StreamedContent streamedContent = new DefaultStreamedContent(bais, "image/jpeg");
			fInput.delete();
			fOutput.delete();
			return streamedContent;

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PreguntaDTO> consultarPreguntasDadoIdTema(Long temaId) throws Exception {
		if(temaId == null){
			throw new Exception("No ha llegado el id del tema");
		}
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EvaluacionDTO evaluacionDTO = (EvaluacionDTO) session.getAttribute("evaluacionMostrar");
		
		List<Pregunta> listaPreguntasDadoTema = preguntaDAO.consultarPreguntasDadoIdTema(temaId);
		List<PreguntaDTO> listaPreguntaDTO = new ArrayList<>();
		for (Pregunta pregunta : listaPreguntasDadoTema) {
			PreguntaDTO preguntaDTO = new PreguntaDTO();
			preguntaDTO.setDescripcionPregunta(pregunta.getDescripcionPregunta());
			preguntaDTO.setPregId(pregunta.getPregId());
			preguntaDTO.setTemaId_Tema(pregunta.getTema().getTemaId());
			Tema tema = logicTema1.getTema(pregunta.getTema().getTemaId());
			preguntaDTO.setTemaString(tema.getTituloTema());
			
			EvaPregRes evaPregRes = evaPregResDAO.obtenerEvaPregResDadoIdEvalYIdPreg(evaluacionDTO.getEvalId(), pregunta.getPregId());
			if(evaPregRes!=null){
				preguntaDTO.setPerteneceAEvaluacion("Si");
				preguntaDTO.setPerteneceALaEvaluacion(true);
			}else{
				preguntaDTO.setPerteneceAEvaluacion("No");
				preguntaDTO.setPerteneceALaEvaluacion(false);
			}
			
			listaPreguntaDTO.add(preguntaDTO);
		}
		return listaPreguntaDTO;
	}
}
