package ward.complain.portal.services.interfaces;

import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.models.Response;
import ward.complain.portal.models.dtos.reponses.ResponseDTO;
import ward.complain.portal.models.dtos.requests.ResponseRequestDTO;

import java.util.List;

public interface ResponseService {

    ResponseDTO respondComplaint(ResponseRequestDTO dto, long complaintId) throws ModelNotFoundException;

    List<ResponseDTO> getAllResponses();

    List<ResponseDTO> getMyResponses() throws ModelNotFoundException;

    List<ResponseDTO> getCitizenResponsesByComplaintId(long complaintId) throws ModelNotFoundException;

    ResponseDTO getResponseById(long id) throws ModelNotFoundException;

    Response findResponseById(long id) throws ModelNotFoundException;
}