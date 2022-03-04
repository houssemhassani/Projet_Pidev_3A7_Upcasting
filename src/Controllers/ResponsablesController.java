/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Responsable;
import Entities.Service;
import Services.GererResponsableService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ResponsablesController implements Initializable {

    @FXML
    private TableView<Responsable> table_view;
    @FXML
    private TableColumn<Responsable, String> nom;
    @FXML
    private TableColumn<Responsable, String> prenom;
    @FXML
    private TableColumn<Responsable, String> cin;
    @FXML
    private TableColumn<Responsable, String> email;
    @FXML
    private TableColumn<Responsable, String> nomService;
    @FXML
    private AnchorPane liste;
    @FXML
    private Button delete;
    @FXML
    private Button add;
    @FXML
    private Button gererpublication;
    @FXML
    private Button gerercitoyens;
    @FXML
    private Button qrcode;
    @FXML
    private Button update;
    @FXML
    private Button gereremployee;
    @FXML
    private Button recherchebynom;
    @FXML
    private TextField recherchenom;
ArrayList arrayList;
    @FXML
    private ImageView Exit;
    @FXML
    private Button gererresponsable;
    @FXML
    private Button gereradmin;
    @FXML
    private Button gererservice;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       GererResponsableService v =  new  GererResponsableService();
            
             arrayList=v.getResponsables();
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
            
           
            table_view.setItems(observablelist);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
           nomService.setCellValueFactory(new PropertyValueFactory<>("nomService"));
    }    

    @FXML
    private void delete(ActionEvent event) {
        ObservableList<Responsable> r,f;
        GererResponsableService Ann=new GererResponsableService();
        f=table_view.getItems();
        r=table_view.getSelectionModel().getSelectedItems();
        for(Responsable A : r){
           if( Ann.supprimerResponsable(A.getCin()))
            f.remove(A);
        }
    }


    @FXML
    private void add(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ajoutResponsable.fxml"));
            Parent root = loader.load();
            AjoutResponsableController controller =  loader.getController();
            
            
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
            StatController controller =  loader.getController();
            
            
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
    private void qrCode(ActionEvent event) {
        ObservableList<Responsable> r,f;
        GererResponsableService Ann=new GererResponsableService();
        f=table_view.getItems();
        r=table_view.getSelectionModel().getSelectedItems();
        for(Responsable A : r){
           String path= Ann.genererQrCode(A);
//            System.out.println(path.toString());
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
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
           }
            
        }
    }

    @FXML
    private void update(ActionEvent event) {
         try {
             ObservableList<Responsable> r,f;
             r=table_view.getSelectionModel().getSelectedItems();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/modifierServiceResponsable.fxml"));
            Parent root = loader.load();
            ModifierServiceResponsableController controller =  loader.getController();
            for(Responsable A : r){
                controller.setCin(A.getCin()); controller.setEmail(A.getEmail());
                controller.setNom(A.getNom());controller.setPrenom(A.getPrenom());
            }
            
            this.gerercitoyens.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gereremployee(ActionEvent event) {
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
    private void recherchebynom(ActionEvent event) {
        GererResponsableService v =  new  GererResponsableService();
            
             arrayList=v.getResponsablesByNom(this.recherchenom.getText());
           if(arrayList!=null) {
               ObservableList observablelist = FXCollections.observableArrayList(arrayList);
               table_view.setItems(observablelist);
               nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
               prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
               cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
               email.setCellValueFactory(new PropertyValueFactory<>("email"));
               nomService.setCellValueFactory(new PropertyValueFactory<>("nomService"));
           }
           else
           {
               Alert a=new Alert(Alert.AlertType.ERROR);
               a.setContentText("Aucun responsable avec ce nom");
               a.show();
               this.recherchenom.setText("");
               arrayList=v.getResponsables();
               ObservableList observablelist = FXCollections.observableArrayList(arrayList);
               table_view.setItems(observablelist);
               nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
               prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
               cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
               email.setCellValueFactory(new PropertyValueFactory<>("email"));
               nomService.setCellValueFactory(new PropertyValueFactory<>("nomService"));
           }
    }

    @FXML
    private void gererresponsable(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/respnsables.fxml"));
            Parent root = loader.load();
            ResponsablesController controller =  loader.getController();
            this.gerercitoyens.getScene().setRoot(root);
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

    @FXML
    private void gererservice(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/services.fxml"));
            Parent root = loader.load();
            CitoyensController controller =  loader.getController();
            this.gerercitoyens.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    
}
