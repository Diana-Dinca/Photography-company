package app.service.implementation;

import app.model.Locatii;
import app.repository.AdresaRepository;
import app.repository.LocatiiRepository;
import app.service.LocatiiService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class LocatiiServiceImpl implements LocatiiService {

    private LocatiiRepository locatiiRepository = RepositorySinglePointAccess.getLocatiiRepository();
    private AdresaRepository adresaRepository = RepositorySinglePointAccess.getAdresaRepository();

    @Override
    public Locatii save(Locatii user) {
        return locatiiRepository.save(user);
    }

    @Override
    public Locatii update(Locatii user) {
        return locatiiRepository.update(user);
    }

    @Override
    public List<Locatii> findAll() {
        return locatiiRepository.findAll();
    }

    @Override
    public Locatii findById(Integer id) {
        return locatiiRepository.findById(id);
    }

    @Override
    public boolean delete(Locatii user) {
        return locatiiRepository.delete(user);
    }


}
