package ward.complain.portal.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "responses")
@SuperBuilder
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responseId;

    @Column(length = 3000)
    private String message;

    private LocalDateTime respondedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "leader_id")
    @JsonBackReference
    private Leader leader;

    @ManyToOne
    @JoinColumn(name = "complaint_id")
    @JsonBackReference
    private Complaint complaint;

}