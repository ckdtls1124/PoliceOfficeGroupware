package org.spring.p21suck2jo.constructor;

import org.spring.p21suck2jo.dto.MemorandumDto;
import org.spring.p21suck2jo.dto.MemorandumFileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MemorandumFileCRUD {

    List<MemorandumFileDto> findAllFilesInSelectedMemo(Long memoId);

    Long putFileIntoDB(MultipartFile multiFile, MemorandumDto memorandumDto, Long memorandumId, Long sessionPoliceIdLong) throws IOException;

    void deleteFilesInSelectedMemo(Long memoId);

    int deleteSelectedFile(Long memoFileId);
}
