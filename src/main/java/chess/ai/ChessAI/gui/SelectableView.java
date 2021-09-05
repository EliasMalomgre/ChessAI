/*
 * A selectable dot for the user to make moves with
 * 7/4/20
 */
package chess.ai.ChessAI.gui;


import chess.ai.ChessAI.gui.pieces.Bishop;
import chess.ai.ChessAI.gui.pieces.Knight;
import chess.ai.ChessAI.gui.pieces.Queen;
import chess.ai.ChessAI.gui.pieces.Rook;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Joseph
 */
public class SelectableView extends Pane {
    
    public String whiteQueen;
    public String blackQueen;
    public String whiteKnight;
    public String blackKnight;
    public String whiteBishop;
    public String blackBishop;
    public String whiteRook;
    public String blackRook;
    public double tileSize;
    public double selectableSize;
    public double tileCircleSize;
    public static final Color GREY = Color.rgb(90,90,90,0.5); 
    public static final Color LIGHT_GREY = Color.rgb(110,110,110,0.5); 
    private final TileView tile;
    private final GameView controller;
    private final Circle noPiece;
    private final Rectangle rec;
    private final Shape shape;
    private final Shape crown;
    public static final String IMG_ICON_PATH = "src/main/resources/blackknight.png"; //icon paths
    
    public TileView getTile() {
        return tile;
    }

    public GameView getController() {
        return controller;
    }
    
    public final void setPaths(String path) {
        whiteQueen = "src/main/resources/" + path + "/whitequeen.png";
        blackQueen = "src/main/resources/" + path + "/blackqueen.png";
        whiteKnight = "src/main/resources/" + path + "/whiteknight.png";
        blackKnight = "src/main/resources/" + path + "/blackknight.png";
        whiteBishop = "src/main/resources/" + path + "/whitebishop.png";
        blackBishop = "src/main/resources/" + path + "/blackbishop.png";
        whiteRook = "src/main/resources/" + path + "/whiterook.png";
        blackRook = "src/main/resources/" + path + "/blackrook.png";
    }
    
    public final void setSizes(double scale) {
        tileSize = 100*scale;
        selectableSize = 15*scale;
        tileCircleSize = 45*scale;
    }
    
    /**
     * Constructs a selectable that can perform moves on GUI action, or perform moves
     * programmatically
     * @param tile, the tile selectable is on
     * @param controller, controller to redirect flow back to
     * @param hover, color of hover
     * @param ring, color of ring
     * @param solid, color of selectable
     * @param castle, color of castle ring 
     * @param app, object of application
     */
    public SelectableView(TileView tile, GameView controller, Color hover, Color ring, Color solid, Color castle, ChessLite app) {
        setPaths(app.getPath());
        setSizes(app.getScale());
        this.setStyle("-fx-cursor: hand;");
        this.controller = controller;
        this.tile = tile;
        
        noPiece = new Circle();
        noPiece.setRadius(selectableSize);
        noPiece.setFill(solid);
        noPiece.setLayoutX(tileSize / 2);
        noPiece.setLayoutY(tileSize / 2);

        Rectangle rect = new Rectangle(0, 0, tileSize, tileSize);
        Circle round = new Circle(tileSize / 2, tileSize / 2, (tileSize) / 2);
        shape = Shape.subtract(rect, round);
        shape.setFill(ring);
       
        crown = Shape.subtract(rect, round);
        crown.setFill(castle);
        
        rec = new Rectangle();
        rec.setWidth(tileSize);
        rec.setHeight(tileSize);
        rec.setFill(hover);
        
        setMinWidth(tile.getTileSize());
        setMinHeight(tile.getTileSize());
        
        setOnMouseClicked(e -> {
            controller.getSelectedTile().getPiece().setCloseable(false);
            move();
        });
    }
    
    public void setHighlightsNoHover() {
        if(!tile.hasPiece()) {
            setHasNoPiece();
        } else {
            setHasPiece();
        }
    }
    
    public void setHighlights() {
        if(!tile.hasPiece()) {
            setHasNoPieceHover();
        } else {
            setHasPieceHover();
        }
    }
    
    public void setHasNoPieceHover() {
        getChildren().add(noPiece);
        setOnMouseEntered(e -> {
            tile.getChildren().add(rec);
            getChildren().remove(noPiece);
        });
        setOnMouseExited(e -> {
            tile.getChildren().remove(rec);
            getChildren().add(noPiece);
        });
    }
    
    public void setHasPieceHover() {
        getChildren().add(shape);
        setOnMouseEntered(e -> {
            tile.getChildren().add(rec);
            getChildren().remove(shape);
        });
        setOnMouseExited(e -> {
            tile.getChildren().remove(rec);
            getChildren().add(shape);
        });
    }
    
    public void setHasNoPiece() {
        getChildren().add(noPiece);
    }
    
    public void setHasPiece() {
        getChildren().add(shape);
    }

    /**
     * A selectable move action to be called either on GUI click or programmatically
     * By default move does nothing, should be Override to perform an action
     */
    public void move() {}
    
    /**
     * To be called on event
     * By default move does nothing, should be Override to perform an action
     */
    public void onAction() {}
    
    public void setCrownIcon() {
        getChildren().add(crown);
        setOnMouseEntered(e -> {
            tile.getChildren().add(rec);
            getChildren().remove(crown);
        });
        setOnMouseExited(e -> {
            tile.getChildren().remove(rec);
            getChildren().add(crown);
        });
    }
    
