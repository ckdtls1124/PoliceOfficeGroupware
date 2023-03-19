package org.spring.p21suck2jo.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.InputStream;

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
    private String memorandumFileName;

//    private MultipartFile memoFileData;

    @Column
    private String memorandumFileUri;

    private String memorandumFileType;

    private Long size;


    @ManyToOne
    @JoinColumn(name = "policeId")
    private PoliceEntity policeEntity;

    public MemorandumFileEntity(String memorandumFileName, MultipartFile file){
        this.memorandumFileName = memorandumFileName;
    }

}
