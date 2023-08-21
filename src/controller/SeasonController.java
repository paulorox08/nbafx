package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Record;
import model.Season;
import model.Team;


public class SeasonController extends Controller<Season> {
       
    @FXML
    private Button roundButton;
    @FXML
    private Button currentButton;
    @FXML
    private Button gameButton;
    @FXML
    private Button resultButton;
    @FXML
    private Button closeButton;
    private Parent root;
    
    public Season getSeasonModel() {return model;}
    
    public String getChampion() {
        return getSeasonModel().getCurrentTeams().get(0).getName();
    }
    
    @FXML
    public void handleRound() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getSeasonModel(), "/view/SeasonRoundView.fxml", "Season Rounds", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void handleCurrent() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getSeasonModel(), "/view/CurrentRoundTeams.fxml", "Tournament", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void handleGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
            root = loader.load();
            ErrorController error = loader.getController();
            if (getSeasonModel().getCurrentSchedule().isEmpty()) {
                error.noGames();
            }
            else {
                if (getSeasonModel().round() == 0) {
                    getSeasonModel().playGame();
                    error.oneGame();
                }
                else if (getSeasonModel().round() == 1) {
                    getSeasonModel().playGame();
                    error.finalGame(getChampion());
                }
            }
            
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            stage.setTitle("All Games Played!");
            
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void handleResult() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getSeasonModel(), "/view/RecordView.fxml", "Season Record", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
   
}

