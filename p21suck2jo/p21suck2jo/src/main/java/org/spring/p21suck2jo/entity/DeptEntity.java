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

    private String deptName;
    private String deptRanks;
    private int deptPoliceNumber;

    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;



    public static DeptEntity deptCreate(String deptName,String deptRanks ,int deptPoliceNumber, PoliceEntity policeEntity){
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setDeptName(deptName);
        deptEntity.setDeptRanks(deptRanks);
        deptEntity.setDeptPoliceNumber(deptPoliceNumber);
        deptEntity.setPolice(policeEntity);
    return deptEntity;
    }
}
