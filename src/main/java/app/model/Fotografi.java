package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Data
@NamedQueries(
        {
                @NamedQuery(name = "findFotografByName", query = "from Fotografi foto where foto.nume= :name"),
                @NamedQuery(name = "findFotografById", query = "from Fotografi foto where foto.id = :id"),
                @NamedQuery(name = "findAllFotografi", query = "from Fotografi")
        }
)
public class Fotografi implements Serializable {
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

    @Column
    private Integer PretMinim;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Evenimente> programari;

}
