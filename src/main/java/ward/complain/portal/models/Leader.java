package ward.complain.portal.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "leaders")
public class Leader extends User {

    @Column(nullable = false)
    private String position;

//    @ManyToOne
    @OneToOne
    @JoinColumn(name = "ward_id")
    @JsonBackReference
    private Ward ward;

}
