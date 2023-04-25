package org.spring.p21suck2jo.constructor;

import org.spring.p21suck2jo.dto.ApprovingMemberAllDto;
import org.spring.p21suck2jo.dto.MemoApprovedMemberDto;
import org.spring.p21suck2jo.dto.MemorandumDto;
import org.spring.p21suck2jo.entity.MemoApprovedMember;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemorandumCRUD {

//    경찰 Entity 관련한 작업
    Long changeStringPoliceIdLongPoliceId(HttpSession currentSession);

    PoliceEntity makePoliceEntityWithSessionPoliceId(Long policeId);

    PoliceEntity findPoliceEntityByPoliceName(String updatedApprovingPoliceName);

//    결재 문서 몇 결재선 관련 작업

    //    SelectMemorandum
    List<MemorandumDto> findReceivedAllMemoByLogInPoliceId(Long policeId);

    Page<MemorandumDto> findAllMemoNoSearch(PoliceEntity policeEntity, Pageable pageable);

    Page<MemorandumDto> findAllMemoWithSearch(String search, Pageable pageable);

    MemorandumDto showSelectedMemo(Long memoId);

    //    SelectApprovingPoliceOfficers
    List<MemoApprovedMemberDto> showApprovingMemberListEachMemo(HttpSession currentSession, Pageable pageable);

    List<ApprovingMemberAllDto> showAllApprovingMemberNameDeptSelectedMemo();

    List<MemoApprovedMemberDto> findMemoApprovedMemberApproved(Long policeId, Pageable pageable);

    String returnApprovedMemberName(Long memorandumIdLong);

    String returnApprovingActions(String policeName);

    //  CreateMemorandum
    Long writeMemorandum(MemorandumDto memorandumDto, Long policeId);

    //    CreateApprovingPoliceOfficer
    void setApprovingMember(String policeName, Long memorandumId);

    //    UpdateMemorandum
    void updateMemorandum(MemorandumDto memorandumDto, Long policeId);

    //    UpdateApprovedPoliceOfficer
    MemoApprovedMember findApprovedMemberInSelectedMemo(Long memorandumId);

    List<ApprovingMemberAllDto> findApprovingMembersByPoliceEntities();


    void addApprovingMemberAllEntity(String policeName, String deptName);
}
