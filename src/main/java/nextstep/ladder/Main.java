package nextstep.ladder;

import java.util.List;
import nextstep.ladder.domain.line.Lines;
import nextstep.ladder.domain.line.RandomLadderConnectOrder;
import nextstep.ladder.domain.result.LadderResult;
import nextstep.ladder.domain.result.Results;
import nextstep.ladder.domain.user.User;
import nextstep.ladder.domain.user.Users;
import nextstep.ladder.view.Input;
import nextstep.ladder.view.Output;

public class Main {

    public static void main(String[] args) {
        Users users = new Users(Input.inputPersonName());
        List<String> result = Input.inputExecutionResult();
        int height = Input.inputLadderHeight();
        Lines lines = new Lines(height, RandomLadderConnectOrder.createLadderConnectOrders(height,
            users.size()));

        Output.printLadderConsoleResult(users, lines, result);

        LadderResult ladderResult = new LadderResult(Results.createResults(result), users);
        ladderResult.calculateLadderResult(users, lines);

        while (true) {
            String userName = Input.inputUserForDrawConfirmation();
            if (Output.findAllDrawResult(ladderResult, userName)) {
                break;
            }

            Output.printDrawResult(ladderResult, new User(userName));
        }

    }
}
