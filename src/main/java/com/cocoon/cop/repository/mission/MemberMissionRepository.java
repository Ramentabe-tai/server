package com.cocoon.cop.repository.mission;

import com.cocoon.cop.domain.main.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {

}
