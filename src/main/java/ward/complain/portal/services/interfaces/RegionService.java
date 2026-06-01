package ward.complain.portal.services.interfaces;

import ward.complain.portal.models.Region;
import ward.complain.portal.models.dtos.requests.RegionDTO;

import java.util.List;

public interface RegionService {

    Region createRegion(RegionDTO dto);

    List<Region> getAllRegions();

    Region getRegionById(Long regionId);

    Region updateRegion(Long regionId, RegionDTO dto);

    void deleteRegion(Long regionId);

}