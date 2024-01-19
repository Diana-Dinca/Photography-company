package app;

import app.controller.gui.LoginController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.Properties;


@SpringBootApplication
public class Main {

    private static final String APPLICATION_CONFIGURATION_FILE = "app.configuration.properties";

    public static void main(String[] args) {

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(APPLICATION_CONFIGURATION_FILE)) {
            Properties properties = new Properties();
            properties.load(input);

            // Decide app mode from file
            String mode = properties.getProperty("mode");

            if (mode.equals("web")) {
                SpringApplication.run(Main.class, args);
            } else {
                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        } catch (Exception ex) {
            System.out.println("Error at starting application...");
            ex.printStackTrace();
        }
        /*LocatiiService locatiiService= ServiceSinglePointAccess.getLocatiiService();
        Locatii locatie= locatiiService.findById(14);
        locatiiService.delete(locatie);*/

        /*Adresa adresa1= new Adresa();
        adresa1.setOras("Cluj");
        adresa1.setJudet("Cluj-Napoca");
        adresa1.setStrada("observatorului 23");

        Adresa adresa2= new Adresa();
        adresa2.setOras("Cluj");
        adresa2.setJudet("Cluj-Napoca");
        adresa2.setStrada("ceahlaului 3");

        Clienti client= new Clienti();
        //client.setAdresa(adresa1);
        client.setCnp("1234567891012");
        client.setNume("Bogdan");
        client.setPrenume("Bogdanel");
        client.setEmail("bog.bogdan@yahoo.com");
        client.setParola("1234");
        client.setTelefon("0770838424");

        Fotografi fotograf= new Fotografi();
        fotograf.setAdresa(adresa2);
        fotograf.setNume("Diana");
        fotograf.setPrenume("Dinca");
        fotograf.setTelefon("077083948");
        fotograf.setPretMinim(400);
        fotograf.setEmail("diana.di@yahoo.com");
        fotograf.setParola("abcd");

        Locatii locatie= new Locatii();
        locatie.setDetaliiSuplimentare("Parcul Central");

        ClientiService clientiService= ServiceSinglePointAccess.getClientiService();
        Clienti savedClient = clientiService.save(client);

        FotografiService fotografiService= ServiceSinglePointAccess.getFotografiService();
        Fotografi savedFotograf = fotografiService.save(fotograf);

        LocatiiService locatiiService= ServiceSinglePointAccess.getLocatiiService();
        Locatii savedLocatie = locatiiService.save(locatie);

        //AdresaService adresaService= ServiceSinglePointAccess.getAdresaService();
        //Adresa savedAdresa = adresaService.save(adresa1);
       // AdresaService adresaService2= ServiceSinglePointAccess.getAdresaService();
       // Adresa savedAdresa2 = adresaService2.save(adresa2);

        EvenimenteService evenimenteService= ServiceSinglePointAccess.getEvenimenteService();

        //evenimenteService.createEvent(savedClient, fotograf.getNume(),1, new Date());
        evenimenteService.deleteById(1);*/
        //System.out.println(evenimenteService);

        // Test implementation
//        User user = new User();
//        user.setName("Test");
//        user.setPassword("1234");
//        user.setSalary(22);
//        Address address = new Address();
//        address.setCity("Cluj-Napoca");
//        address.setStreet("Dorobantilor");
//        user.setAddress(address);
//
//        UserService userService = ServiceSinglePointAccess.getUserService();
//        User savedUser = userService.save(user);
//
//        Movie movie = new Movie();
//        movie.setName("Infinity War");
//        movie.setPrice(34.0);
//
//        MovieRepository movieRepository = RepositorySinglePointAccess.getMovieRepository();
//        Movie savedMovie = movieRepository.save(movie);
//
//        userService.createReservation(savedUser, savedMovie, new Date());
//        System.out.println(userService.findById(savedUser.getId()));
    }
}
