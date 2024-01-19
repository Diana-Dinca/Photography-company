package app.service.implementation;

import app.model.Fotografi;
import app.repository.AdresaRepository;
import app.repository.FotografiRepository;
import app.service.FotografiService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class FotografiServiceImpl implements FotografiService {

    private FotografiRepository fotografiRepository = RepositorySinglePointAccess.getFotografiRepository();
    private AdresaRepository adresaRepository = RepositorySinglePointAccess.getAdresaRepository();

    @Override
    public Fotografi save(Fotografi user) {
        return fotografiRepository.save(user);
    }

    @Override
    public Fotografi update(Fotografi user) {
        return fotografiRepository.update(user);
    }

    @Override
    public List<Fotografi> findAll() {
        return fotografiRepository.findAll();
    }

    @Override
    public Fotografi findById(Integer id) {
        return fotografiRepository.findById(id);
    }

    @Override
    public boolean delete(Fotografi user) {
        return fotografiRepository.delete(user);
    }

    @Override
    public Fotografi findByName(String name) {
        return fotografiRepository.findByName(name);
    }

    /*@Override
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
