/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entites.Publication;
import Services.GererPublicationService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ghassen-pc
 */
public class ModifierController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextArea txtstatut;
    @FXML
    private Button btnmodif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Publication p=new Publication();
        p.setId(Integer.parseInt(id.getText()));
        p.setStatus(this.txtstatut.getText());
        if (txtstatut.getText().trim().isEmpty()){
            return;
        }
        GererPublicationService gp=new GererPublicationService();
        gp.modifierPhotoPublication(p);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/publications.fxml"));
                   Parent root = loader.load();
                  
                   PublicationsController controller =  loader.getController();
                   
                   this.btnmodif.getScene().setRoot(root);
        
    }
    public void setid (String id){
        this.id.setText(id); 
    }
    public void setSTATUS(String status)
    {
        this.txtstatut.setText(status);
    }
    
    
    
    
    
    
    
}
