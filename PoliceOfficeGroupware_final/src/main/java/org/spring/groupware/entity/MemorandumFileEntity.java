package org.spring.groupware.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "memorandumFile")
@Builder
public class MemorandumFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memorandumFile_id")
    private Long memorandumFileId;

    @Column
    private String memorandumFileOriginalName;

    private String memorandumFileSavedName;

    @Column
    private String memorandumFilePath;

    @ManyToOne
    @JoinColumn(name="memorandum_id")
    private MemorandumEntity memorandumEntity;



    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;


}