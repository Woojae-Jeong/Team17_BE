package homeTry.adminTag.teamTag.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import homeTry.adminTag.teamTag.dto.request.TeamTagRequest;
import homeTry.adminTag.teamTag.dto.response.TeamTagResponse;
import homeTry.adminTag.teamTag.service.TeamTagService;
import homeTry.common.annotation.LoginMember;
import homeTry.member.dto.MemberDTO;

@RestController
@RequestMapping("/api/admin/teamtag")
public class TeamTagController {
    
    private final TeamTagService teamTagService;

    public TeamTagController(TeamTagService teamTagService) {
        this.teamTagService = teamTagService;
    }

    @GetMapping
    public ResponseEntity<TeamTagResponse> getTeamTagList(
        @LoginMember MemberDTO memberDTO
    ) {
        return new ResponseEntity<>(teamTagService.getTeamTagList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createTeamTag(
            @RequestBody TeamTagRequest teamTagRequest,
            @LoginMember MemberDTO memberDTO
    ) {
        teamTagService.addTeamTag(teamTagRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{teamTagId}")
    public ResponseEntity<Void> deleteTeamTag(
            @PathVariable Long teamTagId,
            @LoginMember MemberDTO memberDTO
    ) {
        teamTagService.deleteTeamTag(teamTagId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
