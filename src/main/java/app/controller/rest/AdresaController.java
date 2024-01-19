package app.controller.rest;

import app.dto.AdresaDTO;
import app.model.Adresa;
import app.service.AdresaService;
import app.single_point_access.ServiceSinglePointAccess;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController

// every request to http://localhost:8080/user will enter on this controller
@RequestMapping("/adresa")
public class AdresaController {

    private AdresaService adresaService = ServiceSinglePointAccess.getAdresaService();

    // Map http://localhost:8080/user/all
    // Get - to take data from server
    @GetMapping("/all")
    public ResponseEntity<List<Adresa>> getAllAdresa() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(adresaService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    // {id} - will be taken from path - Path Variable
    // Attention - GET doesn't have a body
    @GetMapping("/id/{id}")
    public ResponseEntity<Adresa> getAdresaById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(adresaService.findById(id));
    }

    // Post - create data
    // RequestBody - is the data sent to server through request
    // For POST, PUT, DELETE we can send information both : Path Variable & RequestBody
    @PostMapping("/create")
    public ResponseEntity<Adresa> createClient(@RequestBody Adresa adr) {
        return ResponseEntity.status(HttpStatus.OK).body(adresaService.save(adr));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateName/{id}/{strada}")
    public ResponseEntity<Adresa> updateClientName(@PathVariable Integer id, @PathVariable String name) {
        Adresa adr = adresaService.findById(id);
        adr.setStrada(name);
        Adresa adrUpdated = adresaService.update(adr);
        return ResponseEntity.status(HttpStatus.OK).body(adrUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<Adresa> update(@RequestBody Adresa adr) {
        Adresa adrFromDB = adresaService.findById(adr.getId());
        adrFromDB.setStrada(adr.getStrada());
        adrFromDB.setOras(adr.getOras());
        adrFromDB.setJudet(adr.getJudet());
        Adresa adrUpdated = adresaService.update(adrFromDB);
        return ResponseEntity.status(HttpStatus.OK).body(adrUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Adresa adr = adresaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(adresaService.delete(adr));
    }

    @Operation(summary = "Get details (name, address, phone) from all users")
    @GetMapping("/details_all")
    public ResponseEntity<List<AdresaDTO>> getAllAdresaDetails() {

        List<Adresa> adresa = adresaService.findAll();
        List<AdresaDTO> adresaDTOS = new ArrayList<>();

        for (Adresa user : adresa) {
            AdresaDTO adresaDTO = new AdresaDTO();
            adresaDTO.setJudet(user.getJudet());
            adresaDTO.setOras(user.getOras());
            adresaDTO.setStrada(user.getStrada());

            adresaDTOS.add(adresaDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(adresaDTOS);
    }

}
