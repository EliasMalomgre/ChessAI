package chess.ai.ChessAI.domain;


import chess.ai.ChessAI.domain.pieces.Piece;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
public final class Tile {

    public double tileSize;
    private int rowBoard; //position relative to board
    private int colBoard;
    private boolean isLight;
    private boolean isSelected;
    private Piece piece; //piece on tile
    private final boolean boardIsWhite;


    public Boolean hasPiece() {
        return piece != null;
    }
    
    /*public boolean hasKing() {
        return hasPiece() && Objects.requireNonNull(getPiece()).isKing();
    }


    public void movePiece(Tile tile) {
        tile.setPiece(this.getPiece()); 
        this.getPiece().setTile(tile);
        this.getPiece().moveToSlowly(tile);
        this.setPiece(null);
    }


    public void movePieceEnPassant(Tile tile, Tile taken) {
        tile.setPiece(this.getPiece()); 
        this.getPiece().setTile(tile);
        this.getPiece().moveToSlowly(tile);
        taken.setPiece(null);
        this.setPiece(null);
    }*/


    public Tile(boolean light, int row, int col, boolean boardIsWhite) {
        this.isLight = light;
        this.rowBoard = row;
        this.colBoard = col;
        this.boardIsWhite = boardIsWhite;
    }
    
}
