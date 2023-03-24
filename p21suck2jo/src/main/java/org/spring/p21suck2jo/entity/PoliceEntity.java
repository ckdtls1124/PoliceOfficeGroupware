package org.spring.p21suck2jo.entity;

import lombok.*;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.springframework.security.crypto.password.PasswordEncoder;

//import org.spring.p21suck2jo.convert.PoliceConvert;
//import org.spring.p21suck2jo.dto.PoliceDto;
//import org.spring.p21suck2jo.dto.PoliceDto;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "police_officer")
public class PoliceEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "police_id")
    private Long policeId;

    @Column(nullable = true)
    private String password;


    private String policeName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private int policeNumber; //사원번호
    private String ranks; //직책 <- table

    private String zip_code;
    private String policeAddress;
    private String DetailAddress;
    private String policePhone;

    @ManyToOne
    @JoinColumn(name ="dept_id")
    private DeptEntity dept;

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<BoardEntity> boardList = new ArrayList<>();
    
    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<ReplyEntity> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<AttendEntity> attendList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<CalendarEntity> calendarList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<MemorandumEntity> moMemorandumList = new ArrayList<>();


    @OneToMany(mappedBy = "eventJoinPolice",cascade = CascadeType.ALL)
    private List<EventEntity> EventList = new ArrayList<>();


    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<InquiryEntity> inquiryList = new ArrayList<>();



    public static PoliceEntity pwUpdateEntity(PoliceDto policeDto, PasswordEncoder passwordEncoder) {
        PoliceEntity policeEntity = new PoliceEntity();

        policeEntity.setPoliceId(policeDto.getPoliceId());
        policeEntity.setEmail(policeDto.getEmail());
        policeEntity.setPoliceNumber(policeDto.getPoliceNumber());
        policeEntity.setPassword(policeDto.getPassword());
        return policeEntity;
    }
}
