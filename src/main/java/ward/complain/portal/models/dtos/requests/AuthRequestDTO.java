package ward.complain.portal.models.dtos.requests;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;
}