package co.edu.usbcali.mathusb.dataaccess.dao;

import co.edu.usbcali.mathusb.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.mathusb.modelo.Pregunta;

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
 * Pregunta entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Pregunta
 */
@Scope("singleton")
@Repository("PreguntaDAO")
public class PreguntaDAO extends HibernateDaoImpl<Pregunta, Long>
    implements IPreguntaDAO {
    private static final Logger log = LoggerFactory.getLogger(PreguntaDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPreguntaDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IPreguntaDAO) ctx.getBean("PreguntaDAO");
    }

	@Override
	public List<Pregunta> consultarPreguntaDadoIdEval(Long evalId) {
		String hql = "SELECT p "
				+ "FROM Pregunta p, EvaPregRes epr "
				+ "WHERE p.pregId = epr.pregunta.pregId AND "
				+ "epr.evaluacion.evalId = '"+evalId+"' ";
		
		Query query = createQuery(hql);
		return query.list();
	}

	@Override
	public List<Pregunta> consultarPreguntasDadoIdTema(Long temaId) {
		String hql = "SELECT p "
				+ "FROM Pregunta p "
				+ "WHERE p.tema.temaId = '"+temaId+"' ";
		
		Query query = createQuery(hql);
		
		return query.list();
	}
}
