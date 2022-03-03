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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierMotDePasseCitoyenController implements Initializable {

    @FXML
    private Label cin;
    @FXML
    private PasswordField mdp;
    @FXML
    private PasswordField confirmmdp;
    @FXML
    private Button modifier;
    @FXML
    private Label labelerreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifierMDP(ActionEvent event) {
    }
    public void setCin(String s)
    {
        this.cin.setText(s);
    }
}
