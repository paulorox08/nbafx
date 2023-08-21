package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;

public class PlayerUpdateController extends Controller<Team>{
    
    @FXML private Button updateButton;
    @FXML private Button addButton;
    @FXML private Button closeButton;
    @FXML private TextField nameTf;
    @FXML private TextField creditTf;
    @FXML private TextField ageTf;
    @FXML private TextField noTf; 
    private Parent root;
    private String playerName;
    
    public Team getTeamModel() {return model;}
    
    @FXML
    public void initialize() {
        try {
            for (Player i: getTeamModel().getPlayers().getFilteredPlayersList()) {
                nameTf.setText(i.getName());
                creditTf.setText(String.valueOf(i.getCredit()));
                ageTf.setText(String.valueOf(i.getAge()));
                noTf.setText(String.valueOf(i.getNo()));
            }
        } catch (NullPointerException e) {
        }
        
        if ("".equals(nameTf.getText())) {
            disableUpdateButton();
        }
        else {
            disableAddButton();
        }
    }
 
    private Boolean testValidName(String text) {
        return text.matches("^[A-Z][a-zA-Z ]+$");
    }
    private Boolean testValidCredit(String text) {
        return text.matches("^-?\\d+(\\.\\d+)?$");
    }
    private Boolean testValidAge(String text) {
        return text.matches("^\\d+$");
    }
    private Boolean testValidNo(String text) {
        return text.matches("^\\d+$");
    }
    
    private void generateErrors(String name, String credit, String age, String no) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
            root = loader.load();
            ErrorController errorController = loader.getController();
            if (!testValidName(name)) {
                errorController.generateNameError();
            }
            if (!testValidCredit(credit)) {
                errorController.generateCreditError();
            }
            if (!testValidAge(age)) {
                errorController.generateAgeError();
            }
            if (!testValidNo(no)) {
                errorController.generateNoError();
            }
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
    
    @FXML
    public void disableAddButton() {
        addButton.setDisable(true);
    }
    @FXML
    public void disableUpdateButton() {
        updateButton.setDisable(true);
    }
    
    @FXML
    public void handleAdd(ActionEvent event) {
        String name = nameTf.getText();
        String credit = creditTf.getText();
        String age = ageTf.getText();
        String no = noTf.getText();
        
        if (!testValidName(name) || !testValidCredit(credit) || !testValidAge(age) || !testValidNo(no)) {
            generateErrors(name, credit, age, no);
        }
        else {

            Player newPlayer = new Player(name, Double.valueOf(credit), Integer.valueOf(age), Integer.valueOf(no));
            getTeamModel().getPlayers().addPlayer(newPlayer);
            newPlayer.setTeam(getTeamModel());
        }
        
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void handleUpdate(ActionEvent event) {
        String name = nameTf.getText();
        String credit = creditTf.getText();
        String age = ageTf.getText();
        String no = noTf.getText();
        
        if (!testValidName(name) || !testValidCredit(credit) || !testValidAge(age) || !testValidNo(no)) {
            generateErrors(name, credit, age, no);
        }
        else {
            for (Player i: getTeamModel().getPlayers().getFilteredPlayersList()) {
                i.setName(name);
                i.setCredit(Double.valueOf(credit));
                i.setAge(Integer.valueOf(age));
                i.setNo(Integer.valueOf(no));
            }
            
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.close();
        }
    }
    
    @FXML
    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
   
}
