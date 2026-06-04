package ward.complain.portal.services.interfaces;

import ward.complain.portal.models.Ward;
import ward.complain.portal.models.dtos.reponses.WardResponseDTO;
import ward.complain.portal.models.dtos.requests.WardDTO;

import java.util.List;

public interface WardService {

    Ward createWard(WardDTO dto);

    List<WardResponseDTO> getAllWards();

    WardResponseDTO getWardById(Long wardId);

    Ward findWardById(Long wardId);

    Ward updateWard(Long wardId, WardDTO dto);

    void deleteWard(Long wardId);

    List<Ward> getWardsByDistrict(Long districtId);
}