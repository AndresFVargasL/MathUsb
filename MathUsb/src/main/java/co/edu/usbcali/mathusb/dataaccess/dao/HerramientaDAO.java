package co.edu.usbcali.mathusb.dataaccess.dao;

import co.edu.usbcali.mathusb.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.mathusb.modelo.Herramienta;
import co.edu.usbcali.mathusb.modelo.TipoHerramienta;

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
 * Herramienta entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Herramienta
 */
@Scope("singleton")
@Repository("HerramientaDAO")
public class HerramientaDAO extends HibernateDaoImpl<Herramienta, Long> implements IHerramientaDAO {
	private static final Logger log = LoggerFactory.getLogger(HerramientaDAO.class);
	@Resource
	private SessionFactory sessionFactory;

	public static IHerramientaDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IHerramientaDAO) ctx.getBean("HerramientaDAO");
	}

	@Override
	public List<Herramienta> herramientasOrdenadasPorFecha() {
		String hql = "SELECT h "
				+ "FROM Herramienta h "
				+ "WHERE h.estadoRegistro = 'A' "
				+ "ORDER BY h.fechaPublicacion DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Herramienta> listaHerramientas = query.list();
		return listaHerramientas;
	}

	@Override
	public List<Herramienta> herramientasDelUsuarioOrdenadasPorFecha(String usuLogin) {
		String hql = "SELECT h " + "FROM Herramienta h, Usuario u " + "WHERE u.usuaId = h.usuario.usuaId AND "
				+ "u.emailUsuario = '" + usuLogin + "' " + "ORDER BY h.fechaPublicacion DESC";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Herramienta> listaHerramientas = query.list();
		return listaHerramientas;
	}

}
