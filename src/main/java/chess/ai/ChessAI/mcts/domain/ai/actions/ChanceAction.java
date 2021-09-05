package chess.ai.ChessAI.mcts.domain.ai.actions;

import chess.ai.ChessAI.domain.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChanceAction implements Action {
    private final double probability;

    @Override
    public Game performAction(Game game) {
        return null;
    }
}
