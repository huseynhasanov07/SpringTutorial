package atl.academy.project.library.service;

import atl.academy.project.library.dto.AuthenticationRequest;
import atl.academy.project.library.dto.AuthenticationResponse;
import atl.academy.project.library.dto.RegisterRequest;
import atl.academy.project.library.model.Role;
import atl.academy.project.library.model.Token;
import atl.academy.project.library.model.TokenType;
import atl.academy.project.library.model.User;
import atl.academy.project.library.repository.TokenRepository;
import atl.academy.project.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenRepository tokenRepository;

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    private final JwtService service;

    private final AuthenticationManager manager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        var savedUser = repository.save(user);
        var jwtToken = service.generateToken(user);
        savedUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    private void savedUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = service.generateToken(user);
        savedUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

}
