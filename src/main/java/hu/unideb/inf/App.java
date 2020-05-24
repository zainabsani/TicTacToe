package hu.unideb.inf;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * JavaFX App Tic Tac Toe:
 * which can replace red circle with yellow circle
 * and replace a yellow circle with a green circle
 */

public class App extends Application {
    @FXML
    private TableView<GameResult> highScoreTable;
    @FXML
    private TableColumn<GameResult, java.time.Duration> duration;
    @FXML
    private TableColumn<GameResult, ZonedDateTime> created;
    @FXML
    private Label errorLabel;

    Stage WINDOW;
    Scene scene1,scene2;
    private GameResultDao gameResultDao;

    Label char1 = new Label();
    Label char2 = new Label();
    TextField player1= new TextField();
    TextField player2= new TextField();
    //use for player's name

    /**
     * Table to add scores to database which will show
     * top 5 on high score page
     */

    TableView table = new TableView();
    TableColumn dateCol = new TableColumn("Date");
    TableColumn timeCol = new TableColumn("Time");
    TableColumn playOneCol = new TableColumn("Player 1");
    TableColumn playTwoCol = new TableColumn("Player 2");
    TableColumn playOneTurnCol = new TableColumn("Player 1 turns");
    TableColumn playTwoTurnCol = new TableColumn("Player 2 turns");
    TableColumn winCol = new TableColumn("Winner");

    String character1 = "A";
    String character2="B";
    private String currentPlayer = character1;

    private Cell[][] cell = new Cell [3][3];
    private Label statusMsg = new Label();

