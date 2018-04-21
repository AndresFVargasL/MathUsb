package co.edu.usbcali.mathusb.dataaccess.dao;

import co.edu.usbcali.mathusb.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.mathusb.modelo.Comentario;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

/**
 * A data access object (DAO) providing persistence and search support for
 * Comentario entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @see lidis.Comentario
 */
@Scope("singleton")
@Repository("ComentarioDAO")
public class ComentarioDAO extends HibernateDaoImpl<Comentario, Long> implements IComentarioDAO {
	private static final Logger log = LoggerFactory.getLogger(ComentarioDAO.class);
	@Resource
	private SessionFactory sessionFactory;

	public static IComentarioDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IComentarioDAO) ctx.getBean("ComentarioDAO");
	}

	@Override
	public List<Comentario> listaPreguntasForoOrdenadosDelMasRecienteAlMasAntiguo() {
		String hql = "SELECT c " + "FROM Comentario c " + "WHERE c.comentario.comenId IS NULL "
				+ "ORDER BY c.comenId DESC ";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		return query.list();
	}

	@Override
	public List<Comentario> listaRespuestasForoAUnaPreguntaOrdenadosDelMasRecienteAlMasAntiguo(Long idPregunta) {
		String hql = "SELECT c " + "FROM Comentario c " + "WHERE c.comentario.comenId = '" + idPregunta + "' "
				+ "ORDER BY c.comenId DESC ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		return query.list();
	}

	@Override
	public Long cantidadDeRespuestasDeUnaPreguntaEnElForoDadoIdPregunta(Long idPregunta) {
		String hql = "SELECT COUNT(c.comenId) "
				+ "FROM Comentario c "
				+ "WHERE c.comentario.comenId = '"+idPregunta+"' ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Long cantidadRespuestas = (Long)query.uniqueResult();
		
		
		return cantidadRespuestas;
	}

	@Override
	public List<Comentario> listaPreguntasForoDadoIdUsuario(Long idUsuario) {
		String hql = "SELECT c " + "FROM Comentario c " + "WHERE c.comentario.comenId IS NULL and c.usuario.usuaId = '"+idUsuario+"' "
				+ "ORDER BY c.comenId DESC ";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		return query.list();

	}
}
