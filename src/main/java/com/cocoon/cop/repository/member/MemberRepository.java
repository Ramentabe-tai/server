package com.cocoon.cop.repository.member;

import com.cocoon.cop.domain.main.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findMemberByEmail(String email);

    Boolean existsByEmail(String email);


}
