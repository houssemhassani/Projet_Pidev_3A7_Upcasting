/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.EmployeeLoginService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Asus
 */
public class EmployeeLoginController implements Initializable {
    @FXML
    private TextField cin;
    @FXML
    private TextField mdp;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink MdpOublié;

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
        if(EmployeeLoginService.seConnecter(cin.getText(), mdp.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/HomeC.fxml"));
            Parent root = loader.load();
            HomeCController verif=loader.getController();
            this.btnLogin.getScene().setRoot(root);  
            return;
            }
        else
        {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("Mot de Passe ou cin incorrecte");
            a.setTitle("Attention !! ");
            a.show();
            this.cin.setText("");
            this.mdp.setText("");
            return ;
        }
    }
   
    @FXML
    public void MdpOublié(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/MDPOubliéeEmployee.fxml"));
                    Parent root = loader.load();
                    MDPOubiéeEmployeeController verif=loader.getController();
                    
                    this.MdpOublié.getScene().setRoot(root);  
    }
    
}
