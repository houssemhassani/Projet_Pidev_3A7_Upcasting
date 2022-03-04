/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Responsable;
import Entities.Service;
import Services.GererResponsableService;
import Services.GererServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutResponsableController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField cin;
    @FXML
    private Label nomservice;
    @FXML
    private PasswordField mdp;
    @FXML
    private PasswordField confirmmdp;
    @FXML
    private ComboBox<String> services;
    @FXML
    private Label cinerreur;
    @FXML
    private Label serviceeerreur;
    @FXML
    private Label confirmerreur;
    @FXML
    private TextField prenom;
    @FXML
    private TextField num_tel;
    @FXML
    private Button ajouter;
    @FXML
    private Button annuler;
    @FXML
    private Button gererresponsable;
    @FXML
    private Button gererpublication;
    @FXML
    private Button gerercitoyens;
    @FXML
    private Button gereremployee;
    @FXML
    private Button gererservice;
    @FXML
    private ImageView Exit;
    @FXML
    private Button gereradmin;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuBack;
    @FXML
    private ImageView MenuClose;
    @FXML
    private AnchorPane slider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> servicess=new ArrayList<String>();
        GererServices e=new GererServices();
        servicess=e.getAllServices();
        this.services.getItems().addAll(servicess);
    }    

    @FXML
    private void add(ActionEvent event) {
        if (services.getValue()==null)
        {
            this.serviceeerreur.setVisible(true);
            this.serviceeerreur.setText("Selectionnez un service SVP");
            
        }
        if(!confirmmdp.getText().equals(this.mdp.getText()))
        {
        
            this.confirmerreur.setVisible(true);
            this.confirmerreur.setText("Vérifier ce champs");
        }
        else{
            Responsable cit=new Responsable();
            cit.setNom(nom.getText());
            cit.setPrenom(prenom.getText());
            cit.setEmail(email.getText());
           // cit.setNum_tel((Long.parseLong(num_tel.getText())));
            cit.setCin(cin.getText());
            cit.setMot_de_passe(mdp.getText());
            cit.setNom_service(services.getValue().toString());
            GererResponsableService v=new GererResponsableService();
            if(v.ajouterResponsable(cit))
            {
                    Alert a=new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Ajout Responsable Validée");
                    a.show();    
                    try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/respnsables.fxml"));
            Parent root = loader.load();
            ResponsablesController controller =  loader.getController();
            this.ajouter.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            }
            else
            {
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("Ajout Responsable Non Validée");
                a.show();
                
            }
        }
        
    }

    @FXML
    private void annuler(ActionEvent event) {
        this.cin.setText("");
        this.nom.setText("");
        this.prenom.setText("");
        this.email.setText("");
        this.num_tel.setText("");
        this.mdp.setText("");
        this.confirmmdp.setText("");
        this.services.setPromptText("Service");
    }

    @FXML
    private void gererresponsable(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/respnsables.fxml"));
            Parent root = loader.load();
            ResponsablesController controller =  loader.getController();
            this.gererresponsable.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gererpublication(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/publications.fxml"));
            Parent root = loader.load();
            ResponsablesController controller =  loader.getController();
            this.gererpublication.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gerercitoyens(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/citoyens.fxml"));
            Parent root = loader.load();
            CitoyensController controller =  loader.getController();
            this.gerercitoyens.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gereremployee(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Employees.fxml"));
            Parent root = loader.load();
            EmployeesController controller =  loader.getController();
            this.gereremployee.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gererservice(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/services.fxml"));
            Parent root = loader.load();
            ResponsablesController controller =  loader.getController();
            this.gererservice.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gereradmin(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Admins.fxml"));
            Parent root = loader.load();
            AdminsController controller =  loader.getController();
            this.gereradmin.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
