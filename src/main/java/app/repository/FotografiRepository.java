package app.repository;

import app.model.Fotografi;

public interface FotografiRepository extends CRUDRepository<Fotografi, Integer, String> {

    Fotografi save(Fotografi entity);

    Fotografi findByName(String name);

}
