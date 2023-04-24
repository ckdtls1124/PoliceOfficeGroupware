package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemorandumFileDto {

    private Long memorandumFileId;

    private String memorandumFileOriginalName;

    private String memorandumFileSavedName;

    private String memorandumFilePath;

    private MemorandumEntity memorandumEntity;

    private PoliceEntity policeEntity;

}
