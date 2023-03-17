package team.login.teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.login.teamproject.dto.WebDto;
import team.login.teamproject.role.Role;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "webGroup")
public class WebEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Web_no")
    private Long no;

    @Column(nullable = false,unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

public static WebEntity webEntity(WebDto webDto, PasswordEncoder passwordEncoder){

    WebEntity webEntity=new WebEntity();

    webEntity.setNo(webDto.getNo());
    webEntity.setEmail(webDto.getEmail());
    webEntity.setPassword(webEntity.getPassword());
    webEntity.setRole(Role.ADMIN);
    return webEntity;
}


}
