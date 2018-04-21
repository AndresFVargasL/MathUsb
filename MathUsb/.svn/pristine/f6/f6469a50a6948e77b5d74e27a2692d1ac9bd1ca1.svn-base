package co.edu.usbcali.mathusb.dataaccess.dao;

import co.edu.usbcali.mathusb.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.mathusb.modelo.TipoHerramienta;
import co.edu.usbcali.mathusb.modelo.Usuario;

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
 * TipoHerramienta entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.TipoHerramienta
 */
@Scope("singleton")
@Repository("TipoHerramientaDAO")
public class TipoHerramientaDAO extends HibernateDaoImpl<TipoHerramienta, Long>
    implements ITipoHerramientaDAO {
    private static final Logger log = LoggerFactory.getLogger(TipoHerramientaDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ITipoHerramientaDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (ITipoHerramientaDAO) ctx.getBean("TipoHerramientaDAO");
    }

	@Override
	public List<TipoHerramienta> obtenerTiposHerramientasOrdenadasAlfabeticamente() {
		String hql = "SELECT th FROM TipoHerramienta th ORDER BY th.descripcionHerramienta ASC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<TipoHerramienta> listaTipoHerramienta = query.list();
		return listaTipoHerramienta;
	}
}
