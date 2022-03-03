/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class QrcodeController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private ImageView image;
    @FXML
    private Button gerercitoyen;
    @FXML
    private Button gererpublication;
    @FXML
    private Button gererresponsable;
    @FXML
    private Button gererservice;
    @FXML
    private Button gereremployee;
     
  
  
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public void setImage(String path)
    {
       Image im=new Image("file:\\C:\\Users\\Asus\\Documents\\NetBeansProjects\\Project_Pidev\\src\\images\\"+path+".jpg");
       
        
       image.setImage(im);
        
   
         }
    public void setNom(String path)
    {
                this.nom.setText(path);
    }

    @FXML
    private void gerercitoyen(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Citoyens.fxml"));
            Parent root = loader.load();
            CitoyensController controller =  loader.getController();
            this.gerercitoyen.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(QrcodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gererpublication(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/publications.fxml"));
            Parent root = loader.load();
            CitoyensController controller =  loader.getController();
            this.gererpublication.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(QrcodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gererresponsable(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/respnsables.fxml"));
            Parent root = loader.load();
            ResponsablesController controller =  loader.getController();
            this.gererresponsable.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(QrcodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gererservice(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/services.fxml"));
            Parent root = loader.load();
            CitoyensController controller =  loader.getController();
            this.gererservice.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(QrcodeController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(QrcodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
