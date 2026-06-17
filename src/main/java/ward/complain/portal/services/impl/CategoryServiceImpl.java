package ward.complain.portal.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ward.complain.portal.models.ComplaintCategory;
import ward.complain.portal.models.Region;
import ward.complain.portal.models.dtos.requests.CategoryDTO;
import ward.complain.portal.models.dtos.requests.RegionDTO;
import ward.complain.portal.repository.ComplaintCategoryRepository;
import ward.complain.portal.services.interfaces.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ComplaintCategoryRepository categoryRepository;


    @Override
    public ComplaintCategory createCategory(CategoryDTO dto) {
        ComplaintCategory category = ComplaintCategory.builder()
                .categoryName(dto.getCategoryName())
                .build();

        return categoryRepository.save(category);
    }


    @Override
    public List<ComplaintCategory> getAllComplaintCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public ComplaintCategory getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("CATEGORY NOT FOUND"));
    }

    @Override
    public ComplaintCategory updateCategory(Long categoryId, CategoryDTO dto) {
        ComplaintCategory category = getCategoryById(categoryId);

        category.setCategoryName(dto.getCategoryName());

        return categoryRepository.save(category);
    }


    @Override
    public void deleteCategory(Long categoryId) {
        ComplaintCategory region = getCategoryById(categoryId);

        categoryRepository.delete(region);
    }
}