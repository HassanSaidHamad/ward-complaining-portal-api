package ward.complain.portal.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.models.HttpResponse;
import ward.complain.portal.models.User;
import ward.complain.portal.models.dtos.reponses.CitizenResponseDTO;
import ward.complain.portal.models.dtos.reponses.LeaderResponseDTO;
import ward.complain.portal.models.dtos.reponses.UserResponseDTO;
import ward.complain.portal.models.dtos.requests.LeaderRequestDTO;
import ward.complain.portal.services.interfaces.UserService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/add-leader/ward/{wardId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HttpResponse> addNewLeader(@RequestBody LeaderRequestDTO dto, @PathVariable long wardId) throws Exception {

        LeaderResponseDTO leader = userService.addNewLeader(dto, wardId);

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(new Date())
                        .httpStatus(HttpStatus.CREATED)
                        .httpStatusCode(HttpStatus.CREATED.value())
                        .message("LEADER REGISTERED SUCCESSFULLY")
                        .data(leader)
                        .build()
        );
    }



    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HttpResponse> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("USERS RETRIEVED SUCCESSFULLY.")
                .data(users)
                .build());
    }


    @GetMapping("/leaders")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HttpResponse> getAllLeaders() {
        List<LeaderResponseDTO> allLeaders = userService.getAllLeaders();

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("LEADERS RETRIEVED SUCCESSFULLY.")
                .data(allLeaders)
                .build());
    }


    @GetMapping("/citizens")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HttpResponse> getAllCitizens() {
        List<CitizenResponseDTO> allCitizens = userService.getAllCitizens();

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("CITIZENS RETRIEVED SUCCESSFULLY.")
                .data(allCitizens)
                .build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> findById(@PathVariable long id) throws ModelNotFoundException {
        User user = userService.findById(id);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .httpStatusCode(HttpStatus.OK.value())
                .message("USER RETRIEVED SUCCESSFULLY.")
                .data(user)
                .build());
    }


//    @DeleteMapping("/block/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<HttpResponse> blockUser(@PathVariable long id) throws ModelNotFoundException {
//        BlockUserResponseDTO user = userService.blockUser(id);
//
//        return ResponseEntity.ok(HttpResponse.builder()
//                .timeStamp(new Date())
//                .httpStatus(HttpStatus.OK)
//                .httpStatusCode(HttpStatus.OK.value())
//                .message("USER BLOCKED SUCCESSFULLY.")
//                .data(user)
//                .build());
//    }


//    @DeleteMapping("/unblock/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<HttpResponse> unblockUser(@PathVariable long id) throws ModelNotFoundException {
//        BlockUserResponseDTO user = userService.unblockUser(id);
//
//        return ResponseEntity.ok(HttpResponse.builder()
//                .timeStamp(new Date())
//                .httpStatus(HttpStatus.OK)
//                .httpStatusCode(HttpStatus.OK.value())
//                .message("USER UNBLOCKED SUCCESSFULLY.")
//                .data(user)
//                .build());
//    }
}
