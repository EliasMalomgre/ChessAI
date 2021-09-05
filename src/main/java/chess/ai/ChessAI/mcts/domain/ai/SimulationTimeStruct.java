package chess.ai.ChessAI.mcts.domain.ai;

import chess.ai.ChessAI.domain.Game;
import chess.ai.ChessAI.mcts.domain.ai.actions.Action;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class SimulationTimeStruct {
    private final Long time;
    private final Game game;
    private final Action action;
}
