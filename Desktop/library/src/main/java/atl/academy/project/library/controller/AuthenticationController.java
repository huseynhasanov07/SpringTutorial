package atl.academy.project.library.controller;

import atl.academy.project.library.dto.AuthenticationRequest;
import atl.academy.project.library.dto.AuthenticationResponse;
import atl.academy.project.library.dto.RegisterRequest;
import atl.academy.project.library.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public AuthenticationResponse register(RegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return service.authenticate(request);
    }


}
