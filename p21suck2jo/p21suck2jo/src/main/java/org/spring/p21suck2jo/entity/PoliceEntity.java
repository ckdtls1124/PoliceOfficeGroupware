package org.spring.p21suck2jo.entity;

import lombok.*;

import org.spring.p21suck2jo.convert.PoliceConvert;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "police_officer")
public class PoliceEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "police_id")
    private Long policeId;

    @Column(nullable = false)
    private String password;

    private String email;
    private int policeNumber; //사원번호
    private String ranks; //직책 <- table
//    @Converter(converter = PoliceConvert.class)
    private String dept;


    private String zip_code;
    private String policeAddress;
    private String DetailAddress;
    private int policePhone;


    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<BoardEntity> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<AttendEntity> attendList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<CalendarEntity> calendarList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<MemorandumEntity> memorandumList = new ArrayList<>();

    @OneToMany(mappedBy = "event_id",cascade = CascadeType.ALL)
    private List<EventEntity> EventList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<InquiryEntity> inquiryList = new ArrayList<>();

//    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
//    private List<DeptEntity> deptList = new ArrayList<>();


    public static PoliceEntity createOfficer(PoliceDto policeDto){ //test 끝나면 passwordEncoder
        PoliceEntity police = new PoliceEntity();
//        police.setPoliceId(policeDto.getPoliceId());
//        police.setPassword(passwordEncoder.encode(policeDto.getPassword()));
        police.setPassword(policeDto.getPassword());
        police.setEmail(policeDto.getEmail());
        police.setPoliceNumber(policeDto.getPoilceNumber());
        police.setRanks(policeDto.getRanks());
        police.setZip_code(policeDto.getZip_code());
        police.setPoliceAddress(policeDto.getPoliceAddress());
        police.setDetailAddress(policeDto.getDetailAddress());
        return police;
    }

}
