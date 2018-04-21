package co.edu.usbcali.mathusb.presentation.businessDelegate;

import co.edu.usbcali.mathusb.modelo.Asignatura;
import co.edu.usbcali.mathusb.modelo.Comentario;
import co.edu.usbcali.mathusb.modelo.DetalleEvaluacion;
import co.edu.usbcali.mathusb.modelo.EvaPregRes;
import co.edu.usbcali.mathusb.modelo.Evaluacion;
import co.edu.usbcali.mathusb.modelo.Grupo;
import co.edu.usbcali.mathusb.modelo.GrupoUsuario;
import co.edu.usbcali.mathusb.modelo.Herramienta;
import co.edu.usbcali.mathusb.modelo.Parametro;
import co.edu.usbcali.mathusb.modelo.Pregunta;
import co.edu.usbcali.mathusb.modelo.Respuesta;
import co.edu.usbcali.mathusb.modelo.Tema;
import co.edu.usbcali.mathusb.modelo.TipoEvaluacion;
import co.edu.usbcali.mathusb.modelo.TipoHerramienta;
import co.edu.usbcali.mathusb.modelo.TipoUsuario;
import co.edu.usbcali.mathusb.modelo.Usuario;
import co.edu.usbcali.mathusb.modelo.UsuarioEvaluacion;
import co.edu.usbcali.mathusb.modelo.control.AsignaturaLogic;
import co.edu.usbcali.mathusb.modelo.control.ComentarioLogic;
import co.edu.usbcali.mathusb.modelo.control.DetalleEvaluacionLogic;
import co.edu.usbcali.mathusb.modelo.control.EvaPregResLogic;
import co.edu.usbcali.mathusb.modelo.control.EvaluacionLogic;
import co.edu.usbcali.mathusb.modelo.control.GrupoLogic;
import co.edu.usbcali.mathusb.modelo.control.GrupoUsuarioLogic;
import co.edu.usbcali.mathusb.modelo.control.HerramientaLogic;
import co.edu.usbcali.mathusb.modelo.control.IAsignaturaLogic;
import co.edu.usbcali.mathusb.modelo.control.IComentarioLogic;
import co.edu.usbcali.mathusb.modelo.control.IDetalleEvaluacionLogic;
import co.edu.usbcali.mathusb.modelo.control.IEvaPregResLogic;
import co.edu.usbcali.mathusb.modelo.control.IEvaluacionLogic;
import co.edu.usbcali.mathusb.modelo.control.IGrupoLogic;
import co.edu.usbcali.mathusb.modelo.control.IGrupoUsuarioLogic;
import co.edu.usbcali.mathusb.modelo.control.IHerramientaLogic;
import co.edu.usbcali.mathusb.modelo.control.IParametroLogic;
import co.edu.usbcali.mathusb.modelo.control.IPreguntaLogic;
import co.edu.usbcali.mathusb.modelo.control.IRespuestaLogic;
import co.edu.usbcali.mathusb.modelo.control.ITemaLogic;
import co.edu.usbcali.mathusb.modelo.control.ITipoEvaluacionLogic;
import co.edu.usbcali.mathusb.modelo.control.ITipoHerramientaLogic;
import co.edu.usbcali.mathusb.modelo.control.ITipoUsuarioLogic;
import co.edu.usbcali.mathusb.modelo.control.IUsuarioEvaluacionLogic;
import co.edu.usbcali.mathusb.modelo.control.IUsuarioLogic;
import co.edu.usbcali.mathusb.modelo.control.ParametroLogic;
import co.edu.usbcali.mathusb.modelo.control.PreguntaLogic;
import co.edu.usbcali.mathusb.modelo.control.RespuestaLogic;
import co.edu.usbcali.mathusb.modelo.control.TemaLogic;
import co.edu.usbcali.mathusb.modelo.control.TipoEvaluacionLogic;
import co.edu.usbcali.mathusb.modelo.control.TipoHerramientaLogic;
import co.edu.usbcali.mathusb.modelo.control.TipoUsuarioLogic;
import co.edu.usbcali.mathusb.modelo.control.UsuarioEvaluacionLogic;
import co.edu.usbcali.mathusb.modelo.control.UsuarioLogic;
import co.edu.usbcali.mathusb.modelo.dto.AsignaturaDTO;
import co.edu.usbcali.mathusb.modelo.dto.ComentarioDTO;
import co.edu.usbcali.mathusb.modelo.dto.DetalleEvaluacionDTO;
import co.edu.usbcali.mathusb.modelo.dto.EvaPregResDTO;
import co.edu.usbcali.mathusb.modelo.dto.EvaluacionDTO;
import co.edu.usbcali.mathusb.modelo.dto.GrupoDTO;
import co.edu.usbcali.mathusb.modelo.dto.GrupoUsuarioDTO;
import co.edu.usbcali.mathusb.modelo.dto.HerramientaDTO;
import co.edu.usbcali.mathusb.modelo.dto.ParametroDTO;
import co.edu.usbcali.mathusb.modelo.dto.PreguntaDTO;
import co.edu.usbcali.mathusb.modelo.dto.RespuestaDTO;
import co.edu.usbcali.mathusb.modelo.dto.TemaDTO;
import co.edu.usbcali.mathusb.modelo.dto.TipoEvaluacionDTO;
import co.edu.usbcali.mathusb.modelo.dto.TipoHerramientaDTO;
import co.edu.usbcali.mathusb.modelo.dto.TipoUsuarioDTO;
import co.edu.usbcali.mathusb.modelo.dto.UsuarioDTO;
import co.edu.usbcali.mathusb.modelo.dto.UsuarioEvaluacionDTO;
import co.edu.usbcali.mathusb.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.mathusb.utilities.Constantes.REPORT_OUTPUT_TYPE;

