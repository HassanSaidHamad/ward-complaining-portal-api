package ward.complain.portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ward.complain.portal.models.HttpResponse;
import ward.complain.portal.models.dtos.reponses.ComplaintResponseDTO;
import ward.complain.portal.models.dtos.requests.ComplaintReportRequestDTO;
import ward.complain.portal.models.dtos.requests.DashboardDTO;
import ward.complain.portal.services.interfaces.DashboardService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/statistics")
    public ResponseEntity<HttpResponse> getDashboardStatistics() {

        DashboardDTO dashboard =
                dashboardService.getDashboardStatistics();

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(new Date())
                        .httpStatus(HttpStatus.OK)
                        .httpStatusCode(HttpStatus.OK.value())
                        .message("DASHBOARD STATISTICS RETRIEVED SUCCESSFULLY.")
                        .data(dashboard)
                        .build()
        );
    }


    @PostMapping("/report")
    public ResponseEntity<HttpResponse> generateComplaintReport(
            @RequestBody ComplaintReportRequestDTO request
    ) {

        List<ComplaintResponseDTO> report =
                dashboardService.generateComplaintReport(request);

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(new Date())
                        .httpStatus(HttpStatus.OK)
                        .httpStatusCode(HttpStatus.OK.value())
                        .message("COMPLAINT REPORT GENERATED SUCCESSFULLY.")
                        .data(report)
                        .build()
        );
    }
}