package app.controller.rest;

import app.dto.ClientiDTO;
import app.model.Clienti;
import app.service.ClientiService;
import app.single_point_access.ServiceSinglePointAccess;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController

// every request to http://localhost:8080/user will enter on this controller
@RequestMapping("/clienti")
public class ClientiController {

    private ClientiService clientiService = ServiceSinglePointAccess.getClientiService();

    // Map http://localhost:8080/user/all
    // Get - to take data from server
    @GetMapping("/all")
    public ResponseEntity<List<Clienti>> getAllClienti() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(clientiService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    // {id} - will be taken from path - Path Variable
    // Attention - GET doesn't have a body
    @GetMapping("/id/{id}")
    public ResponseEntity<Clienti> getClientiById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientiService.findById(id));
    }

    // Post - create data
    // RequestBody - is the data sent to server through request
    // For POST, PUT, DELETE we can send information both : Path Variable & RequestBody
    @PostMapping("/create")
    public ResponseEntity<Clienti> createClient(@RequestBody Clienti user) {
        return ResponseEntity.status(HttpStatus.OK).body(clientiService.save(user));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateName/{id}/{name}")
    public ResponseEntity<Clienti> updateClientName(@PathVariable Integer id, @PathVariable String name) {
        Clienti client = clientiService.findById(id);
        client.setNume(name);
        Clienti userUpdated = clientiService.update(client);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<Clienti> update(@RequestBody Clienti user) {
        Clienti userFromDB = clientiService.findById(user.getId());
        userFromDB.setNume(user.getNume());
        userFromDB.setParola(user.getParola());
        Clienti userUpdated = clientiService.update(userFromDB);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Clienti user = clientiService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientiService.delete(user));
    }

    @Operation(summary = "Get details (name, address, phone) from all users")
    @GetMapping("/details_all")
    public ResponseEntity<List<ClientiDTO>> getAllClientiDetails() {

        List<Clienti> clienti = clientiService.findAll();
        List<ClientiDTO> clientiDTOS = new ArrayList<>();

        for (Clienti user : clienti) {
            ClientiDTO clientiDTO = new ClientiDTO();
            clientiDTO.setAdresa(user.getAdresa());
            clientiDTO.setTelefon(user.getTelefon());
            clientiDTO.setNume(user.getNume());

            clientiDTOS.add(clientiDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(clientiDTOS);
    }

}
