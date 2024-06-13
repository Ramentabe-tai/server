package com.cocoon.cop.repository.member;

import com.cocoon.cop.dto.MemberDto;

public interface MemberRepositoryCustom {

    MemberDto findByIdToDto(Long memberId);
}
