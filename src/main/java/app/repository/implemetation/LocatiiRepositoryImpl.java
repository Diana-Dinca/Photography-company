package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Locatii;
import app.repository.LocatiiRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class LocatiiRepositoryImpl implements LocatiiRepository {
    @Override
    public Locatii save(Locatii entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idClientSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idClientSaved);
    }

    @Override
    public Locatii update(Locatii entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();

        return findById(entity.getId());
    }

    @Override
    public Locatii findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // HQL - Hibernate Query Language, but use named query instead to reuse them
        // Query query = session.createQuery("from Adresa where id=:id");
        // query.setParameter("id", id);

        TypedQuery<Locatii> query = session.getNamedQuery("findLocatieById");
        query.setParameter("id", id);

        Locatii event;
        try {
            event = (Locatii) query.getSingleResult();
        } catch (NoResultException e) {
            event = null;
        }

        transaction.commit();
        session.close();

        return event;
    }

    @Override
    public List<Locatii> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Locatii> query = session.createNamedQuery("findAllLocatii", Locatii.class);
        List<Locatii> loc = query.getResultList();

        transaction.commit();
        session.close();
        return loc;
    }

    @Override
    public boolean delete(Locatii entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getId();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }
    @Override
    public boolean deleteById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Locatii locToDel= session.get(Locatii.class, id);
        if(locToDel != null)
            session.delete(locToDel);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }
    @Override
    public Locatii findByName(String name) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Used a Named Query - best solution
        TypedQuery<Locatii> query = session.getNamedQuery("findLocatieByName");
        query.setParameter("name", name);
        Locatii foto;
        try {
            foto = query.getSingleResult();
        } catch (NoResultException e) {
            foto = null;
        }
        transaction.commit();
        session.close();

        return foto;
    }
}
