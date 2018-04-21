package co.edu.usbcali.mathusb.dataaccess.dao;

import co.edu.usbcali.mathusb.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.mathusb.modelo.Asignatura;
import co.edu.usbcali.mathusb.modelo.Grupo;

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
 * Grupo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Grupo
 */
@Scope("singleton")
@Repository("GrupoDAO")
public class GrupoDAO extends HibernateDaoImpl<Grupo, Long> implements IGrupoDAO {
    private static final Logger log = LoggerFactory.getLogger(GrupoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IGrupoDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IGrupoDAO) ctx.getBean("GrupoDAO");
    }

	@Override
	public List<Grupo> traerGruposDeDocentes() {
		String hql = "SELECT g "
				+ "FROM Grupo g, GrupoUsuario gu, Usuario u, TipoUsuario tu "
				+ "WHERE g.grupId = gu.grupo.grupId and "
				+ "gu.usuario.usuaId = u.usuaId and "
				+ "u.tipoUsuario.tiusId = tu.tiusId and "
				+ "tu.nombreCorto = 'DOC' "
				+ "ORDER BY g.descripcionGrupo ASC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Grupo> losGruposDeDocentes = query.list();
		return losGruposDeDocentes;
	}

	@Override
	public List<Grupo> listaDeGruposDelUsuario(Long idUsuario) {
		String hql = "SELECT g "
				+ "FROM Grupo g, GrupoUsuario gu "
				+ "WHERE g.grupId = gu.grupo.grupId and "
				+ "gu.usuario.usuaId = '"+idUsuario+"' "
				+ "ORDER BY g.descripcionGrupo ASC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Grupo> losGruposDelUsuario = query.list();
		return losGruposDelUsuario;
	}

	@Override
	public Grupo obtenerGrupoDadoIdUsuarioYDescripcion(Long idUsuario, String descripcion) {
		String hql = "SELECT g "
				+ "FROM Grupo g, GrupoUsuario gu "
				+ "WHERE g.grupId = gu.grupo.grupId AND "
				+ "gu.usuario.usuaId = '"+idUsuario+"' AND "
				+ "g.descripcionGrupo = '"+descripcion+"'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Grupo grupo = (Grupo) query.uniqueResult();
		
		return grupo;
	}
}
