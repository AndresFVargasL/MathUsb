package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.dataaccess.dao.*;
import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.ComentarioDTO;
import co.edu.usbcali.mathusb.utilities.Utilities;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.DefaultStreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;

import net.sourceforge.jeuclid.MutableLayoutContext;
import net.sourceforge.jeuclid.context.LayoutContextImpl;
import net.sourceforge.jeuclid.context.Parameter;
import net.sourceforge.jeuclid.converter.Converter;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura/
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("ComentarioLogic")
public class ComentarioLogic implements IComentarioLogic {
	private static final Logger log = LoggerFactory.getLogger(ComentarioLogic.class);

	/**
	 * DAO injected by Spring that manages Comentario entities
	 *
	 */
	@Autowired
	private IComentarioDAO comentarioDAO;

	/**
	 * Logic injected by Spring that manages Comentario entities
	 *
	 */
	@Autowired
	IComentarioLogic logicComentario1;

	/**
	 * Logic injected by Spring that manages Usuario entities
	 *
	 */
	@Autowired
	IUsuarioLogic logicUsuario2;

	@Autowired
	IParametroLogic parametroLogic;

	@Transactional(readOnly = true)
	public List<Comentario> getComentario() throws Exception {
		log.debug("finding all Comentario instances");

		List<Comentario> list = new ArrayList<Comentario>();

		try {
			list = comentarioDAO.findAll();
		} catch (Exception e) {
			log.error("finding all Comentario failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL + "Comentario");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveComentario(Comentario entity) throws Exception {
		log.debug("saving Comentario instance");

		try {
			if (entity.getComentario() == null) {
				throw new ZMessManager().new ForeignException("comentario");
			}

			if (entity.getUsuario() == null) {
				throw new ZMessManager().new ForeignException("usuario");
			}

			if (entity.getComenId() == null) {
				throw new ZMessManager().new EmptyFieldException("comenId");
			}

			if (entity.getContenidoComentario() == null) {
				throw new ZMessManager().new EmptyFieldException("contenidoComentario");
			}

			if ((entity.getContenidoComentario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getContenidoComentario(), 10485760) == false)) {
				throw new ZMessManager().new NotValidFormatException("contenidoComentario");
			}

			if ((entity.getTituloComentario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getTituloComentario(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("tituloComentario");
			}

			if (entity.getComentario().getComenId() == null) {
				throw new ZMessManager().new EmptyFieldException("comenId_Comentario");
			}

			if (entity.getUsuario().getUsuaId() == null) {
				throw new ZMessManager().new EmptyFieldException("usuaId_Usuario");
			}

			if (getComentario(entity.getComenId()) != null) {
				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			}

			comentarioDAO.save(entity);

			log.debug("save Comentario successful");
		} catch (Exception e) {
			log.error("save Comentario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteComentario(Comentario entity) throws Exception {
		log.debug("deleting Comentario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Comentario");
		}

		if (entity.getComenId() == null) {
			throw new ZMessManager().new EmptyFieldException("comenId");
		}

		List<Comentario> comentarios = null;

		try {
			comentarios = comentarioDAO.findByProperty("comentario.comenId", entity.getComenId());

			if (Utilities.validationsList(comentarios) == true) {
				throw new ZMessManager().new DeletingException("comentarios");
			}

			comentarioDAO.delete(entity);

			log.debug("delete Comentario successful");
		} catch (Exception e) {
			log.error("delete Comentario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateComentario(Comentario entity) throws Exception {
		log.debug("updating Comentario instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Comentario");
			}

			if (entity.getUsuario() == null) {
				throw new ZMessManager().new ForeignException("usuario");
			}

			if (entity.getComenId() == null) {
				throw new ZMessManager().new EmptyFieldException("comenId");
			}

			if (entity.getContenidoComentario() == null) {
				throw new ZMessManager().new EmptyFieldException("contenidoComentario");
			}

			if ((entity.getContenidoComentario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getContenidoComentario(), 10485760) == false)) {
				throw new ZMessManager().new NotValidFormatException("contenidoComentario");
			}

			if ((entity.getTituloComentario() != null)
					&& (Utilities.checkWordAndCheckWithlength(entity.getTituloComentario(), 2500) == false)) {
				throw new ZMessManager().new NotValidFormatException("tituloComentario");
			}

			Parametro parametro = parametroLogic.getParametroPorDescripcion("editorVacio");
			String valorVacioEditor = parametro.getValorParametro();
			if (entity.getContenidoComentario().trim().equals(valorVacioEditor.trim()) == true) {
				throw new Exception("Debe ingresar un valor en el editor de texto matemÃ¡tico");
			}

			if (entity.getUsuario().getUsuaId() == null) {
				throw new ZMessManager().new EmptyFieldException("usuaId_Usuario");
			}

			comentarioDAO.update(entity);

			log.debug("update Comentario successful");
		} catch (Exception e) {
			log.error("update Comentario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<ComentarioDTO> getDataComentario() throws Exception {
		try {
			List<Comentario> comentario = comentarioDAO.findAll();

			List<ComentarioDTO> comentarioDTO = new ArrayList<ComentarioDTO>();

			for (Comentario comentarioTmp : comentario) {
				ComentarioDTO comentarioDTO2 = new ComentarioDTO();

				comentarioDTO2.setComenId(comentarioTmp.getComenId());
				comentarioDTO2.setContenidoComentario((comentarioTmp.getContenidoComentario() != null)
						? comentarioTmp.getContenidoComentario() : null);
				comentarioDTO2.setTituloComentario(
						(comentarioTmp.getTituloComentario() != null) ? comentarioTmp.getTituloComentario() : null);

				if (comentarioTmp.getComentario() != null) {
					comentarioDTO2.setComenId_Comentario(comentarioTmp.getComentario().getComenId());
				} else {
					comentarioDTO2.setComenId_Comentario(null);
				}

				if (comentarioTmp.getUsuario() != null) {
					comentarioDTO2.setUsuaId_Usuario(comentarioTmp.getUsuario().getUsuaId());
				} else {
					comentarioDTO2.setUsuaId_Usuario(null);
				}

				comentarioDTO.add(comentarioDTO2);
			}

			return comentarioDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Comentario getComentario(Long comenId) throws Exception {
		log.debug("getting Comentario instance");

		Comentario entity = null;

		try {
			entity = comentarioDAO.findById(comenId);
		} catch (Exception e) {
			log.error("get Comentario failed", e);
			throw new ZMessManager().new FindingException("Comentario");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<Comentario> findPageComentario(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {
		List<Comentario> entity = null;

		try {
			entity = comentarioDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Comentario Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberComentario() throws Exception {
		Long entity = null;

		try {
			entity = comentarioDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Comentario Count");
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
	public List<Comentario> findByCriteria(Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception {
		List<Comentario> list = new ArrayList<Comentario>();
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
			list = comentarioDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void hacerPregunta(Comentario pregunta) throws Exception {
		log.info("Guardando pregunta, fecha/hora: " + new Date());

		try {
			if (pregunta.getTituloComentario().trim().equals("") == true) {
				throw new Exception("Debe ingresar un valor para Título pregunta");
			}

			if (pregunta.getContenidoComentario().trim().equals("") == true) {
				throw new Exception("Debe ingresar un valor para Contenido Pregunta");
			}

			Parametro parametro = parametroLogic.getParametroPorDescripcion("editorVacio");
			String valorVacioEditor = parametro.getValorParametro();
			if (pregunta.getContenidoComentario().trim().equals(valorVacioEditor.trim()) == true) {
				throw new Exception("Debe ingresar un valor en el editor de texto matemático");
			}

			if (pregunta.getContenidoComentario().trim().length() >= 10485760) {
				throw new Exception("Ha superado el límite de escritura en el editor");
			}
			comentarioDAO.save(pregunta);

			log.info("Pregunta registrada, fecha/hora: " + new Date());
		} catch (Exception e) {
			log.error("Error registrando una pregunta", e);
			throw e;
		} finally {
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ComentarioDTO> obtenerLasPreguntasDelForo() throws Exception {
		try {
			List<ComentarioDTO> comentariosDTO = new ArrayList<ComentarioDTO>();
			List<Comentario> comentarios = comentarioDAO.listaPreguntasForoOrdenadosDelMasRecienteAlMasAntiguo();

			for (Comentario comentario : comentarios) {
				ComentarioDTO comentarioDTO = new ComentarioDTO();
				comentarioDTO.setComenId(comentario.getComenId());
				comentarioDTO.setContenidoComentario(comentario.getContenidoComentario());
				comentarioDTO.setTituloComentario(comentario.getTituloComentario());
				comentarioDTO.setCantidadRespuestas(
						comentarioDAO.cantidadDeRespuestasDeUnaPreguntaEnElForoDadoIdPregunta(comentario.getComenId()));
				Usuario usuario = logicUsuario2.getUsuario(comentario.getUsuario().getUsuaId());
				comentarioDTO.setUsuaId_Usuario(usuario.getUsuaId());
				comentarioDTO
						.setNombreUsuarioQueComenta(usuario.getNombreUsuario() + " " + usuario.getApellidoUsuario());
				comentariosDTO.add(comentarioDTO);
			}

			return comentariosDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public StreamedContent getStreamedContent(ComentarioDTO comentarioDTO) throws Exception {
		try {
			log.info("Obteniendo StreamedContent a las: " + new Date());
			Parametro ruta = parametroLogic.getParametroPorDescripcion("rutaImagenPreguntaForo");
			String rutaString = ruta.getValorParametro();

			// Aquí se debe poner la ruta en un string

			File fInput = new File(rutaString + "input.html");
			FileWriter fw = new FileWriter(fInput);
			fw.write(comentarioDTO.getContenidoComentario());
			fw.close();

			File fOutput = new File(rutaString + "comentario.jpg");

			Converter converter = Converter.getInstance();

			MutableLayoutContext params = new LayoutContextImpl(LayoutContextImpl.getDefaultLayoutContext());
			params.setParameter(Parameter.MATHSIZE, 25f);

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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void responderPregunta(Comentario respuesta) throws Exception {
		log.info("Guardando respuesta, fecha/hora: " + new Date());

		try {
			if (respuesta.getTituloComentario().trim().equals("") == true) {
				throw new Exception("Debe ingresar un valor para Título respuesta");
			}

			if (respuesta.getContenidoComentario().trim().equals("") == true) {
				throw new Exception("Debe ingresar un valor para Contenido Pregunta");
			}

			Comentario pregunta = comentarioDAO.findById(respuesta.getComentario().getComenId());

			if (pregunta == null) {
				throw new Exception("La pregunta no puede ser nula");
			}

			Parametro parametro = parametroLogic.getParametroPorDescripcion("editorVacio");
			String valorVacioEditor = parametro.getValorParametro();
			if (respuesta.getContenidoComentario().trim().equals(valorVacioEditor.trim()) == true) {
				throw new Exception("Debe ingresar un valor en el editor de texto matemático");
			}
			if (respuesta.getContenidoComentario().trim().length() >= 10485760) {
				throw new Exception("Ha superado el límite de escritura en el editor");
			}
			comentarioDAO.save(respuesta);

			log.info("Respuesta registrada, fecha/hora: " + new Date());
		} catch (Exception e) {
			log.error("Error registrando una respuesta del foro", e);
			throw e;
		} finally {
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<ComentarioDTO> obtenerLasRespuestasAUnaPreguntaDelForo(Long idPregunta) throws Exception {
		try {
			List<ComentarioDTO> comentariosDTO = new ArrayList<ComentarioDTO>();
			List<Comentario> comentarios = comentarioDAO
					.listaRespuestasForoAUnaPreguntaOrdenadosDelMasRecienteAlMasAntiguo(idPregunta);

			for (Comentario comentario : comentarios) {
				ComentarioDTO comentarioDTO = new ComentarioDTO();
				comentarioDTO.setComenId(comentario.getComenId());
				comentarioDTO.setContenidoComentario(comentario.getContenidoComentario());
				comentarioDTO.setTituloComentario(comentario.getTituloComentario());
				Usuario usuario = logicUsuario2.getUsuario(comentario.getUsuario().getUsuaId());
				comentarioDTO.setUsuaId_Usuario(usuario.getUsuaId());
				comentarioDTO
						.setNombreUsuarioQueComenta(usuario.getNombreUsuario() + " " + usuario.getApellidoUsuario());
				comentariosDTO.add(comentarioDTO);
			}

			return comentariosDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ComentarioDTO> listaPreguntasForoDadoIdUsuario(Long idUsuario) throws Exception {
		if (idUsuario == null) {
			throw new Exception("El id del usuario no puede ser nulo");
		}
		Usuario usuario = logicUsuario2.getUsuario(idUsuario);
		if (usuario == null) {
			throw new Exception("No existe un usuario con Id " + idUsuario);
		}

		try {
			List<ComentarioDTO> comentariosDTO = new ArrayList<ComentarioDTO>();
			List<Comentario> comentarios = comentarioDAO.listaPreguntasForoDadoIdUsuario(idUsuario);

			for (Comentario comentario : comentarios) {
				ComentarioDTO comentarioDTO = new ComentarioDTO();
				comentarioDTO.setComenId(comentario.getComenId());
				comentarioDTO.setContenidoComentario(comentario.getContenidoComentario());
				comentarioDTO.setTituloComentario(comentario.getTituloComentario());
				comentarioDTO.setCantidadRespuestas(
						comentarioDAO.cantidadDeRespuestasDeUnaPreguntaEnElForoDadoIdPregunta(comentario.getComenId()));
				comentarioDTO.setUsuaId_Usuario(usuario.getUsuaId());
				comentarioDTO
						.setNombreUsuarioQueComenta(usuario.getNombreUsuario() + " " + usuario.getApellidoUsuario());
				comentariosDTO.add(comentarioDTO);
			}

			return comentariosDTO;
		} catch (Exception e) {
			throw e;
		}

	}
}
