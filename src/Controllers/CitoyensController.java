/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Citoyen;

import Services.GererCitoyenService;
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
 * @author lv
 */
public class CitoyensController implements Initializable {

  
   
    @FXML
    private TableView<Citoyen> table_view;
    @FXML
    private AnchorPane liste;
    @FXML
    private Button delete;
    @FXML
    private Button stat;
    @FXML
    private Button add;
private Button detail;
    @FXML
    private TableColumn<Citoyen, String> nom;
    @FXML
    private TableColumn<Citoyen, String> prenom;
    @FXML
    private TableColumn<Citoyen, String> cin;
    @FXML
    private TableColumn<Citoyen, String> email;
    @FXML
    private TableColumn<Citoyen, String> ville;
    @FXML
    private Button gererpublication;
    @FXML
    private Button gererresponsable;
    @FXML
    private Button qrcode;
    @FXML
    private Button gererservice;
    @FXML
    private Button gereremployee;
    @FXML
    private Button gereradmin;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuBack;
    @FXML
    private AnchorPane slider;
    @FXML
    private ImageView Exit;
    @FXML
    private ImageView MenuClose;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GererCitoyenService v =  new  GererCitoyenService();
            
            ArrayList arrayList=(ArrayList)v.getCitoyensNonConfirmee();
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
            table_view.setItems(observablelist);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
    }    
    @FXML
    private void delete(ActionEvent event) {
        ObservableList<Citoyen> r,f;
        GererCitoyenService Ann=new GererCitoyenService();
        f=table_view.getItems();
        r=table_view.getSelectionModel().getSelectedItems();
        for(Citoyen A : r){
           if( Ann.retirerCitoyen(A))
            f.remove(A);
        }
    }
    @FXML
    private void stat(ActionEvent event)throws IOException{   
     
      try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/stat.fxml"));
            Parent root = loader.load();
            StatController controller =  loader.getController();
            this.stat.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
     }
    @FXML
    private void add(ActionEvent event)throws IOException {    
     ObservableList<Citoyen> r,f;
        GererCitoyenService Ann=new GererCitoyenService();
        f=table_view.getItems();
        r=table_view.getSelectionModel().getSelectedItems();
        for(Citoyen A : r){
           if( Ann.confirmerCitoyen(A))
            f.remove(A);
        }
    }

    @FXML
    private void gererpublication(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/publications.fxml"));
            Parent root = loader.load();
            AjoutAdminController controller =  loader.getController();
            this.gererpublication.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @FXML
    private void qrCode(ActionEvent event) {
        ObservableList<Citoyen> r,f;
        GererCitoyenService Ann=new GererCitoyenService();
        f=table_view.getItems();
        r=table_view.getSelectionModel().getSelectedItems();
        for(Citoyen A : r){
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