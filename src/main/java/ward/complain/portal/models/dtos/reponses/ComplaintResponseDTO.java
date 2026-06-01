package ward.complain.portal.models.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ward.complain.portal.enums.ComplainStatus;
import ward.complain.portal.models.ComplaintCategory;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintResponseDTO {
    private Long complaintId;
    private String title;
    private String description;
    private ComplainStatus complainStatus;
    private ComplaintCategory category;
    private String leaderFullName;
    private String citizenFullName;
    private LocalDateTime createdAt;
}