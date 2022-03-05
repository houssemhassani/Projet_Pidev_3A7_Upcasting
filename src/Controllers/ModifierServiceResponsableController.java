/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Responsable;
import Services.GererResponsableService;
import Services.GererServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.A;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierServiceResponsableController implements Initializable {

    @FXML
    private Button update;
    @FXML
    private Button annuler;
    @FXML
    private Button gererpublication;
    @FXML
    private Button gerercitoyens;
    @FXML
    private Button gererresponsable;
    @FXML
    private Button gereremployee;
    @FXML
    private Button gererservice;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField cin;
    @FXML
    private ComboBox<String> service;
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
    @FXML
    private Label nomlabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cin.setDisable(true);
        ArrayList<String> servicess=new ArrayList<String>();
        GererServices e=new GererServices();
        servicess=e.getAllServices();
        this.service.getItems().addAll(servicess);
        // TODO
    }    

    @FXML
    private void update(ActionEvent event) {
        ObservableList<Responsable> r,f;
        GererResponsableService Ann=new GererResponsableService();
        Responsable A=new Responsable();
        A.setCin(cin.getText());A.setNom(nom.getText());A.setService_id(0);A.setEmail(email.getText());
        A.setPrenom(prenom.getText());A.setNom_service(service.getValue().toString());
        Ann.modifierResponsable(A);
        
    }

    @FXML
    private void annuler(ActionEvent event) {
        this.service.setPromptText("service");
    }

    @FXML
    private void gererpublication(ActionEvent event) {
        try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/publication.fxml"));
                   Parent root = loader.load();
                   //System.out.println(path);
                   QrcodeController controller =  loader.getController();
                   //controller.setImage("");
                  // controller.setNom(A.getNom()+" "+A.getPrenom());
                   this.gererpublication.getScene().setRoot(root);
               }
               catch (IOException ex) {
                        System.out.println(ex.getMessage());
               }
    }

    @FXML
    private void gerercitoyens(ActionEvent event) {
        try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Citoyens.fxml"));
                   Parent root = loader.load();
                   //System.out.println(path);
                   CitoyensController controller =  loader.getController();
                   //controller.setImage("");
                  // controller.setNom(A.getNom()+" "+A.getPrenom());
                   this.gerercitoyens.getScene().setRoot(root);
               }
               catch (IOException ex) {
                        System.out.println(ex.getMessage());
               }
    }

    @FXML
    private void gererresponsable(ActionEvent event) {
        try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/respnsables.fxml"));
                   Parent root = loader.load();
                   //System.out.println(path);
                   ResponsablesController controller =  loader.getController();
                   //controller.setImage("");
                  // controller.setNom(A.getNom()+" "+A.getPrenom());
                   this.gererresponsable.getScene().setRoot(root);
               }
               catch (IOException ex) {
                        System.out.println(ex.getMessage());
               }
    }

    @FXML
    private void gereremployee(ActionEvent event) {
        try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Employees.fxml"));
                   Parent root = loader.load();
                   //System.out.println(path);
                   EmployeesController controller =  loader.getController();
                   //controller.setImage("");
                  // controller.setNom(A.getNom()+" "+A.getPrenom());
                   this.gereremployee.getScene().setRoot(root);
               }
               catch (IOException ex) {
                        System.out.println(ex.getMessage());
               }
    }

    @FXML
    private void gererservice(ActionEvent event) {
        try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/services.fxml"));
                   Parent root = loader.load();
                   //System.out.println(path);
                   QrcodeController controller =  loader.getController();
                   //controller.setImage("");
                  // controller.setNom(A.getNom()+" "+A.getPrenom());
                   this.gererpublication.getScene().setRoot(root);
               }
               catch (IOException ex) {
                        System.out.println(ex.getMessage());
               }
    }
    public void setNom(String nom)
    {
        this.nom.setText(nom);
    }
    public void setPrenom(String prenom)
    {
        this.prenom.setText(prenom);
    }
    public void setCin(String cin )
    {
        this.cin.setText(cin);
    }
    public void setEmail(String email)
    {
        this.email.setText(email);
    }

    @FXML
    private void gereradmin(ActionEvent event) {
        try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Admins.fxml"));
                   Parent root = loader.load();
                   //System.out.println(path);
                   AdminsController controller =  loader.getController();
                   //controller.setImage("");
                  // controller.setNom(A.getNom()+" "+A.getPrenom());
                   this.gereradmin.getScene().setRoot(root);
               }
               catch (IOException ex) {
                        System.out.println(ex.getMessage());
               }
    }
    
}
