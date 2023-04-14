package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.PoliceRepository;
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

    public PoliceDto policepw(String email, int policeNumber) {

        Optional<PoliceEntity> policeEntity = policeRepository.findByEmailAndPoliceNumber(email, policeNumber);
        if (!policeEntity.isPresent()) {
            return null;

        } else if (email.equals(policeEntity.get().getEmail()) &&
                            policeNumber == policeEntity.get().getPoliceNumber()) {
            PoliceDto teamDto = PoliceDto.teamDtopw(policeEntity.get());
            return teamDto;
        }
        return null;
    }
    @Transactional
    public void pwUpdate(PoliceDto policeDto) {
        PoliceEntity policeEntity=PoliceEntity.pwUpdateEntity(policeDto,passwordEncoder);
        policeRepository.save(policeEntity);
    }

    public Long findPoliceIdByEmail(String email) {

        Optional<PoliceEntity> policeEntity= policeRepository.findByEmail(email);

        return policeEntity.get().getPoliceId();
    }
}




