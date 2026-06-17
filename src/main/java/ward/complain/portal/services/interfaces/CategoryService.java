package ward.complain.portal.services.interfaces;

import ward.complain.portal.models.ComplaintCategory;
import ward.complain.portal.models.dtos.requests.CategoryDTO;
import ward.complain.portal.models.dtos.requests.RegionDTO;

import java.util.List;

public interface CategoryService {

    ComplaintCategory createCategory(CategoryDTO dto);


    List<ComplaintCategory> getAllComplaintCategories();

    ComplaintCategory getCategoryById(Long categoryId);

    ComplaintCategory updateCategory(Long categoryId, CategoryDTO dto);

    void deleteCategory(Long categoryId);

}