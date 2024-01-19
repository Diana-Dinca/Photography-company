package app.service.implementation;

import app.configuration.HibernateConfiguration;
import app.model.Clienti;
import app.model.Evenimente;
import app.model.Fotografi;
import app.model.Locatii;
import app.repository.EvenimenteRepository;
import app.repository.FotografiRepository;
import app.repository.LocatiiRepository;
import app.service.EvenimenteService;
import app.single_point_access.RepositorySinglePointAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class EvenimenteServiceImpl implements EvenimenteService {

    private EvenimenteRepository evenimenteRepository = RepositorySinglePointAccess.getEvenimenteRepository();
    private FotografiRepository fotografiRepository = RepositorySinglePointAccess.getFotografiRepository();
    private LocatiiRepository locatiiRepository = RepositorySinglePointAccess.getLocatiiRepository();


    @Override
    public Evenimente save(Evenimente user) {
        return evenimenteRepository.save(user);
    }

    @Override
    public Evenimente update(Evenimente user) {
        return evenimenteRepository.update(user);
    }

    @Override
    public List<Evenimente> findAll() {
        return evenimenteRepository.findAll();
    }

    @Override
    public Evenimente findById(Integer id) {
        return evenimenteRepository.findById(id);
    }

    @Override
    public boolean delete(Evenimente user) {
        return evenimenteRepository.delete(user);
    }

    @Override
    public boolean deleteById(Integer id) {
        return evenimenteRepository.deleteById(id);
    }

    @Override
    public Evenimente findByName(String name) {return evenimenteRepository.findByName(name);}

    @Override
    public void createEvent(Clienti client, String numeFotograf, Integer idLocatie, Date dataEvet) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Evenimente event = new Evenimente();
            event.setClient(client);
            event.setDate(dataEvet);
            Fotografi fotograf = fotografiRepository.findByName(numeFotograf);
            event.setFotograf(fotograf);
            Locatii locatii= locatiiRepository.findById(idLocatie);
            event.setLocatie(locatii);
            evenimenteRepository.save(event);
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

   /* @Override
    public void createReservation(Clienti user, Fotografi movie, Date date) {
        Evenimente reservation = new Evenimente();
        reservation.setClient(user);
        reservation.setMovie(movie);
        reservation.setDate(date);

        if (user.getReservations() == null) {
            user.setReservations(new ArrayList<>());
        }

        user.getReservations().add(reservation);
        userRepository.update(user);
    }*/


}
