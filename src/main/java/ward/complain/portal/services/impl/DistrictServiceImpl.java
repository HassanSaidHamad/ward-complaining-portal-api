package ward.complain.portal.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ward.complain.portal.models.Complaint;
import ward.complain.portal.models.District;
import ward.complain.portal.models.Region;
import ward.complain.portal.models.dtos.reponses.ComplaintResponseDTO;
import ward.complain.portal.models.dtos.reponses.DistrictResponseDTO;
import ward.complain.portal.models.dtos.requests.DistrictDTO;
import ward.complain.portal.repository.DistrictRepository;
import ward.complain.portal.repository.RegionRepository;
import ward.complain.portal.services.interfaces.DistrictService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    @Override
    public District createDistrict(DistrictDTO dto) {

        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() ->
                        new RuntimeException("REGION NOT FOUND"));

        District district = District.builder()
                .districtName(dto.getDistrictName())
                .region(region)
                .build();

        return districtRepository.save(district);
    }

    @Override
    public List<DistrictResponseDTO> getAllDistricts() {
        return districtRepository.findAll()
                .stream()
                .map(this::mapToDistrictResponse)
                .toList();
    }

    @Override
    public DistrictResponseDTO getDistrictById(long districtId) {

        District district = districtRepository.findById(districtId)
                .orElseThrow(() ->
                        new RuntimeException("DISTRICT NOT FOUND"));

        return mapToDistrictResponse(district);
    }

    @Override
    public District findDistrictById(long districtId) {
        return districtRepository.findById(districtId)
                .orElseThrow(() ->
                        new RuntimeException("DISTRICT NOT FOUND"));
    }

    @Override
    public District updateDistrict(long districtId, DistrictDTO dto) {

        District district = findDistrictById(districtId);

        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() ->
                        new RuntimeException("REGION NOT FOUND"));

        district.setDistrictName(dto.getDistrictName());
        district.setRegion(region);

        return districtRepository.save(district);
    }

    @Override
    public void deleteDistrict(long districtId) {

        District district = findDistrictById(districtId);

        districtRepository.delete(district);
    }

    @Override
    public List<District> getDistrictsByRegion(long regionId) {
        return districtRepository.findByRegionRegionId(regionId);
    }


    private DistrictResponseDTO mapToDistrictResponse(District district) {
        DistrictResponseDTO responseDTO = new DistrictResponseDTO();

        responseDTO.setDistrictId(district.getDistrictId());
        responseDTO.setDistrictName(district.getDistrictName());
        responseDTO.setRegion(district.getRegion());

        return responseDTO;
    }
}