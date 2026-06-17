package ward.complain.portal.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardDTO {

    private Long totalRegions;
    private Long totalDistricts;
    private Long totalWards;

    private Long totalCitizens;
    private Long totalLeaders;

    private Long totalComplaints;
    private Long pendingComplaints;
    private Long inProgressComplaints;
    private Long resolvedComplaints;

    private Long totalCategories;
}