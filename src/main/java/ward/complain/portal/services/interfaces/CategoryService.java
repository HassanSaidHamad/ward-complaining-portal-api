package ward.complain.portal.services.interfaces;

import ward.complain.portal.models.ComplaintCategory;
import ward.complain.portal.models.Region;
import ward.complain.portal.models.dtos.requests.RegionDTO;

import java.util.List;

public interface CategoryService {

    ComplaintCategory createRegion(RegionDTO dto);

    List<ComplaintCategory> getAllComplaintCategories();

    ComplaintCategory getRegionById(Long regionId);

    ComplaintCategory updateRegion(Long regionId, RegionDTO dto);

    void deleteRegion(Long regionId);

}