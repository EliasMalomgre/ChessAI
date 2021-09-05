/*
 * Class for the King StackPane GUI and functionality
 * 7/3/20
 */
package chess.ai.ChessAI.domain.pieces;

import chess.ai.ChessAI.domain.Tile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public final class King extends Piece {

    public King(boolean isWhite, Tile tile) {
        super(isWhite, tile);

    }
}
