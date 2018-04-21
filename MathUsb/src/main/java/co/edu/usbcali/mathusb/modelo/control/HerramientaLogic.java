package co.edu.usbcali.mathusb.modelo.control;

import co.edu.usbcali.mathusb.dataaccess.dao.*;
import co.edu.usbcali.mathusb.exceptions.*;
import co.edu.usbcali.mathusb.modelo.*;
import co.edu.usbcali.mathusb.modelo.dto.HerramientaDTO;
import co.edu.usbcali.mathusb.utilities.Utilities;

import org.apache.commons.validator.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("HerramientaLogic")
public class HerramientaLogic implements IHerramientaLogic {
    private static final Logger log = LoggerFactory.getLogger(HerramientaLogic.class);

    /**
     * DAO injected by Spring that manages Herramienta entities
     *
     */
    @Autowired
    private IHerramientaDAO herramientaDAO;

    /**
    * Logic injected by Spring that manages TipoHerramienta entities
    *
    */
    @Autowired
    ITipoHerramientaLogic logicTipoHerramienta1;

    /**
    * Logic injected by Spring that manages Usuario entities
    *
    */
    @Autowired
    IUsuarioLogic logicUsuario2;

    @Transactional(readOnly = true)
    public List<Herramienta> getHerramienta() throws Exception {
        log.debug("finding all Herramienta instances");

        List<Herramienta> list = new ArrayList<Herramienta>();

        try {
            list = herramientaDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Herramienta failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Herramienta");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveHerramienta(Herramienta entity) throws Exception {
        log.info("Guardando nueva herramienta, Fecha/Hora: "+new Date());

        try {
        	if(entity.getDescripcionHerramienta().trim().equals("")==true){
            	throw new ZMessManager().new EmptyFieldException(
                        "Título o Descripción de Herramienta");	
            }
        	
        	if(entity.getValorHerramienta().trim().equals("")==true){
            	throw new ZMessManager().new EmptyFieldException(
                        "URL de la ubicación de la Herramienta");
            }
        	
        	UrlValidator urlValidator = new UrlValidator();
        	if(urlValidator.isValid(entity.getValorHerramienta())==false){
        		throw new Exception("La URL ingresada no es correcta");
        	}
        	
        	if (entity.getTipoHerramienta() == null) {
                throw new ZMessManager().new ForeignException("Tipo de Herramienta");
            }
            
            if(entity.getTipoHerramienta().getTiheId().equals(0)==true){
            	throw new Exception("Debe seleccionar un Tipo de Herramienta");
            }

            if (entity.getUsuario() == null) {
                throw new ZMessManager().new ForeignException("Usuario");
            }
          
            if (entity.getDescripcionHerramienta() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Título o Descripción de Herramienta");
            }

            if ((entity.getDescripcionHerramienta() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcionHerramienta(), 2500) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Título o Descripción de Herramienta");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Estado de registro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Estado de registro");
            }

            if (entity.getFechaPublicacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Fecha de Publicación");
            }

            if (entity.getValorHerramienta() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "URL de ubicación de la Herramienta");
            }

            if ((entity.getValorHerramienta() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getValorHerramienta(), 2500) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "URL de ubicación de la Herramienta");
            }

            if (entity.getTipoHerramienta().getTiheId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Tipo de Herramienta");
            }

