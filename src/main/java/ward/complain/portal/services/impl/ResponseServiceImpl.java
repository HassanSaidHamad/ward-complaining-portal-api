package ward.complain.portal.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.models.Complaint;
import ward.complain.portal.models.Leader;
import ward.complain.portal.models.Response;
import ward.complain.portal.models.dtos.reponses.ResponseDTO;
import ward.complain.portal.models.dtos.requests.ResponseRequestDTO;
import ward.complain.portal.repository.ResponseRepository;
import ward.complain.portal.services.interfaces.AuthService;
import ward.complain.portal.services.interfaces.ComplaintService;
import ward.complain.portal.services.interfaces.ResponseService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {
    private final ResponseRepository responseRepository;
    private final AuthService authService;
    private final ComplaintService complaintService;


    @Override
    public ResponseDTO respondComplaint(ResponseRequestDTO dto, long complaintId) throws ModelNotFoundException {
        Leader leader = (Leader) authService.getCurrentUser();
        Complaint complaint = complaintService.findComplaintById(complaintId);
        complaint.setComplainStatus(dto.getComplainStatus());

        Response response = Response.builder()
                .message(dto.getMessage())
                .leader(leader)
                .complaint(complaint)
                .respondedAt(LocalDateTime.now())
                .build();

        responseRepository.save(response);
        return mapToResponse(response);
    }

    @Override
    public List<ResponseDTO> getAllResponses() {
        return responseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ResponseDTO> getMyResponses() throws ModelNotFoundException {
        Leader leader = (Leader) authService.getCurrentUser();

        List<Response> responses = responseRepository.findByLeaderId(leader.getId());

        return responses
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ResponseDTO getResponseById(long id) throws ModelNotFoundException {
        Response response = responseRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Response by ID: " + id + " not found"));

        return mapToResponse(response);
    }

    @Override
    public Response findResponseById(long id) throws ModelNotFoundException {
        return responseRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Response by ID: " + id + " not found"));
    }


    private ResponseDTO mapToResponse(Response response) {
        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setComplaintId(response.getComplaint().getComplaintId());
        responseDTO.setTitle(response.getComplaint().getTitle());
        responseDTO.setDescription(response.getComplaint().getDescription());
        responseDTO.setComplainStatus(response.getComplaint().getComplainStatus());
        responseDTO.setCategory(response.getComplaint().getCategory());
        responseDTO.setResponse(response);
        responseDTO.setLeaderFullName(response.getLeader().getFirstName() + " " + response.getLeader().getLastName());
        responseDTO.setCitizenFullName(response.getComplaint().getCitizen().getFirstName() + " " + response.getComplaint().getCitizen().getLastName());
        responseDTO.setCreatedAt(response.getComplaint().getCreatedAt());
        responseDTO.setRespondedAt(response.getRespondedAt());

        return responseDTO;
    }
}
