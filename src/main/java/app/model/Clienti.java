package app.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@NamedQueries(
        {
                @NamedQuery(name = "findClientByName", query = "from Clienti client where client.nume = :name"),
                @NamedQuery(name = "findClientById", query = "from Clienti client where client.id = :id"),
                @NamedQuery(name = "findAllClienti", query = "from Clienti")
        }
)
public class Clienti implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nume;

    @Column
    private String prenume;

    @Column
    private String cnp;

    @Column
    private String telefon;

    @Column
    private String email;
    @Column
    private String parola;

    @OneToOne(cascade = CascadeType.ALL)
    private Adresa adresa;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Evenimente> programari;

    public void addEvent(Evenimente event)
    {
        programari.add(event);
        event.setClient(this);
    }


}
