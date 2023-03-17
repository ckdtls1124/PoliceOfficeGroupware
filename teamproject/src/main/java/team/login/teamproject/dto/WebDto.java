package team.login.teamproject.dto;

import lombok.*;
import team.login.teamproject.role.Role;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class WebDto {
    private Long no;

    @NotBlank(message = "이메일을 입력 해주세요")
    private String email;
    @NotBlank(message = "비밀번호를 입력 해주세요. 4글자이상 10글자 이하로 입력하세요")
    private String password;
    private Role role;

    private LocalDateTime createTime; //생성시에만 적용
    private LocalDateTime updateTime;// 수정 시에 적용

    }

