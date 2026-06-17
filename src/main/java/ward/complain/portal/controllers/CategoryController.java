package ward.complain.portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ward.complain.portal.models.ComplaintCategory;
import ward.complain.portal.models.HttpResponse;
import ward.complain.portal.models.Region;
import ward.complain.portal.models.dtos.requests.CategoryDTO;
import ward.complain.portal.models.dtos.requests.RegionDTO;
import ward.complain.portal.services.interfaces.CategoryService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<HttpResponse> createCategory(
            @RequestBody CategoryDTO dto) {

        ComplaintCategory region = categoryService.createCategory(dto);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.CREATED)
                .httpStatusCode(HttpStatus.CREATED.value())
                .message("CATEGORY CREATED SUCCESSFULLY.")
                .data(region)
                .build());
    }

    @GetMapping
    public ResponseEntity<HttpResponse> getAllRegions() {

        List<ComplaintCategory> categories = categoryService.getAllComplaintCategories();

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("CATEGORIES RETRIEVED SUCCESSFULLY.")
                .data(categories)
                .build());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<HttpResponse> getRegionById(
            @PathVariable Long categoryId) {

        ComplaintCategory category = categoryService.getCategoryById(categoryId);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("CATEGORY RETRIEVED SUCCESSFULLY.")
                .data(category)
                .build());
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<HttpResponse> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryDTO dto) {

        ComplaintCategory category = categoryService.updateCategory(categoryId, dto);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("CATEGORY UPDATED SUCCESSFULLY.")
                .data(category)
                .build());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<HttpResponse> deleteCategory(
            @PathVariable Long categoryId) {

        categoryService.deleteCategory(categoryId);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("CATEGORY DELETED SUCCESSFULLY.")
                .build());
    }
}