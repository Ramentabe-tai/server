package com.cocoon.cop.repository.mission;

import com.cocoon.cop.dto.MemberMissionDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMissionRepositoryCustomImplTest {


    @Autowired
    MemberMissionRepository memberMissionRepository;


    @Test
    void findAllMissionsForAllMembers() {
        List<MemberMissionDto> allMissionsForAllMembers = memberMissionRepository.findAllMissionsForMember(1L);
        for (MemberMissionDto memberMissionDto : allMissionsForAllMembers) {
            System.out.println("memberMissionDto = " + memberMissionDto);
        }
    }

}