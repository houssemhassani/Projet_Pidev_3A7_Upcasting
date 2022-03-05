/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.ResponsableLoginService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class GérerProfilResponsableController implements Initializable {

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
    @FXML
    private TextField email;
    @FXML
    private Label nomlabel;
    @FXML
    private Label cinlabel;

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
         if(this.cin.getText().equals("") || this.email.getText().equals(""))
        {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("L'un de champ est vide");
            a.show();
            return;
        }
        ResponsableLoginService v=new ResponsableLoginService();
       boolean test=v.modifierEmail(this.cin.getText(), this.email.getText());
       if(test)
       {
           Alert a=new Alert(Alert.AlertType.INFORMATION);
           a.setTitle("confirmation !!");
           
           a.setHeaderText("");
           a.setContentText("Modification effectuée avec succées");
           a.show();
           this.cin.setText("");
           this.email.setText("");
           
       }
       else
       {
           Alert a=new Alert(Alert.AlertType.ERROR);
           a.setTitle("Echec !!");
           
           a.setHeaderText("");
           a.setContentText("CIN Introuvable");
           a.show();
           this.cin.setText("");
           this.email.setText("");
       }
    }

    @FXML
    private void updatemdp(ActionEvent event) {
        if(this.cin2.getText().equals("")||this.confirmmdp.getText().equals("")||
                this.mdp.getText().equals(""))
        {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("");
            a.setContentText("L'un de champs est vide");
            a.show();
            return;
        }
        else
        {
            if(this.mdp.getText().equals(confirmmdp.getText()))
            {
                ResponsableLoginService a=new ResponsableLoginService();
                if(a.modifierMotDePasse(this.cin2.getText(), this.mdp.getText()))
                {
                    Alert b=new Alert(Alert.AlertType.INFORMATION);
                    b.setHeaderText("");
                    b.setContentText("Bien modifié");
                    b.setTitle("Validation !!");
                    b.show();   
                    this.mdp.setText("");
                    this.confirmmdp.setText("");
                    this.cin2.setText("");
                    return;
                }
                else
                {
                    Alert c=new Alert(Alert.AlertType.ERROR);
                    c.setHeaderText("");
                    c.setContentText("CIN Incoorect");
                    c.setTitle("Echec");
                    c.show();
                    return;
                }
            }
        }
    }
    
}
