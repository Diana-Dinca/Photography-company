package app.service;

import app.model.Locatii;

import java.util.List;

public interface LocatiiService {

    Locatii save(Locatii user);

    Locatii update(Locatii user);

    List<Locatii> findAll();

    Locatii findById(Integer id);

    boolean delete(Locatii user);

}
