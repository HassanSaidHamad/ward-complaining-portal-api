package ward.complain.portal.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}