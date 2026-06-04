package ward.complain.portal.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ward.complain.portal.models.District;
import ward.complain.portal.models.Ward;
import ward.complain.portal.models.dtos.reponses.DistrictResponseDTO;
import ward.complain.portal.models.dtos.reponses.WardResponseDTO;
import ward.complain.portal.models.dtos.requests.WardDTO;
import ward.complain.portal.repository.DistrictRepository;
import ward.complain.portal.repository.WardRepository;
import ward.complain.portal.services.interfaces.WardService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;
    private final DistrictRepository districtRepository;

    @Override
    public Ward createWard(WardDTO dto) {

        District district = districtRepository.findById(dto.getDistrictId())
                .orElseThrow(() ->
                        new RuntimeException("DISTRICT NOT FOUND"));

        Ward ward = Ward.builder()
                .wardName(dto.getWardName())
                .district(district)
                .build();

        return wardRepository.save(ward);
    }

    @Override
    public List<WardResponseDTO> getAllWards() {
        return wardRepository.findAll()
                .stream()
                .map(this::mapToWardResponse)
                .toList();
    }

    @Override
    public WardResponseDTO getWardById(Long wardId) {

        Ward ward = wardRepository.findById(wardId)
                .orElseThrow(() ->
                        new RuntimeException("WARD NOT FOUND"));

        return mapToWardResponse(ward);
    }

    @Override
    public Ward findWardById(Long wardId) {
        return wardRepository.findById(wardId)
                .orElseThrow(() ->
                        new RuntimeException("WARD NOT FOUND"));
    }

    @Override
    public Ward updateWard(Long wardId, WardDTO dto) {

        Ward ward = findWardById(wardId);

        District district = districtRepository.findById(dto.getDistrictId())
                .orElseThrow(() ->
                        new RuntimeException("DISTRICT NOT FOUND"));

        ward.setWardName(dto.getWardName());
        ward.setDistrict(district);

        return wardRepository.save(ward);
    }

    @Override
    public void deleteWard(Long wardId) {

        Ward ward = findWardById(wardId);

        wardRepository.delete(ward);
    }

    @Override
    public List<Ward> getWardsByDistrict(Long districtId) {
        return wardRepository.findByDistrictDistrictId(districtId);
    }


    private WardResponseDTO mapToWardResponse(Ward ward) {
        WardResponseDTO responseDTO = new WardResponseDTO();

        responseDTO.setWardId(ward.getWardId());
        responseDTO.setWardName(ward.getWardName());
        responseDTO.setDistrict(ward.getDistrict());
        responseDTO.setRegion(ward.getDistrict().getRegion());

        return responseDTO;
    }
}