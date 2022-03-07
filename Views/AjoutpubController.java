/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.PublicationsController;
import Entites.Publication;
import Services.GererPublicationService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ghassen-pc
 */
public class AjoutpubController implements Initializable {
    @FXML
    private ImageView ivFiles;
    
    
    
    final FileChooser fc = new FileChooser();
    
    @FXML
    private TextArea tfStatut;
    @FXML
    private Button btnajout;
    @FXML
    private Button photo;
    @FXML
    private ImageView Exit;
    @FXML
    private Button ajoutpubbtn;
    @FXML
    private Button btnNav;
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
    private void handleBtnOpenFile(ActionEvent event) {
        fc.setTitle("my file chooser");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image files","*.png","*.jpg","*.gif"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
        ivFiles.setImage(new Image(file.toURI().toString()));
        
        } else{
            System.out.println("a file is invalid!!!");
        }
    }

    @FXML
    private void addPublication(ActionEvent event) throws IOException {
        Publication p = new Publication();
        
        p.setStatus(tfStatut.getText());
        if (tfStatut.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Statut : champ obligaoire");
        }
        
        GererPublicationService gr = new GererPublicationService();
        Image i=ivFiles.getImage();
        
       
        p.setPhoto(i.impl_getUrl());
        p.setCitoyen_id(1);
        GererPublicationService.ajouterPublication(p);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/publications.fxml"));
                   Parent root = loader.load();
                  
                   PublicationsController controller =  loader.getController();
                   
                   this.btnajout.getScene().setRoot(root);
                  
    }

    @FXML
    private void Ajoutpub(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Ajoutpub.fxml"));
                   Parent root = loader.load();
                  
                  
                   this.ajoutpubbtn.getScene().setRoot(root);
    }

    @FXML
    private void NAV(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AjoutCommentaire.fxml"));
                   Parent root = loader.load();
                  
                  
                   this.btnNav.getScene().setRoot(root);
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
