package homeTry.member.dto.response;

import java.time.Duration;

public record MyPageResponse(
    String nickname,
    String email,
    Integer attendance,
    Duration weeklyTotal,
    Duration monthlyTotal
) {

}