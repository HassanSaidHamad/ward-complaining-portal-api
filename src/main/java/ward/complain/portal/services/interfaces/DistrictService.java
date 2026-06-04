package ward.complain.portal.services.interfaces;

import ward.complain.portal.models.District;
import ward.complain.portal.models.dtos.reponses.DistrictResponseDTO;
import ward.complain.portal.models.dtos.requests.DistrictDTO;

import java.util.List;

public interface DistrictService {

    District createDistrict(DistrictDTO dto);

    List<DistrictResponseDTO> getAllDistricts();

    DistrictResponseDTO getDistrictById(long districtId);

    District findDistrictById(long districtId);

    District updateDistrict(long districtId, DistrictDTO dto);

    void deleteDistrict(long districtId);

    List<District> getDistrictsByRegion(long regionId);

}