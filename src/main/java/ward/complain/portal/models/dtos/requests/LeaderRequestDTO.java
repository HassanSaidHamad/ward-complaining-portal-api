package ward.complain.portal.models.dtos.requests;

import lombok.Data;

@Data
public class LeaderRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String position;
}