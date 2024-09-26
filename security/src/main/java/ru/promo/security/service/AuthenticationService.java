package ru.promo.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo.security.controller.domain.SignInRequest;
import ru.promo.security.controller.domain.SignUpRequest;
import ru.promo.security.controller.domain.TokenResponse;
import ru.promo.security.domain.Role;
import ru.promo.security.domain.entity.Employee;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final EmployeeService employeeService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public TokenResponse signUp(SignUpRequest request) {

        var user = Employee.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();

        employeeService.create(user);

        var jwt = jwtService.generateToken(user);
        return new TokenResponse(jwt);
    }

    public TokenResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = employeeService.userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new TokenResponse(jwt);
    }
}
