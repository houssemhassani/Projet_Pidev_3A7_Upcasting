/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entites.Commentaire;
import Entites.Publication;
import Services.GererCommentaireService;
import Services.GererPublicationService;
import Views.AjoutpubController;
import com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.NamespaceVersion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.xml.bind.DatatypeConverter;

/**
 * FXML Controller class
 *
 * @author Ghassen-pc
 */
public class PublicationsController implements Initializable {

    @FXML
    private TableView<Publication> publications;
    @FXML
    private TableColumn<Publication, String> statut;
    @FXML
    private TableColumn<Publication, String> image;
    @FXML
    private TableView<Commentaire> commentaires;
    @FXML
    private TableColumn<Commentaire, String> nomcmnt;
    @FXML
    private TableColumn<Commentaire, String> cotentcmnt;
    @FXML
    private TableColumn<Publication, Integer> id;
    @FXML
    private Button ajoutpubbtn;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
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
    private Button btnChart;
    @FXML
    private Button btnRatingg;
  
    @FXML
    private Button btnAdd;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GererPublicationService v =  new  GererPublicationService();
        
            
            ArrayList arrayList=(ArrayList)v.getPublicationsNonConfirmee();
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
            publications.setItems(observablelist);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            statut.setCellValueFactory(new PropertyValueFactory<>("status"));
            image.setCellValueFactory(new PropertyValueFactory<>("photo"));
            //publications.refresh();
           // cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
           // email.setCellValueFactory(new PropertyValueFactory<>("email"));
   
    }    

    @FXML
    private void comments(MouseEvent event) {
        
        this.commentaires.setVisible(true);
        ObservableList<Publication> r,f;
        ObservableList<Commentaire> c,d;
        GererCommentaireService Ann=new GererCommentaireService();
        c=commentaires.getItems();
        d=commentaires.getSelectionModel().getSelectedItems();
        f=publications.getItems();
        r=publications.getSelectionModel().getSelectedItems();
        Publication pp=new Publication();
        Commentaire cc=new Commentaire();
        for(Publication p:r)
        {
           pp=p;
           
        }
        for(Commentaire e:d) {
           cc=e;
            
        }
        System.out.println(pp.toString());
        System.out.println(cc.toString());
        
        GererCommentaireService v =  new  GererCommentaireService();
        
            ArrayList arrayList=(ArrayList)v.getCommentaires(pp);
            
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
            commentaires.setItems(observablelist);
            nomcmnt.setCellValueFactory(new PropertyValueFactory<>("id_citoyen"));
            cotentcmnt.setCellValueFactory(new PropertyValueFactory<>("content"));
           // image.setCellValueFactory(new PropertyValueFactory<>("photo"));
        
    }

    @FXML
    private void Ajoutpub(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Ajoutpub.fxml"));
                   Parent root = loader.load();
                  
                   AjoutpubController controller =  loader.getController();
                   this.ajoutpubbtn.getScene().setRoot(root);
    }

    @FXML
    private void modifbtn(ActionEvent event) throws IOException {
        ObservableList<Publication> r,f;
        
        f=publications.getItems();
        r=publications.getSelectionModel().getSelectedItems();
        Publication pp=new Publication();
        for(Publication A : r){
            pp=A;
        }
        
        System.out.println(String.valueOf(pp.getId()));
        
        //c.setid(String.valueOf(pp.getId()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/modifier.fxml"));
                   Parent root = loader.load();
                  
                   ModifierController cont=loader.getController();
                   cont.setid(String.valueOf(pp.getId()));
                   cont.setSTATUS(pp.getStatus());
                   this.ajoutpubbtn.getScene().setRoot(root);
        
           

    }

    @FXML
    private void supprimbtn(ActionEvent event) {
        ObservableList<Publication> r,f;
        
        f=publications.getItems();
        r=publications.getSelectionModel().getSelectedItems();
        Publication pp=new Publication();
        for(Publication A : r){
            pp=A;
        }
        GererPublicationService gp=new GererPublicationService();
        System.out.println(pp.getId());
        gp.supprimerPublication(pp.getId());
        f.remove(pp);
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
    private void Add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AjoutCommentaire.fxml"));
                   Parent root = loader.load();
                  
                  
                   this.btnAdd.getScene().setRoot(root);
    }


 
    
    
}
