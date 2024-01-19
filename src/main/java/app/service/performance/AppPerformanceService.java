package app.service.performance;

import app.model.Clienti;
import app.service.ClientiService;
import app.single_point_access.ServiceSinglePointAccess;

import java.util.List;

public class AppPerformanceService implements PerformanceService {

    private ClientiService clientiService = ServiceSinglePointAccess.getClientiService();

    @Override
    public void applyLogicOnUsers() {
        List<Clienti> users = clientiService.findAll();

        for (Clienti user : users) {
            if (user.getTelefon() != null) {
                user.setTelefon("+40" + user.getTelefon());
            }

            if (user.getId() % 2 == 1) {
                if (user.getCnp() != null) {
                    user.setCnp(user.getCnp() + 1);
                }
            }
            clientiService.update(user);
        }

    }
}
