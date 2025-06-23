package org.example.clonezalo.security;

import lombok.RequiredArgsConstructor;
import org.example.clonezalo.exception.NotFoundException;
import org.example.clonezalo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
       return userRepository.findByPhone(phone).orElseThrow(()->new NotFoundException("Không tìm thấy dữ liệu"));
    }
}
