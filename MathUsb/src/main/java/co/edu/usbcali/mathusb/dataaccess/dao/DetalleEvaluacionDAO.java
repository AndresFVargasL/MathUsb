package co.edu.usbcali.mathusb.dataaccess.dao;

import co.edu.usbcali.mathusb.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.mathusb.modelo.DetalleEvaluacion;

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
 * DetalleEvaluacion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.DetalleEvaluacion
 */
@Scope("singleton")
@Repository("DetalleEvaluacionDAO")
public class DetalleEvaluacionDAO extends HibernateDaoImpl<DetalleEvaluacion, Long>
    implements IDetalleEvaluacionDAO {
    private static final Logger log = LoggerFactory.getLogger(DetalleEvaluacionDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IDetalleEvaluacionDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IDetalleEvaluacionDAO) ctx.getBean("DetalleEvaluacionDAO");
    }

	@Override
	public DetalleEvaluacion obtenerDetalleEvaluacionDadoIdEval(Long idEvaluacion) {
		String hql = "SELECT de "
				+ "FROM DetalleEvaluacion de, Evaluacion e "
				+ "WHERE de.evaluacion.evalId = e.evalId AND "
				+ "e.evalId = '"+idEvaluacion+"' ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for(int i =0; i<query.list().size();i++){
			DetalleEvaluacion detalleEvaluacion = (DetalleEvaluacion) query.list().get(i);
			return detalleEvaluacion;
		}
		return null;
	}
}
