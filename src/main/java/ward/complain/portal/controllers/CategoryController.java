package ward.complain.portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ward.complain.portal.models.ComplaintCategory;
import ward.complain.portal.models.HttpResponse;
import ward.complain.portal.models.Region;
import ward.complain.portal.models.dtos.requests.RegionDTO;
import ward.complain.portal.services.interfaces.CategoryService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

//    @PostMapping
//    public ResponseEntity<HttpResponse> createRegion(
//            @RequestBody RegionDTO dto) {
//
//        Region region = categoryService.createRegion(dto);
//
//        return ResponseEntity.ok(HttpResponse.builder()
//                .timeStamp(new Date())
//                .httpStatus(HttpStatus.CREATED)
//                .httpStatusCode(HttpStatus.CREATED.value())
//                .message("REGION CREATED SUCCESSFULLY.")
//                .data(region)
//                .build());
//    }

    @GetMapping
    public ResponseEntity<HttpResponse> getAllRegions() {

        List<ComplaintCategory> regions = categoryService.getAllComplaintCategories();

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("CATEGORIES RETRIEVED SUCCESSFULLY.")
                .data(regions)
                .build());
    }

//    @GetMapping("/{regionId}")
//    public ResponseEntity<HttpResponse> getRegionById(
//            @PathVariable Long regionId) {
//
//        Region region = categoryService.getRegionById(regionId);
//
//        return ResponseEntity.ok(HttpResponse.builder()
//                .timeStamp(new Date())
//                .httpStatus(HttpStatus.OK)
//                .httpStatusCode(HttpStatus.OK.value())
//                .message("REGION RETRIEVED SUCCESSFULLY.")
//                .data(region)
//                .build());
//    }

//    @PutMapping("/{regionId}")
//    public ResponseEntity<HttpResponse> updateRegion(
//            @PathVariable Long regionId,
//            @RequestBody RegionDTO dto) {
//
//        Region region = categoryService.updateRegion(regionId, dto);
//
//        return ResponseEntity.ok(HttpResponse.builder()
//                .timeStamp(new Date())
//                .httpStatus(HttpStatus.OK)
//                .httpStatusCode(HttpStatus.OK.value())
//                .message("REGION UPDATED SUCCESSFULLY.")
//                .data(region)
//                .build());
//    }

//    @DeleteMapping("/{regionId}")
//    public ResponseEntity<HttpResponse> deleteRegion(
//            @PathVariable Long regionId) {
//
//        categoryService.deleteRegion(regionId);
//
//        return ResponseEntity.ok(HttpResponse.builder()
//                .timeStamp(new Date())
//                .httpStatus(HttpStatus.OK)
//                .httpStatusCode(HttpStatus.OK.value())
//                .message("REGION DELETED SUCCESSFULLY.")
//                .build());
//    }
}