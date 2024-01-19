package app.model;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@NamedQueries(
        {
                @NamedQuery(name = "findEvenimentByName", query = "from Evenimente event where event.tipEveniment= :name"),
                @NamedQuery(name = "findEvenimentById", query = "from Evenimente event where event.id = :id"),
                @NamedQuery(name = "findAllEvenimente", query = "from Evenimente")
        }
)
public class Evenimente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@ToString.Exclude
    //@JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Clienti client;

    @ManyToOne(cascade = CascadeType.ALL)
    private Fotografi fotograf;

    @OneToOne(cascade = CascadeType.ALL)
    private Locatii locatie;

    @Column
    private Date date;
    @Column
    private Integer pret;

    @Column
    private String tipEveniment;
}