    @Override
    public void start(Stage primaryStage) throws Exception {

        /**
         * Welcome Page designed Tic Tac Toe start page where
         * player names can be entered and saved
         */

        WINDOW = primaryStage;
        Label label1 = new Label("Tic Tac Toe");
        label1.setAlignment(Pos.CENTER);
        label1.setPadding(new Insets(20, 0, 30, 180));

        Label lblName = new Label("Player 1:");
        lblName.setMinWidth(75);
        lblName.setPadding(new Insets(5, 10, 0, 65));
        player1.setMinWidth(200);

        Label lblName2 = new Label("Player 2:");
        lblName2.setMinWidth(75);
        lblName2.setPadding(new Insets(5, 10, 0, 60));
        player2.setMinWidth(200);

        Button button = new Button("save");
        //log.info(player1+" saved");
        Button button2 = new Button("save");
        //log.info(player2+" saved");

        HBox pane3 = new HBox(2, lblName, player1, button);
        HBox pane4 = new HBox(2, lblName2, player2, button2);

        Label resp1 = new Label();
        Label resp2 = new Label();

        //HERREEEE
        button.setOnAction(e -> {
                character1 = player1.getText();
                char1.setText("Player 1 name is " + character1);
                char1.setPadding(new Insets(5, 10, 0, 60));
        });

        button2.setOnAction(e -> {
                character2 = player2.getText();
                char2.setText("Player 2 name is " + character2);
                char2.setPadding(new Insets(5, 10, 0, 60));
            });

        VBox layout = new VBox(5);
        layout.getChildren().addAll(label1, pane3, char1, char2, pane4,resp1,resp2);
        layout.setSpacing(5);
        layout.setPadding(new Insets(10, 10, 0, 10));

        Button button1 = new Button("Start Game");
        //log.info("Game Started");
        button1.setOnAction( e -> {
                    if(player1.getText().isEmpty() || player2.getText().isEmpty()){
                        resp1.setText("Field cannot be empty");
                    }
                    else{
                        WINDOW.setScene(scene2);
                    }
                });

        HBox hPane = new HBox(10, layout);
        BorderPane bPane = new BorderPane();
        bPane.setCenter(hPane);
        bPane.setBottom(button1);
        bPane.setAlignment(button1, Pos.BOTTOM_CENTER);
        bPane.setMargin(button1, new Insets(0, 0, 50, 0));
        scene1 = new Scene(bPane, 450, 300);

        /**
         * Game Page designed to the second page
         * where grid is implemented and status message about
         * next player is shown at the bottom
         */

        GridPane pane = new GridPane();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cell[i][j] = new Cell();
                pane.add(cell[i][j], j, i);
            }
        }
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(statusMsg);

        scene2 = new Scene(borderPane, 450, 300);
        //To display first scene
        WINDOW.setScene(scene1);
        WINDOW.setTitle("Tic Tac Toe");
        WINDOW.show();
    }

    /**
     * Checks if the board is full
     */

    public boolean isBoardFull(){
        for(int i = 0; i<3;i++){
            for(int j= 0; j<3; j++){
                if(cell[i][j].getPlayer() == " "){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * function to check if player has won
     * player can win with
     * same colors, same row or column or diagonal
     */

    public boolean hasWon(String player){
        for(int i=0;i<3;i++){
            if(cell[i][0].getPlayer() == player && cell[i][1].getPlayer() == player && cell[i][2].getPlayer() == player){
                return true;
                //log.info(player+" won");
            }
        }
        for(int i=0;i<3;i++){
            if(cell[0][i].getPlayer() == player && cell[1][i].getPlayer()  == player && cell[2][i].getPlayer() == player){
                return true;
                //log.info(player+" won");
            }
        }
        if(cell[0][0].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][2].getPlayer() == player){
            return true;
            //log.info(player+" won");
        }
        if(cell[0][2].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][0].getPlayer() == player){
            return true;
            //log.info(player+" won");
        }
        return false;
    }

    /**
     * class which contains actions of the cell
     */

    public class Cell extends Pane {
        private String player = " ";

        public Cell(){
            setStyle("-fx-border-color:black");
            this.setPrefSize(300,300);
            this.setOnMouseClicked(c -> handleClick());
        }


        private void handleClick() {
            if(player == " " && currentPlayer != " "){
                setPlayer(currentPlayer);

                if(hasWon(currentPlayer)){
                    Label msg = new Label(currentPlayer + " won!");
                    msg.setFont(new Font("Arial", 40));
                    //msg.setAlignment(Pos.CENTER);
                    currentPlayer = " ";
                    //place new scene Page 3
                    BorderPane bPane = new BorderPane();
                    bPane.setTop(msg);
                    bPane.setAlignment(msg, Pos.TOP_CENTER);

                    table.setEditable(true);
                    dateCol.setPrefWidth(116);
                    timeCol.setPrefWidth(110);
                    playOneCol.setPrefWidth(120);
                    playTwoCol.setPrefWidth(120);
                    playOneTurnCol.setPrefWidth(100);
                    playTwoTurnCol.setPrefWidth(100);
                    winCol.setPrefWidth(110);

                    table.getColumns().addAll(dateCol,timeCol,playOneCol,playTwoCol,playOneTurnCol,playTwoTurnCol,winCol);

                    Label la = new Label("High Score Table");
                    final VBox vbox = new VBox();
                    vbox.setSpacing(5);
                    vbox.setPadding(new Insets(10, 10, 0, 10));
                    vbox.getChildren().addAll(bPane,la,table);

                    //layout.getChildren().addAll(la,table);
                    //bPane.setCenter(la,);
                    Scene scene3 = new Scene(vbox, 800,500);
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(e ->
                            WINDOW.setScene(scene3));
                    pause.play();


                } else if(isBoardFull()){
                    Label msg = new Label("Draw !");
                    msg.setFont(new Font("Arial", 40));
                    currentPlayer = " ";
                    BorderPane bPane = new BorderPane();
                    bPane.setTop(msg);
                    bPane.setAlignment(msg, Pos.TOP_CENTER);

                    table.setEditable(true);
                    dateCol.setPrefWidth(116);
                    timeCol.setPrefWidth(110);
                    playOneCol.setPrefWidth(120);
                    playTwoCol.setPrefWidth(120);
                    playOneTurnCol.setPrefWidth(100);
                    playTwoTurnCol.setPrefWidth(100);
                    winCol.setPrefWidth(110);
                    table.getColumns().addAll(dateCol,timeCol,playOneCol,playTwoCol,playOneTurnCol,playTwoTurnCol,winCol);

                    Label la = new Label("High Score Table");
                    final VBox vbox = new VBox();
                    vbox.setSpacing(5);
                    vbox.setPadding(new Insets(10, 10, 0, 10));
                    vbox.getChildren().addAll(bPane,la,table);

                    Scene scene3 = new Scene(vbox, 800,500);
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(e ->
                            WINDOW.setScene(scene3));
                    pause.play();
                } else{
                    currentPlayer = (currentPlayer == character2) ? character1:character2;
                    statusMsg.setText(currentPlayer + " must play");
                }
            }
        }

        /**
         * function to get player
         */

        public String getPlayer(){
            return player;
        }

        /**
         * function to set player
         */

        public void setPlayer(String c){
            player = c;
            if(player == character1){
                Ellipse ell = new Ellipse(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2 -10, this.getHeight()/2 -10);
                ell.centerXProperty().bind(this.widthProperty().divide(2));
                ell.centerYProperty().bind(this.heightProperty().divide(2));
                ell.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                ell.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                ell.setFill(Color.MAROON);

                getChildren().add(ell);

            } else if(player == character2){
                Ellipse ellipse = new Ellipse(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2 -10, this.getHeight()/2 -10);
                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                ellipse.setFill(Color.RED);
                getChildren().add(ellipse);
            }
        }
    }

    /**
     * main function to launch argument
     */

    public static void main(String[] args) {
        launch(args);
    }
}