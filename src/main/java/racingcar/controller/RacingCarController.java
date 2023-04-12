package racingcar.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.domain.GameManager;
import racingcar.dto.*;
import racingcar.service.RacingCarService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResultResponse> play(@RequestBody final NamesAndCountRequest namesAndCount) {
        ResultResponse resultResponse = racingCarService.playGame(namesAndCount);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle() {
        return ResponseEntity.badRequest().body("요청이 올바르지 않습니다.");
    }
}
