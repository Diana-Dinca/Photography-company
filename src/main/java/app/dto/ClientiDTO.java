package app.dto;

import app.model.Adresa;
import lombok.Data;

@Data
public class ClientiDTO {

    private String nume;

    private String prenume;

    private String cnp;

    private String telefon;

    private String email;

    private String parola;

    private Adresa adresa;
}
