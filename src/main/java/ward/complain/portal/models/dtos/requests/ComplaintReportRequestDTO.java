package ward.complain.portal.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ward.complain.portal.enums.ComplainStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintReportRequestDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private ComplainStatus complainStatus;
}