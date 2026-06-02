package ward.complain.portal.models.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ward.complain.portal.models.Region;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictResponseDTO {
    private long districtId;
    private String districtName;
    private Region region;
}