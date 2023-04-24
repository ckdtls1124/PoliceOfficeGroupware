package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.constructor.MemorandumCRUD;
import org.spring.p21suck2jo.dto.ApprovingMemberAllDto;
import org.spring.p21suck2jo.dto.MemoApprovedMemberDto;
import org.spring.p21suck2jo.dto.MemorandumDto;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.entity.ApprovingMemberAllEntity;
import org.spring.p21suck2jo.entity.MemoApprovedMember;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemorandumService implements MemorandumCRUD {
    private final MemorandumRepository memorandumRepository;
    private final MemoApprovedMemberRepository memoApprovedMemberRepository;
    private final ApprovingMemberRepository approvingMemberRepository;
    private final PoliceRepository policeRepository;


// 공통============================================================================================

    //    session의 PoliceId를 Long 타입으로 변환
    public Long changeStringPoliceIdLongPoliceId(HttpSession currentSession) {
        Long policeId = Long.valueOf(String.valueOf(currentSession.getAttribute("currentPoliceId")));
        return policeId;
    }

    //    경찰 Entity 제작
    public PoliceEntity makePoliceEntityWithSessionPoliceId(Long policeId) {
        PoliceEntity policeEntity = new PoliceEntity();
        policeEntity.setPoliceId(policeId);
        return policeEntity;
    }

    //    결재문서 송신함에서 각 문서별 결재자 보여주기
    public List<MemoApprovedMemberDto> showApprovingMemberListEachMemo(HttpSession currentSession, Pageable pageable) {
        Long sessionPoliceIdLong = this.changeStringPoliceIdLongPoliceId(currentSession);
        List<MemoApprovedMemberDto> memoApprovingMemberList = this.findMemoApprovedMemberApproved(sessionPoliceIdLong, pageable);

        return memoApprovingMemberList;
    }

    //    결재문서의 결재자 선택을 위해, 전체 경찰의 이름, 부서명 반환
    public List<ApprovingMemberAllDto> showAllApprovingMemberNameDeptSelectedMemo() {
        return ApprovingMemberAllDto.entityToDto(approvingMemberRepository.findAll());
    }

    //    결재 문서 상세 보기(상세 보기 및 수정 작업용)
    public MemorandumDto showSelectedMemo(Long memoId) {
        MemorandumEntity memorandumEntity = memorandumRepository.findById(memoId).get();
        MemorandumDto memorandumDto = MemorandumDto.builder().memorandumId(memorandumEntity.getMemorandumId()).memorandumTitle(memorandumEntity.getMemorandumTitle()).memorandumContent(memorandumEntity.getMemorandumContent()).createTime(memorandumEntity.getCreateTime()).build();
        return memorandumDto;
    }

    public PoliceEntity findPoliceEntityByPoliceName(String updatedApprovingPoliceName){
        Optional<PoliceEntity> policeEntity = policeRepository.findByPoliceName(updatedApprovingPoliceName);
        return policeEntity.get();
    }

//    회원 가입 시, ApprovingMemberAllEntity에 경찰 이름, 부서명 넣기
    @Override
    public void addApprovingMemberAllEntity(String policeName, String deptName) {
        ApprovingMemberAllEntity approvingMemberAllEntity = new ApprovingMemberAllEntity();
        approvingMemberAllEntity.setPoliceName(policeName);
        approvingMemberAllEntity.setDeptName(deptName);

        approvingMemberRepository.save(approvingMemberAllEntity);
    }

//   공통=======================================================================================

//   조회=======================================================================================

    // 결재문서 수신함 보여주기
    public List<MemorandumDto> findReceivedAllMemoByLogInPoliceId(Long policeId) {
        PoliceEntity policeEntity = this.makePoliceEntityWithSessionPoliceId(policeId);
        List<MemoApprovedMember> approvingMemberList = memoApprovedMemberRepository.findMemoApprovedMemberByPolice(policeEntity);
        List<MemorandumDto> memorandumDtos = new ArrayList();

        for (MemoApprovedMember approvingMember : approvingMemberList) {
            MemorandumEntity memorandumEntity = approvingMember.getMemorandum();
            MemorandumDto memorandumDto = MemorandumDto.toMemorandumDto(memorandumEntity);
            memorandumDtos.add(memorandumDto);
        }
        return memorandumDtos;
    }

    //   나의 결재문서 송신함(검색어가 없을 경우)
    public Page<MemorandumDto> findAllMemoNoSearch(PoliceEntity policeEntity, Pageable pageable) {
        Page<MemorandumEntity> memorandumEntityPage = memorandumRepository.findByPolice(policeEntity, pageable);
        Page<MemorandumDto> memorandumDtoPageNoSearch = memorandumEntityPage.map(MemorandumDto::toMemorandumDto);
        return memorandumDtoPageNoSearch;
    }

    //  나의 결재문서 송신함(검색어가 있을 경우)
    public Page<MemorandumDto> findAllMemoWithSearch(String search, Pageable pageable) {
        Page<MemorandumEntity> memorandumEntityPage = memorandumRepository.findByMemorandumTitleContaining(search, pageable);
        Page<MemorandumDto> memorandumDtoPageWithSearch = memorandumEntityPage.map(MemorandumDto::toMemorandumDto);
        return memorandumDtoPageWithSearch;
    }

    //    나의 결재문서 송신함에서, 결재 여부을 보여주기 위해 MemoApprovedMember에서 approved 항목을 가져온다.
    public List<MemoApprovedMemberDto> findMemoApprovedMemberApproved(Long policeId, Pageable pageable) {

        PoliceEntity policeEntity = this.makePoliceEntityWithSessionPoliceId(policeId);
        Page<MemorandumEntity> memorandumEntityPageList = memorandumRepository.findByPolice(policeEntity, pageable);

        List<MemoApprovedMemberDto> approvingMemberList = new ArrayList<>();
        for (MemorandumEntity eachMemorandumEntity : memorandumEntityPageList) {
            Optional<MemoApprovedMember> approvedMember = memoApprovedMemberRepository.findMemoApprovedMemberByMemorandum(eachMemorandumEntity);
            MemoApprovedMemberDto memoApprovingMemberDto = MemoApprovedMemberDto.builder().police(approvedMember.get().getPolice()).memorandum(approvedMember.get().getMemorandum()).approved(approvedMember.get().getApproved()).build();
            approvingMemberList.add(memoApprovingMemberDto);
        }
        return approvingMemberList;
    }

    //    로그인한 사람과 결재문서 등록자가 다르면, 결재자 이름 전달
    public String returnApprovedMemberName(Long memorandumIdLong) {
        MemorandumEntity memorandumEntity = new MemorandumEntity();
        memorandumEntity.setMemorandumId(memorandumIdLong);
        Optional<MemoApprovedMember> approvingMember = memoApprovedMemberRepository.findMemoApprovedMemberByMemorandum(memorandumEntity);
        return (approvingMember.get()).getPolice().getPoliceName();
    }

    //    로그인한 사람과 결재문서 등록자가 같으면, 승인, 반려 버튼 표시
    public String returnApprovingActions(String policeName) {
        Optional<PoliceEntity> policeEntity = policeRepository.findByPoliceName(policeName);
        PoliceDto policeDto = PoliceDto.entityToDtoNoPassword(policeEntity.get());
        return String.valueOf(Long.valueOf(policeDto.getPoliceId()));
    }

//   조회=======================================================================================

    //   작성=======================================================================================
    //    결재 문서 작성
    public Long writeMemorandum(MemorandumDto memorandumDto, Long policeId) {
        PoliceEntity policeEntity = this.makePoliceEntityWithSessionPoliceId(policeId);
        MemorandumEntity memorandum = MemorandumEntity.builder().memorandumTitle(memorandumDto.getMemorandumTitle()).memorandumContent(memorandumDto.getMemorandumContent()).police(policeEntity).approvingMemberList(memorandumDto.getApprovingMemberList()).build();
        Long memorandumId = memorandumRepository.save(memorandum).getMemorandumId();
        return memorandumId;
    }

    //     결재 문서 작성 시, 결재선 지정
    public void setApprovingMember(String policeName, Long memorandumId) {
        Optional<PoliceEntity> policeEntity = policeRepository.findByPoliceName(policeName);
        Optional<MemorandumEntity> memorandumEntity = memorandumRepository.findById(memorandumId);
        MemoApprovedMember approvingMember = new MemoApprovedMember();
        approvingMember.setPolice(policeEntity.get());
        approvingMember.setMemorandum(memorandumEntity.get());
//        결재 문서 작성 시, 결재 여부는 0(대기)으로 설정한다.
        approvingMember.setApproved(0);
        memoApprovedMemberRepository.save(approvingMember);
    }
//   작성=======================================================================================

//   수정=======================================================================================

    //    결재 문서 수정
    public void updateMemorandum(MemorandumDto memorandumDto, Long policeId) {
        PoliceEntity policeEntity = this.makePoliceEntityWithSessionPoliceId(policeId);
        MemorandumEntity memorandumEntity = MemorandumEntity.builder().memorandumTitle(memorandumDto.getMemorandumTitle()).memorandumId(memorandumDto.getMemorandumId()).memorandumContent(memorandumDto.getMemorandumContent()).police(policeEntity).build();

        memorandumRepository.save(memorandumEntity);
    }

//    수정할 문서의 결재선 찾기
    public MemoApprovedMember findApprovedMemberInSelectedMemo(Long memorandumId){
        MemorandumEntity memoInApprovingMember = new MemorandumEntity();
        memoInApprovingMember.setMemorandumId(memorandumId);
        MemoApprovedMember approvedMember = memoApprovedMemberRepository.findMemoApprovedMemberByMemorandum(memoInApprovingMember).get();
        return approvedMember;
    }
//    ==============================================이 부분 부터 Interface에 적용할것 고민하기

    //    수정 시, 결재선을 위한 경찰 Entity로 찾은 ApprovingMembers List를 전체 전달
    public List<ApprovingMemberAllDto> findApprovingMembersByPoliceEntities() {
        List<PoliceEntity> policeEntityList = policeRepository.findAll();
        List<PoliceDto> policeDtoList = new ArrayList<>();
        for (PoliceEntity policeEntity : policeEntityList) {
            PoliceDto policeDto = PoliceDto.entityToDtoNoPassword(policeEntity);
            policeDtoList.add(policeDto);
        }

        List<ApprovingMemberAllDto> approvingMemberNameDeptList = new ArrayList();

        for (PoliceDto i : policeDtoList) {
            ApprovingMemberAllDto approvingMemberNameDept = new ApprovingMemberAllDto();
            approvingMemberNameDept.setDeptName(i.getDept().getDeptName());
            approvingMemberNameDept.setPoliceName(i.getPoliceName());
            approvingMemberNameDeptList.add(approvingMemberNameDept);
        }


        return approvingMemberNameDeptList;
    }



    //    결재선 수정하기
     public void updateSelectedMemoApprovingMember(MemoApprovedMember updatedApprovingMember){
         memoApprovedMemberRepository.save(updatedApprovingMember);
     }

    //    결재 문서 승인 또는 반려 처리
    public void updateApprovedInMemoApprovingMember(String index, Long policeId, String memorandumId) {

//        approvingMember를 찾기 위해, policeEntity, MemorandumEntity를 넣는다.

        PoliceEntity policeEntity = this.makePoliceEntityWithSessionPoliceId(policeId);

        MemorandumEntity memorandumEntity = new MemorandumEntity();
        memorandumEntity.setMemorandumId(Long.valueOf(String.valueOf(memorandumId)));

        Optional<MemoApprovedMember> memoApprovingMember = memoApprovedMemberRepository.findMemoApprovedMemberByMemorandumAndPolice(memorandumEntity, policeEntity);


        Integer approvedNum = Integer.valueOf(String.valueOf(index));

        if (approvedNum == 0) {
            memoApprovingMember.get().setApproved(2);
        } else {
            memoApprovingMember.get().setApproved(1);
        }


        memoApprovedMemberRepository.save(memoApprovingMember.get());
    }


//   수정=======================================================================================

    //   삭제=======================================================================================
    //    결재 문서 삭제
    public void deleteSelectedMemo(Long id) {

        memorandumRepository.deleteById(id);

    }




//   삭제=======================================================================================

}
