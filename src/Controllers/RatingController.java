/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Ghassen-pc
 */
public class RatingController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Rating publicationRating,commentaireRationg;
    @FXML
    private Button envoyer;
    @FXML
    private ImageView Exit;
    @FXML
    private Button ajoutpubbtn;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnChart;
    @FXML
    private Button btnRatingg;
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
    }    

    @FXML
    private void hundelenvoyer(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"Publication Note" + publicationRating.getRating());
        JOptionPane.showMessageDialog(null,"Commentaire Note" + commentaireRationg.getRating());
        
    }

    @FXML
    private void Ajoutpub(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Ajoutpub.fxml"));
                   Parent root = loader.load();
                  
                  
                   this.ajoutpubbtn.getScene().setRoot(root);
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AjoutCommentaire.fxml"));
                   Parent root = loader.load();
                  
                  
                   this.btnAdd.getScene().setRoot(root);
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
    
}
