package nextstep.ladder.domain.line;

import java.util.List;
import java.util.stream.IntStream;
import nextstep.ladder.error.exception.LineNoConsecutiveTrues;

public class Line {

    private final List<Point> points;

    public Line(LadderConnectOrder randomLadderConnectOrder) {
        this(initializeLine(randomLadderConnectOrder));
    }

    public Line(List<Point> points) {
        if (isConsecutiveTrues(points)) {
            throw new LineNoConsecutiveTrues();
        }
        this.points = points;
    }

    private static List<Point> initializeLine(LadderConnectOrder randomLadderConnectOrder) {
        return randomLadderConnectOrder.connectLadder();
    }


    private boolean isConsecutiveTrues(List<Point> points) {
        return IntStream.range(0, points.size() - 1)
            .anyMatch(pointIndex -> points.get(pointIndex).isPointTrue() && points.get(pointIndex + 1).isPointTrue());
    }

    public boolean canMoveRight(int userIndex) {
        if (isValidUserIndexSize(userIndex) && isRightTrue(userIndex)) {
            return true;
        }
        return false;
    }

    public boolean canMoveLeft(int userIndex) {
        if (isValidUserIndexPositive(userIndex) && isLeftTrue(userIndex)) {
            return true;
        }
        return false;
    }

    private boolean isValidUserIndexSize(int userIndex) {
        return userIndex < points.size();
    }

    private boolean isRightTrue(int userIndex) {
        return points.get(userIndex).isPointTrue();
    }

    private boolean isValidUserIndexPositive(int userIndex) {
        return userIndex > 0;
    }

    private boolean isLeftTrue(int userIndex) {
        return points.get(userIndex - 1).isPointTrue();
    }

    public List<Point> getPoints() {
        return points;
    }

    public int size() {
        return points.size();
    }
}
