package app.repository;

import app.model.Evenimente;

public interface EvenimenteRepository extends CRUDRepository<Evenimente, Integer, String> {

    Evenimente save(Evenimente entity);

    Evenimente findByName(String name);

}
