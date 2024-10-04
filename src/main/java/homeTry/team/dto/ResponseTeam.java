package homeTry.team.dto;

import homeTry.tag.dto.TagDTO;
import homeTry.team.model.entity.Team;

import java.util.List;

public record ResponseTeam(
        Long id,
        String teamName,
        String leaderNickname,
        String teamDescription,
        long maxParticipants,
        long currentParticipants,
        String password,
        List<TagDTO> tagList
){
    public static ResponseTeam of(Team team, List<TagDTO> tagList){
        return new ResponseTeam(
                team.getId(),
                team.getTeamName().getValue(),
                team.getLeader().getNickname(),
                team.getTeamDescription().getValue(),
                team.getMaxParticipants().getValue(),
                team.getCurrentParticipants().getValue(),
                team.getPassword().getValue(),
                tagList);
    }
}