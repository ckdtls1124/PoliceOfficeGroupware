package org.spring.p21suck2jo.memorandumService;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.spring.p21suck2jo.memorandumRepository.MemorandumFileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class memorandumFileService {
    private final MemorandumFileRepository memorandumFileRepository;

//    모든 파일 찾기
//    public List<MemorandumFileEntity> getAllMemorandumFiles();
}
