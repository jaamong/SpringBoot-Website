package com.mysite.sbb.user;

import com.mysite.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public SiteUser create(String username, String email, String password) {
        SiteUser user = SiteUser.builder()
                .username(username)
                .email(email)
                .password(encoder.encode(password))
                .build();

        userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> {
            throw new DataNotFoundException("siteUser not found");
        });
    }
}
