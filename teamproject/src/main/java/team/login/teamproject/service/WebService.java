package team.login.teamproject.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.login.teamproject.dto.WebDto;
import team.login.teamproject.entity.WebEntity;
import team.login.teamproject.repository.WebRepository;

@Service
@RequiredArgsConstructor
public class WebService {

    private final WebRepository webRepository;
    private PasswordEncoder passwordEncoder;
    public void inser(WebDto webDto) {
        WebEntity webEntity=WebEntity.webEntity(webDto,passwordEncoder);
            webRepository.save(webEntity);
        }
    }

