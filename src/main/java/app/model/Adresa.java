package app.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
                @NamedQuery(name = "findAdrByCity", query = "from Adresa adr where adr.oras = :name"),
                @NamedQuery(name = "findAdrById", query = "from Adresa adr where adr.id = :id"),
                @NamedQuery(name = "findAllAdr", query = "from Adresa")
        }
)
public class Adresa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String judet;

    @Column
    private String oras;

    @Column
    private String strada;
}
