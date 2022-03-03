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
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ChoixUtilisateurController  implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView admin;
    @FXML
    private ImageView citoyen;
    @FXML
    private ImageView employee;
    @FXML
    private ImageView responsableinterface;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     *
     */
    
    @FXML
    public void adminInterface(MouseEvent event) throws IOException  {
        Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                
                //stage.setMaximized(true);
                stage.close();
                System.out.println("df");
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Views/AdminLogin.fxml")));
                stage.setScene(scene);
                stage.show();
        
    }
    @FXML
    public void citoyenInterface(MouseEvent event)throws IOException
    {
        Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                
                //stage.setMaximized(true);
                stage.close();
                System.out.println("df");
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Views/CitoyenLogin.fxml")));
                stage.setScene(scene);
                stage.show();
    }
    
    @FXML
    public void EmployeeInterface(MouseEvent event)throws IOException
    {
        Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                
                
                stage.close();
                
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Views/EmployeeLogin.fxml")));
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void interfaceresponsable(MouseEvent event) throws IOException {
         Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close(); 
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Views/ResponsableLogin.fxml")));
                stage.setScene(scene);
                stage.show();
    }
}
