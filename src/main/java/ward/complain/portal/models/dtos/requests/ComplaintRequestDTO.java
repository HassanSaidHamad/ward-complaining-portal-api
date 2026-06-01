package ward.complain.portal.models.dtos.requests;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ward.complain.portal.enums.ComplainStatus;
import ward.complain.portal.models.Citizen;
import ward.complain.portal.models.ComplaintCategory;
import ward.complain.portal.models.Response;
import ward.complain.portal.models.Ward;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintRequestDTO {
    private String title;
    private String description;
}