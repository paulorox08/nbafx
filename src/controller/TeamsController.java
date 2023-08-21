package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import static javafx.scene.input.KeyCode.T;
import model.*;

public class TeamsController extends Controller<Teams> {
    @FXML
    private TableView<Team> teamsTv;
    @FXML
    private Button closeButton;
    @FXML
    private Button manageButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
   
    public Teams getTeams() {return model;}
    
    public ObservableList<Team> getTeam() {
        return getTeams().currentTeams();
    }
   

    @FXML
    public void addTeams() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(getTeams(), "/view/AddTeam.fxml", "Adding New Team", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void initialize() {
        manageButton.disableProperty().bind(Bindings.isEmpty(teamsTv.getSelectionModel().getSelectedItems()));
        deleteButton.disableProperty().bind(Bindings.isEmpty(teamsTv.getSelectionModel().getSelectedItems()));
        addButton.disableProperty().bind(Bindings.isNotEmpty(teamsTv.getSelectionModel().getSelectedItems()));
    }
    
    private Team manageTeam() {
        return teamsTv.getSelectionModel().getSelectedItem();
    }

    public String getTeamName() {
        return manageTeam().getName();
    }
    
    @FXML
    public void handleManage(ActionEvent event) throws Exception {
        String name = getTeamName();
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(manageTeam(), "/view/ManageTeamView.fxml", "Managing Team: " + name, stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    public void handleDelete(ActionEvent event) {
        
        for (int x = 0; x < getTeam().size(); x ++) {
            if (manageTeam().getName().equals(getTeam().get(x).getName())) {
                getTeams().remove(getTeam().get(x));
            }
        }
    }
    
}

