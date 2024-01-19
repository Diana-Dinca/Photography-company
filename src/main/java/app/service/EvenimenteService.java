package app.service;

import app.model.Clienti;
import app.model.Evenimente;

import java.util.Date;
import java.util.List;

public interface EvenimenteService {

    Evenimente save(Evenimente user);

    Evenimente update(Evenimente user);

    List<Evenimente> findAll();

    Evenimente findById(Integer id);

    boolean delete(Evenimente user);
    boolean deleteById(Integer id);

    Evenimente findByName(String name);

    void createEvent(Clienti client, String numeFotograf, Integer idLocatie, Date dataEvet);

    //create rez (ob client, fotograf nume, locatie id, data date)
    /*{
        Fotografi fotograf= new Fotografi();
        fotograf= findByName(nume);
    }*/


}
