package ward.complain.portal.services.interfaces;

import ward.complain.portal.models.Ward;
import ward.complain.portal.models.dtos.requests.WardDTO;

import java.util.List;

public interface WardService {

    Ward createWard(WardDTO dto);

    List<Ward> getAllWards();

    Ward getWardById(Long wardId);

    Ward updateWard(Long wardId, WardDTO dto);

    void deleteWard(Long wardId);

    List<Ward> getWardsByDistrict(Long districtId);
}