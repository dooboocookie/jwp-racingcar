package racingcar.dto;

public class RacingCarsResponse {
    private final String name;
    private final int position;

    public RacingCarsResponse(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}