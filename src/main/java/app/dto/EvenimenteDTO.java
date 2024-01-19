package app.dto;

import app.model.Locatii;
import lombok.Data;

import java.util.Date;

@Data
public class EvenimenteDTO {

    private Locatii locatie;

    private Date date;

    private Integer pret;

    private String tipEveniment;
}
