package ward.complain.portal.services.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ward.complain.portal.config.utils.JwtUtils;
import ward.complain.portal.exceptions.models.EmailExistsException;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.exceptions.models.PhoneNumberExistsException;
import ward.complain.portal.models.Citizen;
import ward.complain.portal.models.Role;
import ward.complain.portal.models.User;
import ward.complain.portal.models.Ward;
import ward.complain.portal.models.dtos.reponses.CitizenResponseDTO;
import ward.complain.portal.models.dtos.requests.CitizenRequestDTO;
import ward.complain.portal.repository.RoleRepository;
import ward.complain.portal.repository.UserRepository;
import ward.complain.portal.services.interfaces.AuthService;
import ward.complain.portal.services.interfaces.WardService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private  final WardService wardService;


    @Override
    public CitizenResponseDTO registerNewCitizen(CitizenRequestDTO request, long wardId) throws EmailExistsException, PhoneNumberExistsException {
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailExistsException("Email already taken");
        }


        // Check if phone number already exists
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new PhoneNumberExistsException("Phone number already taken");
        }

        Ward ward = wardService.findWardById(wardId);

        Citizen citizen = new Citizen();
        Role userRole = roleRepository.findByName("ROLE_CITIZEN").orElseGet(() -> {
            Role role = new Role();
            role.setName("ROLE_CITIZEN");
            return roleRepository.save(role);
        });

        citizen.setFirstName(request.getFirstName());
        citizen.setLastName(request.getLastName());
        citizen.setEmail(request.getEmail());
        citizen.setPassword(passwordEncoder.encode(request.getPassword()));
        citizen.setPhone(request.getPhone());
        citizen.setStreetName(ward.getWardName());
        citizen.setWard(ward);

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        citizen.setRoles(roles);

        userRepository.save(citizen);
        return mapToCitizenResponse(citizen);
    }



    private CitizenResponseDTO mapToCitizenResponse(Citizen citizen) {
        CitizenResponseDTO responseDTO = new CitizenResponseDTO();

        responseDTO.setId(citizen.getId());
        responseDTO.setUuid(citizen.getUuid());
        responseDTO.setFirstName(citizen.getFirstName());
        responseDTO.setLastName(citizen.getLastName());
        responseDTO.setEmail(citizen.getEmail());
        responseDTO.setPhone(citizen.getPhone());
        responseDTO.setStreetName(citizen.getStreetName());
        responseDTO.setCreatedAt(citizen.getCreatedAt());

        return responseDTO;
    }


    @Override
    public User getCurrentUser() throws ModelNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ModelNotFoundException("User not found"));
    }
}