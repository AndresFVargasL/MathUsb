package co.edu.usbcali.mathusb.dataaccess.dao;

import co.edu.usbcali.mathusb.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.mathusb.modelo.EvaPregRes;

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
 * EvaPregRes entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.EvaPregRes
 */
@Scope("singleton")
@Repository("EvaPregResDAO")
public class EvaPregResDAO extends HibernateDaoImpl<EvaPregRes, Long>
    implements IEvaPregResDAO {
    private static final Logger log = LoggerFactory.getLogger(EvaPregResDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IEvaPregResDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IEvaPregResDAO) ctx.getBean("EvaPregResDAO");
    }

	@Override
	public EvaPregRes obtenerEvaPregResDadoIdEvalYIdPreg(Long idEval, Long idPreg) {
		String hql = "SELECT epr FROM EvaPregRes epr "
				+ "WHERE epr.evaluacion.evalId = '"+idEval+"' AND "
						+ "epr.pregunta.pregId = '"+idPreg+"' ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		EvaPregRes evaPregRes = (EvaPregRes) query.uniqueResult();
		return evaPregRes;
	}
}
