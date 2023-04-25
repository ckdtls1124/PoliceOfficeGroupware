package org.spring.groupware.dto;

import lombok.*;
import org.spring.groupware.entity.MemorandumEntity;
import org.spring.groupware.entity.PoliceEntity;

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
