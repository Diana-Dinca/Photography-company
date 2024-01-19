package app.repository;

import app.model.Locatii;

public interface LocatiiRepository extends CRUDRepository<Locatii, Integer, String> {

    Locatii save(Locatii entity);

    Locatii findByName(String name);

}
