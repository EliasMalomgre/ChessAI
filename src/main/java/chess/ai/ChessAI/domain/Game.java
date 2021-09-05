package chess.ai.ChessAI.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

    private String id;
    private Board board;


    public Game(boolean whiteStart) {
        this.board = new Board(whiteStart);
    }

    public Game(Game game) {
        this.id = game.getId();
    }

}