package ward.complain.portal.models.dtos.reponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenResponseDTO {
    private long id;
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<String> roles;
    private String streetName;
    private LocalDateTime createdAt;
}