package ward.complain.portal.models.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ward.complain.portal.models.District;
import ward.complain.portal.models.Region;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WardResponseDTO {
    private long wardId;
    private String wardName;
    private District district;
    private Region region;
}