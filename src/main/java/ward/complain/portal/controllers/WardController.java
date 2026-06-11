package ward.complain.portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ward.complain.portal.models.HttpResponse;
import ward.complain.portal.models.Ward;
import ward.complain.portal.models.dtos.requests.WardDTO;
import ward.complain.portal.services.interfaces.WardService;

import java.util.Date;

@RestController
@RequestMapping("/api/wards")
@RequiredArgsConstructor
public class WardController {

    private final WardService wardService;

    @PostMapping
    public ResponseEntity<HttpResponse> createWard(
            @RequestBody WardDTO dto) {

        Ward ward = wardService.createWard(dto);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.CREATED)
                .httpStatusCode(HttpStatus.CREATED.value())
                .message("WARD CREATED SUCCESSFULLY.")
                .data(ward)
                .build());
    }


    @PutMapping("/{wardId}")
    public ResponseEntity<HttpResponse> updateWard(@PathVariable long wardId, @RequestBody WardDTO dto) {
        Ward ward = wardService.updateWard(wardId, dto);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("WARD UPDATED SUCCESSFULLY.")
                .data(ward)
                .build());
    }

    @GetMapping
    public ResponseEntity<HttpResponse> getAllWards() {

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("WARDS RETRIEVED SUCCESSFULLY.")
                .data(wardService.getAllWards())
                .build());
    }


    @GetMapping("/all")
    public ResponseEntity<HttpResponse> getWards() {

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("WARDS RETRIEVED SUCCESSFULLY.")
                .data(wardService.getAllWards())
                .build());
    }


    @GetMapping("/{wardId}")
    public ResponseEntity<HttpResponse> getWardById(
            @PathVariable long wardId) {

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("WARD RETRIEVED SUCCESSFULLY.")
                .data(wardService.getWardById(wardId))
                .build());
    }

    @GetMapping("/district/{districtId}")
    public ResponseEntity<HttpResponse> getWardsByDistrict(
            @PathVariable Long districtId) {

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("WARDS RETRIEVED SUCCESSFULLY.")
                .data(wardService.getWardsByDistrict(districtId))
                .build());
    }

}