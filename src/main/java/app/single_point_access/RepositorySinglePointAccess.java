package app.single_point_access;

import app.repository.*;
import app.repository.implemetation.*;

public class RepositorySinglePointAccess {

    private static ClientiRepository userRepository;
    private static AdresaRepository adresaRepository;
    private static EvenimenteRepository evenimenteRepository;
    private static FotografiRepository fotografiRepository;
    private static LocatiiRepository locatiiRepository;


    static {

        userRepository = new ClientiRepositoryImpl();
        adresaRepository = new AdresaRepositoryImpl();
        evenimenteRepository = new EvenimenteRepositoryImpl();
        fotografiRepository = new FotografiRepositoryImpl();
        locatiiRepository = new LocatiiRepositoryImpl();
    }

    public static ClientiRepository getClientiRepository() {
        return userRepository;
    }
    public static AdresaRepository getAdresaRepository() {
        return adresaRepository;
    }
    public static EvenimenteRepository getEvenimenteRepository() {
        return evenimenteRepository;
    }
    public static FotografiRepository getFotografiRepository() {
        return fotografiRepository;
    }
    public static LocatiiRepository getLocatiiRepository() {
        return locatiiRepository;
    }



}
