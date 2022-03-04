/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.CitoyenLoginService;
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

    public void setCin(String s)
    {
        this.cin.setText(s);
    }

    @FXML
    private void modifierMDP(ActionEvent event) {
        System.out.println("hhh");
        System.out.println(this.mdp.getText());
        if(this.mdp.getText().equals("") || this.confirmmdp.getText().equals(""))
        {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("L'un de champs est vide");
            a.show();
        }
        else
        {
            if(!(this.mdp.getText()).equals(this.confirmmdp.getText()))
            {
                this.labelerreur.setVisible(true);
                this.labelerreur.setText("Non Compatible");
                this.confirmmdp.setText("");
                this.mdp.setText("");
            }
            else
            {
                CitoyenLoginService e=new CitoyenLoginService();
                System.out.println(this.cin.getText());
               if( e.modifierMotDePasse(this.cin.getText(),this.mdp.getText()))
                    try {
                        Alert a=new Alert(Alert.AlertType.CONFIRMATION);
                        a.setContentText("Modification  Validée");
                        a.show();
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/CitoyenLogin.fxml"));
                        Parent root = loader.load();
                        CitoyenLoginController verif=loader.getController();
                        this.modifier.getScene().setRoot(root);
                        
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
               
            }
            
        }
    }
}
