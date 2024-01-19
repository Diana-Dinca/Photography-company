package app.controller.rest;

import app.dto.FotografiDTO;
import app.model.Fotografi;
import app.service.FotografiService;
import app.single_point_access.ServiceSinglePointAccess;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController

// every request to http://localhost:8080/user will enter on this controller
@RequestMapping("/fotografi")
public class FotografiController {

    private FotografiService fotografiService = ServiceSinglePointAccess.getFotografiService();

    // Map http://localhost:8080/user/all
    // Get - to take data from server
    @GetMapping("/all")
    public ResponseEntity<List<Fotografi>> getAllFotografi() {

        // Return a Response which has a status and a body (data)
        return ResponseEntity.status(HttpStatus.OK).body(fotografiService.findAll());
    }

    // {id} - is a value sent by url and is named path variable
    // {id} - will be taken from path - Path Variable
    // Attention - GET doesn't have a body
    @GetMapping("/id/{id}")
    public ResponseEntity<Fotografi> getFotografiById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(fotografiService.findById(id));
    }

    // Post - create data
    // RequestBody - is the data sent to server through request
    // For POST, PUT, DELETE we can send information both : Path Variable & RequestBody
    @PostMapping("/create")
    public ResponseEntity<Fotografi> createFotograf(@RequestBody Fotografi user) {
        return ResponseEntity.status(HttpStatus.OK).body(fotografiService.save(user));
    }

    // Put - update data
    // Put with path variable
    @PutMapping("/updateName/{id}/{name}")
    public ResponseEntity<Fotografi> updateFotografName(@PathVariable Integer id, @PathVariable String name) {
        Fotografi fotografi = fotografiService.findById(id);
        fotografi.setNume(name);
        Fotografi userUpdated = fotografiService.update(fotografi);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<Fotografi> update(@RequestBody Fotografi user) {
        Fotografi userFromDB = fotografiService.findById(user.getId());
        userFromDB.setNume(user.getNume());
        userFromDB.setParola(user.getParola());
        Fotografi userUpdated = fotografiService.update(userFromDB);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody Integer id) {
        Fotografi user = fotografiService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(fotografiService.delete(user));
    }

    @Operation(summary = "Get details (name, address, phone) from all users")
    @GetMapping("/details_all")
    public ResponseEntity<List<FotografiDTO>> getAllFotografiDetails() {

        List<Fotografi> fotografi = fotografiService.findAll();
        List<FotografiDTO> fotografiDTOS = new ArrayList<>();

        for (Fotografi user : fotografi) {
            FotografiDTO fotografiDTO = new FotografiDTO();
            fotografiDTO.setAdresa(user.getAdresa());
            fotografiDTO.setTelefon(user.getTelefon());
            fotografiDTO.setNume(user.getNume());

            fotografiDTOS.add(fotografiDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(fotografiDTOS);
    }

}
