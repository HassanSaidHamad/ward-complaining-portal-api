package ward.complain.portal.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.models.Role;
import ward.complain.portal.models.dtos.requests.RoleDTO;
import ward.complain.portal.repository.RoleRepository;
import ward.complain.portal.services.interfaces.RoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role createRole(RoleDTO dto) {
        if (roleRepository.existsByNameIgnoreCase(dto.getName())) {
            throw new IllegalArgumentException("Role with the same name already exists.");
        }

        Role role = new Role();
        role.setName(dto.getName());
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(long id, RoleDTO dto) throws ModelNotFoundException {
        Role role = findRoleById(id);

        role.setName(dto.getName());
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleById(long id) throws ModelNotFoundException {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Role not found with id: " + id));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    @Override
    public void deleteRole(long id) throws ModelNotFoundException {
        Role role = findRoleById(id);
        roleRepository.delete(role);
    }
}
