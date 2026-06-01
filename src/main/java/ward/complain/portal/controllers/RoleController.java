package ward.complain.portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.models.HttpResponse;
import ward.complain.portal.models.Role;
import ward.complain.portal.models.dtos.requests.RoleDTO;
import ward.complain.portal.services.interfaces.RoleService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<HttpResponse> createRole(@RequestBody RoleDTO dto) {
        Role role = roleService.createRole(dto);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("ROLE CREATED SUCCESSFULLY.")
                .data(role)
                .build());
    }

    @GetMapping
    public ResponseEntity<HttpResponse> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("ROLE RETRIEVED SUCCESSFULLY.")
                .data(roles)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> getRoleById(@PathVariable Long id) throws ModelNotFoundException {
        Role role = roleService.findRoleById(id);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("ROLE RETRIEVED SUCCESSFULLY.")
                .data(role)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> updateRole(@PathVariable Long id, @RequestBody RoleDTO dto) throws ModelNotFoundException {
        Role updateRole = roleService.updateRole(id, dto);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("ROLE UPDATED SUCCESSFULLY.")
                .data(updateRole)
                .build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteRole(@PathVariable long id) throws ModelNotFoundException {
        roleService.deleteRole(id);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("ROLE DELETED SUCCESSFULLY.")
                .build());
    }
}
