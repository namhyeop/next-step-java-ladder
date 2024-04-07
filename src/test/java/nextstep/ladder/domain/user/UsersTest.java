package nextstep.ladder.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.ladder.error.exception.UserSizeEmptyException;
import org.junit.jupiter.api.Test;

class UsersTest {

    @Test
    void Users는_UserIndex를_사용하여_User를_조회한다() {
        Users users = new Users("pobi", "honux", "crong", "jk");
        assertThat(users.findUser(0)).isEqualTo(new User("pobi"));
    }

    @Test
    void Users는_자신의_크기를_반환해야_한다() {
        Users users = new Users("pobi", "honux", "crong", "jk");
        assertThat(users.size()).isEqualTo(4);
    }

    @Test
    void Users는_크기가_0일경우_예외가_발생해야_한다() {
        assertThatThrownBy(() -> new Users(List.of()))
            .isInstanceOf(UserSizeEmptyException.class)
            .hasMessage("등록된 사용자가 존재하지 않습니다 입력값: []");
    }
}
