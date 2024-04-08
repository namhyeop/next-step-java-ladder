package nextstep.ladder.domain.lines;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.ladder.domain.ladderConnectOrder.LadderConnectOrder;
import nextstep.ladder.domain.lines.line.Line;
import nextstep.ladder.domain.lines.line.impl.LineImpl;
import nextstep.ladder.error.exception.LineNoConsecutiveTrues;
import nextstep.ladder.domain.ladderConnectOrder.impl.RandomLadderConnectOrder;
import org.junit.jupiter.api.Test;

class LineTest {

    @Test
    void Line은_사람의_수보다_하나_작게_생성되어야_한다() {
        int countOfPerson = 4;
        LadderConnectOrder randomLadderConnectOrder = new RandomLadderConnectOrder(
            List.of(false, false, true)); //|     |     |-----|

        Line sutLine = new LineImpl(randomLadderConnectOrder);

        assertThat(sutLine.size()).isEqualTo(countOfPerson - 1);
    }

    @Test
    void Line에_두_개의_사다리가_연결된_경우_예외가_발생한다() {
        assertThatThrownBy(
            () -> new LineImpl(List.of(new Point(true), new Point(true))))
            .isInstanceOf(LineNoConsecutiveTrues.class)
            .hasMessage("Line은 연속해서 사다리를 연결할 수 없습니다");
    }

    @Test
    void Line은_UserIndex를_오른쪽으로_이동할지_결정한다() {
        Line firstLineImpl = new LineImpl(List.of(new Point(true), new Point(false))); //  |-----|     |
        Line secondLineImpl = new LineImpl(List.of(new Point(false), new Point(true))); // |     |-----|

        assertThat(firstLineImpl.canMoveRight(0)).isTrue();
        assertThat(secondLineImpl.canMoveRight(0)).isFalse();
    }

    @Test
    void Line의_가장_오른쪽에_사용자가_위치한_경우_오른쪽으로_이동할_수_없다() {
        Line line = new LineImpl(List.of(new Point(true), new Point(false))); //  |-----|     |

        assertThat(line.canMoveRight(2)).isFalse();
    }

    @Test
    void Line은_UserIndex를_왼쪽으로_이동할지_결정한다() {
        Line firstLineImpl = new LineImpl(List.of(new Point(true), new Point(false))); //  |-----|     |
        Line secondLineImpl = new LineImpl(List.of(new Point(false), new Point(true))); // |     |-----|

        assertThat(firstLineImpl.canMoveLeft(1)).isTrue();
        assertThat(secondLineImpl.canMoveLeft(1)).isFalse();
    }

    @Test
    void Line의_가장_왼쪽에_사용자가_위치한_경우_왼쪽으로_이동할_수_없다() {
        Line line = new LineImpl(List.of(new Point(true), new Point(false))); //  |-----|     |

        assertThat(line.canMoveLeft(0)).isFalse();
    }
}