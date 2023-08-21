package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Game;
import model.Season;
import model.Team;


public class CurrentRoundTeamsController extends Controller<Season> {
    
    @FXML private Button closeButton;
    
    public Season getSeasonModel() {return model;}
    
    public final ObservableList<Team> getTeams() {
        return getSeasonModel().getCurrentTeams();
    }
    
    public final ObservableList<Game> getSchedule() {
        return getSeasonModel().getCurrentSchedule();
    }
    
    public Integer getRound() {
        return getSeasonModel().round() + 1;
    }
    
    @FXML
    public void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close(); 
    }
    
}







