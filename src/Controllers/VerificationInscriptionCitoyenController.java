/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class VerificationInscriptionCitoyenController implements Initializable {

    @FXML
    private Label email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public  void setEmail(String email)
    {
        this.email.setVisible(true);
        this.email.setText(email);
        
    }
    
}
