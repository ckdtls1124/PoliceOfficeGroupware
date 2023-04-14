package org.spring.p21suck2jo.config;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailSecurity implements UserDetailsService {

    private final PoliceRepository policeRepository;

    @Override           //loadUserByUsername메서드는 "이런 정보가 들어왔는데 얘 혹시 회원이야?" 라고 묻는 메서드이다.
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<PoliceEntity> police = policeRepository.findByEmail(email);

        if (!police.isPresent()){
            throw new UsernameNotFoundException("사용자가 없습니다.");
    }
        PoliceEntity policeEntity=police.get();
        return User.builder()    //스프링관리자 User 역할을 빌더로 간단하게만듬
                .username(policeEntity.getEmail())
                .password(policeEntity.getPassword())
                .roles(policeEntity.getRole().toString())
                .build();
}

    @Bean  // 비밀번호 암호화
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
