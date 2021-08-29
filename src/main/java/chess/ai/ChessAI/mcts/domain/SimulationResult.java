package chess.ai.ChessAI.mcts.domain;

import chess.ai.ChessAI.mcts.domain.ai.actions.Action;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SimulationResult {
    private Game game;
    private Action action;
}
