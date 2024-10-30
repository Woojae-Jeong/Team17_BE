package homeTry.adminTag.teamTag.dto;

import homeTry.adminTag.teamTag.model.entity.TeamTag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TeamTagDTO(
    @NotNull(message = "tagId는 필수입니다")
    @Positive(message = "tagId는 양수값이여야 합니다")
    Long tagId,
    String teamTagName,
    String teamTagAttribute) {

    public static TeamTagDTO from(TeamTag teamTag) {
        return new TeamTagDTO(teamTag.getId(), teamTag.getTeamTagName().value(), teamTag.getTeamTagAttribute().value());
    }
    
}
