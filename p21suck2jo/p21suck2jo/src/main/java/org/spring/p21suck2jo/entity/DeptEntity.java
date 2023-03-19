package org.spring.p21suck2jo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "dept")
public class DeptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long deptId;

    private String deptNm;

    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;

    public static DeptEntity deptAdd (String deptNm , PoliceEntity policeEntity){
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setDeptNm(deptNm);
        deptEntity.setPolice(policeEntity);
        return deptEntity;

    }
}
