package org.spring.p21suck2jo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private String deptName;//부서이름
    private String deptLocation;//부서위치


    @OneToMany(mappedBy = "dept",cascade = CascadeType.ALL)
    List<PoliceEntity> policeList = new ArrayList<>();


}
