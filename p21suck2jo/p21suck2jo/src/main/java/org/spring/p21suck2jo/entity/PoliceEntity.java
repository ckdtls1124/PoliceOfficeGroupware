package org.spring.p21suck2jo.entity;

import lombok.*;

//import org.spring.p21suck2jo.convert.PoliceConvert;
//import org.spring.p21suck2jo.dto.PoliceDto;
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

    private String dept;
    private String policeName;
    private String email;
    private int policeNumber; //사원번호
    private String ranks; //직책 <- table
<<<<<<< HEAD
    private String zip_code;

//    @Converter(converter = PoliceConvert.class)
//    private String dept;
=======
//    @Converter(converter = PoliceConvert.class)
    private String dept;
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db


    private String zip_code;
    private String policeAddress;
    private String DetailAddress;
    private String policePhone;


    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<BoardEntity> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<AttendEntity> attendList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<CalendarEntity> calendarList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
<<<<<<< HEAD
    private List<MemorandumEntity> moMemorandumList = new ArrayList<>();
=======
    private List<MemorandumEntity> memorandumList = new ArrayList<>();

    @OneToMany(mappedBy = "event_id",cascade = CascadeType.ALL)
    private List<EventEntity> EventList = new ArrayList<>();
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<InquiryEntity> inquiryList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<DeptEntity> deptList = new ArrayList<>();


<<<<<<< HEAD
    public static PoliceEntity createOfficer(PoliceDto policeDto){
=======
    public static PoliceEntity createOfficer(PoliceDto policeDto){ //test 끝나면 passwordEncoder
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db
        PoliceEntity police = new PoliceEntity();
//        police.setPoliceId(policeDto.getPoliceId());
//        police.setPassword(passwordEncoder.encode(policeDto.getPassword()));
        police.setPassword(policeDto.getPassword());
<<<<<<< HEAD
        police.setPoliceName(policeDto.getPoliceName());
        police.setDept(policeDto.getDept());
=======
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db
        police.setEmail(policeDto.getEmail());
        police.setPoliceNumber(policeDto.getPoliceNumber());
        police.setRanks(policeDto.getRanks());
        police.setZip_code(policeDto.getZip_code());
        police.setPoliceAddress(policeDto.getPoliceAddress());
        police.setDetailAddress(policeDto.getDetailAddress());
        police.setPolicePhone(policeDto.getPolicePhone());
        return police;
    }

}
