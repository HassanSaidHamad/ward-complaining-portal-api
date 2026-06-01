package ward.complain.portal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ward.complain.portal.enums.ComplainStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "complaints")
@SuperBuilder
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;

    private String title;

    @Column(length = 5000)
    private String description;

    private String imageUrl;

    private ComplainStatus complainStatus;  // PENDING, IN_PROGRESS, RESOLVED

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ComplaintCategory category;

    @OneToMany(mappedBy = "complaint")
    private List<Response> responses;

}