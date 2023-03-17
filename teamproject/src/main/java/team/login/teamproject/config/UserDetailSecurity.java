package team.login.teamproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.login.teamproject.entity.WebEntity;
import team.login.teamproject.repository.WebRepository;

import javax.persistence.Tuple;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailSecurity implements UserDetailsService {

    private final WebRepository webRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<WebEntity> webId = webRepository.findById(Long.valueOf(email));

        if (!webId.isPresent()){
            throw new UsernameNotFoundException("사용자가 없습니다.");
    }
    WebEntity webEntity=webId.get();
        return User.builder()    //스프링관리자 User 역할을 빌더로 간단하게만듬
                .username(webEntity.getEmail())
                .password(webEntity.getPassword())
                .roles(webEntity.getRole().toString())
                .build();
}

}
