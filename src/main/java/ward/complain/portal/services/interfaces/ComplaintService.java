package ward.complain.portal.services.interfaces;

import ward.complain.portal.enums.ComplainStatus;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.models.Complaint;
import ward.complain.portal.models.dtos.reponses.ComplaintResponseDTO;
import ward.complain.portal.models.dtos.requests.ComplaintRequestDTO;
import ward.complain.portal.models.dtos.requests.UpdateComplaintStatusDTO;

import java.util.List;

public interface ComplaintService {

    ComplaintResponseDTO sendComplaint(ComplaintRequestDTO dto, long categoryId) throws ModelNotFoundException;

    List<ComplaintResponseDTO> getAllComplaints();

    List<ComplaintResponseDTO> getMyComplaints() throws ModelNotFoundException;

    List<ComplaintResponseDTO> getAllCitizenComplaints() throws ModelNotFoundException;

    List<ComplaintResponseDTO> getMyComplaintsByStatus(ComplainStatus status) throws ModelNotFoundException;

    List<ComplaintResponseDTO> getAllComplaintsByStatus(ComplainStatus status);

    ComplaintResponseDTO getComplaintById(Long id) throws ModelNotFoundException;

    Complaint findComplaintById(Long id) throws ModelNotFoundException;

    List<ComplaintResponseDTO> getComplaintsByWard(Long wardId);

    ComplaintResponseDTO updateStatus(Long complaintId, UpdateComplaintStatusDTO dto) throws ModelNotFoundException;
}