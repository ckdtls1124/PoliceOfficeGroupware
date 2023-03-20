package org.spring.p21suck2jo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "memorandumFile")
public class MemorandumFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memorandumFile_id")
    private Long memorandumFileId;

    @Column
    private String memorandumFileName;

    @Column(columnDefinition = "TEXT")
    private String memorandumFileContents;

    private String memorandumFileType;

    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;


<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "memorandum_id")
    private MemorandumEntity memorandumEntity;
=======
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db
}