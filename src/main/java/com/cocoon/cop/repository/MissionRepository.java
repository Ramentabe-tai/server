package com.cocoon.cop.repository;

import com.cocoon.cop.domain.main.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
