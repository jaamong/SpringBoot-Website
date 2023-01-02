package com.mysite.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public SiteUser create(String username, String email, String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        SiteUser user = SiteUser.builder()
                                .username(username)
                                .email(email)
                                .password(encoder.encode(password))
                                .build();

        userRepository.save(user);
        return user;
    }
}
