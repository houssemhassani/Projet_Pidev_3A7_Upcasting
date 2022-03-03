/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.MotDePasseOublieeService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class MDPOunliéeAdminController implements Initializable {

    @FXML
    private TextField cin;
    @FXML
    private TextField code;
    @FXML
    private Button verifieremail;
    @FXML
    private Button codeverifier;
    @FXML
    private Label random;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void verifieremail(ActionEvent event) {
        MotDePasseOublieeService mdp=new MotDePasseOublieeService();
        String verif=mdp.motDePasseOublieeAdmin(this.cin.getText());
        if(verif!=null)
        {
            this.random.setText(verif);
            this.code.setVisible(true);
            this.codeverifier.setVisible(true);
            this.cin.setDisable(true);
            this.verifieremail.setDisable(true);
            
        }
        
    }

    @FXML
    private void codeverifier(ActionEvent event) {
        if(this.code.getText().equals(this.random.getText()))
        {
             try {
                 Alert a=new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Vérification  Validée");
                    a.show();
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ModifierMotDePasseAdmin.fxml"));
                    Parent root = loader.load();
                    ModifierMotDePasseAdminController verif=loader.getController();
                    verif.setCin(this.cin.getText());  
                    this.codeverifier.getScene().setRoot(root);  
                } catch (IOException ex) {
                    
                }     
        }
        else
        {
            Alert a=new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Vérification Non Validée");
                    a.show();
        }
    }
    
    
}
