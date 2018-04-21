package co.edu.usbcali.mathusb.dataaccess.dao;

import co.edu.usbcali.mathusb.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.mathusb.modelo.Asignatura;

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
 * Asignatura entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Asignatura
 */
@Scope("singleton")
@Repository("AsignaturaDAO")
public class AsignaturaDAO extends HibernateDaoImpl<Asignatura, Long>
    implements IAsignaturaDAO {
    private static final Logger log = LoggerFactory.getLogger(AsignaturaDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IAsignaturaDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IAsignaturaDAO) ctx.getBean("AsignaturaDAO");
    }

	@Override
	public List<Asignatura> obtenerAsignaturasOrdenadasPorNombre() {
		String hql = "SELECT a FROM Asignatura a ORDER BY a.nombreAsignatura ASC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Asignatura> lasPublicacionesDelUsuario = query.list();
		return lasPublicacionesDelUsuario;
	}
}
