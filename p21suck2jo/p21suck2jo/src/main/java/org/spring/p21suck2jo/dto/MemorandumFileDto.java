package org.spring.p21suck2jo.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemorandumFileDto {

    private Long memorandumFileId;
    private String memorandumFileName;

    private MultipartFile memoFileData;
    private String memorandumFileUri;
    private String memorandumFileType;
    private Long size;

}
