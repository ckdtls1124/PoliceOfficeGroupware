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
<<<<<<< HEAD
    private Long policeId;

    private String deptName;

    private String policeName;
=======
    private Long deptId;

    private String deptNm;
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db

    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;

<<<<<<< HEAD

    public static DeptEntity deptCreate(String deptName,String policeName , PoliceEntity policeEntity){
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setDeptName(deptName);
        deptEntity.setPoliceName(policeName);
=======
    public static DeptEntity deptAdd (String deptNm , PoliceEntity policeEntity){
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setDeptNm(deptNm);
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db
        deptEntity.setPolice(policeEntity);
        return deptEntity;

    }
}
