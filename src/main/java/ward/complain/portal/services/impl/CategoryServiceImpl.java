package ward.complain.portal.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ward.complain.portal.models.ComplaintCategory;
import ward.complain.portal.models.Region;
import ward.complain.portal.models.dtos.requests.RegionDTO;
import ward.complain.portal.repository.ComplaintCategoryRepository;
import ward.complain.portal.services.interfaces.CategoryService;
import ward.complain.portal.services.interfaces.RegionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ComplaintCategoryRepository categoryRepository;


    @Override
    public ComplaintCategory createRegion(RegionDTO dto) {
        return null;
    }

    @Override
    public List<ComplaintCategory> getAllComplaintCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public ComplaintCategory getRegionById(Long regionId) {
        return null;
    }

    @Override
    public ComplaintCategory updateRegion(Long regionId, RegionDTO dto) {
        return null;
    }

    @Override
    public void deleteRegion(Long regionId) {

    }
}