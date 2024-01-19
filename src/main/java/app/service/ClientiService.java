package app.service;

import app.model.Clienti;

import java.util.List;

public interface ClientiService {

    Clienti save(Clienti user);

    Clienti update(Clienti user);

    List<Clienti> findAll();

    Clienti findById(Integer id);

    boolean delete(Clienti user);

}
