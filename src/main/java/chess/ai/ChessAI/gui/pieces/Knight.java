/*
 * Class for the Knight StackPane GUI and functionality
 * 7/3/20
 */
package chess.ai.ChessAI.gui.pieces;

import chess.ai.ChessAI.gui.GameView;
import chess.ai.ChessAI.gui.GameInfo;
import chess.ai.ChessAI.gui.PieceView;
import chess.ai.ChessAI.gui.TileView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public final class Knight extends PieceView {
    
    /**
     * Attack pattern:
     * O    O    O    O    O    O    O    O 
     * O    O    O    O    O    O    O    O 
     * O    O    X    O    X    O    O    O 
     * O    X    O    O    O    X    O    O 
     * O    O    O    N    O    O    O    O 
     * O    X    O    O    O    X    O    O 
     * O    O    X    O    X    O    O    O 
     * O    O    O    O    O    O    O    O 
     */
    
    public String whiteKnight;
    public String blackKnight;
    
    public final void setPaths(String path) {
        whiteKnight = "src/main/resources/" + path + "/whiteknight.png";
        blackKnight = "src/main/resources/" + path + "/blackknight.png";
    }
    
    /**
     * Constructs a Knight
     * 
     * @param isWhite side of the piece
     * @param tile tile piece belongs to
     * @param path for image
    */
    public Knight(boolean isWhite, TileView tile, String path) {
        super(isWhite, tile);
        setPaths(path);
        Image image = null;
        try {
            if (isWhite) {
                image = new Image(Files.newInputStream(Paths.get(whiteKnight)));


            } else {
                image = new Image(Files.newInputStream(Paths.get(blackKnight)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(tileSize);
        imageView.setFitWidth(tileSize);
        this.getChildren().add(imageView);
    }
    
    @Override
    public void pieceAvailableMoves() {
        GameView controller = getController();
        TileView[][] tiles = controller.getTiles();
        int row = getTile().getRow();
        int col = getTile().getCol();
        ArrayList<TileView> available = getAvailable();
        
        int[][] offsets = {{1,2},{2,1},{-1,2},{-2,1},{1,-2},{2,-1},{-1,-2},{-2,-1}};
        for(int[] offset : offsets) {
            if(withinBounds(row+offset[0],col+offset[1])) {
                TileView tile = tiles[row+offset[0]][col+offset[1]];
                if(!tile.hasPiece() || (tile.getPiece().isWhite() != isWhite())) {
                    available.add(tile);
                }
            }
        }
    }
    
    @Override
    public void pieceAvailableMoves(ArrayList<TileView> whiteList) {
        GameView controller = getController();
        TileView[][] tiles = controller.getTiles();
        int row = getTile().getRow();
        int col = getTile().getCol();
        ArrayList<TileView> available = getAvailable();
        int[][] offsets = {{1,2},{2,1},{-1,2},{-2,1},{1,-2},{2,-1},{-1,-2},{-2,-1}};
        for(int[] offset : offsets) {
            if(withinBounds(row+offset[0],col+offset[1])) {
                TileView tile = tiles[row+offset[0]][col+offset[1]];
                if(whiteListed(whiteList, tile) && 
                        (!tile.hasPiece() || (tile.getPiece().isWhite() != isWhite()))) {
                    available.add(tile);
                }
            }
        }
    }
    
    @Override
    public ArrayList<int[]> calcCommonPieceLocations(int[] location) {
        GameView controller = getController();
        TileView[][] tiles = controller.getTiles();
        int row = location[0];
        int col = location[1];
        ArrayList<int[]> locations = new ArrayList<>();
        int[][] offsets = {{1,2},{2,1},{-1,2},{-2,1},{1,-2},{2,-1},{-1,-2},{-2,-1}};
        for(int[] offset : offsets) {
            if(withinBounds(row+offset[0],col+offset[1])) {
                TileView tile = tiles[row+offset[0]][col+offset[1]];
                if((tile.hasPiece() && (tile.getPiece().isWhite() == isWhite()) 
                        && tile.getPiece().isKnight()) && tile.getPiece() != this) {
                    int[] loc = {row+offset[0],col+offset[1]};
                    locations.add(loc);
                }
            }
        }
        return locations;
    }
    
    @Override
    public boolean isKnight() {
        return true;
    }
    
    @Override
    public String getNotation() {
        return "N";
    }
    
    @Override
    public int getValue() {
        return 3;
    }
    
    @Override
    public byte getInfoCode() {
        return isWhite() ? GameInfo.WHITE_KNIGHT : GameInfo.BLACK_KNIGHT;
    }
}
