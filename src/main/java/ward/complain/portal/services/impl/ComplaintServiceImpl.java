package ward.complain.portal.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ward.complain.portal.enums.ComplainStatus;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.models.Citizen;
import ward.complain.portal.models.Complaint;
import ward.complain.portal.models.ComplaintCategory;
import ward.complain.portal.models.dtos.reponses.ComplaintResponseDTO;
import ward.complain.portal.models.dtos.requests.ComplaintRequestDTO;
import ward.complain.portal.models.dtos.requests.UpdateComplaintStatusDTO;
import ward.complain.portal.repository.ComplaintCategoryRepository;
import ward.complain.portal.repository.ComplaintRepository;
import ward.complain.portal.services.interfaces.AuthService;
import ward.complain.portal.services.interfaces.ComplaintService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintRepository complaintRepository;
    private final ComplaintCategoryRepository categoryRepository;
    private final AuthService authService;

    @Override
    public ComplaintResponseDTO sendComplaint(ComplaintRequestDTO dto, long categoryId) throws ModelNotFoundException {
        Citizen citizen = (Citizen) authService.getCurrentUser();
        ComplaintCategory category = categoryRepository.findById(categoryId).get();

        Complaint complaint = Complaint.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .imageUrl(null)
                .complainStatus(ComplainStatus.PENDING)
                .category(category)
                .citizen(citizen)
                .ward(citizen.getWard())
                .createdAt(LocalDateTime.now())
                .build();

        complaintRepository.save(complaint);
        return mapToComplaintResponse(complaint);
    }


    @Override
    public List<ComplaintResponseDTO> getAllComplaints() {
        return complaintRepository.findAll()
                .stream()
                .map(this::mapToComplaintResponse)
                .toList();
    }

    @Override
    public ComplaintResponseDTO getComplaintById(Long id) throws ModelNotFoundException {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Complaint by ID: " + id + " not found"));

        return mapToComplaintResponse(complaint);
    }

    @Override
    public Complaint findComplaintById(Long id) throws ModelNotFoundException {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Complaint by ID: " + id + " not found"));
    }

    @Override
    public List<ComplaintResponseDTO> getComplaintsByWard(Long wardId) {
        return List.of();
    }

    @Override
    public ComplaintResponseDTO updateStatus(Long complaintId, UpdateComplaintStatusDTO dto) throws ModelNotFoundException {
        Complaint complaint = findComplaintById(complaintId);
        complaint.setComplainStatus(dto.getComplainStatus());

        complaintRepository.save(complaint);
        return mapToComplaintResponse(complaint);
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
