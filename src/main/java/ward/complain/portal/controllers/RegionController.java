package ward.complain.portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ward.complain.portal.models.HttpResponse;
import ward.complain.portal.models.Region;
import ward.complain.portal.models.dtos.requests.RegionDTO;
import ward.complain.portal.services.interfaces.RegionService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/regions")
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class RegionController {
    private final RegionService regionService;

    @PostMapping
    public ResponseEntity<HttpResponse> createRegion(
            @RequestBody RegionDTO dto) {

        Region region = regionService.createRegion(dto);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.CREATED)
                .httpStatusCode(HttpStatus.CREATED.value())
                .message("REGION CREATED SUCCESSFULLY.")
                .data(region)
                .build());
    }


    @GetMapping("/all")
    public ResponseEntity<HttpResponse> getRegions() {

        List<Region> regions = regionService.getAllRegions();

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("REGIONS RETRIEVED SUCCESSFULLY.")
                .data(regions)
                .build());
    }


    @GetMapping
    public ResponseEntity<HttpResponse> getAllRegions() {

        List<Region> regions = regionService.getAllRegions();

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("REGIONS RETRIEVED SUCCESSFULLY.")
                .data(regions)
                .build());
    }

    @GetMapping("/{regionId}")
    public ResponseEntity<HttpResponse> getRegionById(
            @PathVariable Long regionId) {

        Region region = regionService.getRegionById(regionId);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("REGION RETRIEVED SUCCESSFULLY.")
                .data(region)
                .build());
    }

    @PutMapping("/{regionId}")
    public ResponseEntity<HttpResponse> updateRegion(
            @PathVariable Long regionId,
            @RequestBody RegionDTO dto) {

        Region region = regionService.updateRegion(regionId, dto);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("REGION UPDATED SUCCESSFULLY.")
                .data(region)
                .build());
    }

    @DeleteMapping("/{regionId}")
    public ResponseEntity<HttpResponse> deleteRegion(
            @PathVariable Long regionId) {

        regionService.deleteRegion(regionId);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("REGION DELETED SUCCESSFULLY.")
                .build());
    }
}