package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CarsTest {
    Cars cars;

    @BeforeEach
    void setup() {
        List<String> empty = new ArrayList<>();
        cars = Cars.from(empty);
    }

    @DisplayName("Car 등록 테스트")
    @ParameterizedTest(name = "carNames = {0}, expectedSize = {1}")
    @MethodSource("carNamesDummy")
    void addCarTest(List<String> carNames, int expectedSize) {
        for (String carName : carNames) {
            cars.addCar(new Car(carName));
        }
        assertThat(cars.getCars()).hasSize(expectedSize);
    }

    @DisplayName("자동차 경기 우승자들 이름 조회 테스트")
    @Test
    void findWinnerNamesTest() {
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Car car3 = new Car("car3");
        cars.addCar(car1);
        cars.addCar(car2);
        cars.addCar(car3);
        car1.move(9);
        List<Car> winner = cars.findWinner();
        assertThat(winner.get(0).getName()).isEqualTo("car1");
    }

    static Stream<Arguments> carNamesDummy() {
        return Stream.of(
                Arguments.arguments(List.of("aaaa", "bbbb"), 2),
                Arguments.arguments(List.of("가나다라", "가나다라마", "가나다"), 3),
                Arguments.arguments(List.of("1234", "123", "12", "1234"), 4)
        );
    }
}
