package racingcar.controller;

import racingcar.domain.GameManager;
import racingcar.domain.NumberGenerator;
import racingcar.dto.CarNamesRequest;
import racingcar.dto.CarStatusResponse;
import racingcar.dto.GameResultResponse;
import racingcar.dto.GameRoundRequest;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingCarController {
    private final InputView inputView;//입력
    private final OutputView outputView;//출력
    private final GameManager gameManager;//서비스 로직

    public RacingCarController(InputView inputView, OutputView outputView, NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameManager = new GameManager(numberGenerator);
    }

    public void newCarNames() {
        boolean hasException = true;
        while (hasException) {
            hasException = newCarNamesHasException();
        }
    }

    public void newGameRound() {
        boolean hasException = true;
        while (hasException) {
            hasException = newGameRoundHasException();
        }
    }

    public void play() {
        outputView.printResultMessage();
        while (!gameManager.isEnd()) {
            List<CarStatusResponse> roundResultCarStatus = gameManager.playGameRound();
            outputView.printRoundResult(roundResultCarStatus);
        }
        GameResultResponse winnerNames = gameManager.decideWinner();
        outputView.printEndGameResult(winnerNames);
    }

    private boolean newCarNamesHasException() {
        try {
            CarNamesRequest inputCarNames = inputView.inputCarName();
            gameManager.createCars(inputCarNames);
            return false;
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return true;
        }
    }

    private boolean newGameRoundHasException() {
        try {
            GameRoundRequest inputGameRound = inputView.inputGameRound();
            gameManager.createGameRound(inputGameRound);
            return false;
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return true;
        }
    }
}