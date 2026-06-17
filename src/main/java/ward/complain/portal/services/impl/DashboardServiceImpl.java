package ward.complain.portal.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ward.complain.portal.enums.ComplainStatus;
import ward.complain.portal.models.Complaint;
import ward.complain.portal.models.dtos.reponses.ComplaintResponseDTO;
import ward.complain.portal.models.dtos.requests.ComplaintReportRequestDTO;
import ward.complain.portal.models.dtos.requests.DashboardDTO;
import ward.complain.portal.repository.*;
import ward.complain.portal.services.interfaces.DashboardService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;
    private final CitizenRepository citizenRepository;
    private final LeaderRepository leaderRepository;
    private final ComplaintRepository complaintRepository;
    private final ComplaintCategoryRepository categoryRepository;

    @Override
    public DashboardDTO getDashboardStatistics() {

        return DashboardDTO.builder()
                .totalRegions(regionRepository.count())
                .totalDistricts(districtRepository.count())
                .totalWards(wardRepository.count())
                .totalCitizens(citizenRepository.count())
                .totalLeaders(leaderRepository.count())
                .totalComplaints(complaintRepository.count())
                .pendingComplaints(
                        complaintRepository.countByComplainStatus(
                                ComplainStatus.PENDING
                        )
                )
                .inProgressComplaints(
                        complaintRepository.countByComplainStatus(
                                ComplainStatus.IN_PROGRESS
                        )
                )
                .resolvedComplaints(
                        complaintRepository.countByComplainStatus(
                                ComplainStatus.RESOLVED
                        )
                )
                .totalCategories(categoryRepository.count())
                .build();
    }


    @Override
    public List<ComplaintResponseDTO> generateComplaintReport(
            ComplaintReportRequestDTO request
    ) {

        LocalDateTime startDate =
                request.getStartDate().atStartOfDay();

        LocalDateTime endDate =
                request.getEndDate().atTime(23, 59, 59);

        List<Complaint> complaints;

        if (request.getComplainStatus() == null) {

            complaints = complaintRepository.findByCreatedAtBetween(
                    startDate,
                    endDate
            );

        } else {

            complaints =
                    complaintRepository
                            .findByCreatedAtBetweenAndComplainStatus(
                                    startDate,
                                    endDate,
                                    request.getComplainStatus()
                            );
        }

        return complaints.stream()
                .map(this::mapToComplaintResponse)
                .toList();
    }


    private ComplaintResponseDTO mapToComplaintResponse(Complaint complaint) {
        ComplaintResponseDTO responseDTO = new ComplaintResponseDTO();

        responseDTO.setComplaintId(complaint.getComplaintId());
        responseDTO.setTitle(complaint.getTitle());
        responseDTO.setDescription(complaint.getDescription());
        responseDTO.setComplainStatus(complaint.getComplainStatus());
        responseDTO.setCategory(complaint.getCategory());
        responseDTO.setLeaderFullName(complaint.getWard().getLeader().getFirstName() + " " + complaint.getWard().getLeader().getLastName());
        responseDTO.setCitizenFullName(complaint.getCitizen().getFirstName() + " " + complaint.getCitizen().getLastName());
        responseDTO.setCreatedAt(complaint.getCreatedAt());

        return responseDTO;
    }
}