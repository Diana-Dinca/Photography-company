package app.service.implementation;

import app.model.Adresa;
import app.repository.AdresaRepository;
import app.service.AdresaService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class AdresaServiceImpl implements AdresaService {
    private AdresaRepository adresaRepository = RepositorySinglePointAccess.getAdresaRepository();

    @Override
    public Adresa save(Adresa adr) {
        return adresaRepository.save(adr);
    }

    @Override
    public Adresa update(Adresa adr) {
        return adresaRepository.update(adr);
    }

    @Override
    public List<Adresa> findAll() {
        return adresaRepository.findAll();
    }

    @Override
    public Adresa findById(Integer id) {
        return adresaRepository.findById(id);
    }

    @Override
    public boolean delete(Adresa adr) {
        return adresaRepository.delete(adr);
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
    }
    */

}
