package core.basesyntax.dao.impl;

import core.basesyntax.HibernateUtil;
import core.basesyntax.dao.SmileDao;
import core.basesyntax.model.Smile;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SmileDaoImpl implements SmileDao {
    private final SessionFactory sessionFactory;

    public SmileDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SmileDaoImpl() {
        this(HibernateUtil.getSessionFactory());
    }

    @Override
    public Smile create(Smile smile) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(smile);
        tx.commit();
        session.close();
        return smile;
    }

    @Override
    public Smile get(Long id) {
        Session session = sessionFactory.openSession();
        Smile smile = session.get(Smile.class, id);
        session.close();
        return smile;
    }

    @Override
    public List<Smile> getAll() {
        Session session = sessionFactory.openSession();
        List<Smile> list = session.createQuery("from Smile", Smile.class)
                .getResultList();
        session.close();
        return list;
    }
}

