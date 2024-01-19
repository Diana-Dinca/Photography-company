package app.controller.rest;

import app.dto.LocatiiDTO;
import app.model.Locatii;
import app.service.LocatiiService;
import app.single_point_access.ServiceSinglePointAccess;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController

// every request to http://localhost:8080/user will enter on this controller
@RequestMapping("/locatii")
public class LocatiiController {

    private LocatiiService locatiiService = ServiceSinglePointAccess.getLocatiiService();

    // Map http://localhost:8080/user/all
    // Get - to take data from server
    @GetMapping("/all")
    public ResponseEntity<List<Locatii>> getAllLocatii() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(locatiiService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    // {id} - will be taken from path - Path Variable
    // Attention - GET doesn't have a body
    @GetMapping("/id/{id}")
    public ResponseEntity<Locatii> getLocatiiById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(locatiiService.findById(id));
    }

    // Post - create data
    // RequestBody - is the data sent to server through request
    // For POST, PUT, DELETE we can send information both : Path Variable & RequestBody
    @PostMapping("/create")
    public ResponseEntity<Locatii> createLocatii(@RequestBody Locatii loc) {
        return ResponseEntity.status(HttpStatus.OK).body(locatiiService.save(loc));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateName/{id}/{strada}")
    public ResponseEntity<Locatii> updateLocatiiName(@PathVariable Integer id, @PathVariable String detalii) {
        Locatii loc = locatiiService.findById(id);
        loc.setDetaliiSuplimentare(detalii);
        Locatii locUpdated = locatiiService.update(loc);
        return ResponseEntity.status(HttpStatus.OK).body(locUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<Locatii> update(@RequestBody Locatii loc) {
        Locatii locFromDB = locatiiService.findById(loc.getId());
        locFromDB.setDetaliiSuplimentare(loc.getDetaliiSuplimentare());
        Locatii locUpdated = locatiiService.update(locFromDB);
        return ResponseEntity.status(HttpStatus.OK).body(locUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Locatii loc = locatiiService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(locatiiService.delete(loc));
    }

    @Operation(summary = "Get details (name, address, phone) from all users")
    @GetMapping("/details_all")
    public ResponseEntity<List<LocatiiDTO>> getAllLocatiiDetails() {

        List<Locatii> locatii = locatiiService.findAll();
        List<LocatiiDTO> locatiiDTOS = new ArrayList<>();

        for (Locatii loc : locatii) {
            LocatiiDTO locatiiDTO = new LocatiiDTO();
            locatiiDTO.setDetaliiSuplimentare(loc.getDetaliiSuplimentare());

            locatiiDTOS.add(locatiiDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(locatiiDTOS);
    }

}
