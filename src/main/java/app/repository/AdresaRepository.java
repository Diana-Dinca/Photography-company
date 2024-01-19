package app.repository;

import app.model.Adresa;

public interface AdresaRepository extends CRUDRepository<Adresa, Integer, String>{
    Adresa findByName(String name);
}
