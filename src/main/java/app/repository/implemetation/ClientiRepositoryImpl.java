package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Clienti;
import app.repository.ClientiRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientiRepositoryImpl implements ClientiRepository {
    @Override
    public Clienti save(Clienti entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idClientSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idClientSaved);
    }

    @Override
    public Clienti update(Clienti entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();

        return findById(entity.getId());
    }

    @Override
    public Clienti findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // HQL - Hibernate Query Language, but use named query instead to reuse them
        // Query query = session.createQuery("from Adresa where id=:id");
        // query.setParameter("id", id);

        TypedQuery<Clienti> query = session.getNamedQuery("findClientById");
        query.setParameter("id", id);

        Clienti client;
        try {
            client = (Clienti) query.getSingleResult();
        } catch (NoResultException e) {
            client = null;
        }

        transaction.commit();
        session.close();

        return client;
    }

    @Override
    public List<Clienti> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Clienti> query = session.createNamedQuery("findAllClienti", Clienti.class);
        List<Clienti> clienti = query.getResultList();

        transaction.commit();
        session.close();
        return clienti;
    }

    @Override
    public boolean delete(Clienti entity) {
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

        Clienti clientToDel= session.get(Clienti.class, id);
        if(clientToDel != null)
            session.delete(clientToDel);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }
    @Override
    public Clienti findByName(String name) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Used a Named Query - best solution
        TypedQuery<Clienti> query = session.getNamedQuery("findClientByName");
        query.setParameter("name", name);
        Clienti client;
        try {
            client = query.getSingleResult();
        } catch (NoResultException e) {
            client = null;
        }
        transaction.commit();
        session.close();

        return client;
    }
}
