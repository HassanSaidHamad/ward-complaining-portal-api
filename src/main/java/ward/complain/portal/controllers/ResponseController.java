package ward.complain.portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.models.HttpResponse;
import ward.complain.portal.models.dtos.reponses.ComplaintResponseDTO;
import ward.complain.portal.models.dtos.reponses.ResponseDTO;
import ward.complain.portal.models.dtos.requests.ComplaintRequestDTO;
import ward.complain.portal.models.dtos.requests.ResponseRequestDTO;
import ward.complain.portal.models.dtos.requests.UpdateComplaintStatusDTO;
import ward.complain.portal.services.interfaces.ResponseService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/responses")
@RequiredArgsConstructor
public class ResponseController {
    private final ResponseService responseService;

    @PostMapping("/complaint/{complaintId}")
//    @PreAuthorize("hasAuthority('ROLE_LEADER)")
    public ResponseEntity<HttpResponse> respondComplaint(@RequestBody ResponseRequestDTO dto, @PathVariable long complaintId) throws ModelNotFoundException {
        ResponseDTO response = responseService.respondComplaint(dto, complaintId);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("complaint responded SUCCESSFULLY.".toUpperCase())
                .data(response)
                .build());
    }


    @GetMapping
    public ResponseEntity<HttpResponse> getAllResponses() {

        List<ResponseDTO> allResponses = responseService.getAllResponses();

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("RESPONSES RETRIEVED SUCCESSFULLY.")
                .data(allResponses)
                .build());
    }


    @GetMapping("/my-responses")
//    @PreAuthorize("hasAuthority('ROLE_LEADER')")
    public ResponseEntity<HttpResponse> getMyResponses() throws ModelNotFoundException {

        List<ResponseDTO> allResponses = responseService.getMyResponses();

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("RESPONSES RETRIEVED SUCCESSFULLY.")
                .data(allResponses)
                .build());
    }


    @GetMapping("/complaint/{complaintId}")
    public ResponseEntity<HttpResponse> getCitizenResponsesByComplaintId(@PathVariable long complaintId) throws ModelNotFoundException {

        List<ResponseDTO> allResponses = responseService.getCitizenResponsesByComplaintId(complaintId);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("CITIZEN RESPONSES RETRIEVED SUCCESSFULLY.")
                .data(allResponses)
                .build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> getResponseById(@PathVariable long id) throws ModelNotFoundException {

        ResponseDTO response = responseService.getResponseById(id);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("RESPONSE RETRIEVED SUCCESSFULLY.")
                .data(response)
                .build());
    }
}
