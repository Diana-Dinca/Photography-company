package app.controller.rest;

import app.model.Clienti;
import app.model.Fotografi;
import app.service.ClientiService;
import app.service.FotografiService;
import app.single_point_access.ServiceSinglePointAccess;
import app.util.FileUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


// All imported/exported files are taken form resources directory ONLY
@RestController
@RequestMapping("/csv")
public class CSVController {

    private ClientiService clientiService = ServiceSinglePointAccess.getClientiService();
    private FotografiService fotografiService = ServiceSinglePointAccess.getFotografiService();

    // TO DO
    // For project take in consideration that a csv file could have different order of columns
    // Do it for at least 2 entities - import and export
    // Take in consideration data validation or if some data already exists
    // Extract duplicate logic and improve it based on template below
    //
    // For demo - import at least 25 entities and export all entities
    //
    @PostMapping("/import_clienti")
    public ResponseEntity<Boolean> importClientiFromCSV(@RequestBody String filename) {
        try {
            File file = FileUtil.getAndCreateFileFromResourcesDirectory(filename);

            // Read data in a buffer
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<Clienti> users = new ArrayList<>();
            String line;
            boolean firstLine = true;
            String[] headers = null;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                Clienti client = new Clienti();

                if (firstLine) {
                    // Assume the first line contains headers in order
                    firstLine = false;
                    headers = values;
                    continue;
                }

                // The order in csv could be changed
                // This implementation is only for template purpose
                for (int i = 0; i < values.length; i++) {
                    if (i < headers.length) {
                        switch (headers[i].toLowerCase()) {
                            case "nume":
                                client.setNume(values[i]);
                                break;
                            case "prenume":
                                client.setPrenume(values[i]);
                                break;
                            case "cnp":
                                client.setCnp(values[i]);
                                break;
                            case "email":
                                client.setEmail(values[i]);
                                break;
                            case "telefon":
                                client.setTelefon(values[i]);
                                break;
                            default:
                                break;
                        }
                    }
                }


                users.add(client);
            }

            for (Clienti userIterator : users) {
                clientiService.save(userIterator);
            }

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

            // TO DO - treat exception case
        } catch (IOException e) {

            // TO DO - treat exception case
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {

            // TO DO - treat exception case
            throw new RuntimeException(e);
        }
    }

    // You can send the order of fields that must appear in csv
    // Add a new parameter for header

    // You can send the order of fields that must appear in csv
    // Add a new parameter for header
    @PostMapping("/export_clienti")
    public ResponseEntity<Boolean> exportUserToCSV(@RequestBody ExportRequest exportRequest) {

        try {
            File file = FileUtil.getAndCreateFileFromResourcesDirectory(exportRequest.getFilename());
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(exportRequest.getHeader());

            List<Clienti> clienti = clientiService.findAll();
            for (Clienti iterator : clienti) {
                StringBuilder userDetails = new StringBuilder();
                String[] fields = exportRequest.getHeader().split(",");
                for (String field : fields) {
                    switch (field.trim()) {
                        case "nume":
                            userDetails.append(iterator.getNume()).append(",");
                            break;
                        case "prenume":
                            userDetails.append(iterator.getPrenume()).append(",");
                            break;
                        case "cnp":
                            userDetails.append(iterator.getCnp()).append(",");
                            break;
                        case "email":
                            userDetails.append(iterator.getEmail()).append(",");
                            break;
                        case "telefon":
                            userDetails.append(iterator.getTelefon()).append(",");
                            break;
                    }
                }
                userDetails.deleteCharAt(userDetails.length() - 1);
                userDetails.append("\n");
                fileWriter.write(userDetails.toString());
            }

            fileWriter.close();

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (IOException ex) {

            // TODO - treat exception case

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        } catch (URISyntaxException e) {

            // TODO - treat exception case

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

// Fotografi:

    @PostMapping("/import_fotografi")
    public ResponseEntity<Boolean> importFotografiFromCSV(@RequestBody String filename) {
        try {
            File file = FileUtil.getAndCreateFileFromResourcesDirectory(filename);

            // Read data in a buffer
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<Fotografi> fotografi = new ArrayList<>();
            String line;
            boolean firstLine = true;
            String[] headers = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                Fotografi fotograf = new Fotografi();

                if (firstLine) {
                    // Assume the first line contains headers in order
                    firstLine = false;
                    headers = values;
                    continue;
                }

                // The order in csv could be changed
                // This implementation is only for template purpose
                for (int i = 0; i < values.length; i++) {
                    if (i < headers.length) {
                        switch (headers[i].toLowerCase()) {
                            case "nume":
                                fotograf.setNume(values[i]);
                                break;
                            case "prenume":
                                fotograf.setPrenume(values[i]);
                                break;
                            case "cnp":
                                fotograf.setCnp(values[i]);
                                break;
                            case "email":
                                fotograf.setEmail(values[i]);
                            case "telefon":
                                fotograf.setTelefon(values[i]);
                                break;
                            default:
                                break;
                        }
                    }
                }


                fotografi.add(fotograf);
            }

            for (Fotografi iterator : fotografi) {
                fotografiService.save(iterator);
            }

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

            // TO DO - treat exception case
        } catch (IOException e) {

            // TO DO - treat exception case
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {

            // TO DO - treat exception case
            throw new RuntimeException(e);
        }
    }

// You can send the order of fields that must appear in csv
// Add a new parameter for header

    // You can send the order of fields that must appear in csv
// Add a new parameter for header
    @PostMapping("/export_fotografi")
    public ResponseEntity<Boolean> exportFotografiToCSV(@RequestBody ExportRequest exportRequest) {

        try {
            File file = FileUtil.getAndCreateFileFromResourcesDirectory(exportRequest.getFilename());
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(exportRequest.getHeader());

            List<Fotografi> fotografi = fotografiService.findAll();
            for (Fotografi iterator : fotografi) {
                StringBuilder userDetails = new StringBuilder();
                String[] fields = exportRequest.getHeader().split(",");
                for (String field : fields) {
                    switch (field.trim()) {
                        case "nume":
                            userDetails.append(iterator.getNume()).append(",");
                            break;
                        case "prenume":
                             userDetails.append(iterator.getPrenume()).append(",");
                             break;
                        case "email":
                            userDetails.append(iterator.getEmail()).append(",");
                            break;
                        case "telefon":
                            userDetails.append(iterator.getTelefon()).append(",");
                            break;
                    }
                }
                userDetails.deleteCharAt(userDetails.length() - 1);
                userDetails.append("\n");
                fileWriter.write(userDetails.toString());
            }

            fileWriter.close();

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (IOException ex) {

            // TODO - treat exception case

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        } catch (URISyntaxException e) {

            // TODO - treat exception case

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}

