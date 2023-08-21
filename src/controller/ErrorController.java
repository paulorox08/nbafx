package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.*;

public class ErrorController extends Controller<Validator> {
    @FXML
    private Button okayButton;
    @FXML
    private VBox container;
    
    private Text teamExistsTf;
    private Text nameErrorTf;
    private Text ageErrorTf;
    private Text creditErrorTf;
    private Text noErrorTf;
    private Text noGamesTf;
    private Text oneGameTf;
    private Text finalGameTf;

    public Validator validator() {return model;}
    @FXML
    public void close() {
        Stage stage = (Stage) okayButton.getScene().getWindow();
        stage.close();
    }
   
    @FXML
    public void displayTeamName(String name) {
        teamExistsTf = new Text(name + " Team already exists");
        teamExistsTf.getStyleClass().add("text-out");
        container.getChildren().add(teamExistsTf);
    }
    
    @FXML
    public void generateNameError() {
        nameErrorTf = new Text("Incorrect name pattern!");
        nameErrorTf.getStyleClass().add("text-out");
        container.getChildren().add(nameErrorTf);
    }
    @FXML
    public void generateCreditError() {
        creditErrorTf = new Text ("Incorrect credit pattern!");
        creditErrorTf.getStyleClass().add("text-out");
        container.getChildren().add(creditErrorTf);
    }
    @FXML
    public void generateAgeError() {
        ageErrorTf = new Text("Incorrect age pattern!");
        ageErrorTf.getStyleClass().add("text-out");
        container.getChildren().add(ageErrorTf);
    }
    @FXML
    public void generateNoError() {
        noErrorTf = new Text("Incorrect number pattern!");
        noErrorTf.getStyleClass().add("text-out");
        container.getChildren().add(noErrorTf);
    }
    @FXML
    public void noGames() {
        noGamesTf = new Text("No Games to play!\nPlease add games to this round.");
        noGamesTf.getStyleClass().add("text-out");
        container.getChildren().add(noGamesTf);
    }
    @FXML
    public void oneGame() {
        oneGameTf = new Text("All games finished! You can check results now!");
        oneGameTf.getStyleClass().add("text-out");
        container.getChildren().add(oneGameTf);
    }
    
    @FXML
    public void finalGame(String name) {
        finalGameTf = new Text("All games finished! You can check results now!\nThis season ends!\n" + name + " is the Champion!!");
        finalGameTf.getStyleClass().add("text-out");
        container.getChildren().add(finalGameTf);
    }
    
}
