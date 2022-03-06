/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Citoyen;
import Services.CitoyenLoginService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CitoyenInscriptionController implements Initializable {
    @FXML
    private ComboBox villess;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private Button inscrire;
    @FXML
    private Hyperlink login;
    @FXML
    private TextField email;
    @FXML
    private TextField cin;
    @FXML
    private TextField num_tel;
    @FXML
    private PasswordField mdp;
    @FXML
    private PasswordField confirmmdp;
    @FXML
    private Label numerreur;
    @FXML
    private Label villeerreur;
    @FXML
    private Label confirmerreur;
    @FXML
    private Label cinerreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> villes=new ArrayList<>();
        villes.add("Monastir");villes.add("Sousse");villes.add("Tunis");villes.add("Ariana");villes.add("Mannouba");
        villes.add("Ben Arous");villes.add("Bizerte");villes.add("Gasserine");villes.add("Kairouan");villes.add("Sidi Bouzid");
        villes.add("Kef");villes.add("Jandouba");villes.add("Béja");villes.add("Gafsa");villes.add("Mahdia");
        villes.add("Nabeul");villes.add("Seliana");villes.add("Kabili");villes.add("Tataouine");villes.add("Sfax");
        villes.add("Zaghouan");villes.add("Medenin");villes.add("Gabes");villes.add("Touzer");
        // TODO
        villess.getItems().addAll(villes);
    }  
    @FXML
    public void inscrire(){
        if (villess.getValue()==null)
        {
            this.villeerreur.setVisible(true);
            this.villeerreur.setText("Selectionnez une ville");
            
        }
        if(!confirmmdp.getText().equals(this.mdp.getText()))
        {
        
            this.confirmerreur.setVisible(true);
            this.confirmerreur.setText("Vérifier ce champs");
        }
        else{
            Citoyen cit=new Citoyen();
            cit.setNom(nom.getText());
            cit.setPrenom(prenom.getText());
            cit.setEmail(email.getText());
            cit.setNum_tel((Long.parseLong(num_tel.getText())));
            cit.setCin(cin.getText());
            cit.setMot_de_passe(mdp.getText());
            cit.setVille(villess.getValue().toString());
            if(CitoyenLoginService.inscrire(cit))
            {
                try {
                    Alert a=new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Inscription Validée");
                    a.show();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/VerificationInscriptionCitoyen.fxml"));
                    Parent root = loader.load();
                    VerificationInscriptionCitoyenController verif=loader.getController();
                    verif.setEmail(email.getText());
                    nom.getScene().setRoot(root);  
                } catch (IOException ex) {
                    
                }       
            }
            else
            {
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("Inscription Non Validée");
                a.show();
                
            }
        }
        
        
            
    }
    @FXML
    public void login(){
        try {
            Alert a=new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Inscription Validée");
            a.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/CitoyenLogin.fxml"));
            Parent root = loader.load();
              
            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CitoyenInscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