import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private IAsignaturaLogic asignaturaLogic;
    @Autowired
    private IComentarioLogic comentarioLogic;
    @Autowired
    private IDetalleEvaluacionLogic detalleEvaluacionLogic;
    @Autowired
    private IEvaluacionLogic evaluacionLogic;
    @Autowired
    private IEvaPregResLogic evaPregResLogic;
    @Autowired
    private IGrupoLogic grupoLogic;
    @Autowired
    private IGrupoUsuarioLogic grupoUsuarioLogic;
    @Autowired
    private IHerramientaLogic herramientaLogic;
    @Autowired
    private IParametroLogic parametroLogic;
    @Autowired
    private IPreguntaLogic preguntaLogic;
    @Autowired
    private IRespuestaLogic respuestaLogic;
    @Autowired
    private ITemaLogic temaLogic;
    @Autowired
    private ITipoEvaluacionLogic tipoEvaluacionLogic;
    @Autowired
    private ITipoHerramientaLogic tipoHerramientaLogic;
    @Autowired
    private ITipoUsuarioLogic tipoUsuarioLogic;
    @Autowired
    private IUsuarioLogic usuarioLogic;
    @Autowired
    private IUsuarioEvaluacionLogic usuarioEvaluacionLogic;

    public List<Asignatura> getAsignatura() throws Exception {
        return asignaturaLogic.getAsignatura();
    }

    public void saveAsignatura(Asignatura entity) throws Exception {
        asignaturaLogic.saveAsignatura(entity);
    }

    public void deleteAsignatura(Asignatura entity) throws Exception {
        asignaturaLogic.deleteAsignatura(entity);
    }

    public void updateAsignatura(Asignatura entity) throws Exception {
        asignaturaLogic.updateAsignatura(entity);
    }

    public Asignatura getAsignatura(Long asigId) throws Exception {
        Asignatura asignatura = null;

        try {
            asignatura = asignaturaLogic.getAsignatura(asigId);
        } catch (Exception e) {
            throw e;
        }

        return asignatura;
    }

    public List<Asignatura> findByCriteriaInAsignatura(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return asignaturaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Asignatura> findPageAsignatura(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return asignaturaLogic.findPageAsignatura(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberAsignatura() throws Exception {
        return asignaturaLogic.findTotalNumberAsignatura();
    }

    public List<AsignaturaDTO> getDataAsignatura() throws Exception {
        return asignaturaLogic.getDataAsignatura();
    }

    public List<Comentario> getComentario() throws Exception {
        return comentarioLogic.getComentario();
    }

    public void saveComentario(Comentario entity) throws Exception {
        comentarioLogic.saveComentario(entity);
    }

    public void deleteComentario(Comentario entity) throws Exception {
        comentarioLogic.deleteComentario(entity);
    }

    public void updateComentario(Comentario entity) throws Exception {
        comentarioLogic.updateComentario(entity);
    }

    public Comentario getComentario(Long comenId) throws Exception {
        Comentario comentario = null;

        try {
            comentario = comentarioLogic.getComentario(comenId);
        } catch (Exception e) {
            throw e;
        }

        return comentario;
    }

    public List<Comentario> findByCriteriaInComentario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return comentarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Comentario> findPageComentario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return comentarioLogic.findPageComentario(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberComentario() throws Exception {
        return comentarioLogic.findTotalNumberComentario();
    }

    public List<ComentarioDTO> getDataComentario() throws Exception {
        return comentarioLogic.getDataComentario();
    }

    public List<DetalleEvaluacion> getDetalleEvaluacion()
        throws Exception {
        return detalleEvaluacionLogic.getDetalleEvaluacion();
    }

    public void saveDetalleEvaluacion(DetalleEvaluacion entity)
        throws Exception {
        detalleEvaluacionLogic.saveDetalleEvaluacion(entity);
    }

    public void deleteDetalleEvaluacion(DetalleEvaluacion entity)
        throws Exception {
        detalleEvaluacionLogic.deleteDetalleEvaluacion(entity);
    }

    public void updateDetalleEvaluacion(DetalleEvaluacion entity)
        throws Exception {
        detalleEvaluacionLogic.updateDetalleEvaluacion(entity);
    }

    public DetalleEvaluacion getDetalleEvaluacion(Long deevId)
        throws Exception {
        DetalleEvaluacion detalleEvaluacion = null;

        try {
            detalleEvaluacion = detalleEvaluacionLogic.getDetalleEvaluacion(deevId);
        } catch (Exception e) {
            throw e;
        }

        return detalleEvaluacion;
    }

    public List<DetalleEvaluacion> findByCriteriaInDetalleEvaluacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return detalleEvaluacionLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<DetalleEvaluacion> findPageDetalleEvaluacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return detalleEvaluacionLogic.findPageDetalleEvaluacion(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberDetalleEvaluacion() throws Exception {
        return detalleEvaluacionLogic.findTotalNumberDetalleEvaluacion();
    }

    public List<DetalleEvaluacionDTO> getDataDetalleEvaluacion()
        throws Exception {
        return detalleEvaluacionLogic.getDataDetalleEvaluacion();
    }

    public List<Evaluacion> getEvaluacion() throws Exception {
        return evaluacionLogic.getEvaluacion();
    }

    public void saveEvaluacion(Evaluacion entity) throws Exception {
        evaluacionLogic.saveEvaluacion(entity);
    }

    public void deleteEvaluacion(Evaluacion entity) throws Exception {
        evaluacionLogic.deleteEvaluacion(entity);
    }

    public void updateEvaluacion(Evaluacion entity) throws Exception {
        evaluacionLogic.updateEvaluacion(entity);
    }

    public Evaluacion getEvaluacion(Long evalId) throws Exception {
        Evaluacion evaluacion = null;

        try {
            evaluacion = evaluacionLogic.getEvaluacion(evalId);
        } catch (Exception e) {
            throw e;
        }

        return evaluacion;
    }

    public List<Evaluacion> findByCriteriaInEvaluacion(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return evaluacionLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Evaluacion> findPageEvaluacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return evaluacionLogic.findPageEvaluacion(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberEvaluacion() throws Exception {
        return evaluacionLogic.findTotalNumberEvaluacion();
    }

    public List<EvaluacionDTO> getDataEvaluacion() throws Exception {
        return evaluacionLogic.getDataEvaluacion();
    }

    public List<EvaPregRes> getEvaPregRes() throws Exception {
        return evaPregResLogic.getEvaPregRes();
    }

    public void saveEvaPregRes(EvaPregRes entity) throws Exception {
        evaPregResLogic.saveEvaPregRes(entity);
    }

    public void deleteEvaPregRes(EvaPregRes entity) throws Exception {
        evaPregResLogic.deleteEvaPregRes(entity);
    }

    public void updateEvaPregRes(EvaPregRes entity) throws Exception {
        evaPregResLogic.updateEvaPregRes(entity);
    }

    public EvaPregRes getEvaPregRes(Long evprId) throws Exception {
        EvaPregRes evaPregRes = null;

        try {
            evaPregRes = evaPregResLogic.getEvaPregRes(evprId);
        } catch (Exception e) {
            throw e;
        }

        return evaPregRes;
    }

    public List<EvaPregRes> findByCriteriaInEvaPregRes(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return evaPregResLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<EvaPregRes> findPageEvaPregRes(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return evaPregResLogic.findPageEvaPregRes(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberEvaPregRes() throws Exception {
        return evaPregResLogic.findTotalNumberEvaPregRes();
    }

    public List<EvaPregResDTO> getDataEvaPregRes() throws Exception {
        return evaPregResLogic.getDataEvaPregRes();
    }

    public List<Grupo> getGrupo() throws Exception {
        return grupoLogic.getGrupo();
    }

    public void saveGrupo(Grupo entity) throws Exception {
        grupoLogic.saveGrupo(entity);
    }

    public void deleteGrupo(Grupo entity) throws Exception {
        grupoLogic.deleteGrupo(entity);
    }

    public void updateGrupo(Grupo entity) throws Exception {
        grupoLogic.updateGrupo(entity);
    }

    public Grupo getGrupo(Long grupId) throws Exception {
        Grupo grupo = null;

        try {
            grupo = grupoLogic.getGrupo(grupId);
        } catch (Exception e) {
            throw e;
        }

        return grupo;
    }

    public List<Grupo> findByCriteriaInGrupo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return grupoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Grupo> findPageGrupo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return grupoLogic.findPageGrupo(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberGrupo() throws Exception {
        return grupoLogic.findTotalNumberGrupo();
    }

    public List<GrupoDTO> getDataGrupo() throws Exception {
        return grupoLogic.getDataGrupo();
    }

    public List<GrupoUsuario> getGrupoUsuario() throws Exception {
        return grupoUsuarioLogic.getGrupoUsuario();
    }

    public void saveGrupoUsuario(GrupoUsuario entity) throws Exception {
        grupoUsuarioLogic.saveGrupoUsuario(entity);
    }

    public void deleteGrupoUsuario(GrupoUsuario entity)
        throws Exception {
        grupoUsuarioLogic.deleteGrupoUsuario(entity);
    }

    public void updateGrupoUsuario(GrupoUsuario entity)
        throws Exception {
        grupoUsuarioLogic.updateGrupoUsuario(entity);
    }

    public GrupoUsuario getGrupoUsuario(Long grusId) throws Exception {
        GrupoUsuario grupoUsuario = null;

        try {
            grupoUsuario = grupoUsuarioLogic.getGrupoUsuario(grusId);
        } catch (Exception e) {
            throw e;
        }

        return grupoUsuario;
    }

    public List<GrupoUsuario> findByCriteriaInGrupoUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return grupoUsuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<GrupoUsuario> findPageGrupoUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return grupoUsuarioLogic.findPageGrupoUsuario(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberGrupoUsuario() throws Exception {
        return grupoUsuarioLogic.findTotalNumberGrupoUsuario();
    }

    public List<GrupoUsuarioDTO> getDataGrupoUsuario()
        throws Exception {
        return grupoUsuarioLogic.getDataGrupoUsuario();
    }

    public List<Herramienta> getHerramienta() throws Exception {
        return herramientaLogic.getHerramienta();
    }

    public void saveHerramienta(Herramienta entity) throws Exception {
        herramientaLogic.saveHerramienta(entity);
    }

    public void deleteHerramienta(Herramienta entity) throws Exception {
        herramientaLogic.deleteHerramienta(entity);
    }

    public void updateHerramienta(Herramienta entity) throws Exception {
        herramientaLogic.updateHerramienta(entity);
    }

    public Herramienta getHerramienta(Long herrId) throws Exception {
        Herramienta herramienta = null;

        try {
            herramienta = herramientaLogic.getHerramienta(herrId);
        } catch (Exception e) {
            throw e;
        }

        return herramienta;
    }

    public List<Herramienta> findByCriteriaInHerramienta(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return herramientaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Herramienta> findPageHerramienta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return herramientaLogic.findPageHerramienta(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberHerramienta() throws Exception {
        return herramientaLogic.findTotalNumberHerramienta();
    }

    public List<HerramientaDTO> getDataHerramienta() throws Exception {
        return herramientaLogic.getDataHerramienta();
    }

    public List<Parametro> getParametro() throws Exception {
        return parametroLogic.getParametro();
    }

    public void saveParametro(Parametro entity) throws Exception {
        parametroLogic.saveParametro(entity);
    }

    public void deleteParametro(Parametro entity) throws Exception {
        parametroLogic.deleteParametro(entity);
    }

    public void updateParametro(Parametro entity) throws Exception {
        parametroLogic.updateParametro(entity);
    }

    public Parametro getParametro(Long paramId) throws Exception {
        Parametro parametro = null;

        try {
            parametro = parametroLogic.getParametro(paramId);
        } catch (Exception e) {
            throw e;
        }

        return parametro;
    }

    public List<Parametro> findByCriteriaInParametro(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return parametroLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Parametro> findPageParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return parametroLogic.findPageParametro(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberParametro() throws Exception {
        return parametroLogic.findTotalNumberParametro();
    }

    public List<ParametroDTO> getDataParametro() throws Exception {
        return parametroLogic.getDataParametro();
    }

    public List<Pregunta> getPregunta() throws Exception {
        return preguntaLogic.getPregunta();
    }

    public void savePregunta(Pregunta entity) throws Exception {
        preguntaLogic.savePregunta(entity);
    }

    public void deletePregunta(Pregunta entity) throws Exception {
        preguntaLogic.deletePregunta(entity);
    }

    public void updatePregunta(Pregunta entity) throws Exception {
        preguntaLogic.updatePregunta(entity);
    }

    public Pregunta getPregunta(Long pregId) throws Exception {
        Pregunta pregunta = null;

        try {
            pregunta = preguntaLogic.getPregunta(pregId);
        } catch (Exception e) {
            throw e;
        }

        return pregunta;
    }

    public List<Pregunta> findByCriteriaInPregunta(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return preguntaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Pregunta> findPagePregunta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return preguntaLogic.findPagePregunta(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberPregunta() throws Exception {
        return preguntaLogic.findTotalNumberPregunta();
    }

    public List<PreguntaDTO> getDataPregunta() throws Exception {
        return preguntaLogic.getDataPregunta();
    }

    public List<Respuesta> getRespuesta() throws Exception {
        return respuestaLogic.getRespuesta();
    }

    public void saveRespuesta(Respuesta entity) throws Exception {
        respuestaLogic.saveRespuesta(entity);
    }

    public void deleteRespuesta(Respuesta entity) throws Exception {
        respuestaLogic.deleteRespuesta(entity);
    }

    public void updateRespuesta(Respuesta entity) throws Exception {
        respuestaLogic.updateRespuesta(entity);
    }

    public Respuesta getRespuesta(Long respId) throws Exception {
        Respuesta respuesta = null;

        try {
            respuesta = respuestaLogic.getRespuesta(respId);
        } catch (Exception e) {
            throw e;
        }

        return respuesta;
    }

    public List<Respuesta> findByCriteriaInRespuesta(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return respuestaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Respuesta> findPageRespuesta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return respuestaLogic.findPageRespuesta(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberRespuesta() throws Exception {
        return respuestaLogic.findTotalNumberRespuesta();
    }

    public List<RespuestaDTO> getDataRespuesta() throws Exception {
        return respuestaLogic.getDataRespuesta();
    }

    public List<Tema> getTema() throws Exception {
        return temaLogic.getTema();
    }

    public void saveTema(Tema entity) throws Exception {
        temaLogic.saveTema(entity);
    }

    public void deleteTema(Tema entity) throws Exception {
        temaLogic.deleteTema(entity);
    }

    public void updateTema(Tema entity) throws Exception {
        temaLogic.updateTema(entity);
    }

    public Tema getTema(Long temaId) throws Exception {
        Tema tema = null;

        try {
            tema = temaLogic.getTema(temaId);
        } catch (Exception e) {
            throw e;
        }

        return tema;
    }

    public List<Tema> findByCriteriaInTema(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return temaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Tema> findPageTema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return temaLogic.findPageTema(sortColumnName, sortAscending, startRow,
            maxResults);
    }

    public Long findTotalNumberTema() throws Exception {
        return temaLogic.findTotalNumberTema();
    }

    public List<TemaDTO> getDataTema() throws Exception {
        return temaLogic.getDataTema();
    }

    public List<TipoEvaluacion> getTipoEvaluacion() throws Exception {
        return tipoEvaluacionLogic.getTipoEvaluacion();
    }

    public void saveTipoEvaluacion(TipoEvaluacion entity)
        throws Exception {
        tipoEvaluacionLogic.saveTipoEvaluacion(entity);
    }

    public void deleteTipoEvaluacion(TipoEvaluacion entity)
        throws Exception {
        tipoEvaluacionLogic.deleteTipoEvaluacion(entity);
    }

    public void updateTipoEvaluacion(TipoEvaluacion entity)
        throws Exception {
        tipoEvaluacionLogic.updateTipoEvaluacion(entity);
    }

    public TipoEvaluacion getTipoEvaluacion(Long tievId)
        throws Exception {
        TipoEvaluacion tipoEvaluacion = null;

        try {
            tipoEvaluacion = tipoEvaluacionLogic.getTipoEvaluacion(tievId);
        } catch (Exception e) {
            throw e;
        }

        return tipoEvaluacion;
    }

    public List<TipoEvaluacion> findByCriteriaInTipoEvaluacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return tipoEvaluacionLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<TipoEvaluacion> findPageTipoEvaluacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return tipoEvaluacionLogic.findPageTipoEvaluacion(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipoEvaluacion() throws Exception {
        return tipoEvaluacionLogic.findTotalNumberTipoEvaluacion();
    }

    public List<TipoEvaluacionDTO> getDataTipoEvaluacion()
        throws Exception {
        return tipoEvaluacionLogic.getDataTipoEvaluacion();
    }

    public List<TipoHerramienta> getTipoHerramienta() throws Exception {
        return tipoHerramientaLogic.getTipoHerramienta();
    }

    public void saveTipoHerramienta(TipoHerramienta entity)
        throws Exception {
        tipoHerramientaLogic.saveTipoHerramienta(entity);
    }

    public void deleteTipoHerramienta(TipoHerramienta entity)
        throws Exception {
        tipoHerramientaLogic.deleteTipoHerramienta(entity);
    }

    public void updateTipoHerramienta(TipoHerramienta entity)
        throws Exception {
        tipoHerramientaLogic.updateTipoHerramienta(entity);
    }

    public TipoHerramienta getTipoHerramienta(Long tiheId)
        throws Exception {
        TipoHerramienta tipoHerramienta = null;

        try {
            tipoHerramienta = tipoHerramientaLogic.getTipoHerramienta(tiheId);
        } catch (Exception e) {
            throw e;
        }

        return tipoHerramienta;
    }

    public List<TipoHerramienta> findByCriteriaInTipoHerramienta(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return tipoHerramientaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<TipoHerramienta> findPageTipoHerramienta(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return tipoHerramientaLogic.findPageTipoHerramienta(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipoHerramienta() throws Exception {
        return tipoHerramientaLogic.findTotalNumberTipoHerramienta();
    }

    public List<TipoHerramientaDTO> getDataTipoHerramienta()
        throws Exception {
        return tipoHerramientaLogic.getDataTipoHerramienta();
    }

    public List<TipoUsuario> getTipoUsuario() throws Exception {
        return tipoUsuarioLogic.getTipoUsuario();
    }

    public void saveTipoUsuario(TipoUsuario entity) throws Exception {
        tipoUsuarioLogic.saveTipoUsuario(entity);
    }

    public void deleteTipoUsuario(TipoUsuario entity) throws Exception {
        tipoUsuarioLogic.deleteTipoUsuario(entity);
    }

    public void updateTipoUsuario(TipoUsuario entity) throws Exception {
        tipoUsuarioLogic.updateTipoUsuario(entity);
    }

    public TipoUsuario getTipoUsuario(Long tiusId) throws Exception {
        TipoUsuario tipoUsuario = null;

        try {
            tipoUsuario = tipoUsuarioLogic.getTipoUsuario(tiusId);
        } catch (Exception e) {
            throw e;
        }

        return tipoUsuario;
    }

    public List<TipoUsuario> findByCriteriaInTipoUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return tipoUsuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<TipoUsuario> findPageTipoUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return tipoUsuarioLogic.findPageTipoUsuario(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipoUsuario() throws Exception {
        return tipoUsuarioLogic.findTotalNumberTipoUsuario();
    }

    public List<TipoUsuarioDTO> getDataTipoUsuario() throws Exception {
        return tipoUsuarioLogic.getDataTipoUsuario();
    }

    public List<Usuario> getUsuario() throws Exception {
        return usuarioLogic.getUsuario();
    }

    public void saveUsuario(Usuario entity) throws Exception {
        usuarioLogic.saveUsuario(entity);
    }

    public void deleteUsuario(Usuario entity) throws Exception {
        usuarioLogic.deleteUsuario(entity);
    }

    public void updateUsuario(Usuario entity) throws Exception {
        usuarioLogic.updateUsuario(entity);
    }

    public Usuario getUsuario(Long usuaId) throws Exception {
        Usuario usuario = null;

        try {
            usuario = usuarioLogic.getUsuario(usuaId);
        } catch (Exception e) {
            throw e;
        }

        return usuario;
    }

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return usuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return usuarioLogic.findPageUsuario(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberUsuario() throws Exception {
        return usuarioLogic.findTotalNumberUsuario();
    }

    public List<UsuarioDTO> getDataUsuario() throws Exception {
        return usuarioLogic.getDataUsuario();
    }

    public List<UsuarioEvaluacion> getUsuarioEvaluacion()
        throws Exception {
        return usuarioEvaluacionLogic.getUsuarioEvaluacion();
    }

    public void saveUsuarioEvaluacion(UsuarioEvaluacion entity)
        throws Exception {
        usuarioEvaluacionLogic.saveUsuarioEvaluacion(entity);
    }

    public void deleteUsuarioEvaluacion(UsuarioEvaluacion entity)
        throws Exception {
        usuarioEvaluacionLogic.deleteUsuarioEvaluacion(entity);
    }

    public void updateUsuarioEvaluacion(UsuarioEvaluacion entity)
        throws Exception {
        usuarioEvaluacionLogic.updateUsuarioEvaluacion(entity);
    }

    public UsuarioEvaluacion getUsuarioEvaluacion(Long usevId)
        throws Exception {
        UsuarioEvaluacion usuarioEvaluacion = null;

        try {
            usuarioEvaluacion = usuarioEvaluacionLogic.getUsuarioEvaluacion(usevId);
        } catch (Exception e) {
            throw e;
        }

        return usuarioEvaluacion;
    }

    public List<UsuarioEvaluacion> findByCriteriaInUsuarioEvaluacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return usuarioEvaluacionLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<UsuarioEvaluacion> findPageUsuarioEvaluacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return usuarioEvaluacionLogic.findPageUsuarioEvaluacion(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberUsuarioEvaluacion() throws Exception {
        return usuarioEvaluacionLogic.findTotalNumberUsuarioEvaluacion();
    }

    public List<UsuarioEvaluacionDTO> getDataUsuarioEvaluacion()
        throws Exception {
        return usuarioEvaluacionLogic.getDataUsuarioEvaluacion();
    }
    
    public Parametro getParametroPorDescripcion(String descripcion) throws Exception {
    	return parametroLogic.getParametroPorDescripcion(descripcion);
    }

	@Override
	public TipoUsuario obtenerTipoUsuarioPorNombreCorto(String nombreCorto) throws Exception {
		return tipoUsuarioLogic.obtenerTipoUsuarioPorNombreCorto(nombreCorto);
	}

	@Override
	public List<Asignatura> obtenerAsignaturasOrdenadasPorNombre() throws Exception {
		return asignaturaLogic.obtenerAsignaturasOrdenadasPorNombre();
	}

	@Override
	public List<Grupo> traerGruposDeDocentes() throws Exception {
		return grupoLogic.traerGruposDeDocentes();
	}

	@Override
	public GrupoUsuario obtenerGrupoUsuarioPorIdDeGrupoYUsuarioDocente(Long grupId) throws Exception {
		return grupoUsuarioLogic.obtenerGrupoUsuarioPorIdDeGrupoYUsuarioDocente(grupId);
	}

	@Override
	public List<GrupoDTO> traerGruposConDocentesYNombreAsignatura() throws Exception {
		return grupoLogic.traerGruposConDocentesYNombreAsignatura();
	}

	@Override
	public void matricularEstudianteEnUnGrupo(GrupoDTO grupoDTO) throws Exception {
		grupoUsuarioLogic.matricularEstudianteEnUnGrupo(grupoDTO);
	}

	@Override
	public List<GrupoDTO> listaDeGruposDelEstudiante() throws Exception {
		return grupoLogic.listaDeGruposDelEstudiante();
	}

	@Override
	public void desmatricularEstudianteEnUnGrupo(GrupoDTO grupoDTO) throws Exception {
		grupoUsuarioLogic.desmatricularEstudianteEnUnGrupo(grupoDTO);
	}

	@Override
	public List<GrupoDTO> listaDeGruposDelDocente() throws Exception {
		return grupoLogic.listaDeGruposDelDocente();
	}

	@Override
	public List<UsuarioDTO> obtenerListaDeEstudiantesDadoIdGrupo(Long idGrupo) throws Exception {
		return usuarioLogic.obtenerListaDeEstudiantesDadoIdGrupo(idGrupo);
	}

	@Override
	public void guardarNuevaEvaluacion(Evaluacion evaluacion, DetalleEvaluacion detalleEvaluacion, Usuario usuario)
			throws Exception {
		evaluacionLogic.guardarNuevaEvaluacion(evaluacion, detalleEvaluacion, usuario);
		
	}

	@Override
	public Usuario obtenerUsuarioPorEmail(String mail) throws Exception {
		return usuarioLogic.obtenerUsuarioPorEmail(mail);
	}

	@Override
	public List<TipoHerramienta> obtenerTiposHerramientasOrdenadasAlfabeticamente() throws Exception {
		return tipoHerramientaLogic.obtenerTiposHerramientasOrdenadasAlfabeticamente();
	}

	@Override
	public List<HerramientaDTO> herramientasOrdenadasPorFecha() throws Exception {
		return herramientaLogic.herramientasOrdenadasPorFecha();
	}

	@Override
	public List<HerramientaDTO> herramientasDelUsuarioOrdenadasPorFecha() throws Exception {
		return herramientaLogic.herramientasDelUsuarioOrdenadasPorFecha();
	}

	@Override
	public void hacerPregunta(Comentario pregunta) throws Exception {
		comentarioLogic.hacerPregunta(pregunta);
	}

	@Override
	public List<EvaluacionDTO> obtenerListaEvaluacionesDelDocente(Long idDocente) throws Exception {
		return evaluacionLogic.obtenerListaEvaluacionesDelDocente(idDocente);
	}

	@Override
	public List<ComentarioDTO> obtenerLasPreguntasDelForo() throws Exception {
		return comentarioLogic.obtenerLasPreguntasDelForo();
	}

	@Override
	public StreamedContent getStreamedContent(ComentarioDTO comentarioDTO) throws Exception {
		return comentarioLogic.getStreamedContent(comentarioDTO);
	}

	@Override
	public void responderPregunta(Comentario respuesta) throws Exception {
		comentarioLogic.responderPregunta(respuesta);
	}

	@Override
	public List<ComentarioDTO> obtenerLasRespuestasAUnaPreguntaDelForo(Long idPregunta) throws Exception {
		return comentarioLogic.obtenerLasRespuestasAUnaPreguntaDelForo(idPregunta);
	}

	@Override
	public DetalleEvaluacion obtenerDetalleEvaluacionDadoIdEvaluacion(Evaluacion evaluacion) throws Exception {
		return detalleEvaluacionLogic.obtenerDetalleEvaluacionDadoIdEvaluacion(evaluacion);
	}

	@Override
	public void actualizarUnaEvaluacion(Evaluacion evaluacion, DetalleEvaluacion detalleEvaluacion) throws Exception {
		evaluacionLogic.actualizarUnaEvaluacion(evaluacion, detalleEvaluacion);
		
	}

	@Override
	public List<PreguntaDTO> consultarPreguntaDadoIdEval(Long evalId) throws Exception {
		return preguntaLogic.consultarPreguntaDadoIdEval(evalId);
	}

	@Override
	public StreamedContent getStreamedContent(PreguntaDTO preguntaDTO) throws Exception {
		return preguntaLogic.getStreamedContent(preguntaDTO);
	}

	@Override
	public List<EvaluacionDTO> obtenerEvaluacionesDelGrupo(Long idGrupo)
			throws Exception {
		return evaluacionLogic.obtenerEvaluacionesDelGrupo(idGrupo);
	}

	@Override
	public List<ComentarioDTO> listaPreguntasForoDadoIdUsuario(Long idUsuario) throws Exception {
		return comentarioLogic.listaPreguntasForoDadoIdUsuario(idUsuario);
	}

	@Override
	public List<TemaDTO> obtenerTemaDadoPalabraClave(String palabraClave) throws Exception {
		return temaLogic.obtenerTemaDadoPalabraClave(palabraClave);
	}

	@Override
	public List<PreguntaDTO> consultarPreguntasDadoIdTema(Long temaId) throws Exception {
		return preguntaLogic.consultarPreguntasDadoIdTema(temaId);
	}

	@Override
	public List<EvaluacionDTO> obtenerEvaluacionesDelGrupoDisponiblesEnFechaHoy(Long idGrupo) throws Exception {
		return evaluacionLogic.obtenerEvaluacionesDelGrupoDisponiblesEnFechaHoy(idGrupo);
	}
	
	@Override
	public ByteArrayInputStream generarReporteEvaluacion(REPORT_OUTPUT_TYPE reportOutputTye, Long eval_Id) throws Exception {
		return evaluacionLogic.generarReporteEvaluacion(reportOutputTye, eval_Id);
	}
	
}
