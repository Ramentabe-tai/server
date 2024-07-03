package com.cocoon.cop.controller;

import com.cocoon.cop.domain.main.Member;
import com.cocoon.cop.domain.main.MemberMission;
import com.cocoon.cop.domain.main.Mission;
import com.cocoon.cop.repository.MissionRepository;
import com.cocoon.cop.repository.member.MemberRepository;
import com.cocoon.cop.repository.mission.MemberMissionRepository;
import com.cocoon.cop.repository.mission.MemberMissionRepositoryCustom;
import com.cocoon.cop.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;


    @PatchMapping("/{memberId}/mission/{missionId}")
    @Transactional
    public ResponseEntity<Map<String, Object>> memberMissionClearRequest(@PathVariable Long memberId, @PathVariable Long missionId) {
        log.info("memberId: {}, missionId: {}", memberId, missionId);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + memberId));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid mission ID: " + missionId));

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .isCompleted(true)
                .completedDate(LocalDateTime.now())
                .build();

        memberMissionRepository.save(memberMission);

        /**
         * "message": "Mission status updated successfully",
         *     "is_completed": true,
         *     "exp_point": 100
         */

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Mission status updated successfully");
        response.put("is_completed", true);
        response.put("exp_point", mission.getExpPoint());

        member.addExpPoint(mission.getExpPoint());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}/missions")
    public ResponseEntity<Map<String, Object>> getMemberMissions(@PathVariable Long memberId) {
        log.info("memberId: {}", memberId);

        Map<String, Object> response = new HashMap<>();
        response.put("missions", memberMissionRepository.findAllMissionsForMember(memberId));

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{memberId}/exp")
    public ResponseEntity<Map<String, Object>> getMemberExp(@PathVariable Long memberId) {
        log.info("memberId: {}", memberId);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + memberId));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("memberId", memberId);
        response.put("level", member.getLevel());
        response.put("exp", member.getExpPoint());

        return ResponseEntity.ok(response);
    }




}
