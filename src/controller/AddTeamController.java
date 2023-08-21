package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import model.*;

public class AddTeamController extends Controller<Teams> {
    @FXML
    private Button addButton;
    @FXML
    private TextField teamTf;
    
    private Parent root;
    
    private String getTeamText() {
        return teamTf.getText();
    }
    
    public Teams getTeams() {return model;}
    
    private Boolean testValidName(String text) {
        return text.matches("^[A-Z][a-zA-Z ]+$"); 
    }
    
    @FXML
    private void handleAdd(ActionEvent event) {
        String name = getTeamText();
        
        if (getTeams().hasTeam(name)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
                root = loader.load();
                ErrorController errorController = loader.getController();
                errorController.displayTeamName(name);
                
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
                stage.setTitle("Error!");

                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (!testValidName(name)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
                root = loader.load();
                ErrorController errorController = loader.getController();
                errorController.generateNameError();
                
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
                stage.setTitle("Error!");

                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
          getTeams().addTeam(new Team(name));
          Stage stage = (Stage) addButton.getScene().getWindow();
          stage.close();
        }
    }
    
}
