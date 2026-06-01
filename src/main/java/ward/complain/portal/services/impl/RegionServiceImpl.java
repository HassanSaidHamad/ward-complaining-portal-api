package ward.complain.portal.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ward.complain.portal.models.Region;
import ward.complain.portal.models.dtos.requests.RegionDTO;
import ward.complain.portal.repository.RegionRepository;
import ward.complain.portal.services.interfaces.RegionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public Region createRegion(RegionDTO dto) {
        Region region = Region.builder()
                .regionName(dto.getRegionName())
                .build();

        return regionRepository.save(region);
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region getRegionById(Long regionId) {

        return regionRepository.findById(regionId)
                .orElseThrow(() ->
                        new RuntimeException("REGION NOT FOUND"));
    }

    @Override
    public Region updateRegion(Long regionId, RegionDTO dto) {

        Region region = getRegionById(regionId);

        region.setRegionName(dto.getRegionName());

        return regionRepository.save(region);
    }

    @Override
    public void deleteRegion(Long regionId) {

        Region region = getRegionById(regionId);

        regionRepository.delete(region);
    }
}