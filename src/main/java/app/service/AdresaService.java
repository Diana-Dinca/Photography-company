package app.service;

import app.model.Adresa;

import java.util.List;

public interface AdresaService {

    Adresa save(Adresa adr);

    Adresa update(Adresa adr);


    List<Adresa> findAll();

    Adresa findById(Integer id);

    boolean delete(Adresa adr);


}
