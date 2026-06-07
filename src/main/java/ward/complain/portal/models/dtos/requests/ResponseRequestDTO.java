package ward.complain.portal.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ward.complain.portal.enums.ComplainStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRequestDTO {
    private String message;
    private ComplainStatus complainStatus;
}