            if (entity.getUsuario().getUsuaId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "Usuario");
            }

            herramientaDAO.save(entity);

            log.info("Herramienta guardada, Fecha/Hora: "+new Date());
        } catch (Exception e) {
            log.error("Error guardando una nueva herramienta", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteHerramienta(Herramienta entity) throws Exception {
        log.debug("deleting Herramienta instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Herramienta");
        }

        if (entity.getHerrId() == null) {
            throw new ZMessManager().new EmptyFieldException("herrId");
        }

        try {
            herramientaDAO.delete(entity);

            log.debug("delete Herramienta successful");
        } catch (Exception e) {
            log.error("delete Herramienta failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateHerramienta(Herramienta entity) throws Exception {
        log.debug("updating Herramienta instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Herramienta");
            }

            if (entity.getTipoHerramienta() == null) {
                throw new ZMessManager().new ForeignException("tipoHerramienta");
            }

            if (entity.getUsuario() == null) {
                throw new ZMessManager().new ForeignException("usuario");
            }

            if (entity.getDescripcionHerramienta() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "descripcionHerramienta");
            }

            if ((entity.getDescripcionHerramienta() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcionHerramienta(), 2500) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcionHerramienta");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estadoRegistro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoRegistro");
            }

            if (entity.getFechaPublicacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "fechaPublicacion");
            }

            if (entity.getHerrId() == null) {
                throw new ZMessManager().new EmptyFieldException("herrId");
            }

            if (entity.getValorHerramienta() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "valorHerramienta");
            }

            if ((entity.getValorHerramienta() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getValorHerramienta(), 2500) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "valorHerramienta");
            }

            if (entity.getTipoHerramienta().getTiheId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "tiheId_TipoHerramienta");
            }

            if (entity.getUsuario().getUsuaId() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuaId_Usuario");
            }

            herramientaDAO.update(entity);

            log.debug("update Herramienta successful");
        } catch (Exception e) {
            log.error("update Herramienta failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<HerramientaDTO> getDataHerramienta() throws Exception {
        try {
            List<Herramienta> herramienta = herramientaDAO.findAll();

            List<HerramientaDTO> herramientaDTO = new ArrayList<HerramientaDTO>();

            for (Herramienta herramientaTmp : herramienta) {
                HerramientaDTO herramientaDTO2 = new HerramientaDTO();

                herramientaDTO2.setHerrId(herramientaTmp.getHerrId());
                herramientaDTO2.setDescripcionHerramienta((herramientaTmp.getDescripcionHerramienta() != null)
                    ? herramientaTmp.getDescripcionHerramienta() : null);
                herramientaDTO2.setEstadoRegistro((herramientaTmp.getEstadoRegistro() != null)
                    ? herramientaTmp.getEstadoRegistro() : null);
                herramientaDTO2.setFechaPublicacion(herramientaTmp.getFechaPublicacion());
                herramientaDTO2.setValorHerramienta((herramientaTmp.getValorHerramienta() != null)
                    ? herramientaTmp.getValorHerramienta() : null);
                herramientaDTO2.setTiheId_TipoHerramienta((herramientaTmp.getTipoHerramienta()
                                                                         .getTiheId() != null)
                    ? herramientaTmp.getTipoHerramienta().getTiheId() : null);

                if (herramientaTmp.getUsuario() != null) {
                    herramientaDTO2.setUsuaId_Usuario(herramientaTmp.getUsuario()
                                                                    .getUsuaId());
                } else {
                    herramientaDTO2.setUsuaId_Usuario(null);
                }

                herramientaDTO.add(herramientaDTO2);
            }

            return herramientaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Herramienta getHerramienta(Long herrId) throws Exception {
        log.debug("getting Herramienta instance");

        Herramienta entity = null;

        try {
            entity = herramientaDAO.findById(herrId);
        } catch (Exception e) {
            log.error("get Herramienta failed", e);
            throw new ZMessManager().new FindingException("Herramienta");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Herramienta> findPageHerramienta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Herramienta> entity = null;

        try {
            entity = herramientaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Herramienta Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberHerramienta() throws Exception {
        Long entity = null;

        try {
            entity = herramientaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Herramienta Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<Herramienta> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Herramienta> list = new ArrayList<Herramienta>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
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
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
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
            list = herramientaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

	@Override
	@Transactional(readOnly = true)
	public List<HerramientaDTO> herramientasOrdenadasPorFecha() throws Exception {
		List<Herramienta> lasHerramientas = herramientaDAO.herramientasOrdenadasPorFecha();
		List<HerramientaDTO> lasHerramientasDTO = new ArrayList<HerramientaDTO>();
		String[] meses = new String[13];
		meses[0] = "";
		meses[1] = "Enero";
		meses[2] = "Febrero";
		meses[3] = "Marzo";
		meses[4] = "Abril";
		meses[5] = "Mayo";
		meses[6] = "Junio";
		meses[7] = "Julio";
		meses[8] = "Agosto";
		meses[9] = "Septiembre";
		meses[10] = "Octubre";
		meses[11] = "Noviembre";
		meses[12] = "Diciembre";
		for (Herramienta herramienta : lasHerramientas) {
			HerramientaDTO herramientaDTO = new HerramientaDTO();
			herramientaDTO.setDescripcionHerramienta(herramienta.getDescripcionHerramienta());
			herramientaDTO.setHerrId(herramienta.getHerrId());
			herramientaDTO.setValorHerramienta(herramienta.getValorHerramienta());
			String tipoHerramienta = logicTipoHerramienta1.getTipoHerramienta(herramienta.getTipoHerramienta().getTiheId()).getDescripcionHerramienta();
			herramientaDTO.setTipoHerramientaString(tipoHerramienta);
			Usuario usuario = logicUsuario2.getUsuario(herramienta.getUsuario().getUsuaId());
			String usuPublica = usuario.getNombreUsuario()+" "+usuario.getApellidoUsuario();
			herramientaDTO.setUsuarioQuePublico(usuPublica);
			
			SimpleDateFormat sdfDia = new SimpleDateFormat("dd");
			SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
			SimpleDateFormat sdfAnho = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdfHora = new SimpleDateFormat("hh:mm a");
			String dia = sdfDia.format(herramienta.getFechaPublicacion());
			int numMes = Integer.parseInt(sdfMes.format(herramienta.getFechaPublicacion()));
			String mes = meses[numMes];
			String anho = sdfAnho.format(herramienta.getFechaPublicacion());
			String hora = sdfHora.format(herramienta.getFechaPublicacion());
			
			String fechaHora = dia+" de "+mes+" del "+anho+ ", hora: "+hora;
			herramientaDTO.setFechaPublicacionString(fechaHora);
			lasHerramientasDTO.add(herramientaDTO);
		}
		return lasHerramientasDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<HerramientaDTO> herramientasDelUsuarioOrdenadasPorFecha() throws Exception {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		com.vortexbird.seguridad.modelo.dto.UsuarioDTO loginUsuario = (com.vortexbird.seguridad.modelo.dto.UsuarioDTO) session
				.getAttribute("usuarioDTO");
		
		Usuario docente = logicUsuario2.obtenerUsuarioPorEmail(loginUsuario.getUsu_login());
		if(docente==null){
			throw new Exception("No existe el usuario en la base de datos");
		}
		
		List<Herramienta> lasHerramientas = herramientaDAO.herramientasDelUsuarioOrdenadasPorFecha(docente.getEmailUsuario());
		List<HerramientaDTO> lasHerramientasDTO = new ArrayList<HerramientaDTO>();
		String[] meses = new String[13];
		meses[0] = "";
		meses[1] = "Enero";
		meses[2] = "Febrero";
		meses[3] = "Marzo";
		meses[4] = "Abril";
		meses[5] = "Mayo";
		meses[6] = "Junio";
		meses[7] = "Julio";
		meses[8] = "Agosto";
		meses[9] = "Septiembre";
		meses[10] = "Octubre";
		meses[11] = "Noviembre";
		meses[12] = "Diciembre";
		
		String[] diasDeSemana = new String[8];
		diasDeSemana[0] = "";
		diasDeSemana[1] = "Lunes";
		diasDeSemana[2] = "Martes";
		diasDeSemana[3] = "Miércoles";
		diasDeSemana[4] = "Jueves";
		diasDeSemana[5] = "Viernes";
		diasDeSemana[6] = "Sábado";
		diasDeSemana[7] = "Domingo";

		
		for (Herramienta herramienta : lasHerramientas) {
			HerramientaDTO herramientaDTO = new HerramientaDTO();
			herramientaDTO.setDescripcionHerramienta(herramienta.getDescripcionHerramienta());
			herramientaDTO.setHerrId(herramienta.getHerrId());
			herramientaDTO.setValorHerramienta(herramienta.getValorHerramienta());
			String tipoHerramienta = logicTipoHerramienta1.getTipoHerramienta(herramienta.getTipoHerramienta().getTiheId()).getDescripcionHerramienta();
			herramientaDTO.setTipoHerramientaString(tipoHerramienta);
			String usuPublica = docente.getNombreUsuario()+" "+docente.getApellidoUsuario();
			herramientaDTO.setUsuarioQuePublico(usuPublica);
			if(herramienta.getEstadoRegistro().trim().equals("A")==true){
				herramientaDTO.setEstadoRegistro("Activa");
			}else{
				herramientaDTO.setEstadoRegistro("Inactiva");
			}
			
			herramientaDTO.setFechaPublicacion(herramienta.getFechaPublicacion());
			
			SimpleDateFormat sdfDia = new SimpleDateFormat("dd");
			SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
			SimpleDateFormat sdfAnho = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdfHora = new SimpleDateFormat("hh:mm a");
			SimpleDateFormat sdfDiaDeSemana = new SimpleDateFormat("u");
			
			String dia = sdfDia.format(herramienta.getFechaPublicacion());
			int numMes = Integer.parseInt(sdfMes.format(herramienta.getFechaPublicacion()));
			String mes = meses[numMes];
			String anho = sdfAnho.format(herramienta.getFechaPublicacion());
			String hora = sdfHora.format(herramienta.getFechaPublicacion());
			int numDiaDeSemana = Integer.parseInt(sdfDiaDeSemana.format(herramienta.getFechaPublicacion()));
			String diaDeSemana = diasDeSemana[numDiaDeSemana];
			
			String fechaHora = diaDeSemana+", "+ dia+" de "+mes+" del "+anho+ ", hora: "+hora;
			herramientaDTO.setFechaPublicacionString(fechaHora);
			lasHerramientasDTO.add(herramientaDTO);
		}
		return lasHerramientasDTO;
	}
}
