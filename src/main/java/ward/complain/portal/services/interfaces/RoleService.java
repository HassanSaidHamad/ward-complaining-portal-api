package ward.complain.portal.services.interfaces;

import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.models.Role;
import ward.complain.portal.models.dtos.requests.RoleDTO;

import java.util.List;

public interface RoleService {
    Role createRole(RoleDTO dto);
    Role updateRole(long id, RoleDTO dto) throws ModelNotFoundException;
    Role findRoleById(long id) throws ModelNotFoundException;
    List<Role> getAllRoles();
    void deleteRole(long id) throws ModelNotFoundException;
}
