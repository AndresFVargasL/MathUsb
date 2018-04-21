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
import co.edu.usbcali.mathusb.utilities.Constantes.REPORT_OUTPUT_TYPE;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
public interface IBusinessDelegatorView {
    public List<Asignatura> getAsignatura() throws Exception;

    public void saveAsignatura(Asignatura entity) throws Exception;

    public void deleteAsignatura(Asignatura entity) throws Exception;

    public void updateAsignatura(Asignatura entity) throws Exception;

    public Asignatura getAsignatura(Long asigId) throws Exception;

    public List<Asignatura> findByCriteriaInAsignatura(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Asignatura> findPageAsignatura(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAsignatura() throws Exception;

    public List<AsignaturaDTO> getDataAsignatura() throws Exception;

    public List<Comentario> getComentario() throws Exception;

    public void saveComentario(Comentario entity) throws Exception;

    public void deleteComentario(Comentario entity) throws Exception;

    public void updateComentario(Comentario entity) throws Exception;

    public Comentario getComentario(Long comenId) throws Exception;

    public List<Comentario> findByCriteriaInComentario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Comentario> findPageComentario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberComentario() throws Exception;

    public List<ComentarioDTO> getDataComentario() throws Exception;

    public List<DetalleEvaluacion> getDetalleEvaluacion()
        throws Exception;

    public void saveDetalleEvaluacion(DetalleEvaluacion entity)
        throws Exception;

    public void deleteDetalleEvaluacion(DetalleEvaluacion entity)
        throws Exception;

    public void updateDetalleEvaluacion(DetalleEvaluacion entity)
        throws Exception;

    public DetalleEvaluacion getDetalleEvaluacion(Long deevId)
        throws Exception;

    public List<DetalleEvaluacion> findByCriteriaInDetalleEvaluacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<DetalleEvaluacion> findPageDetalleEvaluacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberDetalleEvaluacion() throws Exception;

    public List<DetalleEvaluacionDTO> getDataDetalleEvaluacion()
        throws Exception;

    public List<Evaluacion> getEvaluacion() throws Exception;

    public void saveEvaluacion(Evaluacion entity) throws Exception;

    public void deleteEvaluacion(Evaluacion entity) throws Exception;

    public void updateEvaluacion(Evaluacion entity) throws Exception;

    public Evaluacion getEvaluacion(Long evalId) throws Exception;

    public List<Evaluacion> findByCriteriaInEvaluacion(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Evaluacion> findPageEvaluacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEvaluacion() throws Exception;

    public List<EvaluacionDTO> getDataEvaluacion() throws Exception;

    public List<EvaPregRes> getEvaPregRes() throws Exception;

    public void saveEvaPregRes(EvaPregRes entity) throws Exception;

    public void deleteEvaPregRes(EvaPregRes entity) throws Exception;

    public void updateEvaPregRes(EvaPregRes entity) throws Exception;

    public EvaPregRes getEvaPregRes(Long evprId) throws Exception;

    public List<EvaPregRes> findByCriteriaInEvaPregRes(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<EvaPregRes> findPageEvaPregRes(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEvaPregRes() throws Exception;

    public List<EvaPregResDTO> getDataEvaPregRes() throws Exception;

    public List<Grupo> getGrupo() throws Exception;

    public void saveGrupo(Grupo entity) throws Exception;

    public void deleteGrupo(Grupo entity) throws Exception;

    public void updateGrupo(Grupo entity) throws Exception;

    public Grupo getGrupo(Long grupId) throws Exception;

    public List<Grupo> findByCriteriaInGrupo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Grupo> findPageGrupo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberGrupo() throws Exception;

    public List<GrupoDTO> getDataGrupo() throws Exception;

    public List<GrupoUsuario> getGrupoUsuario() throws Exception;

    public void saveGrupoUsuario(GrupoUsuario entity) throws Exception;

    public void deleteGrupoUsuario(GrupoUsuario entity)
        throws Exception;

    public void updateGrupoUsuario(GrupoUsuario entity)
        throws Exception;

    public GrupoUsuario getGrupoUsuario(Long grusId) throws Exception;

    public List<GrupoUsuario> findByCriteriaInGrupoUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<GrupoUsuario> findPageGrupoUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberGrupoUsuario() throws Exception;

    public List<GrupoUsuarioDTO> getDataGrupoUsuario()
        throws Exception;

    public List<Herramienta> getHerramienta() throws Exception;

    public void saveHerramienta(Herramienta entity) throws Exception;

    public void deleteHerramienta(Herramienta entity) throws Exception;

    public void updateHerramienta(Herramienta entity) throws Exception;

    public Herramienta getHerramienta(Long herrId) throws Exception;

    public List<Herramienta> findByCriteriaInHerramienta(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Herramienta> findPageHerramienta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberHerramienta() throws Exception;

    public List<HerramientaDTO> getDataHerramienta() throws Exception;

    public List<Parametro> getParametro() throws Exception;

    public void saveParametro(Parametro entity) throws Exception;

    public void deleteParametro(Parametro entity) throws Exception;

    public void updateParametro(Parametro entity) throws Exception;

    public Parametro getParametro(Long paramId) throws Exception;

    public List<Parametro> findByCriteriaInParametro(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Parametro> findPageParametro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberParametro() throws Exception;

    public List<ParametroDTO> getDataParametro() throws Exception;

    public List<Pregunta> getPregunta() throws Exception;

    public void savePregunta(Pregunta entity) throws Exception;

    public void deletePregunta(Pregunta entity) throws Exception;

    public void updatePregunta(Pregunta entity) throws Exception;

    public Pregunta getPregunta(Long pregId) throws Exception;

    public List<Pregunta> findByCriteriaInPregunta(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Pregunta> findPagePregunta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPregunta() throws Exception;

    public List<PreguntaDTO> getDataPregunta() throws Exception;

    public List<Respuesta> getRespuesta() throws Exception;

    public void saveRespuesta(Respuesta entity) throws Exception;

    public void deleteRespuesta(Respuesta entity) throws Exception;

    public void updateRespuesta(Respuesta entity) throws Exception;

    public Respuesta getRespuesta(Long respId) throws Exception;

    public List<Respuesta> findByCriteriaInRespuesta(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Respuesta> findPageRespuesta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRespuesta() throws Exception;

    public List<RespuestaDTO> getDataRespuesta() throws Exception;

    public List<Tema> getTema() throws Exception;

    public void saveTema(Tema entity) throws Exception;

    public void deleteTema(Tema entity) throws Exception;

    public void updateTema(Tema entity) throws Exception;

    public Tema getTema(Long temaId) throws Exception;

    public List<Tema> findByCriteriaInTema(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Tema> findPageTema(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTema() throws Exception;

    public List<TemaDTO> getDataTema() throws Exception;

    public List<TipoEvaluacion> getTipoEvaluacion() throws Exception;

    public void saveTipoEvaluacion(TipoEvaluacion entity)
        throws Exception;

    public void deleteTipoEvaluacion(TipoEvaluacion entity)
        throws Exception;

    public void updateTipoEvaluacion(TipoEvaluacion entity)
        throws Exception;

    public TipoEvaluacion getTipoEvaluacion(Long tievId)
        throws Exception;

    public List<TipoEvaluacion> findByCriteriaInTipoEvaluacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<TipoEvaluacion> findPageTipoEvaluacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoEvaluacion() throws Exception;

    public List<TipoEvaluacionDTO> getDataTipoEvaluacion()
        throws Exception;

    public List<TipoHerramienta> getTipoHerramienta() throws Exception;

    public void saveTipoHerramienta(TipoHerramienta entity)
        throws Exception;

    public void deleteTipoHerramienta(TipoHerramienta entity)
        throws Exception;

    public void updateTipoHerramienta(TipoHerramienta entity)
        throws Exception;

    public TipoHerramienta getTipoHerramienta(Long tiheId)
        throws Exception;

    public List<TipoHerramienta> findByCriteriaInTipoHerramienta(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<TipoHerramienta> findPageTipoHerramienta(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberTipoHerramienta() throws Exception;

    public List<TipoHerramientaDTO> getDataTipoHerramienta()
        throws Exception;

    public List<TipoUsuario> getTipoUsuario() throws Exception;

    public void saveTipoUsuario(TipoUsuario entity) throws Exception;

    public void deleteTipoUsuario(TipoUsuario entity) throws Exception;

    public void updateTipoUsuario(TipoUsuario entity) throws Exception;

    public TipoUsuario getTipoUsuario(Long tiusId) throws Exception;

    public List<TipoUsuario> findByCriteriaInTipoUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TipoUsuario> findPageTipoUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoUsuario() throws Exception;

    public List<TipoUsuarioDTO> getDataTipoUsuario() throws Exception;

    public List<Usuario> getUsuario() throws Exception;

    public void saveUsuario(Usuario entity) throws Exception;

    public void deleteUsuario(Usuario entity) throws Exception;

    public void updateUsuario(Usuario entity) throws Exception;

    public Usuario getUsuario(Long usuaId) throws Exception;

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsuario() throws Exception;

    public List<UsuarioDTO> getDataUsuario() throws Exception;

    public List<UsuarioEvaluacion> getUsuarioEvaluacion()
        throws Exception;

    public void saveUsuarioEvaluacion(UsuarioEvaluacion entity)
        throws Exception;

    public void deleteUsuarioEvaluacion(UsuarioEvaluacion entity)
        throws Exception;

    public void updateUsuarioEvaluacion(UsuarioEvaluacion entity)
        throws Exception;

    public UsuarioEvaluacion getUsuarioEvaluacion(Long usevId)
        throws Exception;

    public List<UsuarioEvaluacion> findByCriteriaInUsuarioEvaluacion(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<UsuarioEvaluacion> findPageUsuarioEvaluacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberUsuarioEvaluacion() throws Exception;

    public List<UsuarioEvaluacionDTO> getDataUsuarioEvaluacion()
        throws Exception;
    
    public Parametro getParametroPorDescripcion(String descripcion) throws Exception;
    
    public TipoUsuario obtenerTipoUsuarioPorNombreCorto(String nombreCorto) throws Exception;
    
    public List<Asignatura> obtenerAsignaturasOrdenadasPorNombre() throws Exception;
    
    public List<Grupo> traerGruposDeDocentes() throws Exception;
    
    public GrupoUsuario obtenerGrupoUsuarioPorIdDeGrupoYUsuarioDocente(Long grupId) throws Exception;
    
    public List<GrupoDTO> traerGruposConDocentesYNombreAsignatura() throws Exception;
    
    public void matricularEstudianteEnUnGrupo(GrupoDTO grupoDTO) throws Exception;
    
    public List<GrupoDTO> listaDeGruposDelEstudiante() throws Exception;
    
    public List<GrupoDTO> listaDeGruposDelDocente() throws Exception;
    
    public void desmatricularEstudianteEnUnGrupo(GrupoDTO grupoDTO) throws Exception;
    
    public List<UsuarioDTO> obtenerListaDeEstudiantesDadoIdGrupo(Long idGrupo) throws Exception;
    
    public void guardarNuevaEvaluacion(Evaluacion evaluacion, DetalleEvaluacion detalleEvaluacion, Usuario usuario) throws Exception;
    
    public Usuario obtenerUsuarioPorEmail(String mail) throws Exception;
    
	public List<TipoHerramienta> obtenerTiposHerramientasOrdenadasAlfabeticamente() throws Exception;
	
	public List<HerramientaDTO> herramientasOrdenadasPorFecha() throws Exception;
	
	public List<HerramientaDTO> herramientasDelUsuarioOrdenadasPorFecha() throws Exception;
	
	public void hacerPregunta(Comentario pregunta) throws Exception;
	
	public List<EvaluacionDTO> obtenerListaEvaluacionesDelDocente(Long idDocente) throws Exception;
	
	public List<ComentarioDTO> obtenerLasPreguntasDelForo() throws Exception;
	
	public StreamedContent getStreamedContent(ComentarioDTO comentarioDTO) throws Exception;
	
	public void responderPregunta(Comentario respuesta) throws Exception;
	
	public List<ComentarioDTO> obtenerLasRespuestasAUnaPreguntaDelForo(Long idPregunta) throws Exception;
	
	public DetalleEvaluacion obtenerDetalleEvaluacionDadoIdEvaluacion(Evaluacion evaluacion) throws Exception;
	
	public void actualizarUnaEvaluacion(Evaluacion evaluacion, DetalleEvaluacion detalleEvaluacion) throws Exception;
	
	public List<PreguntaDTO> consultarPreguntaDadoIdEval(Long evalId) throws Exception;
	
	public StreamedContent getStreamedContent(PreguntaDTO preguntaDTO) throws Exception;
	
	public List<EvaluacionDTO> obtenerEvaluacionesDelGrupo(Long idGrupo) throws Exception;

	public List<ComentarioDTO> listaPreguntasForoDadoIdUsuario(Long idUsuario) throws Exception;
	
	public List<TemaDTO> obtenerTemaDadoPalabraClave(String palabraClave) throws Exception;

	public List<PreguntaDTO> consultarPreguntasDadoIdTema(Long temaId) throws Exception;
	
	public List<EvaluacionDTO> obtenerEvaluacionesDelGrupoDisponiblesEnFechaHoy(Long idGrupo) throws Exception;
	
	public ByteArrayInputStream generarReporteEvaluacion(REPORT_OUTPUT_TYPE reportOutputTye, Long eval_Id) throws Exception;
}
