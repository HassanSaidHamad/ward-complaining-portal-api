package ward.complain.portal.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "citizens")
public class Citizen extends User {

    @Column(nullable = true)
    private String streetName;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    @JsonBackReference
    private Ward ward;

    @OneToMany(mappedBy = "citizen")
    @JsonBackReference
    private List<Complaint> complaints;
}
