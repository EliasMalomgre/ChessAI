package chess.ai.ChessAI.domain;


import chess.ai.ChessAI.domain.pieces.King;
import chess.ai.ChessAI.domain.pieces.Piece;
import chess.ai.ChessAI.domain.pieces.PieceType;
import chess.ai.ChessAI.gui.GameInfo;
import chess.ai.ChessAI.gui.PieceView;

import java.util.ArrayList;

import static chess.ai.ChessAI.gui.GameView.HEIGHT;
import static chess.ai.ChessAI.gui.GameView.WIDTH;

public class Board {

    private final Tile[][] tiles;
    private final PieceType[] NOTATION_TO_CONSTRUCTOR_TABLE = {PieceType.NoPiece, //convert ByteBoard to objects
            PieceType.WhitePawn, PieceType.WhiteBishop, PieceType.WhiteKnight,
            PieceType.WhiteRook, PieceType.WhiteQueen, PieceType.WhiteKing,
            PieceType.BlackPawn, PieceType.BlackBishop, PieceType.BlackKnight,
            PieceType.BlackRook, PieceType.BlackQueen, PieceType.BlackKing};
    private Piece blackKing; //kings
    private Piece whiteKing;
    private final ArrayList<Piece> blackNotKing = new ArrayList<>(); //pieces
    private final ArrayList<Piece> whiteNotKing = new ArrayList<>();


    public Board(boolean whiteStart){
        tiles = new Tile[8][8];
        initBoard(GameInfo.INITIAL_BOARD);
    }



    public final void initBoard(byte[][] board) {
        boolean isLight = false;
        blackNotKing.clear();
        whiteNotKing.clear();
        //i is row, j is column
        for(int i = 0; i < HEIGHT; i++) {
            for(int j = 0; j < WIDTH; j++) {
                Tile tile = new Tile(isLight, i, j, true);
                tiles[i][j] = tile;
                isLight = !isLight;
                Piece piece = NOTATION_TO_CONSTRUCTOR_TABLE[board[i][j]].createPiece(tiles[i][j]);
                if(piece != null) {
                    if(piece.isWhite()) {
                        if(piece.getClass().equals(King.class)) {
                            whiteKing = piece;
                        } else {
                            whiteNotKing.add(piece);
                        }
                    } else {
                        if(piece.getClass().equals(King.class)) {
                            blackKing = piece;
                        } else {
                            blackNotKing.add(piece);
                        }
                    }
                    tiles[i][j].setPiece(piece);
                }
            }
            isLight = !isLight;
        }
    }
}
