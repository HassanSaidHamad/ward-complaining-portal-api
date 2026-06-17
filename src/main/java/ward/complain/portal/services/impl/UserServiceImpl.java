package ward.complain.portal.services.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ward.complain.portal.exceptions.models.EmailExistsException;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.exceptions.models.PhoneNumberExistsException;
import ward.complain.portal.models.*;
import ward.complain.portal.models.dtos.reponses.CitizenResponseDTO;
import ward.complain.portal.models.dtos.reponses.LeaderResponseDTO;
import ward.complain.portal.models.dtos.reponses.UserResponseDTO;
import ward.complain.portal.models.dtos.requests.LeaderRequestDTO;
import ward.complain.portal.repository.RoleRepository;
import ward.complain.portal.repository.UserRepository;
import ward.complain.portal.services.interfaces.UserService;
import ward.complain.portal.services.interfaces.WardService;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final WardService wardService;


    @Override
    public LeaderResponseDTO addNewLeader(LeaderRequestDTO dto, long wardId) throws EmailExistsException, PhoneNumberExistsException {
        // Check if email already exists
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailExistsException("Email already taken");
        }


        // Check if phone number already exists
        if (userRepository.existsByPhone(dto.getPhone())) {
            throw new PhoneNumberExistsException("Phone number already taken");
        }

        Ward ward = wardService.findWardById(wardId);


        Leader leader = new Leader();
        Role userRole = roleRepository.findByName("ROLE_LEADER").orElseGet(() -> {
            Role role = new Role();
            role.setName("ROLE_LEADER");
            return roleRepository.save(role);
        });

        leader.setFirstName(dto.getFirstName());
        leader.setLastName(dto.getLastName());
        leader.setEmail(dto.getEmail());
        leader.setPassword(passwordEncoder.encode(dto.getPassword()));
        leader.setPhone(dto.getPhone());
        leader.setPosition(dto.getPosition());
        leader.setWard(ward);

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        leader.setRoles(roles);

        userRepository.save(leader);
        return mapToLeaderResponse(leader);
    }

    @Override
    public LeaderResponseDTO updateLeader(LeaderRequestDTO dto, long leaderId) throws EmailExistsException, PhoneNumberExistsException, IOException, ModelNotFoundException {

        Leader leader = (Leader) findById(leaderId);

        // 2. Check email uniqueness (if changed)
        if (!leader.getEmail().equals(dto.getEmail()) &&
                userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailExistsException("Email already taken");
        }

        // 3. Check phone uniqueness (if changed)
        if (!leader.getPhone().equals(dto.getPhone()) &&
                userRepository.existsByPhone(dto.getPhone())) {
            throw new PhoneNumberExistsException("Phone number already taken");
        }

        leader.setFirstName(dto.getFirstName());
        leader.setLastName(dto.getLastName());
        leader.setEmail(dto.getEmail());
        leader.setPhone(dto.getPhone());
        leader.setPosition(dto.getPosition());

        userRepository.save(leader);
        return mapToLeaderResponse(leader);
    }


    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    @Override
    public List<LeaderResponseDTO> getAllLeaders() {
        return userRepository.findLeadersByRoleName("ROLE_LEADER")
                .stream()
                .map(this::mapToLeaderResponse)
                .toList();
    }

    @Override
    public List<CitizenResponseDTO> getAllCitizens() {
        return userRepository.findCitizensByRoleName("ROLE_CITIZEN")
                .stream()
                .map(this::mapToCitizenResponse)
                .toList();
    }


    @Override
    public User findById(long id) throws ModelNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("User not found with id: " + id));
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    //    Responses
    private UserResponseDTO mapToUserResponse(User tourist) {
        UserResponseDTO responseDTO = new UserResponseDTO();

        responseDTO.setId(tourist.getId());
        responseDTO.setUuid(tourist.getUuid());
        responseDTO.setFirstName(tourist.getFirstName());
        responseDTO.setLastName(tourist.getLastName());
        responseDTO.setEmail(tourist.getEmail());
        responseDTO.setPhone(tourist.getPhone());
        responseDTO.setRoles(tourist.getRoles().stream().map(Role::getName).toList());
        responseDTO.setCreatedAt(tourist.getCreatedAt());
        responseDTO.setDeletedAt(tourist.getDeletedAt());

        return responseDTO;
    }


    private LeaderResponseDTO mapToLeaderResponse(Leader leader) {
        LeaderResponseDTO responseDTO = new LeaderResponseDTO();

        responseDTO.setId(leader.getId());
        responseDTO.setUuid(leader.getUuid());
        responseDTO.setFirstName(leader.getFirstName());
        responseDTO.setLastName(leader.getLastName());
        responseDTO.setEmail(leader.getEmail());
        responseDTO.setPhone(leader.getPhone());
        responseDTO.setRoles(leader.getRoles().stream().map(Role::getName).toList());
        responseDTO.setPosition(leader.getPosition());
        responseDTO.setCreatedAt(leader.getCreatedAt());

        return responseDTO;
    }


    private CitizenResponseDTO mapToCitizenResponse(Citizen citizen) {
        CitizenResponseDTO responseDTO = new CitizenResponseDTO();

        responseDTO.setId(citizen.getId());
        responseDTO.setUuid(citizen.getUuid());
        responseDTO.setFirstName(citizen.getFirstName());
        responseDTO.setLastName(citizen.getLastName());
        responseDTO.setEmail(citizen.getEmail());
        responseDTO.setPhone(citizen.getPhone());
        responseDTO.setRoles(citizen.getRoles().stream().map(Role::getName).toList());
        responseDTO.setStreetName(citizen.getStreetName());
        responseDTO.setCreatedAt(citizen.getCreatedAt());

        return responseDTO;
    }

}