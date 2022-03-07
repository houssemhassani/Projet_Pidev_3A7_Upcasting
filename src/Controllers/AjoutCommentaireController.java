/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entites.Commentaire;
import Services.GererCommentaireService;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ghassen-pc
 */
public class AjoutCommentaireController implements Initializable {

    @FXML
    private Button BtnAjouter;
   
    @FXML
    private TextArea tfCantent;
    @FXML
    private Label pub_id;
    @FXML
    private Label citoyen_id;
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
    private Button ajoutpubbtn;
    @FXML
    private Button btnChart;
    @FXML
    private Button btnRatingg;
    @FXML
    private Button btnC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void addCommentaire(MouseEvent event) throws IOException {
        
    }

    @FXML
    private void addCommentaire(ActionEvent event) throws IOException {
        Commentaire c = new Commentaire();
        
       c.setContent(tfCantent.getText());
        if (tfCantent.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Content : champ obligaoire");
        }
        
        GererCommentaireService gr = new GererCommentaireService();
       
        
       
       
       // p.setCitoyen_id(1);
       GererCommentaireService.ajouterCitoyenCommentaire(c);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/publications.fxml"));
                   Parent root = loader.load();
                  
                   PublicationsController controller =  loader.getController();
                   
                   this.BtnAjouter.getScene().setRoot(root);
    }
    public  void setPub(String s)
    {
        this.pub_id.setText(s);
    }
     public  void setcit(String s)
    {
        this.citoyen_id.setText(s);
    }

    @FXML
    private void Ajoutpub(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Ajoutpub.fxml"));
                   Parent root = loader.load();
                  
                  
                   this.ajoutpubbtn.getScene().setRoot(root);
    }

   
   

    @FXML
    private void hundelChart(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Chart.fxml"));
                   Parent root = loader.load();
                  
                  
                   this.btnChart.getScene().setRoot(root);
    }

    @FXML
    private void hundelRatingg(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Rating.fxml"));
                   Parent root = loader.load();
                  
                  
                   this.btnRatingg.getScene().setRoot(root);
    }

    
   
    

    
    @FXML
    private void NavC(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AjoutCommentaire.fxml"));
                   Parent root = loader.load();
                  
                  
                   this.btnC.getScene().setRoot(root);
    }
    
}
