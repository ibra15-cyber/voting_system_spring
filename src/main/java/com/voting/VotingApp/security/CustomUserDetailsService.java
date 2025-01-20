//package com.voting.VotingApp.security;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class CustomUserDetailsService implements UserDetailsService {
//
//    UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        Optional<User> user = userRepository.findByEmail(username);
//        User user = userRepository.findByEmail(username)
//                .orElseThrow(()-> new NotFoundException("User Not Found"));
//
//        return AuthUser.builder()
//                .user(user)
//                .build();
//    }
//}
