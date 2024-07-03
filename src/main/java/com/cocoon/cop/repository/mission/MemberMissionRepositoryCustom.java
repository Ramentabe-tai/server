package com.cocoon.cop.repository.mission;

import com.cocoon.cop.dto.MemberMissionDto;

import java.util.List;

public interface MemberMissionRepositoryCustom {

    List<MemberMissionDto> findAllMissionsForMember(Long memberId);

}
