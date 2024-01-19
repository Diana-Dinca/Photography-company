package app.service;

import app.model.Fotografi;

import java.util.List;

public interface FotografiService {

    Fotografi save(Fotografi user);

    Fotografi update(Fotografi user);

    List<Fotografi> findAll();

    Fotografi findById(Integer id);

    boolean delete(Fotografi user);

    Fotografi findByName(String name);


}
