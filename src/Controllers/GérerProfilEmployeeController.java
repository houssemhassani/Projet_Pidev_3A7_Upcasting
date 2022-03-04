/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class GérerProfilEmployeeController implements Initializable {

    @FXML
    private ImageView Exit;
    @FXML
    private Button gererresponsable;
    @FXML
    private Button gerercitoyens;
    @FXML
    private Button gereradmin;
    @FXML
    private Button gererpublication;
    @FXML
    private Button gererservice;
    @FXML
    private Button gereremployee;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuBack;
    @FXML
    private ImageView MenuClose;
    @FXML
    private AnchorPane slider;
    @FXML
    private TextField cin;
    @FXML
    private Button updateemail;
    @FXML
    private TextField cin2;
    @FXML
    private TextField mdp;
    @FXML
    private TextField confirmmdp;
    @FXML
    private Button updatemdp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gererresponsable(ActionEvent event) {
    }

    @FXML
    private void gerercitoyens(ActionEvent event) {
    }

    @FXML
    private void gereradmin(ActionEvent event) {
    }

    @FXML
    private void gererpublication(ActionEvent event) {
    }

    @FXML
    private void gererservice(ActionEvent event) {
    }

    @FXML
    private void gereremployee(ActionEvent event) {
    }

    @FXML
    private void updateemail(ActionEvent event) {
    }

    @FXML
    private void updatemdp(ActionEvent event) {
    }
    
}
