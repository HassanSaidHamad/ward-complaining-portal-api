package ward.complain.portal.services.interfaces;


import ward.complain.portal.exceptions.models.EmailExistsException;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.exceptions.models.PhoneNumberExistsException;
import ward.complain.portal.models.User;
import ward.complain.portal.models.dtos.reponses.CitizenResponseDTO;
import ward.complain.portal.models.dtos.requests.CitizenRequestDTO;

public interface AuthService {
    CitizenResponseDTO registerNewCitizen(CitizenRequestDTO request, long wardId) throws EmailExistsException, PhoneNumberExistsException;

    User getCurrentUser() throws ModelNotFoundException;
}
