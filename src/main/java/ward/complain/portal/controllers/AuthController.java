package ward.complain.portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ward.complain.portal.config.utils.JwtUtils;
import ward.complain.portal.exceptions.models.EmailExistsException;
import ward.complain.portal.exceptions.models.ModelNotFoundException;
import ward.complain.portal.exceptions.models.PhoneNumberExistsException;
import ward.complain.portal.models.HttpResponse;
import ward.complain.portal.models.User;
import ward.complain.portal.models.dtos.reponses.CitizenResponseDTO;
import ward.complain.portal.models.dtos.requests.AuthRequestDTO;
import ward.complain.portal.models.dtos.requests.CitizenRequestDTO;
import ward.complain.portal.repository.UserRepository;
import ward.complain.portal.services.impl.AuthServiceImpl;
import ward.complain.portal.services.interfaces.AuthService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final AuthServiceImpl authServiceImpl;
    private final AuthService authService;


//    @PostMapping("/login")
//    public Map<String, Object> login(@RequestBody AuthRequestDTO request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//
//        User loggedIn = userRepository.findByEmail(request.getEmail()).orElseThrow();

    /// /        if (!loggedIn.isEnabled()) {
    /// /            throw new DisabledException("Account is not yet enabled. Please verify your email.");
    /// /        }
//
//        String token = jwtUtils.generateAccessToken(loggedIn);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("token", token);
//        response.put("user", loggedIn);
//        return response;
//    }
    
    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody AuthRequestDTO request)
            throws ModelNotFoundException {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(HttpResponse.builder()
                            .timeStamp(new Date())
                            .httpStatus(HttpStatus.UNAUTHORIZED)
                            .httpStatusCode(HttpStatus.UNAUTHORIZED.value())
                            .message("Invalid email or password")
                            .build());
        }

        User loggedIn = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ModelNotFoundException("User not found"));

        // role check
        boolean isAllowed = loggedIn.getRoles().stream()
                .anyMatch(role ->
                        role.getName().equals("ROLE_ADMIN") ||
                                role.getName().equals("ROLE_LEADER") ||
                                role.getName().equals("ROLE_CITIZEN")
                );

        if (!isAllowed) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(HttpResponse.builder()
                            .timeStamp(new Date())
                            .httpStatus(HttpStatus.FORBIDDEN)
                            .httpStatusCode(HttpStatus.FORBIDDEN.value())
                            .message("Access denied")
                            .build());
        }


        String token = jwtUtils.generateAccessToken(loggedIn);

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(new Date())
                        .httpStatus(HttpStatus.OK)
                        .httpStatusCode(HttpStatus.OK.value())
                        .message("Login successful")
                        .data(Map.of(
                                "token", token,
                                "user", loggedIn
                        ))
                        .build()
        );
    }


    @PostMapping("/login-citizen")
    public Map<String, Object> loginTourist(@RequestBody AuthRequestDTO request) throws ModelNotFoundException {

        // 1. Authenticate credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. Fetch user
        User loggedIn = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ModelNotFoundException("User not found"));

        // 3. Check role (ONLY TOURIST ALLOWED)
        boolean isCitizen = loggedIn.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ROLE_CITIZEN"));

        if (!isCitizen) {
            throw new ModelNotFoundException("Access denied: Only citizen can login here");
        }


        // 5. Generate token
        String token = jwtUtils.generateAccessToken(loggedIn);

        // 6. Build response
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", loggedIn);

        return response;
    }


    @PostMapping("/register-citizen/ward/{wardId}")
    public ResponseEntity<CitizenResponseDTO> registerNewUser(@RequestBody CitizenRequestDTO request, @PathVariable long wardId) throws PhoneNumberExistsException, EmailExistsException {
        return ResponseEntity.ok(authServiceImpl.registerNewCitizen(request, wardId));
    }


}
