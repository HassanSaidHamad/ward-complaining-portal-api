package ward.complain.portal.services.interfaces;

import ward.complain.portal.models.District;
import ward.complain.portal.models.dtos.reponses.DistrictResponseDTO;
import ward.complain.portal.models.dtos.requests.DistrictDTO;

import java.util.List;

public interface DistrictService {

    District createDistrict(DistrictDTO dto);

    List<DistrictResponseDTO> getAllDistricts();

    District getDistrictById(Long districtId);

    District updateDistrict(Long districtId, DistrictDTO dto);

    void deleteDistrict(Long districtId);

    List<District> getDistrictsByRegion(Long regionId);

}