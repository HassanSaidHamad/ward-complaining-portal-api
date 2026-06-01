package ward.complain.portal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wards")
@SuperBuilder
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wardId;

    private String wardName;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @OneToMany(mappedBy = "ward")
    private List<Citizen> users;

    //    @OneToMany(mappedBy = "ward")
//    private List<Leader> leaders;

    @OneToOne(mappedBy = "ward")
    private Leader leader;
}