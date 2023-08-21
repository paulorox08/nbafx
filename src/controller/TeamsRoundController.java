package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.*;


public class TeamsRoundController extends Controller<Season> {
    
    @FXML private TableView roundTv;
    @FXML private ListView roundLv;
    @FXML private Button transferButton;
    @FXML private TableColumn gameCol;
    @FXML private TableColumn team1Col;
    @FXML private HBox container;
    @FXML private Button arrangeSeasonButton;

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
    
    private ObservableList<Team> getSelected() {
        return roundLv.getSelectionModel().getSelectedItems();
    }
    
    @FXML
    public void initialize() {
        roundTv.setPlaceholder(new Label("No teams added to round"));
        roundLv.setPlaceholder(new Label("All teams added to round."));
        roundLv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        transferButton.disableProperty().bind(
                Bindings.createBooleanBinding(() -> getSelected().size() != 2, getSelected()));
        
        arrangeSeasonButton.disableProperty().bind(Bindings.isNotEmpty(roundLv.getItems()));

    }
    
    @FXML
    public void handleTransfer() {
        getSeasonModel().addTeams(getSelected());
       
    }
   
    
    @FXML
    public void handleClose() {
        Stage stage = (Stage) arrangeSeasonButton.getScene().getWindow();
        stage.close();
    }

}



