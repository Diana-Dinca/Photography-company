package app.repository;

import app.model.Clienti;

public interface ClientiRepository extends CRUDRepository<Clienti, Integer, String> {

    Clienti findByName(String name);

}
