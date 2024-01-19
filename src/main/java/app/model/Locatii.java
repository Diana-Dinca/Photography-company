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
@Data
@NamedQueries(
        {
                @NamedQuery(name = "findLocatieByName", query = "from Locatii loc where loc.detaliiSuplimentare= :name"),
                @NamedQuery(name = "findLocatieById", query = "from Locatii loc where loc.id = :id"),
                @NamedQuery(name = "findAllLocatii", query = "from Locatii")
        }
)
public class Locatii implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Evenimente> programari;

    @Column
    private String detaliiSuplimentare;
}
