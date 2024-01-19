package app.service.implementation;

import app.model.Clienti;
import app.repository.AdresaRepository;
import app.repository.ClientiRepository;
import app.service.ClientiService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class ClientiServiceImpl implements ClientiService {

    private ClientiRepository clientiRepository = RepositorySinglePointAccess.getClientiRepository();
    private AdresaRepository adresaRepository = RepositorySinglePointAccess.getAdresaRepository();

    @Override
    public Clienti save(Clienti user) {
        return clientiRepository.save(user);
    }

    @Override
    public Clienti update(Clienti user) {
        return clientiRepository.update(user);
    }

    @Override
    public List<Clienti> findAll() {
        return clientiRepository.findAll();
    }

    @Override
    public Clienti findById(Integer id) {
        return clientiRepository.findById(id);
    }

    @Override
    public boolean delete(Clienti user) {
        return clientiRepository.delete(user);
    }

    //@Override
    /*public void createReservation(Clienti user, Fotografi movie, Date date) {
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
