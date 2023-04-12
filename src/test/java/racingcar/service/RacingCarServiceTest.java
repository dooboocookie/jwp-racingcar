package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dto.NamesAndCountRequest;
import racingcar.dto.RacingCarResponse;
import racingcar.dto.ResultResponse;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RacingCarServiceTest {

    private final RacingCarService racingCarService;

    @Autowired
    RacingCarServiceTest(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @DisplayName("이름들과 시도 횟수를 입력받아 결과를 반환한다.")
    @Test
    void playGame() {
        //given
        NamesAndCountRequest namesAndCountRequest = new NamesAndCountRequest("망고,루카,소니,현구막", 10);
        //when
        ResultResponse resultResponse = racingCarService.playGame(namesAndCountRequest);
        //then
        List<String> names = resultResponse.getRacingCars().stream().map(RacingCarResponse::getName).collect(Collectors.toList());
        assertThat(resultResponse.getWinners()).isNotNull();
        assertThat(names).containsExactly("망고", "루카", "소니", "현구막");
    }
}