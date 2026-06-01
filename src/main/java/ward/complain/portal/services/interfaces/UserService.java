package ward.complain.portal.services.interfaces;


import ward.complain.portal.exceptions.models.EmailExistsException;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.exceptions.models.PhoneNumberExistsException;
import ward.complain.portal.models.User;
import ward.complain.portal.models.dtos.reponses.CitizenResponseDTO;
import ward.complain.portal.models.dtos.reponses.LeaderResponseDTO;
import ward.complain.portal.models.dtos.reponses.UserResponseDTO;
import ward.complain.portal.models.dtos.requests.LeaderRequestDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    LeaderResponseDTO addNewLeader(LeaderRequestDTO dto, long wardId) throws EmailExistsException, PhoneNumberExistsException, IOException;

//    UpdateProviderResponseDTO addProviderByAdmin(String firstName, String lastName, String email, String phone, String password, String businessName, String description, MultipartFile document) throws EmailExistsException, PhoneNumberExistsException, IOException;

    List<UserResponseDTO> getAllUsers();
    List<LeaderResponseDTO> getAllLeaders();
    List<CitizenResponseDTO> getAllCitizens();
    User findById(long id) throws ModelNotFoundException;

    Optional<User> findByEmail(String email);
//    BlockUserResponseDTO blockUser(long userId) throws ModelNotFoundException;
//    BlockUserResponseDTO unblockUser(long userId) throws ModelNotFoundException;
}
