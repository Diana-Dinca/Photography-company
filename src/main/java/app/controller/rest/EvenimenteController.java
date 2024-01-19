package app.controller.rest;

import app.dto.EvenimenteDTO;
import app.model.Evenimente;
import app.service.EvenimenteService;
import app.single_point_access.ServiceSinglePointAccess;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController

// every request to http://localhost:8080/user will enter on this controller
@RequestMapping("/evenimente")
public class EvenimenteController {

    private EvenimenteService evenimenteService = ServiceSinglePointAccess.getEvenimenteService();

    // Map http://localhost:8080/user/all
    // Get - to take data from server
    @GetMapping("/all")
    public ResponseEntity<List<Evenimente>> getAllEvenimente() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(evenimenteService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    // {id} - will be taken from path - Path Variable
    // Attention - GET doesn't have a body
    @GetMapping("/id/{id}")
    public ResponseEntity<Evenimente> getEvenimenteById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(evenimenteService.findById(id));
    }

    // Post - create data
    // RequestBody - is the data sent to server through request
    // For POST, PUT, DELETE we can send information both : Path Variable & RequestBody
    @PostMapping("/create")
    public ResponseEntity<Evenimente> createEveniment(@RequestBody Evenimente ev) {
        return ResponseEntity.status(HttpStatus.OK).body(evenimenteService.save(ev));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateName/{id}/{strada}")
    public ResponseEntity<Evenimente> updateEvenimenteName(@PathVariable Integer id, @PathVariable String name) {
        Evenimente ev = evenimenteService.findById(id);
        ev.setTipEveniment(name);
        Evenimente evUpdated = evenimenteService.update(ev);
        return ResponseEntity.status(HttpStatus.OK).body(evUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<Evenimente> update(@RequestBody Evenimente ev) {
        Evenimente evFromDB = evenimenteService.findById(ev.getId());
        evFromDB.setTipEveniment(ev.getTipEveniment());
        evFromDB.setDate(ev.getDate());
        evFromDB.setPret(ev.getPret());
        Evenimente adrUpdated = evenimenteService.update(evFromDB);
        return ResponseEntity.status(HttpStatus.OK).body(adrUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Evenimente ev = evenimenteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(evenimenteService.delete(ev));
    }

    @Operation(summary = "Get details (name, address, phone) from all users")
    @GetMapping("/details_all")
    public ResponseEntity<List<EvenimenteDTO>> getAllAdresaDetails() {

        List<Evenimente> ev = evenimenteService.findAll();
        List<EvenimenteDTO> evenimenteDTOS = new ArrayList<>();

        for (Evenimente e : ev) {
            EvenimenteDTO evenimenteDTO = new EvenimenteDTO();
            evenimenteDTO.setDate(e.getDate());
            evenimenteDTO.setPret(e.getPret());
            evenimenteDTO.setTipEveniment(e.getTipEveniment());

            evenimenteDTOS.add(evenimenteDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(evenimenteDTOS);
    }

}
