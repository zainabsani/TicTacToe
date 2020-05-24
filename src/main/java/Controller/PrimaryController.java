package Controller;
import java.io.IOException;
import java.time.Instant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class PrimaryController extends ActionEvent {

    @FXML
    private void switchToScoreTable() throws IOException {
        //App.setRoot("scoretable");
    }
}
//    private String player1;
//    private String player2;
//
//
//    @FXML
//    private Label winner;
//
//    @FXML
//    private GridPane gameGrid;
//
//    @FXML
//    private Button doneButton;
//
//    private char currentPlayer = 'A';
//    private Cell[][] cell = new Cell[3][3];
//    private Label statusMsg = new Label("A must play");
//
//    Stage WINDOW;
//    Scene scene1,scene2;
//
//    public void start(Stage primaryStage) throws Exception{
//        //Game page
//        GridPane pane = new GridPane();
//
//        for (int i = 0; i < 3; i++){
//            for (int j=0; j<3; j++){
//                cell[i][j] = new Cell();
//                pane.add(cell[i][j], j, i);
//            }
//        }
//        BorderPane borderPane = new BorderPane();
//        borderPane.setCenter(pane);
//        borderPane.setBottom(statusMsg);
//        scene2 = new Scene(borderPane, 450, 300);
//        //To display first scene
//        WINDOW.setScene(scene1);
//        WINDOW.setTitle("Tic Tac Toe");
//        WINDOW.show();
//    }
//
//    public boolean isBoardFull(){
//        for(int i = 0; i<3;i++){
//            for(int j= 0; j<3; j++){
//                if(cell[i][j].getPlayer() == ' '){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public boolean hasWon(char player){
//        for(int i=0;i<3;i++){
//            if(cell[i][0].getPlayer() == player && cell[i][1].getPlayer() == player && cell[i][2].getPlayer() == player){
//                return true;
//            }
//        }
//        for(int i=0;i<3;i++){
//            if(cell[0][i].getPlayer() == player && cell[1][i].getPlayer()  == player && cell[2][i].getPlayer() == player){
//                return true;
//            }
//        }
//        if(cell[0][0].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][2].getPlayer() == player){
//            return true;
//        }
//        if(cell[0][2].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][0].getPlayer() == player){
//            return true;
//        }
//        return false;
//    }
//
//    public class Cell extends Pane{
//        private char player = ' ';
//
//        public Cell(){
//            setStyle("-fx-border-color:black");
//            this.setPrefSize(300,300);
//            this.setOnMouseClicked(c -> handleClick());
//        }
//
//        private void handleClick() {
//            if(player == ' ' && currentPlayer != ' '){
//                setPlayer(currentPlayer);
//
//                if(hasWon(currentPlayer)){
//                    Label msg = new Label(currentPlayer + " won!");
//                    currentPlayer = ' ';
//                    //place new scene
//                    BorderPane bPane = new BorderPane();
//                    bPane.setTop(msg);
//                    VBox layout= new VBox(5);
//                    Label la = new Label("High Score Table");
//                    layout.getChildren().addAll(la);
//                    bPane.setCenter(la);
//                    Scene scene3 = new Scene(bPane, 450,300);
//                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
//                    pause.setOnFinished(e ->
//                            WINDOW.setScene(scene3));
//                    pause.play();
//
//
//                } else if(isBoardFull()){
//                    statusMsg.setText("Draw !");
//                    currentPlayer = ' ';
//                } else{
//                    currentPlayer = (currentPlayer == 'A') ? 'B':'A';
//                    statusMsg.setText(currentPlayer + " must play");
//                }
//            }
//        }
//
//        public char getPlayer(){
//            return player;
//        }
//
//        public void setPlayer(char c){
//            player = c;
//            if(player == 'A'){
//                Ellipse ell = new Ellipse(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2 -10, this.getHeight()/2 -10);
//                ell.centerXProperty().bind(this.widthProperty().divide(2));
//                ell.centerYProperty().bind(this.heightProperty().divide(2));
//                ell.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
//                ell.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
//                //ellipse.setStroke(Color.RED);
//                //ellipse.setStroke(Color.RED);
//                ell.setFill(Color.MAROON);
//
//                getChildren().add(ell);
//
//            } else if(player == 'B'){
//                Ellipse ellipse = new Ellipse(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2 -10, this.getHeight()/2 -10);
//                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
//                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
//                ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
//                ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
//                //ellipse.setStroke(Color.RED);
//                //ellipse.setStroke(Color.RED);
//                ellipse.setFill(Color.RED);
//
//                getChildren().add(ellipse);
//            }
//        }
//    }

