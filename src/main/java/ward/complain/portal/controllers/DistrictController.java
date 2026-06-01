package ward.complain.portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ward.complain.portal.models.District;
import ward.complain.portal.models.HttpResponse;
import ward.complain.portal.models.dtos.requests.DistrictDTO;
import ward.complain.portal.services.interfaces.DistrictService;

import java.util.Date;

@RestController
@RequestMapping("/api/districts")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @PostMapping
    public ResponseEntity<HttpResponse> createDistrict(@RequestBody DistrictDTO dto) {

        District district = districtService.createDistrict(dto);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("DISTRICT CREATED SUCCESSFULLY.")
                .data(district)
                .build());
    }

    @GetMapping
    public ResponseEntity<HttpResponse> getAllDistricts() {

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("DISTRICTS RETRIEVED SUCCESSFULLY.")
                .data(districtService.getAllDistricts())
                .build());
    }

    @GetMapping("/region/{regionId}")
    public ResponseEntity<HttpResponse> getDistrictsByRegion(
            @PathVariable Long regionId) {

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("DISTRICTS RETRIEVED SUCCESSFULLY.")
                .data(districtService.getDistrictsByRegion(regionId))
                .build());
    }

}