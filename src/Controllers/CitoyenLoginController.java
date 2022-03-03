/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.AdminLoginService;
import Services.CitoyenLoginService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CitoyenLoginController implements Initializable {
    @FXML
    private TextField cin;
    @FXML
    private TextField mdp;
    private Button btnLogin;
    @FXML
    private Hyperlink inscri;
    @FXML
    private Hyperlink MdpOublié;
    @FXML
    private AnchorPane inscrippane;
    @FXML
    private Label cinlable;
    @FXML
    private Button btnlogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    public void Login(ActionEvent event) throws IOException, SQLException
    {
        CitoyenLoginService s=new CitoyenLoginService();
         if (this.cin.getText().equals("") || this.mdp.getText().equals(""))
        {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("l'un de champs est vide");
            a.setTitle("Vide!!");
            a.show();
            this.cin.setText("");
            this.mdp.setText("");
            return;
        }
        if(s.seConnecter(cin.getText(), mdp.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/HomeC.fxml"));
            Parent root = loader.load();
            HomeCController verif=loader.getController();
            this.btnlogin.getScene().setRoot(root);  
            }
        else
        {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("Mot de Passe ou cin incorrecte");
            a.setTitle("Attention !! ");
            a.show();
            this.cin.setText("");
            this.mdp.setText("");
        }
    }
    @FXML
    public void Inscri(ActionEvent event) throws IOException
    {
       try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/CitoyenInscription.fxml"));
            Parent root = loader.load();
            CitoyenInscriptionController controller =  loader.getController();
            this.inscri.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    public void MdpOublié(ActionEvent event) throws IOException
    {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/MDPOubiéeCitoyen.fxml"));
            Parent root = loader.load();
            MDPOubiéeCitoyenController controller =  loader.getController();
            this.MdpOublié.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
