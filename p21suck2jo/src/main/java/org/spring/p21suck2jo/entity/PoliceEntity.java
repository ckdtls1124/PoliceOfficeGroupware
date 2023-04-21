package org.spring.p21suck2jo.entity;

import lombok.*;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

//import org.spring.p21suck2jo.convert.PoliceConvert;
//import org.spring.p21suck2jo.dto.PoliceDto;
//import org.spring.p21suck2jo.dto.PoliceDto;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "police_officer")
//@Embeddable
public class PoliceEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "police_id")
    private Long policeId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String password;

    private String policeName;
    @Column(nullable = false, unique = true)
    private int policeNumber; //사원번호
    private String ranks; //직책 <- table

    @Enumerated(EnumType.STRING)
    private Role role; //권한

    private LocalDateTime createTime;
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



    public void myPageUpdate(PoliceDto policeDto) {
        this.setPoliceAddress(policeDto.getPoliceAddress());
        this.setDetailAddress(policeDto.getDetailAddress());
        this.setZip_code(policeDto.getZip_code());
        this.setPolicePhone(policeDto.getPolicePhone());
        this.setPoliceName(policeDto.getPoliceName());
        this.setEmail(policeDto.getEmail());
    }

    public void updatePolice(PoliceDto policeDto,PasswordEncoder passwordEncoder){
        this.setRanks(policeDto.getRanks());
        this.setPoliceNumber(policeDto.getPoliceNumber());
        this.setDept(policeDto.getDept());
        this.setCreateTime(policeDto.getCreateTime());
        this.setPassword(passwordEncoder.encode(policeDto.getPassword()));
    }


    public static PoliceEntity pwUpdateEntity(PoliceDto policeDto, PasswordEncoder passwordEncoder) {
        PoliceEntity policeEntity = new PoliceEntity();

        policeEntity.setPoliceId(policeDto.getPoliceId());
        policeEntity.setEmail(policeDto.getEmail());
        policeEntity.setPassword(passwordEncoder.encode(policeDto.getPassword()));
        policeEntity.setPoliceNumber(policeDto.getPoliceNumber());
        policeEntity.setPoliceName(policeDto.getPoliceName());
        policeEntity.setRanks(policeDto.getRanks());
        policeEntity.setRole(Role.MEMBER);
//        policeEntity.setCreateTime(policeDto.getCreateTime());
//        policeEntity.setUpdateTime(policeDto.getUpdateTime());
        return policeEntity;
    }
}
