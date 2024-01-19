package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Evenimente;
import app.repository.EvenimenteRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class EvenimenteRepositoryImpl implements EvenimenteRepository {
    @Override
    public Evenimente save(Evenimente entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idClientSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idClientSaved);
    }

    @Override
    public Evenimente update(Evenimente entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();

        return findById(entity.getId());
    }

    @Override
    public Evenimente findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // HQL - Hibernate Query Language, but use named query instead to reuse them
        // Query query = session.createQuery("from Adresa where id=:id");
        // query.setParameter("id", id);

        TypedQuery<Evenimente> query = session.getNamedQuery("findEvenimentById");
        query.setParameter("id", id);

        Evenimente event;
        try {
            event = (Evenimente) query.getSingleResult();
        } catch (NoResultException e) {
            event = null;
        }

        transaction.commit();
        session.close();

        return event;
    }

    @Override
    public List<Evenimente> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Evenimente> query = session.createNamedQuery("findAllEvenimente", Evenimente.class);
        List<Evenimente> event = query.getResultList();

        transaction.commit();
        session.close();
        return event;
    }

    @Override
    public boolean delete(Evenimente entity) {
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

        Evenimente eventToDel= session.get(Evenimente.class, id);
        if(eventToDel != null)
            session.delete(eventToDel);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }
    @Override
    public Evenimente findByName(String name) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Used a Named Query - best solution
        TypedQuery<Evenimente> query = session.getNamedQuery("findEvenimentByName");
        query.setParameter("name", name);
        Evenimente event;
        try {
            event = query.getSingleResult();
        } catch (NoResultException e) {
            event = null;
        }
        transaction.commit();
        session.close();

        return event;
    }
}
