package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PoliceLoginService {

    private final PoliceRepository policeRepository;
    private final PasswordEncoder passwordEncoder;


    public PoliceDto policeid(int policeNumber) {
        Optional<PoliceEntity> policeEntity = policeRepository.findByPoliceNumber(policeNumber);
        if (!policeEntity.isPresent()) {
            return null;
        }
        PoliceDto teamDto = PoliceDto.teamDtoid(policeEntity.get());
        return teamDto;
    }


    //SMTP 메일로 임시비밀번호 받기
    @Value("${spring.mail.username}")
    private String sendFrom;
    private final JavaMailSender javaMailSender;
    private final BCryptPasswordEncoder encoder;
    @Transactional
    public void sendTmpPwd(PoliceDto dto) {    //임시비밀번호
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String tmpPwd = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            tmpPwd += charSet[idx];
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(dto.getEmail());
            message.setFrom(sendFrom);
            message.setSubject("1석2조 임시 비밀번호 안내 이메일입니다.");
            message.setText("안녕하세요.\n"
                    + "1석2조 임시비밀번호 안내 관련 이메일 입니다.\n"
                    + "임시 비밀번호를 발급하오니 사이트에 접속하셔서 로그인 하신 후\n"
                    + "반드시 비밀번호를 변경해주시기 바랍니다.\n\n"
                    + "임시 비밀번호 : " + tmpPwd);
            javaMailSender.send(message);
        } catch (MailParseException e) {
            e.printStackTrace();
        } catch (MailAuthenticationException e) {
            e.printStackTrace();
        } catch (MailSendException e) {
            e.printStackTrace();
        } catch (MailException e) {
            e.printStackTrace();
        }

        PoliceEntity user = policeRepository.findByPoliceNumber(dto.getPoliceNumber()).orElseThrow(() -> {
            return new IllegalArgumentException("임시 비밀번호 변경 실패: 사용자 사원번호를 찾을 수 없습니다.");
        });

        user.setPassword(encoder.encode(tmpPwd));
    }

    //그전방법 비밀번호찾기
//    public PoliceDto policepw(String email, int policeNumber) {
//
//        Optional<PoliceEntity> policeEntity = policeRepository.findByEmailAndPoliceNumber(email, policeNumber);
//        if (!policeEntity.isPresent()) {
//            return null;
//
//        } else if (email.equals(policeEntity.get().getEmail()) &&
//                            policeNumber == policeEntity.get().getPoliceNumber()) {
//            PoliceDto teamDto = PoliceDto.teamDtopw(policeEntity.get());
//            return teamDto;
//        }
//        return null;
//    }
//    @Transactional
//    public void pwUpdate(PoliceDto policeDto) {
//        PoliceEntity policeEntity=PoliceEntity.pwUpdateEntity(policeDto,passwordEncoder);
//        policeRepository.save(policeEntity);
//    }

    public Long findPoliceIdByEmail(String email) {

        Optional<PoliceEntity> policeEntity= policeRepository.findByEmail(email);

        return policeEntity.get().getPoliceId();
    }
}




