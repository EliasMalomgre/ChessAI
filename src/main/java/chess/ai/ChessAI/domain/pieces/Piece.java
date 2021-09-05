/*
 * Abstract representation of a GUI chess piece for the Board
 * 7/4/20
 */
package chess.ai.ChessAI.domain.pieces;
import chess.ai.ChessAI.domain.Tile;
import chess.ai.ChessAI.gui.SelectableView;
import chess.ai.ChessAI.gui.TileView;
import javafx.geometry.Bounds;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Joseph
 */
@Getter
@Setter
public abstract class Piece {

    private final boolean isWhite;
    private Tile tile;

    public Piece(boolean isWhite, Tile tile) {
        this.isWhite = isWhite;
        this.tile = tile;
    }
}