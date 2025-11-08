package org.dd.bre.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dd.bre.Dto.LoginRequest;
import org.dd.bre.Dto.LoginResponse;
import org.dd.bre.Dto.RegisterRequest;
import org.dd.bre.Exception.PasswordNotMatch;
import org.dd.bre.Exception.UserAlreadyExists;
import org.dd.bre.Repo.UserRepo;
import org.dd.bre.Security.JwtUtil;
import org.dd.bre.Security.Service.CustomUserDetailsService;
import org.dd.bre.model.Cart;
import org.dd.bre.model.User;
import org.dd.bre.model.UserRole;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final CustomUserDetailsService userService;
    private final JwtUtil jwtUtil;

    public LoginResponse Login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserDetails user = userService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(user);

        return new LoginResponse(token);
    }

    @Transactional
    public String Register(RegisterRequest registerRequest) {

        checkIfEmailExists(registerRequest.getEmail());

        checkIfUsernameExists(registerRequest.getUsername());

        String password = registerRequest.getPassword();
        String confirmPassword = registerRequest.getConfirmPassword();

        checkIfPasswordMatch(confirmPassword, password);

        User newUser = buildUser(registerRequest,UserRole.CUSTOMER);

        userRepo.save(newUser);

        return "user registered successfully";
    }

    @Transactional
    public String createAdmin(RegisterRequest registerRequest) {

        checkIfEmailExists(registerRequest.getEmail());

        checkIfUsernameExists(registerRequest.getUsername());

        String password = registerRequest.getPassword();
        String confirmPassword = registerRequest.getConfirmPassword();

        checkIfPasswordMatch(confirmPassword, password);

        User admin = buildUser(registerRequest, UserRole.ADMIN);
        userRepo.save(admin);

        return "Admin created successfully";
    }

    private User buildUser(RegisterRequest request, UserRole role) {
        String[] nameParts = request.getFullName().trim().split(" ", 2);
        String firstName = nameParts[0];
        String lastName = (nameParts.length > 1) ? nameParts[1] : null;

        return User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .firstName(firstName)
                .lastName(lastName)
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .user_role(role)
                .cart(new Cart())
                .build();
    }


    private void checkIfEmailExists(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExists("Email already exists");
        }

    }
    private void checkIfUsernameExists(String username) {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            throw new UserAlreadyExists("Username already exists");
        }
    }
    private void checkIfPasswordMatch(String oldPassword, String newPassword) {
        if (!oldPassword.equals(newPassword)) {
            throw new PasswordNotMatch("Passwords do not match");
        }
    }
}