    /**
     * Opens the promotion selection GUI to choose the piece to promote to
     * @param isWhite whether or not the promotion to is White
     * @param app, object for application
     */
    public void promotionSelection(boolean isWhite, ChessLite app) {
        Pane pane = new Pane();
        pane.setMinSize(tileSize, tileSize);
        VBox elements = new VBox();
        elements.setPadding(new Insets(30,10,10,10));
        elements.setSpacing(20);
        elements.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(0,15,0,15));
        buttons.setAlignment(Pos.CENTER);
        HBox buttons1 = new HBox();
        buttons1.setSpacing(10);
        buttons1.setPadding(new Insets(0,15,0,15));
        buttons1.setAlignment(Pos.CENTER);
        Label selectLabel = new Label("Select: ");
        selectLabel.setFont(new Font("Roboto",20*app.getScale()));
        selectLabel.setAlignment(Pos.CENTER);
        selectLabel.setId("smallfont");
        Button queen = new Button();
        queen.setId("promotionbutton");
        queen.setFocusTraversable(false);
        queen.setOnAction((event) -> {
            Stage theStage = (Stage) queen.getScene().getWindow();
            //app.getClip().play();
            controller.makeMovePromotion(tile, new Queen(isWhite, tile, app.getPath()));
            controller.clearSelectable();
            theStage.close();
        });
        ImageView queenImg = null;
        try {
            queenImg = new ImageView(new Image(Files.newInputStream(Paths.get(isWhite ?
                    whiteQueen : blackQueen))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        queenImg.setFitHeight(80*app.getScale());
        queenImg.setFitWidth(80*app.getScale());
        queen.setGraphic(queenImg);
        Button knight = new Button();
        knight.setId("promotionbutton");
        knight.setFocusTraversable(false);
        knight.setOnAction((event) -> {
            Stage theStage = (Stage) knight.getScene().getWindow();
            //app.getClip().play();
            controller.makeMovePromotion(tile, new Knight(isWhite, tile, app.getPath()));
            controller.clearSelectable();
            theStage.close();
        });
        ImageView knightImg = null;
        try {
            knightImg = new ImageView(new Image(Files.newInputStream(Paths.get(isWhite ?
                    whiteKnight : blackKnight))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        knightImg.setFitHeight(80*app.getScale());
        knightImg.setFitWidth(80*app.getScale());
        knight.setGraphic(knightImg);
        Button rook = new Button();
        rook.setId("promotionbutton");
        rook.setFocusTraversable(false);
        rook.setOnAction((event) -> {
            Stage theStage = (Stage)rook.getScene().getWindow();
            //app.getClip().play();
            controller.makeMovePromotion(tile, new Rook(isWhite, tile, app.getPath()));
            controller.clearSelectable();
            theStage.close();
        });
        ImageView rookImg = null;
        try {
            rookImg = new ImageView(new Image(Files.newInputStream(Paths.get(isWhite ?
                    whiteRook : blackRook))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rookImg.setFitHeight(80*app.getScale());
        rookImg.setFitWidth(80*app.getScale());
        rook.setGraphic(rookImg);
        Button bishop = new Button();
        bishop.setId("promotionbutton");
        bishop.setFocusTraversable(false);
        bishop.setOnAction((event) -> {
            Stage theStage = (Stage) bishop.getScene().getWindow();
            //app.getClip().play();
            controller.makeMovePromotion(tile, new Bishop(isWhite, tile, app.getPath()));
            controller.clearSelectable();
            theStage.close();
        });
        ImageView bishopImg = null;
        try {
            bishopImg = new ImageView(new Image(Files.newInputStream(Paths.get(isWhite ?
                    whiteBishop : blackBishop))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bishopImg.setFitHeight(80*app.getScale());
        bishopImg.setFitWidth(80*app.getScale());
        bishop.setGraphic(bishopImg);
        
        buttons.getChildren().addAll(queen,knight);
        buttons1.getChildren().addAll(rook,bishop);
        
        elements.getChildren().addAll(selectLabel, buttons, buttons1);
        pane.getChildren().add(elements);
        
        Scene scene = getController().getRoot().getScene();
        Scene secondScene = new Scene(pane, scene.getWidth()/2.3, scene.getHeight()/3);
        //secondScene.getStylesheets().add(Selectable.class.getResource("/resources/chess.css").toExternalForm());
        Stage newWindow = new Stage();

        try {
            newWindow.getIcons().add(new Image(Files.newInputStream(Paths.get(IMG_ICON_PATH))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        newWindow.setTitle("Pawn Promotion");
        newWindow.setScene(secondScene);
        newWindow.setResizable(false);
        newWindow.setOnCloseRequest((event)->{
            controller.getSelectedTile().getPiece().moveTo(controller.getSelectedTile());
            controller.clearSelectable();
        });

        newWindow.setX(scene.getWindow().getX() + scene.getWindow().getWidth()/4.5);
        newWindow.setY(scene.getWindow().getY() + scene.getWindow().getHeight()/3.2);
        newWindow.setWidth(80*app.getScale()*2 + 120*app.getScale());
        newWindow.setHeight(scene.getWindow().getHeight()/2.5);

        newWindow.initOwner(scene.getWindow());
        newWindow.initModality(Modality.APPLICATION_MODAL); 
        newWindow.showAndWait();

    }
}
