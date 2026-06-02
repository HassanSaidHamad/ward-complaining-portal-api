package ward.complain.portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.models.Complaint;
import ward.complain.portal.models.HttpResponse;
import ward.complain.portal.models.Region;
import ward.complain.portal.models.dtos.reponses.ComplaintResponseDTO;
import ward.complain.portal.models.dtos.requests.ComplaintRequestDTO;
import ward.complain.portal.models.dtos.requests.UpdateComplaintStatusDTO;
import ward.complain.portal.services.interfaces.ComplaintService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @PostMapping("/category/{categoryId}")
    @PreAuthorize("hasAuthority('ROLE_CITIZEN')")
    public ResponseEntity<HttpResponse> sendComplaint(@RequestBody ComplaintRequestDTO dto, @PathVariable long categoryId) throws ModelNotFoundException {
        ComplaintResponseDTO complaint = complaintService.sendComplaint(dto, categoryId);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("complaint sent SUCCESSFULLY.".toUpperCase())
                .data(complaint)
                .build());
    }


    @PutMapping("/status/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HttpResponse> updateStatus(@RequestBody UpdateComplaintStatusDTO dto, @PathVariable long id) throws ModelNotFoundException {
        ComplaintResponseDTO complaint = complaintService.updateStatus(id, dto);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("complaint status updated SUCCESSFULLY.".toUpperCase())
                .data(complaint)
                .build());
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HttpResponse> getAllComplaints() {

        List<ComplaintResponseDTO> allComplaints = complaintService.getAllComplaints();

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("COMPLAINTS RETRIEVED SUCCESSFULLY.")
                .data(allComplaints)
                .build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> getComplaintById(@PathVariable long id) throws ModelNotFoundException {

        ComplaintResponseDTO complaint = complaintService.getComplaintById(id);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("COMPLAINT RETRIEVED SUCCESSFULLY.")
                .data(complaint)
                .build());
    }
}
