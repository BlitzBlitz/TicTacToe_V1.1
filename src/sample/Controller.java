package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Optional;


public class Controller {

    @FXML
    private Label label;

    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;


    private Game game;

    public void initialize(){
        game = new Game(3);
        label.setText(((game.getTurn() == 1)? "X":"O") +"'s turn");
    }

    @FXML
    public void handleButtonClick(ActionEvent b){
        Button clickedOne =((Button)b.getSource());             // getting the button that was clicked

        int choice = clickedOne.getId().charAt(1) - '0';         //converting button id to int
        game.playTurn(choice);                                  //placing the choice

        clickedOne.setFont(new Font(40));
        clickedOne.setText((game.getTurn() == 1)? "X":"O");
        clickedOne.setDisable(true);                            //making changes to UI
        clickedOne.setEffect(new DropShadow());

        if(game.checkResult()){                                 // if checkResult() is true the game is over
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Game Over!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("file:icon.png"));
            if(game.getWinner() != 0){                          // if !0 we have a winner
                alert.setHeaderText("The winner is: " + ((game.getWinner() == 1)? "X":"O"));
            }else {
                alert.setHeaderText("It`s a DRAW!");        //if 0 we have a draw
            }
            alert.setContentText("Do you want to play again?");
            if(alert.showAndWait().get().equals(ButtonType.OK)){
                restartGame();
            }else {
                Platform.exit();
            }

        }
        label.setText(((game.getTurn() == 1)? "X":"O") +"'s turn");     // sets the label for next turn
    }

    public void restartGame(){
        initialize();
        restartButton(b1);
        restartButton(b2);
        restartButton(b3);
        restartButton(b4);
        restartButton(b5);
        restartButton(b6);
        restartButton(b7);
        restartButton(b8);
        restartButton(b9);
    }

    public void restartButton(Button b){
        b.setText("");
        b.setEffect(null);
        b.setDisable(false);
    }

}
