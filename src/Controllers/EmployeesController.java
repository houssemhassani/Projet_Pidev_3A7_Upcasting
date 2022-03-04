/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Employee;
import Services.GererEmployeeService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class EmployeesController implements Initializable {

    @FXML
    private TableView<Employee> table_view;
    @FXML
    private TableColumn<Employee, String> nom;
    @FXML
    private TableColumn<Employee, String> prenom;
    @FXML
    private TableColumn<Employee, String> cin;
    @FXML
    private TableColumn<Employee, String> email;
    @FXML
    private Button delete;
    @FXML
    private Button add;
    @FXML
    private Button gererpublication;
    @FXML
    private Button gererresponsable;
    @FXML
    private Button qrcode;
    @FXML
    private Button gererservice;
    @FXML
    private Button gerercitoyen;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GererEmployeeService v =  new  GererEmployeeService();
            
            ArrayList arrayList=(ArrayList)v.getEmployees();
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
            table_view.setItems(observablelist);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
    }    

    @FXML
    private void delete(ActionEvent event) {
        ObservableList<Employee> r,f;
        GererEmployeeService Ann=new GererEmployeeService();
        f=table_view.getItems();
        r=table_view.getSelectionModel().getSelectedItems();
        for(Employee A : r){
           if( Ann.supprimerEmployee(A))
            f.remove(A);
    }
    }

    @FXML
    private void add(ActionEvent event) {
         try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ajoutEmployee.fxml"));
            Parent root = loader.load();
            AjoutEmployeeController controller =  loader.getController();
            this.add.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gererpublication(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/publications.fxml"));
            Parent root = loader.load();
            AjoutEmployeeController controller =  loader.getController();
            this.gererpublication.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void qrCode(ActionEvent event) {
        ObservableList<Employee> r,f;
        GererEmployeeService Ann=new GererEmployeeService();
        f=table_view.getItems();
        r=table_view.getSelectionModel().getSelectedItems();
        for(Employee A : r){
           String path= Ann.genererQrCode(A);
           if(path != null)
           {
               try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/qrcode.fxml"));
                   Parent root = loader.load();
                   System.out.println(path);
                   QrcodeController controller =  loader.getController();
                   controller.setImage(path);
                   controller.setNom(A.getNom()+" "+A.getPrenom());
                   this.qrcode.getScene().setRoot(root);
               }
               catch (IOException ex) {
                        System.out.println(ex.getMessage());
               }
           }
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
    private void gerercitoyen(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Citoyens.fxml"));
            Parent root = loader.load();
            CitoyensController controller =  loader.getController();
            this.gerercitoyen.getScene().setRoot(root);
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
