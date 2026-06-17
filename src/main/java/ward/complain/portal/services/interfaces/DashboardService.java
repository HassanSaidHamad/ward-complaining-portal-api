package ward.complain.portal.services.interfaces;

import ward.complain.portal.models.dtos.reponses.ComplaintResponseDTO;
import ward.complain.portal.models.dtos.requests.ComplaintReportRequestDTO;
import ward.complain.portal.models.dtos.requests.DashboardDTO;

import java.util.List;

public interface DashboardService {

    DashboardDTO getDashboardStatistics();

    List<ComplaintResponseDTO> generateComplaintReport(
            ComplaintReportRequestDTO request
    );

}