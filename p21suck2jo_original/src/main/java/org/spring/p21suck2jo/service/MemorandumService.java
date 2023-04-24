package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.ApprovingMemberNameDept;
import org.spring.p21suck2jo.dto.MemoApprovingMemberDto;
import org.spring.p21suck2jo.dto.MemorandumDto;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.entity.MemoApprovingMember;
import org.spring.p21suck2jo.entity.DeptEntity;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.ApprovingMemberRepository;
import org.spring.p21suck2jo.repository.DeptRepository;
import org.spring.p21suck2jo.repository.MemorandumRepository;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemorandumService {
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;
    private final MemorandumRepository memorandumRepository;

    private final PoliceRepository policeRepository;
    private final DeptRepository deptRepository;
    private final ApprovingMemberRepository approvingMemberRepository;

// 공통============================================================================================
    /*
     * 현재 Police ID를 Session에서 받아온다.
     * */
    public Long changeStringPoliceIdLongPoliceId(HttpSession currentSession) {
        Long policeId = Long.valueOf(String.valueOf(currentSession.getAttribute("currentPoliceId")));

        return policeId;
    }

    /*
     * 결재 문서 작성 페이지 이동 시, 결재자 리스트 전달
     * ApporvingMemberNameDept라는 DTO를 이용하여, Police Id, PoliceName, Dept를 따로 저장한다.
     * 이를 위해, 모든 Dept를 찾고, 해당 Dept에 있는 PoliceName을 찾는 과정이 선행된다.
     * 그리고, ApprovingMemberNameDept Dto에  PoliceName, Dept를 저장
     * */
    public List<ApprovingMemberNameDept> showAllApprovingMemberNameDept() {
        List<DeptEntity> deptEntities = deptRepository.findAll();
        List<PoliceDto> policeDtos = new ArrayList();
        for (DeptEntity i : deptEntities) {
            List<PoliceEntity> policeEntityList = policeRepository.findByDept(i);
            for (PoliceEntity j : policeEntityList) {
//                PoliceEntity -> PoliceDto
                PoliceDto policeDto = PoliceDto.officerView(j);
                policeDtos.add(policeDto);
            }
        }

        List<ApprovingMemberNameDept> approvingMemberNameDeptList = new ArrayList();
        for (PoliceDto i : policeDtos) {
            ApprovingMemberNameDept approvingMemberNameDept = new ApprovingMemberNameDept();
            approvingMemberNameDept.setDeptName(i.getDept().getDeptName());
            approvingMemberNameDept.setPoliceName(i.getPoliceName());
            approvingMemberNameDeptList.add(approvingMemberNameDept);
        }

        return approvingMemberNameDeptList;
    }

//   공통=======================================================================================

//   조회============================================
    //   나의 결재함(검색어가 없을 경우)
    public Page<MemorandumDto> findAllMemo(Long policeId, Pageable pageable) {

        PoliceEntity policeEntity = new PoliceEntity();
        policeEntity.setPoliceId(policeId);

        Page<MemorandumEntity> memorandumEntityPage = memorandumRepository.findByPolice(policeEntity, pageable);
        Page<MemorandumDto> memorandumDtos = memorandumEntityPage.map(MemorandumDto::toMemorandumDto);

        return memorandumDtos;

    }

    //  나의 결재함(검색어가 있을 경우)
    public Page<MemorandumDto> memoListSearchPage(String search, Pageable pageable) {
        Page<MemorandumEntity> memorandumEntityPage = memorandumRepository.findByMemorandumTitleContaining(search, pageable);

        Page<MemorandumDto> memorandumDtoPage = memorandumEntityPage.map(MemorandumDto::toMemorandumDto);

        return memorandumDtoPage;
    }

//    각 문서에 대해서, 결재 여부을 보여주기 위해 MemoApprovingMember에서 approved 항목을 가져온다.
    public List<MemoApprovingMemberDto> findApprovingMemberApproveNum(Long policeId, Pageable pageable) {

        PoliceEntity policeEntity = new PoliceEntity();
        policeEntity.setPoliceId(policeId);
        Page<MemorandumEntity> memorandumEntityPage = memorandumRepository.findByPolice(policeEntity, pageable);

        List<MemoApprovingMemberDto> approvingMemberList = new ArrayList<>();

        for(MemorandumEntity i: memorandumEntityPage){
            Optional<MemoApprovingMember> approvingMember = approvingMemberRepository.findByMemorandum(i);

            MemoApprovingMemberDto memoApprovingMemberDto = MemoApprovingMemberDto.builder()
                    .police(approvingMember.get().getPolice())
                    .memorandum(approvingMember.get().getMemorandum())
                    .approved(approvingMember.get().getApproved())
                    .build();


            approvingMemberList.add(memoApprovingMemberDto);
        }




        return approvingMemberList;
    }


    // 수신 결재문서함 보여주기
    public List<MemorandumDto> findReceivedAllMemo(Long policeId) {
        PoliceEntity policeEntity = new PoliceEntity();
        policeEntity.setPoliceId(policeId);
        List<MemoApprovingMember> approvingMemberList = approvingMemberRepository.findByPolice(policeEntity);
        List<MemorandumDto> memorandumDtos = new ArrayList();


        for (MemoApprovingMember approvingMember : approvingMemberList) {
            MemorandumEntity memorandumEntity = approvingMember.getMemorandum();
            MemorandumDto memorandumDto = MemorandumDto.toMemorandumDto(memorandumEntity);
            memorandumDtos.add(memorandumDto);
        }

        return memorandumDtos;
    }


    //    결재 문서 상세 보기(상세 보기 및 수정)
    public MemorandumEntity findSelectedMemo(Long memoId) {
        Optional<MemorandumEntity> memoEntity = memorandumRepository.findById(memoId);

        return memoEntity.get();
    }

    public String findApprovingMemberName(Long memorandumIdLong) {
        MemorandumEntity memorandumEntity = new MemorandumEntity();
        memorandumEntity.setMemorandumId(memorandumIdLong);
        Optional<MemoApprovingMember> approvingMember = approvingMemberRepository.findByMemorandum(memorandumEntity);
        return (approvingMember.get()).getPolice().getPoliceName();
    }



    public String findPoliceByName(String policeName) {
        Optional<PoliceEntity> policeEntity = policeRepository.findByPoliceName(policeName);
        PoliceDto policeDto = PoliceDto.officerView(policeEntity.get());

        return String.valueOf(Long.valueOf(policeDto.getPoliceId()));
    }


//   조회============================================

//   작성============================================
    //    결재 문서 작성
    public Long writeMemorandum(MemorandumDto memorandumDto, Long sessionPoliceId) throws IOException {

        PoliceEntity policeEntity = new PoliceEntity();
        policeEntity.setPoliceId(sessionPoliceId);

        MemorandumEntity memorandum = MemorandumEntity.builder().memorandumTitle(memorandumDto.getMemorandumTitle()).memorandumContent(memorandumDto.getMemorandumContent()).police(policeEntity).approvingMemberList(memorandumDto.getApprovingMemberList()).build();

        Long memorandumId = memorandumRepository.save(memorandum).getMemorandumId();

        return memorandumId;

    }

    //     결재 문서 작성 시, 결재선 지정
    public void setApprovingMember(String policeName, Long memrandumId) {
        Optional<PoliceEntity> policeEntity = policeRepository.findByPoliceName(policeName);
        Optional<MemorandumEntity> memorandumEntity = memorandumRepository.findById(memrandumId);
        MemoApprovingMember approvingMember = new MemoApprovingMember();
        approvingMember.setPolice(policeEntity.get());
        approvingMember.setMemorandum(memorandumEntity.get());
//        결재 문서 작성 시, 결재 여부는 0(대기)으로 설정한다.
        approvingMember.setApproved(0);
        approvingMemberRepository.save(approvingMember);
    }
//   작성============================================

//   수정============================================
    //    결재 문서 수정
    public void updateMemorandum(MemorandumEntity memorandumEntity, Long sessionPoliceId) {
        PoliceEntity policeEntity = new PoliceEntity();
        policeEntity.setPoliceId(sessionPoliceId);

        memorandumEntity.setPolice(policeEntity);

        memorandumRepository.save(memorandumEntity);
    }

    //    수정 시, 결재선을 위한 모든 경찰 리스트 전달
    public List<PoliceDto> findAllPolice() {

        List<PoliceEntity> policeEntityList = policeRepository.findAll();

        List<PoliceDto> policeDtoList = new ArrayList<>();
        for (PoliceEntity policeEntity : policeEntityList) {
            PoliceDto policeDto = PoliceDto.officerView(policeEntity);
            policeDtoList.add(policeDto);
        }
        return policeDtoList;
    }

//    결재 문서 승인 또는 반려 처리
    public void updateApprovedInMemoApprovingMember(String index, Long policeIdLong, String memorandumId) {

//        approvingMember를 찾기 위해, policeEntity, MemorandumEntity를 넣는다.

        PoliceEntity policeEntity = new PoliceEntity();
        policeEntity.setPoliceId(policeIdLong);

        MemorandumEntity memorandumEntity = new MemorandumEntity();
        memorandumEntity.setMemorandumId(Long.valueOf(String.valueOf(memorandumId)));

        Optional<MemoApprovingMember> memoApprovingMember =  approvingMemberRepository.findByMemorandumAndPolice(memorandumEntity, policeEntity);


        Integer approvedNum = Integer.valueOf(String.valueOf(index));

        if(approvedNum == 0){
            memoApprovingMember.get().setApproved(2);
        } else {
            memoApprovingMember.get().setApproved(1);
        }


        approvingMemberRepository.save(memoApprovingMember.get());
    }


//   수정============================================

//   삭제============================================
    //    결재 문서 삭제
    public void deleteSelectedMemo(Long id) {

        memorandumRepository.deleteById(id);

    }



//   삭제============================================

}
