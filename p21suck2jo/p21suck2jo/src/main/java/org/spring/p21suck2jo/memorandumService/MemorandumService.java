package org.spring.p21suck2jo.memorandumService;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.memorandumRepository.MemorandumRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemorandumService {

    private final MemorandumRepository memorandumRepository;


}
