package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.JwtResponse;
import com.personal.beshophihi.dto.LoginDTO;
import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.dto.UserDTO;
import com.personal.beshophihi.security.jwt.JwtUtils;
import com.personal.beshophihi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );
            String token = jwtUtils.generateToken(authentication);
            return new ResponseEntity<>(JwtResponse.builder()
                    .token(token)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseMessage.builder()
                    .status("FAILED")
                    .message("Invalid username or password !")
                    .build(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseMessage> doSignUp (@RequestBody  UserDTO userDTO){
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("User created successfully.")
                        .data(userService.addNewUser(userDTO))
                        .build(),
                HttpStatus.CREATED);
    }


}
