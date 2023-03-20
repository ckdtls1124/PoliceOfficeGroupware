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
    private Long policeId;

    private String deptName;

    private String policeName;

    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;


    public static DeptEntity deptCreate(String deptName,String policeName , PoliceEntity policeEntity){
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setDeptName(deptName);
        deptEntity.setPoliceName(policeName);
        deptEntity.setPolice(policeEntity);
        return deptEntity;

    }
}
