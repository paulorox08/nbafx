package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import static com.sun.javafx.application.PlatformImpl.addListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Association;
import model.*;

public class ViewPlayersController extends Controller<Teams> {
    @FXML private TableView<Player> playersTv;
    @FXML private Button closeButton;
    @FXML private TextField levelTf;
    @FXML private TextField nameTf;
    @FXML private TextField ageFromTf;
    @FXML private TextField ageToTf;
    private String nameToGet = "";
    private String levelToGet = "";
    private String fromAgeToGet = "0";
    private String toAgeToGet = "1000";
    private ObservableList<Player> filtration = FXCollections.<Player>observableArrayList();
    
    public Teams getTeamsModel() {return model;}
    
    public ObservableList<Team> getTeamsList() {return getTeamsModel().currentTeams();}
    
    public void getStuff() {
        for (Team t: getTeamsList()) {
            for (Player p: t.getPlayers().getPlayersList()) {
                filtration.add(p);
            }
        }
    }
    
    public ObservableList<Player> getList() {return filtration;}
    
    @FXML
    public void initialize() {
        playersTv.setPlaceholder(new Label("Players list is not loaded."));
        getStuff();
        levelTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                levelToGet = newValue;
                filtration.clear();
                for (Team t: getTeamsModel().currentTeams()) {
                    t.getPlayers().filterList(nameToGet, levelToGet, Integer.valueOf(fromAgeToGet), Integer.valueOf(toAgeToGet));
                    for (Player p: t.getPlayers().getFilteredPlayersList()) {
                        filtration.add(p);
                    };
                }
            }
        });
        nameTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                nameToGet = newValue;
                filtration.clear();
                for (Team t: getTeamsModel().currentTeams()) {
                    t.getPlayers().filterList(nameToGet, levelToGet, Integer.valueOf(fromAgeToGet), Integer.valueOf(toAgeToGet));
                    for (Player p: t.getPlayers().getFilteredPlayersList()) {
                        filtration.add(p);
                    };
                }
            }
        });
        ageFromTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                fromAgeToGet = newValue;
                filtration.clear();
                for (Team t: getTeamsModel().currentTeams()) {
                    t.getPlayers().filterList(nameToGet, levelToGet, Integer.valueOf(fromAgeToGet), Integer.valueOf(toAgeToGet));
                    for (Player p: t.getPlayers().getFilteredPlayersList()) {
                        filtration.add(p);
                    };
                }
            }
        });
        ageToTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                toAgeToGet = newValue;
                filtration.clear();
                for (Team t: getTeamsModel().currentTeams()) {
                    t.getPlayers().filterList(nameToGet, levelToGet, Integer.valueOf(fromAgeToGet), Integer.valueOf(toAgeToGet));
                    for (Player p: t.getPlayers().getFilteredPlayersList()) {
                        filtration.add(p);
                    };
                }
            }
        });
    }
    
    @FXML
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}

