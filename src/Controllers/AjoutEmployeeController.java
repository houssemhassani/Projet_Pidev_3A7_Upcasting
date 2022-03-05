/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Employee;
import Services.GererAdminService;
import Services.GererEmployeeService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutEmployeeController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField cin;
    @FXML
    private PasswordField mdp;
    @FXML
    private PasswordField confirmmdp;
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
    private Button gererservice;
    private Button gereremployee;
    @FXML
    private Button gereradmin;
    @FXML
    private ImageView Exit;
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
    @FXML
    private Label cinlabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GererAdminService g=new GererAdminService();
            this.nomlabel.setText(g.getAdmin(AdminLoginController.cinn));
    }    

    @FXML
    private void add(ActionEvent event) {
        if(confirmmdp.getText().equals("") || mdp.getText().equals("") || nom.getText().equals("")
                || prenom.getText().equals("") || cin.getText().equals("")|| email.getText().equals(""))
        {
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("Remplir tous les champs SVP !!");
                a.show();
                return;
        }
        if(!confirmmdp.getText().equals(this.mdp.getText()))
        {
        
            this.confirmerreur.setVisible(true);
            this.confirmerreur.setText("Vérifier ce champs");
        }
        else{
            Employee cit=new Employee();
            cit.setNom(nom.getText());
            cit.setPrenom(prenom.getText());
            cit.setEmail(email.getText());
            //cit.set((Long.parseLong(num_tel.getText())));
            cit.setCin(cin.getText());
            cit.setMot_de_passe(mdp.getText());
            GererEmployeeService v=new GererEmployeeService();
            if(v.ajouterEmployee(cit))
            {
                try {
                    Alert a=new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Ajout Employée Validée");
                    a.show();  
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Employees.fxml"));
                    Parent root = loader.load();
                    EmployeesController controller =  loader.getController();
                    this.ajouter.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("Ajout Employée Non Validée");
                a.show();
            }
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
        this.cin.setText("");
        this.confirmmdp.setText("");
        this.email.setText("");
        this.mdp.setText("");
        this.nom.setText("");
        this.num_tel.setText("");
        this.prenom.setText("");
    }

   

    @FXML
    private void gererpublication(ActionEvent event) {
        try {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/publications.fxml"));
                    Parent root = loader.load();
                    ResponsablesController controller =  loader.getController();
                    this.gererpublication.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void gerercitoyens(ActionEvent event) {
        try {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Citoyens.fxml"));
                    Parent root = loader.load();
                    CitoyensController controller =  loader.getController();
                    this.gerercitoyens.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void gererService(ActionEvent event) {
        try {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/services.fxml"));
                    Parent root = loader.load();
                    ResponsablesController controller =  loader.getController();
                    this.gererservice.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    private void gereremployee(ActionEvent event) {
        try {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Employees.fxml"));
                    Parent root = loader.load();
                    EmployeesController controller =  loader.getController();
                    this.gereremployee.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void gereradmin(ActionEvent event) {
        try {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Admins.fxml"));
                    Parent root = loader.load();
                    EmployeesController controller =  loader.getController();
                    this.gereradmin.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void gererResponsable(ActionEvent event) {
        try {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/respnsables.fxml"));
                    Parent root = loader.load();
                    ResponsablesController controller =  loader.getController();
                    this.gererresponsable.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
