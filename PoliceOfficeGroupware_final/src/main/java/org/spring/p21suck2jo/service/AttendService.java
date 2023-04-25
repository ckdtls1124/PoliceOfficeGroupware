package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.entity.AttendEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.AttendRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendService {

    private final AttendRepository attendRepository;

    public void startWorking(LocalDateTime localDateTime, Long sessionPoliceIdLong) {

        PoliceEntity police = new PoliceEntity();
        police.setPoliceId(sessionPoliceIdLong);

        AttendEntity attendEntity = new AttendEntity();
        attendEntity.setCheckTime(localDateTime);
        // 퇴근 시간은 출근 시간으로 입력하고, 퇴근 시, 퇴근 시간으로 업데이트 한다.
        attendEntity.setLeaveTime(localDateTime);
        attendEntity.setPolice(police);

        attendRepository.save(attendEntity);


    }

//      업무 종료 클릭 시, 해당 PoliceEntity가 있는 Row를 찾아서 AttendEntity를 반환한다.
    public AttendEntity findByPolice(Long sessionPoliceIdLong) {

        PoliceEntity police = new PoliceEntity();
        police.setPoliceId(sessionPoliceIdLong);

//      해당 경찰관의 AttendEntity 데이터에서, 가장 마지막에 있는 데이터를 가져오기 위해 List로 출력
        List<AttendEntity> attendEntities= attendRepository.findByPolice(police);

//      가장 마지막에 있는 데이터를 출력
        return attendEntities.get(attendEntities.size()-1);
    }

//      업무 종료를 완료하면, 종료된 시간을 업데이트하여 row를 완성한다.
    public void updateEndWorkingTime(AttendEntity attendEntity) {

        attendRepository.save(attendEntity);
    }
}
