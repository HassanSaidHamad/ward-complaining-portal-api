package ward.complain.portal.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "districts")
@SuperBuilder
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long districtId;

    private String districtName;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonBackReference
    private Region region;

    @OneToMany(mappedBy = "district")
    @JsonBackReference
    private List<Ward> wards;
}